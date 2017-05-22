/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.common;

import com.activenetwork.qa.awo.pages.UwpPage;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 18, 2013
 */
public class UwpHeaderCommonBar extends UwpPage {

	private static UwpHeaderCommonBar _instance = null;

	public static UwpHeaderCommonBar getInstance() {
		if (null == _instance)
			_instance = new UwpHeaderCommonBar();

		return _instance;
	}
	
	protected UwpHeaderCommonBar() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "topnav");
	}


}
