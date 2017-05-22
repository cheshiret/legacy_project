package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * ScriptName: UwpUpdateOrganizationProfile - this page is used to update Organization profile before a customer want to reserve Group tours
 * Description:
 * @date: Apr 29, 2011-1:48:01 AM
 * @author QA-qchen
 *
 */
public class UwpUpdateOrganizationProfile extends UwpPage {
	
	public static UwpUpdateOrganizationProfile _instance = null;
	
	private UwpUpdateOrganizationProfile() {}
	
	public static UwpUpdateOrganizationProfile getInstance() {
		if(null == _instance) {
			_instance = new UwpUpdateOrganizationProfile();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "organizationType");
	}
	
	/**
	 * Set up name of organization
	 * @param orgName
	 */
	public void setOrganizationName(String orgName) {
		browser.setTextField(".id", "organizationName", orgName, true);
	}
	
	/**
	 * Select a type of organization
	 * @param orgType
	 */
	public void selectOrganizationType(String orgType) {
		if(null == orgType || orgType.length() < 1) {
			browser.selectDropdownList(".id", "organizationType", orgType, true);
		} else {
			browser.selectDropdownList(".id", "organizationType", "School", true);
		}
	}

	/**
	 * Update organization profile by setting organization name and selecting organization type
	 * @param orgType
	 * @param orgName
	 */
	public void updateOrganizationProfile(String orgType, String orgName) {
		logger.info("Update Organization Profile: " + orgType + "-" + orgName);
		setOrganizationName(orgName);
		selectOrganizationType(orgType);
	}
	
	/**
	 * Click 'Save Changes' button to finish updated organization profile
	 */
	public void clickSaveChanges() {
		browser.clickGuiObject(".type", "submit", true);
	}
	
	/**
	 * Click 'Back to Public Sales' link to back to public tour search page
	 */
	public void clickBackToPublicSales() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Back to Public Sales", false));
	}
}
