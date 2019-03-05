/*
 * Copyright 2019 Michael Brinkmeier <michael.brinkmeier@uni-osnabrueck.de>.
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
import de.uos.inf.did.abbozza.plugineditor.gui.HelpFrame;
import de.uos.inf.did.abbozza.plugineditor.gui.PathDialog;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;
import java.util.jar.JarFile;
import javax.swing.JOptionPane;

/**
 *
 * @author michael
 */
public class PluginEditor {

    protected static Properties config;
    protected static File configFile;
    protected static String lastPluginPath;
    protected static String arduinoPath;
    protected static String calliopePath;
    protected static String worldsPath;
    protected static JarFile arduinoJar;

    protected static JarFile calliopeJar;
    protected static JarFile worldsJar;
   
    protected static PluginFrame frame;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        arduinoPath = System.getProperty("user.home") + "/Arduino/tools/Abbozza";
        calliopePath = System.getProperty("user.home") + "/abbozzaCalliope";
        worldsPath = System.getProperty("user.home") + "/abbozzaWorlds";
        
        config = new Properties();
        
        // Read the configuration
        configFile = new File(System.getProperty("user.home") + "/.abbozza/pluginEditor.cfg");

        // If the config file doesn't exist ...
        if (!configFile.exists()) {
            try {
                // ... create a new one ...
                Files.createDirectories(configFile.getParentFile().toPath());
                Files.createFile(configFile.toPath());
                // ... and open the config dialog.
                openConfigDialog();
            } catch (IOException ex) {
                // If the config can't be created, exit.
                showErrorMessage("Could not create file " + configFile.getAbsolutePath());
                System.exit(1);
            }
        }

        // Now the config file exists.
        try {
            // Read the config file and apply it.
            config.load(new FileReader(configFile));
            applyConfig();
        } catch (IOException ex) {
            // Try to write it and exit.
            saveConfig();
            showErrorMessage("Cannot read configuration from " + configFile.getAbsolutePath());
            System.exit(1);
        }
        
        try {
            // Create a PluginFrame
            frame = new PluginFrame(lastPluginPath);
            GUITool.centerWindow(frame);
            frame.setVisible(true);
        } catch (IllegalPluginException ex) {
            showErrorMessage("Illegal plugin at " + lastPluginPath);
        }
       
    }

    
    /*****
     *****  Stuff for the configuration
     *****/
    
    
    /**
     * Apply the current config.
     */
    protected static void applyConfig() {        
        arduinoPath = config.getProperty("arduinoPath", System.getProperty("user.home") + "/Arduino/tools/Abbozza");
        calliopePath = config.getProperty("calliopePath", System.getProperty("user.home") + "/abbozzaCalliope");
        worldsPath = config.getProperty("worldsPath", System.getProperty("user.home") + "/abbozzaWorlds");

        checkJars();
        
        lastPluginPath = config.getProperty("lastPlugin",null);
    }

    /**
     * Store the current config.
     */
    protected static void saveConfig() {
        Date now = new Date(System.currentTimeMillis());
        try {
            if ( lastPluginPath != null ) config.setProperty("lastPlugin", lastPluginPath);
            if ( arduinoPath != null ) config.setProperty("arduinoPath",arduinoPath);
            if ( calliopePath != null ) config.setProperty("calliopePath",calliopePath);
            if ( worldsPath != null ) config.setProperty("worldsPath",worldsPath);
            config.store(new FileWriter(configFile), "Created by abbozza! Plugin Editor at " + now.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Can't write configuration to " + configFile.getAbsolutePath(), "abbozza! Plugin Editor", JOptionPane.ERROR_MESSAGE);
        }
        
    }


    /**
     * Show an error message.
     * 
     * @param msg The message
     */
    public static void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "abbozza! Plugin Editor", JOptionPane.ERROR_MESSAGE);                        
    }
    
    /**
     * Open the config dialog and store the configuration afterwards.
     */
    public static void openConfigDialog() {
        PathDialog dialog = new PathDialog(arduinoPath, calliopePath, worldsPath);
        // dialog.setPaths(arduinoPath, calliopePath, worldsPath);
        GUITool.centerWindow(dialog);
        dialog.setVisible(true);
        
        if ( dialog.getState() == 0 ) {
            arduinoPath = dialog.getArduinoPath();
            calliopePath = dialog.getCalliopePath();
            worldsPath = dialog.getWorldsPath();
            
            checkJars();    
            saveConfig();
        } else {
            System.exit(1);
        }
    
    }
    
    /**
     * Check the jar files.
     */
    public static void checkJars() {
        File jar;
        
        if ( arduinoPath != null ) {
            jar = new File(arduinoPath + "/tool/abbozza-arduino.jar");
            if ( jar.exists() ) {
                try {
                    arduinoJar = new JarFile(jar);
                    System.out.println("abbozza-arduino.jar found at " + arduinoJar.getName());
                } catch (IOException ex) {
                    arduinoJar = null;
                }
            } else {
                arduinoJar = null;
            }   
        }
        
        if ( calliopePath != null ) {
            jar = new File(calliopePath + "/lib/abbozza-calliope.jar");
            if ( jar.exists() ) {
                try {
                    calliopeJar = new JarFile(jar);
                    System.out.println("abbozza-calliope.jar found at " + calliopeJar.getName());
                } catch (IOException ex) {
                    calliopeJar = null;
                }
            } else {
                calliopeJar = null;
            }   
        }
        
        if ( worldsPath != null ) {
            jar = new File(worldsPath + "/abbozza-worlds.jar");
            if ( jar.exists() ) {
                try {
                    worldsJar = new JarFile(jar);
                    System.out.println("abbozza-worlds.jar found at " + worldsJar.getName());
                } catch (IOException ex) {
                    worldsJar = null;
                }
            } else {
                worldsJar = null;
            }   
        }
    }


    /**
     * Get a resource as String.
     * 
     * @param name The resource name, relative to the PluginEditor package.
     * @return The content of the Resource
     */
    public static String getResource(String name) {
        StringBuffer content = new StringBuffer();
        int bufSize = 2048;
        int read;
        char[] buf = new char[bufSize];

        InputStreamReader reader = new InputStreamReader(PluginEditor.class.getResourceAsStream(name));
        try {
            while (reader.ready() ) {
                read = reader.read(buf,0,bufSize);
                content.append(buf, 0, read);        
            }
        } catch (IOException ex) {
            return "";
        }
        
        return content.toString();
    }
    
    /**
     * Retrun an InputStream to the requested resource.
     * 
     * @param name The name of the resource.
     * @return 
     */
    public static InputStream getResourceAsStream(String name) {
        return PluginEditor.class.getResourceAsStream(name);
    }

    /**
     * Set the last plugin path in the configuration.
     * @param path The path
     */
    public static void setLastPluginPath(String path) {
        lastPluginPath = path;
        saveConfig();
    }

    public static JarFile getArduinoJar() {
        return arduinoJar;
    }

    public static JarFile getCalliopeJar() {
        return calliopeJar;
    }

    public static JarFile getWorldsJar() {
        return worldsJar;
    }

}
