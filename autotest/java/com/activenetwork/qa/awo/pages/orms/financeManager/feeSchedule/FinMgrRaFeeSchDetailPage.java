/*
 * $Id: FinMgrRaFeeSchDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
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
 * 
 * @author cguo
 */
public class FinMgrRaFeeSchDetailPage extends FinanceManagerPage {

	/**
	 * Script Name : FinMgrRaFeeSchDetailPage Generated : Aug 10, 2005 2:07:03
	 * PM Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2005/08/10
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRaFeeSchDetailPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRaFeeSchDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRaFeeSchDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRaFeeSchDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", 
				"RA Fee Schedule Details");
	}

	/**
	 * Select product by given value.
	 * 
	 * @param product
	 */
	public void selectPrdCategory(String product) {
		browser.selectDropdownList(".id", "prd_grp_cat_id", product);
	}

	/**
	 * Select product sub category
	 * 
	 * @param product
	 */
	public void selectPrdSubCategory(String product) {
		browser.selectDropdownList(".id", "order_type", product);
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
	 * Select assign loop.
	 * 
	 * @param loop
	 */
	public void selectAssignLoop(String loop) {
		if(this.isAssignLoopExists()){
			browser.selectDropdownList(".id", "assignment_loop", loop);
			waitLoading();
		}
		
	}

	public boolean isAssignLoopExists(){
		return browser.checkHtmlObjectExists(".id", "assignment_loop");
	}
	/**
	 * 
	 * @return selected assign loop
	 */
	public String getAssignLoop() {
		return browser.getDropdownListValue(".id", "assignment_loop", 0);
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

	/**
	 * 
	 * @return selected assign product group
	 */
	public String getAssignProdGroup() {
		return browser.getDropdownListValue(".id", "assignment_prodgroup", 0);
	}

	/**
	 * Select assign product.
	 * 
	 * @param product
	 */
	public void selectAssignProduct(String product) {
		browser.selectDropdownList(".id", "assignment_product", product);
		waitLoading();
	}
	
	public void selectAssignPOSProduct(String product) {
		String content = "";
		int i=0;//try three times
		do{
			browser.setTextField(".id", new RegularExpression("s_pn", false), StringUtil.EMPTY);
			browser.setTextField(".id", new RegularExpression("s_pn", false), product, true);
			if(!product.equalsIgnoreCase("All")) {
				Property[] p = Property.toPropertyArray(".className", "ac_results");
				browser.waitExists(p);
				browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
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
	public String getAssignProduct() {
		if(browser.checkHtmlObjectDisplayed(".id", "assignment_product")){
			return browser.getDropdownListValue(".id", "assignment_product", 0);
		}else{
			return browser.getTextFieldValue(".id","s_pn");
		}
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
//		browser.setTextField(".id", "date_effective_ForDisplay", date, 0, IText.Event.LOSEFOCUS);
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_effective_ForDisplay")[0], date);
	}
	
	public void setEffectvieDateAsEmpty(){
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_effective_ForDisplay")[0], StringUtil.EMPTY);
	}
	
	/**
	 * set blank effective date
	 * @param date
	 */
	public void setSpecificEffectiveDate(String date){
//		browser.setTextField(".id", "date_effective_ForDisplay", date);
		setDateAndGetMessage((IText)browser.getTextField(".id", "date_effective_ForDisplay")[0], date);
	}

	/**
	 * 
	 * @return effective date value
	 */
	public String getEffectiveDate() {
		return browser.getTextFieldValue(".id", "date_effective_ForDisplay");
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
	 * Select Location Class
	 * 
	 * @param channel
	 */
	public void selectLocationClass(String channel) {
		channel = channel.replaceFirst("\\d+", "").trim();
		browser.selectDropdownList(".id", "location_class", channel);
	}
	
	public void selectVehicleType(String type) {
		browser.selectDropdownList(".id", "vehicle_type", type);
	}

	/**
	 * get Location Class value
	 * 
	 * @return
	 */
	public String getLocationClass() {
		return browser.getDropdownListValue(".id", "location_class", 0);
	}
	
	public String getVehicleType() {
		return browser.getDropdownListValue(".id", "vehicle_type", 0);
	}

	public List<String> getVehicleTypeValues() {
		return browser.getDropdownElements(".id", "vehicle_type");
	}
	
	public boolean isLicenseYearFromEditable() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", "license_year_from");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find License Year From drop down list object.");
		}
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		
		return enabled;
	}
	
	public boolean isLicenseYearFromExists() {
		return browser.checkHtmlObjectExists(".id", "license_year_from");
	}
	
	/**
	 * Select License Year From
	 * 
	 * @param year
	 */
	public void selectLicenseYearFrom(String year) {
		browser.selectDropdownList(".id", "license_year_from", year);
	}

	public boolean isLicenseYearToExists() {
		return browser.checkHtmlObjectExists(".id", "license_year_to");
	}
	
	public boolean isLicenseYearToEditable() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", "license_year_to");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find License Year To drop down list object.");
		}
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		
		return enabled;
	}
	
	/**
	 * Select License Year To
	 * 
	 * @param year
	 */
	public void selectLicenseYearTo(String year) {
		browser.selectDropdownList(".id", "license_year_to", year);
	}

	/**
	 * 
	 * @return selected sales channel
	 */
	public String getSalesChannel() {
		return browser.getDropdownListValue(".id", "conditions_channel", 0);
	}

	/**
	 * Select Ra Fee earned condition
	 * 
	 * @param value
	 *            - Object's value
	 */
	public void selectRaFeeEarnedOption(String value) {
		browser.selectRadioButton(".name", "earnedoption", ".value", value);
	}

	public boolean isRAFeeEarnedOptionEnabled(String option) {
		IHtmlObject labelObjs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", option);
		if(labelObjs.length < 1) {
			throw new ItemNotFoundException("Cannot find 'When RA Fee Earned' option - " + option);
		}
		
		String id = labelObjs[0].getProperty(".for").trim();
		
		IHtmlObject radioObjs[] = browser.getRadioButton(".id", id);
		boolean enabled = radioObjs[0].isEnabled();
		Browser.unregister(labelObjs);
		Browser.unregister(radioObjs);
		
		return enabled;
	}
	
	/** click remove rate button */
	public void clickRemoveRate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Rate");
	}

	public boolean isAddRateEnable() {
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", "Add Rate");
	}
	
	/** click add rate button */
	public void clickAddRate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Rate");
	}

	/** Click remove thresholdrate button */
	public void clickRemoveThresholdRate() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Remove Threshold Rate");
	}
	
	public void clickRemoveThresholdRate(int i){
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Remove Threshold Rate", i+1);
	}
	
	/** click AddThresholdRate button */
	public void clickAddThresholdRate() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add Threshold Rate");
	}
	
	public void clickAddThresholdRate(int index){
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Add Threshold Rate",index);
	}

	public List<String> getAllEarnedOptions() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression("earnedoption\\d+",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find any 'When RA Fee Earned' options.");
		}
		
		List<String> options = new ArrayList<String>();
		for (IHtmlObject o : objs) {
			options.add(o.getProperty(".text"));
		}
		Browser.unregister(objs);
		
		return options;
	}
	
	/**
	 * 
	 * @return selected RA Fee Earned option
	 */
	public String getSelectedEarnedOption() {
		IHtmlObject[] objs = browser.getRadioButton(".name", "earnedoption");

		String value = "";
		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
				break;
			}
		}

		Browser.unregister(objs);
		if (value.equals("3"))
			return "Order Confirmed (will not be reversed due to non payment)";
		else if (value.equals("2"))
			return "Order Confirmed (will be reversed due to non payment)";
		else
			return "Service Rendered";
	}

	/**
	 * Select transaction type.
	 * 
	 * @param type
	 *            - transaction type
	 */
	public void selectTransactionType(String type, boolean isPopupDialog) {
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
		if(isPopupDialog) {
			ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
			Object pages=browser.waitExists(confirm, this);
			if(pages==confirm) {
				confirm.waitLoading();
				confirm.clickOK();
			}
		}
		this.waitLoading();
	}	
	
	public void selectTransactionType(String type){
		this.selectTransactionType(type,false);
	}

	/**
	 * 
	 * @return selected transaction type
	 */
	public String getTransactionType() {
		return browser.getDropdownListValue(".id", "transaction_type", 0);
	}

	/**
	 * get all product sub category
	 * 
	 * @return
	 */
	public List<String> getAllProductSubCategory() {
		return browser.getDropdownElements(".id", "order_type");
	}

	/**
	 * get Product Sub Category Value
	 * 
	 * @return
	 */
	public String getProductSubCategoryValue() {
		return browser.getDropdownListValue(".id", "order_type", 0);
	}

	/**
	 * get all transaction type value
	 * 
	 * @return
	 */
	public List<String> getAllTransactionType() {
		return browser.getDropdownElements(".id", "transaction_type");
	}

	/**
	 * get all license year from
	 * 
	 * @return
	 */
	public List<String> getLicenseYearFromOptions() {
		return browser.getDropdownElements(".id", "license_year_from");
	}

	/**
	 * get License Year From Value
	 * 
	 * @return
	 */
	public String getLicenseYearFromValue() {
		return browser.getDropdownListValue(".id", "license_year_from", 0);
	}

	/**
	 * get License Year To Value
	 * 
	 * @return
	 */
	public String getLicenseYearToValue() {
		return browser.getDropdownListValue(".id", "license_year_to", 0);
	}
	
	public List<String> getLicenseYearToOptions() {
		return browser.getDropdownElements(".id", "license_year_to");
	}

	/**
	 * get all product group
	 * 
	 * @return
	 */
	public List<String> getAllProductGroup() {
		return browser.getDropdownElements(".id", "assignment_prodgroup");
	}

	/**
	 * get all product
	 * 
	 * @return
	 */
	public List<String> getAllProduct() {
		return browser.getDropdownElements(".id", "assignment_product");
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

	/**
	 * Select account code.
	 * 
	 * @param code
	 *            - account code
	 */
	public void selectAccountCode(String code) {
		browser.selectDropdownList(".id", "account_code", code);
	}
	
	public void selectFirstAccount(){
		browser.selectDropdownList(".id", "account_code", 1);
	}
	
	public void selectPurchaseType(String purchaseType){
		browser.selectDropdownList(".id", "purchasetype_id", purchaseType);
	}
	
	public String getPurchaseType(){
		return browser.getDropdownListValue(".id", "purchasetype_id");
	}
	
	public void selectRAFeeUnit(String value) {
		selectRAFeeUnit(value, true);
	}

	/**
	 * Click on transaction apply Rate
	 * 
	 * @param value
	 *            - object's value
	 */
	public void selectRAFeeUnit(String value, boolean force) {
		browser.selectRadioButton(".id", "feeunitoption", ".value", value, true, 0);
		this.waitLoading();
	}
	
	public boolean isReverseAndRepriceRAFeeEditable() {
		return this.isRadioButtonsEditable("repriceindicator");
	}
	
	public void selectReverseAndRepriceIndicator(boolean indicator){
		int index;
		if(indicator){
			index = 1;
		}else {
			index = 0;
		}
		browser.selectRadioButton(".id", "repriceindicator",index);
		this.waitLoading();
	}
	
	public void selectRateType(String value) {
		browser.selectRadioButton(".id", "servicefee_rateType", ".value", value);
		this.waitLoading();
	}

	/**
	 * 
	 * @return Ra fee unit
	 */
	public String getRAFeeUnit() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "feeunitoption");
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("2"))
			return "Transaction";
		else if(value.equalsIgnoreCase("3")){
			return "Flat by Range of Ticket Quantity";
		}else return "Unit";
			
	}

	/**
	 * 
	 * @return selected account code
	 */
	public String getAccountCode() {
		return browser.getDropdownListValue(".id", "account_code", 0);
	}

	public boolean isBaseRateExists() {
		return browser.checkHtmlObjectExists(".id", "base_rate");
	}
	
	/**
	 * Input base rate
	 * 
	 * @param rate
	 */
	public void setBaseRate(String rate) {
		browser.setTextField(".id", "base_rate", rate);
	}
	
	public boolean isRateChangedUnitsExists() {
		return browser.checkHtmlObjectExists(".id", "rateChangedBase");
	}
	
	public void setRateChangedUnits(String rate){
		browser.setTextField(".id", "rateChangedBase", rate);
	}
	
	public String getRateChangedUnits() {
		return browser.getTextFieldValue(".id", "rateChangedBase");
	}
	
	public boolean isRateFlatAmountExists() {
		return browser.checkHtmlObjectExists(".id", "rateFlatAmountBase");
	}
	
	public void setRateFlatAmount(String rate){
		browser.setTextField(".id", "rateFlatAmountBase", rate);
	}
	
	public String getRateFlatAmount() {
		return browser.getTextFieldValue(".id", "rateFlatAmountBase");
	}
	
	public void setPercentRate(String rate){
		browser.setTextField(".id", "percent",rate);
	}
	
	public void setGroupRate(String rate) {
		browser.setTextField(".id", "group_rate", rate);
	}
	
	public void setFlatAmount(String rate) {
		browser.setTextField(".id", "servicefee_fee", rate);
	}
	
	public void setPercentAmount(String rate) {
		browser.setTextField(".id", "servicefee_percent", rate);
	}
	
	public String getFlatAmount() {
		return browser.getTextFieldValue(".id", "servicefee_fee");
	}
	
	public String getPercentAmount() {
		return browser.getTextFieldValue(".id", "servicefee_percent");
	}

	/**
	 * 
	 * @return Base rate value
	 */
	public String getBaseRate() {
		return browser.getTextFieldValue(".id", "base_rate");
	}
	
	public String getGroupRate() {
		return browser.getTextFieldValue(".id", "group_rate");
	}

	public void selectDeliveryMethod(String deliverymethod){
		browser.selectDropdownList(".id", "delivery_method", deliverymethod);
	}

	public boolean isdeliverymethodExisted(){
		return browser.checkHtmlObjectExists(".id", "delivery_method");
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
	
	public String setupRaFeeSchForAdd(RaFeeScheduleData raFe){
		return this.setupRaFeeSch(raFe, false);
	}
	
	public String setupRaFeeSchForEdit(RaFeeScheduleData raFe){
		return this.setupRaFeeSch(raFe, true);
	}
/**
 * select application product category.
 * @param category
 */
	public void selectAppPrdCategory(String category){
		browser.selectDropdownList(".id", new RegularExpression("assignment_app_prdcat",false), category);
	}
	
	public void selectAppPrdCategoryForList(String category){
		browser.selectDropdownList(".id", new RegularExpression("FeeSchdDetailsView-\\d+\\.applicablePrdCat",false), category);
	}
	
	public void selectAssignProductForList(String product) {
		browser.selectDropdownList(".id", new RegularExpression("FeeSchdDetailsView-\\d+\\.prdID",false), product);
	}
	
	public boolean isRateAppliesToEditable() {
		return this.isRadioButtonsEditable("RateAppliesTo");
	}
	
	public boolean isRateAppliesToEnable() {
		return browser.checkHtmlObjectExists(".id", "RateAppliesTo");
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
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Rate Applies To' radio button object.");
		
		int index = -1;
		for(int i =0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index == -1 ? null : (index == 0 ? OrmsConstants.RATE_APPLIES_TO_NEW_UNITS : OrmsConstants.RATE_APPLIES_TO_NEW_CHANGED_UNITS);
	}
	
	public List<String> getRateAppliesToOptions() {
		return getRadioButtonLabelsById("RateAppliesTo");
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
	
	public void setupRaFeeSchDetailInfo(RaFeeScheduleData raFee,boolean isEdit){
		if (raFee.productSubCategory != null
				&& raFee.productSubCategory.length() > 0) {
			selectPrdSubCategory(raFee.productSubCategory);
		}
		if (raFee.loop != null && raFee.loop.length() > 0) {
			selectAssignLoop(raFee.loop);
		}
		//added for slip
		if (raFee.dock != null && raFee.dock.length() > 0) {
			selectAssignLoop(raFee.dock);
		}
		if(raFee.appPrdCategory != null && raFee.appPrdCategory.length()>0){
			this.selectAppPrdCategory(raFee.appPrdCategory);
			this.waitLoading();
		}
		if(raFee.productCategory.equals("List")) {
			if(!StringUtil.isEmpty(raFee.applicableProductCategory)) {
				this.selectAppPrdCategoryForList(raFee.applicableProductCategory);
				ajax.waitLoading();
				this.waitLoading();
			}
			
			if(!StringUtil.isEmpty(raFee.product)) {
				this.selectAssignProductForList(raFee.product);
				ajax.waitLoading();
				this.waitLoading();
			}
			
			if (raFee.baseRate!=null && raFee.baseRate.length() > 0)
				this.setBaseRate(raFee.baseRate);
		}
		if (raFee.productGroup != null && raFee.productGroup.length() > 0) {
			selectAssignProdGroup(raFee.productGroup);
		}
		
		if(raFee.product != null && raFee.product.length() > 0) {
			if(raFee.productCategory.equalsIgnoreCase("Pos")||raFee.productCategory.equalsIgnoreCase("Activity")){
				selectAssignPOSProduct(raFee.product);
			}else if(!raFee.productCategory.equals("List")){
				selectAssignProduct(raFee.product);
			}
		}
		if (raFee.licenseYearFrom != null && raFee.licenseYearFrom.length() > 0) {
			selectLicenseYearFrom(raFee.licenseYearFrom);
			ajax.waitLoading();
		}
		if (raFee.licenseYearTo != null && raFee.licenseYearTo.length() > 0) {
			selectLicenseYearTo(raFee.licenseYearTo);
		}
		if (raFee.salesChannel != null && raFee.salesChannel.length() > 0) {
			selectSaleChannel(raFee.salesChannel);
		}
		if (raFee.locationClass != null && raFee.locationClass.length() > 0) {
			selectLocationClass(raFee.locationClass);
		}
		if (raFee.vehicleType != null && raFee.vehicleType.length() > 0) {
			selectVehicleType(raFee.vehicleType);
		}
		if (raFee.raFeeOption != null && raFee.raFeeOption.length() > 0) {
			String value = "1";
			if (raFee.raFeeOption
					.equalsIgnoreCase("Order Confirmed (will be reversed due to non payment)")) {
				value = "2";
			} else if (raFee.raFeeOption
					.equalsIgnoreCase("Order Confirmed (will not be reversed due to non payment)")) {
				value = "3";
			} else if (raFee.raFeeOption.equalsIgnoreCase("Service Rendered")) {
				value = "1";
			}
			selectRaFeeEarnedOption(value);
		}

		//select reverse and reprice indicator
		if (raFee.productCategory != null
				&& raFee.productCategory.matches("Site")){			
			selectReverseAndRepriceIndicator(raFee.reverseAndRepriceIndicator);	
		}
				
		if (raFee.tranType != null && raFee.tranType.length() > 0) {
			
			if(isEdit && raFee.productCategory.equalsIgnoreCase("Ticket")){
				this.selectTransactionType(raFee.tranType, true);
			}else{
				this.selectTransactionType(raFee.tranType, false);
			}
			// discussed with group, delete below code. Aug 22, 2013 Nicole
//			//add here for when change transaction type, loop maybe changed as All
//			if (raFee.loop != null && raFee.loop.length() > 0) {
//				selectAssignLoop(raFee.loop);
//			}

			// if transaction type is Change Dates
			if(raFee.tranType.equals(OrmsConstants.TRANNAME_CHANGE_DATE)){
				if(StringUtil.isEmpty(raFee.changeDatesAppliesTo) || raFee.changeDatesAppliesTo.equals("All Change Dates")){
					this.selectAllChangeDates();
				} else {
					this.selectExcludeExtendStay();
				}
				this.waitLoading();
			}
		}
		
		if("Site".equalsIgnoreCase(raFee.productCategory) ||
				"Permit".equalsIgnoreCase(raFee.productCategory) ||
				"Ticket".equalsIgnoreCase(raFee.productCategory) ||
				"Slip".equalsIgnoreCase(raFee.productCategory) ||
				"".equalsIgnoreCase(raFee.productCategory)){
			if (raFee.tranOccur != null && raFee.tranOccur.length() > 0) {
				selectTransactionOccu(raFee.tranOccur);
			}
		}
		if(StringUtil.notEmpty(raFee.minimumUnitOfStay)){
			this.setMinumuUnitOfStay(raFee.minimumUnitOfStay);
		}
		if(StringUtil.notEmpty(raFee.minimumNumOfDayBeforeArrivalDay)){
			this.setMinimumNumOfDaysBeforArrivalDate(raFee.minimumNumOfDayBeforeArrivalDay);
		}
		if (raFee.acctCode != null && raFee.acctCode.length() > 0) {// don't comment this part, some test case specify the account code
			selectAccountCode(raFee.acctCode);
		}else{
			selectFirstAccount();//to handle the account code format changed
		}
		
		if (raFee.applyRate != null && !raFee.applyRate.equals("")) {
			if(isEdit && raFee.productCategory.equalsIgnoreCase("Ticket")){
				this.selectApplyRate(raFee.applyRate, true);
			}else{
				this.selectApplyRate(raFee.applyRate, false);
			}
		}
		
		
		if (raFee.unitOption != null && raFee.unitOption.length() > 0) {
			String value = "2";
			if (raFee.unitOption.indexOf("Unit") != -1) {
				value = "1";
			} else if (raFee.unitOption.indexOf("Transaction") != -1) {
				value = "2";
			}else if(raFee.unitOption.indexOf("Percentage") != -1){
				value = "6";
			}else if(raFee.unitOption.equals("Flat by Range of Ticket Quantity")){
				value = "3";
			}
//			if(this.isApplyRateEditable()){
			//Jane[2014-6-13]:It does not make sense to verify all radio button editable for all apply rate button
			//sometimes, not all radio button were editable, but the one we want to select was editable;
			//So I change code below to select radio button by force.
				selectRAFeeUnit(value, true);
//			}
		}
		// Add rate for site
		if (raFee.productCategory != null
				&& raFee.productCategory.matches("Site|Lottery|POS|GiftCard|Slip")) {
			if(StringUtil.notEmpty(raFee.rateAppliesTo))
			{
				selectRateAppliesTo(raFee.rateAppliesTo);
			}
			
			if (raFee.baseRate != null && raFee.baseRate.length() > 0) {
				setBaseRate(raFee.baseRate);
			}
			if(StringUtil.notEmpty(raFee.changedUnitsRate)){
				setRateChangedUnits(raFee.changedUnitsRate);
			}
			if(StringUtil.notEmpty(raFee.flatAmountRate)){
				setRateFlatAmount(raFee.flatAmountRate);
			}
			
			if(!StringUtil.isEmpty(raFee.percentRate)){
				setPercentRate(raFee.percentRate);
			}

			if (raFee.otherRate != null && !raFee.otherRate.equals("")) {
				String[] otherRate = raFee.otherRate.split("[ ,;]");

				int number = this.getNumOfRemoveRate();

				while (number - 1 > otherRate.length) {
					this.clickRemoveRate();
					number--;
				}

				while (number - 1 < otherRate.length) {
					this.clickAddRate();
					number++;
				}

				for (int i = 0; i < otherRate.length; i++) {
					this.setBaseRateOfOtherRate(otherRate[i], i + 1);
				}
			}
			if(StringUtil.notEmpty(raFee.changedUnitsOtherRate)){
				String[] orChangeUnits = raFee.changedUnitsOtherRate.split("[ ,;]");
				for (int i = 0; i < orChangeUnits.length; i++) {
					this.setChangedUnitsRateOfOtherRate(orChangeUnits[i], i + 1);
				}
			}
			if(StringUtil.notEmpty(raFee.flatAmountOtherRate)){
				String[] orFlatAmount = raFee.flatAmountOtherRate.split("[ ,;]");
				for (int i = 0; i < orFlatAmount.length; i++) {
					this.setFlatAmountRateOfOtherRate(orFlatAmount[i], i + 1);
				}
			}
			
			if(!StringUtil.isEmpty(raFee.maximumFeeRestriction)) {
				this.selectMaximumFeeRestriction(raFee.maximumFeeRestriction);
				this.waitLoading();
			}
			if(!StringUtil.isEmpty(raFee.maximumFeeRate)) {
				this.setMaximumFeeAmount(raFee.maximumFeeRate);
			}
		}

		// For permit
		if (raFee.productCategory != null && raFee.productCategory.length() > 0
				&& raFee.productCategory.equalsIgnoreCase("Permit")) {

			if (raFee.permitCategory != null
					&& !raFee.permitCategory.equals("")) {
				this.selectTicketCategory(raFee.permitCategory);
			}

			if (raFee.permitType != null && raFee.permitType.length() > 0) {
				this.selectPermitType(raFee.permitType);
			}

			if (raFee.personType != null && !raFee.personType.equals("")) {
				this.selectPersonType(raFee.personType);
			}

			if (raFee.baseRate != null && !raFee.baseRate.equals("")) {
				this.setBaseRateForPermit(raFee.baseRate);
			}																						
		}

		if("Slip".equalsIgnoreCase(raFee.productCategory)){
			if(!StringUtil.isEmpty(raFee.marinaRateType))
				this.selectMarinaRateTypeByDropDownList(raFee.marinaRateType);
			
			if (!StringUtil.isEmpty(raFee.baseRate))
				this.setBaseRate(raFee.baseRate);
			
			if(raFee.slipThresholdRates.size()>0)
			{
				int number = this.getNumOfRemoveThresholdRate();

				int j=number-1;
				while (j>0) {
					this.clickRemoveThresholdRate(j-1);
					j--;
				}

				int i=0;
				while (i<raFee.slipThresholdRates.size()) {
					this.clickAddThresholdRate();
					i++;
				}

				for (int m = 0; m < raFee.slipThresholdRates.size(); m++) {
					this.setBaseRateOfOtherRate(raFee.slipThresholdRates.get(m), m+1);
				}
			}
		}
	
		// For ticket
		if (raFee.productCategory != null
				&& raFee.productCategory.equalsIgnoreCase("Ticket")) {
			if (raFee.ticketCategory != null
					&& !raFee.ticketCategory.equals("")) {
				this.selectTicketCategory(raFee.ticketCategory);
			}

			if (raFee.productFeeClass != null
					&& !raFee.productFeeClass.equals("")) {
				this.selectProductFeeClass(raFee.productFeeClass);
			}

			if (raFee.baseRate != null && !raFee.baseRate.equals("") && !raFee.unitOption.equalsIgnoreCase("Flat by Range of Ticket Quantity")) {
				this.setBaseRateForTicket(raFee.baseRate);
			}

			if (raFee.personType != null && !raFee.personType.equals("")) {
				this.selectPersonType(raFee.personType);
			}
			
			
			
			if (raFee.personTypes !=null && raFee.personTypes.length > 0 && raFee.baseRates.length > 0) {
				for (int i = 0; i < raFee.personTypes.length; i++) {
					if (i > 0) {
						this.clickAddTicketType();
						this.waitLoading();
					} 					
					this.selectPersonTypeForTicket(raFee.personTypes[i],i);

					// handle with From Rate and To Rate
					if(raFee.tranType.equalsIgnoreCase("Transfer Tickets - Customer Transfer")
							||raFee.tranType.equalsIgnoreCase("Transfer Tickets - Tour Cancelled")
							||raFee.tranType.equalsIgnoreCase("Change Ticket Date/Time by Customer")
							||raFee.tranType.equalsIgnoreCase("Change Ticket Date/Time - Tour Cancelled"))
					{
						String[] fromTo = new String[2];
						if(raFee.baseRates[i].startsWith(",")){
							fromTo[0]="";
							fromTo[1]=raFee.baseRates[i].replaceAll(",", "");
						}else if(raFee.baseRates[i].endsWith(",")){
							fromTo[0]=raFee.baseRates[i].replaceAll(",", "");
							fromTo[1]="";
						}else{
							fromTo = raFee.baseRates[i].split(",");
						}

						this.setBaseRateForTicket(fromTo[0], i);
						this.setBaseRateForTicket_To(fromTo[1], i);

					}else if(raFee.unitOption
							.equalsIgnoreCase("Flat by Range of Ticket Quantity")){
						for(int j=0; j<raFee.baseRates.length; j++){
							if(j>0){
								this.clickAddRange();
								this.setRange(raFee.rateRanges[j], j);
							}
							this.setRangeRate(raFee.baseRates[j], j);
						}						
					}else{
						this.setBaseRateForTicket(raFee.baseRates[i], i );												
					}

					if(raFee.thresholdRates !=null && i<raFee.thresholdRates.size()){
						if(raFee.thresholdRates.get(i).length>0){
							String[] thresholdRatesOfTicketType = raFee.thresholdRates.get(i);
							for(int j=0; j<thresholdRatesOfTicketType.length; j++){
								String[] fromToThreRate = new String[2];								
								if(thresholdRatesOfTicketType[j].startsWith(",")){
									fromToThreRate[0]="";
									fromToThreRate[1]=thresholdRatesOfTicketType[j].replaceAll(",", "");
								}else if(thresholdRatesOfTicketType[j].endsWith(",")){
									fromToThreRate[0]=thresholdRatesOfTicketType[j].replaceAll(",", "");
									fromToThreRate[1]="";
								}else{
									fromToThreRate = thresholdRatesOfTicketType[j].split(",");
								}

								if(i>0){
									this.clickAddThresholdRate(i);										
									this.setThresholdRatesForOtherTicketType(fromToThreRate[0], j, i);
									if(fromToThreRate.length>1){
										this.setThresholdRatesForOtherTicketType_To(fromToThreRate[1], j, i);
									}

								}else{
									this.clickAddThresholdRate();
									this.setThresholdRatesForFirstTicketType(fromToThreRate[0], j);
									if(fromToThreRate.length>1){
										this.setThresholdRatesForFirstTicketType_To(fromToThreRate[1], j);
									}								
								}

							}
						}
					}

				}
			}			

			if (raFee.deliverymethod != null 
					&& raFee.deliverymethod.length() > 0
					&& isdeliverymethodExisted()) {
				selectDeliveryMethod(raFee.deliverymethod);
			}
		}
        
		// For Privilege,Vehicle,Supply
		if (raFee.productCategory != null
				&& raFee.productCategory.matches("Privilege|VehicleRTI|Supply")) {
			if (raFee.baseRate != null && raFee.baseRate.length() > 0) {
				setBaseRate(raFee.baseRate);
			}
			if (raFee.otherRate != null && raFee.otherRate.length() > 0) {
				setGroupRate(raFee.otherRate);
			}
		}
		
		//For Activity 
		if(raFee.productCategory != null
				&& raFee.productCategory.matches("Activity")) {
			if (raFee.baseRate != null) {
				setBaseRate(raFee.baseRate);
			}
		}
		
		// For service
		if (raFee.productCategory != null
				&& raFee.productCategory.matches("Service")) {
			if (raFee.rateType != null && raFee.rateType.length() > 0) {
				selectRateType(raFee.rateType);
			}
			if (raFee.baseRate != null && raFee.baseRate.length() > 0) {
				setFlatAmount(raFee.baseRate);
			}
			if (raFee.otherRate != null && raFee.otherRate.length() > 0) {
				setPercentAmount(raFee.otherRate);
			}
		}
		
		setEffectiveDate(raFee.effectDate);
		
		//Set for pos
		if(raFee.productCategory != null
				&& raFee.productCategory.equalsIgnoreCase("POS")){
			this.selectPurchaseType(raFee.purchaseType);
		}
	}
	/**
	 * This method used to setup a Ra Fee Schedule
	 * 
	 * @param raFee
	 *            -RaFeeScheduleData
	 * @return new added Fee Schedule Id
	 */
	public String setupRaFeeSch(RaFeeScheduleData raFee, boolean isEdit) {
		this.setupRaFeeSchDetailInfo(raFee,isEdit);
		clickApply();
		ajax.waitLoading();
		waitLoading();
		if (this.checkErrorMsgExist()) {
			return this.getErrorMsg();
		} else {
			raFee.feeId = getFeeSchID();
			clickOK();
			return raFee.feeId;
		}
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
	
	public void clickSpace(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Base Rate");
	}

	/**
	 * Get num of addTicketType
	 * 
	 * @return
	 */
	public int getNumOfAddTicketType() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				"Add Ticket Type");
		int length = obj.length;
		Browser.unregister(obj);
		return length;
	}

	/**
	 * Select ticket category
	 * 
	 * @param category
	 */
	public void selectTicketCategory(String category) {
		browser.selectDropdownList(".id", "ticket_cat_id", category);
		this.waitLoading();
	}

	/**
	 * select permit type
	 * 
	 * @param permitType
	 */
	public void selectPermitType(String permitType) {
		browser.selectDropdownList(".id", "permit_type_id", permitType);
	}

	/**
	 * select product fee class
	 * 
	 * @param fee
	 */
	public void selectProductFeeClass(String fee) {
		browser.selectDropdownList(".id", "product_fee_class_id", fee);
	}

	public String getApplyRate() {
		IHtmlObject objs[] = browser.getRadioButton(".id", "feeunitoption");
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Apply Rate' radio button objects.");
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index == -1 ? null : (index == 0 ? "Per Transaction" : (index == 1 ? "Per Unit" : "Percentage"));
	}
	
	public List<String> getApplyRateOptions() {
		return getRadioButtonLabelsById("feeunitoption");
	}
	
	public void selectApplyRate(String rate) {
		selectApplyRate(rate, false);
	}
	
	/**
	 * select apply rate
	 * 
	 * @param rate
	 */
	public void selectApplyRate(String rate, boolean isPopupDialog) {
		if(browser.checkHtmlObjectExists(".id", "fee_trgt_id"))
		{
			browser.selectDropdownList(".id", "fee_trgt_id", rate);
			if(isPopupDialog) {
				ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
				Object pages=browser.waitExists(confirm, this);
				if(pages==confirm) {
					confirm.waitLoading();
					confirm.clickOK();
				}
			}
		}else if(browser.checkHtmlObjectExists(".id", "feeunitoption"))
		{
			if(rate.equalsIgnoreCase("Per Transaction"))
			{
				browser.selectRadioButton(".id", "feeunitoption",0);
			}else if(rate.equalsIgnoreCase("Per Unit"))
			{
				browser.selectRadioButton(".id", "feeunitoption",1);
			}
		}
		this.waitLoading();
	}

	/**
	 * input base rate for ticket
	 * 
	 * @param rate
	 */
	public void setBaseRateForTicket(String rate) {
		this.setBaseRateForTicket(rate,0);
	}
	
	public void setBaseRateForTicket(String rate, int index){
		browser.setTextField(".id", new RegularExpression("base_from_rate.*",
				false), rate, index);
	}
	
	private String getTicketTypeMarkValue(int index){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("base_rate_ticket_type_.*",false));
		if(objs.length<index){
			throw new ItemNotFoundException("Did not got the ticket type drop down list object of index is " + index);
		}
		String value = objs[index].id().replace("base_rate_ticket_type_", "");
		Browser.unregister(objs);
		return value;
	}
	
	public void setThresholdRatesForFirstTicketType(String text,int index){
		browser.setTextField(".id", new RegularExpression("otherrates_from.*",false), text, index+1);
	}
	
	public void setThresholdRatesForFirstTicketType_To(String text,int index){
		browser.setTextField(".id", new RegularExpression("otherrates_to.*",false), text, index + 1);
	}
	
	public void setThresholdRatesForOtherTicketType(String text,int index, int ticketTypeIndex){
		String markValue = this.getTicketTypeMarkValue(ticketTypeIndex);
		String idValue = "otherrates_from_" + markValue;
		browser.setTextField(".id", idValue, text, index+1);
	}
	
	public void setThresholdRatesForOtherTicketType_To(String text,int index, int ticketTypeIndex){
		String markValue = this.getTicketTypeMarkValue(ticketTypeIndex);
		String idValue = "otherrates_to_" + markValue;
		browser.setTextField(".id", idValue, text, index+1);
	}
	
	public void setBaseRateForTicket_To(String rate, int index){
		browser.setTextField(".id", new RegularExpression("base_to_rate.*",
				false), rate, index);
	}
	
	public void setRange(String range, int index){
		browser.setTextField(".id", "3_2_increment", range, index+1);
	}
	
	public void setRangeRate(String rate, int index){
		browser.setTextField(".id", "3_2_base_increment_rate", rate, index+1);
	}

	/** click add ticket type */
	public void clickAddTicketType() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Ticket Type");
		this.waitLoading();
	}
	
	public void clickAddPersonType(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Person Type");
	}
	
	public void clickRemoveTicketType(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Ticket Type", index+1);
	}

	/** Click Remove Ticket Type */
	public void clickRemoveTicketType() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Remove Ticket Type");
		this.waitLoading();
	}

	/** input thresholdrate for ticket */
	public void setThresholdRateForTicket(String rate) {
		browser.setTextField(".id", "otherrates_from_new", rate);
	}

	/**
	 * select person type for permit
	 * 
	 * @param type
	 */
	public void selectPersonType(String type) {
		browser.selectDropdownList(".id", "base_rate_ticket_type_new",type);
	}

	/** Select person type for ticket */
	public void selectPersonTypeForTicket(String type) {	
		this.selectPersonTypeForTicket(type,0);
	}
	
	public void selectPersonTypeForTicket(String type, int index){
		browser.selectDropdownList(".id", new RegularExpression("base_rate_ticket_type.*|\\d+_\\d+_ticket_type",false), type,index);
	}

	/**
	 * Set base rate for permit
	 * 
	 * @param rate
	 */
	public void setBaseRateForPermit(String rate) {
//		browser.setTextField(".id", "base_from_rate_13", rate);
		browser.setTextField(".id", new RegularExpression("base_from_rate_(13|new)", false), rate);
	}

	/**
	 * set number of other rate
	 * 
	 * @param rate
	 */
	public void setBaseRateOfOtherRate(String rate, int i) {
		browser.setTextField(".id", "otherrates", rate, i);
	}
	
	public String getBaseRateOfOtherRate(int index) {
		return browser.getTextFieldValue(".id", "otherrates", index);
	}
	
	public void setChangedUnitsRateOfOtherRate(String rate, int i) {
		browser.setTextField(".id", "rateChangedUnits", rate, i);
	}
	
	public String getChangedUnitsRateOfOtherRate(int index) {
		return browser.getTextFieldValue(".id", "rateChangedUnits", index);
	}
	
	public void setFlatAmountRateOfOtherRate(String rate, int i) {
		browser.setTextField(".id", "rateFlatAmount", rate, i);
	}

	public String getFlatAmountRateOfOtherRate(int index) {
		return browser.getTextFieldValue(".id", "rateFlatAmount", index);
	}
	
	/**
	 * Set number of threshold rate
	 * 
	 * @param rate
	 * @param i
	 */
	public void setNumOfThresholdRate(String rate, int i) {
		browser.setTextField(".id", "otherrates_from_13", rate, i);
	}

	/**
	 * get the number of remove rate button
	 * 
	 * @return
	 */
	public int getNumOfRemoveRate() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove Rate");
		Browser.unregister(obj);
		return obj.length;
	}
	
	public int getNumOfRemoveThresholdRate() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove Threshold Rate");
		Browser.unregister(obj);
		return obj.length;
	}

	/**
	 * get the number of remove thresholdrate button
	 * 
	 * @return
	 */
	public int getNumOfRemoveThresholdRateButton() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove Threshold Rate");

		Browser.unregister(obj);
		return obj.length;
	}

	/**
	 * get the number of add thresholdrate button
	 * 
	 * @return
	 */
	public int getNumOfAddThresholdRate() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				"Add Threshold Rate");

		Browser.unregister(obj);
		return obj.length;
	}

	/**
	 * verify fee schedule object could edited or not
	 * 
	 * @param id
	 * @return
	 */
	public boolean isFeeSchdIDObjectEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("RA Fee Schedule ID.*",false));
		IHtmlObject[] spanObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), objs[objs.length-1]);
		String value = spanObjs[1].getProperty("isDisabled");
//		HtmlObject[] objs = browser.getHtmlObject(".className",
//				"inputwithrubylabel", ".text", new RegularExpression(
//						"^RA Fee Schedule ID.*", false));
//		String value = objs[0].getProperty("isContentEditable");
		Browser.unregister(spanObjs);
		Browser.unregister(objs);
		
		if ("false".equalsIgnoreCase(value)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * verify location object could edited or not
	 * 
	 * @param loc
	 * @return
	 */
	public boolean isLocationObjectEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Location.*",false));
		IHtmlObject[] spanObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), objs[objs.length-1]);
		String value = spanObjs[spanObjs.length - 1].getProperty("isDisabled");
		if ("false".equalsIgnoreCase(value)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * verify product category object could edited or not
	 * 
	 * @param prd
	 * @return
	 */
	public boolean isProductCategoryEditable(String prd) {
		IHtmlObject[] objs = browser.getDropdownList(".id", "prd_grp_cat_id");
		ISelect list = (ISelect) objs[0];
		List<String> value = list.getAllOptions();
		Browser.unregister(objs);
		if (value.size() == 1 && prd.equals(value.get(0))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * verify product sub category object could edited or not
	 * 
	 * @param prd
	 * @return
	 */
	public boolean isProductSubCategoryEditable() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "order_type");
		String value = objs[0].getProperty("isDisabled");
		Browser.unregister(objs);
		if ("true".equalsIgnoreCase(value)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * verify location class object could edited or not
	 * 
	 * @return
	 */
	public boolean isLocationClassEditable() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "location_class");
		String value = objs[0].getProperty("isDisabled");
		Browser.unregister(objs);
		if ("true".equalsIgnoreCase(value)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * verify RA Fee Condition(When RA Fee Earned) object could edited or not
	 * 
	 * @return
	 */
	public boolean isRAFeeConditionEditable() {
		IHtmlObject[] radioObjs = browser.getRadioButton(".id", new RegularExpression("earnedoption\\d+",false));
		
		IRadioButton radio = (IRadioButton)radioObjs[0];
		boolean editable = radio.isEnabled();
		Browser.unregister(radioObjs);
		return editable;
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
	
	/**
	 * verify apply rate object could edited or not
	 * 
	 * @return
	 */
	public boolean isApplyRateEditable() {
		return this.isRadioButtonsEditable("feeunitoption");
	}

	public boolean isApplyRateEditable(String option) {
		IHtmlObject[] radioObjs = browser.getRadioButton(".id", "feeunitoption");
		
		int index = -1;
		if(option.equalsIgnoreCase("Per Transaction")) {
			index = 0;
		} else if(option.equalsIgnoreCase("Per Unit")) {
			index = 1;
		} else if(option.equalsIgnoreCase("Percentage")) {
			index = 2;
		}
		if(index < 0) throw new ItemNotFoundException("Cannot find Apply Rate option - " + option);
		
		boolean editable = radioObjs[index].isEnabled();
		
		Browser.unregister(radioObjs);
		return editable;
	}
	
	/**
	 * This method used to verify RA Fee Schedule Details info in page is same
	 * with given schedule info
	 * 
	 * @param schedule
	 *            -RaFeeScheduleData
	 */
	public void verifyScheduleDetails(RaFeeScheduleData schedule) {
		logger.info("Start to Verify fee schedule details.");

		if (!schedule.feeId.equalsIgnoreCase(getFeeSchID())) {
			throw new ItemNotFoundException("Fee Schedule Id " + getFeeSchID()
					+ " not same with given value " + schedule.feeId);
		}
		if (schedule.productCategory != null
				&& !schedule.productCategory.equals("")) {
			if (!schedule.productCategory.equalsIgnoreCase(this
					.getPrdCategory())) {
				throw new ItemNotFoundException("Product Category "
						+ getPrdCategory() + " not same with given value "
						+ schedule.productCategory);
			}
			logger.info("Product Category is correct as "+schedule.productCategory);
		}
		if (schedule.productSubCategory != null
				&& !schedule.productSubCategory.equals("")) {
			if (!schedule.productSubCategory.equalsIgnoreCase(this
					.getProductSubCategoryValue())) {
				throw new ItemNotFoundException("Product Sub Category "
						+ getPrdCategory() + " not same with given value "
						+ schedule.productSubCategory);
			}
			logger.info("Product Sub Category is correct as "+schedule.productSubCategory);
		}
		if (!schedule.productCategory.matches("Activity|POS") && schedule.loop != null && !schedule.loop.equals("")) {
			if (!schedule.loop.equalsIgnoreCase(this.getAssignLoop())) {
				throw new ItemNotFoundException("Assignment Loop "
						+ getAssignLoop() + " not same with given value "
						+ schedule.loop);
			}
			logger.info("Assignment Loop is correct as "+schedule.loop);
		}
		
		if (!schedule.productCategory.matches("Activity|POS") && schedule.dock != null && !schedule.dock.equals("")) {
			if (!schedule.dock.equalsIgnoreCase(this.getAssignLoop())) {
				throw new ItemNotFoundException("Assignment dock "
						+ getAssignLoop() + " not same with given value "
						+ schedule.dock);
			}
			logger.info("Assignment dock is correct as "+schedule.dock);
		}
		
		if (schedule.productGroup != null && !schedule.productGroup.equals("")) {
			if (!schedule.productGroup.equalsIgnoreCase(this
					.getAssignProdGroup())) {
				throw new ItemNotFoundException("Assignment Product Group "
						+ getAssignProdGroup() + " not same with given value "
						+ schedule.productGroup);
			}
			logger.info("Assignment Product Group is correct as "+schedule.productGroup);
		}
		if (schedule.product != null && !schedule.product.equals("")) {
			if (!this.getAssignProduct().contains(schedule.product)) {// format on page is like: product name(product ID)
				throw new ItemNotFoundException("Assignment Product "
						+ getAssignProduct() + " not same with given value "
						+ schedule.product);
			}
			logger.info("Assignment Product is correct as "+schedule.product);
		}
		if (schedule.effectDate != null && !schedule.effectDate.equals("")) {
			if (DateFunctions.compareDates(schedule.effectDate, this.getEffectiveDate())!=0) {
				throw new ItemNotFoundException("Effective date "
						+ getEffectiveDate() + " not same with given value "
						+ schedule.effectDate);
			}
			logger.info("Effective date is correct as "+schedule.effectDate);
		}
		if (schedule.licenseYearFrom != null
				&& !schedule.licenseYearFrom.equals("")) {
			if (!schedule.licenseYearFrom.equalsIgnoreCase(this
					.getLicenseYearFromValue())) {
				throw new ItemNotFoundException("License Year From "
						+ getEffectiveDate() + " not same with given value "
						+ schedule.licenseYearFrom);
			}
			logger.info("License Year From is correct as "+schedule.licenseYearFrom);
		}
		if (schedule.licenseYearTo != null
				&& !schedule.licenseYearTo.equals("")) {
			if (!schedule.licenseYearTo.equalsIgnoreCase(this
					.getLicenseYearToValue())) {
				throw new ItemNotFoundException("License Year To "
						+ getEffectiveDate() + " not same with given value "
						+ schedule.licenseYearTo);
			}
			logger.info("License Year To is correct as "+schedule.licenseYearTo);
		}
		if (schedule.salesChannel != null && !schedule.salesChannel.equals("")) {
			if (!schedule.salesChannel.equalsIgnoreCase(this.getSalesChannel())) {
				throw new ItemNotFoundException("Sales Channel "
						+ getSalesChannel() + " not same with given value "
						+ schedule.salesChannel);
			}
			logger.info("Sales Channel is correct as "+schedule.salesChannel);
		}
		if (schedule.locationClass != null
				&& !schedule.locationClass.equals("")) {
			if (!schedule.locationClass.replaceFirst("\\d+", "").trim().equalsIgnoreCase(this
					.getLocationClass())) {
				throw new ItemNotFoundException("Location Class "
						+ getLocationClass() + " not same with given value "
						+ schedule.locationClass);
			}
			logger.info("Location Class is correct as "+schedule.locationClass);
		}
		if (schedule.vehicleType != null
				&& !schedule.vehicleType.equals("")) {
			if (!schedule.vehicleType.equalsIgnoreCase(this.getVehicleType())) {
				throw new ItemNotFoundException("Vehicle Type "
						+ getVehicleType() + " not same with given value "
						+ schedule.vehicleType);
			}
			logger.info("Vehicle Type is correct as "+schedule.vehicleType);
		}
		if (!schedule.productCategory.matches("Activity|POS") && schedule.raFeeOption != null && !schedule.raFeeOption.equals("")) {
			if (!schedule.raFeeOption.equalsIgnoreCase(this
					.getSelectedEarnedOption())) {
				throw new ItemNotFoundException("Selected Earned Option: "
						+ getSelectedEarnedOption()
						+ ", not same with given value: " + schedule.raFeeOption);
			}
			logger.info("Selected Earned Option is correct as "+schedule.raFeeOption);
		}
		if (schedule.tranType != null && !schedule.tranType.equals("")) {
			if (!schedule.tranType.equalsIgnoreCase(this.getTransactionType())) {
				throw new ItemNotFoundException("Selected Transaction Type "
						+ getTransactionType() + " not same with given value "
						+ schedule.tranType);
			}
			logger.info("Selected Transaction Type is correct as "+schedule.tranType);
		}
		if (schedule.tranOccur != null && !schedule.tranOccur.equals("")) {
			if (!schedule.tranOccur.equalsIgnoreCase(this.getTransactionOccu())) {
				throw new ItemNotFoundException(
						"Selected Transaction Occurrence "
								+ getTransactionOccu()
								+ " not same with given value "
								+ schedule.tranOccur);
			}
			logger.info("Selected Transaction Occurrence is correct as "+schedule.tranOccur);
		}
		if (schedule.acctCode != null && !schedule.acctCode.equals("")) {
			if (!schedule.acctCode.equalsIgnoreCase(this.getAccountCode())) {
				throw new ItemNotFoundException("Selected Account Code "
						+ getAccountCode() + " not same with given value "
						+ schedule.acctCode);
			}
			logger.info("Selected Account Code is correct as "+schedule.acctCode);
		}
		if (schedule.unitOption != null && !schedule.unitOption.equals("")) {
			if (!schedule.unitOption.equalsIgnoreCase(this.getRAFeeUnit())) {
				throw new ItemNotFoundException("Selected RA Fee Unit "
						+ getRAFeeUnit() + " not same with given value "
						+ schedule.unitOption);
			}
			logger.info("Selected RA Fee Unit is correct as "+schedule.unitOption);
		}
		
		if (schedule.productCategory.matches("Service")){
			if (schedule.baseRate != null && !schedule.baseRate.equals("")) {
				if (!schedule.baseRate.equalsIgnoreCase(this.getFlatAmount())) {
					throw new ItemNotFoundException("Flat Amount " + getFlatAmount()
							+ " not same with given value " + schedule.baseRate);
				}
				logger.info("Flat Amount is correct as "+schedule.baseRate);
			}
			if (schedule.otherRate != null && !schedule.otherRate.equals("")) {
				if (!schedule.otherRate.equalsIgnoreCase(this.getPercentAmount())) {
					throw new ItemNotFoundException("Percent Amount " + getPercentAmount()
							+ " not same with given value " + schedule.otherRate);
				}
				logger.info("Percent Amount is correct as "+schedule.otherRate);
			}
		}else if(schedule.productCategory.matches("Site|Activity|POS")) {
			if(!StringUtil.isEmpty(schedule.applyRate)) {
				if(!MiscFunctions.compareResult("Apply Rate", schedule.applyRate, this.getApplyRate())) throw new ErrorOnPageException("Apply Rate is not correct.");
			}
			if(!StringUtil.isEmpty(schedule.rateAppliesTo)) {
				if(!MiscFunctions.compareResult("Rate Applies To", schedule.rateAppliesTo, this.getRateAppliesTo()));
			}
			if(!StringUtil.isEmpty(schedule.baseRate)) {
				if(!MiscFunctions.compareResult("Base Rate", Double.parseDouble(schedule.baseRate), Double.parseDouble(this.getBaseRate()))) throw new ErrorOnPageException("Base Rate is not correct.");
			}
			if(!StringUtil.isEmpty(schedule.changedUnitsRate)) {
				if(!MiscFunctions.compareResult("Rate (Changed Units)", Double.parseDouble(schedule.changedUnitsRate), Double.parseDouble(this.getRateChangedUnits()))) throw new ErrorOnPageException("Rate (Changed Units) is not correct.");
			}
			if(!StringUtil.isEmpty(schedule.flatAmountRate)) {
				if(!MiscFunctions.compareResult("Rate (Flat Amount)", Double.parseDouble(schedule.flatAmountRate), Double.parseDouble(this.getRateFlatAmount()))) throw new ErrorOnPageException("Rate (Flat Amount) is not correct.");
			}
			
			if(!StringUtil.isEmpty(schedule.otherRate)) {
				String otherRates[] = schedule.otherRate.split("[ ,;]");
				for(int i = 0; i < otherRates.length; i ++) {
					if(!MiscFunctions.compareResult("Base Rate of Other Rates", Double.parseDouble(otherRates[i]), Double.parseDouble(this.getBaseRateOfOtherRate(i + 1)))) throw new ErrorOnPageException((i + 1) + " Base Rate of Other Rates is not correct."); 
				}
			}
			if(!StringUtil.isEmpty(schedule.changedUnitsOtherRate)) {
				String changedUnitsOtherRates[] = schedule.changedUnitsOtherRate.split("[ ,;]");
				for(int i = 0; i < changedUnitsOtherRates.length; i ++) {
					if(!MiscFunctions.compareResult("Changed Units Rate of Other Rates", Double.parseDouble(changedUnitsOtherRates[i]), Double.parseDouble(this.getChangedUnitsRateOfOtherRate(i + 1)))) throw new ErrorOnPageException((i + 1) + " Changed Units Rate of Other Rates is not correct.");
				}
			}
			
			if(!StringUtil.isEmpty(schedule.flatAmountOtherRate)) {
				String flatAmountOtherRates[] = schedule.flatAmountOtherRate.split("[ ,;]");
				for(int i = 0; i < flatAmountOtherRates.length; i ++) {
					if(!MiscFunctions.compareResult("Flat Amount Rate of Other Rates", Double.parseDouble(flatAmountOtherRates[i]), Double.parseDouble(this.getFlatAmountRateOfOtherRate(i + 1)))) throw new ErrorOnPageException((i + 1) + " Flat Amount Rate of Other Rates is not correct.");
				}
			}
			
			if(!StringUtil.isEmpty(schedule.maximumFeeRestriction)) {
				if(!MiscFunctions.compareResult("Maximum Fee Restriction", schedule.maximumFeeRestriction, this.getMaximumFeeRestriction())) throw new ErrorOnPageException("Maximum Fee Restriction is not correct.");
			}
			if(!StringUtil.isEmpty(schedule.maximumFeeRate)) {
				if(!MiscFunctions.compareResult("Maximum Fee Amount", Double.parseDouble(schedule.maximumFeeRate), Double.parseDouble(this.getMaximumFeeAmount()))) throw new ErrorOnPageException("Maximum Fee Amount is not correct.");
			}
		} else {
			if (schedule.baseRate != null && !schedule.baseRate.equals("")) {
				if (!schedule.baseRate.equalsIgnoreCase(this.getBaseRate())) {
					throw new ItemNotFoundException("Base Rate " + getBaseRate()
							+ " not same with given value " + schedule.baseRate);
				}
				logger.info("Base Rate is correct as "+schedule.baseRate);
			}
			if (schedule.otherRate != null && !schedule.otherRate.equals("")) {
				if (!schedule.otherRate.equalsIgnoreCase(this.getGroupRate())) {
					throw new ItemNotFoundException("Group Rate " + getGroupRate()
							+ " not same with given value " + schedule.otherRate);
				}
				logger.info("Group Rate  Rate is correct as "+schedule.otherRate);
			}
		}
		
		if(schedule.productCategory.matches("Ticket")){
			if(schedule.personTypes !=null && schedule.personTypes.length >0){
				List<String> actTicketTypes = new ArrayList<String>();
				if(!schedule.unitOption.equals("Flat by Range of Ticket Quantity")){
					actTicketTypes = this.getTicketTypes();
				}else{
					actTicketTypes = this.getTicketTypeOfFlatBy();
				}
				if(schedule.personTypes.length != actTicketTypes.size()){
					throw new ErrorOnPageException("The ticket type list info is not correct.");
				}else{
					for(int i=0; i<schedule.personTypes.length; i++){
						if(!schedule.personTypes[i].equals(actTicketTypes.get(i))){
							throw new ErrorOnPageException("Expect ticket type should be " + schedule.personTypes[i] 
							       +", but actullay is " + actTicketTypes.get(i));
						}
					}
				}
			}
			
			if(schedule.baseRates !=null && schedule.baseRates.length >0){
				List<String> actBaseRates = new ArrayList<String>();
				List<String> actBaseRateRanges = new ArrayList<String>(); 
				if(!schedule.unitOption.equals("Flat by Range of Ticket Quantity")){
					actBaseRates = this.getBaseRates();					
				}else {
					actBaseRates = this.getBaseRatesOfFlatBy();	
					actBaseRateRanges = this.getRateRanges();
				}
				
				if(schedule.baseRates.length != actBaseRates.size()){
					throw new ErrorOnPageException("Base rate info is not correct.");
				}else{
					for(int i=0; i<schedule.baseRates.length; i++){
						if(!schedule.baseRates[i].equals(actBaseRates.get(i))){
							throw new ErrorOnPageException("Expect base rate info shoulde be " + schedule.baseRates[i] 
							            + ", but actullay is " + actBaseRates.get(i));
						}
					}
				}
				
				if(schedule.rateRanges != null && schedule.rateRanges.length >0){
					if(schedule.rateRanges.length != actBaseRateRanges.size()){
						throw new ErrorOnPageException("Base rate range info is not correct.");
					}else{
						for(int i=0; i<schedule.rateRanges.length; i++){
							if(!schedule.rateRanges[i].equals(actBaseRateRanges.get(i))){
								throw new ErrorOnPageException("Expect range should be " + schedule.rateRanges[i] 
								          + ", but actually is " + actBaseRateRanges.get(i));	
							}
						}
					}
				}
			}
			
			if(schedule.thresholdRates != null && schedule.thresholdRates.size()>0){
				List<List<String>> actThresholdRates = this.getThresholdRate();
				if(schedule.thresholdRates.size() != actThresholdRates.size()){
					throw new ErrorOnPageException("Threshold rate info is not correct.");	
				}else{
					for(int i=0; i<schedule.thresholdRates.size(); i++){
						if(schedule.thresholdRates.get(i).length != actThresholdRates.get(i).size()){
							throw new ErrorOnPageException("Threshold rate info is not correct.");
						}else{
							for(int j=0; j<schedule.thresholdRates.get(i).length; j++){
								if(!schedule.thresholdRates.get(i)[j].equals(actThresholdRates.get(i).get(j))){
									throw new ErrorOnPageException("Expect threshold rate should be " + schedule.thresholdRates.get(i)[j]
									        + ", but actually is " + actThresholdRates.get(i).get(j));
								}
							}
						}
					}
				}
				
			}
			
		}//end if(schedule.productCategory.matches("Ticket"))

		if(schedule.productCategory.matches("Slip")) {
			if(schedule.slipThresholdRates.size()>0)
				verifySlipThresholdRate(schedule.slipThresholdRates);
			if(!StringUtil.isEmpty(schedule.marinaRateType)){
				String rateType=getMarinaRateTypeValue();
				if (!schedule.marinaRateType.equalsIgnoreCase(rateType)) {
					throw new ItemNotFoundException("Marina Rate Type should be " + schedule.marinaRateType
							+ ", but actually is " + rateType);
				}
				logger.info("Percent Amount is correct as "+schedule.otherRate);
			}
		}
		
		if(schedule.deliverymethod !=null && schedule.deliverymethod.length()>0){
			String value = this.getDeliveryMethodValue();
			if(!schedule.deliverymethod.equals(value)){
				throw new ErrorOnPageException("Expect delivery method should be " + schedule.deliverymethod  +
						", but actually is " + value);
			}
		}
		
		if(StringUtil.notEmpty(schedule.purchaseType)){
			String value = this.getPurchaseType();
			if(!schedule.purchaseType.equals(value)){
				throw new ErrorOnPageException("Expect purchase type should be " + schedule.purchaseType  +
						", but actually is " + value);
			}
		}
			
	}
	
	private void verifySlipThresholdRate(List<String> slipThresholdRates) {
		List<String> rates = getSlipThresholdRates();
		int len=slipThresholdRates.size();
		int len_1 = rates.size();
		if(len!=len_1)
			throw new ErrorOnPageException("Check number of threshold rates failed!!!");
		
		boolean found = false;
		boolean result = true;
		for(int i=0; i<len; i++) {
			found = false;
			for(int j=0; j<len_1;j++) {
				if(StringUtil.compareNumStrings(slipThresholdRates.get(i),rates.get(j))==0) {
					found = true;
					break;
				}
			}
			result &=found;
			if(!result)
				throw new ErrorOnPageException("Cannot find threshold rate-->"+slipThresholdRates.get(i));
		}
		
		logger.info("Verify Slip Threshold rate successfully.");
		
	}
	
	private List<String> getTicketTypes(){
		List<String> ticketTypes = new ArrayList<String>();
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("base_rate_ticket_type.*",false));
		for(int i=0; i<objs.length; i++){
			String ticketType = ((ISelect)objs[i]).getSelectedText();
			ticketTypes.add(ticketType);
		}
		
		Browser.unregister(objs);
		
		return ticketTypes;
	}
	
	private List<String> getTicketTypeOfFlatBy(){
		List<String> ticketTypes = new ArrayList<String>();
		IHtmlObject[] objs = browser.getDropdownList(".id", "3_2_ticket_type");
		for(int i=0; i<objs.length; i++){
			String ticketType = ((ISelect)objs[i]).getSelectedText();
			ticketTypes.add(ticketType);
		}
		
		Browser.unregister(objs);
		
		return ticketTypes;
	}
	
	private List<String> getBaseRatesOfFlatBy(){
		List<String> baseRates = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTextField(".id", "3_2_base_increment_rate");
		for(int i=1; i<objs.length; i++){
			String rate = ((IText)objs[i]).getText();
			baseRates.add(rate);
		}
		
		Browser.unregister(objs);
		return baseRates;
	}
	
	public List<String> getSlipThresholdRates()
	{
		IHtmlObject[] objs = browser.getTextField(".id", "otherrates");
		List<String> rates = new ArrayList<String>();
		int len = objs.length-1;
		if(len>0)
		{
			for(int i=1;i<objs.length;i++)
			{
				IText t = (IText)objs[i];
				rates.add(t.getText());
			}
		}
		Browser.unregister(objs);
		return rates;
	}
	
	private List<String> getRateRanges(){
		List<String> baseRateRanges = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTextField(".id", "3_2_increment");
		for(int i=1; i<objs.length; i++){
			String range = ((IText)objs[i]).getText();
			baseRateRanges.add(range);
		}
		
		Browser.unregister(objs);
		return baseRateRanges;
	}
	
	private List<String> getBaseRates(){
		List<String> baseRates = new ArrayList<String>();
		IHtmlObject[] objsFrom = browser.getTextField(".id", new RegularExpression("base_from_rate_.*",false));
		IHtmlObject[] objsTo = browser.getTextField(".id", new RegularExpression("base_to_rate_.*",false));
		for(int i=0; i<objsFrom.length;i++){
			String baseFromRate = ((IText)objsFrom[i]).getText();
			String baseToRate = "";
			if(objsFrom.length == objsTo.length){
				baseToRate = "," + ((IText)objsTo[i]).getText();
			}
			
			String text = baseFromRate+ baseToRate;
			baseRates.add(text);
		}
		
		Browser.unregister(objsFrom);
		Browser.unregister(objsTo);
		return baseRates;
	}
	
	private List<List<String>> getThresholdRate(){
		List<List<String>> threRatesList = new ArrayList<List<String>>();
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("base_rate_ticket_type_.*",false));
		for(int i=0; i<objs.length; i++){
			List<String> threRate = new ArrayList<String>();
			String markValue = objs[i].id().replaceAll("base_rate_ticket_type_", "");
			String fromThreId = "otherrates_from_" + markValue;
			String toThreId = "otherrates_to_" + markValue;
			IHtmlObject[] objsFrom = browser.getTextField(".id",fromThreId);
			IHtmlObject[] objsTo = browser.getTextField(".id", toThreId);
			for(int j=1; j<objsFrom.length; j++){
				String threFromRate = ((IText)objsFrom[j]).getText();
				String threToRate = "";
				if(objsFrom.length == objsTo.length){
					threToRate = "," + ((IText)objsTo[j]).getText();
				}
				
				String text = threFromRate+ threToRate;
				threRate.add(text);
			}
			
			threRatesList.add(threRate)	;
			Browser.unregister(objsFrom);
			Browser.unregister(objsTo);
		}
		
		Browser.unregister(objs);
		return threRatesList;
	}

	/**
	 * 
	 * @return new added fee schedule Id
	 */
	public String getFeeSchID() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text",new RegularExpression("^RA Fee Schedule ID",false));
		String text = objs[objs.length-1].text();
		
		String[] ids = RegularExpression.getMatches(text, "\\d+");
		if(ids==null||ids.length<1){
			throw new ErrorOnPageException("Not Found Fee Schedule ID.");
		}
		
		Browser.unregister(objs);

		return ids[0];
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
	
	public String getTransMethod() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "feeunitoption");
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
	
	public boolean checkAddTicketTypeIsExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add Ticket Type");
	}
	
	public String getDeliveryMethodValue(){
		return browser.getDropdownListValue(".id", "delivery_method");
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
	
	private boolean checkPerTypeIsEnable(String value){
		IHtmlObject[] objs = browser.getRadioButton(".id", "feeunitoption", ".value", value);

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
	
	public void verifyFeePerTypeWhetherEnableByTransactionType(boolean isPerTranEnable,
			boolean isFlatByEnable, boolean isPerUnitEnabl){
		boolean result = true;
		
		boolean isPerTranEnableAct = this.checkPerTransactionIsEnable();	
		boolean isFlatByEnableAct = this.checkFlatbyRangeIsEnable();
		boolean isPerUnitEnableAct = this.checkPerUnitIsEnable();
		
		result &= MiscFunctions.compareResult("Per Transaction Enable info", isPerTranEnable, isPerTranEnableAct);
		result &= MiscFunctions.compareResult("Flat by Range of Ticket Quantity", isFlatByEnable, isFlatByEnableAct);
		result &= MiscFunctions.compareResult("Per Unit", isPerUnitEnabl, isPerUnitEnableAct);
		if(!result){
			throw new ErrorOnPageException("Fee Per Type is not correct by Transaction type, please check log.");
		}else{
			logger.info("Fee Per Type is  correct by Transaction type. ");
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
	
	public List<String> getTicketTypeListElementsForFlatBy(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("(3_2_ticket_typedisabled)|(3_2_ticket_type)",false));
		List<String> elements = ((ISelect) objs[objs.length-1]).getAllOptions();
		Browser.unregister(objs);
		return elements;
	}
	
	public List<String> getTicketTypeListElements(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("base_rate_ticket_type.*",false));
		List<String> elements = ((ISelect) objs[objs.length-1]).getAllOptions();
		Browser.unregister(objs);
		return elements;
	}
	
	public void clickAddRange(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Range");
	}
	
	public void clickRemoveRange(int index){
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Range",index+1);
	}
	
	public boolean checkRangeIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", "3_2_increment");
		if(objs.length<index+1){
			throw new ItemNotFoundException("Did not found range object with index = " + index);
		}
		boolean isEnable = objs[index+1].isEnabled();
		Browser.unregister(objs);
		return isEnable;
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
	
	public boolean checkRateIsEnable(){
		return this.checkRateIsEnable(0);
	}
	
	public boolean checkRateIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("base_from_rate.*",false));
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
	
	public boolean checkToRateIsEnable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("base_to_rate.*",false));
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
		return browser.checkHtmlObjectExists(".id", new RegularExpression("base_to_rate.*",false),".class","Html.INPUT.text");
	}
	
	public boolean checkToRateIsExisting(int index){
		boolean isExisting = true;
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("base_to_rate.*",false));
		if(objs.length<=index + 1){
			isExisting = false;
		}
		
		Browser.unregister(objs);
		return isExisting;
	}
	
	public boolean checkRateIsExisting(int index){
		boolean isExisting = true;
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("base_from_rate.*",false));
		if(objs.length<=index + 1){
			isExisting = false;
		}
		
		Browser.unregister(objs);
		return isExisting;
	}
	
	public String getRange(int index){
		return browser.getTextFieldValue(".id", "3_2_increment", index+1);
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
	
	public boolean checkAddThresholdRateIsExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add Threshold Rate");
	}
	
	public boolean checkThresholdRateTextIsInable(int index){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("otherrates_from.*",false));
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
	
	public boolean checkThresholdRateTextIsExisting(int index){
		boolean isExisting = true;
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("otherrates_from.*",false));
		
		if(objs.length<=index+1){
			isExisting = false;
		}
		
		Browser.unregister(objs);
		return isExisting;
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
	/**
	 * check span elemant edit.
	 * @param value
	 * @return
	 */
	public boolean checkSpanElementEdit(String value){
		boolean isEidt = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", value);
		if(objs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		if(objs[0].getAttributeValue(".isTextEdit").equals("true")){
			isEidt = true;
		}
		isEidt = false;
		Browser.unregister(objs);
		return isEidt;
	}
	/**
	 * check ra fee sch id edit or not.
	 * @param id
	 * @return
	 */
	public boolean checkRaFeeScheIdEidt(String id){
		return this.checkSpanElementEdit(id);
	}
	
	/**
	 * check RA fee schedule location edit or not.
	 * @param id
	 * @return
	 */
	public boolean checkRaFeeScheLocation(String location){
		return this.checkSpanElementEdit(location);
	}
	/**
	 * get product category option.
	 * @return
	 */
	public List<String> getProductCategoryOption(){
		return browser.getDropdownElements(".id", "prd_grp_cat_id");
	}
	
	public void verifyProductCategoryNotEdit(String category){
		List<String> option = this.getProductCategoryOption();
		if(option.size()>1){
			throw new ErrorOnPageException("Prodcut category length just only one");
		}
		if(!option.get(0).equals(category)){
			throw new ErrorOnPageException("Product category",category,option.get(0)); 
		}
	}
	
	public void verifyRaFeeScheduleNotEdit(String id){
		if(this.checkRaFeeScheIdEidt(id)){
			throw new ErrorOnPageException("The schedule id should not edit");
		}
	}
	
	public void verifyRaFeeLocationNotEdit(String location){
		if(this.checkRaFeeScheLocation(location)){
			throw new ErrorOnPageException("The schedule locaion should not edit");
		}
	}
	/**
	 * verify apply rate  info.
	 */
	public void verifyApplyRateDisable(){
		boolean isEnable = true;
	   if(this.checkPerTransactionIsEnable()){
		   isEnable &= false;
		   logger.info("per transaction should be disable");
	   }
	   if(this.checkPerUnitIsEnable()){
		   isEnable &= false;
		   logger.info("per unit should be disable");
	   }
	   if(!isEnable){
		   throw new ErrorOnPageException("The applay rate should not enable");
	   }
	}
	
	public List<String> getApplicableProductCategoryOptions() {
		return browser.getDropdownElements(".id", "assignment_app_prdcat");
	}
	
	public String getApplicableProductCategorySelectedValue() {
		return browser.getDropdownListValue(".id", "assignment_app_prdcat");
	}
	
	public List<String> getApplicableProduct(){
		return browser.getDropdownElements(".id", "assignment_app_prdcat");
	}
	
	/**
	 * verify assign applicable product category error.
	 * @param list
	 */
	public void verifyAssignAppPrdCategory(List<String> list){
		boolean isContains = true;
		List<String> actualList = this.getApplicableProduct();
		if(list.size()!=actualList.size()){
			throw new ErrorOnPageException("Applicable product category",list.size(),actualList.size());
		}
		for(int i =0;i<list.size();i++){
			if(!actualList.contains(list.get(i))){
				isContains &= false;
				logger.error(list.get(i) +"shold contain in applicable product category option list");
			}
		}
		if(!isContains){
			throw new ErrorOnPageException("Applicable product category error");
		}
	}
	/**
	 * get sales channel.
	 * @return
	 */
	public List<String> getSalesChanel(){
		return browser.getDropdownElements(".id", "conditions_channel");
	}
	/**
	 * Verify sales channel list info
	 * @param list
	 */
	public void verifySalesChannelList(List<String> list){
		boolean isContains = true;
		List<String> actualList = this.getSalesChanel();
		if(list.size() != actualList.size()){
			throw new ErrorOnPageException("Sales channel option",list.size(),actualList.size());
		}
		for(int i =0;i<list.size();i++){
			if(!actualList.contains(list.get(i))){
				isContains &= false;
				logger.error(list.get(i) +"shold contain in sale channel option list");
			}
		}
		if(!isContains){
			throw new ErrorOnPageException("Applicable sale channel error");
		}
	}
	/**
	 * get error message.
	 * @return
	 */
	public String getErrorMsg(){
//		 HtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".classname", "message msgerror");
		 IHtmlObject[] objs = browser.getHtmlObject(".classname", new RegularExpression("(message msgsuccess|message msgerror)",false));
		 if(objs.length<1){
			 throw new ErrorOnPageException("No error message was found");
		 }
		 String errorMsg = objs[0].getProperty(".text");
		 Browser.unregister(objs);
		 return errorMsg;
	}
	
	public String getError(){
		 IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".classname", "message msgerror");
		 if(objs.length<1){
			 throw new ErrorOnPageException("No error message was found");
		 }
		 String errorMsg = objs[0].getProperty(".text");
		 Browser.unregister(objs);
		 return errorMsg;
	}
	
	public boolean checkErrorMsgExist() {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("(message msgsuccess|message msgerror)",false));
	}
	/**
	 * check element disable.
	 * @param id
	 * @return
	 */
	private boolean checkElementDisable(String id){
		boolean isDisable = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id",id);
		if(objs.length<1){
			throw new ErrorOnPageException("No element was found");
		}
		String disable = objs[0].getProperty(".isDisabled");
		if(disable.equals("true")){
			isDisable = true;
		}
		Browser.unregister(objs);
		return isDisable;
	}
	
	public boolean checkProductCategoryDisable(){
		return this.checkElementDisable("prd_grp_cat_id");
	}
	
	public boolean checkAppPrdCtgryDisable(){
		return this.checkElementDisable("assignment_app_prdcat");
	}
	
	public boolean checkProductDisable(){
		return this.checkElementDisable("assignment_product");
	}
	
	public boolean checkEffectiveDateDisable(){
		return this.checkElementDisable("date_effective_ForDisplay");
	}
	public boolean checkSalesChannelDisable(){
		return this.checkElementDisable("conditions_channel");
	}
	
	public boolean checkTransactionTypeDisable(){
		return this.checkElementDisable("transaction_type");
	}
	
	public boolean checkAccountDisable(){
		return this.checkElementDisable("account_code");
	}
	
	public boolean checkRateDisable(){
		return this.checkElementDisable("base_rate");
	}
	
	public boolean checkApplyRateDisable() {
		boolean isDisable = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "feeunitoption");
		if(objs.length < 1){
			throw new ItemNotFoundException("No element was found.");
		}
		for(int i = 0; i < objs.length; i ++) {
			if(objs[i].isEnabled()) {
				isDisable &= false;
			}
		}
		
		Browser.unregister(objs);
		return isDisable;
	}
	
	public String getSelectedApplyRate() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "feeunitoption");

		String value = "";
		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
			}
		}

		Browser.unregister(objs);
		if (value.equals("2")) {
			value = "Per Transaction";
		} else if (value.equals("1")) {
			value = "Per Unit";
		}
		
		return value;
	}
	
	public boolean checkRaFeeSchdlDetailInfoReadOnly(){
		boolean isReadOnly = true;
		if(!this.checkProductCategoryDisable()){
			isReadOnly &= false;
			logger.info("Product category should disable");
		}
		if(!this.checkAppPrdCtgryDisable()){
			isReadOnly &= false;
			logger.info("Applicable product category should disable");
		}
		if(!this.checkProductDisable()){
			isReadOnly &= false;
			logger.info("Product should disable");
		}
		if(!this.checkEffectiveDateDisable()){
			isReadOnly &= false;
			logger.info("Effective date disable");
		}
		if(!this.checkSalesChannelDisable()){
			isReadOnly &= false;
			logger.info("Salues channel should disable");
		}
		if(!this.checkTransactionTypeDisable()){
			isReadOnly &= false;
			logger.info("Transaction type should disable");
		}
		if(!this.checkAccountDisable()){
			isReadOnly &= false;
			logger.info("Code should disable");
		}
		if(!this.checkRateDisable()){
			isReadOnly &= false;
			logger.info("Rate should disable");
		}
		return isReadOnly;
	}
	
	/**
	 * Get marina rate type that has been selected
	 * @return
	 */
	public String getMarinaRateTypeValue() {
		return browser.getDropdownListValue(".id", new RegularExpression("SlipRAFeeScheduleView-\\d+\\.marinaRateType",false));
	}
	
	/**
	 * This method get all the value in the drop down list of marina rate type
	 * @return
	 */
	public List<String> getMarinaRateTypeFromDropDownlist() {
		return browser.getDropdownElements(".id", new RegularExpression("SlipRAFeeScheduleView-\\d+\\.marinaRateType",false));
	}
	
	public void clickDatesToMoveFocusFromCalendar(){
		browser.clickGuiObject(".class", "Html.TD",".text","Dates");
	}
	
	public void selectMarinaRateTypeByDropDownList(String marianRateType){
		browser.selectDropdownList(".id", new RegularExpression("SlipRAFeeScheduleView-\\d+\\.marinaRateType",false), marianRateType);
	}
	
	public List<String> getTransactionTypeOptions() {
		return browser.getDropdownElements(".id", "transaction_type");
	}
	
	public List<String> getTransactionOccurOptions() {
		return browser.getDropdownElements(".id", "transaction_occurance");
	}
	
	public void setNumOfFreeChangesPerTrans(String num){
		browser.setTextField(".id", "numberOfFreeChangeTransactions", num);
	}
	
	public boolean isMaximumFeeRestrictionEditable() {
		return this.isRadioButtonsEditable("maximumFeeRestriction");
	}
	
	public void selectMaximumFeeRestriction(String option) {
		int index = option.equalsIgnoreCase("None") ? 0 : 1;
		browser.selectRadioButton(".id", "maximumFeeRestriction", index);
	}
	
	public String getMaximumFeeRestriction() {
		IHtmlObject objs[] = browser.getRadioButton(".id", "maximumFeeRestriction");
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find 'Maximum Fee Restriction' radio button objects.");
		
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index == -1 ? null : (index == 0 ? "None" : "Flat");
	}
	
	public boolean isMaximumFeeAmountEditable() {
		return browser.checkHtmlObjectEnabled(".id", "maximumFeeAmount");
	}
	
	public boolean isMaximumFeeAmountExists() {
		return browser.checkHtmlObjectExists(".id", "maximumFeeAmount");
	}
	
	public String getMaximumFeeAmount() {
		return browser.getTextFieldValue(".id", "maximumFeeAmount");
	}
	
	public void setMaximumFeeAmount(String amount) {
		browser.setTextField(".id", "maximumFeeAmount", amount);
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
	
	public List<String> getFeeUnitElements(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".for", "feeunitoption");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Fee Unit Label object.");
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
		List<String> actualList = this.getFeeUnitElements();
		actualList.remove("Percentage");
		
		Collections.sort(expectList);
		Collections.sort(actualList);
		
		if(!expectList.equals(actualList)){
			throw new ErrorOnPageException("Apply Fee section is not correct!");
		}
	}
	
	public boolean checkFeeUnitElementsEnable(String feeType) {
		String value = "";
		if(feeType.equals("Per Transaction")){
			value = "2";
		} else if(feeType.equals("Per Unit")){
			value = "1";
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".id", "feeunitoption");
		p[1] = new Property(".value", value);
		return browser.checkHtmlObjectEnabled(p);
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
		return browser.checkHtmlObjectEnabled(p);
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
}
