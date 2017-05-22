/*
 * $Id: VnuMgrAddTicketsPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsChangeTicketTypePage extends OrmsPage {

	/**
	 * Script Name   : VnuMgrAddTicketsPage
	 * Generated     : Feb 19, 2007 2:37:14 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/02/19
	 */
	private static OrmsChangeTicketTypePage _instance = null;

	private OrmsChangeTicketTypePage() {
	}

	public static OrmsChangeTicketTypePage getInstance() {
		if (null == _instance)
			_instance = new OrmsChangeTicketTypePage();

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Change Ticket Type");
		p[2] = new Property(".href", new RegularExpression( //javascript:void( initForm() ) in QA4 environment
				".*changetickettype.*|.*void.*", false));
		return browser.checkHtmlObjectExists(p);
	}

	/**
	 * Input Quantity num
	 * @param quantity
	 */
	public void setQuantityNum(String quantity) {
		//Note: Please add comments if you found id changed
//		if(browser.checkHtmlObjectExists(".id", "quantity_1_0")){
//			browser.setTextField(".id", "quantity_1_0", quantity);
//		}
//		//For QA3(Build3.02.01.164) Venue/Operation Mgr
//		if(browser.checkHtmlObjectExists(".id", "quantity_0_0")){
//			browser.setTextField(".id", "quantity_0_0", quantity);
//		}
//		if(browser.checkHtmlObjectExists(".id", "quantity_0_1")){
//			browser.setTextField(".id", "quantity_0_1", quantity);
//		}
//		//For QA3(Build3.02.01.164) Call Mgr
//		if(browser.checkHtmlObjectExists(".id", "quantity_0_0_2")){
//			browser.setTextField(".id", "quantity_0_0_2", quantity);
//		}
//		if(browser.checkHtmlObjectExists(".id", "quantity_0_0_3")){
//			browser.setTextField(".id", "quantity_0_0_3", quantity);
//		}
		//update for id changed(QA3(Build3.02.01.171),Venue Manager)--Sophia
		if(browser.checkHtmlObjectExists(".id", new RegularExpression("^quantity.*",false))){
			IHtmlObject[] objs=browser.getTextField(".id", new RegularExpression("^quantity.*",false));
			IText text=(IText)objs[objs.length-1];
			text.setText(quantity);
		}
		
	}

	public void selectFromType(String fType) {
//		int index = 0;
//		if (fType.equalsIgnoreCase("Adult")) {
//			index = 0;
//		} else if (fType.equalsIgnoreCase("Child")) {
//			index = 1;
//		} else if (fType.equalsIgnoreCase("Youth")) {
//			index = 2;
//		} else if (fType.equalsIgnoreCase("Child (3 - 16)")) {
//			index = 2;
//		} else if (fType.equalsIgnoreCase("Interagency Access Pass")) {
//			index = 2;
//		} else {
//			throw new ItemNotFoundException(
//					"The input item is out of the dropdown list bound");
//		}
//		browser.selectDropdownList(".id", "fromTicketType_0_0", index, 1); //the From dropdown list's index is 1 in all of the dropdown lists
//		if(browser.checkHtmlObjectExists(".id", "fromTicketType_0_0")){
//			browser.selectDropdownList(".id", "fromTicketType_0_0", fType,1);
//		}
//		if(browser.checkHtmlObjectExists(".id", "fromTicketType_1_0")){
//			browser.selectDropdownList(".id", "fromTicketType_1_0", fType,1);
//		}
//		if(browser.checkHtmlObjectExists(".id", "fromTicketType_0_1")){
//			browser.selectDropdownList(".id", "fromTicketType_0_1", fType,1);
//		}
//		//For QA3(Build3.02.01.164) Call Mgr
//		if(browser.checkHtmlObjectExists(".id", "fromTicketType_0_0_2")){
//			browser.selectDropdownList(".id", "fromTicketType_0_0_2", fType,1);
//		}
		//update for id changed(QA3(Build3.02.01.171),Venue Manager)--Sophia
		if(browser.checkHtmlObjectExists(".id", new RegularExpression("^fromTicketType.*",false))){
			IHtmlObject[] objs=browser.getDropdownList(".id", new RegularExpression("^fromTicketType.*",false));
			ISelect text=(ISelect)objs[objs.length-1];
			text.select(fType);
		}
	}

	/**
	 * Select ticket Type
	 * @param tType
	 */
	public void selectToType(String tType) {
//		int index = 0;
//		if (tType.equalsIgnoreCase("Adult")) {
//			index = 0;
//		} else if (tType.equalsIgnoreCase("Child")) {
//			index = 1;
//		} else if (tType.equalsIgnoreCase("Youth")) {
//			index = 2;
//		} else {
//			throw new ItemNotFoundException(
//					"The input item is out of the dropdown list bound");
//		}
//		browser.selectDropdownList(".id", "toTicketType_0_0", index, 1);
//		if(browser.checkHtmlObjectExists(".id", "toTicketType_0_0")){
//			browser.selectDropdownList(".id", "toTicketType_0_0", tType,1);
//		}
//		if(browser.checkHtmlObjectExists(".id", "toTicketType_1_0")){
//			browser.selectDropdownList(".id", "toTicketType_1_0", tType,1);
//		}
//		if(browser.checkHtmlObjectExists(".id", "toTicketType_0_1")){
//			browser.selectDropdownList(".id", "toTicketType_0_1", tType,1);
//		}
//		//For QA3(Build3.02.01.164) Call Mgr
//		if(browser.checkHtmlObjectExists(".id", "toTicketType_0_0_2")){
//			browser.selectDropdownList(".id", "toTicketType_0_0_2", tType,1);
//		}
		//update for id changed(QA3(Build3.02.01.171),Venue Manager)--Sophia
		if(browser.checkHtmlObjectExists(".id", new RegularExpression("^toTicketType.*",false))){
			IHtmlObject[] objs=browser.getDropdownList(".id", new RegularExpression("^toTicketType.*",false));
			ISelect text=(ISelect)objs[objs.length-1];
			text.select(tType);
		}
	}

	/**Click AddTicketSelection*/
	public void clickAddTicketSelection() {
		browser.clickGuiObject(".class", "Html.A", ".text","Add Ticket Selection");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**click Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**Change ticket type*/
	public void changeTicketType(String fType, String tType, String addNum) {
		this.setQuantityNum(addNum);
		selectFromType(fType);
		selectToType(tType);
	}
	
	public List<String> getDeliveryMethods(){
		return getAttibuteValueByName("Delivery Method");
	}
	/**
	 * get each value.
	 * @param name
	 * @return
	 */
	private List<String> getAttibuteValueByName(String name){
		List<String> list = new ArrayList<String>();
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute which name is - " + name);
		}
		for(int i=0;i<objs.length;i++){
			String value = objs[i].getProperty(".text").split(name)[1].trim();
			list.add(value);
		}
		return list;
	}
	/**
	 * get the ticket selection table text info.
	 * @return
	 */
	public List<List<String>> getTicketSelectionRowInfo(){
		List<List<String>> selectionArray = new ArrayList<List<String>>();
		selectionArray.clear();
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".id", new RegularExpression("^ticketSelectionTable_0_0_\\d+",false));
		if(objs.length<1){
			new ErrorOnDataException("Table can't exist");
		}
		for(int i = 0;i<objs.length;i++){
			IHtmlTable table = (IHtmlTable)objs[i];
			selectionArray.add(table.getRowValues(2));
		}
		return selectionArray;
	}
	
	public String getTourDateInvInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Tour Date.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("The span about tour inventory information for tour date is not correct!");
		}
		String content = objs[0].text().replace("Tour Date", "");
		return content;
	}
}
