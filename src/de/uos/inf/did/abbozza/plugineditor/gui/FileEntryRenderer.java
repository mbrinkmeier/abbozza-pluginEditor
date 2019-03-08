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

import de.uos.inf.did.abbozza.plugineditor.FileEntry;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author michael
 */
public class FileEntryRenderer implements ListCellRenderer<FileEntry> {

    protected static Image jsImage;
    protected static Image javaImage;
    protected static Image installImage;
    protected static Image miscImage;
    
    public FileEntryRenderer() {
        super();
        try {
            if (jsImage == null) {
                jsImage = ImageIO.read(getClass().getResourceAsStream("text-x-javascript.png")).getScaledInstance(24,24,Image.SCALE_DEFAULT);
            }
            if (javaImage == null) {
                javaImage = ImageIO.read(getClass().getResourceAsStream("text-x-java.png"));
                javaImage = javaImage.getScaledInstance(24,24,Image.SCALE_DEFAULT);
            }
            if (installImage == null) {
                installImage = ImageIO.read(getClass().getResourceAsStream("text-x-csrc.png"));
                installImage = installImage.getScaledInstance(24,24,Image.SCALE_DEFAULT);
            }
            if (miscImage == null) {
                miscImage = ImageIO.read(getClass().getResourceAsStream("unknown.png"));
                miscImage = miscImage.getScaledInstance(24,24,Image.SCALE_DEFAULT);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileEntryRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends FileEntry> list, FileEntry value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel panel = new JPanel();
        if ( !isSelected ) {
            panel.setBackground(Color.white);
        } else if ( !value.isDeletable() ) {
            panel.setBackground(new Color(255,230,230));
        } else {
            panel.setBackground(new Color(230,230,230));            
        }
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String txt = value.getName();
        
        if ( value.getType() == FileEntry.TYPE_JAVA ) {
            switch ( value.getJavaType() ) {
            case FileEntry.JTYPE_HTTP:
                txt = txt + " [HttpHandler]";
                break;
            case FileEntry.JTYPE_PANEL:
                txt = txt + " [MonitorPanel]";
                break;
            case FileEntry.JTYPE_LISTENER:
                txt = txt + " [MonitorListener]";
                break;
            }
        }
        JLabel name = new JLabel(txt);
        panel.add(name, FlowLayout.LEFT);
        JLabel icon = null;
        switch ( value.getType() ) {
            case FileEntry.TYPE_JS :
                icon = new JLabel(new ImageIcon(jsImage));
                break;
            case FileEntry.TYPE_JAVA :
                icon = new JLabel(new ImageIcon(javaImage));
                break;
            case FileEntry.TYPE_INSTALL :
                icon = new JLabel(new ImageIcon(installImage));
                break;
            default :
                icon = new JLabel(new ImageIcon(miscImage));
                break;
        }
        panel.add(icon, FlowLayout.LEFT);
        return panel;
    }
    
}
