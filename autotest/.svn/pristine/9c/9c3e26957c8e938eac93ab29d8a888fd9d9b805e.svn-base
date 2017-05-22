package com.activenetwork.qa.awo.pages.orms.common.lottery;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsRevokeLotteryPage extends OrmsPage {
	private static OrmsRevokeLotteryPage _instance = null;

	public OrmsRevokeLotteryPage() {

	}

	public static OrmsRevokeLotteryPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsRevokeLotteryPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Complete Revoke");
	}
	
	public void setNotes(String note) {
		browser.setTextArea(".id", new RegularExpression("(userNotes|revokeNotes)",false), note);
	}
	
	public void selectReason(String reason) {
		if (reason == null || reason.length() < 1)
			browser.selectDropdownList(".id", new RegularExpression("(reasonID|revokeReason)",false), 1);
		else {
			browser.selectDropdownList(".id", new RegularExpression("(reasonID|revokeReason)",false), reason);
		}
	}
	

	/**Click Complete Revoke*/
	public void clickCompleteRevoke() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Complete Revoke");
	}

	/**Click Don't Revoke*/
	public void clickDontRevoke() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Revoke");
	}
}
