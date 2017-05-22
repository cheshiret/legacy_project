package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class InvMgrTourConfirmActionPage extends InventoryManagerPage {

	private static InvMgrTourConfirmActionPage instance = null;

	private InvMgrTourConfirmActionPage() {

	}

	public static InvMgrTourConfirmActionPage getInstance() {
		if (instance == null) {
			instance = new InvMgrTourConfirmActionPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return this.browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression("(O|o)(K|k)", false));
	}

	public void clickOkButton() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("(O|o)(K|k)", false), true);
	}

}
