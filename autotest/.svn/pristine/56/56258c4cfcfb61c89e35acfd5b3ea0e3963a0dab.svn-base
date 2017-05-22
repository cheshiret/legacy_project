package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsPaymentSearchPage extends OrmsPage {

	/**
	 * Script Name   : <b>FldMgrPaymentDetailPage</b>
	 * Generated     : <b>Sep 30, 2004 6:34:21 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/09/30
	 * @author CGuo
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPaymentSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPaymentSearchPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPaymentSearchPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsPaymentSearchPage();
		}

		return _instance;
	}

	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
		//use Go button as the pageMark
		//.href: javascript:invokeAction(%20"SearchPayment.do",%20"link",%20"PagingWorker",%20"pfTag_SearchPayments:pfWorker_PayRefundWorker:pParamResetPaging_true:pfParam_:pfDriver_SearchPayment.do:"%20)
		return browser.checkHtmlObjectExists(".class","Html.SELECT",".id","searchpayrefmethod");
//        RegularExpression res = new RegularExpression("^Payment ID Customer Amount Change Tendered Net Amount Date/Time Pay. Method Pay. Status Collecting User Batch ID.*", false);
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", res);
	}

	/**
	 * Select Search Type from drop down list
	 * @param type Search Type
	 */
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", "payreftypeid", type);
	}

	/**
	 * Select Payment Type
	 * @param type payment Type
	 */
	public void selectPaymentType(String type) {
		browser.selectDropdownList(".id", "searchpayrefmethod", type);
	}

	/**
	 * Select Payment Status
	 * @param status
	 */
	public void selectPaymentStatus(String status) {
		browser.selectDropdownList(".id", "searchpayrefstatus", status);
	}

	/**
	 * Select Date Range Type
	 * @param range Date Range Type
	 */
	public void selectDateRange(String range) {
		browser.selectDropdownList(".id", "daterange", range);
	}

	/**
	 * Input search value
	 * @param value 
	 */
	public void setSearchTypeIDValue(String value) {
		browser.setTextField(".id", "payreftypesearchvalue", value);
	}

	/**
	 * Input start date
	 * @param date Start date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "searchstartdate_ForDisplay", date);
	}

	/**
	 * Input end date
	 * @param date End date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "searchenddate_ForDisplay", date);
	}

	/**
	 * Click Go Button
	 *
	 */
	public void clickGo() {
		Property[] properties = new Property[3];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".text", new RegularExpression("^Search$", false));
		properties[2] = new Property(".id", "goAnchor");
		browser.clickGuiObject(properties, true);
	}

	/**
	 * Click the first payment in field manager
	 *
	 */
	public void clickFirstPaymentForFieldManager() {
		IHtmlObject[] frames=getTransactionFrame();
	  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("\\d+", false), frames[0]);
	  objs[0].click();
	  Browser.unregister(frames);
	  Browser.unregister(objs);
	}
	
	/**
	 * Click the first payment
	 *
	 */
	public void clickFirstPayment() {
		IHtmlObject[] tops = browser.getTableTestObject(".text", new RegularExpression("^Payment ID Customer.*",false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",new RegularExpression("\\d+", false),tops[0]);
		objs[0].click();
		Browser.unregister(objs,tops);
	}
	
	public boolean checkPaymentIdLinkExist(String paymentID){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",paymentID);
	}
	
	/**
	 * Click the  payment by given payment ID
	 *
	 */
	public void clickPaymentByPaymentID(String paymentID) {
	  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",paymentID);
	  if(null != objs && objs.length > 0){
		  objs[0].click();
	  } else {
		  logger.error("Can't get payment record according given ID:"+paymentID);
	  }
	  Browser.unregister(objs);
	}

	/**
	 * Click specific payment
	 * @param paymentID
	 */
	public void clickPayment(String paymentID) {
		browser.clickGuiObject(".class", "Html.A", ".text", paymentID);
	}

	/**
	 * Search Payment by Payment Id
	 * @param id Payment Id
	 */
	public void searchPaymentByID(String id) {
		this.selectSearchType("Payment ID");
		this.setSearchTypeIDValue(id);
		this.clickGo();
	}

	/**
	 * Search payments by Order Number
	 * @param orderNum Order Number
	 */
	public void searchPaymentsByOrderNum(String orderNum) {
	  	setFromDate("");
		this.selectSearchType("Order Number");
		this.setSearchTypeIDValue(orderNum);
		this.clickGo();
		waitLoading();
	}

	/**
	 * Get one payment Id for one payment method
	 * @param payType payment method
	 * @return  payment Id
	 */
	public String getPaymentIdByPayType(String payType) {
	  	payType = turnPayTypeName(payType);
	  	if(payType.equalsIgnoreCase("MC")){
	  		payType = "MAST";
	  	}else if (payType.equalsIgnoreCase("Personal Check")||payType.equalsIgnoreCase("PER CHQ")) {
			payType = "PCHK";
		}
	  	RegularExpression rex = new RegularExpression("Payment ID Customer Amount Change Tendered.*", false);
	  	IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
	  	IHtmlTable tableGrid =(IHtmlTable)objs[0];
		int colNum = getColNum("Pay. Method");
		for(int i=1;i<tableGrid.rowCount();i++) {
		  	if((tableGrid.getCellValue(i,colNum).toString().equalsIgnoreCase(payType))||(payType.contains(tableGrid.getCellValue(i,colNum).toString()))) {//updated by pzhu, this condition: payType=Discover/JCB/UnionPay, but on the web page, displayes as 'DISC'
			  	return tableGrid.getCellValue(i,0).toString();
		  	}
		}
		Browser.unregister(objs);
		throw new ItemNotFoundException("Can not find payment by payment method is "+payType);
	}
	
	/**
	 * Get All Payments search by one Order Num
	 * @param orderNum Order Num
	 * @return Payments
	 */
	public List<Object> getAllPaymentsForOneOrder(String orderNum) {
		List<Object> payments = new ArrayList<Object>();
		searchPaymentsByOrderNum(orderNum);
		waitLoading();
		payments = getAllPayments();

		return payments;
	}

	/**
	 * Get All Payments from payments list
	 * @return  Payment list
	 */
	public List<Object> getAllPayments() {
		List<Object> payments = new ArrayList<Object>();
		RegularExpression rex = new RegularExpression(
				"Payment ID Customer Amount Change Tendered.*", false);
		IHtmlObject[] objs = null;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",
					rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount()-1; i++) {
			  	List<String> rowInfo = new ArrayList<String>();
				for (int j = 0; j < tableGrid.columnCount(); j++) {
					rowInfo.add(tableGrid.getCellValue(i, j));
				}
				payments.add(rowInfo);
			}
		} while (gotoNext());

		Browser.unregister(objs);
		return payments;
	}

	/**
	 * Set up Search Criteria
	 * @param pay Store many search criteria
	 */
	public void setupSearchCriteria(Payment pay) {
		clearSearchCritieria();
		this.clickPaymentTab();
		this.waitLoading();
		if (pay.searchType != null) {
			if (pay.searchType.equalsIgnoreCase("Order Number")) {
				selectSearchType("Order Number");
				setSearchTypeIDValue(pay.belongOrderNum);
			} else {
				selectSearchType(pay.searchType);
				setSearchTypeIDValue(pay.searchValue);
			} 

		}
		if(pay.dateRange!=null && pay.dateRange.length()>0)
		{
		  	selectDateRange(pay.dateRange);
		}
		if(pay.startDate==null || pay.startDate.length()<1) {
			pay.startDate=DateFunctions.getDateAfterToday(-90);
			pay.endDate=DateFunctions.getDateAfterToday(0);
		}
		setFromDate(pay.startDate);
		setToDate(pay.endDate);
		
		if (pay.status != null && !pay.status.equals("")) {
			selectPaymentStatus(pay.status);
		}
		if (pay.payType != null && !pay.payType.equals("")) {
			String payType = turnPayTypeName(pay.payType);
			selectPaymentType(payType);
		}
		clickGo();
		waitLoading();
	}

	/**
	 * Turn paymentType name from full name to short name
	 * @param payType
	 * @return
	 */
	public String turnPayTypeName(String payType)
	{
	  	if (payType.equalsIgnoreCase("Personal Check")||payType.equalsIgnoreCase("PCHK")) {
			payType = "PER CHQ";
		} else if (payType.equalsIgnoreCase("Travellers Check")) {
			payType = "TRAV CHK";
		} else if (payType.equalsIgnoreCase("Certified Check")) {
			payType = "CERT CHQ";
		} else if (payType.equalsIgnoreCase("Money Order")) {
			payType = "MON ORD";
		} else if (payType.equalsIgnoreCase("MasterCard")||payType.equalsIgnoreCase("MAST")){
		  	payType = "MC";
		} else if (payType.equalsIgnoreCase("Discover")){
		  	payType = "DISC";
		} else if(payType.equalsIgnoreCase("American Express")){
		    payType = "AMEX";
		}
	  	return payType.toUpperCase();
	}
	/**
	 * Search payments by diffrent critier and check the specific payment in the search result
	 * @param pay
	 */
	public void searchAndVerifyPayments(Payment pay) {
		logger.info("Start to search and verify payment " + pay.paymentID);
		setupSearchCriteria(pay);

		if (pay.searchType != null && pay.searchType.equals("Collect Location")) {
			int colNum = getColNum("Collect Location");
			verifyPaymentsList(colNum, pay.searchValue, pay.paymentID);
		} else if (pay.searchType != null
				&& pay.searchType.equalsIgnoreCase("Collect User (Last Name)")) {
			int colNum = getColNum("Collecting User");
			verifyPaymentsList(colNum, pay.collectUser, pay.paymentID);
		} else if (pay.searchType != null
				&& pay.searchType.equalsIgnoreCase("Customer (Last Name)")) {
			int colNum = getColNum("Customer");
			verifyPaymentsList(colNum, pay.lName + ", " + pay.fName, pay.paymentID);
		}
		if (pay.status != null && !pay.status.equals("")) {
			int colNum = getColNum("Pay. Status");
			verifyPaymentsList(colNum, pay.status, pay.paymentID);
		}
		if (pay.payType != null && !pay.payType.equals("")) {
			int colNum = getColNum("Pay. Method");
			verifyPaymentsList(colNum, pay.payType, pay.paymentID);
		}
	}

	/**
	 * Verify Sepcific column info correct,and found specific paymentID prepared
	 * @param colNum
	 * @param value
	 */
	public void verifyPaymentsList(int colNum, String value, String paymentID) {
		RegularExpression rex = new RegularExpression(
				"Payment ID Customer Amount Change Tendered.*", false);
		IHtmlObject[] objs = null;
		boolean foundPayment = false;
		objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",
				rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		for (int i = 1; i < tableGrid.rowCount(); i++) {
			if(tableGrid.getCellValue(i, 0)==null||tableGrid.getCellValue(i, colNum)==null){
				continue;
			}
			if (tableGrid.getCellValue(i, 0).equals(paymentID)) {
				foundPayment = true;
			}
			if (!tableGrid.getCellValue(i, colNum).trim().equalsIgnoreCase(
					value)) {
				Browser.unregister(objs);
				logger.error("Search Payments by " + value + " Fail! ");
				throw new ItemNotFoundException("Search Payments Fail!");
			}
		}
		if (!foundPayment) {
			Browser.unregister(objs);
			logger.error("Search Payments by " + value + " Fail! ");
			throw new ItemNotFoundException("Not Found Given Payment "+paymentID);
		}

		Browser.unregister(objs);
	}

	/**
	 * Check Next button exists or not,if exists,click it
	 * @return Next button exists or not
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");

		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			//MiscFunctions.clickObject(objs[0]);
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	/**
	 * Get column Num for specific Column Name
	 * @param colName
	 * @return column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression("Payment ID Customer Amount Change Tendered.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString().trim().equalsIgnoreCase(colName.trim())) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	/**
	 * Turn a payment vector to a payment object
	 * @param payments
	 * @return Payment Info
	 */
	public Payment turnRowToPayment(List<String> payment) { 
		Payment pay = new Payment();
		if (payment != null && payment.size() > 0) {
			pay.paymentID = payment.get(getColNum("Payment ID"));
			pay.customer = payment.get(getColNum("Customer"));
			pay.amount = payment.get(getColNum("Amount"));
			pay.payType = payment.get(getColNum("Pay. Method"));
			pay.status = payment.get(getColNum("Pay. Status"));
			pay.collectLocation = payment.get(getColNum("Collect Location"));
			pay.collectUser = payment.get(getColNum("Collecting User"));
		}
		return pay;
	}

	/**
	 * Clear search Critieria to default
	 *
	 */
	public void clearSearchCritieria() {
		selectSearchType("* All");
		setSearchTypeIDValue("");
		selectPaymentStatus("* All");
		selectPaymentType("* All");
	}

	/**
	 * Click Payments Link
	 *
	 */
	public void clickPaymentTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Payments");
	}
	
	/**
	 * Click Refunds Link
	 */
	public void clickRefundsTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Refunds");
	}
	
	public void setPaymentAmountGreater(String value){
		browser.setTextField(".id", "paymentMin", value);
	}
	
	public void setPaymentAmountLess(String value){
		browser.setTextField(".id", "paymentMax", value);
	}
	
	private List<String> getComlumnValueByName(String name){
		List<String> colLists = new ArrayList<String>();
		RegularExpression rex = new RegularExpression(
				"Payment ID Customer Amount Change Tendered.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int col = tableGrid.findColumn(0, name);
		List<String> colList = tableGrid.getColumnValues(col);
//		colList.remove(colList.size() -1);
		colList.remove(0);
		colLists.addAll(colList);
		Browser.unregister(objs);
		return colLists;
	}
	
	public List<BigDecimal> getAmountListValue(){
		List<String> amountList = getComlumnValueByName("Amount");
		List<BigDecimal> returnList = new ArrayList<BigDecimal>();
		
		// if contains "$", remove it.
		for(String amount:amountList){
			amount = amount.replaceAll("(\\$)|(,)", StringUtil.EMPTY);
			if(StringUtil.notEmpty(amount)){
				returnList.add(new BigDecimal(amount));
			}
		
		}
		return returnList;
	}
	
	public List<String> getPaymentIDList(){
		return getComlumnValueByName("Payment ID");
	}
}
