/*
 * $Id: InvMgrChangeRequestDetail.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * TODO: enter class description
 * 
 * @author cguo
 */
public class InvMgrChangeRequestDetail extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrChangeRequestDetail
	 * Generated     : Dec 19, 2005 5:31:53 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/12/19
	 */

	private static InvMgrChangeRequestDetail _instance = null;

	public static InvMgrChangeRequestDetail getInstance() {
		if (null == _instance) {
			_instance = new InvMgrChangeRequestDetail();
		}

		return _instance;
	}

	protected InvMgrChangeRequestDetail() {

	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Change Request Details");
	}

	/**
	 * Select Status
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", "detailStatus", status);
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Apply*/
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**Click Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click Change CR status
	 * @param status
	 */
	public void changeCRStatus(String status) {
		this.selectStatus(status);
		this.clickOK();
	}
	
	/** Get default element of the 'Status' drop down list*/
	public String getStatusDefaultElement(){
		return browser.getDropdownListValue(".id", "detailStatus", 0);
	}
	
	/** Get all elements of the 'Status' drop down list*/
	public List<String> getStatusElements(){
		return browser.getDropdownElements(".id", "detailStatus");

	}
	
	/**Click Change Requests tab*/
	public void clickChangeRequestsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Requests");
	}
	
	/**Check object exist*/
	public boolean checkObjectExist(String textValue){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".value", textValue);
	}
	
	/**
	 * Get the warning message 
	 * @return warning message
	 */
	public String getWarningMessage(){
	    IHtmlObject[] obj=browser.getHtmlObject(".id","NOTSET");
	    String warningMessage=obj[0].getProperty(".text").toString();
	    Browser.unregister(obj);
	    
	    return warningMessage;
	}
}
