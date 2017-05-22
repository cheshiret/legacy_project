package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;

public class UwpActiveAdvantageTrialOfferPage extends UwpPage{
	private static UwpActiveAdvantageTrialOfferPage _instance = null;

	public UwpActiveAdvantageTrialOfferPage() {
	}

	public static UwpActiveAdvantageTrialOfferPage getInstance() {
		if (null == _instance)
			_instance = new UwpActiveAdvantageTrialOfferPage();

		return _instance;
	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".id","EMAIL_INPUT_VERIFY");
	}
	
	public void clickNoThanks() {
		browser.clickGuiObject(".class","Html.BUTTON",".id","nobtn");
	}

}
