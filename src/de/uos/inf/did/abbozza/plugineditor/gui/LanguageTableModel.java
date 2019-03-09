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

import de.uos.inf.did.abbozza.plugineditor.XMLTool;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class LanguageTableModel extends AbstractTableModel {    

    private final ArrayList<String> ids;             // The ids of locale entries
    private final ArrayList<String> languages;       // The languages
    private final HashMap<String,String> msgs;       // The messages
    private final LanguagePanel panel;               // The language panel
    private String language;                         // The current language
    
    
    
    public LanguageTableModel(LanguagePanel panel) {
        this.panel = panel;
        ids = new ArrayList<>();
        languages = new ArrayList<>();
        msgs = new HashMap<>();
    }    
    
    /**
     * Load the locale froim the given plugin element.
     * 
     * @param plugin The plugin element.
     */
    public void load(Element plugin) {
        ids.clear();
        languages.clear();
        msgs.clear();
        
        Element loc = XMLTool.getFirstElement(plugin, "locales");
        
        // Parse the languages
        NodeList langs = loc.getElementsByTagName("language");
        for ( int i = 0; i < langs.getLength(); i++ ) {
            Element lang = (Element) langs.item(i);
            String langId = lang.getAttribute("id");
            languages.add(langId);
            
            NodeList children = lang.getChildNodes();
            for ( int j = 0 ; j < children.getLength(); j++ ) {
                try {
                    Element child = (Element) children.item(j);
                    if ( child.getTagName().equals("msg") ) {
                        String id = child.getAttribute("id");
                        if ( !ids.contains(id) ) {
                            ids.add(id);
                        }
                        msgs.put(id +"_" + langId, child.getTextContent() );
                    } 
                } catch (ClassCastException ex) {}
            }
        }      
        panel.setLanguages(languages);
    }
    
    /**
     * Save teh language entries to the given Document and Element.
     * 
     * @param xml The XML-Document
     * @param plugin  The parent Element
     */
    public void save(Document xml, Element plugin) {
        Element loc = xml.createElement("locales");

        for ( String lang : languages ) {
            Element langEl = xml.createElement("language");
            langEl.setAttribute("id", lang);
            loc.appendChild(langEl);
            
            for ( String id : ids ) {
                String msg = msgs.get(id + "_" + lang);
                if ( msg != null ) {
                    Element msgEl = xml.createElement("msg");
                    msgEl.setAttribute("id", id);
                    msgEl.setTextContent(msg);
                    langEl.appendChild(msgEl);
                }
            }
        }
        plugin.appendChild(loc);
    }
    
    
    /**
     * REturns the number of ids.
     * 
     * @return The number of ids.
     */
    @Override
    public int getRowCount() {
        return ids.size();
    }

    /**
     * Return the number of columns
     * 
     * @return The number of columns
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * Get the value at the specified position.
     * 
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {   
        if ( columnIndex == 0 ) {
            return ids.get(rowIndex);
        } else {
            String id = ids.get(rowIndex);
            return msgs.get(id + "_" + language);
        }    
    }
 
    /**
     * Set the language entry according to the row number and the current 
     * language.
     * 
     * @param value The value to be set
     * @param rowIndex The row index
     * @param columnIndex The column index
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {   
        String val = value.toString();
        String id = ids.get(rowIndex);
                    
        if ( columnIndex == 0 ) {
            // If the id is changed, keep the messages for all languages
            ids.set(rowIndex,val);
            for ( String lang : languages ) {
                String msg = msgs.get(id + "_" + lang);
                msgs.remove(id + "_" + lang);
                if (msg != null) msgs.put(val + "_" + lang,msg);
            }
        } else {
            // Just change the message
            msgs.put(id + "_" + language,val);
        }    
    }

    /**
     * Set the current display language.
     * @param lang The language.
     */
    public void setLanguage(String lang) {
        if ( !languages.contains( lang ) ) {
            languages.add(lang);
            panel.setLanguages(languages);
        }
        language = lang;
    }
    
    /**
     * Return the column name
     * 
     * @param column The index of the column
     * @return 
     */
    @Override
    public String getColumnName(int column) {
        if ( column == 0 ) {
            return "ID";
        } else {
            return language;
        }
    }
    
    /**
     * Every cell is editable.
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    /**
     * Add an Id to the list of ids.
     * @param id 
     */
    public void newId(String id) {
        if ( !ids.contains(id) ) {
            ids.add(id);
        }
    }
    
    
    /**
     * Delete a specific entry.
     * 
     * @param id 
     */
    public void delId(int id) {
        if ( (id < 0) || ( id>= ids.size() ) ) return;
        String msg = ids.get(id);
        ids.remove(msg);
    }
}
