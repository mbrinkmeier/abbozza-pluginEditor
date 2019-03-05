/**
 * @license abbozza!
 *
 * Copyright 2015 Michael Brinkmeier ( michael.brinkmeier@uni-osnabrueck.de )
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.uos.inf.did.abbozza.plugineditor;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author mbrinkmeier
 */
public class XMLTool {

    /**
     * This operation converts a XML-document to a string representation.
     * 
     * @param doc The XML-document
     * @return The string representation of the XML-document
     */
    public static String documentToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    /**
     * This operation converts a XML-node to a string representation.
     * @param doc The node
     * @return The String representation
     */
    public static String documentToString(Node doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    /**
     * This operation reads an XML-ducument from the given URL
     * @param url The URL of the XML-document
     * @return The document
     */
    public static Document getXml(URL url) {
        Document xml = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            URLConnection conn = url.openConnection();
            xml = builder.parse(conn.getInputStream());
        } catch (ParserConfigurationException ex) {
            xml = null;
        } catch (SAXException ex) {
            xml = null;
        } catch (IOException ex) {
            xml = null;
        }
        return xml;
    }
    
    /**
     * This operation reads an XML-ducument from the given URL
     * @param url The URL of the XML-document
     * @param timeout The time till the connection times out in ms
     * @return The document
     */
    public static Document getXml(URL url, int timeout) {
        Document xml = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(timeout);
            xml = builder.parse(conn.getInputStream());
        } catch (ParserConfigurationException ex) {
            xml = null;
        } catch (SAXException ex) {
            xml = null;
        } catch (IOException ex) {
            xml = null;
        }
        return xml;
    }

    
    public static Document documentFromString(String text) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(text));
            
            Document doc = db.parse(is);
            return doc;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLTool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLTool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static String getStringAttribute(Node node, String name, String def) {
        NamedNodeMap attrs = node.getAttributes();
        Node attr = attrs.getNamedItem(name);
        if ( attr != null ) {
            return attr.getTextContent();
        }
        return def;
    }
    

    public static String getTextContent(Document doc, String tag) {
        String result = "";
        NodeList nodes = doc.getElementsByTagName(tag);
        for ( int i = 0; i < nodes.getLength(); i++ ) {
            Node node = nodes.item(i);
            result = result + node.getTextContent();
        }
        return result;
    }

    public static String getTextContent(Element el, String tag) {
        String result = "";
        NodeList nodes = el.getElementsByTagName(tag);
        for ( int i = 0; i < nodes.getLength(); i++ ) {
            Node node = nodes.item(i);
            result = result + node.getTextContent();
        }
        return result;
    }

    
    public static Element getFirstElement(Document doc, String tag) {
        Element el = null;
        NodeList els = doc.getElementsByTagName(tag);
        if ( els.getLength() > 0 ) {
           el = (Element) els.item(0);
        }
        return el;
    }
    
    public static Element getFirstElement(Element doc, String tag) {
        Element el = null;
        NodeList els = doc.getElementsByTagName(tag);
        if ( els.getLength() > 0 ) {
           el = (Element) els.item(0);
        }
        return el;
    }


    /**
     * Construct a string representing the children of the given element 
     * having the given tag.
     * @param element The parent
     * @param tag The tag
     * @return A String representing all children of the given type.
     */
    public static String elementsToString(Element element, String tag) {
        String result = null;
        
        if ( element == null ) return null;
        NodeList els = element.getElementsByTagName(tag);
        Element el;
        for ( int i = 0; i < els.getLength(); i++ ) {
            el = (Element) els.item(i);
            if ( i > 0 ) {
                result = result + "\n";
            } else {
                result = "";
            }
            result = result + toString(el);
        }
        
        return result;
    }
    
    
    public static String toString(Element element) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            
            
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(element);
            transformer.transform(source, result);
            
            String xmlString = result.getWriter().toString();
            return xmlString;
        } catch (TransformerConfigurationException ex) {
            return "";
        } catch (TransformerException ex) {
            return ""; 
        }
    }
    
}