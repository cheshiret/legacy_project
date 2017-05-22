package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
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
public class LicMgrLotteryExecutionConfigSelectWidget extends DialogWidget {
	
	private static LicMgrLotteryExecutionConfigSelectWidget _instance = null;
	
	private LicMgrLotteryExecutionConfigSelectWidget() {
		super();
	}
	
	public static LicMgrLotteryExecutionConfigSelectWidget getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryExecutionConfigSelectWidget();
		
		return _instance;
	}
	
	private Property[] lotteryExecutionConfig() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.privilegeLotteryExecConfig", false));
	}
	
	public void selectLotteryExecutionConfig(String config) {
		browser.selectDropdownList(lotteryExecutionConfig(), config);
	}
}
