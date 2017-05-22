/*
 * Created on Jan 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author Ssong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRaFeeSchMainPage extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRaFeeSchMainPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRaFeeSchMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRaFeeSchMainPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRaFeeSchMainPage();
		}

		return _instance;
	}

	public boolean exists() {
//		RegularExpression reg = new RegularExpression("FindRAFees", false);
//		return browser.checkHtmlObjectExists(".href", reg, ".text",
//				new RegularExpression("Go", false));
		 RegularExpression rex = new RegularExpression("Fee Schedule ID(| )Active(| )Location.*",false);
		 return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",rex) || browser.checkHtmlObjectExists(".id", new RegularExpression("(Slip)?RAFeeScheduleSearchResultGrid_LIST", false));
	}

	/**
	 * Click Add New Button
	 * 
	 */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/**
	 * Click Activate Button
	 * 
	 */
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/**
	 * Click Deactivate Button
	 * 
	 */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/**
	 * Click Go Button
	 * 
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^(Go|Search)$", false));
	}

	/**
	 * Select search by value from drop down list
	 * 
	 * @param searchType
	 */
	public void selectSearchBy(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression("search_location|FeeSearchRequest\\.searchBy", false), searchType);
	}

	/**
	 * Input search value
	 * 
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression("search_location_value|FeeSearchRequest\\.value", false), value);
	}

	/**
	 * Select date type.
	 * 
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("(search_date)|(FeeSearchRequest.dateBy)", false), type);
	}

	/** Deselect date type. */
	public void deselectDateType() {
		browser.selectDropdownList(".id", new RegularExpression("(search_date)|(FeeSearchRequest.dateBy)", false), 0);
	}

	/**
	 * Fill in search from date.
	 * 
	 * @param date
	 *            - from date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", new RegularExpression("(search_from_ForDisplay)|(FeeSearchRequest.start_ForDisplay)", false), date);
	}

	/**
	 * Fill in search end date.
	 * 
	 * @param date
	 *            - end date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", new RegularExpression("(search_to_ForDisplay)|(FeeSearchRequest.end_ForDisplay)", false), date);
	}

	/**
	 * Select active or inactive status.
	 * 
	 * @param type
	 */
	public void selectShowStatus(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id",new RegularExpression("(show_active)|(FeeSearchRequest.feeSchdStatus)",false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("(show_active)|(FeeSearchRequest.feeSchdStatus)",false), type);
		}
	}

	/** Deselect show stasue. */
	public void deselectShowStatus() {
		browser.selectDropdownList(".id", new RegularExpression("(show_active)|(FeeSearchRequest.feeSchdStatus)",false), 0);
	}

	/**
	 * Select product category.
	 * 
	 * @param type
	 */
	public void selectProductCategory(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("FeeSearchRequest-\\d+\\.prdGrpCat", false), 0);//prd_cat_type UI changed on 3.03.01
		} else {
			browser.selectDropdownList(".id", new RegularExpression("FeeSearchRequest-\\d+\\.prdGrpCat", false), type);
		}
	}

	/** Deselect product category. */
	public void deselectProductCategory() {
		browser.selectDropdownList(".id", new RegularExpression("FeeSearchRequest-\\d+\\.prdGrpCat", false), 0);
	}
	
	public void selectApplicableProductCategory(String appPrdCat) {
		browser.selectDropdownList(".id", "FeeSearchRequest.applicableProductCategory", appPrdCat);
	}
	
	/**
	 * Select unit type.
	 * 
	 * @param type
	 *            - unit type
	 */
	public void selectUnitType(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.unitType", type);
	}

	/** Deselect unit type. */
	public void deselectUnitType() {
		browser.selectDropdownList(".id", "FeeSearchRequest.unitType", 0);
	}

	/**
	 * Select ticket category.
	 * 
	 * @param type
	 *            - ticket category
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
	 * 
	 * @param type
	 *            - permit type
	 */
	public void selectPermitType(String type) {
		browser.selectDropdownList(".id", "permit_type_id", type);
	}

	/** Deselect permit type. */
	public void deselectPermitType() {
		browser.selectDropdownList(".id", "permit_type_id", 0);
	}

	/**
	 * Select product fee class
	 * 
	 * @param feeClass
	 */
	public void selectProductFeeClass(String feeClass) {
		browser.selectDropdownList(".id", "product_fee_class_id", feeClass);
	}

	/** Deselect product fee class. */
	public void deselectProductFeeClass() {
		browser.selectDropdownList(".id", "product_fee_class_id", 0);
	}

	/**
	 * Select transaction type.
	 * 
	 * @param type
	 *            - transaction type
	 */
	public void selectTransType(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.transactionTypeID", type);//trans_type UI changed on 3.03.01
	}

	/** Deselect transaction type. */
	public void deselectTransType() {     
		browser.selectDropdownList(".id", "FeeSearchRequest.transactionTypeID", 0);
		Browser.sleep(2);
	}

	/**
	 * Select transaction occ type.
	 * 
	 * @param type
	 *            - occ type
	 */
	public void selectTransOcc(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.transactionOccurrenceID", type);
	}

	/** Deselect transaction occ type. */
	public void deselectTransOcc() {
		browser.selectDropdownList(".id", "FeeSearchRequest.transactionOccurrenceID", 0);
	}

	/**
	 * Select sales channel from drop down list
	 * 
	 * @param channel
	 */
	public void selectSalesChannel(String channel) {
		browser.selectDropdownList(".id", "FeeSearchRequest.salesChannel", channel);
	}

	/**
	 * Deselect sales channel
	 * 
	 */
	public void deselectSalesChannel() {
		browser.selectDropdownList(".id", "FeeSearchRequest.salesChannel", 0);
	}
	
	public void deselectLocationClass(){
		browser.selectDropdownList(".id", "FeeSearchRequest.locationClassID", 0);
	}

	/**
	 * This method is used to select the first Schedule check box
	 * 
	 */
	public void selectFirstScheduleCheckBox() {
		browser.checkHtmlObjectExists(".id", new RegularExpression("GenericGrid-\\d+.selectedItems",false));//added by pzhu
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+.selectedItems",false));
	}

	/**
	 * go to fee schedule's detail page by given id.
	 * 
	 * @param scheduleID
	 *            - fee schedule id
	 */
	public void gotoFeeScheduleDetailPg(String scheduleID) {
		browser.clickGuiObject(".class", "Html.A", ".text", scheduleID);
	}

	/**
	 * This method used to search fee schedule by schedule id
	 * 
	 * @param feeSchID
	 */
	public void searchByFeeScheduleId(String feeSchID) {
		selectSearchBy("Fee Schedule ID");
		setSearchValue(feeSchID);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchByProduct(String prdName,String status){
		selectSearchBy("Product");
		this.setSearchValue(prdName);
		this.selectShowStatus(status);
		this.clickGo();
		ajax.waitLoading();
		waitLoading();
	}

	/**
	 * This method used to clear all search criteria
	 * 
	 */
	public void clearAllSearchCriteria() {
		setSearchValue("");
		setFromDate("");
		setToDate("");
		deselectShowStatus();
		deselectProductCategory();
		deselectUnitType();
		deselectTicketCategory();
		deselectPermitType();
		deselectProductFeeClass();
		Browser.sleep(3);
		deselectTransType();
		deselectTransOcc();
		deselectSalesChannel();
		deselectLocationClass();
	}

	/**
	 * This method used to search Fee Schedule without input any criteria
	 * 
	 */
	public void searchScheduleWithoutCriteria() {
		logger.info("Search Schedule Without Any Criteria.");

		clearAllSearchCriteria();
		clickGo();
		waitLoading();
		RegularExpression rex = new RegularExpression(
				"^Fee Schedule ID( )?Active.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
		int num = tableGrid.rowCount();
		Browser.unregister(objs);
		if (num < 2) {
			throw new ItemNotFoundException("No Fee Schedule Found!");
		}
	}
	
	private void selectProductSubCategory(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", "FeeSearchRequest.prdSubCatID", 0);//search_order_type UI changed on 3.03.01
		} else {
			browser.selectDropdownList(".id", "FeeSearchRequest.prdSubCatID", type);
		}
	}
	
	public void selectLocationClass(String locClass){
		browser.selectDropdownList(".id", "FeeSearchRequest.locationClassID", locClass);
	}
	
	public void searchSchedule(RaFeeScheduleData schedule){
		clearAllSearchCriteria();
		// TODO not contains all search criteria
		if(!StringUtil.isEmpty(schedule.searchBy)) {
			this.selectSearchBy(schedule.searchBy);
		}
		
		if(!StringUtil.isEmpty(schedule.searchBy) && !StringUtil.isEmpty(schedule.searchValue)) {
			this.setSearchValue(schedule.searchValue);
		}
		if(StringUtil.notEmpty(schedule.searchStatus)){
			this.selectShowStatus(schedule.searchStatus);
		}
		if(StringUtil.notEmpty(schedule.searchDate)){
			this.selectDateType(schedule.searchDate);
		}
		if(StringUtil.notEmpty(schedule.searchDateFrom)){
			this.setFromDate(schedule.searchDateFrom);
		}
	    if(StringUtil.notEmpty(schedule.searchDateTo)){
	    	this.setToDate(schedule.searchDateTo);
	    }
		if(StringUtil.notEmpty(schedule.productCategory)){
			this.selectProductCategory(schedule.productCategory);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(schedule.productSubCategory)){
			this.selectProductSubCategory(schedule.productSubCategory);
		}
		if(StringUtil.notEmpty(schedule.unitOption)){
			String option = schedule.unitOption;
			if(option.contains("Unit")){
				if(option.contains("Stay")){
					option = "Per Stay Unit";
				}else{
					option = "Per Unit";
				}			
			}//other option TODO
			this.selectUnitType(option);
		}
		if(StringUtil.notEmpty(schedule.tranType)){
			this.selectTransType(schedule.tranType);
		}
		if(StringUtil.notEmpty(schedule.tranOccur)){
			this.selectTransOcc(schedule.tranOccur);
		}
		if(StringUtil.notEmpty(schedule.salesChannel)){
			String option = schedule.salesChannel;
			if(option.contains("Field")){
				option = "Field";
			}else if(option.contains("Call")){
				option = "Call Center";
			}
			this.selectSalesChannel(option);
		}
		if(StringUtil.notEmpty(schedule.marinaRateType))
			this.selectMarinaRateType(schedule.marinaRateType);
		
		if(StringUtil.notEmpty(schedule.locationClass)){
			this.selectLocationClass(schedule.locationClass);
		}
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public List<String> getFeeID(){
		List<String> feeIDList = new ArrayList<String>();

		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^(RA )?Fee Schedule ID.*", false));
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];
		for(int i=1; i<table.rowCount(); i++){
			feeIDList.add(table.getCellValue(i, 1));
		}
		
		return feeIDList;
	}
	
	public void selectAllCheckBox(){
		browser.selectCheckBox(".name", "all_slct");
	}

	public void cleanupFeeSchedule(RaFeeScheduleData schedule){
		this.searchSchedule(schedule);
		List<String> feeIDList = this.getFeeID();
		if(feeIDList.size()>0){
			this.selectAllCheckBox();
			this.clickDeactivate();
			this.waitLoading();
		} else {
			logger.error("Doesn't find any fee schedule info.");
		}
	}
	
	public void changeScheduleStatus(String feeSchdId, String status) {
		this.changeScheduleStatus(feeSchdId, status, true);
	}
	
	/**
	 * This method used to change schedule status
	 * 
	 * @param feeSchdId
	 * @param status
	 * @update by pzhu for deactivate conflicted Fee Schedule ID
	 * Example of error msg about conflicted : The RA Fee Schedule 133846227 cannot be activated because another identical active RA Fee Schedule 133846295 exists
	 * 
	 */
	public String changeScheduleStatus(String feeSchdId, String status, boolean isOverrideExisting) {
		logger.info("Start to Change RA Fee Schedule(ID#=" + feeSchdId + ") Status to " + status);
//		String tmpStr="";
		String tmpID="";
		ArrayList<String> ids=new ArrayList<String>();
		searchByFeeScheduleId(feeSchdId);
		selectFirstScheduleCheckBox();
//		selectAllCheckBox();
		
		String errorMsg = "";
		if (status.equalsIgnoreCase("Active")) {
			clickActivate();
			ajax.waitLoading();
			browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Activate");
			
			if(this.isErrorMsgExists()) {
				errorMsg = this.getErrorMsg();
			}
			if(isOverrideExisting) {
				if(!StringUtil.isEmpty(errorMsg)) {
					logger.info("There is an error when activate the RA fee schedule: -->" + errorMsg);
					Pattern p = Pattern.compile("\\d+");
					Matcher m = p.matcher(errorMsg);
					while (m.find()) {
					    ids.add(m.group(0));
					  }
					if(ids.size()>1) {// now we just solve that there are 2 IDs in the error msg. First is current, second is old.
						tmpID = ids.get(ids.size()-1);//tmpID.substring(tmpID.indexOf(" ")).trim();
						changeScheduleStatus(tmpID,"Inactive");
						changeScheduleStatus(feeSchdId,"Active");
					}
				}
			}
		} else if (status.equalsIgnoreCase("Inactive")) {
			clickDeactivate();
			ajax.waitLoading();
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
		ajax.waitLoading();
		waitLoading();
		
		return errorMsg;
	}

	/**
	 * This method used to verify specific schedule status equals given status
	 * 
	 */
	public void verifyStatus(String feeSchdId, String status) {
		logger.info("Start to verify schedule " + feeSchdId + " is " + status);

		searchByFeeScheduleId(feeSchdId);
		if (status.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS)) {
			if (isFeeScheduleActive(feeSchdId)) {
				throw new ErrorOnPageException("Fee Schedule Status is not Inactive");
			}
		} else if (status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)) {
			if (!isFeeScheduleActive(feeSchdId)) {
				throw new ErrorOnPageException("Fee Schedule Status is not Active");
			}
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
	}

	/**
	 * verify whether or not the special fee schedule is active status.
	 * 
	 * @param feeSchID
	 *            - fee schedule id
	 * @return true - active; false - inactive
	 */
	public boolean isFeeScheduleActive(String feeSchID) {
		boolean toReturn = false;
		String status = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id",
					new RegularExpression("RAFeeScheduleSearchResultGrid_LIST", false));


		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		if (tableGrid.rowCount() > 0) {
			status = tableGrid.getCellValue(1, 2);
		}

		Browser.unregister(objs);
		if (status.equalsIgnoreCase("Yes")) {
			toReturn = true;
		}
		return toReturn;
	}

	public RaFeeScheduleData getFeeSchInfo(int row) {
		RaFeeScheduleData schedule = new RaFeeScheduleData();

		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Fee Schedule ID.*|RA Fee Schedule ID.*", false));
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];
		schedule.feeId = table.getCellValue(row, 1);
		schedule.activeStatus = table.getCellValue(row, 2);
		schedule.location = table.getCellValue(row, 3);
		schedule.productCategory = table.getCellValue(row, 5);
		if(schedule.productCategory.equals("Slip")){
			schedule.dock = table.getCellValue(row, 4);
			schedule.product = table.getCellValue(row, 7);
			schedule.effectDate = table.getCellValue(row, 8);
			schedule.marinaRateType = table.getCellValue(row, 9);
			schedule.baseRate = table.getCellValue(row,10);
			schedule.unitOption = table.getCellValue(row,11);
			schedule.applyRate = table.getCellValue(row,12);
			schedule.tranType = table.getCellValue(row,13);
			schedule.tranOccur =  table.getCellValue(row,14);
			schedule.salesChannel = table.getCellValue(row,16);
			schedule.acctCode = table.getCellValue(row, 18);
		}else{
			schedule.loop = table.getCellValue(row, 4);
			schedule.productSubCategory = table.getCellValue(row, 6);
			schedule.product = table.getCellValue(row, 7);
			schedule.licenseYearFrom = table.getCellValue(row, 8);
			schedule.licenseYearTo = table.getCellValue(row, 9);
			schedule.effectDate = table.getCellValue(row, 10);
			schedule.baseRate = table.getCellValue(row, 11);
			schedule.unitOption = table.getCellValue(row, 12);
			schedule.applyRate = table.getCellValue(row,  getColumnNumberByColName("Apply Rate"));
			schedule.tranType = table.getCellValue(row, getColumnNumberByColName("Transaction Type"));
			schedule.tranOccur = table.getCellValue(row, getColumnNumberByColName("Transaction Occurrence"));
			schedule.ticketCategory = table.getCellValue(row, getColumnNumberByColName("Ticket/Permit Category"));
			schedule.permitType = table.getCellValue(row, getColumnNumberByColName("Permit Type"));
			schedule.vehicleType = table.getCellValue(row, getColumnNumberByColName("Vehicle Type"));
			schedule.productFeeClass = table.getCellValue(row, getColumnNumberByColName("Product Fee Class"));
			schedule.salesChannel = table.getCellValue(row, getColumnNumberByColName("Sales Channel"));
			schedule.locationClass = table.getCellValue(row, getColumnNumberByColName("Location Class"));
			schedule.acctCode = table.getCellValue(row, getColumnNumberByColName("Account"));
System.out.println(schedule.applyRate+","+schedule.tranType);
		}
		
		Browser.unregister(objs);

		return schedule;
	}
	
	private int getColumnNumberByColName(String colName){
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Fee Schedule ID.*|RA Fee Schedule ID.*", false));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Not Found RA Fee schedule table.");
		}
		IHtmlTable grid = (IHtmlTable)objs[objs.length-1];
		int colNum = grid.findColumn(0, colName);
		return colNum;
	}
	
	public void verifySchdInSearchList(String id) {
		List<String> colValue = this.getSpecificColValueList("Fee Schedule ID");
		if(!colValue.contains(id)){
			throw new ItemNotFoundException("Search Fee Schedule Fail!");
		}else{
			logger.info("Fee schedule with id:" + id + " is exist");
		}
	}

	public void verifyScheduleListInfo(RaFeeScheduleData schedule) {
		logger.info("Start to Verify fee schedule list information.");
		
		RaFeeScheduleData actually = this.getFeeSchInfo(1);

		if (!schedule.feeId.equalsIgnoreCase(actually.feeId)) {
			throw new ItemNotFoundException("Fee Schedule Id " + actually.feeId
					+ " not same with given value " + schedule.feeId);
		}
		if (!schedule.activeStatus.equalsIgnoreCase(actually.activeStatus)) {
			throw new ItemNotFoundException("Active Status "
					+ actually.activeStatus + " not same with given value "
					+ schedule.activeStatus);
		}
		if (!schedule.location.equalsIgnoreCase(actually.location)) {
			throw new ItemNotFoundException("Location " + actually.location
					+ " not same with given value " + schedule.location);
		}
		if(schedule.productCategory.equalsIgnoreCase("Slip")){
			if(!schedule.dock.equalsIgnoreCase(actually.dock)){
				throw new ItemNotFoundException("Dock"
						+ actually.dock + " not same with given value "
						+ schedule.dock);
			}
		}else{
			if(!schedule.loop.equalsIgnoreCase(actually.loop)){
				throw new ItemNotFoundException("Loop/Area/Venue"
						+ actually.loop + " not same with given value "
						+ schedule.loop);
			}
		}
		
		if (!schedule.productCategory
				.equalsIgnoreCase(actually.productCategory)) {
			throw new ItemNotFoundException("Product Category "
					+ actually.productCategory + " not same with given value "
					+ schedule.productCategory);
		}
		if (schedule.productSubCategory != null
				&& !schedule.productSubCategory.equals("")) {
			if (!schedule.productSubCategory
					.equalsIgnoreCase(actually.productSubCategory)) {
				throw new ItemNotFoundException("Product Sub Category "
						+ actually.productSubCategory
						+ " not same with given value "
						+ schedule.productSubCategory);
			}
		}
		if (schedule.product != null && !schedule.product.equals("")) {
			String product = "";
			if (schedule.product.contains("-")) {
				product = schedule.product.split("-")[1];
			} else {
				product = schedule.product;
			}
			if (!product.equalsIgnoreCase(actually.product)) {
				throw new ItemNotFoundException("Assignment Product "
						+ actually.product + " not same with given value "
						+ product);
			}
		} else {
			if (!schedule.productGroup.equalsIgnoreCase(actually.product)) {
				throw new ItemNotFoundException("Assignment Product Group "
						+ actually.product + " not same with given value "
						+ schedule.productGroup);
			}
		}
		if (schedule.licenseYearFrom != null
				&& !schedule.licenseYearFrom.equals("")) {
			if (!schedule.licenseYearFrom
					.equalsIgnoreCase(actually.licenseYearFrom)) {
				throw new ItemNotFoundException("License Year From "
						+ actually.licenseYearFrom
						+ " not same with given value "
						+ schedule.licenseYearFrom);
			}
		}
		if (schedule.licenseYearTo != null
				&& !schedule.licenseYearTo.equals("")) {
			if (!schedule.licenseYearTo
					.equalsIgnoreCase(actually.licenseYearTo)) {
				throw new ItemNotFoundException("License Year To "
						+ actually.licenseYearTo
						+ " not same with given value "
						+ schedule.licenseYearTo);
			}
		}
		if (schedule.effectDate != null && !schedule.effectDate.equals("")) {
			if (!DateFunctions.formatDate(schedule.effectDate, "EEE MMM d yyyy").equalsIgnoreCase(DateFunctions.formatDate(
					actually.effectDate, "EEE MMM d yyyy"))) {
				throw new ItemNotFoundException("Effective date "
						+ actually.effectDate + " not same with given value "
						+ schedule.effectDate);
			}
		}
		if ("Service".equals(schedule.productCategory)) {
			String rate = "";
			if (schedule.baseRate != null && !schedule.baseRate.equals("")) {
				rate = "Flat:" + schedule.baseRate;
			}
			if (schedule.otherRate != null && !schedule.otherRate.equals("")) {
				rate = rate + "Percent:" + schedule.otherRate;
			}
			if (!rate.equalsIgnoreCase(actually.baseRate.replaceAll(" ", ""))) {
				throw new ItemNotFoundException("Base Rate "
						+ actually.baseRate + " not same with given value "
						+ rate);
			}
		} else {
			if (schedule.baseRate != null && !schedule.baseRate.equals("")) {
				if (!schedule.baseRate.equalsIgnoreCase(actually.baseRate)) {
					throw new ItemNotFoundException("Base Rate "
							+ actually.baseRate + " not same with given value "
							+ schedule.baseRate);
				}
			}
		}
		if (schedule.unitOption != null && !schedule.unitOption.equals("")) {
			if (!schedule.unitOption.equalsIgnoreCase(actually.unitOption)) {
				throw new ItemNotFoundException("Selected RA Fee Unit "
						+ actually.unitOption + " not same with given value "
						+ schedule.unitOption);
			}
		}
		if (schedule.applyRate != null && !schedule.applyRate.equals("")) {
			if (!schedule.applyRate.equalsIgnoreCase(actually.applyRate)) {
				throw new ItemNotFoundException("Apply Rate "
						+ actually.applyRate + " not same with given value "
						+ schedule.applyRate);
			}
		} else {
			if (!"Order Item Level".equalsIgnoreCase(actually.applyRate)) {
				throw new ItemNotFoundException("Apply Rate "
						+ actually.applyRate
						+ " not same with given value 'Order Item Level'");
			}
		}
		if (schedule.tranType != null && !schedule.tranType.equals("")) {
			if (!schedule.tranType.equalsIgnoreCase(actually.tranType)) {
				throw new ItemNotFoundException("Selected Transaction Type "
						+ actually.tranType + " not same with given value "
						+ schedule.tranType);
			}
		}
//		if (schedule.tranOccur != null && !schedule.tranOccur.equals("")) {
//			if (!schedule.tranOccur.equalsIgnoreCase(actually.tranOccur)) {
//				throw new ItemNotFoundException(
//						"Selected Transaction Occurrence " + actually.tranOccur
//								+ " not same with given value "
//								+ schedule.tranOccur);
//			}
//		} else {
//			if (!"All".equalsIgnoreCase(actually.tranOccur)) {
//				throw new ItemNotFoundException(
//						"Selected Transaction Occurrence " + actually.tranOccur
//								+ " not same with given value 'All'");
//			}
//		}
		
		if (!schedule.tranOccur.equalsIgnoreCase(actually.tranOccur)) {
			throw new ItemNotFoundException(
					"Selected Transaction Occurrence " + actually.tranOccur
							+ " not same with given value "
							+ schedule.tranOccur);
		}
		if (schedule.ticketCategory != null
				&& !schedule.ticketCategory.equals("")) {
			if (!schedule.ticketCategory
					.equalsIgnoreCase(actually.ticketCategory)) {
				throw new ItemNotFoundException("Ticket/Permit Category "
						+ actually.ticketCategory
						+ " not same with given value "
						+ schedule.ticketCategory);
			}
		}
		if (schedule.permitType != null && !schedule.permitType.equals("")) {
			if (!schedule.permitType.equalsIgnoreCase(actually.permitType)) {
				throw new ItemNotFoundException("Permit Type "
						+ actually.permitType + " not same with given value "
						+ schedule.permitType);
			}
		}
		if (schedule.vehicleType != null && !schedule.vehicleType.equals("")) {
			if (!(schedule.vehicleType).equalsIgnoreCase(actually.vehicleType)) {
				throw new ItemNotFoundException("Vehicle Type "
						+ actually.vehicleType + " not same with given value "
						+ schedule.vehicleType);
			}
			
		}else {
			if (schedule.lineofbusiness.equals(OrmsConstants.HUNTING_FISHING) &&!"Service".equals(schedule.productCategory)
					&& !"All".equalsIgnoreCase(actually.vehicleType)) {
				throw new ItemNotFoundException("Vehicle Type "
						+ actually.vehicleType
						+ " not same with given value 'All'");
			}
		} 
		if (schedule.productFeeClass != null
				&& !schedule.productFeeClass.equals("")) {
			if (!schedule.productFeeClass
					.equalsIgnoreCase(actually.productFeeClass)) {
				throw new ItemNotFoundException("Product Fee Class "
						+ actually.productFeeClass
						+ " not same with given value "
						+ schedule.productFeeClass);
			}
		}
		if (schedule.salesChannel != null && !schedule.salesChannel.equals("")) {
			String channel = "";
			if ("Field".equals(schedule.salesChannel)) {
				channel = schedule.salesChannel + "Mgr";
			} else {
				channel = schedule.salesChannel;
			}
			if (!channel.replaceAll(" ", "").equalsIgnoreCase(
					actually.salesChannel)) {
				throw new ItemNotFoundException("Sales Channel "
						+ actually.salesChannel + " not same with given value "
						+ channel);
			}
		}
		if (schedule.locationClass != null
				&& !schedule.locationClass.equals("")) {
			if (!schedule.locationClass.replaceFirst("\\d+", "").trim()
					.equalsIgnoreCase(actually.locationClass)) {
				throw new ItemNotFoundException("Location Class "
						+ actually.locationClass
						+ " not same with given value "
						+ schedule.locationClass);
			}
		}
		if (schedule.acctCode != null && !schedule.acctCode.equals("")) {
			if (!schedule.acctCode.equalsIgnoreCase(actually.acctCode)) {
				throw new ItemNotFoundException("Account Code "
						+ actually.acctCode + " not same with given value "
						+ schedule.acctCode);
			}
		}
	}
	
	private IHtmlObject[] getRaFeeSchedulsTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?RAFeeScheduleSearchResultGrid(_LIST)?",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can not find the table...");
		}
		return objs;
	}
	/**
	 * get fee schedule status by id
	 * @param id
	 * @return
	 */
	public String getFeeScheduleStatusById(String id){
		String status = "";
		IHtmlObject[] objs = getRaFeeSchedulsTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		for(int i=0;i<table.rowCount();i++){
			if(table.getCellValue(i, 1).equals(id)){
				status = table.getCellValue(i, 2);
			}
		}
		Browser.unregister(objs);
		return status;
	}
	/**
	 * verify fee Schedule status;
	 * @param id
	 * @param status
	 */
	public void verifyFeeScheduleStatus(String id,String status){
		String actualStatus = this.getFeeScheduleStatusById(id);
		if(!actualStatus.equals(status)){
			throw new ErrorOnPageException("Fee schedule status",status,actualStatus);
		}
	}
	/**
	 * verify fee schedule active status.
	 * @param id
	 */
	public void verifyFeeScheduleActiveStatus(String id){
		this.verifyFeeScheduleStatus(id, "Yes");
	}
	/**
	 * verify fee schedule inactive status.
	 * @param id
	 */
	public void verifyFeeScheduleInactiveStatus(String id){
		this.verifyFeeScheduleStatus(id, "No");
	}
	/**
	 * select check box.
	 * @param index
	 */
	public void selectCheckBox(int index){
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index);
	}
	/**
	 * active RA fee schedule by id.
	 * @param id
	 */
	public void selectRaFeeSchedulCheckBoxById(String id){
		IHtmlObject[] objs = getRaFeeSchedulsTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		for(int i=0;i<table.rowCount();i++){
			if(table.getCellValue(i, 1).equals(id)){
				this.selectCheckBox(i-1);
			}
		}
	}
	/**
	 * active ra fee schedules.
	 * @param id
	 */
	public void activeRaFeeSchedules(String... ids){
		/*this.selectRaFeeSchedulCheckBoxById(id);
		this.clickActivate();
		ajax.waitLoading();
		this.waitExists();*/
		for(String id:ids){
			this.changeScheduleStatus(id, "Active");
		}
	}
	
	/**
	 * select all check box.
	 */
	public void checkAllBox(){
		browser.selectCheckBox(".class", "Html.CHECKBOX", ".value", "all");
	}
	/**
	 * check and deactive ra fee schedule;
	 * @param prdName
	 */
	public void checkAndDeactiveRaFeeSchedule(String prdName){
		this.searchByProduct(prdName, "Active");
		IHtmlObject[] objs = getRaFeeSchedulsTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		if(table.rowCount()<=1){
			logger.info("No "+prdName+" + active ra fee schedules was exist");
		}
		else{
			this.selectAllCheckBox();
			this.clickDeactivate();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	/**
	 * Deactive ra fee schedules.
	 * @param id
	 */
	public void deactiveRaFeeSchedules(String id){
		/*this.selectRaFeeSchedulCheckBoxById(id);
		this.clickDeactivate();
		ajax.waitLoading();
		this.waitExists();*/
		this.changeScheduleStatus(id, "Inactive");
	}
	/**
	 * Click ra sch Fee id.
	 * @param raFeeSchID
	 */
	public void clickRaSchFeeID(String raFeeSchID){
		browser.clickGuiObject(".class", "Html.A",".text",raFeeSchID);
	}
	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "NOTSET");
	}
	
	/**
	 * get error message.
	 * @return
	 */
	public String getErrorMsg(){
		String errorMsg = "";
		 IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		 if(objs.length>0){
			 errorMsg = objs[0].getProperty(".text");
		 }
		 Browser.unregister(objs);
		 return errorMsg;
	}
	
	public List<String> getSpecificColValueList(String col){
		PagingComponent turnPage = new PagingComponent();
		List<String> values = new ArrayList<String>();
		do{
			IHtmlObject[] objs = this.getRaFeeSchedulsTable();
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
			if(tableGrid.rowCount()>1){
				int colNum = tableGrid.findColumn(0, col);
				List<String> columnList = tableGrid.getColumnValues(colNum);
				columnList.remove(0);
				values.addAll(columnList);
			 } else {
				 break;
			 }
			Browser.unregister(objs);
		}while(turnPage.clickNext());
		
//		do{
//			HtmlObject[] objs = this.getSearchFeeScheduleTable();
//			ITable tableGrid = (ITable) objs[objs.length-1];
//			int colNum = tableGrid.findColumn(0, col);
//			if(tableGrid.rowCount()>1){
//				for(int i=1;i<tableGrid.rowCount();i++){
//					values.add(tableGrid.getCellValue(i, colNum));
//				}
//			 } else {
//				 break;
//			 }
//			Browser.unregister(objs);
//		}while(this.clickNext());
		return values;
	}
	
	public void verifySearchResults(String val, String col) {
//		List<String> values = new ArrayList<String>();
		/*do{
			HtmlObject[] objs = this.getSearchFeeScheduleTable();
			ITable tableGrid = (ITable) objs[objs.length-1];
			int colNum = tableGrid.findColumn(0, col);
		
			if(tableGrid.rowCount()>1){
				for(int i=0;i<tableGrid.rowCount();i++){
					values.add(tableGrid.getCellValue(i, colNum));
				}
			 }
			Browser.unregister(objs);
		}while(this.clickNext());*/
		List<String> values = this.getSpecificColValueList(col);
		if(values.size() <= 0){
			logger.error("There isn't any records!");
		} else {
			for (String value : values) {
				if (!value.contains(val)) {
					throw new ErrorOnPageException("Search Fee Schedule Fail!", val, value);
				}
			}
		}
	}

	public void selectMarinaRateType(String mRateType) {
		browser.selectDropdownList(".id", "SlipFeeSearchRequest.marinaRateType", mRateType);
	}
	
	public List<String[]> getSpecificColsValueList(String[] col){
		PagingComponent turnPage = new PagingComponent();
		List<String[]> list = new ArrayList<String[]>();
		do{
			IHtmlObject[] objs = this.getRaFeeSchedulsTable();
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
			if(tableGrid.rowCount()>1) {
				for(int i=1;i<tableGrid.rowCount();i++) {
					String[] values=new String[col.length];
					for(int j=0;j<col.length;j++) {
						values[j]=tableGrid.getCellValue(i, tableGrid.findColumn(0, col[j]));
					}
					list.add(values);
				}
			} else
				break;
			Browser.unregister(objs);
		}while(turnPage.clickNext());
		return list;
	}

	public List<String> getProductCategory(){
		return browser.getDropdownElements(".id", new RegularExpression("FeeSearchRequest-\\d+.prdGrpCat", false));
	}

	public List<String> getAppProductCategory(){
		return browser.getDropdownElements(".id", new RegularExpression("FeeSearchRequest.applicableProductCategory", false));
	}
	
	public List<String> getMarinaRateType(){
		return browser.getDropdownElements(".id", new RegularExpression("SlipFeeSearchRequest.marinaRateType", false));
	}
	
	public boolean isMarinaRateTypeExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SlipFeeSearchRequest.marinaRateType", false));
	}
	
	public boolean isRateExist(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Fee Schedule ID.*|RA Fee Schedule ID.*", false));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Not Found RA Fee schedule table.");
		}
		
		IHtmlTable grid = (IHtmlTable)objs[0];
		int colNum = grid.findColumn(0, "Rate");
		if(colNum <= 0) {
			logger.error("Rate is not exist in search result.");
			return false;
		}
		else {
			logger.error("Rate is exist in search result.");
			return true;
		}
	}
	
	public String getFirstFeeScheduleID(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?RAFeeScheduleSearchResultGrid_LIST",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can not find the table...");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		String id=table.getCellValue(1, 1);
		
		return id;
	}
	
	public void verifyColNameDisplay(String colName) {
		logger.info("Verify column name "+colName+" display on page");
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?RAFeeScheduleSearchResultGrid_LIST", false));
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
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?RAFeeScheduleSearchResultGrid_LIST", false));
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, new RegularExpression("(RA )?Fee Schedule ID", false)), scheId);
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
	
	
	public void deactiveAllTheFeeForProduct(String product){
		this.searchByProduct(product, OrmsConstants.ACTIVE_STATUS);
		this.selectAllCheckBox();
		this.clickDeactivate();
		ajax.waitLoading();
		this.waitLoading();
	}
}
