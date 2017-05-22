package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.entrance.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:PCR2530
 * @Preconditions:TC035844
 * @SPEC:
 * @Task#:Auto-893
 *
 * @author nding1
 * @Date  Feb 22, 2012
 */
public class VerifyAddAuditLogs extends InventoryManagerTestCase{

	private EntranceInfo entranceInfo = new EntranceInfo();
	private PermitTypeInformation permitTypeInfo = new PermitTypeInformation();
	private AuditLogInfo searchCriteria = new AuditLogInfo();
	private LoginInfo loginAm = new LoginInfo();
	private AdminManager am = AdminManager.getInstance();

	public void execute(){
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		// add new Entrance and make sure the new Entrance is added successfully.
		this.addNewEntrance();
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
		inventory.facilityName = "Boundary Waters Canoe Area Wilderness (Reservations)";// ID=72600

		// entranceInfo
		entranceInfo.entranceCode = "Verify"+DateFunctions.getCurrentTime();
		entranceInfo.entranceName = "Verify Update Restriction Window";
		entranceInfo.entranceType = "Entry Point";
		entranceInfo.description = "Verify Update Restriction Window for PCR2530.";
		entranceInfo.entryType = "Entrance";

		// permitTypeInfo
		permitTypeInfo.permitType = "Day Use Motor";
		permitTypeInfo.permitTypeID = "280877670";
		permitTypeInfo.maxGroupSize ="5";
		permitTypeInfo.maxWatercraft = "10";
		permitTypeInfo.issueStartDateType = "1 day prior to entry";
		permitTypeInfo.startDateTime = "6:00";
		permitTypeInfo.startAmPm = "AM";
		permitTypeInfo.issueEndDateType = "On Entry Day";
		permitTypeInfo.endDateTime = "9:00";
		permitTypeInfo.endAmPm = "PM";
		permitTypeInfo.updateResWindow = "1";
		permitTypeInfo.status = "Active";

		// login info for Admin Manager
		loginAm.url = AwoUtil.getOrmsURL(env);
		loginAm.userName = TestProperty.getProperty("orms.adm.user");
		loginAm.password = TestProperty.getProperty("orms.adm.pw");
		loginAm.contract = "NRRS Contract";
		loginAm.location = "Administrator/NRRS";

		// searchCriteria
		searchCriteria.searchType="Location";
		searchCriteria.searchValue=inventory.facilityName;
		searchCriteria.stratDate=DateFunctions.getDateAfterToday(-3);
		searchCriteria.endDate=DateFunctions.getDateAfterToday(1);
		searchCriteria.application="Inventory Manager";
		searchCriteria.logArea="Entrance Setup";
		searchCriteria.actionType="All";
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
		invAuditLogPage.searchLogs(searchCriteria);

		// get all logs from UI
		List<List<String>> logs = invAuditLogPage.getLogs();
		List<List<String>> actualLogs = new ArrayList<List<String>>();
		String value = "";

		// at least two logs in the page.
		if(logs.size()>=2){
			if(actualLogs.size()<2){
				for(int i=0; i<logs.size(); i++){
					List<String> log = logs.get(i);
					value = log.get(3);

					// get logs which are need to be verified.
					if(value.matches("Entrance ID: \\d+, Entrance Code: "+entranceInfo.entranceCode+", Entrance: "+entranceInfo.entranceName+"Verify Update Restriction Window.*")){
						actualLogs.add(log);
					}
				}
			} else {
				logger.error("The number of logs is not correct!Except number of logs is two!");
				throw new ErrorOnPageException("The number of logs is not correct!Except number of logs is two!");
			}
		} else {
			logger.error("The number of logs is not correct!At least two logs in the page!");
			throw new ErrorOnPageException("The number of logs is not correct!At least two logs in the page!");
		}

		for(int j=0;j<actualLogs.size();j++){
			List<String> actualLog = actualLogs.get(j);

			// verify whether action is correct or not
			value = actualLog.get(2);
			if(!"Add Entrance".equals(value) && !"Assign Permit Type To Entrance".equals(value)){
				logger.error("Expect Action:Add Entrance or Assign Permit Type To Entrance is not displayed in the page.Actual displayed is:"+value);
				throw new ErrorOnPageException("Expect Action:Add Entrance or Assign Permit Type To Entrance is not displayed in the page.Actual displayed is:"+value);
			}

			// get expect logs
			List<String> expectLog = this.parseExpectLogs(value);
			String expectValue = "";

			//  verify Date/Time
			value = actualLog.get(0).substring(0, 11).replaceAll(",", " ");
			expectValue = expectLog.get(0);
			if(DateFunctions.compareDates(value, expectValue) != 0){
				logger.error("Date/Time is not correct.Expect is:"+expectValue+", but displayed in the page is:"+value);
				throw new ErrorOnPageException("Date/Time is not correct.Expect is:"+expectValue+", but displayed in the page is:"+value);
			}

			// verify other fields
			for(int m=1;m<expectLog.size();m++){
				value = actualLog.get(m);
				expectValue = expectLog.get(m);

				// verify whether each field of each line is the same as expect or not.
				if(!value.matches(expectValue)){
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
	private List<String> parseExpectLogs(String action){
		List<String> log = new ArrayList<String>();

		log.add(DateFunctions.getToday());
		log.add(searchCriteria.logArea);
		log.add(action);
		log.add(this.parseActionDetails(action, permitTypeInfo.permitTypeID));
		log.add(inventory.facilityName);
		log.add(login.userName);
		log.add(searchCriteria.application.split(" ")[0]);

		return log;
	}

	/**
	 * Parse Action Details.
	 *
	 * @param type
	 * @param permitTypeID
	 */
	private String parseActionDetails(String type, String permitTypeID){
		String actionDetail = "";
		logger.info("Parse Action Details.");

		if("Add Entrance".equals(type)){
			actionDetail = "Entrance ID: "+"\\d+"+", Entrance Code: "+entranceInfo.entranceCode+", Entrance: "+entranceInfo.entranceName;
		} else if("Assign Permit Type To Entrance".equals(type)){
			actionDetail = "Entrance ID: "+"\\d+"+", Entrance Code: "+entranceInfo.entranceCode+", Entrance: "+entranceInfo.entranceName;
			actionDetail = actionDetail+" Entrance Permit Type ID: "+"\\d+"+", Permit Type ID: "+permitTypeID+
				", Permit Type: "+permitTypeInfo.permitType+", Attributes: MaxGroupSize: "+permitTypeInfo.maxGroupSize+", MaxNumOfWatercraft: "+permitTypeInfo.maxWatercraft+
				", Issue Start Date/Time: "+permitTypeInfo.issueStartDateType+" "+permitTypeInfo.startDateTime+permitTypeInfo.startAmPm.substring(0, 1)+
				", Issue End Date/Time: "+permitTypeInfo.issueEndDateType+" "+permitTypeInfo.endDateTime+permitTypeInfo.endAmPm.substring(0, 1)+
				"Update Restriction Window: "+permitTypeInfo.updateResWindow;
		}

		return actionDetail;
	}

	/**
	 * Add new Entrance and make sure the new Entrance is added successfully.
	 */
	private void addNewEntrance(){
		logger.info("Add new Entrance and make sure the new Entrance is added successfully.");
		im.gotoEntranceListPage("Entrance Set-up");
		Object page = im.addNewEntrance(entranceInfo, null, permitTypeInfo, false);
		if(page instanceof InvMgrEntranceListPage){
			logger.info("Add new Entrance successfully.");
		} else {
			logger.error("The new entrance has not been added successfully!");
			throw new ErrorOnPageException("The new entrance has not been added successfully!");
		}
	}
}
