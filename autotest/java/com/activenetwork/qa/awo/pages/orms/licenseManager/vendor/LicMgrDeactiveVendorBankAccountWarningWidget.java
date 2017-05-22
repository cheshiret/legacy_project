/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Sep 29, 2011
 */
public class LicMgrDeactiveVendorBankAccountWarningWidget  extends DialogWidget{
	public static LicMgrDeactiveVendorBankAccountWarningWidget _instance = null;
	
	protected LicMgrDeactiveVendorBankAccountWarningWidget() {
		super();
	}
	
	public static LicMgrDeactiveVendorBankAccountWarningWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrDeactiveVendorBankAccountWarningWidget();
		}
		
		return _instance;
	}
	
	/**
	 * Get the error message
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		String msg = objs[0].getProperty(".text").trim();
		return msg;
	}
}
