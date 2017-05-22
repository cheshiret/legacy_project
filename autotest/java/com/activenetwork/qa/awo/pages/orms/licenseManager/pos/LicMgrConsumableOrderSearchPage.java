package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;


import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 *
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author qchen
 * @Date  Oct 27, 2011
 */
public class LicMgrConsumableOrderSearchPage extends LicMgrOrderSearchCommonPage {

	private static LicMgrConsumableOrderSearchPage _instance = null;

	protected LicMgrConsumableOrderSearchPage() {}

	public static LicMgrConsumableOrderSearchPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrConsumableOrderSearchPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Search", ".href", new RegularExpression("consumablesOrder$", false));
	}

	@Override
	public void setupOrderSearchCriteria(Object orderInfo) {
		// TODO Finish it
	}
}
