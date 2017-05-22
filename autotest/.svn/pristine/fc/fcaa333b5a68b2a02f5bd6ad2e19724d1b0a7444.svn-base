package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.entrance.edit;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:PCR2530
 * @Preconditions:TC035843
 * @SPEC:
 * @Task#:Auto-893
 * 
 * @author nding1
 * @Date  Feb 22, 2012
 */
public class VerifyEditAuditLogs extends InventoryManagerTestCase{

	private EntranceInfo entranceInfo = new EntranceInfo();
	private PermitTypeInformation permitTypeInfo = new PermitTypeInformation();
	private AuditLogInfo searchCriteriaForAuditLog = new AuditLogInfo();
	private LoginInfo loginAm = new LoginInfo();
	private AdminManager am = AdminManager.getInstance();
	private String oldValue = "";
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		
		// go to Entrance Detail Page
		im.gotoEntranceListPage("Entrance Set-up");
		im.addNewEntrance(entranceInfo, null, permitTypeInfo, false);
		
		// add new Entrance and make sure the new Entrance is added successfully.
		im.editEntrance(entranceInfo, null, permitTypeInfo, false);
		
		// edit Entrance Info and make sure the edit successfully.
		oldValue=permitTypeInfo.updateResWindow;
		permitTypeInfo.updateResWindow = "2";// 1 -> 2
		im.editEntrance(entranceInfo, null, permitTypeInfo, false);
		im.logoutInvManager();

		// go to Admin Manager to verify Audit log.
		am.loginAdminManager(loginAm, true);
		this.verifyAuditLog();
		am.logoutAdminMgr();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		inventory.facilityName = am.getFacilityName("72600", schema);

		// login info for Admin Manager
		loginAm.url = AwoUtil.getOrmsURL(env);
		loginAm.userName = TestProperty.getProperty("orms.adm.user");
		loginAm.password = TestProperty.getProperty("orms.adm.pw");
		loginAm.contract = "NRRS Contract";
		loginAm.location = "Administrator/NRRS";
		
		// entranceInfo
		entranceInfo.entranceCode = "VerifyEditAuditLogs"+DateFunctions.getCurrentTime();
		entranceInfo.entranceName = "VerifyEditAuditLogs"+DateFunctions.getCurrentTime();
		entranceInfo.entranceType = "Entry Point";
		entranceInfo.description = "Verify Update Restriction Window for PCR2530.";
		entranceInfo.entryType = "Entrance";

		//  searchCriteria For Entrance
		entranceInfo.searchBy = "Entrance Code";
		entranceInfo.searchValue = entranceInfo.entranceCode;
		entranceInfo.status = "Inactive";
		
		// permitTypeInfo
		permitTypeInfo.permitType = "Day Use Motor";
		permitTypeInfo.permitTypeID = "280877670";
		permitTypeInfo.maxGroupSize ="5";
		permitTypeInfo.maxWatercraft = "10";
		permitTypeInfo.issueStartDateType = "On Entry Day";
		permitTypeInfo.startDateTime = "6:00";
		permitTypeInfo.startAmPm = "AM";
		permitTypeInfo.issueEndDateType = "On Entry Day";
		permitTypeInfo.endDateTime = "9:00";
		permitTypeInfo.endAmPm = "PM";
		permitTypeInfo.updateResWindow = "1";
		permitTypeInfo.status = "Active";
		
		// searchCriteria For Audit Log
		searchCriteriaForAuditLog.searchType="Location";
		searchCriteriaForAuditLog.searchValue=inventory.facilityName;
		searchCriteriaForAuditLog.stratDate=DateFunctions.getDateAfterToday(-3);
		searchCriteriaForAuditLog.endDate=DateFunctions.getDateAfterToday(1);
		searchCriteriaForAuditLog.application="Inventory Manager";
		searchCriteriaForAuditLog.logArea="Entrance Setup";
		searchCriteriaForAuditLog.actionType="All";
	}
	
	/**
	 * Verify Inventory Audit Logs in Admin Manager.
	 */
	private void verifyAuditLog(){
		logger.info("Verify Inventory Audit Logs.");
		
		// go to Inventory Audit Logs page
		am.gotoAuditLogsPage("Inventory Audit Logs");

		// set up the search criteria and get the logs.
		AdminMgrInventoryAuditLogsPage invAuditLogPage = AdminMgrInventoryAuditLogsPage.getInstance();
		invAuditLogPage.searchLogs(searchCriteriaForAuditLog);
		
		// get all logs from UI
		List<List<String>> logs = invAuditLogPage.getLogs();
		List<List<String>> actualLogs = new ArrayList<List<String>>();
		String value = "";
		
		// at least one logs in the page.
		if(logs.size() > 0){

			for(int i=0; i<logs.size(); i++){
				List<String> log = logs.get(i);
				value = log.get(2);
				
				// get logs which are need to be verified.
				if(value.equals("Update Entrance/Permit Type Attribute")){
					if(actualLogs.size() < 1){
						actualLogs.add(log);
					}
				}
			}
		} else {
			logger.error("The number of logs is not correct!At least one logs in the page!");
			throw new ErrorOnPageException("The number of logs is not correct!At least one logs in the page!");
		}
		
		for(int j=0;j<actualLogs.size();j++){
			List<String> actualLog = actualLogs.get(j);
			
			// verify whether action is correct or not
			value = actualLog.get(2);
			if(!"Update Entrance/Permit Type Attribute".equals(value)){
				logger.error("Expect Action:Update Entrance/Permit Type Attribute is not displayed in the page.Actual displayed is:"+value);
				throw new ErrorOnPageException("Expect Action:Update Entrance/Permit Type Attribute is not displayed in the page.Actual displayed is:"+value);
			}
			
			// get expect logs
			List<String> expectLog = this.parseExpectLogs();
			String expectValue = "";
			
			//  verify Date/Time
			logger.info("verify Data/Time.");
			value = actualLog.get(0).replace(", ", ",").substring(0, 11).replaceAll(",", " ");
			expectValue = expectLog.get(0);
			if(DateFunctions.compareDates(value, expectValue) != 0){
				logger.error("Date/Time is not correct.Expect is:"+expectValue+", but displayed in the page is:"+value);
				throw new ErrorOnPageException("Date/Time is not correct.Expect is:"+expectValue+", but displayed in the page is:"+value);
			}
			
			// verify other fields
			for(int m=1;m<expectLog.size();m++){
				value = actualLog.get(m);
				expectValue = expectLog.get(m);
				logger.info("Verify other fields:"+value);
				
				// verify whether each field of each line is the same as expect or not.
				if(!value.matches(expectValue.replace("(", "\\(").replace(")", "\\)"))) {
					logger.error("The field is not correct.Expect is:"+expectValue+", but displayed in the page is:"+value);
					throw new ErrorOnPageException("The field is not correct.Expect is:"+expectValue+", but displayed in the page is:"+value);
				}
			}
		}
	}

	/**
	 * Prepare except logs to verify.
	 * 
	 * @return Logs
	 */
	private List<String> parseExpectLogs(){
		logger.info("Prepare except logs to verify.");
		List<String> log = new ArrayList<String>();

		log.add(DateFunctions.getToday());
		log.add(searchCriteriaForAuditLog.logArea);
		log.add("Update Entrance/Permit Type Attribute");
		log.add(this.parseActionDetails());
		log.add(inventory.facilityName);
		log.add(login.userName);
		log.add(searchCriteriaForAuditLog.application.split(" ")[0]);

		return log;
	}

	/**
	 * Parse Action Details.
	 */
	private String parseActionDetails(){
		String actionDetail = "";
		logger.info("Parse Action Details.");
		actionDetail = "Entrance ID: "+"\\d+"+", Entrance Code: "+entranceInfo.entranceCode+", Entrance: "+entranceInfo.entranceName+
			" Update Restriction Window:?"+oldValue+" --> "+permitTypeInfo.updateResWindow;
		//DEFECT-40687 won't fix
		return actionDetail;
	}
}
