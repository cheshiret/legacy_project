package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 20, 2011
 */
public class LicMgrStoreEFTInvoicePaymentsPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreEFTInvoicePaymentsPage _instance = null;
	
	protected LicMgrStoreEFTInvoicePaymentsPage() {}
	
	public static LicMgrStoreEFTInvoicePaymentsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreEFTInvoicePaymentsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Apply Invoice Payment");
	}
	
	/**
	 * Click 'Apply Invoice Payment' button
	 */
	public void clickApplyInvoicePayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply Invoice Payment");
	}
	
	/**
	 * Set value to 'Applied From Date' text field
	 * @param fromDate
	 */
	public void setAppliedFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression("EftInvoicePaymentsSearchCriteria-\\d+\\.appliedFromDate_ForDisplay", false), fromDate);
	}
	
	/**
	 * Set value to 'Applied To Date' text field
	 * @param toDate
	 */
	public void setAppliedToDate(String toDate) {
		browser.setTextField("", new RegularExpression("EftInvoicePaymentsSearchCriteria-\\d+\\.appliedToDate_ForDisplay", false), toDate);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
}
