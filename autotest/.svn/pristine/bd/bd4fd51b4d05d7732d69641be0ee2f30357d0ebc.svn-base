/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import java.util.List;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author szhou
 * @Date Jul 26, 2011
 */
public class SupportCenterIncreaseSitePage extends SupportCenterChangeSitePage {
	private static SupportCenterIncreaseSitePage _instance = null;

	public static SupportCenterIncreaseSitePage getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterIncreaseSitePage();
		}
		return _instance;
	}
	
	protected Property[] newTicket_IncreaseSiteInventoryLabel() {
		return Property.toPropertyArray(".class","FlexLabel","automationName","Increase Site Inventory","id","lblHeader");
	}
	
	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(newTicket_IncreaseSiteInventoryLabel());
	}
	
	
}
