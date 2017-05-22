/*
 * Created on Dec 9, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.voucher;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrVoucherProgramDetailsPage extends FinanceManagerPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrVoucherProgramDetailsPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrVoucherProgramDetailsPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrVoucherProgramDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrVoucherProgramDetailsPage();
		}
		return _instance;
	}

	/** Check this page is exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"voucherprogramdetails");
	}

	/** Select programe type from drop down list */
	public void selectProgramType(String programType) {
		new Thread() {
			public void run() {
				ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
				while (!confirm.exists()) {
					
				}
				confirm.waitLoading();
			};
		}.start();
		
		browser.selectDropdownList(".id", "voucher_program_type", programType);
//		waitExists();
	}

	/**
	 * get selected program type value
	 * @return
	 */
	public String getProgramType() {
		return browser.getDropdownListValue(".id", "voucher_program_type");
	}
	
	/**
	 * Input program name
	 * 
	 * @param programName
	 */
	public void setProgramName(String programName) {
		browser.setTextField(".id", "voucher_program_name", programName,IText.Event.LOSEFOCUS);
	}
	
	/**
	 * select line of business
	 * @param lineOfBusiness
	 */
	public void selectLineOfBusiness(String lineOfBusiness) {
		browser.selectDropdownList(".id", "line_of_business", lineOfBusiness);
	}
	
	/**
	 * Input programe effective start date
	 * 
	 * @param start
	 */
	public void setEffectiveStart(String start) {
		browser.setCalendarField(".id", "effective_start_date_ForDisplay",start);
	}

	/**
	 * Input programe effective end date
	 * 
	 * @param end
	 */
	public void setEffectiveEnd(String end) {
		browser.setCalendarField(".id", "effective_end_date_ForDisplay", end);
	}

	/**
	 * Select account code
	 * 
	 * @param code
	 */
	public void selectAccountCode(String code) {
		if(!StringUtil.isEmpty(code)){
			browser.selectDropdownList(".id", "account_code", code);
		}else{
			browser.selectDropdownList(".id", "account_code", 1);
		}
	}

	/** Click OK button */
	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click Apply Button */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public boolean checkProgramTypeDropDownListIsEixsting(){
		return browser.checkHtmlObjectDisplayed(".id", "voucher_program_type");
	}

	/**
	 * This method used to add new voucher program
	 * 
	 * @param vp
	 *            VoucherProgram
	 */
	public void setUpNewVoucherProgram(VoucherProgram vp) {
		if (vp.programeType != null && !vp.programeType.equals("") && this.checkProgramTypeDropDownListIsEixsting()) {
			selectProgramType(vp.programeType);
			waitLoading();
		}
		setProgramName(vp.programeName);
		ajax.waitLoading();
		this.waitLoading();
		selectLineOfBusiness(vp.lineOfBusiness);
		if (vp.createLocation != null && !vp.createLocation.equals("")) {
			this.selectLocationForCreation(vp.createLocation, vp.locationCategoryForCreation);
//			ISelect dropdown = getLocationForCreationDropdown();
//			if (dropdown != null) {
//				if (dropdown.getAllOptions().contains(vp.createLocation)) {
//					dropdown.select(vp.createLocation);
//					Browser.sleep(1);
//					this.waitLoading();
//				} else {
//					dropdown.select(new RegularExpression("Select\\.*", false));
//					selectLocation(vp.createLocation, vp.locationCategoryForCreation);
//				}
//			}
			
		}
		if (vp.productCategoryForCreation != null
				&& !vp.productCategoryForCreation.equals("")) {
			selectProdCatForCreation(vp.productCategoryForCreation);
		}
		if (vp.emergencyCancellation != null
				&& !vp.emergencyCancellation.equals("")) {
			selectEmergencyCancelRadio(vp.emergencyCancellation);
		}
		if (vp.redirectionToRefund != null
				&& !vp.redirectionToRefund.equals("")) {
			selectRedirectToRefund(vp.redirectionToRefund);
		}
		// update by lesley wang
		if (vp.redirectionToRefundWeb != null
				&& !vp.redirectionToRefundWeb.equals("")) {
			selectRedirectToRefundWeb(vp.redirectionToRefundWeb);
		}	
		if (vp.locationForUse != null && !vp.locationForUse.equals("")) {
//			ISelect dropdown = getLocationForUseDropdown();
//			if (dropdown.getAllOptions().contains(vp.locationForUse)) {
//				dropdown.select(vp.locationForUse);
//				Browser.sleep(1);
//				this.waitLoading();
//			} else {
//				dropdown.select(new RegularExpression("Select\\.*", false));
//				selectLocation(vp.locationForUse, vp.locationCategoryForUse);
//			}
			selectLocationForUse(vp.locationForUse, vp.locationCategoryForUse);
		}
		if (vp.productCategoryForUse != null
				&& !vp.productCategoryForUse.equals("")) {
			selectProdCatForUse(vp.productCategoryForUse);
		}
		if (vp.sameBillCustomer != null && !vp.sameBillCustomer.equals("")) {
			selectSameBillCustRadio(vp.sameBillCustomer);
		}
		
		if(vp.expireIndicator)
		{
			selectExpiryIndicator(vp.expireIndicator);
			if(!StringUtil.isEmpty(vp.expiryUnit))
			{
				selectExpiryUnit(vp.expiryUnit);
			}
			if(!StringUtil.isEmpty(vp.expiryPeriod))
			{
				setExpiryPeriod(vp.expiryPeriod);
			}
			
			if(!StringUtil.isEmpty(vp.expiryMethod))
			{
				selectExpiryPeriodCalculationMethod(vp.expiryMethod);
			}
			
		}else{
			selectExpiryIndicator(vp.expireIndicator);
		}
		
		if(!StringUtil.isEmpty(vp.inactivityFeesIndicator))
		{
			if(vp.inactivityFeesIndicator.equalsIgnoreCase("true"))
			{
				selectInactivityFeesIndicator(vp.inactivityFeesIndicator);
				if(!StringUtil.isEmpty(vp.inactivityFeesUnit))
				{
					selectInactivityFeesUnit(vp.inactivityFeesUnit);
				}
				if(!StringUtil.isEmpty(vp.inactivityFeesPeriod))
				{
					setInactivityFeesPeriod(vp.inactivityFeesPeriod);
				}
				
				if(!StringUtil.isEmpty(vp.inactivityFeesFrequency))
				{
					selectInactivityFeesFrequency(vp.inactivityFeesFrequency);
				}
				
				if(!StringUtil.isEmpty(vp.inactivityFeeRestrictionsIndicator))
				{
					selectInactivityFeeRestrictionsIndicator(vp.inactivityFeeRestrictionsIndicator);
					
					if(!StringUtil.isEmpty(vp.inactivityFeeRestriction))
					{
						setInactivityFeeRestriction(vp.inactivityFeeRestriction);
					}
				}
			}else{
				selectInactivityFeesIndicator(vp.inactivityFeesIndicator);
			}
		}
		
		if(StringUtil.notEmpty(vp.allowMailingGiftCard)){
			this.selectActivationRestrictionIndicator(vp.allowMailingGiftCard);
		}
		
		if (vp.startDate != null && vp.startDate.length() > 0) {
			setEffectiveStart(vp.startDate);
		}
		if (vp.endDate != null && vp.endDate.length() > 0) {
			setEffectiveEnd(vp.endDate);
			this.clickEffectiveEndLabel();
		}
		selectAccountCode(vp.account);
	}

	/**
	 * This method used to edit existing voucher program
	 * 
	 * @param vp
	 *            VoucherProgram
	 */
	public void updateVoucherProgram(VoucherProgram vp) {
	
	
	
		if(vp.expireIndicator)
		{
			selectExpiryIndicator(vp.expireIndicator);
			if(!StringUtil.isEmpty(vp.expiryUnit))
			{
				selectExpiryUnit(vp.expiryUnit);
			}
			if(!StringUtil.isEmpty(vp.expiryPeriod))
			{
				setExpiryPeriod(vp.expiryPeriod);
			}
			
			if(!StringUtil.isEmpty(vp.expiryMethod))
			{
				selectExpiryPeriodCalculationMethod(vp.expiryMethod);
			}
			
		}else{
			selectExpiryIndicator(vp.expireIndicator);
		}
		
		if(!StringUtil.isEmpty(vp.inactivityFeesIndicator))
		{
			if(vp.inactivityFeesIndicator.equalsIgnoreCase("true"))
			{
				selectInactivityFeesIndicator(vp.inactivityFeesIndicator);
				if(!StringUtil.isEmpty(vp.inactivityFeesUnit))
				{
					selectInactivityFeesUnit(vp.inactivityFeesUnit);
				}
				if(!StringUtil.isEmpty(vp.inactivityFeesPeriod))
				{
					setInactivityFeesPeriod(vp.inactivityFeesPeriod);
				}
				
				if(!StringUtil.isEmpty(vp.inactivityFeesFrequency))
				{
					selectInactivityFeesFrequency(vp.inactivityFeesFrequency);
				}
				
				if(!StringUtil.isEmpty(vp.inactivityFeeRestrictionsIndicator))
				{
					selectInactivityFeeRestrictionsIndicator(vp.inactivityFeeRestrictionsIndicator);
					
					if(!StringUtil.isEmpty(vp.inactivityFeeRestriction))
					{
						setInactivityFeeRestriction(vp.inactivityFeeRestriction);
					}
				}
			}else{
				selectInactivityFeesIndicator(vp.inactivityFeesIndicator);
			}
		}
		
		if(StringUtil.notEmpty(vp.allowMailingGiftCard)){
			this.selectActivationRestrictionIndicator(vp.allowMailingGiftCard);
		}
		
		if (vp.startDate != null && vp.startDate.length() > 0) {
			setEffectiveStart(vp.startDate);
		}
		if (vp.endDate != null && vp.endDate.length() > 0) {
			setEffectiveEnd(vp.endDate);
			this.clickEffectiveEndLabel();
		}
		
		if(!StringUtil.isEmpty(vp.account))
		{
			selectAccountCode(vp.account);
		}
	}
	
	
	/**
	 * @param inactivityFeeRestriction
	 */
	private void setInactivityFeeRestriction(String inactivityFeeRestriction) {
		browser.setTextField(".id", "charge_amount", inactivityFeeRestriction);
		
	}

	/**
	 * @param inactivityFeeRestrictionsIndicator
	 */
	private void selectInactivityFeeRestrictionsIndicator(
			String inactivityFeeRestrictionsIndicator) {
		
		browser.selectRadioButton(".id", "inactivityFeeRestrictionsIndicator",
				".value", ((inactivityFeeRestrictionsIndicator.equalsIgnoreCase("true"))?"true":"false"));
		this.waitLoading();
	}

	/**
	 * @param inactivityFeesFrequency
	 */
	private void selectInactivityFeesFrequency(String inactivityFeesFrequency) {
		// inactivity_fee_frequency
		if(inactivityFeesFrequency.equalsIgnoreCase("Monthly"))
		{
			browser.selectRadioButton(".id", "inactivity_fee_frequency", ".value", "1");
		}else if(inactivityFeesFrequency.equalsIgnoreCase("Annualy"))
		{
			browser.selectRadioButton(".id", "inactivity_fee_frequency", ".value", "2");
		}else if(inactivityFeesFrequency.equalsIgnoreCase("Daily"))
		{
			browser.selectRadioButton(".id", "inactivity_fee_frequency", ".value", "3");
		}
		this.waitLoading();
		
	}

	/**
	 * @param inactivityFeesPeriod
	 */
	private void setInactivityFeesPeriod(String inactivityFeesPeriod) {
		browser.setTextField(".id", "inactivity_fee_period", inactivityFeesPeriod);
		
	}

	/**
	 * @param inactivityFeesUnit
	 */
	private void selectInactivityFeesUnit(String inactivityFeesUnit) {
		browser.selectDropdownList(".id", "inactivity_fee_unit", inactivityFeesUnit);
		
	}

	/**
	 * @param inactivityFeesIndicator
	 */
	private void selectInactivityFeesIndicator(String inactivityFeesIndicator) {
		browser.selectRadioButton(".id", "inactivityFeeIndicator",
				".value", ((inactivityFeesIndicator.equalsIgnoreCase("true"))?"true":"false"));
		this.waitLoading();
		
	}
	
	private void selectActivationRestrictionIndicator(String activationRestrictionIndicator){
		browser.selectRadioButton(".id", "activationRestrictionIndicator",
				".value", ((activationRestrictionIndicator.equalsIgnoreCase("true"))?"true":"false"));
		this.waitLoading();
	}

	/**
	 * @param expiryPeriodCalculationMethod
	 */
	private void selectExpiryPeriodCalculationMethod(
			String expiryPeriodCalculationMethod) {
		browser.selectDropdownList(".id", "expiry_period_calculation_method", expiryPeriodCalculationMethod);
		
	}

	/**
	 * @param expiryPeriod
	 */
	private void setExpiryPeriod(String expiryPeriod) {
		browser.setTextField(".id", "expiry_period", expiryPeriod);
		
	}

	/**
	 * @param expiryUnit
	 */
	private void selectExpiryUnit(String expiryUnit) {
		browser.selectDropdownList(".id", "expiry_period_unit", expiryUnit);
	}

	/**
	 * @param expireIndicator
	 */
	private void selectExpiryIndicator(boolean expireIndicator) {
		browser.selectRadioButton(".id", "expiry",
				".value", ((expireIndicator)?"true":"false"));
		this.waitLoading();
		
	}

	private void clickEffectiveEndLabel(){
		browser.clickGuiObject(".class", "Html.LABEL",".text","Effective End");
	}

	private void selectLocation(String location, String locationCategory) {
		waitSearchLocationExists();
		setLocationSearchValue(location);
		selectLocationCategory(locationCategory);
		clickSearchLocation();

		browser.searchObjectWaitExists(".class", "Html.A", ".text",
				new RegularExpression("Select", false));

		selectLocationFromSearchResult();
		this.waitLoading();
	}
	
	public void selectLocationForCreation(String location,String locationCategory){
		browser.selectDropdownList(".id","location_for_creation",1,1);//select 'Select.....' option
		selectLocation(location, locationCategory);
	}

	private void waitSearchLocationExists() {
		browser.searchObjectWaitExists(".class", "Html.A", ".text",
				new RegularExpression("^\\s*Location\\s*$", false));
	}

	private ISelect getLocationForUseDropdown() {
		return getVisibleSelectByTopAndSelfId("locations_for_use",
				"location_for_use");
	}

	public void selectSearchBy(String searchType) {
		browser.selectDropdownList(".id", "location_search", searchType);
	}

	public void setLocationSearchValue(String value) {
		browser.setTextField(".id", "locationName", value);
	}

	public void selectLocationCategory(String category) {
		browser.selectDropdownList(".id", "showCategory", category);
	}

	public void clickSearchLocation() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Search", false));
	}

	public void selectLocationFromSearchResult() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Select", false));
	}

	private ISelect getLocationForCreationDropdown() {
		return getVisibleSelectByTopAndSelfId("locations_for_creation",
				"location_for_creation");
	}

	/*
	 * If there is "Add" button under select on this detail page, a hidden
	 * select with same id always above the visible one, we need to filter it
	 * out
	 * 
	 * @param topTableId
	 *            Container id which contains the hidden and visible select
	 * @param selectId
	 *            Select id
	 * @return Visible dropdown list
	 */
	private ISelect getVisibleSelectByTopAndSelfId(String topTableId,
			String selectId) {
		/*
		 * We may have multiple location dropdowns in future, change this to
		 * list at that time
		 */
		IHtmlObject visibleSelection = null;
		
		IHtmlObject[] table = browser.getHtmlObject(".class", "Html.TABLE",
				".id", topTableId);
		if (table != null && table.length > 0) {
			IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY",
					table[0]);
	
			
			for (IHtmlObject tbody : tbodys) {
				if (!"displayNone".equals(tbody.getProperty(".className"))) { // Visible
					visibleSelection = browser.getHtmlObject(".class",
							"Html.SELECT", ".id", selectId, tbody)[0];
					break;
				}
			}
		}
		return (ISelect) visibleSelection;
		
	}

	/**
	 * Select product for creation
	 * 
	 * @param prod
	 */
	public void selectProdCatForCreation(String prod) {
		browser.selectDropdownList(".id","prdcat_for_creation", prod,1);
		waitLoading();
	}

	/**
	 * Select emergency cancel radio button
	 * 
	 * @param str
	 *            yes/no
	 */
	public void selectEmergencyCancelRadio(String str) {
		if (null != str && !str.equals("")) {
			if (str.equalsIgnoreCase("Yes")) {
				browser.selectRadioButton(".id", "emergency_cancellation",
						".value", "true");
			} else if (str.equalsIgnoreCase("No")) {
				browser.selectRadioButton(".id", "emergency_cancellation",
						".value", "false");
			}
		}
	}

	/**
	 * Select Redirect to refund radio
	 * 
	 * @param str
	 *            yes/no
	 */
	public void selectRedirectToRefund(String str) {
		if (null != str && !str.equals("")) {
			if (str.equalsIgnoreCase("Yes")) {
				browser.selectRadioButton(".id", "redirect_to_refund",
						".value", "true");
			} else if (str.equalsIgnoreCase("No")) {
				browser.selectRadioButton(".id", "redirect_to_refund",
						".value", "false");
			}
		}
	}

	/**
	 * Select Redirect to refund radio (Web)
	 * 
	 * @param str
	 *            yes/no
	 */
	public void selectRedirectToRefundWeb(String str) {
		if (null != str && !str.equals("")) {
			if (str.equalsIgnoreCase("Yes")) {
				browser.selectRadioButton(".id", "redirect_to_refund_web",
						".value", "true");
			} else if (str.equalsIgnoreCase("No")) {
				browser.selectRadioButton(".id", "redirect_to_refund_web",
						".value", "false");
			}
		}
	}

	/**
	 * Select Location For use as payment
	 * 
	 * @param location
	 */
	public void selectLocationForUse(String location, String locationCategory) {
		browser.selectDropdownList(".id", "location_for_use",1,1);
		selectLocation(location, locationCategory);
	}

	/**
	 * Select product category For use
	 * 
	 * @param prod
	 */
	public void selectProdCatForUse(String prod) {
		browser.selectDropdownList(".id","prdcat_for_use", prod,1);
		waitLoading();
	}

	/**
	 * Select use same bill customer
	 * 
	 * @param confirm
	 *            yes/no
	 */
	public void selectSameBillCustRadio(String confirm) {
		if (null != confirm && !confirm.equals("")) {
			if (confirm.equalsIgnoreCase("Yes")) {
				browser.selectRadioButton(".id", "same_billing_customer",
						".value", "true");
			} else if (confirm.equalsIgnoreCase("No")) {
				browser.selectRadioButton(".id", "same_billing_customer",
						".value", "false");
			}
		}
	}

	/** After set Total On Hand Amount,Click other space to refresh Page */
	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
//		browser.clickGuiObject(".class", "Html.FORM", ".id", new RegularExpression("Add Voucher Program", false));
		waitLoading();
	}

	/**
	 * This method is used to extend Effective start date
	 * 
	 * @param givenDate
	 * @param extendDaysNum
	 */
	public void extendsEffectiveStartDate(String givenDate, int extendDaysNum) {
		logger.info("Extends Effective Start Date!");
		setEffectiveStart(DateFunctions.getDateAfterGivenDay(givenDate,
				-extendDaysNum));
	}

	/**
	 * This method is used to extend effective end date
	 * 
	 * @param givenDate
	 * @param extendDaysNum
	 */
	public void extendsEffectiveEndDate(String givenDate, int extendDaysNum) {
		logger.info("Extends Effective End Date!");
		setEffectiveEnd(DateFunctions.getDateAfterGivenDay(givenDate,
				extendDaysNum));
	}

	/**
	 * Get the radiobutton checked value in voucher program page.
	 * 
	 * @param key
	 * @param value
	 * @return - the radiobutton checked value
	 */
	public String getRadioButtonCheckedValue(String key, String value) {
		IHtmlObject[] objs = browser.getRadioButton(key, value);
		for (int i = 0; i < objs.length; i++) {
			if (((IRadioButton) objs[i]).isSelected()) {
				if (objs[i].getProperty(".value").toString()
						.equalsIgnoreCase("false")) {
					return "No";
				} else {
					return "Yes";
				}
			}
		}
		Browser.unregister(objs);
		return "";
	}

	/**
	 * Reterive program details information
	 * 
	 * @return Voucher program
	 */
	public VoucherProgram getAllProgramDetails() {
		VoucherProgram vp = new VoucherProgram();
		RegularExpression rex = new RegularExpression(
				"Update Voucher Program Voucher Program ID.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		vp.programId = text.substring(
				text.indexOf("Program ID") + "Program ID".length(),
				text.indexOf("Voucher Program Name")).trim();
		vp.programeName = text.substring(
				text.indexOf("Voucher Program Name")
						+ "Voucher Program Name".length(),
				text.indexOf("Program Type")).trim();
		if(text.indexOf("Customer Account")!=-1){
			vp.programeType = text.substring(
					text.indexOf("Program Type") + "Program Type".length(),text.indexOf("Customer Account")).trim();
		}else{
			vp.programeType = text.substring(
					text.indexOf("Program Type") + "Program Type".length()).trim();
		}
		

		if ("Refund".equals(vp.programeType)) {
			vp.createLocation = browser.getDropdownListValue(".id",
					"location_for_creation", 1).trim();
			
		} else {
			vp.createLocation = browser.getDropdownListValue(".id",
					"location_for_creation", 0).trim();
		}
		if (isProductCategoryForCreationExist()) {
			vp.productCategoryForCreation = browser.getDropdownListValue(".id",
					"prdcat_for_creation", 1).trim();
		}
		if (isEmergencyCancellationExist()) {
			vp.emergencyCancellation = getRadioButtonCheckedValue(".id",
					"emergency_cancellation").trim();
		}
		if (isRedirectionToRefundExist()) {
			vp.redirectionToRefund = getRadioButtonCheckedValue(".id",
					"redirect_to_refund").trim();
		}
		if (isLocationForUseExist() && !isLocationForUseHidden()) {
			vp.locationForUse = browser.getDropdownListValue(".id",
					"location_for_use", 1).trim();
		}
		if (isProductCategoryForUseExist()) {
			vp.productCategoryForUse = browser.getDropdownListValue(".id",
					"prdcat_for_use", 1).trim();
		}
		if (isBillCustomerExist()) {
			vp.sameBillCustomer = getRadioButtonCheckedValue(".id",
					"same_billing_customer").trim();
		}

		vp.startDate = getEffectiveStartDate();
		vp.endDate = getEffectiveEndDate();
		vp.account = getAccountCode();
		return vp;
	}
	
	public String getLineOfBussiness(){
		return browser.getDropdownListValue(".id", "line_of_business");
	}
	
	public String getCreationLocation(){
		return browser.getDropdownListValue(".id", "location_for_creation").trim();
	}
	
	public boolean getExpiryIndicator(){
		return this.getRadioButtonCheckedValue(".id", "expiry").equals("Yes");
	}
	
	public String getExpiryUnit(){
		return browser.getDropdownListValue(".id", "expiry_period_unit");
	}
	
	public String getExpiryPeriod(){
		return browser.getTextFieldValue(".id", "expiry_period");
	}
	
	public String getExpiryMethod(){
		return browser.getDropdownListValue(".id", "expiry_period_calculation_method");
	}
	
	public String getExpiryPeriodAppliesTo(){
		return browser.getDropdownListValue(".id", "expiry_period_applies_to");
	}
	
	public String getInactivityFeesIndicator(){
		return this.getRadioButtonCheckedValue(".id", "inactivityFeeIndicator").equals("Yes")?"true":"false";
	}
	
	public String getInactivityFeesUnit(){
		return browser.getDropdownListValue(".id", "inactivity_fee_unit");
	}
	
	public String getInactivityFeesPeriod(){
		return browser.getTextFieldValue(".id", "inactivity_fee_period");
	}
	
	public String getInactivityFeesFrequency(){
		String frequency = "";
		IHtmlObject[] objs = browser.getRadioButton(".id", "inactivity_fee_frequency");
		for (int i = 0; i < objs.length; i++) {
			if (((IRadioButton) objs[i]).isSelected()) {
				String value = objs[i].getProperty(".value").toString();
				if(value.equalsIgnoreCase("1")){
					frequency = "Monthly";
				}else if(value.equalsIgnoreCase("2")){
					frequency = "Annualy";
				}else{
					frequency = "Daily";
				}
			}
		}
		Browser.unregister(objs);
		return frequency;
	}
	
	public String getInactivityFeeRestrictionsIndicator(){
		return this.getRadioButtonCheckedValue(".id", "inactivityFeeRestrictionsIndicator").equals("Yes")?"true":"false";
	}
	
	public String getInactivityFeeRestriction(){
		return browser.getTextFieldValue(".id", "charge_amount");
	}
	
	public String getInactivityFeesAppliesTo(){
		return browser.getDropdownListValue(".id", "inactivity_fees_applies_to");
	}
		
	public String getAllowMailingOfActivatedGiftCard(){
		return this.getRadioButtonCheckedValue(".id", "activationRestrictionIndicator").equals("Yes")?"true":"false";
	}
	
	public String getEffectiveStartDate(){
		return browser.getTextFieldValue(".id",	"effective_start_date_ForDisplay").trim();
	}

	public String getEffectiveEndDate(){
		return  browser.getTextFieldValue(".id", "effective_end_date_ForDisplay").trim();
	}
	
	public String getAccountCode(){
		return browser.getDropdownListValue(".id", "account_code", 0).trim();
	}

	public boolean isProductCategoryForCreationExist() {
		return browser.checkHtmlObjectExists(".id", "prdcat_for_creation");
	}

	public boolean isEmergencyCancellationExist() {
		return browser.checkHtmlObjectExists(".id", "emergency_cancellation");
	}

	public boolean isRedirectionToRefundExist() {
		return browser.checkHtmlObjectExists(".id", "redirect_to_refund");
	}

	public boolean isLocationForUseExist() {
		return browser.checkHtmlObjectExists( ".id", "location_for_use");
	}

	/**
	 * verify if the 'Location For Use' drop down list is hidden or not
	 * @return
	 */
	public boolean isLocationForUseHidden() {
		IHtmlObject objs[] = browser.getDropdownList(".id", "location_for_use");
		if(objs.length < 1) {
			Browser.unregister(objs);
			return true;
		}
		 boolean hidden = objs[0].getProperty(".type").trim().equals("Hidden") ? true : false;
		 
		 Browser.unregister(objs);
		 return hidden;
	}
	
	public boolean isProductCategoryForUseExist() {
		return browser.checkHtmlObjectExists(".id", "prdcat_for_creation");
	}

	public boolean isBillCustomerExist() {
		return browser.checkHtmlObjectExists(".id", "same_billing_customer");
	}

	/**
	 * Verify Program info in details page is equals with given program
	 * 
	 * @param program
	 */
	public void verifyProgramDetails(VoucherProgram program) {
		logger.info("Verify Voucher Program Deatails information.");

		boolean result = true;
		VoucherProgram vp = getAllProgramDetails();
		result &= MiscFunctions.compareResult("Program ID", program.programId, vp.programId);
		result &= program.programeName.equalsIgnoreCase(vp.programeName);
		result &= MiscFunctions.compareResult("Program Type", program.programeType, vp.programeType);
		result &= MiscFunctions.compareResult("Program Create Location", program.createLocation, vp.createLocation);
		result &= MiscFunctions.compareResult("Program product Category For Creation", program.productCategoryForCreation, vp.productCategoryForCreation);
		result &= MiscFunctions.compareResult("Program emergency Cancellation", program.emergencyCancellation, vp.emergencyCancellation);
		result &= MiscFunctions.compareResult("Program redirection To Refund", program.redirectionToRefund, vp.redirectionToRefund);
		result &= MiscFunctions.compareResult("Program location For Use", program.locationForUse, vp.locationForUse);
		result &= MiscFunctions.compareResult("Program product Category For Use", program.productCategoryForUse, vp.productCategoryForUse);
		result &= MiscFunctions.compareResult("Program allow same Bill Customer", program.sameBillCustomer, vp.sameBillCustomer);
		result &= MiscFunctions.compareResult("Program effective startDate ", program.startDate, vp.startDate);
		result &= MiscFunctions.compareResult("Program effective endDate", program.endDate, vp.endDate);
		result &= MiscFunctions.compareResult("Program account code", program.account, vp.account);
		if(!result){
			throw new ErrorOnPageException("---Error:Check log above....");
		}
	}

	/**
	 * Get voucher program ID
	 * 
	 * @return
	 */
	public String getVoucherProgramID() {
		IHtmlObject[] objs = browser.getTableTestObject(".class", ".SPAN", ".text",
				new RegularExpression("^Voucher Program ID.*", false));
		String temp = objs[0].getProperty(".text").toString();
		String voucherProgramID = temp.split("Voucher Program ID")[1].trim();

		Browser.unregister(objs);
		return voucherProgramID;
	}
	
	public boolean checkSpanObjectEditable(String divName){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class",
				"Html.SPAN", ".text", new RegularExpression(
						"^" + divName + ".*", false));
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", divObjs[0]);
		String value = spanObjs[spanObjs.length-1].getProperty("isDisabled");
		Browser.unregister(spanObjs);
		Browser.unregister(divObjs);
		if ("false".equalsIgnoreCase(value)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * verify voucher program id object could edited or not
	 * 
	 * @return
	 */
	public boolean isVoucherProgramIDObjectEditable() {
		return this.checkSpanObjectEditable("Voucher Program ID");
	}

	/**
	 * verify voucher program name object could edited or not
	 * 
	 * @return
	 */
	public boolean isVoucherProgramNameObjectEditable() {
		return this.checkSpanObjectEditable("Voucher Program Name");
	}

	/**
	 * verify program type object could edited or not
	 * 
	 * @return
	 */
	public boolean isProgramTypeObjectEditable() {
		return this.checkSpanObjectEditable("Program Type");
	}

	/**
	 * verify line of business object could edited or not
	 * 
	 * @return
	 */
	public boolean isLineOfBusinessObjectEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "line_of_business");
		String value = objs[0].getProperty("isDisable");
		Browser.unregister(objs);
		if ("false".equalsIgnoreCase(value)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * verify location object could edited or not
	 * 
	 * @return
	 */
	public boolean isLocationObjectEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				"location_for_creation");
		String value = objs[0].getProperty("isDisable");
		Browser.unregister(objs);
		if ("false".equalsIgnoreCase(value)) {
			return true;
		} else {
			return false;
		}
	}

}
