package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.financialconfig;

import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FinancialConfig;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreFinancialConfigPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;


/**
 * 
 * @Description: This case is used to verify whether editing store financial config successfully or not
 * @Preconditions: Need an existing vendor, and store which Location Class=Commercial Agent.
 * @SPEC: <<Edit Store Financial Info.doc>>
 * @Task#: AUTO-801
 * 
 * @author qchen
 * @Date  Jan 6, 2012
 */
public class EditFinancialConfig extends LicenseManagerTestCase {
	private FinancialConfig config = new FinancialConfig();
	private String storeName = "";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoStoreDetailPage(storeName);
		
		//edit store financial info
		lm.editStoreFinancialConfig(config);
		
		//verify edited financial info
		this.verifyStoreFinancialConfigInfo(config);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		storeName = "AutoTest";
		
		Random random = new Random();
		config.thresholdEnforcement = new String[]{"50% of Bond Amount", "75% of Bond Amount", "100% of Bond Amount", "125% of Bond Amount", "150% of Bond Amount", "Threshold Amount"}[random.nextInt(5)];
		if(config.thresholdEnforcement.equals("Threshold Amount")) {
			config.thresholdAmount = String.valueOf(random.nextInt(10000));
		}
		config.thresholdAction = new String[]{"EFT Immediately (Notify)", "Notification Only", "Stop Sales (Notify)"}[random.nextInt(2)];
		config.revokeIfBondExpired = new Boolean[]{true, false}[random.nextInt(1)];
		config.notificationEmails = new String[]{"quentin.chen" + random.nextInt(1000) + "@activenetwork.com", "shane.song"+ random.nextInt(1000) +"@activenetwork.com", "peter.zhu"+ random.nextInt(1000) +"@activenetwork.com"};
		config.notificationEmailsToRemove = new String[]{config.notificationEmails[random.nextInt(2)]};
	}
	
	/**
	 * Verify store financial config info edited correctly
	 * @param expectedConfig
	 */
	private void verifyStoreFinancialConfigInfo(FinancialConfig expectedConfig) {
		LicMgrStoreFinancialConfigPage financialConfigPage = LicMgrStoreFinancialConfigPage.getInstance();
		
		logger.info("Verify Financial Config info.");
		boolean result = financialConfigPage.compareFinancialConfigInfo(expectedConfig);
		if(!result) {
			throw new ErrorOnPageException("Actual Financial Config info doesn't match with expected.");
		} else logger.info("Actual Financial Config info matches with expected.");
	}
}
