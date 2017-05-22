package com.activenetwork.qa.awo.pages.orms.licenseManager.tax;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the tax detail page in license manger
 * How to go to this page:Go to schedule search page( Select "Tax" for the Financials dropdown list at the right top menu of license manager ) and then
 * click "Add New" on search page
 * @author Phoebe
 */
public class LicMgrTaxDetialPg extends LicMgrCommonTopMenuPage {
	static private LicMgrTaxDetialPg _instance = null;

	protected LicMgrTaxDetialPg() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrTaxDetialPg getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrTaxDetialPg();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV", ".id", "tax.setup.content");
	}
	
	protected Property[] taxNameTextField() {
		return Property.toPropertyArray(".id", "TaxTypeView.name");
	}
	
	protected Property[] taxCodeTextField() {
		return Property.toPropertyArray(".id", "TaxTypeView.taxCode");
	}
	
	protected Property[] taxDescTextArea() {
		return Property.toPropertyArray(".id", "TaxTypeView.description");
	}
	
	protected Property[] taxRateTypeDropdownList() {
		return Property.toPropertyArray(".id", "TaxTypeView.rateType");
	}
	
	protected Property[] feeTypeCheckBox() {
		return Property.toPropertyArray(".id", "TaxTypeView.applicableFeeTypes");
	}
	
	protected Property[] okBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Ok");
	}
	
	protected Property[] cancelBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Cancel");
	}
	
	protected Property[] applyBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Apply");
	}
	
	public void setTaxName(String name){
		browser.setTextField(this.taxNameTextField(), name);
	}
	
	public void setTaxCode(String code){
		browser.setTextField(this.taxCodeTextField(), code);
	}
	
	public void setTaxDescription(String des){
		browser.setTextArea(this.taxDescTextArea(), des);
	}
	
	public void selectTaxRateType(String rateType){
		browser.selectDropdownList(this.taxRateTypeDropdownList(), rateType);
	}
	
	public void clickOk(){
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickApply(){
		browser.clickGuiObject(this.applyBtn());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelBtn());
	}
	
	public void selectFeeType(String feeType){
		String value = "";
		if(feeType.equalsIgnoreCase("Activity Fee")){
			value = OrmsConstants.FEETYPE_ACTIVITYFEE; //"39";
		}
		if(feeType.equalsIgnoreCase("Transaction Fee")){
			value = OrmsConstants.FEETYPE_TRANFEE; //"4";
		}
		if(feeType.equalsIgnoreCase("Vendor Fee")){
			value = OrmsConstants.FEETYPE_VENDORFEE; //"36";
		}
		if(feeType.equalsIgnoreCase("Privilege Fee")){
			value = OrmsConstants.FEETYPE_PRIVILEGEFEE ; //"34";
		}
		if(feeType.equalsIgnoreCase("Privilege Lottery Fee")){
			value = OrmsConstants.FEETYPE_PRIVILEGELOTTERYFEE; //"37";
		}
		browser.selectCheckBox(Property.concatPropertyArray(this.feeTypeCheckBox(), ".value",value));
	}
	
	public void selectFeeTypes(List<String> feeTypes){
		for(String type:feeTypes){
			this.selectFeeType(type);
		}
	}
	
	public void setTaxInfo(Tax tax){
		if(StringUtil.notEmpty(tax.taxName)){
			this.setTaxName(tax.taxName);
		}
		if(StringUtil.notEmpty(tax.taxCode)){
			this.setTaxCode(tax.taxCode);
		}
		if(StringUtil.notEmpty(tax.taxDescription)){
			this.setTaxDescription(tax.taxDescription);
		}
		if(StringUtil.notEmpty(tax.taxRateType)){
			this.selectTaxRateType(tax.taxRateType);
		}
		if(tax.feeTypes!=null && tax.feeTypes.size() >0){
			this.selectFeeTypes(tax.feeTypes);
		}
	}
	
	public void setTaxInfoAndClickApply(Tax tax){
		this.setTaxInfo(tax);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	
	public String getTaxId(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Tax Name.*", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find the span object for tax id!");
		}
		String id = objs[0].text().replace("Tax Name", "");
		Browser.unregister(objs);
		return id;
	}
	
	public String getTaxName(){
		return browser.getTextFieldValue(this.taxNameTextField());
	}
	
	public String getTaxCode(){
		return browser.getTextFieldValue(this.taxCodeTextField());
	}
	
	public String getTaxDescription(){
		return browser.getTextAreaValue(this.taxDescTextArea());
	}
	
	public String getTaxRateType(){
		return browser.getDropdownListValue(this.taxRateTypeDropdownList(), 0);
	}
	
	public List<String> getFeeType(){
		List<String> feeTypes = new ArrayList<String>();
		IHtmlObject[] objs = browser.getCheckBox(this.feeTypeCheckBox());
		for(int i=0;i<objs.length; i++){
			ICheckBox checkBoxObj = (ICheckBox)objs[i];
			if(checkBoxObj.isSelected()){
				String value = checkBoxObj.getAttributeValue("value");
				String feeType = "";
				if(value.equalsIgnoreCase(OrmsConstants.FEETYPE_ACTIVITYFEE)){
					feeType = "Activity Fee"; //"39";
				}
				if(value.equalsIgnoreCase(OrmsConstants.FEETYPE_TRANFEE)){
					feeType = "Transaction Fee"; //"4";
				}
				if(value.equalsIgnoreCase(OrmsConstants.FEETYPE_VENDORFEE)){
					feeType = "Vendor Fee"; //"36";
				}
				if(value.equalsIgnoreCase(OrmsConstants.FEETYPE_PRIVILEGEFEE)){
					feeType =  "Privilege Fee"; //"34";
				}
				if(value.equalsIgnoreCase(OrmsConstants.FEETYPE_PRIVILEGELOTTERYFEE)){
					feeType = "Privilege Lottery Fee"; //"37";
				}
				feeTypes.add(feeType);
			}
		}
		return feeTypes;
	}
	
	public String getErrorMsg(){
		String msg = "";
		if(browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "message msgerror")){
			msg = browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
		}else if(browser.checkHtmlObjectExists(".class", "Html.SPAN", ".className", "message msgerror")){
			msg = browser.getObjectText(".class", "Html.SPAN", ".className", "message msgerror");
		}
		return msg;
    }
	
	public boolean compareTaxInfo(Tax tax){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Tax Name:", tax.taxName, this.getTaxName());
		passed &= MiscFunctions.compareResult("Tax Code:", tax.taxCode, this.getTaxCode());
		passed &= MiscFunctions.compareResult("Tax Description:", tax.taxDescription, this.getTaxDescription());
		passed &= MiscFunctions.compareResult("Tax Rate Type:", tax.taxRateType, this.getTaxRateType());
		if(!tax.feeTypes.containsAll(this.getFeeType())||!this.getFeeType().containsAll(tax.feeTypes)){
			logger.info("The fee type is not correct, expect fee type:"+tax.feeTypes.toString() + ", but actually is:"+this.getFeeType().toString());
			passed = false;
		}
		return passed;
	}
	
	public void verifyTaxInfo(Tax expTax){
		if(!this.compareTaxInfo(expTax)){
			throw new ErrorOnPageException("Tax detail is not correct, please check the log above!");
		}
		logger.info("New added tax information is correct!");
	}
}
