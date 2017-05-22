package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;

/**
 * @Description: Send My Password page, shown after click Forgot Password link on Sign in page
 * @Preconditions:
 * @SPEC: 
 * 
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Jul 31, 2013
 */
public class UwpSendMyPasswordPage extends UwpPage {

	private static UwpSendMyPasswordPage _instance = null;

	public static UwpSendMyPasswordPage getInstance() {
		if (null == _instance)
			_instance = new UwpSendMyPasswordPage();

		return _instance;
	}

	protected UwpSendMyPasswordPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "HTML.form", ".id", "memberForgotPasswordForm");
	}

}
