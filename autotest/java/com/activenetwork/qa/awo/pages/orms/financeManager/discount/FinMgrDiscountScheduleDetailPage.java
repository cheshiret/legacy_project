/*
 * Created on Sep 27, 2009
 *
 * XXX To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.discount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author vzhao You can access this page by clicking on any discount schedule
 *         id
 */
public class FinMgrDiscountScheduleDetailPage extends FinanceManagerPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDiscountScheduleDetailPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDiscountScheduleDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDiscountScheduleDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrDiscountScheduleDetailPage();
		}

		return _instance;
	}
	
	private RegularExpression discount_name_id = new RegularExpression("discountType|SlipDiscountScheduleView\\.discountID", false);
	private RegularExpression fee_type_id = new RegularExpression("feeType|SlipDiscountScheduleView-\\d+\\.feeType", false);
	
	private RegularExpression product_category_pattern = new RegularExpression("prdGrpCategory|SlipDiscountScheduleView-\\d+\\.productGroupCategory", false);
	
	private RegularExpression inv_start_date_id = new RegularExpression("startDate_ForDisplay|SlipDiscountScheduleView.startDate_ForDisplay", false);
	private RegularExpression inv_end_date_id = new RegularExpression("endDate_ForDisplay|SlipDiscountScheduleView.endDate_ForDisplay", false);
	private RegularExpression base_rate_id = new RegularExpression("rates.base|SlipDiscountScheduleView.discntAmt", false);
	private RegularExpression account_id= new RegularExpression("SlipDiscountScheduleView.accountID|account", false);
	private RegularExpression product_id_pattern = new RegularExpression("(^product$)|(SlipDiscountScheduleView-\\d+\\.productID)",false);
	
	private RegularExpression dock_id_pattern = new RegularExpression("loop|SlipDiscountScheduleView-\\d+\\.dockID",false);
	private RegularExpression product_group_pattern = new RegularExpression("^(SlipDiscountScheduleView-\\d+\\.)?productGroup(ID)?$", false);
	private RegularExpression effc_date_id_pattern = new RegularExpression("(SlipDiscountScheduleView\\.)?effectiveDate_ForDisplay",false);
	                                                                         
	private String marina_rate_type_id = "SlipDiscountScheduleView.marinaRateType";

	private RegularExpression sale_channel_pattern = new RegularExpression("salesChannel|SlipDiscountScheduleView.condition.salesChannel", false);
	private RegularExpression customer_type_pattern = new RegularExpression("customerType|SlipDiscountScheduleView.condition.custTypeID",false);
	private RegularExpression customer_pass_pattern = new RegularExpression("customerPass|SlipDiscountScheduleView.condition.passTypeID",false);
	private RegularExpression is_member_pattern = new RegularExpression("memberType|SlipDiscountScheduleView.condition.member", false);
	private RegularExpression season_type_pattern = new RegularExpression("seasonType|SlipDiscountScheduleView.condition.seasonID", false);
	private RegularExpression in_out_state_pattern = new RegularExpression("outOfStateType|SlipDiscountScheduleView.condition.outOfState", false);
	private RegularExpression transaction_type_pattern = new RegularExpression("(SlipDiscountScheduleView\\.transactionType)|(transType)", false);
	private RegularExpression transaction_occurrence_pattern = new RegularExpression("(SlipDiscountScheduleView.transactionOccurrence)|(transOccu)",false);
	private String boat_category_pattern = "SlipDiscountScheduleView.boatCategory";
	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return ((browser.checkHtmlObjectExists(".class", "Html.A", ".text","Discount Schedule Details"))
				&&(browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Cancel")));
	}//updated by pzhu 2012/2/20

	/** Click OK button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click Cancel button */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/** Click Apply button */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	
	/**
	 * Select discount name from drop down list
	 * 
	 * @param name
	 *            - discount name
	 */
	public void selectDiscountName(String name) {
		browser.selectDropdownList(".id", discount_name_id, name);
		waitLoading();
	}

	/**
	 * 
	 * @return selected discount name
	 */
	public String getDiscountName() {
		return browser.getDropdownListValue(".id", discount_name_id, 0);
	}

	/**
	 * Select Fee Type from drop down list
	 * 
	 * @param feeType
	 */
	public void selectFeeType(String feeType) {
		browser.selectDropdownList(".id", fee_type_id, feeType);
		waitLoading();
	}

	/**
	 * 
	 * @return selected fee type
	 */
	public String getFeeType() {
		return browser.getDropdownListValue(".id", fee_type_id, 0);
	}

	/**
	 * Click Product category drop down and select one item
	 * 
	 * @param category
	 *            ---Ticket, POS, Site
	 */
	public void selectProductCategory(String category) {
		browser.selectDropdownList(".id", product_category_pattern, category);
	}

	/** Don't select any product category */
	public void deselectProductCategory() {
		browser.selectDropdownList(".id", "prdGrpCategory", 0);
		waitLoading();
	}

	/**
	 * 
	 * @return selected product category
	 */
	public String getProductCategory() {
		return browser.getDropdownListValue(".id", product_category_pattern, 0);
	}

	/**
	 * Select one loop item
	 * 
	 * @param loop
	 */
	public void selectLoop(String loop) {
		browser.selectDropdownList(".id",dock_id_pattern, loop);
	}

	/** Don't select any loop item */
	public void deselectloop() {
		browser.selectDropdownList(".id", dock_id_pattern, 0);
		waitLoading();
	}

	/**
	 * 
	 * @return selected loop value
	 */
	public String getLoop() {
		return browser.getDropdownListValue(".id", dock_id_pattern, 0);
	}
	
	public boolean isLoopExist(){
		return browser.checkHtmlObjectExists(".id", dock_id_pattern);
	}

	public boolean isProductDisplayedAsDropdownList() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", product_id_pattern);
	}
	
	/**
	 * Select one product
	 * 
	 * @param product
	 */
	public void selectProduct(String product) {
		browser.selectDropdownList(".id", product_id_pattern, product);
	}
	
	public void setProduct(String product) {
		String content = "";
		int i=0;//try three times
		do{
			browser.setTextField(".id", "s_pn", StringUtil.EMPTY);
			browser.setTextField(".id", "s_pn", product);
			if(!product.equalsIgnoreCase("All")) {
				Property[] p = Property.toPropertyArray(".className", "ac_results");
				browser.waitExists(p);
				browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
				Browser.sleep(3);
				browser.clickGuiObject(".class", "Html.LI", ".text", new RegularExpression(product+"\\(.*", false), true);
				ajax.waitLoading();
				content = browser.getTextFieldValue(".id", new RegularExpression("s_pn", false));
				i++;
			}
		}while(i<3&&!content.matches(product+ "\\(\\d+\\)"));
		if(!content.matches(product+ "\\(\\d+\\)")){
			throw new ErrorOnPageException("The product is not set properly after three times tries!");
		}
	}

	/** Don't select any product */
	public void deselectProduct() {
		browser.selectDropdownList(".id", product_id_pattern, 0);
		waitLoading();
	}

	/**
	 * 
	 * @return selected product
	 */
	public String getProduct() {
		if(browser.checkHtmlObjectDisplayed(".id", "s_pn")){
			return browser.getTextFieldValue(".id", "s_pn");
		}else{
			return browser.getDropdownListValue(".id", product_id_pattern, 0);
		}
	}

	/**
	 * Select Product Group
	 * 
	 * @param group
	 */
	public void selectProductGroup(String group) {
		browser.selectDropdownList(".id", product_group_pattern, group);
	}

	/** Don't select any product group */
	public void deselectProductGroup() {
		browser.selectDropdownList(".id", product_group_pattern, 0);
		this.waitLoading();
	}

	/**
	 * 
	 * @return selected product group
	 */
	public String getProductGroup() {
		return browser.getDropdownListValue(".id", product_group_pattern, 0);
	}

	/**
	 * Input effective date
	 * 
	 * @param date
	 */
	public void setEffectiveDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", effc_date_id_pattern, date);
	}

	/**
	 * Get Inputted effective date
	 * 
	 * @return
	 */
	public String getEffectiveDate() {
		return browser.getTextFieldValue(".id", effc_date_id_pattern);
	}

	public void setEffectiveEndDate(String endDate){
		browser.setTextField(".id", "effectiveEndDate_ForDisplay", endDate);
	}
	
	// only exist when fee type is POS Fee.
	public String getEffectiveEndDate(){
		return browser.getTextFieldValue(".id", "effectiveEndDate_ForDisplay");
	}
	
	public String getPurchaseType(){
		return browser.getDropdownListValue(".id", "purchasetype");
	}
	
	public void setPurchaseType(String type){
		browser.selectDropdownList(".id", "purchasetype", type);
	}
	
	/**
	 * Input inventory start date
	 * 
	 * @param date
	 */
	public void setInventoryStartDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", inv_start_date_id, date);
	}

	/**
	 * Get inventory start date
	 * 
	 * @return
	 */
	public String getInvStartDate() {
		return browser.getTextFieldValue(".id", inv_start_date_id);
	}

	/**
	 * Set inventory end date
	 * 
	 * @param date
	 */
	public void setInventoryEndDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", inv_end_date_id, date);
	}

	/**
	 * Get inventory end date
	 * 
	 * @return
	 */
	public String getInvEndDate() {
		return browser.getTextFieldValue(".id", inv_end_date_id);
	}
	
	public boolean isOrderCreateStartDateExist(){
		IHtmlObject[] objs=browser.getTextField(".id", "orderCreateStart_ForDisplay");
		if(objs.length>0){
			Browser.unregister(objs);
			return true;
		}else{
			Browser.unregister(objs);
			return false;
		}
		
	}
	
	public boolean isOrderCreateEndDateExist(){
		return browser.checkHtmlObjectDisplayed(".id", "orderCreateEnd_ForDisplay");
	}
	
	public void setOrderCreateEndDate(String date){
		browser.setTextField(".id", "orderCreateEnd_ForDisplay", date);
	}
	
	public void setOrderCreateStartDate(String date) {
		browser.setTextField(".id", "orderCreateStart_ForDisplay", date);
	}
	
	public String getErrorMessage(){                              
		return browser.getObjectText(".id", new RegularExpression("com.reserveamerica.((common.ServerAssertionException)|(system.product.api.ActiveDiscountScheduleConfictEx))",false));
	}
	
	public String getOrderCreateEndDate(){
		return browser.getTextFieldValue(".id", "orderCreateEnd_ForDisplay");
	}

	/**
	 * Get inventory end date
	 * 
	 * @return
	 */
	public String getOrderCreateStartDate() {
		return browser.getTextFieldValue(".id", "orderCreateStart_ForDisplay");
	}

	/**
	 * Set mon Rate
	 * 
	 * @param rate
	 */
	public void setMonRate(String rate) {
		browser.setTextField(".id", "rates.mon", rate);
	}

	/**
	 * Set tue Rate
	 * 
	 * @param rate
	 */
	public void setTueRate(String rate) {
		browser.setTextField(".id", "rates.tue", rate);
	}

	/**
	 * Set wed Rate
	 * 
	 * @param rate
	 */
	public void setWedRate(String rate) {
		browser.setTextField(".id", "rates.wed", rate);
	}

	/**
	 * Set Thu Rate
	 * 
	 * @param rate
	 */
	public void setThuRate(String rate) {
		browser.setTextField(".id", "rates.thu", rate);
	}

	/**
	 * Set Fri Rate
	 * 
	 * @param rate
	 */
	public void setFriRate(String rate) {
		browser.setTextField(".id", "rates.fri", rate);
	}

	/**
	 * Set Sat Rate
	 * 
	 * @param rate
	 */
	public void setSatRate(String rate) {
		browser.setTextField(".id", "rates.sat", rate);
	}

	/**
	 * Set Sun Rate
	 * 
	 * @param rate
	 */
	public void setSunRate(String rate) {
		browser.setTextField(".id", "rates.sun", rate);
	}

	/**
	 * Set Base Rate
	 * 
	 * @param rate
	 */
	public void setBaseRate(String rate) {
		browser.setTextField(".id", base_rate_id, rate);
	}

	/**
	 * 
	 * @return base rate value
	 */
	public String getBaseRate() {
		return browser.getTextFieldValue(".id", base_rate_id);
	}

	/**
	 * Set Sales Channel
	 * 
	 * @param channel
	 *            --All, CallCenter, Field, Web
	 */
	public void selectSalesChannel(String channel) {
		browser.selectDropdownList(".id", sale_channel_pattern, channel);
	}

	/** Don't select any sales channel */
	public void deselectSalesChannel() {
		browser.selectDropdownList(".id", sale_channel_pattern, 0);
	}

	/**
	 * 
	 * @return selected sales channel
	 */
	public String getSalesChannel() {
		return browser.getDropdownListValue(".id", sale_channel_pattern, 0);
	}

	/**
	 * Select Customer Type
	 * 
	 * @param type
	 *            --Disabled, over 85, senior, standard
	 */
	public void selectCustomerType(String type) {
		browser.selectDropdownList(".id", customer_type_pattern, type);
	}

	/** Don't select any customer type */
	public void deselectCustomerType() {
		browser.selectDropdownList(".id", customer_type_pattern, 0);
	}

	/**
	 * 
	 * @return selected customer type
	 */
	public String getCustomerType() {
		return browser.getDropdownListValue(".id", customer_type_pattern, 0);
	}

	/**
	 * Select customer Pass
	 * 
	 * @param value
	 *            --Access Pass,etc
	 */
	public void selectCustomerPass(String value) {
		browser.selectDropdownList(".id", customer_pass_pattern, value);
	}

	/** Don't select any customer Pass */
	public void deselectCustomerPass() {
		browser.selectDropdownList(".id", customer_pass_pattern, 0);
	}

	/**
	 * 
	 * @return selected Customer Pass
	 */
	public String getCustomerPass() {
		return browser.getDropdownListValue(".id", customer_pass_pattern, 0);
	}
	
	public List<String> getCustomerPassElement(){
		return browser.getDropdownElements(".id", customer_pass_pattern);
	}

	/**
	 * Select Member Type
	 * 
	 * @param type
	 *            --All, Yes, No
	 */
	public void selectMemberType(String type) {
		browser.selectDropdownList(".id", is_member_pattern, type);
	}

	/** Don't select any member Type */
	public void deselectMemberType() {
		browser.selectDropdownList(".id", is_member_pattern, 0);
	}

	/**
	 * 
	 * @return selected member type
	 */
	public String getMemberType() {
		return browser.getDropdownListValue(".id", is_member_pattern, 0);
	}

	/**
	 * Select Season
	 * 
	 * @param type
	 *            --All, core, mid, etc
	 */
	public void selectSeason(String type) {
		browser.selectDropdownList(".id", season_type_pattern, type);
	}

	/** Don't select any Season */
	public void deselectSeason() {
		browser.selectDropdownList(".id", season_type_pattern, 0);
	}

	/**
	 * 
	 * @return selected season
	 */
	public String getSeason() {
		return browser.getDropdownListValue(".id", season_type_pattern, 0);
	}

	/**
	 * Select out of state
	 * 
	 * @param type
	 *            ---All, In State,Out of State
	 */
	public void selectInOutState(String type) {
		browser.selectDropdownList(".id", in_out_state_pattern, type);
	}

	/** Don't select any item from outofstate drop down */
	public void deselectInOutState() {
		browser.selectDropdownList(".id", in_out_state_pattern, 0);
	}

	/**
	 * 
	 * @return selected In/out state
	 */
	public String getInOutState() {
		return browser.getDropdownListValue(".id", in_out_state_pattern, 0);
	}
	
	/**
	 * select transaction type
	 * @param type
	 * @Return void
	 * @Throws
	 */
	public void selectTranType(String type){
		browser.selectDropdownList(".id", transaction_type_pattern, type);
	}
	
	/**
	 * select transaction Occurrence
	 * @param occurrence
	 * @Return void
	 * @Throws
	 */
	public void selectTranOccurrence(String occurrence){
		browser.selectDropdownList(".id", transaction_occurrence_pattern, occurrence);
	}

	/**
	 * Select account
	 * 
	 * @param account
	 */
	public void selectAccount(String account) {
		browser.selectDropdownList(".id", account_id, account);
	}
	
	public void selectFirstAccount(){
		browser.selectDropdownList(".id", account_id, 1);
	}

	/** Don't select any account from the account drop down */
	public void deselectAccount() {
		browser.selectDropdownList(".id", account_id, 0);
	}

	/**
	 * 
	 * @return selected account value
	 */
	public String getAccount() {
		return browser.getDropdownListValue(".id", account_id, 0);
	}
	/**
	 * Just set up discount name and fee type
	 * @param schedule
	 */
	public void setupDiscountNameFeeTypeAndProductCategory(ScheduleData schedule){
		if (schedule.discountName != null && schedule.discountName.length() > 0) {
			selectDiscountName(schedule.discountName);
		}
		if (schedule.feeType != null && schedule.feeType.length() > 0) {
			selectFeeType(schedule.feeType);
		}
		if (schedule.productCategory != null
				&& schedule.productCategory.length() > 0) {
			selectProductCategory(schedule.productCategory);
			ajax.waitLoading();
		}
	}
	
	/**
	 * Set up fields except discount name and fee type
	 * @param schedule
	 * @return
	 */
	public void setupOtherFields(ScheduleData schedule){
		if (schedule.loop != null && schedule.loop.length() > 0) {
			selectLoop(schedule.loop);
			ajax.waitLoading();
		}
		if (schedule.productGroup != null && schedule.productGroup.length() > 0) {
			selectProductGroup(schedule.productGroup);
			ajax.waitLoading();
		}
		if (schedule.product != null && schedule.product.length() > 0) {
//			if(schedule.productCategory.equalsIgnoreCase("Slip")){
			if(isProductDisplayedAsDropdownList()) {
				selectProduct(schedule.product);
			} else {
				setProduct(schedule.product);
			}
			ajax.waitLoading();
//			}
		}
		if(!"".equals(schedule.effectiveDate)){
			setEffectiveDate(schedule.effectiveDate);
		}
		if(StringUtil.notEmpty(schedule.effectiveEndDate)){
			this.setEffectiveEndDate(schedule.effectiveEndDate);
		}
		if (!"Transaction Fee".equals(schedule.feeType)) {
			if(!"".equals(schedule.startDate)){
				setInventoryStartDate(schedule.startDate);
			}
			if(!"".equals(schedule.endDate)){
				setInventoryEndDate(schedule.endDate);
			}	
		}
		if(this.isOrderCreateStartDateExist()&& !"".endsWith(schedule.orderCreateStartDate)){
			this.setOrderCreateStartDate(schedule.orderCreateStartDate);
		}
		if(this.isOrderCreateEndDateExist()&& !"".endsWith(schedule.orderCreateEndDate)){
			this.setOrderCreateEndDate(schedule.orderCreateEndDate);
		}
		if(!StringUtil.isEmpty(schedule.minimumUnitOfStay) && isMinimumUnitOfStayExists()) {
			this.setMinimumUnitOfStay(schedule.minimumUnitOfStay);
		}
		if(StringUtil.notEmpty(schedule.marinaRateType)){
			this.selectMarinaRateType(schedule.marinaRateType);
		}
		if (null != schedule.rate && schedule.rate != "")
			this.setBaseRate(schedule.rate);
		if (null != schedule.monRate && schedule.monRate != "")
			this.setMonRate(schedule.monRate);
		if (null != schedule.tueRate && schedule.tueRate != "")
			this.setTueRate(schedule.tueRate);
		if (null != schedule.wedRate && schedule.wedRate != "")
			this.setWedRate(schedule.wedRate);
		if (null != schedule.thuRate && schedule.thuRate != "")
			this.setThuRate(schedule.thuRate);
		if (null != schedule.friRate && schedule.friRate != "")
			this.setFriRate(schedule.friRate);
		if (null != schedule.satRate && schedule.satRate != "")
			this.setSatRate(schedule.satRate);
		if (null != schedule.sunRate && schedule.sunRate != "")
			this.setSunRate(schedule.sunRate);

		if (schedule.salesChannel != null && schedule.salesChannel.length() > 0) {
			selectSalesChannel(schedule.salesChannel);
		}
		if (schedule.customerType != null && schedule.customerType.length() > 0) {
			selectCustomerType(schedule.customerType);
		}
		if (schedule.custPass != null && schedule.custPass.length() > 0) {
			selectCustomerPass(schedule.custPass);
		}
		if (schedule.member != null && schedule.member.length() > 0) {
			selectMemberType(schedule.member);
		}
		if (schedule.season != null && schedule.season.length() > 0) {
			selectSeason(schedule.season);
		}
		if (schedule.state != null && schedule.state.length() > 0) {
			selectInOutState(schedule.state);
		}
		if(schedule.tranType != null && schedule.tranType.trim().length()>0){
			this.selectTranType(schedule.tranType);
		}
		if(schedule.tranOccur != null && schedule.tranOccur.trim().length()>0){
			this.selectTranOccurrence(schedule.tranOccur);
		}
		if (schedule.accountCode != null && schedule.accountCode.length() > 0) {
			// if used in setup script no matter if account code is empty, always get it from UI but not
			// from the static datapool or somewhere.
			if(Boolean.parseBoolean(TestProperty.getProperty("forceOperation"))){
				selectFirstAccount();
			}else{
				selectAccount(schedule.accountCode);
			}
		}
		
		if(!StringUtil.isEmpty(schedule.boatCategory)) {
			selectBoatCategory(schedule.boatCategory);
		}
		
		//Below flow were added for 'Buy X Get Y Discount'
		if(StringUtil.notEmpty(schedule.disctAppliedTo))
			selectDisctAppliedTo(schedule.disctAppliedTo);
		if(isDisctAppliedDaysOfWeekExisted()) {
			if(schedule.disctAppliedMon)
				selectApplicableDayOfMon();
			else
				unselectApplicableDayOfMon();
			if(schedule.disctAppliedTue)
				selectApplicableDayOfTue();
			else
				unselectApplicableDayOfTue();
			if(schedule.disctAppliedWed)
				selectApplicableDayOfWed();
			else
				unselectApplicableDayOfWed();
			if(schedule.disctAppliedThu)
				selectApplicableDayOfThu();
			else
				unselectApplicableDayOfThu();
			if(schedule.disctAppliedFri)
				selectApplicableDayOfFri();
			else
				unselectApplicableDayOfFri();
			if(schedule.disctAppliedSat)
				selectApplicableDayOfSat();
			else
				unselectApplicableDayOfSat();
			if(schedule.disctAppliedSun)
				selectApplicableDayOfSun();
			else
				unselectApplicableDayOfSun();
		}
		if(StringUtil.notEmpty(schedule.disctBuyX))
			setBuyXValue(schedule.disctBuyX);
		if(StringUtil.notEmpty(schedule.disctGetY))
			setGetYDisctValue(schedule.disctGetY);
		if(StringUtil.notEmpty(schedule.maxDisctUnits))
			setMaxDisctedYValue(schedule.maxDisctUnits);
		if(StringUtil.notEmpty(schedule.disctCalMethod))
			selectDisctCalculateMethod(schedule.disctCalMethod);
		//End of flow for 'Buy X Get Y Discount'
		
		if(schedule.feeType.equalsIgnoreCase("POS Fee")){
			this.setPurchaseType(schedule.purchaseType);
		}
	}
	
	
	public void setUpDiscountScheduleInfo(ScheduleData schedule){
		this.setupDiscountNameFeeTypeAndProductCategory(schedule);
		this.setupOtherFields(schedule);
	}
	
	public void setUpDiscountScheduleInfoAndClickApply(ScheduleData schedule){
		this.setUpDiscountScheduleInfo(schedule);
		clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * This method used to set up a discount schedule
	 * 
	 * @param schedule
	 * @return new schedule id
	 */
	public String setupDiscountSchedule(ScheduleData schedule) {
		this.setUpDiscountScheduleInfoAndClickApply(schedule);
		String schedId = getScheduleId();
		clickOK();

		return schedId;
	}
	
	public void updateDiscountScheduleInfo(ScheduleData schedule){
		if (schedule.loop != null && schedule.loop.length() > 0) {
			selectLoop(schedule.loop);
		}
		if (schedule.productGroup != null && schedule.productGroup.length() > 0) {
			selectProductGroup(schedule.productGroup);
		}
		if (schedule.product != null && schedule.product.length() > 0) {
//			if(schedule.productCategory.equalsIgnoreCase("Slip")){
			if(isProductDisplayedAsDropdownList()) {
				selectProduct(schedule.product);
			} else {
				setProduct(schedule.product);
			}
			ajax.waitLoading();
			this.waitLoading();
//			}
		}
		if(!"".equals(schedule.effectiveDate)){
			setEffectiveDate(schedule.effectiveDate);
		}
		if(StringUtil.notEmpty(schedule.effectiveEndDate)){
			this.setEffectiveEndDate(schedule.effectiveEndDate);
		}
		if (!"Transaction Fee".equals(schedule.feeType)) {
			if(StringUtil.notEmpty(schedule.startDate)){
				setInventoryStartDate(schedule.startDate);
			}
			if(StringUtil.notEmpty(schedule.endDate)){
				setInventoryEndDate(schedule.endDate);
			}	
		}
		if(StringUtil.notEmpty(schedule.marinaRateType)){
			this.selectMarinaRateType(schedule.marinaRateType);
		}
		if (null != schedule.rate && schedule.rate != "")
			this.setBaseRate(schedule.rate);
		if (null != schedule.monRate && schedule.monRate != "")
			this.setMonRate(schedule.monRate);
		if (null != schedule.tueRate && schedule.tueRate != "")
			this.setTueRate(schedule.tueRate);
		if (null != schedule.wedRate && schedule.wedRate != "")
			this.setWedRate(schedule.wedRate);
		if (null != schedule.thuRate && schedule.thuRate != "")
			this.setThuRate(schedule.thuRate);
		if (null != schedule.friRate && schedule.friRate != "")
			this.setFriRate(schedule.friRate);
		if (null != schedule.satRate && schedule.satRate != "")
			this.setSatRate(schedule.satRate);
		if (null != schedule.sunRate && schedule.sunRate != "")
			this.setSunRate(schedule.sunRate);

		if (schedule.salesChannel != null && schedule.salesChannel.length() > 0) {
			selectSalesChannel(schedule.salesChannel);
		}
		if (schedule.customerType != null && schedule.customerType.length() > 0) {
			selectCustomerType(schedule.customerType);
		}
		if (schedule.custPass != null && schedule.custPass.length() > 0) {
			selectCustomerPass(schedule.custPass);
		}
		if (schedule.member != null && schedule.member.length() > 0) {
			selectMemberType(schedule.member);
		}
		if (schedule.season != null && schedule.season.length() > 0) {
			selectSeason(schedule.season);
		}
		if (schedule.state != null && schedule.state.length() > 0) {
			selectInOutState(schedule.state);
		}
		if(schedule.tranType != null && schedule.tranType.trim().length()>0){
			this.selectTranType(schedule.tranType);
		}
		if(schedule.tranOccur != null && schedule.tranOccur.trim().length()>0){
			this.selectTranOccurrence(schedule.tranOccur);
		}
		if (schedule.accountCode != null && schedule.accountCode.length() > 0) {
			// if used in setup script no matter if account code is empty, always get it from UI but not
			// from the static datapool or somewhere.
			if(Boolean.parseBoolean(TestProperty.getProperty("forceOperation"))){
				selectFirstAccount();
			}else{
				selectAccount(schedule.accountCode);
			}
		}
		if(schedule.productCategory.equalsIgnoreCase("Slip")){
			this.selectBoatCategory(schedule.boatCategory);
		}
		if(schedule.feeType.equalsIgnoreCase("POS Fee")){
			this.setPurchaseType(schedule.purchaseType);
		}
	}
	
	public String updateDiscountScheduleAndClickApply(ScheduleData schedule){
		this.updateDiscountScheduleInfo(schedule);
		clickApply();
		this.waitLoading();
		String schedId = getScheduleId();
		return schedId;
	}
	
	public String updateDiscountSchedule(ScheduleData schedule){
		String schedId = updateDiscountScheduleAndClickApply(schedule);
		clickOK();
		return schedId;
	}

	private Property[] minimumUnitOfStay() {
		return Property.toPropertyArray(".id", "minimumunitofstay");
	}
	
	public boolean isMinimumUnitOfStayExists() {
		return browser.checkHtmlObjectExists(minimumUnitOfStay());
	}
	
	public void setMinimumUnitOfStay(String stay) {
		browser.setTextField(minimumUnitOfStay(), stay);
	}
	
	public boolean isMarinaRateTypeEnable(){
		return browser.checkHtmlObjectEnabled(".id",marina_rate_type_id);
	}
	/**
	 * @param marinaRateType
	 */
	public void selectMarinaRateType(String marinaRateType) {
		if(isMarinaRateTypeEnable()){
			browser.selectDropdownList(".id", marina_rate_type_id, marinaRateType);
		}
	}
	
	public String getMarinaRateType(){
		return browser.getDropdownListValue(".id", marina_rate_type_id);
	}
	
	public void selectBoatCategory(String category) {
		browser.selectDropdownList(".id", boat_category_pattern, category);
	}
	
	public String getBoatCategory(){
		return browser.getDropdownListValue(".id", boat_category_pattern);
	}

	/**
	 * get schedule id after click apply button
	 * 
	 * @return schedule id
	 */
	public String getScheduleId() {
		RegularExpression rex = new RegularExpression(
				"Discount Schedule\\W*+Discount Schedule ID.*", false);
		IHtmlObject[] obj = browser.getTableTestObject(".text", rex);
		String scheduleId = ((IHtmlTable) obj[0]).getCellValue(0, 2).split(
				"ID")[1].trim();

		Browser.unregister(obj);
		return scheduleId;
	}

	/**
	 * This method used to verify schedule detail info
	 * 
	 * @param schedule
	 */
	public void verifyScheduleDetail(ScheduleData schedule) {
		logger.info("Start to verify schedule details info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Discount name", schedule.discountName, getDiscountName());
		result &= MiscFunctions.compareResult("Fee Type", schedule.feeType, getFeeType());
		result &= MiscFunctions.compareResult("Product Category", schedule.productCategory, getProductCategory());
		result &= MiscFunctions.compareResult("product", schedule.product, getProduct().split("\\(")[0].trim());

		// Effective Date, Inventory Start Date, Inventory End Date
		if(schedule.feeType.equalsIgnoreCase("Attribute Fee") || schedule.feeType.equalsIgnoreCase("Use Fee")){
			result &= MiscFunctions.compareResult("Effective Date", schedule.effectiveDate, getEffectiveDate());
			result &= MiscFunctions.compareResult("Inventory Start Date", schedule.startDate, getInvStartDate());
			result &= MiscFunctions.compareResult("Inventory End Date", schedule.endDate, getInvEndDate());
			if(!schedule.productCategory.equalsIgnoreCase("Slip")){
				result &= MiscFunctions.compareResult("Order Create Start Date", schedule.orderCreateStartDate, this.getOrderCreateStartDate());
			}
		} else if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){// Effective Date
			if (!schedule.effectiveDate.equalsIgnoreCase(getEffectiveDate())) {
				throw new ErrorOnPageException("Effective Date " + getEffectiveDate() + " not correct.");
			}
		} else if(schedule.feeType.equalsIgnoreCase("POS Fee")){// Effective Start Date, Effective End Date
			result &= MiscFunctions.compareResult("Effective Start Date", schedule.effectiveDate, getEffectiveDate());
			result &= MiscFunctions.compareResult("Effective End Date", schedule.effectiveEndDate, getEffectiveEndDate());
			result &= MiscFunctions.compareResult("Purchase Type", schedule.purchaseType, getPurchaseType());
			result &= MiscFunctions.compareResult("Customer Pass", schedule.custPass, getCustomerPass());
		}
		result &= MiscFunctions.compareResult("Assignment product group", schedule.productGroup, getProductGroup());
		if(!schedule.rateType.equalsIgnoreCase("Override")){
			result &= MiscFunctions.compareResult("Base Rate", Double.valueOf(schedule.rate), Double.valueOf(getBaseRate()));
			if(StringUtil.isEmpty(schedule.salesChannel)){
				result &= MiscFunctions.compareResult("Sales Channel", "All", getSalesChannel());// default value is All
			} else {
				result &= MiscFunctions.compareResult("Sales Channel", schedule.salesChannel, getSalesChannel());
			}

			if(StringUtil.isEmpty(schedule.customerType)){
				result &= MiscFunctions.compareResult("Customer Type", "All", getCustomerType());// default value is All
			} else {
				result &= MiscFunctions.compareResult("Customer Type", schedule.customerType, getCustomerType());
			}
		}
		
		// 'Override' rate type,POS fee doesn't have loop customer pass, member, season, in/out state, so don;t need to verify
		if((schedule.feeType.equalsIgnoreCase("Attribute Fee") ||
				schedule.feeType.equalsIgnoreCase("Transaction Fee") ||
				schedule.feeType.equalsIgnoreCase("Use Fee"))&&!schedule.rateType.equalsIgnoreCase("Override")){

			result &= MiscFunctions.compareResult("getLoop", schedule.loop, getLoop());
			result &= MiscFunctions.compareResult("Customer Pass", schedule.custPass, getCustomerPass());
			result &= MiscFunctions.compareResult("Member", schedule.member, getMemberType());
			result &= MiscFunctions.compareResult("Season", schedule.season, getSeason());
			result &= MiscFunctions.compareResult("In/Out State", schedule.state, getInOutState());
		}
		result &= MiscFunctions.compareResult("Account", schedule.accountCode, getAccount());
		
		if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
			result &= MiscFunctions.compareResult("Transaction type", schedule.tranType, getTransType());
			result &= MiscFunctions.compareResult("Transaction occurrence", schedule.tranOccur, getTransOccurrence());
		}
		if(schedule.productCategory.equalsIgnoreCase("Slip")){
			if(!schedule.rateType.equalsIgnoreCase("Override")){
				result &= MiscFunctions.compareResult("Boat category", schedule.boatCategory, this.getBoatCategory());
			}
			result &= MiscFunctions.compareResult("Marina rate type", schedule.marinaRateType, this.getMarinaRateType());
		}
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}

	public String getTransType(){
		return browser.getDropdownListValue(".id", transaction_type_pattern, 0);
	}
	
	public String getTransOccurrence(){
		return browser.getDropdownListValue(".id", transaction_occurrence_pattern, 0);
	}
	
	/**
	 * Update discount schedule details information
	 * 
	 * @param schInfo
	 *            ---Feescheduledata data
	 * @param arrivalDate
	 *            -- arrival date
	 */
	public void updateDiscountScheduleDetails(FeeScheduleData schInfo,
			String arrivalDate) {
		if (null != schInfo.productCategory && schInfo.productCategory != ""
				&& schInfo.productCategory != "All")
			this.selectProductCategory(schInfo.productCategory);
		if (null != schInfo.loop && schInfo.loop != "" && schInfo.loop != "All")
			this.selectLoop(schInfo.loop);
		if (null != schInfo.product && schInfo.product != ""
				&& schInfo.product != "All")
			this.selectProduct(schInfo.product);
		if (null != schInfo.productGroup && schInfo.productGroup != ""
				&& schInfo.productGroup != "All")
			this.selectProductGroup(schInfo.productGroup);

		String effectDate = DateFunctions.formatDate(this.getEffectiveDate());
		String invStartDate = DateFunctions.formatDate(this.getInvStartDate());
		String invEndDate = DateFunctions.formatDate(this.getInvEndDate());

		// set the discount date
		if (schInfo.effectDate.length() > 0)
			this.setEffectiveDate(schInfo.effectDate);
		else if (DateFunctions.compareDates(arrivalDate, effectDate) != 1)
			this.setEffectiveDate(DateFunctions.getDateAfterToday(-7));// active
		// the
		// schedule
		// 7
		// days
		// before
		// today

		if (schInfo.startInv.length() > 0)
			this.setInventoryStartDate(schInfo.startInv);
		else if (DateFunctions.compareDates(arrivalDate, invStartDate) != 1)
			this.setInventoryStartDate(DateFunctions.getDateAfterToday(-14));// active
		// the
		// start
		// 7
		// days
		// before
		// today

		if (schInfo.endInv.length() > 0)
			this.setInventoryEndDate(schInfo.endInv);
		else if (DateFunctions.compareDates(arrivalDate, invEndDate) != -1)
			this.setInventoryEndDate(DateFunctions.getDateAfterToday(14));// active
		// the
		// end
		// 7
		// days
		// after
		// today

		// set the discount rate here
		if (null != schInfo.discountRate && schInfo.discountRate != "")
			this.setBaseRate(schInfo.discountRate);
		if (null != schInfo.monRate && schInfo.monRate != "")
			this.setMonRate(schInfo.monRate);
		if (null != schInfo.tuesRate && schInfo.tuesRate != "")
			this.setTueRate(schInfo.tuesRate);
		if (null != schInfo.wedRate && schInfo.wedRate != "")
			this.setWedRate(schInfo.wedRate);
		if (null != schInfo.thurRate && schInfo.thurRate != "")
			this.setThuRate(schInfo.thurRate);
		if (null != schInfo.friRate && schInfo.friRate != "")
			this.setFriRate(schInfo.friRate);
		if (null != schInfo.satRate && schInfo.satRate != "")
			this.setSatRate(schInfo.satRate);
		if (null != schInfo.sunRate && schInfo.sunRate != "")
			this.setSunRate(schInfo.sunRate);

		// select customer type & pass
		if (null != schInfo.custType && schInfo.custType != "")
			this.selectCustomerType(schInfo.custType);
		if (null != schInfo.custPass && schInfo.custPass != "")
			this.selectCustomerPass(schInfo.custPass);
	}
	
	public void selectDisctAppliedTo(String appliedTo) {
		int index = 0;
		if(appliedTo.equalsIgnoreCase("All Units in the Stay"))
			index = 0;
		else if(appliedTo.equalsIgnoreCase("Unit(s) within Inventory Start/End"))
			index = 1;
		else
			throw new ErrorOnDataException("Unhandled applied to type "+appliedTo);
		browser.selectRadioButton(".id", "discountAppliesTo", index);
	}
	
	public boolean isDisctAppliedDaysOfWeekExisted() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "Applicable Day of the Week for Discount");
	}
	
	public void selectApplicableDayOfMon() {
		browser.selectCheckBox(".id", "monInd");
	}
	
	public void selectApplicableDayOfTue() {
		browser.selectCheckBox(".id", "tueInd");
	}
	
	public void selectApplicableDayOfWed() {
		browser.selectCheckBox(".id", "wedInd");
	}
	
	public void selectApplicableDayOfThu() {
		browser.selectCheckBox(".id", "thuInd");
	}
	
	public void selectApplicableDayOfFri() {
		browser.selectCheckBox(".id", "friInd");
	}
	
	public void selectApplicableDayOfSat() {
		browser.selectCheckBox(".id", "satInd");
	}
	
	public void selectApplicableDayOfSun() {
		browser.selectCheckBox(".id", "sunInd");
	}
	
	public void unselectApplicableDayOfMon() {
		browser.unSelectCheckBox(".id", "monInd");
	}
	
	public void unselectApplicableDayOfTue() {
		browser.unSelectCheckBox(".id", "tueInd");
	}
	
	public void unselectApplicableDayOfWed() {
		browser.unSelectCheckBox(".id", "wedInd");
	}
	
	public void unselectApplicableDayOfThu() {
		browser.unSelectCheckBox(".id", "thuInd");
	}
	
	public void unselectApplicableDayOfFri() {
		browser.unSelectCheckBox(".id", "friInd");
	}
	
	public void unselectApplicableDayOfSat() {
		browser.unSelectCheckBox(".id", "satInd");
	}
	
	public void unselectApplicableDayOfSun() {
		browser.unSelectCheckBox(".id", "sunInd");
	}
	
	public void setBuyXValue(String value) {
		browser.setTextField(".id", "buyX", value);
	}
	
	public void setGetYDisctValue(String value) {
		browser.setTextField(".id", "getYDiscounted", value);
	}
	
	public void setMaxDisctedYValue(String value) {
		browser.setTextField(".id", "maxDiscountedY", value);
	}
	
	public void selectDisctCalculateMethod(String calMethod) {
		int index = 0;
		if(calMethod.equalsIgnoreCase("Sequential"))
			index = 0;
		else if(calMethod.equalsIgnoreCase("Low Priced"))
			index = 1;
		else if(calMethod.equalsIgnoreCase("High Priced"))
			index = 2;
		else
			throw new ErrorOnDataException("Unhandled applied to type "+calMethod);
		browser.selectRadioButton(".id", "calculationMethod", index);
	}
	
	public String getRateTypeInDiscountDetails(){
		return browser.getTextFieldValue(".id", "rateType");
	}
	
	public String getUnitTypeInDiscountDetails(){
		return browser.getTextFieldValue(".id", "unitType");
	}
	
	public List<String> getBehaviorInDiscountDetails(){
		List<String> behaviros = new ArrayList<String>(); 
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Discount Details.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".classname", "inputwithrubylabel checkbox", topObjs[0]);
		for(int i=0; i<objs.length; i++){
			IHtmlObject[] inputObj = browser.getHtmlObject(".class", "Html.INPUT.CHECKBOX", objs[i]);
			ICheckBox ic = (ICheckBox)inputObj[0];
			if( ic.isSelected() ){
				IHtmlObject[] labelObj = browser.getHtmlObject(".class", "Html.LABEL", objs[i]);
				String content = labelObj[0].text();
				behaviros.add(content);
				Browser.unregister(labelObj);
			}
			Browser.unregister(inputObj);
		}
		Browser.unregister(topObjs);
		Browser.unregister(objs);
		return behaviros;
	}
	
	public void verifyDiscountDeatilsField(String rateType, String unitType, List<String> behaviors){
		boolean passed = true;
		String actRateType = this.getRateTypeInDiscountDetails();
		List<String> actBehaviors = this.getBehaviorInDiscountDetails();
		if(!actRateType.equalsIgnoreCase(rateType)){
			passed = false;
			logger.info("Rate type in discount detail is not correct! expect:" + rateType + ",but actually is:" +  actRateType);
		}
		if(!(behaviors.containsAll(actBehaviors)&&actBehaviors.containsAll(behaviors))){
			passed = false;
			logger.info("Behaviors in discount detail is not correct! expect:" + behaviors.toString() + ",but actually is:" + actBehaviors.toString());
		}
		if(StringUtil.notEmpty(unitType)){
			String actUntiType = this.getUnitTypeInDiscountDetails();
			if(!actUntiType.equalsIgnoreCase(unitType)){
				passed = false;
				logger.info("Rate type in discount detail is not correct! expect:" + unitType + ",but actually is:" +  actUntiType);
			}
		}
		if(!passed){
			throw new ErrorOnDataException("Content for discount details is not correct, please check the log above");
		}
	}
	
	public boolean isRateTypeInDiscountDetialEditable(){
		return browser.checkHtmlObjectEnabled(".id", "rateType");
	}
	
	public boolean isUnitTypeInDiscountDetailEditable(){
		return browser.checkHtmlObjectEnabled(".id", "unitType");
	}
	
	public boolean isBehaviorInDiscountDetailEditable(){
		boolean enabled = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Discount Details.*", false));
		IHtmlObject[] inputObjs = browser.getHtmlObject(".class", "Html.INPUT.CHECKBOX", objs[0]);
		for(int i=0; i<inputObjs.length; i++){
			enabled &= inputObjs[i].isEnabled();    
			logger.info("The " + i + "th check box for behavior is " + (inputObjs[i].isEnabled()?"editable":"not editable"));
		}
		Browser.unregister(objs);
		Browser.unregister(inputObjs);
		return enabled;
	}
	
	public void verifyDiscountDetailFildNotEditable(String feeType, String rateType){
		boolean passed = true;
		passed &= !this.isRateTypeInDiscountDetialEditable();
		passed &= !this.isBehaviorInDiscountDetailEditable();
		if(!feeType.equalsIgnoreCase("Transaction Fee")&&!rateType.equalsIgnoreCase("Override")){
			passed &= !this.isUnitTypeInDiscountDetailEditable();
		}
		if(!passed){
			throw new ErrorOnDataException("Discount Detail field should not be editable, please check the log above.");
		}	
	}
	
	public boolean isObjectDisplay(String patternString){
		boolean displayed = browser.checkHtmlObjectDisplayed(".id", patternString);
		logger.info(patternString + " is " + (displayed?"displayed":"not displayed")); 
		return displayed;
	}
	
	public boolean isObjectDisplay(RegularExpression patternExp){
		boolean displayed = browser.checkHtmlObjectDisplayed(".id", patternExp);
		logger.info(patternExp.toString() + " is " + (displayed?"displayed":"not displayed")); 
		return displayed;
	}

	public boolean isObjectEditable(String patternString){
		boolean enabled = browser.checkHtmlObjectEnabled(".id", patternString);
		logger.info(patternString + " is " + (enabled?"enabled":"not enabled")); 
		return enabled;
	}
	
	public boolean isObjectEditable(RegularExpression patternExp){
		boolean enabled = browser.checkHtmlObjectEnabled(".id", patternExp);
		logger.info(patternExp.toString() + " is " + (enabled?"enabled":"not enabled")); 
		return enabled;
	}
	
	
	
	/**
	 * Dock/Area, Product Group, Product, Status, Effective Date, Inventory Start Date, Inventory End Date, Marina Rate Type, and Account
	 */
	public void verifyFieldDisplayed(String rateType, String feeType){
		boolean passed = true;
		passed &= this.isObjectDisplay(dock_id_pattern);
		passed &= this.isObjectDisplay(product_group_pattern);
		passed &= this.isObjectDisplay(product_id_pattern);
		passed &= this.isObjectDisplay(effc_date_id_pattern);
		passed &= this.isObjectDisplay(marina_rate_type_id);
		passed &= this.isObjectDisplay(account_id);
		if(!feeType.equalsIgnoreCase("Transaction Fee")){
			passed &= this.isObjectDisplay(inv_start_date_id);
			passed &= this.isObjectDisplay(inv_end_date_id);
		    if(!rateType.equalsIgnoreCase("Override")){
				passed &= this.isObjectDisplay(base_rate_id);
				passed &= this.isObjectDisplay(sale_channel_pattern);
				passed &= this.isObjectDisplay(customer_type_pattern);
				passed &= this.isObjectDisplay(is_member_pattern);
				passed &= this.isObjectDisplay(season_type_pattern);
				passed &= this.isObjectDisplay(in_out_state_pattern);
				passed &= this.isObjectDisplay(boat_category_pattern);
		    }
		}else{
			passed &= this.isObjectDisplay(transaction_type_pattern);
			passed &= this.isObjectDisplay(transaction_occurrence_pattern);
		}
		if(!passed){
			throw new ErrorOnDataException("Not all the fields display in the page, please check the log above.");
		}
		logger.info("Fields should display is displayed!");
	}
	
	public void verifyFieldEditable(String rateType, String unitType, String feeType){
		boolean passed = true;
		passed &= this.isObjectEditable(dock_id_pattern);
		passed &= this.isObjectEditable(product_group_pattern);
		passed &= this.isObjectEditable(product_id_pattern);
		passed &= this.isObjectEditable(effc_date_id_pattern);
		if(!unitType.equalsIgnoreCase("Per Unit Of Stay")){
			passed &= this.isObjectEditable(marina_rate_type_id);
		}
		passed &= this.isObjectEditable(account_id);
		if(!rateType.equalsIgnoreCase("Override")){
			passed &= this.isObjectEditable(sale_channel_pattern);
			passed &= this.isObjectEditable(customer_type_pattern);
			passed &= this.isObjectEditable(customer_pass_pattern);
			passed &= this.isObjectEditable(is_member_pattern);
			passed &= this.isObjectEditable(season_type_pattern);
			passed &= this.isObjectEditable(in_out_state_pattern);
			passed &= this.isObjectEditable(base_rate_id);
			passed &= this.isObjectEditable(boat_category_pattern);
		}
		if(!feeType.equalsIgnoreCase("Transaction Fee")){
			passed &= this.isObjectEditable(inv_start_date_id);
			passed &= this.isObjectEditable(inv_end_date_id);
		}else{
			passed &= this.isObjectEditable(transaction_type_pattern);
			passed &= this.isObjectEditable(transaction_occurrence_pattern);
		}
		if(!passed){
			throw new ErrorOnDataException("Not all the fields enabled in the page, please check the log above.");
		}
		logger.info("Fields should display is correct!");
	}
	
	public void verifyNoEditableField(String feeType, String unitType){
		boolean passed = true;
		passed &= !this.isObjectEditable(discount_name_id);
		passed &= !this.isObjectEditable(fee_type_id);
		passed &= !this.isObjectEditable(product_category_pattern);
		//The fields in discount details section
		passed &= !this.isRateTypeInDiscountDetialEditable();
		passed &= !this.isBehaviorInDiscountDetailEditable();
		if(!feeType.equalsIgnoreCase("Transaction Fee")){
			passed &= !this.isUnitTypeInDiscountDetailEditable();
		}
		if(unitType.equalsIgnoreCase("Per Unit Of Stay")){
			passed &= !this.isObjectEditable(marina_rate_type_id);
		}
		if(!passed){
			throw new ErrorOnDataException("Some of the field is editable, please check the log above.");
		}
		logger.info("Fields should not editable is correct!");
	}
}
