package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryAssignNewParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is to verify assign a participation location to marina lottery(slip lottery product), and the lottery is not on-going
 * the steps as follow:
 *               1.Assign a participation to lottery who is on-going and has no participation,check the error message
 *               2.Assign a participation to lottery who is on-going and has one participation,check the error message
 * @Preconditions:
 * 1. Make sure the following two lottery has been setup:
 *        on going with one participation
 *        on_going_with_one_participation // this one has been update to "LotteryForDeleteDock"
 * @LinkSetUp:  d_inv_new_lottery_program:id=350(LOTTERYNAME='on going without participation')
 *            //it is conflict with other lottery so use others  d_inv_new_lottery_program:id=360(LOTTERYNAME='on_going_with_one_participation')
 				d_inv_new_lottery_program:id=380(LOTTERYNAME='LotteryForDeleteDock')
 * @SPEC: TC:042119
 * @Task#: Auto-1342
 * @author Phoebe Chen
 * @Date  Dec 24, 2012
 */
public class AssignParticipation_LotteryOnGoingValidation extends InventoryManagerTestCase{
	private String errorMsg ="";
	private Lottery lottery1 = new Lottery();
	LotteryParticipationPage lotteryPaticipationPg = LotteryParticipationPage.getInstance();
	LotteryAssignNewParticipationPage lotteryAssignNewParticiptionPg = LotteryAssignNewParticipationPage
			.getInstance();
	String facilityId;
	@Override
	public void execute() {
		//login into the inventory manager
		im.loginInventoryManager(login);
		
		im.checkOngoingLottery(facilityId, "", lottery.name, schema, DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		
		im.checkOngoingLottery(facilityId, "", lottery1.name, schema, DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		
		//Go to the lottery details page
		im.gotoLotteryDetailsPageFromHomePage(lottery);
		
		//Assign the location to lottery who has no Participation
		im.assignLocationToLottery(lottery);
		//Verify the assign
		this.verifyErrorMessage();
		
		gotoLotterySearchPageFromLotteryAssignParticipationPage();
		
		//Assign location to lottery who has one participation location
		im.gotoLotteryDetailPageFromSearchPage(lottery1);
		gotoLotteryParticipationPageFromDetailPage();
		//Verify the assign
		this.verifyAssignNewButtonDisabled();
		
		im.logoutInvManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "on going without participation";
		lottery.id = im.getLotteryId(schema, lottery.name);
		
		facilityId = "552903"; //"552832";
		lottery.facility = im.getFacilityName(facilityId, schema);//"Jordan Lake State Rec Area"//old one--"Jones Lake State Park";
		lottery.productGroup = "Full attributes";
		lottery.products = "All";
		errorMsg = "The participation in Lottery " + lottery.id + " - " + lottery.name + " cannot be modified because a Lottery Run is currently on-going.";
		
		lottery1.name = "LotteryForDeleteDock";//"on_going_with_one_participation";
		lottery1.facility = lottery.facility;
		lottery1.id = im.getLotteryId(schema, lottery1.name);
	}

	/**
	 * Go to lottery search/list page from lottery participation page
	 */
	public void gotoLotterySearchPageFromLotteryAssignParticipationPage(){
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		lotteryAssignNewParticiptionPg.clickLotteries();
		ajax.waitLoading();
		lotterySearchPg.waitLoading();
	}
	
	public void gotoLotteryParticipationPageFromDetailPage(){
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		lotteryDetailsPg.clickLotteryParticiption();
		lotteryPaticipationPg.waitLoading();
	}
	
	private void verifyErrorMessage() {
		String errorMessage = lotteryAssignNewParticiptionPg.getErrorMessage();
		if(!errorMsg.equalsIgnoreCase(errorMessage)){
			throw new ErrorOnPageException("The error message:"+errorMsg+" is expected, but now it is:" + errorMessage);
		}
	}

	private void verifyAssignNewButtonDisabled() {
		if(lotteryPaticipationPg.isAssignNewLinkEnabled()){
			throw new ErrorOnPageException("The assign new link is enabled for the on-going lottery with one participation!");
		}
	}
}
