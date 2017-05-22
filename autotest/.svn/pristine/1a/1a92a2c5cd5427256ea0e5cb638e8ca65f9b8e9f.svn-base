package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

/**
 * define the property keys for ShoppingCartResources.properties file
 * @author jdu
 *
 */
public enum ShoppingcartProperty implements PropertiesAttribute {
	OrderTotal(new String[]{"cart.view.priceCol.total.orderTotal"},"Total Order:"),
	NonCommercial_72201(new String[]{"cart.permit.yesAck.nonCommercial.72201"},"I acknowledge that I am not one of the types of groups listed above"),
	NonCommercial_72202(new String[]{"cart.permit.yesAck.nonCommercial.72202"},"I acknowledge that I'm not one of the groups listed above."),
	NonCommercial_72203(new String[]{"cart.permit.yesAck.nonCommercial.72203"},"I acknowledge that I am not one of the types of groups listed above")
	;
	
	private String[] key;
	private String defaultValue;
	private ShoppingcartProperty(String[] key, String defaultValue) {
		this.key=key;
		this.defaultValue=defaultValue;
	}
	
	private ShoppingcartProperty(String[] key) {
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
