package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

/*
 * $Id: FinMgrFeePenaltyDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;



public class FinMgrFeePenaltyDetailPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrFeePenaltyDetailPage
	 * Generated     : Aug 10, 2005 10:25:10 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/08/10
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrFeePenaltyDetailPage _instance = null;

	private String SLIP_VIEW_PREFIX = "SlipFeePenaltyScheduleView";
	private boolean isSlip = false;
	public final String Type_Seasonal = "Seasonal";
	public final String Type_Lease = "Lease";
	public final String Type_Transient = "Transient";
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrFeePenaltyDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrFeePenaltyDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrFeePenaltyDetailPage();
		}

		return _instance;
	}

	/** Determine if the Fee Penalty Detail page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Fee (Penalty|Schedule) Details", false));
	}
	
	/**
	 * Select product by given value.
	 * @param product
	 */
	public void selectPrdCategory(String product) {
//		if(this.isSlip)
//		{
//			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".prdGrpCat", product);
//		}else
//		{
			browser.selectDropdownList(".id", "prd_grp_cat_id", product);
//		}
		waitLoading();
	}
	
	/**
	 * 
	 * @return selected product category
	 */
	public String getPrdCategory() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", this.SLIP_VIEW_PREFIX+".prdGrpCat", 0);
		}else{
			return browser.getDropdownListValue(".id", "prd_grp_cat_id", 0);	
		}
	  	
	}
	
	/**
	 * Select fee type by given type.
	 * @param type - fee type
	 */
	public void selectFeeType(String type) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".feeType", type);
		}else{
			
		browser.selectDropdownList(".id", "penalty_fee_type", type);
		}
		waitLoading();
	}

	/**
	 * 
	 * @return selected fee type value
	 */
	public String getFeeType() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", this.SLIP_VIEW_PREFIX+".feeType", 0);
		}else{
			return browser.getDropdownListValue(".id", "penalty_fee_type", 0);	
		}	  	
	}
	
	/**
	 * Select assign loop.
	 * @param loop
	 */
	public void selectAssignLoop(String loop) {
		browser.selectDropdownList(".id", new RegularExpression("assignment_loop|SlipFeePenaltyScheduleView-\\d+\\.loopID", false), loop); //Sara "assignment_loop", loop);
		waitLoading();
	}

	/**
	 * 
	 * @return selected assign loop
	 */
	public String getAssignLoop() {
	  	return browser.getDropdownListValue(".id", "assignment_loop", 0);
	}
	
	/**
	 * Select assign dock.
	 * @param loop
	 */
	public void selectAssignDock(String dock) {
		browser.selectDropdownList(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.loopID",false), dock);
		ajax.waitLoading();
		waitLoading();
	}

	/**
	 * 
	 * @return selected assign dock
	 */
	public String getAssignDock() {
	  	return browser.getDropdownListValue(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.loopID",false), 0);
	}
	
	/**
	 * Select assign loop group.
	 * @param group - loop group
	 */
	public void selectAssignProdGroup(String group) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.groupID",false), group);
		}else{
			browser.selectDropdownList(".id", "assignment_prodgroup", group);
		}
		ajax.waitLoading();
		waitLoading();
	}

	/**
	 * 
	 * @return selected assign product group
	 */
	public String getAssignProdGroup() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.groupID",false), 0);
		}else{
			return browser.getDropdownListValue(".id", "assignment_prodgroup", 0);
		}
	}
	
	/**
	 * Select assign product.
	 * @param product
	 */
	public void selectAssignProduct(String product) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.prdID",false), product);
		}else{
			browser.selectDropdownList(".id", "assignment_product", product);
		}
		ajax.waitLoading();
		waitLoading();
	}

	/**
	 * 
	 * @return selected assign product
	 */
	public String getAssignProduct() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.prdID",false), 0);
		}else{
			return browser.getDropdownListValue(".id", "assignment_product", 0);
		}
	}
	
	/**
	 * Fill in the effective date.
	 * @param date - effective date
	 */
	public void setEffectiveDate(String date) {
		if(this.isSlip)
		{
			browser.setTextField(".id", this.SLIP_VIEW_PREFIX+".effectDate_ForDisplay", date, 0, IText.Event.LOSEFOCUS);
		}else{
			browser.setTextField(".id", "date_effective_ForDisplay", date, 0, IText.Event.LOSEFOCUS);	
		}
		
//		this.refresh();
	}
	
	/**
	 * 
	 * @return effective date value
	 */
	public String getEffectiveDate() {
		if(this.isSlip)
		{
			return browser.getTextFieldValue(".id", this.SLIP_VIEW_PREFIX+".effectDate_ForDisplay");
		}else{
			return browser.getTextFieldValue(".id", "date_effective_ForDisplay");
		}
	}

	/**
	 * Fill in inventory start date.
	 * @param date - start date
	 */
	public void setInvStartDate(String date) {
		if(this.isSlip)
		{
			browser.setTextField(".id", this.SLIP_VIEW_PREFIX+".startDate_ForDisplay", date, 0, IText.Event.LOSEFOCUS);
		}else{
			browser.setTextField(".id", "date_start_ForDisplay", date, 0, IText.Event.LOSEFOCUS);	
		}
//		this.refresh();
	}

	/**
	 * 
	 * @return Inventory Start Date
	 */
	public String getInvStartDate() {
		if(this.isSlip)
		{
			return browser.getTextFieldValue(".id", this.SLIP_VIEW_PREFIX+".startDate_ForDisplay");
		}else{
			return browser.getTextFieldValue(".id", "date_start_ForDisplay");
		}
	}
	
	/**
	 * Fill in inventory end date.
	 * @param date -end date
	 */
	public void setInvEndDate(String date) {
		if(this.isSlip)
		{
			browser.setTextField(".id", this.SLIP_VIEW_PREFIX+".endDate_ForDisplay", date, 0, IText.Event.LOSEFOCUS);
		}else{
			browser.setTextField(".id", "date_end_ForDisplay", date, 0, IText.Event.LOSEFOCUS);
		}
//		this.refresh();
	}

	/**
	 * 
	 * @return Inventory end date
	 */
	public String getInvEndDate() {
		if(this.isSlip)
		{
			return browser.getTextFieldValue(".id", this.SLIP_VIEW_PREFIX+".endDate_ForDisplay");
		}else{
			return browser.getTextFieldValue(".id", "date_end_ForDisplay");
		}
	}
	
	/**
	 * Select transaction type.
	 * @param type - transaction type
	 */
	public void selectTransactionType(String type) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".tranType", type);
		}else{
			browser.selectDropdownList(".id", "transaction_type", type);
		}
	}
	
	/**
	 * 
	 * @return selected transaction type
	 */
	public String getTransactionType() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", this.SLIP_VIEW_PREFIX+".tranType", 0);
		}else{
			return browser.getDropdownListValue(".id", "transaction_type", 0);
		}
	}
	
	/**
	 * Select transaction occurrence.
	 * @param type - transaction occurance
	 */
	public void selectTransactionOccu(String type) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".tranOccur", type);	
		}else{
			browser.selectDropdownList(".id", "transaction_occurance", type);
		}
	}
	
	public void selectNumOfFreeChangePerReservation(String numOfFreeChgPerRes){
		browser.setTextField(".id", "numberOfFreeChangeTransactions",numOfFreeChgPerRes,true);
	}
	
	/**
	 * 
	 * @return selected transaction occurence
	 */
	public String getTransactionOccu() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", this.SLIP_VIEW_PREFIX+".tranOccur", 0);
		}else{
			return browser.getDropdownListValue(".id", "transaction_occurance", 0);
		}
	}
	
	public void selectMarinaRateType(String marianRateType){
		if(marianRateType.equals(Type_Seasonal)){
			this.selectMarinaRateTypeRadioButton(0);
		}else if(marianRateType.equals(Type_Lease)){
			this.selectMarinaRateTypeRadioButton(1);
		}else if(marianRateType.equals(Type_Transient)){
			this.selectMarinaRateTypeRadioButton(2);
		}
	}
	
	public void selectMarinaRateTypeRadioButton(int index){
		browser.selectRadioButton(".id", new RegularExpression(this.SLIP_VIEW_PREFIX+"-\\d+\\.marinaRateType",false), index);
	}
	
	/**
	 * Select given permit category
	 * @param permitCat
	 */
	public void selectPermitCategory(String permitCat) {
	  	browser.selectDropdownList(".id", "ticket_cat_id", permitCat);
	}
	
	/**
	 * 
	 * @return selected permit category
	 */
	public String getPermitCategory() {
	  	return browser.getDropdownListValue(".id", "ticket_cat_id", 0);
	}
	
	/**
	 * Select given permit type
	 * @param permitType
	 */
	public void selectPermitType(String permitType) {
	  	browser.selectDropdownList(".id", "permit_type_id", permitType);
	}
	
	/**
	 * 
	 * @return selected permit type
	 */
	public String getPermitType() {
	  	return browser.getDropdownListValue(".id", "permit_type_id", 0);
	}
	/**
	 * Select account code.
	 * @param code - account code
	 */
	public void selectAccountCode(String code) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".accountID", code);
		}else{
			browser.selectDropdownList(".id", "account_code", code);
		}
	}
	
	public void selectFirstAccount(){
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".accountID", 1);
		}else{
			browser.selectDropdownList(".id", "account_code", 1);
		}
	}

	/**
	 * 
	 * @return selected account code
	 */
	public String getAccountCode() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", this.SLIP_VIEW_PREFIX+".accountID", 0);
		}else{
			return browser.getDropdownListValue(".id", "account_code", 0);
		}
	}
	
	/**
	 * Select unit.
	 * @param unit 
	 */
	public void selectUnit(String unit) {
		if(this.isSlip)
		{
			browser.selectDropdownList(".id", this.SLIP_VIEW_PREFIX+".unit", unit);
		}else{
			browser.selectDropdownList(".id", "penalty_units", unit);
		}
		waitLoading();
	}

	/**
	 * 
	 * @return selected unit
	 */
	public String getUnit() {
		if(this.isSlip)
		{
			return browser.getDropdownListValue(".id", this.SLIP_VIEW_PREFIX+".unit", 0);
		}else{
			return browser.getDropdownListValue(".id", "penalty_units", 0);
		}
	}
	
	/**
	 * Input per unit price
	 * @param price
	 */
	public void setUnitValue(String price) {
		if(this.isSlip)
		{
			browser.setTextField(".id", this.SLIP_VIEW_PREFIX+".value", price);
		}else{
			browser.setTextField(".id", "penalty_value", price);
		}
	}
	
	/**
	 * Select consider associated tax as revenue
	 * @param include
	 */
	public void selectIncludeTaxAsRevenue(String include) {
	  if(include.equalsIgnoreCase("Yes")) {
	    browser.selectRadioButton(".id", "includeTaxAsRevenue", ".value", "1");
	  } else {
	    browser.selectRadioButton(".id", "includeTaxAsRevenue", ".value", new RegularExpression("2|0", false)); //"2");
	  }
	}
	
	/**
	 * 
	 * @return per unit price
	 */
	public String getUnitValue() {
		if(this.isSlip)
		{
			return browser.getTextFieldValue(".id", this.SLIP_VIEW_PREFIX+".value");
		}else{
			return browser.getTextFieldValue(".id", "penalty_value");
		}
	}
	
	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/** Click on Apply link. */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void setSlipType(String type){
		if(type.equalsIgnoreCase("Slip"))
		{
			this.isSlip = true;
		}else{
			this.isSlip = false;
		}
	}
	
	/**
	 * This method used to setup a Fee penalty and get the new add penalty id
	 * @param fp-FeePenaltyData
	 * @return penaly id
	 */
	public String setupFeePenalty(FeePenaltyData fp) {
		
		this.fillFeePenalty(fp);
		
		clickApply();
		ajax.waitLoading();
		waitLoading();		
		fp.id = getFeeSchID();
		clickOK();
		return fp.id;

	}

		/**
	 * @param fp
	 */
	public void fillFeePenalty(FeePenaltyData fp) {
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		this.setSlipType(fp.productCategory);
		if(fp.productCategory != null && fp.productCategory.length() > 0) {
		  	selectPrdCategory(fp.productCategory);
		}
		if(fp.feeType != null && fp.feeType.length() > 0) {
		  	selectFeeType(fp.feeType);
		}
		if(null != fp.loop && fp.loop.length() > 0) {
	  		selectAssignLoop(fp.loop);
		}
		if(!StringUtil.isEmpty(fp.dock))
		{
			this.selectAssignDock(fp.dock);
		}
		if(null != fp.productGroup && fp.productGroup.length() > 0) {
		  	selectAssignProdGroup(fp.productGroup);
		}
		if(null != fp.product && fp.product.length() > 0) {
		  	selectAssignProduct(fp.product);
		}
		
		if(null != fp.effectDate && fp.effectDate.length() > 0) {
		  	setEffectiveDate(fp.effectDate);
		  	Object pages = browser.waitExists(this,confirmPage);
			if(confirmPage ==pages){
				confirmPage.dismiss();
				this.waitLoading();
			}
		}else{
			setEffectiveDate(DateFunctions.getDateAfterToday(-5));
		}
		if(null != fp.startInv && fp.startInv.length() > 0) {
		  	setInvStartDate(fp.startInv);
		  	Object pages = browser.waitExists(this,confirmPage);
			if(confirmPage ==pages){
				confirmPage.dismiss();
				this.waitLoading();
			}
		}else{
			setInvStartDate(DateFunctions.getDateAfterToday(-365));
		}
		if(null != fp.endInv && fp.endInv.length() > 0) {
		  	setInvEndDate(fp.endInv);
		  	Object pages = browser.waitExists(this,confirmPage);
			if(confirmPage ==pages){
				confirmPage.dismiss();
				this.waitLoading();
			}
		}else{
			setInvEndDate(DateFunctions.getDateAfterToday(365));
		}
		if(StringUtil.notEmpty(fp.minimumUnitOfStay)){
			this.setMinumuUnitOfStay(fp.minimumUnitOfStay);
		}
		if(StringUtil.notEmpty(fp.minimumNumOfDayBeforeArrivalDay)){
			this.setMinimumNumOfDaysBeforArrivalDate(fp.minimumNumOfDayBeforeArrivalDay);
		}
		if(null != fp.tranType && fp.tranType.length() > 0) {
		  	selectTransactionType(fp.tranType);
		}
		if(null != fp.tranOccur && fp.tranOccur.length() > 0) {
			this.waitLoading();
		  	selectTransactionOccu(fp.tranOccur);
		}
		
		if(!StringUtil.isEmpty(fp.marinaRateType))
		{
			this.selectMarinaRateType(fp.marinaRateType);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		if(null != fp.permitCategory && fp.permitCategory.length() > 0) {
		  	selectPermitCategory(fp.permitCategory);
		}
		if(null != fp.permitType && fp.permitType.length() > 0) {
		  	selectPermitType(fp.permitType);
		}
		if(null != fp.accountCode && fp.accountCode.length() > 0) {
			// if used in setup script no matter if account code is empty, always get it from UI but not
			// from the static datapool or somewhere.
			if(Boolean.parseBoolean(TestProperty.getProperty("forceOperation"))){
				selectFirstAccount();
			}else{
				selectAccountCode(fp.accountCode);
			}
		}else{
			selectFirstAccount();
		}
		
		if(null != fp.includeTax && fp.includeTax.length() > 0){
		  selectIncludeTaxAsRevenue(fp.includeTax);
		}
		
		if(null != fp.permitCategory && fp.permitCategory.length() > 0){
		  selectPermitCategory(fp.permitCategory);
		}
		
		if(null != fp.permitType&& fp.permitType.length() > 0){
		  selectPermitType(fp.permitType);
		}
		  
		if(null != fp.units && fp.units.length() > 0) {
		  	selectUnit(fp.units);
		}
		if(null != fp.value && fp.value.length() > 0) {
		  	setUnitValue(fp.value);
		}
		if(!StringUtil.isEmpty(fp.numOfFreeChgPerRev)){
			selectNumOfFreeChangePerReservation(fp.numOfFreeChgPerRev);
		}
		
	}

		/**
	 * This method used to verify penalty detail
	 * @param penalty
	 */
	public void verifyPenaltyDetail(FeePenaltyData penalty) {
	  	logger.info("Start to Verfiy Penalty Details.");
	  	this.setSlipType(penalty.productCategory);
	  	
	  	if(!penalty.id.equalsIgnoreCase(getFeeSchID())) {
	  	  throw new ErrorOnPageException("Penalty Id " + getFeeSchID() + " not Correct.");
	  	}
	  	if(!penalty.productCategory.equalsIgnoreCase(getPrdCategory())) {
	  	  throw new ErrorOnPageException("Product Category " + getPrdCategory() + " not Correct.");
	  	}
	  	if(!penalty.feeType.equalsIgnoreCase(getFeeType())) {
	  	  throw new ErrorOnPageException("Fee Type " + getFeeType() + " not Correct.");
	  	}
	  	if(!StringUtil.isEmpty(penalty.loop))
	  	{
		  	if(!penalty.loop.equalsIgnoreCase(getAssignLoop())) {
		  	  throw new ErrorOnPageException("Assign Loop " + getAssignLoop() + " not Correct.");
		  	}
	  	}
	  	if(!StringUtil.isEmpty(penalty.dock))
	  	{
		  	if(!penalty.dock.equalsIgnoreCase(getAssignDock())) {
		  	  throw new ErrorOnPageException("Assign Dock " + getAssignDock() + " not Correct.");
		  	}
	  	}
	  	
	  	if(!penalty.productGroup.equalsIgnoreCase(getAssignProdGroup())) {
	  	  throw new ErrorOnPageException("Assign Product Group " + getAssignProdGroup() + " not Correct.");
	  	}
	  	if(!penalty.product.equalsIgnoreCase(getAssignProduct())) {
	  	  throw new ErrorOnPageException("Assign Product " + getAssignProduct() + " not Correct.");
	  	}
	  	if(!penalty.effectDate.equalsIgnoreCase(getEffectiveDate())) {
	  	  throw new ErrorOnPageException("Effective Date " + getEffectiveDate() + " not Correct.");
	  	}
	  	if(!penalty.startInv.equalsIgnoreCase(getInvStartDate())) {
	  	  throw new ErrorOnPageException("Start Date " + getInvStartDate() + " not Correct.");
	  	}
	  	if(!penalty.endInv.equalsIgnoreCase(getInvEndDate())) {
	  	  throw new ErrorOnPageException("End Date " + getInvEndDate() + " not Correct.");
	  	}
	  	if(!penalty.tranType.equalsIgnoreCase(getTransactionType())) {
	  	  throw new ErrorOnPageException("Transaction Type " + getTransactionType() + " not Correct.");
	  	}
	  	if(!penalty.tranOccur.equalsIgnoreCase(getTransactionOccu())) {
	  	  throw new ErrorOnPageException("Transaction Occurrence " + getTransactionOccu() + " not Correct.");
	  	}
		if(StringUtil.notEmpty(penalty.minimumUnitOfStay)){
			String temp = this.getMinimumUnitOfStay();
			if (!penalty.minimumUnitOfStay.equalsIgnoreCase(temp)) {
				throw new ErrorOnPageException("Minimum Unit of Stay "
						+ temp + " not same with given value "
						+ penalty.minimumUnitOfStay);
			}
		}
		if(StringUtil.notEmpty(penalty.minimumNumOfDayBeforeArrivalDay)){
			String temp = this.getMinimumNumOfDaysBeforeArrivalDate();
			if (!penalty.minimumNumOfDayBeforeArrivalDay.equalsIgnoreCase(temp)) {
				throw new ErrorOnPageException("Minimum Number of Day before arrival date "
						+ temp + " not same with given value "
						+ penalty.minimumNumOfDayBeforeArrivalDay);
			}
		}
	  	if(!StringUtil.isEmpty(penalty.marinaRateType))
	  	{
	  		if(!penalty.marinaRateType.equalsIgnoreCase(this.getMarinaRateTypeOfRadioButton())) {
	  	  	  throw new ErrorOnPageException("Marina Rate type " + this.getMarinaRateTypeOfRadioButton() + " not Correct.");
	  	  	}
	  	}
	  	if(!StringUtil.isEmpty(penalty.accountCode))
	  	{
		  	if(!penalty.accountCode.equalsIgnoreCase(getAccountCode())) {
		  	  throw new ErrorOnPageException("Account Code " + getAccountCode() + " not Correct.");
		  	}
	  	}
	  	if(!penalty.units.equalsIgnoreCase(getUnit())) {
	  	  throw new ErrorOnPageException("Unit " + getUnit() + " not Correct.");
	  	}
	  	if(!penalty.value.equalsIgnoreCase(getUnitValue())) {
	  	  throw new ErrorOnPageException("Value " + getUnitValue() + " not Correct.");
	  	}
	}
	
	/**
	 * 
	 * @return penalty Id
	 */
	public String getFeeSchID() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Update Fee Penalty.*|^Add Fee Penalty.*", false));
		String feeScheduleId = ((IHtmlTable)objs[1]).getCellValue(0, 1).split("Fee Penalty ID")[1].trim();
				
		Browser.unregister(objs);
		return feeScheduleId;
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
	
	public String getMarinaRateTypeOfRadioButton(){
		IHtmlObject[] radios = browser.getRadioButton(".id", new RegularExpression("SlipFeePenaltyScheduleView-\\d+\\.marinaRateType(\\d+)?", false));
		String type = null;
		
		if(radios.length<1)
		{
			return type;
		}
//		int i=0;
		for(IHtmlObject o: radios)
		{
			IRadioButton rad= (IRadioButton)o; 
			if(rad.isSelected())
			{
				String id = rad.id();
				IHtmlObject[] labels = browser.getHtmlObject(".class","Html.LABEL",".for", id);	
				type = labels[0].text();
				Browser.unregister(labels);
				return type;
			}
//			i++;

		}
		Browser.unregister(radios);

		return type;
	}
	
	public String getMsg()
	{
		String error = "";
		IHtmlObject[] errorMsg = browser.getHtmlObject(".id",	"NOTSET");
		if(errorMsg.length>0)
		{
			error = errorMsg[0].text();
		}
		Browser.unregister(errorMsg);
		return error;
	}
	
	private void refresh() {
		browser.clickGuiObject(".class", "Html.TD", ".text", new RegularExpression("^Product Category",false));
	}
	/**
	 * check element enable or not.
	 * @param id
	 * @return
	 */
	public boolean checkElementEnable(String id){
		boolean isEnable = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(id,false));
		if(objs.length<1){
			throw new ErrorOnPageException("No element was found in page");
		}
		String enableText = objs[0].getAttributeValue("isDisabled");
		if(enableText.equals("true")){
			isEnable = false;
		}
		return isEnable;
	}
	/**
	 * check fee type enable.
	 * @return
	 */
	public boolean checkFeeTypeEnable(){
		return this.checkElementEnable("SlipFeePenaltyScheduleView.feeType");
	}
	/**
	 * check dock enable.
	 * @return
	 */
	public boolean checkDockEnable(){
		return this.checkElementEnable("SlipFeePenaltyScheduleView-\\d+\\.loopID");
	}
	/**
	 * check product group enable.
	 * @return
	 */
	public boolean checkProductGroupEnable(){
		return this.checkElementEnable("SlipFeePenaltyScheduleView-\\d+\\.groupID");
	}
	/**
	 * check product enable.
	 * @return
	 */
    public boolean checkProcutEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView-\\d+\\.prdID");
    }
    /**
     * check effective date enabel.
     * @return
     */
    public boolean checkEffeDateEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.effectDate_ForDisplay");
    }
    /**
     * check inventory start date enable.
     * @return
     */
    public boolean checkInvStartDateEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.startDate_ForDisplay");
    }
    /**
     * check inventory end date enable.
     * 
     */
    public boolean checkInvEndDateEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.endDate_ForDisplay");
    }
    /**
     * check  transaction type enable.
     * 
     */
    public boolean checkTranscTypeEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.tranType");
    }
    /*
     * check transaction occurrence enable
     */
    public boolean checkTrancOccrEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.tranOccur");
    }
    /*
     * check code enable  enable
     */
    public boolean checkCodeEnable(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.accountID");
    }
    /*
     * check code penalty unit enable.
     */
    public boolean checkPenaltyUnit(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.unit");
    }
    /**
     * check penalty value enable.
     */
    public boolean checkPenaltyValue(){
    	return this.checkElementEnable("SlipFeePenaltyScheduleView.value");
    }
    
    public void verifyPenaltyCreateionInfoEnalbe(){
    	boolean isEnable =true;
    	if(!this.checkFeeTypeEnable()){
    		isEnable &= false;
    		logger.error("Fee Type should enalbe");
    	}if(!this.checkDockEnable()){
    		isEnable &= false;
    		logger.error("Dock should enabel");
    	}if(!this.checkProductGroupEnable()){
    		isEnable &= false;
    		logger.error("Product group should enable");
    	}if(!this.checkProcutEnable()){
    		isEnable &= false;
    		logger.error("Product should enable");
    	}if(!this.checkEffeDateEnable()){
    		isEnable &= false;
    		logger.error("Effective date should enable");
    	}if(!this.checkInvStartDateEnable()){
    		isEnable &= false;
    		logger.error("Inventory start date should enable");
    	}if(!this.checkInvEndDateEnable()){
    		isEnable &= false;
    		logger.error("Inventory end date should enable");
    	}if(!this.checkTranscTypeEnable()){
    		isEnable &= false;
    		logger.error("transaction type should enable");
    	}if(!this.checkTrancOccrEnable()){
    		isEnable &= false;
    		logger.error("transaction occurrence should enable");
    	}if(!this.checkCodeEnable()){
    		isEnable &= false;
    		logger.error("Code should enable");
    	}if(!this.checkPenaltyUnit()){
    		isEnable &= false;
    		logger.error("Penalty unit should enable");
    	}if(!this.checkPenaltyValue()){
    		isEnable &= false;
    		logger.error("Penalty value should enable");
    	}
    	
    	if(!isEnable){
    		throw new ErrorOnPageException("Penalty creation info should enable");
    	}
    }
    
    public boolean checkMarinaTateType(int index){
    	return browser.isRadioButtonSelected(".id", new RegularExpression("SlipFeePenaltyScheduleView-\\d+\\.marinaRateType",false), index);
    }
    
    public String getSelectedMarinaTateType(){
    	String marinaType = "";
    	if(this.checkMarinaTateType(0)){
    		marinaType = "Seasonal";
    	}if(this.checkMarinaTateType(1)){
    		marinaType = "Lease";
    	}if(this.checkMarinaTateType(2)){
    		marinaType = "Transient";
    	}
    	return marinaType;
    }
    
 /*   public List<String> getMarinaRateTypeList(){
		List<String> rateTypeList = new ArrayList<String>();
		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABLE", ".for", new RegularExpression("SlipFeePenaltyScheduleView-\\d+\\.marinaRateType",false));
		if(objs.length<1){
			throw new ErrorOnPageException("No element was exist");
		}
		for(int i=0;i<objs.length;i++){
			String rateType = objs[0].getProperty(".text");
			rateTypeList.add(rateType);
		}
		return rateTypeList;
	}*/
    public List<String> getMarinaRateTypeList(){
		IHtmlObject[] radios = browser.getRadioButton(".id", new RegularExpression("SlipFeePenaltyScheduleView-\\d+\\.marinaRateType(\\d+)", false));
		if(radios.length<1){
			throw new ItemNotFoundException("Did not get Maria rate type radio button object.");
		}
//		String id = radios[0].id(); //all marina rate type radio button id is same
//		IHtmlObject[] labels = browser.getHtmlObject(".class","Html.LABEL",".for", id);
//		if(labels.length<1){
//			throw new ItemNotFoundException("Did not get Maria rate type label object.");
//		}
		List<String> elements = new ArrayList<String>();
		for(IHtmlObject radio : radios){
			String value = browser.getObjectText(".class","Html.LABEL",".for", radio.id());
			elements.add(value);
		}
		Browser.unregister(radios);
		return elements;
	}
	
	
	/**
	 * get fee type list.
	 * @return
	 */
	public List<String> getFeeTypeList(){
		return browser.getDropdownElements(".id", "SlipFeePenaltyScheduleView.feeType");
	}
	/**
	 * get penalty unit list.
	 * @return
	 */
	public List<String> getPenaltyUnitList(){
		return browser.getDropdownElements(".id", "SlipFeePenaltyScheduleView.unit");
	}
	/**
	 * get transaction type list.
	 * @return
	 */
	public List<String> getTransTypeLit(){
		return browser.getDropdownElements(".id", "SlipFeePenaltyScheduleView.tranType");
	}
	/**
	 * get error message.
	 * @return
	 */
	public String getError() {
		IHtmlObject[] objs=browser.getHtmlObject(".classname", "message msgerror");
		if(objs.length<1){
			throw new ErrorOnPageException("can not find the error message object...");
		}
		String text=objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return text;
	}
	/*
	 * get account account
	 */
	public List<String> getAccountCount(){
		return browser.getDropdownElements(".id", "SlipFeePenaltyScheduleView.accountID");
	}
	
	public void setNumOfFreeChangesPerTrans(String num){
		browser.setTextField(".id", "numberOfFreeChangeTransactions", num);
	}
}
