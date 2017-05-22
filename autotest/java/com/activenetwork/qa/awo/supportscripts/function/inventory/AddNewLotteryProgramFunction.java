package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Nov 22, 2012
 */
public class AddNewLotteryProgramFunction extends FunctionCase {
	
	private LoginInfo login = new LoginInfo();
	private Lottery lottery = new Lottery();
	private LotterySchedule lotterySchedule = new LotterySchedule();
	private InventoryManager im = InventoryManager.getInstance();
	private String lotteryScheduleId;
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		if (!lotterySearchPg.exists()) {
			if (!im.isLotterySetup()) {
				im.selectLotterySetup();
			} else {
				im.clickLotteryTab();
			}
		}

		if (im.checkLotteryExist(lottery.name)) {
			im.gotoLotteryDetailsPageFromHomePage(lottery);
			lottery.id = im.editLottery(lottery);

			im.unassignLocationToLottery();

			im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
			im.deactiveAllLotterSchedule();

			im.clickLotteryTab();
			im.changeLotteryStatus(false, lottery);
		} else {
			lottery.id = im.addNewLottery(lottery);
			im.changeLotteryStatus(false, lottery);
		}

		if (lottery.isAssignParticipation || lottery.isAssignLotterySchedule) {
			im.gotoLotteryDetailsPageFromHomePage(lottery);
			if (lottery.isAssignParticipation) {
				im.assignLocationToLottery(lottery);
			}
			if (lottery.isAssignLotterySchedule) {
				lotteryScheduleId = im.addLotterySchedule(lotterySchedule, true,lottery.productCategory);
				if(StringUtil.isEmpty(lotteryScheduleId)||!lotteryScheduleId.matches("\\d+")){
			  		throw new ErrorOnPageException("Create lottery schedule Fail.");
			  	}
			}
		}
		if(!StringUtil.isEmpty(lottery.id)){
			if(!StringUtil.isEmpty(lotteryScheduleId)){
				newAddValue = lottery.id + ","+lotteryScheduleId;
			}else{
				newAddValue = lottery.id;
			}
			
		}
	
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		lottery = (Lottery)param[1];
		lotterySchedule = (LotterySchedule)param[2];
	}
}
