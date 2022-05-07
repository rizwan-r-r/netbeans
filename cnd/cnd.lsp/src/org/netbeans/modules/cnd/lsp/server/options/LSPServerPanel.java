/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.cnd.lsp.server.options;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import org.netbeans.modules.cnd.lsp.server.ClangdOptions;
import org.netbeans.modules.cnd.lsp.server.ClangdProcess;
import org.openide.util.Exceptions;

final class LSPServerPanel extends javax.swing.JPanel {

    private static final Logger LOG = Logger.getLogger(LSPServerPanel.class.getName());

    private final static String CLANGD_URI = "https://clangd.llvm.org"; // NOI18N

    private final LSPServerOptionsPanelController controller;
    private final FileFilter executableFilesFileFilter;
    private final FileFilter normalFilesFileFilter;
    private JFileChooser fileChooser;
    private DefaultComboBoxModel<ClangdOptions.ClangdLogLevel> levelModel;

    private final class DocumentListenerWatcher implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (controller.isChanged()) {
                controller.changed();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (controller.isChanged()) {
                controller.changed();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

    }

    private final DocumentListenerWatcher documentWatcher = new DocumentListenerWatcher();
    private boolean documentListenersAdded = false;

    LSPServerPanel(LSPServerOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        levelModel = new DefaultComboBoxModel<>(ClangdOptions.ClangdLogLevel.values());
        selLogLevel.setModel(levelModel);

        this.executableFilesFileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.canExecute();
            }

            @Override
            public String getDescription() {
                String description = org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.lblExecutableFiles"); // NOI18N
                return description;
            }
        };
        this.normalFilesFileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return true;
            }

            @Override
            public String getDescription() {
                String description = org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.lblNormalFiles"); // NOI18N
                return description;
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnGroupLogOutput = new javax.swing.ButtonGroup();
        btnGroupLogLevel = new javax.swing.ButtonGroup();
        lblClangdExecutable = new javax.swing.JLabel();
        txtInstallPath = new javax.swing.JTextField();
        cmdFind = new javax.swing.JButton();
        cmdBrowse = new javax.swing.JButton();
        lblLogLevel = new javax.swing.JLabel();
        selLogLevel = new javax.swing.JComboBox<>();
        lblClangdLog = new javax.swing.JLabel();
        optLoToStderr = new javax.swing.JRadioButton();
        optLogToFile = new javax.swing.JRadioButton();
        txtLogFile = new javax.swing.JTextField();
        cmdBrowseLog = new javax.swing.JButton();
        pnlSpacer = new javax.swing.JPanel();
        lblClangdDownload = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(lblClangdExecutable, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.lblClangdExecutable.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        add(lblClangdExecutable, gridBagConstraints);

        txtInstallPath.setText(org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.txtInstallPath.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 24, 8, 8);
        add(txtInstallPath, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(cmdFind, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.cmdFind.text")); // NOI18N
        cmdFind.setToolTipText(org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.cmdFind.toolTipText")); // NOI18N
        cmdFind.setMinimumSize(new java.awt.Dimension(159, 38));
        cmdFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFindActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(cmdFind, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(cmdBrowse, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.cmdBrowse.text")); // NOI18N
        cmdBrowse.setToolTipText(org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.cmdBrowse.toolTipText")); // NOI18N
        cmdBrowse.setMinimumSize(new java.awt.Dimension(159, 38));
        cmdBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBrowseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 17);
        add(cmdBrowse, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblLogLevel, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.lblLogLevel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        add(lblLogLevel, gridBagConstraints);

        selLogLevel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selLogLevelItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 24, 8, 8);
        add(selLogLevel, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(lblClangdLog, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.lblClangdLog.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        add(lblClangdLog, gridBagConstraints);

        btnGroupLogOutput.add(optLoToStderr);
        optLoToStderr.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(optLoToStderr, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.optLoToStderr.text")); // NOI18N
        optLoToStderr.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                optLoToStderrItemStateChanged(evt);
            }
        });
        optLoToStderr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optLoToStderrActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 24, 8, 8);
        add(optLoToStderr, gridBagConstraints);

        btnGroupLogOutput.add(optLogToFile);
        org.openide.awt.Mnemonics.setLocalizedText(optLogToFile, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.optLogToFile.text")); // NOI18N
        optLogToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optLogToFileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 24, 0, 0);
        add(optLogToFile, gridBagConstraints);

        txtLogFile.setText(org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.txtLogFile.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 24, 8, 8);
        add(txtLogFile, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(cmdBrowseLog, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.cmdBrowseLog.text")); // NOI18N
        cmdBrowseLog.setToolTipText(org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.cmdBrowseLog.toolTipText")); // NOI18N
        cmdBrowseLog.setMinimumSize(new java.awt.Dimension(159, 38));
        cmdBrowseLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBrowseLogActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 16);
        add(cmdBrowseLog, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 10.0;
        add(pnlSpacer, gridBagConstraints);

        lblClangdDownload.setFont(lblClangdDownload.getFont().deriveFont(lblClangdDownload.getFont().getSize()-2f));
        lblClangdDownload.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(lblClangdDownload, org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.lblClangdDownload.text")); // NOI18N
        lblClangdDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClangdDownload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblClangdDownloadMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        add(lblClangdDownload, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFindActionPerformed
        String PATH = System.getenv("PATH"); // NOI18N
        if (PATH == null) {
            String message = org.openide.util.NbBundle.getMessage(LSPServerPanel.class, "LSPServerPanel.NO_PATH"); // NOI18N
            JOptionPane.showMessageDialog(this, message);
            return;
        }
        String[] parts = PATH.split(File.pathSeparator);
        File clangd = null;

        for (String part : parts) {
            File directory = new File(part);
            File unix = new File(directory, "clangd");
            if (unix.exists() && unix.isFile() && unix.canExecute()) {
                clangd = unix;
                break;
            }
            File windows = new File(directory, "clangd.exe");
            if (windows.exists() && windows.isFile() && windows.canExecute()) {
                clangd = windows;
                break;
            }
        }
        controller.changed();
        txtInstallPath.setText(clangd == null ? "" : clangd.getAbsolutePath());
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdFindActionPerformed

    private JFileChooser getFileChooser() {
        if (fileChooser == null) {
            File userHome = new File(System.getProperty("user.home")); // NOI18N
            fileChooser = new JFileChooser(userHome);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        }
        return fileChooser;
    }

    private void cmdBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBrowseActionPerformed
        getFileChooser().setFileFilter(executableFilesFileFilter);
        int result = getFileChooser().showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File executable = getFileChooser().getSelectedFile();
            if (executable.isFile() && executable.canExecute()) {
                txtInstallPath.setText(executable.getAbsolutePath());
            }
        }
    }//GEN-LAST:event_cmdBrowseActionPerformed

    private void optLoToStderrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optLoToStderrActionPerformed
        updateLogChannel();
    }//GEN-LAST:event_optLoToStderrActionPerformed

    private void updateLogChannel() {
        controller.changed();
        boolean stderr = optLoToStderr.isSelected();
        if (stderr) {
            txtLogFile.setEnabled(false);
            cmdBrowseLog.setEnabled(false);
        } else {
            txtLogFile.setEnabled(true);
            cmdBrowseLog.setEnabled(true);
        }
    }

    private void cmdBrowseLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBrowseLogActionPerformed
        getFileChooser().setFileFilter(normalFilesFileFilter);
        int result = getFileChooser().showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File logFile = getFileChooser().getSelectedFile();
            if (logFile.isFile()) {
                controller.changed();
                txtLogFile.setText(logFile.getAbsolutePath());
            }
        }
    }//GEN-LAST:event_cmdBrowseLogActionPerformed

    private void optLogToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optLogToFileActionPerformed
        updateLogChannel();
    }//GEN-LAST:event_optLogToFileActionPerformed

    private void lblClangdDownloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClangdDownloadMouseClicked

        try {
            URI uri = new URI(CLANGD_URI);
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }

    }//GEN-LAST:event_lblClangdDownloadMouseClicked

    private void selLogLevelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selLogLevelItemStateChanged
        controller.changed();
    }//GEN-LAST:event_selLogLevelItemStateChanged

    private void optLoToStderrItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optLoToStderrItemStateChanged
        updateLogChannel();
    }//GEN-LAST:event_optLoToStderrItemStateChanged

    void load() {
        ClangdOptions options = ClangdOptions.load();
        selLogLevel.setSelectedItem(options.getLevel());

        File file = options.getLogFile();
        if (file == null) {
            optLoToStderr.setSelected(true);
        } else {
            optLogToFile.setSelected(true);
            txtLogFile.setText(file.getAbsolutePath());
        }
        updateLogChannel();

        File clangd = options.getClangdExecutable();
        txtInstallPath.setText(clangd == null ? "" : clangd.getAbsolutePath());

        if (!documentListenersAdded) {
            documentListenersAdded = true;
            txtLogFile.getDocument().addDocumentListener(documentWatcher);
            txtInstallPath.getDocument().addDocumentListener(documentWatcher);
        }

    }

    void store() {
        LOG.log(Level.FINE, "Storing ClangdOptions");
        ClangdOptions options = ClangdOptions.load();
        ClangdOptions.ClangdLogLevel level = (ClangdOptions.ClangdLogLevel) selLogLevel.getSelectedItem();
        options.setLevel(level);

        boolean stderr = optLoToStderr.isSelected();
        if (stderr) {
            options.setLogFile(null);
        } else {
            String file = txtLogFile.getText();
            file = file == null ? "" : file.trim();
            options.setLogFile("".equals(file) ? null : new File(file));
        }

        String clangdString = txtInstallPath.getText();
        clangdString = clangdString == null ? "" : clangdString.trim();
        options.setClangdExecutable("".equals(clangdString) ? null : new File(clangdString));

        if (documentListenersAdded) {
            documentListenersAdded = false;
            txtLogFile.getDocument().removeDocumentListener(documentWatcher);
            txtInstallPath.getDocument().removeDocumentListener(documentWatcher);
        }

        ClangdOptions.save(options);

        LOG.log(Level.INFO, "Restarting clangd...");
        ClangdProcess.getInstance().restart();
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupLogLevel;
    private javax.swing.ButtonGroup btnGroupLogOutput;
    private javax.swing.JButton cmdBrowse;
    private javax.swing.JButton cmdBrowseLog;
    private javax.swing.JButton cmdFind;
    private javax.swing.JLabel lblClangdDownload;
    private javax.swing.JLabel lblClangdExecutable;
    private javax.swing.JLabel lblClangdLog;
    private javax.swing.JLabel lblLogLevel;
    private javax.swing.JRadioButton optLoToStderr;
    private javax.swing.JRadioButton optLogToFile;
    private javax.swing.JPanel pnlSpacer;
    private javax.swing.JComboBox<ClangdOptions.ClangdLogLevel> selLogLevel;
    private javax.swing.JTextField txtInstallPath;
    private javax.swing.JTextField txtLogFile;
    // End of variables declaration//GEN-END:variables
}
