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

import de.uos.inf.did.abbozza.plugineditor.GUITool;
import de.uos.inf.did.abbozza.plugineditor.PluginEditor;
import java.awt.Point;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author michael
 */
public class HelpFrame extends javax.swing.JFrame {

    protected static HelpFrame instance = null;
    
    public static void open(String item) {
        if (instance == null) {
            instance = new HelpFrame();
            GUITool.centerWindow(instance);
        } else {
            setItem(item);
        }
        instance.setVisible(true);
    }
    
    public static void hideFrame() {
        if (instance == null) {
            return;
        }
        instance.setVisible(false);
    }
    

    public static void toggle(String item) {
        if ( instance == null ) {
            open(item);
            setItem(item);
        } else {
            if ( instance.isVisible() ) {
                instance.setVisible(false);
            } else {
                setItem(item);
                instance.setVisible(true);                
            }
        }
    }

    private HTMLDocument helpDoc;
    private StyleSheet style;

    /**
     * Creates new form HelpFrame
     */
    public HelpFrame() {
        helpDoc = new HTMLDocument();

        initComponents();

        helpPane.setContentType("text/html");
        helpDoc = (HTMLDocument) helpPane.getDocument();
        StyleSheet style = new StyleSheet();
        style.importStyleSheet(HelpFrame.class.getResource("help/plugineditor.css"));
        helpDoc.getStyleSheet().addStyleSheet(style);
        // helpPane.setDocument(helpDoc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        helpPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("abbozza! Plugin Editor - Help");
        setAutoRequestFocus(false);
        setLocationByPlatform(true);

        scrollPane.setBorder(null);

        helpPane.setEditable(false);
        scrollPane.setViewportView(helpPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane helpPane;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    public static void setItem(String item) {
        if ( instance == null ) return;
        String content = PluginEditor.getResource("gui/help/" + item + ".html");
        instance.helpPane.setText(content);
        instance.helpPane.setCaretPosition(0);
    }
    
}
