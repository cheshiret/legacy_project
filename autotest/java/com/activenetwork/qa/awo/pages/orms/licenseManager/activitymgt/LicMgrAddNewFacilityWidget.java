package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 7, 2014
 */
public class LicMgrAddNewFacilityWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrAddNewFacilityWidget _instance = new LicMgrAddNewFacilityWidget();
	}

	protected LicMgrAddNewFacilityWidget() {
		super("Add New Facility");
	}

	public static LicMgrAddNewFacilityWidget getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] agency(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityLocationBean-\\d+.agency", false));
	}
	
	protected Property[] region(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityLocationBean-\\d+.region", false));
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}
	/** Page Object Property Definition END */
	
	public void selectAgency(String agency){
		browser.selectDropdownList(agency(), agency);
	}
	
	public void selectRegion(String region){
		browser.selectDropdownList(region(), region);
	}
	public void clickOK(){
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}
	
	public void setupAgencyAndRegionInfo(String agency, String region){
		if(StringUtil.notEmpty(agency)){
			this.selectAgency(agency);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(region))
			this.selectRegion(region);
	}
	
	public void updateAddressesAndClickOK(String agency, String regiond) {
		setupAgencyAndRegionInfo(agency, regiond);
		clickOK();
		ajax.waitLoading();
	}
	
	public void updateAddressesAndClickCancel(String agency, String regiond) {
		setupAgencyAndRegionInfo(agency, regiond);
		clickCancel();
		ajax.waitLoading();
	}
}
