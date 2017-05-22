package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlotteryschedule;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is verified edit ongoing lottery
 * @LinkSetUp: d_inv_new_lottery_program:id=360(LOTTERYNAME='on_going_with_one_participation')
 * @SPEC:[TC:062380] Edit Lottery Schedule - Applicable Season 
 * @Task#:Auto-2057
 * @author Phoebe Chen
 * @Date  March 26, 2015
 */
public class VerifyEditOngoingLottery extends InventoryManagerTestCase{
	private String facilityId;
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		
		lotterySchedule.id  = im.checkOngoingLottery(facilityId, "", lottery.name, schema, DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchedule.id);
		
		this.verifyApplicableSeasonNotEditable();
		
		im.logoutInvManager();
	}

	private void verifyApplicableSeasonNotEditable() {
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage .getInstance();
		if(!(lotteryScheduleDetailsPg.getApplicableSeasonElement().size()==1)){
			throw new ErrorOnPageException("Applicable season should not be editable!");
		}
		logger.info("It is correct that applicable season not editable!");
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "on going without participation";
		lottery.id = im.getLotteryId(schema, lottery.name);
		
		facilityId = "552903"; //"552832";
		lottery.facility = im.getFacilityName(facilityId, schema);
		lottery.productGroup = "Full attributes";
		lottery.products = "All";
	}
}
