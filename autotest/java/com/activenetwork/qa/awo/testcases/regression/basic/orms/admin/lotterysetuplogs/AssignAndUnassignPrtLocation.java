package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.lotterysetuplogs;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Assign and unassign a participation location for a Slip Lottery in Inventory Manager with NC contract and Goes to admin Manager and check the Inventory Audit Logs of the actions.
 * @Preconditions:
 * @SPEC: [TC:043048,TC:043049]
 * @Task#:AUTO-1345
 * 
 * @author Jasmine
 * @Date  Dec 10, 2012
 */
public class AssignAndUnassignPrtLocation extends AdminManagerTestCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIm = new LoginInfo();
	private AuditLogInfo  lotteryLogs = new AuditLogInfo();
	private Lottery lottery = new Lottery();
	private AdminMgrInventoryAuditLogsPage auditLogsPg = AdminMgrInventoryAuditLogsPage.getInstance();
	private String facilityId;
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		lottery.id = this.getLotteryIdByName(lottery.name);
		im.gotoLotteryDetailsPageFromLotterySearchPage(lottery);
		im.assignLocationToLottery(lottery,true);
		im.unassignLocationToLottery();
		im.logoutInvManager();
		
		lotteryLogs.searchValue = lottery.id;
		lotteryLogs.actionDetails = "Lottery ID: " + lottery.id+", "+"Lottery Name: "+ lottery.name +" Participating Location:"+facilityId+", "+"Location Name:"+lottery.facility+", "+"Product Groups: "+lottery.productCategory+", "+"Products: "+lottery.products;//DEFECT-40687 won't fix
		am.loginAdminMgr(login);
		am.gotoInventoryAuditLogsPage();
		//Verify assign audit log.
		auditLogsPg.searchLogs(lotteryLogs);
		auditLogsPg.verifyInvAuditLogs(lotteryLogs);
		
		//Verify unassign audit logs. 
		lotteryLogs.actionType = "Unassign";
	    lotteryLogs.action =lotteryLogs.actionType +" Participating Location From Lottery";
		auditLogsPg.searchLogs(lotteryLogs);
		auditLogsPg.verifyInvAuditLogs(lotteryLogs);
		am.logoutAdminMgr();
	}

	public void wrapParameters(Object[] param) {
		login.contract="NC Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator - Auto/North Carolina State Parks";
	     schema = DataBaseFunctions.getSchemaName("NC", env);
	     
	     loginIm.url = login.url;
	     loginIm.contract = login.contract;
	     loginIm.location = login.location;
	     loginIm.userName = TestProperty.getProperty("orms.im.user");
	     loginIm.password = TestProperty.getProperty("orms.im.pw");
	     
	     lottery.location = "Jones Lake State Park";
	     lottery.name = "AssignLocation";
	     lottery.revenueLocation =lottery.location;
	     lottery.facility = lottery.location;
	     lottery.productCategory = "All";
	     lottery.products="All";
	     facilityId = DataBaseFunctions.getFacilityID(lottery.location, schema);
	     lotteryLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(), "MMM d, yyyy");
	    
	     lotteryLogs.userName = loginIm.userName;
	     lotteryLogs.searchType = "Action Details";
	     lotteryLogs.stratDate = DateFunctions.getToday();
	     lotteryLogs.endDate = DateFunctions.getToday();
	     lotteryLogs.application = "Inventory Manager";
	     lotteryLogs.logArea = "Lottery Setup";
	     lotteryLogs.actionType = "Assign";
	     lotteryLogs.action =lotteryLogs.actionType +" Participating Location To Lottery";
	     lotteryLogs.affectedLocation =  login.location.split("/")[1];
	   
	}
	/**
	 * get lottery id by name.
	 * @param lotteryId
	 * @return
	 */
	public String getLotteryIdByName(String lotteryName){
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		lotterySearchPg.searchLotterByName(lottery.name);
		String id = lotterySearchPg.getLotteryIdByName(lottery.name);
		return id;
	}
}
