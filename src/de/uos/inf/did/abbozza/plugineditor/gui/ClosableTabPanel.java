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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author michael
 */
public class ClosableTabPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClosableTabPanel
     */
    public ClosableTabPanel(JTabbedPane tabbedPane, JPanel panel, String title) {
        initComponents();

        titleLabel.setText(title);
        closeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                tabbedPane.remove(panel);
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 3, 0);
        flowLayout1.setAlignOnBaseline(true);
        setLayout(flowLayout1);

        titleLabel.setText("jLabel1");
        add(titleLabel);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/uos/inf/did/abbozza/plugineditor/close.png"))); // NOI18N
        closeButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        add(closeButton);
    }// </editor-fold>//GEN-END:initComponents


    public void setTitle(String title) {
        titleLabel.setText(title);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
