/*
 * Created on Jan 11, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.raFee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.RaFeeInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 * 
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRaFeeMainPage extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRaFeeMainPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRaFeeMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRaFeeMainPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRaFeeMainPage();
		}
		return _instance;
	}

	/**
	 * check RA Fee detail page exists or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^RA Fee ID( |)Order.*", false));
	}

	/**
	 * Retrive Ra fee schedule info from page.
	 * 
	 * @return-RaFeeScheduleData
	 */
	public RaFeeScheduleData getRaFeeSchedule() {
		RaFeeScheduleData feeSchd = new RaFeeScheduleData();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression(
						"^Actions Void RA Fee Cancel RA Fee.*", false));
		String temp = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		feeSchd.product = temp.substring(
				temp.indexOf("Product ") + "Product ".length(),
				temp.indexOf("Revenue Location")).trim();
		feeSchd.location = temp.substring(
				temp.indexOf("Revenue Location ")
						+ "Revenue Location ".length(),
				temp.indexOf("Sales Channel")).trim();
		feeSchd.salesChannel = temp.substring(
				temp.indexOf("Sales Channel ") + "Sales Channel ".length(),
				temp.indexOf("Transaction Type")).trim();
		feeSchd.tranType = temp.substring(
				temp.indexOf("Transaction Type ")
						+ "Transaction Type ".length(),
				temp.indexOf("Transaction Occurrence")).trim();
		feeSchd.tranOccur = temp.substring(
				temp.indexOf("Transaction Occurrence ")
						+ "Transaction Occurrence ".length(),
				temp.indexOf("Pricing Info")).trim();
		feeSchd.feeId = temp.substring(
				temp.indexOf("RA Fee Schedule ID ")
						+ "RA Fee Schedule ID ".length(), temp.indexOf("Unit"))
				.trim();
		feeSchd.unitOption = temp.substring(
				temp.indexOf("Unit ") + "Unit ".length(),
				temp.indexOf("Number of Unit")).trim();
		feeSchd.baseRate = temp.substring(
				temp.indexOf("Rate ") + "Rate ".length(),
				temp.indexOf("Account")).trim().replaceFirst("\\$", "");
		feeSchd.acctCode = temp.substring(
				temp.indexOf("Account ") + "Account ".length(),
				temp.indexOf("Threshold Schedule ID")).trim();
		return feeSchd;
	}
	
	public List<RaFeeInfo> getRaFeeInfos(String applyType){
		
		List<RaFeeInfo> raFeeInfoList = new ArrayList<RaFeeInfo>();
		PagingComponent page=new PagingComponent(_instance);
		do{
			IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^RA Fee ID( |)Order.*", false));
			
			if(objs.length<1){
				throw new ItemNotFoundException("Did not found RA Fee Records Table Object.");
			}
			
			IHtmlTable table = (IHtmlTable)objs[objs.length-1];
			
			for(int i=1; i<table.rowCount(); i++){
			RaFeeInfo raFeeInfo = new RaFeeInfo();
			
			if(applyType.equals(OrmsConstants.APPLYFEE_ORDERLEVEL)){
				if(StringUtil.notEmpty(table.getCellValue(i, 2)))
					continue;
			}else if(applyType.equals(OrmsConstants.APPLYFEE_ORDERITEMLEVEL)){
				if(StringUtil.isEmpty(table.getCellValue(i, 2)))
					continue;
			}
			raFeeInfo.setRaFeeID(table.getCellValue(i, 0));
			raFeeInfo.setOrdNumber(table.getCellValue(i, 1));
			raFeeInfo.setProduct(table.getCellValue(i, 2));
			raFeeInfo.setRevenueLocation(table.getCellValue(i, 3));
			raFeeInfo.setType(table.getCellValue(i, 4));
			raFeeInfo.setStatus(table.getCellValue(i, 5));
			raFeeInfo.setSalesChannel(table.getCellValue(i, 6));
			raFeeInfo.setTranType(table.getCellValue(i, 7));
			raFeeInfo.setTranOccurrence(table.getCellValue(i, 8));
			if(table.getCellValue(i, 13).contains("$")){
				raFeeInfo.setPrice(table.getCellValue(i, 13).split("\\$")[1].replaceAll("\\)", ""));
			}else {
				raFeeInfo.setPrice(table.getCellValue(i, 13));
			}
			
			raFeeInfo.setRaFeeScheduleID(table.getCellValue(i, 15));
			raFeeInfo.setAccount(table.getCellValue(i, 16));
			raFeeInfo.setThresholdScheduleID(table.getCellValue(i, 17));
			
			raFeeInfoList.add(raFeeInfo);
			
			Browser.unregister(objs);	
		}}while(page.clickNext());
		
			
		return raFeeInfoList;
	}	
	
	public List<RaFeeInfo> getRaFeeInfos(){
		return getRaFeeInfos(StringUtil.EMPTY);
	}
	
	public void verifyRaFeeInfos(List<RaFeeInfo> expRaFeeInfos){		
		List<RaFeeInfo> actRaFeeInfos = this.getRaFeeInfos();
		
		if(actRaFeeInfos.size() != expRaFeeInfos.size()){
			throw new ErrorOnDataException("The ra fee info not correct, dut to the ra fee info size not match, " +
					"expect size should be " + expRaFeeInfos.size() + ", but actually size is " + actRaFeeInfos.size());
		}
		
		for(int i=0; i<expRaFeeInfos.size(); i++){
			RaFeeInfo expRaFeeInfo = expRaFeeInfos.get(i);
			RaFeeInfo actRaFeeInfo = actRaFeeInfos.get(i);

			this.compareRaFeeRecord(expRaFeeInfo, actRaFeeInfo);
		}
	
	}
	
	public void compareRaFeeRecord(RaFeeInfo expRaFeeInfo,RaFeeInfo actRaFeeInfo){
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Order Number", 
				expRaFeeInfo.getOrdNumber(), actRaFeeInfo.getOrdNumber());
		result &= MiscFunctions.compareResult("Product", 
				expRaFeeInfo.getProduct(), actRaFeeInfo.getProduct().split("-")[0]);
		if(expRaFeeInfo.getType().matches("[0-9]+")){
			expRaFeeInfo.setType(MiscFunctions.covertFeeJournalType(expRaFeeInfo.getType()));
		}
		result &= MiscFunctions.compareResult("Type", 
				expRaFeeInfo.getType(), actRaFeeInfo.getType());
		
		if(expRaFeeInfo.getStatus().matches("[0-9]+")){
			expRaFeeInfo.setStatus(MiscFunctions.covertFeeStatus(expRaFeeInfo.getStatus()));
		}
		result &= MiscFunctions.compareResult("Status", 
				expRaFeeInfo.getStatus(), actRaFeeInfo.getStatus());
		
		if(expRaFeeInfo.getPrice().length()>0){
			if("".equals(actRaFeeInfo.getPrice())){
				actRaFeeInfo.setPrice("0.00");
			}
			result &= MiscFunctions.compareResult("Price", 
					new BigDecimal(expRaFeeInfo.getPrice()), new BigDecimal(actRaFeeInfo.getPrice()));
		}else {
			result &= MiscFunctions.compareResult("Price", 
					expRaFeeInfo.getPrice(), actRaFeeInfo.getPrice());
		}
		
		result &= MiscFunctions.compareResult("RA Fee Schedule ID", 
				expRaFeeInfo.getRaFeeScheduleID(), actRaFeeInfo.getRaFeeScheduleID());	
		
		if (!result) {
			throw new ErrorOnDataException(
					"RA Fee info is not correct,please check log....");
		}
	}
	
	public void compareRaFeeRecords(List<RaFeeInfo> expRaFeeInfos, List<RaFeeInfo> actRaFeeInfos){
		for(int i=0; i<expRaFeeInfos.size(); i++){
			compareRaFeeRecord(expRaFeeInfos.get(i), actRaFeeInfos.get(i));
		}
	}

	/**
	 * Get Order number from page
	 * 
	 * @return
	 */
	public String getOrdNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression(
						"^Actions Void RA Fee Cancel RA Fee.*", false));
		String temp = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		return temp.substring(temp.indexOf("Order ") + "Order ".length(),
				temp.indexOf("Product")).trim();
	}

	/**
	 * This method used to verify price is correct.
	 * 
	 */
	public void verifyPriceCorrect() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression(
						"^Actions Void RA Fee Cancel RA Fee.*", false));
		String temp = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		String price = temp.substring(
				temp.indexOf("Price ") + "Price ".length(),
				temp.indexOf("Date/Time")).trim().replaceFirst("\\$", "");
		String unit = temp.substring(temp.indexOf("Unit ") + "Unit ".length(),
				temp.indexOf("Number of Unit")).trim();
		String rate = temp.substring(temp.indexOf("Rate ") + "Rate ".length(),
				temp.indexOf("Account")).trim().replaceFirst("\\$", "");

		if (unit.equalsIgnoreCase("Transaction")) {
			if (!price.equals(rate)) {
				throw new ItemNotFoundException("Price " + price
						+ " not correct!");
			}
		} else if (unit.equalsIgnoreCase("Unit")) {
			String unitNum = temp.substring(
					temp.indexOf("Number of Unit ")
							+ "Number of Unit ".length(), temp.indexOf("Rate"))
					.trim();
			if (!(Math.abs(Double.parseDouble(price) - Double.parseDouble(rate)
					* Double.parseDouble(unitNum)) < .0000001)) {
				throw new ItemNotFoundException("Price " + price
						+ " not correct!");
			}
		} else {
			throw new ItemNotFoundException("Unknown Unit" + unit);
		}

	}

	/**
	 * This method used to verify Ra Fee Details Info
	 * 
	 * @param resNum
	 * @param schedule
	 */
	public void verifyRaFeeDetail(String resNum, RaFeeScheduleData schedule) {
		logger.info("Start to verify RA Fee Detail.");

		if (!resNum.equalsIgnoreCase(getOrdNum())) {
			throw new ItemNotFoundException("Order Number " + getOrdNum()
					+ " not Correct.");
		}
		verifyPriceCorrect();
		RaFeeScheduleData feeSchd = getRaFeeSchedule();
		if (!schedule.product.equalsIgnoreCase(feeSchd.product)) {
			throw new ItemNotFoundException("Prodcut " + feeSchd.product
					+ " not Correct.");
		}
		if (!schedule.location.equalsIgnoreCase(feeSchd.location)) {
			throw new ItemNotFoundException("Revenue Location "
					+ feeSchd.location + " not Correct.");
		}
		if (!schedule.salesChannel.equalsIgnoreCase(feeSchd.salesChannel)) {
			throw new ItemNotFoundException("Sales Channel "
					+ feeSchd.salesChannel + " not Correct.");
		}
		if (!schedule.tranType.equalsIgnoreCase(feeSchd.tranType)) {
			throw new ItemNotFoundException("Transaction Type "
					+ feeSchd.tranType + " not Correct.");
		}
		if (!schedule.tranOccur.equalsIgnoreCase(feeSchd.tranOccur)) {
			throw new ItemNotFoundException("Transaction Occurrence "
					+ feeSchd.tranOccur + " not Correct.");
		}
		if (!schedule.feeId.equalsIgnoreCase(feeSchd.feeId)) {
			throw new ItemNotFoundException("Fee Schedule ID " + feeSchd.feeId
					+ " not Correct.");
		}
		if (!schedule.unitOption.equalsIgnoreCase(feeSchd.unitOption)) {
			throw new ItemNotFoundException("Unit " + feeSchd.unitOption
					+ " not Correct.");
		}
		if (!schedule.baseRate.equalsIgnoreCase(feeSchd.baseRate)) {
			throw new ItemNotFoundException("Rate " + feeSchd.baseRate
					+ " not Correct.");
		}
		if (!schedule.acctCode.equalsIgnoreCase(feeSchd.acctCode)) {
			throw new ItemNotFoundException("Account " + feeSchd.acctCode
					+ " not Correct.");
		}
	}

	/** Click on Go link. */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^(Go|Search)$", false));
	}

	/**
	 * Input search by value
	 * 
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", "rafee_search_id_value", value);
	}
	
	public void selectSearchDate(String date){
		browser.selectDropdownList(".id", "rafee_search_date", date);
	}
	
	public void setDateFrom(String date) {
		browser.setTextField(".id", "rafee_search_date_from_ForDisplay", date);
	}
	
	public void setDateTo(String date) {
		browser.setTextField(".id", "rafee_search_date_to_ForDisplay", date);
	}
	
	public void selectRaFeeType(String type){
		browser.selectDropdownList(".id", "rafee_type", type);
	}
	
	public void selectRaFeeType(){
		browser.selectDropdownList(".id", "rafee_type", 0);
	}
	
	public void selectRaFeeStatus(String status){
		browser.selectDropdownList(".id", "rafee_status", status);
	}
	
	public void selectRaFeeStatus(){
		browser.selectDropdownList(".id", "rafee_status", 0);
	}
	
	public void clickRaFeeID(String raFeeID){
		browser.clickGuiObject(".class", "Html.A",".text",raFeeID);
	}
	
	public void setSearchCriteria(RaFeeInfo raFeeInfo){				
		if(null != raFeeInfo.getSearchType() && raFeeInfo.getSearchType().length()>0){
			this.selectSearchType(raFeeInfo.getSearchType());
		}else {
			this.selectSearchType();
		}
		
		if(null != raFeeInfo.getSearchValue()){
			this.setSearchValue(raFeeInfo.getSearchValue());
		}
		
		if(null != raFeeInfo.getSearchDate()){
			this.selectSearchDate(raFeeInfo.getSearchDate());
		}
		
		if(null != raFeeInfo.getDateFrom()){
			this.setDateFrom(raFeeInfo.getDateFrom());
		}
		
		if(null != raFeeInfo.getDateTo()){
			this.setDateTo(raFeeInfo.getDateTo());
		}
		
		if(null != raFeeInfo.getType() && raFeeInfo.getType().length()>0){
			this.selectRaFeeType(raFeeInfo.getType());
		}else {
			this.selectRaFeeType();
		}
		
		if(null != raFeeInfo.getStatus() && raFeeInfo.getStatus().length()>0){
			this.selectRaFeeStatus(raFeeInfo.getStatus());
		}else {
			this.selectRaFeeStatus();
		}
		
		if(null != raFeeInfo.getTranType()){
			this.selectTransType(raFeeInfo.getTranType());
		}
	}

	/**
	 * Select given search type from drop down list
	 * 
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "rafee_search_id", searchType);
	}
	
	public void selectSearchType() {
		browser.selectDropdownList(".id", "rafee_search_id", 0);
	}

	public void searchByOrderNum(String ordNum) {
		selectSearchType("Order Number");
		setSearchValue(ordNum);
		clickGo();
		waitLoading();
	}
	
	public void searchByOrdAndStatus(String ordNum,String status){
		this.selectRaFeeStatus(status);
		this.searchByOrderNum(ordNum);
	}
	
	/**
	 * Select location class from drop down list
	 * 
	 * @param locationClass
	 */
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", "search_location_class", locationClass);
	}
	public void searchByLocationClass(String ordNum,String locationClass) {
		selectLocationClass(locationClass);
		this.searchByOrderNum(ordNum);
	}

	public List<String> getAllOptionsOfLocationClass()
	{
		List<String> optionStrings = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".id","search_location_class");
		IHtmlObject[] childObjs = objs[0].getChildren();
		for(IHtmlObject obj: childObjs)
		{
			optionStrings.add(((IHtmlObject)obj).text());
		}
			
		Browser.unregister(objs);
		Browser.unregister(childObjs);
		return optionStrings;
	}
	/**
	 * This method used to goto specific Ra Fee Detail Page
	 * 
	 * @param raFeeId
	 */
	public void gotoRaFeeDetailPg(String raFeeId) {
		browser.clickGuiObject(".class", "Html.A", ".text", raFeeId);
	}

	/**
	 * 
	 * @return the first RA Fee Id
	 */
	public String getFirstRaFeeId() {
		RegularExpression rex = new RegularExpression("^RA Fee ID Order.*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int colNum = getColNum("RA Fee ID");
		String raFeeId = tableGrid.getCellValue(1, colNum);
		Browser.unregister(objs);
		return raFeeId;
	}
	
	public String getRAFeeScheduleId(String feeID){
		RegularExpression rex = new RegularExpression("^RA Fee ID Order.*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int colNum = getColNum("RA Fee Schedule ID");
		int rowNum = tableGrid.findRow(0, feeID);
		String id = tableGrid.getCellValue(rowNum, colNum);
		Browser.unregister(objs);
		return id;
	}

	/***
	 * Get the RA Fee information via transaction type
	 * @param tranType ----Transaction type
	 * @param col ---the column number which information you want to retrieve
	 * @return the information of the cell
	 */
	public String getRaFeeInfoByTranType(String tranType, int col) {
		int toRecord = 0;
		int count = 0;
		String record = "";

		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^RA Fee ID Order.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		for (int row = 0; row < table.rowCount(); row++) {
			if (table.getCellValue(row, 9).trim().equalsIgnoreCase(tranType)) {
				count++;
				toRecord = row;
				break;
			}
		}

		if (count == 0) {
			throw new ItemNotFoundException(
					"The given transaction type"+tranType+" can not be found!");
		}
		
		record = table.getCellValue(toRecord, col);
		Browser.unregister(objs);
		return record;
	}
	

	/**
	 * Verify specific column value is the same with given value
	 * 
	 * @param colName
	 *            column Name
	 * @param value
	 */
	public void verifyRaFeeInfo(String colName, String value) {
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression("^RA Fee ID Order.*",
				false);
		IHtmlObject[] objs = null;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			if (tableGrid.rowCount() > 1) {
				for (int i = 1; i < tableGrid.rowCount(); i++) {
					if (null != tableGrid.getCellValue(i, colNum)) {
						if (!tableGrid.getCellValue(i, colNum).trim().equals(
								value)) {
							Browser.unregister(objs);
							logger.error("RA Fee Info "
									+ tableGrid.getCellValue(i, colNum)
									+ " is not Correct! ");
							throw new ItemNotFoundException(tableGrid
									.getCellValue(i, colNum)
									+ " is different with given value " + value);
						}
					}
				}
			} else {
				Browser.unregister(objs);
				throw new ItemNotFoundException("No RA Fee Found.");
			}

		} while (gotoNext());

		Browser.unregister(objs);
	}

	/**
	 * Check whether there have next page,if have,click Next Button
	 * 
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	/**
	 * Check whether there have last page,if have,click Last Button
	 * 
	 * @return
	 */
	public boolean gotoLast() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Last");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}
	/**
	 * Get RA fee record of which RA fee ID is feeId.
	 * 
	 * @return
	 */
	public RaFeeScheduleData getRAFeeRecord(String feeId)
	{
		RaFeeScheduleData fee = new RaFeeScheduleData();
		IHtmlObject[] objs;
		
		this.gotoLast();
		
		do{
			objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(".*"+feeId+".*", false));
			if(objs.length>0){
				break;
			}
		}while((objs.length==0)&&(this.gotoPrevious()));
		
		if(objs.length!=0)
		{
			IHtmlObject[] columns = browser.getHtmlObject(".class", "Html.TD",objs[0]);
			fee.feeId = columns[getColNum("RA Fee ID")].text();
			fee.product = columns[getColNum("Product")].text();
			Browser.unregister(objs);
			Browser.unregister(columns);
			return fee;
		}else{
			Browser.unregister(objs);
			return null;
		}
				
		
	}
	/**
	 * Check whether there have previous page,if have,click previous Button
	 * 
	 * @return
	 */
	public boolean gotoPrevious() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Previous");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}
	 
	/**
	 * Get Column Number by Column Name
	 * 
	 * @param colName
	 * @return Column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression("^RA Fee ID Order.*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colnum=tableGrid.findColumn(0, colName);
			Browser.unregister(objs);
			return colnum;
			
		}else{
			Browser.unregister(objs);
			return -1;
		}
		
	}
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<RaFeeInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<RaFeeInfo> records = new ArrayList<RaFeeInfo>();
		int rows;
		int columns;
		RaFeeInfo fee;
		
		
		do{
			objs = browser.getTableTestObject(".className", new RegularExpression("listView|searchResult",false));
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't RA fee table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page FinMgrRaFeeMainPage, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				fee = new RaFeeInfo();
				fee.setRaFeeID(table.getCellValue(i, table.findColumn(0, "RA Fee ID")));
				fee.setOrdNumber(table.getCellValue(i, table.findColumn(0, "Order")));
				fee.setRevenueLocation(table.getCellValue(i, table.findColumn(0, "Revenue Location")));
				fee.setTranType(table.getCellValue(i, table.findColumn(0, "Transaction Type")));
				
				fee.setMarinaRateType((table.findColumn(0, "Marina Rate Type")>0) ? (table.getCellValue(i, table.findColumn(0, "Marina Rate Type"))):StringUtil.EMPTY);
				fee.setPrice(table.getCellValue(i, table.findColumn(0, "Price")));
				fee.setRaFeeScheduleID(table.getCellValue(i, table.findColumn(0, "RA Fee Schedule ID")));

				records.add(fee);			
			}

		}while(goNext());
		Browser.unregister(objs);
		
		return records;
	}
	
	/**
	 * If "Next" button is available, click it
	 *
	 */
	public boolean goNext() {
		IHtmlObject[] pageingBar = browser.getHtmlObject(".class", "Html.TABLE", ".className", "pagingBar");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next", pageingBar[0]);
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		ajax.waitLoading();
		Browser.unregister(pageingBar);
		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	public void selectTransType(String transType)
	{
		browser.selectDropdownList(".id", "rafee_transaction_type", transType);
	}
	
	public void selectTransType(int index)
	{
		browser.selectDropdownList(".id", "rafee_transaction_type", index);
	}
	
	public List<String> getAllColNamesOfRAFeeTable() {
		List<String> cols = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "RAFeeScheduleSearchResultGrid_LIST");
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't RA fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		cols = table.getRowValues(0);		
		Browser.unregister(objs);
		return cols;
	}
	
	public List<String> getRAFeeStatus(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^RA Fee ID.*", false));
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't RA fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> statuses = table.getColumnValues(getColNum("Status"));
		Browser.unregister(objs);
		return statuses;
	}
	
	public String getMessage(){
		return browser.getObjectText(".id", new RegularExpression("msg.common.search.nomatchesfound", false));
	}
}
