package com.activenetwork.qa.awo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.w3c.dom.Node;

import com.activenetwork.qa.awo.crypto.SSH_Auth;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.SSH2;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.XmlParser;

public class OrmsConfiguration {
	public static OrmsConfiguration _instance=null;
	private AutomationLogger logger=AutomationLogger.getInstance();
	private Properties ormsProperty;
	private XmlParser epayment;
	private String version;
	private boolean loaded;
	
	public static OrmsConfiguration getInstance() {
		if(_instance==null) {
			_instance=new OrmsConfiguration();
		}
		
		return _instance;
	}
	
	private OrmsConfiguration() {
		ormsProperty=null;
		epayment=null;
		version=null;
		loaded=false;
	}
	
	public void load() {
		if(loaded) {
			return;
		}
		logger.info("Loading Orms server configurations......");
		SSH2 ssh=SSH2.getInstance();
		String env=TestProperty.getProperty("target_env");
		String host=TestProperty.getProperty(env+".orms.appsvr1");
		String config_path=TestProperty.getProperty(env+".orms.config.path");
		String propFile=config_path+"/"+TestProperty.getProperty(env+".orms.config.properties");
		String epayFile=config_path+"/"+TestProperty.getProperty(env+".orms.config.epayment_processors");
		String versionCmd="cat "+TestProperty.getProperty(env+".orms.version");
		ssh.connect(SSH_Auth.getInstance(),host);
		String prop_src=ssh.readFile(propFile);
		String epayment_src=ssh.readFile(epayFile);
		version=ssh.exec(versionCmd).trim();

		ssh.disconnect();
		try {
			ormsProperty=new Properties();
			ormsProperty.load(StringUtil.stringToInputStream(prop_src));
		} catch (IOException e) {
			logger.warn("Failed to load "+propFile+" due to "+e.getMessage());
		}
		epayment=new XmlParser(epayment_src);
		logger.info("Orms server configurations loaded");
		loaded=true;
	}
	
	public String getOrmsProperty(String key) {
		return ormsProperty.getProperty(key);
	}
	
	public String getEpaymentProcessor(String contractCode) {
		List<Property[]> list=new ArrayList<Property[]>();
		list.add(Property.toPropertyArray("tag","Environment","name",contractCode.toUpperCase()));
		list.add(Property.toPropertyArray("tag","EPaymentProcessor"));
		Node node=epayment.getNodeByAttribute(list);
		
		if(node==null) {
			return null;
		} else {
			return node.getAttributes().getNamedItem("name").getNodeValue();
		}
	}
	
	public String getOrmsVersion() {
		return version;
	}
	
	
}
