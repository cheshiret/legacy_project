/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.pages.web.common.UwpHeaderCommonBar;

/**
 * @Description: Contact Us page. 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 19, 2013
 */
public class HFContactUsPage extends UwpHeaderCommonBar {
	private static HFContactUsPage _instance = null;

	public static HFContactUsPage getInstance() {
		if (null == _instance)
			_instance = new HFContactUsPage();

		return _instance;
	}

	protected HFContactUsPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".id", "contactTable");
	}
}
