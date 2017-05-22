/*
 * $Id: InvMgrChangeReqItemDetail.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author cguo
 */
public class InvMgrChangeReqItemDetail extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrChangeReqItems
	 * Generated     : Dec 19, 2005 3:12:02 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/12/19
	 */

	private static InvMgrChangeReqItemDetail _instance = null;

	public static InvMgrChangeReqItemDetail getInstance() {
		if (null == _instance) {
			_instance = new InvMgrChangeReqItemDetail();
		}

		return _instance;
	}

	protected InvMgrChangeReqItemDetail() {

	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Change Request Item Details");
	}
	
	public void waitExists(int timeout){
		Browser.sleep(timeout);
		browser.waitExists();
	}
	
	/**
	 * Set Item's Detail
	 * @param details
	 */
	public void setItemDetails(String details) {
		browser.setTextArea(".id", "itemDetails", details);
	}

	/**
	 * Set notes
	 * @param notes
	 */
	public void setNewNotes(String notes) {
		browser.setTextArea(".id", "newNotes", notes);
	}

	/**Click Apply Button*/
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Add one chang reg item
	 * @param item
	 * @param notes
	 * @return
	 */
	public String addChangeReqItem(String item, String notes) {
		if (!item.equals("")) {
			this.setItemDetails(item);
		}
		if (!notes.equals("")) {
			this.setNewNotes(notes);
		}
		this.clickApply();

		String id = this.getChangeRequestItemID();
		this.clickOK();

		return id;
	}

	/**
	 * Get change request item ID
	 * @return
	 */
	public String getChangeRequestItemID() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Change Request Item ?Change Request ID.*",false));
		IHtmlTable table = (IHtmlTable)objs[0];
		System.out.println(table.getCellValue(0, 1).toString());
		String toReturn = table.getCellValue(0, 3).toString().replace("Item ID", "").trim();
		Browser.unregister(objs);
		return toReturn;
		
//		Property[] p = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("Request Item Details ?ID.*", false));
//		Property[] p1 = Property.toPropertyArray(".class", "Html.INPUT.text");
//		HtmlObject[] objs = browser.getHtmlObject(Property.atList(p, p1));
//		if(objs==null || objs.length<1){
//			throw new ObjectNotFoundException("Can't find any text input object under 'Request Item Details' section. ");
//		}
//		
//		String toReturn = "";
//		for(int i=0; i<objs.length; i++){
//			String value = objs[i].getProperty(".value").trim();
//			if(value.matches("\\d+")){
//				toReturn = value;
//				break;
//			}
//		}
//		
//		if(StringUtil.isEmpty(toReturn)){
//			throw new ErrorOnPageException("No change request item id can be found.");
//		}
//		logger.debug("Get change request item id - "+toReturn);
//		Browser.unregister(objs);
//		return toReturn;
	}

	/**Click Change Requests tab*/
	public void clickChangeRequestsTab(){
		browser.clickGuiObject(".id", "Change Requests");
	}
	
	/**Check textField and textArea object exist*/
	public boolean checkObjectExist(String textValue){
		boolean inputTextExisted = browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".value", textValue);
		return inputTextExisted || browser.checkHtmlObjectExists(".class", "Html.TEXTAREA", ".text", textValue);
	}
	
	/**
	 * Select Status
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", "itemdetailStatus", status);
	}
	
	/** Check ID of 'Request Item Details'section is exist that make sure the change request items is correct*/
	public boolean checkRequestItemDetailID(String id){
		return browser.checkHtmlObjectExists(".id", "val_"+id);
	}
	
	/**
	 * Check Type of 'Request Item Details'section is exist that make sure the change request items is correct
	 * @param type
	 * @return
	 */
	public boolean checkRequestItemDetailType(String type){
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".text",type);
	}

	/**
	 * Check Code of 'Request Item Details'section is exist that make sure the change request items is correct
	 * @param code
	 * @return
	 */
	public boolean checkRequestItemDetailCode(String code){
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".text",code);
	}
	
	/**
	 * Check Name of 'Request Item Details'section is exist that make sure the change request items is correct
	 * @param name
	 * @return
	 */
	public boolean checkRequestItemDetailName(String name){
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".text",name);
	}
	
	/**
	 * Check Loop of 'Request Item Details'section is exist that make sure the change request items is correct
	 * @param loop
	 * @return
	 */
	public boolean checkRequestItemDetailLoop(String loop){
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".text",loop);
	}
	
	/** Get request item details */
	public String getRequestItemDetail() {
		String requestItemDetail = "";
		Property textAreaProperty[] = Property.toPropertyArray(".id", "itemDetails");
		if(browser.checkHtmlObjectExists(textAreaProperty)) {
			IHtmlObject[] objs = browser.getTextArea(".id","itemDetails");
			if(objs.length < 1) throw new ItemNotFoundException("Cannot find Request Item Details textarea object.");
			requestItemDetail= objs[0].getProperty(".text").toString().replace("\n", " ");
		} else {
		//Quentin[20131115] 3.05 ui changes, Request Item Details changed from 1 TextArea to 4 TextField
			IHtmlObject objs[] = browser.getTextField(".className", "inputwithrubylabel");
			if(objs.length < 1) throw new ItemNotFoundException("Cannot find Request Item Details textfield objects.");
			for(int i = 0; i < objs.length; i ++) {
				requestItemDetail += ((IText)objs[i]).getText();
				if(i < objs.length - 1) {
					requestItemDetail += " : ";
				}
			}
		
			Browser.unregister(objs);
		}
		
		return requestItemDetail;
	}
}
