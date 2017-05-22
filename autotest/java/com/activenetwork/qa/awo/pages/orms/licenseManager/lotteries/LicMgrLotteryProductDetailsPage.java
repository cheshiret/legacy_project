
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.util.RegularExpression;





/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Nov 2, 2012
 */


public class LicMgrLotteryProductDetailsPage extends LicMgrLotteryProductCommonPage {

	private static LicMgrLotteryProductDetailsPage instance = null;

	protected LicMgrLotteryProductDetailsPage() {
	}

	public static LicMgrLotteryProductDetailsPage getInstance() {
		if (instance == null) {
			instance = new LicMgrLotteryProductDetailsPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLotteryUI-\\d+",false));
	}

	public void clickLicenseYearsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Licen(s|c)e Years(\\(\\d+\\))?", false));
	}

	public void clickBusinessRulesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Business Rules");
	}
	
	public void clickQuantityControlsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Quantity Controls");
	}
	
	public void clickParametersTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Parameters(\\(\\d+\\))?", false));
	}
	
	public void clickTextDisplayTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Text Display");
	}
	
	public void clickContractFeesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Contractor Fees");
	}
	
	public void clickSubTab(String tabName){
		browser.clickGuiObject(".class", "Html.A", ".text", tabName);
	}
}
