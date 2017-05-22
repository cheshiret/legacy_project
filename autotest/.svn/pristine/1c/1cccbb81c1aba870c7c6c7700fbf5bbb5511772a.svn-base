/*
 * Created on Jan 5, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData.searchCriteria;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;



/**
 * @author Ssong
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrFeePenaltyMainPage  extends FinanceManagerPage{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrFeePenaltyMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrFeePenaltyMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrFeePenaltyMainPage getInstance()
	{
		if (null == _instance) {
			_instance = new FinMgrFeePenaltyMainPage();
		}

		return _instance;
	}

	/** Determine if the Fee Penalty Main page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("FeePenaltyScheduleSearchUI",false));//added by Nicole, 13 Nov, 2012 for 3.03
	}
	
	/** Click on Active link. */
	public void clickActive() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Activate",false));
	}

	/** Click on Deactivate link. */
	public void clickDeactive() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Deactivate",false));
	}

	/** Click on Go link. */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("^(Go|Search)$",false));
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Add New",false));
	}
	
	/**
	 * Select search type.
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.searchBy", searchType);
	}
	
	public void selectSearchType(int index) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.searchBy", index);
	}

	
	/**
	 * Fill in search value.
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", "FeePenaltySearchReq.value", value);
	}

	/**
	 * Select date type.
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.dateBy", type);
	}

	/** Deselect date type. */
	public void deselectDateType() {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.dateBy", 0);
	}

	/**
	 * Fill in search from date.
	 * @param date - from date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "FeePenaltySearchReq.start_ForDisplay", date);
	}

	/**
	 * Fill in search end date.
	 * @param date - end date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "FeePenaltySearchReq.end_ForDisplay", date);
	}

	/**
	 * Select active or inactive status.
	 * @param type
	 */
	public void selectShowStatus(String type) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.feeSchdStatus", type);
	}

	/** Deselect show stasue. */
	public void deselectShowStatus() {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.feeSchdStatus", 0);
	}

	/**
	 * Select product category.
	 * @param type
	 */
	public void selectProductCategory(String type) {
		browser.selectDropdownList(".id", new RegularExpression("FeePenaltySearchReq-\\d+\\.prdGrpCat",false), type);
		ajax.waitLoading();
		this.waitLoading();
	}

	/** Deselect product category. */
	public void deselectProductCategory() {
		browser.selectDropdownList(".id", new RegularExpression("FeePenaltySearchReq-\\d+\\.prdGrpCat",false), 0);
		ajax.waitLoading();
		this.waitLoading();
	}

	/**
	 * Select fee type.
	 * @param type - fee type
	 */
	public void selectFeeType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("FeePenaltySearchReq-\\d+\\.feeType",false), type);
	}

	/** Deselect fee type. */
	public void deselectFeeType() {
		browser.selectDropdownList(".id", new RegularExpression("FeePenaltySearchReq-\\d+\\.feeType",false), 0);
	}
	/**
	 * Select unit type.
	 * @param type - unit type
	 */
	public void selectUnitType(String type) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.penaltyUnitType", type);
	}

	/** Deselect unit type. */
	public void deselectUnitType() {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.penaltyUnitType", 0);
	}

	/**
	 * Select ticket category.
	 * @param type - ticket category
	 */
	public void selectTicketCategory(String type) {
		browser.selectDropdownList(".id", "ticket_cat_id", type);
	}

	/** Deselect ticket category.. */
	public void deselectTicketCategory() {
		browser.selectDropdownList(".id", "ticket_cat_id", 0);
	}

	/**
	 * Select permit type.
	 * @param type - permit type
	 */
	public void selectPermitType(String type) {
		browser.selectDropdownList(".id", "permit_type_id", type);
	}

	/** Deselect permit type. */
	public void deselectPermitType() {
		browser.selectDropdownList(".id", "permit_type_id", 0);
	}
	/**
	 * Select transaction type.
	 * @param type - transaction type
	 */
	public void selectTransType(String type) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.transactionTypeID", type);
	}

	/** Deselect transaction type. */
	public void deselectTransType() {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.transactionTypeID", 0);
	}

	/**
	 * Select transaction occ type.
	 * @param type - occ type
	 */
	public void selectTransOcc(String type) {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.transactionOccurrenceID", type);
	}
	
	/** Deselect transaction occ type. */
	public void deselectTransOcc() {
		browser.selectDropdownList(".id", "FeePenaltySearchReq.transactionOccurrenceID", 0);

	}
	
	/**
	 * Select marina rate type.
	 * @param type - type
	 */
	public void selectMarinaRateType(String type) {
		if(browser.checkHtmlObjectExists(".id", "SlipFeePenaltySearchRequest.marinaRateType"))
		{
			browser.selectDropdownList(".id", "SlipFeePenaltySearchRequest.marinaRateType", type);
		}else{
			logger.error("Cannot found marina rate type dropdown list!!!");
		}
	}
	
	/** Deselect marina rate type. */
	public void deselectMarinaRateType() {
		if(browser.checkHtmlObjectExists(".id", "SlipFeePenaltySearchRequest.marinaRateType"))
		{
			browser.selectDropdownList(".id", "SlipFeePenaltySearchRequest.marinaRateType", 0);
		}else{
			logger.error("Cannot found marina rate type dropdown list!!!");
		}
	}
	
	/**
	 * This method used to search fee penalty by id
	 * @param feeSchID
	 */
	public void searchByFeePenaltyId(String penaltyID)
	{
	  	selectSearchType("Penalty Schedule ID");
	  	setSearchValue(penaltyID);
	  	clickGo();
	  	ajax.waitLoading();
	  	waitLoading();
	}

	/**
	 * go to fee penalty's detail page by given id.
	 * @param penaltyID - fee penalty id
	 */
	public void gotoFeePenaltyDetailPg(String penaltyID) {
		browser.clickGuiObject(".class", "Html.A", ".text", penaltyID);
	}
	/**
	 * This method used to clear all search criteria
	 *
	 */
	public void searchFeePenalty(searchCriteria search)
	{
		this.clearAllSearchCriteria();
		this.setSearchCriteria(search);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setSearchCriteria(searchCriteria search)
	{
		
		
		if(!StringUtil.isEmpty(search.searchType))
		{
			this.selectSearchType(search.searchType);
		}
		
		if(!StringUtil.isEmpty(search.searchValue))
		{
			this.setSearchValue(search.searchValue);
		}
		
		if(!StringUtil.isEmpty(search.dateType))
		{
			this.selectDateType(search.dateType);
		}
		
		if(!StringUtil.isEmpty(search.dateFrom))
		{
			this.setFromDate(search.dateFrom);
		}
		if(!StringUtil.isEmpty(search.dateTo))
		{
			this.setToDate(search.dateTo);
		}
		
		if(!StringUtil.isEmpty(search.showStatus))
		{
			this.selectShowStatus(search.showStatus);
		}
		
		if(!StringUtil.isEmpty(search.prdCategory))
		{
			this.selectProductCategory(search.prdCategory);
		}
		if(!StringUtil.isEmpty(search.feeType))
		{
			this.selectFeeType(search.feeType);
		}
		
		if(!StringUtil.isEmpty(search.units))
		{
			this.selectUnitType(search.units);
		}
		if(!StringUtil.isEmpty(search.transType))
		{
			this.selectTransType(search.transType);
		}
		if(!StringUtil.isEmpty(search.transOccurrence))
		{
			this.selectTransOcc(search.transOccurrence);
		}
		if(!StringUtil.isEmpty(search.marinaRateType))
		{
			this.selectMarinaRateType(search.marinaRateType);
		}		
	}
	
	public void clearAllSearchCriteria()
	{
		selectSearchType(0);
		setSearchValue("");
		deselectDateType();
		setFromDate("");
		setToDate("");
		deselectShowStatus();
		deselectProductCategory();
		deselectFeeType();
		deselectUnitType();
		deselectTicketCategory();
		deselectPermitType();
		deselectTransType();
		deselectTransOcc();
		deselectMarinaRateType();
	}
	
	/**
	 * This method used to search Fee Penalty without input any criteria
	 *
	 */
	public void searchPenaltyWithoutAnyCriteria()
	{
	  	logger.info("Search Penalty Without Any Criteria.");
	  	
	  	clearAllSearchCriteria();
	  	clickGo();
	  	waitLoading();
	  	RegularExpression rex = new RegularExpression( "^Fee Schedule ID( )?Active( )?Location.*", false );
	  	IHtmlObject[]  objs = browser.getTableTestObject(".id","FeePenaltiesSearchResultGrid_LIST",".text",rex);
	  	IHtmlTable tableGrid = (IHtmlTable)objs[0];
	  	int num = tableGrid.rowCount();
	  	Browser.unregister(objs);
	  	if(num<2)
	  	{
	  	  throw new ItemNotFoundException("No Fee Penalty Found!");
	  	}
	}
	
	/**
	 * Select specific schedule check box
	 * @param schdId
	 */
	public void selectScheduleCheckBox(String schdId) {
	  	browser.selectCheckBox(".class","Html.INPUT.checkbox",".value",schdId);
	}
	
	/**
	 * This method used to change schedule status
	 * @param penaltyId
	 * @param status
	 */
	public void changeScheduleStatus(String penaltyId,String status)
	{
		FinMgrFeePenaltyMainPage finPenaltyMainPg = FinMgrFeePenaltyMainPage.getInstance();
	  	logger.info("Start to Change Fee Penalty Status to "+status);
	  	String tmpStr = "";
		String tmpID = "";
		ArrayList<String> ids = new ArrayList<String>();
	  	
	  	searchByFeePenaltyId(penaltyId);
	  	finPenaltyMainPg.waitLoading();
	  	selectScheduleCheckBox(penaltyId);
	  	if(status.equalsIgnoreCase("Active")){
	  	  clickActive();
	  	/*added by peter, for conflict fee schedule...-->start*/
	  	ajax.waitLoading();
		browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Activate");
		IHtmlObject[] errorMsg = browser.getHtmlObject(".id",
				"NOTSET");
		if (errorMsg.length > 0) {
			tmpStr = errorMsg[0].text();
			logger.info("There is an error when activate the schedule: -->"
					+ tmpStr);
			// tmpID =
			// tmpStr.substring((tmpStr.lastIndexOf("Fee Schedule with ID ="))+"Fee Schedule with ID =".length()).trim();
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(tmpStr);
			while (m.find()) {
				ids.add(m.group(0));
			}
			if (ids.size() > 1)// now we just solve that there are 2 IDs in
								// the error msg. First is current, second
								// is old.
			{
				tmpID = ids.get(ids.size() - 1);// tmpID.substring(tmpID.indexOf(" ")).trim();
				changeScheduleStatus(tmpID, "Inactive");
				changeScheduleStatus(penaltyId, "Active");
			}

		}
		/*added by peter, for conflict fee schedule...-->end*/
	  	}else if(status.equalsIgnoreCase("Inactive")){
	  	  clickDeactive();
	  	}else{
	  	  throw new ItemNotFoundException("Unkown Status "+status);
	  	}
	  	waitLoading();
	}
	
   /**
	* This method used to verify specific penalty status equals given status
	*
	*/
	public void verifyStatus(String penaltyId,String status)
	{
	  searchByFeePenaltyId(penaltyId);
	  if(status.equalsIgnoreCase("Inactive")){
	    	if(isFeePenaltyActive(penaltyId)){
	      	throw new ItemNotFoundException("Fee penalty Status is not Inactive");
	    }
	  }else if(status.equalsIgnoreCase("Active")){
	    	if(!isFeePenaltyActive(penaltyId)){
	      	throw new ItemNotFoundException("Fee penalty Status is not Active");
	    }
	  }else{
		  throw new ItemNotFoundException("Unkown Status "+status);
	  }
	}
	  
  	/**
	 * verify whether or not the special fee penalty is active status.
	 * @param penaltyId - fee penalty id
	 * @return true - active; false - inactive
	 */
	public boolean isFeePenaltyActive(String penaltyId) {
		
	  	boolean toReturn=false;
	  	String status = "";
	  	IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("FeePenaltiesSearchResultGrid_LIST", false));
	  	
	  	IHtmlTable table = (IHtmlTable)objs[0];
	  	if(table.rowCount()>0){
	  		status =table.getCellValue(1, 2).trim(); //updated by pzhu 2012/2/21
	  	}else{
	  		throw new ItemNotFoundException("Table row count is null");
	  	}

	  	Browser.unregister(objs);
	  	if(status.equalsIgnoreCase("Yes")){
	  	  	toReturn=true;
	  	}
	  	return toReturn;
	}
	
	private IHtmlObject[] getPenaltyScheduleTableObject() {
		 IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Fee (Penalty|Schedule ID) Active Location.*",false));
		 if(objs.length < 1) {
			 throw new ItemNotFoundException("Cannot find Penalty Schedule table object.");
		 }
		 
		 return objs;
	}
	
	/**
	 * Get the occurrence of the penalty
	 * @param transactionType - transaction type
	 * @return - the content of the occurrence
	 */
	public String getPenaltyOccurrence(String transactionType){
	    IHtmlObject[] objs = getPenaltyScheduleTableObject();
	    IHtmlTable table = (IHtmlTable)objs[1];
	    int colIndex = table.findColumn(0, "Transaction Type");
	    int rowIndex = table.findRow(colIndex, transactionType);
	    String occurrence = table.getCellValue(rowIndex, colIndex + 1);
//	  	String temp = objs[0].getProperty(".text").toString();	  		  	
//	  	String occurrence = temp.split(transactionType)[1].trim();
//	  	
//	  	if(occurrence.matches("\\d-\\d.*")){
//	  		occurrence = temp.split(transactionType)[1].trim().split(" ")[0];
//	  	}else {
//	  	  	int length = occurrence.split(" ").length;
//	  	    String account = occurrence.split(" ")[length-1].trim();
//	  	    occurrence = occurrence.split(account)[0].trim();
//	  	}
	  	
	  	Browser.unregister(objs);
	  	return occurrence;
	}
	
	public String getPenaltyInfo(){
		IHtmlObject[] objs = getPenaltyScheduleTableObject();
	    String temp = objs[1].getProperty(".text").toString();
	    
	    Browser.unregister(objs);
	    return temp;
	}
	public void gotoFeeSchDetailPg(String feeSchID) {
		browser.clickGuiObject(".class", "Html.A", ".text", feeSchID);
	}	
	public String getMsg()
	{
		String error = "";
		IHtmlObject[] errorMsg = browser.getHtmlObject(".id",	"NOTSET");
		if(errorMsg.length>0)
		{
			error = errorMsg[0].text();
		}
		Browser.unregister(errorMsg);
		return error;
	}
	/*
	 * @param: type "Slip", "Site"....
	 * */
	
	public List<FeePenaltyData> getAllRecordsOnPage(String type) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<FeePenaltyData> records = new ArrayList<FeePenaltyData>();
		int rows;
		int columns;
		
		FeePenaltyData fee;

		do{

		objs = browser.getTableTestObject(".id", new RegularExpression("^(SlipFeePenaltiesSearchResultGrid_LIST|FeePenaltiesSearchResultGrid_LIST)$", false));
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find fee penalty table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrFeePenaltyMainPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			fee = new FeePenaltyData();
			
			fee.id = table.getCellValue(i, table.findColumn(0, "Fee Schedule ID"));
			fee.activeStatus = table.getCellValue(i, table.findColumn(0, "Active"));
			fee.location = table.getCellValue(i, table.findColumn(0, "Location"));
			if(type.equalsIgnoreCase("Slip")){
				fee.dock = table.getCellValue(i, table.findColumn(0, "Dock/Area"));
				fee.marinaRateType = table.getCellValue(i, table.findColumn(0, "Marina Rate Type"));
			}
			if(type.equalsIgnoreCase("Site")){
				fee.minimumUnitOfStay = table.getCellValue(i, table.findColumn(0, "Min Unit of Stay"));
				fee.minimumNumOfDayBeforeArrivalDay = table.getCellValue(i, table.findColumn(0, "Min Number of Days before Arrival Date"));
			}
			fee.productCategory = table.getCellValue(i, table.findColumn(0, "Product Category"));
			fee.product = table.getCellValue(i, table.findColumn(0, "Product or Product Group"));
			fee.feeType = table.getCellValue(i, table.findColumn(0, "Fee Type"));
			fee.effectDate = table.getCellValue(i, table.findColumn(0, "Effective(Start) Date"));
			fee.startInv = table.getCellValue(i, table.findColumn(0, "Inventory Start Date"));
			fee.endInv = table.getCellValue(i, table.findColumn(0, "Inventory End Date"));
			fee.value = table.getCellValue(i, table.findColumn(0, "Value"));
			fee.units = table.getCellValue(i, table.findColumn(0, "Unit"));
			fee.tranType = table.getCellValue(i, table.findColumn(0, "Transaction Type"));
			fee.tranOccur = table.getCellValue(i, table.findColumn(0, "Transaction Occurrence"));
			fee.accountCode = table.getCellValue(i, table.findColumn(0, "Account"));
			records.add(fee);			
		}
				
		}while(gotoNext());
		
		Browser.unregister(objs);
		return records;
	}
	
		/**
		 * Check whether gotonext button exist, if exit,click it.
		 * @return
		 */
	public boolean gotoNext() {
		IHtmlObject[] pageBar = browser.getHtmlObject(".class", "Html.TABLE",
				".className", "pagingBar");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next", pageBar[0]);
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
			ajax.waitLoading();
		}

		Browser.unregister(pageBar);
		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	public List<String> getAllColNamesOfPenaltyFeeTable() {
		List<String> cols = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "FeePenaltiesSearchResultGrid_LIST");
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't Penalty fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		cols = table.getRowValues(0);		
		Browser.unregister(objs);
		return cols;
	}
	
	public String getFirstFeeScheduleID(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?FeePenaltiesSearchResultGrid_LIST",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can not find the table...");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		String id=table.getCellValue(1, 1);
		
		return id;
	}
	
	public void verifyColNameDisplay(String colName) {
		logger.info("Verify column name "+colName+" display on page");
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?FeePenaltiesSearchResultGrid_LIST", false));
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, colName);
		Browser.unregister(objs);
		if(col<0) {
			throw new ItemNotFoundException("Could not find column name "+colName);
		}
		logger.info("---Verify Column name "+colName+" displayed on page.");
	}
	
	public void verifyFeeScheColValue(String scheId, String colName, String value) {
		logger.info("Verify fee schedule "+scheId+" column "+colName+" value on page");
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?FeePenaltiesSearchResultGrid_LIST", false));
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, "Fee Schedule ID"), scheId);
		int col = table.findColumn(0, colName);
		String valueUI = table.getCellValue(row, col);
		Browser.unregister(objs);
		if(StringUtil.isEmpty(value)) {
			if(StringUtil.notEmpty(valueUI)){
				throw new ErrorOnPageException("Fee schedule "+scheId+" column "+colName+" value was wrong.", value, valueUI);
			}
		}else if(!valueUI.equalsIgnoreCase(value)) {
			throw new ErrorOnPageException("Fee schedule "+scheId+" column "+colName+" value was wrong.", value, valueUI);
		}
		logger.info("---Verify fee schedule "+scheId+" column "+colName+" value successfully.");
	}
}
