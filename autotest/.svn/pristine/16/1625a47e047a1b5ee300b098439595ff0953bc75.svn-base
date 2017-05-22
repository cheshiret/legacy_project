/*
 * $Id: FinMgrFeeMainPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.financeManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * Description : XDE Tester Script
 * 
 * @author CGuo
 */
public class FinMgrFeeMainPage extends FinanceManagerPage {

	/**
	 * Script Name : <b>FinMgrFeeMainPage</b> Generated : <b>Oct 6, 2004 8:24:47
	 * PM</b> Description : XDE Tester Script Original Host : WinNT Version 5.1
	 * Build 2600 (Service Pack 2)
	 * 
	 * @since 2004/10/06
	 * @author CGuo
	 */

	/** A handle to the unique Singleton instance. */
	static private FinMgrFeeMainPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrFeeMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrFeeMainPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrFeeMainPage();
		}

		return _instance;
	}

	public boolean exists() {
		boolean flag2= browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^ ?Fee Schedule ID.*",false));
		return flag2;
	}

	/** Click on Active link. */
	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/** Click on Deactivate link. */
	public void clickDeactive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click on Go link. */
	public void clickGo() {
//		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("\\s*Go", false));
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("\\s*Search", false)); // Lesley[20131015]: Go button is changed to Search button in 3.05.00
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/** Click RA Fee Thresholds Tab */
	public void clickRaFeeThresholdTab() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"RA Fee Thresholds");
	}

	/** Click RA Fee Schedules Tab */
	public void clickRaFeeSchedule() {
		browser.clickGuiObject(".class", "Html.A", ".text", "RA Fee Schedules");
	}

	/**
	 * Select search type.
	 * 
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression
				("search_location|FeeSearchRequest.searchBy", false), searchType);
	}

	/** Deselect search type. */
	public void deselectSearchType() {
		browser.selectDropdownList(".id", new RegularExpression
				("search_location|FeeSearchRequest.searchBy", false), 0);
	}

	/**
	 * Fill in search value.
	 * 
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression("search_location_value|FeeSearchRequest.value", false), value, true, 0, IText.Event.LOSEFOCUS);
	}

	/**
	 * Select date type.
	 * 
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.dateBy", type);
	}

	/** Deselect date type. */
	public void deselectDateType() {
		browser.selectDropdownList(".id", "FeeSearchRequest.dateBy", 0);
	}

	/**
	 * Fill in search from date.
	 * 
	 * @param date
	 *            - from date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "FeeSearchRequest.start_ForDisplay", date);
	}                                

	/**
	 * Fill in search end date.
	 * 
	 * @param date
	 *            - end date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "FeeSearchRequest.end_ForDisplay", date);
	}

	/**
	 * Select active or inactive status.
	 * 
	 * @param type
	 */
	public void selectShowStatus(String type) {
		browser.selectDropdownList(".id", new RegularExpression("show_active|(FeeSearchRequest.feeSchdStatus)",false), type);
	}

	/** Deselect show stasue. */
	public void deselectShowStatus() {
		browser.selectDropdownList(".id", new RegularExpression("show_active|(FeeSearchRequest.feeSchdStatus)",false), 0);
	}

	/**
	 * Select product category.
	 * 
	 * @param type
	 */
	public void selectProductCategory(String type) {
		browser.selectDropdownList(".id", new RegularExpression("prd_cat_type|(FeeSearchRequest-\\d+\\.prdGrpCat)",false), type);
	}
	
	public void selectApplicableProductCategory(String prdCat) {
		browser.selectDropdownList(".id", new RegularExpression("FeeSearchRequest.applicableProductCategory",false), prdCat);
	}

	/** Deselect product category. */
	public void deselectProductCategory() {
		browser.selectDropdownList(".id", new RegularExpression("prd_cat_type|(FeeSearchRequest-\\d+\\.prdGrpCat)|FeeSearchRequest.applicableProductCategory",false), 0);
	}

	/**
	 * Select fee type.
	 * 
	 * @param type
	 *            - fee type
	 */
	public void selectFeeType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("fee_type|(FeeSearchRequest.feeType)",false), type);
	}

	/** Deselect fee type. */
	public void deselectFeeType() {
		browser.selectDropdownList(".id", new RegularExpression("fee_type|(FeeSearchRequest.feeType)",false), 0);
	}

	/**
	 * Select unit type.
	 * 
	 * @param type
	 *            - unit type
	 */
	public void selectUnitType(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.unitTypeStr", type);
	}

	/** Deselect unit type. */
	public void deselectUnitType() {
		browser.selectDropdownList(".id", "FeeSearchRequest.unitTypeStr", 0);
	}

	/**
	 * Select ticket category.
	 * 
	 * @param type
	 *            - ticket category
	 */
	public void selectTicketCategory(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.ticketCategory", type);
	}

	/** Deselect ticket category.. */
	public void deselectTicketCategory() {
		browser.selectDropdownList(".id", "FeeSearchRequest.ticketCategory", 0);
	}

	/**
	 * Select permit type.
	 * 
	 * @param type
	 *            - permit type
	 */
	public void selectPermitType(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.permitTyleId", type);
	}

	/** Deselect permit type. */
	public void deselectPermitType() {
		browser.selectDropdownList(".id", "FeeSearchRequest.permitTyleId", 0);
	}

	/**
	 * Select rate type.
	 * 
	 * @param type
	 *            - rate type
	 */
	public void selectRateType(String type) {
		browser.selectDropdownList(".id", "FeeSearchRequest.prdRateType", type);
	}

	/** Deselect rate type. */
	public void deselectRateType() {
		browser.selectDropdownList(".id", new RegularExpression("rate_type|(FeeSearchRequest\\.prdRateType)",false), 0);
	}
	
	public boolean isRateTypeIsExisting(){
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("rate_type|(FeeSearchRequest\\.prdRateType)",false));
	}

	/**
	 * Verify whether or not the rate type dropdown list is disable
	 * 
	 * @return true - enable; false - disable
	 */
	public boolean isRateTypeEnable() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("rate_type|(FeeSearchRequest\\.prdRateType)",false));
		boolean toReturn = true;

		if (objs[0].getProperty(".disabled").toString()
				.equalsIgnoreCase("true")) {
			toReturn = false;
		}

		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Select transaction type.
	 * 
	 * @param type
	 *            - transaction type
	 */
	public void selectTransType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("trans_type|(FeeSearchRequest.transactionTypeID)",false), type);
	}

	/** Deselect transaction type. */
	public void deselectTransType() {
		browser.selectDropdownList(".id", new RegularExpression("trans_type|(FeeSearchRequest.transactionTypeID)",false), 0);
	}

	/**
	 * Select transaction occ type.
	 * 
	 * @param type
	 *            - occ type
	 */
	public void selectTransOcc(String type) {
		browser.selectDropdownList(".id", new RegularExpression("trans_occ_type|(FeeSearchRequest.transactionOccurrenceID)",false), type);
	}

	/** Deselect transaction occ type. */
	public void deselectTransOcc() {
		browser.selectDropdownList(".id", new RegularExpression("trans_occ_type|(FeeSearchRequest.transactionOccurrenceID)",false), 0);
	}

	/**
	 * Select attribution type.
	 * 
	 * @param type
	 *            - attribution type
	 */
	public void selectAttribute(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("search_attr|(FeeSearchRequest.attributeID)",false), 1, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("search_attr|(FeeSearchRequest.attributeID)",false), type, true);
		}
	}

	/** Deselect attribution type. */
	public void deselectAttribute() {
		browser.selectDropdownList(".id", new RegularExpression("search_attr|(FeeSearchRequest.attributeID)",false), 0);
	}

	/**
	 * Select season.
	 * 
	 * @param type
	 *            - season
	 */
	public void selectSeason(String type) {
		browser.selectDropdownList(".id", new RegularExpression("search_season|(FeeSearchRequest.seasonID)",false), type);
	}

	/** Deselect season. */
	public void deselectSeason() {
		browser.selectDropdownList(".id", new RegularExpression("search_season|(FeeSearchRequest.seasonID)",false), 0);
	}

	/**
	 * Select sales channel.
	 * 
	 * @param type
	 *            - sales channel.
	 */
	public void selectSalesChannel(String type) {
		browser.selectDropdownList(".id", new RegularExpression("sales_channel|(FeeSearchRequest.salesChannel)",false), type);
	}

	/** Deselect sales channel. */
	public void deselectSalesChannel() {
		browser.selectDropdownList(".id", new RegularExpression("sales_channel|(FeeSearchRequest.salesChannel)",false), 0);
	}

	/**
	 * Select In or Out state.
	 * 
	 * @param type
	 *            - in or out state
	 */
	public void selectInOutState(String type) {
		browser.selectDropdownList(".id", new RegularExpression("in_out_state|(FeeSearchRequest.outOfState)",false), type);
	}

	/** Deselect In or Out state. */
	public void deselectInOutState() {
		browser.selectDropdownList(".id", new RegularExpression("in_out_state|(FeeSearchRequest.outOfState)",false), 0);
	}

	/**
	 * Select customer type.
	 * 
	 * @param type
	 *            - customer type
	 */
	public void selectCustomerType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("customer_type|(FeeSearchRequest.custTypeID)",false), type);
	}

	/** Deselect customer type. */
	public void deselectCustomerType() {
		browser.selectDropdownList(".id", new RegularExpression("customer_type|(FeeSearchRequest.custTypeID)",false), 0);
	}

	public void selectMarinaRateType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeSearchRequest.marinaRateType",false), 1, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeSearchRequest.marinaRateType",false), type, true);
		}
	}
	
	public void selectRafting(String rafting){
		if(StringUtil.isEmpty(rafting)){
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeSearchRequest.raftingType",false), 1, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeSearchRequest.raftingType",false), rafting, true);
		}
	}
	
	public boolean checkBoatCategoryIsExisting(){
		return browser.checkHtmlObjectDisplayed(".id",new RegularExpression("SlipFeeSearchRequest.boatCategory",false));
	}
	
	public void selectBoatCategory(String boat){
		if(StringUtil.isEmpty(boat)){
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeSearchRequest.boatCategory",false), 1, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeSearchRequest.boatCategory",false), boat, true);
		}
	}

	public void selectAttrType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("FeeSearchRequest.attributeID",false), 1, true);
		}
		browser.selectDropdownList(".id", new RegularExpression("FeeSearchRequest.attributeID",false), type, true);
	}

	/**
	 * Select specific schedule check box
	 * 
	 * @param schdId
	 */
	public void selectScheduleCheckBox(String schdId) {
//		Property[] trPro = new Property[2];
//		trPro[0] = new Property(".class", "Html.TR");
//		trPro[1] = new Property(".text", new RegularExpression(schdId + ".*",
//				false));
//		HtmlObject[] trObjs = browser.getHtmlObject(trPro);
//		if (trObjs.length < 1) {
//			throw new ItemNotFoundException(
//					"Did not fuond tr object. schedule id = " + schdId);
//		}
//
//		Property[] checkPro = new Property[1];
//		checkPro[0] = new Property(".class", "Html.INPUT.checkbox");
//		browser.selectCheckBox(checkPro, 0, trObjs[0]);
//
//		Browser.unregister(trObjs);
		
		browser.selectCheckBox(".value", schdId, true);
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
		ajax.waitLoading();
		deselectFeeType();
		ajax.waitLoading();
		deselectUnitType();
		deselectTicketCategory();
		deselectPermitType();
		if (this.isRateTypeIsExisting() && this.isRateTypeEnable()) {
			this.deselectRateType();
		}
		deselectTransType();
		deselectTransOcc();
		deselectAttribute();
		deselectSeason();
		deselectSalesChannel();
		deselectInOutState();
		deselectCustomerType();
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
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];
		int num = table.rowCount();
		Browser.unregister(objs);
		if (num < 2) {
			throw new ItemNotFoundException("No Fee Schedule Found!");
		}
	}

	/**
	 * This method used to change schedule status
	 * 
	 * @param feeSchdId
	 *            - fee schedule id
	 * @param status
	 *            - active/deactive
	 * 
	 * @error msg1: Fee schedule having ID = 133841257 cannot be activated
	 *        because it is overlapping another Fee Schedule with ID = 133846215
	 *        msg2: Fee schedule having ID = 1005649829 cannot be activated
	 *        because identical Fee Schedule with ID = 1005649816 already exists
	 * 
	 */
	public void changeScheduleStatus(String feeSchdId, String status) {
		logger.info("Start to Change Fee Schedule Status to " + status);
		String tmpStr = "";
		String tmpID = "";
		ArrayList<String> ids = new ArrayList<String>();
		searchByFeeScheduleId(feeSchdId);
		selectScheduleCheckBox(feeSchdId);
		if (status.equalsIgnoreCase("Active")) {
			clickActive();
			ajax.waitLoading();
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
					changeScheduleStatus(feeSchdId, "Active");
				}

			}
		} else if (status.equalsIgnoreCase("Inactive")) {
			clickDeactive();
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
		waitLoading();
	}

	/** This method used to verify specific schedule status equals given status */
	public void verifyStatus(String feeSchdId, String status) {
		clearAllSearchCriteria();
		searchByFeeScheduleId(feeSchdId);
		if (status.equalsIgnoreCase("Inactive")) {
			if (isFeeScheduleActive(feeSchdId)) {
				throw new ItemNotFoundException(
						"Fee Schedule Status is not Inactive");
			}
		} else if (status.equalsIgnoreCase("Active")) {
			if (!isFeeScheduleActive(feeSchdId)) {
				throw new ItemNotFoundException(
						"Fee Schedule Status is not Active");
			}
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
	}

	/**
	 * This method used to search fee schedule by schedule id
	 * 
	 * @param feeSchID
	 */
	public void searchByFeeScheduleId(String feeSchID) {
		selectSearchType("Fee Schedule ID");
		setSearchValue(feeSchID);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}

	/**
	 * go to fee schedule's detail page by given id.
	 * 
	 * @param feeSchID
	 *            - fee schedule id
	 */
	public void gotoFeeSchDetailPg(String feeSchID) {
		browser.clickGuiObject(".class", "Html.A", ".text", feeSchID);
	}

	/**
	 * Click on first fee schedule id link to go to detail page.
	 * 
	 * @return - selected schedule id
	 */
	public String gotoFirstFeeSchDetailsPg() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href",
				new RegularExpression(".*FeeAdminFeeDetails\\.do.*", false));
		String schID = objs[0].getProperty(".text").toString();

		ILink link = (ILink) objs[0];
		link.click();

		Browser.unregister(objs);
		return schID;
	}

	/**
	 * This method goes to retrieve the cell value for given column name
	 * 
	 * @param cloumnName
	 *            - the special column name which you want to get the cell value
	 *            from
	 * @return - cell value
	 */
	public String getFeeDetailBySchID(String colName) {
		String cellValue = "";
		IHtmlObject[] feeSchTable = browser.getTableTestObject(".classIndex",
				"7");
		IHtmlTable tableGrid = (IHtmlTable) feeSchTable[0];

		for (int col = 0; col < tableGrid.columnCount(); col++) {
			String toCompare = tableGrid.getCellValue(0, col).toString();

			if (toCompare.equalsIgnoreCase(colName)) {// compare column with
				// retrieve the column
				// value
				cellValue = tableGrid.getCellValue(1, col).toString();
				break;// exit for when found column
			} else if (col == tableGrid.columnCount() - 1
					&& !toCompare.equalsIgnoreCase(colName))
				throw new ItemNotFoundException("Given column '" + colName
						+ "' not found.");
		}

		Browser.unregister(feeSchTable);
		return cellValue;
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
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^ ?Fee Schedule ID Active.*",
						false));

		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
		if (tableGrid.rowCount() == 1) {
			throw new ErrorOnPageException("Could not find any fee schedule in schedule list.");
		}
		
		if(!isFeeScheduleExists(feeSchID)){
			throw new ErrorOnPageException("Could not find fee schedule"+feeSchID+" in schedule list.");
		}
		
		for(int i=1;i<tableGrid.rowCount();i++){
			String id = tableGrid.getCellValue(i, 1);
			String status = tableGrid.getCellValue(i, 2);
			if(id.equals(feeSchID)
				&& status.equalsIgnoreCase("Yes")){
				toReturn = true;
				break;
			}
		}
		
		Browser.unregister(objs);
		
		return toReturn;
	}

	public List<FeeScheduleData> getFeeSchSearchResults() {
		List<FeeScheduleData> list = new ArrayList<FeeScheduleData>();
//		HtmlObject[] objs = browser.getTableTestObject(".id",
//				new RegularExpression("FeeScheduleSearchResultGrid_LIST", false));
		
        	IHtmlObject[] objs = browser.getTableTestObject(".text",
    				new RegularExpression("( )?Fee Schedule ID( )?Active( )?Location.*", false));
    		if (objs.length < 1) {
    			throw new ObjectNotFoundException(
    					"Can't find the search result table.....");
    		}
        	IHtmlTable table = (IHtmlTable) objs[objs.length-1];
        	int tableRows = table.rowCount();
		for (int i = 1; i < tableRows; i++) {
			FeeScheduleData fee = new FeeScheduleData();
			fee.feeSchdId = table.getCellValue(i,  table.findColumn(0, "Fee Schedule ID"));
			fee.activeStatus = table.getCellValue(i,  table.findColumn(0, "Active"));
			fee.location = table.getCellValue(i,  table.findColumn(0, "Location"));
			fee.productCategory = table.getCellValue(i,  table.findColumn(0, "Product Category"));
			if(fee.productCategory.equals("Slip")) {
				fee.dock = table.getCellValue(i,  table.findColumn(0, "Dock/Area"));
				fee.rafting = table.getCellValue(i, table.findColumn(0, "Rafting"));
			} else if(fee.productCategory.equals("Site")) {
				fee.loop = table.getCellValue(i,  table.findColumn(0, "Loop/Area/Venue"));
				fee.minimumUnitOfStay = table.getCellValue(i,  table.findColumn(0, "Min Unit of Stay"));
				fee.minimumNumOfDayBeforeArrivalDay = table.getCellValue(i,  table.findColumn(0, "Min Number of Days before Arrival Date"));
			} else if(fee.productCategory.equals("List")) {
				fee.loop = table.getCellValue(i,  table.findColumn(0, "Loop/Area/Venue"));
			}
			
			fee.prodOrProdgroup = table.getCellValue(i,  table.findColumn(0, "Product or Product Group"));
			fee.feeType = table.getCellValue(i,  table.findColumn(0, "Fee Type"));
			fee.effectDate = table.getCellValue(i,  table.findColumn(0, "Effective (Start) Date"));
			fee.startInv = table.getCellValue(i,  table.findColumn(0, "Inventory Start Date"));
			fee.endInv = table.getCellValue(i, table.findColumn(0, "Inventory End Date"));
			if(fee.productCategory.equals("Ticket") || fee.productCategory.equals("Permit")) {
				fee.ticketCategory = table.getCellValue(i,  table.findColumn(0, "Ticket/Permit Category"));
			}
			if(fee.productCategory.equals("Permit")) {
				fee.permitType = table.getCellValue(i, table.findColumn(0, "Permit Type"));
			}
			fee.rateType = table.getCellValue(i,  table.findColumn(0, "Rate Type"));
			fee.rate = table.getCellValue(i, table.findColumn(0, "Rate"));
			fee.unit = table.getCellValue(i, table.findColumn(0, "Unit"));
			fee.tranType = table.getCellValue(i,  table.findColumn(0, "Transaction Type"));
			fee.tranOccur = table.getCellValue(i, table.findColumn(0, "Transaction Occurrence"));
			fee.attrType = table.getCellValue(i,  table.findColumn(0, "Attribute"));
			fee.season = table.getCellValue(i, table.findColumn(0, "Season"));
			fee.salesChannel = table.getCellValue(i,  table.findColumn(0, "Sales Channel"));
			fee.state = table.getCellValue(i,  table.findColumn(0, "In/Out State"));
			fee.custType = table.getCellValue(i,  table.findColumn(0, "Customer Type"));
			if(fee.productCategory.equals("Slip")) {
				fee.boatCategory = table.getCellValue(i, table.findColumn(0, "Boat Category"));
			}
			fee.acctCode = table.getCellValue(i,  table.findColumn(0, "Account"));

			list.add(fee);
		}
		return list;
	}

	public boolean compareFeeScheduleListInfo(FeeScheduleData expected) {
		this.searchByFeeScheduleId(expected.feeSchdId);
		List<FeeScheduleData> result = this.getFeeSchSearchResults();
		if(result.size() < 1) {
			throw new ErrorOnPageException("Cannot find any fee schedule searched by id - " + expected.feeSchdId);
		}
		
		FeeScheduleData actual = result.get(0);
		
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Fee Schedule ID", expected.feeSchdId, actual.feeSchdId);
		pass &= MiscFunctions.compareResult("Active", expected.activeStatus, actual.activeStatus);
		pass &= MiscFunctions.compareResult("Location", expected.location, actual.location);
		if(expected.productCategory.equals("Slip")) {
			pass &= MiscFunctions.compareResult("Loop/Area/Venue", expected.dock, actual.dock);
			if(!expected.feeType.equals("Transaction Fee")){
				expected.rafting = ("").equals(expected.rafting)? "All":expected.rafting;
			}
			pass &= MiscFunctions.compareResult("Rafting",expected.rafting, actual.rafting);
		} else if(expected.productCategory.equals("Site") || expected.productCategory.equals("List")) {
			pass &= MiscFunctions.compareResult("Loop/Area/Venue", expected.loop, actual.loop);
		} else throw new ErrorOnPageException("Please finish these code to handle your situation.");
		pass &= MiscFunctions.compareResult("Product Category", expected.productCategory, actual.productCategory);
		if(expected.productCategory.equals("List")){
			pass &= MiscFunctions.compareResult("Product or Product Group", expected.product, actual.prodOrProdgroup);
		}else{
			pass &= MiscFunctions.compareResult("Product or Product Group", expected.product.equals("All") ? expected.productGroup : expected.product.split("-")[1].trim(), actual.prodOrProdgroup);
		}
		pass &= MiscFunctions.compareResult("Fee Type", expected.feeType, actual.feeType);
		pass &= MiscFunctions.compareResult("Effective Start Date", expected.effectDate, actual.effectDate);
		pass &= MiscFunctions.compareResult("Inventory Start Date", expected.startInv, actual.startInv);
		pass &= MiscFunctions.compareResult("Inventory End Date", expected.endInv, actual.endInv);
		pass &= MiscFunctions.compareResult("Rate Type", expected.productCategory.equals("Slip") ? expected.marinaRateType : expected.rateType, actual.rateType);
		pass &= MiscFunctions.compareResult("Rate", expected.feeType.equals("Transaction Fee") ? expected.tranFee : expected.rate, actual.rate);
		if(expected.feeType.equals("Transaction Fee")) {
			pass &= MiscFunctions.compareResult("Unit", expected.tranFeeOption, actual.unit);
			pass &= MiscFunctions.compareResult("Rate", expected.tranFee, actual.rate);
		} else if(expected.feeType.equals("Attribute Fee")) {
			pass &= MiscFunctions.compareResult("Unit", expected.productCategory.equals("Slip") ? expected.slipPricingUnit : "", actual.unit);
		}
	
		//Holiday Rates
		//Apply Rate
		pass &= MiscFunctions.compareResult("Permit Type", expected.permitType, actual.permitType);
		if(expected.productCategory.equals("Permit")) {
			pass &= MiscFunctions.compareResult("Permit Category", expected.permitCategory, actual.permitCategory);
		} else if(expected.productCategory.equals("Ticket")) {
			pass &= MiscFunctions.compareResult("Ticket Category", expected.ticketCategory, actual.ticketCategory);
		}
		if(!StringUtil.isEmpty(expected.tranType) || !StringUtil.isEmpty(actual.tranType)) //add this for Use fee which does not have trans type
			pass &= MiscFunctions.compareResult("Transaction Type", expected.tranType, actual.tranType);
		if(!StringUtil.isEmpty(expected.tranOccur) || !StringUtil.isEmpty(actual.tranOccur)) //add this for Use fee which does not have trans occur
			pass &= MiscFunctions.compareResult("Transaction Occurrence", expected.tranOccur, actual.tranOccur);
		if(!StringUtil.isEmpty(expected.attrType) || !StringUtil.isEmpty(actual.attrType)) {
			pass &= MiscFunctions.compareResult("Attribute", expected.attrType, actual.attrType);
		}
		if(!StringUtil.isEmpty(expected.season) || !StringUtil.isEmpty(actual.season)) {
			pass &= MiscFunctions.compareResult("Season", expected.season.toUpperCase(), actual.season.toUpperCase());
		}
		pass &= MiscFunctions.compareResult("Sales Channel", expected.salesChannel, actual.salesChannel);
		pass &= MiscFunctions.compareResult("In/Out State", expected.state, actual.state);
		//pass &= MiscFunctions.compareResult("Customer Type", expected.custType, actual.custType);
		if(expected.productCategory.equals("Slip")) {
			pass &= MiscFunctions.compareResult("Boat Category", expected.boatCategory, actual.boatCategory);
		}
		pass &= MiscFunctions.compareResult("Account", expected.acctCode, actual.acctCode);
		
		return pass;
	}
	
	public void verifyFeeScheduleListInfo(FeeScheduleData expected) {
		if(!this.compareFeeScheduleListInfo(expected)) {
			throw new ErrorOnPageException("The Fee Schedule(ID#=" + expected.feeSchdId + ") list info are incorrect, please refer to log for details.");
		} else logger.info("The Fee Schedule(ID#=" + expected.feeSchdId + ") list info is correct.");
	}
	
	/**
	 * Verify whether or not the fee schedule exists in schedule list page.
	 * 
	 * @param feeSchID
	 *            - fee schedule id
	 * @return true - exists; false - not found
	 */
	public boolean isFeeScheduleExists(String feeSchID) {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				feeSchID);
		if (objs.length > 0)
			toReturn = true;

		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * 
	 * @param colName
	 * @param value
	 * @return
	 */
	public boolean isFeeScheduleExists(String colName, String value) {
		if(!isFirstFeeScheduleExist()) {
			return false;
		}
		
		boolean exists = true;
		if(colName.equalsIgnoreCase("ID")) {
			exists = isFeeScheduleExists(value);
		} else if(colName.equalsIgnoreCase("Product")) {
			exists = isRecordExists("Product or Product Group", value);
		} else if(colName.equalsIgnoreCase("Location")) {
			exists = isRecordExists("Location", value);
		} else throw new ErrorOnPageException("Unknown column: " + colName);
		
		return exists;
	}
	
	/**
	 * Fill in all search criteria.
	 * 
	 * @param schedule
	 *            - FeeScheduleData
	 */
	public void setupSearchCriteria(FeeScheduleData schedule) {
		logger.info("Set up search criteria for fee schedule...");

		if (StringUtil.notEmpty(schedule.productCategory)) {
			clearAllSearchCriteria();
			selectProductCategory(schedule.productCategory);
			ajax.waitLoading();
		}
		if (StringUtil.notEmpty(schedule.searchType)) {
			selectSearchType(schedule.searchType);
			ajax.waitLoading();
			setSearchValue(schedule.searchValue);
		}
		if (StringUtil.notEmpty(schedule.dateType)) {
			selectDateType(schedule.dateType);
			setFromDate(schedule.fromDate);
			setToDate(schedule.toDate);
		}
		if ( StringUtil.notEmpty(schedule.activeStatus)) {
			selectShowStatus(schedule.activeStatus);
		}
		if (StringUtil.notEmpty(schedule.assignPrdCategory)) {
			selectApplicableProductCategory(schedule.assignPrdCategory);
		}
		
		if ( StringUtil.notEmpty(schedule.feeType)) {
			selectFeeType(schedule.feeType);
			ajax.waitLoading();
		}
		if ( StringUtil.notEmpty(schedule.unit)) {
			this.selectUnitType(schedule.unit);
		}
		if (StringUtil.notEmpty(schedule.ticketOrpermitCat)) {
			this.selectTicketCategory(schedule.ticketOrpermitCat);
		}
		if ( StringUtil.notEmpty(schedule.permitType)) {
			this.selectPermitType(schedule.permitType);
		}
		if ( StringUtil.notEmpty(schedule.rateType)) {
			this.selectRateType(schedule.rateType);
		}
		if (StringUtil.notEmpty(schedule.tranType)) {
			this.selectTransType(schedule.tranType);
		}
		if ( StringUtil.notEmpty(schedule.tranOccur)) {
			this.selectTransOcc(schedule.tranOccur);
		}
		if (StringUtil.notEmpty(schedule.attrType)) {
			this.selectAttribute(schedule.attrType);
		}
		if (StringUtil.notEmpty(schedule.season) ) {
			this.selectSeason(schedule.season);
		}
		if (StringUtil.notEmpty(schedule.salesChannel) ) {
			String salesChannel = schedule.salesChannel;
			if(salesChannel.contains("Call")){
				salesChannel = "Call Center";
			}else if(salesChannel.contains("Field")){
				salesChannel = "Field";
			}
			this.selectSalesChannel(salesChannel);
		}
		if (StringUtil.notEmpty(schedule.state)) {
			this.selectInOutState(schedule.state);
		}
		if (StringUtil.notEmpty(schedule.custType)) {
			this.selectCustomerType(schedule.custType);
		}
		
		if(isMarinaRateTypeExist() &&  StringUtil.notEmpty(schedule.marinaRateType)){
			this.selectMarinaRateType(schedule.marinaRateType);
		}
		
		if(isRaftingExist() &&  StringUtil.notEmpty(schedule.rafting)){
			this.selectRafting(schedule.rafting);
		}

		if(isBoatCategoryExist() &&StringUtil.notEmpty(schedule.boatCategory)){
			this.selectBoatCategory(schedule.boatCategory);
		}
		
	}
	
	public List<String> getFeeID(){
		List<String> feeIDList = new ArrayList<String>();

		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Fee Schedule ID.*", false));
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];
		for(int i=1; i<table.rowCount(); i++){
			feeIDList.add(table.getCellValue(i, 1));
		}
		
		return feeIDList;
	}
	
	public void cleanupFeeSchedule(FeeScheduleData schedule){
		this.setupSearchCriteria(schedule);
		this.clickGo();
		ajax.waitLoading();
		List<String> feeIDList = this.getFeeID();
		if(feeIDList.size()>0){
			this.selectAllCheckBox();
			this.clickDeactive();
			ajax.waitLoading();
		} else {
			logger.error("Doesn't find any fee schedule info.");
		}
	}
	
	public void selectAllCheckBox(){
		browser.selectCheckBox(".name", "all_slct");
	}
	
	private IHtmlObject[] getSearchFeeScheduleTable(){
		RegularExpression rex = new RegularExpression(
				"^Fee Schedule ID Active Location.*", false);
		return browser.getHtmlObject(".class", "Html.TABLE",
					".text", rex);
	}
	public List<String> getSpecificColValueList(String col){
		return this.getSpecificColValueList(col, true);
	}
	
	public List<String> getSpecificColValueList(String col,boolean gotoNext){
		PagingComponent turnPage = new PagingComponent();
		List<String> values = new ArrayList<String>();
		do{
			IHtmlObject[] objs = this.getSearchFeeScheduleTable();
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
		}while(gotoNext&&turnPage.clickNext());
		
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

	public boolean isRecordExists(String colName, String value) {
		List<String> colValues = this.getSpecificColValueList(colName);
		if(colValues.size() < 1) {
			return false;
		}
		for(String cv : colValues) {
			if(value.contains(cv)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void verifySearchResults(String val, String col) {
		this.verifySearchResults(val, col, true);
	}
	
	public void verifySearchResults(String val, String col,boolean gotoNext) {
		List<String> values = this.getSpecificColValueList(col,gotoNext);
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

	public void verifySchdInSearchList(String id) {
		RegularExpression rex = new RegularExpression(
				"^Fee Schedule ID Active Location.*", false);

		boolean foundSchd = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
		for (int i = 1; i < tableGrid.rowCount(); i++) {
			if (tableGrid.getCellValue(i, 1).toString().equals(id)) {
				logger
						.info("Fee schedule :" + id
								+ " is in the search result!");
				foundSchd = true;
				break;
			}
		}
		if (!foundSchd) {
			Browser.unregister(objs);
			throw new ItemNotFoundException("Search Fee Schedule Fail!");
		}

		Browser.unregister(objs);
	}
	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "NOTSET");
	}
	
	public String getErrorMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = null;
		if (objs.length > 0) {
			errorMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return errorMsg;
	}

	public boolean isFirstFeeScheduleExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", 
				".id", new RegularExpression("row_0_checkbox|GenericGrid-\\d+.selectedItems", false));
	}

	public void selectAllCheckbox() {
		browser.selectCheckBox(".value", "all");
	}
	
	public void deactivateAllSchedules() {
		logger.info("Deactivate All Fee Schedules in the current page...");
		this.selectAllCheckbox();
		this.clickDeactive();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void unSelectFirstFeeSchedule() {
		browser.unSelectCheckBox(".id", "row_0_checkbox");
	}
	
	public int getSearchResultNums() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("\\s?Fee Schedule ID\\s?Active\\s?Location.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the search result table.....");
		}
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];
		int tableRows = table.rowCount();
		Browser.unregister(objs);
		logger.info("Search fee schedule number as "+tableRows);
		return tableRows;
	}
	
	public boolean checkFeeScheduleIsExist(FeeScheduleData fd){
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		
		feeMainPg.setupSearchCriteria(fd);
		feeMainPg.clickGo();
		ajax.waitLoading();
		feeMainPg.waitLoading();
		
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("FeeScheduleSearchResultGrid_LIST|SlipFeeScheduleSearchResultGrid_LIST",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		int num = table.rowCount();
		Browser.unregister(objs);
		if (num < 2) {
			return false;
		}else{
			return true;
		}
	}
	
	public String getFirstFeeScheduleID(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?FeeScheduleSearchResultGrid_LIST",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Can not find the table...");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		String id=table.getCellValue(1, 1);
		
		return id;
	}
	
	public void clickFeesScheduleId(String scheduleId){
		browser.clickGuiObject(".class", "Html.A", ".text", scheduleId, true);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/** 
 	 * Click the next button
 	 * */
 	public boolean clickNext(){
 		boolean toReturn = true;

 		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Next", false))){
 			this.browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Next", false), true);
 			ajax.waitLoading();
 			this.waitLoading();
 		} else {
 			toReturn = false;
 		}
 		
// 		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Next", false));
// 		if(objs.length<1){
// 			objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("Next", false));
// 			toReturn = false;
// 		}
//
// 		if(toReturn){
// 			this.browser.clickGuiObject(".class", "Html.A", ".text",
// 	 				new RegularExpression("Next", false));
// 			ajax.waitLoading();
// 			this.waitExists();
// 		}
// 		Browser.unregister(objs);
 		return toReturn;
 	}
	/**
 	 * Get the column index.
 	 */
 	public int getColIndex(String colName) {
 		int colNum = 0;
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
 				".id", "privilegeInstanceList_LIST");
 		if (objs.length > 0) {
 			IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
 			//System.out.println(cusTableGrid.getColumnValues(8));
 			colNum = cusTableGrid.findColumn(0, colName);
 		} else
 			throw new ObjectNotFoundException("Object can't find.");

 		Browser.unregister(objs);
 		return colNum;
 	}
 	
 	public void verifyFeeScheduleSearchResult(FeeScheduleData expectedSchedlue){
 		if(StringUtil.notEmpty(expectedSchedlue.feeType)){
 			this.verifySearchResults(expectedSchedlue.feeType, "Fee Type");
 		}if(StringUtil.notEmpty(expectedSchedlue.unit)){
 			this.verifySearchResults(expectedSchedlue.unit, "Unit");
 		}if(StringUtil.notEmpty(expectedSchedlue.tranType)){
 			this.verifySearchResults(expectedSchedlue.tranType, "Transaction Type");
 		}if(StringUtil.notEmpty(expectedSchedlue.tranOccur)){
 			this.verifySearchResults(expectedSchedlue.tranOccur, "Transaction Occurrence");
 		}if(StringUtil.notEmpty(expectedSchedlue.attrValue)){
 			this.verifySearchResults(expectedSchedlue.attrValue, "Attribute");
 		}if(StringUtil.notEmpty(expectedSchedlue.season)){
 			this.verifySearchResults(expectedSchedlue.season, "Season");
 		}if(StringUtil.notEmpty(expectedSchedlue.salesChannel)){
 			this.verifySearchResults(expectedSchedlue.salesChannel, "Sales Channel");
 		}if(StringUtil.notEmpty(expectedSchedlue.state)){
 			this.verifySearchResults(expectedSchedlue.state, "In/Out State");
 		}if(StringUtil.notEmpty(expectedSchedlue.custType)){
 			this.verifySearchResults(expectedSchedlue.custType, "Customer Type");
 		}
 	}
 	
 	public void searchFeeScheduleBySimpleInfo(FeeScheduleData feeData) {
 		if(!StringUtil.isEmpty(feeData.searchType)) {
 			selectSearchType(feeData.searchType);
 		}
		if(!StringUtil.isEmpty(feeData.searchValue)) {
			setSearchValue(feeData.searchValue);
		}
 		if(!StringUtil.isEmpty(feeData.activeStatus)) {
 			selectShowStatus(OrmsConstants.ACTIVE_STATUS);
 		}
 		if(!StringUtil.isEmpty(feeData.productCategory)) {
 			selectProductCategory(feeData.productCategory);
 			ajax.waitLoading();
 		}
// 		if(!StringUtil.isEmpty(feeData.assignPrdCategory)) {
// 			selectApplicableProductCategory(feeData.assignPrdCategory);
// 		}
 		if(!StringUtil.isEmpty(feeData.feeType)) {
 			selectFeeType(feeData.feeType);
 			ajax.waitLoading();
 		}
//
// 		if(!StringUtil.isEmpty(feeData.unit)) {
// 			selectUnitType(feeData.unit);
// 		}
//
// 		if(!StringUtil.isEmpty(feeData.tranType)) {
// 			selectTransType(feeData.tranType);
// 		}
 		
 		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
 	}
 	
 	/**
 	 * search fee schedule.
 	 * @param fee
 	 */
 	public void searchFeeSchedule(FeeScheduleData fee){
 		this.setupSearchCriteria(fee);
 		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
 	}
 	
 	public List<String> getMarinaRateTypeList(){
 		return browser.getDropdownElements(".id", "SlipFeeSearchRequest.marinaRateType");
 	}
 	public List<String> getRaftingList(){
 		return browser.getDropdownElements(".id", "SlipFeeSearchRequest.raftingType");
 	}
 	public List<String> getBoatCategoryList(){
 		return browser.getDropdownElements(".id", "SlipFeeSearchRequest.boatCategory");
 	}
 	public List<String> getAttributeList(){
 		return browser.getDropdownElements(".id", "FeeSearchRequest.attributeID");
 	}
 
 	private void verifyListInfo(List<String> expectedList,List<String> actualList){
 		//List<String> actualList = this.getMarinaRateTypeList();
 		if(expectedList.size()!=actualList.size()){
 			throw new ErrorOnPageException("the list size",expectedList.size(),actualList.size());
 		}
 		if(!actualList.containsAll(expectedList)){
 			throw new ErrorOnPageException("List info error",actualList,expectedList);
 		}
 		
 	}
 	/**
 	 * verify MarinaType info
 	 * @param expectedList
 	 */
 	public void verifyMarinaTypeListInfo(List<String> expectedList){
 		this.verifyListInfo(expectedList,this.getMarinaRateTypeList());
 	}
 	/**
 	 * verify Boat category list info
 	 * @param expectedList
 	 */
 	public void verifyBoatCategoryListInfo(List<String> expectedList){
 		this.verifyListInfo(expectedList,this.getBoatCategoryList());
 	}
 	/**
 	 * verify rafting list info.
 	 * @param expectedList
 	 */
 	public void verifyRaftingListInfo(List<String> expectedList){
 		this.verifyListInfo(expectedList,this.getRaftingList());
 	}
 	/**
 	 * verify attribute list info
 	 * @param expectedList
 	 */
 	public void verifyAttributeListInfo(List<String> expectedList){
 		this.verifyListInfo(expectedList,this.getAttributeList());
 	}
 	
	public void activateOrDeactivateFeeSchedule(boolean toActive, String... ids) {
		for(String id : ids) {
			selectScheduleCheckBox(id);
		}
		
		if(toActive) {
			clickActive();
		} else {
			clickDeactive();
		}
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void activateOrDeactivateFeeSchedule(boolean toActive, String id) {
		if(!this.isFeeScheduleExists(id)) {
			this.searchByFeeScheduleId(id);
		}
		this.selectScheduleCheckBox(id);
		if(toActive) {
			clickActive();
			ajax.waitLoading();
			this.waitLoading();
			String errorMsg = this.getErrorMsg();
			if(!StringUtil.isEmpty(errorMsg)) {
				String existingIDs[] = RegularExpression.getMatches(errorMsg, "\\d+");
				for(int i = 1; i < existingIDs.length; i ++) {
					this.activateOrDeactivateFeeSchedule(false, existingIDs[i]);
				}
				
				if(!this.isFeeScheduleExists(id)) {
					this.searchByFeeScheduleId(id);
				}
				this.activateOrDeactivateFeeSchedule(true, id);
			}
		} else {
			clickDeactive();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	public boolean isMarinaRateTypeExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SlipFeeSearchRequest.marinaRateType",false));
	}
	
	public boolean isRaftingExist(){
		return browser.checkHtmlObjectExists(".id", "SlipFeeSearchRequest.raftingType");
	}
	
	public boolean isBoatCategoryExist(){
		return browser.checkHtmlObjectExists(".id", "SlipFeeSearchRequest.boatCategory");
	}
	
	/**
	 * verify order by ASCII.
	 * @param val
	 * @param col
	 */
	public List<String> getExpectedOrderByASCII(List<String> list){
		List<String> resutlList= new ArrayList<String>();
		String mySs[]=new String[list.size()];
		for(int i=0;i<list.size();i++){
			mySs[i]=new String(list.get(i));
		}
		Arrays.sort(mySs);
		for(int i=0;i<mySs.length;i++){
			resutlList.add(mySs[i]);
			System.out.println(mySs[i]);
		}
		return resutlList;
		
		/*for(int i=0;i<list.size();i++){
			for(int j=0;j<list.get(i).length()-1;j++){
				char previous = list.get(i).charAt(j);
				char last = list.get(i).charAt(j+1);
				if((int)previous == (int)last){
					j++;
					continue;
				}
				if((int)previous>(int)last){
					isCorrect = false;
				}
			}
		}
		return isCorrect;*/
	}
	
	public void clickRateTypeLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Rate Type");
	}
	
	public void clickAttribute(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Attribute");
	}
	
	public void clickRafting(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Rafting");
	}
	
	public void clickBoatCategory(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Boat Category");
	}
	
	public void clickFirstButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "First");
	}
	
	public void gotoFirst(){
		this.clickFirstButton();
		ajax.waitLoading();
		this.waitLoading();
	}

	
	public boolean getStatusByID(String id){
		this.searchByFeeScheduleId(id);
		
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("SlipFeeScheduleSearchResultGrid_LIST", false));
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find fee schedule list table by id SlipFeeScheduleSearchResultGrid_LIST.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		String status = table.getCellValue(1, 2);
		Browser.unregister(objs);
		if("No".equalsIgnoreCase(status)){
			return false;
		} else {
			return true;
		}
	}
	
	public List<String> getRateTeypeColList(){
		List<String> list = new ArrayList<String>();;
		List<FeeScheduleData> feeScheduleList = this.getFeeSchSearchResults();
		for(int i=0;i<feeScheduleList.size();i++){
			list.add(feeScheduleList.get(i).rateType);
		}
		return list;
	}
	
	public List<String> getRaftingColList(){
		List<String> list = new ArrayList<String>();;
		List<FeeScheduleData> feeScheduleList = this.getFeeSchSearchResults();
		for(int i=0;i<feeScheduleList.size();i++){
			list.add(feeScheduleList.get(i).rafting);
		}
		return list;
	}
	
	public List<String> getAttributeColList(){
		List<String> list = new ArrayList<String>();;
		List<FeeScheduleData> feeScheduleList = this.getFeeSchSearchResults();
		for(int i=0;i<feeScheduleList.size();i++){
			list.add(feeScheduleList.get(i).attrType);
		}
		return list;
	}
	
	public List<String> getBoatCategoryColList(){
		List<String> list = new ArrayList<String>();;
		List<FeeScheduleData> feeScheduleList = this.getFeeSchSearchResults();
		for(int i=0;i<feeScheduleList.size();i++){
			list.add(feeScheduleList.get(i).boatCategory);
		}
		return list;
	}

	public void verifySearchByFromDate(String colName, String expectValue){
		this.verifyDate(colName, expectValue, true);
	}

	public void verifySearchByToDate(String colName, String expectValue){
		this.verifyDate(colName, expectValue, false);
	}
	
	public void verifyDate(String colName, String expectValue, boolean isFrom){
		List<String> values = this.getSpecificColValueList(colName);
		if(values.size() <= 0){
			logger.error("There isn't any records!");
		}
		
		for (String actualValue : values) {
			if(isFrom){// search by from date
				// actual date should be later than or equal to expect date
				if(DateFunctions.compareDates(actualValue, expectValue) < 0){
					throw new ErrorOnPageException("Actual date("+actualValue+") should be later than or equal to expect date("+expectValue+")");
				}
			} else {// search by to date
				// actual date should be earlier than or equal to expect date
				if(DateFunctions.compareDates(actualValue, expectValue) > 0){
					throw new ErrorOnPageException("Actual date("+actualValue+") should be earlier than or equal to expect date("+expectValue+")");
				}
			}
		}
	}
	
	public List<String> getAllColNamesOfFeeTable() {
		List<String> cols = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "FeeScheduleSearchResultGrid_LIST");
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't fee table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		cols = table.getRowValues(0);		
		Browser.unregister(objs);
		return cols;
	}
	
	public void verifyColNameDisplay(String colName) {
		logger.info("Verify column name "+colName+" display on page");
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?FeeScheduleSearchResultGrid_LIST", false));
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
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(Slip)?FeeScheduleSearchResultGrid_LIST", false));
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
	
	public void deactiveAllTheFeeForProduct(String product, String feeType){
 		FeeScheduleData feeData = new FeeScheduleData();
 		feeData.searchType = "Product";
 		feeData.searchValue = product;
 		feeData.activeStatus = OrmsConstants.ACTIVE_STATUS;
 		feeData.feeType = feeType;
		this.searchFeeScheduleBySimpleInfo(feeData);
		this.selectAllCheckbox();
		this.clickDeactive();
		ajax.waitLoading();
		this.waitLoading();
	}
}
