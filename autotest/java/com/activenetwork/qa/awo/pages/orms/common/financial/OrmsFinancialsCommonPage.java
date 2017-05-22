package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 10, 2013
 */
public abstract class OrmsFinancialsCommonPage extends OrmsPage {
	
	/**
	 * Click FinancialSession Tab
	 *
	 */
	public void clickFinancalSessionsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Financial Sessions");
	}
	
	/**
	 * Click Deposit Tab
	 *
	 */
	public void clickDepositsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Deposits");
	}
	
	/**
	 * Click Refunds Tab
	 *
	 */
	public void clickRefundsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Refunds");
	}
	
	/**
	 * click Vouchers tab
	 *
	 */
	public void clickVouchersTab(){
	    browser.clickGuiObject(".class", "Html.SPAN", ".text", "Vouchers");
	}
	
	/** Click Payments link */
	public void clickPaymentsTab(){
		browser.clickGuiObject(".class","Html.SPAN",".text",new RegularExpression("Payments",false));
	}
	
	
	public void clickTransmittalsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Transmittals");
	}
	
	public boolean isTransmittalsTabExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Transmittals");
	}
}
