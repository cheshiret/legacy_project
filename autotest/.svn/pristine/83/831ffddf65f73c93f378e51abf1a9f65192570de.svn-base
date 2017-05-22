package com.activenetwork.qa.awo.util;

/*
 * @(#)TransformationApp02.java	1.9 98/11/10
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.AutomationLogger;

public class XmlTransformer {
	// Global value so it can be ref'd by the tree-adapter
	private Document document = null;
	private Element rootNode = null;
	private String transCode;
	
	public XmlTransformer getInstance(){
		XmlTransformer _instance = new XmlTransformer();
		return _instance;
	}
	
	public String transformToString(Hashtable<String, LinkedHashMap<String, String>> nodesInfo) {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		transform(nodesInfo,baos);
		try {
			String xml=baos.toString("UTF-8");
			
			return xml;
		} catch (UnsupportedEncodingException e) {

			throw new ActionFailedException(e);
		}
	}
	
	public void transform(Hashtable<String, LinkedHashMap<String, String>> nodesInfo, OutputStream output) {
		try {
			//factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//builder
			DocumentBuilder builder = factory.newDocumentBuilder();
			//document
			document = builder.newDocument();
			LinkedHashMap<String, String> root = nodesInfo.get("root");
			constructRoot(root);
			LinkedHashMap<String, String> head = nodesInfo.get("head");
			constructHead(head);
			LinkedHashMap<String, String> body = nodesInfo.get("body");
			//Note: MS_INTERNET_CODE_COMPLETION_TRANSACTION has no body section
			if(transCode.equals(OrmsConstants.MS_INTERNET_CODE_CUSTOMER_INQUIRY)
			|| transCode.equals(OrmsConstants.MS_INTERNET_CODE_PURCHASE)
			|| transCode.equals(OrmsConstants.MS_INTERNET_CODE_VEHICLE_INQUIRY)
			|| transCode.equals(OrmsConstants.MS_INTERNET_CODE_VEHICLE_RENEWAL)){			
				if(nodesInfo.containsKey("license")){
					LinkedHashMap<String, String> license = nodesInfo.get("license");
					constructBodySection(body, license);
				}else
					constructBodySection(body);
			}
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			
			DOMSource domSource = new DOMSource(document);
			
			StreamResult xmlResult = new StreamResult(output);
			transFormer.transform(domSource, xmlResult);

		} catch (Exception e) {
			throw new ActionFailedException(e);
		} 		
	}
	
	public void transform(Hashtable<String, LinkedHashMap<String, String>> nodesInfo, String inputfile) {
		FileOutputStream out;
		try {
			File file = new File(inputfile);
			if(!file.exists()) {
				file.createNewFile();
			}
			out = new FileOutputStream(file);
		} catch (Exception e) {
			throw new ActionFailedException(e);
		}
		transform(nodesInfo,out);
	}
	
	private void constructRoot(LinkedHashMap<String, String> root) {
		//XML Version
		document.setXmlVersion("1.0");
		String rootName = root.get("root");
		//Root node
		rootNode = document.createElement(rootName);
		document.appendChild(rootNode);
	}
		
	private void constructHead(LinkedHashMap<String, String> head) {
		for(Iterator<String> it =  head.keySet().iterator();it.hasNext();){
			String key = (String) it.next();
			String value = (String) head.get(key);
			if(key.equals("transaction_type"))
				transCode = value;
			Element element = document.createElement(key);
			element.setTextContent(value);
			rootNode.appendChild(element);
		}
	}
	
	private void constructBodySection(LinkedHashMap<String, String> body) {
		constructBodySection(body, null);
	}
	
	private void constructBodySection(LinkedHashMap<String, String> body, LinkedHashMap<String, String> license) {
		Element cust = document.createElement("cust");
		for(Iterator<String> it =  body.keySet().iterator();it.hasNext();){
			String key = (String) it.next();
			String value = (String) body.get(key);
			Element element = document.createElement(key);
			element.setTextContent(value);
			cust.appendChild(element);
		}
		if(license != null){
			Element licenseSection = document.createElement("license");
			for(Iterator<String> it =  license.keySet().iterator();it.hasNext();){
				String key = (String) it.next();
				String value = (String) license.get(key);
				Element element = document.createElement(key);
				element.setTextContent(value);
				licenseSection.appendChild(element);
			}
			cust.appendChild(licenseSection);
		}
		rootNode.appendChild(cust);
	}

	public void main(String argv[]) {
			
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//factory.setNamespaceAware(true);
		//factory.setValidating(true);   

		try {
			File f = new File("");

			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(f);

			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);

		} catch (TransformerConfigurationException tce) {
			// Error generated by the parser
			System.out.println("\n** Transformer Factory error");
			System.out.println("   " + tce.getMessage());

			// Use the contained exception, if any
			Throwable x = tce;
			if (tce.getException() != null)
				x = tce.getException();
			x.printStackTrace();

		} catch (TransformerException te) {
			// Error generated by the parser
			System.out.println("\n** Transformation error");
			System.out.println("   " + te.getMessage());

			// Use the contained exception, if any
			Throwable x = te;
			if (te.getException() != null)
				x = te.getException();
			x.printStackTrace();

		} catch (SAXException sxe) {
			// Error generated by this application
			// (or a parser-initialization error)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();

		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();

		} catch (IOException ioe) {
			// I/O error
			ioe.printStackTrace();
		}

	} // main  

}
