package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdjustmentInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class FinMgrReconAdjustmentPage extends FinanceManagerPage{

	protected FinMgrReconAdjustmentPage() {
	}

	private static FinMgrReconAdjustmentPage _instance = null;

	public static FinMgrReconAdjustmentPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrReconAdjustmentPage();
		return _instance;
	}

	/** Determine if the reconcile adjustment page exists */
	public boolean exists() {
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\( \"DepositReconAdjustments.do\", \"SearchNonCashAdjustments\", \"DepositsReconcileWorker\".*",false);
		return browser.checkHtmlObjectExists(".text",new RegularExpression("Search", false), ".href",rex);
	}
	
	public void selectAdjustmentRadio(){
		browser.selectRadioButton(".id", "_actionNonCashUnReconAdjustment_");
	}
	
	public int getAdjustmentRadioNum(){
		IHtmlObject[] objs = browser.getRadioButton(".id", "_actionNonCashUnReconAdjustment_");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	public boolean isReconcAdjustmentButtonEnable(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Reconcile Non-Cash Depositable Adjustment without Matching");
	}
	
	public void clickReconcAdjustmentButton(){
		browser.clickGuiObject(".class","Html.A",".text","Reconcile Non-Cash Depositable Adjustment without Matching");
	}
	
	/**
	 * Get column Num for specific Column Name
	 * @param colName
	 * @return column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(" ?Adjustment ID Amount Adjustment Type.*", false);
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
	 * This method used to verify given column value are all given value
	 * @param colName column name
	 * @param value column value
	 */
	public void verifyAdjustmentList(String colName,String value){
		logger.info("Verify "+colName+" Column are all "+value);
		
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(" ?Adjustment ID Amount Adjustment Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for(int i=1;i<tableGrid.rowCount();i++){
				String cellValue = tableGrid.getCellValue(i, colNum);
				if(!cellValue.equalsIgnoreCase(value)){
					throw new ErrorOnPageException("Cell Value is not equals given "+value);
				}
			}
		}else{
			throw new ErrorOnPageException("Parse Table Failed.");
		}
		Browser.unregister(objs);
	}
	
	public boolean isRadioButtonEnable(){
		IHtmlObject[] objs=browser.getRadioButton(".id","_actionNonCashUnReconAdjustment_");
		if(objs.length<1){
			throw new ItemNotFoundException("Can't found the radiobutton");
		}
		
		boolean flag=Boolean.parseBoolean(objs[0].getProperty(".disabled"));
		Browser.unregister(objs);
		return !flag;
	}
	/**
	 * This method used to retrieve the first adjustment detail info
	 * @return adjustment info
	 */
	public AdjustmentInfo getAdjustmentInfo(){
		AdjustmentInfo adj = new AdjustmentInfo();
		RegularExpression rex = new RegularExpression(" ?Adjustment ID Amount Adjustment Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		adj.adjustmentID = tableGrid.getCellValue(1, 1);
		adj.adjustmentAmount = tableGrid.getCellValue(1, 2);
		adj.adjustType = tableGrid.getCellValue(1, 3);
		adj.adjustIn = tableGrid.getCellValue(1, 4);
		adj.adjustDate = DateFunctions.formatDate(tableGrid.getCellValue(1, 5));
		adj.reconcileDate = tableGrid.getCellValue(1, 6);
		adj.manualFlag = tableGrid.getCellValue(1, 7);
		adj.adjustUser = tableGrid.getCellValue(1, 8);
		adj.location = tableGrid.getCellValue(1, 9);

		Browser.unregister(objs);
		return adj;
	}
	
	/**
	 * This method used to verify adjustment detail info
	 * @param adjust
	 */
	public void verifyAdjustmentDetail(AdjustmentInfo adjust){
		logger.info("Verify Adjustment Detail Info");
		
		AdjustmentInfo adj = this.getAdjustmentInfo();
		if(!adj.adjustmentID.matches("\\d+")){
			throw new ErrorOnPageException("Adjustment ID "+adj.adjustmentID+" not correct.");
		}
		if(!adj.adjustmentAmount.equalsIgnoreCase(adjust.adjustmentAmount)){
			throw new ErrorOnPageException("Adjustment Amount "+adj.adjustmentAmount+" not correct.");
		}
		if(!adj.adjustType.equalsIgnoreCase(adjust.adjustType)){
			throw new ErrorOnPageException("Adjustment Type "+adj.adjustType+" not correct.");
		}
		if(!adj.adjustIn.equalsIgnoreCase(adjust.adjustIn)){
			throw new ErrorOnPageException("Adjusted In "+adj.adjustIn+" not correct.");
		}
		if(!adj.adjustDate.equalsIgnoreCase(adjust.adjustDate)){
			throw new ErrorOnPageException("Adjustment Date "+adj.adjustDate+" not correct.");
		}
		if(!adj.manualFlag.equalsIgnoreCase(adjust.manualFlag)){
			throw new ErrorOnPageException("Adjustment Manually Rec Flag "+adj.manualFlag+" not correct.");
		}
		if(!adj.adjustUser.equalsIgnoreCase(adjust.adjustUser)){
			throw new ErrorOnPageException("Adjusting User "+adj.adjustUser+" not correct.");
		}
		if(!adj.location.equalsIgnoreCase(adjust.location)){
			throw new ErrorOnPageException("Adjustment Location "+adj.location+" not correct.");
		}
	}
	
	public void selectSearchType(String searchType){
		browser.selectDropdownList(".id", "_searchtype", searchType);
	}
	
	public String getSearchType(){
		return browser.getDropdownListValue(".id", "_searchtype",0);
	}
	
	public void setSearchValue(String value){
		browser.setTextField(".id", "_searchinput", value);
	}
	
	public String getSearchValue(){
		return browser.getTextFieldValue(".id", "_searchinput");
	}
	
	public void setFromDate(String fromDate){
		browser.setTextField(".id", "_searchstartdate_ForDisplay", fromDate);
	}
	
	public String getFromDate(){
		return DateFunctions.formatDate(browser.getTextFieldValue(".id", "_searchstartdate_ForDisplay"));
	}
	
	public void setToDate(String toDate){
		browser.setTextField(".id", "_searchenddate_ForDisplay", toDate);
	}
	
	public String getToDate(){
		return DateFunctions.formatDate(browser.getTextFieldValue(".id", "_searchenddate_ForDisplay"));
	}
	
	public void selectAdjustmentType(String adjustType){
		browser.selectDropdownList(".id", "_searchpaymenttype", adjustType);
	}
	
	public String getAdjustmentType(){
		return browser.getDropdownListValue(".id", "_searchpaymenttype",0);
	}
	
	public void selectAdjustIn(String adjustIn){
		browser.selectDropdownList(".id", "_searchAdjustmentCode", adjustIn);
	}
	
	public String getAdjustIn(){
		return browser.getDropdownListValue(".id", "_searchAdjustmentCode",0);
	}
	
	public List<String> getAdjustedIn(){
		List<String> adjustedIn = browser.getDropdownElements(".id", "_searchAdjustmentCode");
		return adjustedIn;
	}
	
	public void selectAdjustmentsStatus(String status){
		if(status==null||status.equals("")){
			browser.selectDropdownList(".id", "_searchReconciled",0);
		}else{
			browser.selectDropdownList(".id", "_searchReconciled", status);
		}
	}
	
	public String getAdjustmentStatus(){
		return browser.getDropdownListValue(".id", "_searchReconciled",0);
	}
	
	public List<String> getAdjustmentsStatus(){
		List<String> adjustsStatus = browser.getDropdownElements(".id", "_searchReconciled");
		return adjustsStatus;
	}
	
	public void selectManuallyReconFlag(String flag){
		browser.selectDropdownList(".id", "_searchmanualrecon", flag);
	}
	
	public String getManuallyReconFlag(){
		return browser.getDropdownListValue(".id", "_searchmanualrecon",0);
	}
	
	public List<String> getReconcileFlag(){
		List<String> manualFlags = browser.getDropdownElements(".id", "_searchmanualrecon");
		return manualFlags;
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	public void clickColumnName(String columnName){
		browser.clickGuiObject(".class","Html.A",".text",columnName);
	}
	
	public List<String> getColumnValue(String columnName){
		List<String> values = new ArrayList<String>();
		RegularExpression rex = new RegularExpression(" ?Adjustment ID Amount Adjustment Type.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int colCounts = tableGrid.columnCount();
		for (int i = 0; i < colCounts; i++) {
			if (tableGrid.getCellValue(0, i).toString().trim().equalsIgnoreCase(columnName.trim())) {
				for (int j = 1; j < tableGrid.rowCount(); j++) {
					values.add(tableGrid.getCellValue(j, i));
			    }
			}
		}
		Browser.unregister(objs);
	    return values;
	}
	
	public void searchBy(String searchType,String searchValue){
		logger.info("Search Adjustment by "+searchType);
		
		this.selectSearchType(searchType);
		this.setSearchValue(searchValue);
		this.clickGo();
		this.waitLoading();
	}
	
	public void setupSearchCriteria(AdjustmentInfo adjust){
		String log = "Search Adjustment by ";
		
		if(adjust.searchType!=null&&!adjust.searchType.equals("")){
			if(adjust.searchType.equalsIgnoreCase("Adjustment ID")){
				selectSearchType("Adjustment ID");
				setSearchValue(adjust.adjustmentID);
			}else if(adjust.searchType.equalsIgnoreCase("Adjusting User")){
				selectSearchType("Adjusting User");
				setSearchValue(adjust.adjustUser);
			}else if(adjust.searchType.equalsIgnoreCase("Adjustment Location")){
				selectSearchType("Adjustment Location");
				setSearchValue(adjust.location);
			}else{
				throw new ErrorOnPageException("Unknown Search Type.");
			}
			log +=adjust.searchType;
		}
		if(adjust.startDate!=null&&!adjust.startDate.equals("")){
			setFromDate(adjust.startDate);
			log +=" and From "+adjust.startDate;
		}
		if(adjust.endDate!=null&&!adjust.endDate.equals("")){
			setToDate(adjust.endDate);
			log +=" and To "+adjust.endDate;
		}
		if(adjust.adjustType!=null&&!adjust.adjustType.equals("")){
			selectAdjustmentType(adjust.adjustType);
			log +=" and Adjust Type is "+adjust.adjustType;
		}
		if(adjust.adjustIn!=null&&!adjust.adjustIn.equals("")){
			selectAdjustIn(adjust.adjustIn);
			log +=" and Adjusted In Is "+adjust.adjustIn;
		}
		if(adjust.adjustmentStatus!=null&&!adjust.adjustmentStatus.equals("")){
			selectAdjustmentsStatus(adjust.adjustmentStatus);
			log +=" and Adjustment status Is "+adjust.adjustmentStatus;
		}
		if(adjust.manualFlag!=null&&!adjust.manualFlag.equals("")){
			selectManuallyReconFlag(adjust.manualFlag);
			log +=" and Manual Flag Is "+adjust.manualFlag;
		}
		logger.info(log);
	}
	/**
	 * This method used to search adjustment by different search criteria
	 * @param adjust
	 */
	public void searchAdjustment(AdjustmentInfo adjust){
		this.setupSearchCriteria(adjust);
		this.clickGo();
		this.waitLoading();
	}
	
	/**
	 * This method used to search adjustment by adjustments status
	 * @param status
	 */
	public void searchAdjustmentsByStatus(String status){
		logger.info("Search Adjustments By Adjustments Status "+status);
		
		selectAdjustmentsStatus(status);
		clickGo();
		waitLoading();
	}
	/**
	 * This method used to verify search criteria correct,not affected by other criteria
	 * @param adjust
	 */
	public void verifySearchCriteriaCorrect(AdjustmentInfo adjust){
		logger.info("Verify Search Criteria Correct.");
		
		if(!getSearchType().equalsIgnoreCase(adjust.searchType)){
			throw new ErrorOnPageException("Search Type Not Correct.");
		}
		if(!getSearchValue().equalsIgnoreCase(adjust.searchValue)){
			throw new ErrorOnPageException("Search Value Not Correct.");
		}
		if(DateFunctions.diffBetween(getFromDate(),adjust.startDate)!=0){
			throw new ErrorOnPageException("Adjustment Start Date Not Correct.");
		}
		if(DateFunctions.diffBetween(getToDate(),adjust.endDate)!=0){
			throw new ErrorOnPageException("Adjustment End Date Not Correct.");
		}
		if(!getAdjustmentType().equalsIgnoreCase(adjust.adjustType)){
			throw new ErrorOnPageException("Adjustment Type Criteria Not Correct.");
		}
		if(!getAdjustIn().equalsIgnoreCase(adjust.adjustIn)){
			throw new ErrorOnPageException("Adjusted In Criteria Not Correct.");
		}
		if(!getAdjustmentStatus().equalsIgnoreCase(adjust.adjustmentStatus)){
			throw new ErrorOnPageException("Adjustments Status Criteria Not Correct.");
		}
		if(!getManuallyReconFlag().equalsIgnoreCase(adjust.manualFlag)){
			throw new ErrorOnPageException("Manually Reconciled Criteria Not Correct.");
		}
	}
	
	public void verifySearchResultCorrect(AdjustmentInfo adjust){
		logger.info("Verify Search Result Correct.");
		
		if(adjust.adjustType!=null&&!adjust.adjustType.equals("")){
			if(adjust.adjustType.equalsIgnoreCase("PCHK")){
				adjust.adjustType="Personal Check Adjustment";
			}
			verifyAdjustmentList("Adjustment Type", adjust.adjustType);
		}
		if(adjust.adjustIn!=null&&!adjust.adjustIn.equals("")){
			verifyAdjustmentList("Adjusted In", adjust.adjustIn);
		}
		if(adjust.manualFlag!=null&&!adjust.manualFlag.equals("")){
			verifyAdjustmentList("Manually Rec", adjust.manualFlag);
		}
	}

	/**
	 * This method used to get adjustment info record number
	 * @return
	 */
	public int getAdjustmentRowNum(){
		RegularExpression rex = new RegularExpression(" ?Adjustment ID Amount Adjustment Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int num = tableGrid.rowCount();
		Browser.unregister(objs);
		return num;
	}
	
	public void verifyDateInGivenRange(String fromDate,String toDate){
		 RegularExpression rex = new RegularExpression(" ?Adjustment ID Amount Adjustment Type.*", false);
		 IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
    	 IHtmlTable tableGrid = (IHtmlTable) objs[0];
	     for (int i = 1; i < tableGrid.rowCount(); i++) {
	       if (null != tableGrid.getCellValue(i, 5)) {
				String date = DateFunctions.formatDate(tableGrid.getCellValue(i, 5).toString(),"M/d/yyyy");
				if(!fromDate.equals("")&&!toDate.equals("")){
					int value = DateFunctions.compareDates(fromDate,date)+DateFunctions.compareDates(toDate,date);
					if(value==2||value==-2)
					{
				  		throw new ErrorOnPageException("Date "+date+" is not in the given date range!");
					}
				}else if(!fromDate.equals("")){
					int value = DateFunctions.compareDates(fromDate,date);
					if(value==1)
					{
				  		throw new ErrorOnPageException("Date "+date+" should greater than from date "+fromDate);
					}
				}else if(!toDate.equals("")){
					int value = DateFunctions.compareDates(date,toDate);
					if(value==1)
					{
				  		throw new ErrorOnPageException("To Date "+toDate+" should greater than "+date);
					}
				}
	       }
	     }
	     Browser.unregister(objs);
	 }
	
	public void verifyResultSortCorrect(boolean isAscend,String colName){
		logger.info("Verify Adjustment List Sorting Correct.");
		
		List<String> values = this.getColumnValue(colName);
		if(values!=null&&values.size()>1){
			int num = ((Character)values.get(0).charAt(0)).compareTo(values.get(1).charAt(0));
			if(isAscend){
				if(num>0){
					throw new ErrorOnPageException(colName+" Column is not Sort Ascending.");
				}
			}else{
				if(num<0){
					throw new ErrorOnPageException(colName+" Column is not Sort Descending.");
				}
			}
		}
	}
}
