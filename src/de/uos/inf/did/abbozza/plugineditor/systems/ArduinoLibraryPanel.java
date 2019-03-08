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

import de.uos.inf.did.abbozza.plugineditor.FileEntry;
import de.uos.inf.did.abbozza.plugineditor.IllegalPluginException;
import de.uos.inf.did.abbozza.plugineditor.XMLTool;
import de.uos.inf.did.abbozza.plugineditor.gui.PluginFrame;
import de.uos.inf.did.abbozza.plugineditor.gui.PluginPanel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class ArduinoLibraryPanel extends javax.swing.JPanel implements PluginPanel {

    protected PluginFrame frame;
    protected DefaultListModel libraries;

    /**
     * The standradr constructor.
     * 
     * @param frame 
     */
    public ArduinoLibraryPanel(PluginFrame frame){
        this.frame = frame;
        initComponents();
        
        // libraryList.setCellEditor(this);
        
        libraries = new DefaultListModel();
        libraryList.setModel(libraries);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        libraryList = new javax.swing.JList<>();
        libraryField = new javax.swing.JTextField();

        setName("Libraries"); // NOI18N

        buttonPanel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        addButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addButton.setText("+");
        addButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(addButton);

        delButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        delButton.setText("-");
        delButton.setEnabled(false);
        delButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        delButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(delButton);

        libraryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        libraryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                libraryListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(libraryList);

        libraryField.setText("<library name>");
        libraryField.setToolTipText("<html>The name of an Arduino library<br/>required by the plugin</html>");
        libraryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libraryFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(libraryField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(libraryField))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        if ( libraryList.getSelectedIndex() >= 0 ) {
            libraries.removeRange(libraryList.getMinSelectionIndex(),libraryList.getMaxSelectionIndex());
        }
    }//GEN-LAST:event_delButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        libraries.addElement(libraryField.getText());
    }//GEN-LAST:event_addButtonActionPerformed

    private void libraryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_libraryListValueChanged
        if ( libraryList.getSelectedIndex() >= 0 ) {
            delButton.setEnabled(true);
        } else {
            delButton.setEnabled(false);            
        }
    }//GEN-LAST:event_libraryListValueChanged

    private void libraryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libraryFieldActionPerformed
        libraries.addElement(libraryField.getText());
    }//GEN-LAST:event_libraryFieldActionPerformed

    /**
     * Load all files from the plugin xml
     * @param plugin
     * @throws IllegalPluginException 
     */
    @Override
    public void load(Element plugin) throws IllegalPluginException {
        // Look for install files
        NodeList installs = plugin.getElementsByTagName("library");
        for (int i = 0; i < installs.getLength(); i++) {
            Element install = (Element) installs.item(i);
            String name = install.getAttribute("name");
            libraries.addElement(name);
        }
    }

    
    /**
     * Save the files to the given document.
     * 
     * @param xml The Document
     * @param plugin The Plugin element
     */
    @Override
    public void save(Document xml, Element plugin) {
        Element req = null;
        Element file;
        String name;
        
        // Find requirements tag
        req = XMLTool.getFirstElement(plugin,"requirements");
        
        for ( Object obj : libraries.toArray() ) {
            name = (String) obj;
            if ( req == null ) {
                req = xml.createElement("requirements");
                plugin.appendChild(req);
            }
            file = xml.createElement("library");
            file.setAttribute("name", name);
            req.appendChild(file);
        }
    }
    

    @Override
    public boolean isBasePanel() {
        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton delButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField libraryField;
    private javax.swing.JList<String> libraryList;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean build() { return true; }

}
