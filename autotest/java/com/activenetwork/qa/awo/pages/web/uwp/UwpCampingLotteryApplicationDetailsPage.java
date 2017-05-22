package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;

public class UwpCampingLotteryApplicationDetailsPage  extends UwpPage {

	private static UwpCampingLotteryApplicationDetailsPage _instance = null;

	public static UwpCampingLotteryApplicationDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpCampingLotteryApplicationDetailsPage();

		return _instance;
	}

	public UwpCampingLotteryApplicationDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "contenthdr", ".text", "Lottery Application");
	}
	
	public String getLotteryApplicationContent(){
		return browser.getObjectText(".class", "Html.TABLE", ".className", "contable splitPanel");
	}
}
