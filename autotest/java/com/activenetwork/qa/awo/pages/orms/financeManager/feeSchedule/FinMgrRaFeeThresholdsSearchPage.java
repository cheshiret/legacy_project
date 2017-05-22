/*
 * Created on Dec 22, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author Ssong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRaFeeThresholdsSearchPage extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRaFeeThresholdsSearchPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRaFeeThresholdsSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRaFeeThresholdsSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrRaFeeThresholdsSearchPage();
		}

		return _instance;
	}

	protected PagingComponent pageCom = new PagingComponent(_instance);

	/**
	 * return whether the page mark display in the page
	 */
	public boolean exists() {
		RegularExpression reg = new RegularExpression("FindRAFeeThresholds.do",
				false);
		return browser.checkHtmlObjectExists(".href", reg, ".text",
				new RegularExpression("Go", false))
				|| browser
						.checkHtmlObjectExists(
								".id",
								new RegularExpression(
										"(Slip)?RAFee(Threshold)?ScheduleSearchResultGrid_LIST",
										false));// SlipRAFeeScheduleSearchResultGrid_LIST
	}

	// RegularExpression rex = new
	// RegularExpression("Fee Schedule ID Active Location Product.*",false);
	// return browser.checkTestObjectExists(".class", "Html.TABLE", ".text",
	// rex);
	// }

	private static final String FEE_SCHEDULE_ID_COL = "Fee Schedule ID";
	private static final String ACTIVE_COL = "Active";
	private static final String LOCATION_COL = "Location";
	private static final String LOOP_AREA_VENUE_COL = "Loop/Area/Venue";
	private static final String DOCK_AREA_COL = "Dock/Area";
	private static final String PRODUCT_CATEGORY_COL = "Product Category";
	private static final String PRODUCT_OR_PRODUCT_GROUP_COL = "Product or Product Group";
	private static final Object EFFECTIVE_START_DATE_COL = new RegularExpression(
			"Effective(\\s)?\\(Start\\) Date", false);
	private static final String CURRENT_COUNT_COL = "Current Count";
	private static final String PRODUCT_FEE_CLASS_COL = "Product Fee Class";
	private static final String TRANSACTION_TYPE_COL = "Transaction Type";
	private static final String TRANSACTION_OCCURRENCE_COL = "Transaction Occurrence";
	private static final String SALES_CHANNEL_COL = "Sales Channel";

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
		browser.selectDropdownList(".id", new RegularExpression(
				"(search_location)|(FeeSearchRequest.searchBy)", false),
				searchType);
	}

	/**
	 * Input search value
	 * 
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression(
				"(search_location_value)|(FeeSearchRequest.value)", false),
				value);
	}

	/**
	 * Select date type.
	 * 
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"(search_date)|(FeeSearchRequest.dateBy)", false), type);
	}

	/** Deselect date type. */
	public void deselectDateType() {
		browser.selectDropdownList(".id", "search_date", 0);
	}

	/**
	 * Fill in search from date.
	 * 
	 * @param date
	 *            - from date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", new RegularExpression(
				"(search_from_ForDisplay)|(FeeSearchRequest.start_ForDisplay)",
				false), date);
	}

	/**
	 * Fill in search end date.
	 * 
	 * @param date
	 *            - end date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", new RegularExpression(
				"(search_to_ForDisplay)|(FeeSearchRequest.end_ForDisplay)",
				false), date);
	}

	/**
	 * Select active or inactive status.
	 * 
	 * @param type
	 */
	public void selectShowStatus(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"(show_active)|(FeeSearchRequest.feeSchdStatus)", false), type);
	}

	/** Deselect show stasue. */
	public void deselectShowStatus() {
		browser.selectDropdownList(".id", new RegularExpression(
				"(show_active)|(FeeSearchRequest.feeSchdStatus)", false), 0);
	}

	/**
	 * Select product category.
	 * 
	 * @param type
	 */
	public void selectProductCategory(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"(prd_cat_type)|(FeeSearchRequest-\\d+\\.prdGrpCat)", false),
				type);
	}

	/** Deselect product category. */
	public void deselectProductCategory() {
		browser.selectDropdownList(".id", new RegularExpression(
				"(prd_cat_type)|(FeeSearchRequest-\\d+\\.prdGrpCat)", false), 0);
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
		browser.selectDropdownList(".id", new RegularExpression("trans_type|FeeSearchRequest.transactionTypeID",false), type);
	}

	/** Deselect transaction type. */
	public void deselectTransType() {
		browser.selectDropdownList(".id", new RegularExpression("trans_type|FeeSearchRequest.transactionTypeID",false), 0);
	}

	/**
	 * Select transaction occ type.
	 * 
	 * @param type
	 *            - occ type
	 */
	public void selectTransOcc(String type) {
		browser.selectDropdownList(".id", new RegularExpression("trans_occ_type|FeeSearchRequest.transactionOccurrenceID",false), type);
	}

	/** Deselect transaction occ type. */
	public void deselectTransOcc() {
		browser.selectDropdownList(".id", new RegularExpression("trans_occ_type|FeeSearchRequest.transactionOccurrenceID",false), 0);
	}

	/**
	 * Select sales channel from drop down list
	 * 
	 * @param channel
	 */
	public void selectSalesChannel(String channel) {
		browser.selectDropdownList(".id", new RegularExpression("sales_channel|FeeSearchRequest.salesChannel",false), channel);
	}

	public void selectMarinaRateType(String rateType) {
		browser.selectDropdownList(".id",
				"SlipFeeSearchRequest.marinaRateType", rateType);
	}

	public void deselectMarinaRateType() {
		browser.selectDropdownList(".id",
				"SlipFeeSearchRequest.marinaRateType", 0);
	}

	/**
	 * Deselect sales channel
	 * 
	 */
	public void deselectSalesChannel() {
		browser.selectDropdownList(".id", new RegularExpression("sales_channel|FeeSearchRequest.salesChannel",false), 0);
	}

	public void selectAllCheckbox() {
		browser.selectCheckBox(".name", "all_slct");
	}

	/**
	 * This method is used to select the first Schedule check box
	 * 
	 */
	public void selectFirstScheduleCheckBox() {
		browser.selectCheckBox(".id", "row_0_checkbox");
	}

	/**
	 * This method used to clear all search criteria
	 * 
	 */
	public void clearAllCriteria() {
		setSearchValue("");
		setFromDate("");
		setToDate("");
		deselectShowStatus();
		deselectProductCategory();
		ajax.waitLoading();
		deselectTransType();
		deselectTransOcc();
		deselectSalesChannel();
		this.deselectMarinaRateType();
	}

	/**
	 * Search Schedule By SheduleId
	 * 
	 * @param scheduleId
	 */
	public void searchBySheduleId(String scheduleId) {
		selectSearchBy("Fee Schedule ID");
		setSearchValue(scheduleId);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}

	public void searchSchedule(ScheduleData schedule) {
		if (!StringUtil.isEmpty(schedule.searchBy)
				&& !StringUtil.isEmpty(schedule.searchValue)) {
			this.selectSearchBy(schedule.searchBy);
			this.setSearchValue(schedule.searchValue);
		}
		if (!StringUtil.isEmpty(schedule.dateType)) {
			this.selectDateType(schedule.dateType);
			this.setFromDate(schedule.fromDate);
			this.setToDate(schedule.toDate);
		}
		if (!StringUtil.isEmpty(schedule.searchStatus)) {
			this.selectShowStatus(schedule.searchStatus);
		}
		if (!StringUtil.isEmpty(schedule.productCategory)) {
			this.selectProductCategory(schedule.productCategory);
			ajax.waitLoading();
		}
		if (!StringUtil.isEmpty(schedule.productFeeClass)) {
			this.selectProductFeeClass(schedule.productFeeClass);
		}
		if (!StringUtil.isEmpty(schedule.tranType)) {
			this.selectTransType(schedule.tranType);
		}
		if (!StringUtil.isEmpty(schedule.tranOccur)) {
			this.selectTransOcc(schedule.tranOccur);
		}
		if (!StringUtil.isEmpty(schedule.salesChannel)) {
			this.selectSalesChannel(schedule.salesChannel);
		}
		if (!StringUtil.isEmpty(schedule.marinaRateType)) {
			this.selectMarinaRateType(schedule.marinaRateType);
		}

		clickGo();
		ajax.waitLoading();
		waitLoading();
	}

	public List<String> getFeeID() {
		List<String> feeIDList = new ArrayList<String>();

		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Fee Schedule ID.*", false));
		IHtmlTable table = (IHtmlTable) objs[objs.length - 1];
		for (int i = 1; i < table.rowCount(); i++) {
			feeIDList.add(table.getCellValue(i, 1));
		}

		return feeIDList;
	}

	/**
	 * This method used to click specific threshold schedule
	 * 
	 * @param id
	 *            schedule id
	 */
	public void clickThreshold(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}

	public void changeThresholdSchduleStatus(String scheduleId, String status) {
		changeThresholdSchduleStatus(scheduleId, status, true);
	}
	
	/**
	 * This method used to verify Activate or deactivate schedule correct
	 * 
	 * @param scheduleId
	 * @param status
	 */
	public void changeThresholdSchduleStatus(String scheduleId, String status, boolean isOverrideExisting) {
		logger.info("Start to Change RA Fee Threshold Schedule(ID#="
				+ scheduleId + ") Status to " + status + "!");

		String tmpID="";
		ArrayList<String> ids=new ArrayList<String>();
		if (!isThresholdScheduleSearched(scheduleId)) {
			searchBySheduleId(scheduleId);
		}
		// selectFirstScheduleCheckBox();
		selectAllCheckbox();
		if (status.equalsIgnoreCase("Active")) {
			clickActivate();
			ajax.waitLoading();
			browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Activate");
			//HtmlObject[] errorMsg = browser.getHtmlObject(".id", "com.reserveamerica.common.BaseEx");
			String errorMsg = "";
			if(this.isErrorMsgExists()) {
				errorMsg = this.getErrorMsg();
			}
			if(!StringUtil.isEmpty(errorMsg) && isOverrideExisting) {
				logger.info("There is an error when activate the RA fee threshold schedule: -->" + errorMsg);
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(errorMsg);
				while (m.find()) {
				    ids.add(m.group(0));
				  }
				if(ids.size()>1) {// now we just solve that there are 2 IDs in the error msg. First is current, second is old.
					tmpID = ids.get(ids.size()-1);//tmpID.substring(tmpID.indexOf(" ")).trim();
					changeThresholdSchduleStatus(tmpID,"Inactive");
					changeThresholdSchduleStatus(scheduleId,"Active");
				}
			}
			
		} else if (status.equalsIgnoreCase("Inactive")) {
			clickDeactivate();
		}
		waitLoading();
	}

	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "NOTSET");
	}
	
	/**
	 * get error message.
	 * @return
	 */
	public String getErrorMsg(){
		 IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		 if(objs.length<1){
			 throw new ErrorOnPageException("No error message was found");
		 }
		 String errorMsg = objs[0].getProperty(".text");
		 Browser.unregister(objs);
		 return errorMsg;
	}
	/**
	 * verify whether or not the special fee schedule is active status.
	 * 
	 * @param feeSchID
	 *            - fee schedule id
	 * @return true - active; false - inactive
	 */
	public boolean isFeeScheduleActive(String scheduleID) {
		boolean toReturn = false;
		String status = "";

		if (!isThresholdScheduleSearched(scheduleID)) {
			searchBySheduleId(scheduleID);
		}
		// HtmlObject[] objs = browser.getTableTestObject(".text", new
		// RegularExpression("^Fee Schedule ID Active.*", false));
		IHtmlObject objs[] = browser.getTableTestObject(".id",new RegularExpression(
				"(Slip)?RAFee(Threshold)?ScheduleSearchResultGrid_LIST", false));
		if (objs.length < 0) {
			throw new ItemNotFoundException(
					"Can't find RA Fee Threshold schedule list table object.");
		}
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

	public boolean isThresholdScheduleSearched(String id) {
		boolean exists = browser.checkHtmlObjectExists(".class", "Html.A",
				".text", id);
		if (exists) {
			IHtmlObject objs[] = browser.getTableTestObject(".id",new RegularExpression(
					"(Slip)?RAFee(Threshold)?ScheduleSearchResultGrid_LIST",
					false));
			if (objs.length < 0) {
				throw new ItemNotFoundException(
						"Can't find RA Fee Threshold schedule list table object.");
			}
			IHtmlTable table = (IHtmlTable) objs[0];
			int rowCount = table.rowCount();
			Browser.unregister(objs);

			return rowCount == 2;
		}

		return false;
	}

	/**
	 * Verify specific schedule status is given status
	 * 
	 * @param scheduleId
	 * @param status
	 */
	public void verifyStatus(String scheduleId, String status) {
		logger.info("Start to verify schedule " + scheduleId + " is " + status);

		if (!isThresholdScheduleSearched(scheduleId)) {
			searchBySheduleId(scheduleId);
		}
		String isActive = "No";
		if (status.equalsIgnoreCase("Inactive")) {
			isActive = "No";
		} else if (status.equalsIgnoreCase("Active")) {
			isActive = "Yes";
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
		verifyScheduleInfo("Active", isActive);
	}

	/**
	 * This method used to list all existing schedule
	 * 
	 */
	public void searchWithoutAnyCriteria() {
		clearAllCriteria();
		clickGo();
		waitLoading();
		RegularExpression rex = new RegularExpression(
				"( )?Fee Schedule ID( )?Active( )?Location.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
		int num = tableGrid.rowCount();
		Browser.unregister(objs);
		if (num < 2) {
			throw new ItemNotFoundException("No RA Fee Thresholds Found!");
		}
	}

	public ScheduleData getThresholdScheduleInfo(String id) {
		if (!isThresholdScheduleSearched(id)) {
			searchBySheduleId(id);
		}
		IHtmlObject objs[] = browser.getTableTestObject(".id",new RegularExpression(
				"(Slip)?RAFee(Threshold)?ScheduleSearchResultGrid_LIST", false));
		if (objs.length < 0) {
			throw new ItemNotFoundException(
					"Can't find RA Fee Threshold schedule list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowIndex = table.findRow(1, id);
		List<String> rowValues = table.getRowValues(rowIndex);

		ScheduleData schedule = new ScheduleData();
		String idOnUI = rowValues.get(table.findColumn(0, FEE_SCHEDULE_ID_COL));
		if (!idOnUI.equals(id)) {
			throw new ErrorOnPageException(
					"The searched RA Fee Threshold schedule is incorrect.");
		}
		schedule.scheduleId = id;
		schedule.activeStatus = rowValues.get(table.findColumn(0, ACTIVE_COL));
		schedule.location = rowValues.get(table.findColumn(0, LOCATION_COL));
		schedule.productCategory = rowValues.get(table.findColumn(0,
				PRODUCT_CATEGORY_COL));
		if (schedule.productCategory.equalsIgnoreCase("Slip")) {
			schedule.dock = rowValues.get(table.findColumn(0, DOCK_AREA_COL));
		} else {
			schedule.loop = rowValues.get(table.findColumn(0,
					LOOP_AREA_VENUE_COL));
		}
		schedule.product = rowValues.get(table.findColumn(0,
				PRODUCT_OR_PRODUCT_GROUP_COL));
		schedule.productGroup = schedule.product;
		schedule.effectiveDate = rowValues.get(table.findColumn(0,
				EFFECTIVE_START_DATE_COL));
		schedule.currentCounter = rowValues.get(table.findColumn(0,
				CURRENT_COUNT_COL));
		schedule.productFeeClass = rowValues.get(table.findColumn(0,
				PRODUCT_FEE_CLASS_COL));
		schedule.tranType = rowValues.get(table.findColumn(0,
				TRANSACTION_TYPE_COL));
		schedule.tranOccur = rowValues.get(table.findColumn(0,
				TRANSACTION_OCCURRENCE_COL));
		schedule.salesChannel = rowValues.get(table.findColumn(0,
				SALES_CHANNEL_COL));

		Browser.unregister(objs);

		return schedule;
	}

	public boolean compareScheduleInfo(ScheduleData expected) {
		logger.info("Compare RA Fee Threshold schedule(ID#="
				+ expected.scheduleId + ") list info.");

		ScheduleData actual = getThresholdScheduleInfo(expected.scheduleId);
		boolean result = true;
		result &= MiscFunctions.compareResult("Threshold Schedule ID",
				expected.scheduleId, actual.scheduleId);
		result &= MiscFunctions.compareResult("Status", expected.activeStatus,
				actual.activeStatus);
		result &= MiscFunctions.compareResult("Location", expected.location,
				actual.location);
		if (expected.productCategory.equalsIgnoreCase("Slip")) {
			result &= MiscFunctions.compareResult("Dock/Area", expected.dock,
					actual.dock);
		} else {
			result &= MiscFunctions.compareResult("Loop/Area/Venue",
					expected.loop, actual.loop);
		}
		result &= MiscFunctions.compareResult("Product Category",
				expected.productCategory, actual.productCategory);
		if (!expected.productGroup.equalsIgnoreCase("All")
				&& expected.product.equalsIgnoreCase("All")) {
			result &= MiscFunctions.compareResult("Product or Product Group",
					expected.productGroup, actual.productGroup);
		} else {
			result &= expected.product.contains(actual.product);
		}
		result &= MiscFunctions.compareResult("Effective (Start) Date",
				expected.effectiveDate, actual.effectiveDate);
		result &= MiscFunctions.compareResult("Current Count",
				expected.currentCounter, actual.currentCounter);
		result &= MiscFunctions.compareResult("Product Fee Class",
				expected.productFeeClass, actual.productFeeClass);
		result &= MiscFunctions.compareResult("Transaction Type",
				expected.tranType, actual.tranType);
		result &= MiscFunctions.compareResult("Transaction Occurrence",
				expected.tranOccur, actual.tranOccur);
		result &= MiscFunctions.compareResult("Sales Channel",
				expected.salesChannel.replace(" ", ""),
				actual.salesChannel.replace("Mgr", ""));

		return result;
	}

	public void verifyThresholdListInfo(ScheduleData expected) {
		if (!compareScheduleInfo(expected)) {
			throw new ErrorOnPageException(
					"RA Fee Threshold schedule list info are NOT correct.");
		} else
			logger.info("RA Fee Threshold schedule list info are correct.");
	}

	/**
	 * Verify specific column value is the same with given value
	 * 
	 * @param colName
	 *            column Name
	 * @param value
	 */
	public void verifyScheduleInfo(String colName, String value) {
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(
				"Fee Schedule ID( )?Active( )?Location.*", false);
		IHtmlObject[] objs = null;
		do {
			objs = browser.getTableTestObject(".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
			if (tableGrid.rowCount() > 1) {
				for (int i = 1; i < tableGrid.rowCount(); i++) {
					if (null != tableGrid.getCellValue(i, colNum)) {
						if (!tableGrid.getCellValue(i, colNum).toString()
								.trim().equals(value)) {
							Browser.unregister(objs);
							logger.error("Schedule Info "
									+ tableGrid.getCellValue(i, colNum)
									+ " is not Correct! ");
							throw new ItemNotFoundException(
									tableGrid.getCellValue(i, colNum)
											+ " is different with given value "
											+ value);
						}
					}
				}
			} else {
				Browser.unregister(objs);
				throw new ItemNotFoundException("No Schedule Found.");
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
			((ILink) objs[0]).click();
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
		RegularExpression rex = new RegularExpression(
				"Fee Schedule ID( )?Active( )?Location.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString()
						.equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	public void verifySearchResults(String val, String col) {
		List<String> values=this.getColumnValues(col);
		for (String value : values) {
			if (!value.contains(val)) {
				throw new ErrorOnPageException(
						"Fee schedule column content is not correct", val,
						value);
			}
		}

	}
	
	public List<String>  getColumnValues(String colName){
		List<String> values = new ArrayList<String>();
		do {
			RegularExpression rex = new RegularExpression(
					"Fee Schedule ID( )?Active( )?Location.*", false);
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
					".text", rex);
			if (null == objs || objs.length < 0) {
				throw new ErrorOnPageException(
						"Can not find the search table...");
			}
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
			int colNum = tableGrid.findColumn(0, colName);

			if (tableGrid.rowCount() > 1) {
				for (int i = 1; i < tableGrid.rowCount(); i++) {
					values.add(tableGrid.getCellValue(i, colNum));
				}
			}
			Browser.unregister(objs);
		} while (pageCom.clickNext());
		
		return values;
	}

	public void verifySchdInSearchList(String id) {
		boolean foundSchd = false;
		do {
			RegularExpression rex = new RegularExpression(
					"Fee Schedule ID( )?Active( )?Location.*", false);
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
					".text", rex);
			if (null == objs || objs.length < 0) {
				throw new ErrorOnPageException(
						"Can not find the search table...");
			}
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 1).toString().equals(id)) {
					logger.info("Fee schedule :" + id
							+ " is in the search result!");
					foundSchd = true;
					break;
				}
			}
			Browser.unregister(objs);
		} while (pageCom.clickNext());
		
		if (!foundSchd) {
			throw new ItemNotFoundException(
					"Fee schedule is not in the searching list");
		}
	}

	public String getErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".id",
				"NOTSET");
	}
	
	public String getFirstFeeScheduleID(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("RAFeeThresholdScheduleSearchResultGrid_LIST|SlipRAFeeScheduleSearchResultGrid_LIST",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can not find the table...");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		String id=table.getCellValue(1, 1);
		
		return id;
	}
	
	public void verifyColNameDisplay(String colName) {
		logger.info("Verify column name "+colName+" display on page");
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("RAFeeThresholdScheduleSearchResultGrid_LIST|SlipRAFeeScheduleSearchResultGrid_LIST", false));
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
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("RAFeeThresholdScheduleSearchResultGrid_LIST|SlipRAFeeScheduleSearchResultGrid_LIST", false));
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
