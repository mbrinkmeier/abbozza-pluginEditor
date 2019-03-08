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
package de.uos.inf.did.abbozza.plugineditor.systems;

import de.uos.inf.did.abbozza.plugineditor.gui.PluginFrame;

/**
 *
 * @author michael
 */
public class ArduinoManager extends SystemManager {

    private ArduinoLibraryPanel libraryPanel;
    
    public ArduinoManager(PluginFrame frame) {
        super(frame);
    }

    @Override
    public String getSystem() {
        return "arduino";
    }

    @Override
    public String getDisplayName() {
        return "abbozza! Arduino";
    }
    
    
    @Override
    public void initPanels() {
        libraryPanel = new ArduinoLibraryPanel(frame);
        System.out.println(libraryPanel.getName());
        frame.addInfoContainerPanel(libraryPanel, false);
    }
    
}
