/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Apr 17, 2012
 */
public class LicMgrAlertDialogWidget extends DialogWidget {
	
	private static LicMgrAlertDialogWidget instance = null;

	private LicMgrAlertDialogWidget(boolean isTitleNull) {
		super("Alert" + (isTitleNull ? "|" : ""));
	}
	
	public static LicMgrAlertDialogWidget getInstance() {
		return getInstance(false);
	}

	//Jane[2014-6-26]:updated for sanity test case LM _MSInternet_PrivilegePurchase on ORMS3.06.01
	public static LicMgrAlertDialogWidget getInstance(boolean isTitleNull) {
		if (instance == null) {
			instance = new LicMgrAlertDialogWidget(isTitleNull);
		}
		return instance;
	}

	public String getMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SPAN",".className","messagebox_text");
		if(objs.length<1)
			objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "ui-dialog-content ui-widget-content");
		if(objs.length<1)
			throw new ItemNotFoundException("Could not get error message on widget.");
		String msg = objs[0].text().trim();
		Browser.unregister(objs);
		return msg;
	}
}
