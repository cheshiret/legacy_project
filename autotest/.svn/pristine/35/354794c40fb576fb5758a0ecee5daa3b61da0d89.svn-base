package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.StringProcessor;
import com.activenetwork.qa.testapi.util.Property;

/**
 * define the attributes in brand.xml file, this is general for all brand.xml files for all RA,Rec.gov and plws. 
 * @author jdu
 *
 */
public class BrandAttribute implements XmlAttribute {
	public static BrandAttribute landSearchArea(String name) {
		return new BrandAttribute(name,"landSearchArea");
	}
	
	public static BrandAttribute statesOnWebExclude(String name) {
		return new BrandAttribute(name,"statesOnWebExclude");
	}
	
	public static BrandAttribute homeLink(String name) {
		return new BrandAttribute(name,"homeLink");
	}
	public static BrandAttribute ssoURL(String name) {
		return new BrandAttribute(name,"ssoURL");
	}
	public static BrandAttribute dynamicLabeling(String name) {
		return new BrandAttribute(name,"dynamicLabeling");
	}
	public static BrandAttribute photoToolEnabled(String name) {
		return new BrandAttribute(name,"photoToolEnabled");
	}

	private List<Property[]> prop;
	private String attributeName;
	private StringProcessor[] processors;
	private BrandAttribute(String brandName, String attribute, StringProcessor... processors) {
		Property[] p=Property.toPropertyArray("tag","brand","name",brandName);
		this.prop=new ArrayList<Property[]>();
		this.prop.add(p);
		this.attributeName=attribute;
		this.processors=processors;
	}
	
	private BrandAttribute(String brandName, String attribute) {
		this(brandName,attribute,(StringProcessor)null);
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
		return processors;
	}

	@Override
	public String getAttributeName() {
		return attributeName;
	}
}
