package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.lotterysetuplogs;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:Update the details of a Slip Lottery Schedule in Inventory Manager with NC contract and Goes to Admin Manager and check the Inventory Audit Logs of the actions.
 * @Preconditions:
 * @SPEC: [TC:043042]
 * @Task#:AUTO-1345
 * 
 * @author Jasmine
 * @Date  Dec 10, 2012
 */
public class UpdateLotterySchdl extends AdminManagerTestCase{

	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIm = new LoginInfo();
	private AuditLogInfo  lotteryLogs = new AuditLogInfo();
	private Lottery lottery = new Lottery();
	private LotterySchedule lotterySchd = new LotterySchedule();
	private AdminMgrInventoryAuditLogsPage auditLogsPg = AdminMgrInventoryAuditLogsPage.getInstance();
	private String oldFreezeDuration;
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);
		lotterySchd.id = im.addInactiveLotterySchedule(lotterySchd, "Slip");
		lotterySchd.freezeDuration = "2";
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchd.id);
		im.editLotterySchedule(lotterySchd, "Slip");
		im.logoutInvManager();
		
		lotteryLogs.searchValue = lotterySchd.id;
		lotteryLogs.actionDetails = "Lottery Schedule ID: " + lotterySchd.id+", "+"Lottery Name: "+ lottery.name + " Freeze Duration:"+oldFreezeDuration+" --> "+lotterySchd.freezeDuration;
		am.loginAdminMgr(login);
		am.gotoInventoryAuditLogsPage();
		auditLogsPg.searchLogs(lotteryLogs);
		auditLogsPg.verifyInvAuditLogs(lotteryLogs);
		am.logoutAdminMgr();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);

		loginIm.url = login.url;
		loginIm.contract = login.contract;
		loginIm.location = login.location;
		loginIm.userName = TestProperty.getProperty("orms.im.user");
		loginIm.password = TestProperty.getProperty("orms.im.pw");

		lottery.name = "LotteryForAuditLog";

		lotteryLogs.userName = loginIm.userName;
		lotteryLogs.searchType = "Action Details";
		lotteryLogs.searchValue = lottery.name;
		lotteryLogs.stratDate = DateFunctions.getToday();
		lotteryLogs.endDate = DateFunctions.getToday();
		lotteryLogs.application = "Inventory Manager";
		lotteryLogs.logArea = "Lottery Setup";
		lotteryLogs.actionType = "Update";
		lotteryLogs.action = lotteryLogs.actionType + " Lottery Schedule Details";
		lottery.productCategory = "Slip";
		lotteryLogs.affectedLocation = "North Carolina State Parks";
		lotteryLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(), "MMM d, yyyy");

		lotterySchd.appStartDate = DateFunctions.getDateAfterToday(1);
		lotterySchd.appEndDate = DateFunctions.getDateAfterToday(2);
		lotterySchd.field = true;
		oldFreezeDuration ="1";
		lotterySchd.executeDate = DateFunctions.getDateAfterToday(3);
		lotterySchd.invStartDate = DateFunctions.getDateAfterToday(25);
		lotterySchd.invEndDate = DateFunctions.getDateAfterToday(30);
		lotterySchd.applicableSeason="FutureSeason (01/01/2022 - 01/31/2022)";
		lotterySchd.freezeDuration = oldFreezeDuration;
		lotterySchd.customerAcceptanceDeadline = "2";
		lotterySchd.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";
	}

}
