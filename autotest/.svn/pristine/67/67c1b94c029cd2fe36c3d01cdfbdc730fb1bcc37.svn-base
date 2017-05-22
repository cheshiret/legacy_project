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
 * Add a new list, edit it.
 * Verify audit log in Admin Manager.
 * @Preconditions:
 * @SPEC: Edit List - Log [TC:051067] 
 * @Task#: Auto-1995
 * 
 * @author nding1
 * @Date  Jan 8, 2014
 */
public class VerifyEditListAuditLog extends InventoryManagerTestCase{
	private String facilityID;
	private ListInfo listInfo = new ListInfo();
	private ListInfo newList = new ListInfo();
	private LoginInfo loginAM = new LoginInfo();
	private AdminManager am = AdminManager.getInstance();
	private AuditLogInfo auditLog = new AuditLogInfo();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoListSearchPageFromTopMenu();
		listInfo.setListID(im.addList(listInfo));
		newList.setListID(listInfo.getListID());
		im.editList(newList);
		im.logoutInvManager();

		auditLog.searchValue = listInfo.getListID();
		auditLog.actionDetails = "List ID: "+listInfo.getListID()+", List Name: "+newList.getListName()+
				" List Information - Number of Choices:"+listInfo.getNumOfChoice()+" --> "+newList.getNumOfChoice()+
//				" List Information - List Name:"+listInfo.getListName()+" --> "+newList.getListName()+
//				" List Submission Rules:Maximum Number of Entries per List: "+listInfo.getListSubrules().get(0).getMaxNum()+" --> Maximum Number of Entries per List: "+newList.getListSubrules().get(0).getMaxNum()+
				" Messaging - Header Message:"+listInfo.getHeaderMessage()+" --> "+newList.getHeaderMessage()+
				" Messaging - List Term and Conditions:"+listInfo.getListTermCon()+" --> "+newList.getListTermCon() + 
				" List Submission Rules:Maximum Number of Entries per List: "+listInfo.getListSubrules().get(0).getMaxNum()+" --> Maximum Number of Entries per List: "+newList.getListSubrules().get(0).getMaxNum()+
				" List Information - List Name:"+listInfo.getListName()+" --> "+newList.getListName();
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
		im.closeList(listInfo.getListID(), "Not Available", "Close list - VerifyEditListInfoAuditLog");
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
		
		listInfo.setListName("VerifyEditListAuditLog"+DateFunctions.getCurrentTime());
		listInfo.setNumOfChoice("2");
		listInfo.setHeaderMessage("Happy New Year!");
		listInfo.setListTermCon("Let life be beautiful like summer flowers and death like autumn leaves.");
		
		ListSubmissionRules listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("8");
		listSubrules.setRule("Maximum Number of Entries per List");
		List<ListSubmissionRules> ruleList = new ArrayList<ListSubmissionRules>();
		ruleList.add(listSubrules);
		listInfo.setListSubrules(ruleList);

		// new list info
		newList.setListName("New-VerifyEditListAuditLog"+DateFunctions.getCurrentTime());
		newList.setNumOfChoice("7");
		newList.setHeaderMessage("Every day may not be good, but there's something good in every day.");
		newList.setListTermCon("Keep calm and carry on.");
		
		listSubrules = new ListSubmissionRules();
		listSubrules.setMaxNum("997");
		listSubrules.setRule("Maximum Number of Entries per List");
		ruleList = new ArrayList<ListSubmissionRules>();
		ruleList.add(listSubrules);
		newList.setListSubrules(ruleList);
		
		// info for search audit log
		auditLog.searchType = "Action Details";
		auditLog.stratDate = DateFunctions.getToday();
		auditLog.endDate = DateFunctions.getToday();
		auditLog.logArea = "List";
		auditLog.actionType = "Update";

		auditLog.action = "Update List";
		auditLog.affectedLocation = facilityName;
		auditLog.userName = login.userName;
		auditLog.application = "Inventory";
		auditLog.dateTime = DateFunctions.getToday();
	}
}
