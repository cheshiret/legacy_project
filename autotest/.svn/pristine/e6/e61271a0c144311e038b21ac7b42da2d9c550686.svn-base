package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 8, 2011
 */
public abstract class LicMgrPrivilegeQuantityControlCommonWidget extends DialogWidget {
	
	protected LicMgrPrivilegeQuantityControlCommonWidget(String titleName) {
		super(titleName);
	}
	
	public void selectLocationClass(String locClass){
		browser.selectDropdownList(".id", 
				new RegularExpression("ProductQuantityControlView-\\d+\\.locationClassID",false), locClass);
	}
	
	public void setMaxQuantityPerTransaction(String maxQuantityPerTran){
		browser.setTextField(".id", 
				new RegularExpression("ProductQuantityControlView-\\d+\\.maxQtyPerTrans",false), maxQuantityPerTran);
	}
	
	public void setMaxAllowed(String maxAllowed){
		browser.setTextField(".id",new 
				RegularExpression("ProductQuantityControlView-\\d+\\.maxAllowed",false), maxAllowed);
	}
	
	public void setMinQuantityPerTransaction(String minQuantityPerTran){
		browser.setTextField(".id",
				new RegularExpression("ProductQuantityControlView-\\d+\\.minQtyPerTrans",false), minQuantityPerTran);
	}
	
	public void setReplacementMaxAllowed(String replacementMaxAllowed){
		browser.setTextField(".id", 
				new RegularExpression("ProductQuantityControlView-\\d+\\.maxReplacementAllowed",false), replacementMaxAllowed);
	}
	
	public boolean isReplacementMaxAllowedExist(){
		return browser.checkHtmlObjectExists(".id", 
				new RegularExpression("ProductQuantityControlView-\\d+\\.maxReplacementAllowed",false));
	}
	public String getErrorMessage() {
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public void selectMultiSalesAllowance(String multiSalesAllowance){
		browser.selectDropdownList(".id", 
				new RegularExpression("ProductQuantityControlView-\\d+\\.multiSalesAllowance",false), multiSalesAllowance);
		ajax.waitLoading();
	}
	
	/**
	 * Check whether the ID field is editable or not
	 * @return
	 */
	public boolean checkQuantityControlIDIsEditable() {
		Property[] p = new Property[3];
		p[0] = new Property(".class","Html.DIV");
		p[1] = new Property(".className", "inputwithrubylabel");
		p[2] = new Property( ".text", new RegularExpression("^ID",false));
		IHtmlObject[] topObjs = browser.getHtmlObject(p);
		IHtmlObject objs[] = null;
		if(topObjs.length > 0) {
			objs = browser.getTextField(new Property[]{new Property(".className", "readonly")}, topObjs[0]);
		}
		
		boolean property = Boolean.parseBoolean(objs[0].getProperty("isDisabled"));
		
		Browser.unregister(topObjs);
		Browser.unregister(objs);
		return property ? false:true;
	}
	
	public boolean checkMaxQuantityPerTranIsExists() {
		boolean isExists = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "theTag1");
		String property = objs[0].style("display").trim();
		if(property.equals("none")){
			isExists = false;
		}else {
			isExists = true;
		}
		
		Browser.unregister(objs);
		return isExists;
	}
	
	public boolean checkMaxAllowedIsExists() {
		boolean isExists = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "theTag2");
		String property = objs[0].style("display").trim();
		if(property.equals("none")){
			isExists = false;
		}else {
			isExists = true;
		}
		
		Browser.unregister(objs);
		return isExists;
	}	
	
	public boolean checkMinQuantityPerTranIsExists() {
		boolean isExists = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "theTag1");
		String property = objs[0].style("display").trim();
		if(property.equals("none")){
			isExists = false;
		}else {
			isExists = true;
		}
		
		Browser.unregister(objs);
		return isExists;
	}
	
	public boolean checkReplacementMaxAllowedExist() {
		return browser.checkHtmlObjectExists(".id", 
				new RegularExpression("ProductQuantityControlView-\\d+\\.maxReplacementAllowed",false));
	}
	
	/**
	 * Verify whether the system require the User to specify the Multiple Sales Allowance per Customer in the selected Location Class for the Privilege Product.
	 * @param optionValue - the multiple sales allowance option
	 * @param perTranIsDisplay
	 * @param maxAllowanceIsDisplay
	 * @return
	 */
	public boolean verifyMultiSalesAllowanceOptionBusinessRule(String optionValue, boolean perTranIsDisplay, boolean maxAllowedIsDisplay){
		boolean result = true;

		this.selectMultiSalesAllowance(optionValue);
		
		if(perTranIsDisplay) {
			if(!this.checkMaxQuantityPerTranIsExists()) {
				result &= false;
				logger.error("Max per transaction option should exists.");
			}
		} else {
			if(this.checkMaxQuantityPerTranIsExists()) {
				result &= false;
				logger.error("Max per transaction option should not exists.");
			}
		}
		
		if(maxAllowedIsDisplay) {
			if(!this.checkMaxAllowedIsExists()) {
				result &= false;
				logger.error("Max per transaction option should exists.");
			}
		} else {
			if(this.checkMaxAllowedIsExists()) {
				result &= false;
				logger.error("Max per transaction option should not exists.");
			}
		}
		
		return result;
	}

}
