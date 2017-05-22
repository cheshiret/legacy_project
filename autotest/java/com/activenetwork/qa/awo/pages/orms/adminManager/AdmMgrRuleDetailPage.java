/*
 * Created on Jan 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.supportscripts.qasetup.admin.RulesCreation;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author QA
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrRuleDetailPage extends AdminManagerPage {

	static private AdmMgrRuleDetailPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrRuleDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrRuleDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrRuleDetailPage();
		}

		return _instance;
	}

	/** Determain whether the object exist */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New Definition");
	}

	/**
	 * Check the location wether is corrct
	 * 
	 * @param location
	 *            -- the expected location
	 */
	public boolean checkLocation(String location) {
		IHtmlObject[] objs = browser.getTableTestObject(".classIndex", "0",
				".text", new RegularExpression("Configure Rule", false));
		String loc = ((IHtmlTable) objs[0]).getCellValue(0, 5).toString()
				.substring("Location Name".length()).trim();
		Browser.unregister(objs);
		return loc.equalsIgnoreCase(location);
	}

	/**
	 * Verify the rule name is correct
	 * 
	 * @param rule
	 *            -- the expected rule name
	 * @return
	 */
	public boolean checkRule(String rule) {
		IHtmlObject[] objs = browser.getTableTestObject(".classIndex", "0",
				".text", new RegularExpression("Configure Rule", false));
		String ruleName = ((IHtmlTable) objs[0]).getCellValue(0, 5).toString()
				.substring("Rule Name".length()).trim();

		Browser.unregister(objs);
		return ruleName.equalsIgnoreCase(rule);
	}

	/** Set the rule cond ID */
	public void setRuleCondID(String item) {
		browser.setTextField(".id", "search_rulecond_id", item);
	}

	/**
	 * Select the status of the rule for search
	 * 
	 * @param item
	 *            : active\inactive
	 */
	public void selectSearchShow(String item) {
		browser.selectDropdownList(".id", "search_active", item);
	}

	/** Select the product Category for search */
	public void selectSearchProductCategory(String item) {
		browser.selectDropdownList(".id", "search_product_category", item);
	}

	/** Select the product group for search */
	public void selectSearchProductGroup(String item) {
		browser.selectDropdownList(".id", "search_product_group", item);
	}

	/** Select the sale channel for search */
	public void selectSearchSaleChannel(String item) {
		browser.selectDropdownList(".id", "search_sales_channel", item);
	}

	/** Select the customer type for search */
	public void selectSearchCustomerType(String item) {
		browser.selectDropdownList(".id", "search_customer_type", item);
	}

	/** Select the season type for search */
	public void selectSearchSeasonType(String item) {
		browser.selectDropdownList(".id", "search_season_type", item);
	}

	/** Select the customer pass type for search */
	public void selectSearchCustomerPassType(String item) {
		browser.selectDropdownList(".id", "search_pass_type", item);
	}

	/** Select the out of State for search */
	public void selectSearchOutOfState(String item) {
		browser.selectDropdownList(".id", "search_out_of_state_type", item);
	}

	/** Select the payment type for search */
	public void selectSearchPaymentType(String item) {
		browser.selectDropdownList(".id", "search_payment_type", item);
	}

	/** Select the customer member for search */
	public void selectSearchCustomerMemeber(String item) {
		browser.selectDropdownList(".id", "search_customer_member_type", item);
	}

	/** Select the transaction occurrence for search */
	public void selectSearchTransactionOccurrence(String item) {
		browser
				.selectDropdownList(".id", "search_transaction_occurrence",
						item);
	}

	/** Select the status */
	public void selectStatus(String item) {
		browser.selectDropdownList(".id", "active", item);
	}

	/** Select the product category */
	public void selectProductCategory(String item) {
		browser.selectDropdownList(".id", "product_category", item);
	}

	public boolean isMarinaRateTypeExists() {
		return browser.checkHtmlObjectExists(".id", "marina_rate_type");
	}
	
	public void selectMarinaRateType(String type) {
		browser.selectDropdownList(".id", "marina_rate_type", type);
	}
	
	/** Select the ticket category */
	public void selectTicketCategory(String item) {
		browser.selectDropdownList(".id", "sales_category", item);
	}

	/** Select the product group */
	public void selectProductGroup(String item) {
		browser.selectDropdownList(".id", "product_group", item);
	}

	/** Select the sale channel */
	public void selectSaleChannel(String item) {
		browser.selectDropdownList(".id", "sales_channel", item);
	}

	/** Select the customer type */
	public void selectCustomerType(String item) {
		browser.selectDropdownList(".id", "customer_type", item);
	}

	/** Select the season type */
	public void selectSeasonType(String item) {
		browser.selectDropdownList(".id", "season_type", item);
	}

	public boolean isSeasonTypeDropdownListExists() {
		return browser.checkHtmlObjectExists(".id", "season_type");
	}
	
	public boolean isSeasonTypeDropdownListEnabled() {
		if(isSeasonTypeDropdownListExists()) {
			IHtmlObject objs[] = browser.getDropdownList(".id", "season_type");
			if(objs.length < 1) {
				throw new ItemNotFoundException("Can't find 'Season Type' dropdown list object.");
			}
			boolean enabled = ((ISelect)objs[0]).isEnabled();
			Browser.unregister(objs);
			
			return enabled;
		} else {
			return false;
		}
	}
	
	/** Select the customer pass type */
	public void selectCustomerPassType(String item) {
		browser.selectDropdownList(".id", "pass_type", item);
	}

	/** Select the out of state */
	public void selectOutOfState(String item) {
		browser.selectDropdownList(".id", "out_of_state_type", item);
	}

	public boolean isPaymentTypeDropdownListExists() {
		return browser.checkHtmlObjectExists(".id", "payment_type");
	}
	
	/** Select the payment type */
	public void selectPaymentType(String item) {
		browser.selectDropdownList(".id", "payment_type", item, true);
	}

	/** Select the customer member */
	public void selectCustomerMemeber(String item) {
		browser.selectDropdownList(".id", "customer_member_type", item);
	}

	/** Select the transaction occurrence */
	public void selectTransactionOccurrence(String item) {
		browser.selectDropdownList(".id", new RegularExpression("(transaction_occurrence|TransactionOccurrence)", false), item);
	}

	/** Select the loop */
	public void selectLoop(String item) {
		browser.selectDropdownList(".id", "loop", item);
	}

	/** select the product */
	public void selectProduct(String item) {
		browser.selectDropdownList(".id", "product", item);
	}

	/** Select the associated party */
	public void selectAssociatedParty(String item) {
		browser.selectDropdownList(".id", "associated_party", item);
	}

	/** Select the unit */
	public void selectUnit(String item) {
		browser.selectDropdownList(".id", "Unit", item);
	}

	/** Set the description */
	public void setDescription(String text) {
		browser.setTextField(".id", "description", text);
	}

	/** Set the start date */
	public void setStartDate(String date) {
		browser.setTextField(".id", new RegularExpression("start_date(_ForDisplay)?", false), date, 0, IText.Event.LOSEFOCUS);
	}

	/** Set the end date */
	public void setEndDate(String date) {
		browser.setTextField(".id", new RegularExpression("end_date(_ForDisplay)?", false), date, 0, IText.Event.LOSEFOCUS);
	}

	/** Set the effective date */
	public void setEffectiveDate(String date) {
		browser.setTextField(".id", new RegularExpression("effective_date(_ForDisplay)?", false), date, 0,IText.Event.LOSEFOCUS);
	}

	/** Set the minimum stay */
	public void setMinimumStay(String text) {
		browser.setTextField(".id", "MinimumStay", text);
	}

	/** Set the Minimum Stay Monday */
	public void setMinimumStayMon(String text) {
		browser.setTextField(".id", "MinimumStayMon", text);
	}

	/** Set the Minimum Stay Tuesday */
	public void setMinimumStayTue(String text) {
		browser.setTextField(".id", "MinimumStayTue", text);
	}

	/** Set the Minimum Stay Wednesday */
	public void setMinimumStayWed(String text) {
		browser.setTextField(".id", "MinimumStayWed", text);
	}

	/** Set the Minimum Stay Thursday */
	public void setMinimumStayThu(String text) {
		browser.setTextField(".id", "MinimumStayThu", text);
	}

	/** Set the Minimum Stay Friday */
	public void setMinimumStayFri(String text) {
		browser.setTextField(".id", "MinimumStayFri", text);
	}

	/** Set the Minimum Stay Saturday */
	public void setMinimumStaySat(String text) {
		browser.setTextField(".id", "MinimumStaySat", text);
	}

	/** Set the Minimum Stay Sunday */
	public void setMinimumStaySun(String text) {
		browser.setTextField(".id", "MinimumStaySun", text);
	}

	/** Set the Minimus Stay on holiday */
	public void setMinimumStayHoliday(String text) {
		browser.setTextField(".id", "MinimumStayHoliday", text);
	}

	/** Select the check box Multiples Only */
	public void selectCheckBoxMultiplesOnly() {
		browser.selectCheckBox(".id", "MultiplesOnly");
	}

	/** Select the check box Combine Overlapping Seasons */
	public void selectCheckBoxMinimumStayCombineOverlappingSeasons() {
		browser.selectCheckBox(".id", "MinimumStayCombineOverlappingSeasons");
	}

	/** Select the check box Minimum Stay Required When Stay Includes Start Day */
	public void selectCheckBoxMinimumStayRequiredWhenStayIncludesStartDay() {
		browser.selectCheckBox(".id",
				"MinimumStayRequiredWhenStayIncludesStartDay");
	}

	/** Select one access type */
	public void selectAccessType(String accesstype) {
		browser.selectDropdownList(".id", "AccessType", accesstype);

	}

	/** Set the length of stay */
	public void setLength(String length) {
		browser.setTextField(".id", "Length", length);
	}

	/** select open time */
	public void selectOpenTime(String time) {
		browser.selectDropdownList(".id", "OpenTime", time);
	}

	/** set daily open time */
	public void setDailyOpenTime(String time) {
		browser.setTextField(".id", "DailyOpenTime", time);
	}

	/** set Monday open time */
	public void setMonOpenTime(String time) {
		browser.setTextField(".id", "MonOpenTime", time);
	}

	/** set Tuesday open time */
	public void setTueOpenTime(String time) {
		browser.setTextField(".id", "TueOpenTime", time);
	}

	/** set Wednesday open time */
	public void setWedOpenTime(String time) {
		browser.setTextField(".id", "WedOpenTime", time);
	}

	/** set Thuesday open time */
	public void setThuOpenTime(String time) {
		browser.setTextField(".id", "ThuOpenTime", time);
	}

	/** set Friday open time */
	public void setFriOpenTime(String time) {
		browser.setTextField(".id", "FriOpenTime", time);
	}

	/** set Saturday open time */
	public void setSatOpenTime(String time) {
		browser.setTextField(".id", "SatOpenTime", time);
	}

	/** set Sunday open time */
	public void setSunOpenTime(String time) {
		browser.setTextField(".id", "SunOpenTime", time);
	}

	/** Select close time */
	public void selectCloseTime(String time) {
		browser.selectDropdownList(".id", "CloseTime", time);
	}

	/** Select daily close time */
	public void setDailyCloseTime(String time) {
		browser.setTextField(".id", "DailyCloseTime", time);
	}

	/** Select Mon close time */
	public void setMonCloseTime(String time) {
		browser.setTextField(".id", "MonCloseTime", time);
	}

	/** Select Tue close time */
	public void setTueCloseTime(String time) {
		browser.setTextField(".id", "TueCloseTime", time);
	}

	/** Select Wed close time */
	public void setWedCloseTime(String time) {
		browser.setTextField(".id", "WedCloseTime", time);
	}

	/** Select Thu close time */
	public void setThuCloseTime(String time) {
		browser.setTextField(".id", "ThuCloseTime", time);
	}

	/** Select Fri close time */
	public void setFriCloseTime(String time) {
		browser.setTextField(".id", "FriCloseTime", time);
	}

	/** Select Sat close time */
	public void setSatCloseTime(String time) {
		browser.setTextField(".id", "SatCloseTime", time);
	}

	/** Select Sun close time */
	public void setSunCloseTime(String time) {
		browser.setTextField(".id", "SunCloseTime", time);
	}

	/** Select associated entrance */
	public void selectAssociatedEntrance(String entrance) {
		browser.selectDropdownList(".id", "AssociatedEntrance", entrance);
	}

	/** Set Minimum Payment Deadline */
	public void setminimumPaymentDeadline(String deadline) {
		browser.setTextField(".id", "MinimumPaymentDeadline", deadline);
	}

	/** Set MinimumPaymentReminderDaysBeforeDeadline */
	public void setMinimumPaymentReminderDaysBeforeDeadline(
			String reminderdeadline) {
		browser.setTextField(".id", "MinimumPaymentReminderDaysBeforeDeadline",
				reminderdeadline);
	}

	/** select block stay */
	public void selectBlockStay(String date) {

		String[] blockdate = date.split("[ ,;]");

		for (int i = 0; i < blockdate.length; i++) {

			if (blockdate[i].trim().equalsIgnoreCase("Mon")
					|| blockdate[i].equalsIgnoreCase("Monday")
					|| blockdate[i].equalsIgnoreCase("Mon.")) {
				browser.selectCheckBox(".id", "BlockStay_0");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Tue")
					|| blockdate[i].equalsIgnoreCase("Tuesday")
					|| blockdate[i].equalsIgnoreCase("Tue.")) {
				browser.selectCheckBox(".id", "BlockStay_1");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Wed")
					|| blockdate[i].equalsIgnoreCase("Wednesday")
					|| blockdate[i].equalsIgnoreCase("Wed.")) {
				browser.selectCheckBox(".id", "BlockStay_2");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Thu")
					|| blockdate[i].equalsIgnoreCase("Thuresday")
					|| blockdate[i].equalsIgnoreCase("Thu.")) {
				browser.selectCheckBox(".id", "BlockStay_3");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Fri")
					|| blockdate[i].equalsIgnoreCase("Friday")
					|| blockdate[i].equalsIgnoreCase("Fri.")) {
				browser.selectCheckBox(".id", "BlockStay_4");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Sat")
					|| blockdate[i].equalsIgnoreCase("Saturday")
					|| blockdate[i].equalsIgnoreCase("Sat.")) {
				browser.selectCheckBox(".id", "BlockStay_5");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Sun")
					|| blockdate[i].equalsIgnoreCase("Sunday")
					|| blockdate[i].equalsIgnoreCase("Sun.")) {
				browser.selectCheckBox(".id", "BlockStay_6");
			}
		}

	}

		
	/** Select holiday block Stay */
	public void selectHolidayBlockStay(String date) {
		String[] blockdate = date.split("[ ,;]");
		for (int i = 0; i < blockdate.length; i++) {
			if (blockdate[i].trim().equalsIgnoreCase("Mon")
					|| blockdate[i].equalsIgnoreCase("Monday")
					|| blockdate[i].equalsIgnoreCase("Mon.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_0");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Tue")
					|| blockdate[i].equalsIgnoreCase("Tuesday")
					|| blockdate[i].equalsIgnoreCase("Tue.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_1");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Wed")
					|| blockdate[i].equalsIgnoreCase("Wednesday")
					|| blockdate[i].equalsIgnoreCase("Wed.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_2");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Thu")
					|| blockdate[i].equalsIgnoreCase("Thuresday")
					|| blockdate[i].equalsIgnoreCase("Thu.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_3");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Fri")
					|| blockdate[i].equalsIgnoreCase("Friday")
					|| blockdate[i].equalsIgnoreCase("Fri.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_4");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Sat")
					|| blockdate[i].equalsIgnoreCase("Saturday")
					|| blockdate[i].equalsIgnoreCase("Sat.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_5");
			}
			if (blockdate[i].trim().equalsIgnoreCase("Sun")
					|| blockdate[i].equalsIgnoreCase("Sunday")
					|| blockdate[i].equalsIgnoreCase("Sun.")) {
				browser.selectCheckBox(".id", "HolidayBlockStay_6");
			}
		}

	}

	/** Check Allow availability Exception */
	public void selectAvalException() {
		browser.selectCheckBox(".id", "AllowAvailabilityException");
	}

	/** Check Allow Stay Length */
	public void selectAllowStayLen() {
		browser.selectCheckBox(".id", "AllowStayLengthException");
	}

	/** Select Maximum Window Type */
	public void selectMaximumWindowType(String type) {
		browser.selectDropdownList(".id", "MaximumWindowType", type);
	}

	/** select checkbox RollupEndOfMonthExtraDays */
	public void selectRollupEndOfMonthExtraDays(String check) {
		if (check.equalsIgnoreCase("yes")) {
			browser.selectCheckBox(".id", "RollupEndOfMonthExtraDays");
		}
	}

	public void unselectRollupEndOfMonthExtraDays() {
		browser.unSelectCheckBox(".id", "RollupEndOfMonthExtraDays");
	}
	
	/** Set BlockReleaseLength */
	public void setBlockReleaseLength(String len) {
		browser.setTextField(".id", "BlockReleaseLength", len);
	}

	/** select BlockReleaseUnit */
	public void selectBlockReleaseUnit(String unit) {
		browser.selectDropdownList(".id", "BlockReleaseUnit", unit);
	}

	/** Set BlockReleaseDayOfMonth */
	public void setBlockReleaseDayOfMonth(String month) {
		browser.setTextField(".id", "BlockReleaseDayOfMonth", month);
	}

	/** select BlockReleaseDayOfWeek */
	public void selectBlockReleaseDayOfWeek(String week) {
		browser.selectDropdownList(".id", "BlockReleaseDayOfWeek", week);
	}

	/** Set BlockReleaseDayOfWeekWithinMonth */
	public void setBlockReleaseDayOfWeekWithinMonth(String month) {
		browser.setTextField(".id", "BlockReleaseDayOfWeekWithinMonth", month);
	}

	public void unselectAllStartStayDayOfWeek() {
		IHtmlObject objs[] = browser.getCheckBox(".id", "StartStayDayOfWeek");
		if(objs.length > 0) {
			for(int i = 0; i < objs.length; i ++) {
				((ICheckBox)objs[i]).deselect();
			}
		}
	}
	
	/** Select start stay day of week */
	public void selectStartStayDayOfWeek(String day) {
		// browser.selectCheckBox(".id","StartStayDayOfWeek",".defaultValue",day);
		IHtmlObject objs[] = browser.getCheckBox(".id", "StartStayDayOfWeek");
		String[] date = day.split("[ ,;]");

		for (int i = 0; i < date.length; i++) {
			if (date[i].trim().equalsIgnoreCase("none")) {
				((ICheckBox) objs[0]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Sun")
					|| date[i].equalsIgnoreCase("Sunday")
					|| date[i].equalsIgnoreCase("Sun.")) {
				((ICheckBox) objs[1]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Mon")
					|| date[i].equalsIgnoreCase("Monday")
					|| date[i].equalsIgnoreCase("Mon.")) {
				((ICheckBox) objs[2]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Tue")
					|| date[i].equalsIgnoreCase("Tuesday")
					|| date[i].equalsIgnoreCase("Tue.")) {
				((ICheckBox) objs[3]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Wed")
					|| date[i].equalsIgnoreCase("Wednesday")
					|| date[i].equalsIgnoreCase("Wed.")) {
				((ICheckBox) objs[4]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Thu")
					|| date[i].equalsIgnoreCase("Thuresday")
					|| date[i].equalsIgnoreCase("Thu.")) {
				((ICheckBox) objs[5]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Fri")
					|| date[i].equalsIgnoreCase("Friday")
					|| date[i].equalsIgnoreCase("Fri.")) {
				((ICheckBox) objs[6]).select();
			}
			if (date[i].trim().equalsIgnoreCase("Sat")
					|| date[i].equalsIgnoreCase("Saturday")
					|| date[i].equalsIgnoreCase("Sat.")) {
				((ICheckBox) objs[7]).select();
			}
		}
	}

	/** Select Transaction */
	public void selectTransaction(String transaction) {
		browser.selectDropdownList(".id", "Transaction", transaction, true);
	}

//	/** Select Transaction Occurrence */
//	public void selectTransactionOccurence(String occur) {
//		browser.selectDropdownList(".id", new RegularExpression("transaction_occurrence|TransactionOccurrence(|FilteredByPrdCategory)", false), occur, true);
//	}

	/** set transaction occurrence days */
	public void setTransactionOccurDays(String days) {
		browser.setTextField(".id", "TransactionOccurrenceDays", days, true);
	}

	/** set transaction occurrence time */
	public void setTransactionOccurenceTime(String time) {
		browser.setTextField(".id", "TransactionOccurrenceTime", time, true);
	}

	/** Select Transaction Type for maximum/minimum group size rule*/
	public void selectTransactionType(String transType) {
		browser.selectDropdownList(".id", "TransactionTypeFilteredByPrdCategory", transType, true);
	}
	
	/** set Maximum Stay per period */
	public void setMaximumStayPerPeriod(String stay) {
		browser.setTextField(".id", "MaximumStayPerTimePeriodLength", stay,
				true);
	}

	/** Set Maximum Number of Orders Per Period */
	public void setMaximumNumberOfOrdersPerPeriod(String order) {
		browser.setTextField(".id", "MaximumNumberOfOrdersPerPeriod", order,
				true);
	}

	/** set Maximum Number Of Reservations With The Same Start Date */
	public void setMaxNumOfResWithSameStartDate(String num) {
		browser.setTextField(".id",
				"MaximumNumberOfReservationsWithSameStartDate", num, true);
	}

	public void setMaxNumOfOrders(String num) {
		browser.setTextField(".id",
				"MaximumNumberOfTourOrders", num, true);
	}
	
	public void setMaxNumOfTimes(String num) {
		browser.setTextField(".id",
				"MaximumNumberOfTourTimes", num, true);
	}
	
	public void setMaxNumOfTickets(String num) {
		browser.setTextField(".id",
				"MaximumNumberOfTickets", num, true);
	}

	/** Maximum Number Of Concurrent Reservations For Same Customer Pass Number **/
	public void setMaximumNumberOfConcurrentReservationsForSameCustomerPassNumber(
			String num) {
		browser
				.setTextField(
						".id",
						"MaximumNumberOfConcurrentReservationsForSameCustomerPassNumber",
						num, true);
	}

	/** Set Maximum Number Of Orders Within Stay Period */
	public void setMaxNumOfOrdWithinStayPer(String num) {
		browser.setTextField(".id", "MaximumNumberOfOrdersWithinStayPeriod",
				num, true);
	}

	/** Set Maximum Number Of Orders Within a Booking Period */
	public void setMaxNumOfOrdWithinBookPer(String num) {
		browser.setTextField(".id",
				"MaximumNumberOfOrdersWithinABookingPeriod", num, true);
	}

	/** Set Maximum Number Of Orders Per Call */
	public void setMaximumNumberOfOrdersPerCall(String num) {
		browser.setTextField(".id", "MaximumNumberOfOrdersPerCall", num, true);
	}

	/** Set Maximum Number Of Concurrent Reservations */
	public void setMaximumNumberOfConcurrentReservations(String num) {
		browser.setTextField(".id", "MaximumNumberOfConcurrentReservations",
				num, true);
	}

	/** Set Minimum Time Away Length */
	public void setMinimumTimeAwayLength(String time) {
		browser.setTextField(".id", "MinimumTimeAwayLength", time, true);
	}

	/** Select Minimum Time Away Unit */
	public void selectMinimumTimeAwayUnit(String time) {
		browser.selectDropdownList(".id", "MinimumTimeAwayUnit", time, true);
	}

	/** Set Time Out Length */
	public void setTimeOutLen(String len) {
		browser.setTextField(".id", "TimeoutLength", len, true);
	}
	/**
	 * set permit type.
	 * @param permitType
	 */
	public void selectPermitType(String permitType){
		browser.selectDropdownList(".id","PermitType",permitType);
	}
	/*
	 * select issue station.
	 */
	public void selectIssueStation(String issueStation){
		browser.selectDropdownList(".id", "IssueStation", issueStation);
	}

	/** Set group size number for person type */
	public void setPersonTypeGroupSizeNum(String personType, String num) {
		Property[] parentP = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^" + personType, false));
		Property[] childP = Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("GroupSizesByPersonType_.*", false));
		browser.setTextField(Property.atList(parentP, childP), num);
	}
	
	public void setPersonTypeGroupSizeNum(String[] personTypes, String[] nums) {
		for (int i = 0; i < personTypes.length; i++) {
			this.setPersonTypeGroupSizeNum(personTypes[i], nums[i]);
			this.waitLoading();
		}
	}
	
	/** Set Maximum Stay for Maximum Stay rule */
	public void setMaximumStay(String stay) {
		browser.setTextField(".id", "MaximumStay", stay);
	}
	
	/** Select level for Maximum Stay rule */
	public void selectLevel(String level) {
		browser.selectDropdownList(".id", "Level", level);
	}
	
	/** Select restriction for Restriction Entrance rule */
	public void selectRestriction(String value) {
		browser.selectDropdownList(".id", "Restriction", value);
	}
	
	/**
	 * The method execute the process that add new Rule
	 * 
	 * @param ruleData
	 *            -- rule information
	 * @return
	 */
	public String addNewRule(RuleDataInfo ruleData) {
		if(StringUtil.isEmpty(ruleData.ruleName)){
			ruleData.ruleName = this.getRuleName();
		}
		if (ruleData.status != null && !ruleData.status.equals("")) {
			this.selectStatus(ruleData.status);
		}

		if (ruleData.productCategory != null
				&& !ruleData.productCategory.equals("")) {
			this.selectProductCategory(ruleData.productCategory);
			ajax.waitLoading();
			waitLoading();
		}
		
		if(!StringUtil.isEmpty(ruleData.marinaRateType)) {
			this.selectMarinaRateType(ruleData.marinaRateType);
		}
		
		if (!StringUtil.isEmpty(ruleData.ticketCategory) && ruleData.productCategory.matches("Permit|Ticket")) {
			this.selectTicketCategory(ruleData.ticketCategory);
		}
		
		if (ruleData.productGroup != null && !ruleData.productGroup.equals("")) {
			this.selectProductGroup(ruleData.productGroup);
		}

		if (ruleData.loop != null && !ruleData.loop.equals("")) {
			this.selectLoop(ruleData.loop);
		}

		if (ruleData.product != null && !ruleData.product.equals("")) {
			this.selectProduct(ruleData.product);
		}

		if (ruleData.salesChannel != null && !ruleData.salesChannel.equals("")) {
			this.selectSaleChannel(ruleData.salesChannel);
		}

		if (ruleData.customerType != null && !ruleData.customerType.equals("")) {
			this.selectCustomerType(ruleData.customerType);
		}

		if(isSeasonTypeDropdownListExists() && isSeasonTypeDropdownListEnabled()) {
			if (ruleData.season != null && !ruleData.season.equals("")) {
				this.selectSeasonType(ruleData.season);
			}
		}

		if (ruleData.customerPassType != null
				&& !ruleData.customerPassType.equals("")) {
			this.selectCustomerPassType(ruleData.customerPassType);
		}

		if (ruleData.outOfState != null && !ruleData.outOfState.equals("")) {
			this.selectOutOfState(ruleData.outOfState);
		}
		
		if(isPaymentTypeDropdownListExists()) {
			if (ruleData.paymentType != null && !ruleData.paymentType.equals("")) {
				this.selectPaymentType(ruleData.paymentType);
			}
		}
		if (ruleData.customerMember != null
				&& !ruleData.customerMember.equals("")) {
			this.selectCustomerMemeber(ruleData.customerMember);
		}

		// judge whether the value from data pool equals with 'All' in case it
		// causes Memory Leak
		if (ruleData.associatedParty != null
				&& !ruleData.associatedParty.equals("")
				&& !ruleData.associatedParty.equalsIgnoreCase("All")) {
			this.selectAssociatedParty(ruleData.associatedParty);
		}
		
		if (ruleData.comments != null && !ruleData.comments.equals("")) {
			this.setDescription(ruleData.comments);
		}

		if (ruleData.startDate != null && !ruleData.startDate.equals("")) {
			this.setStartDate(ruleData.startDate);
		}

		if (ruleData.endDate != null && !ruleData.endDate.equals("")) {
			this.setEndDate(ruleData.endDate);
		}

		if (ruleData.effectiveDate != null
				&& !ruleData.effectiveDate.equals("")) {
			this.setEffectiveDate(ruleData.effectiveDate);
		}
		
		if (ruleData.accessType != null && !ruleData.accessType.equals("")) {
			this.selectAccessType(ruleData.accessType);
		}

		if (ruleData.ruleName.equals("Access Type") || (ruleData.length != null && !ruleData.length.equals(""))) {
			this.setLength(ruleData.length);
		}

		if (ruleData.minimumStay != null
				&& !ruleData.minimumStay.equals("")) {
			this.setMinimumStay(ruleData.minimumStay);
		}

		if (ruleData.minimumStayMon != null && !ruleData.minimumStayMon.equals("")) {
			this.setMinimumStayMon(ruleData.minimumStayMon);
		}

		if (ruleData.minimumStayTue != null && !ruleData.minimumStayTue.equals("")) {
			this.setMinimumStayTue(ruleData.minimumStayTue);
		}

		if (ruleData.minimumStayWed != null && !ruleData.minimumStayWed.equals("")) {
			this.setMinimumStayWed(ruleData.minimumStayWed);
		}

		if (ruleData.minimumStayThu != null && !ruleData.minimumStayThu.equals("")) {
			this.setMinimumStayThu(ruleData.minimumStayThu);
		}

		if (ruleData.minimumStayFri != null && !ruleData.minimumStayFri.equals("")) {
			this.setMinimumStayFri(ruleData.minimumStayFri);
		}

		if (ruleData.minimumStaySat != null && !ruleData.minimumStaySat.equals("")) {
			this.setMinimumStaySat(ruleData.minimumStaySat);
		}

		if (ruleData.minimumStaySun != null && !ruleData.minimumStaySun.equals("")) {
			this.setMinimumStaySun(ruleData.minimumStaySun);
		}

		if (ruleData.minimumStayHoliday != null
				&& !ruleData.minimumStayHoliday.equals("")) {
			this.setMinimumStayHoliday(ruleData.minimumStayHoliday);
		}

		if (ruleData.unit != null && !ruleData.unit.equals("")) {
			this.selectUnit(ruleData.unit);
		}

		if (ruleData.multiplesOnly != null && !ruleData.multiplesOnly.equals("")) {
			if (ruleData.multiplesOnly.equalsIgnoreCase("Yes")) {
				this.selectCheckBoxMultiplesOnly();
			}
		}

		if (ruleData.minimumStayRequiredWhenStayIncludesStartDay != null
				&& !ruleData.minimumStayRequiredWhenStayIncludesStartDay.equals("")) {
			if (ruleData.minimumStayRequiredWhenStayIncludesStartDay.equalsIgnoreCase("Yes")) {
				this
						.selectCheckBoxMinimumStayRequiredWhenStayIncludesStartDay();
			}
		}

		if (ruleData.combineOverlappingSeasons != null
				&& !ruleData.combineOverlappingSeasons.equals("")) {
			if (ruleData.combineOverlappingSeasons.equalsIgnoreCase("Yes")) {
				this.selectCheckBoxMinimumStayCombineOverlappingSeasons();
			}
		}

		if (ruleData.opentime != null && !ruleData.opentime.equals("")) {
			this.selectOpenTime(ruleData.opentime);
		}

		if (ruleData.dailyopentime != null
				&& !ruleData.dailyopentime.equals("")) {
			this.setDailyOpenTime(ruleData.dailyopentime);
		}

		if (ruleData.monOpenTime != null && !ruleData.monOpenTime.equals("")) {
			this.setMonOpenTime(ruleData.monOpenTime);
		}

		if (ruleData.tueOpenTime != null && !ruleData.tueOpenTime.equals("")) {
			this.setTueOpenTime(ruleData.tueOpenTime);
		}

		if (ruleData.wedOpenTime != null && !ruleData.wedOpenTime.equals("")) {
			this.setWedOpenTime(ruleData.wedOpenTime);
		}

		if (ruleData.thuOpenTime != null && !ruleData.thuOpenTime.equals("")) {
			this.setThuOpenTime(ruleData.thuOpenTime);
		}

		if (ruleData.friOpenTime != null && !ruleData.friOpenTime.equals("")) {
			this.setFriOpenTime(ruleData.friOpenTime);

		}

		if (ruleData.satOpenTime != null && !ruleData.satOpenTime.equals("")) {
			this.setSatOpenTime(ruleData.satOpenTime);
		}

		if (ruleData.sunOpenTime != null && !ruleData.sunOpenTime.equals("")) {
			this.setSunOpenTime(ruleData.sunOpenTime);
		}

		if (ruleData.closeTime != null && !ruleData.closeTime.equals("")) {
			this.selectCloseTime(ruleData.closeTime);
		}

		if (ruleData.dailyCloseTime != null
				&& !ruleData.dailyCloseTime.equals("")) {
			this.setDailyCloseTime(ruleData.dailyCloseTime);
		}

		if (ruleData.monCloseTime != null && !ruleData.monCloseTime.equals("")) {
			this.setMonCloseTime(ruleData.monCloseTime);
		}

		if (ruleData.tueCloseTime != null && !ruleData.tueCloseTime.equals("")) {
			this.setTueCloseTime(ruleData.tueCloseTime);
		}

		if (ruleData.wedCloseTime != null && !ruleData.wedCloseTime.equals("")) {
			this.setWedCloseTime(ruleData.wedCloseTime);
		}

		if (ruleData.thuCloseTime != null && !ruleData.thuCloseTime.equals("")) {
			this.setThuCloseTime(ruleData.thuCloseTime);
		}

		if (ruleData.friCloseTime != null && !ruleData.friCloseTime.equals("")) {
			this.setFriCloseTime(ruleData.friCloseTime);
		}

		if (ruleData.satCloseTime != null && !ruleData.satCloseTime.equals("")) {
			this.setSatCloseTime(ruleData.satCloseTime);
		}

		if (ruleData.sunCloseTime != null && !ruleData.sunCloseTime.equals("")) {
			this.setSunCloseTime(ruleData.sunCloseTime);
		}

		// Associated Entrance
		if (ruleData.associateEntrance != null
				&& !ruleData.associateEntrance.equals("")) {
			this.selectAssociatedEntrance(ruleData.associateEntrance);
		}

		// Automatic Cancellation
		if (ruleData.minimumPaymentDeadline != null
				&& !ruleData.minimumPaymentDeadline.equals("")) {
			this.setminimumPaymentDeadline(ruleData.minimumPaymentDeadline);
		}

		if (ruleData.minimumPaymentReminderDaysBeforeDeadline != null
				&& !ruleData.minimumPaymentReminderDaysBeforeDeadline
						.equals("")) {
			this
					.setMinimumPaymentReminderDaysBeforeDeadline(ruleData.minimumPaymentReminderDaysBeforeDeadline);
		}

		// Block Stay
		if (ruleData.blockStay != null && !ruleData.blockStay.equals("")) {
			this.selectBlockStay(ruleData.blockStay);
		}

		if (ruleData.holidayblockstay != null
				&& !ruleData.holidayblockstay.equals("")) {
			this.selectHolidayBlockStay(ruleData.holidayblockstay);
		}

		if (ruleData.allowAvailabilityException.equalsIgnoreCase("Yes")) {
			this.selectAvalException();
		}

		if (ruleData.allowStayLengthException.equalsIgnoreCase("Yes")) {
			this.selectAllowStayLen();
		}

		// Maximum Window
		if (!StringUtil.isEmpty(ruleData.maximumWindowType)) {
			this.selectMaximumWindowType(ruleData.maximumWindowType);
		}

		if (ruleData.rollupEndOfMonthExtraDays.equalsIgnoreCase("Yes")) {
			this
					.selectRollupEndOfMonthExtraDays(ruleData.rollupEndOfMonthExtraDays);
		} else {
			this.unselectRollupEndOfMonthExtraDays();
		}

		if(ruleData.ruleName.equals("Maximum Window")){
			if (ruleData.blockReleaseLength != null
					&& !ruleData.blockReleaseLength.equals("")) {
				this.setBlockReleaseLength(ruleData.blockReleaseLength);
			} else {
				this.setBlockReleaseLength(StringUtil.EMPTY);
			}
			
			if (ruleData.blockReleaseUnit != null
					&& !ruleData.blockReleaseUnit.equals("")) {
				this.selectBlockReleaseUnit(ruleData.blockReleaseUnit);
			} else {
				this.selectBlockReleaseUnit("none");
			}

			if (ruleData.blockReleaseDayOfMonth != null
					&& !ruleData.blockReleaseDayOfMonth.equals("")) {
				this.setBlockReleaseDayOfMonth(ruleData.blockReleaseDayOfMonth);
			} else {
				this.setBlockReleaseDayOfMonth(StringUtil.EMPTY);
			}

			if (ruleData.blockReleaseDayOfWeek != null
					&& !ruleData.blockReleaseDayOfWeek.equals("")) {
				this.selectBlockReleaseDayOfWeek(ruleData.blockReleaseDayOfWeek);
			} else {
				this.selectBlockReleaseDayOfWeek("none");
			}

			if (ruleData.blockReleaseDayOfWeekWithinMonth != null
					&& !ruleData.blockReleaseDayOfWeekWithinMonth.equals("")) {
				this
						.setBlockReleaseDayOfWeekWithinMonth(ruleData.blockReleaseDayOfWeekWithinMonth);
			} else {
				this.setBlockReleaseDayOfWeekWithinMonth(StringUtil.EMPTY);
			}
		}

		// Specified Stay Start
		if (ruleData.startStayDayOfWeek != null
				&& !ruleData.startStayDayOfWeek.equals("")) {
			this.unselectAllStartStayDayOfWeek();
			this.selectStartStayDayOfWeek(ruleData.startStayDayOfWeek);
		}
		// maximum/minimum group size
		if (!StringUtil.isEmpty(ruleData.transactionType)) {
			this.selectTransactionType(ruleData.transactionType);
		}
		
		// Transaction Restriction
		if (ruleData.transaction != null && !ruleData.transaction.equals("")) {
			this.selectTransaction(ruleData.transaction);
		}

		if (ruleData.transactionOccurrence != null
				&& !ruleData.transactionOccurrence.equals("")) {
			this.selectTransactionOccurrence(ruleData.transactionOccurrence);
		}

		if (ruleData.transactionOccurrenceDays != null
				&& !ruleData.transactionOccurrenceDays.equals("")) {
			this.setTransactionOccurDays(ruleData.transactionOccurrenceDays);
		}

		if (ruleData.transactionOccurrenceTime != null
				&& !ruleData.transactionOccurrenceTime.equals("")) {
			this.setTransactionOccurenceTime(ruleData.transactionOccurrenceTime);
		}

		if (ruleData.maximumStayPerPeriod != null
				&& !ruleData.maximumStayPerPeriod.equals("")) {
			this.setMaximumStayPerPeriod(ruleData.maximumStayPerPeriod);
		}

		if (ruleData.maximumNumberOfOrdersPerPeriod != null
				&& !ruleData.maximumNumberOfOrdersPerPeriod.equals("")) {
			this
					.setMaximumNumberOfOrdersPerPeriod(ruleData.maximumNumberOfOrdersPerPeriod);
		}

		// MaximumNumberOfReservationsWithSameStartDate
		if (ruleData.maximumNumberOfReservationsWithSameStartDate != null
				&& !ruleData.maximumNumberOfReservationsWithSameStartDate
						.equals("")) {
			this
					.setMaxNumOfResWithSameStartDate(ruleData.maximumNumberOfReservationsWithSameStartDate);
		}

		// MaximumNumberOfOrders-Times-TicketsWithinInventoryPeriodRule
		if (ruleData.maxOrders != null && !ruleData.maxOrders.equals("")) {
			this.setMaxNumOfOrders(ruleData.maxOrders);
		}
		if (ruleData.maxTimes != null && !ruleData.maxTimes.equals("")) {
			this.setMaxNumOfTimes(ruleData.maxTimes);
		}
		if (ruleData.maxTickets != null && !ruleData.maxTickets.equals("")) {
			this.setMaxNumOfTickets(ruleData.maxTickets);
		}

		// Maximum Number Of Concurrent Reservations for Same Customer Pass
		// Number
		if (ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber != null
				&& !ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber
						.equals("")) {
			this
					.setMaximumNumberOfConcurrentReservationsForSameCustomerPassNumber(ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber);
		}

		// MaximumNumberOfOrdersWithinStayPeriod
		if (ruleData.maximumNumberOfOrdersWithinStayPeriod != null
				&& !ruleData.maximumNumberOfOrdersWithinStayPeriod.equals("")) {
			this
					.setMaxNumOfOrdWithinStayPer(ruleData.maximumNumberOfOrdersWithinStayPeriod);
		}

		// Maximum Number Of Orders Within a Booking Period
		if (ruleData.maximumNumberOfOrdersWithinABookingPeriod != null
				&& !ruleData.maximumNumberOfOrdersWithinABookingPeriod
						.equals("")) {
			this
					.setMaxNumOfOrdWithinBookPer(ruleData.maximumNumberOfOrdersWithinABookingPeriod);
		}

		// Maximum Number Of Orders Per Call
		if (ruleData.maximumNumberOfOrdersPerCall != null
				&& !ruleData.maximumNumberOfOrdersPerCall.equals("")) {
			this
					.setMaximumNumberOfOrdersPerCall(ruleData.maximumNumberOfOrdersPerCall);
		}

		if (ruleData.maximumNumberOfConcurrentReservations != null
				&& !ruleData.maximumNumberOfConcurrentReservations.equals("")) {
			this
					.setMaximumNumberOfConcurrentReservations(ruleData.maximumNumberOfConcurrentReservations);
		}

		if (ruleData.minimumTimeAwayLength != null
				&& !ruleData.minimumTimeAwayLength.equals("")) {
			this.setMinimumTimeAwayLength(ruleData.minimumTimeAwayLength);
		}

		if (ruleData.minimumTimeAwayUnit != null
				&& !ruleData.minimumTimeAwayUnit.equals("")) {
			this.selectMinimumTimeAwayUnit(ruleData.minimumTimeAwayUnit);
		}

		if (ruleData.timeoutLen != null && !ruleData.timeoutLen.equals("")) {
			this.setTimeOutLen(ruleData.timeoutLen);
		}

		// Product Restricted in Use
		if(this.isProductRestrictedInUsedRule()) {
			this.unselectAllCustomerTypeCheckboxes();
			this.unselectAllCustomerPassTypeCheckboxes();
			if (ruleData.customerTypes != null && ruleData.customerTypes.length > 0) {
				for (int i = 0; i < ruleData.customerTypes.length; i++) {
					if (!StringUtil.isEmpty(ruleData.customerTypes[i])) {
						this.selectCustomerTypeByCheckBox(ruleData.customerTypes[i]);
					}
				}
			}

			if (ruleData.customerPassTypes != null
					&& ruleData.customerPassTypes.length > 0) {
				for (int i = 0; i < ruleData.customerPassTypes.length; i++) {
					if (!ruleData.customerPassTypes[i].equals("")) {
						this
								.selectCustomerPassTypeByCheckBox(ruleData.customerPassTypes[i]);
					}
				}
			}
		}
		
		if(ruleData.permitType != null 
				&& ruleData.permitType.length()>0){
			this.selectPermitType(ruleData.permitType);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		//For Issue permit restriction.
		if(ruleData.issueStation != null
				&& ruleData.issueStation.length()>0){
			this.selectIssueStation(ruleData.issueStation);
		}
		
		//Maximum Number of Concurrent Reservations (Permits), Maximum Total Stay Permits
		if(!StringUtil.isEmpty(ruleData.reservationStatus)) {
			this.selectReservationStatus(ruleData.reservationStatus);
		}
				
		// Maximum/Minimum Group Size 
		if (ruleData.personTypes != null && ruleData.personTypes.length > 0 && 
				ruleData.personTypesNums != null && ruleData.personTypes.length == ruleData.personTypesNums.length) {
			this.setPersonTypeGroupSizeNum(ruleData.personTypes, ruleData.personTypesNums);
		}
		
		// Maximum Stay
		if (StringUtil.notEmpty(ruleData.maximumStay)) {
			this.setMaximumStay(ruleData.maximumStay);
		}
		
		if (StringUtil.notEmpty(ruleData.level)) {
			this.selectLevel(ruleData.level);
		}
		
		// Restrict Entrance 
		if (StringUtil.notEmpty(ruleData.restriction)) {
			this.selectRestriction(ruleData.restriction);
		}
		
		//click Add New Definition button
		this.clickAddNewDefination();

		
		return "";
	}

	public void selectReservationStatus(String status) {
		browser.selectDropdownList(".id", "ReservationStatus", status, true);
	}
	
	public void selectCustomerTypeByCheckBox(String customerType) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^(| )" + customerType, false));
		if (objs.length < 0) {
			throw new ItemNotFoundException(
					"Did not found customer type check box object.");
		}
		Property[] p = new Property[1];
		p[0] = new Property(".id", "CustomerType");
		browser.selectCheckBox(p, 0, true, objs[0]);

		Browser.unregister(objs);
	}
	
	public boolean isProductRestrictedInUsedRule() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", ".id", "CustomerType");
	}
	
	private List<String> getCheckboxLabelNames(String forValue) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", forValue);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find checkbox label object by for value: " + forValue);
		
		List<String> names = new ArrayList<String>();
		for(int i = 0; i < objs.length; i ++) {
			names.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return names;
	}
	
	/**
	 * Product Restricted in Use rule
	 * @return
	 */
	public List<String> getCustomerTypes() {
		return getCheckboxLabelNames("CustomerType");
	}
	
	/**
	 * Product Restricted in Use rule
	 * @return
	 */
	public List<String> getCustomerPasses() {
		return getCheckboxLabelNames("CustomerPassType");
	}
	
	public void unselectAllCustomerTypeCheckboxes() {
		IHtmlObject objs[] = browser.getCheckBox(".id", "CustomerType");
		if(objs.length > 0) {
			for(int i = 0; i < objs.length; i ++) {
				((ICheckBox)objs[i]).deselect();
			}
		}
	}
	
	public void selectCustomerPassTypeByCheckBox(String customerPassType) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^(| )" + customerPassType,
						false));
		if (objs.length < 0) {
			throw new ItemNotFoundException(
					"Did not found customer type check box object.");
		}
		Property[] p = new Property[1];
		p[0] = new Property(".id", "CustomerPassType");
		browser.selectCheckBox(p, 0, true, objs[0]);

		Browser.unregister(objs);
	}

	public void unselectAllCustomerPassTypeCheckboxes() {
		IHtmlObject objs[] = browser.getCheckBox(".id", "CustomerPassType");
		if(objs.length > 0) {
			for(int i = 0; i < objs.length; i ++) {
				((ICheckBox)objs[i]).deselect();
			}
		}
	}
	
	/** Click Find Rules link */
	public void clickFindRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Find Rules");
	}

	/** Click the Delete link */
	public void clickDelect() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/** Click the Deactivate link */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click the Add New Defination link */
	public void clickAddNewDefination() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add New Definition");
	}

	/** Click the Activate link */
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/** Click Go link */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/** Check whether the error msg exist */
	public boolean isErrorMsgExist() {
		// return
		// (browser.checkHtmlObjectExists(".class","Html.DIV",".className","message msgerror")||browser.checkHtmlObjectExists(".class","Html.DIV",".className","message msgsuccess"));
		return browser.checkHtmlObjectExists(".class", "Html.DIV",
				".className", "message msgerror");
	}

	/** Click select location */
	public void clickSelectLocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Select Location",
				true);
	}

	public void selectFirstRule() {
		browser.selectCheckBox(".id", "row_0_checkbox");
	}

//	public String getRuleInfo(String ruleName) {
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
//				".text", new RegularExpression(
//						"^Rule Cond. ID Location Active Ticket Category .*",
//						false));
//		String[] temp = objs[0].getProperty(".text").toString().split(ruleName);
//		String ruleInfo = temp[temp.length - 2];
//		Browser.unregister(objs);
//
//		return ruleInfo;
//	}

	public String getErrorMsg() {
		String msgs[] = getErrorMsgs();
		if(msgs != null && msgs.length > 0 ) return msgs[0];
		return null;
	}
	
	public String[] getErrorMsgs() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".className", "message msgerror");
		
		String msgs[] = null;
		if (objs != null && objs.length > 0) {
			msgs = new String[objs.length];
			
			for(int i = 0; i < objs.length; i ++) {
				msgs[i] = objs[i].text();
			}
		}
		
		Browser.unregister(objs);
		return msgs;
	}
	
	/**
	 * Get Maximum Number of Orders-Times-Tickets rule id by Rule Data Info
	 * @param ruleInfo
	 * @return
	 */
	public String getMaximumNumOfOrdersTimesTicketsRuleIDByRuleInfo(RuleDataInfo ruleInfo){
		String id = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Cond. ID Location Active.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find rule table on Rule Value sub page.");
		}
		System.out.println("table length: " + objs.length);
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 2){
			logger.info("There is no existing rule for Maximum Number of Orders-Times-Tickets Rule.");
			return null;
		}
		
		for(int i=0; i<table.rowCount()-1; i++){
			String active = table.getCellValue(i, 3);
			String productCategory = table.getCellValue(i, 4);
			String productGroup = table.getCellValue(i, 6);
			String product = table.getCellValue(i, 8);
			String salesChannel = table.getCellValue(i, 9);
			String custType = table.getCellValue(i, 10);
			String custPassType = table.getCellValue(i, 12);
			String outOfState = table.getCellValue(i, 13);
			String startDate = table.getCellValue(i, 18);
			String endDate = table.getCellValue(i, 19);
			String effectiveDate = table.getCellValue(i, 20);
			String maxOrders = table.getCellValue(i, 21);
			String maxTimes = table.getCellValue(i, 22);
			String maxTickets = table.getCellValue(i, 23);
			
			if(active.equals(ruleInfo.status)
			&& productCategory.equals(ruleInfo.productCategory)
			&& productGroup.equals(ruleInfo.productGroup)
			&& product.equals(ruleInfo.product)
			&& salesChannel.equals(ruleInfo.salesChannel)
			&& custType.equals(ruleInfo.customerType)
			&& custPassType.equals(ruleInfo.customerPassType)
			&& outOfState.equals(ruleInfo.outOfState)
			&& DateFunctions.compareDates(startDate, ruleInfo.startDate) == 0
			&& DateFunctions.compareDates(endDate, ruleInfo.endDate) == 0
			&& DateFunctions.compareDates(effectiveDate, ruleInfo.effectiveDate) == 0
			&& maxOrders.equals(ruleInfo.maxOrders)
			&& maxTimes.equals(ruleInfo.maxTimes)
			&& maxTickets.equals(ruleInfo.maxTickets)){
				id = table.getCellValue(i, 1);
				break;				
			}
		}
		
		Browser.unregister(objs);
		return id;
	}
	

	
	
	public void searchRuleByID(String id){
		logger.info("Search rule by id:"+id);
		setRuleCondID(id);
		clickGo();
		this.waitLoading();
	}
	
	/**
	 * select Rule By Row Num
	 * @param row -- Started from 0
	 */
	public void selectRuleByRowNum(int row){
		browser.selectCheckBox(".id", "row_"+row+"_checkbox");
	}
	
	/**
	 * Activate rule by ID
	 * @param id
	 */
	public void activateRuleByID(String id){
		
		searchRuleByID(id);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Cond. ID Location Active.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find rule table on Rule Value sub page.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 2){
			throw new ErrorOnDataException("There is no existing rule with id "+id);
		}
		
		logger.info("Activate rule with id "+id);
		selectRuleByRowNum(0);//select the first row check box
		this.clickActivate();
		this.waitLoading();
		
		Browser.unregister(objs);
	}
	
	/**
	 * Deactivate rule by ID
	 * @param id
	 */
	public void deactivateRuleByID(String id){
		searchRuleByID(id);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Cond. ID Location Active.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find rule table on Rule Value sub page.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 2){
			throw new ErrorOnDataException("There is no existing rule with id "+id);
		}
		
		logger.info("Deactivate rule with id "+id);
		selectRuleByRowNum(0);//select the first row check box
		this.clickDeactivate();
		this.waitLoading();
		
		Browser.unregister(objs);
	}
	
	/**
	 * Delete rule by ID
	 * @param id
	 */
	public void deleteRuleByID(String id){
		searchRuleByID(id);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Cond. ID Location Active.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find rule table on Rule Value sub page.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 2){
			throw new ErrorOnDataException("There is no existing rule with id "+id);
		}
		
		logger.info("Delete rule by id: "+id);
		selectRuleByRowNum(0);//select the first row check box
		this.clickDelect();
		this.waitLoading();
		
		Browser.unregister(objs);
	}
	
	/**
	 * verify rule status
	 * @param id
	 * @param status
	 */
	public void verifyRuleStatus(String id, String status){
		String active = getRuleStatusByID(id);
		if(null == active){
			throw new ErrorOnPageException("There is no existing rule with id "+id);
		}
		if(!status.equals(active)){
			throw new ErrorOnPageException("Rule(id:"+id+") status should be "+status, "status", "active");
		}
		logger.info("Rule(id:"+id+") status was correct.");
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getRuleStatusByID(String id) {
		this.searchRuleByID(id);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Cond. ID Location Active.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find rule table on Rule Value sub page.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 2){
			logger.info("There is no existing rule with id "+id);
			return null;
		}
		String status = table.getCellValue(1, 3);
		
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentLocationName(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Location Name.*", false));
		if(objs.length <1){
			throw new ErrorOnPageException("Could not find current location name.");
		}
		String content = objs[0].text();
		String locationName = content.replaceAll("Location Name", "").trim();
		
		Browser.unregister(objs);
		return locationName;
	}
	
	/**
	 * get drop down list options of Product Category
	 * @return
	 */
	public List<String> getProductCategoryOptions() {
		return browser.getDropdownElements(".id", "product_category");
	}
	
	/**
	 * get drop down list options of Ticket/Permit Category
	 * @return
	 */
	public List<String> getTicketCategoryOptions() {
		return browser.getDropdownElements(".id", "sales_category");
	}
	
	public List<String> getPermitCategoryOptions() {
		return getTicketCategoryOptions();
	}
	
	/**
	 * get drop down list options of Permit Type
	 * @return
	 */
	public List<String> getPermitTypeOptions() {
		return browser.getDropdownElements(".id", "PermitType");
	}
	
	/**
	 * get drop down list options of Reservation Status
	 * @return
	 */
	public List<String> getReservationStatusOptions() {
		return browser.getDropdownElements(".id", "ReservationStatus");
	}
	
	private IHtmlObject[] getRuleTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("Rule Cond. ID(\\s)?Location(\\s)?Active", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Rule table object.");
		}
		
		return objs;
	}
	
	/**
	 * get rule id identified by an unique comment
	 * @param comment
	 * @return
	 */
	public String getRuleCondIDByComment(String comment) {
		IHtmlObject commentObjs[] = browser.getTextField(".id", "description");
		if(commentObjs.length < 1) {
			throw new ItemNotFoundException("Can't find Comment text field object.");
		}
		int counter = -1;
		for(int i = 0; i < commentObjs.length; i ++) {
			if(((IText)commentObjs[i]).getText().equals(comment)) {
				counter = i;
				break;
			}
		}
		if(counter == -1) {
			throw new ErrorOnPageException("There is not a rule instance exists which its comment is: " + comment);
		}
		
		//get rule cond id
		IHtmlObject objs[] = getRuleTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		String id = table.getCellValue(counter + 1, 1);
		
		Browser.unregister(commentObjs);
		Browser.unregister(objs);
		
		return id;
	}
	
	/**
	 * get rule value by rule id 
	 * @param id
	 * @return
	 */
	public List<String> getRuleValuesByRuleID(String id){
		IHtmlObject objs[] = getRuleTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(1, id);
		List<String> rowValues = table.getRowValues(rowIndex);
		
		Browser.unregister(objs);
		return rowValues;
	}
	
	/**
	 * get rule common attributes
	 * @param id
	 * @return
	 */
	public RuleDataInfo getRuleInfoForCommonByRuleID(String id){
		List<String> rowValues=this.getRuleValuesByRuleID(id);
		
		RuleDataInfo rule = new RuleDataInfo();
		rule.condID = id;
		rule.location = rowValues.get(2);
		rule.status = rowValues.get(3);
		rule.productCategory = rowValues.get(4);
		if(isMarinaRateTypeExists()) {
			rule.marinaRateType=rowValues.get(5);
			rule.ticketCategory = rowValues.get(6);
			rule.productGroup = rowValues.get(7);
			rule.loop = rowValues.get(8);
			rule.product = rowValues.get(9);
			rule.salesChannel = rowValues.get(10);
			rule.customerType = rowValues.get(11);
			rule.season=rowValues.get(12);
			rule.customerPassType = rowValues.get(13);
			rule.outOfState = rowValues.get(14);
			rule.customerMember = rowValues.get(15);
			rule.associatedParty = rowValues.get(16);
			rule.startDate = rowValues.get(18);
			rule.endDate = rowValues.get(19);
			rule.effectiveDate = rowValues.get(20);
		} else {
			rule.ticketCategory = rowValues.get(5);
			rule.productGroup = rowValues.get(6);
			rule.loop = rowValues.get(7);
			rule.product = rowValues.get(8);
			rule.salesChannel = rowValues.get(9);
			rule.customerType = rowValues.get(10);
			rule.customerPassType = rowValues.get(11);
			rule.outOfState = rowValues.get(12);
			rule.customerMember = rowValues.get(13);
			rule.associatedParty = rowValues.get(14);
			rule.startDate = rowValues.get(16);
			rule.endDate = rowValues.get(17);
			rule.effectiveDate = rowValues.get(18);
		}
		
		
		return rule;
	}
	
	/**
	 * get issue permit rule info.
	 * @param id
	 * @return
	 */
	public RuleDataInfo getIssuePermitResRuleInfo(String id){
		List<String> rowValues=this.getRuleValuesByRuleID(id);
		
		RuleDataInfo rule = this.getRuleInfoForCommonByRuleID(id);
		
		//different rule
		rule.permitType = rowValues.get(19);
		rule.issueStation = rowValues.get(20);
	
		return rule;
	}
	
	public RuleDataInfo getMaximumOrdersWithinStayPeriodRuleInfo(String id){
		List<String> rowValues=this.getRuleValuesByRuleID(id);
		
		RuleDataInfo rule = this.getRuleInfoForCommonByRuleID(id);
		
		//different rule
		rule.maximumNumberOfOrdersWithinStayPeriod = rowValues.get(21);
		rule.reservationStatus = rowValues.get(22);
	
		return rule;
	}
	
	public List<String> getRuleListInfo(String id) {
		IHtmlObject objs[] = getRuleTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(1, id);
		List<String> rowValues = table.getRowValues(rowIndex);
		
		Browser.unregister(objs);
		return rowValues;
	}
	
	public RuleDataInfo getMaximumNumberOfOrdersTimesTicketsPerCallCartRuleInfo(String id) {
		List<String> rowValues = getRuleListInfo(id);
		
		RuleDataInfo rule = new RuleDataInfo();
		rule.condID = id;
		rule.location = rowValues.get(2);
		rule.status = rowValues.get(3);
		rule.productCategory = rowValues.get(4);
		rule.ticketCategory = rowValues.get(5);
		rule.productGroup = rowValues.get(6);
		rule.loop = rowValues.get(7);
		rule.product = rowValues.get(8);
		rule.salesChannel = rowValues.get(9);
		rule.customerType = rowValues.get(10);
		rule.customerPassType = rowValues.get(11);
		rule.outOfState = rowValues.get(12);
		rule.customerMember = rowValues.get(13);
		rule.associatedParty = rowValues.get(14);
		rule.startDate = rowValues.get(16);
		rule.endDate = rowValues.get(17);
		rule.effectiveDate = rowValues.get(18);
		rule.maxOrders = rowValues.get(19);
		rule.maxTimes = rowValues.get(20);
		rule.maxTickets = rowValues.get(21);
		
		return rule;
	}
	
	/**
	 * Get rule detail info
	 * @param id
	 * @return
	 */
	public RuleDataInfo getMaximumNumberOfConcurrentReservationsRuleInfo(String id) {
		List<String> rowValues = getRuleListInfo(id);
		
		RuleDataInfo rule = new RuleDataInfo();
		rule.condID = id;
		rule.location = rowValues.get(2);
		rule.status = rowValues.get(3);
		rule.productCategory = rowValues.get(4);
		rule.ticketCategory = rowValues.get(6);
		rule.productGroup = rowValues.get(7);
		rule.loop = rowValues.get(8);
		rule.product = rowValues.get(9);
		rule.salesChannel = rowValues.get(10);
		rule.customerType = rowValues.get(11);
		rule.season = rowValues.get(12);
		rule.customerPassType = rowValues.get(13);
		rule.outOfState = rowValues.get(14);
//		rule.paymentType = rowValues.get(15);//product change
		rule.customerMember = rowValues.get(15);
		rule.associatedParty = rowValues.get(16);
		//TODO comment
//		String c = rowValues.get(17);
//		rule.comments = browser.getTextFieldValue(".id", "", rowIndex - 1);
		rule.startDate = rowValues.get(18);
		rule.endDate = rowValues.get(19);
		rule.effectiveDate = rowValues.get(20);
		//TODO different rule
		rule.maximumNumberOfConcurrentReservations = rowValues.get(21);
		rule.permitType = rowValues.get(22);
		rule.reservationStatus = rowValues.get(23);
		
		return rule;
	}
	
	public RuleDataInfo getProductRestrictedInUseRuleInfo(String id) {
		List<String> rowValues = this.getRuleListInfo(id);
		
		RuleDataInfo rule = new RuleDataInfo();
		rule.condID = id;
		rule.location = rowValues.get(2);
		rule.status = rowValues.get(3);
		rule.productCategory = rowValues.get(4);
		rule.productGroup = rowValues.get(6);
		rule.loop = rowValues.get(7);
		rule.product = rowValues.get(8);
		rule.salesChannel = rowValues.get(9);
		rule.season = rowValues.get(10);
		rule.outOfState = rowValues.get(11);
		rule.startDate = rowValues.get(17);
		rule.endDate = rowValues.get(18);
		rule.effectiveDate = rowValues.get(19);
		rule.customerTypes = rowValues.get(20).split(", ");
		rule.customerPassTypes = rowValues.get(21).split(", ");
		
		return rule;
	}
	
	/**
	 * Compare the rule common info
	 * @param expected
	 * @param actual
	 * @return
	 */
	public boolean commonCompareRuleInfo(RuleDataInfo expected, RuleDataInfo actual) {
		logger.info("Compare rule common info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Location", expected.location, actual.location);
		result &= MiscFunctions.compareResult("Status", (expected.status.equals(OrmsConstants.ACTIVE_STATUS) || expected.status.equals(OrmsConstants.YES_STATUS)) ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS, actual.status);
		result &= MiscFunctions.compareResult("Product Category", expected.productCategory, actual.productCategory);
		result &= MiscFunctions.compareResult("Ticket Category", expected.ticketCategory, actual.ticketCategory);
		result &= MiscFunctions.compareResult("Product Group", expected.productGroup, actual.productGroup);
		result &= MiscFunctions.compareResult("Area/Loop", expected.loop, actual.loop);
		result &= MiscFunctions.compareResult("Product", expected.product, actual.product);
		result &= MiscFunctions.compareResult("Sales Channel", expected.salesChannel, actual.salesChannel);
		result &= MiscFunctions.compareResult("Customer Type", expected.customerType, actual.customerType);
		result &= MiscFunctions.compareResult("Out Of State", expected.outOfState, actual.outOfState);
		result &= MiscFunctions.compareResult("Payment Type", expected.paymentType, actual.paymentType);
		result &= MiscFunctions.compareResult("Customer Member", expected.customerMember, actual.customerMember);
		result &= MiscFunctions.compareResult("Associated Party", expected.associatedParty, actual.associatedParty);
		result &= MiscFunctions.compareResult("Start Date", expected.startDate, actual.startDate);
		result &= MiscFunctions.compareResult("End Date", expected.endDate, actual.endDate);
		result &= MiscFunctions.compareResult("Effective Date", expected.effectiveDate, actual.effectiveDate);
		
		return result;
	}
	
	public void verifyRuleInfo(RuleDataInfo expected, RuleDataInfo actual) {
		boolean result = true;
		result &= commonCompareRuleInfo(expected, actual);
		if(!StringUtil.isEmpty(expected.permitType)) result &= MiscFunctions.compareResult("Permit Type", expected.permitType, actual.permitType);
		if(!StringUtil.isEmpty(expected.reservationStatus)) result &= MiscFunctions.compareResult("Reservation Status", expected.reservationStatus, actual.reservationStatus);
		if(!StringUtil.isEmpty(expected.maximumNumberOfConcurrentReservations)) result &= MiscFunctions.compareResult("Maximum Number Of Concurrent Reservations", expected.maximumNumberOfConcurrentReservations, actual.maximumNumberOfConcurrentReservations);
		
		//Product Restricted in Use rule
		if(expected.customerTypes != null && expected.customerTypes.length > 0) {
			result &= MiscFunctions.compareResult("Customer Type size", expected.customerTypes.length, actual.customerTypes.length);
			for(int i = 0; i < expected.customerTypes.length; i ++) {
				result &= MiscFunctions.compareResult("Customer Type - " + i, expected.customerTypes[i], actual.customerTypes[i]);
			}
		}
		if(expected.customerPassTypes != null && expected.customerPassTypes.length > 0) {
			result &= MiscFunctions.compareResult("Customer Pass Type size", expected.customerPassTypes.length, actual.customerPassTypes.length);
			for(int i = 0; i < expected.customerPassTypes.length; i ++) {
				result &= MiscFunctions.compareResult("Customer Pass Type - " + i, expected.customerPassTypes[i], actual.customerPassTypes[i]);
			}
		}
		
		//TODO different rule
		
		if(!result) {
			throw new ErrorOnPageException("The rule info is NOT correct, please refer to log for detail info.");
		} else logger.info("Rule info is correct.");
	}
	
	/**
	 * verify issue permit restriction rule info.
	 * @param expected
	 * @param actual
	 */
	public void verifyIssuePermitResRuleInfo(RuleDataInfo expected, RuleDataInfo actual){
		boolean result = true;
		result &= commonCompareRuleInfo(expected, actual);
		result &= MiscFunctions.compareResult("Permit Type", expected.permitType, actual.permitType);
		result &= MiscFunctions.compareResult("Issue Station", expected.issueStation, actual.issueStation);
		if(!result) {
			throw new ErrorOnPageException("The rule info is NOT correct, please refer to log for detail info.");
		} else logger.info("Rule info is correct.");
	}
	
	/**
	 * verify Maximum Number Of Orders Within Stay Period rule info.
	 * @param expected
	 * @param actual
	 */
	public void verifyMaximumOrdersWithinStayPeriodRuleInfo(RuleDataInfo expected, RuleDataInfo actual){
		boolean result = true;
		result &= commonCompareRuleInfo(expected, actual);
		result &= MiscFunctions.compareResult("Maximum Number Of Orders Within Stay Period", expected.maximumNumberOfOrdersWithinStayPeriod, actual.maximumNumberOfOrdersWithinStayPeriod);
		result &= MiscFunctions.compareResult("Reservation Status", expected.reservationStatus, actual.reservationStatus);
		if(!result) {
			throw new ErrorOnPageException("The rule info is NOT correct, please refer to log for detail info.");
		} else logger.info("Rule info is correct.");
	}
	
	public void searchActiveRuleBycommon(RuleDataInfo src, String ruleName)
	{
		this.selectSearchShow(src.status);
		
		if (!ruleName.equalsIgnoreCase(RulesCreation.TIME_TO_CLEAR)) {
			
			if(!StringUtil.isEmpty(src.productCategory))
			{
				this.selectProductCategory(src.productCategory);
			}
		}

		
		if(!StringUtil.isEmpty(src.productGroup))
		{
			this.selectProductGroup(src.productGroup);
		}
		
		if(!StringUtil.isEmpty(src.salesChannel))
		{
			this.selectSaleChannel(src.salesChannel);
		}
		
		if(!StringUtil.isEmpty(src.customerType))
		{
			this.selectCustomerType(src.customerType);
		}
		
		if(!StringUtil.isEmpty(src.season))
		{
			this.selectSeasonType(src.season);
		}
		
		if(!StringUtil.isEmpty(src.customerPassType))
		{
			this.selectCustomerPassType(src.customerPassType);
		}
		
		if(!StringUtil.isEmpty(src.outOfState))
		{
			this.selectOutOfState(src.outOfState);
		}
		
		if(!StringUtil.isEmpty(src.paymentType))
		{
			this.selectPaymentType(src.paymentType);
		}
				
		if(!StringUtil.isEmpty(src.customerMember))
		{
			this.selectCustomerMemeber(src.customerMember);
		}
		
		this.clickGo();
		this.waitLoading();
		
	}
	
		
	public String checkRuleExistInList(String ruleName, RuleDataInfo src)
	{
			
		this.searchActiveRuleBycommon(src, ruleName);		
		List<RuleDataInfo> rules = this.getAllRecordsOnPage(ruleName);
		
		RuleDataInfo rule = this.getLatestRule(rules);
		logger.info("New created Rule ID is -->"+rule.condID);
		logger.info("New rule added on page is-->\r\n"+StringUtil.ObjToString(rule));
		logger.info("New rule from datapool is-->\r\n"+StringUtil.ObjToString(src));
		return ((this.compareRules(rule, src, ruleName)?"true":"false")+"&"+rule.condID);
		

	}
	
	
	
	/**
	 * @param rules
	 */
	private RuleDataInfo getLatestRule(List<RuleDataInfo> rules) {

		int[] ids = new int[rules.size()];
		//fetch all rule id
		for(int i=0;i<rules.size();i++)
		{
			ids[i] = Integer.valueOf(rules.get(i).condID);
		}
		//order Asc
		Arrays.sort(ids);
		
		//get the rule of which the id number is the largest.
		int id = ids[ids.length-1];
		RuleDataInfo latestRule = null;
		
		for(RuleDataInfo rule: rules)
		{
			if(String.valueOf(id).equalsIgnoreCase(rule.condID))
			{
				latestRule = rule;
				break;
			}
		}
		
		return latestRule;
	}

	/**
	 * @param ruleData: rule record got from page
	 * @param src: source rule from datapool
	 * @param ruleName: rule type
	 */
	private boolean compareRules(RuleDataInfo ruleData, RuleDataInfo src, String ruleName) {

		boolean result = true;

		
		if (!ruleName.equalsIgnoreCase(RulesCreation.TIME_TO_CLEAR)) {
			
			result &= (StringUtil.isEmpty(src.productCategory)?true:ruleData.productCategory.equalsIgnoreCase(src.productCategory));
		}

		result &= (StringUtil.isEmpty(src.productGroup)?true:ruleData.productGroup.equalsIgnoreCase(src.productGroup));
		result &= (StringUtil.isEmpty(src.product)?true:ruleData.product.equalsIgnoreCase(src.product));
		result &= (StringUtil.isEmpty(src.salesChannel)?true:ruleData.salesChannel.equalsIgnoreCase(src.salesChannel));
		result &= (StringUtil.isEmpty(src.customerType)?true:ruleData.customerType.equalsIgnoreCase(src.customerType));
		
		//result &= (StringUtil.isEmpty(src.season)?true:ruleData.season.equalsIgnoreCase(src.season));
		
		result &= (StringUtil.isEmpty(src.startDate)?true:ruleData.startDate.equalsIgnoreCase(DateFunctions.formatDate(src.startDate, "MM-dd-yyyy")));
		result &= (StringUtil.isEmpty(src.endDate)?true:ruleData.endDate.equalsIgnoreCase(DateFunctions.formatDate(src.endDate, "MM-dd-yyyy")));
		result &= (StringUtil.isEmpty(src.effectiveDate)?true:ruleData.effectiveDate.equalsIgnoreCase(DateFunctions.formatDate(src.effectiveDate, "MM-dd-yyyy")));
		
		
		if(ruleName.equals(RulesCreation.ACCESS_TIME)){
			result &= (StringUtil.isEmpty(src.opentime)?true:ruleData.opentime.equalsIgnoreCase(src.opentime));
			result &= (StringUtil.isEmpty(src.dailyopentime)?true:this.compareTime(ruleData.dailyopentime,src.dailyopentime));
			result &= (StringUtil.isEmpty(src.monOpenTime)?true:this.compareTime(ruleData.monOpenTime,src.monOpenTime));
			result &= (StringUtil.isEmpty(src.tueOpenTime)?true:this.compareTime(ruleData.tueOpenTime,src.tueOpenTime));
			result &= (StringUtil.isEmpty(src.wedOpenTime)?true:this.compareTime(ruleData.wedOpenTime,src.wedOpenTime));
			result &= (StringUtil.isEmpty(src.thuOpenTime)?true:this.compareTime(ruleData.thuOpenTime,src.thuOpenTime));
			result &= (StringUtil.isEmpty(src.friOpenTime)?true:this.compareTime(ruleData.friOpenTime,src.friOpenTime));
			result &= (StringUtil.isEmpty(src.satOpenTime)?true:this.compareTime(ruleData.satOpenTime,src.satOpenTime));
			result &= (StringUtil.isEmpty(src.sunOpenTime)?true:this.compareTime(ruleData.sunOpenTime,src.sunOpenTime));
			result &= (StringUtil.isEmpty(src.closeTime)?true:ruleData.closeTime.equalsIgnoreCase(src.closeTime));
			result &= (StringUtil.isEmpty(src.dailyCloseTime)?true:this.compareTime(ruleData.dailyCloseTime,src.dailyCloseTime));
			result &= (StringUtil.isEmpty(src.monCloseTime)?true:this.compareTime(ruleData.monCloseTime,src.monCloseTime));
			result &= (StringUtil.isEmpty(src.tueCloseTime)?true:this.compareTime(ruleData.tueCloseTime,src.tueCloseTime));
			result &= (StringUtil.isEmpty(src.wedCloseTime)?true:this.compareTime(ruleData.wedCloseTime,src.wedCloseTime));
			result &= (StringUtil.isEmpty(src.thuCloseTime)?true:this.compareTime(ruleData.thuCloseTime,src.thuCloseTime));
			result &= (StringUtil.isEmpty(src.friCloseTime)?true:this.compareTime(ruleData.friCloseTime,src.friCloseTime));
			result &= (StringUtil.isEmpty(src.satCloseTime)?true:this.compareTime(ruleData.satCloseTime,src.satCloseTime));
			result &= (StringUtil.isEmpty(src.sunCloseTime)?true:this.compareTime(ruleData.sunCloseTime,src.sunCloseTime));
				
		}else if(ruleName.equals(RulesCreation.ACCESS_TYPE)){
			result &= (StringUtil.isEmpty(src.accessType)?true:ruleData.accessType.equalsIgnoreCase(src.accessType));
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));

			
		}else if(ruleName.equals(RulesCreation.ASSOCIATE_ENTRANCE)){
			
			result &= (StringUtil.isEmpty(src.associateEntrance)?true:ruleData.associateEntrance.equalsIgnoreCase(src.associateEntrance));


			
		}else if(ruleName.equals(RulesCreation.BLOCK_STAY)){
			result &= (StringUtil.isEmpty(src.transactionOccurrence)?true:ruleData.transactionOccurrence.equalsIgnoreCase(src.transactionOccurrence));
			
			//need change compare-->mon,tue
			//mon	tue wed	thu	fri	sat	sun
			result &= this.compareDaysOfWeek(src.blockStay, ruleData.blockStay);
			result &= this.compareDaysOfWeek(src.holidayblockstay, ruleData.holidayblockstay);
			
			result &= (StringUtil.isEmpty(src.allowAvailabilityException)?true:ruleData.allowAvailabilityException.equalsIgnoreCase(src.allowAvailabilityException));
			result &= (StringUtil.isEmpty(src.allowStayLengthException)?true:ruleData.allowStayLengthException.equalsIgnoreCase(src.allowStayLengthException));
			
		}else if(ruleName.equals(RulesCreation.INVENTORY_HOLD_TIMEOUT)){
			
			result &= (StringUtil.isEmpty(src.timeoutLen)?true:ruleData.timeoutLen.equalsIgnoreCase(src.timeoutLen));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_CONSECUTIVE_STAY)){
			
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			result &= (StringUtil.isEmpty(src.minimumTimeAwayLength)?true:ruleData.minimumTimeAwayLength.equalsIgnoreCase(src.minimumTimeAwayLength));
			result &= (StringUtil.isEmpty(src.minimumTimeAwayUnit)?true:ruleData.minimumTimeAwayUnit.equalsIgnoreCase(src.minimumTimeAwayUnit));
			

		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfConcurrentReservations)?true:ruleData.maximumNumberOfConcurrentReservations.equalsIgnoreCase(src.maximumNumberOfConcurrentReservations));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_FOR_SAME_CUSTOMER_PASS_NUMBER)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber)?true:ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber.equalsIgnoreCase(src.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber));
			
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_PER_CALL)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfOrdersPerCall)?true:ruleData.maximumNumberOfOrdersPerCall.equalsIgnoreCase(src.maximumNumberOfOrdersPerCall));
					
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_WITHIN_INVENTORY_PERIOD)){
			
			
			result &= (StringUtil.isEmpty(src.maxOrders)?true:ruleData.maxOrders.equalsIgnoreCase(src.maxOrders));
			result &= (StringUtil.isEmpty(src.maxTimes)?true:ruleData.maxTimes.equalsIgnoreCase(src.maxTimes));
			result &= (StringUtil.isEmpty(src.maxTickets)?true:ruleData.maxTickets.equalsIgnoreCase(src.maxTickets));
			
						
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_WITHIN_A_BOOKING_PERIOD)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfOrdersWithinABookingPeriod)?true:ruleData.maximumNumberOfOrdersWithinABookingPeriod.equalsIgnoreCase(src.maximumNumberOfOrdersWithinABookingPeriod));
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));

			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_WITHIN_STAY_PERIOD)){
			result &= (StringUtil.isEmpty(src.maximumNumberOfOrdersWithinStayPeriod)?true:ruleData.maximumNumberOfOrdersWithinStayPeriod.equalsIgnoreCase(src.maximumNumberOfOrdersWithinStayPeriod));
			
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_PERMITS_PER_PERIOD_NON_PROFIT_ORGANIZATION)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfOrdersPerPeriod)?true:ruleData.maximumNumberOfOrdersPerPeriod.equalsIgnoreCase(src.maximumNumberOfOrdersPerPeriod));
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
		
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_RESERVATIONS_WITH_THE_SAME_START_DATE)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfReservationsWithSameStartDate)?true:ruleData.maximumNumberOfReservationsWithSameStartDate.equalsIgnoreCase(src.maximumNumberOfReservationsWithSameStartDate));
			
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_STAYS_PER_PERIOD)){
			
			result &= (StringUtil.isEmpty(src.maximumNumberOfOrdersPerPeriod)?true:ruleData.maximumNumberOfOrdersPerPeriod.equalsIgnoreCase(src.maximumNumberOfOrdersPerPeriod));
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
	
		}else if(ruleName.equals(RulesCreation.MAXIMUM_STAY_PER_PERIOD)){
			
			result &= (StringUtil.isEmpty(src.maximumStayPerPeriod)?true:ruleData.maximumStayPerPeriod.equalsIgnoreCase(src.maximumStayPerPeriod));
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
			
	
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_TIME_TO_RECEIVE_PAYMENT)){
			
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_TOTAL_STAY)){
			
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_WINDOW)){
			
			result &= (StringUtil.isEmpty(src.maximumWindowType)?true:ruleData.maximumWindowType.equalsIgnoreCase(src.maximumWindowType));
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			result &= (StringUtil.isEmpty(src.rollupEndOfMonthExtraDays)?true:ruleData.rollupEndOfMonthExtraDays.equalsIgnoreCase(src.rollupEndOfMonthExtraDays));
			result &= (StringUtil.isEmpty(src.blockReleaseLength)?true:ruleData.blockReleaseLength.equalsIgnoreCase(src.blockReleaseLength));
			result &= (StringUtil.isEmpty(src.blockReleaseUnit)?true:ruleData.blockReleaseUnit.equalsIgnoreCase(src.blockReleaseUnit));
			result &= (StringUtil.isEmpty(src.blockReleaseDayOfMonth)?true:ruleData.blockReleaseDayOfMonth.equalsIgnoreCase(src.blockReleaseDayOfMonth));
			result &= (StringUtil.isEmpty(src.blockReleaseDayOfWeek)?true:ruleData.blockReleaseDayOfWeek.equalsIgnoreCase(src.blockReleaseDayOfWeek));
			result &= (StringUtil.isEmpty(src.blockReleaseDayOfWeekWithinMonth)?true:ruleData.blockReleaseDayOfWeekWithinMonth.equalsIgnoreCase(src.blockReleaseDayOfWeekWithinMonth));
			
			
		}else if(ruleName.equals(RulesCreation.MINIMUM_STAY)){
			result &= (StringUtil.isEmpty(src.transactionOccurrence)?true:ruleData.transactionOccurrence.equalsIgnoreCase(src.transactionOccurrence));
			result &= (StringUtil.isEmpty(src.minimumStay)?true:ruleData.minimumStay.equalsIgnoreCase(src.minimumStay));
			result &= (StringUtil.isEmpty(src.minimumStayMon)?true:ruleData.minimumStayMon.equalsIgnoreCase(src.minimumStayMon));
			result &= (StringUtil.isEmpty(src.minimumStayTue)?true:ruleData.minimumStayTue.equalsIgnoreCase(src.minimumStayTue));
			result &= (StringUtil.isEmpty(src.minimumStayWed)?true:ruleData.minimumStayWed.equalsIgnoreCase(src.minimumStayWed));
			result &= (StringUtil.isEmpty(src.minimumStayThu)?true:ruleData.minimumStayThu.equalsIgnoreCase(src.minimumStayThu));
			result &= (StringUtil.isEmpty(src.minimumStayFri)?true:ruleData.minimumStayFri.equalsIgnoreCase(src.minimumStayFri));
			result &= (StringUtil.isEmpty(src.minimumStaySat)?true:ruleData.minimumStaySat.equalsIgnoreCase(src.minimumStaySat));
			result &= (StringUtil.isEmpty(src.minimumStaySun)?true:ruleData.minimumStaySun.equalsIgnoreCase(src.minimumStaySun));
			result &= (StringUtil.isEmpty(src.minimumStayHoliday)?true:ruleData.minimumStayHoliday.equalsIgnoreCase(src.minimumStayHoliday));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			result &= (StringUtil.isEmpty(src.multiplesOnly)?true:ruleData.multiplesOnly.equalsIgnoreCase(src.multiplesOnly));
			result &= (StringUtil.isEmpty(src.minimumStayRequiredWhenStayIncludesStartDay)?true:ruleData.minimumStayRequiredWhenStayIncludesStartDay.equalsIgnoreCase(src.minimumStayRequiredWhenStayIncludesStartDay));
			result &= (StringUtil.isEmpty(src.combineOverlappingSeasons)?true:ruleData.combineOverlappingSeasons.equalsIgnoreCase(src.combineOverlappingSeasons));
			
	
		}else if(ruleName.equals(RulesCreation.MINIMUM_WINDOW)){
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
		}else if(ruleName.equals(RulesCreation.PRODUCT_RESTRICTED_IN_USE)){
			
		}else if(ruleName.equals(RulesCreation.SPECIFIED_STAY_START)){
			//need change compare: -->none Sunday Monday Tuesday Wednesday Thursday Friday Saturday
			result &= this.compareDaysOfWeek(src.startStayDayOfWeek, ruleData.startStayDayOfWeek);

			
		}else if(ruleName.equals(RulesCreation.STAY_BEYOND_MAXIMUM_WINDOW)){
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
		}else if(ruleName.equals(RulesCreation.TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED)){
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
		}else if(ruleName.equals(RulesCreation.TIME_TO_CLEAR)){
			result &= (StringUtil.isEmpty(src.length)?true:ruleData.length.equalsIgnoreCase(src.length));
			result &= (StringUtil.isEmpty(src.unit)?true:ruleData.unit.equalsIgnoreCase(src.unit));
			
		}else if(ruleName.equals(RulesCreation.TRANSACTION_RESTRICTION)){
			
			result &= (StringUtil.isEmpty(src.transaction)?true:ruleData.transaction.equalsIgnoreCase(src.transaction));
			result &= (StringUtil.isEmpty(src.transactionOccurrence)?true:ruleData.transactionOccurrence.equalsIgnoreCase(src.transactionOccurrence));
			result &= (StringUtil.isEmpty(src.transactionOccurrenceDays)?true:ruleData.transactionOccurrenceDays.equalsIgnoreCase(src.transactionOccurrenceDays));
			result &= (StringUtil.isEmpty(src.transactionOccurrenceTime)?true:ruleData.transactionOccurrenceTime.equalsIgnoreCase(src.transactionOccurrenceTime));
			

			
		}else if(ruleName.equals(RulesCreation.ISSUE_PERMIT_RESTRICTION)){
			
			result &= (StringUtil.isEmpty(src.permitType)?true:ruleData.permitType.equalsIgnoreCase(src.permitType));
			result &= (StringUtil.isEmpty(src.issueStation)?true:ruleData.issueStation.equalsIgnoreCase(src.issueStation));
			
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_PRE_CALL_CART)){
			result &= (StringUtil.isEmpty(src.maxOrders)?true:ruleData.maxOrders.equalsIgnoreCase(src.maxOrders));
			result &= (StringUtil.isEmpty(src.maxTimes)?true:ruleData.maxTimes.equalsIgnoreCase(src.maxTimes));
			result &= (StringUtil.isEmpty(src.maxTickets)?true:ruleData.maxTickets.equalsIgnoreCase(src.maxTickets));
			
					
		}

				
		return result;

		
	}
	
	private boolean compareTime(String src, String des)
	{
		src = src.replace(":", ".");
		des = des.replace(":", ".");
		
		return (StringUtil.compareNumStrings(src, des)==0);
	}

	private boolean compareDaysOfWeek(String src,String des) {
		//src: mon,tue,wed,thu,fri,sat
		//des: none Sunday Monday Tuesday Wednesday Thursday Friday Saturday
		String[] valueSrc = src.split("[ .,]");
		String[] newSrc = this.formatWeekText(valueSrc);
		
		String[] valueDes = src.split("[ .,]");
		String[] newDes = this.formatWeekText(valueDes);
		
		Arrays.sort(newSrc);
		Arrays.sort(newDes);
		
		return Arrays.toString(newSrc).equalsIgnoreCase(Arrays.toString(newDes));
	}

	/**
	 * 
	 */
	private String[] formatWeekText(String[] date) {
		
		String[] result = new String[date.length];
		for(int i=0; i<date.length; i++)
		{
			if (date[i].trim().equalsIgnoreCase("none")) {
				result[i] = "none";
			}
			if (date[i].trim().equalsIgnoreCase("Sun")
					|| date[i].equalsIgnoreCase("Sunday")
					|| date[i].equalsIgnoreCase("Sun.")) {
				result[i] = "Sunday";
			}
			if (date[i].trim().equalsIgnoreCase("Mon")
					|| date[i].equalsIgnoreCase("Monday")
					|| date[i].equalsIgnoreCase("Mon.")) {
				result[i] = "Monday";
			}
			if (date[i].trim().equalsIgnoreCase("Tue")
					|| date[i].equalsIgnoreCase("Tuesday")
					|| date[i].equalsIgnoreCase("Tue.")) {
				result[i] = "Tuesday";
			}
			if (date[i].trim().equalsIgnoreCase("Wed")
					|| date[i].equalsIgnoreCase("Wednesday")
					|| date[i].equalsIgnoreCase("Wed.")) {
				result[i] = "Wednesday";
			}
			if (date[i].trim().equalsIgnoreCase("Thu")
					|| date[i].equalsIgnoreCase("Thuresday")
					|| date[i].equalsIgnoreCase("Thu.")) {
				result[i] = "Thuresday";
			}
			if (date[i].trim().equalsIgnoreCase("Fri")
					|| date[i].equalsIgnoreCase("Friday")
					|| date[i].equalsIgnoreCase("Fri.")) {
				result[i] = "Friday";
			}
			if (date[i].trim().equalsIgnoreCase("Sat")
					|| date[i].equalsIgnoreCase("Saturday")
					|| date[i].equalsIgnoreCase("Sat.")) {
				result[i] = "Saturday";
			}
		}
		
		return result;
		
	}

	/**
	 * @return
	 */
	private RuleDataInfo fetchRecord(int recordNum, IHtmlTable table, String ruleName) {
		RuleDataInfo ruleData = new RuleDataInfo();

		//Command columns
		ruleData.condID = table.getCellValue(recordNum, table.findColumn(0, "Rule Cond. ID"));
		ruleData.location = table.getCellValue(recordNum, table.findColumn(0, "Location"));
		ruleData.status = (table.getCellValue(recordNum, table.findColumn(0, "Active")).equalsIgnoreCase("Yes")?"Active":"Inactive");
		
		if (!ruleName.equalsIgnoreCase(RulesCreation.TIME_TO_CLEAR)) {
			ruleData.productCategory = table.getCellValue(recordNum, table.findColumn(0, "Product Category"));
		}
				
		//ruleData.ticketCategory = table.getCellValue(recordNum, table.findColumn(0, "Ticket Category"));
		ruleData.productGroup = table.getCellValue(recordNum, table.findColumn(0, "Product Group"));
		ruleData.loop = table.getCellValue(recordNum, table.findColumn(0, "Loop/Dock"));
		ruleData.product = table.getCellValue(recordNum, table.findColumn(0, "Product"));
		
		int col = table.findColumn(0, "Sales Channel");
		if(col>0){
			ruleData.salesChannel = table.getCellValue(recordNum, col);
		}
		ruleData.customerType = table.getCellValue(recordNum, table.findColumn(0, "Customer Type"));
		col = table.findColumn(0, "Season Type");
		if(col>0){
			ruleData.season = table.getCellValue(recordNum, col);
		}
		col = table.findColumn(0, "Customer Pass Type");
		if(col>0){
			ruleData.customerPassType = table.getCellValue(recordNum, table.findColumn(0, "Customer Pass Type"));
		}
		col = table.findColumn(0, "Out Of State");
		if(col>0){
			ruleData.outOfState = table.getCellValue(recordNum, col);
		}
		//ruleData.paymentType = table.getCellValue(recordNum, table.findColumn(0, "Payment Type"));\

		col = table.findColumn(0, "Customer Member");
		if(col>0){
			ruleData.customerMember = table.getCellValue(recordNum, col);
		}

		col = table.findColumn(0, "Associated Party");
		if(col>0){
			ruleData.associatedParty = table.getCellValue(recordNum, col);
		}
		ruleData.comments = table.getCellValue(recordNum, table.findColumn(0, "Comments"));
		ruleData.startDate = table.getCellValue(recordNum, table.findColumn(0, "Start Date"));
		ruleData.endDate = table.getCellValue(recordNum, table.findColumn(0, "End Date"));
		ruleData.effectiveDate = table.getCellValue(recordNum, table.findColumn(0, "Effective Date"));
		
		if(ruleName.equals(RulesCreation.ACCESS_TIME)){
			ruleData.opentime = table.getCellValue(recordNum, table.findColumn(0, "Open Time"));
			ruleData.dailyopentime = table.getCellValue(recordNum, table.findColumn(0, "Daily Open Time"));
			ruleData.monOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Monday Open Time"));
			ruleData.tueOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Tuesday Open Time"));
			ruleData.wedOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Wednesday Open Time"));
			ruleData.thuOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Thursday Open Time"));
			ruleData.friOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Friday Open Time"));
			ruleData.satOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Saturday Open Time"));
			ruleData.sunOpenTime = table.getCellValue(recordNum, table.findColumn(0, "Sunday Open Time"));
			ruleData.closeTime = table.getCellValue(recordNum, table.findColumn(0, "Close Time"));
			ruleData.dailyCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Daily Close Time"));
			ruleData.monCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Monday Close Time"));
			ruleData.tueCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Tuesday Close Time"));
			ruleData.wedCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Wednesday Close Time"));
			ruleData.thuCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Thursday Close Time"));
			ruleData.friCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Friday Close Time"));
			ruleData.satCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Saturday Close Time"));
			ruleData.sunCloseTime = table.getCellValue(recordNum, table.findColumn(0, "Sunday Close Time"));
		}else if(ruleName.equals(RulesCreation.ACCESS_TYPE)){
			ruleData.accessType = table.getCellValue(recordNum, table.findColumn(0, "Access Type"));
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.ASSOCIATE_ENTRANCE)){
			ruleData.associateEntrance = table.getCellValue(recordNum, table.findColumn(0, "Associated Entrance"));
			
		}else if(ruleName.equals(RulesCreation.BLOCK_STAY)){
			ruleData.transactionOccurrence = table.getCellValue(recordNum, table.findColumn(0, "Transaction Occurrence"));
			ruleData.blockStay = table.getCellValue(recordNum, table.findColumn(0, "Block Stay"));
			ruleData.holidayblockstay = table.getCellValue(recordNum, table.findColumn(0, "Holiday Block Stay"));
			ruleData.allowAvailabilityException = (table.getCellValue(recordNum, table.findColumn(0, "Allow Availability Exception")).equalsIgnoreCase("true")?"yes":"no");
			ruleData.allowStayLengthException = (table.getCellValue(recordNum, table.findColumn(0, "Allow Stay Length Exception")).equalsIgnoreCase("true")?"yes":"no");
			
		}else if(ruleName.equals(RulesCreation.INVENTORY_HOLD_TIMEOUT)){
			
			ruleData.timeoutLen = table.getCellValue(recordNum, table.findColumn(0, "Timeout Length"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_CONSECUTIVE_STAY)){
			
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			ruleData.minimumTimeAwayLength = table.getCellValue(recordNum, table.findColumn(0, "Minimum Time Away Length"));
			ruleData.minimumTimeAwayUnit = table.getCellValue(recordNum, table.findColumn(0, "Minimum Time Away Unit"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS)){
			
			ruleData.maximumNumberOfConcurrentReservations = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Concurrent Reservations"));

		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_FOR_SAME_CUSTOMER_PASS_NUMBER)){
			
			ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Concurrent Reservations for Same Customer Pass Number"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_PER_CALL)){
			
			ruleData.maximumNumberOfOrdersPerCall = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Orders Per Call"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_WITHIN_INVENTORY_PERIOD)){
			
			ruleData.maxOrders = table.getCellValue(recordNum, table.findColumn(0, "Max Orders"));
			ruleData.maxTimes = table.getCellValue(recordNum, table.findColumn(0, "Max Times"));
			ruleData.maxTickets = table.getCellValue(recordNum, table.findColumn(0, "Max Tickets"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_WITHIN_A_BOOKING_PERIOD)){
			

			ruleData.maximumNumberOfOrdersWithinABookingPeriod = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Orders Within A Booking Period"));
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
		
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_WITHIN_STAY_PERIOD)){
			ruleData.maximumNumberOfOrdersWithinStayPeriod = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Orders Within Stay Period"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_PERMITS_PER_PERIOD_NON_PROFIT_ORGANIZATION)){
			

			ruleData.maximumNumberOfOrdersPerPeriod = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Orders Per Period"));
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
		
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_RESERVATIONS_WITH_THE_SAME_START_DATE)){
			
			ruleData.maximumNumberOfReservationsWithSameStartDate = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Reservations With Same Start Date"));
			
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_STAYS_PER_PERIOD)){
			
			ruleData.maximumNumberOfOrdersPerPeriod = table.getCellValue(recordNum, table.findColumn(0, "Maximum Number Of Orders Per Period"));
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
	
		}else if(ruleName.equals(RulesCreation.MAXIMUM_STAY_PER_PERIOD)){
			ruleData.maximumStayPerPeriod = table.getCellValue(recordNum, table.findColumn(0, "Maximum Stay Per Period"));
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_TIME_TO_RECEIVE_PAYMENT)){
			
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_TOTAL_STAY)){
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_WINDOW)){
			
			ruleData.maximumWindowType = table.getCellValue(recordNum, table.findColumn(0, "Maximum Window Type"));
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			ruleData.rollupEndOfMonthExtraDays = (table.getCellValue(recordNum, table.findColumn(0, "Rollup End Of Month Extra Days")).equalsIgnoreCase("true")?"yes":"no");
			ruleData.blockReleaseLength =table.getCellValue(recordNum, table.findColumn(0, "Block Release Length"));
			ruleData.blockReleaseUnit = table.getCellValue(recordNum, table.findColumn(0, "Block Release Unit"));
			ruleData.blockReleaseDayOfMonth = table.getCellValue(recordNum, table.findColumn(0, "Block Release Day Of Month"));
			ruleData.blockReleaseDayOfWeek = table.getCellValue(recordNum, table.findColumn(0, "Block Release Day Of Week"));
			ruleData.blockReleaseDayOfWeekWithinMonth = table.getCellValue(recordNum, table.findColumn(0, "Block Release Day Of Week Within Month"));
			
			
		}else if(ruleName.equals(RulesCreation.MINIMUM_STAY)){
			ruleData.transactionOccurrence = table.getCellValue(recordNum, table.findColumn(0, "Transaction Occurrence"));
			ruleData.minimumStay = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay"));
			ruleData.minimumStayMon =table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Monday"));
			ruleData.minimumStayTue = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Tuesday"));
			ruleData.minimumStayWed = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Wednesday"));
			ruleData.minimumStayThu =table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Thursday"));
			ruleData.minimumStayFri = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Friday"));
			ruleData.minimumStaySat = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Saturday"));
			ruleData.minimumStaySun = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Sunday"));
			ruleData.minimumStayHoliday = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Holiday"));
			ruleData.unit = table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			ruleData.multiplesOnly = table.getCellValue(recordNum, table.findColumn(0, "Multiples Only")).equalsIgnoreCase("true")?"yes":"no";
			ruleData.minimumStayRequiredWhenStayIncludesStartDay = table.getCellValue(recordNum, table.findColumn(0, "Minimum Stay Required When Stay Includes Start Day")).equalsIgnoreCase("true")?"yes":"no";
			ruleData.combineOverlappingSeasons = table.getCellValue(recordNum, table.findColumn(0, "Combine Overlapping Seasons")).equalsIgnoreCase("true")?"yes":"no";
	
		}else if(ruleName.equals(RulesCreation.MINIMUM_WINDOW)){
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit =table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.PRODUCT_RESTRICTED_IN_USE)){
			
		}else if(ruleName.equals(RulesCreation.SPECIFIED_STAY_START)){
			ruleData.startStayDayOfWeek = table.getCellValue(recordNum, table.findColumn(0, "Start Stay Day Of Week"));
			
		}else if(ruleName.equals(RulesCreation.STAY_BEYOND_MAXIMUM_WINDOW)){
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit =table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED)){
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit =table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.TIME_TO_CLEAR)){
			ruleData.length = table.getCellValue(recordNum, table.findColumn(0, "Length"));
			ruleData.unit =table.getCellValue(recordNum, table.findColumn(0, "Unit"));
			
		}else if(ruleName.equals(RulesCreation.TRANSACTION_RESTRICTION)){
			ruleData.transaction = table.getCellValue(recordNum, table.findColumn(0, "Transaction"));
			ruleData.transactionOccurrence = table.getCellValue(recordNum, table.findColumn(0, "Transaction Occurrence"));
			ruleData.transactionOccurrenceDays = table.getCellValue(recordNum, table.findColumn(0, "Transaction Occurrence Days"));
			ruleData.transactionOccurrenceTime = table.getCellValue(recordNum, table.findColumn(0, "Transaction Occurrence Time"));
			
		}else if(ruleName.equals(RulesCreation.ISSUE_PERMIT_RESTRICTION)){
			
			ruleData.permitType = table.getCellValue(recordNum, table.findColumn(0, "Permit Type"));
			ruleData.issueStation = table.getCellValue(recordNum, table.findColumn(0, "Issue Station"));
			
		}else if(ruleName.equals(RulesCreation.MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_PRE_CALL_CART)){
			ruleData.maxOrders = table.getCellValue(recordNum, table.findColumn(0, "Max Orders"));
			ruleData.maxTimes = table.getCellValue(recordNum, table.findColumn(0, "Max Times"));
			ruleData.maxTickets = table.getCellValue(recordNum, table.findColumn(0, "Max Tickets"));
			
		}else if(ruleName.equalsIgnoreCase("Maximum Number of Entries Per List")){
			ruleData.maximumNumberOfEntries = Integer.valueOf(table.getCellValue(recordNum, table.findColumn(0, "Maximum Number of Entries")).trim());
		}
		
		return ruleData;
	}

	
	public List<RuleDataInfo> getAllRecordsOnPage(String ruleName) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<RuleDataInfo> records = new ArrayList<RuleDataInfo>();
		int rows;
		int columns;
		RuleDataInfo rule;
		
		
		do{
			objs = this.getRuleTableObject();
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't rule list table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page AdmMgrRuleDetailPage, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows-1; i ++) {
				rule = this.fetchRecord(i, table, ruleName);
				records.add(rule);			
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
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);
		this.waitLoading();
		return toReturn;
	}
	
	private String getRuleName(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Rule Name.*",false));
		String ruleName = objs[0].text().replace("Rule Name", "").trim();
		return ruleName;
	}
}
