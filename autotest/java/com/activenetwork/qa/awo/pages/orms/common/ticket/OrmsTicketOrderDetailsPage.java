/*
 * $Id: VnuMgrTicketDetailPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsTicketOrderDetailsPage extends OrmsPage {

	/**
	 * Script Name : VnuMgrTicketDetailPage Generated : Feb 19, 2007 2:13:16 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2007/02/19
	 */

	private static OrmsTicketOrderDetailsPage _instance = null;

	protected OrmsTicketOrderDetailsPage() {
	}

	public static OrmsTicketOrderDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketOrderDetailsPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Order Actions.*Tour Inventory.*", false));
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Order.*Tour Inventory.*", false));
//		browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "OrderDetails");//for order detail appear during making ticket order process
		return this.checkLastTrailValueIs("Ticket Order Details");
	}

	/**
	 * Get ticket order number
	 * @return
	 */
	public String getOrderNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", "ordNum");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Order Number div object.");
		}
		String ordNum = objs[0].getProperty(".text").split("Order #")[1].trim();
		Browser.unregister(objs);
		
		return ordNum;
	}
	
	/** Click Transfer Tickets */
	public void clickTransferTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Transfer( Tickets)?", false));
	}

	/** Click Add Tickets */
	public void clickAddTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Tickets");
	}

	/** Click Add to cart */
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add To Cart");
	}

	/** Click cancel Ticket */
	public void clickCancelTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Tickets");
	}
	
	/** click the cancel button */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}

	/** Click Change Ticket type */
	public void clickChangeTicketType() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Change (Ticket )?Type", false));
	}

	/** Click Void Ticket */
	public void clickVoidTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Void (Ticket )?Order", false));
	}

	/** Click Manage tickets */
	public void clickManageTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Manage Tickets", false),true);
	}

	/** Click Change Ticket Time */
	public void clickChangeTicketTime() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Change (Ticket )?Time", false));
	}

	/** Click Fees */
	public void clickFees() {
		IHtmlObject[] objs = getTransactionFrame();
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees",false,0,objs[0]);
		Browser.unregister(objs);
	}

	/** Click History */
	public void clickHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "History");
	}

	/** Click Note Alerts */
	public void clickNoteAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	/** Click Request ConfLetter */
	public void clickRequestConfLetter() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Request Conf. Letter");
	}

	/** Click OK */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/** Click OK */
	public void clickAccept() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Accept");
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	/** Click Pring Ticket */
	public void clickPrintTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Print( Tickets)?", false));
	}

	/** Click Pring All Tickets */
	public void clickPrintAllTickets() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Print All Tickets");
	}

	/** Retrieve Invoice Num */
	public String getInvoiceNum() {
		String invoiceNum = browser.getObjectText(".class", "Html.A", ".id",
				"invoiceNum").toString();
		return invoiceNum;
	}

	/*** Retrieve Tour Date */
	public String getTourDate() {
		String tourDate = "";
		StringBuffer tourDateBuffer = new StringBuffer();
		IHtmlObject[] tourDateInfo = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression(
						"^Tour Inventory Tour.*", false));

		for (int i = 0; i < tourDateInfo.length; i++) {
			String tempPri = tourDateInfo[i].getProperty(".text").toString();
			String splitTempPri[] = tempPri.split("Tour Date |Tour Time");
			String resultSplitTempPri = splitTempPri[1];
			tourDateBuffer.append(resultSplitTempPri + "*****");

		}
		Browser.unregister(tourDateInfo);
		tourDate = tourDateBuffer.toString();
		return tourDate;
	}

	/**
	 * get the number of the different cutomer types include Adult, Child, Youth
	 * */
	public String getTypeNum(String type) {
		String typeNum = "";
		IHtmlObject[] typeRes = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression("^Tour.*"+type+".*", false));
		if(typeRes.length < 1){
			return "0";
		}
		String result = typeRes[typeRes.length-1].getProperty(".text").toString().replaceAll(
				"\\r\\n", " ").trim();
//		int order = result.indexOf(type) + type.length() + 1;
		int order = result.indexOf(type) + type.length();
		result = result.substring(order).trim();
		String[] temp = result.split(" ");
		typeNum = temp[0].substring(0, 1);
		if (typeNum.length() == 0 || !typeNum.matches("[0-9]+")) {
			typeNum = "0";
		}
		Browser.unregister(typeRes);
		return typeNum;
	}

	/**
	 * Get the order status from the table
	 * 
	 * @return order status
	 */
	public String getOrderStatus() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Tour( Inventory)?.*", false));
		String result = obj[obj.length-1].getProperty(".text").toString().replaceAll(
				"\\r\\n", " ");
		int i = result.indexOf("Order Item Status")
				+ "Order Item Status".length();
		int j = result.indexOf("Confirmation Status");
		String status = result.substring(i, j).trim();
		Browser.unregister(obj);
		return status;
	}

	/**
	 * Get Ticket details cell value
	 * 
	 * @param row
	 *            --The row of the cell
	 * @param col
	 *            --The col of the cell
	 * @return---Get the cell value
	 */
	public String getTicketDetailsCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Order Actions.*",false));

		String toReturn = ((IHtmlTable) objs[1]).getCellValue(row, col);
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Get ticket price from the table
	 * 
	 * @return price
	 */
	public String getTicketPrice() {
		IHtmlObject[] priceInfo = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression("^Balance.*",
						false));
		String tempPri = priceInfo[0].getProperty(".text").toString();
		int i = tempPri.indexOf("Price");
		int j = tempPri.indexOf("Paid");
		String prices = tempPri.substring(i + 6, j).trim();
		Browser.unregister(priceInfo);

		return prices;
	}

	/**
	 * 
	 * TODO check the button which label is as the param is disabled or not
	 * 
	 * @param label
	 * @return
	 * @Return boolean if the button is disabled,return true,or return false.
	 * @Throws
	 */
	public boolean isThisButtonDisabled(String label) {
		boolean flag = false;

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", label);

		try {
			if (objs.length < 1) {
				throw new ItemNotFoundException("the button that label is "
						+ label + " is not found");
			}

			IHtmlObject[] urlobjs = browser.getHtmlObject(".class", "Html.A",
					".text", label);

			flag = urlobjs.length < 1 ? true : false;
			Browser.unregister(urlobjs);
		} finally {
			Browser.unregister(objs);
			
		}
		return flag;
	}
	
	/**
	 * Click 'Request Print at Home' button
	 */
	public void clickRequestPrintAtHome() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Print at Home", true);
	}
	/**
	 * check request print at home button exist or not
	 * @return
	 */
	public boolean checkRequestPrintAtHomeButtonExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Request Print at Home");
	}
	
	/**
	 * Check whether the 'Request Print at Home' button is enabled or not
	 * @return
	 */
	public boolean checkRequestPrintAtHomeEnabled() {
		return !isThisButtonDisabled("Request Print at Home");
	}
	
	/**
	 * Check if the 'Request Print at Home' button exists or not
	 * @return
	 */
	public boolean checkRequestPrintAtHomeExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Request Print at Home");
	}
	
	/**
	 * Get error message on order detail page
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("NOTSET|V-100100",false));//Shane[20140106],add 'V-100100' for Minimum Ticket Restriction message
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not error message object.");
		}
		String msg = objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return msg;
	}
	
	/**
	 * 
	 * @param expectedMsg
	 */
	public void verifyErrorMsgAfterClickRequestPrintAtHome(String expectedMsg) {
		logger.info("Verify whether error message displayed at ticket order detail page is correct or not.");
		
		this.clickRequestPrintAtHome();
		this.waitLoading();
		String msgOnPage = this.getErrorMessage();
		if(!msgOnPage.equals(expectedMsg)) {
			throw new ErrorOnPageException("The error message is wrong. Expected is: " + expectedMsg + ", but actual is: " + msgOnPage);
		} else logger.info("----Error message - '" + msgOnPage + "' is correct.");
	}
	
	/**
	 * Get printed number of specific order item identified by delivery method
	 * @param deliveryMethod
	 * @return
	 */
	public int getPrintedNum(String deliveryMethod) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("Delivery Method(\\s)?" + deliveryMethod + "$", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find TR object.");
		}
//		int num1 = objs[0].getChildren().length;
//		System.out.println(num1);
//		for(int i = 0; i < num1; i ++) {
//			System.out.println(objs[i].getProperty(".text"));
//		}
		int num = Integer.parseInt(objs[objs.length - 1].getChildren()[1].getProperty(".text").split("Printed")[1].trim());
		Browser.unregister(objs);
		
		return num;
	}
	/**
	 * Get delivery method by ticket type.
	 * @param ticketType
	 * @return
	 */
	public String getDeliveryMethodByTicketType(String ticketType){
		String title = "Delivery Method";
		IHtmlObject[] deliveryMethodObj = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression("^"+ticketType+".*",
						false));
		if(deliveryMethodObj.length<1){
			throw new ErrorOnDataException("No this ticket type delivery method object exist");
		}
		String tableText = deliveryMethodObj[0].getProperty(".text").toString();
		int i = tableText.indexOf(title);
		String deliveryMethod = tableText.substring(i + title.length(), tableText.length());
		Browser.unregister(deliveryMethodObj);
		return deliveryMethod;
	}
	
	/**
	 * Get delivery method by ticket type in paticipant attribute(per ticket part).
	 * @param ticketType
	 * @return
	 */
	public List<String> getDeliveryMethodInPerTicket(){
		String title = "Delivery Method";
		List<String> list = new ArrayList<String>();
		IHtmlObject perTicket[] = browser.getHtmlObject(".id", "attrSectionPerTicket");
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + "Delivery Method", false)),perTicket[0] );
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute which name is - " + title);
		}
		for(int i=0;i<objs.length;i++){
			String value = objs[i].getChildren()[1].getProperty(".value").trim();
			list.add(value);
		}
		return list;
	
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

	/************************Order detail page during making process******************************/
	private String getAttributeValueByName(String name) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name, false)));
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute which name is - " + name);
		}
		
		String value = objs[0].getProperty(".text").split(name)[1].trim();
		Browser.unregister(objs);
		
		return value;
	}
	
	public String getTicketCategory() {
		return getAttributeValueByName("Ticket Category");
	}
	
	public String getSalesChannel() {
		return getAttributeValueByName("Sales Channel");
	}
	
	public String getTourName() {
		return getAttributeValueByName("Tour");
	}
	
	public String getTourDateValue() {
		return getAttributeValueByName("Tour Date");
	}
	
	public String getTourTime() {
		return getAttributeValueByName("Tour Time");
	}
	
	public String getTourStatus() {
		return getAttributeValueByName("Tour Status");
	}
	
	public String getTicketNumByType(String type){
		return getAttributeValueByName(type);
	}
	
	public String getDeliveryMethod() {
		return getAttributeValueByName("Delivery Method");
	}
	
	/**
	 * common set TPA text field value for 'Per Ticket' identified by TicketType, AttributeID and TPA row index
	 * @param index - locate the TPAs row
	 * @param ticketType - locate the TPAs row
	 * @param attributeID - locate specific TPA position
	 * @param text
	 */
	private void commonSetTPATextField(int index, String ticketType, String attributeID, String text) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".id", new RegularExpression("currentSubsectionRow" + index + ticketType, false)));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found TPA object, index = " + index + ". Ticket Type = " + ticketType);
		}
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + attributeID + "\\]", false), text, objs[0]);
		Browser.unregister(objs);
//		browser.setTextField(Property.atList(Property.toPropertyArray(".class", "Html.TR", ".id", new RegularExpression("currentSubsectionRow" + index + ticketType, false)), Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + attributeID + "\\]" + ":DATE2_ForDisplay", false))), text);
//		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[" + attributeID + "]", false), text, top);
	}
	
	/**
	 * 
	 * @param index
	 * @param ticketType
	 * @param attributeID
	 * @param value
	 */
	private void commonSelectTPADropdownList(int index, String ticketType, String attributeID, String value) {
		//TODO
	}
	
//	public void setFirstName(String ticketType, int index, String fName) {
//		commonSetTPATextField(ticketType, "2631", index, fName);
//	}
//	
//	public void setDateOfBirth(String ticketType, int index, String dob) {
//		commonSetTPATextField(ticketType, "2632", index, dob);
//	}
//	
//	public void setVisitedTimes(String ticketType, int index, String time) {
//		commonSetTPATextField(ticketType, "2633", index, time);
//	}
//	
//	public void setArrivalTime(String ticketType, int index, String arrivalTime) {
//		commonSetTPATextField(ticketType, "2634", index, arrivalTime);
//	}
//	
//	public void setPower(String ticketType, int index, String power) {
//		commonSetTPATextField(ticketType, "2635", index, power);
//	}
//	
//	public void setLastName(String ticketType, int index, String lName) {
//		commonSetTPATextField(ticketType, "2636", index, lName);
//	}
//	
//	public void setSplConsid(String ticketType, int index, String splConsid) {
//		commonSetTPATextField(ticketType, "2637", index, splConsid);
//	}
	
	private String convertAttribute(String from) {
		String to = "";
		String attributeStrs[] =new String[] {"First Name", "Date of Birth", "Visited Times", "Arrival Time", "Power", "Last Name", 
				"Special Considerations","Tour_Power", "EverVisit", "WishPriceFrom", "WishPriceTo", "Tour_SplConsid", 
				"EverVisited?", "Tour Power","Wish Price From", "Wish Price To", "Names", "Alerts", 
				"Arrival", "Gender", "Currency", "Anniversary", "WishPrice", "Address","Grade K-12"};
		String attributeIDs[] = new String[] {"2631", "2632", "2633", "2634", "2635", "2636", 
				"2637", "2635", "2000004", "2000007", "2000013", "2637", 
				"2000004", "2635", "2000007", "2000013", "300000001", "300000003", 
				"2000006", "300000002", "300000011", "300000005", "2000007", "2000002","2638"};
		
		if(from.matches("[0-9]+")) {
			for(int i = 0; i < attributeIDs.length; i ++) {
				if(from.equals(attributeIDs[i])) {
					to = attributeStrs[i];
					break;
				}
			}
		} else {
			for(int i = 0; i < attributeStrs.length; i ++) {
				if(from.equals(attributeStrs[i])) {
					to = attributeIDs[i];
					break;
				}
			}
		}
		if(to.length() < 1) {
			throw new ItemNotFoundException("Unkown TPA attribute - " + from);
		}
		
		return to;
	}
	
	public void setTPAsForEachTicket(String ticketType, int index, Map<String, String> map) {
		String attributeID = "";
		String key = "";
		Set<Map.Entry<String, String>> set = map.entrySet();
		for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			key = entry.getKey();
			
			attributeID = convertAttribute(key);
			
			//use corresponding method to set TPA values
			switch(Integer.parseInt(attributeID)) {
			case 2631://First Name
			case 2632://Date of Birth
			case 2633:
			case 2634:
			case 2635:
			case 2636://Last Name
				if(attributeID.equals("2631"))
					attributeID = "2631|2000003";
				if(attributeID.equals("2636"))
					attributeID = "2636|2000005";
				commonSetTPATextField(index, ticketType, attributeID, entry.getValue());
				break;
			case 2637:
				commonSelectTPADropdownList(index, ticketType, attributeID, entry.getValue());
				break;
				
				default:
					//TODO
			}
		}
	}
	
	/**
	 * set TPAs for 'Per Ticket'
	 * @param ticketType
	 * @param list
	 */
	public void setTourParticipantAttributesForPerTicket(String ticketType, List<Map<String, String>> list) {
		if(ticketType.contains("(") && ticketType.contains(")")) {
			ticketType = ticketType.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		}
		IHtmlObject[] perTicketObjs = browser.getHtmlObject(".id", "attrSectionPerTicket");
		IHtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("currentSubsectionRow\\d+" + ticketType, false), perTicketObjs[0]);
		
		if(trObjs.length < 1) {
			throw new ItemNotFoundException("Can't find any TPA TR object(s) under ticket type - " + ticketType);
		}
		int countOnPage = trObjs.length - 2;//the 1st and last rows are NOT TPAs
		int expectedCount = list.size();
		if( countOnPage != expectedCount) {
			throw new ErrorOnPageException("There should be " + expectedCount + " 'Per Ticket' TPAs for these tickets, but actual is " + countOnPage);
		}
		int tpaRowIndex = -1;
		for(int i = 0; i < list.size(); i ++) {
			tpaRowIndex = Integer.parseInt(trObjs[i+1].getProperty(".id").split("currentSubsectionRow")[1].split(ticketType)[0].trim());
			setTPAsForEachTicket(ticketType, tpaRowIndex, list.get(i));
		}
		
		Browser.unregister(perTicketObjs);
		Browser.unregister(trObjs);
	}
	
	public void setTourParticipantAttributeForPerInventory(String key, String value) {

//		HtmlObject trObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".id", new RegularExpression("attrSectionPerInventory", false), ".text", new RegularExpression("^" + key, false)));
		//[Jane:]changed here for venue mgr setup TPA info page, per inventory TR id changed to currentSubsectionRow5Adult, not attrSectionPerInventory
		IHtmlObject trObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^" + key, false)));

		if(trObjs.length < 1) {
			throw new ItemNotFoundException("Can't find Tour Participant Attribute section object by key - " + key);
		}
		
		String attributeID = convertAttribute(key);
		//use corresponding method to set TPA values
		switch(Integer.parseInt(attributeID)) {
		case 2631:
		case 2632:
		case 2633:
		case 2634:
		case 2635://Tour Power
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:BOOLEAN_YESNO", false), value, true, trObjs[trObjs.length-1]);
			break;
		case 2636:
			//TODO
			break;
		case 2637://Special Considerations
			browser.setTextArea(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value, true, 0, trObjs[trObjs.length-1]);
			break;
		case 2638://grade
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value, true,trObjs[trObjs.length-1]);
			break;
		case 2000004://EverVisited
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:BOOLEAN_YESNO", false), value, true, trObjs[trObjs.length-1]);
			break;
		case 2000007: //Wish Price From
			browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value, trObjs[trObjs.length-1]);
			break;
		case 2000013://Wish Price To
			browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false), value, trObjs[trObjs.length-1]);
			break;
			default:
				//TODO
		}

		Browser.unregister(trObjs);
	}
	
	/**
	 * set tour participant attributes
	 * @param map - key is TPA name, value is TPA value
	 */
	public void setTourParticipantAttributesForPerInventory(Map<String, String> map) {
		IHtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("attrSectionPerInventory", false));
		if(trObjs.length < 1) {
			throw new ItemNotFoundException("Can't find Tour Participant Attribute section object.");
		}
		
		if(trObjs.length != map.size()) {
			throw new ErrorOnPageException("The actual Tour Participant Attributes length doesn't match your parameter. Expected is: " + map.size() + ", but actual is: " + trObjs.length);
		}
		Set<Map.Entry<String, String>> set = map.entrySet();
//		for(int i = 0; i < trObjs.length; i ++) {
			for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
				setTourParticipantAttributeForPerInventory(entry.getKey(), entry.getValue());
			}
//		}
		
//		Browser.unregister(trObjs);
	}

//	/**
//	 * Get tour participant attributes keys - values
//	 * @return key - TPA name, value - TPA value
//	 */
//	public Map<String, String> getTourParticipantAttributesForPerInventory() {
//		HtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("attrSectionPerInventory", false));
//		if(trObjs.length < 1) {
//			throw new ItemNotFoundException("Can't find Tour Participant Attribute section object.");
//		}
//		
//		IHtmlObject children[] = null;
//		String key = "";
//		String value = "";
//		String tempAttrID = "";
//		Map<String, String> map = new HashMap<String, String>();
//		for(int i = 0; i < trObjs.length; i ++) {
//			children = trObjs[i].getChildren();
//			key = children[0].getProperty(".text");
//			tempAttrID = this.convertAttribute(key);
//			switch(Integer.parseInt(tempAttrID)) {
//			case 2631:
//				break;
//			case 2632:
//				break;
//			case 2633:
//				break;
//			case 2634:
//				break;
//			case 2635:
//				break;
//			case 2636:
//				break;
//			case 2637://Special Considerations
//				value = browser.getTextAreaValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + tempAttrID + "\\]", false));
//				break;
//				default:
//			}
//			
//			if(!StringUtil.isEmpty(value)) {
//				map.put(key, value);
//			}
//		}
//		
//		Browser.unregister(trObjs);
//		Browser.unregister(children);
//		return map;
//	}
	
	private IHtmlObject[] getTourInventoryTableObject(String tourName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory(\\s)?Tour(\\s)?" + tourName + ".*Ticket Status(\\s)?Active.*",false));
		
		if(objs.length == 0) {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory(\\s)?Tour(\\s)?" + tourName, false));
		}
		
		return objs;
	}
	
	/**
	 * Get tour participant attributes keys - values
	 * @param tourName
	 * @return
	 */
	public Map<String, String> getTourParticipantAttributesOfPerInventory(String tourName){
		Map<String, String> tourParticipantAttributesForPerInventory = new HashMap<String, String>();
		IHtmlObject[] tourObjs = getTourInventoryTableObject(tourName);

		if(tourObjs.length<1){
			throw new ItemNotFoundException("Did not found tour inventory info. Tour Name = " + tourName);
		}
		
		IHtmlObject[] perInventoryObjs = browser.getHtmlObject(Property.toPropertyArray(".id", new RegularExpression("attrSectionPerInventory", false)), tourObjs[tourObjs.length - 1]);
		if(perInventoryObjs.length<1){
			throw new ItemNotFoundException("Did not found per inventory object.");
		}
		
		String key = "";
		String value = "";
		String tempAttrID = "";
		for(int i=0; i<perInventoryObjs.length; i++){					
			IHtmlObject[] typeObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TD",".className","cell_control"), perInventoryObjs[i]);
			IHtmlObject[] lableObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TD",".className","label_row"), perInventoryObjs[i]);
			if(typeObjs.length<1){
				throw new ItemNotFoundException("Did not found ttribute type per inventory object.");
			}
			key = lableObjs[0].text();//attrTypeObjs[0].text();
			// For checkbox attribute with group name and label name. Updated by Lesley Wang
			IHtmlObject[] checkboxLabelDivs = browser.getHtmlObject(".class", "Html.DIV", ".classname", "label_ruby", perInventoryObjs[i]);
			if (checkboxLabelDivs.length > 0) {
				key = checkboxLabelDivs[0].text();
			} else {
				//[Jane:]For some TPS cases, per inventory info has label_row in cell_control, in this condition, will use label_row in cell_contral as key value
				IHtmlObject[] attrTypeObjs = browser.getHtmlObject(".class", "Html.LABEL", typeObjs[typeObjs.length-1]);
				if(attrTypeObjs.length > 0)
					key = attrTypeObjs[0].text();
			}	
			
			tempAttrID = this.convertAttribute(key);
			
			switch(Integer.parseInt(tempAttrID)) {
			case 2631:
				break;
			case 2632:
				break;
			case 2633:
				break;
			case 2634:
				break;
			case 2636:
				break;
			case 2637://Special Considerations
			case 2000002: // Address for NRRS	
				value = browser.getTextAreaValue(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + tempAttrID + "\\]",false)), 0, perInventoryObjs[i]);
				break;
			case 300000011:
				value = browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + tempAttrID + "\\]",false)), 0, perInventoryObjs[i]);
				break;
			case 2000004:
			case 2635:
				value = browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:BOOLEAN_YESNO",false)), 0, perInventoryObjs[i]);
				break;
			case 2000007:
			case 2000013:
				value = browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]",false)), 0, perInventoryObjs[i]);
				break;
			case 300000003:
				value = this.getSelectedCheckboxesLabel(tempAttrID, perInventoryObjs[i]);
				break;
			case 2000006: // Arrival
				value = browser.getDropdownListValue(Property.toPropertyArray(".id", 
						new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + tempAttrID + "\\]:TIME_hour",false)), 0, perInventoryObjs[i])
						+ " " + browser.getDropdownListValue(Property.toPropertyArray(".id", 
						new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + tempAttrID + "\\]:TIME_minute",false)), 0, perInventoryObjs[i]) 
						+ " " + browser.getDropdownListValue(Property.toPropertyArray(".id", 
						new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[" + tempAttrID + "\\]:TIME_ampm",false)), 0, perInventoryObjs[i]);
				break;		
			default:
			}
			
			tourParticipantAttributesForPerInventory.put(key, value);
			//Browser.unregister(attrTypeObjs);
			Browser.unregister(lableObjs);
			Browser.unregister(typeObjs);
			Browser.unregister(checkboxLabelDivs);
		}
		Browser.unregister(perInventoryObjs);
		Browser.unregister(tourObjs);	
		
		return tourParticipantAttributesForPerInventory;
	}
	
	/**
	 * Get tour TPA values of per ticket
	 * @param tourName
	 * @param contractCode
	 * @return
	 */
	public Map<String, List<Map<String, String>>> getTourParticipantAttributesOfPerTicket(String tourName){
		IHtmlObject[] tourObjs = getTourInventoryTableObject(tourName);
		
		if(tourObjs.length<1){
			throw new ItemNotFoundException("Did not found tour inventory info. Tour Name = " + tourName);
		}
		
		IHtmlObject[] ticketTypeObjs = null;
		IHtmlObject[] attrTRObjs = null;
		IHtmlObject[] attrTypeObjs = null;
		IHtmlObject[] perTicketObjs = null;
		Map<String, List<Map<String, String>>> tourParticipantAttributesForPerTicket = new HashMap<String, List<Map<String, String>>>();
		for(int k = 0; k < tourObjs.length; k ++) {
			ticketTypeObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN",".className","label_span_ticket_attribute"), tourObjs[k]);
			if(ticketTypeObjs.length<1){
				throw new ItemNotFoundException("Did not found ticke type object.");
			}		
			perTicketObjs = browser.getHtmlObject(".id", "attrSectionPerTicket", tourObjs[k]);
			attrTRObjs = browser.getHtmlObject(Property.toPropertyArray(".id", new RegularExpression("currentSubsectionRow\\d+.*", false)), perTicketObjs[0]);
			if(attrTRObjs.length<2){
				throw new ItemNotFoundException("Did not found tour participant attribute row object.");
			}
			
			attrTypeObjs = browser.getHtmlObject(".class", "Html.LABEL", attrTRObjs[1]);
			if(attrTypeObjs.length<1){
				throw new ItemNotFoundException("Did not found tour participant attribute type object.");
			}
								
			for(int i=0; i<ticketTypeObjs.length; i++){
				String ticketType = ticketTypeObjs[i].text();
				List<Map<String, String>> tpasListForTicketType = new ArrayList<Map<String, String>>();
				
				for(int j=0; j<attrTRObjs.length; j++){								
					if(attrTRObjs[j].id().endsWith(ticketType) 
							&& (browser.checkHtmlObjectExists(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper((-\\d+)|)\\.attr\\[\\d+\\]",false)), attrTRObjs[j])
									|| browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.SPAN", ".className","tour_participant_form_attributes_nolabel"), attrTRObjs[j]))){
											
						Map<String, String> perTicketTPA = new HashMap<String, String>();
						String value = "";
						String key = "";
						String attributeID = "";
						for(int n=0; n< attrTypeObjs.length; n++){
							key = attrTypeObjs[n].text().replace("*", "").trim();
							if(!key.equals("Ticket #") && !key.equals("Delivery Method")){
								attributeID = this.convertAttribute(key);
								
								//use corresponding method to get TPA values
								switch(Integer.parseInt(attributeID)) {
									case 2631://First Name
									case 2632://Date of Birth
									case 2636://Last Name
									case 2633://Visited Times
									case 300000001: // Names for CA
									case 300000006: // Visited times for CA
										if(attributeID.equals("2631"))
											attributeID = "2631|2000003";
										if(attributeID.equals("2636"))
											attributeID = "2636|2000005";
										if (attributeID.equals("2633"))
											attributeID = "2633|300000006";
										if (attributeID.equals("2632"))
											attributeID = "2632|2000001";
										value = this.getTextTPAValueOfPerTicket(attrTRObjs[j], attributeID, n);
										break;
									case 2634:
									case 2635://Power
									case 300000002: // Gender
										value = this.getDropDownListTPAValueOfPerTicket(attrTRObjs[j], attributeID, n);
										break;
//									case 2633:
//										break;
									case 2637:
										//TODO
									case 300000005: // Anniversary for CA
										value = this.getTextTPAValueOfPerTicket(attrTRObjs[j], attributeID, n);
//										value = DateFunctions.formatDate(value, "MM/dd/yyyy");
										break;
									default:
										//TODO
								}
								
								if(Integer.parseInt(attributeID.split("\\|")[0]) == 2632) {
									value = DateFunctions.formatDate(value);//formated to "M/d/yyyy"
								}
								
								perTicketTPA.put(key, value);
							}							
						}						
						tpasListForTicketType.add(perTicketTPA);										
					}
				}
				
				tourParticipantAttributesForPerTicket.put(ticketType, tpasListForTicketType);
			}
		}
		
		Browser.unregister(attrTypeObjs);
		Browser.unregister(attrTRObjs);
		Browser.unregister(ticketTypeObjs);
		Browser.unregister(tourObjs);
		Browser.unregister(perTicketObjs);
		
		return tourParticipantAttributesForPerTicket;
	}
	
	/**
	 * Get text TPA value
	 * @param topObject
	 * @param attrID
	 * @param index
	 * @return
	 */
	public String getTextTPAValueOfPerTicket(IHtmlObject topObject, String attrID,int index){
		String value = "";
		IHtmlObject[] objs1 = browser.getHtmlObject(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper((-\\d+)|)\\.attr\\[" + attrID+ "\\](:DATE_ForValidation)?",false)), topObject);
		
		IHtmlObject[] objs2 = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className","tour_participant_form_attributes_nolabel"), topObject);
		if(objs1.length<1 && objs2.length<1){
			throw new ItemNotFoundException("Did not found TPA first name object.");
		}else if (objs1.length>0){
			String objType = objs1[0].getProperty(".className");
			if(objType.equals("inputwithrubylabel")){
				value = objs1[0].getAttributeValue("value");
				//
				if(StringUtil.isEmpty(value)) {
//					System.out.println(objs1[0].text());
					IHtmlObject[] spanObjs= browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), objs1[0]);
					if(spanObjs.length<1){
						throw new ItemNotFoundException("Did not found first name SPAN object.");
					}
					value = spanObjs[1].text();
					Browser.unregister(spanObjs);
				}	
			}else {
				value = browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper((-\\d+)|)\\.attr\\[" + attrID+ "\\](:DATE_ForValidation)?",false)), topObject);
			}
		}else {
			
			value = objs2[index-2].text();
		}
		
		Browser.unregister(objs1);
		Browser.unregister(objs2);
		return value;
	}
	
	/**
	 * Get drop down list TPA value
	 * @param topObject
	 * @param attrID
	 * @param index
	 * @return
	 */
	public String getDropDownListTPAValueOfPerTicket(IHtmlObject topObject, String attrID,int index){
		String value = "";
		IHtmlObject[] objs1 = browser.getHtmlObject(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper((-\\d+)|)\\.attr\\[" + attrID+ "\\]",false)), topObject);
		
		IHtmlObject[] objs2 = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className","tour_participant_form_attributes_nolabel"), topObject);
		if(objs1.length<1 && objs2.length<1){
			throw new ItemNotFoundException("Did not found TPA power object.");
		}else if(objs1.length>0){
			String objType = objs1[0].getProperty(".class");
			if(objType.equals("Html.DIV")){
				IHtmlObject[] spanObjs= browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), objs1[0]);
				if(spanObjs.length<1){
					throw new ItemNotFoundException("Did not found TPA power span object.");
				}
				
				value = spanObjs[0].text();
				Browser.unregister(spanObjs);
			}else {
				value = browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper((-\\d+)|)\\.attr\\[" + attrID+ "\\]",false)), 0, topObject);
			}
		}else {
			value = objs2[index-2].text();
		}
				
		Browser.unregister(objs1);
		Browser.unregister(objs2);
		return value;
	}
	
	/**
	 * 
	 * @param map
	 */
	public void updateTourParticipantAttributesForPerInventory(String tourName, Map<String, String> map) {
		String key, value = "";
		Map<String, String> originalMap = this.getTourParticipantAttributesOfPerInventory(tourName);
		Set<Map.Entry<String, String>> set = map.entrySet();
		for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			key = entry.getKey();
			value = entry.getValue();
			if(originalMap.containsKey(key)) {//if the TPA already exists, just update the value
				if(!originalMap.get(key).equals(value)) {
					this.setTourParticipantAttributeForPerInventory(key, value);
				}
			} else {//if the TPA doesn't exists, set key and value
				this.setTourParticipantAttributeForPerInventory(key, value);
			}
		}
	}
	
	/**
	 * Get ticket status
	 * @param isComboTour
	 * @param comboTourName
	 * @param tourName
	 * @return
	 */
	public String getTicketStatus(boolean isComboTour,String comboTourName, String tourName){
		IHtmlObject[] ticktStatusDivObjs = new IHtmlObject[]{};
		IHtmlObject[]tourObjs = new IHtmlObject[]{};
		IHtmlObject[] tourInstanceTRObjs = new IHtmlObject[]{};
		if(isComboTour){
			tourObjs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory( |)Tour(| )" + comboTourName + ".*",false));
			if(tourObjs.length<1){
				throw new ItemNotFoundException("Did not found combo tour object.");
			}
			tourInstanceTRObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Tour( |)" + tourName + ".*",false)), tourObjs[tourObjs.length-1]);
			if(tourInstanceTRObjs.length<1){
				throw new ItemNotFoundException("Did not found tour instance object.");
			}
			ticktStatusDivObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Ticket Status.*",false)), tourInstanceTRObjs[tourInstanceTRObjs.length-1]);
		}else {
			tourObjs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory( |)Tour(| )" + tourName + ".*",false));
			if(tourObjs.length<1){
				throw new ItemNotFoundException("Did not found combo tour object.");
			}
			ticktStatusDivObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Ticket Status.*",false)), tourObjs[tourObjs.length-1]);
		}				
		
		if(ticktStatusDivObjs.length<1){
			throw new ItemNotFoundException("Did not found ticket status DIV object.");
		}
		
		IHtmlObject[] ticketStatusSpanObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), ticktStatusDivObjs[0]);
		if(ticketStatusSpanObjs.length<1){
			throw new ItemNotFoundException("Did not found ticket status SPAN object.");
		}
		
		String status = ticketStatusSpanObjs[1].text();
		Browser.unregister(ticketStatusSpanObjs);
		Browser.unregister(ticktStatusDivObjs);
		Browser.unregister(tourInstanceTRObjs);
		Browser.unregister(tourObjs);
		
		return status;
	}
	
	/**
	 * Get ticket order item status
	 * @param tourName
	 * @return
	 */
	public String getTicketOrderItemStatus(String tourName){
		IHtmlObject[] tourObjs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour Inventory( |)Tour(| )" + tourName + ".*",false));
		if(tourObjs.length<1){
			throw new ItemNotFoundException("Did not found combo tour object.");
		}
		
		IHtmlObject[] ticktStatusDivObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Order Item Status.*",false)), tourObjs[0]);
		if(ticktStatusDivObjs.length<1){
			throw new ItemNotFoundException("Did not found ticket status DIV object.");
		}
		
		IHtmlObject[] ticketStatusSpanObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), ticktStatusDivObjs[0]);
		if(ticketStatusSpanObjs.length<1){
			throw new ItemNotFoundException("Did not found ticket status SPAN object.");
		}
		
		String status = ticketStatusSpanObjs[1].text();
		Browser.unregister(ticketStatusSpanObjs);
		Browser.unregister(ticktStatusDivObjs);
		Browser.unregister(tourObjs);
		
		return status;
	}
	
	/**
	 * Verify order detail info for ticket order
	 * TODO: Verify all ticket types(Now, this method only verify first ticket type&num)
	 * @param ticket
	 */
	public void verifyOrderDetailInfo(TicketInfo ticket){
		boolean flag = true;
		String tour = getTourName();
		String date = getTourDateValue();
		String time = getTourTime();
		String status = getTourStatus();
		String num0 = getTicketNumByType(ticket.types[0]);
		String method = getDeliveryMethod();
		
		if(!tour.equalsIgnoreCase(ticket.tour)){
			flag &= false;
			logger.error("Tour Name display un-correctly on order detail page. Expected value:"+ticket.tour+", Value on page:"+tour);
		}
		
		if(DateFunctions.compareDates(ticket.startDate, date)!=0){
			flag &= false;
			logger.error("Tour Date display un-correctly on order detail page. Expected value:"+ticket.startDate+", Value on page:"+date);
		}
		
		if(!time.equalsIgnoreCase(ticket.timeSlot+" "+ticket.timeAmPm)){
			flag &= false;
			logger.error("Tour Time display un-correctly on order detail page. Expected value:"+ticket.timeSlot+" "+ticket.timeAmPm+", Value on page:"+time);
		}
		
		if(!status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)){
			flag &= false;
			logger.error("Tour Status display un-correctly on order detail page. Expected value:"+OrmsConstants.ACTIVE_STATUS+", Value on page:"+status);
		}
		
		if(!num0.equalsIgnoreCase(ticket.typeNums[0])){
			flag &= false;
			logger.error("Ticket Num display un-correctly on order detail page. Expected value:"+ticket.typeNums[0]+", Value on page:"+num0);
		}
		
		if(!method.equalsIgnoreCase(ticket.deliveryMethod)){
			flag &= false;
			logger.error("Ticket delivery method display un-correctly on order detail page. Expected value:"+ticket.deliveryMethod+", Value on page:"+method);
		}
		
		if(flag){
			logger.info("Tour detail info on order detail page were correct.");
		}else{
			throw new ErrorOnPageException("Tour detail info display un-correct on order detail page. Check log for detail info.");
		}
	}
	
	/**
	 * 
	 * @param expectedMap
	 * @return
	 */
	public boolean verifyTPAInfoOfPerInventory(String tourName, Map<String, String> expectedMap) {
		OrmsTicketOrderDetailsPage orderDetailPage = OrmsTicketOrderDetailsPage.getInstance();
		
		logger.info("Verify the TPA info of 'Per Inventory' for tour - " + tourName + " is correct or not.");
		boolean result = true;
		Map<String, String> mapOnPage = orderDetailPage.getTourParticipantAttributesOfPerInventory(tourName);
		if (mapOnPage.containsKey(OrmsConstants.TPA_LABEL_ARRIVAL)) {
			String[] times = mapOnPage.get(OrmsConstants.TPA_LABEL_ARRIVAL).split(" ");
			mapOnPage.remove(OrmsConstants.TPA_LABEL_ARRIVAL);
			mapOnPage.put(OrmsConstants.TPA_LABEL_ARRIVAL_HH, times[0]);
			mapOnPage.put(OrmsConstants.TPA_LABEL_ARRIVAL_MM, times[1]);
			mapOnPage.put(OrmsConstants.TPA_LABEL_ARRIVAL_AP, times[2]);
		}
		if(expectedMap.size() != mapOnPage.size()) {
			throw new ErrorOnPageException("The Tour Participant Attributes lenth on page doesn't match the expected value.");
		}
		Set<Map.Entry<String, String>> set = expectedMap.entrySet();
		for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			 result &= MiscFunctions.compareResult("Tour Pariticipant Attribute", entry.getValue(), mapOnPage.get(entry.getKey()));
		}
		
		if(!result) {
			throw new ErrorOnPageException("Tour Pariticipant Attribute value(s) is(are) wrong. Please refer log for details.");
		} else logger.info("Tour Participant Attributes are all correct.");
		
		return result;
	}
	
	/**
	 * Verify tour participant attribute info of per ticket
	 * @param tourName
	 * @param tpaOfPerTicket
	 */
	public void verifyTPAInfoOfPerTicket(String tourName, Map<String, List<Map<String, String>>> tpaOfPerTicket){
       logger.info("Verify the TPA info of 'Per Ticket' for tour - " + tourName + " is correct or not.");
       Map<String, List<Map<String, String>>> actualTPAMapOfPerTicket = this.getTourParticipantAttributesOfPerTicket(tourName);
       
       for(Entry<String, List<Map<String, String>>> entry : tpaOfPerTicket.entrySet()){
    	   String type = entry.getKey();
           List<Map<String, String>> actualTPAListForTicket = actualTPAMapOfPerTicket.get(type);
           List<Map<String, String>> expectedTPAListForTicket = entry.getValue();
           
           if(actualTPAListForTicket.size() != expectedTPAListForTicket.size()) {
        	   throw new ErrorOnPageException("The Ticket Type - " + type + " 'Per Ticket' TPA length doesn't match. Expected is: " + expectedTPAListForTicket.size() + ", but actual is: " + actualTPAListForTicket.size());
           }
           
           for (int i = 0; i < expectedTPAListForTicket.size(); i++) { // handle the date format issue, by Lesley.
        	   Map<String, String> tempMap = expectedTPAListForTicket.get(i);
        	   if (tempMap.containsKey("Date of Birth")) {
        		   tempMap.put("Date of Birth", DateFunctions.formatDate(tempMap.get("Date of Birth")));
        	   }
        	   if (tempMap.containsKey("Anniversary")) {
        		   tempMap.put("Anniversary", DateFunctions.formatDate(tempMap.get("Anniversary"), "EEE MMM dd yyyy"));
        	   }
           }
          
//           for(int i = 0; i < expectedTPAListForTicket.size(); i ++) {
//        	   System.out.println("expected TPA_fname: " + expectedTPAListForTicket.get(i).get("First Name"));
//        	   System.out.println("expected TPA_lname: " + expectedTPAListForTicket.get(i).get("Last Name"));
//        	   System.out.println("expected TPA_date of birth: " + expectedTPAListForTicket.get(i).get("Date of Birth"));
//           }
//           for(int j = 0; j < actualTPAListForTicket.size(); j ++) {
//        	   System.out.println("actual TPA_fname: " + actualTPAListForTicket.get(j).get("First Name"));
//        	   System.out.println("actual TPA_lname: " + actualTPAListForTicket.get(j).get("Last Name"));
//        	   System.out.println("actual TPA_date of birth: " + actualTPAListForTicket.get(j).get("Date of Birth"));
//           }
           for(int i = 0; i < expectedTPAListForTicket.size(); i ++) {
	           if(!expectedTPAListForTicket.containsAll(actualTPAListForTicket)){
	              throw new ErrorOnPageException("The Ticket Type - " + type + " 'Per Ticket' TPA values don't all match, please double check.");
	           }
           }
       }
    }
	
	/**
	 * Check Tour participant attribute per ticket info whether existing
	 * @param tourName
	 * @return
	 */
	public boolean checkTPAPerTicketInfoWhetherExisting(String tourName){
		return this.checkTPAInfoWetherExisting(tourName, true);	
	}
	
	/**
	 * Check Tour participant attribute per inventory info whether existing
	 * @param tourName
	 * @return
	 */
	public boolean checkTPAPerInventoryInfoWhetherExisting(String tourName){
		return this.checkTPAInfoWetherExisting(tourName, false);	
	}
	
	/**
	 * Check TPA info whether existing
	 * @param tourName
	 * @param isPerTicket
	 * @return
	 */
	public boolean checkTPAInfoWetherExisting(String tourName, boolean isPerTicket){
		boolean existing;
		IHtmlObject[] tourObjs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Tour( Inventory)?(\\s)?Tour(\\s)?" + tourName + ".*",false));
		
		if(tourObjs.length<1){
			throw new ItemNotFoundException("Did not found tour inventory info. Tour Name = " + tourName);
		}
		
		if(isPerTicket){
			logger.info("Check TPA per ticket info whether existing. Tour Name = " + tourName);
			existing = browser.checkHtmlObjectExists(".id", "attrSectionPerTicket", tourObjs[tourObjs.length-1]);
		}else {
			logger.info("Check TPA per inventory info whether existing. Tour Name = " + tourName);
			existing = browser.checkHtmlObjectExists(".id", "attrSectionPerInventory", tourObjs[tourObjs.length-1]);
		}
		
		return existing;
	}	
	
	/**
	 * Get ticket types sorted.
	 */
	public List<String> getTicketTypes() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SPAN",".className", "label_span_ticket_attribute");
		List<String> types = new ArrayList<String>();
		for(IHtmlObject o: objs)
		{
			types.add(o.text());
		}
		Browser.unregister(objs);
		return types;
		
	}

	/**
	 * Get attribute group name of per Ticket.
	 */
	public List<String> getAttrGroupNameOfPerTicket() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TR",".id", "attrSectionPerTicket");
		IHtmlObject[] tdObjs = null;
		List<String> attrGroups = new ArrayList<String>();
		for(IHtmlObject o: objs)
		{
			tdObjs = browser.getHtmlObject(".class", "Html.TD", o);
			attrGroups.add(tdObjs[0].text());
						
		}
		
		Browser.unregister(tdObjs);
		Browser.unregister(objs);
		return attrGroups;
		
	}

	/**
	 * Get attribute group name of per Inventory.
	 */
	public List<String> getAttrGroupNameOfPerInventory() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TR",".id", "attrSectionPerInventory");
		IHtmlObject[] tdObjs = null;
		List<String> attrGroups = new ArrayList<String>();
		for(IHtmlObject o: objs)
		{
			tdObjs = browser.getHtmlObject(".class", "Html.TD", o);
			attrGroups.add(tdObjs[0].text());
						
		}
		
		Browser.unregister(tdObjs);
		Browser.unregister(objs);
		return attrGroups;
		
	}
	
	/**
	 * Check is this attribute element(html) editable of Per Ticket. 
	 * @paran groupName, group name of attribute for Per Ticket
	 * @paran content, attribute text value
	 */
	public boolean isEditableOfAttrPerTicket(String groupName, String content) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "attrSectionPerTicket", ".text", new RegularExpression("^"+groupName,false));
		IHtmlObject[] attrElements = browser.getHtmlObject(".text", content, objs[0]);
		boolean result = true;
		for(IHtmlObject o: attrElements)
		{
			if(o.tag().equalsIgnoreCase("SPAN"))
			{
				result &= true;
			}else if(o.tag().equalsIgnoreCase("SELECT")){
				if(o.getProperty("disabled").equalsIgnoreCase("disabled"))
				{
					result &= true;
				}else{
					result &=false;
				}
			}
		}
		Browser.unregister(objs);
		Browser.unregister(attrElements);
		return result;
	}
	
	/**
	 * Check is this attribute element(html) editable of Per Inventory. 
	 * @paran groupName, group name of attribute for Per Inventory
	 * @paran content, attribute text value
	 * 	
	 */
	public boolean isEditableOfAttrPerInventory(String groupName, String content) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "attrSectionPerInventory", ".text", new RegularExpression("^"+groupName,false));
		IHtmlObject[] attrElements = browser.getHtmlObject(".text", content, objs[0]);
		boolean result = true;
		for(IHtmlObject o: attrElements)
		{
			if(o.tag().equalsIgnoreCase("SPAN"))
			{
				result &= true;
			}else if((o.tag().equalsIgnoreCase("SELECT"))||(o.tag().equalsIgnoreCase("textarea")))
			{
				if(o.getProperty("disabled").equalsIgnoreCase("true"))
				{
					result &= true;
				}else{
					result &=false;
				}
			}
				
		}
		Browser.unregister(objs);
		Browser.unregister(attrElements);
		return result;
	}
	
	/**
	 * Verify all the TPA fields are NOT editable
	 */
	public void verifyTPAsAreNotEditable() {
		logger.info("Verify if the all TPA fields are NOT editable or not.");
		
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("^AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]", false));
		boolean result = true;
		if(objs.length > 0) {
			for(int i = 0; i < objs.length; i ++) {
				if(!Boolean.valueOf(objs[i].getProperty("isDisabled"))) {
					logger.error(objs[i].getProperty(".id") + " is editable with Inactive ticket order.");
					result &= false;
				}
			}
		}
		
		if(!result) {
			throw new ErrorOnPageException("There are some fields in this ticket order page editable, please refer ERROR log for details info.");
		} 
		
		logger.info("As expected, all TPA fields are not editable.");
		
		Browser.unregister(objs);
	}

	
	/** Click Charge POS */
	public void clickChargePOS() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Charge POS");
	}
	
	/** Click Charges Label */
	public void clickCharges() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Charges");
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Charges");//Quentin[20140328]
	}
	
	/**
	 * Get selected Alert labels
	 * @return
	 * @author Lesley Wang
	 * @date Jan 24, 2013
	 */
	private String getSelectedCheckboxesLabel(String attrID, IHtmlObject topObject) {
		IHtmlObject[] objs = browser.getCheckBox(Property.toPropertyArray(".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[" + attrID + "\\]_\\d+", false)), topObject);
		String label = "";
		if (objs.length > 0) {
			String id = null;
			for (IHtmlObject o : objs) {
				id = o.id();
				if (browser.isCheckBoxSelected(".id",  id)	) {
					label += browser.getObjectText(".class", "Html.LABEL", ".for", id) + ",";
				}
			}
		}
		
		Browser.unregister(objs);
		if(!StringUtil.isEmpty(label) && label.contains(",")){
			label=label.substring(0, label.lastIndexOf(","));
		}
		logger.info("The selected checkboxes lables for the attrID=" + attrID + " are " + label );
		return label;
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
