/*
 * $Id: FinMgrFeeSchDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.financeManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.FeeDataForPersonOrTicketType;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PromoCode;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFeeCustomDuration;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFeeEventHoliday;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author CGuo
 */
public class FinMgrFeeSchDetailPage extends FinanceManagerPage {

	/**
	 * Script Name : FinMgrFeeSchDetailPage Generated : Oct 21, 2004 6:35:41 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 *
	 * @since 2004/10/21
	 */

	/** A handle to the unique Singleton instance. */
	static private FinMgrFeeSchDetailPage _instance = null;
	public final String Type_Seasonal = "Seasonal";
	public final String Type_Lease = "Lease";
	public final String Type_Transient = "Transient";
	public final String Method_Calculation_Daily = "Daily";
	public final String Method_Calculation_Percentage = "Percentage";
	
	public final String LENGTH_RANGE = "Length Range";
	public final String LENGTH_INCREMENT = "Length Increments";
	public final String RANGE_VIEW = "SlipFeeRangeView";
	public final String INCREMENT_VIEW = "SlipFeeIncrementView";
	public final String DAILY_NIGHTLY_FEE = "nightlyFee";
	public final String SEASON_FEE = "seasonFee";
	public final String MONTHLY_FEE = "monthlyFee";
	public final String RANGE = "rangeFeet";
	

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrFeeSchDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrFeeSchDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrFeeSchDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return (browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Fee Schedule Details"))&&(browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"QA"));
	}

	/**
	 * Select product by given value.
	 *
	 * @param product
	 */
	public void selectPrdCategory(String product) {
		browser.selectDropdownList(".id", "prd_grp_cat_id", product);
		waitLoading();
	}

	/**
	 *
	 * @return selected product category
	 */
	public String getPrdCategory() {
		return browser.getDropdownListValue(".id", "prd_grp_cat_id", 0);
	}

	/**
	 * Select fee type by given type.
	 *
	 * @param type
	 *            - fee type
	 */
	public void selectFeeType(String type) {
		browser.selectDropdownList(".id", "fee_type", type);
		waitLoading();
	}

	/**
	 *
	 * @return selected fee type value
	 */
	public String getFeeType() {
		return browser.getDropdownListValue(".id", "fee_type", 0);
	}

	/**
	 * Select assign loop.
	 *
	 * @param loop
	 */
	public void selectAssignLoop(String loop) {
		browser.selectDropdownList(".id", "assignment_loop", loop);
		waitLoading();
	}

	/**
	 *
	 * @return selected assign loop
	 */
	public String getAssignLoop() {
		return browser.getDropdownListValue(".id", "assignment_loop", 0);
	}
	
	public boolean isAssignLooppExist(){
		return browser.checkHtmlObjectDisplayed(".id", "assignment_loop");
	}
	
	public List<String> getAssignLoopElement() {
		return browser.getDropdownElements(".id", "assignment_loop");
	}

	/**
	 * Select assign loop group.
	 *
	 * @param group
	 *            - loop group
	 */
	public void selectAssignProdGroup(String group) {
		browser.selectDropdownList(".id", "assignment_prodgroup", group);
		waitLoading();
	}
	
	public void selectAssignProdCategory(String category) {//FeeSchdDetailsView-\\d+\\.applicablePrdCat used for List assignment product category
		browser.selectDropdownList(".id", new RegularExpression("(assignment_app_prdcat)|(FeeSchdDetailsView-\\d+\\.applicablePrdCat)",false), category);
	}
	
	public String getAssignProdCategory(){//FeeSchdDetailsView-\\d+\\.applicablePrdCat used for List assignment product category
		return browser.getDropdownListValue(".id", new RegularExpression("(assignment_app_prdcat)|(FeeSchdDetailsView-\\d+\\.applicablePrdCat)",false),0);
	}
	
	public List<String> getAssignProdCategoryElements(){//FeeSchdDetailsView-\\d+\\.applicablePrdCat used for List assignment product category
		return browser.getDropdownElements(".id", new RegularExpression("(assignment_app_prdcat)|(FeeSchdDetailsView-\\d+\\.applicablePrdCat)",false));
	}

	/**
	 * Select assign loop group.Use in Lotterry Transaction Fee
	 *
	 * @param group
	 *            - loop group
	 */
	public void selectApplicableAssignProdGroup(String group) {
		browser.selectDropdownList(".id", "assignment_app_prdcat", group);
		waitLoading();
	}

	/**
	 *
	 * @return selected assign product group
	 */
	public String getAssignProdGroup() {
		return browser.getDropdownListValue(".id", "assignment_prodgroup", 0);
	}

	public List<String> getAssignProdGroupElements() {
		return browser.getDropdownElements(".id", "assignment_prodgroup");
	}
	
	/**
	 * Select assign product.
	 *
	 * @param product
	 */
	public void selectAssignProduct(String product) {//FeeSchdDetailsView-\\d+\\.prdID used for List product
		if(browser.checkHtmlObjectDisplayed(".id", new RegularExpression("(assignment_product)|(FeeSchdDetailsView-\\d+\\.prdID)",false))){
			browser.selectDropdownList(".id", new RegularExpression("(assignment_product)|(FeeSchdDetailsView-\\d+\\.prdID)",false), product);
			waitLoading();
		}else{
			this.setAssignPOSProduct(product);
		}
		
	}
	
	public void setAssignPOSProduct(String product) {
		String content = "";
		int i=0;//try three times
		do{
			browser.setTextField(".id", new RegularExpression("s_pn", false), StringUtil.EMPTY);
			browser.setTextField(".id", new RegularExpression("s_pn", false), product, true);
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

	/**
	 *
	 * @return selected assign product
	 */
	public String getAssignProduct() {//FeeSchdDetailsView-\\d+\\.prdID used for List product
		if(browser.checkHtmlObjectDisplayed(".class", "Html.Select", ".id", new RegularExpression("(assignment_product)|(FeeSchdDetailsView-\\d+\\.prdID)",false))){
			return browser.getDropdownListValue(".id", new RegularExpression("(assignment_product)|(FeeSchdDetailsView-\\d+\\.prdID)",false), 0);	
		}else{
			return browser.getTextFieldValue(".id","s_pn");
		}
		
	}
	
	public List<String> getAssignProductElements(){//FeeSchdDetailsView-\\d+\\.prdID used for List product
		return browser.getDropdownElements(".id", new RegularExpression("(assignment_product)|(FeeSchdDetailsView-\\d+\\.prdID)",false));
	}

	/**
	 * Fill in the effective date.
	 *
	 * @param date
	 *            - effective date
	 */
	public void setEffectiveDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
//		browser.setTextField(".id", "date_effective_ForDisplay", date);
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_effective_ForDisplay")[0], date);
	}
	
	public void setEffectiveDateAsBlank() {
		browser.setTextField(".id", "date_effective_ForDisplay", "");
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_effective_ForDisplay")[0], "");
	}

	/**
	 *
	 * @return effective date value
	 */
	public String getEffectiveDate() {
		return browser.getTextFieldValue(".id", "date_effective_ForDisplay");
	}
	
	public String getErrorMsg() {
		IHtmlObject[] objs=browser.getHtmlObject(".classname", "message msgerror");
		if(objs.length<1){
			throw new ErrorOnPageException("can not find the error message object...");
		}
		String text=objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return text;
	}
	
	public boolean checkErrorMessageExisting(){
		return browser.checkHtmlObjectExists(".classname", "message msgerror");
	}

	/**
	 * Input Effective start date
	 *
	 * @param startDate
	 */
	public void setEffectStartDate(String startDate) {
		browser.setCalendarField(".id", new RegularExpression("FeeSchdDetailsView(-\\d+)?\\.effectDate_ForDisplay|date_start_ForDisplay|date_effective_ForDisplay",false), startDate);
	}

	/**
	 *
	 * @return effective start date
	 */
	public String getEffectStartDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("FeeSchdDetailsView(-\\d+)?\\.effectDate_ForDisplay|date_start_ForDisplay|date_effective_ForDisplay",false));
	}

	/**
	 * Input effective end date
	 *
	 * @param endDate
	 */
	public void setEffectEndDate(String endDate) {
		browser.setCalendarField(".id", new RegularExpression("FeeSchdDetailsView(-\\d+)?\\.effectEndDate_ForDisplay|date_end_ForDisplay|date_effective_end_ForDisplay",false), endDate);
	}

	/**
	 * Check if Effective End Date text field exist.
	 * @return
	 */
	public boolean isEffectEndDateExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("FeeSchdDetailsView(-\\d+)?\\.effectEndDate_ForDisplay|date_end_ForDisplay|date_effective_end_ForDisplay",false));
	}
	/**
	 *
	 * @return effective end date
	 */
	public String getEffectEndDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("FeeSchdDetailsView(-\\d+)?\\.effectEndDate_ForDisplay|date_end_ForDisplay|date_effective_end_ForDisplay",false));
	}

	/**
	 * Fill in inventory start date.
	 *
	 * @param date
	 *            - start date
	 */
	public void setInvStartDate(String date) {
//		browser.setTextField(".id", "date_start_ForDisplay", date);
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_start_ForDisplay")[0], date);
	}

	/**
	 *
	 * @return Inventory Start Date
	 */
	public String getInvStartDate() {
		return browser.getTextFieldValue(".id", "date_start_ForDisplay");
	}

	/**
	 * Fill in inventory end date.
	 *
	 * @param date
	 *            -end date
	 */
	public void setInvEndDate(String date) {
//		browser.setTextField(".id", "date_end_ForDisplay", date);
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_end_ForDisplay")[0], date);
	}

	/**
	 *
	 * @return Inventory end date
	 */
	public String getInvEndDate() {
		return browser.getTextFieldValue(".id", "date_end_ForDisplay");
	}

	/**
	 * Select season.
	 *
	 * @param season
	 */
	public void selectSeason(String season) {
		browser.selectDropdownList(".id", "conditions_season", season);
	}

	/**
	 *
	 * @return selected season
	 */
	public String getSeason() {
		return browser.getDropdownListValue(".id", "conditions_season", 0);
	}
	/**
	 * set License Year.
	 *
	 * @param License Year
	 */
	public void selectLicenseYear(String licenseYear) {
		browser.selectDropdownList(".id", "license_year_from", licenseYear);
	}
	/**
	 *
	 * @return License Year
	 */
	public String getLicenseYear() {
		return browser.getDropdownListValue(".id", "license_year_from", 0);
	}
	/**
	 * Select sales channel.
	 *
	 * @param channel
	 *            - sales channel
	 */
	public void selectSaleChannel(String channel) {
		browser.selectDropdownList(".id", "conditions_channel", channel);
	}

	/**
	 *
	 * @return selected sales channel
	 */
	public String getSalesChannel() {
		return browser.getDropdownListValue(".id", "conditions_channel", 0);
	}
	
	public List<String> getSalesChannelList() {
		return browser.getDropdownElements(".id", "conditions_channel");
	}
	/**
	 * Select location class.
	 *
	 * @param channel
	 *            - sales channel
	 */
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", "location_class", locationClass);
	}

	/**
	 *
	 * @return selected location class
	 */
	public String getLocationClass() {
		return browser.getDropdownListValue(".id", "location_class", 0);
	}
	public List<String> getLocationClassList() {
		return browser.getDropdownElements(".id", "location_class");
	}

	/**
	 * Select state.
	 *
	 * @param state
	 */
	public void selectState(String state) {
		browser.selectDropdownList(".id", "conditions_in_out_state", state);
	}

	/**
	 *
	 * @return In/out state
	 */
	public String getState() {
		return browser
				.getDropdownListValue(".id", "conditions_in_out_state", 0);
	}

	public void setMinumuUnitOfStay(String days){
		browser.setTextField(".id","minunitdays", days);
	}
	
	public String getMinimumUnitOfStay(){
		return browser.getTextFieldValue(".id","minunitdays");
	}
	
	public void setMinimumNumOfDaysBeforArrivalDate(String days){
		browser.setTextField(".id","minunitbeforarrivaldays", days);
	}
	
	public String getMinimumNumOfDaysBeforeArrivalDate(){
		return browser.getTextFieldValue(".id","minunitbeforarrivaldays");
	}
	
	/**
	 * Select customer type.
	 *
	 * @param type
	 *            - customer type
	 */
	public void selectCustomerType(String type) {
		browser.selectDropdownList(".id", "conditions_customer_type", type);
	}

	/**
	 *
	 * @return selected customer type
	 */
	public String getCustomerType() {
		return browser.getDropdownListValue(".id", "conditions_customer_type",
				0);
	}
	public void selectTransactionType(String type){
		this.selectTransactionType(type, false);
	}
	
	/**
	 * Select transaction type.
	 *
	 * @param type
	 *            - transaction type
	 */
	public void selectTransactionType(String type, boolean isPopupDialog) {	
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
		
//		if(isPopupDialog){
//			new Thread() {
//				public void run() {            
//					ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
//					while (!confirm.exists()) {
//						
//					}
//					confirm.waitExists();
//				};
//			}.start();
//		}
		
		browser.selectDropdownList(".id", "transaction_type", type);	
		Object pg = browser.waitExists(confirm, this);
		if(pg==confirm){
			confirm.clickOK();
			this.waitLoading();
		}
		
	}
	private Property[] applicableTaxes(){
		return Property.toPropertyArray(".id",new RegularExpression("ActivityFeeScheduleView-\\d+\\.applicableTaxType_\\d+",false));
	}
	public void selectTax(String taxName){
		IHtmlObject [] taxLabel=browser.getHtmlObject(".className","trailing",".text",taxName);
		IHtmlObject [] taxCheckBoxes=browser.getCheckBox(applicableTaxes(),taxLabel[taxLabel.length-1].getParent());
		ICheckBox taxCheckBox=(ICheckBox)taxCheckBoxes[taxCheckBoxes.length-1];
		taxCheckBox.select();
		Browser.unregister(taxLabel);
		Browser.unregister(taxCheckBoxes);
	}
	/**
	 *
	 * @return selected transaction type
	 */
	public String getTransactionType() {
		return browser.getDropdownListValue(".id", "transaction_type", 0);
	}
	
	public List<String> getTransactionTypeList() {
		return browser.getDropdownElements(".id", "transaction_type");
	}

	/**
	 * Select transaction occurrence.
	 *
	 * @param type
	 *            - transaction occurance
	 */
	public void selectTransactionOccu(String type) {
		browser.selectDropdownList(".id", "transaction_occurance", type);
	}

	/**
	 *
	 * @return selected transaction occurence
	 */
	public String getTransactionOccu() {
		return browser.getDropdownListValue(".id", "transaction_occurance", 0);
	}

	public boolean isTransactionFeeExists() {
		return browser.checkHtmlObjectExists(".id", "fee");
	}
	
	public boolean isTransactionFeeEnabled() {
		return browser.checkHtmlObjectEnabled(".id", "fee");
	}
	
	/**
	 * Fill in transaction fee rate by given index.
	 *
	 * @param rate
	 *            - transaction fee rate
	 * @param index
	 *            - object index
	 * @return defaultValue - previous rate
	 */
	public String setTransactionFee(String rate, int index) {
//		IHtmlObject[] objs = browser.getTextField(".id", "fee");
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("fee|(TransactionFeeScheduleView-\\d+\\.tranRate:CURRENCY_INPUT)",false));

		
		String defaultValue = objs[index].getProperty(".value").toString();
//		IText text = (IText) objs[index];
//		text.setText(rate);
		browser.setTextField(".id", new RegularExpression("fee|(TransactionFeeScheduleView-\\d+\\.tranRate:CURRENCY_INPUT)",false), rate, index);
		Browser.unregister(objs);
		return defaultValue;
	}
	
	public boolean isTransactionFeeChangedUnitsExists() {
		return browser.checkHtmlObjectExists(".id", "feeChanged");
	}
	
	public boolean isTransactionFeeChangedUnitsEnabled() {
		return browser.checkHtmlObjectEnabled(".id", "feeChanged");
	}
	
	public void setTransactionFeeChangedUnits(String rate){
		browser.setTextField(".id", "feeChanged", rate);
	}
	
	public String getTransactionFeeChangedUnits() {
		return browser.getTextFieldValue(".id", "feeChanged");
	}
	
	public boolean isTransactionFeeFlatAmountExists() {
		return browser.checkHtmlObjectExists(".id", "feeFlatAmount");
	}
	
	public boolean isTransactionFeeFlatAmountEnabled() {
		return browser.checkHtmlObjectEnabled(".id", "feeFlatAmount");
	}
	
	public void setTransactionFeeFlatAmount(String rate){
		browser.setTextField(".id", "feeFlatAmount", rate);
	}
	
	public String getTransactionFeeFlatAmount() {
		return browser.getTextFieldValue(".id", "feeFlatAmount");
	}
	
	public void setTransactionFeeNumOfFreeChgPerRev(String num){
		browser.setTextField(".id", "numberOfFreeChangeTransactions", num);
	}

	public void setTransactionFee_To(String rate, int index) {
		browser.setTextField(".id", "to_fee", rate, index);
	}

	public String setTransactionFee(String rate) {
		return setTransactionFee(rate, 0);
	}

	/**
	 *
	 * @return Transaction Fee
	 */
	public String getTransactionFee() {
		return browser.getTextFieldValue(".id", "fee",".name","fee");
	}
	
	public String getTransactionFee(int index){
		return browser.getTextFieldValue(".id", "fee",".name","fee",index + 1);
	}
	
	public String getTransactionFee_To(int index){
		return browser.getTextFieldValue(".id", "to_fee",".name","to_fee",index + 1);
	}

	public void selectTransactionMethod(String option) {
		if (option.equalsIgnoreCase("Per Transaction")) {
			if (!this.checkTranFeeOptionExist("2")) {
				this.selectTransMethod("2");
			}
		} else if (option.equalsIgnoreCase("Per Unit")) {
			if (!this.checkTranFeeOptionExist("1")) {
				this.selectTransMethod("1");
			}
		} else if (option.equalsIgnoreCase("Flat by Range of Ticket Quantity")) {
			if (!this.checkTranFeeOptionExist("3")) {
				this.selectTransMethod("3");
			}
		}
	}
	
	/**
	 * Click on transaction apply method
	 *
	 * @param value
	 *            - object's value
	 */
	public void selectTransMethod(String value) {

		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		browser.selectRadioButton(".id", "envtype", ".value", value);
		browser.waitExists(confirmDialog,this);
		if (confirmDialog.exists()) {
			confirmDialog.dismiss();
		}
	}
	
	public int getTransMethodObjectSize(){
		IHtmlObject[] objs = browser.getRadioButton(".id", "envtype");
		int objsSize = objs.length;
		Browser.unregister(objs);
		return objsSize;
	}
	
	/**
	 *
	 * @return Transaction fee unit
	 */
	public String getTransMethod() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "envtype");
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("2"))
			return "Per Transaction";
		else if(value.equalsIgnoreCase("3"))
			return "Flat by Range of Ticket Quantity";
		else 
			return "Per Unit";
	}

	private boolean isRadioButtonsEditable(String id) {
		IHtmlObject[] radioObjs = browser.getRadioButton(".id", id);
		
		boolean editable = true;
		for(int i = 0; i < radioObjs.length; i ++) {
			editable &= radioObjs[i].isEnabled();
		}
		
		Browser.unregister(radioObjs);
		return editable;
	}
	
	public boolean isApplicableUnitsEnabled() {
		return isRadioButtonsEditable("applicableUnits");
	}
	
	public void selectApplicableUnit(String unit) {
		int index = unit.equalsIgnoreCase("All") ? 0 : 1;
		browser.selectRadioButton(".id", "applicableUnits", index);
	}
	
	public String getApplicableUnit() {
		IHtmlObject objs[] = browser.getRadioButton(".id", "applicableUnits");
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Applicable Units radio button object.");
		
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index == -1 ? null : (index == 0 ? "All" : "Exclude Units in Penalty Fees");
	}
	
	/**
	 * Set the rate
	 *
	 * @param rate
	 */
	public void setRate(String rate) {
		browser.setTextField(".id", "fee", rate);
	}

	/**
	 *
	 * @return Rate value
	 */
	public String getRate() {
		return browser.getTextFieldValue(".id", "fee");
	}
	
	public boolean checkRateIsExisting(int index){
		boolean isExisting = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "fee");
		if(objs.length<=index + 1){
			isExisting = false;
		}
		
		Browser.unregister(objs);
		return isExisting;
	}
	
	public boolean checkRateIsEnable(){
		return this.checkRateIsEnable(0);
	}
	
	public boolean checkRateIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", "fee");
		IHtmlObject text;
		if(objs.length>index+1){
			text = objs[index+1];
		}else{
			text = objs[index];
		}
		
		boolean isEnable = text.isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkToRateExisting(){
		return browser.checkHtmlObjectExists(".id", "to_fee");
	}
	
	public boolean checkToRateIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", "to_fee");
		IHtmlObject text;
		if(objs.length>index+1){
			text = objs[index+1];
		}else{
			text = objs[index];
		}
		
		boolean isEnable = text.isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	/**
	 * Select unit of stay.
	 *
	 * @param unit
	 *            - unit of stay
	 */
	public void selectUnitOfStay(String unit) {
		browser.selectDropdownList(".id", "unit_of_stay_type", unit);
	}

	/**
	 *
	 * @return selected unit of stay
	 */
	public String getUnitOfStay() {
		return browser.getDropdownListValue(".id", "unit_of_stay_type", 0);
	}

	/**
	 * Select attirbute type
	 *
	 * @param attrType
	 */
	public void selectAttrType(String attrType) {
		browser.selectDropdownList(".id", "attribute_type", attrType);
		waitLoading();
	}

	/**
	 *
	 * @return selected attribute type
	 */
	public String getAttrType() {
		return browser.getDropdownListValue(".id", "attribute_type", 0);
	}
	
	public List<String> getUnitOfStayElement() {
		return browser.getDropdownElements(".id", "unit_of_stay_type");
	}

	/**
	 * Select given attribute values
	 *
	 * @param values
	 */
	public void selectAttrValueCheckBox(String... values) {
	
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TR", ".text",new RegularExpression("Attribute Values.*",false));
		if(tbls.length<1)
		{
			Browser.unregister(tbls);
			logger.error("Cannot find attribute table on page...");

		}
		
		IHtmlObject[] checkboxs = browser.getCheckBox(Property.toPropertyArray(".id", new RegularExpression("row_\\d?_checkbox",false)), tbls[0]);
		
		for(IHtmlObject o: checkboxs)//clear all selected checkbox
		{
			ICheckBox chk = (ICheckBox)o;
			chk.deselect();			
		}	
		
		for (String value : values) {			
			
			for(IHtmlObject o: checkboxs)
			{
				String tmp = null;
				ICheckBox chk = (ICheckBox)o;
				tmp = o.getParent().text().trim();
				if(tmp.equalsIgnoreCase(value))
				{
					chk.select();
					break;
				}
		
			}	
		}
		
		Browser.unregister(tbls);
		Browser.unregister(checkboxs);
	}
	
	public List<String> getAttrValue()
	{
		List<String> attrs = new ArrayList<String>();
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", ".id","attr_value_check_bar");
		if(tbls.length<1)
		{
			Browser.unregister(tbls);
			logger.error("Cannot find attribute value table on page...");
			return attrs;
		}
		
		IHtmlObject[] checkboxs = browser.getCheckBox(Property.toPropertyArray(".id", new RegularExpression("row_\\d?_checkbox",false)), tbls[0]);
		
		for(IHtmlObject o: checkboxs)
		{
			String tmp = null;
			ICheckBox chk = (ICheckBox)o;
			if(chk.isSelected())
			{
				tmp = o.getParent().text().trim();
				attrs.add(tmp);
			}
		}
		Browser.unregister(tbls);
		Browser.unregister(checkboxs);
		
		return attrs;
		
	}
	
	public String getMarinaRateTypeOfDropDownList(){
		return browser.getDropdownListValue(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false));
	}
	
	public List<String> getMarinaRateTypeElementsOfDropDownList(){
		return browser.getDropdownElements(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false));
	}
	
	public String getMarinaRateTypeOfRadioButton(){
		IHtmlObject[] radios = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType", false));
		String type = null;
		
		if(radios.length<1){
			throw new ItemNotFoundException("Did not get any marina rate type radio button object.");
		}
		for(IHtmlObject o: radios)
		{
			IRadioButton rad= (IRadioButton)o; 
			if(rad.isSelected())
			{
				String id = rad.id();
				IHtmlObject[] labels = browser.getHtmlObject(".class","Html.LABEL",".for", id);	
				type = labels[0].text();
				Browser.unregister(labels);
				break;
			}
		}
		Browser.unregister(radios);

		return type;
	}
	
	public List<String> getMarinaRateTypeElementsOfRadioButton(){
		IHtmlObject[] radios = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType(\\d)?", false));
		if(radios.length<1){
			throw new ItemNotFoundException("Did not get Maria rate type radio button object.");
		}
		
		List<String> elements = new ArrayList<String>();
		for(IHtmlObject radio : radios){
			String id = radio.id();
			IHtmlObject[] labels = browser.getHtmlObject(".class","Html.LABEL",".for", id);
			if(labels.length<1){
				throw new ItemNotFoundException("Did not get Maria rate type label object.");
			}
			String value = labels[labels.length-1].text();
			elements.add(value);
			Browser.unregister(labels);
			Browser.unregister(radio);
		}
		Browser.unregister(radios);
		
		return elements;
	}
	
	public String getRafting()
	{
		return browser.getDropdownListValue(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.raftingType", false));
	}
	
	public List<String> getRaftingElements(){
		return browser.getDropdownElements(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.raftingType", false));
	}

	
	/**
	 * Select account code.
	 *
	 * @param code
	 *            - account code
	 */
	public void selectAccountCode(String code) {
//		browser.selectDropdownList(".id", "account_code", code);
		browser.selectDropdownList(".id", new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)",false), code);
	}
	
	public void selectAccountCode(int index) {
//		int size = browser.getDropdownElements(".id", "account_code").size();
		int size = browser.getDropdownElements(".id", new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)",false)).size();

		if(size>1){
//			browser.selectDropdownList(".id", "account_code", index);
			browser.selectDropdownList(".id", new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)",false), index);
			
		}
	}


	/**
	 *
	 * @return selected account code
	 */
	public String getAccountCode(int index) {
//		return browser.getDropdownElements(".id", new RegularExpression("account_code",false)).get(index);

			return browser.getDropdownElements(".id", new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)",false)).get(index);
	}

	/**
	 *
	 * @return selected account code
	 */
	public String getAccountCode() {
//		return browser.getDropdownListValue(".id", "account_code", 0);
		return browser.getDropdownListValue(".id", new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)",false), 0);

	}

	/**
	 * Select ticket category
	 *
	 * @param type
	 *            - category
	 */
	public void selectTicketCategory(String type) {
		if(type.matches("\\d+")){
			browser.selectDropdownList(".id", "ticket_cat_id", Integer.parseInt(type));
		}else{
			browser.selectDropdownList(".id", "ticket_cat_id", type);
		}
		waitLoading();
	}

	/**
	 *
	 * @return selected ticket category
	 */
	public String getTicketCategory() {
		return browser.getDropdownListValue(".id", "ticket_cat_id", 0);
	}

	/**
	 * Select ticket type
	 *
	 * @param type
	 */
	public void selectTicketType(String type) {
		browser.selectDropdownList(".id", "ticket_type", type.trim(), 1);
	}

	/**
	 * Select the ticket type
	 *
	 * @param type
	 * @param index
	 */
	public void selectTicketType(String type, int index) {
		IHtmlObject[] objs = browser.getDropdownList(".id", "ticket_type");
		((ISelect) objs[index]).select(type);
		Browser.unregister(objs);
	}

	/**
	 * Click the link to add ticket type
	 */
	public void clickAddTicketType() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Add Ticket Type", false));
	}
	
	public void clickRemoveTicketType(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Ticket Type", index+1);
	}
	
	public void clickRemoveRange(int index){
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Range",index+1);
	}
	
	public void clickRemoveRangeOfFee(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Range", true, 0, rangeSections[index]);
		Browser.unregister(rangeSections);
	}
	
	public int getRemoveRangeOfFeeObjectLength(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text","Remove Range",rangeSections[index]);
		int length = objs.length;
		
		Browser.unregister(objs);
		Browser.unregister(rangeSections);
		return length;
	}
	
	public void clickRemoveCustomDuration(int index){
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Custom Duration",index);
	}

	/**
	 *
	 * @return selected ticket type
	 */
	public String getTicketType() {
		return browser.getDropdownListValue(".id", "ticket_type", 1);
	}
	
	public String getTicketType(int index){
		return browser.getDropdownListValue(".id", "ticket_type", index + 1);
	}
	
	public String getTicketTypeForFlatBy(int index){
		return browser.getDropdownListValue(".id", "3_2_ticket_typedisabled",index);
	}
	
	public List<String> getTicketTypeListElements(){
		IHtmlObject[] objs = browser.getDropdownList(".id", "ticket_type");
		List<String> elements = ((ISelect) objs[objs.length-1]).getAllOptions();
		Browser.unregister(objs);
		return elements;
	}
	
	public List<String> getTicketTypeListElementsForFlatBy(){
		IHtmlObject[] objs = browser.getDropdownList(".id", "3_2_ticket_typedisabled");
		List<String> elements = ((ISelect) objs[objs.length-1]).getAllOptions();
		Browser.unregister(objs);
		return elements;
	}

	/**
	 * Select rate type radio button by given value
	 *
	 * @param value
	 *            :1:Family 2:Group
	 */
	public void selectRateType(String value) {
		browser.selectRadioButton(".id", "rate_type_id", ".value", value);
		waitLoading();
	}

	/** get rate type */
	public String getRateType() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "rate_type_id");
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("2"))
			return "Group";
		else
			return "Regular";
	}

	public void selectTicketUnit(String value) {
		browser.selectRadioButton(".id", "envtype", ".value", value);
		waitLoading();
	}

	/**
	 *
	 * @return checked ticket unit
	 */
	public String getTicketUnit() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "envtype");
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("3"))
			return "Flat by Range of Ticket Quantity";
		else
			return "Per Ticket";
	}

	/**
	 * Fill in daily rate by given index.
	 *
	 * @param rate
	 *            - daily rate
	 * @param index
	 *            - object index
	 * @return previous rate
	 */
	public String setDailyRate(String rate, int index) {
		IHtmlObject[] objs = browser.getTextField(".id", "duration_daily");

		String defaultValue = objs[index].getProperty(".value").toString();

		IText text = (IText) objs[index];
		text.setText(rate);

		Browser.unregister(objs);
		return defaultValue;
	}

	/** set daily rate */
	public String setDailyRate(String rate) {
		return setDailyRate(rate, 0);
	}

	/**
	 * Fill in weekly rate.
	 *
	 * @param rate
	 *            - weekly rate
	 */
	public void setWeeklyRate(String rate) {
		browser.setTextField(".id", "duration_weekly", rate);
	}

	/**
	 *
	 * @return weekly fee rate
	 */
	public String getWeeklyRate() {
		return browser.getTextFieldValue(".id", "duration_weekly");
	}

	/**
	 * Fill in monthly rate.
	 *
	 * @param rate
	 *            - monthly rate
	 */
	public void setMonthlyRate(String rate) {
		browser.setTextField(".id", "duration_monthly", rate);
	}

	/**
	 *
	 * @return monthly fee rate
	 */
	public String getMonthlyRate() {
		return browser.getTextFieldValue(".id", "duration_monthly");
	}

	// Fee by customer duration
	/**
	 * Fill in customer unit quantity.
	 */
	public void setCustomUnitQuantity(String quantity) {
		browser.setTextField(".id", "custom_duration_quantity", quantity);
	}

	/**
	 *
	 * @return customer unit quantity
	 */
	public String getCustUnitQty() {
		return browser.getTextFieldValue(".id", "custom_duration_quantity");
	}

	/**
	 * Fill ni customer rate.
	 *
	 * @param rate
	 */
	public void setCustomRate(String rate) {
		browser.setTextField(".id", "custom_duration_rate", rate);
	}

	/**
	 *
	 * @return customer rate
	 */
	public String getCustomRate() {
		return browser.getTextFieldValue(".id", "custom_duration_rate");
	}

	// Fee by day of week
	/**
	 * Fill in monday rate by given index.
	 *
	 * @param rate
	 *            - mon rate
	 */
	public void setMonRate(String rate, int index) {
		browser.setTextField(".id", "day_mon", rate, index);
	}

	/**
	 *
	 * @return monday rate
	 */
	public String getMonRate(int index) {
		return getTextFieldValue(".id", "day_mon", index);
	}

	/**
	 * Set monday's rate
	 *
	 * @param rate
	 */
	public void setMonRate(String rate) {
		setMonRate(rate, 0);
	}

	/**
	 * Fill in tue rate by given index.
	 *
	 * @param rate
	 */
	public void setTueRate(String rate, int index) {
		browser.setTextField(".id", "day_tue", rate, index);
	}

	/**
	 * set tuesday rate
	 *
	 * @param rate
	 */
	public void setTueRate(String rate) {
		setTueRate(rate, 0);
	}

	/**
	 *
	 * @return Tuesday rate
	 */
	public String getTueRate(int index) {
		return getTextFieldValue(".id", "day_tue", index);
	}

	/**
	 * Fill in wed rate by given index.
	 *
	 * @param rate
	 */
	public void setWedRate(String rate, int index) {
		browser.setTextField(".id", "day_wed", rate, index);
	}

	/**
	 * set Wednesday rate
	 *
	 * @param rate
	 */
	public void setWedRate(String rate) {
		setWedRate(rate, 0);
	}

	/**
	 *
	 * @return wednesday rate
	 */
	public String getWedRate(int index) {
		return getTextFieldValue(".id", "day_wed", index);
	}

	/**
	 * Fill in thu rate by given index.
	 *
	 * @param rate
	 */
	public void setThuRate(String rate, int index) {
		browser.setTextField(".id", "day_thu", rate, index);
	}

	/**
	 * set Thursday rate
	 *
	 * @param rate
	 */
	public void setThuRate(String rate) {
		setThuRate(rate, 0);
	}

	/**
	 * get Thursday rate
	 *
	 * @return Thusday rate
	 */
	public String getThuRate(int index) {
		return getTextFieldValue(".id", "day_thu", index);
	}

	/**
	 * Fill in fri rate by given index.
	 *
	 * @param rate
	 */
	public void setFriRate(String rate, int index) {
		browser.setTextField(".id", "day_fri", rate, index);
	}

	/**
	 * Set the friday rate
	 *
	 * @param rate
	 */
	public void setFriRate(String rate) {
		setFriRate(rate, 0);
	}

	/**
	 *
	 * @return Friday rate value
	 */
	public String getFriRate(int index) {
		return getTextFieldValue(".id", "day_fri", index);
	}

	/**
	 * Fill in sat rate by given index.
	 *
	 * @param rate
	 */
	public void setSatRate(String rate, int index) {
		browser.setTextField(".id", "day_sat", rate, index);
	}

	/**
	 * set saturday rate
	 *
	 * @param rate
	 */
	public void setSatRate(String rate) {
		setSatRate(rate, 0);
	}

	/**
	 *
	 * @return saturday rate value
	 */
	public String getSatRate(int index) {
		return getTextFieldValue(".id", "day_sat", index);
	}

	/**
	 * Fill in sunday rate by given index.
	 *
	 * @param rate
	 */
	public void setSunRate(String rate, int index) {
		browser.setTextField(".id", "day_sun", rate, index);
	}

	/**
	 * set sunday rate
	 *
	 * @param rate
	 */
	public void setSunRate(String rate) {
		setSunRate(rate, 0);
	}

	/**
	 * get sunday rate
	 *
	 * @return sunday rate value
	 */
	public String getSunRate(int index) {
		return getTextFieldValue(".id", "day_sun", index);
	}

	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK" , true);
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/** Click on Apply link. */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply" , true);
	}

	/**
	 * Retrieve the default daily rate.
	 *
	 * @return - default rate
	 */
	public String getDailyRate(int index) {
		return getTextFieldValue(".id", "duration_daily", index);
	}

	/**
	 * select permit category from drop down list
	 *
	 * @param permitCategory
	 */
	public void selectPermitCategory(String permitCategory) {
		browser.selectDropdownList(".id", "ticket_cat_id", permitCategory);
	}

	/**
	 * select permit type from drop down list
	 *
	 * @param permitType
	 */
	public void selectPermitType(String permitType) {
		browser.selectDropdownList(".id", "permit_type_id", permitType);
	}

	/**
	 * set any Unit Rate
	 *
	 * @param anyUnitRate
	 */
	public void setAnyUnitRate(String anyUnitRate) {
		browser.setTextField(".id", "duration_daily", anyUnitRate);
	}

	/**
	 * select full stay required for multi-unit rate
	 */
	public void selectMultiUnit() {
		browser.selectCheckBox(".id", "multi_unit_rate");
	}
	
	public void unselectMultiUnit() {
		browser.unSelectCheckBox(".id", "multi_unit_rate");
	}
	
	public boolean isMultiUnitCheckBoxExisted() {
		return browser.checkHtmlObjectExists(".id", "multi_unit_rate");
	}

	public boolean isMultiUnitCheckBoxSelected() {
		return browser.isCheckBoxSelected(".id", "multi_unit_rate");
	}
	/**
	 * This method use index to identify a unique text field
	 *
	 * @param propertyKey
	 * @param value
	 * @param index
	 * @return text field value
	 */
	public String getTextFieldValue(String propertyKey, Object value, int index) {
		String toReturn = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				propertyKey, value);
		if (objs.length > index)
			toReturn = objs[index].getProperty(".value").toString();
		else
			throw new ItemNotFoundException("Failed to find Object " + index);
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 *
	 * @param fee
	 * @param isGroupRate
	 *            - is fee rate type is group or family
	 * @param isOccupantsRanges
	 *            - is Fees for Additional Occupants is increments or ranges
	 * @param isVehiclesRanges
	 *            - is Fees for Additional Vehicles is increments or ranges
	 * @return
	 */
	public String setupAttributeFee(FeeScheduleData fee) {
		
		this.fillAttributeFee(fee);

		this.clickApply();
		this.waitLoading();

		String feeID = this.getFeeSchID(); // get fee schedule id when create
		// new
		this.clickOK();

		return feeID;
	}
	
	public void fillAttributeFee(FeeScheduleData fee)
	{
		if("Slip".equalsIgnoreCase(fee.productCategory)){
			
			if (null != fee.dock && fee.dock.length() > 0) {
				this.selectDock(fee.dock);
				this.waitLoading();
			}
		}else{
			if (null != fee.loop && fee.loop.length() > 0) {
				this.selectAssignLoop(fee.loop);
				this.waitLoading();
			}	
		}
		
		if (null != fee.productGroup && fee.productGroup.length() > 0) {
			this.selectAssignProdGroup(fee.productGroup);
			this.waitLoading();
		}
		if (null != fee.product && fee.product.length() > 0) {
			this.selectAssignProduct(fee.product);
			this.waitLoading();
		}
		if (null != fee.effectDate && fee.effectDate.length() > 0) {
			this.setEffectiveDate(fee.effectDate.trim());
//			this.refresh();
		}
		if (null != fee.startInv && fee.startInv.length() > 0) {
			this.setInvStartDate(fee.startInv.trim());
//			this.refresh();
		}
		if (null != fee.endInv && fee.endInv.length() > 0) {

			this.setInvEndDate(fee.endInv.trim());
//			this.refresh();
		}
		if (null != fee.season && fee.season.length() > 0) {
			this.selectSeason(fee.season);
		}
		if (null != fee.salesChannel && fee.salesChannel.length() > 0) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if (null != fee.state && fee.state.length() > 0) {
			this.selectState(fee.state);
		}
		if (null != fee.custType && fee.custType.length() > 0) {
			this.selectCustomerType(fee.custType);
		}
		if (!StringUtil.isEmpty(fee.boatCategory)) {
			this.selectBoatCategory(fee.boatCategory);
		}
		
		if("Slip".equalsIgnoreCase(fee.productCategory)) {
			/*adding for slip of marina------------------------------->>*/
			if(!StringUtil.isEmpty(fee.boatCategory)) {
				this.selectBoatCategory(fee.boatCategory);
			}
		}
		
		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
		}else {
			this.selectAccountCode(1);
		}
	
		if (null != fee.attrType && fee.attrType.length() > 0) {
			this.selectAttrType(fee.attrType);
			ajax.waitLoading();
			this.waitLoading();
		}
		if (null != fee.attrValue && fee.attrValue.length() > 0) {
			this.selectAttrValueCheckBox(fee.attrValue);
		} else if(fee.attrValues != null && fee.attrValues.length > 0) {
			this.selectAttrValueCheckBox(fee.attrValues);
		}
		
		if("Slip".equalsIgnoreCase(fee.productCategory)){
			/*adding for slip of marina------------------------------->>*/
			if(!StringUtil.isEmpty(fee.marinaRateType)) {
				this.selectMarinaRateType(fee.marinaRateType);
				ajax.waitLoading();
				
			}
		}
		
		if (null != fee.unitOfStay && fee.unitOfStay.length() > 0) {
			this.selectUnitOfStay(fee.unitOfStay);
		}
		
		if("Slip".equalsIgnoreCase(fee.productCategory)) {
			/*adding for slip of marina------------------------------->>*/
			if(!StringUtil.isEmpty(fee.rafting)) {
				this.selectRafting(fee.rafting);
			}
			
			/*adding for slip of marina------------------------------->>*/
			if(!StringUtil.isEmpty(fee.slipPricingUnit)) {
				this.selectUnit(fee.slipPricingUnit);
				ajax.waitLoading();
			}
			
			if(fee.slipFees.size()>0) {
				this.setSlipFees(fee.slipFees,fee.slipPricingUnit);
			}
			
			/*adding for slip of marina------------------------------->>*/
			if(!StringUtil.isEmpty(fee.calculationMethod))
			{
				this.selectCalculationMethod(fee.calculationMethod);
				ajax.waitLoading();
			}
			
			if(fee.pricingBasedonArrival.size()>0)
			{
				this.addPricingBaseOn("Arrival",fee.pricingBasedonArrival);
			}
			
			if(fee.pricingBasedonDeparture.size()>0)
			{
				this.addPricingBaseOn("Departure",fee.pricingBasedonDeparture);
			}
		}
		
		// about use fee of permit
		if (null != fee.permitCategory && fee.permitCategory.length() > 0) {
			this.selectPermitCategory(fee.permitCategory);
		}
		if (null != fee.permitType && fee.permitType.length() > 0) {
			this.selectPermitType(fee.permitType);
		}
		// about Ticket Category of ticket
		if (null != fee.ticketCategory && fee.ticketCategory.length() > 0) {
			this.selectTicketCategory(fee.ticketCategory);
		}
		if (fee.isGroupRate) {
			// base fee
			this.selectRateType("2");// select Group radio
			if (null != fee.anyUnitRate && fee.anyUnitRate.length() > 0) {
				this.setAnyUnitRate(fee.anyUnitRate);
			}
			if (null != fee.monRate && fee.monRate.length() > 0) {
				this.setMonRate(fee.monRate);
			}
			if (null != fee.tuesRate && fee.tuesRate.length() > 0) {
				this.setTueRate(fee.tuesRate);
			}
			if (null != fee.wedRate && fee.wedRate.length() > 0) {
				this.setWedRate(fee.wedRate);
			}
			if (null != fee.thurRate && fee.thurRate.length() > 0) {
				this.setThuRate(fee.thurRate);
			}
			if (null != fee.friRate && fee.friRate.length() > 0) {
				this.setFriRate(fee.friRate);
			}
			if (null != fee.satRate && fee.satRate.length() > 0) {
				this.setSatRate(fee.satRate);
			}
			if (null != fee.sunRate && fee.sunRate.length() > 0) {
				this.setSunRate(fee.sunRate);
			}
			if (fee.isOccupantsRanges) {
				// select Ranges radio about "Fees for Additional Occupants"
				this.selectOccRadio("2");
				String prefix = "1_2";
				if (null != fee.rangesOccRate && fee.rangesOccRate.length() > 0) {
					this.setIncrementRate((String) fee.rangesOccRate, prefix);
				}
				if (null != fee.anyUnitRangsOccRate
						&& fee.anyUnitRangsOccRate.length() > 0) {
					this.setBaseIncrementRate(fee.anyUnitRangsOccRate, prefix);
				}
				if (null != fee.monRangesOccRate
						&& fee.monRangesOccRate.length() > 0) {
					this.setMonIncrementRate(fee.monRangesOccRate, prefix);
				}
				if (null != fee.tueRangesOccRate
						&& fee.tueRangesOccRate.length() > 0) {
					this.setTueIncrementRate(fee.tueRangesOccRate, prefix);
				}
				if (null != fee.wedRangesOccRate
						&& fee.wedRangesOccRate.length() > 0) {
					this.setWedIncrementRate(fee.wedRangesOccRate, prefix);
				}
				if (null != fee.thuRangesOccRate
						&& fee.thuRangesOccRate.length() > 0) {
					this.setThuIncrementRate(fee.thuRangesOccRate, prefix);
				}
				if (null != fee.friRangesOccRate
						&& fee.friRangesOccRate.length() > 0) {
					this.setFriIncrementRate(fee.friRangesOccRate, prefix);
				}
				if (null != fee.satRangesOccRate
						&& fee.satRangesOccRate.length() > 0) {
					this.setSatIncrementRate(fee.satRangesOccRate, prefix);
				}
				if (null != fee.sunRangesOccRate
						&& fee.sunRangesOccRate.length() > 0) {
					this.setSunIncrementRate(fee.sunRangesOccRate, prefix);
				}
			} else {
				// select Increments radio about "Fees for Additional Occupants"
				this.selectOccRadio("1");
				String prefix = "1_1";
				if (null != fee.incrementsOccRate
						&& fee.incrementsOccRate.length() > 0) {
					this.setIncrementRate(fee.incrementsOccRate, prefix);
				}
				if (null != fee.anyUnitIncrementsOccRate
						&& fee.anyUnitIncrementsOccRate.length() > 0) {
					this.setBaseIncrementRate(fee.anyUnitIncrementsOccRate,
							prefix);
				}
				if (null != fee.monIncrementsOccRate
						&& fee.monIncrementsOccRate.length() > 0) {
					this.setMonIncrementRate(fee.monIncrementsOccRate, prefix);
				}
				if (null != fee.tueIncrementsOccRate
						&& fee.tueIncrementsOccRate.length() > 0) {
					this.setTueIncrementRate(fee.tueIncrementsOccRate, prefix);
				}
				if (null != fee.wedIncrementsOccRate
						&& fee.wedIncrementsOccRate.length() > 0) {
					this.setWedIncrementRate(fee.wedIncrementsOccRate, prefix);
				}
				if (null != fee.thuIncrementsOccRate
						&& fee.thuIncrementsOccRate.length() > 0) {
					this.setThuIncrementRate(fee.thuIncrementsOccRate, prefix);
				}
				if (null != fee.friIncrementsOccRate
						&& fee.friIncrementsOccRate.length() > 0) {
					this.setFriIncrementRate(fee.friIncrementsOccRate, prefix);
				}
				if (null != fee.satIncrementsOccRate
						&& fee.satIncrementsOccRate.length() > 0) {
					this.setSatIncrementRate(fee.satIncrementsOccRate, prefix);
				}
				if (null != fee.sunIncrementsOccRate
						&& fee.sunIncrementsOccRate.length() > 0) {
					this.setSunIncrementRate(fee.sunIncrementsOccRate, prefix);
				}
			}
			if (fee.isVehiclesRanges) {
				// select Ranges radio about "Fees for Additional Vehicles"
				this.selectVehRadio("2");
				String prefix = "2_2";
				if (null != fee.rangesVehRate && fee.rangesVehRate.length() > 0) {
					this.setIncrementRate(fee.rangesVehRate, prefix);
				}
				if (null != fee.anyUnitRangesVehRate
						&& fee.anyUnitRangesVehRate.length() > 0) {
					this.setBaseIncrementRate(fee.anyUnitRangesVehRate, prefix);
				}
				if (null != fee.monRangesVehRate
						&& fee.monRangesVehRate.length() > 0) {
					this.setMonIncrementRate(fee.monRangesVehRate, prefix);
				}
				if (null != fee.tueRangesVehRate
						&& fee.tueRangesVehRate.length() > 0) {
					this.setTueIncrementRate(fee.tueRangesVehRate, prefix);
				}
				if (null != fee.wedRangesVehRate
						&& fee.wedRangesVehRate.length() > 0) {
					this.setWedIncrementRate(fee.wedRangesVehRate, prefix);
				}
				if (null != fee.thuRangesVehRate
						&& fee.thuRangesVehRate.length() > 0) {
					this.setThuIncrementRate(fee.thuRangesVehRate, prefix);
				}
				if (null != fee.friRangesVehRate
						&& fee.friRangesVehRate.length() > 0) {
					this.setFriIncrementRate(fee.friRangesVehRate, prefix);
				}
				if (null != fee.satRangesVehRate
						&& fee.satRangesVehRate.length() > 0) {
					this.setSatIncrementRate(fee.satRangesVehRate, prefix);
				}
				if (null != fee.sunRangesVehRate
						&& fee.sunRangesVehRate.length() > 0) {
					this.setSunIncrementRate(fee.sunRangesVehRate, prefix);
				}
			} else {
				// select Increments radio about "Fees for Additional Vehicles"
				this.selectVehRadio("1");
				String prefix = "2_1";
				if (null != fee.incrementsVehRate
						&& fee.incrementsVehRate.length() > 0) {
					this.setIncrementRate(fee.incrementsVehRate, prefix);
				}
				if (null != fee.anyUnitIncrementsVehRate
						&& fee.anyUnitIncrementsVehRate.length() > 0) {
					this.setBaseIncrementRate(fee.anyUnitIncrementsVehRate,
							prefix);
				}
				if (null != fee.monIncrementsVehRate
						&& fee.monIncrementsVehRate.length() > 0) {
					this.setMonIncrementRate(fee.monIncrementsVehRate, prefix);
				}
				if (null != fee.tueIncrementsVehRate
						&& fee.tueIncrementsVehRate.length() > 0) {
					this.setTueIncrementRate(fee.tueIncrementsVehRate, prefix);
				}
				if (null != fee.wedIncrementsVehRate
						&& fee.wedIncrementsVehRate.length() > 0) {
					this.setWedIncrementRate(fee.wedIncrementsVehRate, prefix);
				}
				if (null != fee.thuIncrementsVehRate
						&& fee.thuIncrementsVehRate.length() > 0) {
					this.setThuIncrementRate(fee.thuIncrementsVehRate, prefix);
				}
				if (null != fee.friIncrementsVehRate
						&& fee.friIncrementsVehRate.length() > 0) {
					this.setFriIncrementRate(fee.friIncrementsVehRate, prefix);
				}
				if (null != fee.satIncrementsVehRate
						&& fee.satIncrementsVehRate.length() > 0) {
					this.setSatIncrementRate(fee.satIncrementsVehRate, prefix);
				}
				if (null != fee.sunIncrementsVehRate
						&& fee.sunIncrementsVehRate.length() > 0) {
					this.setSunIncrementRate(fee.sunIncrementsVehRate, prefix);
				}

			}
		} else {
			// fee by duration
			if (null != fee.nightlyRate && fee.nightlyRate.length() > 0) {
				this.setDailyRate(fee.nightlyRate);
			}
			if (null != fee.weeklyRate && fee.weeklyRate.length() > 0) {
				this.setWeeklyRate(fee.weeklyRate);
			}
			if (null != fee.monthlyRate && fee.monthlyRate.length() > 0) {
				this.setMonthlyRate(fee.monthlyRate);
			}
			if (fee.isFullStayMultiunit) {
				this.selectMultiUnit();
			}

			// fee by customer duration
			if (null != fee.unitQuantity && fee.unitQuantity.length() > 0) {
				this.setCustomUnitQuantity(fee.unitQuantity);
			}
			if (null != fee.custRate && fee.custRate.length() > 0) {
				this.setCustomRate(fee.custRate);
			}
			// fee by day of week
			if (null != fee.monRate && fee.monRate.length() > 0) {
				this.setMonRate(fee.monRate);
			}
			if (null != fee.tuesRate && fee.tuesRate.length() > 0) {
				this.setTueRate(fee.tuesRate);
			}
			if (null != fee.wedRate && fee.wedRate.length() > 0) {
				this.setWedRate(fee.wedRate);
			}
			if (null != fee.thurRate && fee.thurRate.length() > 0) {
				this.setThuRate(fee.thurRate);
			}
			if (null != fee.friRate && fee.friRate.length() > 0) {
				this.setFriRate(fee.friRate);
			}
			if (null != fee.satRate && fee.satRate.length() > 0) {
				this.setSatRate(fee.satRate);
			}
			if (null != fee.sunRate && fee.sunRate.length() > 0) {
				this.setSunRate(fee.sunRate);
			}
		}
	}

	public void removeAllPricingBasedOn(String type)
	{
		IHtmlObject[] buttons = this.getRemovePercentButtons(type);
		
		int size = buttons.length;
		
		for(int i=0;i<size;i++)
		{
			this.removePricingBasedOn(type, 0);
		}
		
		Browser.unregister(buttons);
		
	}
	public void removePricingBasedOn(String type, int index)
	{
		OrmsConfirmDialogWidget alert =OrmsConfirmDialogWidget.getInstance();
			
		IHtmlObject[] buttons = this.getRemovePercentButtons(type);
			 
		int size = buttons.length;
		if((size<1)||(index>(size-1)))
		{
			throw new ErrorOnPageException("Cannot find button-->"+type);
		}
		buttons[index].click();
		ajax.waitLoading();
		alert.waitLoading();
		alert.clickOK();
		ajax.waitLoading();
		this.waitLoading();
		
		Browser.unregister(buttons);
		
	}
	
	public IHtmlObject[] getRemovePercentButtons(String type)
	{
		IHtmlObject tr = this.getPricingBasedOnSection(type);
		
		IHtmlObject[] buttons = null;
		String text = null;
		
		if(type.equalsIgnoreCase("Arrival"))
		{
			text = "Remove Arrival Percent";
			
		}else if(type.equalsIgnoreCase("Departure"))
		{
			text = "Remove Departure Percent";
		}
		buttons = browser.getHtmlObject(".class", "Html.A", ".text", text, tr);		
		return buttons;
	}
	
	
	public List<PricingBase> getPricingBasedOn(String type)
	{

		IHtmlObject tr = this.getPricingBasedOnSection(type);
		
		List<PricingBase> prices = new ArrayList<PricingBase>();
		
		IHtmlObject[] fields = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.percent",false)),tr);
		int size = fields.length;
		if(size<1)
		{
			logger.error("Cannot find any Pricing Based on "+type);
			return prices;
		}
	
		for(int i=0; i<size; i++)
		{
			PricingBase p = new PricingBase();
			p.startMonth = this.getStartMonth(i, tr);
			p.startDate = this.getStartDay(i, tr);
			p.endMonth = this.getEndMonth(i, tr);
			p.endDate = this.getEndDay(i, tr);
			p.percent = this.getPricingPercent(i,tr);
			prices.add(p);			
		}
		
		Browser.unregister(tr);
		return prices;	
		
	}
	
	public IHtmlObject getPricingBasedOnSection(String type)
	{
		IHtmlObject[] trs = null;
		if(type.equalsIgnoreCase("Arrival"))
		{
			trs = browser.getHtmlObject(".class", "Html.TR", ".id", "arrival_pricing_bar");
		}else if(type.equalsIgnoreCase("Departure"))
		{
			trs = browser.getHtmlObject(".class", "Html.TR", ".id", "departure_pricing_bar");
		}
		
		if(trs.length<0)
		{
			Browser.unregister(trs);
			throw new ErrorOnPageException("Cannot find cell for Pricing Based on "+type);
		}
		return trs[0];
	}
	
	
	/**
	 * @param pricing based on type
	 * @param pricingBasedonArrival
	 */
	private void addPricingBaseOn(String type,
			List<PricingBase> pricingBasedonArrival) {
				
		int size = pricingBasedonArrival.size();
		for (int i=0; i<size;i++)
		{
			if(type.equalsIgnoreCase("Arrival"))
			{	
				this.clickAddArrivalPercent();
			}else if(type.equalsIgnoreCase("Departure"))
			{
				this.clickAddDeparturePercent();
			}
		
			ajax.waitLoading();
			this.waitLoading();
		}
		
		this.setPricingBasePercentInfo(type, pricingBasedonArrival);
	}
	
	public void setPricingBasePercentInfo(String type, List<PricingBase> pricingBasedonArrival){
		IHtmlObject[] trs = null;
		if(type.equalsIgnoreCase("Arrival")){
			trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Arrival Start.*", false));
		}else if(type.equalsIgnoreCase("Departure")){
			trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Departure Start.*", false));
		}
		
		if(trs.length<0){
			Browser.unregister(trs);
			throw new ErrorOnPageException("Cannot find cell for Pricing Based on "+type);
		}
		for(int j=0; j<pricingBasedonArrival.size();j++)
		{
			PricingBase pricing = pricingBasedonArrival.get(j);
			if(!StringUtil.isEmpty(pricing.startMonth))
			{
				this.selectStartMonth(pricing.startMonth, 0, trs[j]);
			}
			
			if(!StringUtil.isEmpty(pricing.startDate))
			{
				this.selectStartDay(pricing.startDate, 0, trs[j]);
			}
			
			if(!StringUtil.isEmpty(pricing.endMonth))
			{
				this.selectEndMonth(pricing.endMonth, 0, trs[j]);
			}
			
			if(!StringUtil.isEmpty(pricing.endDate))
			{
				this.selectEndDay(pricing.endDate, 0, trs[j]);
			}
			
			if(!StringUtil.isEmpty(pricing.percent))
			{
				this.setPricingPercent(pricing.percent, 0, trs[j]);
			}
			
		}
		Browser.unregister(trs);
	}
	
	public void addOneMorePricingBaseonArrivalItem(String type){
		if(type.equalsIgnoreCase("Arrival"))
		{	
			this.clickAddArrivalPercent();
		}else if(type.equalsIgnoreCase("Departure"))
		{
			this.clickAddDeparturePercent();
		}
	
		ajax.waitLoading();
		this.waitLoading();
	}

	public void selectStartMonth(String month, int index, IHtmlObject top)
	{
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth",false)), month, true, index, top);
	}
	
	public String getStartMonth(int index, IHtmlObject top)
	{
		return browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth",false)), index, top);
	}
	
	public void selectStartDay(String day, int index, IHtmlObject top)
	{	
		if(day.equalsIgnoreCase("Day")){// select 'Day'
			browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false)), 0, index, true, top);
		} else {
			browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false)), day, true, index, top);
		}
	}
	
	public String getStartDay(int index, IHtmlObject top)
	{
		return browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false)), index, top);
	}
	

	public void selectEndMonth(String month, int index, IHtmlObject top)
	{
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateMonth",false)), month, true, index, top);
	}
	
	public String getEndMonth(int index, IHtmlObject top)
	{
		return browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateMonth",false)), index, top);
	}
	
	public void selectEndDay(String day, int index, IHtmlObject top)
	{
		if(day.equalsIgnoreCase("Day")){// select 'Day'
			browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateDay",false)), 0, index, true, top);
		} else {
			browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateDay",false)), day, true, index, top);
		}
	}
	
	public String getEndDay(int index, IHtmlObject top)
	{
		return browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateDay",false)), index, top);
	}
	
	public void setPricingPercent(String value, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.percent",false)), value, true, index, top);
	}
	
	public String getPricingPercent(int index, IHtmlObject top)
	{
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.percent",false)), index, top);
	}
	
	public void clickAddArrivalPercent()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Arrival Percent",false));
	}
	
	public void clickAddDeparturePercent()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Departure Percent",false));
	}
	
	public String setupPosFeeSchedule(FeeScheduleData fee) {
		if (null != fee.productGroup && fee.productGroup.length() > 0) {
			this.selectAssignProdGroup(fee.productGroup);
		}
		if (null != fee.product && fee.product.length() > 0) {
			//add a new method for select pos product with auto-completed box
			this.selectAssignProduct(fee.product);
		}
		if (null != fee.fromDate && fee.fromDate.length() > 0) {
			this.setEffectStartDate(fee.fromDate);
		}
		if (null != fee.toDate && fee.toDate.length() > 0 && this.isEffectEndDateExist()) {
			this.setEffectEndDate(fee.toDate);
		}
		if (null != fee.salesChannel && fee.salesChannel.length() > 0) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
		}else
			this.selectAccountCode(1);
		if (null != fee.rate && fee.rate.length() > 0) {
			this.setRate(fee.rate);
		}
		if(null != fee.purchaseType && fee.purchaseType.length() > 0){
			this.selectPurchaseType(fee.purchaseType);
		}
		this.clickApply();
		this.waitLoading();

		String feeID = this.getFeeSchID(); // get fee schedule id when create
		// new
		this.clickOK();

		return feeID;
	}
	
	public String setupTransactionFeeForAdd(FeeScheduleData fee){
		return this.setupTransactionFee(fee, false);
	}
	
	public String setupTransactionFeeForEdit(FeeScheduleData fee){
		return this.setupTransactionFee(fee, true);
	}
	
	public void clickEffectiveLabel(){
		browser.clickGuiObject(".class", "Html.LABEL",".text","Effective");
	}
	
	public boolean isMaximumFeeRestrictionEnabled() {
		return isRadioButtonsEditable("maximumFeeRestriction");
	}
	
	public void selectMaximumFeeRestriction(String restrictionType){
		int index = 0;  //Default:OrmsConstants.RESTRICTION_NONE
		if(restrictionType.equalsIgnoreCase(OrmsConstants.RESTRICTION_FLAT)){
			index = 1;
		}
		if(restrictionType.equalsIgnoreCase(OrmsConstants.RESTRICTION_BASED_ON_PENALTY_CHARGES)){
			index = 2;
		}
		if(restrictionType.equalsIgnoreCase(OrmsConstants.RESTRICTION_COMBINATION_OF_FLAT_AND_PENALTY_CHARGES)){
			index = 3;
		}
		browser.selectRadioButton(".id", "maximumFeeRestriction", index);
		ajax.waitLoading();
	}
	
	private List<String> getRadioButtonLabelsById(String id) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", id);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find any '" + id + "' label object.");
		
		List<String> options = new ArrayList<String>();
		for(int i = 0; i < objs.length; i ++) {
			options.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return options;
	}
	
	public List<String> getMaximumFeeRestrictionOptions() {
		return getRadioButtonLabelsById("maximumFeeRestriction");
	}
	
	public String getMaximumFeeRestriction() {
		IHtmlObject objs[] = browser.getRadioButton(".id", "maximumFeeRestriction");
		
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Maximum Fee Restriction radio button objects.");
		
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index == -1 ? null : (index == 0 ? "None" : (index == 1 ? "Flat" : (index == 2 ? "Based On Penalty Charges" : "Combination of Flat and Penalty Charges")));
	}
	
	public boolean isMaximumFeeRateExists() {
		return browser.checkHtmlObjectExists(".id", "maximumFeeAmount");
	}
	
	public void setMaximumFeeRate(String rate){
		browser.setTextField(".id", "maximumFeeAmount", rate);
	}
	
	public String getMaximumFeeRate() {
		return browser.getTextFieldValue(".id", "maximumFeeAmount");
	}
	
	public boolean isRateAppliesToEnabled() {
		return isRadioButtonsEditable("RateAppliesTo");
	}
	
	public void selectRateAppliesTo(String applyTo){
		int index = 0; //Default:OrmsConstants.RATE_APPLIES_TO_NEW_UNITS
		if(applyTo.equalsIgnoreCase(OrmsConstants.RATE_APPLIES_TO_NEW_CHANGED_UNITS)){
			index = 1;
		}
		browser.selectRadioButton(".id", "RateAppliesTo", index);
		ajax.waitLoading();
	}
	
	public String getRateAppliesTo() {
		IHtmlObject objs[] = browser.getRadioButton(".id", "RateAppliesTo");
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Rate Applies To radio button object.");
		
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index == -1 ? null : (index == 0 ? "New Unit(s)" : (index == 1 ? "New/Changed Unit(s)" : ""));
	}
	
	public List<String> getRateAppliesToOptions() {
		return getRadioButtonLabelsById("RateAppliesTo");
	}
	
	public String setupTransactionFee(FeeScheduleData fee, boolean isEdit) {
		this.setupTransactionFeeContent(fee, isEdit);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
		String feeID = this.getFeeSchID(); // get fee schedule id when create
		this.clickOK();
		ajax.waitLoading();
		return feeID;
	}

	/**
	 * Fill in all transaction fee fields and click apply, then retrieve the
	 * schedule id, and then click on ok.
	 *
	 * @param fee
	 *            - fee schedule data
	 * @param isEmbedInTicketFee
	 *            - if select isEmbedInTicketFee checkbox on ticket transaction
	 *            fee
	 * @return - fee schedule id
	 */
	public void setupTransactionFeeContent(FeeScheduleData fee, boolean isEdit) {
		if (null != fee.loop && fee.loop.length() > 0) {
			this.selectAssignLoop(fee.loop);
			this.waitLoading();
		}
		if("Slip".equalsIgnoreCase(fee.productCategory)){
			
			if (null != fee.dock && fee.dock.length() > 0) {
				this.selectDock(fee.dock);
				this.waitLoading();
			}
		}
//		}else{ //For slip product category, it is also have loop, its loop is its dock
//			if (null != fee.loop && fee.loop.length() > 0) {
//				this.selectAssignLoop(fee.loop);
//				this.waitLoading();
//			}	
//		}
		if (null != fee.productGroup && fee.productGroup.length() > 0) {
			this.selectAssignProdGroup(fee.productGroup);
			this.waitLoading();
		}
		if(null != fee.assignPrdCategory && fee.assignPrdCategory.length() > 0){
			this.selectAssignProdCategory(fee.assignPrdCategory);
			this.waitLoading();
		}
		if (null != fee.product && fee.product.length() > 0) {
			this.selectAssignProduct(fee.product);
			this.waitLoading();
		}
		if (null != fee.effectDate && fee.effectDate.length() > 0) {
			this.setEffectiveDate(fee.effectDate);
			this.clickEffectiveLabel();
		}
		if (null != fee.salesChannel && fee.salesChannel.length() > 0) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if (!StringUtil.isEmpty(fee.state)) {
			this.selectState(fee.state);
		}
		if (null != fee.marinaRateType && fee.marinaRateType.length() > 0) {
			this.selectMarinaRateTypeByDropDownList(fee.marinaRateType);
			ajax.waitLoading();
		}
		if (null != fee.tranType && fee.tranType.length() > 0) {
			if(isEdit && fee.productCategory.equalsIgnoreCase("Ticket")){
				this.selectTransactionType(fee.tranType, true);
			}else{
				this.selectTransactionType(fee.tranType);
			}

			// if transaction type is Change Dates
			if(fee.tranType.equals(OrmsConstants.TRANNAME_CHANGE_DATE) && "Site".equalsIgnoreCase(fee.productCategory)){
				if(StringUtil.isEmpty(fee.changeDatesAppliesTo) || fee.changeDatesAppliesTo.equals("All Change Dates")){
					this.selectAllChangeDates();
				} else {
					this.selectExcludeExtendStay();
				}
				this.waitLoading();
			}
		}
		
		if (null != fee.tranOccur && fee.tranOccur.length() > 0) {
			this.selectTransactionOccu(fee.tranOccur);
		}
		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
		} else {
			this.selectAccountCode(1);
		}
		if (null != fee.tranFeeOption && fee.tranFeeOption.length() > 0) {
			if (fee.tranFeeOption.equalsIgnoreCase("Per Transaction")) {
				if (!this.checkTranFeeOptionExist("2")) {
					this.selectTransMethod("2");
				}
			} else if (fee.tranFeeOption.equalsIgnoreCase("Per Unit")) {
				if (!this.checkTranFeeOptionExist("1")) {
					this.selectTransMethod("1");
				}
			} else if (fee.tranFeeOption
					.equalsIgnoreCase("Flat by Range of Ticket Quantity")) {
				if (!this.checkTranFeeOptionExist("3")) {
					this.selectTransMethod("3");
				}
			} else{
				throw new ItemNotFoundException("Unknown type.");
			}

		}

		if (fee.productCategory.equalsIgnoreCase("Permit")) {
			if (null != fee.permitCategory && fee.permitCategory.length() > 0) {
				this.selectPermitCategory(fee.permitCategory);
			}
			if (null != fee.permitType && fee.permitType.length() > 0) {
				this.selectPermitType(fee.permitType);
			}
			if (fee.personTypes.length > 0 && fee.anyDayRates.length > 0) {
				for (int i = 0; i < fee.personTypes.length; i++) {
					if (i > 0) {
						this.clickAddPersonTypeInTrans();
						this.selectPersonType(fee.personTypes[i], i + 1);
					} else {
						this.selectPersonType(fee.personTypes[i]);
					}
					this.setTransactionFee(fee.anyDayRates[i], i + 1);
				}
			} else {
				throw new ItemNotFoundException("Unknown type.");
			}

		} else if (fee.productCategory.equalsIgnoreCase("Ticket")) {
			if (null != fee.ticketCategory && fee.ticketCategory.length() > 0) {
				this.selectTicketCategory(fee.ticketCategory);
			}
			if (null != fee.applyFee && fee.applyFee.length() > 0) {
				this.selectApplyFee(fee.applyFee);
			}
			
			if (null != fee.tranFeeOption && fee.tranFeeOption.length() > 0) {
				if (fee.tranFeeOption.equalsIgnoreCase("Per Transaction")) {
					if (!this.checkTranFeeOptionExist("2")) {
						this.selectTransMethod("2");
					}
				} else if (fee.tranFeeOption.equalsIgnoreCase("Per Unit")) {
					if (!this.checkTranFeeOptionExist("1")) {
						this.selectTransMethod("1");
					}
				} else if (fee.tranFeeOption
						.equalsIgnoreCase("Flat by Range of Ticket Quantity")) {
					if (!this.checkTranFeeOptionExist("3")) {
						this.selectTransMethod("3");
					}
				} else{
					throw new ItemNotFoundException("Unknown type.");
				}

			}
			if (checkIsEmbedInTicketFeeExit() && fee.isEmbedInTicketFee) {
				this.selectEmbedInTicketFeeCheckbox();
			}

			if (fee.ticketTypes !=null && fee.ticketTypes.length > 0 && fee.anyDayRates.length > 0) {
				for (int i = 0; i < fee.ticketTypes.length; i++) {
					if (i > 0) {
						this.clickAddTicketType();
						this.selectTicketType(fee.ticketTypes[i], i + 1);
					} else {
						this.selectTicketType(fee.ticketTypes[i]);
					}
					// handle with From Rate and To Rate
					if(fee.tranType.equalsIgnoreCase("Transfer Tickets - Customer Transfer")
					||fee.tranType.equalsIgnoreCase("Transfer Tickets - Tour Cancelled")
					||fee.tranType.equalsIgnoreCase("Change Ticket Date/Time by Customer")
					||fee.tranType.equalsIgnoreCase("Change Ticket Date/Time - Tour Cancelled"))
					{
						String[] fromTo = new String[2];
						if(fee.anyDayRates[i].startsWith(",")){
							fromTo[0]="";
							fromTo[1]=fee.anyDayRates[i].replaceAll(",", "");
						}else if(fee.anyDayRates[i].endsWith(",")){
							fromTo[0]=fee.anyDayRates[i].replaceAll(",", "");
							fromTo[1]="";
						}else{
							fromTo = fee.anyDayRates[i].split(",");
						}

						this.setTransactionFee(fromTo[0], i + 1);
						this.setTransactionFee_To(fromTo[1], i + 1);

					}else if(fee.tranFeeOption
							.equalsIgnoreCase("Flat by Range of Ticket Quantity")){
						for(int j=0; j<fee.anyDayRates.length; j++){
							if(j>0){
								this.clickAddRange();
								this.setRange(fee.anyDayRanges[j], j);
							}
							this.setRangeRate(fee.anyDayRates[j], j);
						}						
					}else{
						this.setTransactionFee(fee.anyDayRates[i], i + 1);
					}
				}
			} else {
				throw new ItemNotFoundException("Unknown type.");
			}
			
			if(fee.deliveryMethod !=null && fee.deliveryMethod.length()>0 && this.checkDeliveryMethodEnable()){
				this.selectDeliveryMethod(fee.deliveryMethod);
			}

		} else if (fee.productCategory.equalsIgnoreCase("Lottery")) {
			if (null != fee.appProductGroup && fee.appProductGroup.length() > 0) {
				this.selectApplicableAssignProdGroup(fee.appProductGroup);
			}
		} else if (fee.productCategory.equalsIgnoreCase("Service")){
			if(StringUtil.notEmpty(fee.serviceFeeAmount)){
				this.setupFlatAmountForServiceProduct(fee.serviceFeeAmount);
			}
		}
		
		if(StringUtil.notEmpty(fee.rateApplyTo)){
			this.selectRateAppliesTo(fee.rateApplyTo);
		}

		if (null != fee.tranFee && fee.tranFee.length() > 0) {
			this.setTransactionFee(fee.tranFee);
		}
		
		if (StringUtil.notEmpty(fee.tranFeeChangedUnits)){
			this.setTransactionFeeChangedUnits(fee.tranFeeChangedUnits);
		}
		if (StringUtil.notEmpty(fee.tranFeeFlatAmount)){
			this.setTransactionFeeFlatAmount(fee.tranFeeFlatAmount);
		}
		if (StringUtil.notEmpty(fee.tranFeeNumOfFreeChgPerRev)){
			this.setTransactionFeeNumOfFreeChgPerRev(fee.tranFeeNumOfFreeChgPerRev);
		}
		
		if (StringUtil.notEmpty(fee.maximumFeeRestriction)){
			this.selectMaximumFeeRestriction(fee.maximumFeeRestriction);
		}
		if (StringUtil.notEmpty(fee.maximumFeeRate)){
			this.setMaximumFeeRate(fee.maximumFeeRate);
		}
		if(StringUtil.notEmpty(fee.minimumUnitOfStay)){
			this.setMinumuUnitOfStay(fee.minimumUnitOfStay);
		}
		if(StringUtil.notEmpty(fee.minimumNumOfDayBeforeArrivalDay)){
			this.setMinimumNumOfDaysBeforArrivalDate(fee.minimumNumOfDayBeforeArrivalDay);
		}
		if(StringUtil.notEmpty(fee.minimumUnitOfStay)){
			this.setMinumuUnitOfStay(fee.minimumUnitOfStay);
		}
		if(StringUtil.notEmpty(fee.minimumNumOfDayBeforeArrivalDay)){
			this.setMinimumNumOfDaysBeforArrivalDate(fee.minimumNumOfDayBeforeArrivalDay);
		}
		
	}

	private void setupFlatAmountForServiceProduct(String amount) {
		browser.setTextField(".id", "servicefee_fee", amount);
	}

	/**
	 * check IsEmbedInTicketFee Exit
	 *
	 * @return
	 */
	public boolean checkIsEmbedInTicketFeeExit() {
		return browser.checkHtmlObjectExists(".id", "embed_fee");
	}

	/**
	 * check per Transaction or Per Unit able
	 *
	 * @param value
	 *            1.per Unit 2.per Transaction
	 * @return
	 */
	public boolean checkTranFeeOptionExist(String value) {
		Property[] p = new Property[3];
		p[0] = new Property(".id", "envtype");
		p[1] = new Property(".disabled", "true");
		p[2] = new Property(".value", value);
		return browser.checkHtmlObjectExists(p);
	}
	
	public boolean checkTranFeeOptionEnable(String feeType) {
		String value = "";
		if(feeType.equals("Per Transaction")){
			value = "2";
		} else if(feeType.equals("Per Unit")){
			value = "1";
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".id", "envtype");
		p[1] = new Property(".value", value);
//		browser.checkHtmlObjectEnabled(p);
//		
//		HtmlObject objs[] = browser.getHtmlObject(p);
//
//		if (objs[0].getProperty(".disabled").toString().equalsIgnoreCase("true")) {
//			return false;
//		} else {
//			return true;
//		}
		return browser.checkHtmlObjectEnabled(p);
	}
	
	public int getTranFeeOptionNum(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", "envtype");
		int num=objs.length;
		Browser.unregister(objs);
		return num;
	}

	/**
	 * select Apply Fee
	 *
	 * @param applyFee
	 *            :Per Order Item/Per Order
	 */
	public void selectApplyFee(String applyFee) {
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		browser.selectDropdownList(".id", "apply_fee", applyFee);
		browser.waitExists(confirmDialog,this);
		if (confirmDialog.exists()) {
			confirmDialog.dismiss();
		}
	}

	/** selsect Embed In Ticket Fee Checkbox on ticket transaction fee */
	public void selectEmbedInTicketFeeCheckbox() {
		browser.selectCheckBox(".id", "embed_fee");
	}

	/**
	 * Fill in the fee parameters when fee unit is Per Permit.
	 *
	 * @param fee
	 *            - fee schedule data
	 */
	public void setBasePermitFee(FeeScheduleData fee) {
		this.setMultipleBasePermitFee(fee, 0, 1);
	}

	/**
	 * Fill in the fee parameters when fee unit is Per Permit.
	 *
	 * @param fee
	 *            - fee schedule data for multiple person type
	 * @index - object index, will be multiple records when add more Person Type
	 */
	public void setMultipleBasePermitFee(FeeScheduleData fee, int feeIndex,
			int objIndex) {
		if (null != fee.anyDayRates && fee.anyDayRates.length > 0) {
			this.setAnyDayRate(fee.anyDayRates[feeIndex], objIndex);
		}
		if (null != fee.permitMonRates && fee.permitMonRates.length > 0) {
			this.setPermitMonRate(fee.permitMonRates[feeIndex], objIndex);
		}
		if (null != fee.permitTueRates && fee.permitTueRates.length > 0) {
			this.setPermitTueRate(fee.permitTueRates[feeIndex], objIndex);
		}
		if (null != fee.permitWedRates && fee.permitWedRates.length > 0) {
			this.setPermitWesRate(fee.permitWedRates[feeIndex], objIndex);
		}
		if (null != fee.permitThuRates && fee.permitThuRates.length > 0) {
			this.setPermitThuRate(fee.permitThuRates[feeIndex], objIndex);
		}
		if (null != fee.permitFriRates && fee.permitFriRates.length > 0) {
			this.setPermitFriRate(fee.permitFriRates[feeIndex], objIndex);
		}
		if (null != fee.permitSatRates && fee.permitSatRates.length > 0) {
			this.setPermitSatRate(fee.permitSatRates[feeIndex], objIndex);
		}
		if (null != fee.permitSunRates && fee.permitSunRates.length > 0) {
			this.setPermitSunRate(fee.permitSunRates[feeIndex], objIndex);
		}
	}

	/**
	 * Fill in all data when unit is Flat by Range of Group Size or Per Person
	 * Per Period
	 *
	 * @param fee
	 *            - fee schedule data
	 * @param personIndex
	 *            - person type index
	 * @param feeIndex
	 *            - fee data index for each person type
	 * @param objIndex
	 *            - object index, start from 1
	 */
	public void setMultiFlatRangePermitFee(FeeScheduleData fee,
			int personIndex, int feeIndex, int objIndex) {
		if (null != fee.rangeFlats && fee.rangeFlats.size() > 0) {
			if (checkRangeIsEnable(objIndex)) {
				this.setIncrementRate(fee.rangeFlats.get(personIndex).get(
						feeIndex), "3_2", objIndex);// 1 more hidden Range
				// object
			}
		}
		if (null != fee.anyDayFlats && fee.anyDayFlats.size() > 0) {
			this.setBaseIncrementRate(fee.anyDayFlats.get(personIndex).get(
					feeIndex), "3_2", objIndex);
		}
		if (null != fee.monFlats && fee.monFlats.size() > 0 && isMonIncrementRateExisted("3_2")) {
			this.setMonIncrementRate(fee.monFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if (null != fee.tueFlats && fee.tueFlats.size() > 0 && isTueIncrementRateExisted("3_2")) {
			this.setTueIncrementRate(fee.tueFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if (null != fee.wedFlats && fee.wedFlats.size() > 0 && isWedIncrementRateExisted("3_2")) {
			this.setWedIncrementRate(fee.wedFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if (null != fee.thuFlats && fee.thuFlats.size() > 0 && isThuIncrementRateExisted("3_2")) {
			this.setThuIncrementRate(fee.thuFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if (null != fee.friFlats && fee.friFlats.size() > 0 && isFriIncrementRateExisted("3_2")) {
			this.setFriIncrementRate(fee.friFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if (null != fee.satFlats && fee.satFlats.size() > 0 && isSatIncrementRateExisted("3_2")) {
			this.setSatIncrementRate(fee.satFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if (null != fee.sunFlats && fee.sunFlats.size() > 0 && isSunIncrementRateExisted("3_2")) {
			this.setSunIncrementRate(fee.sunFlats.get(personIndex)
					.get(feeIndex), "3_2", objIndex);
		}
		if(isMaximumUseFeeExisted()){
			if(StringUtil.isEmpty(fee.maximumUseFee)){
				fee.maximumUseFee = getBaseIncrementRate("3_2", objIndex);
			}
			setMaximumUseFee(fee.maximumUseFee);
		}
	}
     
	public void selectDock(String dock){
		browser.selectDropdownList(".id", "assignment_loop", dock);
	}
	/**
	 * set in out state.
	 * @param inOutState
	 */
	public void selectInOutState(String inOutState){
		browser.selectDropdownList(".id", "conditions_in_out_state", inOutState);
	}
	/**
	 * select boat category.
	 * @param boatCategory
	 */
	public void selectBoatCategory(String boatCategory){
		browser.selectDropdownList(".id", "ticket_cat_id", boatCategory);
	}
	
	public String getBoatCategory(){
		return browser.getDropdownListValue(".id", "ticket_cat_id", 0);
	}
	
	public List<String> getBoatCategoryElement(){
		return browser.getDropdownElements(".id", "ticket_cat_id");
	}
	/**
	 * select marina rate type.
	 * @param index
	 */
	public void selectMarinaRateTypeRadioButton(int index){
		browser.selectRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), index);
	}
	/**
	 * select unit check box.
	 * @param index
	 */
	public void selectUnitRadioButton(int index){
		browser.selectRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit",false), index);
	}
	
	public List<String> getUnitElementsOfRadioButton(){
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit(\\d)?",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get unit radio button object.");
		}
		
		List<String> elements = new ArrayList<String>();
		for(IHtmlObject radio : objs){
			String id = radio.id();
			IHtmlObject[] labels = browser.getHtmlObject(".class","Html.LABEL",".for", id);
			if(labels.length<1){
				throw new ItemNotFoundException("Did not get unit label object.");
			}
			String value = labels[labels.length-1].text();
			elements.add(value);
			Browser.unregister(labels);
			Browser.unregister(radio);
		}
		Browser.unregister(objs);
		
		return elements;
	}
	
	public boolean checkUnitRadioButtonIsEditable(){
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit(\\d+)?",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get unit radio button object.");
		}
		
		boolean isEditable = true;
		for(IHtmlObject obj: objs){
			IRadioButton rad= (IRadioButton)obj;
			isEditable &= rad.isEnabled();
		}
		
		Browser.unregister(objs);
		return isEditable;
	}
	
	public String getUnitOfRadioButton(){
		IHtmlObject[] radios = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit(\\d+)?", false));
		String unit = null;
		
		if(radios.length<1){
			throw new ItemNotFoundException("Did not get unit radio button object.");
		}
		
		for(int i=0; i<radios.length; i++){
			IRadioButton rad= (IRadioButton)radios[i];
			
			if(rad.isSelected()){
				String id = radios[i].id();
				IHtmlObject[] labels = browser.getHtmlObject(".class","Html.LABEL",".for", id);	
				if(labels.length<1){
					throw new ItemNotFoundException("Did not get unit label object.");
				}
				unit = labels[labels.length-1].text();
				Browser.unregister(labels);
				break;
			}			
		}
		
		Browser.unregister(radios);
		return unit;
	}
	
	public boolean isMarinaRateTypeEnable(){
		return browser.checkHtmlObjectEnabled(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false));
	}
	
	/**
	 * select marina rate type.
	 * @param marianTateType
	 */
	public void selectMarinaRateType(String marianRateType){
		if(isMarinaRateTypeEnable()){
			if(marianRateType.equals(Type_Seasonal)){
				this.selectMarinaRateTypeRadioButton(0);
			}else if(marianRateType.equals(Type_Lease)){
				this.selectMarinaRateTypeRadioButton(1);
			}else if(marianRateType.equals(Type_Transient)){
				this.selectMarinaRateTypeRadioButton(2);
			}
		}
	}
	
	public void selectMarinaRateTypeByDropDownList(String marianRateType){
		if(isMarinaRateTypeEnable()){
			browser.selectDropdownList(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), marianRateType);
		}
	}
	/**
	 * select unit.
	 * @param unit
	 */
	public void selectUnit(String unit){
		if(unit.equals(LENGTH_RANGE)){
			this.selectUnitRadioButton(0);
		}else if(unit.equals(LENGTH_INCREMENT)){
			this.selectUnitRadioButton(1);
		}
	}
	
	public void selectRafting(String rafting){
		browser.selectDropdownList(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.raftingType",false), rafting);
	}
	
	/**
	 * Fill in all use fee fields and click apply, then retrieve the schedule id
	 * and then click on ok.
	 *
	 * @param fee
	 *            - fee schedule data
	 * @param isGroupRate
	 *            - is fee rate type is group or family
	 * @param isOccupantsRanges
	 *            - is Fees for Additional Occupants is increments or ranges
	 * @param isVehiclesRanges
	 *            - is Fees for Additional Vehicles is increments or ranges
	 * @return - fee schedule id
	 * @throws PageNotFoundException
	 */
	public void setBaseRateDailyNightly(String rate){
		browser.setTextField(".id", "duration_daily", rate);
	}
	/**
	 * set base rate weekly rate.
	 * @param rate
	 */
	public void setBaseRateWeekly(String rate){
		browser.setTextField(".id", "duration_weekly", rate);
	}
	/**
	 * set base rate monthly 
	 * @param rate 
	 */
	public void setBaseRateMonthly(String rate){
		browser.setTextField(".id", "duration_monthly", rate);
	}
	
	public void setMinimumUseFee(String minimumUstFee){
		browser.setTextField(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.minimumFeeStr",false), minimumUstFee);
	}
	public void setBaseRatePerSeason(String perSeason){
		browser.setTextField(".id", "duration_season", perSeason);
	}
	
	public String setupUseFee(FeeScheduleData fee) throws PageNotFoundException {
		
		this.fillUseFee(fee);
		
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
		String returnValue = this.getFeeSchID();
		this.clickOK();
		return returnValue;
	}
	
	/**
	 * @param fee
	 */
	public void fillUseFee(FeeScheduleData fee) {
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		if("Site".equalsIgnoreCase(fee.productCategory)){
			if (null != fee.loop && fee.loop.length() > 0) {
				this.selectAssignLoop(fee.loop);
				this.waitLoading();
			}
			
			if (null != fee.custType && fee.custType.length() > 0) {
				this.selectCustomerType(fee.custType);
			}
		}
		
		if (null != fee.productGroup && fee.productGroup.length() > 0) {
			this.selectAssignProdGroup(fee.productGroup);
			this.waitLoading();
		}
		if (null != fee.product && fee.product.length() > 0) {
			this.selectAssignProduct(fee.product);
			this.waitLoading();
		}
		if (null != fee.effectDate && fee.effectDate.length() > 0) {
			this.setEffectiveDate(fee.effectDate);
		}
		if (null != fee.startInv && fee.startInv.length() > 0) {
			this.setInvStartDate(fee.startInv);
		}
		if (null != fee.endInv && fee.endInv.length() > 0) {
			this.setInvEndDate(fee.endInv);
		}
		if (null != fee.season && fee.season.length() > 0) {
			this.selectSeason(fee.season);
		}
		if (null != fee.salesChannel && fee.salesChannel.length() > 0) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if (null != fee.state && fee.state.length() > 0) {
			this.selectState(fee.state);
		}
		if (null != fee.unitOfStay && fee.unitOfStay.length() > 0) {
			this.selectUnitOfStay(fee.unitOfStay);
		}
		
		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
		} else {
			this.selectAccountCode(1);
		}
		
		if("Slip".equalsIgnoreCase(fee.productCategory)){
			if(null != fee.dock && fee.dock.length()>0){//due to reselect dock info, so will reload product group and product info, so need to reselect product info
				this.selectDock(fee.dock);
				this.waitLoading();
				
				if (null != fee.productGroup && fee.productGroup.length() > 0) {
					this.selectAssignProdGroup(fee.productGroup);
					this.waitLoading();
				}
				if (null != fee.product && fee.product.length() > 0) {
					this.selectAssignProduct(fee.product);
					this.waitLoading();
				}
			}
			
			if(null != fee.state && fee.state.length()>0){
				this.selectInOutState(fee.state);
			}
			if(null != fee.boatCategory && fee.boatCategory.length()>0){
				this.selectBoatCategory(fee.boatCategory);
			}
			if(null != fee.marinaRateType && fee.marinaRateType.length()>0){
				this.selectMarinaRateType(fee.marinaRateType);
				ajax.waitLoading();
			}
			if(!StringUtil.isEmpty(fee.rafting) && fee.marinaRateType.equals(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)){
				this.selectRafting(fee.rafting);
			}
		    if(null != fee.slipPricingUnit && fee.slipPricingUnit.length()>0){
		    	this.selectUnit(fee.slipPricingUnit);
		    	ajax.waitLoading();
		    	browser.waitExists(this, confirmWidget);
		    	if(confirmWidget.exists()){
		    		confirmWidget.clickOK();
			    	ajax.waitLoading();
			    	this.waitLoading();
		    	}
		    }
		    if(!StringUtil.isEmpty(fee.nightlyRate)) {
		    	this.setDailyRate(fee.nightlyRate);
		    }
		    if(!StringUtil.isEmpty(fee.weeklyRate)) {
		    	this.setWeeklyRate(fee.weeklyRate);
		    }
		    if(!StringUtil.isEmpty(fee.monthlyRate)) {
		    	this.setMonthlyRate(fee.monthlyRate);
		    }
		    
		    if(null != fee.baseRatePerSeason && fee.baseRatePerSeason.length()>0){
		    	this.setBaseRatePerSeason(fee.baseRatePerSeason);
		    }
		    if(null != fee.minimumUseFee && fee.minimumUseFee.length()>0){
		        this.setMinimumUseFee( fee.minimumUseFee);
		    }
		    
		    if (fee.isFullStayMultiunit) 
				this.selectMultiUnit();
			else
				this.unselectMultiUnit();
		    
		    if(fee.slipFees.size()>0)
		    {
		    	
		    	this.setSlipFees(fee.slipFees,fee.slipPricingUnit);
		    }
		    
		    if(!StringUtil.isEmpty(fee.calculationMethod))
		    {
		    	this.selectCalculationMethod(fee.calculationMethod);
		    	ajax.waitLoading();
		    }
		    
		    if(fee.pricingBasedonArrival.size()>0)
			{
				this.addPricingBaseOn("Arrival",fee.pricingBasedonArrival);
			}
			
			if(fee.pricingBasedonDeparture.size()>0)
			{
				this.addPricingBaseOn("Departure",fee.pricingBasedonDeparture);
			}
			
			if (null != fee.custType && fee.custType.length() > 0) {
				this.selectCustomerType(fee.custType);
			}
			
		}
		
		if (fee.productCategory.equalsIgnoreCase("Permit")) {
			/**
			 * PERMIT use fee
			 */
			if (null != fee.permitCategory && fee.permitCategory.length() > 0) {
				this.selectPermitCategory(fee.permitCategory);
			}
			if (null != fee.permitType && fee.permitType.length() > 0) {
				this.selectPermitType(fee.permitType);
			}
			// 1 is default, and 4 is the same to it
			if (null != fee.permitUnit && fee.permitUnit.length() > 0) {
				this.selectPermitUnit(fee.permitUnit);

				if (fee.permitUnit.equals("Per Permit")) {// Per Permit unit
					this.setBasePermitFee(fee);
				} else if (fee.permitUnit.equals("Flat by Range of Group Size") || fee.permitUnit.equals("Per Person Per Period")) {
					if (null != fee.personTypes && fee.personTypes.length > 0) {
						int objIndex = 1;// 1 hidden object
						for (int i = 0; i < fee.personTypes.length; i++) {
							this.selectPersonType(fee.personTypes[i], i);// select
							// person
							// type
							for (int j = 0; j < fee.rangeFlats.get(i).size(); j++) {
								this.setMultiFlatRangePermitFee(fee, i, j,
										objIndex);
								if (j < fee.rangeFlats.get(i).size() - 1) {
									this.clickAddRange(i);
									objIndex++;// increase the object index to
									// next row
									Browser.sleep(1);
								}
							}
							if (i < fee.personTypes.length - 1) {
								this.clickAddPersonType();
								objIndex = objIndex + 2;// 1 hidden object for
								// each person type
								// section
								Browser.sleep(1);
							}
						}
					}
				} else {// unit type is 1 or 4
					if (null != fee.personTypes && fee.personTypes.length > 1) {
						for (int i = 0; i < fee.personTypes.length; i++) {
							if(i > 0) {
								this.clickAddPersonType();
							}
							this.selectPersonType(fee.personTypes[i], i + 1);
							this.setMultipleBasePermitFee(fee, i, i + 1);
							Browser.sleep(1);
						}
					} else {//single permit person type
						this.selectPersonType(fee.personTypes[0]);
						this.setBasePermitFee(fee);
					}
				}
			} else {// handle the default Unit 'Per Person'
				if (null != fee.personTypes && (fee.personTypes.length > 0)) {//multiple permit person types
					for (int i = 0; i < fee.personTypes.length; i++) {
						this.selectPersonType(fee.personTypes[i]);
						this.setMultipleBasePermitFee(fee, i, i + 1);
						if(i > 0) {
							this.clickAddPersonType();
						}
						Browser.sleep(1);
					}
				} else {//single permit person type
					this.selectPersonType(fee.personTypes[0]);
					this.setBasePermitFee(fee);
				}
			}
		} else {
			/**
			 * SITE use fee schedule start here
			 */
			if (fee.isGroupRate) {
				this.selectRateType("2");// select Group radio
				// base fee
				if (null != fee.anyUnitRate && fee.anyUnitRate.length() > 0) {
					this.setAnyUnitRate(fee.anyUnitRate);
				}
				if (null != fee.monRate && fee.monRate.length() > 0) {
					this.setMonRate(fee.monRate);
				}
				if (null != fee.tuesRate && fee.tuesRate.length() > 0) {
					this.setTueRate(fee.tuesRate);
				}
				if (null != fee.wedRate && fee.wedRate.length() > 0) {
					this.setWedRate(fee.wedRate);
				}
				if (null != fee.thurRate && fee.thurRate.length() > 0) {
					this.setThuRate(fee.thurRate);
				}
				if (null != fee.friRate && fee.friRate.length() > 0) {
					this.setFriRate(fee.friRate);
				}
				if (null != fee.satRate && fee.satRate.length() > 0) {
					this.setSatRate(fee.satRate);
				}
				if (null != fee.sunRate && fee.sunRate.length() > 0) {
					this.setSunRate(fee.sunRate);
				}
				if (fee.isOccupantsRanges) {
					// select Ranges radio about "Fees for Additional Occupants"
					this.selectOccRadio("2");
					if (confirmDialog.exists()) {
						confirmDialog.dismiss();
					}

					browser.waitExists();

//					String prefix = "1_2";
					String prefix = "2_1";
					if (null != fee.rangesOccRate
							&& fee.rangesOccRate.length() > 0) {
						this.setIncrementRate(fee.rangesOccRate, prefix);
					}
					if (null != fee.anyUnitRangsOccRate
							&& fee.anyUnitRangsOccRate.length() > 0) {
						this.setBaseIncrementRate(fee.anyUnitRangsOccRate,
								prefix);
					}
					if (null != fee.monRangesOccRate
							&& fee.monRangesOccRate.length() > 0) {
						this.setMonIncrementRate(fee.monRangesOccRate, prefix);
					}
					if (null != fee.tueRangesOccRate
							&& fee.tueRangesOccRate.length() > 0) {
						this.setTueIncrementRate(fee.tueRangesOccRate, prefix);
					}
					if (null != fee.wedRangesOccRate
							&& fee.wedRangesOccRate.length() > 0) {
						this.setWedIncrementRate(fee.wedRangesOccRate, prefix);
					}
					if (null != fee.thuRangesOccRate
							&& fee.thuRangesOccRate.length() > 0) {
						this.setThuIncrementRate(fee.thuRangesOccRate, prefix);
					}
					if (null != fee.friRangesOccRate
							&& fee.friRangesOccRate.length() > 0) {
						this.setFriIncrementRate(fee.friRangesOccRate, prefix);
					}
					if (null != fee.satRangesOccRate
							&& fee.satRangesOccRate.length() > 0) {
						this.setSatIncrementRate(fee.satRangesOccRate, prefix);
					}
					if (null != fee.sunRangesOccRate
							&& fee.sunRangesOccRate.length() > 0) {
						this.setSunIncrementRate(fee.sunRangesOccRate, prefix);
					}
				} else {
					// select Increments radio about
					// "Fees for Additional Occupants"
					this.selectOccRadio("1");
					if (confirmDialog.exists()) {
						confirmDialog.dismiss();
					}
					browser.waitExists();

					String prefix = "1_1";
					if (null != fee.incrementsOccRate
							&& fee.incrementsOccRate.length() > 0) {
						this.setIncrementRate(fee.incrementsOccRate, prefix);
					}
					if (null != fee.anyUnitIncrementsOccRate
							&& fee.anyUnitIncrementsOccRate.length() > 0) {
						this.setBaseIncrementRate(fee.anyUnitIncrementsOccRate,
								prefix);
					}
					if (null != fee.monIncrementsOccRate
							&& fee.monIncrementsOccRate.length() > 0) {
						this.setMonIncrementRate(fee.monIncrementsOccRate,
								prefix);
					}
					if (null != fee.tueIncrementsOccRate
							&& fee.tueIncrementsOccRate.length() > 0) {
						this.setTueIncrementRate(fee.tueIncrementsOccRate,
								prefix);
					}
					if (null != fee.wedIncrementsOccRate
							&& fee.wedIncrementsOccRate.length() > 0) {
						this.setWedIncrementRate(fee.wedIncrementsOccRate,
								prefix);
					}
					if (null != fee.thuIncrementsOccRate
							&& fee.thuIncrementsOccRate.length() > 0) {
						this.setThuIncrementRate(fee.thuIncrementsOccRate,
								prefix);
					}
					if (null != fee.friIncrementsOccRate
							&& fee.friIncrementsOccRate.length() > 0) {
						this.setFriIncrementRate(fee.friIncrementsOccRate,
								prefix);
					}
					if (null != fee.satIncrementsOccRate
							&& fee.satIncrementsOccRate.length() > 0) {
						this.setSatIncrementRate(fee.satIncrementsOccRate,
								prefix);
					}
					if (null != fee.sunIncrementsOccRate
							&& fee.sunIncrementsOccRate.length() > 0) {
						this.setSunIncrementRate(fee.sunIncrementsOccRate,
								prefix);
					}
				}
				if (fee.isVehiclesRanges) {
					// select Ranges radio about "Fees for Additional Vehicles"
					this.selectVehRadio("2");
					if (confirmDialog.exists()) {
						confirmDialog.dismiss();
					}

					browser.waitExists();

					String prefix = "2_2";
					if (null != fee.rangesVehRate
							&& fee.rangesVehRate.length() > 0) {
						this.setIncrementRate(fee.rangesVehRate, prefix);
					}
					if (null != fee.anyUnitRangesVehRate
							&& fee.anyUnitRangesVehRate.length() > 0) {
						this.setBaseIncrementRate(fee.anyUnitRangesVehRate,
								prefix);
					}
					if (null != fee.monRangesVehRate
							&& fee.monRangesVehRate.length() > 0) {
						this.setMonIncrementRate(fee.monRangesVehRate, prefix);
					}
					if (null != fee.tueRangesVehRate
							&& fee.tueRangesVehRate.length() > 0) {
						this.setTueIncrementRate(fee.tueRangesVehRate, prefix);
					}
					if (null != fee.wedRangesVehRate
							&& fee.wedRangesVehRate.length() > 0) {
						this.setWedIncrementRate(fee.wedRangesVehRate, prefix);
					}
					if (null != fee.thuRangesVehRate
							&& fee.thuRangesVehRate.length() > 0) {
						this.setThuIncrementRate(fee.thuRangesVehRate, prefix);
					}
					if (null != fee.friRangesVehRate
							&& fee.friRangesVehRate.length() > 0) {
						this.setFriIncrementRate(fee.friRangesVehRate, prefix);
					}
					if (null != fee.satRangesVehRate
							&& fee.satRangesVehRate.length() > 0) {
						this.setSatIncrementRate(fee.satRangesVehRate, prefix);
					}
					if (null != fee.sunRangesVehRate
							&& fee.sunRangesVehRate.length() > 0) {
						this.setSunIncrementRate(fee.sunRangesVehRate, prefix);
					}
				} else {
					// select Increments radio about
					// "Fees for Additional Vehicles"
					this.selectVehRadio("1");
					if (confirmDialog.exists()) {
						confirmDialog.dismiss();
					}

					browser.waitExists();

					String prefix = "2_1";
					if (null != fee.incrementsVehRate
							&& fee.incrementsVehRate.length() > 0) {
						this.setIncrementRate(fee.incrementsVehRate, prefix);
					}
					if (null != fee.anyUnitIncrementsVehRate
							&& fee.anyUnitIncrementsVehRate.length() > 0) {
						this.setBaseIncrementRate(fee.anyUnitIncrementsVehRate,
								prefix);
					}
					if (null != fee.monIncrementsVehRate
							&& fee.monIncrementsVehRate.length() > 0) {
						this.setMonIncrementRate(fee.monIncrementsVehRate,
								prefix);
					}
					if (null != fee.tueIncrementsVehRate
							&& fee.tueIncrementsVehRate.length() > 0) {
						this.setTueIncrementRate(fee.tueIncrementsVehRate,
								prefix);
					}
					if (null != fee.wedIncrementsVehRate
							&& fee.wedIncrementsVehRate.length() > 0) {
						this.setWedIncrementRate(fee.wedIncrementsVehRate,
								prefix);
					}
					if (null != fee.thuIncrementsVehRate
							&& fee.thuIncrementsVehRate.length() > 0) {
						this.setThuIncrementRate(fee.thuIncrementsVehRate,
								prefix);
					}
					if (null != fee.friIncrementsVehRate
							&& fee.friIncrementsVehRate.length() > 0) {
						this.setFriIncrementRate(fee.friIncrementsVehRate,
								prefix);
					}
					if (null != fee.satIncrementsVehRate
							&& fee.satIncrementsVehRate.length() > 0) {
						this.setSatIncrementRate(fee.satIncrementsVehRate,
								prefix);
					}
					if (null != fee.sunIncrementsVehRate
							&& fee.sunIncrementsVehRate.length() > 0) {
						this.setSunIncrementRate(fee.sunIncrementsVehRate,
								prefix);
					}
				}
			} else {// family use fee for site here
				// fee by duration
				if (null != fee.nightlyRate && fee.nightlyRate.length() > 0) {
					this.setDailyRate(fee.nightlyRate);
				}
				if (null != fee.weeklyRate && fee.weeklyRate.length() > 0) {
					this.setWeeklyRate(fee.weeklyRate);
				}
				if (null != fee.monthlyRate && fee.monthlyRate.length() > 0) {
					this.setMonthlyRate(fee.monthlyRate);
				}
				if (fee.isFullStayMultiunit) {
					this.selectMultiUnit();
				}

				// fee by customer duration
				if (null != fee.unitQuantity && fee.unitQuantity.length() > 0) {
					this.setCustomUnitQuantity(fee.unitQuantity);
				}
				if (null != fee.custRate && fee.custRate.length() > 0) {
					this.setCustomRate(fee.custRate);
				}

				// fee by day of week
				if (null != fee.monRate && fee.monRate.length() > 0) {
					this.setMonRate(fee.monRate);
				}
				if (null != fee.tuesRate && fee.tuesRate.length() > 0) {
					this.setTueRate(fee.tuesRate);
				}
				if (null != fee.wedRate && fee.wedRate.length() > 0) {
					this.setWedRate(fee.wedRate);
				}
				if (null != fee.thurRate && fee.thurRate.length() > 0) {
					this.setThuRate(fee.thurRate);
				}
				if (null != fee.friRate && fee.friRate.length() > 0) {
					this.setFriRate(fee.friRate);
				}
				if (null != fee.satRate && fee.satRate.length() > 0) {
					this.setSatRate(fee.satRate);
				}
				if (null != fee.sunRate && fee.sunRate.length() > 0) {
					this.setSunRate(fee.sunRate);
				}
				if (fee.isAddEventHoliday) {
					for (int i = 0; i < fee.holidayName.length; i++) {
						this.clickAddEventHoliday();
						Browser.sleep(1);
						// there is 1 hidden object, index should start from 1
						this.setHolidayName(fee.holidayName[i], i + 1);
						this.setHolidayRate(fee.holidayRate[i], i + 1);
						this.setHolidayInvStart(fee.holidayInvStart[i], i + 1);
						this.setHolidayInvEnd(fee.holidayInvEnd[i], i + 1);
					}
				}
			}
		}
	}

	/**
	 * @param calculationMethod
	 */
	public void selectCalculationMethod(String calculationMethod) {		
			if(calculationMethod.equals(Method_Calculation_Daily)){
				this.selectCalculationMethodRadioButton(0);
			}else if(calculationMethod.equals(Method_Calculation_Percentage)){
				this.selectCalculationMethodRadioButton(1);
			}				
	}

	
	public void selectCalculationMethodRadioButton(int index){
		browser.selectRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.calculationMethod",false), index);
	}
	/**
	 * @param slipFees
	 */
	public List<String> getSlipDailyFee(String unit)
	{
		if(unit.contains("Range"))
		{
			return this.getSlipFeeByUnitAndType(RANGE_VIEW, DAILY_NIGHTLY_FEE);
		}else if(unit.contains("Increments"))
		{
			return this.getSlipFeeByUnitAndType(INCREMENT_VIEW, DAILY_NIGHTLY_FEE);
		}
		return null;
		
	}

	
	public List<String> getSlipFeeByUnitAndType(String unit, String type)
	{
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression(unit+"-\\d+\\."+type, false)));
		List<String> dailyFees= new ArrayList<String>();
		for(IHtmlObject o: objs)
		{
			IText tmp = (IText)o;
			dailyFees.add(tmp.getText());			
		}
		Browser.unregister(objs);
		return dailyFees;
	}

	public List<String> getSlipRange(String unit)
	{
		if(unit.contains("Range"))
		{
			return this.getSlipFeeByUnitAndType(RANGE_VIEW, this.RANGE);
		}else if(unit.contains("Increments"))
		{
			return this.getSlipFeeByUnitAndType(INCREMENT_VIEW, this.RANGE);
		}
		return null;
		
	}

	public List<String> getSlipSeasonFee(String unit)
	{
		if(unit.contains("Range"))
		{
			return this.getSlipFeeByUnitAndType(RANGE_VIEW, SEASON_FEE);
		}else if(unit.contains("Increments"))
		{
			return this.getSlipFeeByUnitAndType(INCREMENT_VIEW, SEASON_FEE);
		}
		return null;
		
	}
	
	public List<String> getSlipMonthlyFee(String unit)
	{
		if(unit.contains("Range"))
		{
			return this.getSlipFeeByUnitAndType(RANGE_VIEW, MONTHLY_FEE);
		}else if(unit.contains("Increments"))
		{
			return this.getSlipFeeByUnitAndType(INCREMENT_VIEW, MONTHLY_FEE);
		}
		return null;
		
	}

	public void addUnitRange(List<SlipFee> fees, String unit){
		int size = fees.size();

		if(unit.contains("Range"))
		{
			for(int i=1; i<size; i++)
			{
				this.clickAddRange();
				ajax.waitLoading();
				this.waitLoading();
			}
		}else if(unit.contains("Increments"))
		{
			for(int i=0; i<size; i++)//different
			{
				this.clickAddIncrement();
				ajax.waitLoading();
				this.waitLoading();
			}
		}
	}
	
	public void setSlipFees(List<SlipFee> fees, String unit) {
		//there is already 1 record on the page.
		//remove existing Length Range/Length Increments records
		this.removeAllLengthRangeExceptTheDefaultOne();
		this.removeAllRangeIncrement();
		this.addUnitRange(fees, unit);
		this.setUpSlipFeesInfo(fees, unit);
	}
	
	public void setUpSlipFeesInfo(List<SlipFee> fees, String unit){
//		HtmlObject[] tbls =this.getSlipRateTable();
		IHtmlObject[] tbls=this.getSlipRateTR();//modify this for multiple range

		if(fees.size() != tbls.length){
			throw new ErrorOnPageException("The slip fee length should equal tr length on page");
		}
		for(int j=0;j<fees.size();j++) {//add one slip fee
			SlipFee fee = fees.get(j);
			IHtmlObject tbl = tbls[j];
			
			if(unit.contains("Range")) {
				if(!StringUtil.isEmpty(fee.rangeFeet)) {
					this.setRangeFeeRange(fee.rangeFeet, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.perSeasonFee)) {
					this.setRangeFeeSeason(fee.perSeasonFee, 0, tbl);
				}
				
				if(fee.isFeePerFoot) {
					this.selectUnselectRangeFeePerFoot(true, 0, tbl);
				} else {
					this.selectUnselectRangeFeePerFoot(false, 0, tbl);
				}
				
				if(fee.isfullStayForMultiUnitRate && this.isFullStayMultiUnitRateCheckBoxExisted(tbl)) {
					this.selectUnselectFullStayForMultiUnitRate(true, tbl);
				} else {
					this.selectUnselectFullStayForMultiUnitRate(false, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.dailyNightlyFee)) {
					this.setRangeFeeDailyNightly(fee.dailyNightlyFee, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.weeklyFee)) {
					this.setRangeFeeWeekly(fee.weeklyFee, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.monthlyFee)) {
					this.setRangeFeeMonthly(fee.monthlyFee, 0, tbl);
				}
				
				//setup Custom Duration info by row
				if(fee.customDurations.size()>0) {
					this.addSlipFeeCustomDuration(fee.customDurations,j);//j was indicate for jth Range
				}
				
				//setup day of week rates
				if(!StringUtil.isEmpty(fee.mondayRate)) {
					this.setSlipMondayRate(fee.mondayRate, tbl);
				}
				if(!StringUtil.isEmpty(fee.tuesdayRate)) {
					this.setSlipTuesdayRate(fee.tuesdayRate, tbl);
				}
				if(!StringUtil.isEmpty(fee.wednesdayRate)) {
					this.setSlipWednesdayRate(fee.wednesdayRate, tbl);
				}
				if(!StringUtil.isEmpty(fee.thursdayRate)) {
					this.setSlipThursdayRate(fee.thursdayRate, tbl);
				}
				if(!StringUtil.isEmpty(fee.fridayRate)) {
					this.setSlipFridayRate(fee.fridayRate, tbl);
				}
				if(!StringUtil.isEmpty(fee.saturdayRate)) {
					this.setSlipSaturdayRate(fee.saturdayRate, tbl);
				}
				if(!StringUtil.isEmpty(fee.sundayRate)) {
					this.setSlipSundayRate(fee.sundayRate, tbl);
				}
				
				//setup Event Holiday fee setup
				setEventHolidays(fee.holidays, j);
				
			} else if(unit.contains("Increments")) {
				if(!StringUtil.isEmpty(fee.increment)) {
					this.setIncrementFeeIncrement(fee.increment, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.perSeasonFee)) {
					this.setIncrementFeeSeason(fee.perSeasonFee, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.monthlyFee)) {
					this.setIncrementFeeMonthly(fee.monthlyFee, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.dailyNightlyFee)) {
					this.setIncrementFeeDaily(fee.dailyNightlyFee, 0, tbl);
				}
				
				if(!StringUtil.isEmpty(fee.weeklyFee)) {
					this.setIncrementFeeWeekly(fee.weeklyFee, 0, tbl);
				}
			}
		}
		
		Browser.unregister(tbls);
	}
	
	/**
	 * remove increment.
	 * @param fees
	 */
	public void removeIncrement(List<SlipFee> fees){
		for(int k=fees.size()-1;k>0;k--){
			SlipFee feeRemove = fees.get(k);
			if(feeRemove.isRemoveIncrement){
				this.clickRemoveIncrement(k);
			}
		}
	}
	/**
	 * Click remove Increment.
	 */
	public void clickRemoveIncrement(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Increment",index);
	}
	public String getRangeSlipDailyNightFee(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.nightlyFee",false), index);
	}
	
	public String getRangeSlipWeeklyFee(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.weeklyFee",false), index);
	}
	
	public String getRangeSlipMonthlyFee(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.monthlyFee|SlipFeeIncrementView-\\d+\\.monthlyFee",false), index);
	}
	
	public String getIncrementPerSeasonFee(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.seasonFee",false), index);
	}
	
	public String getLengthRangeSeasonFee(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.seasonFee",false), index);
	}
	
	public String getBasePerSeasonFee(){
		return browser.getTextFieldValue(".id", "duration_season");
	}
	
	public String getBaseMonthlyFee(){
		return browser.getTextFieldValue(".id", "duration_monthly");
	}
	
	public String getBaseDailyNightlyFee(){
		return browser.getTextFieldValue(".id", "duration_daily");
	}
	
	public String getBaseWeeklyFee(){
		return browser.getTextFieldValue(".id", "duration_weekly");
	}
	
	public String getIncremnets(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.increment",false),index);
	}
	
	public String getMinimumUseFee(){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.minimumFeeStr",false));
	}
	
	public boolean checkMinimumUseFeeIsEnabled(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.minimumFeeStr",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Minimum Use Fee Text Object.");
		}
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	private IHtmlObject[] getPricingBasedOnArrivalTr(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".id", "arrival_pricing_bar");
		if(objs.length<1){
			throw new ErrorOnPageException("No specific element was exist");
		}
		return objs;
		
	}
	
	private IHtmlObject[] getPricingBaseOnDepartureTr(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".id", "departure_pricing_bar");
		if(objs.length<1){
			throw new ErrorOnPageException("No specific element was exist");
		}
		return objs;
	}
	/**
	 * get arrival start month.
	 * @param index
	 * @return
	 */
	public String getArrivalStartMonth(int index){
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getArrivalStartDate(int index){
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getArrivalEndMonth(int index){
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateMonth",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getArrivalEndDate(int index){
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateDay",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getArrivalPercent(int index){
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.percent",false));
		String text = browser.getTextFieldValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	
	public String getDepartureStartMonth(int index){
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getDepartureStartDate(int index){
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getDepartureEndMonth(int index){
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateMonth",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getDepartureEndDate(int index){
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.endDateDay",false));
		String text = browser.getDropdownListValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	public String getDeparturePercent(int index){
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.percent",false));
		String text = browser.getTextFieldValue(p, index, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	
	private void addSlipFeeCustomDuration(List<SlipFeeCustomDuration> customDurations, int j) {
		//Note: For editing, maybe already exist durations
		IHtmlObject[] tops =this.getSlipRateTR();
		IHtmlObject top = tops[j];
		IHtmlObject[] existedDurations = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.duration", false), top);
		int existedSize=existedDurations.length;
		logger.info("Already exist "+existedSize+" Custom Duration.");
		Browser.unregister(existedDurations);
		Browser.unregister(tops);
		int size = customDurations.size();
		for(int i=0; i<size-existedSize; i++) {
			this.clickAddCustomerDuration(j);
			ajax.waitLoading();
			this.waitLoading();
		}
		
	    this.setUpSlipFeeCustomDurationInfo(customDurations, j);
	}
	
	private void setUpSlipFeeCustomDurationInfo(List<SlipFeeCustomDuration> customDurations, int j){
		
		IHtmlObject[] tops =this.getSlipRateTR();
		IHtmlObject top = tops[j];

		for(int m=0;m<customDurations.size();m++)
		{
			this.setCustomDuration(customDurations.get(m).customDuration,m,top);
			this.setCustomDurationRate(customDurations.get(m).rate,m,top);
		}
				
		Browser.unregister(tops);
	}

	
	public List<SlipFeeCustomDuration> getCustomDurationByRangeAndUnit(String range, String unit/*'Length Range' or 'Length Increment'*/) {
		
		IHtmlObject[] tbls =this.getSlipRateTable(); 
		IHtmlObject tbl = tbls[0];
		
		List<SlipFeeCustomDuration> durations = new ArrayList<SlipFeeCustomDuration>();
		
		List<String> result= this.getSlipRange(unit);
		int size = result.size();
		int index = 9999;
		
		for(int i=0; i<size;i++)
		{
			if(result.get(i).equals(range))
			{
				index = i;
				break;
			}
		}
		if(index==9999)
		{
			throw new ErrorOnPageException("Cannot find fee range for -->range="+range+"; unit="+unit);
		}
		
		
		
		IHtmlObject[] custTbl = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("dyable_\\d+", false),tbl);
		IHtmlObject currentTbl = custTbl[index];
		
		
		IHtmlObject[] customDuration = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.duration",false)),currentTbl);
		IHtmlObject[] rate = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.fee",false)),currentTbl);
		

		int num = customDuration.length;		
		
		if(num<1)
		{
			logger.error("Cannot find any custom duration part....");
			return durations;
		}
		
		for(int m=0;m<num;m++)
		{
			
			SlipFeeCustomDuration tmp = new SlipFeeCustomDuration();
			
			tmp.customDuration = ((IText)customDuration[m]).getText();
			tmp.rate = ((IText)rate[m]).getText();
			durations.add(tmp);			
		}
				
		Browser.unregister(tbls);
		Browser.unregister(custTbl);
		Browser.unregister(customDuration);
		Browser.unregister(rate);
		
		return durations;
	}

	
	/**
	 * @param rate
	 * @param m
	 * @param currentTbl
	 */
	private void setCustomDurationRate(String rate, int m, IHtmlObject top) {
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.fee", false)), rate, false, m, top);
		
	}

	/**
	 * @param customDuration
	 * @param m
	 * @param currentTbl
	 */
	private void setCustomDuration(String customDuration, int m,
			IHtmlObject top) {
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.duration", false)), customDuration, false, m, top);
		
	}

	/**
	 * @param tbl
	 */
//	public void clickAddCustomerDuration(int index) {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Add Custom Duration",false,index);
//	}

	/**
	 * 
	 */
	public IHtmlObject[] getSlipRateTable() {
		IHtmlObject[] slipRateTbl = browser.getHtmlObject(".class", "Html.TABLE", ".id", "slip.rate.dtable");
		if(slipRateTbl.length<1)
		{
			Browser.unregister(slipRateTbl);
			throw new ErrorOnPageException("Cannot find any slip fee part on page-->id=slip.rate.dtable");
		}
		return slipRateTbl;
		
	}

	/**
	 * 
	 */
	private void clickAddIncrement() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Increment",false));
		
	}

	public void setRangeFeeRange(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.rangeFeet", false)), text, false, index, top);
	}
	
	public void setRangeFeeSeason(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.seasonFee", false)), text, false, index, top);
	}
	
	public void setRangeFeeDailyNightly(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.nightlyFee", false)), text, false, index, top);
	}
	
	public void setRangeFeeWeekly(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.weeklyFee", false)), text, false, index, top);
	}
	
	public void setRangeFeeMonthly(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.monthlyFee", false)), text, false, index, top);
	}
	
	
	
	public void selectUnselectRangeFeePerFoot(boolean isFeePerFoot, int index, IHtmlObject top)
	{
		if(isFeePerFoot)
		{
			browser.selectCheckBox(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.feePerFoot", false)), index, false, top);
		}else{
			browser.unSelectCheckBox(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.feePerFoot", false)), index, top);		
		}
		
	}
			
	public void setIncrementFeeIncrement(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.increment", false)), text, false, index, top);
	}
	
	public void setIncrementFeeSeason(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.seasonFee", false)), text, false, index, top);
	}
	
	public void setIncrementFeeMonthly(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.monthlyFee", false)), text, false, index, top);
	}
	public String getIncrementFeeMonthly(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.monthlyFee", false), index);
	}
	
	public void setIncrementFeeDaily(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.nightlyFee", false)), text, false, index, top);
	}
	
	public String getIncrementFeeDaily(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.nightlyFee", false), index);
	}
	
	public void setIncrementFeeWeekly(String text, int index, IHtmlObject top)
	{
		browser.setTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.weeklyFee", false)), text, false, index, top);
	}
	
	public String getIncrementFeeWeekly(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.weeklyFee", false), index);
	}
	
	public boolean isSeasonalSelected(){
		 return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), 0);
	}
	
	public boolean isLeaseSelected(){
		return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), 1);
	}
	
	public boolean isTransientSelected(){
		return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), 2);
	}
	
	public boolean isLengthRangeSelected(){
		return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit",false), 0);
	}
	
	public boolean isLengthIncrementsSelected(){
		return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit",false), 1);
	}
	
	public boolean isDailySelected(){
		return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.calculationMethod",false), 0);
	}
	
	public boolean isPercentageSelected(){
		return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.calculationMethod",false), 1);
	}
	
	public boolean checkCalculationMethodIsEnabled(){
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.calculationMethod",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found calculation method radio button object.");
		}
		boolean isEnabled = true;
		for(IHtmlObject obj : objs){
			IRadioButton radioButton = (IRadioButton)obj;
			isEnabled &= radioButton.isEnabled();
		}
		
		Browser.unregister(objs);
		return isEnabled;
	}
	/**
	 * Check whether 'Add Range' button is enabled
	 * @return
	 */
	public boolean checkAddRangeButtonIsEnabled(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Add Range");
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	/**
	 * get dock.
	 * @return
	 */
	public String getDock(){
		return browser.getTextFieldValue(".id", "assignment_loop");
	}
	
	
	/*
	 * get marina rate type
	 */
	public String getMarinaRateType(){
		String marinaTateType ="";
	     if(this.isSeasonalSelected()){
	    	 marinaTateType = this.Type_Seasonal;
	     }else if(this.isLeaseSelected()){
	    	 marinaTateType = this.Type_Lease;
	     }else if(this.isTransientSelected()){
	    	 marinaTateType = this.Type_Transient;
	     }
	     return marinaTateType;
	}
	
	public String getMarinaRateTypeForTransactionFee(){
		return browser.getDropdownListValue(".id",  new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false));
	}
	/**
	 * get unit
	 * @return
	 */
	public String getUnit(){
		String unit="";
		if(this.isLengthRangeSelected()){
			unit = this.LENGTH_RANGE;
		}else if(this.isLengthIncrementsSelected()){
			unit = this.LENGTH_INCREMENT;
		}
		return unit;
	}
	
	
	public String getCalculationMethod(){
		String method = "";
		if(this.isDailySelected()){
			method = this.Method_Calculation_Daily;
		}else if(this.isPercentageSelected()){
			method = this.Method_Calculation_Percentage;
		}
		return method;
	}
	
	public List<String> getCalculationMethodElements(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression("SlipFeeScheduleView-\\d+\\.calculationMethod",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Calculation Method Label object.");
		}
		List<String> elements = new ArrayList<String>();
		for(IHtmlObject obj: objs){
			String element = obj.text();
			elements.add(element);
		}
		
		Browser.unregister(objs);
		return elements;
	}

	/**
	 * set 'anyDay', 'monRate'... for ticket or permit
	 * @param fdForType
	 * @Return void
	 * @Throws
	 */
	public void setRateForTicketOrPersonType(FeeDataForPersonOrTicketType[] fdForType){
		for(int i=0;i<fdForType.length;i++){
			int index=i+1;
//			this.selectPersonType(fdForType[i].personOrTicketType, i);//2013.08.08 Quentine
			
			// for 306, Nicole, Feb 12, 2014 -- start
			// if unit is Flat by Range of Ticket Quantity, index = i
			if("Flat by Range of Ticket Quantity".equals(this.getTicketUnit())){
				this.selectPersonType(fdForType[i].personOrTicketType, i);
			} else if("Per Ticket".equals(this.getTicketUnit())){
				// if unit is Per Ticket, index = i+1
				this.selectPersonType(fdForType[i].personOrTicketType, index);
			}
			// for 306, Nicole, Feb 12, 2014 -- end
			
			if(StringUtil.notEmpty(fdForType[i].ticketAnyDayRate)){
				this.setAnyDayRate(fdForType[i].ticketAnyDayRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketMonRate)){
				this.setMonRate(fdForType[i].ticketMonRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketTueRate)){
				this.setTueRate(fdForType[i].ticketTueRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketWedRate)){
				this.setWedRate(fdForType[i].ticketWedRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketThuRate)){
				this.setThuRate(fdForType[i].ticketThuRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketFriRate)){
				this.setFriRate(fdForType[i].ticketFriRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketSatRate)){
				this.setSatRate(fdForType[i].ticketSatRate, index);
			}
			if(StringUtil.notEmpty(fdForType[i].ticketSunRate)){
				this.setSunRate(fdForType[i].ticketSunRate, index);
			}
			
			if(i!=fdForType.length-1){
				if(this.isAddPersonTypeExist())
				    this.clickAddPersonType();
				else
					this.clickAddTicketType();
				}
		}
	}
	public String setupTourTicketUseFee(FeeScheduleData fee) {
		return setupTourTicketUseFee(fee, 0);
	}

	/**
	 * Fill in all tour ticket fee fields and click apply, then retrieve the
	 * schedule id and then click on ok.
	 *
	 * @param fee
	 *            - fee schedule data
	 * @param index
	 *            - fee rate object index
	 * @return - fee schedule id
	 */
	public String setupTourTicketUseFee(FeeScheduleData fee, int index) {
		if (null != fee.loop && fee.loop.length() > 0) {
			this.selectAssignLoop(fee.loop);
		}
		if (null != fee.productGroup && fee.productGroup.length() > 0) {
			this.selectAssignProdGroup(fee.productGroup);
		}
		if (null != fee.product && fee.product.length() > 0) {
			this.selectAssignProduct(fee.product);
		}
		if (null != fee.effectDate && fee.effectDate.length() > 0) {
			this.setEffectiveDate(fee.effectDate);
		}
		if (null != fee.startInv && fee.startInv.length() > 0) {
			this.setInvStartDate(fee.startInv);
		}
		if (null != fee.endInv && fee.endInv.length() > 0) {
			this.setInvEndDate(fee.endInv);
		}
		if (null != fee.season && fee.season.length() > 0) {
			this.selectSeason(fee.season);
		}
		if (null != fee.salesChannel && fee.salesChannel.length() > 0) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if (null != fee.state && fee.state.length() > 0) {
			this.selectState(fee.state);
		}

		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
		}else{
			this.selectAccountCode(1);
		}

		if (null != fee.ticketCategory && fee.ticketCategory.length() > 0) {
			this.selectTicketCategory(fee.ticketCategory);
		}

		if (null != fee.rateType && fee.rateType.length() > 0) {
			if (fee.rateType.equalsIgnoreCase("Regular")) {
				this.selectRateType("4");
			} else if (fee.rateType.equalsIgnoreCase("Group")) {
				this.selectRateType("2");
			} else
				throw new ItemNotFoundException("Unknown type.");
		}
		if (null != fee.ticketUnit && fee.ticketUnit.length() > 0) {
			if (fee.ticketUnit.equalsIgnoreCase("Per Ticket")) {
				this.selectTicketUnit("1");
			} else if (fee.ticketUnit
					.equalsIgnoreCase("Flat by Range of Ticket Quantity")) {
				this.selectTicketUnit("3");
			} else
				throw new ItemNotFoundException("Unknown type.");
		}

		if (null != fee.ticketType && fee.ticketType.length() > 0) {
			this.selectTicketType(fee.ticketType);
		}

		// fee by any date
		if (null != fee.nightlyRate && fee.nightlyRate.length() > 0) {
			this.setDailyRate(fee.nightlyRate, index);
		}

		if(fee.personOrTypeData!=null){
			this.setRateForTicketOrPersonType(fee.personOrTypeData);
		}

		// fee by day of week
		if (null != fee.monRate && fee.monRate.length() > 0) {
			this.setMonRate(fee.monRate, index);
		}
		if (null != fee.tuesRate && fee.tuesRate.length() > 0) {
			this.setTueRate(fee.tuesRate, index);
		}
		if (null != fee.wedRate && fee.wedRate.length() > 0) {
			this.setWedRate(fee.wedRate, index);
		}
		if (null != fee.thurRate && fee.thurRate.length() > 0) {
			this.setThuRate(fee.thurRate, index);
		}
		if (null != fee.friRate && fee.friRate.length() > 0) {
			this.setFriRate(fee.friRate, index);
		}
		if (null != fee.satRate && fee.satRate.length() > 0) {
			this.setSatRate(fee.satRate, index);
		}
		if (null != fee.sunRate && fee.sunRate.length() > 0) {
			this.setSunRate(fee.sunRate, index);
		}

		this.clickApply();
		this.waitLoading();

		String feeID = this.getFeeSchID(); // get fee schedule id when create
		// new
		this.clickOK();

		return feeID;
	}

	/**
	 * Fill in all tour ticket transaction fee fields and click apply, then
	 * retrieve the schedule id and then click on ok.
	 *
	 * @param fee
	 *            - fee schedule data
	 * @param index
	 *            - fee rate object index
	 * @return - fee schedule id
	 */
	public String setupTourTicketTranFee(FeeScheduleData fee, int index) {
		if (null != fee.loop && fee.loop.length() > 0) {
			this.selectAssignLoop(fee.loop);
		}
		if (null != fee.productGroup && fee.productGroup.length() > 0) {
			this.selectAssignProdGroup(fee.productGroup);
		}
		if (null != fee.product && fee.product.length() > 0) {
			this.selectAssignProduct(fee.product);
		}
		if (null != fee.effectDate && fee.effectDate.length() > 0) {
			this.setEffectiveDate(fee.effectDate);
		}
		if (null != fee.startInv && fee.startInv.length() > 0) {
			this.setInvStartDate(fee.startInv);
		}
		if (null != fee.endInv && fee.endInv.length() > 0) {
			this.setInvEndDate(fee.endInv);
		}
		if (null != fee.season && fee.season.length() > 0) {
			this.selectSeason(fee.season);
		}
		if (null != fee.salesChannel && fee.salesChannel.length() > 0) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if (null != fee.state && fee.state.length() > 0) {
			this.selectState(fee.state);
		}

		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
		}

		// select transaction type and occurrence
		if (null != fee.tranType && fee.tranType.length() > 0) {
			this.selectTransactionType(fee.tranType);
		}
		if (null != fee.tranOccur && fee.tranOccur.length() > 0) {
			this.selectAccountCode(fee.tranOccur);
		}

		if (null != fee.ticketCategory && fee.ticketCategory.length() > 0) {
			this.selectTicketCategory(fee.ticketCategory);
		}
		if (null != fee.ticketType && fee.ticketType.length() > 0) {
			this.selectTicketType(fee.ticketType);
		}

		if (null != fee.tranFee && fee.tranFee.length() > 0) {
			this.setTransactionFee(fee.tranFee, index);
		}

		this.clickApply();
		this.waitLoading();

		String feeID = this.getFeeSchID(); // get fee schedule id when create
		// new
		this.clickOK();

		return feeID;
	}

	/**
	 * get the fee schedule id when update or create new after clicking apply
	 * button
	 *
	 * @return - fee schedule id
	 * @throws PageNotFoundException
	 */
	public String getFeeSchID() throws PageNotFoundException {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",".text", new RegularExpression("Fee Schedule ID.*",false));
		String temp = objs[0].getProperty(".text").toString();
		String feeID = temp.split("Fee Schedule ID")[1].split("Location")[0].trim();

		Browser.unregister(objs);
		return feeID;
	}

	/**
	 * This mehtod used to verify schedule details is same with given schedule
	 * data
	 *
	 * @param schedule
	 */
	public void verifyFeeScheduleDetails(FeeScheduleData schedule) {
		logger.info("Start to Verify fee schedule details.");

		int index = 0;
		if (schedule.feeType.equalsIgnoreCase("Ticket Fee")) {
			index = 1;
		}
		if (schedule.productCategory != null
				&& !schedule.productCategory.equals("")) {
			if (!schedule.productCategory.equalsIgnoreCase(this
					.getPrdCategory())) {
				throw new ItemNotFoundException("Product Category "
						+ getPrdCategory() + " not same with given value "
						+ schedule.productCategory);
			}
		}
		if (!schedule.feeType.equalsIgnoreCase(this.getFeeType())) {
			throw new ItemNotFoundException("Fee Type " + getFeeType()
					+ " not same with given value " + schedule.feeType);
		}
		if(!schedule.productCategory.equalsIgnoreCase("Slip"))
		{
			if (this.isAssignLooppExist()&& schedule.loop != null && !schedule.loop.equals("")) {
				if (!schedule.loop.equalsIgnoreCase(this.getAssignLoop())) {
					throw new ItemNotFoundException("Assignment Loop "
							+ getAssignLoop() + " not same with given value "
							+ schedule.loop);
				}
			}
		}
		
		if (schedule.productGroup != null && !schedule.productGroup.equals("")) {
			if (!schedule.productGroup.equalsIgnoreCase(this
					.getAssignProdGroup())) {
				throw new ItemNotFoundException("Assignment Product Group "
						+ getAssignProdGroup() + " not same with given value "
						+ schedule.productGroup);
			}
		}
		if (schedule.product != null && !schedule.product.equals("")) {
			if (!this.getAssignProduct().contains(schedule.product)) {
				throw new ItemNotFoundException("Assignment Product "
						+ getAssignProduct() + " not same with given value "
						+ schedule.product);
			}
		}
		if (schedule.effectDate != null && !schedule.effectDate.equals("")) {
			if (DateFunctions.compareDates(schedule.effectDate, getEffectiveDate()) !=0 ) {
				throw new ItemNotFoundException("Effective date "
						+ getEffectiveDate() + " not same with given value "
						+ schedule.effectDate);
			}
		}
		if (schedule.fromDate != null && !schedule.fromDate.equals("")) {
			if (DateFunctions.compareDates(schedule.fromDate, getEffectStartDate()) !=0 ) {
				throw new ItemNotFoundException("Effective start date "
						+ getEffectStartDate() + " not same with given value "
						+ schedule.fromDate);
			}
		}
		if (schedule.toDate != null && !schedule.toDate.equals("")) {
			if (DateFunctions.compareDates(schedule.toDate, getEffectEndDate()) !=0 ) {
				throw new ItemNotFoundException("Effective end date "
						+ getEffectEndDate() + " not same with given value "
						+ schedule.toDate);
			}
		}
		if (schedule.startInv != null && !schedule.startInv.equals("")) {
			if (DateFunctions.compareDates(schedule.startInv, getInvStartDate()) !=0 ) {
				throw new ItemNotFoundException("Inventory Start date "
						+ getInvStartDate() + " not same with given value "
						+ schedule.startInv);
			}
		}
		if (schedule.endInv != null && !schedule.endInv.equals("")) {
			if (DateFunctions.compareDates(schedule.endInv, getInvEndDate()) !=0 ) {
				throw new ItemNotFoundException("Inventory End date "
						+ getInvEndDate() + " not same with given value "
						+ schedule.endInv);
			}
		}
		if (schedule.season != null && !schedule.season.equals("")) {
			if (!schedule.season.equalsIgnoreCase(this.getSeason())) {
				throw new ItemNotFoundException("Season " + getSeason()
						+ " not same with given value " + schedule.season);
			}
		}
		if (schedule.salesChannel != null && !schedule.salesChannel.equals("")) {
			if (!schedule.salesChannel.equalsIgnoreCase(this.getSalesChannel())) {
				throw new ItemNotFoundException("Sales Channel "
						+ getSalesChannel() + " not same with given value "
						+ schedule.salesChannel);
			}
		}
		if(StringUtil.notEmpty(schedule.minimumUnitOfStay)){
			String temp = this.getMinimumUnitOfStay();
			if (!schedule.minimumUnitOfStay.equalsIgnoreCase(temp)) {
				throw new ItemNotFoundException("Minimum Unit of Stay "
						+ temp + " not same with given value "
						+ schedule.minimumUnitOfStay);
			}
		}
		if(StringUtil.notEmpty(schedule.minimumNumOfDayBeforeArrivalDay)){
			String temp = this.getMinimumNumOfDaysBeforeArrivalDate();
			if (!schedule.minimumNumOfDayBeforeArrivalDay.equalsIgnoreCase(temp)) {
				throw new ItemNotFoundException("Minimum Number of Day before arrival date "
						+ temp + " not same with given value "
						+ schedule.minimumNumOfDayBeforeArrivalDay);
			}
		}
		if (!StringUtil.isEmpty(schedule.state)) {
			String actualInOutState = this.getState();
			if (!schedule.state.equalsIgnoreCase(actualInOutState)) {
				throw new ItemNotFoundException("In/Out State " + actualInOutState
						+ " not same with given value " + schedule.state);
			}
		}
		if (schedule.custType != null && !schedule.custType.equals("")) {
			if (!schedule.custType.equalsIgnoreCase(this.getCustomerType())) {
				throw new ItemNotFoundException("Customer Type "
						+ getCustomerType() + " not same with given value "
						+ schedule.custType);
			}
		}
		if (schedule.unitOfStay != null && !schedule.unitOfStay.equals("")) {
			if (!schedule.unitOfStay.equalsIgnoreCase(this.getUnitOfStay())) {
				throw new ItemNotFoundException("Unit " + getUnitOfStay()
						+ " not same with given value " + schedule.unitOfStay);
			}
		}
		if (schedule.attrType != null && !schedule.attrType.equals("")) {
			if (!schedule.attrType.equalsIgnoreCase(this.getAttrType())) {
				throw new ItemNotFoundException("Attribute Type "
						+ getAttrType() + " not same with given value "
						+ schedule.attrType);
			}
		}
		if (schedule.acctCode != null && !schedule.acctCode.equals("")) {
			if (!schedule.acctCode.equalsIgnoreCase(this.getAccountCode())) {
				throw new ItemNotFoundException("Account Code "
						+ getAccountCode() + " not same with given value "
						+ schedule.acctCode);
			}
		}
		if (!StringUtil.isEmpty(schedule.rate) || !StringUtil.isEmpty(schedule.tranFee)) {
			String actualRate = this.getRate();
			String rate = (StringUtil.isEmpty(schedule.rate) ? schedule.tranFee : schedule.rate);
			if (Math.abs(Double.parseDouble(actualRate)-Double.parseDouble(rate))>0.0001) {
				throw new ItemNotFoundException("Rate " + actualRate
						+ " not same with given value " + (StringUtil.isEmpty(schedule.rate) ? schedule.tranFee : schedule.rate));
			}
		}
		if (schedule.nightlyRate != null && !schedule.nightlyRate.equals("")) {
			if (!schedule.nightlyRate
					.equalsIgnoreCase(this.getDailyRate(index))) {
				throw new ItemNotFoundException("Daily Rate "
						+ getDailyRate(index) + " not same with given value "
						+ schedule.nightlyRate);
			}
		}
		if (schedule.weeklyRate != null && !schedule.weeklyRate.equals("")) {
			if (!schedule.weeklyRate.equalsIgnoreCase(this.getWeeklyRate())) {
				throw new ItemNotFoundException("Weekly Rate "
						+ getWeeklyRate() + " not same with given value "
						+ schedule.weeklyRate);
			}
		}
		if (schedule.monthlyRate != null && !schedule.monthlyRate.equals("")) {
			if (!schedule.monthlyRate.equalsIgnoreCase(this.getMonthlyRate())) {
				throw new ItemNotFoundException("Monthly Rate "
						+ getMonthlyRate() + " not same with given value "
						+ schedule.monthlyRate);
			}
		}
		if (schedule.unitQuantity != null && !schedule.unitQuantity.equals("")) {
			if (!schedule.unitQuantity.equalsIgnoreCase(this.getCustUnitQty())) {
				throw new ItemNotFoundException("Unit Qty " + getCustUnitQty()
						+ " not same with given value " + schedule.unitQuantity);
			}
		}
		if (schedule.custRate != null && !schedule.custRate.equals("")) {
			if (!schedule.custRate.equalsIgnoreCase(this.getCustomRate())) {
				throw new ItemNotFoundException("Customer Rate "
						+ getCustomRate() + " not same with given value "
						+ schedule.custRate);
			}
		}
		if (schedule.monRate != null && !schedule.monRate.equals("")) {
			if (!schedule.monRate.equalsIgnoreCase(this.getMonRate(index))) {
				throw new ItemNotFoundException("Monday Rate "
						+ getMonRate(index) + " not same with given value "
						+ schedule.monRate);
			}
		}
		if (schedule.tuesRate != null && !schedule.tuesRate.equals("")) {
			if (!schedule.tuesRate.equalsIgnoreCase(this.getTueRate(index))) {
				throw new ItemNotFoundException("Tuesday Rate "
						+ getTueRate(index) + " not same with given value "
						+ schedule.tuesRate);
			}
		}
		if (schedule.wedRate != null && !schedule.wedRate.equals("")) {
			if (!schedule.wedRate.equalsIgnoreCase(this.getWedRate(index))) {
				throw new ItemNotFoundException("Wednesday Rate "
						+ getWedRate(index) + " not same with given value "
						+ schedule.wedRate);
			}
		}
		if (schedule.thurRate != null && !schedule.thurRate.equals("")) {
			if (!schedule.thurRate.equalsIgnoreCase(this.getThuRate(index))) {
				throw new ItemNotFoundException("Thursday Rate "
						+ getThuRate(index) + " not same with given value "
						+ schedule.thurRate);
			}
		}
		if (schedule.friRate != null && !schedule.friRate.equals("")) {
			if (!schedule.friRate.equalsIgnoreCase(this.getFriRate(index))) {
				throw new ItemNotFoundException("Friday Rate "
						+ getFriRate(index) + " not same with given value "
						+ schedule.friRate);
			}
		}
		if (schedule.satRate != null && !schedule.satRate.equals("")) {
			if (!schedule.satRate.equalsIgnoreCase(this.getSatRate(index))) {
				throw new ItemNotFoundException("Saturday Rate "
						+ getSatRate(index) + " not same with given value "
						+ schedule.satRate);
			}
		}
		if (schedule.sunRate != null && !schedule.sunRate.equals("")) {
			if (!schedule.sunRate.equalsIgnoreCase(this.getSunRate(index))) {
				throw new ItemNotFoundException("Sunday Rate "
						+ getSunRate(index) + " not same with given value "
						+ schedule.sunRate);
			}
		}
		if (schedule.tranType != null && !schedule.tranType.equals("")) {
			if (!schedule.tranType.equalsIgnoreCase(this.getTransactionType())) {
				throw new ItemNotFoundException("Transaction Type "
						+ getTransactionType() + " not same with given value "
						+ schedule.tranType);
			}
		}
		if (schedule.tranOccur != null && !schedule.tranOccur.equals("")) {
			if (!schedule.tranOccur.equalsIgnoreCase(this.getTransactionOccu())) {
				throw new ItemNotFoundException("Transaction Occurrence "
						+ getTransactionOccu() + " not same with given value "
						+ schedule.tranOccur);
			}
		}
		if (schedule.tranFee != null && !schedule.tranFee.equals("")) {
			if(new BigDecimal(schedule.tranFee).compareTo(new BigDecimal(this.getTransactionFee())) != 0){
				throw new ItemNotFoundException("Transaction Fee "
						+ getTransactionFee() + " not same value with given value "
						+ schedule.tranFee);
			}
		}
		
		if (schedule.tranFeeOption != null
				&& !schedule.tranFeeOption.equals("")) {
			if (!schedule.tranFeeOption.equalsIgnoreCase(this.getTransMethod())) {
				throw new ItemNotFoundException("Transaction Fee Method "
						+ getTransMethod() + " not same with given value "
						+ schedule.tranFeeOption);
			}
		}
		
		if(!StringUtil.isEmpty(schedule.rateApplyTo)) {
			if(!MiscFunctions.compareResult("Rate Applies To", schedule.rateApplyTo, this.getRateAppliesTo())) throw new ErrorOnPageException("Rate Applies To is not correct");
		}
		if(!StringUtil.isEmpty(schedule.tranFeeChangedUnits)) {
			if(!MiscFunctions.compareResult("Rate (Changed Units)", Double.parseDouble(schedule.tranFeeChangedUnits), Double.parseDouble(this.getTransactionFeeChangedUnits()))) throw new ErrorOnPageException("Rate (Changed Units) is not correct.");
		}
		if(!StringUtil.isEmpty(schedule.tranFeeFlatAmount)) {
			if(!MiscFunctions.compareResult("Rate (Flat Amount)", Double.parseDouble(schedule.tranFeeFlatAmount), Double.parseDouble(this.getTransactionFeeFlatAmount()))) throw new ErrorOnPageException("Rate (Flat Amount) is not correct.");
		}
		if(!StringUtil.isEmpty(schedule.maximumFeeRestriction)) {
			if(!MiscFunctions.compareResult("Maximum Fee Restriction", schedule.maximumFeeRestriction, this.getMaximumFeeRestriction())) throw new ErrorOnPageException("Maximum Fee Restriction is not correct.");
		}
		if(!StringUtil.isEmpty(schedule.maximumFeeRate)) {
			if(!MiscFunctions.compareResult("Maximum Fee Amount", schedule.maximumFeeRate, this.getMaximumFeeRate()));
		}
		
		
		//========================TICKET============================
		if(schedule.ticketTypes != null && schedule.ticketTypes.length>0 
				&& schedule.productCategory.equals("Ticket") 
				&& schedule.feeType.equalsIgnoreCase("Transaction Fee")){
			for(int i=0; i< schedule.ticketTypes.length; i++){
				if(schedule.tranType.equalsIgnoreCase("Transfer Tickets - Customer Transfer")
						||schedule.tranType.equalsIgnoreCase("Transfer Tickets - Tour Cancelled")
						||schedule.tranType.equalsIgnoreCase("Change Ticket Date/Time by Customer")
						||schedule.tranType.equalsIgnoreCase("Change Ticket Date/Time - Tour Cancelled")){
					String fromRate = this.getTransactionFee(i);
					String toRate = this.getTransactionFee_To(i);
					String anyDayRate = fromRate + "," + toRate;
					if(!schedule.anyDayRates[i].equals(anyDayRate)){
						throw new ItemNotFoundException("The From Rate and To Rate is not correct. Expect From rate and To rate should be " + schedule.anyDayRates[i] +
								"; but actually is " + anyDayRate);
					}
				}else if(schedule.tranFeeOption
						.equalsIgnoreCase("Flat by Range of Ticket Quantity")){
					for(int j=0; j<schedule.anyDayRates.length; j++){
						String range = this.getRange(j);
						String rangeRate = this.getRangeRate(j);
						if(!schedule.anyDayRanges[j].equals(range)){
							throw new ItemNotFoundException("The range is not correct. Expect range should be " + schedule.anyDayRanges[j] +
									"; but actually is " + range);
						}
						
						if(!schedule.anyDayRates[j].equals(rangeRate)){
							throw new ItemNotFoundException("The range Rate is not correct. Expect range rate should be " + schedule.anyDayRates[j] +
									"; but actually is " + rangeRate);
						}
					}
				}else {
					String rate = this.getTransactionFee(i);
					if(!schedule.anyDayRates[i].equals(rate)){
						throw new ItemNotFoundException("The Rate is not correct. Expect rate should be " + schedule.anyDayRates[i] +
								", but actually is " + rate);
					}
				}
				
				String ticketType;
				if(schedule.tranFeeOption
						.equalsIgnoreCase("Flat by Range of Ticket Quantity")){
					ticketType = this.getTicketTypeForFlatBy(i);
				}else {
					ticketType = this.getTicketType(i);
				}
				if(!schedule.ticketTypes[i].equals(ticketType)){
					throw new ItemNotFoundException("The ticket type is not correct. Expect ticket type should be " + schedule.ticketTypes[i] +
							"; but actullay is " + ticketType);
				}
				
			}
		}

		
		if(this.checkIsEmbedInTicketFeeExit()){
			boolean vaule = this.checkEmbedInTicketIsSelected();
			if(schedule.isEmbedInTicketFee != this.checkEmbedInTicketIsSelected()){
				throw new ErrorOnPageException("The embed in ticket fee check box selected should be " + schedule.isEmbedInTicketFee
						+ ", but actually is " + vaule);
			}
		}
		
		if (schedule.ticketCategory != null
				&& !schedule.ticketCategory.equals("")) {
			if (!schedule.ticketCategory.equalsIgnoreCase(this
					.getTicketCategory())) {
				throw new ItemNotFoundException("Ticket Category "
						+ getTicketCategory() + " not same with given value "
						+ schedule.ticketCategory);
			}
		}
		if (schedule.rateType != null && !schedule.rateType.equals("")) {
			if (!schedule.rateType.equalsIgnoreCase(this.getRateType())) {
				throw new ItemNotFoundException("Rate Type " + getRateType()
						+ " not same with given value " + schedule.rateType);
			}
		}
		if (schedule.ticketUnit != null && !schedule.ticketUnit.equals("")) {
			if (!schedule.ticketUnit.equalsIgnoreCase(this.getTicketUnit())) {
				throw new ItemNotFoundException("Ticket Unit "
						+ getTicketUnit() + " not same with given value "
						+ schedule.ticketUnit);
			}
		}
		if (schedule.ticketType != null && schedule.ticketType.length() > 0) {
			if (!schedule.ticketType.equalsIgnoreCase(this.getTicketType())) {
				throw new ItemNotFoundException("Ticket Type "
						+ getTicketType() + " not same with given value "
						+ schedule.ticketType);
			}
		}
		
		if(schedule.deliveryMethod != null && schedule.deliveryMethod.length() >0){
			String value = this.getDeliveryMethodValue();
			if(!schedule.deliveryMethod.equalsIgnoreCase(value)){
				throw new ErrorOnPageException("The Delivery method is not correct, expect is " + schedule.deliveryMethod  
						+", but actually is " + value);
			}
		}
		if(schedule.productCategory.equals("Slip")){
			if(null != schedule.dock && schedule.dock.length()>0){
				if(!schedule.dock.equals(schedule.dock)){
					throw new ErrorOnPageException("Dock", schedule.dock, this.getDock());
				}
			}
			if(null != schedule.marinaRateType && schedule.marinaRateType.length()>0){
				String value = "";
				if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
					value = this.getMarinaRateTypeForTransactionFee();
				}else{
					value = this.getMarinaRateType();
				}
				
				if(!schedule.marinaRateType.equals(value)){
					throw new ErrorOnPageException("Marina type", schedule.marinaRateType, value);
				}
			}
			if(null != schedule.unitOfStay && schedule.unitOfStay.length()>0){
				if(!schedule.unitOfStay.equals(this.getUnitOfStay())){
					throw new ErrorOnPageException("Unit of Stay", schedule.unitOfStay, this.getUnitOfStay());
				}
			}
			if(null != schedule.rafting && schedule.rafting.length()>0){
				if(!schedule.rafting.equals(this.getRafting())){
					throw new ErrorOnPageException("Rafting", schedule.rafting, this.getRafting());
				}
			}
			if(null != schedule.slipPricingUnit && schedule.slipPricingUnit.length()>0){
				String value = this.getUnit();
				if(!schedule.slipPricingUnit.equals(value)){
					throw new ErrorOnPageException("slipPricingUnit", schedule.slipPricingUnit, value);
				}
			}
			if(null != schedule.baseRatePerSeason && schedule.baseRatePerSeason.length()>0){
				if(StringUtil.compareNumStrings(schedule.baseRatePerSeason,this.getBasePerSeasonFee())!=0){
					throw new ErrorOnPageException("Base per season fee", schedule.baseRatePerSeason, this.getBasePerSeasonFee());
				}
			}
			if(null != schedule.minimumUseFee && schedule.minimumUseFee.length()>0){
				if(StringUtil.compareNumStrings(schedule.minimumUseFee,this.getMinimumUseFee())!=0){
					throw new ErrorOnPageException("Minimum use fee", schedule.minimumUseFee, this.getMinimumUseFee());
				}
			}
			if(null != schedule.calculationMethod && schedule.calculationMethod.length()>0){
				if(!schedule.calculationMethod.equals(this.getCalculationMethod())){
					throw new ErrorOnPageException("calculation method", schedule.calculationMethod, this.getCalculationMethod());
				}
			}
			if(null != schedule.slipFees && schedule.slipFees.size()>0){
				this.verifySlipFees(schedule.slipFees,schedule.slipPricingUnit);
			}
			if(null != schedule.pricingBasedonArrival && schedule.pricingBasedonArrival.size()>0){
				this.verifyPricingBasedOnArrival(schedule.pricingBasedonArrival);
			}
		}

	}
	
	public void verifySlipFees(List<SlipFee> slipFees,String unit){
		
			for(int i =0;i<slipFees.size();i++){
				String tempValue = "";
				if(null != slipFees.get(i).dailyNightlyFee && slipFees.get(i).dailyNightlyFee.length()>0){
					if(unit.equals(LENGTH_RANGE)){
						tempValue = this.getRangeSlipDailyNightFee(i);
					}else{
						tempValue = this.getIncrementFeeDaily(i);
					}
					
					if(StringUtil.compareNumStrings(slipFees.get(i).dailyNightlyFee, tempValue)!=0){
						throw new ErrorOnPageException("Daily night fee",slipFees.get(i).dailyNightlyFee,tempValue);
					}
				}
				if(null != slipFees.get(i).weeklyFee && slipFees.get(i).weeklyFee.length()>0){
					if(unit.equals(LENGTH_RANGE)){
						tempValue = this.getRangeSlipWeeklyFee(i);
					}else{
						tempValue = this.getIncrementFeeWeekly(i);
					}
					if(StringUtil.compareNumStrings(slipFees.get(i).weeklyFee,tempValue)!=0){
						throw new ErrorOnPageException("weekly fee",slipFees.get(i).weeklyFee,tempValue);
					}
				}
				if(null != slipFees.get(i).monthlyFee && slipFees.get(i).monthlyFee.length()>0){
					if(unit.equals(LENGTH_RANGE)){
						tempValue = this.getRangeSlipMonthlyFee(i);
					}else{
						tempValue = this.getIncrementFeeMonthly(i);
					}
					if(StringUtil.compareNumStrings(slipFees.get(i).monthlyFee,tempValue)!=0){
						throw new ErrorOnPageException("Monthly fee",slipFees.get(i).monthlyFee,tempValue);
					}
				}
				if(null != slipFees.get(i).perSeasonFee && slipFees.get(i).perSeasonFee.length()>0){
					if(unit.equals(LENGTH_RANGE)){
						tempValue = this.getLengthRangeSeasonFee(i);
					}else{
						tempValue = this.getIncrementPerSeasonFee(i);
					}
					if(StringUtil.compareNumStrings(slipFees.get(i).perSeasonFee,tempValue)!=0){
						throw new ErrorOnPageException("Per season fee",slipFees.get(i).perSeasonFee,tempValue);
					}
				}
				if(null != slipFees.get(i).increment && slipFees.get(i).increment.length()>0){
					tempValue = this.getIncremnets(i);
					if(StringUtil.compareNumStrings(slipFees.get(i).increment,tempValue)!=0){
						throw new ErrorOnPageException("Increments",slipFees.get(i).increment,tempValue);
					}
				}
			}
}


	

	public int getIndexForItem(ISelect dropDownList, String subItem) {

		// TestObject[] subItems = List_product().getChildren();

		int toReturn = -1;
		//
		// for(int i = 0; i< subItems.length ; i++){
		// String siteNum = "";
		// String[] siteNums =
		// subItems[i].getProperty(".text").toString().split("-");
		// if(siteNums.length < 2){
		// siteNum = "All";
		// }
		// if(siteNums.length == 2){
		// siteNum = siteNums[0];
		// }else if (siteNums.length > 2){
		// siteNum = siteNums[0]+ "-" + siteNums[1];
		// }
		//
		// if(subItem.equalsIgnoreCase(siteNum)){
		// if (toReturn == - 1){
		// toReturn = i;
		// }else{
		// toReturn = -2;
		// }
		//
		// }
		// }
		return toReturn;
	}

	/**
	 * select unit for permit
	 *
	 * @param unitvalue
	 *            :1.Per Person,2.Per Permit,3.Flat by Range of Group Size,4.Per
	 *            Person Per Day; 5. Per Person Per Period
	 */
	public void selectPermitUnit(String unitvalue) {
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		int index = -1;
		if(unitvalue.equalsIgnoreCase("Per Person")){index = 0;}             
		if(unitvalue.equalsIgnoreCase("Per Permit")){index = 1;}             
		if(unitvalue.equalsIgnoreCase("Flat by Range of Group Size")){index = 2;}             
		if(unitvalue.equalsIgnoreCase("Per Person Per Stay Unit")){index = 3;}             
		if(unitvalue.equalsIgnoreCase("Per Person Per Period")){index = 4;}             
		if(unitvalue.equalsIgnoreCase("Per Stay Unit")){index = 5;}
		if(index == -1){
			throw new ErrorOnPageException("Can not found radio button lable as:" + unitvalue);
		}
		browser.selectRadioButton(".id", "envtype", index);
		browser.waitExists(confirmDialog,this);
		if (confirmDialog.exists()) {
			confirmDialog.dismiss();
		}
	}
	
	public List<String> getFeeRateTypeElements(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".for","envtype");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Fee Rate Type Label object.");
		}
		List<String> elements = new ArrayList<String>();
		for(IHtmlObject obj: objs ){
			String value = obj.text();
			elements.add(value);
		}
		
		Browser.unregister(objs);
		return elements;
	}
	
	public void verifyApplyFeeValue(List<String> expectList){
		List<String> actualList = this.getFeeRateTypeElements();
		Collections.sort(expectList);
		Collections.sort(actualList);
		
		if(!expectList.equals(actualList)){
			throw new ErrorOnPageException("Apply Fee section is not correct!");
		}
	}

	public void clickAddRange(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Range", index);
	}
	
	public void clickAddRange(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Range");
	}
	

	// public void clickAddPersonType() {
	// Property[] p1 = new Property[2];
	// p1[0] = new Property(".class", "Html.TABLE");
	// p1[1] = new Property(".id", "additional_ticket_fees");
	// HtmlObject[] objs = browser.getHtmlObject(p1);
	//
	// Property[] p2 = new Property[2];
	// p2[0] = new Property(".class", "Html.A");
	// p2[1] = new Property(".text", "Add Person Type");
	//
	// browser.clickGuiObject(p2, false, 0, objs[0]);
	// }

	public void clickAddPersonTypeInTrans() {
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class", "Html.TABLE");
		p1[1] = new Property(".id", "transaction_fees");
		IHtmlObject[] objs = browser.getHtmlObject(p1);

		Property[] p2 = new Property[2];
		p2[0] = new Property(".class", "Html.A");
		p2[1] = new Property(".text", "Add Person Type");

		browser.clickGuiObject(p2, false, 0, objs[0]);
	}

	/**
	 * select person type for Per Person
	 *
	 * @param personType
	 */
	public void selectPersonType(String personType) {
		browser.selectDropdownList(".id", new RegularExpression(
				".*ticket_type", false), personType, 1);
	}

	/**
	 * select person type for Per Person
	 *
	 * @param personType
	 */
	public void selectPersonType(String personType, int index) {
		browser.selectDropdownList(".id", new RegularExpression(
				".*ticket_type", false), personType, index);
	}

	/**
	 * set any day rate
	 *
	 * @param anyDayRate
	 */
	public void setAnyDayRate(String anyDayRate) {
		browser.setTextField(".id", "duration_daily", anyDayRate, 1);
	}
	
	public void removeAllPricingBaseOn(String label){
		OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
		
		while(browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(label, false)))
		{
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(label, false));
			ajax.waitLoading();
			confirm.waitLoading();
			confirm.clickOK();
			ajax.waitLoading();
			this.waitLoading();			
		}
	}
	
	public void removeAllLengthRangeExceptTheDefaultOne(){
		OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
		RegularExpression regx = new RegularExpression("Remove Range", false);
		
		while(browser.checkHtmlObjectExists(".class", "Html.A", ".text", regx))
		{
			browser.clickGuiObject(".class", "Html.A", ".text", regx);
			ajax.waitLoading();
			browser.waitExists(confirm, this);
			if(confirm.exists()){
				confirm.clickOK();
				ajax.waitLoading();
				this.waitLoading();		
			}
		}
	}
	
	public void removeAllCustomDurationForSpecialLengthRange(int lrIndex){
	OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
		IHtmlObject[] tbls =this.getSlipRateTable(); 
		IHtmlObject tmp = tbls[0];
		
		IHtmlObject[] custTbl = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("dyable_\\d+", false),tmp);
		IHtmlObject currentTbl = custTbl[lrIndex];
		int customDurationItmeNum = browser.getHtmlObject(".class", "Html.A", ".text", "Remove Custom Duration", currentTbl).length;
		for(int i=0; i<customDurationItmeNum; i++)
		{
			browser.clickGuiObject(".class", "Html.A", ".text", "Remove Custom Duration", true, 0, currentTbl);
			ajax.waitLoading();
			browser.waitExists(confirm, this);
			if(confirm.exists()){
				confirm.clickOK();
				ajax.waitLoading();
				this.waitLoading();		
			}
		}
				
		Browser.unregister(tbls);
		Browser.unregister(custTbl);
	}
	
	
	public void removeAllRangeIncrement(){
		OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
		RegularExpression regx = new RegularExpression("Remove Increment", false);
		
		while(browser.checkHtmlObjectExists(".class", "Html.A", ".text", regx))
		{
			browser.clickGuiObject(".class", "Html.A", ".text", regx);
			ajax.waitLoading();
			browser.waitExists(confirm, this);
			if(confirm.exists()){
				confirm.clickOK();
				ajax.waitLoading();
				this.waitLoading();		
			}
		}
	}
	
	public void removeAllCustomDuration(){
		OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
		String label = "Remove Custom Duration";
		
		while(browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(label, false)))
		{
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(label, false));
			ajax.waitLoading();
			confirm.waitLoading();
			confirm.clickOK();
			ajax.waitLoading();
			this.waitLoading();			
		}
	}

	/**
	 * set any day rate
	 *
	 * @param anyDayRate
	 */
	public void setAnyDayRate(String anyDayRate, int index) {
		browser.setTextField(".id", new RegularExpression("duration_daily|.*_base_increment_rate$",false), anyDayRate, index);
	}

	/**
	 * set Permit Monday Rate
	 *
	 * @param permitMonRate
	 */
	public void setPermitMonRate(String permitMonRate) {
		browser.setTextField(".id", "day_mon", permitMonRate, 1);
	}

	/**
	 * set Permit Monday Rate
	 *
	 * @param permitMonRate
	 */
	public void setPermitMonRate(String permitMonRate, int index) {
		browser.setTextField(".id", "day_mon", permitMonRate, index);
	}

	/**
	 * set Permit Tuesday Rate
	 *
	 * @param permitTueRate
	 */
	public void setPermitTueRate(String permitTueRate) {
		browser.setTextField(".id", "day_tue", permitTueRate, 1);
	}

	/**
	 * set Permit Tuesday Rate
	 *
	 * @param permitTueRate
	 */
	public void setPermitTueRate(String permitTueRate, int index) {
		browser.setTextField(".id", "day_tue", permitTueRate, index);
	}

	/**
	 * set Permit Wednesday Rate
	 *
	 * @param permitWedRate
	 */
	public void setPermitWesRate(String permitWedRate) {
		browser.setTextField(".id", "day_wed", permitWedRate, 1);
	}

	/**
	 * set Permit Wednesday Rate
	 *
	 * @param permitWedRate
	 */
	public void setPermitWesRate(String permitWedRate, int index) {
		browser.setTextField(".id", "day_wed", permitWedRate, index);
	}

	/**
	 * set Permit thurday Rate
	 *
	 * @param permitThuRate
	 */
	public void setPermitThuRate(String permitThuRate) {
		browser.setTextField(".id", "day_thu", permitThuRate, 1);
	}

	/**
	 * set Permit thurday Rate
	 *
	 * @param permitThuRate
	 */
	public void setPermitThuRate(String permitThuRate, int index) {
		browser.setTextField(".id", "day_thu", permitThuRate, index);
	}

	/**
	 * set Permit Friday Rate
	 *
	 * @param permitFriRate
	 */
	public void setPermitFriRate(String permitFriRate) {
		browser.setTextField(".id", "day_fri", permitFriRate, 1);
	}

	/**
	 * set Permit Friday Rate
	 *
	 * @param permitFriRate
	 */
	public void setPermitFriRate(String permitFriRate, int index) {
		browser.setTextField(".id", "day_fri", permitFriRate, index);
	}

	/**
	 * set Permit Saturday Rate
	 *
	 * @param permitSatRate
	 */
	public void setPermitSatRate(String permitSatRate) {
		browser.setTextField(".id", "day_sat", permitSatRate, 1);
	}

	/**
	 * set Permit Saturday Rate
	 *
	 * @param permitSatRate
	 */
	public void setPermitSatRate(String permitSatRate, int index) {
		browser.setTextField(".id", "day_sat", permitSatRate, index);
	}

	/**
	 * set Permit Sunday Rate
	 *
	 * @param permitSunRate
	 */
	public void setPermitSunRate(String permitSunRate) {
		browser.setTextField(".id", "day_sun", permitSunRate, 1);
	}

	/**
	 * set Permit Sunday Rate
	 *
	 * @param permitSunRate
	 */
	public void setPermitSunRate(String permitSunRate, int index) {
		browser.setTextField(".id", "day_sun", permitSunRate, index);
	}

	/**
	 * select person type for nuit:Flat by Range of Group Size
	 *
	 * @param personTypeFlat
	 */
	public void selectPersonTypeFlat(String personTypeFlat) {
		browser.selectDropdownList(".id", "3_2_ticket_type", personTypeFlat);
		this.waitLoading();
	}
	
	public String getRange(int index){
		return browser.getTextFieldValue(".id", "3_2_increment", index+1);
	}
	
	public String getRangeRate(int index){
		return browser.getTextFieldValue(".id", "3_2_base_increment_rate", index+1);
	}
	
	public boolean checkRangeIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", "3_2_increment");
		if(objs.length<index+1){
			throw new ItemNotFoundException("Did not found range object with index = " + index+1);
		}
		boolean isEnable = objs[index+1].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public void setRangeRate(String rate,int index){
		browser.setTextField(".id", "3_2_base_increment_rate", rate, index+1);
	}
	
	public void setRange(String range,int index){
		browser.setTextField(".id", "3_2_increment", range, index+1);
	}
	
	public boolean checkRangeRateIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", "3_2_base_increment_rate");
		if(objs.length<index+1){
			throw new ItemNotFoundException("Did not found range rate object with index = " + index);
		}
		
		boolean isEnable = objs[index+1].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkRangeRateIsExisting(int index){
		boolean isExisting = true;
		IHtmlObject[] objs = browser.getTextField(".id", "3_2_base_increment_rate");
		
		if(objs.length<=index+1){
			isExisting = false;
		}
		
		Browser.unregister(objs);
		return isExisting;
	}

	/**
	 * select Increments or Ranges for Fees for Additional Occupants
	 *
	 * @param value
	 *            ----1.Increments 2.Ranges
	 */
	public void selectOccRadio(String value) {
		browser.selectRadioButton(".id", "occ_incr_type_id", ".value", value);
	}

	/**
	 * select Increments or Ranges Fees for Additional Vehicles
	 *
	 * @param value
	 *            ----1.Increments 2.Ranges
	 */
	public void selectVehRadio(String value) {
		browser.selectRadioButton(".id", "veh_incr_type_id", ".value", value);
		this.waitLoading();
	}

	/**
	 * set Increment Rate
	 *
	 * @param incrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setIncrementRate(String incrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_increment", incrementRate, 1);
	}

	public void setIncrementRate(String incrementRate, String prefix, int index) {
		browser
				.setTextField(".id", prefix + "_increment", incrementRate,
						index);
	}

	/**
	 * set Base Increment Rate
	 *
	 * @param BaseIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setBaseIncrementRate(String BaseIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_base_increment_rate",
				BaseIncrementRate, 1);
	}

	public void setBaseIncrementRate(String BaseIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_base_increment_rate",
				BaseIncrementRate, index);
	}

	public String getBaseIncrementRate(String prefix, int index) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", prefix + "_base_increment_rate"), index);
	}
	
	/**
	 * set Monday Increment Rate
	 *
	 * @param monIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setMonIncrementRate(String monIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_mon_increment_rate",
				monIncrementRate, 1);
	}

	public void setMonIncrementRate(String monIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_mon_increment_rate",
				monIncrementRate, index);
	}
    public boolean isMonIncrementRateExisted(String prefix){
    	return browser.checkHtmlObjectExists(".id", prefix + "_mon_increment_rate");
    }
	/**
	 * set Tuesday Increment Rate
	 *
	 * @param tueIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setTueIncrementRate(String tueIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_tue_increment_rate",
				tueIncrementRate, 1);
	}

	public void setTueIncrementRate(String tueIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_tue_increment_rate",
				tueIncrementRate, index);
	}
	
    public boolean isTueIncrementRateExisted(String prefix){
    	return browser.checkHtmlObjectExists(".id", prefix + "_tue_increment_rate");
    }
	/**
	 * set Wednesday Increment Rate
	 *
	 * @param wedIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setWedIncrementRate(String wedIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_wed_increment_rate",
				wedIncrementRate, 1);
	}

	public void setWedIncrementRate(String wedIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_wed_increment_rate",
				wedIncrementRate, index);
	}

    public boolean isWedIncrementRateExisted(String prefix){
    	return browser.checkHtmlObjectExists(".id", prefix + "_wed_increment_rate");
    }
    
	/**
	 * set Thurthday Increment Rate
	 *
	 * @param thuIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setThuIncrementRate(String thuIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_thu_increment_rate",
				thuIncrementRate, 1);
	}

	public void setThuIncrementRate(String thuIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_thu_increment_rate",
				thuIncrementRate, index);
	}

    public boolean isThuIncrementRateExisted(String prefix){
    	return browser.checkHtmlObjectExists(".id", prefix + "_thu_increment_rate");
    }
    
	/**
	 * set Friday Increment Rate
	 *
	 * @param friIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setFriIncrementRate(String friIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_fri_increment_rate",
				friIncrementRate, 1);
	}

	public void setFriIncrementRate(String friIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_fri_increment_rate",
				friIncrementRate, index);
	}

    public boolean isFriIncrementRateExisted(String prefix){
    	return browser.checkHtmlObjectExists(".id", prefix + "_fri_increment_rate");
    }
    
	/**
	 * set Saturday Increment Rate
	 *
	 * @param satIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setSatIncrementRate(String satIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_sat_increment_rate",
				satIncrementRate, 1);
	}

	public void setSatIncrementRate(String satIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_sat_increment_rate",
				satIncrementRate, index);
	}

    public boolean isSatIncrementRateExisted(String prefix){
    	return browser.checkHtmlObjectExists(".id", prefix + "_sat_increment_rate");
    }
    
	/**
	 * set Sunday Increment Rate
	 *
	 * @param sunIncrementRate
	 * @param prefix
	 *            ---1_1 1_2 2_1 2_2 3_2
	 */
	public void setSunIncrementRate(String sunIncrementRate, String prefix) {
		browser.setTextField(".id", prefix + "_sun_increment_rate",
				sunIncrementRate, 1);
	}

	public void setSunIncrementRate(String sunIncrementRate, String prefix,
			int index) {
		browser.setTextField(".id", prefix + "_sun_increment_rate",
				sunIncrementRate, index);
	}

	public boolean isSunIncrementRateExisted(String prefix){
		return browser.checkHtmlObjectExists(".id", prefix + "_sun_increment_rate");
	}
	   
	public boolean isMaximumUseFeeExisted(){
		return browser.checkHtmlObjectExists(".id", "max_use_fee");
	}
	
	public void setMaximumUseFee(String maximumUseFee){
		browser.setTextField(".id", "max_use_fee", maximumUseFee);
	}
	
	/** Click on Add Event/Holiday button */
	public void clickAddEventHoliday() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Add Event/Holiday");
	}

	/** Click on Add Person Type button */
	public void clickAddPersonType() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Person Type");
		this.waitLoading();
	}

	/**judge if 'Add Person Type' exists*/
	public boolean isAddPersonTypeExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Person Type");
	}

	/** Set Event/Holiday name, index start from 1 due to one hidden object. */
	public void setHolidayName(String name, int index) {
		browser.setTextField(".id", "holiday_name", name, index);
	}

	/** Set Event/Holiday rate, index start from 1 due to one hidden object. */
	public void setHolidayRate(String rate, int index) {
		browser.setTextField(".id", "holiday_rate", rate, index);
	}

	/**
	 * Set Event/Holiday inventory start date, index start from 1 due to one
	 * hidden object.
	 */
	public void setHolidayInvStart(String startDate, int index) {
		browser.setTextField(".id", "holiday_start_date_ForDisplay", startDate,
				index);
	}

	/**
	 * Set Event/Holiday inventory end date, index start from 1 due to one
	 * hidden object.
	 */
	public void setHolidayInvEnd(String endDate, int index) {
		browser.setTextField(".id", "holiday_end_date_ForDisplay", endDate,
				index);
	}

	/**
	 * verify fee schedule object could edited or not
	 * @param id
	 * @return
	 */
	public boolean isFeeSchdIDObjectEditable(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.SPAN", ".text", new RegularExpression("^Fee Schedule ID.*",false));
		boolean editable=!browser.checkHtmlObjectExists(".class","Html.SPAN",".text",new RegularExpression("^\\d+$", false), objs[0]); ////check fee schedule id is span,as span area can not be edit
		Browser.unregister(objs);
		return editable;
	}

	/**
	 * verify location object could edited or not
	 * @param loc
	 * @return
	 */
	public boolean isLocationObjectEditable(){
		IHtmlObject[] objs=browser.getHtmlObject(".className", "inputwithrubylabel", ".text",new RegularExpression("^Location.*",false));
		boolean editable = !browser.checkHtmlObjectExists(".class","Html.SPAN", objs[0]); //added by pzhu 2012/2/23
		Browser.unregister(objs);
	    return editable;
	}

	/**
	 * verify rate type object could edited or not
	 * @return
	 */
	public boolean isRateTypeEditable(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", "rate_type_id");
//		for(){
			String value=objs[0].getProperty("isDisabled");
			Browser.unregister(objs);
		    if("false".equalsIgnoreCase(value)){
		    	return true;
		    }else{
		    	return false;
		    }
//		}
	}

	/**
	 * verify product category object could edited or not
	 * @param prd
	 * @return
	 */
	public boolean isProductCategoryEditable(String prd){
		IHtmlObject[] objs=browser.getDropdownList(".id", "prd_grp_cat_id");
		ISelect list=(ISelect)objs[0];
		List<String> value=list.getAllOptions();
		Browser.unregister(objs);
	    if(value.size()==1 && prd.equals(value.get(0))){
	    	return false;
	    }else{
	    	return true;
	    }
	}

	/**
	 * verify fee type object could edited or not
	 * @param type
	 * @return
	 */
	public boolean isFeeTypeEditable(String type){
		IHtmlObject[] objs=browser.getDropdownList(".id", "fee_type");
		ISelect list=(ISelect)objs[0];
		List<String> value=list.getAllOptions();
		Browser.unregister(objs);
	    if(value.size()==1 && type.equals(value.get(0))){
	    	return false;
	    }else{
	    	return true;
	    }
	}

	/**
	 * verify unit object could edited or not
	 * @param unit
	 * @return
	 */
	public boolean isUnitEditable(String unit){
		IHtmlObject[] objs=browser.getDropdownList(".id", "unit_of_stay_type");
		ISelect list=(ISelect)objs[0];
		List<String> value=list.getAllOptions();
		Browser.unregister(objs);
	    if(value.size()==1 && unit.equals(value.get(0))){
	    	return false;
	    }else{
	    	return true;
	    }
	}


	/**
	 * verify object in the edit fee schedule page could not be edit
	 * @param schedule
	 */
	public void verifyScheduleObjectCannotEdit(FeeScheduleData schedule){
		logger.info("Verify object in the edit fee schedule page could not be edit.");

		if(this.isFeeSchdIDObjectEditable()){
			throw new ItemNotFoundException("fee schedule id could not be edited .");
		}
		if(this.isLocationObjectEditable()){
			throw new ItemNotFoundException("location could not be edited .");
		}
		if(this.isProductCategoryEditable(schedule.productCategory)){
			throw new ItemNotFoundException("product category could not be edited .");
		}
		if(!"".equals(schedule.rateType) && this.isRateTypeEditable()){
			throw new ItemNotFoundException("rate type could not be edited .");
		}
		if(!"".equals(schedule.unitOfStay) && this.isUnitEditable(schedule.unitOfStay)){
			throw new ItemNotFoundException("unit of stay could not be edited .");
		}
	}

	public void selectDeliveryMethod(String method_name) {
		browser.selectDropdownList(".id", "delivery_method", method_name);		
	}
	
	public boolean checkDeliveryMethodEnable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", "delivery_method");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found delivery method drop down list object.");
		}
		ISelect deliveryMethodDropDown = (ISelect) objs[0];
		boolean enable = deliveryMethodDropDown.isEnabled();
		
		return enable;
	}
	
	public boolean checkAddTicketTypeIsExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add Ticket Type");
	}
	
	public String getDeliveryMethodValue(){
		return browser.getDropdownListValue(".id", "delivery_method");
	}
	
	private boolean checkPerTypeIsEnable(String value){
		IHtmlObject[] objs = browser.getRadioButton(".id", "envtype", ".value", value);

		if(objs.length<1){
			throw new ItemNotFoundException("Did not found fee per type radio button with value = " + value);
		}

		IRadioButton radioButton = (IRadioButton)objs[0];

		boolean isEnable = radioButton.isEnabled();
		Browser.unregister(objs);

		return isEnable;
	}
	
	public boolean checkPerTransactionIsEnable(){
		return this.checkPerTypeIsEnable("2");
	}
	
	public boolean checkFlatbyRangeIsEnable(){
		return this.checkPerTypeIsEnable("3");
	}
	
	public boolean checkPerUnitIsEnable(){
		return this.checkPerTypeIsEnable("1");
	}
	
	public boolean checkEmbedInTicketIsEnable(){
		IHtmlObject[] objs = browser.getCheckBox(".id", "embed_fee");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the Embed In Ticket check box object.");
		}
		
		ICheckBox checkBoxObs = (ICheckBox)objs[0];
		
		boolean isEnable = checkBoxObs.isEnabled();
		Browser.unregister(objs);
		
		return isEnable;
	}
	
	public boolean checkEmbedInTicketIsSelected(){
		IHtmlObject[] objs = browser.getCheckBox(".id", "embed_fee");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the Embed In Ticket check box object.");
		}
		
		ICheckBox checkBoxObs = (ICheckBox)objs[0];
		boolean isSelected = checkBoxObs.isSelected();
		
		Browser.unregister(objs);
		
		return isSelected;
	}
	
	public void verifyTicketTypeDropDownList(boolean multiOption, boolean isFlatBy){
		List<String> ticketTypeListAct;
		if(isFlatBy){
			ticketTypeListAct = this.getTicketTypeListElementsForFlatBy();
		}else{
			ticketTypeListAct = this.getTicketTypeListElements();
		}
		
		
		if(multiOption){
			if(ticketTypeListAct.size()<2){
				throw new ErrorOnPageException("The ticket type option drop down list should have multi option.");
			}else{
				logger.info("The ticket type option drop down list have multi option.");
			}
		}else{
			if(ticketTypeListAct.size() != 1){
				throw new ErrorOnPageException("The ticket type option drop down list should have one option.");
			}else{
				logger.info("The ticket type option drop down list have sigle option.");
			}
		}
		
		boolean result = MiscFunctions.compareResult("The ticket type option", "All", ticketTypeListAct.get(0));
		if(!result){
			throw new ErrorOnPageException("The ticket type option is not correct. Please check log.");
		}
	}
	
	public void verifyEmbedInTicketIsEnable(boolean expIsEnable){
		logger.info("Verify Embed In Ticket enable info.");
		boolean actIsEnable = this.checkEmbedInTicketIsEnable();
		boolean result = MiscFunctions.compareResult("Embed In Ticket Enable", expIsEnable, actIsEnable);
		if(!result){
			throw new ErrorOnPageException("Embed In Ticket Enable info is not correct, please check log.");
		}else{
			logger.info("Embed In Ticket Enable info is correct");
		}
	}
	
	public void verifyAddTicketTypeButtonIsEnable(boolean expIsEnable){
		logger.info("Verify Ticket Type button enable info");
		boolean actIsEnable = this.checkAddTicketTypeIsExisting();
		
		boolean result =MiscFunctions.compareResult("Add Ticket Type Button", expIsEnable, actIsEnable);
		if(!result){
			throw new ErrorOnPageException("Add Ticket Type button Enable info is not correct, please check log.");
		}else {
			logger.info("Add Ticket Type button Enable info is correct");
		}			
	}
	
	public void verifyDeliveryMethodInfo(boolean isEnable, String expValue){
		logger.info("Verify Delivery method whether enable.");
	
		boolean isEnableAct = this.checkDeliveryMethodEnable();
		boolean result = MiscFunctions.compareResult("Delivery Method", isEnable, isEnableAct);
		
		String actulValue = this.getDeliveryMethodValue();
		result &= MiscFunctions.compareResult("Delivery Method Default Value", expValue, actulValue);
		
		if(!result){
			throw new ErrorOnPageException("Delivery method info is not correct, please check log.");			
		}else{
			logger.info("Delivery method info is correct.");
		}		
	}
	
	public void verifyFeePerTypeWhetherEnableByTransactionType(boolean isPerTranEnable,
			boolean isFlatByEnable, boolean isPerUnitEnabl, boolean isEmbedEnable){
		boolean result = true;
		
		boolean isPerTranEnableAct = this.checkPerTransactionIsEnable();	
		boolean isFlatByEnableAct = this.checkFlatbyRangeIsEnable();
		boolean isPerUnitEnableAct = this.checkPerUnitIsEnable();
		boolean isEmabedEnableAct = this.checkEmbedInTicketIsEnable();
		
		result &= MiscFunctions.compareResult("Per Transaction Enable info", isPerTranEnable, isPerTranEnableAct);
		result &= MiscFunctions.compareResult("Flat by Range of Ticket Quantity", isFlatByEnable, isFlatByEnableAct);
		result &= MiscFunctions.compareResult("Per Unit", isPerUnitEnabl, isPerUnitEnableAct);
		result &= MiscFunctions.compareResult("Embed In Ticket", isEmbedEnable, isEmabedEnableAct);
		if(!result){
			throw new ErrorOnPageException("Fee Per Type is not correct by Transaction type, please check log.");
		}else{
			logger.info("Fee Per Type is  correct by Transaction type. ");
		}
	}
	/**
	 * check rafting indicator exist or not.
	 * @return
	 */
	public boolean checkRaftingExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.raftingType",false));
	}
	
	public boolean checkRaftingIsEnabled(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.raftingType",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Rafting drop down list object.");
		}
		ISelect dropdown = (ISelect)objs[0];
		boolean isEnabled = dropdown.isEnabled();
		
		Browser.unregister(objs);
		return isEnabled;
	}
	/**
	 * verify rafting not exist.
	 */
	public void verifyRaftingNotexist(){
		boolean isExist = this.checkRaftingExist();
		if(isExist){
			throw new ErrorOnPageException("Rafting indicator was not exist");
		}
	}
	/**
	 * verify unit selected.
	 * @param Unit
	 */
	public void verifyUnitSelected(String Unit){
        boolean isChecked = true;
		if(Unit.equals(this.LENGTH_RANGE)){
			isChecked= browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit",false), 0);
		}else if(Unit.equals(this.LENGTH_INCREMENT)){
			isChecked = browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.slipPricingUnit",false), 1);
		}
		if(!isChecked){
			throw new ErrorOnPageException("The unit " + Unit + "should be selected");
		}
	}
	/**
	 * verify marina rate type was selected.
	 * @param rateType
	 */
	public void verifyMmRateTypeSelected(String rateType){
		 boolean isChecked = true;
		 if(rateType.equals(this.Type_Seasonal)){
			 isChecked= browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), 0);
		 }else if(rateType.equals(this.Type_Lease)){
			 isChecked= browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), 1); 
		 }else if(rateType.equals(this.Type_Transient)){
			 isChecked= browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false), 2); 
		 }
		 if(!isChecked){
				throw new ErrorOnPageException("The marina rate type " + rateType + "should be selected");
		}
	}
	/**
	 * verify pricing base on arrival info.
	 * @param priceingBase
	 */
	public void verifyPricingBasedOnArrival(List<PricingBase> priceingBase){
			for(int i =0;i<priceingBase.size();i++){
				String tempValue = "";
				if(null != priceingBase.get(i).startMonth && priceingBase.get(i).startMonth.length()>0){
					tempValue = this.getArrivalStartMonth(i);
					if(!priceingBase.get(i).startMonth.equals(tempValue)){
						throw new ErrorOnPageException("Arrival start month",priceingBase.get(i).startMonth,tempValue);
					}
				}
				if(null != priceingBase.get(i).startDate && priceingBase.get(i).startDate.length()>0){
					tempValue = this.getArrivalStartDate(i);
					if(!priceingBase.get(i).startDate.equals(tempValue)){
						throw new ErrorOnPageException("Arrival start date",priceingBase.get(i).startDate,tempValue);
					}
				}
				if(null != priceingBase.get(i).endMonth && priceingBase.get(i).endMonth.length()>0){
					tempValue = this.getArrivalEndMonth(i);
					if(!priceingBase.get(i).endMonth.equals(tempValue)){
						throw new ErrorOnPageException("Monthly fee",priceingBase.get(i).endMonth,tempValue);
					}
				}
				if(null != priceingBase.get(i).endDate && priceingBase.get(i).endDate.length()>0){
					tempValue = this.getArrivalEndDate(i);
					if(!priceingBase.get(i).endDate.equals(tempValue)){
						throw new ErrorOnPageException("Per season fee",priceingBase.get(i).endDate,tempValue);
					}
				}
				if(null != priceingBase.get(i).percent && priceingBase.get(i).percent.length()>0){
					tempValue = this.getArrivalPercent(i);
					if(!priceingBase.get(i).percent.equals(tempValue)){
						throw new ErrorOnPageException("Increments",priceingBase.get(i).percent,tempValue);
					}
				}
			}
}
	
	public void verifyPricingBasedOnDeparture(List<PricingBase> priceingBase){
		for(int i =0;i<priceingBase.size();i++){
			String tempValue = "";
			if(null != priceingBase.get(i).startMonth && priceingBase.get(i).startMonth.length()>0){
				tempValue = this.getDepartureStartMonth(i);
				if(!priceingBase.get(i).startMonth.equals(tempValue)){
					throw new ErrorOnPageException("Arrival start month",priceingBase.get(i).startMonth,tempValue);
				}
			}
			if(null != priceingBase.get(i).startDate && priceingBase.get(i).startDate.length()>0){
				tempValue = this.getDepartureStartDate(i);
				if(!priceingBase.get(i).startDate.equals(tempValue)){
					throw new ErrorOnPageException("Arrival start date",priceingBase.get(i).startDate,tempValue);
				}
			}
			if(null != priceingBase.get(i).endMonth && priceingBase.get(i).endMonth.length()>0){
				tempValue = this.getDepartureEndMonth(i);
				if(!priceingBase.get(i).endMonth.equals(tempValue)){
					throw new ErrorOnPageException("Monthly fee",priceingBase.get(i).endMonth,tempValue);
				}
			}
			if(null != priceingBase.get(i).endDate && priceingBase.get(i).endDate.length()>0){
				tempValue = this.getDepartureEndDate(i);
				if(!priceingBase.get(i).endDate.equals(tempValue)){
					throw new ErrorOnPageException("Per season fee",priceingBase.get(i).endDate,tempValue);
				}
			}
			if(null != priceingBase.get(i).percent && priceingBase.get(i).percent.length()>0){
				tempValue = this.getDeparturePercent(i);
				if(!priceingBase.get(i).percent.equals(tempValue)){
					throw new ErrorOnPageException("Increments",priceingBase.get(i).percent,tempValue);
				}
			}
		}
	}
	/**
	 * check attribute enable..
	 * @param id
	 * @param index
	 * @return
	 */
	public boolean checkAttributeEnable(String id){
		boolean enable = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(id,false));
		if(objs.length<1){
			throw new ErrorOnPageException("No Specific Element was found");
		}
		String isEnable = objs[0].getProperty("isDisabled");
		if(!isEnable.equals("false")){
			enable =  false;
			}
		Browser.unregister(objs);
		return enable;
	}
	
	/**
	 * check slip fee attribute enable.
	 * @param id
	 * @param index
	 * @return
	 */
	public boolean checkSlipFeeAttrEnable(String id, int index){
		boolean enable = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(id,false));
		if(objs.length<1){
			throw new ErrorOnPageException("No Specific Element was found");
		}
		String isEnable = objs[index].getProperty("isDisabled");
		if(!isEnable.equals("false")){
			enable =  false;
		}
		Browser.unregister(objs);
		return enable;
	}
	/**
	 * check base rate daily night.
	 * @return
	 */
	public boolean checkBaseRateDailyNightEnable(){
		return this.checkAttributeEnable("duration_daily");
	}
	
	public boolean checkBaseRateWeeklyEnable(){
		return this.checkAttributeEnable("duration_weekly");
	}
	
	public boolean checkBaseRateMonthlyEnable(){
		return this.checkAttributeEnable("duration_monthly");
	}
	
	public boolean checkBaseFullStayMultiRateEnable(){
		return this.checkAttributeEnable("multi_unit_rate");
	}
	
	public boolean checkSlipIncrementsEnable(int index){
		return this.checkSlipFeeAttrEnable("SlipFeeIncrementView-\\d+\\.increment", index);
	}
	
	public boolean checkSlipDailyEnable(int index){
		return this.checkSlipFeeAttrEnable("SlipFeeIncrementView-\\d+\\.nightlyFee", index);
	}
	
	public boolean checkSlipWeeklyEnable(int index){
		return this.checkSlipFeeAttrEnable("SlipFeeIncrementView-\\d+\\.weeklyFee", index);
	}
	
	public boolean checkSlipMonthlyEnable(int index){
		return this.checkSlipFeeAttrEnable("SlipFeeIncrementView-\\d+\\.monthlyFee", index);
	}
	
	public boolean checkLinkEnable(String text){
		boolean enable = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", text);
		if(objs.length<1){
			throw new ErrorOnPageException("No Specific Element was found");
		}
		String isEnable = objs[0].getProperty("isDisabled");
		if(!isEnable.equals("false")){
			enable =  false;
		}
		Browser.unregister(objs);
		return enable;
	}
	
	public boolean checkAddIncrementsEnable(){
		return  this.checkLinkEnable("Add Increment");
	}
	
	public boolean checkRemoveIncrementEnable(){
		return  this.checkLinkEnable("Remove Increment");
	}
	/**
	 * check length increments base rate section enable.
	 * @return
	 */
	public boolean checkLengthIncrementsBaseRateSectionEnable(){
		boolean isEnable = true;
		if(!this.checkBaseFullStayMultiRateEnable()){
			isEnable &= false;
			logger.info("Full stay req'd for Multi-unit Rate check box should be enable");
		}
		if(!this.checkBaseRateDailyNightEnable()){
			isEnable &= false;
			logger.info("Base Rate daily/Nightly should be enable");
		}
		if(!this.checkBaseRateMonthlyEnable()){
			isEnable &= false;
			logger.info("Base Rate Monthly should be enable");
		}
		if(!this.checkBaseRateWeeklyEnable()){
			isEnable &= false;
			logger.info("Base Rate weekly should be enable");
		}
		
		return isEnable;
	}
	
	public boolean checkLengthIncrementsSlipFee(List<SlipFee> slipFees){
		boolean isEnable = true;
		if(null != slipFees && slipFees.size()>0){
			for(int i=0;i<slipFees.size();i++){
				if(!this.checkSlipIncrementsEnable(i)){
					isEnable &= false;
					logger.info("Slip fee increment should be enable");
				}
				if(!this.checkSlipDailyEnable(i)){
					isEnable &= false;
					logger.info("Slip fee daily should be enable");
				}
				if(!this.checkSlipWeeklyEnable(i)){
					isEnable &= false;
					logger.info("Slip fee weekly should be enable");
				}
				if(!this.checkSlipMonthlyEnable(i)){
					isEnable &= false;
					logger.info("Slip fee monthly should be enable");
				}
			}
		}
		return isEnable;
	}
	/**
	 * verify fee Increment remove success.
	 * @param slipFees
	 */
	public void verifyFeeIncrementRemoveSuccess(){
		IHtmlObject[] obj = browser.getTableTestObject(".id", "slip.rate.dtable");
		if(obj.length<1){
			throw new ErrorOnPageException("No table exist");
		}
		 IHtmlObject[] trObj = browser.getHtmlObject(".id", new RegularExpression("171732001_\\d+", false), obj[0]);
		 if(trObj.length>=1){
			 throw new ErrorOnPageException("Increment tr still exist");
		 }
		 Browser.unregister(obj);
		 Browser.unregister(trObj);
	}
	
	public boolean checkRemoveArrivalPercentEnable(){
		return this.checkLinkEnable("Remove Arrival Percent");
	}
	
	public boolean checkRemoveDeparturePercentEnable(){
		return this.checkLinkEnable("Remove Departure Percent");
	}
	
	public boolean checkAddArrivalPercentEnable(){
		return this.checkLinkEnable("Add Arrival Percent");
	}
	public boolean checkAddDeparturePercentEnable(){
		return this.checkLinkEnable("Add Departure Percent");
	}
	
	public boolean checkArrivalStartMonthEnalbe(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth");
	}
	
	public boolean checkArrivalStartDateEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.startDateDay");
	}
	
	public boolean checkArrivalEndMonthEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.endDateMonth");
	}
	
	public boolean checkArriavalEndDateEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.endDateDay");
	}
	
	public boolean checkArrivalPercent(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.percent");
	}
	
	public boolean checkDepartureStartMonthEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth");
	}
	
	public boolean checkDepartureStartDateEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.startDateDay");
	}
	
	public boolean checkDepartureEndMonthEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.endDateMonth");
	}
	public boolean checkDepartureEndDateEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.endDateDay");
	}
	
	public boolean checkDeparturePercentEnable(){
		return this.checkAttributeEnable("SlipArrivalDeparturePricingView-\\d+\\.percent");
	}
	
	
	public void verifyPricingBaseOnArrivalInfoEnable(){
		boolean isEnable = true;
		if(!this.checkAddArrivalPercentEnable()){
			isEnable &= false;
			logger.error("Add arrival percent link should enable");
		}if(!this.checkRemoveArrivalPercentEnable()){
			isEnable &= false;
			logger.error("Remove arrival percent link should enable");
		}if(!this.checkArrivalStartMonthEnalbe()){
			isEnable &= false;
			logger.error("Arrival start month should enable");
		}if(!this.checkArrivalStartDateEnable()){
			isEnable &= false;
			logger.error("Arrival start date should enable");
		}if(!this.checkArrivalEndMonthEnable()){
			isEnable &= false;
			logger.error("Arrival end month date should enable");
		}if(!this.checkArrivalPercent()){
			isEnable &= false;
			logger.error("Arrival percent should enable");
		}
		if(!isEnable){
			throw new ErrorOnPageException("Pricing Based on Prrival info should enable");
		}
	}
	
	public void verifyPricingBasedOnDepartureEnable(){
		boolean isEnable = true;
		if(!this.checkAddDeparturePercentEnable()){
			isEnable &= false;
			logger.error("add departure percent link should enable");
		}if(!this.checkRemoveDeparturePercentEnable()){
			isEnable &= false;
			logger.error("Remove departure percent link should enable");
		}if(!this.checkDepartureEndMonthEnable()){
			isEnable &= false;
			logger.error("Departure start month should enable");
		}if(!this.checkDepartureStartDateEnable()){
			isEnable &= false;
			logger.error("Departure start date should enable");
		}if(!this.checkDepartureEndMonthEnable()){
			isEnable &= false;
			logger.error("Departure end month should enable");
		}if(!this.checkDepartureEndDateEnable()){
			isEnable &= false;
			logger.error("Departure end date should enable");
		}
		if(!isEnable){
			throw new ErrorOnPageException("Pricing Based on departure should enable");
		}
	}
	/**
	 * click remove arrival percent.
	 */
	public void clickRemoveArrivalPercent(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Arrival Percent",index);
	}
	/**
	 * click remove departure percent.
	 */
	public void clickRemoveDeparturePercent(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Departure Percent",index);
	}
	/**
	 * verify no pricing based info exist.
	 */
	public void verifyNoPricingBaseInfoExist(){
		IHtmlObject[] topArrivalObjs = this.getPricingBasedOnArrivalTr();
		IHtmlObject[] arrivalTableobjs = browser.getHtmlObject(".id", new RegularExpression("dyable_\\d+",false),topArrivalObjs[0]);
		if(arrivalTableobjs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		IHtmlObject[] arruvalTr =  browser.getHtmlObject(".class", "Html.TR",arrivalTableobjs[0]);
		if(arruvalTr.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		
		String pricingArrival = arruvalTr[0].getProperty(".id");
		if(pricingArrival.length()>0){
			throw new ErrorOnPageException("Pricing Vased on arrival info still exist");
		}
		IHtmlObject[] topDepartureObjs = this.getPricingBasedOnArrivalTr();
		if(topDepartureObjs.length>1){
			throw new ErrorOnPageException("There shoud just have button tr exist");
		}
		IHtmlObject[] departureTableobjs = browser.getHtmlObject(".id", new RegularExpression("dyable_\\d+",false),topDepartureObjs[0]);
		if(departureTableobjs.length<1){
			throw new ErrorOnPageException("Pricing Vased on departure info still exist");
		}
		IHtmlObject[] departureTr = browser.getHtmlObject(".class","Html.TR",departureTableobjs[0]);
		if(departureTr.length<1){
			throw new ErrorOnPageException("Pricing Vased on departure info still exist");
		}
		
		String pricingDeparture = departureTr[0].getProperty(".id");
		if(pricingDeparture.length()>0){
			throw new ErrorOnPageException("Pricing Vased on departure info still exist");
		}
		Browser.unregister(topArrivalObjs);
		Browser.unregister(arrivalTableobjs);
		Browser.unregister(topDepartureObjs);
		Browser.unregister(departureTableobjs);
	}
	
	/**
	 * This method was used to get multiple Range/Increment in Fee section
	 * @return
	 */
	public IHtmlObject[] getSlipRateTR() {
		Property[] properties=new Property[3];
		properties[0]=new Property(".class", "Html.TR");
		properties[1]=new Property(".text", new RegularExpression("(Range\\(>=\\))|(Increment\\(for every\\)).*", false));
		properties[2]=new Property(".id", new RegularExpression("\\d+_\\d", false));
		IHtmlObject[] slipRateTbl = browser.getHtmlObject(properties);
		if(slipRateTbl.length<1)
		{
			Browser.unregister(slipRateTbl);
			throw new ErrorOnPageException("Cannot find any slip fee part on page-->text=Range(>=)");
		}
		return slipRateTbl;
		
	}
	
	public int getSlipRateTRObjectLength(){
		IHtmlObject[] slipRateTR = this.getSlipRateTR();
		int length  = slipRateTR.length;
		
		Browser.unregister(slipRateTR);
		return length;
	}

	public void selectUnselectFullStayForMultiUnitRate(boolean isfullStayForMultiUnitRate, IHtmlObject top) {
		if(isfullStayForMultiUnitRate)
			browser.selectCheckBox(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.fullStayForMultiUnitRate", false)), 0, false, top);
		else
			browser.unSelectCheckBox(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.fullStayForMultiUnitRate", false)), 0, top);		
	}
	
	public boolean isFeePerFootCheckBoxExisted(IHtmlObject top) {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.feePerFoot", false), top);
	}
	
	public boolean isFullStayMultiUnitRateCheckBoxExisted(IHtmlObject top) {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.fullStayForMultiUnitRate", false)), top);
	}
	
	private void setSlipDayOfWeekRate(String dayOfWeek, String rate, IHtmlObject top) {
		browser.setTextField(".id", new RegularExpression("SlipFeeRangeView-\\d+\\." + dayOfWeek+ "Fee", false), rate, top);
	}
	
	public void setSlipMondayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("monday", rate, top);
	}
	
	public void setSlipTuesdayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("tuesday", rate, top);
	}
	
	public void setSlipWednesdayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("wednesday", rate, top);
	}
	
	public void setSlipThursdayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("thursday", rate, top);
	}
	
	public void setSlipFridayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("friday", rate, top);
	}
	
	public void setSlipSaturdayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("saturday", rate, top);
	}
	
	public void setSlipSundayRate(String rate, IHtmlObject top) {
		this.setSlipDayOfWeekRate("sunday", rate, top);
	}
	
	public void removeAllEventHolidays(IHtmlObject top) {
		Property properties[] = new Property[] {new Property(".class", "Html.A"), new Property(".text", "Remove Event/Holiday")};
		
		if(browser.checkHtmlObjectExists(properties, top)) {
			browser.clickGuiObject(properties, true, 0, top);
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	public void setEventHolidays(List<SlipFeeEventHoliday> holidays, int topIndex) {
		this.addEventHoliday(holidays.size(), topIndex);
		IHtmlObject tops[] = this.getSlipRateTR();
		this.setEventHolidaysValue(holidays, tops[topIndex]);
	}
	
	private void addEventHoliday(int holidaysSize, int topIndex) {
		IHtmlObject[] tops = this.getSlipRateTR();
		this.removeAllEventHolidays(tops[topIndex]);
		
		for(int i = 0; i < holidaysSize; i ++) {
			this.addEventHoliday(tops[topIndex]);
		}
	}
	
	private void setEventHolidaysValue(List<SlipFeeEventHoliday> holidays, IHtmlObject top) {
//		HtmlObject[] tops = this.getSlipRateTR();
//		this.removeAllEventHolidays(tops[topIndex]);
		
		for(int i = 0; i < holidays.size(); i ++) {
//			tops = this.getSlipRateTR();
//			this.addEventHoliday(tops[topIndex]);
			
			this.setEventHolidayName(holidays.get(i).name, top, i);
			this.setEventHolidayDailyNightlyRate(holidays.get(i).dailyNightlyRate, top, i);
			this.setEventHolidayInventoryStartDate(holidays.get(i).inventoryStart, top, i);
			this.setEventHolidayInventoryEndDate(holidays.get(i).inventoryEnd, top, i);
		}
	}
	
	public void addEventHoliday(IHtmlObject top) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Event/Holiday", true, 0, top);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setEventHolidayName(String name, IHtmlObject top, int index) {
		browser.setTextField(".id", new RegularExpression("SlipFeeRangeHolidayRateView-\\d+\\.name", false), name, false, index, top);
	}
	
	public void setEventHolidayDailyNightlyRate(String rate, IHtmlObject top, int index) {
		browser.setTextField(".id", new RegularExpression("SlipFeeRangeHolidayRateView-\\d+\\.rate", false), rate, false, index, top);
	}
	
	public void setEventHolidayInventoryStartDate(String startDate, IHtmlObject top, int index) {
		browser.setCalendarField(".id", new RegularExpression("SlipFeeRangeHolidayRateView-\\d+\\.startDate_ForDisplay", false), startDate, false, index, top);
	}
	
	public void setEventHolidayInventoryEndDate(String endDate, IHtmlObject top, int index) {
		browser.setCalendarField(".id", new RegularExpression("SlipFeeRangeHolidayRateView-\\d+\\.endDate_ForDisplay", false), endDate, false, index, top);
	}
	
	public List<String> getLengthRangeFeeLableElements(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + index);
		
		IHtmlObject[] labelObjs = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression("SlipFeeRangeView-\\d+.*",false), rangeSections[index]);
		if(labelObjs.length<1){
			throw new ItemNotFoundException("Did not get length range fee label object");
		}
		
		List<String> lableElements = new ArrayList<String>();
		for(IHtmlObject labelObj: labelObjs){
			String value = labelObj.text();
			lableElements.add(value);
		}
		
		Browser.unregister(rangeSections);
		Browser.unregister(labelObjs);
		return lableElements;
	}
	
	public boolean isFullStayMultiUnitRateCheckBoxExisted(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + index);
		
		boolean isExisting = this.isFullStayMultiUnitRateCheckBoxExisted(rangeSections[index]);
		
		Browser.unregister(rangeSections);
		return isExisting;
	}
	
	public boolean isAddCustomerDurationButtonExisting(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + index);
		
		boolean isExisting = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Custom Duration", rangeSections[index]);
		Browser.unregister(rangeSections);
		return isExisting;
	}
	
	public void clickAddCustomerDuration(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + index);
		
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Custom Duration", true, 0, rangeSections[index]);
		Browser.unregister(rangeSections);
	}
	
	public void clickRemoveCustomerDuration(int indexOfFee, int indexOfCustDur){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<indexOfFee)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + indexOfFee);
		
		Property[] properties=new Property[3];
		properties[0]=new Property(".class", "Html.TR");
		properties[1]=new Property(".text", new RegularExpression("^Custom Duration.*", false));
		properties[2]=new Property(".id", new RegularExpression("\\d+_\\d", false));
		IHtmlObject[] custDurations = browser.getHtmlObject(properties, rangeSections[indexOfFee]);
		if(custDurations.length<indexOfCustDur){
			throw new ItemNotFoundException("Did not found Customer Duration TR object with index is " + indexOfCustDur);
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Custom Duration", true, 0, custDurations[indexOfCustDur]);
		Browser.unregister(rangeSections);
		Browser.unregister(custDurations);
	}
	
	public List<String> getCustomerDurationLabelValue(int indexOfFee, int indexOfCustDur){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<indexOfFee)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + indexOfFee);
		
		Property[] properties=new Property[3];
		properties[0]=new Property(".class", "Html.TR");
		properties[1]=new Property(".text", new RegularExpression("^Custom Duration.*", false));
		properties[2]=new Property(".id", new RegularExpression("\\d+_\\d", false));
		IHtmlObject[] custDurations = browser.getHtmlObject(properties, rangeSections[indexOfFee]);
		if(custDurations.length<indexOfCustDur){
			throw new ItemNotFoundException("Did not found Customer Duration TR object with index is " + indexOfCustDur);
		}
		
		IHtmlObject[] labelObjs = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression("SlipFeeCustomDurationView-\\d+.*",false), custDurations[indexOfCustDur]);
		if(labelObjs.length<1){
			throw new ItemNotFoundException("Did not found Custom Duration Label object");
		}
		
		List<String> elements = new ArrayList<String>();
		for(IHtmlObject labelObj: labelObjs){
			String value = labelObj.text();
			elements.add(value);
		}
		
		Browser.unregister(rangeSections);
		Browser.unregister(custDurations);
		Browser.unregister(labelObjs);
		return elements;
	}
	
	public int getCustomerDurationInfoSize(int indexOfFee){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<indexOfFee)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + indexOfFee);
		
		Property[] properties=new Property[3];
		properties[0]=new Property(".class", "Html.TR");
		properties[1]=new Property(".text", new RegularExpression("^Custom Duration.*", false));
		properties[2]=new Property(".id", new RegularExpression("\\d+_\\d", false));
		IHtmlObject[] custDurations = browser.getHtmlObject(properties, rangeSections[indexOfFee]);
		int size = custDurations.length;
		Browser.unregister(rangeSections);
		Browser.unregister(custDurations);
		return size;
	}
	
	public boolean isFeePerFootCheckBoxSelected(int index) {
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + index);
		boolean isSelected = this.isFeePerFootCheckBoxSelected(rangeSections[index]);
		Browser.unregister(rangeSections);
		return isSelected;
	}
	
	public boolean isFullStayForMultiUnitRateCheckBoxSelected(int index) {
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Did not found range fee TR object with index is " + index);
		
		boolean isSelected = this.isFullStayForMultiUnitRateCheckBoxSelected(rangeSections[index]);
		Browser.unregister(rangeSections);
		return isSelected;
	}
	
	public boolean checkRangeFeetIsEnabled(int index){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.rangeFeet", false));
		if(objs.length<index){
			throw new ItemNotFoundException("Did not get Range Fee Text Object with index is " + index);
		}
		
		boolean isEnabled = objs[index].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public String getRangeFeet(IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.rangeFeet", false)), top);
	}
		
	public String getIncrement(IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("SlipFeeIncrementView-\\d+\\.increment", false)), top);
	}
	
	public String getRangeFeet(int index){
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<index)
			throw new ItemNotFoundException("Could range fee TR object with index is " + index);
		
		String value =  this.getRangeFeet(rangeSections[index]);
		
		Browser.unregister(rangeSections);
		return value;
	}
	
	public String getDailyNightlyFeeValue(IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("(SlipFeeRangeView|SlipFeeIncrementView)-\\d+\\.nightlyFee", false)), top);
	}
	
	public String getWeeklyFeeValue(IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("(SlipFeeRangeView|SlipFeeIncrementView)-\\d+\\.weeklyFee", false)), top);
	}
	
	public String getMonthlyFeeValue(IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("(SlipFeeRangeView|SlipFeeIncrementView)-\\d+\\.monthlyFee", false)), top);
	}
	
	public boolean isFeePerFootCheckBoxSelected(IHtmlObject top) {
		return browser.isCheckBoxSelected(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.feePerFoot", false), top);
	}
	
	public boolean isFullStayForMultiUnitRateCheckBoxSelected(IHtmlObject top) {
		return browser.isCheckBoxSelected(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.feePerFoot", false), top);
	}
	
	public String getCustomDurationValue(int index, IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.duration", false)), index, top);
	}
	
	public String getCustomDurationRate(int index, IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.fee", false)), index, top);
	}
	
	public IHtmlObject getRangeRecordTR(int index){
		IHtmlObject[] sections=this.getSlipRateTR();
		if(sections.length<index+1)
			throw new ItemNotFoundException("Could not the range for fee.");
		logger.info("Found "+sections.length+" range sections from UI.");
		return sections[index];
	}
	
	
	public List<SlipFeeCustomDuration>  getCustomDuration(IHtmlObject top) {
		List<SlipFeeCustomDuration> customDurations = new ArrayList<SlipFeeCustomDuration>();
		
		IHtmlObject[] durations=browser.getHtmlObject(".class", "Html.INPUT.text", ".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.duration", false), top);
		if(durations.length<1)
			return null;//no custom duration was found
		logger.info("Found "+durations.length+" custom durations for current range fee.");
		
		for(int i=0;i<durations.length;i++) {
			SlipFeeCustomDuration duration=new SlipFeeCustomDuration();
			duration.customDuration=getCustomDurationValue(i, top);
			duration.rate=getCustomDurationRate(i, top);
			customDurations.add(duration);
		}
		Browser.unregister(durations);
		return customDurations;
	}
	
	public List<SlipFee> getSlipUseFees(String reservationType, String slipPricingUnit) {
		List<SlipFee> slipFeesUI=new ArrayList<SlipFee>();
		
		IHtmlObject[] sections=this.getSlipRateTR();
		if(sections.length<1)
			throw new ItemNotFoundException("Could not find any range for fee.");
		logger.info("Found "+sections.length+" range sections from UI.");

		for(int i=0;i<sections.length;i++) {
			FeeScheduleData schedule = new FeeScheduleData();
			SlipFee slipFee=schedule.new SlipFee();
			if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)){
				if(slipPricingUnit.equals("Length Range")) {
					slipFee.rangeFeet=getRangeFeet(sections[i]);
					if(this.isFeePerFootCheckBoxExisted(sections[i]))
						slipFee.isFeePerFoot=isFeePerFootCheckBoxSelected(sections[i]);
					if(this.isFullStayMultiUnitRateCheckBoxExisted(sections[i]))
						slipFee.isfullStayForMultiUnitRate=isFullStayForMultiUnitRateCheckBoxSelected(sections[i]);
				} else if(slipPricingUnit.equals("Length Increments"))
					slipFee.increment=getIncrement(sections[i]);
				else
					throw new ErrorOnDataException("Un-handled use fee unit "+slipPricingUnit);
				slipFee.dailyNightlyFee=getDailyNightlyFeeValue(sections[i]);
				slipFee.weeklyFee=getWeeklyFeeValue(sections[i]);
				slipFee.monthlyFee=getMonthlyFeeValue(sections[i]);
			} else if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)){
				if(slipPricingUnit.equals("Length Range")) {
					slipFee.rangeFeet=getRangeFeet(sections[i]);
					if(this.isFeePerFootCheckBoxExisted(sections[i]))
						slipFee.isFeePerFoot=isFeePerFootCheckBoxSelected(sections[i]);
				} else if(slipPricingUnit.equals("Length Increments"))
					slipFee.increment=getIncrement(sections[i]);
				else
					throw new ErrorOnDataException("Un-handled use fee unit "+slipPricingUnit);
				slipFee.monthlyFee=getMonthlyFeeValue(sections[i]);
			} else if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)){
				if(slipPricingUnit.equals("Length Range")) {
					slipFee.rangeFeet=getRangeFeet(sections[i]);
				} else if(slipPricingUnit.equals("Length Increments"))
					slipFee.increment=getIncrement(sections[i]);
				else
					throw new ErrorOnDataException("Un-handled use fee unit "+slipPricingUnit);
				slipFee.perSeasonFee=getPerSeasonValue(sections[i]);
				if(this.isFeePerFootCheckBoxExisted(sections[i]))
					slipFee.isFeePerFoot=isFeePerFootCheckBoxSelected(sections[i]);
			}else
				throw new ErrorOnDataException("Un-handled reservation type "+reservationType);
			
			List<SlipFeeCustomDuration> customDurations = getCustomDuration(sections[i]);
			if(customDurations != null)
				slipFee.customDurations.addAll(customDurations);	
			slipFeesUI.add(slipFee);
		}
		Browser.unregister(sections);
		
		return slipFeesUI;
	}
	
	public FeeScheduleData getSlipUseFee() {
		logger.info("Get Slip Use fee info from UI.");
		
		FeeScheduleData schedule = new FeeScheduleData();
		schedule.productCategory=this.getPrdCategory();
		schedule.feeType=this.getFeeType();
		schedule.dock=this.getAssignLoop();
		schedule.productGroup=this.getAssignProdGroup();
		schedule.product=this.getAssignProduct();
		schedule.effectDate=DateFunctions.formatDate(this.getEffectiveDate(), "MM/dd/yyyy");
		schedule.startInv=DateFunctions.formatDate(this.getInvStartDate(), "MM/dd/yyyy");
		schedule.endInv=DateFunctions.formatDate(this.getInvEndDate(), "MM/dd/yyyy");
		schedule.season=this.getSeason();
		schedule.salesChannel=this.getSalesChannel();
		schedule.state=this.getState();
		schedule.custType=this.getCustomerType();
		schedule.boatCategory=this.getBoatCategory();
		schedule.acctCode=this.getAccountCode();
		schedule.marinaRateType=this.getMarinaRateType();
		schedule.unitOfStay=this.getUnitOfStay();
		if(this.isRaftingTypeDropdownListExisted())
			schedule.rafting=this.getRafting();
		schedule.slipPricingUnit=this.getUnit();
		if(this.isMultiUnitCheckBoxExisted())
			schedule.isFullStayMultiunit=isMultiUnitCheckBoxSelected();
		schedule.slipFees.addAll(this.getSlipUseFees(schedule.marinaRateType, schedule.slipPricingUnit));
		schedule.minimumUseFee=this.getMinimumUseFee();
		schedule.calculationMethod=this.getCalculationMethod();
		if(schedule.calculationMethod.equals("Percentage")) {
			List<PricingBase> pricingOnArrival=getSlipPricingBasedonArrival();
			if(pricingOnArrival!=null)
				schedule.pricingBasedonArrival.addAll(pricingOnArrival);
			List<PricingBase> pricingOnDeparture=getSlipPricingBasedonDeparture();
			if(pricingOnDeparture!=null)
				schedule.pricingBasedonDeparture.addAll(pricingOnDeparture);
		}
		return schedule;
	}
	
	public void compareSlipUseFeeDetails(FeeScheduleData schedule) {
		FeeScheduleData scheduleUI=getSlipUseFee();
		
		logger.info("Compare Slip Use fee details info.");
		boolean passed=true;
		
		passed &= MiscFunctions.compareResult("Product Category", schedule.productCategory, scheduleUI.productCategory);
		passed &= MiscFunctions.compareResult("Fee Type", schedule.feeType, scheduleUI.feeType);
		passed &= MiscFunctions.compareResult("Product Group", schedule.productGroup, scheduleUI.productGroup);
		passed &= MiscFunctions.compareResult("Product", schedule.product, scheduleUI.product);
		passed &= MiscFunctions.compareResult("Effective Date", schedule.effectDate, scheduleUI.effectDate);
		passed &= MiscFunctions.compareResult("Inventory Start Date", schedule.startInv, scheduleUI.startInv);
		passed &= MiscFunctions.compareResult("Inventory End Date", schedule.endInv, scheduleUI.endInv	);
		passed &= MiscFunctions.compareResult("Season", schedule.season, scheduleUI.season);
		passed &= MiscFunctions.compareResult("Sales Channel", schedule.salesChannel, scheduleUI.salesChannel);
		passed &= MiscFunctions.compareResult("In/Out State", schedule.state, scheduleUI.state);
		passed &= MiscFunctions.compareResult("Customer Type", schedule.custType, scheduleUI.custType);
		passed &= MiscFunctions.compareResult("Boat Category", schedule.boatCategory, scheduleUI.boatCategory);
		passed &= MiscFunctions.compareResult("Marina Rate Type", schedule.marinaRateType, scheduleUI.marinaRateType);
		passed &= MiscFunctions.compareResult("Unit of stay", schedule.marinaRateType, scheduleUI.marinaRateType);
		if(this.isRaftingTypeDropdownListExisted())
			passed &= MiscFunctions.compareResult("Rafting", schedule.rafting, scheduleUI.rafting);
		passed &= MiscFunctions.compareResult("Unit", schedule.slipPricingUnit, scheduleUI.slipPricingUnit);
		passed &= MiscFunctions.compareResult("Minimum Use Fee", schedule.minimumUseFee, scheduleUI.minimumUseFee);
		passed &= MiscFunctions.compareResult("Calculation Method", schedule.calculationMethod, scheduleUI.calculationMethod);
		
		if(schedule.slipPricingUnit.equals("Length Range")) {
			logger.info("Compare fee info for 'Length Range' type.");
			passed &= compareSlipUseFeeForLengthRange(schedule.slipFees, scheduleUI.slipFees, schedule.marinaRateType);
		} else if(schedule.slipPricingUnit.equals("Length Increments")) {
			logger.info("Compare fee info for 'Length Increments' type.");
			passed &= compareSlipUseFeeForLengthIncrements(schedule.slipFees, scheduleUI.slipFees, schedule.marinaRateType);
			passed &= MiscFunctions.compareResult("Full Stay Req'd for Multi-unit Rate", schedule.isFullStayMultiunit, scheduleUI.isFullStayMultiunit);
		} else{
			throw new ErrorOnDataException("Un-handled unit type:"+schedule.unit);
		}
		
		if(schedule.calculationMethod.equals("Percentage")) {
			if(schedule.pricingBasedonArrival.size()>0)
				passed &= compareSlipPricingBasedOnArrivalOrDeparture(schedule.pricingBasedonArrival, scheduleUI.pricingBasedonArrival);
			if(schedule.pricingBasedonDeparture.size()>0)
				passed &= compareSlipPricingBasedOnArrivalOrDeparture(schedule.pricingBasedonDeparture, scheduleUI.pricingBasedonDeparture);
		}
		
		if(!passed)
			throw new ErrorOnPageException("Failed to compare Slip Use fee details. Please check log for more details.");
		logger.info("Verify slip use fee details successfully.");
	}
	
	public boolean compareSlipUseFeeForLengthRange(List<SlipFee>  slipFees, List<SlipFee> slipFeesUI, String reservationType) {
		if(slipFees.size() != slipFeesUI.size())
			throw new ErrorOnPageException("Slip Use fee size was not correct."+" Expected fees size:"+slipFees.size()+" Actual fees size:"+slipFeesUI.size());
				
		boolean passed=true;
		for(int i=0;i<slipFees.size();i++) {
			SlipFee fee=slipFees.get(i);
			boolean found=false;
			for(int j=0;j<slipFeesUI.size();j++) {
				if(fee.rangeFeet.equals(slipFeesUI.get(j).rangeFeet)) {
					found=true;
					if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)){
						passed &= MiscFunctions.compareResult("Daily/Nightly Fee", fee.dailyNightlyFee, slipFeesUI.get(j).dailyNightlyFee);
						passed &= MiscFunctions.compareResult("Weekly Fee", fee.weeklyFee, slipFeesUI.get(j).weeklyFee);
						passed &= MiscFunctions.compareResult("Monthly Fee", fee.monthlyFee, slipFeesUI.get(j).monthlyFee);
						passed &= MiscFunctions.compareResult("Fee Per Foot", fee.isFeePerFoot, slipFeesUI.get(j).isFeePerFoot);
						passed &= MiscFunctions.compareResult("Full Stay Req'd for Multi-unit Rate", fee.isfullStayForMultiUnitRate, slipFeesUI.get(j).isfullStayForMultiUnitRate);
					}
					if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)){
						passed &= MiscFunctions.compareResult("Monthly Fee", fee.monthlyFee, slipFeesUI.get(j).monthlyFee);
						passed &= MiscFunctions.compareResult("Fee Per Foot", fee.isFeePerFoot, slipFeesUI.get(j).isFeePerFoot);
					}
					if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)){
						passed &= MiscFunctions.compareResult("Per Season Fee", fee.perSeasonFee, slipFeesUI.get(j).perSeasonFee);
						passed &= MiscFunctions.compareResult("Fee Per Feet", fee.isFeePerFoot, slipFeesUI.get(j).isFeePerFoot);
					}
					if(fee.customDurations.size()>0) {
						passed &=this.compareSlipUseFeeForCustomDuration(fee.customDurations, slipFeesUI.get(j).customDurations);
					}
					break;
				}
			}
			
			if(!found)
				throw new ErrorOnPageException("Could not find slip fee for range "+fee.rangeFeet+" from UI.");
		}
		
		return passed;
	}
	
	public boolean compareSlipUseFeeForLengthIncrements(List<SlipFee>  slipFees, List<SlipFee> slipFeesUI, String reservationType) {
		if(slipFees.size() != slipFeesUI.size())
			throw new ErrorOnPageException("Slip Use fee size was not correct."+" Expected fees size:"+slipFees.size()+" Actual fees size:"+slipFeesUI.size());
				
		boolean passed=true;
		for(int i=0;i<slipFees.size();i++) {
			SlipFee fee=slipFees.get(i);
			boolean found=false;
			for(int j=0;j<slipFeesUI.size();j++) {
				if(fee.increment.equals(slipFeesUI.get(j).increment)) {
					found=true;
					if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)){
						passed &= MiscFunctions.compareResult("Daily/Nightly Fee", fee.dailyNightlyFee, slipFeesUI.get(j).dailyNightlyFee);
						passed &= MiscFunctions.compareResult("Weekly Fee", fee.weeklyFee, slipFeesUI.get(j).weeklyFee);
						passed &= MiscFunctions.compareResult("Monthly Fee", fee.monthlyFee, slipFeesUI.get(j).monthlyFee);
					}
					if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)){
						passed &= MiscFunctions.compareResult("Monthly Fee", fee.monthlyFee, slipFeesUI.get(j).monthlyFee);
					}
					if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)){
						passed &= MiscFunctions.compareResult("Per Season Fee", fee.perSeasonFee, slipFeesUI.get(j).perSeasonFee);
					}
				}
			}
			
			if(!found)
				throw new ErrorOnPageException("Could not find slip fee for increment "+fee.increment+" from UI.");
		}
		
		return passed;
	}
	
	public boolean compareSlipUseFeeForCustomDuration(List<SlipFeeCustomDuration> durations, List<SlipFeeCustomDuration> durationsUI) {
		if(durations.size() != durationsUI.size())
			throw new ErrorOnPageException("Custom Duration size was not correct."+" Expected Duration size:"+durations.size()+" Actual Duration size:"+durationsUI.size());
		
		boolean passed=true;
		for(int i=0;i<durations.size();i++) {
			SlipFeeCustomDuration duration=durations.get(i);
			boolean found=false;
			for(int j=0;j<durationsUI.size();j++) {
				if(duration.customDuration.equals(durationsUI.get(j).customDuration)
				&& duration.rate.equals(durationsUI.get(j).rate)){
					found=true;
					break;
				}
			}
			
			if(found)
				passed &= true;
			else
				passed &= false;
		}
		if(!passed)
			throw new ErrorOnPageException("Custom Durations were not correct on page.", durations, durationsUI);
		logger.info("Custom Durations were correct.");
		return passed;
	}
	
	/**
	 * Check whether range feet is editable for each slip fee
	 * @param top
	 * @return
	 */
	public boolean isRangeFeetEditable(IHtmlObject top){
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.rangeFeet", false)), top);
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	/**
	 * Check fee per foot is editable for each slip fee
	 * @param top
	 * @return
	 */
	public boolean isFeePerFootCheckBoxEditable(IHtmlObject top) {
		IHtmlObject[] objs = browser.getCheckBox(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.feePerFoot", false)), top);
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public boolean isFullStayForMultiUnitRateCheckBoxEditable(IHtmlObject top){
		IHtmlObject[] objs = browser.getCheckBox(Property.toPropertyArray(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.fullStayForMultiUnitRate", false)), top);
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public boolean isFullStayForMultiUnitRateCheckBoxExist(IHtmlObject top){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SlipFeeRangeView-\\d+\\.fullStayForMultiUnitRate", false), top);
	}
	
	public boolean isAddCustomDurationButtonEnable(IHtmlObject top){                                     
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".text", "Add Custom Duration"), top);
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public boolean isCustomDuraionFieldEnabled(IHtmlObject top){
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.duration", false)), top);
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public boolean isCustomDuraionRateFieldEnabled(IHtmlObject top){
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("SlipFeeCustomDurationView-\\d+\\.fee", false)), top);
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public boolean isRemoveCustomDurationEnabled(IHtmlObject top){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Remove Custom Duration", top);
	}
	
	public boolean isRemoveRangeButtonEnable(IHtmlObject top){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Remove Range", top);
	}
	
	/**
	 * This method is used to verify the custom duration UI under the range of the slip fee
	 * @param top
	 * @param reservationType
	 * @return
	 */
	public boolean  VerifyCustomDurationUI(IHtmlObject top,String reservationType) {
		boolean passed = true;
		//Check The "Add Custom Duration" button (for Transient and Lease) is displayed and enabled for each Range Record
		if((!reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)) && (!isAddCustomDurationButtonEnable(top))){
			logger.error("'Add Custom Duration' button is not enabled for each range record!");
			passed = false;
		}
		
		IHtmlObject[] durations=browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("\\d+_\\d", false), top);
		if(durations.length > 0){
			logger.info("Found "+durations.length+" custom durations for current range fee.");
			for(int i=1;i<durations.length;i++) {
				//Customer Duration is editable
				System.out.println(durations[i].getAttributeValue("id"));
				if(!isCustomDuraionFieldEnabled(durations[i])){  //237694823_0 
					logger.error("'Custom Duration' is not editable!");
					passed = false;
				}
				if(!isCustomDuraionRateFieldEnabled(durations[i])){
					logger.error("'Custom Duration' is not editable!");
					passed = false;
				}
				if(!isRemoveCustomDurationEnabled(durations[i])){
					logger.error("'Reomve Custom Duration' button is not enabled!");
					passed = false;
				}
			}
		}
		Browser.unregister(durations);
		return passed;
	}
	
	
	/**
	 * This method is used to verify the UI of slip fees when editting use fee
	 * @param reservationType
	 * @return
	 */
	public boolean verifySlipFeesUI(String reservationType) {
		boolean passed = true;
		IHtmlObject[] rangeSections=this.getSlipRateTR();
		if(rangeSections.length<1){
			throw new ItemNotFoundException("Could not find any range for fee.");
		}
		
		logger.info("Found "+rangeSections.length+" range sections from UI.");

		for(int i=0;i<rangeSections.length;i++) {
			//Check Range value whether is editable
			if(i == 0 && isRangeFeetEditable(rangeSections[i])){
				logger.error("Range value is editable for the first range record;");
				passed = false;
			}
			if(i !=0 && (!isRangeFeetEditable(rangeSections[i]))){
				logger.error("Range value is not editable except the first range record;");
				passed = false;
			}
			
			//Check Fee Per Foot checkbox are editable
			if(!isFeePerFootCheckBoxEditable(rangeSections[i])){
				logger.error("'Fee Per Foot' is not editable!");
				passed = false;
			}
			
			//Check Full Stay Req'd for Multi-unit Rate checkbox only applicable for the first range of the Transient Fee Schedule
			if(i == 0 && reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)){
				if(!isFullStayForMultiUnitRateCheckBoxEditable(rangeSections[i])){
					logger.error("The  Full Stay Req'd for Multi-unit Rate checkbox is not editable except for the first range record of transient fee schedule!");
					passed = false;
				}
			}else{
				if(isFullStayForMultiUnitRateCheckBoxExist(rangeSections[i])){
					logger.error("The Full Stay Req'd for Multi-unit Rate checkbox is exist for the range record!");
					passed = false;
				}
			}
			if(i!=0 && (isFullStayForMultiUnitRateCheckBoxExist(rangeSections[i]))){
				logger.error("The first Full Stay Req'd for Multi-unit Rate checkbox is exist for the range record that is not the first!");
				passed = false;
			}
			
			//Check 'Remove Range' button whether is enabled
			if(i==0 && isRemoveRangeButtonEnable(rangeSections[i])){
				logger.error("'Remove Range' button is enable for the first range record;");
				passed = false;
			}
			if(i!=0 && !isRemoveRangeButtonEnable(rangeSections[i])){
				logger.error("'Remove Range' button is not enable except the first range record;");
				passed = false;
			}
			passed &= VerifyCustomDurationUI(rangeSections[i], reservationType);
		}
		Browser.unregister(rangeSections);
		return passed;
	}
	
	public boolean isRaftingTypeDropdownListExisted() {
		return browser.checkHtmlObjectExists(".id", "SlipFeeScheduleView-\\d+\\.raftingType");
	}
	
	public IHtmlObject[] getSlipPricingBasedonArrivalTR() {
		Property[] properties=new Property[3];
		properties[0]=new Property(".class", "Html.TR");
		properties[1]=new Property(".text", new RegularExpression("Arrival Start.*", false));
		properties[2]=new Property(".id", new RegularExpression("\\d+_\\d", false));
		IHtmlObject[] trs = browser.getHtmlObject(properties);
		return trs;
	}
	
	public IHtmlObject[] getSlipPricingBasedonDepartureTR() {
		Property[] properties=new Property[3];
		properties[0]=new Property(".class", "Html.TR");
		properties[1]=new Property(".text", new RegularExpression("Departure Start.*", false));
		properties[2]=new Property(".id", new RegularExpression("\\d+_\\d", false));
		IHtmlObject[] trs = browser.getHtmlObject(properties);
		return trs;
	}
	
	public List<PricingBase> getSlipPricingBasedonArrival() {
		List<PricingBase> pricingBasedonArrivalList = new ArrayList<PricingBase>();
		
		IHtmlObject[] pricingTRs=getSlipPricingBasedonArrivalTR();
		if(pricingTRs.length<1) {
			Browser.unregister(pricingTRs);
			return null;
		}
		
		for(int i=0;i<pricingTRs.length;i++) {
			PricingBase pricing=new PricingBase();
			pricing.startMonth=this.getStartMonth(0, pricingTRs[i]);
			pricing.startDate=this.getStartDay(0, pricingTRs[i]);
			pricing.endMonth=this.getEndMonth(0, pricingTRs[i]);
			pricing.endDate=this.getEndDay(0, pricingTRs[i]);
			pricing.percent=this.getPricingPercent(0, pricingTRs[i]);
			pricingBasedonArrivalList.add(pricing);
		}
		
		Browser.unregister(pricingTRs);
		return pricingBasedonArrivalList;
	}
	
	public List<PricingBase> getSlipPricingBasedonDeparture() {
		List<PricingBase> pricingBasedonDepartureList = new ArrayList<PricingBase>();
		
		IHtmlObject[] pricingTRs=getSlipPricingBasedonDepartureTR();
		if(pricingTRs.length<1) {
			Browser.unregister(pricingTRs);
			return null;
		}
		
		for(int i=0;i<pricingTRs.length;i++) {
			PricingBase pricing=new PricingBase();
			pricing.startMonth=this.getStartMonth(0, pricingTRs[i]);
			pricing.startDate=this.getStartDay(0, pricingTRs[i]);
			pricing.endMonth=this.getEndMonth(0, pricingTRs[i]);
			pricing.endDate=this.getEndDay(0, pricingTRs[i]);
			pricing.percent=this.getPricingPercent(0, pricingTRs[i]);
			pricingBasedonDepartureList.add(pricing);
		}
		
		Browser.unregister(pricingTRs);
		return pricingBasedonDepartureList;
	}
	
	public boolean compareSlipPricingBasedOnArrivalOrDeparture(List<PricingBase> list, List<PricingBase> listUI) {
		boolean passed=true;
		if(list.size()!=listUI.size())
			throw new ErrorOnPageException("Pricing bases on arrival or departure list size were not equal.", list.size(), listUI.size());
		
		for(int i=0;i<list.size();i++) {
			boolean found=false;
			for(int j=0;j<listUI.size();j++){
				if(list.get(i).startMonth.equals(listUI.get(j).startMonth)
				&& list.get(i).startDate.equals(listUI.get(j).startDate)
				&& list.get(i).endMonth.equals(listUI.get(j).endMonth)
				&& list.get(i).endDate.equals(listUI.get(j).endDate)
				&& list.get(i).percent.equals(listUI.get(j).percent)) {
					found=true;
					break;
				}
			}
			
			if(found)
				passed &= true;
			else
				passed &= false;
		}
		return passed;
	}
	
	public String getPerSeasonValue(IHtmlObject top){
		return browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("(SlipFeeRangeView|SlipFeeIncrementView)-\\d+\\.seasonFee",false)), top);
	}
	
	public void clickAssignment(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Assignment");
	}

//	private void refresh() {
//		browser.clickGuiObject(".class", "Html.TD", ".text", new RegularExpression("^Product Category",false));
//		
//	}
	//keep match with String 
	private boolean isDropDownListEnabled(String idInfo){
		IHtmlObject[] objs=browser.getDropdownList(".id", idInfo);
		ISelect list=(ISelect)objs[0];
		boolean enable = list.isEnabled();
		List<String> value=list.getAllOptions();
		if(value.size() < 2 && (!"All".equalsIgnoreCase(value.get(0)))){
			enable = false;
		}
		Browser.unregister(objs);
		return enable;
	}
	//added by summer
	private boolean isDropDownListEnabled(RegularExpression idInfo){
		IHtmlObject[] objs=browser.getDropdownList(".id", idInfo);
		ISelect list=(ISelect)objs[0];
		boolean enable = list.isEnabled();
		List<String> value=list.getAllOptions();
		if(value.size() < 2 && (!"All".equalsIgnoreCase(value.get(0)))){
			enable = false;
		}
		Browser.unregister(objs);
		return enable;
	}
	
	public boolean isDateSectionTextIsEnabled(String dateIdInfo){
		IHtmlObject[] objs = browser.getTextField(".id", dateIdInfo);
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get date text with id:" + dateIdInfo);
		}
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	/**
	 * This method is used to check the assignment section is editable
	 * @return
	 */
	public boolean checkAssignmentSectionIsEnabled(){
		boolean enable = true;
		enable &= isDropDownListEnabled(new RegularExpression("assignment_loop",false));
		enable &= isDropDownListEnabled(new RegularExpression("assignment_prodgroup",false));
		enable &= isDropDownListEnabled(new RegularExpression("assignment_product",false));
		return enable;
	}
	
	/**
	 * This method is used to check date section is editable
	 * @return
	 */
	public boolean checkDateSectionIsEnabled(){
		boolean enable = true;
		enable &= isDateSectionTextIsEnabled("date_effective_ForDisplay");
		enable &= isDateSectionTextIsEnabled("date_start_ForDisplay");
		enable &= isDateSectionTextIsEnabled("date_end_ForDisplay");
		return enable;
	}
	
	/**
	 * This method is uesd to check conditions section is editable.Please call isDropDownListEnabled(String) if need accurate matching
	 * @return
	 */
	public boolean checkConditionSectionIsEnabled(){
		boolean enable = true;
		enable &= isDropDownListEnabled(new RegularExpression("conditions_season",false));
		enable &= isDropDownListEnabled(new RegularExpression("conditions_channel",false));
		enable &= isDropDownListEnabled(new RegularExpression("conditions_in_out_state",false));
		enable &= isDropDownListEnabled(new RegularExpression("conditions_customer_type",false));
		enable &= isDropDownListEnabled(new RegularExpression("ticket_cat_id",false));
		return enable;
	}
	
	public boolean checkAccountIsEnable(){
//		return isDropDownListEnabled("account_code");
		return isDropDownListEnabled(new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)",false));

	}
	
	public boolean checkMarinaRateTypeIsEnabled(){
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("SlipFeeScheduleView-\\d+\\.marinaRateType",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found marina rate type radio button object.");
		}
		boolean isEnabled = true;
		for(IHtmlObject obj : objs){
			IRadioButton radioButton = (IRadioButton)obj;
			isEnabled &= radioButton.isEnabled();
		}
		
		Browser.unregister(objs);
		return isEnabled;
	}
	
	public List<String> getArrivalStartMonthElements() {
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth",false));
		List<String> list = browser.getDropdownElements(p, objs[0]);
		Browser.unregister(objs);
		return list;
	}
	
	public List<String> getArrivalStartDateElements() {
		IHtmlObject[] objs = this.getPricingBasedOnArrivalTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false));
		List<String> list = browser.getDropdownElements(p, objs[0]);
		Browser.unregister(objs);
		return list;
	}
	
	public List<String> getDepartureStartMonthElements() {
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateMonth",false));
		List<String> list = browser.getDropdownElements(p, objs[0]);
		Browser.unregister(objs);
		return list;
	}
	
	public List<String> getDepartureStartDateElements() {
		IHtmlObject[] objs = this.getPricingBaseOnDepartureTr();
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("SlipArrivalDeparturePricingView-\\d+\\.startDateDay",false));
		List<String> list = browser.getDropdownElements(p, objs[0]);
		Browser.unregister(objs);
		return list;
	}
	
	public void verifyPricingBasedOnArrival() {
		List<String> monthListUI=getArrivalStartMonthElements();
		List<String> dayListUI=getArrivalStartDateElements();
		String startMonthUI=getArrivalStartMonth(0);
		String startDayUI=getArrivalStartDate(0);
		String endMonthUI=getArrivalEndMonth(0);
		String endDayUI=getArrivalEndDate(0);
		String perentUI=getArrivalPercent(0);
		
		boolean passed=true;
		passed &= MiscFunctions.compareResult("Arrival Start Month default value", "Month", startMonthUI);
		passed &= MiscFunctions.compareResult("Arrival Start Day default value", "Day", startDayUI);
		passed &= MiscFunctions.compareResult("Arrival End Month default value", "Month", endMonthUI);
		passed &= MiscFunctions.compareResult("Arrival End Day default value", "Day", endDayUI);
		passed &= MiscFunctions.compareResult("Arrival Percent default value", "", perentUI);
		
		if(!passed)
			throw new ErrorOnPageException("Pricing Based on Arrival default values were not correct on page.");
		
		logger.info("Check Arrival Start month list.");
		monthListUI.remove(0);
		MiscFunctions.compareStringList("Arrival Start Month should from Jan to Dec.", Arrays.asList(DateFunctions.MONTHS_SHORT), monthListUI);
		logger.info("Check Arrival Start days list.");
		dayListUI.remove(0);
		MiscFunctions.compareStringList("Arrival Start Day should from 1 to 31.", Arrays.asList(DateFunctions.DAYS), dayListUI);
		
		logger.info("Pricing based on Arrival UI is correct on page.");
	}
	
	public void verifyPricingBasedOnDeparture() {
		List<String> monthListUI=getDepartureStartMonthElements();
		List<String> dayListUI=getDepartureStartDateElements();
		String startMonthUI=getDepartureStartMonth(0);
		String startDayUI=getDepartureStartDate(0);
		String endMonthUI=getDepartureEndMonth(0);
		String endDayUI=getDepartureEndDate(0);
		String perentUI=getDeparturePercent(0);
		
		boolean passed=true;
		passed &= MiscFunctions.compareResult("Departure Start Month default value", "Month", startMonthUI);
		passed &= MiscFunctions.compareResult("Departure Start Day default value", "Day", startDayUI);
		passed &= MiscFunctions.compareResult("Departure End Month default value", "Month", endMonthUI);
		passed &= MiscFunctions.compareResult("Departure End Day default value", "Day", endDayUI);
		passed &= MiscFunctions.compareResult("Departure Percent default value", "", perentUI);
		
		if(!passed)
			throw new ErrorOnPageException("Pricing Based on Departure default values were not correct on page.");
		
		logger.info("Check Arrival Start month list.");
		monthListUI.remove(0);
		MiscFunctions.compareStringList("Departure Start Month should from Jan to Dec.", Arrays.asList(DateFunctions.MONTHS_SHORT), monthListUI);
		logger.info("Check Arrival Start days list.");
		dayListUI.remove(0);
		MiscFunctions.compareStringList("Departure Start Day should from 1 to 31.", Arrays.asList(DateFunctions.DAYS), dayListUI);
		
		logger.info("Pricing based on Departure UI is correct on page.");
	}
	
	public void setNumOfFreeChangesPerTrans(String num){
		browser.setTextField(".id", "numberOfFreeChangeTransactions", num);
	}

	private void selectRecalculateTransactionFees(String value){
		browser.selectRadioButton(".id", "recalctransfee", ".value", value);
	}
	
	public void selectRecalculateTransactionFees_NotRecalculate(){
		this.selectRecalculateTransactionFees("false");
	}

	public void selectRecalculateTransactionFees_Recalculate(){
		this.selectRecalculateTransactionFees("true");
	}
	
	// if transaction type is 'Change Dates', 'Change Dates Applies To' section is displayed.
	public void selectAllChangeDates(){
		browser.selectRadioButton(".id", "changedatestype", ".value", "1");
	}
	
	public void selectExcludeExtendStay(){
		browser.selectRadioButton(".id", "changedatestype", ".value", "2");
	}
	
	public List<String> getOptionOfChangeDatesAppliesTo(){
		List<String> optionList = new ArrayList<String>();
		
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.Label", ".for", "changedatestype");
		if(objs.length<0){
			throw new ItemNotFoundException("Can't find Change Dates Applies To section!");
		}
		
		for(int i=0; i<objs.length; i++){
			optionList.add(objs[i].getProperty(".text").trim());
		}
		Browser.unregister(objs);
		return optionList;
	}
	
	public void verifyOptionOfChangeDatesAppliesTo(List<String> expectList){
		List<String> actualList = this.getOptionOfChangeDatesAppliesTo();
		
		Collections.sort(actualList);
		Collections.sort(expectList);
		
		if(actualList.equals(expectList)){
			logger.info("Options of Chage Dates Applies To is correct.");
		} else {
			throw new ErrorOnPageException("Options of Chage Dates Applies To is not correct!");
		}
	}
	
	/**
	 * All Change Dates should be default selection.
	 */
	public void verifyDefaultCheckedOfChangeDatesAppliesTo(){
		int index = -1;
		
		// get index by value of radio button(All Change Dates)
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", "changedatestype");
		if(objs.length<0){
			throw new ItemNotFoundException("Can't find Change Dates Applies To section!");
		}

		for(int i=0; i<objs.length; i++){
			if("All Change Dates".equals(objs[i].getProperty(".text").trim())){
				index = i;
				break;
			}
		}
		Browser.unregister(objs);
		if(index < 0){
			throw new ItemNotFoundException("There isn't 'All Change Dates'!");
		}
		
		// check if All Change Dates is default selection
		if(browser.isRadioButtonSelected(".id", "changedatestype", index)){
			logger.info("Default select is All Change Dates, correct!");
		} else {
			throw new ErrorOnPageException("Default select should be All Change Dates");
		}
	}
	
	/**
	 * Get the products shown in list after set a prefix of product name
	 * @param prd_prefix
	 * @return
	 */
	public List<String> getAvailableProductsByPrefix(String prd_prefix){
		browser.setTextField(".id", new RegularExpression("s_pn", false), prd_prefix, true);
		Property[] p = Property.toPropertyArray(".className", "ac_results");
		browser.waitExists(p);
		browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
		Browser.sleep(3);
		IHtmlObject[] topObj = browser.getHtmlObject(p);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LI", topObj[0]);
		List<String> products = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			products.add(objs[i].text());
		}
		ajax.waitLoading();
		Browser.unregister(objs);
		Browser.unregister(topObj);
		return products;
	}
	
	/**
	 * This method get the split type for account of Activity and transaction fee for activity product
	 * @return
	 */
	public String[] getAccountSplitTypes(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",  new RegularExpression("^Split by.*", false));
		if(objs.length < 1 ){
			throw new ErrorOnPageException("Split by type table is not available!");
		}
		String text = objs[0].text().replaceAll("Split by", "");
		String[] splitTypes = text.split(" ");
		Browser.unregister(objs);
		return splitTypes;
	}
	
	/**
	 * Get the tax names that are avaible for the fee schedule
	 * @return
	 */
	public List<String> taxNames(){
		String prefix = "Applicable Taxes";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text",  new RegularExpression("^" + prefix + ".*", false));
		if(objs.length < 1 ){
			throw new ErrorOnPageException("Applicable taxes object can not be found!");
		}
		String text = objs[0].text().replaceAll(prefix, "");
		List<String> taxes = Arrays.asList(text.split(" "));
		Browser.unregister(objs);
		return taxes;
	}
	
	protected Property[] purchaseTypeDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "purchasetype_id");
	}
	
	public List<String> getPuchaseTypeElements(){
		return browser.getDropdownElements(this.purchaseTypeDropdownList());
	}
	
	public boolean isPurchaseTypeSectionExist(){
		return browser.checkHtmlObjectExists(this.purchaseTypeDropdownList());
	}
	
	public void selectPurchaseType(String type){
		browser.selectDropdownList(this.purchaseTypeDropdownList(), type);
	}
	
	protected Property[] scheduleTypeDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("FacilityFeeSchdDetailView(-\\d+)?\\.scheduleType",false));
	}
	
	protected Property[] scheduleNameTextField(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("(FeeSchdDetailsView(-\\d+)?\\.scheduleName)|(ActivityFeeScheduleView(-\\d+)?\\.scheduleName)",false));
	}
	
	public void selectScheduleType(String scheduleType){
		browser.selectDropdownList(this.scheduleTypeDropdownList(), scheduleType);
	}
	
	public String getScheduleType(){
		return browser.getDropdownListValue(this.scheduleTypeDropdownList(), 0);
	}
	
	public void setScheduleName(String scheduleName){
		browser.setTextField(this.scheduleNameTextField(), scheduleName);
	}
	
	public String getScheduleName(){
		return browser.getTextFieldValue(this.scheduleNameTextField());
	}
	
	protected Property[] discountTypeRadioBtn(String num){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("FacilityFeeSchdDetailView-\\d+\\.automaticDiscount" + num,false));
	}
	
	public void selectDiscountType(String discountType){
		String num = "";
		if(discountType.equalsIgnoreCase("Automatic")){
			num = "0";
		}
		if(discountType.equalsIgnoreCase("Manual")){
			num = "1";
		}
		browser.selectRadioButton(this.discountTypeRadioBtn(num), 0);
	}
	
	public String getDiscountType(){
		String discountType = "";
		IHtmlObject[] objs = browser.getRadioButton(this.discountTypeRadioBtn("\\d"));
		for(int i=0; i<objs.length; i++){
			if(((IRadioButton)objs[i]).isSelected()){
				String id_cont = ((IRadioButton)objs[i]).getAttributeValue("id");
				discountType = browser.getObjectText(".class", "Html.LABEL", ".for", id_cont);
				break;
			}
		}
		Browser.unregister(objs);
		return discountType;
	}
	
	protected Property[] feeAppliesToRadioBtn(String num){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("FacilityFeeSchdDetailView-\\d+\\.feeApplyType" + num,false));
	}
	
	public void selectFeeAppliesTo(String feeApplyTo){
		String num = "";
		if(feeApplyTo.equalsIgnoreCase("Equipment Rental")){
			num = "0";
		}
		if(feeApplyTo.equalsIgnoreCase("Deposit")){
			num = "1";
		}
		browser.selectRadioButton(this.feeAppliesToRadioBtn(num), 0);
	}
	
	public String getFeeAppliesTo(){
		String feeApplyTo = "";
		IHtmlObject[] objs = browser.getRadioButton(this.feeAppliesToRadioBtn("\\d"));
		for(int i=0; i<objs.length; i++){
			if(((IRadioButton)objs[i]).isSelected()){
				String id_cont = ((IRadioButton)objs[i]).getAttributeValue("id");
				feeApplyTo = browser.getObjectText(".class", "Html.LABEL", ".for", id_cont);
				break;
			}
		}
		Browser.unregister(objs);
		return feeApplyTo;
	}
	
	
	protected Property[] chargeFeeDuringRadioBtn(String num){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("FacilityFeeSchdDetailView-\\d+\\.feeChargeTiming" + num,false));
	}
	
	public void selectChangeFeeDuring(String whenToCharge){
		String num = "";
		if(whenToCharge.equalsIgnoreCase("Reservation")){
			num = "0";
		}
		if(whenToCharge.equalsIgnoreCase("Check-Out")){
			num = "1";
		}
		browser.selectRadioButton(this.chargeFeeDuringRadioBtn(num), 0);
	}
	
	public String getChargeFeeDuring(){
		String chargeFeeDuring = "";
		IHtmlObject[] objs = browser.getRadioButton(this.chargeFeeDuringRadioBtn("\\d"));
		for(int i=0; i<objs.length; i++){
			if(((IRadioButton)objs[i]).isSelected()){
				String id_cont = ((IRadioButton)objs[i]).getAttributeValue("id");
				chargeFeeDuring = browser.getObjectText(".class", "Html.LABEL", ".for", id_cont);
				break;
			}
		}
		Browser.unregister(objs);
		return chargeFeeDuring;
	}
	
	protected Property[] applyDiscountRadioBtn(String num){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("FacilityFeeSchdDetailView-\\d+\\.unitType" + num,false));
	}
	
	public void selectApplyDiscount(String applyType){
		String num = "";
		if(applyType.equalsIgnoreCase("Per Unit")){
			num = "0";
		}
		if(applyType.equalsIgnoreCase("Per Hour")){
			num = "1";
		}
		if(applyType.equalsIgnoreCase("Percentage")){
			num = "2";
		}
		browser.selectRadioButton(this.applyDiscountRadioBtn(num), 0);
	}
	
	public String getApplyDiscount(){
		String applyDiscount = "";
		IHtmlObject[] objs = browser.getRadioButton(this.applyDiscountRadioBtn("\\d"));
		for(int i=0; i<objs.length; i++){
			if(((IRadioButton)objs[i]).isSelected()){
				String id_cont = ((IRadioButton)objs[i]).getAttributeValue("id");
				applyDiscount = browser.getObjectText(".class", "Html.LABEL", ".for", id_cont);
				break;
			}
		}
		Browser.unregister(objs);
		return applyDiscount;
	}
	
	protected Property[] orderCreateStartDate(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityFeeSchdDetailView(-\\d+)?\\.orderCreateStartDate_ForDisplay", false));
	}
	
	protected Property[] orderCreateEndDate(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityFeeSchdDetailView(-\\d+)?\\.orderCreateEndDate_ForDisplay", false));
	}
	
	public void setOrderCreateStartDate(String startDate){
		browser.setTextField(this.orderCreateStartDate(), startDate);
	}
	
	public void setOrderCreateEndDate(String endDate){
		browser.setTextField(this.orderCreateEndDate(), endDate);
	}
	
	public String getOrderCreateStartDate(){
		return browser.getTextFieldValue(this.orderCreateStartDate());
	}
	
	public String getOrderCreateEndDate(){
		return browser.getTextFieldValue(this.orderCreateEndDate());
	}
	
	protected Property[] customerPassDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "conditions_pass");
	}
	
	public void selectCustomerPass(String custPass){
		browser.selectDropdownList(this.customerPassDropdownList(), custPass);
	}
	
	public String getCustomerPass(){
		return browser.getDropdownListValue(this.customerPassDropdownList(), 0);
	}
	protected Property[] addCodeBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Add Code");
	}
	
	protected Property[] removeCodeBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Remove Code");
	}
	
	protected Property[] codeTextField(){                            
		return Property.toPropertyArray(".id", new RegularExpression("CouponLightView-\\d+\\.code", false));
	}
	
	protected Property[] descriptionTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("CouponLightView-\\d+\\.couponDescription", false));
	}
	
	public void setPromoCode(String code, int index){
		browser.setTextField(this.codeTextField(), code, index);
	}
	
	public void setPromoDes(String des, int index){
		browser.setTextField(this.descriptionTextField(), des, index);
	}
	
	public String getPromoCode(int index){
		return browser.getTextFieldValue(this.codeTextField(), index);
	}
	
	public String getPromoDes(int index){
		return browser.getTextFieldValue(this.descriptionTextField(), index);
	}
	
	public void clickAddCode(){
		browser.clickGuiObject(this.addCodeBtn());
	}

	public void clickRemoveCode(){
		browser.clickGuiObject(this.removeCodeBtn());
	}
	
	public int getPromoCodesNum(){
		IHtmlObject[] objs = browser.getHtmlObject(this.descriptionTextField());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public void setUpPromoCode(PromoCode pd, int index){
		this.setPromoCode(pd.code, index);
		this.setPromoDes(pd.description, index);
	}
	
	public void setUpPromoCodes(List<PromoCode> promoCodes){
		for(int i=0; i<promoCodes.size(); i++){
			this.setUpPromoCode(promoCodes.get(i), i);
		}
	}
	
	public List<PromoCode> getPromoCode(){
		List<PromoCode> promoCds = new ArrayList<PromoCode>();
		int num = this.getPromoCodesNum();
		for(int i=0; i<num; i++){
			PromoCode pc = new FeeScheduleData().new PromoCode();
			pc.code = this.getPromoCode(i);
			pc.description = this.getPromoDes(i);
			promoCds.add(pc);
		}
		return promoCds;
	}

	
	public void removeAllPromoCodes(){
		int num = this.getPromoCodesNum();
		for(int i=0; i<num; i++){
			this.clickRemoveCode();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	private String prefix = "(FeeSchdDetailsView|ActivityFeeScheduleView|ActivityTransactionFeeScheduleView|VendorFeeScheduleView)-\\d+\\.";
	
	public List<String> getSplitIntoNums(){
		return browser.getDropdownElements(this.slipByNumRevenueAccountsDropdownList());
	}
	
	protected Property[] splitByPercentRadioButton(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO",  ".id", new RegularExpression(prefix+ "feeSchdSplitType0", false));
	}
	
	protected Property[] splitByAmountRadioButton(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO",  ".id", new RegularExpression(prefix+ "feeSchdSplitType1", false));
	}
	
	protected Property[] slipByNumRevenueAccountsDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression(prefix+ "feeSchdAcctSplitNum", false));
	}
	
	protected Property[] accountDropDownList(){                        
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("account_code|(FeeScheduleSplitView-\\d+\\.account)", false));
	}
	
	public void selectSplitByPercent(){
		browser.selectRadioButton(this.splitByPercentRadioButton(), 0);
	}
	
	public void selectSplitByAmount(){
		browser.selectRadioButton(this.splitByAmountRadioButton(), 0);
		System.out.println("ok?");
	}
	
	public void selectSplitIntNumOfAccount(String num){
		browser.selectDropdownList(this.slipByNumRevenueAccountsDropdownList(), num);
	}
	
	public void selectAccount(String account, int index){
		browser.selectDropdownList(this.accountDropDownList(), account, index);
	}

	protected Property[] rateAmountTextFieldForFacilityFee(){                                                     
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id",  new RegularExpression("FacilityFeeSchdDetailView(-\\d+)?.fixedAmount:CURRENCY_INPUT", false));
	}
	protected Property[] rateAmountTextFieldForActivityFee(){                                                     
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id",  new RegularExpression("ActivityFeeScheduleView(-\\d+)?.fixedAmount:CURRENCY_INPUT", false));
	}
	protected Property[] rateAmountTextFieldForVendorFee(){                                                     
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id",  new RegularExpression("VendorFeeScheduleView(-\\d+)?.vendorFee:CURRENCY_INPUT", false));
	}
	public void setRateAmountForFacilityFee(String amount){
		browser.setTextField(this.rateAmountTextFieldForFacilityFee(), amount);
	}
	
	
	public String getRateAmountForFacilityFee(){
		return browser.getTextFieldValue(this.rateAmountTextFieldForFacilityFee());
	}

	public void setRateAmountForActivityFee(String amount){
		browser.setTextField(this.rateAmountTextFieldForActivityFee(), amount);
	}
	
	public String getRateAmountForActivityFee(){
		return browser.getTextFieldValue(this.rateAmountTextFieldForActivityFee());
	}

	public void setRateAmountForVendorFee(String amount){
		browser.setTextField(this.rateAmountTextFieldForVendorFee(), amount);
	}
	
	public String getRateAmountForVendorFee(){
		return browser.getTextFieldValue(this.rateAmountTextFieldForVendorFee());
	}

	
	protected Property[] accountAmountTextField(){
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id",  new RegularExpression("FeeScheduleSplitView-\\d+\\.value:CURRENCY_INPUT", false));
	}
	
	public void setPercentOrAmountForAccount(String percentOrAmount, int index){
		browser.setTextField(this.accountAmountTextField(), percentOrAmount, index);
	}
	
	public void moveFocus(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Dates");
	}
	
	protected Property[] productSubCatDropdownList(){
		return Property.toPropertyArray(".id",  new RegularExpression("FacilityFeeSchdDetailView-\\d+\\.prdGrpSubCat",false));
	}
	
	public String getSubCategory(){
		return browser.getDropdownListValue(this.productSubCatDropdownList(), 0);
	}
	
	public String splitByType(){
		String splitBy = "";
		if(browser.isRadioButtonSelected(this.splitByPercentRadioButton())){
			splitBy = "Percent";
		}
		if(browser.isRadioButtonSelected(this.splitByAmountRadioButton())){
			splitBy = "Amount";
		}
		return splitBy;
	}
	
	public String getSplitIntoNum(){
		return browser.getDropdownListValue(this.slipByNumRevenueAccountsDropdownList(), 0);
	}
		
	public List<String> getAccounts(int num){
		List<String> accounts = new ArrayList<String>();
		for(int i=0; i< num; i++){
			accounts.add(browser.getDropdownListValue(this.accountDropDownList(), i));
		}
		return accounts;
	}
	
	public List<String> getPercentOrAmountForEachAccount(int num){
		List<String> percentOrAmount = new ArrayList<String>();
		for(int i=0; i< num; i++){
			percentOrAmount.add(browser.getTextFieldValue(this.accountAmountTextField(), i));
		}
		return percentOrAmount;
	}

	public void fillUpFaiclityFee(FeeScheduleData fee){
		if(StringUtil.notEmpty(fee.scheduleType)){
			this.selectScheduleType(fee.scheduleType);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(fee.scheduleName)){
			this.setScheduleName(fee.scheduleName);
		}
		if(StringUtil.notEmpty(fee.discountType)){
			this.selectDiscountType(fee.discountType);
		}
		if (StringUtil.notEmpty(fee.productGroup)) {
			this.selectAssignProdGroup(fee.productGroup);
			this.waitLoading();
		}
		if (StringUtil.notEmpty(fee.product)) {
			this.selectAssignProduct(fee.product);
			this.waitLoading();
		}
		if (StringUtil.notEmpty(fee.effectStartDate)) {
			this.setEffectStartDate(fee.effectStartDate);
		}
		if(StringUtil.notEmpty(fee.effectEndDate)){
			this.setEffectEndDate(fee.effectEndDate);
		}
		if(StringUtil.notEmpty(fee.orderCreateStartDate)){
			this.setOrderCreateStartDate(fee.orderCreateStartDate);
		}
		if(StringUtil.notEmpty(fee.orderCreateEndDate)){
			this.setOrderCreateEndDate(fee.orderCreateEndDate);
		}
		if (StringUtil.notEmpty(fee.salesChannel)) {
			this.selectSaleChannel(fee.salesChannel);
		}
		if(StringUtil.notEmpty(fee.custPass)){
			this.selectCustomerPass(fee.custPass);
		}
		if(fee.promoCodes.size() > 0){
			int needNum = fee.promoCodes.size()-this.getPromoCodesNum();
			while(needNum>0){
				this.clickAddCode();
				ajax.waitLoading();
				this.waitLoading();
				needNum--;
			}
			this.setUpPromoCodes(fee.promoCodes);
		}
		if(StringUtil.notEmpty(fee.feeApplyTo)){
			this.selectFeeAppliesTo(fee.feeApplyTo);
			ajax.waitLoading();
			this.waitLoading();
			Browser.sleep(3);
			if(fee.feeApplyTo.equalsIgnoreCase("Deposit")){
				if(StringUtil.notEmpty(fee.chargeFeeDuring)){
					this.selectChangeFeeDuring(fee.chargeFeeDuring);
				}
			}
		}
		if(StringUtil.notEmpty(fee.applyRate)){
			this.selectApplyDiscount(fee.applyRate);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		if(fee.rateAmount != null){
			this.setRateAmountForFacilityFee(fee.rateAmount);
			this.moveFocus();
			ajax.waitLoading();
		}
		if(fee.splitRateBy != null){
			if(fee.splitRateBy.equalsIgnoreCase("Percent")){
				this.selectSplitByPercent();
			}
			if(fee.splitRateBy.equalsIgnoreCase("Amount")){
				this.selectSplitByAmount();
			}
			ajax.waitLoading();
			this.waitLoading();
		}
		if(fee.splitIntoNum != null){
			//Change the number to other, so that it can be changed when select it 
			this.selectSplitIntNumOfAccount("1");
			ajax.waitLoading();
			this.waitLoading();
			
			this.selectSplitIntNumOfAccount(fee.splitIntoNum);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(fee.accounts.size() > 0){
			for(int i=0; i<fee.accounts.size(); i++){
				this.selectAccount(fee.accounts.get(i), i);
				if(i < (fee.accounts.size() -1)){
					this.setPercentOrAmountForAccount(fee.percentOrAmountForEachAccount.get(i), i);
					this.moveFocus();
					ajax.waitLoading();
					this.waitLoading();
				}
			}
		}
	}
	//added by Summer
	public void fillUpActivityFee(FeeScheduleData fee){
		if(StringUtil.notEmpty(fee.scheduleName)){
			this.setScheduleName(fee.scheduleName);
			ajax.waitLoading();
		}
		if (StringUtil.notEmpty(fee.productGroup)) {
			this.selectAssignProdGroup(fee.productGroup);
			ajax.waitLoading();
			this.waitLoading();
		}
		if (StringUtil.notEmpty(fee.product)) {
			this.selectAssignProduct(fee.product);
			ajax.waitLoading();
			this.waitLoading();
		}
		if (StringUtil.notEmpty(fee.effectStartDate)) {
			this.setEffectStartDate(fee.effectStartDate);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fee.effectEndDate)){
			this.setEffectEndDate(fee.effectEndDate);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fee.licenseYear)){
			this.selectLicenseYear(fee.licenseYear);
			ajax.waitLoading();
		}
		if (StringUtil.notEmpty(fee.salesChannel)) {
			this.selectSaleChannel(fee.salesChannel);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fee.locationClass)){
			this.selectLocationClass(fee.locationClass);
			ajax.waitLoading();
		}
		if(fee.applicableTaxes.size()>0) {
			for(int i=0;i<fee.applicableTaxes.size();i++){
				String taxName=fee.applicableTaxes.get(i);
				this.selectTax(taxName);
			}
		}
		if(StringUtil.notEmpty(fee.applyRate)){
			this.selectApplyDiscount(fee.applyRate);
			ajax.waitLoading();
		}
		
		if(fee.rateAmount != null){
			this.setRateAmountForActivityFee(fee.rateAmount);
			this.moveFocus();
			ajax.waitLoading();
		}
		if(fee.splitRateBy != null){
			if(fee.splitRateBy.equalsIgnoreCase("Percent")){
				this.selectSplitByPercent();
			}
			if(fee.splitRateBy.equalsIgnoreCase("Amount")){
				this.selectSplitByAmount();
			}
			ajax.waitLoading();
			this.waitLoading();
		}
		if(fee.splitIntoNum != null){
			//Change the number to other, so that it can be changed when select it 
			this.selectSplitIntNumOfAccount("1");
			ajax.waitLoading();
			this.waitLoading();
			
			this.selectSplitIntNumOfAccount(fee.splitIntoNum);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(fee.accounts.size() > 0){
			for(int i=0; i<fee.accounts.size(); i++){
				this.selectAccount(fee.accounts.get(i), i);
				if(i < (fee.accounts.size() -1)){
					this.setPercentOrAmountForAccount(fee.percentOrAmountForEachAccount.get(i), i);
					this.moveFocus();
					ajax.waitLoading();
					this.waitLoading();
				}
			}
		}
	}
	//added by Summer
	public void fillUpVendorFee(FeeScheduleData fee){
		if (StringUtil.notEmpty(fee.productGroup)) {
			this.selectAssignProdGroup(fee.productGroup);
			this.waitLoading();
		}
		if (StringUtil.notEmpty(fee.product)) {
			this.selectAssignProduct(fee.product);
			this.waitLoading();
		}
		if (StringUtil.notEmpty(fee.effectStartDate)) {
			this.setEffectStartDate(fee.effectStartDate);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fee.effectEndDate)){
			this.setEffectEndDate(fee.effectEndDate);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fee.licenseYear)){
			this.selectLicenseYear(fee.licenseYear);
			ajax.waitLoading();
		}
		if (StringUtil.notEmpty(fee.salesChannel)) {
			this.selectSaleChannel(fee.salesChannel);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fee.locationClass)){
			this.selectLocationClass(fee.locationClass);
			ajax.waitLoading();
		}
		if(fee.applicableTaxes.size()>0) {
			for(int i=0;i<fee.applicableTaxes.size();i++){
				String taxName=fee.applicableTaxes.get(i);
				this.selectTax(taxName);
			}
		}

		if (null != fee.acctCode && fee.acctCode.length() > 0) {
			this.selectAccountCode(fee.acctCode);
			
		}else {
			this.selectAccountCode(1);	
		}
		ajax.waitLoading();
		if(fee.rateAmount != null){
			this.setRateAmountForVendorFee(fee.rateAmount);
			this.moveFocus();
			ajax.waitLoading();
		}


	}
	
	
	
	public void setupFacilityFeeAndClickApply(FeeScheduleData fd){
		this.fillUpFaiclityFee(fd);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public String setupFacilityFee(FeeScheduleData fd) {
		this.setupFacilityFeeAndClickApply(fd);

		String feeID = this.getFeeSchID(); // get fee schedule id when create
		this.clickOK();
		return feeID;
	}
	public void setupActivityFeeAndClickApply(FeeScheduleData fd){
		this.fillUpActivityFee(fd);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	public void setupVendorFeeAndClickApply(FeeScheduleData fd){
		this.fillUpVendorFee(fd);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	public String setupActivityFee(FeeScheduleData fd){
		this.setupActivityFeeAndClickApply(fd);
		String feeID = this.getFeeSchID(); // get fee schedule id when create
		this.clickOK();
		return feeID;

	}
	public String setupVendorFee(FeeScheduleData fd){
		this.setupVendorFeeAndClickApply(fd);
		String feeID = this.getFeeSchID(); // get fee schedule id when create
		this.clickOK();
		return feeID;

	}
	public boolean compareFacilityFeeInfo(FeeScheduleData expSchedule){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Product Category:", expSchedule.productCategory, this.getPrdCategory());
		passed &= MiscFunctions.compareResult("Product Sub Category:", expSchedule.productSubCategory, this.getSubCategory());
		passed &= MiscFunctions.compareResult("Fee Type:", expSchedule.feeType, this.getFeeType());
		passed &= MiscFunctions.compareResult("Schedule Type:", expSchedule.scheduleType, this.getScheduleType());
		passed &= MiscFunctions.compareResult("Schedule Name:", expSchedule.scheduleName, this.getScheduleName());
		passed &= MiscFunctions.compareResult("Discount Type:", expSchedule.discountType, this.getDiscountType());
		passed &= MiscFunctions.compareResult("Product Group:", expSchedule.productGroup, this.getAssignProdGroup());
		passed &= MiscFunctions.compareResult("Product:", expSchedule.product, this.getAssignProduct().split("\\(")[0]);
		passed &= MiscFunctions.compareResult("Effective Start date:", expSchedule.effectStartDate, this.getEffectStartDate());
		passed &= MiscFunctions.compareResult("Effective End date:", expSchedule.effectEndDate, this.getEffectEndDate());
		passed &= MiscFunctions.compareResult("Order Create Start date:", expSchedule.orderCreateStartDate, this.getOrderCreateStartDate());
		passed &= MiscFunctions.compareResult("Order Create end date:", expSchedule.orderCreateEndDate, this.getOrderCreateEndDate());
		passed &= MiscFunctions.compareResult("Sale Channel:", expSchedule.salesChannel, this.getSalesChannel());
		passed &= MiscFunctions.compareResult("Customer Pass:", expSchedule.custPass, this.getCustomerPass());
		List<PromoCode> actPd = this.getPromoCode();
		for(int i=0; i<expSchedule.promoCodes.size(); i++){
			if(!actPd.get(i).code.equalsIgnoreCase(expSchedule.promoCodes.get(i).code)||!actPd.get(i).description.equalsIgnoreCase(expSchedule.promoCodes.get(i).description)){
				logger.info("The "+ i + "th promocode is not correct, expect---" + expSchedule.promoCodes.get(i).toString() + "; but actually is:"
						+ actPd.get(i).toString());
				passed = false;
			}
		}
		passed &= MiscFunctions.compareResult("Fee Applies To:", expSchedule.feeApplyTo, this.getFeeAppliesTo());
//		if(expSchedule.feeApplyTo.equalsIgnoreCase("Deposit")){
//			passed &= MiscFunctions.compareResult("Charge Fee During:", expSchedule.chargeFeeDuring, this.getChargeFeeDuring());
//		}
		passed &= MiscFunctions.compareResult("Apply Discount:", expSchedule.applyRate, this.getApplyDiscount());
		passed &= MiscFunctions.compareResult("Rate Amount:", expSchedule.rateAmount, this.getRateAmountForFacilityFee());
		passed &= MiscFunctions.compareResult("Split By:", expSchedule.splitRateBy, this.splitByType());
		passed &= MiscFunctions.compareResult("Split Into Num:", expSchedule.splitIntoNum, this.getSplitIntoNum());
		List<String> actAccounts = this.getAccounts(Integer.parseInt(this.getSplitIntoNum()));
		if(! (expSchedule.accounts.containsAll(actAccounts)&&actAccounts.containsAll(expSchedule.accounts)) ){
			logger.info("Accounts are not correct, expect:" + expSchedule.accounts.toString()+ ",but actually is:" + actAccounts.toString());
			passed = false;
		}else{
			logger.info("Accounts are correct!");
		}
		List<String> actAmount = this.getPercentOrAmountForEachAccount(Integer.parseInt(this.getSplitIntoNum()));
		if(! (expSchedule.percentOrAmountForEachAccount.containsAll(actAmount)&&actAmount.containsAll(expSchedule.percentOrAmountForEachAccount))){
			logger.info("Percent or amount are not correct, expect:" + expSchedule.percentOrAmountForEachAccount.toString()+ ",but actually is:" + actAmount.toString());
			passed = false;
		}else{
			logger.info("Percent or amount are correct!");
		}
		return passed;
	}
	
	public void verifyFacilityFeeInfo(FeeScheduleData expSchedule){
		if(!this.compareFacilityFeeInfo(expSchedule)){
			throw new ErrorOnPageException("Facility Fee Schedule info is not correct, please check the log above!");
		}
		logger.info("Facility fee schedule information is correct!");
	}
}
