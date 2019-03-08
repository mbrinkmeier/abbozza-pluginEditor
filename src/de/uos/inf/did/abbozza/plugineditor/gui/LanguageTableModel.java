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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class LanguageTableModel extends AbstractTableModel {    

    private HashSet<String> ids;
    private String[] idArray;
    private HashSet<String> languages;
    private String[] languageArray;
    private Hashtable<String,String> msgs;
    private PluginLanguagePanel panel;
    private String language;
    
    
    
    public LanguageTableModel(PluginLanguagePanel panel) {
        this.panel = panel;
        ids = new HashSet<String>();
        languages = new HashSet<String>();
        msgs = new Hashtable<String,String>();
    }    
    
    public void load(Element plugin) {
        ids.clear();
        languages.clear();
        msgs.clear();
        
        Element loc = XMLTool.getFirstElement(plugin, "locales");
        
        // Parse the languages
        NodeList langs = loc.getElementsByTagName("language");
        for ( int i = 0; i < langs.getLength(); i++ ) {
            Element lang = (Element) langs.item(i);
            String language = lang.getAttribute("id");
            languages.add(language);
            
            NodeList children = lang.getChildNodes();
            for ( int j = 0 ; j < children.getLength(); j++ ) {
                try {
                    Element child = (Element) children.item(j);
                    if ( child.getTagName().equals("msg") ) {
                        String id = child.getAttribute("id");
                        ids.add(id);
                        msgs.put(id +"_" + language, child.getTextContent() );
                    } 
                } catch (ClassCastException ex) {}
            }
        }
        
        idArray = new String[ids.size()];
        ids.toArray(idArray);
               
        languageArray = new String[languages.size()];
        languages.toArray(languageArray);        
        panel.setLanguages(languageArray);
    }
    
    
    public void save(Document xml, Element plugin) {
        Element loc = xml.createElement("locales");

        for ( String lang : languageArray ) {
            Element langEl = xml.createElement("language");
            langEl.setAttribute("id", lang);
            loc.appendChild(langEl);
            
            for ( String id : idArray ) {
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
    
    
    @Override
    public int getRowCount() {
        return ids.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {   
        if ( columnIndex == 0 ) {
            return idArray[rowIndex];
        } else {
            String id = idArray[rowIndex];
            return msgs.get(id + "_" + language);
        }    
    }
 
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {   
        String val = value.toString();
        String id = idArray[rowIndex];
        
                
        if ( columnIndex == 0 ) {
            // If the id is changed, keep the messages for all languages
            idArray[rowIndex] = val;
            for ( String lang : languages ) {
                String msg = msgs.get(id + "_" + lang);
                msgs.remove(id + "_" + lang);
                if (msg != null) msgs.put(val + "_" + lang,msg);
            }
            ids.remove(id);
            ids.add(val);
        } else {
            // Just change the message
            msgs.put(id + "_" + language,val);
        }    
    }

    
    public void setLanguage(String lang) {
        if ( !languages.contains( lang ) ) {
            languages.add(lang);
            languageArray = new String[languages.size()];
            languages.toArray(languageArray);        
            panel.setLanguages(languageArray);
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
    public void addId(String id) {
        ids.add(id);
        idArray = new String[ids.size()];
        ids.toArray(idArray);
        System.out.println(ids.size());
    }
    
    public void delId(int id) {
        if ( (id < 0) || ( id>= idArray.length ) ) return;
        String msg = idArray[id];
        ids.remove(msg);
        idArray = new String[ids.size()];
        ids.toArray(idArray);
    }
}
