/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Aug 27, 2012
 */
public class LicMgrVendorEFTInvoiceTopMeunPage extends LicMgrCommonTopMenuPage{

	
	private static LicMgrVendorEFTInvoiceTopMeunPage _instance = null;

	protected LicMgrVendorEFTInvoiceTopMeunPage() {
	}

	public static LicMgrVendorEFTInvoiceTopMeunPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVendorEFTInvoiceTopMeunPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text", new RegularExpression(
				"^Vendor Info.*", false));
	}
	
}
