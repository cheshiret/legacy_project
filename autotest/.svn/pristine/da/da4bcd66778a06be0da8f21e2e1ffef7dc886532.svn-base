package com.activenetwork.qa.awo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.activenetwork.qa.awo.crypto.SSH_Auth;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Brand;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.BrandAttribute;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.PropertiesAttribute;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.RAProperty;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.XmlAttribute;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.SSH2;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.XmlParser;

public class WebConfiguration {
	AutomationLogger logger=AutomationLogger.getInstance();
	private Map<Conf,Object> confLoaded; //hold all loaded configurations
	private String build;
	private String subBrand; //for PLW
	
	private static WebConfiguration _instance=null;
	
	public static WebConfiguration getInstance() {
		if(_instance==null) {
			_instance=new WebConfiguration();
		}
		
		return _instance;
	}
	
	private WebConfiguration() {
		confLoaded=new HashMap<Conf,Object>();
		subBrand=null;
	}
	
	public void loadConf(String subBrand,Conf...confs) {
		this.subBrand=subBrand;
		loadConf(confs);
	}

	public void loadConf(Conf... confs) {
		SSH2 ssh=connectWebServer();
		
		List<Conf> confList=filter(confs);
		for(Conf conf:confList) {
			String value=replaceVariable(conf.getValue());
			Class<?> type=conf.getType();
			
			if(type.equals(Properties.class)) {
				String prop=ssh.readFile(value);
				Properties p=new Properties();
				try {
					p.load(StringUtil.stringToInputStream(prop));
				} catch (IOException e) {
					logger.warn("Failed to load configuration "+conf, e);
				}
				confLoaded.put(conf, p);
			} else if(type.equals(XmlParser.class)) {
				String xml=ssh.readFile(value);
				confLoaded.put(conf,new XmlParser(xml));
			} else {
				String text=ssh.exec(conf.getValue());
				confLoaded.put(conf,text);
			} 
		}
		
		ssh.disconnect();
	}
	
	private List<Conf> filter(Conf... confs) {
		List<Conf> c=new ArrayList<Conf>();
		for(Conf conf:confs) {
			if(!confLoaded.containsKey(conf)) {
				c.add(conf);
			} else {
				logger.debug(conf.toString()+" has already been loaded");
			}
		}
		
		return c;
		
	}
	
	public String getXmlConfig(Conf conf, XmlAttribute item, String subBrand) {
		String value="";
		XmlParser xml=this.getXml(conf,subBrand);
		org.w3c.dom.NodeList nodeList = xml.getNodesByAttribute(item.getProperty());
		
		if(item.getAttributeName().equalsIgnoreCase("auto_check_exists")) {//only check if the node exists or not
			return Boolean.toString(nodeList!=null && nodeList.getLength()>0);
		}
		
		
		if (nodeList==null) {
			return null;
		} else {
			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node node = nodeList.item(i);
			
				org.w3c.dom.Node attrNode=node.getAttributes().getNamedItem(item.getAttributeName());
				if(attrNode!=null) {
					value= attrNode.getNodeValue();
					break;
				}
			}
		}
		
		StringProcessor[] ps=item.getProcessors();
		
		if(ps!=null) {
			value=StringProcessor.pipe(value, ps);
		}
		
		return value;
	}
	
	public String getXmlConfig(Conf conf, XmlAttribute item) {
		return getXmlConfig(conf,item,null);
	}
	
	/*
	 *Generally get a UIOption value
	 *@param conf - the named configuration
	 *@param item - the predefined UIOptions target 
	 */
	public String getUIOption(Conf conf,UIOptions item) {	
		return getXmlConfig(conf,item);
	}
	
	public String getUIOption(Conf conf,UIOptions item,String subBrand) {	
		return getXmlConfig(conf,item,subBrand);
	}
	
	public boolean getUIOptionBoolean(Conf conf, UIOptions option) {
		return getUIOptionBoolean(conf,option,null);
	}
	
	public boolean getUIOptionBoolean(Conf conf, UIOptions option, String subBrand) {
		String value=getUIOption(conf,option,subBrand);
		return Boolean.parseBoolean(value);
	}
	
	public boolean uiOptionValueContains(Conf conf, UIOptions option, String text) {
		String value=getUIOption(conf,option,subBrand);
		return value.contains(text);
	}
	
	public String getBrandXmlAttribute(Conf conf, BrandAttribute attr) {
		return getXmlConfig(conf,attr);
	}
	
	public XmlParser getXml(Conf conf, String subBrand) {
		this.subBrand=subBrand;
		if(!confLoaded.containsKey(conf)) {
			loadConf(conf);
		}
		return (XmlParser) confLoaded.get(conf);
	}
	
	public XmlParser getXml(Conf conf) {
		return getXml(conf,null);
	}
	
	public Properties getProperties(Conf conf) {
		if(!confLoaded.containsKey(conf)) {
			loadConf(conf);
		}
		return (Properties) confLoaded.get(conf);
	}
	
	public String getPropertiesValue(Conf conf, PropertiesAttribute item) {
		Properties p=getProperties(conf);
		
		String[] keys=item.getKey();
		for(String key:keys) {
			String aValue=p.getProperty(key);
			if(aValue!=null) {
				return aValue;
			}
		}
		
		return item.getDefaultValue();
		
	}
	
	public void loadCommomConf() {	
		logger.info("Loading web server configurations");
		
		loadConf(Conf.Build_version,Conf.RA_prop,Conf.rec_UIOptions,Conf.uwp_UIOptions,Conf.pl_UIOptions);

		String env=TestProperty.getProperty("target_env");
		getUWPBuild();
			
		//set no cancel,no change properties
		String nocancel=getPropertiesValue(Conf.RA_prop,RAProperty.NoCancel);
		logger.debug("web.nocancel.contracts="+nocancel);
		TestProperty.putProperty(env+".web.nocancel.contracts", nocancel);
		String nochange=getPropertiesValue(Conf.RA_prop,RAProperty.NoChange);
		logger.debug("web.nochange.contracts="+nochange);
		TestProperty.putProperty(env+".web.nochange.contracts", nochange);
					
		//Check whether rec.gov/RA/PLW is unified search mode or not
		String value=getUIOption(Conf.rec_UIOptions, UIOptions.UnifiedSearch);
		TestProperty.putProperty("recgov.unified.search", value);
		
		value=getUIOption(Conf.uwp_UIOptions,UIOptions.UnifiedSearch);
		TestProperty.putProperty(env+".web.ra.unifiedsearch", value);
		
		value=getUIOption(Conf.pl_UIOptions,UIOptions.UnifiedSearch);
		TestProperty.putProperty(env+".web.plw.unifiedsearch", value);
			
		//Get landSearchArea value from Brand.xml
		value= getBrandXmlAttribute(Conf.rec_BrandXml, BrandAttribute.landSearchArea("NRSO"));
		TestProperty.putProperty("unified.search.distance", value);
		
		//Get 'Site Transfer' contracts
		value=getUIOption(Conf.uwp_UIOptions, UIOptions.AllowDateChange);
		TestProperty.putProperty(env+".web.issitetransfer.contracts", value);
		
		//Get 'Site Transfer' contracts
		value=getUIOption(Conf.rec_UIOptions, UIOptions.AllowDateChange);
		TestProperty.putProperty(env+".rec.issitetransfer.contracts", value);
		
		logger.info("Common Web configrations are loaded.");
	}
	
	/**
	 * Currently there are 2 variables:
	 * ${env} - the qa environment id
	 * ${brandname} - the plw brand name for PLW related configurations
	 * @param text
	 * @return
	 */
	private String replaceVariable(String text) {
		if(text.contains("${env}")) {
			String env="tor"+TestProperty.getProperty("target_env"); //Sara[2014/6/10] Add prefix "tor" for the changed web configuration file environment flag
			String sitesFolder=TestProperty.getProperty(env+".web.sites", env);
			text=text.replaceAll("\\$\\{env\\}", sitesFolder);
		}
		
		if(text.contains("${brandname}")) {
			if(StringUtil.isEmpty(this.subBrand)) {
				throw new ItemNotFoundException("PLW sub Brand name is unknown!");
			}
			text= text.replaceAll("\\$\\{brandname\\}", subBrand.toLowerCase());
		}
		
		return text;
	}
	
	private SSH2 connectWebServer() {
		String env=TestProperty.getProperty("target_env");
		SSH2 ssh=SSH2.getInstance();
		String host=TestProperty.getProperty(env+".web.server");
		ssh.connect(SSH_Auth.getInstance(),host);
		return ssh;
	}
	
	/**
	 * get the current web build number
	 * @return
	 */
	public String getUWPBuild() {
		if(StringUtil.isEmpty(build)) {
			
			//Parse the UWP build number
			String[] tokens=RegularExpression.getMatches(confLoaded.get(Conf.Build_version).toString(), "\\d+(\\.\\d+)+");
	
			if(tokens.length>0) {
				TestProperty.putProperty("web.build", tokens[0]);
				logger.info("UWP build#"+tokens[0]);
				build=tokens[0];
			} else {
				logger.warn("Failed to get UWP build number");
			}
		}
		return build;
	}
	
	/***********************************************
	 ** Below are Utility methods for convenience **
	 ***********************************************/
	
	/**
	 * check if the given UWP brand is using unified search mode.
	 * For PLW, the brand should be PLW_<Contract brief>. Now all PLW sites are not using unified search
	 * @param brand
	 * @return
	 */
	public static boolean isUnifiedSearch(Brand brand) {
		WebConfiguration wc=WebConfiguration.getInstance();
		
		switch(brand) {
		case Rec_gov:
			return wc.getUIOptionBoolean(Conf.rec_UIOptions, UIOptions.UnifiedSearch);
		case RA:
			return wc.getUIOptionBoolean(Conf.uwp_UIOptions, UIOptions.UnifiedSearch);
		case PLW:
			return false;
		default:
			throw new NotSupportedException(brand+" is an invalid UWP brand");
			
		}
	}
}
