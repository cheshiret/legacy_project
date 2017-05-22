package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 2, 2013
 */
public class LicMgrApplicationOrderDetailsPage extends LicMgrOrderDetailsCommonPage {
	
	private static LicMgrApplicationOrderDetailsPage _instance = null;
	private LicMgrApplicationOrderDetailsPage() {}
	public static LicMgrApplicationOrderDetailsPage getInstance() {
		if(_instance == null) _instance = new LicMgrApplicationOrderDetailsPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "LotteryApplicationOrderDetailsUI");
	}
	
	private Property[] change() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Change");
	}
	
	private Property[] reverse() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Reverse");
	}
	
	private Property[] revoke() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Revoke");
	}
	
	private Property[] addToCart() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add to Cart");
	}
	
	private Property[] fees() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Fees");
	}
	
	private Property[] receipts() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Receipts");
	}
	
	private Property[] history() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "History");
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	public void clickChange() {
		browser.clickGuiObject(change());
	}
	
	public void clickReverse() {
		browser.clickGuiObject(reverse());
	}
	
	public void clickRevoke() {
		browser.clickGuiObject(revoke());
	}
	
	public void clickAddToCart() {
		browser.clickGuiObject(addToCart());
	}
	
	public void clickFees() {
		browser.clickGuiObject(fees());
	}
	
	public void clickReceipts() {
		browser.clickGuiObject(receipts());
	}
	
	public void clickHistory() {
		browser.clickGuiObject(history());
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
	
	private Property[] choicesTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Choices");
	}
	
	private Property[] exceptionsTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Exceptions");
	}
	
	public void clickChoicesTab() {
		browser.clickGuiObject(choicesTab());
	}
	
	public void clickExceptionsTab() {
		browser.clickGuiObject(exceptionsTab());
	}
}
