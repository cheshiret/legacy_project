/*
 * Created on Sep 27, 2009
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author vzhao You can access this page by selecting 'Discount' from top menu,
 *         and then click on 'Discount Schedules' tab in Finance Manager
 * 
 */
public class FinMgrDiscountSchedulePage extends FinanceManagerPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDiscountSchedulePage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDiscountSchedulePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDiscountSchedulePage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrDiscountSchedulePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New");
	}

	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	public void clickDeactive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search",false));
	}

	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	public void clickDiscounts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Discounts");
	}

	public void selectSearchType(String searchType) {
		RegularExpression reg = new RegularExpression("DiscountScheduleSearchCriteriaExt-\\d+.searchBy", false);
		browser.selectDropdownList(".id", reg, searchType);
	}
	
	public String getSearchType(){
		RegularExpression reg = new RegularExpression("DiscountScheduleSearchCriteriaExt-\\d+.searchBy", false);
		return browser.getDropdownListValue(".id", reg);
	}
	
	public String getSearchValue(){
		RegularExpression reg = new RegularExpression("DiscountScheduleSearchCriteriaExt.searchByValue", false);
		return browser.getTextFieldValue(".id", reg);
	}

	public void deselectSearchType() {
		RegularExpression reg = new RegularExpression("DiscountScheduleSearchCriteriaExt-\\d+.searchBy", false);

		browser.selectDropdownList(".id", reg, 0);
	}

	public void setSearchValue(String value) {
		RegularExpression reg = new RegularExpression("DiscountScheduleSearchCriteriaExt.searchByValue", false);

		browser.setTextField(".id", reg, value);
	}

	public void selectDateType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.dateType|DiscountScheduleSearchCriteriaExt-\\d+\\.dateType", false),
				type);
	}

	public void deselectDateType() {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.dateType|DiscountScheduleSearchCriteriaExt-\\d+\\.dateType", false), 0);
	}

	public void setStartDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id",
				"discnt.schedule.search.startDate_ForDisplay", date);
	}

	public void setEndDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id",
				"discnt.schedule.search.endDate_ForDisplay", date);
	}
	
	public void setFromDate(String fromDate) {
		browser.setCalendarField(".id", new RegularExpression("DiscountScheduleSearchCriteriaExt.startDateValue_ForDisplay", false), fromDate);
	}
	
	public void setToDate(String toDate) {
		browser.setCalendarField(".id", new RegularExpression("DiscountScheduleSearchCriteriaExt.endDateValue_ForDisplay", false), toDate);
	}

	public void selectStatus(String type) {
		browser
				.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search.status)|(DiscountScheduleSearchCriteriaExt.status)",false),
						type);
	}

	public void deselectStatus() {
		browser.selectDropdownList(".id",  new RegularExpression("(discnt.schedule.search.status)|(DiscountScheduleSearchCriteriaExt.status)",false), 0);
	}

	public void selectProductCategory(String category) {
		browser.selectDropdownList(".id",  new RegularExpression("(discnt.schedule.search.prdCategory)|(DiscountScheduleSearchCriteriaExt-\\d+.productCategory)",false),
				category);
	}

	public void deselectProductCategory() {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search.prdCategory)|(DiscountScheduleSearchCriteriaExt-\\d+.productCategory)",false),
				0);
	}

	public void selectRateType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).rateType", false),
				type);
	}

	public void deselectRateType() {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).rateType", false), 0);
	}

	public void selectFeeType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search.feeType)|(DiscountScheduleSearchCriteriaExt.feeType)",false),
				type);
	}

	public void deselectFeeType() {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search.feeType)|(DiscountScheduleSearchCriteriaExt.feeType)",false), 0);
	}

	public void selectRateApply(String type) {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).unitType", false),
				type);
	}

	public void deselectRateApply() {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).unitType", false), 0);
	}

	public void selectAdditionalDiscount(String value) {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).additional", false),
				value);
	}

	public void deselectAdditionalDiscount() {
		browser.selectDropdownList(".id", new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).additional", false),
				0);
	}

	public void selectAutomaticDiscount(String value) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.automatic|DiscountScheduleSearchCriteriaExt.automaticDiscount", false),
				value);
	}

	public void deselectAutomaticDiscount() {
		browser
				.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.automatic|DiscountScheduleSearchCriteriaExt.automaticDiscount", false),
						0);
	}

	public void selectDisplayDiscount(String value) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.display|DiscountScheduleSearchCriteriaExt.displayDiscount", false),
				value);
	}

	public void deselectDisplayDiscount() {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.display|DiscountScheduleSearchCriteriaExt.displayDiscount", false), 0);
	}

//	public void selectBuyXGetYDiscount(String option) {
//		browser.selectDropdownList(".id", "DiscountScheduleSearchCriteriaExt.bogoDiscount", option);
//	}
//	
//	public void deselectBuyXGetYDiscount() {
//		browser.selectDropdownList(".id", "DiscountScheduleSearchCriteriaExt.bogoDiscount", 0);
//	}
	
	public void selectTransType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.transType|DiscountScheduleSearchCriteriaExt.transanctionType", false),
				type);
	}

	public void deselectTransType() {
		browser
				.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.transType|DiscountScheduleSearchCriteriaExt.transanctionType", false),
						0);
	}

	public void selectTransOcc(String type) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.transOccu|DiscountScheduleSearchCriteriaExt.transactionOccurence", false),
				type);
	}

	public void deselectTransOcc() {
		browser
				.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.transOccu|DiscountScheduleSearchCriteriaExt.transactionOccurence", false),
						0);
	}

	public void selectSalesChannel(String channel) {
		browser.selectDropdownList(".id",
				new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).salesChannel", false), channel);
	}

	public void deselectSalesChannel() {
		browser.selectDropdownList(".id",
				new RegularExpression("(discnt.schedule.search|DiscountScheduleSearchCriteriaExt).salesChannel", false), 0);
	}

	public void selectCustomerType(String type) {
		browser.selectDropdownList(".id",
				new RegularExpression("discnt.schedule.search.customerType|DiscountScheduleSearchCriteriaExt.customerTypeId", false), type);
	}

	public void deselectCustomerType() {
		browser.selectDropdownList(".id",
				new RegularExpression("discnt.schedule.search.customerType|DiscountScheduleSearchCriteriaExt.customerTypeId", false), 0);
	}

	public void selectCustomerPass(String value) {
		browser.selectDropdownList(".id",
				new RegularExpression("discnt.schedule.search.customerPass|DiscountScheduleSearchCriteriaExt.customerPassId", false), value);
	}

	public void deselectCustomerPass() {
		browser.selectDropdownList(".id",
				new RegularExpression("discnt.schedule.search.customerPass|DiscountScheduleSearchCriteriaExt.customerPassId", false), 0);
	}

	public void selectMemberType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.memberType|DiscountScheduleSearchCriteriaExt.member", false),
				type);
	}

	public void deselectMemberType() {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.memberType|DiscountScheduleSearchCriteriaExt.member", false),
				0);
	}

	public void selectSeason(String type) {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.seasonType|DiscountScheduleSearchCriteriaExt.seasonID", false),
				type);
	}

	public void deselectSeason() {
		browser.selectDropdownList(".id", new RegularExpression("discnt.schedule.search.seasonType|DiscountScheduleSearchCriteriaExt.seasonID", false),
				0);
	}

	public void selectInOutState(String type) {
		browser.selectDropdownList(".id",
				new RegularExpression("discnt.schedule.search.outOfStateType|DiscountScheduleSearchCriteriaExt.outOfState", false), type);
	}

	public void deselectInOutState() {
		browser.selectDropdownList(".id",
				new RegularExpression("discnt.schedule.search.outOfStateType|DiscountScheduleSearchCriteriaExt.outOfState", false), 0);
	}

	public void selectMarinaRateType(String rateType) {
		browser.selectDropdownList(".id", new RegularExpression("SlipDiscountScheduleSearchCriteria-\\d+\\.marinaRateType", false), rateType);
	}
	
	public void deselectMarinaRateType() {
		browser.selectDropdownList(".id", new RegularExpression("SlipDiscountScheduleSearchCriteria-\\d+\\.marinaRateType", false), 0);
	}
	
	public void selectBoatCategory(String boatCategory) {
		browser.selectDropdownList(".id", new RegularExpression("SlipDiscountScheduleSearchCriteria.boatCategory", false), boatCategory);
	}
	
	public void deselectBoatCategory() {
		browser.selectDropdownList(".id", new RegularExpression("SlipDiscountScheduleSearchCriteria.boatCategory", false), 0);
	}
	
	public void gotoDiscountMainPg() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Discounts");
	}

	public void gotoDiscountSchDetailPg(String schID) {
		browser.clickGuiObject(".class", "Html.A", ".text", schID, true);
	}
	
	public void selectAllSchedule(){
		 browser.selectCheckBox(".class","Html.INPUT.checkbox",".name","all_slct");
	}
	
	public void clickFirstDiscountSchedule(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+", false), true);
	}

	public void searchByScheduleId(String scheduleId) {
		this.searchBySearchByAndValue("Discount Schedule ID", scheduleId);
	}
	
	public void searchByDiscountName(String discount) {
		this.searchBySearchByAndValue("Discount Name", discount);
	}
	
	public void searchByProduct(String product) {
		this.searchBySearchByAndValue("Product", product);
	}
	
	private void searchBySearchByAndValue(String searchType, String searchValue) {
		selectSearchType(searchType);
		setSearchValue(searchValue);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	private void searchBySearchValueAndStatus(String searchType, String searchValue, String status){
		selectSearchType(searchType);
		setSearchValue(searchValue);
		this.selectStatus(status);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}


	public void selectScheduleCheckBox(String scheduleId) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("DiscountScheduleSearchResultGrid_LIST", false));
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int row = tableGrid.findRow(1, scheduleId);
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), row-1);
		
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(".id", "NOTSET");// id changed by Sophia "com.reserveamerica.system.product.api.ActiveDiscountScheduleConfictEx"
	}//added by pzhu 2012/2/20
	
	/**
	 * This method used to change specific schedule status to given status
	 * 
	 */
	public void changeDiscountStatus(String scheduleId, String status) {
		logger.info("Start to change schedule Status to " + status);
		String tmpStr = "";
		String tmpID = "";
		ArrayList<String> ids = new ArrayList<String>();
		searchByScheduleId(scheduleId);
		this.waitExist(OrmsConstants.VERY_LONG_SLEEP + 400); //ms
		selectScheduleCheckBox(scheduleId);
		if (status.equalsIgnoreCase("Inactive")) {
			clickDeactive();
		} else if (status.equalsIgnoreCase("Active")) {
			clickActive();
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
					changeDiscountStatus(tmpID, "Inactive");
					changeDiscountStatus(scheduleId, "Active");
				}

			}
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
		
		
		waitLoading();
		
		String errorMsg = getErrorMsg();
		if (StringUtil.notEmpty(errorMsg)) {
			throw new RuntimeException(errorMsg);
		}
	}

	private void waitExist(int timeout) {
		browser.waitExists(timeout);
	}

	/**
	 * Verify discount status equals given status
	 * 
	 * @param distName
	 * @param status
	 */
	public void verifyStatus(String scheduleId, String status) {
		logger.info("Start to Verify Schedule " + scheduleId + " is " + status);

		searchByScheduleId(scheduleId);
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
	 * Verify specific column value is the same with given value
	 * 
	 * @param colName
	 *            column Name
	 * @param value
	 */
	public void verifyScheduleInfo(String colName, String value) {
		logger.info("Start to verify " + colName + " is " + value);

		int colNum = getColNum(colName);
		IHtmlObject[] objs= browser.getTableTestObject(".id", new RegularExpression("DiscountScheduleSearchResultGrid_LIST", false));
		
		IHtmlTable table = (IHtmlTable) objs[0];
		if (table.rowCount() > 1) {
			for (int i = 1; i < table.rowCount(); i++) {
				if (null != table.getCellValue(i, colNum)) {
					if (!table.getCellValue(i, colNum).trim().equals(value)) {
						Browser.unregister(objs);
						throw new ItemNotFoundException(table.getCellValue(i,
								colNum)
								+ " is different with given value " + value);
					}
				}
			}
		} else {
			Browser.unregister(objs);
			throw new ItemNotFoundException("No Schedule Found.");
		}
		Browser.unregister(objs);
	}

	/**
	 * Get Column Number by Column Name
	 * 
	 * @param colName
	 * @return Column Number
	 */
	public int getColNum(String colName) {
		IHtmlObject[] objs= browser.getTableTestObject(".id", new RegularExpression("DiscountScheduleSearchResultGrid_LIST", false));

		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString().equalsIgnoreCase(
						colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	/**
	 * This method goes to retrieve the discount schedule id by given
	 * product/production group
	 * 
	 * @param product
	 *            - the specified product or product group
	 * @return - the retrieved schedule id
	 */
	public String getActiveSchByProdOrProdGroup(String product) {
		int schColNum = -1;
		int activeColNum = -1;
		int prodColNum = -1;
		String scheduleID = "";
		boolean found=false;
		IHtmlObject[] discSchTable = browser.getTableTestObject(".text",
				new RegularExpression("^Discount Schedule#.+", false));
		IHtmlTable tableGrid = (IHtmlTable) discSchTable[discSchTable.length-1];

		for (int i = 0; i < tableGrid.columnCount() && !found; i++) {
			if (tableGrid.getCellValue(0, i).toString().equals("Discount Schedule#"))
				schColNum = i;
			else if (tableGrid.getCellValue(0, i).toString().equals("Active"))
				activeColNum = i;
			else if (tableGrid.getCellValue(0, i).toString().equals("Product or Product Group"))
				prodColNum = i;
			
			found=schColNum>=0 && activeColNum>=0 && prodColNum>=0;
		}
		found=false;
		for (int row = 0; row < tableGrid.rowCount() && !found; row++) {
			String status = tableGrid.getCellValue(row, activeColNum)
					.toString();
			String prod = tableGrid.getCellValue(row, prodColNum).toString();
			if (status.equalsIgnoreCase("Yes")
					&& prod.equalsIgnoreCase(product)) {
				scheduleID = tableGrid.getCellValue(row, schColNum).toString();
				found=true;
			} else if (row == tableGrid.rowCount() - 1
					&& !status.equalsIgnoreCase("Yes")
					&& !prod.equalsIgnoreCase(product))
				throw new ItemNotFoundException(
						"No active discount schedule for specified product "
								+ product);
		}
		Browser.unregister(discSchTable);

		if(StringUtil.isEmpty(scheduleID)) {
			throw new ItemNotFoundException("Failed to retrieve a valid schedue id.");
		}
		return scheduleID;
	}

	/**
	 * Set up the search criteria for fee schedule
	 * 
	 * @param schData
	 */
	public void setupSearchCriteria(FeeScheduleData schData) {
		this.selectSearchType(schData.searchType);
		this.setSearchValue(schData.searchValue);

		if (null != schData.dateType && schData.dateType.length() > 0) {
			this.selectDateType(schData.dateType);
			this.setStartDate(schData.fromDate);
			this.setEndDate(schData.toDate);
		}

		if (null != schData.feeType && schData.feeType.length() > 0) {
			this.selectFeeType(schData.feeType);
		}

		if (null != schData.activeStatus && schData.activeStatus.length() > 0) {
			this.selectStatus(schData.activeStatus);
		}

		if (null != schData.custType && schData.custType.length() > 0) {
			this.selectCustomerType(schData.custType);
		}

		if (null != schData.custPass && schData.custPass.length() > 0) {
			this.selectCustomerPass(schData.custPass);
		}
	}
	
	public void setupSearchCriteria(ScheduleData schedule) {
		if(!StringUtil.isEmpty(schedule.searchBy)) {
			this.selectSearchType(schedule.searchBy);
		}
		if(!StringUtil.isEmpty(schedule.searchValue)) {
			this.setSearchValue(schedule.searchValue);
		}
		if(!StringUtil.isEmpty(schedule.dateType)) {
			this.selectDateType(schedule.dateType);
		}
		if(!StringUtil.isEmpty(schedule.fromDate)) {
			this.setFromDate(schedule.fromDate);
		}
		if(!StringUtil.isEmpty(schedule.toDate)) {
			this.setToDate(schedule.toDate);
		}
		if(!StringUtil.isEmpty(schedule.activeStatus)) {
			this.selectStatus(schedule.activeStatus);
		}
		if(!StringUtil.isEmpty(schedule.productCategory)) {
			this.selectProductCategory(schedule.productCategory);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(schedule.feeType)) {
			this.selectFeeType(schedule.feeType);
		}
		if(!StringUtil.isEmpty(schedule.rateType)) {
			this.selectRateType(schedule.rateType);
		}
		//Rate Applies
		if(!StringUtil.isEmpty(schedule.appRate)) {
			this.selectRateApply(schedule.appRate);
		}
		if(!StringUtil.isEmpty(schedule.additionalDiscount)) {
			this.selectAdditionalDiscount(schedule.additionalDiscount);
		}
		if(!StringUtil.isEmpty(schedule.automaticDiscount)) {
			this.selectAutomaticDiscount(schedule.automaticDiscount);
		}
		if(!StringUtil.isEmpty(schedule.displayDiscount)) {
			this.selectDisplayDiscount(schedule.displayDiscount);
		}
		if(!StringUtil.isEmpty(schedule.buyXGetYDiscount)) {
			this.selectBuyXGetYDisct(schedule.buyXGetYDiscount);
		}
		if(!StringUtil.isEmpty(schedule.tranType)) {
			this.selectTransType(schedule.tranType);
		}
		if(!StringUtil.isEmpty(schedule.tranOccur)) {
			this.selectTransOcc(schedule.tranOccur);
		}
		if(!StringUtil.isEmpty(schedule.salesChannel)) {
			this.selectSalesChannel(schedule.salesChannel);
		}
		if(!StringUtil.isEmpty(schedule.customerType)) {
			this.selectCustomerType(schedule.customerType);
		}
		if(!StringUtil.isEmpty(schedule.custPass)) {
			this.selectCustomerPass(schedule.custPass);
		}
		if(!StringUtil.isEmpty(schedule.member)) {
			this.selectMemberType(schedule.member);
		}
		if(!StringUtil.isEmpty(schedule.season)) {
			this.selectSeason(schedule.season);
		}
		if(!StringUtil.isEmpty(schedule.state)) {
			this.selectInOutState(schedule.state);
		}
		if(!StringUtil.isEmpty(schedule.marinaRateType)) {
			this.selectMarinaRateType(schedule.marinaRateType);
		}
		if(!StringUtil.isEmpty(schedule.boatCategory)) {
			this.selectBoatCategory(schedule.boatCategory);
		}
	}

	/**
	 * Verify the No matches Found message displays after searching.
	 * 
	 * @return true - not found schedule; false - found schedule
	 */
	public boolean isNoScheduleFound() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "Messages");

		String msg = "Found";
		if (objs.length > 0) {
			msg = objs[0].getProperty(".text").toString();
		}
		Browser.unregister(objs);

		return msg.equalsIgnoreCase("No Matches Found");
	}

	/**
	 * Verify whether or not the disocunt schedule exist for given discount
	 * name.
	 * 
	 * @param disName
	 *            - disocunt name
	 * @return
	 */
	public boolean isSpecialDiscountExists(String disName) {
		boolean toReturn = false;
		IHtmlObject[] discSchTable = browser.getTableTestObject(".text",
				new RegularExpression("^Discount Schedule#.+", false));
		IHtmlTable tableGrid = (IHtmlTable) discSchTable[discSchTable.length-1];

		int disNameCol = tableGrid.findColumn(0, "Discount Name");

		String discountName = "";
		for (int row = 0; row < tableGrid.rowCount(); row++) {
			discountName = tableGrid.getCellValue(row, disNameCol);
			if (discountName.equalsIgnoreCase(disName)) {
				toReturn = true;
				break;// only check one record exists
			}
		}

		Browser.unregister(discSchTable);
		return toReturn;
	}

	public boolean isDiscountScheduleExists(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	public void verifyDiscountScheduleExists(String id) {
		if(!this.isDiscountScheduleExists(id)) {
			throw new ItemNotFoundException("Cannot find discount schedule - " + id);
		} else logger.info("Found discount schedule - " + id);
	}
	
	/**
	 * verify whether or not the special discount schedule is active status.
	 * 
	 * @param discountSchId
	 *            -discount schedule Name
	 * @return true - active; false - inactive
	 */
	public boolean isDiscountSchActive(String discountSchId, String disName) {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^( )*Discount Schedule# Active.*",
						false));

		String temp = objs[0].getProperty(".text").toString();
		String status = temp.split(discountSchId)[1].split(disName)[0].trim();

		Browser.unregister(objs);
		if (status.equalsIgnoreCase("Yes")) {
			toReturn = true;
		}
		return toReturn;
	}
	
	/**
	 * Search discount schedule 
	 * @param fee
	 */
	public void searchDiscountSch(FeeScheduleData fee){
		this.selectSearchType(fee.searchType); 
		this.setSearchValue(fee.searchValue);
		this.selectStatus(fee.activeStatus);
		this.selectProductCategory(fee.productCategory);
		ajax.waitLoading();
		this.selectFeeType(fee.feeType);
		this.clickGo();
		this.waitLoading();
	}
	
	public void searchDiscountSchedule(ScheduleData schedule) {
		this.setupSearchCriteria(schedule);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * get discount schedules in current page
	 * @return
	 */
	public List<FeeScheduleData> getDiscountSchdules(){
		List<FeeScheduleData> list=new ArrayList<FeeScheduleData>();
		IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("^Discount Schedule# ?Active.*",false));
		IHtmlTable table=(IHtmlTable)objs[objs.length-1];
		int rowCount=table.rowCount();
		
		for(int i=1;i<rowCount;i++){
			FeeScheduleData fee=new FeeScheduleData();
			fee.feeSchdId=table.getCellValue(i, 1);
			fee.activeStatus=table.getCellValue(i, 2);
			fee.discountName=table.getCellValue(i, 3);
			fee.location=table.getCellValue(i, 4);
			fee.productCategory=table.getCellValue(i, 5);
			fee.prodOrProdgroup=table.getCellValue(i, 6);
			fee.feeType=table.getCellValue(i, 7);
			fee.effectDate=table.getCellValue(i, 8);
			fee.startInv=table.getCellValue(i, 9);
			fee.endInv=table.getCellValue(i, 10);
			fee.rate=table.getCellValue(i, 11);
			fee.rateType=table.getCellValue(i, 12);
			fee.tranType=table.getCellValue(i, 18);
			fee.salesChannel=table.getCellValue(i, 20);
			fee.custType=table.getCellValue(i, 21);
			fee.custPass = table.getCellValue(i, 22);
			fee.state=table.getCellValue(i, 25);
			list.add(fee);
		}
		return list;
	}
	
	 public List<ScheduleData> getAllRecordOnPage(){
			IHtmlObject objs[] = null;
			IHtmlTable table = null;
			List<ScheduleData> records = new ArrayList<ScheduleData>();
			int rows;
			int columns;
			ScheduleData info;
			
			//RegularExpression rex = new RegularExpression("^Discount Schedule# Active.*",false);
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", "DiscountScheduleSearchResultGrid_LIST");
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't find discount schedule table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				info = new ScheduleData();
				info.scheduleId = table.getCellValue(i, table.findColumn(0, "Discount Schedule#"));
				info.activeStatus = table.getCellValue(i, table.findColumn(0, "Active"));
				info.discountName = table.getCellValue(i, table.findColumn(0, "Discount Name"));
				info.location = table.getCellValue(i, table.findColumn(0, "Location"));
				info.productCategory = table.getCellValue(i, table.findColumn(0, "Product Category"));
				info.product = table.getCellValue(i, 5);
				info.feeType = table.getCellValue(i, table.findColumn(0, "Fee Type"));
				info.effectiveDate = table.getCellValue(i, table.findColumn(0, "Effective Date"));
				info.startDate = table.getCellValue(i, table.findColumn(0, "Inventory Start Date"));
				info.endDate = table.getCellValue(i, table.findColumn(0, "Inventory End Date"));
				info.rate = table.getCellValue(i, table.findColumn(0, "Base Rate"));
				info.custPass = table.getCellValue(i, table.findColumn(0, "Cust Pass"));
				info.accountCode = table.getCellValue(i, table.findColumn(0, "Account"));

				records.add(info);			
			}
			
			
			Browser.unregister(objs);
			return records;
		}
	 
	 public List<String> getAllOptionsForBuyXGetYDisctDropDownList() {
		 return browser.getDropdownElements(".id", "DiscountScheduleSearchCriteriaExt.bogoDiscount");
	 } 
	 
	 public void selectBuyXGetYDisct(String indicator) {
		 browser.selectDropdownList(".id", "DiscountScheduleSearchCriteriaExt.bogoDiscount", indicator);
	 }
	 
	 public List<String> getAllValuesByColName(String colName) {
		 List<String> values = new ArrayList<String>();
		 IHtmlObject[] listTab = browser.getTableTestObject(".id", "DiscountScheduleSearchResultGrid_LIST");
		 if(listTab.length<1)
			 throw new ItemNotFoundException("Could not get list table on page.");
		 IHtmlTable tab = (IHtmlTable)listTab[0];
		 values.addAll(tab.getColumnValues(tab.findColumn(0, colName)));
		 values.remove(0);//remove 0th row value
		 Browser.unregister(listTab);
		 return values;
	 }
	 
	 public void verifySearchCriticalRemaining(String searchType, String searchValue, String discountName){
		 boolean passed = true;
		 passed &= MiscFunctions.compareResult("Search type:", searchType, this.getSearchType());
		 passed &= MiscFunctions.compareResult("Search value:", searchValue, this.getSearchValue());
		 List<ScheduleData> record = this.getAllRecordOnPage();
		 for(int i=0; i<record.size(); i++){
			 passed &= MiscFunctions.compareResult(i+"th discount name:", discountName , record.get(i).discountName);
		 }
		 if(!passed){
			 throw new ErrorOnPageException("The search critical is not maintaining, please check the log above");
		 }
		 logger.info("Search critical is maintaining as previous set, it is correct");
	 }
	 
	 public void changeAllDiscountForProduct(String productName, String status){
		 String searchStatus = status.equalsIgnoreCase("Active")?"Inactive":"Active";
		 this.searchBySearchValueAndStatus("Product",productName,searchStatus);
		 this.selectAllSchedule();
		 List<String> colValues = this.getAllValuesByColName("Product or Product Group");
		 for(String value:colValues) {
			if(!value.equalsIgnoreCase(productName))
				throw new ErrorOnPageException("All column value should be "+productName+", however, there is a value "+value);
		 }
		 if(status.equalsIgnoreCase("Active")){
			 this.clickActive();
		 }else{
			 this.clickDeactive();
		 }
		 ajax.waitLoading();
		 this.waitLoading();
	 }
	 
	 public void deactiveAllDiscountForProduct(String productName){
		this.changeAllDiscountForProduct(productName, "Inactive");
	 }
	 
	 public void activeAllDiscountForProduct(String productName){
		 this.changeAllDiscountForProduct(productName, "Active");
	 }
	 
}
