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
package org.netbeans.modules.cnd.debugger.common2;

import java.util.Collection;
import org.netbeans.modules.cnd.debugger.common2.debugger.processlist.api.ProcessInfo;
import org.netbeans.modules.cnd.debugger.common2.debugger.processlist.api.ProcessList;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironment;

/**
 *
 */
abstract public class ProcessListAccessor {
  private static volatile ProcessListAccessor DEFAULT;

    public static ProcessListAccessor getDefault() {
        ProcessListAccessor a = DEFAULT;
        if (a != null) {
            return a;
        }

        try {
            Class.forName(ProcessList.class.getName(), true,
                    ProcessList.class.getClassLoader());
        } catch (Exception e) {
        }

        return DEFAULT;
    }

    public static void setDefault(ProcessListAccessor accessor) {
        if (DEFAULT != null) {
            throw new IllegalStateException();
        }

        DEFAULT = accessor;
    }
    
    abstract public ProcessList create(final Collection<ProcessInfo> info, ExecutionEnvironment execEnv);    
}