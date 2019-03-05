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

import de.uos.inf.did.abbozza.plugineditor.gui.PluginFrame;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class PluginLanguagePanel extends javax.swing.JPanel implements PluginPanel {
    
    protected PluginFrame frame;
    protected RSyntaxTextArea editor;
    protected LanguageTableModel tableModel;
    
    /**
     * Creates new form PluginInfoPanel
     */
    public PluginLanguagePanel(PluginFrame frame) {
        this.frame = frame;
        tableModel = new LanguageTableModel(this);
        
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localeComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgTable = new javax.swing.JTable();

        setName("Languages"); // NOI18N

        localeComboBox.setEditable(true);
        localeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                localeComboBoxItemStateChanged(evt);
            }
        });

        msgTable.setModel(tableModel);
        msgTable.setToolTipText("");
        msgTable.setSurrendersFocusOnKeystroke(true);
        msgTable.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(msgTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(localeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(localeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void localeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_localeComboBoxItemStateChanged
        tableModel.setLanguage((String) localeComboBox.getSelectedItem());
        msgTable.updateUI();
    }//GEN-LAST:event_localeComboBoxItemStateChanged

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> localeComboBox;
    private javax.swing.JTable msgTable;
    // End of variables declaration//GEN-END:variables

    public void setLanguages(String[] languages) {
        String oldLang = (String) localeComboBox.getSelectedItem();
        this.localeComboBox.setModel(new DefaultComboBoxModel( languages ));
        tableModel.setLanguage((String) localeComboBox.getSelectedItem());
        if ( oldLang != null ) localeComboBox.setSelectedItem(oldLang);
    }
    
    
    @Override
    public void load(Element plugin) throws IllegalPluginException {
        tableModel.load(plugin);
    }

    @Override
    public void save(Document xml, Element plugin) {
        tableModel.save(xml,plugin);
    }

}