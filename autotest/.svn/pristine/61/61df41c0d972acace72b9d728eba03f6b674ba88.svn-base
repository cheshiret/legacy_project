package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.list;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo.ListSubmissionRules;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Add a new list and verify audit log in Admin Manager.Create List - Log [TC:050998] 
 * @Preconditions:
 * @SPEC: Create List - Log [TC:050998] 
 * @Task#: Auto-1994
 * 
 * @author nding1
 * @Date  Jan 7, 2014
 */
public class VerifyAddListAuditLog extends InventoryManagerTestCase{
	private String facilityID;
	private ListInfo listInfo = new ListInfo();
	private LoginInfo loginAM = new LoginInfo();
	private AdminManager am = AdminManager.getInstance();
	private AuditLogInfo auditLog = new AuditLogInfo();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		listInfo.setListID(im.addList(listInfo));
		im.logoutInvManager();

		auditLog.searchValue = listInfo.getListID();
		auditLog.actionDetails = "List ID: "+listInfo.getListID()+", List Name: "+listInfo.getListName();
		
		am.loginAdminMgr(loginAM);
		am.gotoInventoryAuditLogsPage();
		
		AdminMgrInventoryAuditLogsPage invAuditLogPage = AdminMgrInventoryAuditLogsPage.getInstance();
		invAuditLogPage.searchLogs(auditLog);
		invAuditLogPage.verifyInventoryAuditLogsDetailInfo(auditLog);
		am.logoutAdminMgr();
		
		// clean up: close list
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();

		// close it
		im.closeList(listInfo.getListID(), "Not Available", "Close list - VerifyAddListAuditLog");
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		loginAM.url = login.url;
		loginAM.contract = login.contract;
		loginAM.location = login.location;
		loginAM.userName = login.userName;
		loginAM.password = login.password;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552859";// New River State Park
		String facilityName = im.getFacilityName(facilityID, schema);
		
		listInfo.setListName("VerifyAddListAuditLog"+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("2");
		listInfo.setHeaderMessage("Good moring, everyone~");
		listInfo.setListTermCon("Thank you for purchasing this list.");
		
		// must setup submission rule info!!
		ListSubmissionRules listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("8");
		listSubrules.setRule("Maximum Number of Entries per List");
		List<ListSubmissionRules> ruleList = new ArrayList<ListSubmissionRules>();
		ruleList.add(listSubrules);
		listInfo.setListSubrules(ruleList);

		// info for search audit log
		auditLog.searchType = "Action Details";
		auditLog.stratDate = DateFunctions.getToday();
		auditLog.endDate = DateFunctions.getToday();
		auditLog.logArea = "List";
		auditLog.actionType = "Add";

		auditLog.action = "Add List";
		auditLog.affectedLocation = facilityName;
		auditLog.userName = login.userName;
		auditLog.application = "Inventory";
		auditLog.dateTime = DateFunctions.getToday();
	}
}
