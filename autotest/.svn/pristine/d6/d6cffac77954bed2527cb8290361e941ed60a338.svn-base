/*
 * Created on Mar 2, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrMapViewPage extends InventoryManagerPage implements IPopupPage{
  
    private static InvMgrMapViewPage _instance = null;
    
    private Object urlValue;
    
	public static InvMgrMapViewPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrMapViewPage();
		}

		return _instance;
	}

	protected InvMgrMapViewPage() {
		browser = null;
//		urlValue = new RegularExpression(".*MapMaker.*e_Param=.*orms_session_id=.*", false);
		urlValue = new RegularExpression(".*MapMaker.*|.*MapEditPage.*", false);
	}
	
	public boolean exists() {
		browser = Browser.getInstance().getBrowser("url", urlValue);
		return browser!=null;
		
		/*boolean isFinished = false;
		HtmlObject obj[] = browser.getDropdownList(".id", "selectArea");
		String isDisabled = obj[0].getProperty("isDisabled").toString().trim();
		if(browser != null){
			while(! isFinished) {
				if(isDisabled.equalsIgnoreCase("false")) {
					isFinished = true;
				}
			}
		}
		return isFinished;*/
	}
	
	/**
	 * waitForPageLoad - wait for the drop down list appear
	 */
	public void waitForPageLoad() {
		boolean isFinished = false;
		
		int counter = 0;
		if(browser != null){
			while((! isFinished) && (counter <= Browser.LONG_SLEEP)) {
				isFinished = browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("selectArea|docks_area",false));
				Browser.sleep(1);
				counter++;
			}
		}

		if(! isFinished) {
			throw new PageNotFoundException("InvMgrMapViewPage is not found after " + counter);
		}
	}
	
	/**
	 * Sets the area
	 * @param area
	 */
	public void setArea(String area) {
	   browser.setTextField(".id", "newMapArea", area);
	}
	
	/**
	 * Select mapped
	 * @param area
	 */
	public void selectMapped(String area) {
	  browser.selectDropdownList(".id", "selectArea", area);
	}
	
	/**
	 * Click to add NSS
	 */
	public void clickAddNSS() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	/**
	 * Click to add loop
	 */
	public void clickAdd() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Add", 1);
	}
	
	/**
	 * Click to remove
	 */
	public void clickRemove() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Remove", 2);
	}
	
	/**
	 * Click to remove NSS
	 */
	public void clickRemoveNSS() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	/**
	 * Click to save the edits/modifications
	 */
	public void clickSave() {
	  browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Save Edits)|(Save)", false));
	}
	
	/**
	 * Click to refresh page
	 */
	public void clickRefresh() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Refresh", 1);
	}
	
	/**
	 * Click to close current page
	 */
	public void clickClose() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Close", 1);
	}
	
	/**
	 * Select the loop
	 * @param loop
	 */
	public void selectLoop(String loop) {
	  browser.selectDropdownList(".id", "selectLoop", loop);
	}
	
	/**
	 * Select the mapped site
	 * @param mapped
	 */
	public void selectMappedSite(String mapped) {
	  browser.selectDropdownList(".id", "selectSite", mapped);
	}
	
	/**
	 * Select the unmapped
	 * @param unmapped
	 */
	public void selectUnMapped(String unmapped) {
	  browser.selectDropdownList(".id", "selectSiteNoPosition", unmapped);
	}
	
	/**
	 * Select the amenity
	 */
	public void selectAmenity() {
	  browser.selectRadioButton(".id", "siteAmenityRadio_amenity");
	}
	
	/**
	 * Select the service
	 * @param service
	 */
	public void selectService(String service) {
	  browser.selectDropdownList(".id", "allAmenity", service);
	}
	
	/**
	 * Select the mapped service
	 * @param service
	 */
	public void selectMappedService(String service) {
	  browser.selectDropdownList(".id", "selectAmenity", service);
	}
	
	/**
	 * Click to add new
	 */
	public void clickAddNew() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	/**
	 * Remove the service
	 */
	public void removeService() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Remove", 1);
	}
	
	/**
	 * Get ready only value for specific object
	 * @param id
	 * @return
	 */
	public String getReadOnlyValue(String id){
		String readOnlyValue = "";
		IHtmlObject[] textObjs = browser.getTextField(".id",id);
		if(textObjs.length>0){
			readOnlyValue = textObjs[0].getAttributeValue("readOnly");
		}else throw new ObjectNotFoundException("Object doesn't find.");
		
		return readOnlyValue;
	}
	
	/**
	 * Verify Read-only value for specific object
	 * @param id
	 * @param idValue
	 */
	public void verifyReadOnlyValue(String id, String idValue){
		String value = this.getReadOnlyValue(id);
		System.out.println(value);
		if(!value.matches(idValue)){
			throw new ErrorOnDataException("The read-only value of " +id+ " should be " +idValue);
		}
	}
	
	/**
	 * Check object exists or not
	 * @param idvalue
	 * @return
	 */
	public boolean checkObjectExist(String idvalue){
		return browser.checkHtmlObjectExists(".id", idvalue);
	}
	
	/**
	 * Verify specific object exist or not 
	 * @param id
	 * @param idValue
	 */
	public void verifyCheckObjectExist(String id, String idValue){
		if(idValue.equals("false")){
			if(this.checkObjectExist(id)){
				throw new ErrorOnDataException("Object should doesn't exist.");
			}
		}else {
			if(!this.checkObjectExist(id)){
				throw new ErrorOnDataException("Object should exist.");
			}
		}

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
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		browser.clickGuiObject(".class", "Html.A", ".text", "View Change Request Items");
	}
}
