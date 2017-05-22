package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

import java.util.Properties;

import com.activenetwork.qa.testapi.datacollection.DataAttribute;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.XmlParser;

/**
 * Define different web configuration files/information, which include xml files, properties files and other information such as uwp build number
 * @author jdu
 *
 */
public enum Conf implements DataAttribute {
	//Xml Configurations
	rec_UIOptions("web.recgov.ui.options",XmlParser.class),
	uwp_UIOptions("web.uwp.ui.options",XmlParser.class),
	pl_UIOptions("web.uwppl.ui.options",XmlParser.class),
	plbrand_UIOptions("web.uwppl.brand.ui.options",XmlParser.class),
	rec_BrandXml("web.recgov.brand.xml",XmlParser.class),
	plbrand_Navigation("web.uwppl.brand.navigation",XmlParser.class),
	
	//Properties configurations
	RA_prop("web.uwp.ra2.properties",Properties.class),
	Shoppingcart_prop("web.uwp.config.shoppingcart",Properties.class),
	Permit_prop("web.recgov.permit.properties",Properties.class),
	HFPrivilege_prop("web.hf.privilege.properties",Properties.class),
	HFCustomer_prop("web.hf.customer.properties",Properties.class),
	HFLottery_prop("web.hf.lottery.properties",Properties.class),
	
	//Resource
	uwp_MarianResource("web.uwp.marina.resource",Properties.class),
	
	//Text information
	Build_version(TestProperty.getProperty("web.java.path")+" -jar "+TestProperty.getProperty("web.uwp.jar"),String.class);
	
	private String value;
	
	private Class<?> dataType;
	
	private Conf(String key, Class<?> dataType) {
		value=TestProperty.getProperty(key);
		this.dataType=dataType;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public Class<?> getType() {
		return dataType;
	}
}
