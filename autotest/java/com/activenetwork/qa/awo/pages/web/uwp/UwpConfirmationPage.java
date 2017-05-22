
package com.activenetwork.qa.awo.pages.web.uwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.common.UwpConfirmationCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpConfirmationPage extends UwpConfirmationCommonPage {
	private static UwpConfirmationPage _instance = null;

	public static UwpConfirmationPage getInstance() {
		if (null == _instance)
			_instance = new UwpConfirmationPage();

		return _instance;
	}

	public UwpConfirmationPage() {
	  	timeout = Browser.VERY_LONG_SLEEP;
	}
	
	protected Property[] shoppingitemsTable(){
		return Property.concatPropertyArray(table(), ".id", "shoppingitems1");
	}
	/**
	 * local data collection class
	 */
	static class ConfirmationListGui {
		String resvNo;
		ILink resvLink;
	}

	/**
	 * Verify whether the order has been processed completed
	 */
	public void verifySuccess() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shophdr");
		String returnStr = ((String) objs[0].getProperty(".text"));
		Browser.unregister(objs);

		if (!returnStr.matches(UwpSchConstants.PASS))
			throw new ItemNotFoundException(
					"The order processing may not be complete.");
	}
	
	/**
	 * Go to reservation details page by reservation id
	 * @param str - reservation ID
	 */
	public void gotoResDetailByResNo(String str) {
		IHtmlObject[] objs = getAllReservationLinks();

		List<ConfirmationListGui> allConfirmation = new ArrayList<ConfirmationListGui>();
		for (int i = 0; i < objs.length; i++) {
			ConfirmationListGui confirm = new ConfirmationListGui();
			confirm.resvLink = (ILink) objs[i];
			confirm.resvNo = (String) objs[i].getProperty(".text");

			allConfirmation.add(confirm);
		}
		for (int i = 0; i < allConfirmation.size(); i++) {
			ConfirmationListGui gui = (ConfirmationListGui) allConfirmation.get(i);
			if (gui.resvNo.matches(".*" + str + ".*")) {
				gui.resvLink.click();
			}
		}

		Browser.unregister(objs);
	}
	
	public IHtmlObject[] getAllReservationLinks() {
		RegularExpression reg = new RegularExpression(UwpSchConstants.RESVNO,	false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text",reg);
		
		return objs;
	}
	
	public void clickReservationLink(String resNum){
		browser.clickGuiObject(Property.concatPropertyArray(a(), ".text", resNum));
	}
	
	
	/**
	 * Go to specified reservation's details page by index
	 * @param index - index of reservation items
	 */
	public void gotoResDetail(int index) {
		IHtmlObject[] objs = getAllReservationLinks();
		if (index > objs.length + 1)
			index = objs.length + 1;
		
		objs[index - 1].click();

		Browser.unregister(objs);
	}
	/**
	 * Retrieve the reservation ID by index
	 * @param index - index of reservation items, start from 0
	 * @return -  reservation ID
	 */
	public String getResNo(int index) {
		String resNumbers=getAllResNo();
		String[] tokens=resNumbers.split(" ");
		
		return tokens[index];
	}
	/**
	 * Retrieve all reservation IDs
	 * @return
	 */
	public String getAllResNo() {
		String allResNo = ""; 
		StringBuffer returnStrBuf = new StringBuffer();
		
		IHtmlObject[] objs = getAllReservationLinks();
		if(objs!=null && objs.length>0){
			for (int i = 0; i < objs.length; i++) {
				returnStrBuf = returnStrBuf.append(objs[i].getProperty(".text") + " ");
			}
		}else{
			String[] nums = this.getAllReservationNums();
			for(int i=0; i<nums.length; i++){
				returnStrBuf = returnStrBuf.append(nums[i] + " ");
			}
		}
		
		Browser.unregister(objs);
		allResNo = returnStrBuf.toString().trim();
		
		logger.info("ResNums:"+allResNo);
		return allResNo;
	}
	/**
	 * Go to reservation details page by ID
	 * @param resID - reservation ID
	 */
	public void gotoResDetailByResID(String resID) {
		browser.clickGuiObject(".class", "Html.A", ".text", resID);
	}
	/**
	 * Go to first reservation details page
	 */
	public void gotoResDetailFirst() {
		gotoResDetail(1);
	}
	/**
	 * Go to last reservation details page
	 */
	public void gotoResDetailLast() {
		gotoResDetail(999);
	}
	
	/**
	 * Click 'Continue to RA Home' link
	 */
	public void clickContinueToRAHome() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "backhome");
	}
	
	/**
	 * Click 'Continue to Recreation.gov home' link
	 */
	public void clickContinueRecGovHomeLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Continue to Recreation.gov home");
	}
	
	/**
	 * Click 'Continue to Current reservation' link
	 */
	public void clickContinueToCurrentRes() {
		browser.clickGuiObject(".id", "reservationLink");
	}
	
	/**
	 * Click 'Back to shopping cart' link when payment is declined.
	 */
	public void clickBackToShoppingCart(){
//	  	browser.clickGuiObject(".id", "continueshop");
		//because selenium will click on the center of the whole link which is just blank, so update to click on the img object
//		browser.clickGuiObject(".class", "Html.IMG", ".title", "Back To Shopping Cart", true);
		
		//Quentin[20130905] Back To Shopping Cart link has been changed in RA.com
		Property oldProperty[] = Property.toPropertyArray(".class", "Html.IMG", ".title", "Back To Shopping Cart");
		Property newProperty[] = Property.toPropertyArray(".class", "Html.A", ".id", "continueshop");
		if(browser.checkHtmlObjectExists(oldProperty)) {
			browser.clickGuiObject(oldProperty, true);
		} else if(browser.checkHtmlObjectExists(newProperty)) {
			browser.clickGuiObject(newProperty, true);
		}else throw new ObjectNotFoundException("Can't find Back to Shopping Cart button.");
	}

	/**
	 * Verify the balance in confirm page is equal as given value.
	 * @param balance - balance
	 */
	public void verifyBalance(String balance) {
		String context = getShoppingItemTableText();

		if(context.indexOf("Balance: $" + balance)==-1&&context.indexOf("Balance:$" + balance)==-1)
		  	throw new ItemNotFoundException("The balanceis not correct.");
		else
		  	logger.info("The balance is correct.");
	}
	
	/**
	 * Retrieve the shoping items table's text
	 * @return
	 */
	public String getShoppingItemTableText(){
	  	IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems1");
		String returnStr = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return returnStr;
	}
	
	/**
	 * Return the error message if the purchases is not completed because of some reasons
	 * @return
	 */
	public String getErrorMsg(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "shoppingitems1");
		String msg = "";
		if(objs.length > 0){
			msg = objs[0].getProperty(".text").toString();
		} else {
			throw new ItemNotFoundException("There is no error message displays.");
		}
		Browser.unregister(objs);
		return msg;
	}
	
	/**
	 * Get POS name row
	 * @param posName
	 * @return
	 */
	public int getPosRowNum(String posName){
		IHtmlObject[] obj = browser.getTableTestObject(".id", "shoppingitems1");
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(posName+".*")) {
				posRowNum = i;
				break;
			}
		}
		
		return posRowNum;
	}
	
	/**
	 * Retrieve the special POS amount by given POS name.
	 * @param name - POS name
	 * @return POS amount
	 */
	public String getPOSAmountByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(".id", "shoppingitems1");
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in confirmation page!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in confirmation page!");
		}
		
		String amount=table.getCellValue(posRowNum, 3).split(":")[1].trim();
		amount = amount.replaceAll("[a-zA-Z]", "").trim();
		Browser.unregister(obj);
		
		return amount;
	}
	
	public String getPOSQuantityByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(".id", "shoppingitems1");
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in confirmation page!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in confirmation page!");
		}
		
		String amount=table.getCellValue(posRowNum, 2).split(":")[1].trim();
		Browser.unregister(obj);
		
		return amount;
	}
	
	public String getPOSReservationFeeByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(".id", "shoppingitems1");
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in cart!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in cart!");
		}
		
		String str=table.getCellValue(posRowNum, 3).trim();
		String fee = "";
		if (str.contains("Reservation Fee")) {
			fee = str.substring(str.indexOf("Reservation Fee"));
		} else if (str.contains("Mailing Fee")) {
			fee = str.substring(str.indexOf("Mailing Fee"));
		}
//		String fee = str.substring(str.indexOf("Reservation Fee"));
		fee = fee.split(":")[1].trim();
		fee = fee.replaceAll("[a-zA-Z]", "").trim();
		Browser.unregister(obj);
		
		return fee;
	}	
	
	public String getPOSOrderNumByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(".id", "shoppingitems1");
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in confirmation page!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in confirmation page!");
		}
		
		String posOrderNum = RegularExpression.getMatches(table.getCellValue(posRowNum, 0), "3-\\d+")[0].trim();
		Browser.unregister(obj);
		
		return posOrderNum;
	}
	
	public String[] getPOSOrderNums() {
		return RegularExpression.getMatches(getShoppingItemTableText(), "3-\\d+");
	}
	
	public String getPOSOrderNum() {
		return getPOSOrderNums()[0];
	}
	
    /**
     * judge if Button "Print Tickets & Permits" exists
     * @return
     */
	public boolean isPrintTicketsPermitsButtonExist() {
	     return browser.checkHtmlObjectExists(Property.toPropertyArray(".id", "backhome", ".text", "Print Tickets & Permits",".class","Html.BUTTON"));
	}
	
	public void clickPrintTicketsPermitsButton(){
		browser.clickGuiObject(Property.toPropertyArray(".id", "backhome", ".text", "Print Tickets & Permits",".class","Html.BUTTON"));
	}

	/**
	 * @return
	 */
	public boolean isContinueToRecHomeLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Continue to Recreation.gov home");
	}
	
	public void clickContinueToHomeBtn(){
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Continue to home");
	}
	
	public void verifyTotalAmount(BigDecimal totalAmount){
		BigDecimal actualTotalAmount = this.getTotalAmount();
		if(actualTotalAmount.compareTo(totalAmount)==0){
			logger.info("Successfully verify total amount:"+totalAmount);
		}else{
			throw new ErrorOnDataException("Failed to verify total amount.", totalAmount, actualTotalAmount);
		}
		
	}
}
