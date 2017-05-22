package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.financialconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FinancialConfig;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreFinancialConfigPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify Store Financial Info Change History
 * @Preconditions: Need an existing vendor and store. And the store Location Class should be 'Commercial Agent'
 * @SPEC: <<View Store Financial Info Change History.doc>> & <<Edit Store Financial Info.doc>>
 * @Task#: AUTO-801
 * 
 * @author qchen
 * @Date  Jan 6, 2012
 */
public class ViewChangeHistory extends LicenseManagerTestCase {
	private FinancialConfig config = new FinancialConfig();
	private String storeName = "";
	ChangeHistory changeHistory1 = new ChangeHistory();
	ChangeHistory changeHistory2 = new ChangeHistory();
	ChangeHistory changeHistory3 = new ChangeHistory();
	ChangeHistory changeHistory4 = new ChangeHistory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoStoreDetailPage(storeName);
		
		//get the old financial config info from page
		FinancialConfig oldConfig = this.getFinancialConfigInfo();
		
		//edit store financial info
		this.prepareEditingParameters(oldConfig);
		lm.editStoreFinancialConfig(config);
		
		//verify store change history info
		lm.gotoStoreChangeHistoryPage();
		List<ChangeHistory> expectedHistories = this.prepareExpectedChangeHistoryInfo(oldConfig, config);
		this.verifyChangeHistoryInfo(expectedHistories);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		storeName = "QuentinTestAgent";
	}
	
	private void prepareEditingParameters(FinancialConfig oldInfo) {
		Random random = new Random();
		String enforcements[] = new String[]{"50% of Bond Amount", "75% of Bond Amount", "100% of Bond Amount", "125% of Bond Amount", "150% of Bond Amount", "Threshold Amount"};
		String actions[] = new String[]{"EFT Immediately (Notify)", "Notification Only", "Stop Sales (Notify)"};
		
		config.thresholdEnforcement = this.getDifferentValue(enforcements, oldInfo.thresholdEnforcement);
		config.thresholdAmount = String.valueOf(random.nextInt(10000));
		config.thresholdAction = this.getDifferentValue(actions, oldInfo.thresholdAction);
		config.revokeIfBondExpired = oldInfo.revokeIfBondExpired ? false : true;
		config.notificationEmails = new String[]{"chenqing" + random.nextInt(1000) + "@activenetwork.com", "songxin"+ random.nextInt(1000) +"@activenetwork.com"};
		config.notificationEmailsToRemove = new String[]{config.notificationEmails[0]};
	}
	
	private String getDifferentValue(String array[], String arg) {
		Random random = new Random();
		int index = random.nextInt(array.length - 1);
		String toReturn = array[index];
		if(toReturn.equals(arg)) {
			toReturn = this.getDifferentValue(array, arg);
		}
		return toReturn;
	}
	
	private List<ChangeHistory> prepareExpectedChangeHistoryInfo(FinancialConfig oldConfig, FinancialConfig newConfig) {
		//Threshold Enforcement
		changeHistory1.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(TestProperty.getProperty(env + ".db.schema.prefix") + "MS"));
		changeHistory1.object = "Agent Financial Information";
		changeHistory1.action = "Update";
		changeHistory1.field = "Sales Threshold Enforcement";
		changeHistory1.oldValue = oldConfig.thresholdEnforcement;
		changeHistory1.newValue = newConfig.thresholdEnforcement;
		changeHistory1.user = DataBaseFunctions.getLoginUserName(login.userName);
		changeHistory1.location = login.location.split("/")[1].trim();
		
		//Threshold Amount
		if(newConfig.thresholdEnforcement.equals("Threshold Amount")) {
			changeHistory2.changeDate = changeHistory1.changeDate;
			changeHistory2.object = changeHistory1.object;
			changeHistory2.action = changeHistory1.action;
			changeHistory2.field = "Threshold Amount($)";
			changeHistory2.oldValue = oldConfig.thresholdAmount;
			changeHistory2.newValue = newConfig.thresholdAmount;
			changeHistory2.user = changeHistory1.user;
			changeHistory2.location = changeHistory1.location;
		}
		
		//Threshold Action Notification email
		changeHistory3.changeDate = changeHistory1.changeDate;
		changeHistory3.object = changeHistory1.object;
		changeHistory3.action = changeHistory1.action;
		changeHistory3.field = "vendor.store.financial.notificationemails";
		for(int i = 0 ; i < oldConfig.notificationEmails.length - 1; i ++) {
			changeHistory3.oldValue = oldConfig.notificationEmails[i] + "," + oldConfig.notificationEmails[i+1]; 
		}
		for(int i = 0 ; i < newConfig.notificationEmails.length - 1; i ++) {
			changeHistory3.newValue = newConfig.notificationEmails[i] + "," + newConfig.notificationEmails[i+1]; 
		}
		changeHistory3.user = changeHistory1.user;
		changeHistory3.location = changeHistory1.location;
		
		//Threshold Action
		changeHistory4.changeDate = changeHistory1.changeDate;
		changeHistory4.object = changeHistory1.object;
		changeHistory4.action = changeHistory1.action;
		changeHistory4.field = "Sales Threshold Action";
		changeHistory4.oldValue = oldConfig.thresholdAction;
		changeHistory4.newValue = newConfig.thresholdAction;
		changeHistory4.user = changeHistory1.user;
		changeHistory4.location = changeHistory1.location;
		
		List<ChangeHistory> histories = new ArrayList<ChangeHistory>();
		histories.add(changeHistory1);
		if(newConfig.thresholdEnforcement.equals("Threshold Amount")) {
			histories.add(changeHistory2);
		}
		histories.add(changeHistory3);
		histories.add(changeHistory4);
		
		return histories;
	}
	
	private FinancialConfig getFinancialConfigInfo() {
		LicMgrStoreDetailsPage storeDetailsPage = LicMgrStoreDetailsPage.getInstance();
		LicMgrStoreFinancialConfigPage configPage = LicMgrStoreFinancialConfigPage.getInstance();
		
		logger.info("Get old finnacial config info from page.");
		storeDetailsPage.clickFinancialConfigTab();
		ajax.waitLoading();
		configPage.waitLoading();
		FinancialConfig info = configPage.getFinancialConfigInfo();
		
		return info;
	}
	
	/**
	 * Verify Store Financial Config change history info
	 * @param expectedPriv
	 */
	private void verifyChangeHistoryInfo(List<ChangeHistory> expectedHistories) {
		LicMgrStoreChangeHistoryPage changeHistoryPage = LicMgrStoreChangeHistoryPage.getInstance();
		
		logger.info("Verify store financial config info is correct or not.");
		boolean result = changeHistoryPage.compareFinancialConfigChangeHistoryInfo(expectedHistories);
		if(!result) {
			throw new ErrorOnPageException("Store Financial Config change history info are incorrect.");
		} else logger.info("Store Financial Config Change History info are correct.");
	}
}
