package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

/**
 * Define the property keys for HFCustomer properties file
 * @author jdu
 *
 */
public enum HFCustomerProperty implements PropertiesAttribute {
ResidenceCheck1(new String[]{"failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.1.UWP_RD_0001_HFSK"}),
ResidenceCheck2(new String[]{"failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.2.UWP_RD_0001_HFSK"}),
ResidenceCheck3(new String[]{"failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.3.UWP_RD_0001_HFSK"}),
ResidenceCheck4(new String[]{"failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.4.UWP_RD_0001_HFSK"}),
ResidenceCheck18(new String[]{"failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.18.UWP_RD_0001_HFSK"}),
;

	private String[] key;
	private String defaultValue;
	private HFCustomerProperty(String[] key, String defaultValue) {
		this.key=key;
	}
	
	private HFCustomerProperty(String[] key) {
		this(key,null);
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
