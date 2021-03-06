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
import de.uos.inf.did.abbozza.plugineditor.GUITool;
import de.uos.inf.did.abbozza.plugineditor.IllegalPluginException;
import de.uos.inf.did.abbozza.plugineditor.PluginEditor;
import de.uos.inf.did.abbozza.plugineditor.XMLTool;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class FilesPanel extends javax.swing.JPanel implements PluginPanel {
        
    protected PluginFrame frame;
    protected DefaultListModel<FileEntry> files;
    
    /**
     * Creates new form PluginFilesPanel
     * @param frame
     */
    public FilesPanel(PluginFrame frame) {
        files = new DefaultListModel();
        this.frame = frame;
        
        initComponents();
        
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    openButtonActionPerformed(null);
                }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();

        setName("Files"); // NOI18N

        fileList.setModel(files);
        fileList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fileList.setToolTipText("");
        fileList.setCellRenderer(new FileEntryRenderer());
        fileList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fileListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(fileList);

        addButton.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        addButton.setText("+");
        addButton.setToolTipText("Add a file");
        addButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        delButton.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        delButton.setText("-");
        delButton.setToolTipText("Remove selected file");
        delButton.setAlignmentY(0.0F);
        delButton.setEnabled(false);
        delButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        delButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        delButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit Entry");
        editButton.setToolTipText("Edit the file entry");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/uos/inf/did/abbozza/plugineditor/gui/help.png"))); // NOI18N
        helpButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        openButton.setText("Open");
        openButton.setToolTipText("Open file editor");
        openButton.setEnabled(false);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handler for the add button.
     * @param evt 
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        FileDialog dialog = new FileDialog(frame,true, null);
        GUITool.centerWindow(dialog);
        dialog.setVisible(true);
        
        if ( dialog.state == 0 ) {
            FileEntry entry = new FileEntry(dialog.getName(),dialog.getTarget(),dialog.getFileType(),dialog.getJavaType(),dialog.getPrefix());
            if ( frame.createFile(entry) ) {
                files.addElement(entry);
                frame.setChanged(true);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * Handler for the delete button.
     * @param evt 
     */
    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        if ( fileList.getSelectedIndex() >= 0 ) {
            FileEntry entry = (FileEntry) files.elementAt(fileList.getSelectedIndex());
            if ( entry.isDeletable() ) {
                files.remove(fileList.getSelectedIndex());
                fileList.setSelectedIndex(-1);
                frame.setChanged(true);
            }
        }
    }//GEN-LAST:event_delButtonActionPerformed

    /**
     * Activate or deactivate some GUI buttons, If the file list is changed.
     * 
     * @param evt 
     */
    private void fileListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_fileListValueChanged
        if ( fileList.getSelectedIndex() >= 0 ) {
            FileEntry entry = (FileEntry) files.elementAt(fileList.getSelectedIndex());            
            if ( entry.isDeletable() ) {
                editButton.setEnabled(true);
                delButton.setEnabled(true);
                openButton.setEnabled(true);
            } else {
                editButton.setEnabled(false);
                delButton.setEnabled(false);            
                openButton.setEnabled(true);                
            }
        } else {
            editButton.setEnabled(false);
            delButton.setEnabled(false);            
            openButton.setEnabled(false);
        }
    }//GEN-LAST:event_fileListValueChanged

    /**
     * Handler for the open Button.
     * Opens a file panel for the selected entry.
     * 
     * @param evt 
     */
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        try {
            FileEntry entry = files.get(fileList.getSelectedIndex());
            File file = new File(frame.getPluginPath() + "/" + entry.getName() );
            String mimetype = Files.probeContentType(file.toPath());
            if ( (mimetype != null ) && mimetype.startsWith("image")) {
                
            } else {
                FileEditorPanel fileEditor = new FileEditorPanel(entry,this.frame);
                if ( (entry.getType() == FileEntry.TYPE_JS) || ((mimetype != null ) && mimetype.contains("javascript")) ) {
                    fileEditor.setSyntaxStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
                } else if ((mimetype != null ) && mimetype.contains("csrc")) {
                    fileEditor.setSyntaxStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                } else if ( (entry.getType() == FileEntry.TYPE_JAVA) || ((mimetype != null ) && mimetype.contains("java")) ) {
                    fileEditor.setSyntaxStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                }  else if ( (mimetype != null ) && mimetype.contains("xml")) {
                    fileEditor.setSyntaxStyle(SyntaxConstants.SYNTAX_STYLE_XML);                    
}
                frame.addFileContainerPanel(fileEditor, true);
            }
        } catch (IOException ex) {
            Logger.getLogger(FilesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    /**
     * Handler for the editButton.
     * Opens an FileEntry edit dialog.
     * @param evt 
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int idx = fileList.getSelectedIndex();
        FileEntry entry = files.get(idx);
        
        if ( !entry.isDeletable() ) return;
        
        FileDialog dialog = new FileDialog(frame,true, entry);
        GUITool.centerWindow(dialog);
        dialog.setVisible(true);
        
        if ( dialog.state == 0 ) {
            String oldName = entry.getName();
            FileEntry newEntry = new FileEntry(dialog.getName(),dialog.getTarget(),dialog.getFileType(),dialog.getJavaType(),dialog.getPrefix());
            if ( renameFile(oldName,newEntry.getName()) || !oldName.equals(newEntry.getName()) ) {
            } else {
                return;
            }
            files.set(idx , newEntry);
            frame.setChanged(true);
        }
        
    }//GEN-LAST:event_editButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        HelpFrame.open("filespanel");
    }//GEN-LAST:event_helpButtonActionPerformed

    /**
     * Load all files from the plugin xml
     * @param plugin
     * @throws IllegalPluginException 
     */
    @Override
    public void load(Element plugin) throws IllegalPluginException {
        // Look for javascript files
        NodeList jss = plugin.getElementsByTagName("js");
        for (int i = 0; i < jss.getLength(); i++) {
            Element js = (Element) jss.item(i);
            String name = js.getAttribute("file");
            FileEntry entry = new FileEntry(name,null,FileEntry.TYPE_JS);
            files.addElement(entry);
        }
        
        // Look for install files
        NodeList installs = plugin.getElementsByTagName("install");
        for (int i = 0; i < installs.getLength(); i++) {
            Element install = (Element) installs.item(i);
            String name = install.getAttribute("file");
            String target = install.getAttribute("target");
            FileEntry entry = new FileEntry(name,target,FileEntry.TYPE_INSTALL);
            files.addElement(entry);
        }
        
        // Look for java files
        NodeList javas = plugin.getElementsByTagName("java");
        for (int i = 0; i < javas.getLength(); i++) {
            Element java = (Element) javas.item(i);
            String name = java.getAttribute("file");
            FileEntry entry = new FileEntry(name,null,FileEntry.TYPE_JAVA);
            files.addElement(entry);
        }
        
        // Look for HttpHandlers
        javas = plugin.getElementsByTagName("handler");
        for (int i = 0; i < javas.getLength(); i++) {
            Element java = (Element) javas.item(i);
            String name = java.getAttribute("class");
            FileEntry entry = new FileEntry(name + ".java",null,FileEntry.TYPE_JAVA,FileEntry.JTYPE_HTTP,"");
            files.addElement(entry);
        }

        // Look for MonitorPanels
        javas = plugin.getElementsByTagName("monitorpanel");
        for (int i = 0; i < javas.getLength(); i++) {
            Element java = (Element) javas.item(i);
            String name = java.getAttribute("class");
            String prefix = java.getAttribute("prefix");
            FileEntry entry = new FileEntry(name + ".java",null,FileEntry.TYPE_JAVA,FileEntry.JTYPE_PANEL,prefix);
            files.addElement(entry);
        }

        // Look for MonitorListeners
        javas = plugin.getElementsByTagName("monitorlistener");
        for (int i = 0; i < javas.getLength(); i++) {
            Element java = (Element) javas.item(i);
            String name = java.getAttribute("class");
            String prefix = java.getAttribute("prefix");
            FileEntry entry = new FileEntry(name + ".java",null,FileEntry.TYPE_JAVA,FileEntry.JTYPE_LISTENER, prefix);
            files.addElement(entry);
        }

        // Look for misc files
        NodeList miscs = plugin.getElementsByTagName("misc");
        for (int i = 0; i < miscs.getLength(); i++) {
            Element misc = (Element) javas.item(i);
            String name = misc.getAttribute("file");
            FileEntry entry = new FileEntry(name,null,FileEntry.TYPE_MISC);
            files.addElement(entry);
        }
     
        // fileList.setModel(files);
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
        FileEntry entry;
        
        // Find requirements tag
        req = XMLTool.getFirstElement(plugin,"requirements");
        
        for ( Object obj : files.toArray() ) {
            entry = (FileEntry) obj;
            switch ( entry.getType() ) {
                case FileEntry.TYPE_WORLD:
                    break;
                    
                case FileEntry.TYPE_JS :
                    file = xml.createElement("js");
                    file.setAttribute("file", entry.getName());
                    plugin.appendChild(file);
                    break;
                    
                case FileEntry.TYPE_JAVA :
                switch (entry.getJavaType()) {
                    case FileEntry.JTYPE_OTHER:
                        file = xml.createElement("java");
                        file.setAttribute("file", entry.getName());
                        break;
                    case FileEntry.JTYPE_HTTP:
                        file = xml.createElement("handler");
                        file.setAttribute("class", entry.getName().replaceAll(".java",""));
                        break;
                    case FileEntry.JTYPE_PANEL:
                        file = xml.createElement("monitorpanel");
                        file.setAttribute("class", entry.getName().replaceAll(".java",""));
                        file.setAttribute("prefix", entry.getPrefix());
                        break;
                    default:
                        file = xml.createElement("monitstaticorlistener");
                        file.setAttribute("class", entry.getName().replaceAll(".java",""));                        
                        file.setAttribute("prefix", entry.getPrefix());
                        break;
                }
                    plugin.appendChild(file);
                    break;

                case FileEntry.TYPE_INSTALL :
                    if ( req == null ) {
                        req = xml.createElement("requirements");
                        plugin.appendChild(req);
                    }
                    file = xml.createElement("install");
                    file.setAttribute("file", entry.getName());
                    file.setAttribute("target", entry.getTarget());
                    req.appendChild(file);
                    break;
                case FileEntry.TYPE_MISC :
                    file = xml.createElement("misc");
                    file.setAttribute("file", entry.getName());
                    plugin.appendChild(file);
                    break;
            }
        }
    }

    /**
     * Create an file.
     * Returns true if the creation succeeds or the file exists already, false otherwise.
     * 
     * @param entry
     * @return
     */
    /*
    protected boolean createFile(FileEntry entry) {
        String template = "templates/javascript.tmpl";
        File file = new File(frame.getPluginPath() + "/" + entry.getName());
        try {
            System.out.println(file.toPath().getParent());
            try {
                frame.setStatusMsg("Creating file " + file.toPath().getParent() );
                Files.createDirectories(file.toPath().getParent());
            } catch ( FileAlreadyExistsException aeex ) {
                PluginEditor.showErrorMessage("Couldn't create directory " + file.toPath().getParent() );
                return false;
            }
            Files.createFile(file.toPath());
            switch ( entry.getType() ) {
                case FileEntry.TYPE_WORLD:
                    template = null;
                    break;
                case FileEntry.TYPE_JS:
                    template = "templates/javascript.tmpl";
                    break;
                case FileEntry.TYPE_INSTALL:
                    template = null;
                    break;
                case FileEntry.TYPE_JAVA:
                    switch ( entry.getJavaType() ) {
                        case FileEntry.JTYPE_HTTP :
                            template = "templates/PluginHttpHandler.tmpl";
                            break;
                        case FileEntry.JTYPE_PANEL :
                            template = "templates/PluginMonitorPanel.tmpl";
                            break;
                        case FileEntry.JTYPE_LISTENER :
                            template = "templates/PluginMonitorListener.tmpl";
                            break;
                        case FileEntry.JTYPE_OTHER :
                            template = "templates/Empty.tmpl";
                            break;
                    }
                    break;
                case FileEntry.TYPE_MISC:
                    template = null;
                    break;
            }
            if ( template != null ) {
                frame.writeTemplate(template , entry.getName());
            }
        } catch (IOException ex) {
            PluginEditor.showErrorMessage("Couldn't create " + file.getAbsolutePath());
            return false;
        }
        return true;
    }
    */
    
    /**
     * Rename a file. 
     * Returns true if the ranming succeeds, false otherwise.
     * 
     * @param old Old name of the file
     * @param now New name of the file
     * @return 
     */
    protected boolean renameFile(String old, String now) {
        File oldFile = new File(frame.getPluginPath() + "/" + old);
        File newFile = new File(frame.getPluginPath() + "/" + now);
        try {
            Files.move(oldFile.toPath(),newFile.toPath());
        } catch (IOException ex) {
            PluginEditor.showErrorMessage("Couldn't move " + oldFile.getAbsolutePath() + " to " + newFile.getAbsolutePath());
            return false;
        }
        return true;
        
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton delButton;
    private javax.swing.JButton editButton;
    private javax.swing.JList<FileEntry> fileList;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isBasePanel() {
        return true;
    }

    
    /**
     * Build all java files in teh plugin.
     * 
     */
    @Override
    public boolean build() {
        FileEntry entry = null;
        boolean aborted = false;
    
        for ( Object obj : files.toArray() ) {
            entry = (FileEntry) obj;
            if ( entry.getType() == FileEntry.TYPE_JAVA  ) {
                if ( !buildFile(entry) ) {
                    aborted = true;
                    break;
                }
            }
        }
        
        if ( !aborted ) {
            frame.setStatusMsg("Build successfull!");
            return true;
        }
        
        return false;
    }   

    /**
     * Compile a java file.
     * @param entry 
     */
    public boolean buildFile(FileEntry entry) {
        frame.setStatusMsg("Compiling " + entry.getName() + " ...");

        String arguments = "";
        String system = frame.getSystem();
        if ( system.equals("arduino") && (PluginEditor.getArduinoJar() != null) )  {
            arguments = PluginEditor.getArduinoJar().getName(); 
        } else if ( system.equals("calliopeC")  && (PluginEditor.getCalliopeJar() != null) ) {
            arguments = PluginEditor.getCalliopeJar().getName();             
        } else if ( system.equals("worlds")  && (PluginEditor.getWorldsJar() != null) ) {
            arguments = PluginEditor.getWorldsJar().getName();             
        }
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, out, err, "-cp" , arguments, frame.getPluginPath() + "/" + entry.getName() );
        if ( result == 0 ) {
            frame.setStatusMsg("Compliation of " + entry.getName() + " successfull!");
            return true;
        } else {
            frame.setErrorMsg("Error during compilation!");
            MessageFrame msgFrame = new MessageFrame("Error during compilation of " + entry.getName() , err.toString());
            GUITool.centerWindow(msgFrame);
            return false;
        }
    }

}
