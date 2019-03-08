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
package de.uos.inf.did.abbozza.plugineditor.gui;

import de.uos.inf.did.abbozza.plugineditor.IllegalPluginException;
import de.uos.inf.did.abbozza.plugineditor.PluginEditor;
import de.uos.inf.did.abbozza.plugineditor.XMLTool;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class PluginOptionsPanel extends PluginFeaturePanel {
    
    public PluginOptionsPanel(PluginFrame frame) {
        super(frame);
        this.setName("Options");
        featureCheckBox.setText("Provide Options");
    }
    
        /**
     * Load the feature element from the given plugin element.
     * 
     * @param plugin
     * @throws IllegalPluginException 
     */
    @Override
    public void load(Element plugin) throws IllegalPluginException {
        if (plugin == null) {
            featureCheckBox.setSelected(false);
            editor.setEditable(false);
            editor.setEnabled(false);
            editor.setText(PluginEditor.getResource("templates/options.tmpl"));
        } else {
            featureCheckBox.setSelected(true);
            editor.setEditable(true);
            editor.setEnabled(true);
            Element el = XMLTool.getFirstElement(plugin, "options");
            if ( el != null ) editor.setText(XMLTool.childrenToString(el));
        }
    }


    /**
     * Save the features to the given document.
     * @param xml
     * @param plugin 
     */
    @Override
    public void save(Document xml, Element plugin) {
        if ( !featureCheckBox.isSelected() ) {
            return;
        }
        
        if (plugin != null) {
            Element feature = (Element) xml.createElement("options");
            Document doc = XMLTool.documentFromString("<xml>" + editor.getText() + "</xml>");
            Element root = (Element) doc.getFirstChild();
            
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++ ) {
                try {
                    Element child = (Element) children.item(i);
                    if (child != null) {
                        root.removeChild(child);
                    }
                    xml.adoptNode(child);
                    feature.appendChild(child);
                } catch (ClassCastException ex) {}
            }
            
            plugin.appendChild(feature);
        }
    }

    
}
