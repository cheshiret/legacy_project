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
public class LicMgrConsumableProductDetailsPage extends LicMgrConsumableProductCommonPage {
	private static LicMgrConsumableProductDetailsPage _instance = null;
	
	protected LicMgrConsumableProductDetailsPage() {}
	
	public static LicMgrConsumableProductDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrConsumableProductDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("ConsumableProductView-\\d+\\.productSubCategory", false));
	}
	
	public void clickChangeHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change History");
	}
	
	public String getCode() {
		return browser.getTextFieldValue(".id", new RegularExpression("ConsumableProductView-\\d+\\.productCode", false)).trim();
	}
	
	public String getProductName() {
		return browser.getTextFieldValue(".id", new RegularExpression("ConsumableProductView-\\d+\\.name", false)).trim();
	}
	
	public String getDescription() {
		return browser.getTextFieldValue(".id", new RegularExpression("ConsumableProductView-\\d+\\.description", false));
	}
	
	public String getProductGroup() {
		return browser.getDropdownListValue(".id", new RegularExpression("ConsumableProductView-\\d+\\.productGroup", false), 0);
	}
	
	public String getVariablePrice() {
		return browser.getDropdownListValue(".id", new RegularExpression("ConsumableProductView-\\d+\\.variablePrice", false), 0);
	}
	
	public void clickPricingTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Pricing");
	}
	
	public void clickFiscalYearTab() {
		clickSubTab("Fiscal Year");
	}
	
	public void clickAgentAssignmentTab() {
		clickSubTab("Agent Assignments");
	}
	
	public void clickQuestionsTab() {
		clickSubTab("Questions");
	}
	
	public void clickSubTab(String subTab){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", subTab);
		ajax.waitLoading();
	}

	public void clickContractFeesTab() {
		clickSubTab("Contractor Fees");
	}
}
