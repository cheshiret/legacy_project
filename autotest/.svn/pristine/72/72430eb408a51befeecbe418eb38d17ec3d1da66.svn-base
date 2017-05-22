/*
 * Created on Jan 3, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.discount;

import java.util.Iterator;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDiscountDetailsPage extends FinanceManagerPage{
  	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDiscountDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDiscountDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDiscountDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrDiscountDetailsPage();
		}

		return _instance;
	}


	/** Determine if the Discount Details page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "discountdetailview");
	}
	
	/**
	 * This method used to input discount name
	 * @param disName
	 */
	public void setDiscountName(String disName) {
	  	browser.setTextField(".id", "name", disName);
	}
	
	/**
	 * 
	 * @return discount name
	 */
	public String getDiscountName() {
	  	return browser.getTextFieldValue(".id", "name");
	}
	
	/**
	 * 
	 * @return discount name after clicking apply button.
	 */
	public String getDiscountNameAfterApply() {
		RegularExpression rex = new RegularExpression("Discount Name.*Discount Name.*", false);
	  	IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
	  	String text = objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	String taxName = text.substring("Discount Name   Discount Name".length(), text.indexOf("Discount Description")).trim();
		return taxName;
	}
	
	/**
	 * Input discount description
	 * @param desc
	 */
	public void setDiscountDesc(String desc) {
	  	browser.setTextArea(".id", "description", desc);
	}
	
	/**Set promo code**/
	public void setPromoCode(String code) {
		IHtmlObject[] objs = browser.getTextField(".id", "promoCode");
		((IText)objs[objs.length-1]).setText(code);
		Browser.unregister(objs);		
	}
	/**Set promo description**/
	public void setPromoDescription(String description) {
		IHtmlObject[] objs = browser.getTextField(".id", "promoDescription");
		((IText)objs[objs.length-1]).setText(description);
		Browser.unregister(objs);
	}
	
	public int getPromoNum(){
		IHtmlObject[] objs = browser.getTextField(".id", "promoCode");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	/**
	 * 
	 * @return discount description
	 */
	public String getDiscountDesc() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TEXTAREA", ".id", "description");
	  	String value = objs[0].getProperty(".value").toString();
	  	Browser.unregister(objs);
	  	return value;
	}
	
	/**
	 * This method used to select rate type from drop down list
	 * @param rateType
	 */
	public void selectRateType(String rateType) {
	  	browser.selectDropdownList(".id", "rateType", rateType);
	}
	
	/**
	 * 
	 * @return selected rate type
	 */
	public String getRateType() {
	  	return browser.getDropdownListValue(".id", "rateType", 0);
	}
	
	/**
	 * Deselect Attribute Fee
	 *
	 */
	public void deselectAttrFee() {
//		IHtmlObject[] objs = browser.getHtmlObject(".id", "fee_type_12");
//		String checked = objs[0].getProperty(".checked").toString();
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("true")) {
//		  	browser.selectCheckBox(".id", "fee_type_12");
//		}
		deselectFeeTypeByID("fee_type_12");
	}

	private void selectFeeTypeByID(String id) {
	  	IHtmlObject[] objs = browser.getCheckBox(".id", id);
//		Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
		if(!((ICheckBox)objs[0]).isSelected()) {//Quentin[20131009] getProperty(".checked") cannot work
//		  	browser.selectCheckBox(".id", id);
			((ICheckBox)objs[0]).select();
		}
		Browser.unregister(objs);
	}
	
	private void deselectFeeTypeByID(String id) {
	  	IHtmlObject[] objs = browser.getCheckBox(".id", id);
		if(((ICheckBox)objs[0]).isSelected()) {//Quentin[20131009] getProperty(".checked") cannot work
		  	browser.unSelectCheckBox(".id", id);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Select Attribute Fee
	 *
	 */
	public void selectAttrFee() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "fee_type_12");
////		Object checked = objs[0].getProperty(".checked");
////		Browser.unregister(objs);
////		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		if(!((ICheckBox)objs[0]).isSelected()) {//Quentin[20131009] getProperty(".checked") cannot work
//		  	browser.selectCheckBox(".id", "fee_type_12");
//		}
//		Browser.unregister(objs);
		selectFeeTypeByID("fee_type_12");
	}
	
	/**
	 * Deselect Pos Fee
	 *
	 */
	public void deselectPosFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "fee_type_13");
//		String checked = objs[0].getProperty(".checked").toString();
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("true")) {
//		  	browser.selectCheckBox(".id", "fee_type_13");
//		}
		deselectFeeTypeByID("fee_type_13");
	}

	/**
	 * Select Pos Fee
	 *
	 */
	public void selectPosFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "fee_type_13");
//		Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		  	browser.selectCheckBox(".id", "fee_type_13");
//		}
		selectFeeTypeByID("fee_type_13");
	}

	/**
	 * Deselect Transaction Fee
	 *
	 */
	public void deselectTransactionFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "fee_type_4");
//		String checked = objs[0].getProperty(".checked").toString();
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("true")) {
//		  	browser.selectCheckBox(".id", "fee_type_4");
//		}
		deselectFeeTypeByID("fee_type_4");
	}

	/**
	 * Select Transaction Fee
	 *
	 */
	public void selectTransactionFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "fee_type_4");
//	  	Object checked = objs[0].getProperty(".checked");		
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		  	browser.selectCheckBox(".id", "fee_type_4");
//		}
		selectFeeTypeByID("fee_type_4");
	}

	/**
	 * Deselect Use Fee
	 *
	 */
	public void deselectUseFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "fee_type_2");
//		String checked = objs[0].getProperty(".checked").toString();
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("true")) {
//		  	browser.selectCheckBox(".id", "fee_type_2");
//		}
		deselectFeeTypeByID("fee_type_2");
	}

	/**
	 * Select Use Fee
	 *
	 */
	public void selectUseFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id","fee_type_2");
//	  	Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		  	browser.selectCheckBox(".id", "fee_type_2");
//		}
		selectFeeTypeByID("fee_type_2");
	}
	
	/**
	 * Select specific fee type
	 * @param feeType
	 */
	public void selectFeeType(String feeType) {
	  	if(feeType.equalsIgnoreCase("Attribute Fee")){
	  	  	selectAttrFee();
	  	}else if(feeType.equalsIgnoreCase("POS Fee")){
	  	  	selectPosFee();
	  	}else if(feeType.equalsIgnoreCase("Transaction Fee")){
	  	  	selectTransactionFee();
	  	}else if(feeType.equalsIgnoreCase("Use Fee")){
	  	  	selectUseFee();
	  	}else{
	  	  throw new ItemNotFoundException("Unknow Fee Type.");
	  	}
	}
	
	/**
	 * This method used to check given fee type is checked
	 * @param feeType
	 */
	public void checkFeeTypeChecked(String feeType) {
	  	IHtmlObject[] objs = null;
	  	if(feeType.equalsIgnoreCase("Attribute Fee")){
	  	  	objs = browser.getCheckBox(".id", "fee_type_12");
	  	}else if(feeType.equalsIgnoreCase("POS Fee")){
	  	  	objs = browser.getCheckBox(".id", "fee_type_13");
	  	}else if(feeType.equalsIgnoreCase("Transaction Fee")){
	  	  	objs = browser.getCheckBox(".id", "fee_type_4");
	  	}else if(feeType.equalsIgnoreCase("Use Fee")){
	  	  	objs = browser.getCheckBox(".id", "fee_type_2");
	  	}else{
	  	  throw new ItemNotFoundException("Unknow Fee Type.");
	  	}
//	  	Object checked = objs[0].getProperty(".checked");
	  	boolean isSelected = ((ICheckBox)objs[0]).isSelected();
		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
		if(!isSelected)	throw new ItemNotFoundException(feeType + " is not checked."); //Quentin[20131009] getProperty(".checked") cannot work
	}
	
	/**
	 * 
	 * @return checked fee type number
	 */
	public int getCheckedFeeTypeNum() {
	  	IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("fee_type_\\d", false));
	  	int num = 0;
	  	for(IHtmlObject obj : objs){
	  		if(((ICheckBox)obj).isSelected()){
	  			num ++;
	  		}
	  	}
	  	Browser.unregister(objs);
	  	return num;
	}
	
	private void selectBehavior(String id) {
		IHtmlObject[] objs = browser.getCheckBox(".id", id);
		if(!((ICheckBox)objs[0]).isSelected()) {
//		  	browser.selectCheckBox(".id", id);
			((ICheckBox)objs[0]).select();
		}
		Browser.unregister(objs);
	}
	
	private void deselectBehavior(String id) {
		IHtmlObject[] objs = browser.getCheckBox(".id", id);
		Browser.unregister(objs);
		if(((ICheckBox)objs[0]).isSelected()) {
			((ICheckBox)objs[0]).deselect();
		}
	}
	
	/**
	 * select additional discount
	 *
	 */
	public void selectAddiDiscount() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "additional_ind");
//		Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		  	browser.selectCheckBox(".id", "additional_ind");
//		}
		selectBehavior("additional_ind");
	}
	
	/**
	 * Deselect Additional discount
	 *
	 */
	public void deselectAddiDiscount() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "additional_ind");
//		String checked = objs[0].getProperty(".checked").toString();
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("true")) {
//		  	browser.selectCheckBox(".id", "additional_ind");
//		}
		deselectBehavior("additional_ind");
	}
	
	/**
	 * select automatic discount
	 *
	 */
	public void selectAutoDiscount() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "automatic_ind");
//		Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false"))
//		{
//		  	browser.selectCheckBox(".id", "automatic_ind");
//		}
		selectBehavior("automatic_ind");
	}
	
	/**
	 * Deselet automatic discount
	 *
	 */
	public void deselectAutoDiscount() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "automatic_ind");
//		String checked = objs[0].getProperty(".checked").toString();
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("true")) {
//		  	browser.selectCheckBox(".id", "automatic_ind");
//		}
		deselectBehavior("automatic_ind");
	}
	
	/**
	 * select display discount
	 *
	 */
	public void selectDisplayDiscount() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "display_ind");
//		Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		  	browser.selectCheckBox(".id", "display_ind");
//		}
		selectBehavior("display_ind");
	}
	
	/**
	 * select given discount behavior
	 * @param disBehavior
	 */
	public void selectDiscountBehavior(String disBehavior) {
	  if(StringUtil.notEmpty(disBehavior)){
		  if(disBehavior.equalsIgnoreCase("Addtional Discount")){
	  	    selectAddiDiscount();
	  	  }else if(disBehavior.equalsIgnoreCase("Automatic Discount")){
	  	    selectAutoDiscount();
	  	  }else if(disBehavior.equalsIgnoreCase("Display Discount")){
	  	    selectDisplayDiscount();
	  	  }else if(disBehavior.equalsIgnoreCase("Buy X Get Y Discount")){
	  		selectBuyXGetYDiscount();
	  		ajax.waitLoading();
	  		this.waitLoading();
	  	  }else{
	  	    throw new ItemNotFoundException("Unknow Discount Behavior - " + disBehavior);
	  	  }
	  }
	}
	
	/**
	 * Checked given discount behavior is checked
	 * @param discountBehavior
	 */
	public void checkBehaviorChecked(String discountBehavior) {
	  	IHtmlObject[] objs = null;
	  	if(discountBehavior.equalsIgnoreCase("Addtional Discount")){
	  	  	objs = browser.getCheckBox(".id", "additional_ind");
	  	}else if(discountBehavior.equalsIgnoreCase("Automatic Discount")){
	  	  	objs = browser.getCheckBox(".id", "automatic_ind");
	  	}else if(discountBehavior.equalsIgnoreCase("Display Discount")){
	  	  	objs = browser.getCheckBox(".id", "display_ind");
	  	}else{
	  	  throw new ItemNotFoundException("Unknown Discount Behavior.");
	  	}
	  	Object checked = objs[0].getProperty(".checked");
		Browser.unregister(objs);
		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
		  	throw new ItemNotFoundException(discountBehavior + " is not checked.");
		}
	}
	
	/**
	 * 
	 * @return checked discount behavior num
	 */
	public int getCheckedBehaviorNum() {
	  	IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression(".+_ind", false));
	  	int num = 0;
	  	for(IHtmlObject obj : objs){
	  		if(((ICheckBox)obj).isSelected()){
	  			num ++;
	  		}
	  	}
	  	Browser.unregister(objs);
	  	return num;
	}
	
	/**
	 * Select radio button of rate unit
	 * @param rateUnit
	 */
	public void selectRateUnit(String rateUnit) {
	  	if(rateUnit.equalsIgnoreCase("Per Stay")){
	  	  	browser.selectRadioButton(".id", "unitTypePerStay", ".value", "true");
	  	}else if(rateUnit.equalsIgnoreCase("Per Unit of Stay")){
	  	  	browser.selectRadioButton(".id", "unitTypePerStay", ".value", "false");
	  	}
	}
	
	/**
	 * 
	 * @return checked rate unit
	 */
	public String getCheckedRateUnit() {
	  	IHtmlObject[] objs = browser.getRadioButton(".id", "unitTypePerStay");
	  	if(objs.length < 1) {
	  		throw new ItemNotFoundException("Cannot find Rate Unit object.");
	  	}
	  	String option = ((IRadioButton)objs[0]).isSelected() ? "Per Stay" : "Per Unit of Stay";
	  	
	  	Browser.unregister(objs);
	  	return option;
	}
	
	/**
	 * Input all discount details info
	 * @param disct- DiscountData
	 */
	public void enterAllDiscountDetails(DiscountData disct) {
	  	setDiscountName(disct.name);
	  	setDiscountDesc(disct.description);
	  	selectRateType(disct.rateType);
	  	ajax.waitLoading();
	  	this.waitLoading();
	  	for(Iterator<String> it = disct.feeTypes.iterator();it.hasNext();){
	  	  selectFeeType(it.next().toString());
	  	}
	  	for(Iterator<String> it = disct.behaviors.iterator();it.hasNext();){
	  	  selectDiscountBehavior(it.next().toString());
	  	}  
	  	selectRateUnit(disct.rateUnit);
	  	if(null!=disct.promoCode && disct.promoCode.length()>0){
	  		if(this.getPromoNum() >0 ){
	  			this.clickAddCode();
	  			ajax.waitLoading();
	  			this.waitLoading();
	  		}
	  		this.setPromoCode(disct.promoCode);
	  		if(null!=disct.promoDescription && disct.promoDescription.length()>0){
	  			this.setPromoDescription(disct.promoDescription);
	  		}
	  		if(StringUtil.notEmpty(disct.maxUsagePerCust))
	  			this.setMaxUsagePerCust(disct.maxUsagePerCust);
	  	}
	}
	
	/**
	 * Click Apply link
	 */
	public void clickApply() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**Click Add code button*/
	public void clickAddCode(){
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Add Code");
	}
	
	public void clickRemoveCode(){
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Remove Code");
	}
	
	/**
	 * Click OK link
	 */
	public void clickOK() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * This method used to verify discount details information
	 * @param discount
	 */
	public void verifyDiscountDetails(DiscountData discount) {
	  	logger.info("Start to verify discount details info.");
	  	
	  	if(discount.name != null && ! discount.name.equals("")){
	  	  if(! discount.name.equalsIgnoreCase(getDiscountName())){
	  	    throw new ItemNotFoundException("Discount name " + getDiscountName() + " not correct.");
	  	  }
	  	}
	  	if(discount.description != null && ! discount.description.equals("")){
	  	  if(!discount.description.equalsIgnoreCase(getDiscountDesc())){
	  	    throw new ItemNotFoundException("Discount description " + getDiscountDesc() + " not correct.");
	  	  }
	  	}
	  	if(!discount.rateType.equalsIgnoreCase(getRateType())){
	  	  throw new ItemNotFoundException("Discount Rate Type " + getRateType() + " not correct.");
	  	}
	  	for(Iterator<String> it = discount.feeTypes.iterator(); it.hasNext(); ){
	  	  checkFeeTypeChecked(it.next().toString());
	  	}
	  	if(discount.feeTypes.size() != getCheckedFeeTypeNum()){
	  	  throw new ItemNotFoundException("Checked Fee Type num " + getCheckedFeeTypeNum() + " not Correct!");
	  	}
	  	for(Iterator<String> it = discount.behaviors.iterator(); it.hasNext(); ){
	  	  checkBehaviorChecked(it.next().toString());
	  	}
	  	if(discount.behaviors.size() != getCheckedBehaviorNum()){
	  	  throw new ItemNotFoundException("Checked discount behavior num " + getCheckedBehaviorNum() + " not Correct!");
	  	}
	  	if( ! discount.rateUnit.equalsIgnoreCase(getCheckedRateUnit())){
	  	  throw new ItemNotFoundException("Checked rate unit " + getCheckedRateUnit() + " not correct.");
	  	}
	  	
	  	// verify promo code section added by Sophia
	  	String actualValue=this.getPromoCode();
	  	if( ! discount.promoCode.equalsIgnoreCase(actualValue)){
		  	  throw new ErrorOnPageException("promo code is not correct...; Expect value is :"
						+ discount.promoCode + ",but actual:" + actualValue);
		}
	  	actualValue=this.getPromoDescription();
	  	if( ! discount.promoDescription.equalsIgnoreCase(actualValue)){
		  	  throw new ItemNotFoundException("promo descriptio is not correct...; Expect value is :"
						+ discount.promoDescription + ",but actual:" + actualValue);
		}
	  	actualValue=this.getMaxUsagePerCust();
	  	if( ! discount.maxUsagePerCust.equalsIgnoreCase(actualValue)){
		  	  throw new ItemNotFoundException("max usage per customer is not correct...; Expect value is :"
						+ discount.maxUsagePerCust + ",but actual:" + actualValue);
		}
	  	
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(".id","com.reserveamerica.system.product.configurable.view.DiscountValidationEx" );//"com.reserveamerica.system.product.api.DiscountExistsEx" id changed by Sophia
		                                     
	}
	
	public void selectBuyXGetYDiscount() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "bogo_ind");
//		Object checked = objs[0].getProperty(".checked");
//		Browser.unregister(objs);
//		if(checked == null || String.valueOf(checked).equalsIgnoreCase("false")) {
//		  	browser.selectCheckBox(".id", "bogo_ind");
//		}
		selectBehavior("bogo_ind");
	}
	
	public void setMaxUsagePerCust(String maxValue) {
		IHtmlObject[] objs = browser.getTextField(".id", "maximumUsage");
		((IText)objs[objs.length-1]).setText(maxValue);
		Browser.unregister(objs);		
	}
	
	public boolean isPromoCodeSectionExist(){
		IHtmlObject[] objs = browser.getTextField(".id", "promoCode");
		if(objs.length>1){
			Browser.unregister(objs);	
			return true;
		}else{
			Browser.unregister(objs);	
			return false;
		}
	}
	
	public boolean isPromoDescriptionSectionExist(){
		IHtmlObject[] objs = browser.getTextField(".id", "promoDescription");
		if(objs.length>1){
			Browser.unregister(objs);	
			return true;
		}else{
			Browser.unregister(objs);	
			return false;
		}
	}
	
	public boolean isMaxUsagePerCustSectionExist(){
		IHtmlObject[] objs = browser.getTextField(".id", "maximumUsage");
		if(objs.length>1){
			Browser.unregister(objs);	
			return true;
		}else{
			Browser.unregister(objs);	
			return false;
		}
	}
	
	public boolean isMaxUsagePerCustSectionReadOnly(){
		IHtmlObject[] objs = browser.getTextField(".id", "maximumUsage");
		String value=objs[objs.length-1].getProperty(".disabled");
		
		Browser.unregister(objs);	
		return Boolean.valueOf(value);
	}
	
	public String getPromoCode() {
		IHtmlObject[] objs = browser.getTextField(".id", "promoCode");
	  	String value = objs[objs.length-1].getProperty(".value").toString();
	  	Browser.unregister(objs);
	  	return value;
	}
	
	public String getPromoDescription() {
		IHtmlObject[] objs = browser.getTextField(".id", "promoDescription");
	  	String value = objs[objs.length-1].getProperty(".value").toString();
	  	Browser.unregister(objs);
	  	return value;
	}
	
	public String getMaxUsagePerCust() {
		IHtmlObject[] objs = browser.getTextField(".id", "maximumUsage");
	  	String value = objs[objs.length-1].getProperty(".value").toString();
	  	Browser.unregister(objs);
	  	return value;
	}
}
