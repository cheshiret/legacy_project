/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 21, 2011
 */
public class LicMgrSupplyProductDetailsPage extends LicMgrProductCommonPage {

	private static LicMgrSupplyProductDetailsPage instance=null;
	
	protected LicMgrSupplyProductDetailsPage(){}
	
	public static LicMgrSupplyProductDetailsPage getInstance(){
		if(instance==null){
			instance=new LicMgrSupplyProductDetailsPage();
		}
		return instance;
	}
	
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "EditConsumableProduct");	
	}
	
	public void clickPricingSubTab(){
		browser.clickGuiObject( ".class","Html.A", ".text", "Pricing");
	}
	
	public void clickQuestionsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Questions");
	}
	
	/**click 'Contractor Fees' link*/
	public void clickContractorFeesTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Contractor Fees");
	    ajax.waitLoading();
	}

	public void clickAgentAssignmentTab() {
		clickSubTab("Agent Assignments");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
	public void clickInventoryTracking() {
		browser.clickGuiObject(".class", "Html.A",".text","Inventory Tracking");
	}
}
