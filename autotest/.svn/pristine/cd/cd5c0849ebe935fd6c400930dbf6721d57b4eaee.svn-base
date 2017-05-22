package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Script Name   : InvMgrFacilitySupplementaryInfo
 * @since  2010/12/22
 * @author Sara Wang
 */

public class InvMgrFacilitySupplementaryInfo extends InventoryManagerPage{


	private static InvMgrFacilitySupplementaryInfo _instance = null;

	public static InvMgrFacilitySupplementaryInfo getInstance() {
		if (null == _instance) {
			_instance = new InvMgrFacilitySupplementaryInfo();
		}

		return _instance;
	}

	protected InvMgrFacilitySupplementaryInfo() {
	}

	public boolean exists() {
		RegularExpression res = new RegularExpression("^Facility Details Telecommunications.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", res);
	}

	//The selection of 'Facility Details Telecommunications'
	public void selectVoiceLine(String voiceLine){
		browser.selectDropdownList(".id", "attr_351", voiceLine);
	}
	public void selectFaxLine(String faxLine){
		browser.selectDropdownList(".id", "attr_364", faxLine);
	}
	public void selectFaxLocation(String faxLocation){
		browser.selectDropdownList(".id", "attr_365", faxLocation);
	}
	public void selectFaxDistanceToPark(String faxDistanceToPark){
		browser.selectDropdownList(".id", "attr_365", faxDistanceToPark);
	}

	//The selection of 'Internet'
	public void selectInternetProvidedByRa(String internetProvidedByRA){
		browser.selectDropdownList(".id", "attr_10221", internetProvidedByRA);
	}

	public void selectHardwareProvidedByRa(String hardwareProvidedByRa){
		browser.selectDropdownList(".id", "attr_10222", hardwareProvidedByRa);
	}

	/**
	 * Select Internet
	 * @param internetIndex:
	 *                      0->Satellite
	 *                      1->ISP
	 *                      2->ADSL/T1
	 *                      3->Wireless
	 *                      4->Dial UP
	 */
	public void selectInternetConnectivity(String internetIndex) {
		String[] internets = internetIndex.split("[,;]");
		for (int i = 0; i < internets.length; i++) {
			browser.selectCheckBox(".id", "attr_10220", Integer.parseInt(internets[i].trim()));
		}
	}

	//The selection of 'Staffing'
	public void selectFullTimeStaff(String fullTimeStaff){
		browser.selectDropdownList(".id", "attr_355", fullTimeStaff);
	}

	public void selectSeasonalStaff(String seasonalStaff){
		browser.selectDropdownList(".id", "attr_356", seasonalStaff);
	}

	public void selectSummerVisitsPerWeek(String summerVisitsPerWeek){
		browser.selectDropdownList(".id", "attr_358", summerVisitsPerWeek);
	}

	public void selectWinterVisitsPerWeek(String winterVisitsPerWeek){
		browser.selectDropdownList(".id", "attr_359", winterVisitsPerWeek);
	}

	public void selectDistanceTravelledPerVisit(String winterVisitsPerWeek){
		browser.selectDropdownList(".id", "attr_359", winterVisitsPerWeek);
	}

	public void selectSummer(){
		browser.selectCheckBox(".id", "attr_357", 0);
	}

	public void selectWinter(){
		browser.selectCheckBox(".id", "attr_357", 1);
	}

	//The selection of 'Park Configuration'
	public void selectStaffedEntrancesNum(String startfedEntrances){
		browser.selectDropdownList(".id", "attr_361", startfedEntrances);
	}
	public void selectNonStaffedEntrancesNum(String nonStaffedEntrances){
		browser.selectDropdownList(".id", "attr_362", nonStaffedEntrances);
	}
	public void selectRemoteSitesNum(String remoteSites){
		browser.selectDropdownList(".id", "attr_363", remoteSites);
	}

	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void selectFacilityCategory(String category){
		browser.selectDropdownList(".id","page_name",category);
	}
	
	/**View change request item*/
	public void clickViewChangeRequestItems() {
		browser.clickGuiObject(".class", "Html.A", ".text","View Change Request Items");
	}
	
	/**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
}
