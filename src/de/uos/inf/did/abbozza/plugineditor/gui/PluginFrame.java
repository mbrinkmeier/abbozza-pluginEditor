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
import de.uos.inf.did.abbozza.plugineditor.systems.ArduinoManager;
import de.uos.inf.did.abbozza.plugineditor.systems.CalliopeManager;
import de.uos.inf.did.abbozza.plugineditor.systems.MonitorManager;
import de.uos.inf.did.abbozza.plugineditor.systems.SystemManager;
import de.uos.inf.did.abbozza.plugineditor.systems.WorldsManager;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.jar.JarOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author michael
 */
public class PluginFrame extends javax.swing.JFrame implements DocumentListener, 
                                                               ItemListener, 
                                                               ListDataListener, 
                                                               TableModelListener {

    // protected Plugin plugin;
    protected String pluginPath;

    protected InfoPanel infoPanel;
    protected FeaturePanel featurePanel;
    protected OptionsPanel optionsPanel;
    protected LanguagePanel languagePanel;
    protected FilesPanel filesPanel;

    private boolean changed;

    protected HashMap<String, SystemManager> systems;
    protected SystemManager systemManager;

    protected Image errorIcon;
    
    public Collection<SystemManager> getSystems() {
        return systems.values();
    }

    /**
     * Creates new PluginFrame.
     *
     * @param pluginPath The path for the plugin
     * @throws IllegalPluginException
     */
    public PluginFrame(String pluginPath) throws IllegalPluginException {
        // Register system managers
        systems = new LinkedHashMap();
        systems.put("arduino", new ArduinoManager(this));
        systems.put("calliopeC", new CalliopeManager(this));
        systems.put("worlds", new WorldsManager(this));
        systems.put("monitor", new MonitorManager(this));
        
        try {
            errorIcon = ImageIO.read(PluginEditor.class.getResourceAsStream("error.png"));
        } catch (IOException ex) {
            errorIcon = null;
        }

        // If the plugin path is null, ask for a plugin path
        if (pluginPath == null) {
            this.pluginPath = askForPlugin();

            // Exit, if no path is selected.
            if (this.pluginPath == null) {
                System.exit(1);
            }
        } else {
            this.pluginPath = pluginPath;
        }

        Document xml = openPluginPath();

        initComponents();

        try {
            this.setIconImage(ImageIO.read(PluginEditor.class.getResourceAsStream("abbozza_plugineditor_icon.png")));
        } catch (IOException ex) {}
        
        changed = false;

        infoPanel = new InfoPanel(this);
        infoContainer.add(infoPanel);

        featurePanel = new FeaturePanel(this);
        fileContainer.add(featurePanel);

        optionsPanel = new OptionsPanel(this);
        fileContainer.add(optionsPanel);

        languagePanel = new LanguagePanel(this);
        fileContainer.add(languagePanel);

        filesPanel = new FilesPanel(this);
        infoContainer.add(filesPanel);

        load(xml);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        footerPanel = new javax.swing.JPanel();
        statusIcon = new javax.swing.JLabel();
        statusMsg = new javax.swing.JLabel();
        headerPanel = new javax.swing.JPanel();
        pathField = new javax.swing.JTextField();
        changeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        infoContainer = new javax.swing.JTabbedPane();
        fileContainer = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        newMenuItem = new javax.swing.JMenuItem();
        renameItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        closeMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        settingsItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        quitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        buildItem = new javax.swing.JMenuItem();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("abbozza! Plugin Editor");
        setMinimumSize(new java.awt.Dimension(440, 500));

        footerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        footerPanel.setMaximumSize(new java.awt.Dimension(32767, 20));
        footerPanel.setMinimumSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addComponent(statusIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addGroup(footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusMsg))
                .addGap(0, 0, 0))
        );

        getContentPane().add(footerPanel, java.awt.BorderLayout.SOUTH);

        headerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        headerPanel.setMaximumSize(new java.awt.Dimension(32767, 24));
        headerPanel.setMinimumSize(new java.awt.Dimension(0, 24));
        headerPanel.setPreferredSize(new java.awt.Dimension(105, 24));

        pathField.setEditable(false);
        pathField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pathField.setText("<to be loaded>");
        pathField.setBorder(null);
        pathField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathFieldActionPerformed(evt);
            }
        });

        changeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeLabel.setText("*");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addComponent(pathField, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(changeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeLabel)
                    .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        getContentPane().add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 320));
        jPanel1.setPreferredSize(new java.awt.Dimension(946, 450));

        infoContainer.setMinimumSize(new java.awt.Dimension(5, 400));

        fileContainer.setMinimumSize(new java.awt.Dimension(5, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(infoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fileContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
            .addComponent(infoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open plugin");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(openMenuItem);

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setText("New plugin");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(newMenuItem);

        renameItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        renameItem.setText("Reload");
        renameItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameItemActionPerformed(evt);
            }
        });
        jMenu1.add(renameItem);
        jMenu1.add(jSeparator2);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuItem);
        jMenu1.add(jSeparator1);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        closeMenuItem.setText("Close");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(closeMenuItem);
        jMenu1.add(jSeparator3);

        settingsItem.setText("Settings");
        settingsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsItemActionPerformed(evt);
            }
        });
        jMenu1.add(settingsItem);
        jMenu1.add(jSeparator4);

        quitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        quitMenuItem.setText("Quit");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(quitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Plugin");

        buildItem.setText("Build Jar");
        buildItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildItemActionPerformed(evt);
            }
        });
        jMenu2.add(buildItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        Document newXml;
        String oldPluginPath;
        String newPluginPath = askForPlugin();
        if (newPluginPath != null) {
            oldPluginPath = pluginPath;
            pluginPath = newPluginPath;
            try {
                newXml = openPluginPath();
                load(newXml);
            } catch (IllegalPluginException ex) {
                PluginEditor.showErrorMessage(ex.getMsg());
                pluginPath = oldPluginPath;
            }
        }
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed
        quit();
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        quit();
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        Document newXml;
        String oldPluginPath;
        String newPluginPath = askForPlugin();
        if (newPluginPath != null) {
            oldPluginPath = pluginPath;
            pluginPath = newPluginPath;
            try {
                newXml = openPluginPath();
                load(newXml);
            } catch (IllegalPluginException ex) {
                PluginEditor.showErrorMessage(ex.getMsg());
                pluginPath = oldPluginPath;
            }
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        setStatusMsg("Saving ...");
        try {
            save();
            setStatusMsg("Saved successfully!");
        } catch (IOException ex) {
            setStatusMsg("Error during saving!");
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void renameItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_renameItemActionPerformed

    private void settingsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsItemActionPerformed
        PluginEditor.openConfigDialog();
    }//GEN-LAST:event_settingsItemActionPerformed

    private void buildItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildItemActionPerformed
        buildJar();
    }//GEN-LAST:event_buildItemActionPerformed

    private void pathFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem buildItem;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JMenuItem closeMenuItem;
    protected javax.swing.JTabbedPane fileContainer;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headerPanel;
    protected javax.swing.JTabbedPane infoContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JTextField pathField;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JMenuItem renameItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem settingsItem;
    private javax.swing.JLabel statusIcon;
    private javax.swing.JLabel statusMsg;
    // End of variables declaration//GEN-END:variables

    /**
     * Ask the user for a plugin path.
     *
     * @return The selected path. If the selection is aborted null is returned.
     */
    protected String askForPlugin() {
        String dir = null;

        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Plugin directory";
            }
        };
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Choose directory of plugin");
        chooser.setApproveButtonText("Choose directory");

        if (pluginPath != null) {
            chooser.setCurrentDirectory((new File(pluginPath)).getParentFile());
        }

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File pluginDir = chooser.getSelectedFile();
            dir = pluginDir.getAbsolutePath();
        }

        return dir;
    }

    /**
     * Open the plugin path and return the describing plugin.xml.
     *
     * @return The Document representing plugin.xml.
     * @throws IllegalPluginException If the plugin is corrupted or can't be
     * created.
     */
    protected Document openPluginPath() throws IllegalPluginException {
        Document xml = null;
        File directory = new File(pluginPath);

        // If the directory doesn't exist ...
        if (!directory.exists()) {
            // ... create it
            try {
                Files.createDirectories(directory.toPath());
            } catch (IOException ex) {
                throw new IllegalPluginException("Can't create " + directory.getAbsolutePath());
            }
        }

        // Open the plugin.xml
        File xmlFile = new File(directory, "plugin.xml");
        if (!xmlFile.exists()) {
            // If it doesn't exist, create it from template
            askForSystem();
            writeTemplate("templates/plugin.tmpl", "plugin.xml");
        }

        try {
            xml = XMLTool.getXml(xmlFile.toURI().toURL());
        } catch (MalformedURLException ex) {
            throw new IllegalPluginException("File " + xmlFile.getAbsolutePath() + " is no vaild XML file");
        }

        PluginEditor.setLastPluginPath(pluginPath);

        return xml;
    }

    /**
     * Load the plugin from the given XML-Document.
     *
     * @param xml The document
     */
    protected void load(Document xml) throws IllegalPluginException {
        Element plugin = null;

        if (xml != null) {
            NodeList plugins = xml.getElementsByTagName("plugin");
            if (plugins.getLength() == 0) {
                throw new IllegalPluginException("No plugin tag defined in plugin.xml");
            }

            try {
                plugin = (Element) plugins.item(0);
            } catch (ClassCastException ex) {
                throw new IllegalPluginException("No plugin tag defined in plugin.xml");
            }

            String system = plugin.getAttribute("system");
            systemManager = systems.get(system);
            if (systemManager == null) {
                throw new IllegalPluginException("Unknown System " + system);
            }

            pathField.setText("[" + systemManager.getSystem() + "] " + pluginPath);

            // Reset the panels
            for (Component panelRaw : infoContainer.getComponents()) {
                try {
                    PluginPanel panel = (PluginPanel) panelRaw;
                    if (!panel.isBasePanel()) {
                        infoContainer.remove(panelRaw);
                    }
                } catch (ClassCastException ex) {
                }
            }

            for (Component panelRaw : fileContainer.getComponents()) {
                try {
                    PluginPanel panel = (PluginPanel) panelRaw;
                    if (!panel.isBasePanel()) {
                        fileContainer.remove(panelRaw);
                    }
                } catch (ClassCastException ex) {
                }
            }

            // Insert the system specific panel
            systemManager.initPanels();

            // Reset the panels
            for (Component panelRaw : infoContainer.getComponents()) {
                try {
                    PluginPanel panel = (PluginPanel) panelRaw;
                    panel.load(plugin);
                } catch (ClassCastException ex) {
                }
            }

            for (Component panelRaw : fileContainer.getComponents()) {
                try {
                    PluginPanel panel = (PluginPanel) panelRaw;
                    panel.load(plugin);
                } catch (ClassCastException ex) {
                }
            }

            infoContainer.setSelectedComponent(infoPanel);
        }
        
        setChanged(false);
    }

    /**
     * Save the current plugin.
     *
     * @throws IOException
     */
    protected void save() throws IOException {
        File xmlFile = new File(pluginPath, "plugin.xml");
        if (!xmlFile.exists()) {
            Files.createFile(xmlFile.toPath());
        }

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xml = builder.newDocument();
            Element root = (Element) xml.createElement("plugin");
            xml.appendChild(root);

            String system = systemManager.getSystem();
            root.setAttribute("system", system);

            for (Component panelRaw : infoContainer.getComponents()) {
                try {
                    PluginPanel panel = (PluginPanel) panelRaw;
                    panel.save(xml, root);
                } catch (ClassCastException ex) {
                }
            }

            for (Component panelRaw : fileContainer.getComponents()) {
                try {
                    PluginPanel panel = (PluginPanel) panelRaw;
                    panel.save(xml, root);
                } catch (ClassCastException ex) {
                }
            }

            String text = XMLTool.documentToString(xml);
            // System.out.println(text);
            File file = new File(pluginPath + "/plugin.xml");
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
            
            changed = false;
            
        } catch (ParserConfigurationException ex) {
        }

        setChanged(false);
    }

    /**
     * Call build on all panels. Abort as soon as one returns an error.
     * @return 
     */
    public boolean build() {
        try {
            save();
        } catch (IOException ex) {
            setStatusMsg("Couldn't save the plugin");
            return false;
        }
        
        // Reset the panels
        for (Component panelRaw : infoContainer.getComponents()) {
            try {
                PluginPanel panel = (PluginPanel) panelRaw;
                if ( !panel.build() ) {
                    return false;
                }
            } catch (ClassCastException ex) {
            }
        }

        for (Component panelRaw : fileContainer.getComponents()) {
            try {
                PluginPanel panel = (PluginPanel) panelRaw;
                if ( !panel.build() ) {
                    return false;
                }
            } catch (ClassCastException ex) {
            }            
        }
        
        return true;
    }

    /**
     * Quit the program.
     */
    protected void quit() {
        if (changed) {
            int choice = JOptionPane.showConfirmDialog(this, "Save the plugin", "abbozza! Plugin Editor", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    save();
                } catch (IOException ex) {
                    this.setStatusMsg("Could not save plugin!");
                }
            } else if (choice == JOptionPane.NO_OPTION) {
                dispose();
            }
        } else {
            dispose();
        }
        HelpFrame.hideFrame();
    }

    /**
     * Display the given status nessage
     *
     * @param msg
     */
    public void setStatusMsg(String msg) {
        statusMsg.setText(msg);
        statusIcon.setIcon(null);
    }

    /**
     * Display the given status nessage
     *
     * @param msg
     */
    public void setErrorMsg(String msg) {
        statusMsg.setText(msg);
        statusIcon.setIcon(new ImageIcon(errorIcon));        
    }


    /**
     * Update the change indicator.
     */
    public void setChanged(boolean flag) {
        changed = flag;
        
        if (changed) {
            changeLabel.setText("*");
        } else {
            changeLabel.setText(" ");
        }
    }

    /**
     * Return the id of the current plugin.
     *
     * @return The id.
     */
    public String getId() {
        return infoPanel.getId();
    }

    /**
     * Returns the system of the plugin.
     *
     * @return
     */
    public String getSystem() {
        return systemManager.getSystem();
    }

    /**
     * Get the content of the given file as String.
     *
     * @param entry
     * @return
     */
    public String getFileContent(FileEntry entry) {
        StringBuffer content = new StringBuffer();
        int bufSize = 2048;
        int read;
        char[] buf = new char[bufSize];

        File file = new File(this.pluginPath + "/" + entry.getName());
        if (!file.exists()) {
            try {
                // The file doesn't exist, read the tempate
                InputStreamReader reader = new InputStreamReader(PluginEditor.class.getResourceAsStream("templates/javascript.tmpl"));
                while (reader.ready()) {
                    read = reader.read(buf, 0, bufSize);
                    content.append(buf, 0, read);
                }
            } catch (IOException ex) {
                Logger.getLogger(PluginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                // Load the file
                FileReader reader = new FileReader(file);
                while (reader.ready()) {
                    read = reader.read(buf, 0, bufSize);
                    content.append(buf, 0, read);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(PluginFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PluginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return content.toString();
    }

    /**
     * Write the content to the given file.
     *
     * @param entry The FileEntry representing the file
     * @param content The content to be written
     */
    public void writeFileContent(FileEntry entry, String content) {
        int bufSize = 2048;
        int read;
        char[] buf = new char[bufSize];

        try {
            File file = new File(this.pluginPath + "/" + entry.getName());
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            PluginEditor.showErrorMessage("Can't write to " + this.pluginPath + "/" + entry.getName());
        }
    }

    /**
     * Write a specific template to the given target.
     *
     * The following placeholders are replaced: %%SYSTEM%% by the current
     * system-
     *
     * @param template
     * @param target
     */
    public void writeTemplate(String template, String target) {
        if (template == null) {
            return;
        }

        StringBuffer buffer = new StringBuffer();
        int bufSize = 2048;
        int read;
        char[] buf = new char[bufSize];

        try {
            File targetFile = new File(this.pluginPath + "/" + target);

            // The file doesn't exist, read the tempate
            InputStreamReader reader = new InputStreamReader(PluginEditor.getResourceAsStream(template));
            while (reader.ready()) {
                read = reader.read(buf, 0, bufSize);
                buffer.append(buf, 0, read);
            }

            // Now replace the placeholders
            String content = buffer.toString();
            content = content.replace("%%SYSTEM%%", systemManager.getSystem());
            content = content.replace("%%CLASSNAME%%", target.replace(".java", ""));

            // Now write it to the target
            FileWriter writer = new FileWriter(targetFile);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(PluginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Ask the user for the system.
     */
    public void askForSystem() {
        SystemDialog dialog = new SystemDialog(this);
        GUITool.centerWindow(dialog);
        dialog.setVisible(true);

        if (dialog.getState() == 0) {
            systemManager = dialog.getSystem();
        } else {
            System.exit(1);
        }
    }

    /**
     * Returns the path of the current plugin directory.
     * @return The plugin's path
     */
    public String getPluginPath() {
        return pluginPath;
    }

    /**
     * Add a panel to the file section.
     * 
     * @param panel The panel to be added
     * @param closable This flag indicates, wether the panel is closable.
     */
    public void addFileContainerPanel(JPanel panel, boolean closable) {
        fileContainer.add(panel);
        if (closable) {
            ClosableTabPanel tab = new ClosableTabPanel(fileContainer, panel, panel.getName());
            fileContainer.setTabComponentAt(fileContainer.indexOfComponent(panel), tab);
            
        }
        fileContainer.setSelectedComponent(panel);
    }

    /**
     * Add a panel to the info section.
     * 
     * @param panel The panel to be added
     * @param closable This flag indicates, wether the panel is closable.
     */
    public void addInfoContainerPanel(JPanel panel, boolean closable) {
        infoContainer.add(panel);
        if (closable) {
            ClosableTabPanel tab = new ClosableTabPanel(infoContainer, panel, panel.getName());
            infoContainer.setTabComponentAt(infoContainer.indexOfComponent(panel), tab);
        }
        infoContainer.setSelectedComponent(panel);
    }
    
    

    /**
     * Build a jar containig all files in the plugin directory.
     */
    public void buildJar() {
        if ( !build() ) return;

        File source = new File(pluginPath);
        File target;
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar File", "jar");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            target = chooser.getSelectedFile();
        } else {
            return;
        }
        
        try {
            JarOutputStream out = new JarOutputStream(new FileOutputStream(target));
            Files.walkFileTree( source.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String name = file.toRealPath().toString();
                    name = name.replace(pluginPath + "/","");
                    System.out.println(name);
                    out.putNextEntry(new ZipEntry(name));
                    Files.copy(file, out);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(PluginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Various listeners for changes in panels.
     */

    @Override
    public void insertUpdate(DocumentEvent e) {
        setChanged(true);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setChanged(true);

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        setChanged(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        setChanged(true);
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        setChanged(true);
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        setChanged(true);
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        setChanged(true);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        setChanged(true);
    }
        
}
