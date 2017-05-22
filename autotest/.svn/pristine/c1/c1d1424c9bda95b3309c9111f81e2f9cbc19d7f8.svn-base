package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 4, 2013
 */
public class LicMgrProcessingConfigurationPage extends LicMgrProcessingDetailsPage {
	
	private static LicMgrProcessingConfigurationPage _instance = null;
	
	private LicMgrProcessingConfigurationPage() {}
	
	public static LicMgrProcessingConfigurationPage getInstance() {
		if(_instance == null) _instance = new LicMgrProcessingConfigurationPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("^LotteryExecConfig-", false));
	}
}
