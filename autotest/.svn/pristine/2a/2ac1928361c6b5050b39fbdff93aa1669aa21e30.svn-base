package com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.Property;
/**
 *  This page is the fee schedule common page in license manager, to go to this page:Select "Fee Schedule" from 'Admin' dropdown list
 * @author phoebe
 */
public class LicMgrFeeScheduleCommonPage extends LicMgrCommonTopMenuPage {
	
	private static LicMgrFeeScheduleCommonPage _instance = null;
	
	protected LicMgrFeeScheduleCommonPage() {}
	
	public static LicMgrFeeScheduleCommonPage getInstance() {
		if(_instance == null) _instance = new LicMgrFeeScheduleCommonPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "ActivityFeeScheduleSearchResultGrid");
	}
	
	protected Property[] feesTab() {
		return Property.concatPropertyArray(this.a(), ".text", "Fees");
	}
	
	protected Property[] RAFeeScheduleTab(){
		return Property.concatPropertyArray(this.a(), ".text", "RA Fee Schedules");
	}
	
	public void clickFeesTab(){
		browser.clickGuiObject(this.feesTab());
	}
	
	public void clickRAScheduleTab(){
		browser.clickGuiObject(this.RAFeeScheduleTab());
	}
}
