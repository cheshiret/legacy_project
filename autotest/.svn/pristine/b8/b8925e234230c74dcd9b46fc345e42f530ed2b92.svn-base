/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderSummaryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReceiptInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;


/**
 * @author raonqa
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsOrderSummaryPage extends OrmsPage {

	/**
	 * Script Name : <b>FldMgrOrderSumPage</b> Generated : <b>Sep 24, 2004
	 * 3:00:07 PM</b> Description : XDE Tester Script Original Host : WinNT
	 * Version 5.1 Build 2600 (Service Pack 2)
	 * 
	 * @since 2004/09/24
	 */
	// private static String ORD_NUM_REGEX = "\\d-\\d+";

	private final static String PREV_SITE_NAME_REGEX = "CHANGED FROM Site: [0-9]+\\-[A-z]+\\_[A-z]+\\_[0-9]+";

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsOrderSummaryPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsOrderSummaryPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsOrderSummaryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsOrderSummaryPage();
		}

		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				"Order Summary");
	}

	/** Click on the "Finish Order" link-button */
	public void clickFinishOrder() {
		RegularExpression pattern=new RegularExpression("Finish (Order|Call)",false);
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", pattern);
		browser.clickGuiObject(p);
	}

	/** Determine whether "Print Permit" button exists */
	public boolean printPermitExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Print Permit");
	}

	/** Click on the "Print Ticket" button */
	public void clickPrintTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Tickets");
	}
	
	/** Click on the "Print Documents" button */
	public void clickPrintDocuments(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Documents");
	}

	public void clickFinishButton() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^Finish (Call|Order)", false));
	}

	/** Click on the "Print Permit" button */
	public void clickPrintPermit() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Permit");
	}

	/** Click the button to finish the call for Call Manager */
	public void clickFinishCall() {
		RegularExpression pattern = new RegularExpression(
				"^Finish (Call|Order)", false);
		browser.clickGuiObject(".class", "Html.A", ".text", pattern, true);
	}

	public String getTransacions(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(".*Transaction.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find TR which starts with 'Transaction(s)'!!");
		}
		String test = objs[0].getProperty(".text").replaceAll("Transaction\\(s\\)", "").trim();
		Browser.unregister(objs);
		return test;
	}
	
	/** Determine the transaction type, as displayed on the page */
	public String getTransType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".classindex", "5");
		String toReturn = ((IHtmlTable) objs[0]).getCellValue(0, 1).toString();
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getTransactionsInfo(int index){
		IHtmlObject objs[]=browser.getHtmlObject(".class","Html.SPAN",".className","orderItemDetail");
		if(objs.length<1){
			throw new ItemNotFoundException("The Transactions info Object did not found.");
		}
		
		String tranInfo = objs[index].text();
		Browser.unregister(objs);
		return tranInfo;
	}
	
	/**
	 * Default get the first order's transaction info
	 * @return
	 */
	public String getTransactionsInfo(){
		return getTransactionsInfo(0);
	}
	
	public boolean checkCustInfoWhetherExistingAtReceiptItemPart(String info){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.TD",".text",new RegularExpression(info,false)));
	}
	
	public boolean verifyTransactionInfo(String info, int index){
		String actValue = this.getTransactionsInfo(index);	
		if(!actValue.equals(info)){
			logger.error("Transaction name should be " + info 
					+ ", but actually is " + actValue);
			return false;
		}else {
			logger.info("Transaction name is correct.");
			return true;
		}
	}
	
	/**
	 * Default verify the first order's transaction info 
	 * @param info
	 * @return
	 */
	public boolean verifyTransactionInfo(String info){
		return verifyTransactionInfo(info, 0);
	}
	
	public boolean verifyProductItemInfo(String info){
		List<String> itemDetails = this.getOrderItemDetails();
		if(!itemDetails.get(0).replaceAll("Remove", "").trim().equals(info)){
			logger.error("Vehicle item info should be " + info 
					+ ", but actually is " + itemDetails.get(0));
			return false;
		}else {
			logger.info("Vehicle item info is correct.");
			return true;
		}		
	}
	
	public boolean verifyShippingAddressInfo(Address expAddress){
		boolean result = true;
		String value = this.getRelatedInfoByGiveName("Address");
		if(!value.equals(expAddress.address)){
			result &= false;
			logger.error("Expect Shipping Address should be: " + expAddress.address 
					+ ", but actually is " + value);
		}
		
		value = this.getRelatedInfoByGiveName("Supplemental Address");
		if(!value.equals(expAddress.supplementalAddr)){
			result &= false;
			logger.error("Expect Shipping supplemental Address should be: " + expAddress.supplementalAddr 
					+ ", but actually is " + value);
		}
		
		value = this.getRelatedInfoByGiveName("City/Town");
		if(!value.equals(expAddress.city)){
			result &= false;
			logger.error("Expect Shipping city should be: " + expAddress.city 
					+ ", but actually is " + value);
		}
		
		value = this.getRelatedInfoByGiveName("State");
		if(!value.equals(expAddress.state)){
			result &= false;
			logger.error("Expect Shipping state should be: " + expAddress.state 
					+ ", but actually is " + value);
		}
		
		value = this.getRelatedInfoByGiveName("County");
		if(!value.equals(expAddress.county)){
			result &= false;
			logger.error("Expect Shipping county should be: " + expAddress.county 
					+ ", but actually is " + value);
		}
		
		value = this.getRelatedInfoByGiveName("ZIP/Postal");
		if(!value.equals(expAddress.zip)){
			result &= false;
			logger.error("Expect Shipping zip should be: " + expAddress.zip 
					+ ", but actually is " + value);
		}
		
		value = this.getRelatedInfoByGiveName("Country");
		if(!value.equals(expAddress.country)){
			result &= false;
			logger.error("Expect Shipping country should be: " + expAddress.country 
					+ ", but actually is " + value);
		}
		
		return result;
	}
	
	public boolean verifyCustomerInfo(Customer cust, String fromOrTo) {
		String expValue;
		boolean result = true;

		if (null != fromOrTo && fromOrTo.length() > 0) {
			expValue = fromOrTo;
		} else {
			expValue = "";
		}

		if(!StringUtil.isEmpty(cust.businessName)) {
			expValue = cust.businessName + "(\\s)?\\(" + cust.custNum + "\\)";
		} else {
			if(StringUtil.isEmpty(cust.custNum) && !StringUtil.isEmpty(cust.licenseNum)) {
				cust.custNum = cust.licenseNum;
			}
			expValue = expValue + cust.lName + "," + cust.fName;
			if(!StringUtil.isEmpty(cust.custNum)) {
				expValue += "(\\s)?\\(" + cust.custNum + "\\)";
			}
		}
		result &= this.checkCustInfoOrVehiclInfo(expValue);
		
		
		if(!StringUtil.isEmpty(cust.residencyStatus)) {
			result &= this.checkCustInfoOrVehiclInfo(cust.residencyStatus);
		}
		
		// customer address should default to Mailing Address.
		// If the mailing address doesn't exist, use customer's physical address
		if(!StringUtil.isEmpty(cust.mailingAddr.address)) {
			expValue = cust.mailingAddr.address;
			if (null != cust.mailingAddr.supplementalAddr
					&& cust.mailingAddr.supplementalAddr.length() > 0) {
				expValue = expValue + ", " + cust.mailingAddr.supplementalAddr;
			}
	
			result &= this.checkCustInfoOrVehiclInfo(expValue);
		}
		
		if(!StringUtil.isEmpty(cust.mailingAddr.city)) {
			String shortName = this.convertStateName(cust.mailingAddr.state);
			expValue = cust.mailingAddr.city + ", " + shortName + ", "
					+ cust.mailingAddr.zip;
			result &= this.checkCustInfoOrVehiclInfo(expValue);
		}
		
		return result;
	}
	
	private String convertStateName(String from) {
		String to = "";
		String stateLongName[] = new String[] {"Alabama", "New York", "Mississippi", "Saskatchewan"};
		String stateShortName[] = new String[] {"AL", "NY", "MS", "SK"};
		
		if(from.length() ==2) {
			for(int i = 0; i < stateShortName.length; i ++) {
				if(from.equals(stateShortName[i])) {
					to = stateLongName[i];
					break;
				}
			}
		} else {
			for(int i = 0; i < stateLongName.length; i ++) {
				if(from.equals(stateLongName[i])) {
					to = stateShortName[i];
					break;
				}
			}
		}
		if(to.length() < 1) {
			throw new ItemNotFoundException("State Name " + from);
		}
		
		return to;
	}
	
	public boolean checkCustInfoOrVehiclInfo(String info){
		boolean existing = this.checkCustInfoWhetherExistingAtReceiptItemPart(info);
		
		if(!existing){
			logger.error("Expect '" + info + "' info should existing." );
		}else {
			logger.info("'"  + info + "' existing");
		}
		
		return existing;
	}
	
	public String getVehicleMINum(){
		IHtmlObject objs[]=browser.getHtmlObject(".class","Html.TD",".text",new RegularExpression("^(MI #)|(Reg\\. Number):MI.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("The MI vehicle certification number object did not found.");
		}
		
		String number = objs[objs.length-1].text().split(":")[1].trim();
		Browser.unregister(objs);
		return number;
	}
	
	public String getInspectionID(){
		IHtmlObject objs[]=browser.getHtmlObject(".class","Html.TD",".text",new RegularExpression("^Inspection ID:.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("The Inspection ID object did not found.");
		}
		
		String number = objs[0].text().split(":")[1].trim();
		Browser.unregister(objs);
		return number;
	}
	
	public String getTitleNum(){
		IHtmlObject objs[]=browser.getHtmlObject(".class","Html.TD",".text",new RegularExpression("^Title #:.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("The MI vehicle certification number object did not found.");
		}
		
		String number = objs[0].text().split(":")[1].trim();
		Browser.unregister(objs);
		return number;
	}
	
	public List<String> getOrderItemDetails() {
		List<String> details=new ArrayList<String>();
//		IHtmlObject objs[]=browser.getHtmlObject(".class","Html.TD",".className","orderItemDetail");
//		for(IHtmlObject o: objs) {
//			if(null != o.text() && o.text().length()>0){
//				details.add(o.text());
//			}
//		} product UI changed,className is null
		
		IHtmlObject objs[]=browser.getTableTestObject(".text",new RegularExpression("^Order #:.*",false));
		IHtmlTable table=(IHtmlTable) objs[0];
		details.add(table.getCellValue(0, 1));
		
		Browser.unregister(objs);
		return details;
	}

	/** Get the reservation number on the summary page */
	public String getResNum() {
		return getResNum(false);
	}

	/** This method only return the first order's order number */
	public String getResNum(boolean midStayIn) {
		int curser = 1; // take the 1st resNum by default
		if (midStayIn) // take the 2nd resNum if it is midStay in
			curser = 2;
		String[] numbers = getOrdNumsArray(false);

		return numbers[curser];
	}

	public String[] getOrdNumsArray(boolean isHnF) {
		RegularExpression reg = new RegularExpression("^Receipt Details.*",
				false);
//        HtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".className", "orderItemDetail");
		int count=0;
		boolean found=false;
		String[] numbers=null;
		while (!found && count<30) {
			String text = browser.getObjectText(".class", "Html.DIV", ".text", reg);
			
//		    String orderItemDetails;
//		    if(objs!=null)
//		    for(int i=0;i<objs.length;i++){	
//		    	orderItemDetails=objs[i].text();
//		    	if(orderItemDetails!=null){
//					text.replace(orderItemDetails, " ");
//				}
//		    }
			
			if(text!=null && text.length()>0) {
				String rex = "";
				//the order number maybe 1 or 2 digits for LM,but for other sale channel may retrieve site id with 2 digits 
				//In Call Manager, H&F Product, the order num are 2 digits;
				String managerName = this.getManagerName();
				if(managerName.equalsIgnoreCase("License Manager") || isHnF){
					rex = "(?<=\\D)[1-9](\\d?)-\\d{1,}"; // update by lesley, for supply order number
				} 
//				else if(managerName.equalsIgnoreCase("Marina Manager")) {
//					rex = "(?<=\\D)M[1-9]-\\d{1,}";
//				}
				else{
					rex = "(?<=\\D)[1-9]-\\d{1,}|(?<=\\D)M[1-9]-\\d{1,}";
				}
				numbers = RegularExpression.getMatches(text, rex);
			}
			
			found= numbers!=null && numbers.length>0;
			count++;
			if(!found && count<30) {
				Timer.sleep(1000);
			}
		}
		if(found) {
			//Quentin[20140709] remove some loop numbers like 1-30. -- Start
			List<String> tempList = new ArrayList<String>();
			for(String n : numbers) {
				if(!n.startsWith("1-")) {
					tempList.add(n);
				}
			}
			numbers = tempList.toArray(new String[0]);
			//-- End
			return StringUtil.distinctFilter(numbers);
		} else {
			return new String[0];
		}
	}
	
	public String getAllOrdNums(){
		return getAllOrdNums(false);
	}
	
	/**
	 * Get the order numbers of all orders shown
	 * In Call Manager, H&F Product, the order num are 2 digits;
	 * To avoid confusion with site order num
	 * @param isHF
	 * @return order numbers delimited by a space " "
	 */
	public String getAllOrdNums(boolean isHF) {
		String result = "";
		StringBuffer resultBuffer = new StringBuffer();

		String[] numbers = null;
		
		numbers = getOrdNumsArray(isHF);
		
		for (int i = 0; i < numbers.length; i++) {
			resultBuffer.append(" " + numbers[i].trim());
		}

		result = resultBuffer.toString().trim();

		if (result.length() < 1)
			throw new ItemNotFoundException("Failed to get order numbers.");

		return result;
	}

	/**
	 * Get the transaction information, such as "Mid Stay Transfer Out"
	 * 
	 * @return transaction information
	 */
	public String getAllTransactionInfo() {
		String result = "";
		StringBuffer resultBuffer = new StringBuffer();
		
		IHtmlObject[] tables = browser.getTableTestObject(".text", new RegularExpression("Transaction\\(s\\)", false));
		IHtmlTable table = null;
		if(tables.length < 1) {
			throw new ErrorOnPageException("Can't find any Table object.");
		}

		for(int i = 0 ; i < tables.length; i ++) {
			table = (IHtmlTable)tables[i];
			for(int j = 0; j < table.rowCount(); j ++) {
				if(table.getCellValue(j, 0).trim().equalsIgnoreCase("Transaction(s)")) {
					resultBuffer.append(table.getCellValue(j, 1) + ",");
				}
			}
		}
		result = resultBuffer.toString();
		
		if (result.length() < 1) {
			throw new ItemNotFoundException("Failed to get all transaction(s) info.");
		}
		
		Browser.unregister(tables);
		return result.trim();
	}

	/**
	 *Get the reseravtion information about the reserve number
	 * 
	 * @param resNum
	 *            resvation number
	 * @return reservation information
	 */
	public String getReservationInfo(String resNum) {
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Reservation #: " + resNum,
						false));
		String resInfo = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return resInfo;
	}

	/**
	 * Get the reservation information if the only one reserve number
	 * 
	 * @return reservation number
	 */
	public String getReservationInfo() {
		return this.getReservationInfo("");
	}

	public String getPOSID() {
		String numbers = this.getAllOrdNums();
		String[] posIDs = RegularExpression.getMatches(numbers, "3-\\d+");
		return posIDs[0];
	}

	public String getPOSOrderInfo(String ordNum) {
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",
				".text", new RegularExpression("^"+ordNum+".*", false));
		String resInfo = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return resInfo;
	}
	
	/**
	 * Check whether PDF exist
	 * 
	 * @return
	 * @deprecated
	 */
	public boolean checkPDFExists() {
		String url = browser.url();
		if (url != null && url.indexOf(".pdf") != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Close the PDF
	 * 
	 * @deprecated
	 * */
	public void closePDF() {
		// browser.getHtmlBrowser().close();
	}

//	/**
//	 * Get the first reserve num
//	 * 
//	 * @param td
//	 */
//	public void getReservNum(TestData td) {
//		td.reservNum = getResNum(false);
//	}

	/**
	 * Retrieve event ID
	 * 
	 * @return
	 */
	public String getEventID() {
		String result = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Event ID\\s*\\d+", false));

		if (objs.length > 0) {
			String text = objs[0].text();
			result = RegularExpression.getMatches(text, "\\d+")[0];
		}

		Browser.unregister(objs);
		return result;
	}

	/**
	 * Extract the previous site name from the Order Cart table (i.e. for
	 * reservation transfers)
	 * 
	 * @return the previous site name
	 */
	public String getPrevSiteName() {

		String theString = "";
		IHtmlObject[] tables = browser.getHtmlObject(".class", "Html.TABLE");
		for (int i = 0; i < tables.length; i++) {
			String text = tables[i].getProperty(".text").toString();
			if (text.startsWith("Receipt Item")) {
				theString = text;
				break;
			}
		}
		Browser.unregister(tables);

		Pattern p = Pattern.compile(PREV_SITE_NAME_REGEX);
		Matcher m = p.matcher(theString);

		/**
		 * Loop through matches and split on ":" and then "-" to get rid of
		 * label text prefixing site number-name, then split on "-" to separate
		 * site # from the site name; return only the site name
		 */
		while (m.find()) {
			String[] components = m.group(0).split(":");
			if ((components != null) && (components.length > 1)) {
				String siteNumAndName = components[1];
				components = siteNumAndName.split("-");
				if ((components != null) && (components.length > 1)) {
					String prevSite = components[1];
					return prevSite;
				}
			}
		}
		throw new RuntimeException("failed to get previous site name");
	}

	/**
	 * This method is used to get all refund details Info
	 * 
	 * @return List<String> contain many refund info
	 */
	public List<RefundInfo> getRefunds() {
		RegularExpression rex = new RegularExpression("Refund Details.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		List<RefundInfo> refunds = new ArrayList<RefundInfo>();
		boolean finish = false;
		while (!finish) {
			RefundInfo refund = new RefundInfo();
			refund.amount = text.substring(
					text.indexOf("Amount") + "Amount".length(),
					text.indexOf("Currency")).trim();
			text = text.substring(text.indexOf("Currency")
					+ "Currency".length());

			refund.fromPaymentId = text.substring(
					text.indexOf("Payment ID") + "Payment ID".length(),
					text.indexOf("Payment Group")).trim();
			text = text.substring(text.indexOf("Payment Group"));

			if (text.indexOf("Amount") == -1) {
				refund.paymentMethod = text.substring(
						text.indexOf("Payment Type")
								+ "Payment Type".length()).trim();
				finish = true;
			} else {
				refund.paymentMethod = text.substring(
						text.indexOf("Payment Type")
								+ "Payment Type".length(),
						text.indexOf("Amount")).trim();
				text = text.substring(text.indexOf("Payment Type")
						+ "Payment Type".length());
			}
			refunds.add(refund);
		}
		Browser.unregister(objs);
		return refunds;
	}

	/**
	 * This method is used to get all voucher details information
	 * 
	 * @return Voucher data collection
	 */
	public Voucher getVoucherInfo() {
		RegularExpression rex = new RegularExpression(
				"Vouchers Earned Details.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		Voucher voucher = new Voucher();
		voucher.voucherId = text.substring(
				text.indexOf("VoucherID") + "VoucherID".length(),
				text.indexOf("Amount")).trim();
		voucher.amount = text.substring(
				text.indexOf("Amount") + "Amount".length(),
				text.indexOf("Currency")).trim();
		voucher.customer = text.substring(
				text.indexOf("Customer") + "Customer".length(),
				text.indexOf("Voucher Program")).trim();
		voucher.voucherProgrameName = text.substring(
				text.indexOf("Voucher Program") + "Voucher Program".length(),
				text.indexOf("From:")).trim();
		voucher.fromOrderNum = text.substring(
				text.indexOf("Order") + "Order".length(),
				text.indexOf("Payment")).trim();
		voucher.fromPaymentId = text.substring(
				text.indexOf("Payment") + "Payment".length()).trim();

		return voucher;
	}
	
	/**
	 * This method is used to get all voucher details information
	 * 
	 * @return Voucher data collection
	 */
	public Voucher getVoucherRedeemedInfo() {
		RegularExpression rex = new RegularExpression(
				"Vouchers Redeemed Details.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		Voucher voucher = new Voucher();
		voucher.voucherId = text.substring(
				text.indexOf("VoucherID") + "VoucherID".length(),
				text.indexOf("Available Amount")).trim();
		voucher.amount = text.substring(
				text.indexOf("Available Amount") + "Available Amount".length(),
				text.indexOf("Currency")).trim();
		voucher.customer = text.substring(
				text.indexOf("Customer") + "Customer".length(),
				text.indexOf("Voucher Program")).trim();
		voucher.voucherProgrameName = text.substring(
				text.indexOf("Voucher Program") + "Voucher Program".length(),
				text.indexOf("Applied to:")).trim();
		voucher.fromOrderNum = text.substring(
				text.indexOf("Order") + "Order".length(),
				text.indexOf("Payment")).trim();
		voucher.paymentAmount = text.substring(
				text.indexOf("Payment Amount") + "Payment Amount".length(),
				text.indexOf("Balance")).trim();

		return voucher;
	}
	
	private IHtmlObject[] getVoucherInfoTableObject() {
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text",
				//				new RegularExpression("^Total Vouchers Earned Refund to existing Voucher Refund to New Voucher Voucher Due", false));
				new RegularExpression("^((\\W)*)?Total Vouchers Earned.*Refund to existing Voucher.*Refund to New Voucher.*Voucher Due", false));
		
		return objs;
	}
	
	/**
	 * Get the vouchers item from summary page.Eg:(Total Vouchers Earned,Refund
	 * to New Voucher...)
	 * 
	 * @return vouchers information
	 */
	public String getVouchersItem(int i) {
		IHtmlObject objs[] = getVoucherInfoTableObject();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Voucher detail info object.");
		}
		IHtmlTable resTable = (IHtmlTable) objs[0];
		String vouchersItem = resTable.getCellValue(i, 0);
		Browser.unregister(objs);
		return vouchersItem;
	}

	/**
	 * get the specific value of the vouchers
	 * 
	 * @return
	 */
	public String getVouchersDiffAmount() {
		IHtmlObject objs[] = getVoucherInfoTableObject();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Voucher detail info object.");
		}
		IHtmlTable resTable = (IHtmlTable) objs[0];
		String vouchersDiffAmount =  resTable.getCellValue(0, 4);
		Browser.unregister(objs);
		return vouchersDiffAmount;
	}

	public String getRefundAmount() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Refund Details.*", false));
		String temp = objs[0].getProperty(".text").toString();
		String refundAmount = temp.substring(temp.indexOf("Amount")
//				+ "Amount".length() + 1, temp.indexOf("Currency"));
		+ "Amount".length(), temp.indexOf("Currency"));
		Browser.unregister(objs);
		return refundAmount;
	}

	/**
	 * This method used to retrive customer info
	 * 
	 * @return-Customer
	 */
	public Customer getCustomerInfo() {
		Customer cust = new Customer();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Customer Name.*|^Name.*", false)); //The second text for QA4
		String text = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		cust.mName = text.substring(text.indexOf("Name") + "Name".length(),
				text.indexOf("Phone")).trim();
		cust.hPhone = text.substring(text.indexOf("Phone") + "Phone".length(),
				text.indexOf("Email")).trim();
		cust.email = text.substring(text.indexOf("Email") + "Email".length(),
				text.indexOf("Organization Name")).trim();
		//"Organization Name" is null if there is no Organization Name information
		if(text.contains("Organization Name ")){
			cust.organization = text.substring(
			text.indexOf("Organization Name")
					+ "Organization Name ".length(),
			text.indexOf("Print Receipt")).trim();
		}else{
			cust.organization = text.substring(text.indexOf("Organization Name")
					+ "Organization Name".length());
		}

		return cust;
	}
	
	public String getRelatedInfoByGiveName(String name){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray
				(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name + ".*",false)));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found '" + name + "' object.");
		}
		
		IHtmlObject[] childObjs = objs[0].getChildren();
		String info = childObjs[1].text();
		
		Browser.unregister(childObjs);
		Browser.unregister(objs);
		return info;
	}

	/**
	 * Retrieve the order summary data form order summary page.
	 * @return
	 */
	public OrderSummaryInfo getOrderSummaryData(boolean isSite, boolean isPermit, boolean isTicket) {
		OrderSummaryInfo os = new OrderSummaryInfo();
		
		os.orderNums=this.getOrdNumsArray(false);
		
		//retrieve the data for Receipt Details section
		IHtmlObject[] receiptObjs = this.getSubSectionTables("^Receipt #.*");
		IHtmlTable receiptTable = (IHtmlTable) receiptObjs[0];
		
		for(int i=0; i<receiptTable.columnCount(); i++) {
			String receiptText = receiptTable.getCellValue(0, i);
			if(null != receiptText && receiptText.matches("^Receipt #.*")) {
				os.receiptNum = receiptText.split("#")[1].trim();//get Receipt #
				logger.info("The receipt Number is:" + os.receiptNum);
			} else if(null != receiptText && receiptText.matches("^Receipt Date & Time.*")) {
				os.receiptDateTime = receiptText.split("Time")[1].trim();//get Receipt Date & Time
				logger.info("The receipt date and time is:" + os.receiptDateTime);
			} else if(null != receiptText && receiptText.matches("^Sales Location.*")) {
				os.salesLocation = receiptText.split("Sales Location")[1].trim();// get Sales Location
				logger.info("The receipt sales location is:" + os.salesLocation);
			} else if(null != receiptText && receiptText.matches("^Created By.*")) {
				os.createdBy = receiptText.split("Created By")[1].trim();// get Created By
				logger.info("The order was created by:" + os.createdBy);
			}
		}
		Browser.unregister(receiptObjs);
		
		//retrieve the data for Customer section
//		HtmlObject[] custObjs = this.getSubSectionTables("^Customer.*Organization.*");
		IHtmlObject[] custObjs = this.getSubSectionTables("^Name.*Organization.*");
		String custText = custObjs[0].text().trim();
		if(custText.contains("Phone")){
			os.custName = custText.split("Name")[1].split("Phone")[0].trim();// get Customer Name
			os.custPhone = custText.split("Phone")[1].split("Email")[0].trim();// get Customer Phone
		}else{
			os.custName = custText.split("Name")[1].split("Email")[0].trim();// get Customer Name
		}
	
		os.custEmail = custText.split("Email")[1].split("Organization Name")[0].trim();// get Customer Email
		os.custOrgName = custText.substring(custText.indexOf("Organization Name")).trim().substring(17);// get Customer Organization Name
		Browser.unregister(custObjs);
		
		//retrieve the data for Transaction(s) section
		IHtmlObject[] transObj = this.getSubSectionTables("^Transaction\\(s\\).*");
		IHtmlTable transTable = (IHtmlTable) transObj[0];
		String transactions = transTable.getCellValue(0, 1);
		String[] trans = transactions.split(",");
		for(int i=0; i<trans.length; i++) {
			os.transactions.add(trans[i].trim());
		}
		Browser.unregister(transObj);
		
		//retrieve the data for Receipt Item section
		IHtmlObject[] itemObj = this.getSubSectionTables("^Order #.*");
		IHtmlTable itemTable = (IHtmlTable) itemObj[0];

		
		os.arrivalDates = RegularExpression.getMatches(itemObj[0].text(),"[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}");
		
		for(int i=0; i<itemTable.rowCount(); i++){
			String orderText = itemTable.getCellValue(i, 0);
			if(null != orderText && orderText.matches("^Facility: .*")) {
				os.facilityName = orderText.split(":")[1].trim();
			} else if(null != orderText && orderText.matches(".*Category:.*")) {
				os.prdCategory = orderText.split(":")[1].trim();
			}
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.SPAN");
		p[1] = new Property(".className", "orderItemDetail");
		IHtmlObject[] tdObjs = browser.getHtmlObject(p, itemObj[0]);
		
		os.tourNames.add(tdObjs[0].text());//the first SPAN is tour name in 306 build[Shane 20140219]
		for(int i=1; i<tdObjs.length-1; i++){
			if(tdObjs[i+1].text().matches("[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}.*")){
				if(tdObjs[i].text().matches("^Facility:.*")) {
					continue;//skip the first tour name here,as has been added
				} else {
					os.tourNames.add(tdObjs[i].text());//the second tour name after changed from
				}
			}
		}
		
		Browser.unregister(tdObjs);

		if(os.arrivalDates.length>0) {
			os.tourName = os.tourNames.get(0);
			os.arrivalDate = os.arrivalDates[0];
		}
		
		if(isTicket && itemTable.text().matches(".*@.*")){
			String value = itemTable.text().split("@")[0];
			int length = value.split(" ").length;
			os.numOfTickets = value.split(" ")[length-1];
		}
		
		Browser.unregister(itemObj);		
		return os;
	}

	public OrderSummaryInfo getSiteOrderSummaryData() {
		return getOrderSummaryData(true, false, false);
	}
	
	public OrderSummaryInfo getPermitOrderSummaryData() {
		return getOrderSummaryData(false, true, false);
	}
	
	public OrderSummaryInfo getTicketOrderSummaryData() {
		return getOrderSummaryData(false, false, true);
	}
	
	/**
	 * Retrieve the table objects for each section in order summary page by given expression.
	 * @param regStr
	 * @return
	 */
	public IHtmlObject[] getSubSectionTables(String regStr) {
		RegularExpression reg = new RegularExpression(regStr, false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		if(objs.length==0) {
			throw new ItemNotFoundException("No table match the given expression.");
		}
		return objs;
	}
	
	/**
	 * get the new amount own from the order summary page.
	 * @return
	 */
	public String getNewAmountOwning(){
		String newAmountOwn = "";
		
		IHtmlObject[] objs = getSubSectionTables("New Amount Owing.*");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		newAmountOwn = table.getCellValue(0, 1).split("Owing")[1].replace("$", "").trim();
		return newAmountOwn;
	}
	
	/**
	 * get the span text value.
	 * @return- the attribute text value.
	 */
	private String getAttributeValueByName(String attributeName) {
		IHtmlObject objs[] = null;
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		
		if(null != attributeName && attributeName.length() > 0){
			objs = browser.getHtmlObject(property);
		} else {
			throw new ErrorOnDataException("Unknown attribute name.");
		}
		
		String attributeValue = "";
		System.out.println(objs.length);
		if(objs.length == 1) {
			attributeValue = objs[0].getProperty(".text").split(attributeName)[1].trim();
		} else {
			throw new ActionFailedException("Find 0 or multiple DIV object named - " + attributeName + ".");
		}
		
		Browser.unregister(objs);
		return attributeValue;
	}
	/**
	 * get the receipt number.
	 * @return- the receipt number.
	 */
	public String getReceiptNum(){
		return this.getAttributeValueByName("Receipt #");
	}

	/**
	 * Get MI Num 
	 * @return
	 */
	public String getMINum(){
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".text", new RegularExpression("^Transaction\\(s\\).*", false));
		
		IHtmlObject objs[] = browser.getHtmlObject(property);
		
		String attributeValue = "";
		if(objs.length > 0) {
			String tmp = objs[0].getProperty(".text");
			attributeValue = tmp.split("Reg. Number:")[1].split(" ")[0].trim();
		} else {
			throw new ActionFailedException("Failed to find DIV object text start Transaction(s).");
		}
		
		Browser.unregister(objs);
		return attributeValue;
	}
	
	/**
	 * Get MI Num 
	 * @return
	 */
	public String getVehicleID(){
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".text", new RegularExpression("^Transaction\\(s\\).*", false));
		
		IHtmlObject objs[] = browser.getHtmlObject(property);
		
		String attributeValue = "";
		if(objs.length > 0) {
			attributeValue = objs[0].getProperty(".text").split("ID:")[1].split(" ")[0].trim();
		} else {
			throw new ActionFailedException("Failed to find DIV object text start Transaction(s).");
		}
		
		Browser.unregister(objs);
		return attributeValue;
	}
	
	/**
	 * Get Valid property String for Vehicle Product Order
	 * @return
	 */
	public String[] getValidString(){
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".text", new RegularExpression("^Transaction\\(s\\).*", false));
		RegularExpression regex = new RegularExpression("Valid\\: [a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4} to [a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}", false);
		
		IHtmlObject objs[] = browser.getHtmlObject(property);
		String[] attr;
		String s = "";
		if(objs.length > 0) {
			s = objs[0].getProperty(".text");
			attr = regex.getMatches(s);
			
		} else {
			throw new ActionFailedException("Failed to find DIV object text start Transaction(s).");
		}
		
		Browser.unregister(objs);
		
		Browser.unregister(objs);
		
		return attr;
	}
	
	/**
	 * Get TAN Code for H&F Product
	 * @return
	 */
	public String getTANCode(){
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".text", new RegularExpression("^Transaction\\(s\\).*", false));
		RegularExpression regex = new RegularExpression("TAN\\:[a-zA-Z0-9]{11,12}[0-9]{3}", false);
		
		IHtmlObject objs[] = browser.getHtmlObject(property);
		String[] attr = null;
		String tan = "";
		if(objs.length > 0) {
			String s = objs[0].getProperty(".text");
			attr = regex.getMatches(s);		
		} else {
			throw new ActionFailedException("Failed to find DIV object text start Transaction(s).");
		}
		
		if(null!=attr && attr.length > 0){
			tan = attr[0].split("\\:")[1];
		}
		
		Browser.unregister(objs);
		
		return tan;
	}
	
	public void verifyTANCode(String TANCode) {
		String TANFromUI = getTANCode();
		if (!TANFromUI.equals(TANCode)) {
			throw new ErrorOnPageException("TAN Code display un-correctly on order summary page",TANCode,TANFromUI);
		}
		logger.info("Verify TAN Code successfully on Order summary page");
	}
	
	public boolean isPrintDocumentEnabled() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text","Print Documents");
		boolean enabled=objs.length >0 && objs[0].isEnabled();
		Browser.unregister(objs);
		
		return enabled;
	}
	
	public String getTotalPrice(boolean isHnF){
		String price = "";
		if(isHnF)
		{
			IHtmlObject[] tables = browser.getTableTestObject(".text", new RegularExpression("^Totals.*", false));
			String tableText = tables[0].text();
			
			price = (tableText.substring(tableText.indexOf("Total Price")+"Total Price".length(), tableText.indexOf("Total Past Paid"))).trim();
			
			Browser.unregister(tables);
			
		}
		return price;
		
	}
	
	/**
	 * Get the amounts of Total Refund, Issued Refund, Refund Due on order summary page
	 * @return
	 * @author Lesley Wang
	 * @date Jun 5, 2012
	 */
	public List<String> getRefundsAmount() {
		List<String> refundAmounts = new ArrayList<String> ();
		IHtmlObject[] tables = browser.getTableTestObject(".text", new RegularExpression("^Refunds.*", false));
		String tableText = tables[0].text();
		String reg = "\\d+\\.\\d\\d";
		String[] amounts = RegularExpression.getMatches(tableText, reg);
		for (String s : amounts) {
			refundAmounts.add(s);
		}
		Browser.unregister(tables);
		return refundAmounts;
	}
	
	/**
	 * Get the amount in 'Payment Details'
	 * @return
	 */
	public String getAmountOfPaymentDetail(){
		IHtmlObject[] div = browser.getHtmlObject(".text", new RegularExpression("^Amount.*", false));
		String text = div[0].text();
		String reg = "\\d+\\.\\d\\d";
		String[] amounts = RegularExpression.getMatches(text, reg);
		return amounts[0];
	}
	
	public String getTotalPastPaid(){
		String price = "";
		IHtmlObject[] tables = browser.getTableTestObject(".text", new RegularExpression("^Totals.*", false));
		String tableText = tables[0].text();
			
		price = (tableText.substring(tableText.indexOf("Total Past Paid")+"Total Past Paid".length(), tableText.length())).trim();
			
		Browser.unregister(tables);
	
		return price;
		
	}
	
	public String getOrderNumOfProduct(String prdName)
	{
		IHtmlObject[] tds= browser.getHtmlObject(".class", "Html.TD", ".text", new RegularExpression(prdName,false));
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.TD");
		p[1] = new Property(".vAlign", "top");
		p[2] = new Property(".text", new RegularExpression("Order #",false));
 				
		IHtmlObject[] orderTd = browser.getHtmlObject( p, tds[0]);
		String ordNum = orderTd[0].text();
		ordNum = ordNum.substring("Order #:".length());
		Browser.unregister(tds);
		
		
		return ordNum;
		
	}
	public Map<String, String> getPayInfo(boolean refund, boolean issued){
		Map<String, String> payInfo = new HashMap<String, String>();
		payInfo.put("Price", this.getTotalPrice(true));
		if(!refund){
			payInfo.put("Paid", "$" + this.getAmountOfPaymentDetail());
			payInfo.put("Balance", "$" + this.getNewAmountOwning());
		}else if(!issued){
			payInfo.put("Paid", this.getTotalPastPaid());
			payInfo.put("Balance", "($" + this.getRefundsAmount().get(0)+")");
		}else{
			payInfo.put("Paid", this.getTotalPrice(true));
			payInfo.put("Balance", "$" + this.getNewAmountOwning());
		}
		
	    return payInfo;
	}

	public boolean checkObjectExist(Property[] p, boolean isExist){
		return (isExist==browser.checkHtmlObjectExists(p));
		
	}
	public String getState(String parkName){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", new RegularExpression("^Park: "+parkName, false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't facility name and state.");
		}
		return objs[0].getProperty(".text").replaceAll("Park: ", StringUtil.EMPTY);
	}
	
	public List<String[]> getCustPass(){
		IHtmlObject[] itemObj = this.getSubSectionTables("^\\d-\\d{1,}.*");
		IHtmlTable itemTable = (IHtmlTable) itemObj[0];
		List<String[]> custInfo=new ArrayList<String[]>();
		
		for(int i=0;i<itemTable.rowCount()-1;i+=2){
			String passname=itemTable.getCellValue(i, 1);
			String passnum="";
			String passNumLabel="Pass No.";
			/**
			 * Sara[11/27/2013]
			if(i!=0){//this is work around for UI
				passnum=itemTable.getCellValue(i+1,0);
			}else{
				passnum=itemTable.getCellValue(i+1,1);
			}
			 */
			passnum=itemTable.getCellValue(i+1,1);
			if(passnum.contains(passNumLabel)){
				passnum=passnum.split(passNumLabel.replace(".", "\\."))[1].trim();
			}
			String[] cust=new String[]{passname,passnum};
			custInfo.add(cust);
		}
		
		Browser.unregister(itemObj);
		return custInfo;
	}
	
	public ReceiptInfo getRecipientInfo(){
		//retrieve the data for Receipt Details section
		ReceiptInfo receipt = new ReceiptInfo();
		IHtmlObject[] receiptObjs = this.getSubSectionTables("^Receipt #.*");
		String receiptText = receiptObjs[0].getProperty(".text").toString();
		receipt.id = receiptText.split("Receipt #")[1].split("Receipt Date & Time")[0].trim();//get Receipt number
		receipt.receiptDateAndTime = receiptText.split("Receipt Date & Time")[1].split("Sales Location")[0].trim();//get Receipt Date & Time
		receipt.salesLocation = receiptText.split("Sales Location")[1].split("Created By")[0].trim();//get Receipt Sales Location
		receipt.createdBy = receiptText.split("Created By")[1].split("Print Vehicle Tag")[0].trim();//get Receipt Sales Location
	
		Browser.unregister(receiptObjs);
		return receipt;
	}

	public void clickPrintReceipt() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Receipt");
	}
	
	private List<String> getOrderItemInfoByName(String name){
		List<String> infos=new ArrayList<String>();
		
		IHtmlObject objs[]=browser.getHtmlObject(Property.toPropertyArray(".class","Html.TD",".text", new RegularExpression("^"+name + ":.*",false)));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get " + name + " order item info.");
		}
		
		for(IHtmlObject o: objs) {
			infos.add(o.text().replaceAll(name + ":", "").trim());
		}
		
		Browser.unregister(objs);
		return infos;
	}
	
	/**
	 * This method get the date info of tour, eg"Mon Nov 18 2013, 8:00 PM CST: 1 Adult, 2 Child (3 - 16)"
	 * @param tourName
	 * @return
	 */
	public String getTourDetailItemInfo(String tourName){
		 String value="";
		 IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".className", "orderItemDetail");
		 for(int i=0;i<objs.length;i++){
			 if(objs[i].text().equals(tourName)){
				 for(int j=i;j<objs.length;j++){
					 if(objs[j].text().contains("CST")||objs[j].text().contains("CDT")){
						 value=objs[j].text();
						 break;
					 }
					
				 } 
			 }
			 if(!"".equals(value)){
				 break;
			 }
		 }
		Browser.unregister(objs);
		return value;
	}
	
	public List<String> getDockAreaInfo(){
		return this.getOrderItemInfoByName("Dock/Area");
	}
	
	public List<String> getSlipInfo(){
		return this.getOrderItemInfoByName("Slip");
	}
	
	public List<String> getArriveDateInfo(){
		return this.getOrderItemInfoByName("Arrive");
	}
	
	public List<String> getDepartureDateInfo(){
		return this.getOrderItemInfoByName("Depart");
	}
	
	public List<String> getNightsInfo(){
		return this.getOrderItemInfoByName("Nights");
	}
	
	public List<String> getMonthsInfo(){
		return this.getOrderItemInfoByName("Months");
	}
	
	public List<String> getBoat(){
		return this.getOrderItemInfoByName("Boat");
	}
	
	public List<String> getBoatCategory(){
		return this.getOrderItemInfoByName("Boat Category");
	}
	
	public List<String> getRegistration(){
		return this.getOrderItemInfoByName("Registration #");
	}
	
	public List<String> getBoatLengthWidthAndDepth(){
		return this.getOrderItemInfoByName("Length / Width / Depth");
	}
	
	public List<String> getSeason(){
		return this.getOrderItemInfoByName("Season");
	}
	
	public List<String> getList(){
		return this.getOrderItemInfoByName("List");
	}
	
	public List<String> getPreferredSlipType(){
		return this.getOrderItemInfoByName("Preferred Slip Type");
	}
	
	public List<String> getPreferredDock(){
		return this.getOrderItemInfoByName("Preferred Dock");
	}
	
	public List<String> getPreferredSlip(){
		return this.getOrderItemInfoByName("Preferred Slip");
	}
	
	public List<String> getListEntryType(){
		return this.getOrderItemInfoByName("List Entry Type");
	}
	
	public boolean checkChangeFromIsExisting(){
		return browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".class","Html.TD",".text", "CHANGED FROM"));
	}
	
	private int getTransactionsNum(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", "Transaction(s)");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	private String getTDAttrValueByName(String name, int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", new RegularExpression(/*"^" +*/ name + ".*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get TD object, by name = " +  name);
		}
//		String text = objs[objs.length-1].text().replaceAll(name + ":", "").trim();
		String text = objs[index + 1*(index +1)].text().replaceAll(name + ":", "").trim();
		Browser.unregister(objs);
		return text;
	}
	
	/**
	 * Default the first one
	 * @param name
	 * @return
	 */
	private String getTDAttrValueByName(String name){
		return getTDAttrValueByName(name, 0);
	}
	
	public String getMarina(int index){
		return this.getTDAttrValueByName("Marina", index);
	}
	public String getMarina(){
		return this.getMarina(0);
	}
	public String getSlipReservationNum(int index){
		return this.getTDAttrValueByName("Slip Reservation #", index);
	}
	
	public String getSlipReservationNum(){
		return this.getSlipReservationNum(0);
	}
	
	public String getCustName(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^Name.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find customer name TR object...");
		}
		IHtmlObject[] objs1=browser.getHtmlObject(".class", "Html.SPAN",".text",new RegularExpression("^Name.*",false),objs[0]);
		if(objs1.length<1){
			throw new ErrorOnPageException("Can't find customer name object...");
		}
		
		String name=objs1[0].getProperty(".text").split("Name")[1].trim();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return name;
	}
	
	public String getCustMail(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^Name.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find customer mail TR object...");
		}
		IHtmlObject[] objs1=browser.getHtmlObject(".class", "Html.DIV",".text",new RegularExpression("Email.*",false),objs[0]);
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find customer mail object...");
		}
		
		String mail=objs1[0].getProperty(".text").split("Email")[1].trim();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return mail;
	}
	
	/**
	 * get an individual receipt item qty in order summary page
	 * @param index
	 * @return
	 */
	public int getReceiptItemQtyInSummary(int index) {
		RegularExpression pattern=new RegularExpression("^\\d+$",false);
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class","Html.TD",".text",pattern));
		if(objs.length<index+1) {
			throw new ItemNotFoundException("Failed to find receipt item qty");
		}
		String qty=objs[index].text();
		
		
		Browser.unregister(objs);
		
		return Integer.parseInt(qty);
	}
	
	public List<String> getFeeTypes(){
		List<String> feeTypeList = new ArrayList<String>();
		IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("^Slip Reservation",false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		int endRow = grid.findRow(3,"Subtotal") - 1;
		for(int i=0;i<endRow;i++){
			String tmp = grid.getCellValue(i, 3);
			if(StringUtil.notEmpty(tmp)){
				feeTypeList.add(tmp);
			}
		}
		return feeTypeList;
	}
	
	public boolean isSecurityAlertExist(){
		return browser.checkHtmlObjectDisplayed(".id", "security_alert");
	}
	
	public String getSecurityAlertInfo(){
		return browser.getTextAreaValue(".id", "security_alert");
	}
	
	public boolean compareSecurityAlertInfo(InventoryInfo info) {
		String value = getSecurityAlertInfo();
		String values[] = value.split("\\|");
		String dates[] = values[0].split("-");
		boolean result = true;
		result &= MiscFunctions.compareResult(info.alertType + " Start Date", info.alertStartDate, dates[0].trim());
		result &= MiscFunctions.compareResult(info.alertType + " End Date", info.alertEndDate, dates[1].trim());
		result &= MiscFunctions.compareResult(info.alertType + " Description", info.description, values[1].trim());
		
		return result;
	}
	
	private Property[] feeAmountSpan() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", new RegularExpression("cell_currency_right(Positive)?", false));
	}
	
	private Property[] totalsSectionTable() {
		return Property.toPropertyArray(".text", new RegularExpression("^Totals", false));
	}
	
	private Property[] refundsSectionTable() {
		return Property.toPropertyArray(".text", new RegularExpression("^Refunds", false));
	}
	
	private Property[] paymentsChangeSectionTable() {
		return Property.toPropertyArray(".text", new RegularExpression("^Payments \\& Change", false));
	}
	
	private Property[] vouchersEarnedSectionTable() {
		return Property.toPropertyArray(".text", new RegularExpression("^Vouchers Earned", false));
	}
	private Property[] vouchersRedeemedSectionTable() {
		return Property.toPropertyArray(".text", new RegularExpression("^Vouchers Redeemed", false));
	}
	
	private Property[] outstandingSectionTable() {
		return Property.toPropertyArray(".text", new RegularExpression("^Outstanding", false));
	}
	
	public HashMap<String, Double> getOrderAmountsInTotalsSection() {
		IHtmlObject objs[] = browser.getTableTestObject(totalsSectionTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Totals' section table object.");
		
		//get all amounts span object in Totals section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) throw new ItemNotFoundException("Cannot find any fee amount span object from 'Totals' section.");
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		Browser.unregister(spanObjs);
		
		//get all order amounts
		IHtmlObject subTableObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(subTableObjs.length < 1) throw new ItemNotFoundException("Cannot find sub table in 'Totals' section.");
		IHtmlTable table = (IHtmlTable)subTableObjs[1];
		int rowCount = table.rowCount();
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		
		for(int i = 0; i < amounts.size(); i ++) {
			
			found = false;
			
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = table.findColumns(0, j, amounts.get(i));
				
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = table.getCellValue(j, colIndexes[k] - 1);
						
						if(!StringUtil.isEmpty(feeType) && !typeAmounts.containsKey(feeType)) {
							if(feeType.equalsIgnoreCase("Total Past Paid")) {
								feeType = "Past Paid Amount";
							}
							
							typeAmounts.put(feeType, Double.parseDouble(amounts.get(i).substring(1)));
							found = true;
						}
					}
				}
				
				if(found) {
					break;
				}
			}
		}
		Browser.unregister(objs);
		
		return typeAmounts;
	}
	
	public boolean isVoucherEarnedSectionExist(){
		IHtmlObject objs[] = browser.getTableTestObject(vouchersEarnedSectionTable());
		if(objs.length > 0){
			Browser.unregister(objs);
			return true;
		}else{
			Browser.unregister(objs);
			return false;
		}
	}
	public boolean isVoucherRedeemedSectionExist(){
		IHtmlObject objs[] = browser.getTableTestObject(vouchersRedeemedSectionTable());
		if(objs.length > 0){
			Browser.unregister(objs);
			return true;
		}else{
			Browser.unregister(objs);
			return false;
		}
	}
	
	public boolean isPaymentsChangeSectionExist(){
		IHtmlObject objs[] = browser.getTableTestObject(paymentsChangeSectionTable());
		if(objs.length > 0){
			Browser.unregister(objs);
			return true;
		}else{
			Browser.unregister(objs);
			return false;
		}
	}
	
	public HashMap<String, Double> getOrderAmountsInPaymentsChangeOrVoucherEarnedSection() {
		IHtmlObject objs[] = browser.getTableTestObject(paymentsChangeSectionTable());
		if(objs.length < 1) objs = browser.getTableTestObject(vouchersEarnedSectionTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Payments & Change' or 'Vouchers Earned' section table object.");
		
		//get all amounts span object in Payments & Change/Vouchers Earned section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) throw new ItemNotFoundException("Cannot find any fee amount span object from 'Payments & Change' or 'Vouchers Earned' section.");
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		System.out.println(amounts.size());
		Browser.unregister(spanObjs);
		
		//get all order amounts
		IHtmlObject subTableObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(subTableObjs.length < 1) throw new ItemNotFoundException("Cannot find sub table in 'Payments & Change' or 'Vouchers Earned' section.");
		IHtmlTable table = (IHtmlTable)subTableObjs[1];
		int rowCount = table.rowCount();
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		
		for(int i = 0; i < amounts.size(); i ++) {
			
			found = false;
			
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = table.findColumns(0, j, amounts.get(i));
				
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = table.getCellValue(j, colIndexes[k] - 1);
						
						if(!StringUtil.isEmpty(feeType) && !typeAmounts.containsKey(feeType)) {
							if(feeType.equalsIgnoreCase("Change Tendered")) {
								feeType = "Change Due";
							}
							
							double amt = 0;
							if(amounts.get(i).contains("(")) {
								amt = -Double.parseDouble(amounts.get(i).replaceAll("\\(", StringUtil.EMPTY).replaceAll("\\)", StringUtil.EMPTY).trim().substring(1));
							} else {
								amt = Double.parseDouble(amounts.get(i).substring(1));
							}
							
							typeAmounts.put(feeType, amt);
							found = true;
						}
					}
				}
				
				if(found) {
					break;
				}
			}
		}
		Browser.unregister(objs);
		
		return typeAmounts;
	}
	
	
	public HashMap<String, Double> getOrderAmountsInVoucherRedeemedSection() {
		IHtmlObject objs[] = browser.getTableTestObject(vouchersRedeemedSectionTable());
		if(objs.length < 1){ 
			throw new ItemNotFoundException("Cannot find 'Vouchers Redeemed' section table object.");
		}		
		//get all amounts span object in Vouchers Redeemed section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) {
			throw new ItemNotFoundException("Cannot find any fee amount span object from 'Vouchers Redeemed' section.");
		}
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		System.out.println(amounts.size());
		Browser.unregister(spanObjs);
		
		//get all order amounts
		IHtmlObject subTableObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(subTableObjs.length < 1) {
			throw new ItemNotFoundException("Cannot find sub table in 'Payments & Change' or 'Vouchers Earned' section.");
		}
		IHtmlTable table = (IHtmlTable)subTableObjs[1];
		int rowCount = table.rowCount();
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		
		for(int i = 0; i < amounts.size(); i ++) {
			
			found = false;
			
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = table.findColumns(0, j, amounts.get(i));
				
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = table.getCellValue(j, colIndexes[k] - 1);
						
						if(!StringUtil.isEmpty(feeType) && !typeAmounts.containsKey(feeType)) {
							if(feeType.equalsIgnoreCase("Total Vouchers Payment")) {
								feeType = "Total Voucher Payment";
							}
							
							double amt = 0;
							if(amounts.get(i).contains("(")) {
								amt = -Double.parseDouble(amounts.get(i).replaceAll("\\(", StringUtil.EMPTY).replaceAll("\\)", StringUtil.EMPTY).trim().substring(1));
							} else {
								amt = Double.parseDouble(amounts.get(i).substring(1));
							}
							
							typeAmounts.put(feeType, amt);
							found = true;
						}
					}
				}
				
				if(found) {
					break;
				}
			}
		}
		Browser.unregister(objs);
		
		return typeAmounts;
	}

	
	public boolean isRefundSectionExist(){
		IHtmlObject objs[] = browser.getTableTestObject(refundsSectionTable());
		if(objs.length > 0){
			Browser.unregister(objs);
			return true;
		}else{
			Browser.unregister(objs);
			return false;
		}
	}
	
	public HashMap<String, Double> getOrderAmountsInRefundSection() {
		IHtmlObject objs[] = browser.getTableTestObject(refundsSectionTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Refund' section table object.");
		
		//get all amounts span object in Refund section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) throw new ItemNotFoundException("Cannot find any fee amount span object from 'Refund' section.");
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		Browser.unregister(spanObjs);
		
		//get all order amounts
		IHtmlObject subTableObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(subTableObjs.length < 1) throw new ItemNotFoundException("Cannot find sub table in 'Refund' section.");
		IHtmlTable table = (IHtmlTable)subTableObjs[1];
		int rowCount = table.rowCount();
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		
		for(int i = 0; i < amounts.size(); i ++) {
			
			found = false;
			
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = table.findColumns(0, j, amounts.get(i));
				
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = table.getCellValue(j, colIndexes[k] - 1);
						
						if(!StringUtil.isEmpty(feeType) && !typeAmounts.containsKey(feeType)) {
							String amt=amounts.get(i).replace("$", StringUtil.EMPTY);
							boolean isNegative = false;
							if(amt.contains("(")) {
								amt = amt.replace("(", StringUtil.EMPTY).replace(")", StringUtil.EMPTY);
								isNegative = true;
							}
							double a = Double.parseDouble(amt);
							typeAmounts.put(feeType, isNegative ? - a : a);
							found = true;
						}
					}
				}
				
				if(found) {
					break;
				}
			}
		}
		Browser.unregister(objs);
		
		return typeAmounts;
	}
	
	public HashMap<String, Double> getOrderAmountsInOutstandingSection() {
		IHtmlObject objs[] = browser.getTableTestObject(outstandingSectionTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Outstanding' section table object.");
		
		//get all amounts span object in Outstanding section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) throw new ItemNotFoundException("Cannot find any fee amount span object from 'Outstanding' section.");
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		Browser.unregister(spanObjs);
		
		//get all order amounts
		IHtmlObject subTableObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(subTableObjs.length < 1) throw new ItemNotFoundException("Cannot find sub table in 'Outstanding' section.");
		IHtmlTable table = (IHtmlTable)subTableObjs[1];
		int rowCount = table.rowCount();
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		
		for(int i = 0; i < amounts.size(); i ++) {
			
			found = false;
			
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = table.findColumns(0, j, amounts.get(i));
				
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = table.getCellValue(j, colIndexes[k] - 1);
						
						if(!StringUtil.isEmpty(feeType) && !typeAmounts.containsKey(feeType)) {
							typeAmounts.put(feeType, Double.parseDouble(amounts.get(i).substring(1)));
							found = true;
						}
					}
				}
				
				if(found) {
					break;
				}
			}
		}
		Browser.unregister(objs);
		
		return typeAmounts;
	}
	
	public HashMap<String, Double> getAllOrderAmounts() {
		HashMap<String, Double> allOrderAmounts = new HashMap<String, Double>();
		allOrderAmounts.putAll(this.getOrderAmountsInTotalsSection());
		if(isRefundSectionExist()){
			allOrderAmounts.putAll(this.getOrderAmountsInRefundSection());
		}
		if(isVoucherEarnedSectionExist()||isPaymentsChangeSectionExist()){
			allOrderAmounts.putAll(this.getOrderAmountsInPaymentsChangeOrVoucherEarnedSection());
		}
		if(isVoucherRedeemedSectionExist()){
			allOrderAmounts.putAll(this.getOrderAmountsInVoucherRedeemedSection());
		}
		allOrderAmounts.putAll(this.getOrderAmountsInOutstandingSection());
		
		return allOrderAmounts;
	}
	
	private Property[] pointsEarned() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Points Earned:(\\s)?\\d+", false));
	}
	
	private Property[] passNumber() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Pass No\\.(\\s)?", false));
	}
	
	public boolean isEarnedPointsExists() {
		return browser.checkHtmlObjectExists(pointsEarned());
	}
	
	public int getEarnedPoints(String product) {
		IHtmlObject trObjs[] = null;
		if(!StringUtil.isEmpty(product)) {
			trObjs = browser.getHtmlObject(Property.concatPropertyArray(tr(), ".text", new RegularExpression(product, false)));
			if(trObjs.length < 1) throw new ItemNotFoundException("Cannot find Product - " + product + " TR object.");
		}
		IHtmlObject objs[] = browser.getHtmlObject(pointsEarned(), trObjs != null ? trObjs[0] : null);
//		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Points Earned: TD object.");
		if(objs.length < 1) return 0;
		String text = objs[0].text();
		text = RegularExpression.getMatches(text, "Points Earned:(\\s)?\\d+")[0];
		text = text.split(":")[1].trim();
		
		Browser.unregister(objs);
		Browser.unregister(trObjs);
		return Integer.parseInt(text);
	}
	
	public int getEarnedPoints() {
		return getEarnedPoints(null);
	}
	
	public String getPassNumber() {
		String text = browser.getObjectText(passNumber());
		return text.split("No.")[1].trim();
	}
	
	public boolean compareEarnedPoints(int expected) {
		return MiscFunctions.compareResult("Earned Points", expected, this.getEarnedPoints());
	}
	
	public boolean compareEarnedPoints(String product, int expected) {
		return MiscFunctions.compareResult("Earned Points of product - " + product, expected, this.getEarnedPoints(product));
	}
	
	public void verifyEarnedPoints(int expected) {
		if(!compareEarnedPoints(expected)) throw new ErrorOnPageException("Earned Points is NOT correct.");
		logger.info("Earned Points is correct.");
	}
	
	public void verifyEarnedPoints(String product, int expected) {
		if(!compareEarnedPoints(product, expected)) throw new ErrorOnPageException("Earned Points of product - " + product + " is NOT correct.");
		logger.info("Earned Points of product - " + product + " is correct.");
	}
	
	public void verifyEarnedPointsExists(boolean exists) {
		if(!MiscFunctions.compareResult("'Point Earned:' exists", exists, this.isEarnedPointsExists())) throw new ErrorOnPageException("'Earned Points' shall" + (exists ? "": " NOT") + " exist");
	}
	
	private Property[] pointsRedeemed() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("Points Redeemed:(\\s)?\\d+$", false));
	}
	
	public boolean isRedeemedPointsExists() {
		return browser.checkHtmlObjectExists(pointsRedeemed());
	}
	
	public int getRedeemedPoints(String product) {
		IHtmlObject trObjs[] = null;
		if(!StringUtil.isEmpty(product)) {
			trObjs = browser.getHtmlObject(Property.concatPropertyArray(tr(), ".text", new RegularExpression(product, false)));
			if(trObjs.length < 1) throw new ItemNotFoundException("Cannot find Product - " + product + " TR object.");
		}
		IHtmlObject objs[] = browser.getHtmlObject(pointsRedeemed(), trObjs != null ? trObjs[trObjs.length - 2] : null);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Points Redeemed: TD object.");
		String text = objs[0].text();
		text = RegularExpression.getMatches(text, "Points Redeemed:(\\s)?\\d+")[0];
		text = text.split(":")[1].trim();
		
		Browser.unregister(objs);
		Browser.unregister(trObjs);
		return Integer.parseInt(text);
	}
	
	public int getRedeemedPoints() {
		return getRedeemedPoints(null);
	}
	
	public boolean compareRedeemedPoints(int expected) {
		return MiscFunctions.compareResult("Redeemed Points", expected, getRedeemedPoints());
	}
	
	public void verifyRedeemedPoints(int expected) {
		if(!compareRedeemedPoints(expected)) throw new ErrorOnPageException("Redeemed Points is NOT correct.");
	}
	
	public void verifyRedeemedPointsExists(boolean exists) {
		if(!MiscFunctions.compareResult("'Point Redeemed:' exists", exists, this.isRedeemedPointsExists())) throw new ErrorOnPageException("'Redeemed Points' shall" + (exists ? "": " NOT") + " exist");
	}
	
	private Property[] outfitterNum() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Outfitter \\#\\:", false));
	}
	
	public String getOutfitterLicenseNum() {
		String text = browser.getObjectText(outfitterNum());
		text = text.split(":")[1].trim();
		
		return text;
	}
	
	public boolean compareOutfitterLicenseNum(String expected) {
		return MiscFunctions.compareResult("Outfitter License Num", expected, this.getOutfitterLicenseNum());
	}
	
	private Property[] authorizationCustomer() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Authorization\\:", false));
	}
	
	public String getAuthorizationCustomer() {
		String text = browser.getObjectText(authorizationCustomer());
		text = text.split(":")[1].trim();
		
		return text;
	}
	
	public boolean compareAuthorizationCustomer(String fName, String lName, String halId) {
		String text = this.getAuthorizationCustomer();
		String actualHalID = text.split("\\(")[1].replaceAll("\\)", StringUtil.EMPTY);
		String names[] = text.split("\\(")[0].split(",");
		String actualLastName = names[0].trim();
		String actualFirstName = names[1].trim();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Authorization Customer first name", fName, actualFirstName);
		result &= MiscFunctions.compareResult("Authorization Customer last name", lName, actualLastName);
		result &= MiscFunctions.compareResult("Authorization Customer HAL ID", halId, actualHalID);
		
		return result;
	}
	
	private Property[] primaryPrivilegeHolder() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Primary (Privilege|Licence) Holder\\:", false));
	}
	
	public String getPrimaryPrivilegeHolder() {
		String text = browser.getObjectText(primaryPrivilegeHolder());
		text = text.split(":")[1].trim();
		
		return text;
	}
	
	private Property[] primaryPrivilegeName() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^(Privilege|Licence)\\:", false));
	}
	
	public String getPrimaryPrivilegeName() {
		String text = browser.getObjectText(primaryPrivilegeName());
		text = text.split(":")[1].trim();
		
		return text;
	}
	
	public boolean comparePrimaryPrivilegeHolder(String fName, String lName, String halId) {
		String expected = fName + " " + lName + " (" + halId + ")";
		return MiscFunctions.compareResult("Primary Privilege Holder", expected, this.getPrimaryPrivilegeHolder());
	}
	
	public boolean comparePrimaryPrivilegeName(String code, String name, String privilegeNum) {
		String expected = code + "-" + name + " (" + privilegeNum + ")";
		return MiscFunctions.compareResult("Primary Privilege Name", expected, this.getPrimaryPrivilegeName());
	}
	
	private Property[] sealNums() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Seal \\#\\(s\\)(\\s)?:", false));
	}
	
	public String[] getSealNums() {
		String text = browser.getObjectText(sealNums());
		text = text.split(":")[1].trim();
		String nums[] = text.split(",");
		return nums;
	}
	
	public boolean compareSealNums(String expectedNums[]) {
		boolean result = true;
		String actualNums[] = this.getSealNums();
		
		if(!MiscFunctions.compareResult("Seal Nums size", expectedNums.length, actualNums.length)) throw new ErrorOnPageException("Seal Nums size is not correct.");
		
		for(int i = 0; i < expectedNums.length; i ++) {
			result &= MiscFunctions.compareResult("Seal # - " + (i + 1), expectedNums[i], actualNums[i]);
		}
		
		return result;
	}
	
	private Property[] ledgerNums() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Ledger \\#\\(s\\)(\\s)?:", false));
	}
	
	public String[] getLedgerNums() {
		String text = browser.getObjectText(ledgerNums());
		text = text.split(":")[1].trim();
		String nums[] = text.split(",");
		return nums;
	}
	
	public boolean compareLedgerNums(String expectedNums[]) {
		boolean result = true;
		String actualNums[] = this.getLedgerNums();
		
		if(!MiscFunctions.compareResult("Ledger Nums size", expectedNums.length, actualNums.length)) throw new ErrorOnPageException("Ledger Nums size is not correct.");
		
		for(int i = 0; i < expectedNums.length; i ++) {
			result &= MiscFunctions.compareResult("Ledger # - " + (i + 1), expectedNums[i], actualNums[i]);
		}
		
		return result;
	}
	
	public String getPaymentTendered() {
		String price = "";
		IHtmlObject[] tables = browser.getTableTestObject(".text", new RegularExpression("^Payments & Change.*", false));
		if(tables.length<1)
			throw new ItemNotFoundException("Could not find Payments & Change table on page.");
		String tableText = tables[0].text();
		price = (tableText.substring(tableText.indexOf("Payment Tendered")+"Payment Tendered".length(), tableText.indexOf("Change Tendered"))).trim();
		Browser.unregister(tables);
		return price;
	}
}
