package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

/**
 * define the property keys for ra2.properties file
 * @author jdu
 *
 */
public enum RAProperty implements PropertiesAttribute {
	NoCancel(new String[]{"web.nocancel.contracts"}),
	NoChange(new String[]{"web.nochange.resv.contracts"}),	
	;

	private String[] key;//if size >1, all other keys are alternatives if previous one is not available
	private String defaultValue; // default value is used if the specific key is not found. If default value is null, it is undefined.
	private RAProperty(String[] key, String defaultValue) {
		this.key=key;
		this.defaultValue=defaultValue;
	}
	
	private RAProperty(String[] key) {
		this.key=key;
		this.defaultValue=null;
	}
	@Override
	public Class<?> getType() {
		return String.class;
	}
	@Override
	public String[] getKey() {
		return key;
	}
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

}
