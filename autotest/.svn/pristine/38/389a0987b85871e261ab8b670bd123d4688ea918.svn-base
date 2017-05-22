package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;

public class HFAcceptAwardPage extends HFLotteryApplicationPage {

	private static HFAcceptAwardPage _instance = null;

	public static HFAcceptAwardPage getInstance() {
		if (null == _instance)
			_instance = new HFAcceptAwardPage();

		return _instance;
	}

	protected HFAcceptAwardPage() {
	}

	protected Property[] acceptAwardDiv(){
		return Property.concatPropertyArray(div(), ".id", "acceptAwardDiv");
	}

	protected Property[] pageTitle(){
		return Property.concatPropertyArray(div(), ".id", "pagetitle");
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".id", "acceptAwardButton");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".id", "acceptAwardCloser");
	}

	public String getPgTitle(){
		return browser.getObjectText(Property.atList(acceptAwardDiv(), pageTitle()));
	}

	public void clickOK(){
		browser.clickGuiObject(Property.atList(acceptAwardDiv(), ok()));
	}

	public void clickCancel(){
		browser.clickGuiObject(Property.atList(acceptAwardDiv(), cancel()));
	}
}
