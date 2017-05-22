package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsRefundSearchPage extends OrmsPage {

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
	static private OrmsRefundSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsRefundSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsRefundSearchPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsRefundSearchPage();
		}

		return _instance;
	}
	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
		//use Go button as the pageMark
		//.href: javascript:invokeAction(%20"OpMgrFindRefunds.do",%20"SearchRefunds",%20"PayRefundWorker",%20"FromSearchButton"%20%20)

		return browser.checkHtmlObjectExists(".class", "Html.A", ".href",
						new RegularExpression( "javascript:invokeAction\\(.*\"OpMgrFindRefunds\\.do\",.+\"SearchRefunds\",.+\"PayRefundWorker\",.+\"FromSearchButton\".*", false));
	}
	
	/**
	 * Select Search Type from drop down list
	 * @param type -Search Type
	 */
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", "payreftypeid", type);
	}

	/**
	 * Select Refund Type
	 * @param type Refund Type
	 */
	public void selectRefundType(String type) {
		browser.selectDropdownList(".id", "searchpayrefmethod", type);
	}

	/**
	 * Select Refund Status
	 * @param status
	 */
	public void selectRefundStatus(String status) {
		browser.selectDropdownList(".id", "searchpayrefstatus", status);
	}

	/**
	 * Select Payment method
	 * @param sourcePmt payment method
	 */
	public void selectSourcePaymentMethods(String sourcePmt) {
		browser.selectDropdownList(".id", "sourcePaymentMethod", sourcePmt);
	}

	/**
	 * Select Payment Collect Location
	 * @param location
	 */
	public void selectPaymentCollectLocation(String location) {
		browser.selectDropdownList(".id", "searchsourcepmtslschnl", location);
	}

	/**
	 * Select Date Range Type
	 * @param range
	 */
	public void selectDateRange(String range) {
		browser.selectDropdownList(".id", "daterange", range);
	}

	/**
	 * Input Search Value
	 * @param value
	 */
	public void setSearchTypeIDValue(String value) {
		browser.setTextField(".id", "payreftypesearchvalue", value);
	}

	/**
	 * Input Start Date
	 * @param date Start Date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "searchstartdate_ForDisplay", date);
	}

	/**
	 * Input End Date
	 * @param date End Date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "searchenddate_ForDisplay", date);
	}

	/**
	 * Click Go Button
	 *
	 */
	public void clickGo() {
		IHtmlObject[] objs = getTransactionFrame();
		if(objs!=null&&objs.length>0){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false),false,0,objs[0]);
		}else{
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
		}
	}

	/**
	 * Check there have refund display
	 * @return
	 */
	public boolean checkRefundExists() {
		RegularExpression rex = new RegularExpression(
				"javascript:invokeAction\\(.*\"OpMgrRefundDetails.do\",.*\"RefundDetails\",.*\"PayRefundWorker\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	public void waitSearchResultExists() {
		RegularExpression rex = new RegularExpression(
				"javascript:invokeAction\\(.*\"OpMgrRefundDetails.do\",.*\"RefundDetails\",.*\"PayRefundWorker\".*", false);
		browser.searchObjectWaitExists(".class", "Html.A", ".href", rex);
	}

	/**
	 * Click the first Refund
	 *
	 */
	public void clickFirstRefund() {
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"OpMgrRefundDetails.do\",.*\"RefundDetails\",.*\"PayRefundWorker\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href", rex);
	}

	/**
	 * Click Specific Refund
	 * @param refundID
	 */
	public void clickRefund(String refundID) {
		browser.clickGuiObject(".class", "Html.A", ".text", refundID);
	}

	/**
	 * Search Refund By Refund Id
	 * @param id Refund Id
	 */
	public void searchRefundByID(String id) {
		this.selectSearchType("Refund ID");
		this.setSearchTypeIDValue(id);
		this.clickGo();
		waitLoading();
	}

	/**
	 * Search Refund By Order Number
	 * @param orderNum
	 */
	public void searchRefundByOrderNum(String orderNum) {
		this.searchRefundByOrderNumAndStatus(orderNum, "All");
	}
	
	public void searchRefundByOrderNumAndStatus(String orderNum,String status) {
		selectSearchType("Order Number");
		setSearchTypeIDValue(orderNum);
		selectRefundStatus(status);
		clickGo();
		waitLoading();
	}
	
	/**
	 * Get All Refunds search by one Order Num
	 * @param orderNum Order Num
	 * @return Refunds
	 */
	public List<Object> getAllRefundsForOneOrder(String orderNum) {
		List<Object> refunds = new ArrayList<Object>();
		searchRefundByOrderNum(orderNum);
		waitLoading();
		refunds = getAllRefunds();

		return refunds;
	}
	/**
	 * Search refund by status
	 *
	 */
	public void searchRefundByStatus(String status) {
	  	logger.info("Search Refund By Status " + status);
		selectRefundStatus(status);
		clickGo();
		waitLoading();
	}

	/**
	 * Search refund by refund type
	 * @param refundType
	 */
	public void searchRefundByRefundType(String refundType) {
	  	logger.info("Search Refund By Refund Type "+refundType);
		selectRefundType(turnPayTypeName(refundType));
		clickGo();
		waitLoading();
	}
	
	/**
	 * Search refund by source payment type
	 * @param payType
	 */
	public void searchRefundByPaymentMethod(String payType) {
	  	logger.info("Search Refund By Payment Method "+payType);
		selectSourcePaymentMethods(turnPayTypeName(payType));
		clickGo();
		waitLoading();
	}
	/**
	 * Get all refund Ids in the search list
	 * @return  a vector store many refund Ids
	 */
	public List<String> getRefundIDS() {
		List<String> refundIDS = new ArrayList<String>();
		RegularExpression rex = new RegularExpression("Refund ID Customer Amount Requesting Date.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find refund table.");
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int row = tableGrid.findColumn(0, "Refund ID");
		for (int i = 1; i < tableGrid.rowCount(); i++) {
			String id = tableGrid.getCellValue(i, row);
			if (StringUtil.notEmpty(id)) {
				refundIDS.add(id);
				
			}
		}
		Browser.unregister(objs);
		return refundIDS;
	}
	
	/**
	 * Get All Refunds from Refund list
	 * @return  Refund Vector
	 */
	public List<Object> getAllRefunds() {
		List<Object> refunds = new ArrayList<Object>();
		RegularExpression rex = new RegularExpression("Refund ID Customer Amount Requesting Date.*", false);
		IHtmlObject[] objs = null;
		do {
			objs = browser.getTableTestObject(".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int rowNum = tableGrid.rowCount();
			int colNum = tableGrid.columnCount();
			for (int i = 1; i < (rowNum-1); i++) {  //update by pzhu 2012/3/8
				if(StringUtil.notEmpty(tableGrid.getCellValue(i, 0))){
					List<String> rowInfo = new ArrayList<String>();
					for (int j = 0; j < colNum; j++) {
						rowInfo.add(tableGrid.getCellValue(i, j));
					}
					refunds.add(rowInfo);
				}
			}
		} while (gotoNext());
		Browser.unregister(objs);
		return refunds;
	}

	/**
	 * Click Issue Refund Tab
	 *
	 */
	public void clickIssueRefund() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Issue Refunds");
	}
	
	/**
	 * Input Date Range Information
	 * @param dateRange
	 * @param fromDate
	 * @param toDate
	 */
	public void setupDateCriteria(String dateRange,String fromDate,String toDate) {
		if (dateRange.equalsIgnoreCase("Requesting Date")) {
			selectDateRange("Requesting Date");
		} else if (dateRange.equalsIgnoreCase("Approving Date")) {
			selectDateRange("Approving Date");
		} else if (dateRange.equalsIgnoreCase("Issuing Date")) {
			selectDateRange("Issuing Date");
		} else if (dateRange.equalsIgnoreCase("Void Date")) {
			selectDateRange("Void Date");
		}
		if(dateRange.equals(""))
		{
		  dateRange = "Requesting Date";
		}
		setFromDate(fromDate);
		setToDate(toDate);
	}
	/**
	 * Set and Search refund by different Criteria
	 * @param refund Refund Info store many search criteria
	 */
	public void setupSearchCriteria(RefundInfo refund) {
		String log = "Search  Refund ";
		clearSearchCritieria();
		if (refund.searchType != null&&!refund.searchType.equals("")) {
			if (refund.searchType.equalsIgnoreCase("Requesting Location")) {
				selectSearchType("Requesting Location");
			} else if (refund.searchType
					.equalsIgnoreCase("Customer (Last Name)")) {
				selectSearchType("Customer (Last Name)");
			} else if (refund.searchType
					.equalsIgnoreCase("Requesting User (Last Name)")) {
				selectSearchType("Requesting User (Last Name)");
			}
			setSearchTypeIDValue(refund.searchValue);
			log = log + " by " + refund.searchType;
		}

		if (refund.dateRange != null) {  	
			setupDateCriteria(refund.dateRange,refund.startDate,refund.endDate);
			log = log + " and " + refund.dateRange + " is " + refund.startDate;
		}

		if (refund.status != null && !refund.status.equals("")) {
			selectRefundStatus(refund.status);
			log = log + " and status is " + refund.status;
		}
		if (refund.paymentMethod != null && !refund.paymentMethod.equals("")) {
			selectSourcePaymentMethods(turnPayTypeName(refund.paymentMethod));
			log = log + " and paymentMethod is " + refund.paymentMethod;
		}
		if (refund.refundType != null && !refund.refundType.equals("")) {
			selectRefundType(turnPayTypeName(refund.refundType));
			log = log + " and Refund Type is " + refund.refundType;
		}
		if (refund.collectLocation != null
				&& !refund.collectLocation.equals("")) {
			selectPaymentCollectLocation(refund.collectLocation);
			log = log + " and collect location is " + refund.collectLocation;
		}
		log = log + "!";
		logger.info(log);
		clickGo();
		waitLoading();
	}

	/**
	 * Verify the refund value correct in the search result
	 * @param refund
	 */
	public void verifyRefunds(RefundInfo refund) {
		logger.info("Start to verify refund!");

		if (refund.searchType != null && refund.searchType.equals("Requesting Location")) {
			verifyRefundInfo("Requesting Location", refund.searchValue);
		} else if (refund.searchType != null
				&& refund.searchType.equalsIgnoreCase("Requesting User (Last Name)")) {
			verifyRefundInfo("Requesting User", refund.user);
		} else if (refund.searchType != null
				&& refund.searchType.equalsIgnoreCase("Customer (Last Name)")) {
			verifyRefundInfo("Customer", refund.customer);
		}
		if (refund.status != null && !refund.status.equals("")) {
			verifyRefundInfo("Status", refund.status);
		}
		if (refund.paymentMethod != null && !refund.paymentMethod.equals("")) {
			verifyRefundInfo("Source Payment Method", refund.paymentMethod);
		}
		if (refund.refundType != null && !refund.refundType.equals("")) {
		    verifyRefundInfo("Refund Type", refund.refundType);
		}
	}
	/**
	 * Turn paymentType name from full name to short name
	 * @param payType
	 * @return
	 */
	public String turnPayTypeName(String payType) {
	  	if (payType.equalsIgnoreCase("Personal Check")) {
			payType = "PER CHQ";
		} else if (payType.equalsIgnoreCase("Travellers Check")) {
			payType = "TRAV CHK";
		} else if (payType.equalsIgnoreCase("Certified Check")) {
			payType = "CERT CHQ";
		} else if (payType.equalsIgnoreCase("Money Order")) {
			payType = "MON ORD";
		} else if (payType.equalsIgnoreCase("MasterCard")){
		  	payType = "MC";
		} else if (payType.equalsIgnoreCase("Discover/JCB/UnionPay")){//updated by pzhu
		  	payType = "DISC";
		} else if(payType.equalsIgnoreCase("American Express")){
		    payType = "AMEX";
		}else if(payType.equalsIgnoreCase("Visa")){
		    payType = "VISA";
		}
	  	return payType;
	}
	/**
	 * Clear search Critieria
	 *
	 */
	public void clearSearchCritieria() {
		setSearchTypeIDValue("");
		selectRefundStatus("All");
		selectSourcePaymentMethods("All");
		selectRefundType("All");
		selectPaymentCollectLocation("All");
	}

	/**
	 * Get one Refund Id for one payment method
	 * @param payType payment method
	 * @return  refund Id
	 */
	public String getRefundIdByPayType(String payType) {
	  	RegularExpression rex = new RegularExpression("Refund ID Customer Amount.*", false);
	  	IHtmlObject[] objs = browser.getTableTestObject(".text",rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int colNum = getColNum("Source Payment Method");
		int idCol = getColNum("Refund ID");
		for(int i=1;i<(tableGrid.rowCount());i++) {
		  	if(tableGrid.getCellValue(i, colNum).toString().equalsIgnoreCase(payType)) {
			  	return tableGrid.getCellValue(i,idCol).toString();
		  	}
		}
		Browser.unregister(objs);
		throw new ItemNotFoundException("Can not find refund by payment method is "+payType);
	}
	/**
	 * Turn a refund Vector to a refund info
	 * @param refund vector
	 * @return Refund Info
	 */
	public RefundInfo turnRowVectorToRefund(List<String> refund) {
	  	RefundInfo rfd = new RefundInfo();
		if (refund != null && refund.size() > 0) {
		  rfd.refundID = refund.get(getColNum("Refund ID")).toString();
		  rfd.customer = refund.get(getColNum("Customer")).toString();
		  rfd.amount = refund.get(getColNum("Amount")).toString();
		  rfd.location = refund.get(getColNum("Requesting Location")).toString();
		  rfd.paymentMethod = refund.get(getColNum("Source Payment Method ")).toString();
		  rfd.refundType = refund.get(getColNum("Refund Type")).toString();
		  rfd.collectLocation = refund.get(getColNum("Payment Collect Location")).toString();
		  rfd.status = refund.get(getColNum("Status")).toString();
		  rfd.user = refund.get(getColNum("Requesting User")).toString();
		} 
		return rfd;
	}

	/**
	 * Verify Specific Refund In Search List
	 * @param refundId refund id
	 */
	public void verifyRefundInSearchList(String refundId) {
		RegularExpression rex = new RegularExpression(
				"Refund ID Customer Amount Requesting Date.*", false);
		IHtmlObject[] objs = null;
		boolean found = false;
		do {
			objs = browser.getTableTestObject(".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
					//.getTestData("grid");
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 0).toString().equals(refundId)) {
				  found = true;
					break;
				}
			}
			if (found) {
			  	logger.info("Refund "+refundId+" is in the search result!");
				break;
			}
			if (!hasNext() && !found) {
				Browser.unregister(objs);
				throw new ItemNotFoundException("Search Refunds Fail!");
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}
	
	/**
	 * Check whether there have next page,if have,click Next Button
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Next", false));
		boolean toReturn = false;
		if (objs.length > 0&&objs[0].isEnabled()) {
			toReturn = true;
			//MiscFunctions.clickObject(objs[0]);
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	public boolean hasNext() {
	    IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Next", false));
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
		}
		Browser.unregister(objs);

		return toReturn;
	}
	/**
	 * Get Column Number by Column Name
	 * @param colName
	 * @return Column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression("Refund ID Customer Amount Requesting Date.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
					
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString().trim().equalsIgnoreCase(colName.trim())) {
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}
	/**
	 * Verify a RefundInfo column is same with given value
	 * @param colName
	 * @param value
	 */
	public void verifyRefundInfo( String colName, String value) {
	  int colNum = getColNum( colName );
	  RegularExpression rex = new RegularExpression( "Refund ID Customer Amount Requesting Date.*", false );
	  IHtmlObject[] objs = null;
	  do {
			objs = browser.getTableTestObject(".text",rex);
			if(null!=objs) {
			  IHtmlTable tableGrid = (IHtmlTable) objs[0];
				for (int i = 1; i < tableGrid.rowCount(); i++) {
				  if (null != tableGrid.getCellValue(i, colNum)) {
					if (!tableGrid.getCellValue(i, colNum).toString().trim().equalsIgnoreCase(value)) {
					  	Browser.unregister(objs);
			    	  	throw new ItemNotFoundException("Refund Value '" + tableGrid.getCellValue(i,colNum).toString() + "' not correct! Expect value is:"+value);
			    	}
				}
			}
		}
	} while (gotoNext());
	  Browser.unregister(objs);
   }
	
	/**
	 * Click Print Checks Link
	 */
	public void clickPrintChecksTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Checks");		
	}
	
	public void setRefundAmountGreater(String value){
		browser.setTextField(".id", "refundMin", value);
	}
	
	public void setRefundAmountLess(String value){
		browser.setTextField(".id", "refundMax", value);
	}
	
	private List<String> getComlumnValueByName(String name){
		List<String> colLists = new ArrayList<String>();
		RegularExpression rex = new RegularExpression(
				"Refund ID Customer Amount Requesting Date.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int col = tableGrid.findColumn(0, name);
		List<String> colList = tableGrid.getColumnValues(col);
		
		// remove null value
		colList.remove(0);// remove title
		for(String colValue:colList){
			if(StringUtil.notEmpty(colValue)){
				colLists.add(colValue);
			}
		}
		Browser.unregister(objs);
		return colLists;
	}
	
	public List<BigDecimal> getAmountListValue(){
		List<String> amountList = getComlumnValueByName("Amount");
		List<BigDecimal> returnList = new ArrayList<BigDecimal>();
		
		// if contains "$" and ",", remove it.
		for(String amount:amountList){
			amount = amount.replaceAll("(\\$)|(,)", StringUtil.EMPTY);
			returnList.add(new BigDecimal(amount));
		}
		return returnList;
	}
}
