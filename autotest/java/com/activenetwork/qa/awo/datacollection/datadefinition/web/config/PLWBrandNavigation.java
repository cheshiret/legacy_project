package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.StringProcessor;
import com.activenetwork.qa.testapi.util.Property;

/**
 * Define the attributes in PLW brand navigation.xml file
 * @author jdu
 *
 */
public enum PLWBrandNavigation implements XmlAttribute {
	;

	private List<Property[]> prop;
	private PLWBrandNavigation(List<Property[]> prop) {
		this.prop=prop;
	}
	
	private PLWBrandNavigation(Property[] prop) {
		this.prop=new ArrayList<Property[]>();
		this.prop.add(prop);
	}
	@Override
	public Class<?> getType() {
		return String.class;
	}
	@Override
	public List<Property[]> getProperty() {
		return prop;
	}

	@Override
	public StringProcessor[] getProcessors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttributeName() {
		// TODO Auto-generated method stub
		return null;
	}
}
