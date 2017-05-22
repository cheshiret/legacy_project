package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public class LicMgrCreateNewConsumablePage extends LicMgrConsumableProductCommonPage {
	
	private static LicMgrCreateNewConsumablePage _instance = null;
	
	private String prefixReg = "^ConsumableProductView-[0-9]*.";

	private LicMgrCreateNewConsumablePage() {}
	
	public static LicMgrCreateNewConsumablePage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrCreateNewConsumablePage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("ConsumableProductView-\\d+\\.productSubCategory", false));
	}
	
	/**Retrieve the error message displays in top of page.*/
	public String getErrorMessage() {
		return browser.getObjectText(".id","NOTSET");
	}
	
	/**
	 * Select order type.
	 * @param type
	 */
	public void selectOrderType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"productSubCategory", false), type);
		ajax.waitLoading();
	}
	
	/**De-select order type.*/
	public void deselectOrderType() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"productSubCategory", false), 0);
	}
	
	/**Retrieve the product id.*/
	public String getProductID() {
		return null;//waiting for POS product configure
	}

	/**Set product code.*/
	public void setProductCode(String code) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"productCode", false), code);
	}
	
	/**Set product name.*/
	public void setProductName(String name) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"name", false), name);
	}
	
	/**Set product description.*/
	public void setProductDescription(String description) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"description", false), description);
	}
	
	/**Select product group by given value.*/
	public void selectProductGroup(String group) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"productGroup", false), group);
	}
	
	/**De-select product group.*/
	public void deselectProductGroup() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"productGroupID", false), 0);
	}
	
	/**Select to allow variable price or not. true - Yes, false - No*/
	public void selectVariablePrice(boolean isAllowed) {
		if(isAllowed) {
			browser.selectDropdownList(".id", new RegularExpression(prefixReg+"variablePrice", false), "Yes");
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefixReg+"variablePrice", false), "No");
		}
	}

	/**Click OK button.*/
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
		ajax.waitLoading();
	}
	
	/**Click Cancel button.*/
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**Click Apply button to apply changes.*/
	public void clickApply(){
		browser.clickGuiObject(".class","Html.A",".text","Apply");
		ajax.waitLoading();
	}
	
	/*
	 * Added by Quentin below
	 */
	/**Click 'Pricing' link to goto pricing sub page**/
	public void clickPricingTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Pricing");
		ajax.waitLoading();
	}
	
	/**Click 'Fiscal Year' link to goto fiscal year sub page**/
	public void clickFiscalYearTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fiscal Year", true);
		ajax.waitLoading();
	}
	
	/**Click 'Store Assignment' link to goto store assignment sub page**/
	public void clickStoreAssignmentTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Store Assignment");
		ajax.waitLoading();
	}
	
	/**Click 'Questions' link to goto questions sub page**/
	public void clickQuestionsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Questions");
		ajax.waitLoading();
	}
	
	/**Click 'Contractor Fees' link to goto contractor fees sub page**/
	public void clickContractorFeesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Contractor Fees");
		ajax.waitLoading();
	}
	
	 /*
	  * Methods in Fiscal Year sub page
	  */
	/**Click 'Add Fiscal Year' button to add new fiscal year**/
	public void clickAddFiscalYear() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Fiscal Year");
	}
	
	/**Check Current Records Only check box**/
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.showCurrentRecordsOnly", false));
	}
	
	/**Uncheck Current Records Only check box**/
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.showCurrentRecordsOnly", false));
	}
	
	/**Select the fiscal year instances' status**/
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.status", false), status);
	}
	
	/**Select the fiscal year instances' year**/
	public void selectYear(String fiscalYear) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.fiscalYear", false), fiscalYear);
	}
	
	/**Select the fiscal year instances' location class**/
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.locationClassID", false), locationClass);
	}
	
	/**
	 * Click 'Go' button in Fiscal Year sub page
	 */
	public void clickGoInFiscalYearTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	/**
	 * Click fiscal year id to goto edit fiscal year page
	 * @param fiscalYearID
	 */
	public void selectFiscalYearRecord(String fiscalYearID) {
		browser.clickGuiObject(".class", "Html.A", ".text", fiscalYearID);
	}
}
