/*
 * Copyright 2019 michael.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.uos.inf.did.abbozza.plugineditor;

/**
 *
 * @author michael
 */
public class FileEntry {
    public static final int TYPE_JS = 0;
    public static final int TYPE_JAVA = 1;
    public static final int TYPE_INSTALL = 2;
    public static final int TYPE_MISC = 3;
    
    public static final int JTYPE_OTHER = 0;
    public static final int JTYPE_HTTP = 1;
    public static final int JTYPE_PANEL = 2;
    public static final int JTYPE_LISTENER = 3;

    protected String name;

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public int getJavaType() {
        return javaType;
    }

    public String getPrefix() {
        return prefix;
    }
    protected int type;
    protected String target;
    protected int javaType;
    protected String prefix;
        
    public FileEntry(String name, String target, int type) {
        this.name = name;
        this.target = target;
        this.type = type;
        this.javaType = JTYPE_OTHER;
        this.prefix = null;
    }

    public FileEntry(String name, String target, int type, int jtype, String prefix) {
        this.name = name;
        this.target = target;
        this.type = type;
        this.javaType = jtype;
        this.prefix = prefix;
    }
    
    public String toString() {
        return name;
    }
   
}
