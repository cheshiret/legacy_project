/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jun 21, 2012
 */
public class LicMgrInspectionDetailsPage extends LicMgrCommonTopMenuPage {
	private static LicMgrInspectionDetailsPage instance = null;

	public static LicMgrInspectionDetailsPage getInstance() {
		if (instance == null) {
			instance = new LicMgrInspectionDetailsPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "AddEditVehInspectionContainer") &&
				browser.checkHtmlObjectExists(".id", new RegularExpression("VehicleInspectionInstanceView-\\d+.id", false));
	}
	
	public String getInspStatus() {
		String text = browser.getObjectText(".id", new RegularExpression("VehicleInspectionInstanceView-\\d+.status.name", false));
		return StringUtil.getSubString(text, "Status");
	}
	
	public boolean compareInspStatus(String exp) {
		String actual = this.getInspStatus();
		return MiscFunctions.compareResult("Inspection Status", exp, actual);
	}
    /**
     * Click Complete Insepction button
     */
	public void clickCompleteInspetion(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Complete Insepction");
	}
	/**
	 * Click Close Inspection button
	 */
	public void clickCloseInspection(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Close Inspection");
	}
	/**
	 * Click Print Inspection Form button
	 */
	public void clickPrintInspectionForm(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Print Inspection Form");
	}
	/**
	 * Click OK button
	 */
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				"OK");
	}
	/**
	 * Click Cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Cancel");
	}
	/**
	 * Click Apply button
	 */
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Apply");
	}
	
	public void clickOtherTab(String tabName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(tabName, false), true);
	}
	
	public void switchToOfficersTab() {
		clickOtherTab("Officers");
		ajax.waitLoading();
	}
	/**
	 * Click Add/Change Officer Assignment button
	 */
	public void clickAddChangeOfficerAssignment(){
	    browser.clickGuiObject(".class", "Html.A", ".text",
			"Add/Change Officer Assignment");
	}
	
	public void selectRequestReason(String reason){
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleInspectionInstanceView-\\d+\\.vehicleInspection\\.requestReason", false),
				reason);
	}
}
