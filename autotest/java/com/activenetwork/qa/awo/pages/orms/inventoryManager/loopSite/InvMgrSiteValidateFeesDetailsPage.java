package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Script Name : InvMgrSiteValidateFeesDetailsPage Generated : Dec 03, 2010 
 * @author Sara Wang
 */
public class InvMgrSiteValidateFeesDetailsPage extends InvMgrSiteDetailsCommonPage {
	static private InvMgrSiteValidateFeesDetailsPage _instance = null;
	protected InvMgrSiteValidateFeesDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public InvMgrSiteValidateFeesDetailsPage getInstance()
	throws PageNotFoundException {
		if (null == _instance) {
			_instance = new InvMgrSiteValidateFeesDetailsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
		"Fee Validation Details");
	}

	/** Click OK button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * Check Fee Schedule ID object exist or not
	 * @param feeSchedulID
	 * @return
	 */
	public boolean checkFeeSchedulIDExist(String feeSchedulID){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".value", feeSchedulID);
	}
	
	/**
	 * Check Location object exist or not
	 * @param locationName
	 * @return
	 */
	public boolean checkLocationExist(String locationName){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".value", locationName);
	}
	
	/**
	 * Verify the read-only input object exist or not
	 * @return
	 */
	public boolean verifyReadOnlyObjectExist(){
		boolean flag = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text");
		for(int i=0; i<objs.length; i++){
			if(!objs[i].getProperty(".disabled").equals("true")){
				flag = false;
			}
		}
		
		Browser.unregister(objs);
		return flag;
//		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".disabled", "true");
	}
	
	/** Click Fee Calculation Validation tab */
	public void clickFeeCalculationValidationTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Fee Calculation Validation");
	}
	
	/**
	 * Get the text of Site Table
	 * @return
	 */
	public String getSiteInfo(){
		String siteInfo = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Site Site ID.*|Site ID.*", false));
		if(objs.length>0){
			siteInfo = objs[0].getProperty(".text").toString();
		}else throw new ItemNotFoundException("Object doesn't exist.");
		
		return siteInfo;
	}
	
	/** Check 'Add Event/Holiday' button */
	public boolean checkAddEventHoliday(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Event/Holiday");
	}
	
	/** Check 'Remove Event/Holiday' button */
	public boolean checkRemoveEventHoliday(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Remove Event/Holiday");
	}
	
	public void verifyFeeValidationDetail(String facilityName, String feeSchedulID, String feeType){
		//Verify Fee Schedule id 
		if(!this.checkFeeSchedulIDExist(feeSchedulID)){
			throw new ErrorOnDataException("Fee schedul id ("+feeSchedulID+") object doesn't exist.");
		}
		//Verify Location 
		if(!feeType.contains("discount")){
			if(!this.checkLocationExist(facilityName)){
				throw new ErrorOnDataException("Location object doesn't exist.");
			}
		}
		//The 'Add Event/Holiday' and 'Remove Event/Holiday'  buttons shall not be displayed
		if(this.checkAddEventHoliday()){
			throw new ErrorOnDataException("'Add Event/Holiday' button shoult doesn't exist.");
		}
		if(this.checkRemoveEventHoliday()){
			throw new ErrorOnDataException("'Remove Event/Holiday' button shoult doesn't exist.");
		}
		//Verify all fields/information on the page shall be read-only.
		if(!this.verifyReadOnlyObjectExist()){
			throw new ErrorOnDataException("All fields shall be read-only.");
		}
	}
}
