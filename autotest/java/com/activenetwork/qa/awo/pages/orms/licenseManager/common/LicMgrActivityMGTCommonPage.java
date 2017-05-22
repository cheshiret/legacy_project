package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description: Make sure login role has feature "ViewActivityProduct" and then select "Activity MGT" option from top menu DDL
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 6, 2014
 */
public class LicMgrActivityMGTCommonPage extends LicMgrCommonTopMenuPage {
	static class SingletonHolder {
		protected static LicMgrActivityMGTCommonPage _instance = new LicMgrActivityMGTCommonPage();
	}

	protected LicMgrActivityMGTCommonPage() {
	}

	public static LicMgrActivityMGTCommonPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] activitySearchUITable() {
		return Property.concatPropertyArray(table(), ".id", "ActivitySearchUI");
	}
	
	protected Property[] facilityTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("LM_ActivityMgmtUIPlugin_tabpanel_\\d+", false), ".text", "Facility");
	}
	
	protected Property[] instructorTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("LM_ActivityMgmtUIPlugin_tabpanel_\\d+", false), ".text", "Instructor");
	}
	
	protected Property[] businessRuleTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("LM_ActivityMgmtUIPlugin_tabpanel_\\d+", false), ".text", "Business Rule");
	}
	
	/** Page Object Property Definition END */

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(activitySearchUITable());
	}
	
	public boolean isFacilityTabExisted(){
		return browser.checkHtmlObjectExists(facilityTab());
	}
	
	public void verifyFacilityTabExisted(boolean existed){
		boolean resultFromUI = isFacilityTabExisted();
		if(resultFromUI!=existed){
			throw new ErrorOnPageException("Facility tab should "+(existed?"":"not ")+"display");
		}
		logger.info("Successfully verify Facility tab "+(existed?"displays":"doesn't display "));
	}
	
	public void clickFacilityTab(){
		browser.clickGuiObject(facilityTab());
	}
	
	public void clickInstructorTab(){
		browser.clickGuiObject(instructorTab());
	}
	
	public void clickBusinessRuleTab(){
		browser.clickGuiObject(businessRuleTab());
	}
}
