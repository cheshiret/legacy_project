package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: HF Lottery Terms and Conditions page
 * 
 * @author Lesley Wang
 * @Date  Feb 12, 2014
 */
public class HFLotteryTermsConditionsPage extends HFHeaderBar {
	private static HFLotteryTermsConditionsPage _instance = null;

	public static HFLotteryTermsConditionsPage getInstance() {
		if (null == _instance)
			_instance = new HFLotteryTermsConditionsPage();

		return _instance;
	}
	
	protected HFLotteryTermsConditionsPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] acceptBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".id", "tcSubmit");
	}
	/** Page Object Property Definition END */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(acceptBtn());
	}
	
	public void clickAcceptAndProceed() {
		browser.clickGuiObject(acceptBtn());
	}
}
