package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.pages.web.common.UwpHeaderCommonBar;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  May 2, 2013
 */
public class HFBigGameDrawTermsAndConditionsPage extends UwpHeaderCommonBar {
	private static HFBigGameDrawTermsAndConditionsPage _instance = null;

	public static HFBigGameDrawTermsAndConditionsPage getInstance() {
		if (null == _instance)
			_instance = new HFBigGameDrawTermsAndConditionsPage();

		return _instance;
	}

	protected HFBigGameDrawTermsAndConditionsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "tcSubmit", ".text", "Accept and Proceed");
	}
}
