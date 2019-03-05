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
import de.uos.inf.did.abbozza.plugineditor.PluginPanel;
import de.uos.inf.did.abbozza.plugineditor.XMLTool;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author michael
 */
public class PluginInfoPanel extends javax.swing.JPanel implements PluginPanel {

    protected PluginFrame frame;
    
    /**
     * Creates new form PluginInfoPanel
     */
    public PluginInfoPanel(PluginFrame frame) {
        this.frame = frame;
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        parentComboBox = new javax.swing.JComboBox<>();
        helpButton = new javax.swing.JButton();
        idField = new javax.swing.JFormattedTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setName("General"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 303));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("ID");

        jLabel3.setText("Display Name");

        nameField.setToolTipText("<html>The displayed name.</html>");

        descriptionArea.setColumns(20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setRows(5);
        descriptionArea.setToolTipText("<html>\nA short description of the functionality of the plugin.\n</html>");
        descriptionArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descriptionArea);

        jLabel5.setText("Parent option");

        parentComboBox.setEditable(true);
        parentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "advanced", "devices", "" }));
        parentComboBox.setSelectedItem("");
        parentComboBox.setToolTipText("<html>\nThe id of the parent option in the settings<br/>\ntree for the plugin option.\n</html>");

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/uos/inf/did/abbozza/plugineditor/gui/help.png"))); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        try {
            idField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        idField.setToolTipText("<html>\nThis is the id of the plugin.<br/>\nIt is used as part of the URL to access the<br/>\ncontents and services provided by teh plugin.<br/>\n<br/>\nIts maximal length is 32 characters and it may only<br/>\nconsist of letters and digits.\n</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameField)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(idField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parentComboBox, 0, 233, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(parentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        HelpFrame.open("infopanel");
    }//GEN-LAST:event_helpButtonActionPerformed
 
    /**
     * Load the information from the plugin Element
     * @param plugin The plugin element
     * @throws IllegalPluginException 
     */
    public void load(Element plugin) throws IllegalPluginException {
        if ( plugin != null ) {
            idField.setText(XMLTool.getStringAttribute(plugin, "id", "<id required>"));
            parentComboBox.setSelectedItem(XMLTool.getStringAttribute(plugin, "parent", ""));
            nameField.setText(XMLTool.getTextContent(plugin, "name").trim());
            descriptionArea.setText(XMLTool.getTextContent(plugin, "description").trim());  
        } else {
            idField.setText("new_plugin");
            parentComboBox.setSelectedItem("");
            nameField.setText("A newly created plugin");
            descriptionArea.setText("A description for this new plugin");              
        }
    }
    
    /**
     * Save the information to the given element.
     * @param xml The document containing the element
     * @param plugin The element
     */
    public void save(Document xml, Element plugin) {
        plugin.setAttribute("id", idField.getText());
        plugin.setAttribute("parent", (String) parentComboBox.getSelectedItem());

        Element nm = (Element) xml.createElement("name");
        nm.setTextContent(nameField.getText());
        plugin.appendChild(nm);

        Element desc = (Element) xml.createElement("description");
        desc.setTextContent(descriptionArea.getText());
        plugin.appendChild(desc);       
    }
        
    /**
     * Get the current id entry.
     * @return The id
     */
    public String getId() {
        return idField.getText();
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JButton helpButton;
    private javax.swing.JFormattedTextField idField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JComboBox<String> parentComboBox;
    // End of variables declaration//GEN-END:variables
}
