/*
 * $Id: VnuMgrAddTicketToCartPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Ticket selection page
 * 
 * @author Jdu
 */
public class OrmsTicketSelectionPage extends OrmsPage {

	/**
	 * Script Name : VnuMgrTicketDetailPage Generated : Dec 20, 2006 1:44:20 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2006/12/20
	 */
	private static OrmsTicketSelectionPage _instance = null;

	private OrmsTicketSelectionPage() {
	}

	public static OrmsTicketSelectionPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketSelectionPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
//				new RegularExpression("^(To )?Tour Inventory.*Ticket Selection.*", false));
		return this.checkLastTrailValueIs(".*Ticket Selection");
	}

	/**
	 * For Change/Transfer ticket selction page.
	 * 
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * For create new order ticket selection page
	 * 
	 */
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add To Cart", true);
	}

	/** Click cancel button */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	/**judge if Id pass existing*/
	public boolean isIDPassRequired(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",new RegularExpression("\\s*Add", false));
	}
	
	/**
	 * 
	 * TODO   add ID/Pass for one ticket type
	 * @param ticketType
	 * @param pass
	 * @Return void
	 * @Throws
	 */
	public void setTicketPass(String ticketType,String[] pass){
		String passFeildId = "";
		if (ticketType.equalsIgnoreCase("Adult")) {
			passFeildId = "TicketPass_0_1";
		} else if (ticketType.equalsIgnoreCase("Child")) {
			passFeildId = "TicketPass_0_19";
		} else {
			throw new ItemNotFoundException("Unknown ticket type: "
					+ ticketType);
		}

//		HtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", new RegularExpression("^Ticket Type.*", false));
//
//		if(objs.length<1){
//			Browser.unregister(objs);
//			throw new ItemNotFoundException("The Specify Quantity Table is not found.");
//		}
//		
//		ITable table=(ITable)objs[0];
//		
////		int typeRow=table.findRow(0, ticketType);
//		HtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(("^"+ticketType+".*"), false), table);
//		
//		for (int i = 0; i < pass.length; i++) {
//			browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("\\s*Add", false), true, i, trs[trs.length-1]);
//			browser.setTextField(".id", passFeildId, pass[i], true, i, trs[trs.length-1]);
//		}
//		Browser.unregister(trs);
//		Browser.unregister(objs);
		
		//click Add button to add pass text field
		
		IHtmlObject[] objs = null;
		IHtmlObject[] trs = null;
		for(int i=0; i < pass.length; i++) {
			objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", new RegularExpression("^Ticket Type.*", false));
			trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(("^"+ticketType+".*"), false), objs[0]);
			browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("^Add$", false), true, 0, trs[trs.length-1]);
		}
		
		for(int i = 0; i < pass.length; i++) {
			browser.setTextField(".id", passFeildId, pass[i], i+1);// there is a hidden object
		}
		
		Browser.unregister(trs);
		Browser.unregister(objs);
	}

	/**
	 * 
	 * TODO   add ID/Pass for more Type
	 * @param passes
	 * @Return void
	 * @Throws
	 */
	public void addMorePass(HashMap<String,String[]> passes){
	    for(Map.Entry<String,String[]> pass:passes.entrySet()){
	    	this.setTicketPass(pass.getKey(), pass.getValue());
	    }
	}

	/**
	 * 
	 * TODO click ticket availability link
	 * 
	 * @Return void
	 * @Throws
	 */
	public void clickTicketAvailabilityLink() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Ticket Availability");
	}

	/**
	 * 
	 * TODO click ticket search list link
	 * 
	 * @Return void
	 * @Throws
	 */
	public void clickTicketSearchListLink() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Ticket Search List");
	}

	/**
	 * Set ticket types and corresponding numbers. For create new order ticket
	 * selection page
	 * 
	 * @param types
	 * @param typeNums
	 */
	public void setTicketTypeNumber(String[] types, String[] typeNums) {
		if (types.length != typeNums.length)
			throw new ItemNotFoundException(
					"The size of types and typeNums don't match.");

		String contract=getContract();
		String env=TestProperty.getProperty("target_env");
		String schema=TestProperty.getProperty(env+".db.schema.prefix")+contract;
		
		for (int i = 0; i < types.length; i++) {
			int typeId = DataBaseFunctions.getAdmissionTypeID(schema, types[i]);					
//			browser.setTextField(".id", "TicketTypeAmount_0_" + typeId,	typeNums[i]);
			setTicketNum(typeId, typeNums[i]);
		}
	}
	
	public void setTicketNum(int typeId, String typeNum){
		browser.setTextField(".id", "TicketTypeAmount_0_" + typeId,	typeNum);
//		type = type.replace("(", "\\(").replace(")", "\\)");
//		HtmlObject[] ticketTypeObject = browser.getTableTestObject(".text", new RegularExpression("^Ticket Type.*",false));
//		if(ticketTypeObject.length<1){
//			throw new ItemNotFoundException("Did not found ticket type quantity table object.");
//		}
//		Property[] p = new Property[2];
//		p[0] = new Property(".class", "Html.TR");
//		p[1] = new Property(".text",new RegularExpression("^" + type + ".*",false));
//		HtmlObject[] trObjs = browser.getHtmlObject(p,ticketTypeObject[0]);
//		if(trObjs.length<1){
//			throw new ItemNotFoundException("Did not found ticket type row object. Ticket type = " + type);
//		}
//		
//		browser.setTextField(".id", new RegularExpression("TicketTypeAmount_0_\\d+",false), typeNum, trObjs[0]);
//		Browser.unregister(trObjs);
//		Browser.unregister(ticketTypeObject);
	}

	/** Click Addticket */
	public void clickAddTicketSelection() {
		RegularExpression index = new RegularExpression("[1-9][0-9]*", false);
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add Ticket Selection");
		p[2] = new Property(".classIndex", index);
		browser.clickGuiObject(p);
	}

	/** click remove ticket selection */
	public void clickRemoveTicketSelection() {
		RegularExpression index = new RegularExpression("[1-9][0-9]*", false);
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Remove Ticket Selection");
		p[2] = new Property(".classIndex", index);
		browser.clickGuiObject(p);
	}

	/** Select Transfer Reason */
	public void selectTransferReason(String reason) {
		if (reason == null || reason.length() < 1)
			browser.selectDropdownList(".id", "reason", 0);
		else
			browser.selectDropdownList(".id", "reason", reason);
	}

	/** Get tickte selection table */
	public IHtmlObject[] getTicketSelectionTable() {
		return browser.getHtmlObject(".class", "Html.TABLE", ".id",
//				new RegularExpression("^ticketSelectionTable_[0-9]+_[0-9]+$", false));//QA3
				new RegularExpression("^ticketSelectionTable_[0-9]+(_[0-9]+)?$", false));//both for QA3 and QA4
	}

	/** Get order details table */
	public IHtmlObject[] getOrderDetailsTable() {
		return browser.getHtmlObject(".class", "Html.TABLE", ".id",
				"OrderDetails");
	}

	/**
	 * 
	 * TODO get all type rate from ticket selection page
	 * 
	 * @return
	 * @Return List<String>
	 * @Throws
	 */
	public List<String> getAllTypePrice() {
		List<String> list = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Prices:.*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"The price table is not be found ~!");
		}

		IHtmlTable table = (IHtmlTable) objs[0];

		int groupCol = table.findColumn(0, "Group Rates");
		int regularCol = table.findColumn(0, "Regular Rates");
		int totalRowNum = table.rowCount();

		for (int i = 1; i < totalRowNum; i++) {
			String gropVal = table.getCellValue(i, groupCol + 1);
			String regVal = table.getCellValue(i, regularCol);
			if (gropVal != null && gropVal.trim().length() > 0)
				list.add(gropVal);
			if (regVal != null && regVal.trim().length() > 0)
				list.add(regVal);
		}

		Browser.unregister(objs);
		return list;
	}

	/**
	 * Set ticket infomation
	 * 
	 * @param fromTypes
	 * @param toTypes
	 * @param numbers
	 */
	public void setTicketInfo(String[] fromTypes, String[] toTypes,
			String[] numbers) {
		int size = fromTypes.length;
		if (fromTypes.length != toTypes.length
				&& toTypes.length != numbers.length)
			throw new ItemNotFoundException(
					"The size of fromTypes,toTypes and numbers are not same.");

		IHtmlObject[] objs = this.getTicketSelectionTable();
		System.out.println("table leght: " + objs.length);
		int count = ((IHtmlTable) objs[0]).rowCount() - 2;// HTMLTable.getRowCount(objs[0])
		// - 2;
		Browser.unregister(objs);

		for (int i = size; i > count; i--) {
			this.clickAddTicketSelection();
		}
		for (int i = size; i < count; i++) {
			this.clickRemoveTicketSelection();
		}
		
		//select from ticket types
//		objs = browser.getDropdownList(".id", new RegularExpression("fromTicketType_\\d+_\\d+", false));
		objs = browser.getDropdownList(".id", new RegularExpression("fromTicketType_\\d+", false));//QA4 is changed
		for (int i = 0; i < size; i++) {
			((ISelect) objs[i + 1]).select(fromTypes[i]);
		}
		Browser.unregister(objs);

		//select to ticket types
//		objs = browser.getDropdownList(".id", new RegularExpression("toTicketType_\\d+_\\d+", false));
		objs = browser.getDropdownList(".id", new RegularExpression("toTicketType_\\d+", false));//QA4 is changed
		for (int i = 0; i < size; i++) {
			((ISelect) objs[i + 1]).select(toTypes[i]);
		}
		Browser.unregister(objs);

		//set change quantities
//		objs = browser.getTextField(".id", new RegularExpression("quantity_\\d+_\\d+", false));
		objs = browser.getTextField(".id", new RegularExpression("quantity_\\d+", false));//QA4 is changed
		for (int i = 0; i < size; i++) {
			((IText) objs[i + 1]).setText(numbers[i]);
		}
		Browser.unregister(objs);
	}

	/**
	 * Retrieve ticket information
	 * 
	 * @return
	 */
	public TicketInfo getTicketInfo() {
		TicketInfo ticket = new TicketInfo();
		IHtmlObject[] objs = this.getOrderDetailsTable();
		// String text = HTMLTable.getCellValue(objs[0], 0, 1).toString();
		String text = ((IHtmlTable) objs[0]).getCellValue(0, 1).toString();
		ticket.ordNum = RegularExpression.getMatches(text, "4-[0-9]+")[0];
		ticket.orderStatus = RegularExpression.getMatches(text,
				"Order Status [a-zA-Z]+")[0].substring(13);
		ticket.category = RegularExpression.getMatches(text,
				"Ticket Category [a-zA-Z]+")[0].substring(17);
		ticket.salesChannel = RegularExpression.getMatches(text,
				"Sales Channel [a-zA-Z]+")[0].substring(14);
		String[] prices = RegularExpression.getMatches(text,
				"\\$[0-9]{1,2}\\.[0-9]{2}");
		ticket.price = prices[0];
		ticket.paid = prices[1];
		ticket.unIssuedRefund = prices[2];
		ticket.balance = prices[3];
		ticket.confirmStatus = RegularExpression.getMatches(text,
				"Confirmation Status [a-zA-Z]+")[0].substring(21);

		// text = HTMLTable.getCellValue(objs[0], 1, 1).toString();
		text = ((IHtmlTable) objs[0]).getCellValue(0, 1).toString();
		ticket.park = text.substring(10);

		Browser.unregister(objs);
		return ticket;
	}

	/**
	 * Retrieve total qty
	 * 
	 * @return
	 */
	public int getTotalQty() {
		String qty = browser.getTextFieldValue(".id", "total0");
		return Integer.parseInt(qty);
	}

	/**
	 * retrieven held qty
	 * 
	 * @return
	 */
	public int getHeldQty() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Total Qty : .*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String qty = RegularExpression.getMatches(text, "[0-9]+")[0];
		Browser.unregister(objs);
		return Integer.parseInt(qty);

	}

	public String getDateTime_TimeZone_Quantity() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression(".*# Tickets.*", false));
		if (objs.length < 1) {
			Browser.unregister(objs);
			return null;
		}
		String temp = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return temp;

	}

	/**
	 * set ticket quantity
	 * @param ticketQuantity
	 */
	public void setTicketQuantity(String ticketQuantity) {
		browser.setTextField(".id", "quantity_0", ticketQuantity, 1);
	}
	
	/**
	 * Select 'To Ticket Type' 
	 * @param ticketTypes
	 */
	public void selectToTicketType(String[] ticketTypes){
		if(null != ticketTypes && ticketTypes.length>0) {
			for(int i=0; i<ticketTypes.length; i++){
				browser.selectDropdownList(".id", "toTicketType_0", ticketTypes[i], i+1);
			}
		}
	}
	
    /**
     * Set ticket 'Quantity'
     * @param ticketQuantitys
     */
	public void setTicketQuantity(String[] ticketTypeNums) {
		if(null !=ticketTypeNums && ticketTypeNums.length>0) {
			for(int i=0; i<ticketTypeNums.length; i++){
				browser.setTextField(".id", "quantity_0", ticketTypeNums[i], i+1);
			}
		}
	}
	

	/**
	 * get the error messages from ticket selection page
	 */
	public List<String> getErrorMessages() {
		List<String> list = null;
		RegularExpression regx=new RegularExpression("(.*message.error.*)|(V-\\d+)",false);
		IHtmlObject[] objs = browser.getHtmlObject(".id",regx);
		if (objs.length > 0) {
			list = new ArrayList<String>();
			for (IHtmlObject o : objs) {
				list.add(o.getProperty(".text"));
			}
		}
		Browser.unregister(objs);
		return list;
	}

	/**
	 * 
	 * TODO get the title of Alert
	 * 
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String getAlertTitle() {
		RegularExpression regx=new RegularExpression("vm\\.purchase\\.((tour)|(order))\\.alerts",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id",
				regx);
		String title = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return title;
	}

	/**
	 * 
	 * @Desc click the tour name url and go to the tour details page.
	 * @param @param tourname
	 * @Return void
	 * @Throws
	 */
	public void clickTourNameUrl(String tourname) {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^" + tourname + ".*", false));
	}

	/**
	 * 
	 * TODO if quantity text input feild is existend,return true,or return false
	 * 
	 * @return
	 * @Return boolean
	 * @Throws
	 */
	public boolean isQuantityExistent() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^TicketTypeAmount.*", false));
	}

	/**
	 * 
	 * TODO if all quantity text input are editable,return true,or return false
	 * 
	 * @return
	 * @Return boolean
	 * @Throws
	 */
	public boolean isAllQuantityEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^TicketTypeAmount.*", false));
		try {
			if (objs.length < 1) {
				throw new ItemNotFoundException("Object is not found ~!");
			} else {
				for (IHtmlObject o : objs) {
					boolean temp = !Boolean.parseBoolean(o
							.getProperty(".disabled"));
					if (!temp) {
						return false;
					}
				}
				return true;
			}
		} catch (ItemNotFoundException e) {
			throw e;
		} finally {
			Browser.unregister(objs);
		}
	}

	public boolean isHeldTextEditable(String num) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", num);
		if (objs.length < 1) {
			throw new ItemNotFoundException("There is no Object found~!");
		}
		boolean editable = Boolean.parseBoolean(objs[0]
				.getProperty(".disabled"));
		Browser.unregister(objs);
		return editable;
	}

	public boolean isAvailableTextEditable(String num) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", num);
		boolean editable = false;
		if (objs.length < 1) {
			throw new ItemNotFoundException("There is no Object found~!");
		}
		editable = Boolean.parseBoolean(objs[0]
				.getProperty(".disabled"));
		Browser.unregister(objs);
		return editable;
	}
	
	public String getTourInventoryInfo(boolean isComboTour, String comboTourName, String tourName){
		RegularExpression reg;
		String inventoryInfo = "";
		if(isComboTour){
			reg = new RegularExpression("^" + comboTourName + ".*", false);
		}else {
			reg = new RegularExpression("^" + tourName + ".*", false);
		}
		
		IHtmlObject[] objs = browser.getTableTestObject(".text",reg);
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found tour info object. tour name = " + tourName);
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		
		if(isComboTour){
			for(int i=0; i<table.rowCount(); i++){
				if(table.getRowValues(i).get(0).contains(tourName)){
					inventoryInfo = table.getRowValues(i).get(0).replace(tourName + ":", "").trim();
					break;
				}
			}
		}else {
			inventoryInfo = table.getRowValues(1).get(0);
		}
		
		
		System.out.println(inventoryInfo);
		return inventoryInfo;
	}

	/**
	 * 
	 * TODO if Alert text area is editable,return true
	 * 
	 * @return
	 * @Return boolean
	 * @Throws
	 */
	public boolean isAlertTextAreaEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TEXTAREA",
				".text", new RegularExpression(
						"^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}.*", false));
		boolean editable = false;
		if(objs.length > 0) {
			if((objs[0].getProperty(".readOnly"))!= null)
			{
				editable = false;
			}else{
				editable = true;	
			}
		} else {
			objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "alertsContent");
			
			editable = !(objs.length > 0);
		}
		
		Browser.unregister(objs);
		return editable;
	}

	/**
	 * 
	 * TODO if Note Alert button is enable,return true,or return false
	 * 
	 * @return
	 * @Return boolean
	 * @Throws
	 */
	public boolean isAddNoteAlertButtonEnable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text",
				"Add Note/Alert");
		boolean enable = !Boolean.parseBoolean(objs[0]
				.getProperty(".disabled"));
		Browser.unregister(objs);
		return enable;
	}
	
	/* if only one quantity text feild is found and editable, return true */
	public boolean isOnlyOneQuantityFeild() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^TicketTypeAmount.*", false));
		if(objs.length!=1){
			Browser.unregister(objs);
			return false;
		}
		boolean editable=this.isAllQuantityEditable();
		return editable;
	}

	public static void main(String[] args) {
		OrmsTicketSelectionPage page = OrmsTicketSelectionPage.getInstance();
		// page.clickTourNameUrl("TicketSelection");
		System.out.println(page.getAllTypePrice());
	}

	/**
	 * Select change reason
	 * 
	 * @param reason
	 */
	public void selectChangeReason(String reason) {
		browser.selectDropdownList(".id", "reason", reason);
	}
	
	/**
	 * Check whether the 'Delivery Method' section exists or not
	 * @return
	 */
	public boolean checkDeliveryMethodSectionExists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.radio", ".id", new RegularExpression("DeliveryMethod_\\d+", false));
	}
	
	/**
	 * Select delivery method radio button
	 * @param method
	 */
	public void selectDeliveryMethod(String method) {
		IHtmlObject labels[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", method);
		if(labels.length < 1) {
			throw new ItemNotFoundException("Can't find Delivery Method like " + method + ", please check it.");
		}
		
		int forValue = Integer.parseInt(labels[0].getProperty("for"));
		Browser.unregister(labels);
		browser.selectRadioButton(".value",forValue+"",".id", new RegularExpression("DeliveryMethod_\\d+", false));
		
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
	public List<List<String>> getTicketSelectionRoeInfo(){
		List<List<String>> selectionArray = new ArrayList<List<String>>();
		selectionArray.clear();
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".id", new RegularExpression("^ticketSelectionTable_0_\\d+",false));
		if(objs.length<1){
			new ErrorOnDataException("Table can't exist");
		}
		for(int i = 0;i<objs.length;i++){
			IHtmlTable table = (IHtmlTable)objs[i];
			selectionArray.add(table.getRowValues(2));
		}
		return selectionArray;
	}
	
	public void verifyNoFeeScheAlertMsg(String expectedMsg) {
		logger.info("Verify No Fee schedule alert message on page.");
		String msgUI=getAlertTitle();
		if(!msgUI.equalsIgnoreCase(expectedMsg))
			throw new ErrorOnPageException("Failed to verify alert message.", expectedMsg, msgUI);
		logger.info("Verify No Fee schedule alert message on page successfully.");
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
