/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.admin;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleDetailPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrSelectLocationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CreateRuleFunction extends FunctionCase {

	private AdmMgrHomePage admHmPg = AdmMgrHomePage.getInstance();
	private AdmMgrRuleListPage admRuleLstPg = AdmMgrRuleListPage.getInstance();
	private AdmMgrSelectLocationPage admLocPg = AdmMgrSelectLocationPage
			.getInstance();
	private AdmMgrRuleDetailPage admRuleDetailPg = AdmMgrRuleDetailPage
			.getInstance();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	 
	private LoginInfo login;
	private RuleDataInfo ruleData;
	private String ruleType;
	private AdminManager admgr = AdminManager.getInstance();
	private String result;
	private boolean isLoggedIn = false;
	private String loggedInContract = "";
	private String selectedLocation = "";
	private String selectedLocationCategory = "";
	private String ruleTypeID = "";
	
	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.ruleType = (String)param[1];
		this.ruleData = (RuleDataInfo)param[2];
		this.ruleTypeID  = (String)param[3];
	}

	public void execute() {
		if(!loggedInContract.equalsIgnoreCase(login.contract) && isLoggedIn && isBrowserOpened) {
			admgr.logoutAdminMgr();
			isLoggedIn = false;
		}
		if(!isLoggedIn || !isBrowserOpened) {
			admgr.loginAdminMgr(login);
			isLoggedIn = true;
			loggedInContract = login.contract;
			
			// Select contract rule
			admHmPg.selectContractRules();
			admRuleLstPg.waitLoading();
			admRuleLstPg.enterRuleDetail(this.ruleType);
		}
		
		if(!ruleData.location.equalsIgnoreCase(selectedLocation) || !ruleData.locationCategory.equalsIgnoreCase(selectedLocationCategory)) {
			Object page = browser.waitExists(admLocPg, admRuleDetailPg);
			if (page instanceof AdmMgrRuleDetailPage) {
				admRuleDetailPg.clickSelectLocation();
				admLocPg.waitLoading();
			}
	
			// Select location
			admLocPg.searchLocation(ruleData.locationCategory, ruleData.location);
			admLocPg.clickSelectButton();
			admRuleDetailPg.waitLoading();
			
			selectedLocation = ruleData.location;
			selectedLocationCategory = ruleData.locationCategory;
		}
		
		// Add a new rule
		admRuleDetailPg.addNewRule(ruleData);
		admRuleDetailPg.waitLoading();

		// Set log info
		if (!admRuleDetailPg.isErrorMsgExist()) {
			//result should be: true&ID or false&ID
			String checkResult = this.getRuleScheduleID(ruleData.comments, this.ruleTypeID, ruleData.status);
			
			newAddValue = checkResult;
			logger.info("Check new rule Passed!!!!");
			this.result = "Successful, new rule ID is-->"+checkResult;			
			
		} else {
			this.result = "Error Msg=" + admRuleDetailPg.getErrorMsg();;
			throw new ErrorOnPageException(this.result);
		}

		// Go to search location page
//		admgr.goToSelectLocationPg();
		System.gc();
	}
	
	private String getRuleScheduleID(String dscr, String typeId, String status) {
		String contract = login.contract.split(" ")[0];
		String schema = TestProperty.getProperty(env + ".db.schema.prefix")+ contract;
		db.resetSchema(schema);
		
		String query = "SELECT ID FROM I_RULE_COND  WHERE RULE_ID=" +
		typeId +
				"  AND DSCR='" +
				dscr +
				"'  AND ACTIVE_IND=" + (status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS) ? 1 : 0) + " AND DELETE_IND = 0  ORDER BY ID DESC";
		logger.info("SQL-->"+query);
		List<String> result  = db.executeQuery(query, "ID");
		
		if(result.size()<1) {
			throw new ErrorOnDataException("Cannot find rule of which the description is-->"+dscr+", ruleTypeID is -->"+typeId);
		}
		
		logger.info("Total "+result.size()+" records found...Only save latest record...");
		return result.get(0);
		
	}
}
