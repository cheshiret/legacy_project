package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  May 10, 2012
 */
public class FinMgrEFTPage extends FinanceManagerPage{
	private static FinMgrEFTPage _instance = null;
	protected FinMgrEFTPage() {
	}
	
	public static FinMgrEFTPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrEFTPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
		"SearchEFTTransmissionJobGrid");
	}
	
	private void gotoSubTabPage(String tabName){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", tabName, true);
		ajax.waitLoading();
	}
	
	public void gotoTransmissionJobsTab() {
		gotoSubTabPage("Transmission Jobs");
	}
	
	public void gotoReturnsJobsTab() {
		gotoSubTabPage("Returns Jobs");
	}
	
	public void gotoInvoicingJobsTab() {
		gotoSubTabPage("Invoicing Jobs");
	}
	
	public void gotoInvoicesTab() {
		gotoSubTabPage("Invoices");
	}
	
	public void gotoRemittancesTab() {
		gotoSubTabPage("Remittances");
	}
	
	public void gotoDailyEFTJobsTab() {
		gotoSubTabPage("Daily EFT Jobs");
	}
}
