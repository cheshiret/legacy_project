package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.lotterysetuplogs;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
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
 * @Description:Activate and deactivate an inactive Slip Lottery in Inventory Manager with NC contract and Goes to admin Manager and check the Inventory Audit Logs of the actions.
 * @Preconditions:
 * @SPEC: [TC:043046,TC:043047]
 * @Task#:AUTO-1345
 * 
 * @author Jasmine
 * @Date  Dec 10, 2012
 */
public class ActiveAndDeactiveLotteryPrd extends AdminManagerTestCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIm = new LoginInfo();
	private AuditLogInfo  lotteryLogs = new AuditLogInfo();
	private Lottery lottery = new Lottery();
	private AdminMgrInventoryAuditLogsPage auditLogsPg = AdminMgrInventoryAuditLogsPage.getInstance();
	private LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
	public void execute() {
		im.loginInventoryManager(login);
		lottery.id = im.addNewLottery(lottery);
		lotterySearchPg.activateLottery(lottery.id);
		lotterySearchPg.deactiveLottery(lottery.id);
		im.logoutInvManager();
		
		lotteryLogs.actionDetails = "Lottery ID: " + lottery.id+", "+"Lottery Name: "+ lottery.name;
		am.loginAdminMgr(login);
		am.gotoInventoryAuditLogsPage();
		//Verify activate lottery.
		auditLogsPg.searchLogs(lotteryLogs);
		auditLogsPg.verifyInvAuditLogs(lotteryLogs);
		// Verify deactivate lottery.
		lotteryLogs.actionType = "Deactivate";
		lotteryLogs.action = lotteryLogs.actionType + " Lottery";
		auditLogsPg.searchLogs(lotteryLogs);
		auditLogsPg.verifyInvAuditLogs(lotteryLogs);
		am.logoutAdminMgr();
	}

	@Override
	public void wrapParameters(Object[] param) {
		 login.contract="NC Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator - Auto/North Carolina State Parks";
	     schema = DataBaseFunctions.getSchemaName("MS", env);
	     
	     loginIm.url = login.url;
	     loginIm.contract = login.contract;
	     loginIm.location = login.location;
	     loginIm.userName = TestProperty.getProperty("orms.im.user");
	     loginIm.password = TestProperty.getProperty("orms.im.pw");
	     
	     LotteryPreferenceAttribute preferenceAttribute1 = new LotteryPreferenceAttribute();
	     LotteryPreferenceAttribute preferenceAttribute2 = new LotteryPreferenceAttribute();
	     preferenceAttribute1.label = "Boat Category";
	     preferenceAttribute1.displayOrder = "1";
	     preferenceAttribute2.label = "Dock";//'Slip Type' changed to 'Dock'
	     preferenceAttribute2.entryRequired = "Per Preference";//PCR 4027
	     preferenceAttribute2.displayOrder = "2";
	     lottery.productCategory = "Slip";
	     lottery.description = "AutoTest";
	     lottery.location = "Jones Lake State Park";
	     lottery.locationCategory = "Park";
	     lottery.name = "Jas"+DataBaseFunctions.getEmailSequence();
	     lottery.revenueLocation = "Jones Lake State Park";
	     lottery.attributes.add(preferenceAttribute1);
	     lottery.attributes.add(preferenceAttribute2);
	     
	     lotteryLogs.userName = loginIm.userName;
	     lotteryLogs.searchType = "Action Details";
	     lotteryLogs.searchValue = lottery.name;
	     lotteryLogs.stratDate = DateFunctions.getToday();
	     lotteryLogs.endDate = DateFunctions.getToday();
	     lotteryLogs.application = "Inventory Manager";
	     lotteryLogs.logArea = "Lottery Setup";
	     lotteryLogs.actionType = "Activate";
	     lotteryLogs.action = lotteryLogs.actionType + " Lottery";
	     lotteryLogs.dateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	     lotteryLogs.affectedLocation = login.location.split("/")[1].trim();
	     lotteryLogs.userName = login.userName;
	}

}
