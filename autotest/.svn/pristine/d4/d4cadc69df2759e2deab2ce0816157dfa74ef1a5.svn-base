/*
 * Created on Aug 13, 2009
 *
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.tax;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author CGuo
 *
 */
public class FinMgrTaxSchedulePage extends FinanceManagerPage {
	private static FinMgrTaxSchedulePage _instance = null;

	public static FinMgrTaxSchedulePage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrTaxSchedulePage();
		}

		return _instance;
	}

	protected FinMgrTaxSchedulePage() {
	}
    /** to knowledge if this page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Tax Schedule ID", false));
	}
	
	private String tableID = "(SlipTaxScheduleListGrid_LIST)|taxScheduleListView_LIST";
	
	/**
	 * Select tax schedule search type.
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.searchBy", false), searchType, true);
		ajax.waitLoading();
	}

	public List<String> getSearchTypeOptions() {
		return browser.getDropdownElements(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.searchBy", false));
	}
	
	/**
	 * Fill in tax search value.
	 * @param value
	 */
	public void setTaxSchSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.searchByValue", false), value, true);
	}

	public List<String> getDateTypeOptions() {
		return browser.getDropdownElements(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.dateType", false));
	}
	
	public void selectDateTypeOption(String dateType){
		browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.dateType", false), dateType, true);
	}
	
	/**
	 * This method used to search Schdule by Tax Name
	 * @param taxName
	 */
	public void searchSchdByTaxName(String taxName) {
	  selectSearchType("Tax Name");
	  setTaxSchSearchValue(taxName);
	  clickGo();
	  ajax.waitLoading();
	}
	
	/**
	 * This method used to search Schdule by Schdule Id
	 * @param scheduleId
	 */
	public void searchByScheduleId(String scheduleId) {
	  selectSearchType("Tax Schedule Id");
	  setTaxSchSearchValue(scheduleId);
	  clickGo();
	  ajax.waitLoading();
	  this.waitLoading();
	}
	
	/**  Select the first schedule check box */
	public void selectFirstSchdCheckBox() {
	  	browser.selectCheckBox(".id", new RegularExpression("(TaxScheduleView.id)|(GenericGrid-\\d+.selectedItems)", false), true);
	  	ajax.waitLoading();
	}
	
	/** Click Activate Button */
	public void clickActivate() {
	  	browser.clickGuiObject(".class","Html.A",".text","Activate");
	  	ajax.waitLoading();
	}
	
	/** Click Deactivate Button */
	public void clickDeActivate() {
	  	browser.clickGuiObject(".class","Html.A",".text","Deactivate");
	  	ajax.waitLoading();
	}
	
	/** Click Add New Button */
	public void clickAddNew() {
	  	browser.clickGuiObject(".class","Html.A",".text","Add New");
	}
	
	/**
	 * This method used to get the first schedule ID
	 * @return schedule ID
	 */
	public String getFirstScheduleId() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("((SlipT)|(ListT)|(t))axScheduleList((Grid)|View)_LIST",false));
		if(objs.length > 0) {
			IHtmlTable table = (IHtmlTable)objs[0];
			return table.getCellValue(1, 1);
		 }else {
		  	Browser.unregister(objs);
		  	throw new ItemNotFoundException("Can Not Find Any Schedule!");
		 }
	}
	
	public boolean checkSchduleInCurrentPage(String scheduleId){
		boolean exist = false;
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("((SlipT)|(ListT)|(t))axScheduleList((Grid)|View)_LIST",false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Tax schedule table is not found...");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(0, 1, scheduleId);
		if(rowNum == -1){
			logger.info("The schedule(" + scheduleId +") is not found in current page, may need to search!");
			exist = false;
		}else{
			exist = true;
		}
		Browser.unregister(objs);
		return exist;
	}
	
	public String changeTaxScheduleStatus(String scheduleId, String toStatus, boolean isOverrideExisting) {
		logger.info("Start to Change Tax Schedule Status!");
	  	String toReturn = "";
		String error = "";
	  	String dupID = "";
	  	IHtmlObject[] objs;
	  	String isActive = "No";
	  	searchByScheduleId(scheduleId);
	  	this.waitLoading();
	  	selectFirstSchdCheckBox();
	  	if(toStatus.equalsIgnoreCase("Active")) {
	  	  	clickActivate();
	  	  	ajax.waitLoading();
	  	  	objs = browser.getHtmlObject(".id", "NOTSET");
	  	  	if (objs.length > 0) {
	  	  		error = objs[0].text();//eg. "The Tax Schedule 1005606160 cannot be activated because another identical active Tax Schedule 1005606107 exists"
	  	  		if(isOverrideExisting) {
		  	  		int tmp1 = error.lastIndexOf("Tax Schedule ")+"Tax Schedule ".length();
		  	  		int tmp2 = error.lastIndexOf(" exists");
		  	  		dupID = error.substring(tmp1, tmp2);
		  	  		
		  	  		changeTaxScheduleStatus(dupID,"Inactive");
		  	  		changeTaxScheduleStatus(scheduleId,"Active");
	  	  		} else toReturn = error;
	  	  	}
	  	  	isActive = "Yes";
	  	}else if(toStatus.equalsIgnoreCase("Inactive")) {
	  	  	clickDeActivate();
	  	  	ajax.waitLoading();
	  	  	isActive = "No";
	  	}
	    
	    if(isOverrideExisting) {
		  	searchByScheduleId(scheduleId);
			verifyTaxScheduleInfo("Active",isActive);//updated by pzhu
		  	String log = isActive.equalsIgnoreCase("Yes") ? "Activate": "Deactivate";
		  	logger.info(log +" Tax Schedule "+scheduleId+" Success!");
	    }
	    
	    return toReturn;
	}
	
	/**
	 * verify tax schedule status
	 * @param id
	 * @param status - Yes or No
	 */
	public void verifyTaxScheduleStatus(String id, String status) {
		searchByScheduleId(id);
		verifyTaxScheduleInfo("Active", status);
	}
	
	public void changeTaxSchedulesStatus(String taxName, boolean toActivate, String... ids) {
		if(!StringUtil.isEmpty(taxName)) {
			selectSearchType("Tax Name");
			setTaxSchSearchValue(taxName);
			clickGo();
			ajax.waitLoading();
		}
		
		for(String id : ids) {
			if(!checkSchduleInCurrentPage(id)){
				PagingComponent com = new PagingComponent();
				com.clickLast();
				ajax.waitLoading();
				this.waitLoading();
			}
			selectTaxScheduleCheckBox(id);
		}
		
		if(toActivate) {
			clickActivate();
			ajax.waitLoading();
			this.waitLoading();
			String errorMsg = this.getErrorMessage();
			if(!StringUtil.isEmpty(errorMsg)) {
				String existingIDs[] = RegularExpression.getMatches(errorMsg, "\\d+");
				for(int i = 1; i < existingIDs.length; i ++) {
					this.changeTaxScheduleStatus(existingIDs[i], OrmsConstants.INACTIVE_STATUS);
				}
				this.changeTaxSchedulesStatus(taxName, toActivate, ids);
			}
		} else {
			clickDeActivate();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	public void selectTaxScheduleCheckBox(String id) {
		browser.selectCheckBox(".value", id);
	}
	
	/**
	 * This method used to Activate or deactivate tax schedule
	 * @param scheduleId
	 * @param status
	 */
	public void changeTaxScheduleStatus(String scheduleId, String status) {
		this.changeTaxScheduleStatus(scheduleId, status, true);
	}
	
	/**
	 * verify whether or not the special tax schedule is active status.
	 * @param taxScheduleID - tax schedule ID
	 * @return true - active; false - inactive
	 */
	public boolean isTaxScheduleActive(String taxScheduleID) {
	  	boolean toReturn=false;
	  	String status  = "";
	  	
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".text",
	    	new RegularExpression("^Tax Schedule ID Active.*",false));
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	    if(tableGrid.rowCount() > 0 ) {
	    	status = tableGrid.getCellValue(1, 2);
	    }
	  	Browser.unregister(objs);
	  	
	  	if(status.equalsIgnoreCase("Yes")){
	  	  	toReturn=true;
	  	}
	  	return toReturn;
	}
	
	/**
	 * This method used to verify list schedule data is correct
	 * @param schedule
	 */
	public void verifyTaxSchedule(ScheduleData schedule) {
	  logger.info("Start to verify Schedule Data!");
	  verifyTaxScheduleInfo("Tax Schedule ID", schedule.scheduleId);
	  verifyTaxScheduleInfo("Location", schedule.location);
	  verifyTaxScheduleInfo("Tax Name", schedule.taxName);
	  verifyTaxScheduleInfo("Fee Type", schedule.feeType);
	  verifyTaxScheduleInfo("Effective Start Date", schedule.startDate);
	  verifyTaxScheduleInfo("Effective End Date", schedule.endDate);
	}
	
	  /**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	  public void verifyTaxScheduleInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    boolean found = false;
	    IHtmlTable table;
	    RegularExpression rex = new RegularExpression(".*Tax Schedule ID Active Location.*", false );
	    IHtmlObject[] objs;
	    do {
		   objs = browser.getTableTestObject(".text", rex );
	       table  = (IHtmlTable)objs[0];
	      
	      for ( int i = 1; i < table .rowCount(); i++ ) {
	        if ( null != table.getCellValue( i, colNum ) ) {
	          if ( table.getCellValue( i, colNum ).toString().trim().equals( value.trim() ) ) {
	        	  found = true;
	            
	          }
	        }
	      }
	      
	    } while ((gotoNext())&&(!found));// );
	    if(!found)
	    {
	    	logger.error( "'verifyTaxScheduleInfo'-->>Tax Schedule Info can not be found! In method " );
            throw new ItemNotFoundException( "Tax Schedule Info can not be found! " + value );	
	    }
	    Browser.unregister( objs );
	  }
	  
	/** Click Go link. */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
		ajax.waitLoading();
	}
	
	/** Click Taxe tab goto tax main page  */
	public void clickTaxesTab(){
	  	browser.clickGuiObject(".class","Html.SPAN",".text","Taxes");
	}
	
	/**
	 * Wait page to load by waiting schedule id link object.
	 * @param scheduleID
	 */
	public void searchTaxSchWaitExists(String scheduleID) {
		browser.searchObjectWaitExists(".class", "Html.A", ".text", scheduleID);
	}

	/**
	 * Go to schedule's details page by given id.
	 * @param scheduleID
	 */
	public void gotoTaxSchDetails(String scheduleID) {
		browser.clickGuiObject(".class", "Html.A", ".text", scheduleID);
	}

	/**
	 * This method goes to retrieve the cell value for given column name
	 * As hard to find a unique object for waiting page load, so we check whether the record display
	 *  in first page, if not, do search and wait for the record link in second search result page
	 * @param scheduleID - tax schedule id
	 * @param cloumnName - the special column name which you want to get the cell value from
	 * @return - cell value
	 */
	public String getTaxDetailBySchID(String scheduleID, String columnName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", scheduleID);

		if (objs.length < 1) {//go to the specify tax schedule id if it didn't display at first page
			this.selectSearchType("Tax Schedule Id");
			this.setTaxSchSearchValue(scheduleID);
			this.clickGo();
			this.searchTaxSchWaitExists(scheduleID);
		}
		String cellValue = null;// value in cell to be returned

		IHtmlObject[] taxSchTable = browser.getTableTestObject(".id", new RegularExpression(tableID, false));
		IHtmlTable tableGrid = (IHtmlTable) taxSchTable[0];

		for (int row = 0; row < tableGrid.rowCount(); row++) {
			String toCompareRow = tableGrid.getCellValue(row, 1).toString();

			if (toCompareRow.equalsIgnoreCase(scheduleID)) {//get the record row number here
				for (int col = 0; col < tableGrid.columnCount(); col++) {
					String toCompareCol = tableGrid.getCellValue(0, col).toString();
					if (toCompareCol.equalsIgnoreCase(columnName)) {//compare column with retrieve the column value
						cellValue = tableGrid.getCellValue(row, col).toString();
						break;//exit for when find the specify column
					} else if (col == tableGrid.columnCount() - 1
							&& !toCompareCol.equalsIgnoreCase(columnName))
						throw new ItemNotFoundException("The specify column '"
								+ columnName + "' not found");
				}
				break;//exit for when find the specify row
			}
		}
		Browser.unregister(objs);

		return cellValue;
	}

	  /**
	   * Check whether there have next page,if have,click Next Button
	   * @return
	   */
	  public boolean gotoNext() {              
		IHtmlObject[] tops = browser.getHtmlObject(".class", "Html.TABLE", ".className", "pagingBar");
//	    HtmlObject[] objs = browser.getHtmlObject( ".href", new RegularExpression("^javascript.*",false), ".text", "Next"); //updated by pzhu...//browser.getHtmlObject( ".class", "Html.A", ".text", "Next");
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.A", ".text", "Next", tops[0]);
	    boolean toReturn = false;
	    if ( objs.length > 0 ) {
	      toReturn = true;
//	      objs[0].click();
	      ILink link = (ILink)objs[0];
	      link.click();
	    }
	    Browser.unregister( tops );
	    Browser.unregister( objs );
	    ajax.waitLoading();
	    this.waitLoading();

	    return toReturn;
	  }
	  
	 /**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	  public int getColNum( String colName ) {
	    RegularExpression rex = new RegularExpression( "Tax Schedule ID Active Location.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    if ( null != objs && objs.length > 0 ) {
	      IHtmlTable tableGrid = (IHtmlTable)objs[0];
	      int colCounts = tableGrid.columnCount();//updated by pzhu
	      for ( int i = 0; i < colCounts; i++ ) {
	        if ( tableGrid.getCellValue( 0, i ).toString().trim().equalsIgnoreCase( colName.trim() ) ) {
	          return i;
	        }
	      }
	    }
	    Browser.unregister( objs );
	    return -1;
	  }
	  
		public List<String> getEffectiveStartDateColumnValue(){
			return this.getColumnByName("Effective Start Date");
		}
		
		public List<String> geLocationColumnValue(){
			return this.getColumnByName("Location");
		}
		
		public List<String> getRateColumnValue(){
			return this.getColumnByName("Rate");
		}
		
		/**
		 * Click the tax schedule id we wanted
		 * @param TaxScheduleId 
		 */
		public void clickTaxSchId(String TaxScheduleId)
		{
		  browser.clickGuiObject(".class","Html.A",".text",TaxScheduleId);
		  ajax.waitLoading();
		}
		
		public void selectProductCategory(String category){
			browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria(-\\d+)?\\.productCategory", false), category);
		}
		
		public void selectShowStatusDropDownList(String status){
			browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.active", false), status, true);
		}
		
		public void selectFeeType(String type){
			if(StringUtil.isEmpty(type)){
				browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.feeType", false), 0, true);
			} else {
				browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.feeType", false), type, true);
			}
		}
		
		public boolean checkScheduleInfoInList(ScheduleData schedule){
			boolean exist = true;
			this.searchByScheduleId(schedule.scheduleId);
			IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip|List)taxScheduleList(Grid|View)_LIST",false));
			if(objs.length<1){
				throw new ObjectNotFoundException("Tax schedule table is not found...");
			}
			IHtmlTable table = (IHtmlTable)objs[0];
			int rowNum = table.findRow(0, 1, schedule.scheduleId);
			if(rowNum != 1){
				logger.info("The schedule(" + schedule.scheduleId +") is not found!");
				exist = false;
			}else{
				List<String> scheduleInList = table.getRowValues(rowNum);
				exist &= MiscFunctions.compareResult("Active", schedule.activeStatus, scheduleInList.get(2));
				exist &= MiscFunctions.compareResult("Location", schedule.location, scheduleInList.get(3));
				exist &= MiscFunctions.compareResult("Tax Name", schedule.taxName, scheduleInList.get(4));
				exist &= MiscFunctions.compareResult("Product Category", schedule.productCategory, scheduleInList.get(5));
				exist &= MiscFunctions.compareResult("Product Or Product Group", schedule.product.equals("All") ? schedule.productGroup : schedule.product, scheduleInList.get(6));
				exist &= MiscFunctions.compareResult("Fee Type", schedule.feeType, scheduleInList.get(7));
				exist &= MiscFunctions.compareResult("Effective Start Date", schedule.startDate, scheduleInList.get(8));
				exist &= MiscFunctions.compareResult("Effective End Date", schedule.endDate, scheduleInList.get(9));
				exist &= MiscFunctions.compareResult("Rate", Double.parseDouble(schedule.rate), Double.parseDouble(scheduleInList.get(10)));
				exist &= MiscFunctions.compareResult("Rate Type", schedule.rateType, scheduleInList.get(11));
				exist &= MiscFunctions.compareResult("Customer Type", schedule.customerType, scheduleInList.get(12));
				exist &= MiscFunctions.compareResult("Transaction Type", schedule.tranType, scheduleInList.get(13));
				if("Slip".equalsIgnoreCase(schedule.productCategory)){
					exist &= MiscFunctions.compareResult("Marina Rate Type", schedule.marinaRateType, scheduleInList.get(14));
					exist &= MiscFunctions.compareResult("Account", schedule.accountCode.replaceAll(" ", ""), scheduleInList.get(15).replaceAll(" ", ""));
				}else{
					exist &= MiscFunctions.compareResult("Account", schedule.accountCode.replaceAll(" ", ""), scheduleInList.get(14).replaceAll(" ", ""));
				}
			}
			Browser.unregister(objs);
			return exist;
		}
		
		/**
		 * get tax schedule search results from page
		 * @return
		 * @Return List<FeeScheduleData>
		 * @Throws
		 */
		public List<FeeScheduleData> getTaxSchSearchResults() {
			List<FeeScheduleData> list=new ArrayList<FeeScheduleData>();
			IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression(tableID, false));
			
			if(objs.length<1){
				throw new ObjectNotFoundException("Tax schedule table is not found...");
			}
			IHtmlTable table = (IHtmlTable)objs[0];
			int rowCount=table.rowCount();
			
			for(int i=1;i<rowCount;i++){
				FeeScheduleData fee=new FeeScheduleData();
				fee.feeSchdId=table.getCellValue(i, 1);
				fee.activeStatus=table.getCellValue(i, 2);
				fee.location=table.getCellValue(i, 3);
				fee.taxName=table.getCellValue(i, 4);
				fee.productCategory=table.getCellValue(i, 5);
				fee.prodOrProdgroup=table.getCellValue(i, 6);
				fee.feeType=table.getCellValue(i, 7);
				fee.effectDate=table.getCellValue(i, 8);
				fee.rate=table.getCellValue(i, 10);
				fee.custType=table.getCellValue(i, 12);
				fee.tranType=table.getCellValue(i, 13);
				list.add(fee);
			}
			return list;
		}
		
		/**
		 * search schedule by given tax schedule info
		 * @param tax
		 * @Return void
		 * @Throws
		 */
		public void searchTaxSchedule(FeeScheduleData tax){
			if(!StringUtil.isEmpty(tax.searchType)) {
				this.selectSearchType(tax.searchType);
			}
			if(!StringUtil.isEmpty(tax.searchValue)) {
				this.setTaxSchSearchValue(tax.searchValue);
			}
			if(!StringUtil.isEmpty(tax.activeStatus)) {
				this.selectShowStatusDropDownList(tax.activeStatus);
			}
			if(!StringUtil.isEmpty(tax.productCategory)) {
				this.selectProductCategory(tax.productCategory);
				ajax.waitLoading();
			}
			this.selectFeeType(tax.feeType);
			if(!StringUtil.isEmpty(tax.rateType)) {
				this.selectRateType(tax.rateType);
			}
			this.selectCustomerType(tax.custType);
			this.selectTransactionType(tax.tranType);
			if(tax.productCategory.equalsIgnoreCase("Slip")){
				this.selectMarinaRateType(tax.marinaRateType);
			}
			
			this.clickGo();
			ajax.waitLoading();
			this.waitLoading();
		}
		
		public String getWarningMsg(){
//			return browser.getObjectText(".class", "Html.DIV", ".id", "tax.search.noresult");
			return browser.getObjectText(".class", "Html.DIV", ".className", "message");
		}
		
		/**
		 * Get error message on the page
		 * @return
		 */
		public String getErrorMessage() {
			String errorMsg = "";
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id" , "NOTSET");
			if(objs.length > 0){
				errorMsg = objs[0].text();
			}
			Browser.unregister(objs);
			return errorMsg;
		}

		/**
		 * 1. search schedule
		 * 2. check warning message exist or not
		 * 3. no message, get the first id and return ID
		 * 4. exist message, return null.
		 */
		public String checkTaxScheduleExist(ScheduleData schedule){
			if(StringUtil.notEmpty(schedule.searchBy)){
				this.selectSearchType(schedule.searchBy);
			}
			if(StringUtil.notEmpty(schedule.searchValue)){
				this.setTaxSchSearchValue(schedule.searchValue);
			}
			if(StringUtil.notEmpty(schedule.productCategory)){
				this.selectProductCategory(schedule.productCategory);
				ajax.waitLoading();
				this.waitLoading();
			}
			if(StringUtil.notEmpty(schedule.searchStatus)){
				this.selectShowStatusDropDownList(schedule.searchStatus);
			}
			if(StringUtil.notEmpty(schedule.feeType)){
				this.selectFeeType(schedule.feeType);
			}
			this.clickGo();
			this.waitLoading();
			
			String waningMsg = this.getWarningMsg();
			if(StringUtil.isEmpty(waningMsg)){
				return this.getFirstScheduleId();
			} else {
				return null;
			}
		}
		
		public List<String> getProductCategory(){
			return browser.getDropdownElements(".id", "TaxScheduleSearchCriteria.productCategory");
		}
		
		public List<String> getMarinaRateType(){
			return browser.getDropdownElements(".id", "SlipTaxScheduleSearchCriteria.marinaRateType");
		}
		
		public boolean isMarinaRateTypeExist(){
			return browser.checkHtmlObjectExists(".id", "SlipTaxScheduleSearchCriteria.marinaRateType");
		}
		
		public void selectTransactionType(String type){
			if(StringUtil.isEmpty(type) || type.equalsIgnoreCase("All")){
				browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.transactionType", false), 0, true);
			} else {
				browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.transactionType", false), type, true);
			}
		}
		
		public void selectRateType(String rate){
			browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.rateType", false), rate);
		}
		
		public void selectCustomerType(String customer){
			if(StringUtil.isEmpty(customer) || customer.equalsIgnoreCase("All")){
				browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.customerTypeID", false), 0, true);
			} else {
				browser.selectDropdownList(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.customerTypeID", false), customer);
			}
		}
		
		public void selectMarinaRateType(String marinaRateType){
			if(StringUtil.isEmpty(marinaRateType)){
				browser.selectDropdownList(".id", "SlipTaxScheduleSearchCriteria.marinaRateType", 0, true);
			} else {
				browser.selectDropdownList(".id", "SlipTaxScheduleSearchCriteria.marinaRateType", marinaRateType, true);
			}
			
		}
		
		public void selectApplicableProductCategory(String appProCat){
			if(appProCat.equalsIgnoreCase("All")){
				browser.selectDropdownList(".id", "ListTaxScheduleSearchCriteria.applicablePrdCategory", 0);
			}else{
				browser.selectDropdownList(".id", "ListTaxScheduleSearchCriteria.applicablePrdCategory", appProCat);
			}
		}
		
		private IHtmlTable getSearchResultTable(){
			IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?taxScheduleList(View|Grid)_LIST", false));
			if(objs.length < 1){
				throw new ItemNotFoundException("Can't find tax schedule list.");
			}
			
			IHtmlTable table = (IHtmlTable)objs[0];
			if(table.rowCount() <= 0){
				throw new ErrorOnPageException("There isn't any search result.");
			}
			Browser.unregister(objs);
			return table;
		}
		
		public List<String> getColumnByName(String name){
			List<String> allValue = new ArrayList<String>();
			List<String> columnValue = new ArrayList<String>();
			do{
				columnValue = new ArrayList<String>();
				IHtmlTable table = getSearchResultTable();
				int col = table.findColumn(0, name);
				if(col < 0){
					throw new ItemNotFoundException("Can't find column by name:"+name);
				}
				columnValue = table.getColumnValues(col);
				columnValue.remove(0);// remove title
				allValue.addAll(columnValue);
			}while(gotoNext());
			return allValue;
		}
		
		public List<String> getFeeTypeColumnValue(){
			return this.getColumnByName("Fee Type");
		}

		public List<String> getTransTypeColumnValue(){
			return this.getColumnByName("Transaction Type");
		}

		public List<String> getScheduleIDColumnValue(){
			return this.getColumnByName("Tax Schedule ID");
		}

		public List<String> getProductColumnValue(){
			return this.getColumnByName("Product Or Product Group");
		}

		public List<String> getCustomerTypeColumnValue(){
			return this.getColumnByName("Customer Type");
		}
		
		public void verifySearchResultByColumn(String column, String expectValue){
			List<String> colList = this.getColumnByName(column);
			if(colList.size() < 1){
				throw new ItemNotFoundException("Can't find any record by column name:"+column);
			}
			
			for(int i = 0; i < colList.size(); i ++) {
				if(!colList.get(i).equalsIgnoreCase(expectValue)){
					throw new ErrorOnPageException("Column " + column + " should NOT contais " + expectValue);
				}
			}
		}
		
		public boolean isSearchTypeExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.searchBy", false));
		}
		
		public boolean isSearchValueExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.searchByValue", false));
		}
		
		public boolean isDateExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.dateType", false));
		}
		
		public boolean isFromDateExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.fromDate_ForDisplay", false));
		}
		
		public boolean isToDateExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.toDate_ForDisplay", false));
		}
		
		public void setFromDate(String fromDate){
			browser.setTextField(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.fromDate_ForDisplay", false), fromDate, true);
		}
		
		public void setToDate(String toDate){
			browser.setTextField(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.toDate_ForDisplay", false), toDate, true);
		}
		
		public boolean isStatusExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.active", false));
		}
		
		public boolean isProductCategoryExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria(-\\d+)?\\.productCategory", false));
		}
		
		public boolean isApplicableProductCategoryExists() {
			return browser.checkHtmlObjectExists(".id", "ListTaxScheduleSearchCriteria.applicablePrdCategory");
		}
		
		public boolean isFeeTypeExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.feeType", false));
		}
		
		public boolean isRateTypeExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.rateType", false));
		}
		
		public boolean isCustomerTypeExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.customerTypeID", false));
		}
		
		public boolean isTransactionTypeExists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip|List)?TaxScheduleSearchCriteria.transactionType", false));
		}
		
		public List<String> getSearchResultColumnNames() {
			IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?taxScheduleList(View|Grid)_LIST", false));
			if(objs.length < 1){
				throw new ItemNotFoundException("Can't find tax schedule list table.");
			}
			IHtmlTable table = (IHtmlTable)objs[0];
			List<String> columnNames = table.getRowValues(0);
			
			Browser.unregister(objs);
			return columnNames;
		}
		
		public void verifySearchResultInColumn(String column, String expectValue){
			boolean passed = true;
			List<String> colList = this.getColumnByName(column);
			for(int i=0; i<colList.size(); i++){
				if(!colList.get(i).equalsIgnoreCase(expectValue)){
					logger.info("The " + i + "th record is not correct, expect:" + expectValue + ",but actually is:" +colList.get(i));
					passed = false;
				}
			}
			if(!passed){
				throw new ErrorOnPageException("Column "+column+" should ONLY contais "+expectValue);
			}
		}
}
