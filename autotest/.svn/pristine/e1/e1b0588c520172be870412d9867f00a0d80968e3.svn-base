/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author szhou
 * @Date Jul 26, 2011
 */
public class SupportCenterDecreaseSitePage extends SupportCenterChangeSitePage {
	private static SupportCenterDecreaseSitePage _instance = null;

	public static SupportCenterDecreaseSitePage getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterDecreaseSitePage();
		}
		return _instance;
	}
	
	protected Property[] newTicket_DecreaseSiteInventoryLabel() {
		return Property.toPropertyArray(".class","FlexLabel","automationName","Decrease Site Inventory","id","lblHeader"); 
	}

	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(newTicket_DecreaseSiteInventoryLabel());
	}

}
