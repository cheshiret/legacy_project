package com.activenetwork.qa.awo.pages.orms.common;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RuleValidationInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 28, 2013
 */
public class OrmsRuleValidationPage extends OrmsPage {
	
	private static OrmsRuleValidationPage _instance = null;
	
	private OrmsRuleValidationPage() {}
	
	public static OrmsRuleValidationPage getInstance() {
		if(_instance == null) _instance = new OrmsRuleValidationPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(lookupRules());
	}
	
	private Property[] transactionType() {
		return Property.toPropertyArray(".id", "TransactionType");
	}
	
	private Property[] bookingDate() {
		return Property.toPropertyArray(".id", "bookingdate_ForDisplay");
	}
	
	private Property[] arrivalDate() {
		return Property.toPropertyArray(".id", "date_specific_date_start_ForDisplay");
	}
	
	private Property[] departureDate() {
		return Property.toPropertyArray(".id", "date_specific_date_end_ForDisplay");
	}
	
	private Property[] numOfNights() {
		return Property.toPropertyArray(".id", "date_specific_nights");
	}
	
	private Property[] salesChannel() {
		return Property.toPropertyArray(".id", "saleschannel");
	}
	
	private Property[] inOutState() {
		return Property.toPropertyArray(".id", "inout");
	}
	
	private Property[] customerType() {
		return Property.toPropertyArray(".id", "customertype");
	}
	
	private Property[] customerPass() {
		return Property.toPropertyArray(".id", "customerpass");
	}
	
	private Property[] member() {
		return Property.toPropertyArray(".id", "member");
	}
	
	private Property[] paymentType() {
		return Property.toPropertyArray(".id", "paymenttype");
	}
	
	private Property[] lookupRules() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Lookup Rules");
	}
	
	private Property[] first() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "First");
	}
	
	private Property[] previous() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Previous");
	}
	
	private Property[] next() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Next");
	}
	
	private Property[] last() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Last");
	}
	
	private Property[] ruleTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Name", false));
//		return Property.toPropertyArray(".class", "Html.TABLE", ".className", "searchResult");
	}
	
	private Property[] ruleID(String id) {
		return Property.toPropertyArray(".class", "Html.A", ".text", id);
	}
	
	public void selectReservation() {
		browser.selectCheckBox(transactionType(), 0);
	}
	
	public void selectWalkIn() {
		browser.selectCheckBox(transactionType(), 1);
	}
	
	public void selectTransactionType(String type) {
		if(type.equalsIgnoreCase("Reservation")) {
			this.selectReservation();
		} else if(type.equalsIgnoreCase("Walk-In")) {
			this.selectWalkIn();
		}
	}
	
	public void setBookingDate(String booking) {
		browser.setCalendarField(bookingDate(), booking);
	}
	
	public void setArrivalDate(String arrival) {
		browser.setCalendarField(arrivalDate(), arrival);
	}
	
	public void setDepartureDate(String departure) {
		browser.setCalendarField(departureDate(), departure);
	}
	
	public void setNights(String nights) {
		browser.setTextField(numOfNights(), nights);
	}
	
	public void selectSalesChannel(String salesChannel) {
		browser.selectDropdownList(salesChannel(), salesChannel);
	}
	
	public void selectInOutState(String inOutState) {
		browser.selectDropdownList(inOutState(), inOutState);
	}
	
	public void selectCustomerType(String type) {
		browser.selectDropdownList(customerType(), type);
	}
	
	public void selectCustomerType(int index) {
		browser.selectDropdownList(customerType(), index);
	}
	
	public void selectCustomerPass(String pass) {
		browser.selectDropdownList(customerPass(), pass);
	}
	
	public void selectCustomerPass(int index) {
		browser.selectDropdownList(customerPass(), index);
	}
	
	public void selectMember(String member) {
		browser.selectDropdownList(member(), member);
	}
	
	public void selectPaymentType(String paymentType) {
		browser.selectDropdownList(paymentType(), paymentType);
	}
	
	public void clickLookupRules() {
		browser.clickGuiObject(lookupRules());
	}
	
	public void clickFirst() {
		browser.clickGuiObject(first());
	}
	
	public void clickPrevious() {
		browser.clickGuiObject(previous());
	}
	
	public void clickNext() {
		browser.clickGuiObject(next());
	}
	
	public void clickLast() {
		browser.clickGuiObject(last());
	}
	
	
	private static final String RULE_NAME_COL = "Rule Name";
	private static final String RULE_ID_COL = "Rule ID";
	private static final String PASS_FIAL_COL = "Pass/Fail";
	private static final String LOCATION_SITE_COL = "Location/Site";
	private static final String RULE_DATES_COL = "Rule Dates";
	private static final String ATTRIBUTE_DETAILS_COL = "Attribute Details";
	
	private IHtmlObject[] getRuleTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(ruleTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Rule table object.");
		
		return objs;
	}
	
	public List<List<String>> getAllRuleInfo() {
		IHtmlObject objs[] = this.getRuleTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<List<String>> allInfo = new ArrayList<List<String>>();
		for(int i = 2; i < table.rowCount(); i ++) {
			allInfo.add(table.getRowValues(i));
		}
		
		Browser.unregister(objs);
		return allInfo;
	}
	
	public List<String> getRuleInfo(String id) {
		IHtmlObject objs[] = this.getRuleTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int idColIndex = table.findColumn(0, RULE_ID_COL);
		int rowIndex = table.findRow(idColIndex, id);
		List<String> ruleInfo = table.getRowValues(rowIndex);
		
		Browser.unregister(objs);
		return ruleInfo;
	}
	
	public String getRuleStatus(String id) {
		IHtmlObject objs[] = this.getRuleTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length - 1];
		
		int idColIndex = table.findColumn(0, RULE_ID_COL);
		int statusColIndex = table.findColumn(0, PASS_FIAL_COL);
		int rowIndex = table.findRow(idColIndex, id);
		
		String status = table.getCellValue(rowIndex, statusColIndex);
		
		Browser.unregister(objs);
		return status;
	}
	
	public void clickRuleID(String id) {
		browser.clickGuiObject(ruleID(id));
	}
	
	public String validateRule(RuleValidationInfo validation, String ruleId) {
		if(!StringUtil.isEmpty(validation.getTransactionType())) selectTransactionType(validation.getTransactionType());
		if(!StringUtil.isEmpty(validation.getBookingDtae())) setBookingDate(validation.getBookingDtae());
		if(!StringUtil.isEmpty(validation.getArrivalDate())) setArrivalDate(validation.getArrivalDate());
		if(!StringUtil.isEmpty(validation.getDepartureDate())) setDepartureDate(validation.getDepartureDate());
		if(!StringUtil.isEmpty(validation.getNightsNum())) setNights(validation.getNightsNum());
		if(!StringUtil.isEmpty(validation.getSalesChannel())) selectSalesChannel(validation.getSalesChannel());
		if(!StringUtil.isEmpty(validation.getInOutState())) selectInOutState(validation.getInOutState());
		if(!StringUtil.isEmpty(validation.getCustomerType())) {
			selectCustomerType(validation.getCustomerType());
		} else {
			selectCustomerType(0);
		}
		if(!StringUtil.isEmpty(validation.getCustomerPass())) {
			selectCustomerPass(validation.getCustomerPass());
		} else {
			selectCustomerPass(0);
		}
		if(!StringUtil.isEmpty(validation.getMember())) selectMember(validation.getMember());
		if(!StringUtil.isEmpty(validation.getPaymentType())) selectPaymentType(validation.getPaymentType());
		
		clickLookupRules();
		this.waitLoading();
		
		return this.getRuleStatus(ruleId);
	}
}
