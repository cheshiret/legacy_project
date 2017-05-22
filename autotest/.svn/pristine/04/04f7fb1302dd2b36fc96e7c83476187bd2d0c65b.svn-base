/*
 * $Id: VnuMgrCancelTicketsPage.java 6747 2008-11-26 18:09:36Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsCancelTicketOrderPage extends OrmsPage {

	/**
	 * Script Name : VnuMgrCancelTicketsPage Generated : Feb 19, 2007 2:47:39 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2007/02/19
	 */
	private static OrmsCancelTicketOrderPage _instance = null;

	private OrmsCancelTicketOrderPage() {
	}

	public static OrmsCancelTicketOrderPage getInstance() {
		if (null == _instance)
			_instance = new OrmsCancelTicketOrderPage();

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Cancel Tickets.*",false));
	}

	/** cancel all tickets */
	public void cancelAllTickets() {
		this.selectCancelAllCheckBox();
		this.clickCancelTickets();
	}

	/** Select CancelAll checkbox */
	public void selectCancelAllCheckBox() {
		
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("cancelAll_\\d+_\\d+", false));
		
		for(int i = 0; i < objs.length; i++) {

			((ICheckBox)objs[i]).select();
			this.waitLoading();
		}

		 Browser.unregister(objs);
	}

	/** UnSelect Cancel All Checkbox */
	public void deselectCancelAllCheckBox() {
		browser.unSelectCheckBox(".id", "cancelAll_1_0");
	}

	/** Click Cancel Tickets */
	public void clickCancelTickets() {
		// browser.clickGuiObject(".class","Html.A",".classIndex","0",".text","Cancel Tickets");
		// Subitem s1 = Browser.atDescendant(".class", "Html.A",
		// ".text","Cancel Tickets");
		// Subitem s2 = Browser.atDescendant(".classIndex", "0");

		// TestObject[] obj = browser.getGuiTestObject(Browser.atList(s1, s2));
		// //MiscFunctions.clickObject(obj[0]);
		// obj[0].click();
		// Browser.unregister(obj);

		 IHtmlObject[] objs=browser.getHtmlObject(".class",
		 "Html.A",".text","Cancel Tickets");
		 objs[1].click();
		 Browser.unregister(objs);
//		String href = "javascript:invokeActionTarget( 'TourOrderDetails.do', 'cancelTickets', 'TourDetailsWorker', '', 'transaction')";
//		browser.clickGuiObject(".class", "Html.A", ".href", href);
	}

	/** Click Don't Cancel */
	public void clickDontCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Cancel");
	}
	
	public void setTicketNumberToCancel(String num) {
		this.setTicketNumberToCancel(null, Integer.parseInt(num));
	}
	
	/**
	 * setup ticket number need to cancel identifier its corresponding ticket type
	 * @param type
	 * @param num
	 */
	public void setTicketNumberToCancel(String type, int num) {
		String typeId = "";
		if(type == null || type.length() < 1) {
			typeId = "1";
		}else if(type.equals("Adult")) {
			typeId = "1";
		} else if(type.equals("Child")) {
			typeId = "21|19";
		} else if(type.equals("Child (3 - 16)")) {
			typeId = "36";
		} else throw new ItemNotFoundException("Unkown ticket type - " + type);
		
		browser.setTextField(".id", new RegularExpression("quantity_" + typeId + "_0_0(|_2|_3)", false), String.valueOf(num));//TODO
	}

	/**
	 * Select cancel reason
	 * 
	 * @param reason
	 */
	public void selectCancelReason(String reason) {
		browser.selectDropdownList(".id", "reason_0_0", reason);
	}

	/**
	 * Input cancel Notes
	 * 
	 * @param notes
	 */
	public void setCancelNotes(String notes) {
		browser.setTextArea(".id", "cancelNotes", notes);
	}

	/**
	 * Select all cancel reason
	 * 
	 * @param reason
	 */
	public void selectAllCancelReason(String reason) {

		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("reason_\\d+_\\d+", false));
		
		for(int i = 0; i < objs.length; i++) {

			((ISelect)objs[i]).select(reason);
			this.waitLoading();
		}

		 Browser.unregister(objs);
	}	
	/**
	 * verify the reason drop down list info.
	 */
	 public void verifyReasionDropDownList(){
		boolean pass = true;
		List<String> list = browser.getDropdownElements(".id", "reason_0_0");
		if(!list.get(0).equals("Customer Change")){
			pass &= false;
			logger.error("the expected dropdown option value is Customer Change but actual is"+list.get(0));
		}
		if(!list.get(1).equals("Tour Cancelled")){
			pass &= false;
			logger.error("the expected dropdown option value is Tour Cancelled but actual is"+list.get(1));
		}
		if(!list.get(2).equals("Waive Penalty")){
			pass &= false;
			logger.error("the expected dropdown option value is Waive Penalty but actual is"+list.get(2));
		}
		if(!pass){
			throw new ErrorOnPageException("The reason dropdown list option item error");
		}
	}
	/**
	 * verify the ticket to cancel first line info.
	 * @param ticketInfo
	 */
	public void verifyTicketsToCancelFirstLineUI(TicketInfo ticketInfo){
		this.verifyTicketsToCancelLineUI(ticketInfo, true);
	}
	/**
	 * verify the ticket to cancel second line info.
	 * @param ticketInfo
	 */
	public void verifyTicketsToCancelSecondLineUI(TicketInfo ticketInfo){
		this.verifyTicketsToCancelLineUI(ticketInfo, false);
	}
	/**
	 * verify the ticket to Cancel line info.
	 * @param ticketInfo
	 * @param isFirstLine
	 */
	private void verifyTicketsToCancelLineUI(TicketInfo ticketInfo,boolean isFirstLine){
		boolean pass = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",new RegularExpression("^\\W*"+ticketInfo.types[0]+".*",false));
		if(objs.length<1){
			throw new ErrorOnDataException("No object element exist");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> rowInfo = table.getRowValues(0);
		if(!rowInfo.get(1).contains(ticketInfo.types[0]+ticketInfo.typeNums[0])){
			pass &= false;
			logger.error("Expected UI display text value is "+ticketInfo.types[0]+ticketInfo.typeNums[0]+"but actual is "+rowInfo.get(1));
		}
		if(!rowInfo.get(2).contains("Delivery Method"+ticketInfo.deliveryMethod)){
			pass &= false;
			logger.error("Expected UI display text value is Delivery Method"+ticketInfo.deliveryMethod+"but actual is "+rowInfo.get(2));
		}
		if(isFirstLine){
			if(!rowInfo.get(5).contains("Cancel All")){
				pass &= false;
				logger.error("Expected UI display text value is Cancel All but actual is "+rowInfo.get(5));
			}
			if(!rowInfo.get(7).contains("Reason")){
				pass &= false;
				logger.error("Expected UI display text value is Reason but actual is "+rowInfo.get(7));
			}
			if(!pass){
				throw new ErrorOnPageException("The # of tickets to cancel first line UI error");
			}
		}
	}
	/**
	 * verify the deliveryMethod unedit.
	 * @param devileryMethod
	 */
	public void verifyDeliveryMethodUnEdit(String devileryMethod){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", devileryMethod);
		if(objs.length<1){
			throw new ErrorOnPageException("The delivery method should be unedit");
		}
	}
	
	/**
	 * get the delivery methods.
	 * @return
	 */
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
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute which name is - " + name);
		}
		for(int i=0;i<objs.length;i++){
			String value = objs[i].getProperty(".text").split(name)[1].trim();
			list.add(value);
		}
		return list;
	}
	public String getErrorMessage() {
		String msg = null;
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(objs.length > 0) {
			msg = objs[0].getProperty(".text");
		}
		
		Browser.unregister(objs);
		return msg;
	}
	
	public String getTourDateInvInfo(){
		return getAttibuteValueByName("Tour Date").get(0);
	}
	
}

