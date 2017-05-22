/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author szhou
 * 
 */
public class LicMgrStoreEFTInvoiceTopMeunPage extends LicMgrCommonTopMenuPage {
	private static LicMgrStoreEFTInvoiceTopMeunPage _instance = null;

	protected LicMgrStoreEFTInvoiceTopMeunPage() {
	}

	public static LicMgrStoreEFTInvoiceTopMeunPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrStoreEFTInvoiceTopMeunPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text", new RegularExpression(
				"^Agent Info.*", false));
	}
}
