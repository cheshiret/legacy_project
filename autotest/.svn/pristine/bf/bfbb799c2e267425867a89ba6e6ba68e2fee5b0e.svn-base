package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;

public class HFDeclineAwardPage extends HFLotteryApplicationPage {

	private static HFDeclineAwardPage _instance = null;

	public static HFDeclineAwardPage getInstance() {
		if (null == _instance)
			_instance = new HFDeclineAwardPage();

		return _instance;
	}

	protected HFDeclineAwardPage() {
	}

	protected Property[] acceptAwardDiv(){
		return Property.concatPropertyArray(div(), ".id", "declineAwardDiv");
	}

	protected Property[] pageTitle(){
		return Property.concatPropertyArray(div(), ".id", "pagetitle");
	}

	protected Property[] decline(){
		return Property.concatPropertyArray(a(), ".id", "declineAwardButton");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".id", "declineAwardCloser");
	}

	public String getPgTitle(){
		return browser.getObjectText(Property.atList(acceptAwardDiv(), pageTitle()));
	}

	public void clickDecline(){
		browser.clickGuiObject(Property.atList(acceptAwardDiv(), decline()));
	}

	public void clickCancel(){
		browser.clickGuiObject(Property.atList(acceptAwardDiv(), cancel()));
	}
}
