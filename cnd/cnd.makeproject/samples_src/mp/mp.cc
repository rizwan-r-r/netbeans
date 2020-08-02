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

#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <iostream>

int main(int argc, char**argv) {
    pid_t pid;
    pid_t f_res;

    // Prints welcome message...
    std::cout << "Welcome ..." << std::endl;
    pid=getpid();

    // Prints arguments...
    if (argc > 1) {
        f_res = fork();
        if (0 == f_res) {
            /* Child */
            pid=getpid();
            std::cout << std::endl << "PID child = " << pid << std::endl;
            _exit(0);
        }
        std::cout << "PID parent = " << pid  << "  PID child = " << f_res << std::endl;
        std::cout << std::endl << "Arguments:" << std::endl;
        for (int i = 1; i < argc; i++) {
            std::cout << i << ": " << argv[i] << std::endl;
        }
    }
    int status;
    wait(&status);
    return 0;
}