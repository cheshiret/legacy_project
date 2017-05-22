/*
 * $Id: AdmMgrMinStayRuleDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.awo.datacollection.legacy.MinimumStayRuleData;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author CGuo
 */
public class AdmMgrMinStayRuleDetailPage extends AdminManagerPage {

	/**
	 * Script Name   : AdmRuleDetailPage
	 * Generated     : Jul 11, 2007 11:34:18 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/07/11
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrMinStayRuleDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrMinStayRuleDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrMinStayRuleDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrMinStayRuleDetailPage();
		}

		return _instance;
	}
	
	/**Determain whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New Definition");
	}

	/**Check the location wether is corrct
	 * @param location -- the expected location*/
	public boolean checkLocation(String locationName) {
		IHtmlObject[] objs = browser.getTableTestObject(".class","Html.TABLE",".text", new RegularExpression("Configure Rule", false));
		String loc=((IHtmlTable)objs[0]).getCellValue(0, 0).split("Location Name ")[1];

		Browser.unregister(objs);
		return loc.equalsIgnoreCase(locationName);
	}

	/**
	 * Verify the rule name is correct
	 * @param rule -- the expected rule name
	 * @return
	 */
	public boolean checkRule(String rule) {
		IHtmlObject[] objs = browser.getTableTestObject(".classIndex", "0",
				".text", new RegularExpression("Configure Rule", false));
		String ruleName= ((IHtmlTable)objs[0]).getCellValue(0, 5).toString()
			.substring("Rule Name".length()).trim();
		Browser.unregister(objs);
		return ruleName.equalsIgnoreCase(rule);
	}

	/** Set the rule cond ID*/
	public void setRuleCondID(String item) {
		browser.setTextField(".id", "search_rulecond_id", item);
	}

	/**Select the status of the rule for search
	 *@param item: active\inactive*/
	public void selectSearchShow(String item) {
		browser.selectDropdownList(".id", "search_active", item);
	}

	/**Select the product Category for search*/
	public void selectSearchProductCategory(String item) {
		browser.selectDropdownList(".id", "search_product_category", item);
	}

	/**Select the product group for search*/
	public void selectSearchProductGroup(String item) {
		browser.selectDropdownList(".id", "search_product_group", item);
	}

	/**Select the sale channel for search*/
	public void selectSearchSaleChannel(String item) {
		browser.selectDropdownList(".id", "search_sales_channel", item);
	}

	/**Select the customer type for search*/
	public void selectSearchCustomerType(String item) {
		browser.selectDropdownList(".id", "search_customer_type", item);
	}

	/**Select the season type for search*/
	public void selectSearchSeasonType(String item) {
		browser.selectDropdownList(".id", "search_season_type", item);
	}

	/**Select the customer pass type for search*/
	public void selectSearchCustomerPassType(String item) {
		browser.selectDropdownList(".id", "search_pass_type", item);
	}

	/**Select the out of State for search*/
	public void selectSearchOutOfState(String item) {
		browser.selectDropdownList(".id", "search_out_of_state_type", item);
	}

	/**Select the payment type for search*/
	public void selectSearchPaymentType(String item) {
		browser.selectDropdownList(".id", "search_payment_type", item);
	}

	/**Select the customer member for search*/
	public void selectSearchCustomerMemeber(String item) {
		browser.selectDropdownList(".id", "search_customer_member_type", item);
	}

	/**Select the transaction occurrence for search*/
	public void selectSearchTransactionOccurrence(String item) {
		browser.selectDropdownList(".id", "search_transaction_occurrence",item);
	}

	/**Select the status*/
	public void selectStatus(String item) {
		browser.selectDropdownList(".id", "active", item);
	}

	/**Select the product category*/
	public void selectProductCategory(String item) {
		browser.selectDropdownList(".id", "product_category", item);
	}

	/**Select the ticket category*/
	public void selectTicketCategory(String item) {
		browser.selectDropdownList(".id", "ticket_category", item);
	}

	/**Select the product group */
	public void selectProductGroup(String item) {
		browser.selectDropdownList(".id", "product_group", item);
	}

	/**Select the sale channel*/
	public void selectSaleChannel(String item) {
		browser.selectDropdownList(".id", "sales_channel", item);
	}

	/**Select the customer type*/
	public void selectCustomerType(String item) {
		browser.selectDropdownList(".id", "customer_type", item);
	}

	/**Select the season type*/
	public void selectSeasonType(String item) {
		browser.selectDropdownList(".id", "season_type", item);
	}

	/**Select the customer pass type*/
	public void selectCustomerPassType(String item) {
		browser.selectDropdownList(".id", "pass_type", item);
	}

	/**Select the out of state*/
	public void selectOutOfState(String item) {
		browser.selectDropdownList(".id", "out_of_state_type", item);
	}

	/**Select the payment type*/
	public void selectPaymentType(String item) {
		browser.selectDropdownList(".id", "payment_type", item);
	}

	/**Select the customer member*/
	public void selectCustomerMemeber(String item) {
		browser.selectDropdownList(".id", "customer_member_type", item);
	}

	/**Select the transaction occurrence*/
	public void selectTransactionOccurrence(String item) {
		browser.selectDropdownList(".id", "transaction_occurrence", item);
	}

	/**Select the loop*/
	public void selectLoop(String item) {
		browser.selectDropdownList(".id", "loop", item);
	}

	/**select the product*/
	public void selectProduct(String item) {
		browser.selectDropdownList(".id", "product", item);
	}

	/**Select the associated party*/
	public void selectAssociatedParty(String item) {
		browser.selectDropdownList(".id", "associated_party", item);
	}
	
	/**Select the associated transactionOccurrence*/
	public void setTransactionOccurrence(String item){
		browser.selectDropdownList(".id", "transaction_occurrence", item);
	}

	/**Select the unit*/
	public void selectUnit(String item) {
		browser.selectDropdownList(".id", "unit", item);
	}

	/**Set the description*/
	public void setDescription(String text) {
		browser.setTextField(".id", "description", text);
	}
	
	/**Set the start date*/
	public void setStartDate(String date) {
		browser.setTextField(".id", "start_date", date);
	}

	/**Set the end date*/
	public void setEndDate(String date) {
		browser.setTextField(".id", "end_date", date);
	}

	/**Set the effective date*/
	public void setEffectiveDate(String date) {
		browser.setTextField(".id", "effective_date", date);
	}

	/**Set the minimum stay*/
	public void setMinimumStay(String text) {
		browser.setTextField(".id", "MinimumStay", text);
	}

	/**Set the Minimum Stay Monday*/
	public void setMinimumStayMon(String text) {
		browser.setTextField(".id", "MinimumStayMon", text);
	}

	/**Set the Minimum Stay Tuesday*/
	public void setMinimumStayTue(String text) {
		browser.setTextField(".id", "MinimumStayTue", text);
	}

	/**Set the Minimum Stay Wednesday*/
	public void setMinimumStayWed(String text) {
		browser.setTextField(".id", "MinimumStayWed", text);
	}

	/**Set the Minimum Stay Thursday*/
	public void setMinimumStayThu(String text) {
		browser.setTextField(".id", "MinimumStaythu", text);
	}

	/**Set the Minimum Stay Friday*/
	public void setMinimumStayFri(String text) {
		browser.setTextField(".id", "MinimumStayFri", text);
	}

	/**Set the Minimum Stay Saturday*/
	public void setMinimumStaySat(String text) {
		browser.setTextField(".id", "MinimumStaySat", text);
	}

	/**Set the Minimum Stay Sunday*/
	public void setMinimumStaySun(String text) {
		browser.setTextField(".id", "MinimumStaySun", text);
	}

	/**Set the Minimum Stay Holiday*/
	public void setMinimumStayHoliday(String text){
		browser.setTextField(".id", "MinimumStayHoliday", text);
	}
	
	/**Select the check box Multiples Only*/
	public void selectCheckBoxMultiplesOnly() {
		browser.selectCheckBox(".id", "MultiplesOnly");
	}

	/**Select the check box Combine Overlapping Seasons*/
	public void selectCheckBoxMinimumStayCombineOverlappingSeasons() {
		browser.selectCheckBox(".id", "MinimumStayCombineOverlappingSeasons");
	}

	/**Select the check box Minimum Stay Required When Stay Includes Start Day*/
	public void selectCheckBoxMinimumStayRequiredWhenStayIncludesStartDay() {
		browser.selectCheckBox(".id",
				"MinimumStayRequiredWhenStayIncludesStartDay");
	}

	/**
	 * The method excute the process that add new Rule
	 * @param ruleDate -- rule information
	 * @return
	 */
	public String addNewRule(MinimumStayRuleData ruleDate) {
		this.selectStatus(ruleDate.status);
		this.selectProductCategory(ruleDate.productCategory);
		this.selectTicketCategory(ruleDate.ticketCategory);
		this.selectProductGroup(ruleDate.productGroup);
		this.selectLoop(ruleDate.loop);
		this.selectProduct(ruleDate.product);
		this.selectSaleChannel(ruleDate.salesChannel);
		this.selectCustomerType(ruleDate.customerType);
		this.selectSeasonType(ruleDate.season);
		this.selectCustomerPassType(ruleDate.customerPassType);

		this.selectOutOfState(ruleDate.outOfState);
		this.selectPaymentType(ruleDate.paymentType);
		this.selectCustomerMemeber(ruleDate.customerMember);
		this.selectAssociatedParty(ruleDate.associatedParty);
		
		this.setTransactionOccurrence(ruleDate.transactionOccurrence);
		this.setDescription(ruleDate.comments);
		this.setStartDate(ruleDate.startDate);
		this.setEndDate(ruleDate.endDate);
		this.setEffectiveDate(ruleDate.effectiveDate);
		this.setMinimumStay(ruleDate.numberOfMinimumStay);

		this.setMinimumStayMon(ruleDate.msMonday);
		this.setMinimumStayTue(ruleDate.msTuesday);
		this.setMinimumStayWed(ruleDate.msWednesday);
		this.setMinimumStayThu(ruleDate.msThursday);
		this.setMinimumStayFri(ruleDate.msFriday);
		this.setMinimumStaySat(ruleDate.msSaturday);
		this.setMinimumStaySun(ruleDate.msSunday);
		
		this.setMinimumStayHoliday(ruleDate.minimumStayHoliday);
		this.selectUnit(ruleDate.unit);

		if (ruleDate.mutiplesOnly.equals("Yes")) {
			this.selectCheckBoxMultiplesOnly();
		}
		if (ruleDate.includeStartDay.equals("Yes")) {
			this.selectCheckBoxMinimumStayRequiredWhenStayIncludesStartDay();
		}
		if (ruleDate.combineOverlappingSeason.equals("Yes")) {
			this.selectCheckBoxMinimumStayCombineOverlappingSeasons();
		}

		this.clickAddNewDefination();

		return "";
	}

	/**Click Find Rules link*/
	public void clickFindRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Find Rules");
	}

	/**Click the Delete link*/
	public void clickDelect() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/**Click the Deactivate link*/
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/**Click the Add New Defination link*/
	public void clickAddNewDefination() {
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Add New Definition",false));
	}

	/**Click the Activate link*/
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/**Click Go link*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	/**Check whether the error msg exist*/
	public boolean isErrorMsgExist(){
	  return (browser.checkHtmlObjectExists(".class","Html.DIV",".className","message msgerror")||browser.checkHtmlObjectExists(".class","Html.DIV",".className","message msgsuccess"));
	}

}
