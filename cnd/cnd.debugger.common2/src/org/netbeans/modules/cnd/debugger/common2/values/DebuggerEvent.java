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

package org.netbeans.modules.cnd.debugger.common2.values;

public class DebuggerEvent extends Enum {
    private DebuggerEvent(String name) {
	super(name, Catalog.get(name));
    }

    public static final DebuggerEvent ATTACH =
	new DebuggerEvent("Debugger_attach");		// NOI18N
    public static final DebuggerEvent DETACH =
	new DebuggerEvent("Debugger_detach");		// NOI18N

    private static final DebuggerEvent[] enumeration = {
	ATTACH, DETACH,
    };

    private static String[] tags;


    public static String[] getTags() {
	tags = makeTagsFrom(tags, enumeration);
	return tags;
    }

    public static DebuggerEvent byTag(String s) {
	return (DebuggerEvent) byTagHelp(enumeration, s);
    }

    public static DebuggerEvent valueOf(String s) {
	return (DebuggerEvent) valueOfHelp(enumeration, s);
    }
}