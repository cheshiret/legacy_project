package com.activenetwork.qa.awo.pages.web.hf;

/**
 * @Description: HF Replace Lost Licenses/Permits page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jul 22, 2013
 */
public class HFReplaceLostLicensesPage extends HFMyAccountPanel {
	private static HFReplaceLostLicensesPage _instance = null;

	public static HFReplaceLostLicensesPage getInstance() {
		if (null == _instance)
			_instance = new HFReplaceLostLicensesPage();

		return _instance;
	}

	protected HFReplaceLostLicensesPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.FORM", ".id", "DuplicatePrivKit_form");
	}
}
