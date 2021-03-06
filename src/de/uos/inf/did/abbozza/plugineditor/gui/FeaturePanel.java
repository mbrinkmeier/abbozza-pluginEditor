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
import java.awt.Font;
import javax.swing.text.Highlighter;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class FeaturePanel extends javax.swing.JPanel implements PluginPanel {

    protected static String defaultFeature
            = "<category name=\"category\" id=\"cat.CATEGORY\" color=\"#FF00FF\">\n"
            + "   <block type=\"an_block\"/>\n"
            + "</category>";

    protected PluginFrame frame;
    protected RSyntaxTextArea editor;

    /**
     * Creates new form PluginInfoPanel
     */
    public FeaturePanel(PluginFrame frame) {
        this.frame = frame;
        initComponents();

        editor = new RSyntaxTextArea(50, 120);
        editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        editor.setCodeFoldingEnabled(true);
        editor.setTabSize(3);
        Font font = new Font("Courier New", Font.PLAIN, 14);
        if (!font.getFontName().equals("Courier New")) {
            font = new Font("DejaVu Sans Mono", Font.PLAIN, 14);
        }
        editor.setFont(font);

        Highlighter sourceHighlighter = editor.getHighlighter();

        RTextScrollPane editorPanel = new RTextScrollPane(editor);
        editorPane.add(editorPanel, java.awt.BorderLayout.CENTER);

        CompletionProvider provider = createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.install(editor);
        ac.setAutoActivationDelay(500);
        ac.setAutoActivationEnabled(true);

        editor.setEnabled(false);
        editor.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        featureCheckBox = new javax.swing.JCheckBox();
        editorPane = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();

        setName("Feature"); // NOI18N

        featureCheckBox.setText("Provide feature");
        featureCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                featureCheckBoxActionPerformed(evt);
            }
        });

        editorPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editorPane.setLayout(new java.awt.BorderLayout());

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/uos/inf/did/abbozza/plugineditor/gui/help.png"))); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editorPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(featureCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(featureCheckBox)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editorPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void featureCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_featureCheckBoxActionPerformed
        if (featureCheckBox.isSelected()) {
            editor.setEditable(true);
            editor.setEnabled(true);
        } else {
            editor.setEditable(false);
            editor.setEnabled(false);
        }
    }//GEN-LAST:event_featureCheckBoxActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        HelpFrame.open("features");
    }//GEN-LAST:event_helpButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel editorPane;
    protected javax.swing.JCheckBox featureCheckBox;
    protected javax.swing.JButton helpButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Create a simple provider that adds some calliope related completions.
     */
    private CompletionProvider createCompletionProvider() {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        /*
      try {
        InputStream autoCompleteXML = abbozza.getJarHandler().getInputStream("/lib/ac_" + abbozza.getSystem() + ".xml");
        provider.loadFromXML(autoCompleteXML);  
      } catch (IOException ex) {
      }
         */
        return provider;
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
            editor.setText(PluginEditor.getResource("templates/feature.tmpl"));
        } else {
            featureCheckBox.setSelected(true);
            editor.setEditable(true);
            editor.setEnabled(true);
            Element el = XMLTool.getFirstElement(plugin, "feature");
            if ( el != null ) {
                editor.setText(XMLTool.elementsToString(el , "category"));
            }
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
            Element feature = (Element) xml.createElement("feature");
            feature.setAttribute("id", "feat." + frame.getId());
            feature.setAttribute("option", frame.getId() + ".enabled");
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

    @Override
    public boolean isBasePanel() {
        return true;
    }

    @Override
    public boolean build() { return true; }

    
}
