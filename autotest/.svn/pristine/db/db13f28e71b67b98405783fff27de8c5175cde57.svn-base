package com.activenetwork.qa.awo.pages.orms.financeManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class FinMgrCommonTopMenuPage extends FinanceManagerPage {

	/**
	 * Click the Home link
	 */
	public void clickHome() {
		RegularExpression rex = new RegularExpression("finance\\.rightmenu\\.id\\.[0-9]+", false);
		browser.clickGuiObject(".id", rex,".text", "Home");
	}

	/** Click on Sign out link from top menu. */
	public void clickSignOut() {
		RegularExpression rex = new RegularExpression("finance\\.rightmenu\\.id\\.[0-9]+", false);
		browser.clickGuiObject(".id", rex,".text", "Sign out");
	}

	/** Click on Switch link from top menu. */
	public void clickSwitch() {
		RegularExpression rex = new RegularExpression("finance\\.rightmenu\\.id\\.[0-9]+", false);
		browser.clickGuiObject(".id", rex,".text", "Switch");
	}

	/**
	 * Select by given value to go to special page.
	 * @param item
	 */
	public void selectFromList(String item) {
		browser.selectDropdownList(".id", "page_name", item);
	}

	/** Go to Fee Schedules page.*/
	public void selectFeesSchedules() {
		this.selectFromList("Fee Schedules");
	}

	/** Go to Discounts page.*/
	public void selectDiscounts() {
		this.selectFromList("Discounts");
	}

	/** Go to Taxes page.*/
	public void selectTaxes() {
		this.selectFromList("Taxes");
	}

	/** Go to Fin Sessions and Deposits page.*/
	public void selectFinSessionAndDeposits() {
		this.selectFromList("Fin Sessions and Deposits");
	}

	/** Go to Distribution page.*/
	public void selectDistribution() {
		this.selectFromList("Distribution");
	}

	/** Go to Deposits, Credit Cards, Reconciliations page.*/
	public void selectDeposits() {
		this.selectFromList("Deposits, Credit Cards, Reconciliations");
	}

	/** Go to Batches and Reconciliations page.*/
	public void selectReconciliation() {
		this.selectFromList("Batches and Reconciliations");
	}
	
	/**Go to partner invoice page.*/
	public void selectPartnerInvoice()
	{
	  	this.selectFromList("Partner Invoice");
	}
	/**
	 * Go to RA Fee Main page
	 *
	 */
	public void selectRaFee()
	{
	  	this.selectFromList("RA Fees");
	}
	
	/**
	 * This method is used to get current login user name in the right top page
	 * @return current login user name like:QA-Shane Test-Song
	 */
	public String getCurrentUser() {
		String userName = "";
		RegularExpression rex = new RegularExpression(".*QA-.+ Test-.+", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);

		if (null != objs && objs.length > 0) {
			IHtmlTable table = (IHtmlTable)objs[0];
			String text = table.getProperty(".text")
					.toString();
			userName = text.substring(text.indexOf("QA") - 1,
					text.indexOf(" -")).trim();
		}
		Browser.unregister(objs);
		return userName;
	}



}
