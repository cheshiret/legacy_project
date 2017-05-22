/*
 * $Id: InvMgrSiteValidateFeesPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author cguo
 */
public class InvMgrSiteValidateFeesPage extends InvMgrSiteDetailsCommonPage {

	/**
	 * Script Name : InvMgrSiteValidateFeesPage Generated : Aug 10, 2005 3:11:23
	 * PM Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2005/08/10
	 */

	private static InvMgrSiteValidateFeesPage _instance = null;

	public static InvMgrSiteValidateFeesPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrSiteValidateFeesPage();
		}

		return _instance;
	}

	protected InvMgrSiteValidateFeesPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
		"Calculate Fees");
	}

	/** Click on Sites link. */
	public void clickSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sites");
	}

	/** Click on calculate fee button */
	public void clickCalculateFee() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Calculate Fees");
	}

	/** set book date */
	public void setBookingDate(String bookDate) {
		browser.setTextField(".id", "bookingdate_ForDisplay", bookDate);
	}

	/** set arrival date */
	public void setArrivalDate(String arrivalDate) {
		browser.setTextField(".id", "date_specific_date_start_ForDisplay",
				arrivalDate);
	}

	/** set departure date */
	public void setDepartureDate(String depatureDate) {
		browser.setTextField(".id", "date_specific_date_end_ForDisplay",
				depatureDate);
	}

	public void selectTierOne(String Tier) {
		browser.selectDropdownList(".id", "tier1", Tier, true);
	}

	/**Select the first empty item in tier1**/
	public void selectTierOneNull() {
		browser.selectDropdownList(".id", "tier1", 0);
	}

	public List<String> getItemsFromTierOne() {
		return browser.getDropdownElements(".id", "tier1");
	}

	public void selectTierTwo(String Tier) {
		browser.selectDropdownList(".id", "tier2", Tier);
	}

	/**Select the first empty item in tier2**/
	public void selectTierTwoNull() {
		browser.selectDropdownList(".id", "tier2", 0);
	}

	public List<String> getItemsFromTierTwo() {
		return browser.getDropdownElements(".id", "tier2");
	}

	public void selectSalesChannel(String channel) {
		browser.selectDropdownList(".id", "saleschannel", channel);
	}

	public void selectInOutState(String state) {
		browser.selectDropdownList(".id", "inout", state);
	}

	public void selectCustomerType(String customerType) {
		browser.selectDropdownList(".id", "customertype", customerType);
	}

	public void selectCustomerPass(String customerPass) {
		browser.selectDropdownList(".id", "customerpass", customerPass);
	}

	public void selectMember(String member) {
		browser.selectDropdownList(".id", "member", member);
	}

	public void selectWalkIn() {
		browser.selectRadioButton(".id", "transactionType", 1);
	}

	public void selectReservation() {
		browser.selectRadioButton(".id", "transactionType", 0);
	}

	public void selectFamily() {
		browser.selectRadioButton(".id", "ratetype", 0);
	}

	public void selectGroup() {
		browser.selectRadioButton(".id", "ratetype", 1);
	}

	public void setOccupantNum(String occupantNum) {
		browser.setTextField(".id", "occenterno", occupantNum);
	}

	public void setVehicleNum(String vehicleNum) {
		browser.setTextField(".id", "vehenterno", vehicleNum);
	}

	/** Click Site Details tab */
	public void clickSiteDetailsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Site Details");
	}

	public void clickNonSiteSpecificGroupsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Non-Site-Specific Groups");
	}

	/**
	 * set fee calculate parameters
	 * 
	 * @param feeData
	 */
	public void setFeeCalculationParameters(FeeValidationData feeData) {

		if (feeData.bookDate != null && !"".equalsIgnoreCase(feeData.bookDate)) {
			this.setBookingDate(feeData.bookDate);
		}

		if (feeData.arriveDate != null
				&& !"".equalsIgnoreCase(feeData.arriveDate)) {
			this.setArrivalDate(feeData.arriveDate);
		}

		if (feeData.departureDate != null
				&& !"".equalsIgnoreCase(feeData.departureDate)) {
			this.setDepartureDate(feeData.departureDate);
		}

		if ("walkin".equalsIgnoreCase(feeData.tranType)) {
			this.selectWalkIn();
			if (feeData.bookDate != null
					&& !"".equalsIgnoreCase(feeData.bookDate)
					&& feeData.arriveDate != null
					&& !"".equalsIgnoreCase(feeData.arriveDate)
					&& !feeData.bookDate.equalsIgnoreCase(feeData.arriveDate)) {
				throw new ErrorOnDataException(
				"book date must equal to start date");
			}
		}

		if ("group".equalsIgnoreCase(feeData.rateType)) {
			this.selectGroup();
			this.waitLoading();
			if (feeData.occuptants != null
					&& !"".equalsIgnoreCase(feeData.occuptants)) {
				this.setOccupantNum(feeData.occuptants);
			} else {
				throw new ErrorOnDataException(
				"occupants must have an integer value");
			}

			if (feeData.vehicles != null
					&& !"".equalsIgnoreCase(feeData.vehicles)) {
				this.setVehicleNum(feeData.vehicles);
			} else {
				throw new ErrorOnDataException(
				"vehicles must have an integer value");
			}
		}

		if (feeData.salesChannel != null
				&& !"".equalsIgnoreCase(feeData.salesChannel)) {
			this.selectSalesChannel(feeData.salesChannel);
		}

		if (feeData.state != null && !"".equalsIgnoreCase(feeData.state)) {
			this.selectInOutState(feeData.state);
		}

		if (feeData.custType != null && !"".equalsIgnoreCase(feeData.custType)) {
			this.selectCustomerType(feeData.custType);
		}

		if (feeData.custPass != null && !"".equalsIgnoreCase(feeData.custPass)) {
			this.selectCustomerPass(feeData.custPass);
		}

		if (feeData.member != null && !"".equalsIgnoreCase(feeData.member)) {
			this.selectMember(feeData.member);
		}

		if (feeData.disctTier1 != null
				&& !"".equalsIgnoreCase(feeData.disctTier1)) {
			this.selectTierOne(feeData.disctTier1);
		}

		if (feeData.disctTier2 != null
				&& !"".equalsIgnoreCase(feeData.disctTier2)) {
			this.selectTierTwo(feeData.disctTier2);
		}

	}

	/**
	 * get fee calculate results from system
	 * 
	 * @param style
	 * @return
	 */
	public List<String> getFeeBySystemCalculate(String style) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Amount Rate.*", false));
		IHtmlTable table = (IHtmlTable) objs[1];

		int amountCol = table.findColumn(0, "Amount") + 1;
		int scheduleCol = table.findColumn(0, "Schedule") + 1;
		int rateCol = table.findColumn(0, "Rate Unit") + 1;

		int totalFeeRow = table.findRow(0, "Total " + style + "s");
		String totalFee = table.getCellValue(totalFeeRow, amountCol).split(
		"\\$")[1].replace(",", "").trim();

		List<String> fee = new ArrayList<String>();
		fee.add(totalFee);

		List<String> feeType = table.getColumnValues(0);
		if (feeType == null) {
			throw new ErrorOnDataException("get fee type is failed");
		}
		for (int i = 0; i < feeType.size(); i++) {
			String type = feeType.get(i);
			if (style.equalsIgnoreCase(type)) {
				List<String> feeNum = table.getRowValues(i);
				if (feeNum == null) {
					throw new ErrorOnDataException("get fee amount is failed");
				}
				String fees = feeNum.get(amountCol).split("\\$")[1].replace(
						",", "").trim()
						+ feeNum.get(scheduleCol).trim()
						+ feeNum.get(rateCol).trim();
				fee.add(fees);
			}
		}

		return fee;
	}

	/** Click schedule id */
	public void clickScheduleID(String schedulID) {
		browser.clickGuiObject(".class", "Html.A", ".text", schedulID);
	}

	/** Check Fee validation details tab exist or not */
	public boolean checkFeeValidationDetailsTab() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
		"Fee Validation Details");
	}

	public boolean checkNumberOfNights() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
		"# of Nights");
	}

	public boolean checkNumberOfDays() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
		"# of Days");
	}

	public String getNumOfStay() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"(date_specific_nights|date_specific_days)", false));
	}

	public String getBookingDate() {
		return browser.getTextFieldValue(".id", "bookingdate_ForDisplay");
	}

	public String getArrivalDate() {
		return browser.getTextFieldValue(".id",
		"date_specific_date_start_ForDisplay");
	}

	public String getDepartureDate() {
		return browser.getTextFieldValue(".id",
		"date_specific_date_end_ForDisplay");
	}

	public void setNumOfNights(String numOfNighets) {
		browser.setTextField(".id", "date_specific_nights", numOfNighets);
	}

	public boolean checkOccupants() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
		"Occupants");
	}

	public boolean checkOccupantsEnter() {
		return browser.checkHtmlObjectExists(".id", "occenterno");
	}

	public boolean checkVehicles() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
		"Vehicles");
	}

	public boolean checkVehiclesEnter() {
		return browser.checkHtmlObjectExists(".id", "vehenterno");
	}

	public void clickRefreshAvailableDiscount() {
		browser.clickGuiObject(".class", "Html.A", ".href",
		"javascript:refreshDiscounts()");
	}

	public boolean checkFirst() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href",
		"javascript:go(\"first\")");
	}

	public boolean checkPrevious() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href",
		"javascript:go(\"prev\")");
	}

	public boolean checkNext() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href",
		"javascript:go(\"next\");");
	}

	public boolean checkLast() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href",
		"javascript:go(\"last\")");
	}

	public void clickFirst() {
		if (checkFirst()) {
			browser.clickGuiObject(".class", "Html.A", ".href",
			"javascript:go(\"first\")");
		}
	}

	public void clickPrevious() {
		if (checkPrevious()) {
			browser.clickGuiObject(".class", "Html.A", ".href",
			"javascript:go(\"prev\")");
		}
	}

	public void clickLast() {
		if (checkLast()) {
			browser.clickGuiObject(".class", "Html.A", ".href",
			"javascript:go(\"last\")");
		}
	}

	public void clickNext() {
		if (checkNext()) {
			browser.clickGuiObject(".class", "Html.A", ".href",
			"javascript:go(\"next\");");
		}
	}

	public void clickLoopsAreasTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Loops/Areas;");
	}

	/**
	 * Get the text of Site Table
	 * 
	 * @return
	 */
	public String getSiteInfo() {
		String siteInfo = "";

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Site Site ID.*|Site ID.*", false));
		if (objs.length > 0) {
			siteInfo = objs[0].getProperty(".text").toString();
		} else
			throw new ItemNotFoundException("Object doesn't exist.");

		return siteInfo;
	}

	// /**
	// * Get the specific discount details info. For each row, it includes: Fee
	// Type, Rate, Applies, Fee Dates (# of Units)
	// * @return
	// */
	// public List<List<String>> getDiscountDetailTableInfo(){
	// List<List<String>> specificDisctDetailInfo = new
	// ArrayList<List<String>>();
	//		
	// HtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
	// ".text", new
	// RegularExpression("^Fee Type Rate Applies Fee Dates (# of Units).*",false));
	// if(objs.length>0){
	// for(int i=0; i<objs.length; i++){
	// ITable table = (ITable)objs[i];
	// List<String> rowItems = null;
	// for(int j=1; j<table.rowCount(); j++){
	// rowItems = new ArrayList<String>();
	// String discountDetailInfo = "";
	// for(int k=0; k<table.columnCount(); k++){
	// discountDetailInfo = discountDetailInfo + (table.getCellValue(j, k)+"#");
	// }
	// rowItems.add(discountDetailInfo);
	// }
	// specificDisctDetailInfo.add(rowItems);
	// }
	// }else throw new ItemNotFoundException("Object doesn't find.");
	//
	// Browser.unregister(objs);
	// return specificDisctDetailInfo;
	// }

	public IHtmlObject[] getDiscountDetailInfoTable() {
		return browser.getHtmlObject(".class", "Html.TABLE", ".text",
				new RegularExpression("^Fee Type Rate Applies Fee Dates.*",false));
	}

	/**
	 * Get the specific discount details info. For each row, it includes: Fee
	 * Type, Rate, Applies, Fee Dates (# of Units)
	 * 
	 * @return
	 */
	public List<List<String>> getDiscountDetailTableInfo() {
		List<List<String>> specificDisctDetailInfo = new ArrayList<List<String>>();

		IHtmlObject[] objs = this.getDiscountDetailInfoTable();
		if (objs.length > 0) {
			IHtmlTable table = (IHtmlTable) objs[0];
			List<String> rowItems = null;
			for (int j = 1; j < table.rowCount(); j++) {
				rowItems = new ArrayList<String>();
				for (int k = 0; k < table.columnCount(); k++) {
					rowItems.add(table.getCellValue(j, k));
				}
				specificDisctDetailInfo.add(rowItems);
			}
			specificDisctDetailInfo.add(rowItems);
		} else throw new ItemNotFoundException("Object doesn't find.");

		Browser.unregister(objs);
		return specificDisctDetailInfo;
	}

	/**
	 * Verify the lab and value of '#'of Nights or '# of Days'
	 * 
	 * @param typeOfUse
	 *            : overnight or day
	 */
	public void VerifyInitialiedNumOfNightsOrDays(String typeOfUse) {
		if (typeOfUse.equals("Overnight")) {
			// Verify '#'of Nights when site's Type of Use is "Overnight" with
			// original state
			if (!this.checkNumberOfNights()) {
				throw new ErrorOnDataException(
				"'# of Nights' lable should be exist.");
			}
			if (!this.getNumOfStay().equals("1")) {
				throw new ErrorOnDataException("'# of Nights' should be '1'.");
			}
		} else {
			// Verify '#'of Days when site's Type of Use is "Days" with original
			// state
			if (!this.checkNumberOfDays()) {
				throw new ErrorOnDataException(
				"'# of Days' lable should be exist.");
			}
			if (!this.getNumOfStay().equals("1")) {
				throw new ErrorOnDataException("'# of Days' should be '1'.");
			}
		}
	}

	/**
	 * Verify arrival date, departure date and number of stay
	 * 
	 * @param arrivalDepartureNightsOrDays
	 *            : the order: arrival date, departure date, number of stay
	 */
	public void verifyArrivalDepartureNumOfStay(
			List<FeeValidationData> arrivalDepartureNightsOrDays) {
		FeeValidationData feeData = new FeeValidationData();

		feeData = arrivalDepartureNightsOrDays.get(0);
		this.setArrivalDate(feeData.arriveDate);
		this.refreshPage();
		this.getDepartureDate();
//		if (!DateFunctions.formatDate(this.getDepartureDate(), "M/d/yyyy")
//				.equals(feeData.departureDate)) {
		if (!(DateFunctions.compareDates(this.getDepartureDate(), feeData.departureDate)==0)) {
			throw new ErrorOnDataException("Departure Date should be "
					+ feeData.departureDate);
		}

		feeData = arrivalDepartureNightsOrDays.get(1);
		this.setNumOfNights(feeData.nightStay);
		this.refreshPage();
//		if (!DateFunctions.formatDate(this.getDepartureDate(), "M/d/yyyy")
//				.equals(feeData.departureDate)) {
		if (!(DateFunctions.compareDates(this.getDepartureDate(), feeData.departureDate)==0)) {
			throw new ErrorOnDataException("Departure Date should be "
					+ feeData.departureDate);
		}

		feeData = arrivalDepartureNightsOrDays.get(2);
		this.setDepartureDate(feeData.departureDate);
		this.refreshPage();
		if (!this.getNumOfStay().equals(feeData.nightStay)) {
			throw new ErrorOnDataException("Departure Date should be "
					+ feeData.nightStay);
		}
	}

	/**
	 * Verify rate type information with family or group
	 * 
	 * @param rateType
	 */
	public void verifyRateType(String rateType) {
		if (rateType.equals("Family")) {
			// Rate Type is Family
			if (this.checkOccupants()) {
				throw new ErrorOnDataException(
				"Occupants lable should not be exist. ");
			}
			if (this.checkOccupantsEnter()) {
				throw new ErrorOnDataException(
				"Occupants enter should not be exist. ");
			}
			if (this.checkVehicles()) {
				throw new ErrorOnDataException(
				"Vehicles lable should not be exist. ");
			}
			if (this.checkVehiclesEnter()) {
				throw new ErrorOnDataException(
				"Vehicles enter should not be exist. ");
			}
		} else {
			// Rate Type is Group
			if (!this.checkOccupants()) {
				throw new ErrorOnDataException(
				"Occupants lable should be exist. ");
			}
			if (!this.checkOccupantsEnter()) {
				throw new ErrorOnDataException(
				"Occupants enter should be exist. ");
			}
			if (!this.checkVehicles()) {
				throw new ErrorOnDataException(
				"Vehicles lable should be exist. ");
			}
			if (!this.checkVehiclesEnter()) {
				throw new ErrorOnDataException(
				"Vehicles enter should be exist. ");
			}
		}
	}

	/** Verify initial drop down list of "Discounts /Promotions" */
	public void verifyInitialDiscountsOrPromotions() {
		// Verify discount list with original state
		if (!(this.getItemsFromTierOne().size() == 1 && this
				.getItemsFromTierOne().get(0).equals(""))) {
			throw new ErrorOnDataException(
			"'Tier 1' discount lists shall be empty");
		}
		if (!(this.getItemsFromTierTwo().size() == 1 && this
				.getItemsFromTierTwo().get(0).equals(""))) {
			throw new ErrorOnDataException(
			"'Tier 2' discount lists shall be empty");
		}
	}

	/**
	 * Verify First, Previous, Next and Last buttons display correct or not
	 * 
	 * @param isFirst
	 *            : the site is the first site in site list page
	 * @param isLast
	 *            : the site is the last site in site list page
	 */
	public void verifyFirstPreviousNextLast(boolean isFirst, boolean isLast) {
		if (isFirst) {
			if (!this.checkNext() || !this.checkLast()) {
				throw new ObjectNotFoundException(
				"The Next and Last button should be available.");
			}
		} else if (isLast) {
			if (!this.checkFirst() || !this.checkPrevious()) {
				throw new ObjectNotFoundException(
				"The First and Previous button should be available.");
			}
		} else {
			if (!this.checkNext() || !this.checkLast() || !this.checkFirst()
					|| !this.checkPrevious()) {
				throw new ObjectNotFoundException(
				"The First, Previous, Next and Last button should be available.");
			}
		}
	}

	/**
	 * Verify site info correct via giving site info
	 * 
	 * @param status
	 *            : First, Previous, Next, Last
	 * @param siteInfo
	 *            : site section info
	 */
	public void verifySiteInfo(String status, String siteInfo) {
		if (!(this.getSiteInfo().replaceAll(" ", "")).equals(siteInfo.replaceAll(" ", ""))) {
			throw new ErrorOnDataException(status+ " Site info is not correct in Fee Valculation Validation page.Except one is:"+siteInfo.replaceAll(" ", "")+", but actual one is:"+this.getSiteInfo().replaceAll(" ", ""));
		}
	}

	/**
	 * Get custom fee table object
	 * 
	 * @return: HtmlObject[]
	 */
	public IHtmlObject[] getCustomFeeTable() {
		RegularExpression rex = new RegularExpression(
				"^Amount( )?Rate( )?Schedule( )?Rate Unit( )?Applies To( )?Fee Dates (# of Units)*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (objs.length <= 0) {
			throw new ObjectNotFoundException("Object doesn't find.");
		} else
			return objs;
	}

	/**
	 * Get the custom fee table cell
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getCustomFeeCell(int row, int col) {
		String customFeeCell = "";
		IHtmlObject[] objs = this.getCustomFeeTable();
		IHtmlTable table = (IHtmlTable) objs[1];
		customFeeCell = table.getCellValue(row, col);

		Browser.unregister(objs);
		return customFeeCell;
	}

	/**
	 * Check the 'Attribute Fee' exists or not
	 * 
	 * @return: false: 'Attribute Fee' doexn't exist true: 'Attribute Fee'
	 *          exists Reason: The System Adjustment portion shall be displayed
	 *          only if there are Attribute Fees applicable as displayed in the
	 *          main portion of the Customer Fees section. Otherwise, the System
	 *          Adjustment portion shall be hidden.
	 */
	public int getSystemAdjustmentNum() {
		String customFeeCell = "";
		int systemAdjustmentNum = 0;

		IHtmlObject[] objs = this.getCustomFeeTable();
		IHtmlTable table = (IHtmlTable) objs[1];
		for (int i = 0; i < table.rowCount(); i++) {
			customFeeCell = table.getCellValue(i, 0);
			if (customFeeCell.equals("System Adjustment")) {
				systemAdjustmentNum++;
			}
		}

		Browser.unregister(objs);
		return systemAdjustmentNum;
	}

	/**
	 * Check the 'Attribute Fee' exists or not
	 * 
	 * @return: false: 'Attribute Fee' doexn't exist true: 'Attribute Fee'
	 *          exists Reason: The System Adjustment portion shall be displayed
	 *          only if there are Attribute Fees applicable as displayed in the
	 *          main portion of the Customer Fees section. Otherwise, the System
	 *          Adjustment portion shall be hidden.
	 */
	public int getTotalNum() {
		String customFeeCell = "";
		int systemAdjustmentNum = 0;

		IHtmlObject[] objs = this.getCustomFeeTable();
		IHtmlTable table = (IHtmlTable) objs[1];
		for (int i = 0; i < table.rowCount(); i++) {
			customFeeCell = table.getCellValue(i, 0);

			if (customFeeCell.equals("Total Use Fees")
					|| customFeeCell.equals("Total Attribute Fees")
					|| customFeeCell.equals("Total Transaction Fees")
					|| customFeeCell.equals("Total Fee Penalties")
					|| customFeeCell.equals("Total Discounts")
					|| customFeeCell.equals("Total Taxes")
					|| customFeeCell.equals("Total")) {
				++systemAdjustmentNum;
			}
		}

		Browser.unregister(objs);
		return systemAdjustmentNum;
	}

	public void refreshPage(){
//		browser.clickGuiObject(".class","Html.FORM",".id","e_Form");
		browser.clickGuiObject(".class","Html.TD",".text","Stay Dates");
		this.waitLoading();
	}

	public String getUseErrorMsg() {
		IHtmlObject[] error = browser.getHtmlObject(".id", "errorMsg-1");
		String msg = error[0].getProperty(".text");
		return msg;
	}
	
	public String getScheduleID(String feeType){
		String[] scheduleTypes = new String[]{};
		String scheduleID = "";
		IHtmlObject[] objs = this.getCustomFeeTable();
		if(objs.length>1){
			IHtmlTable table = (IHtmlTable) objs[1];
			for(int i=0; i<table.rowCount(); i++){
				scheduleTypes = new String[table.rowCount()];
				scheduleTypes[i] = table.getCellValue(i, 0);
				// && table.getCellValue(i, 5).equals(feeType)
				if(null!=scheduleTypes[i] && scheduleTypes[i].equals(feeType)){
					scheduleID = table.getCellValue(i, 3);
					break;
				}else continue;

			}
		}
		return scheduleID.trim();
	}
	public String[] getScheduleIDs(String[] feeTypes){
		String[] scheduleIDs = new String[]{};
		for(int i=0; i<feeTypes.length; i++){
			scheduleIDs[i] = this.getScheduleID(feeTypes[i]);
		}
		return scheduleIDs;
	}
	
	public void waitExists(int timeout){
		Browser.sleep(timeout);
		browser.waitExists();
	}
	
	public void selectDiscount(String discountName){
		this.clickRefreshAvailableDiscount();
		this.waitLoading();
		this.selectTierOne(discountName);
		this.waitLoading();
		this.clickCalculateFee();
		this.waitLoading();
	}
}
