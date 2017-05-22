package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.manualhuntselection;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABLotteryApplicationTestCase;
/**
 * 
 * @Description:(P) Verify (For individual type)
 * 1.In hunt selection page, the entered will be retained if click Continue button, others will not;
 * 2.In Shopping cart page, the hunts should be matched the selected;
 * 3.In account overview, order history, and lottery page, lottery and hunt info.
 * @Preconditions:
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1600
 * D_HF_ADD_PRIVILEGE_PRD:id=3170  -ABT ABLotteryAppPriv 
 * D_HF_ADD_PRICING:id=4612,4622
 * D_HF_ASSI_PRI_TO_STORE:id=2390
 * D_HF_ADD_QTY_CONTROL:id=2370
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3300
 * D_HF_ADD_HUNT:id=1110,1120,1130,1140,1150,1160,1170
 * -ABH, AB Walleye Hunt, Individual;Group,DisplayOrder(3)
 * -bAB, bAB Walleye Hunt, Individual;Group, DisplayOrder(1)
 * -3AB, 3 Walleye Hunt, Individual;Group, DisplayOrder(empty) 
 * -IAB, Individual Walleye Hunt, Individual, DisplayOrder(empty) 
 * -GAB, Group Walleye Hunt, Group, DisplayOrder(empty) 
 * -cAB, cAB Walleye Hunt, Individual, DisplayOrder(empty)
 * -dAB, dAB Walleye Hunt, Individual, DisplayOrder(empty)
 * D_HF_ADD_HUNT_QUOTA:id=340
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1190,1200,1210,1220,1230,1240,1250
 * D_HF_ASSIGN_PRIV_TO_HUNT:id=830,840,850,860,870,880,890
 * D_HF_ADD_LOTTERY_PRD:id=560
 * D_HF_ADD_LOTTERY_LICENSE_YEAR:id=440
 * D_HF_ASSIGN_LOTTERY_TO_STORE:id=430
 * D_HF_ADD_LOTTERY_QUANTITY_CONT:id=440
 * D_HF_ASSI_HUNTS_TO_LOTTERY:id=630,640,650,660,670.680
 * D_HF_ADD_LOTTERY_PARAMETER:id=20
 * @SPEC:Manual Hunt selection - Lottery application submission flow [TC:121559] 
 * @Task#:AUTO-2129
 * 
 * @author SWang
 * @Date  Jun 6, 2014
 */
public class SubmissionFlowForIndividual extends HFABLotteryApplicationTestCase{

	@Override
	public void execute() {
		hfab.signIn(url, cus.email, cus.password);
		hfab.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getLegalName());

		//Setup hunt choices without Continue button, the entered will be not retained
		huntsPg.setupHuntChoices(hunt1,hunt2,hunt3,hunt4, hunt5);
		hfab.gotoLotteryDetailsPgFromAddHuntsPg();
		hfab.applyLotteryAsIndividualToAddHuntsPg();
		huntsPg.verifyHuntChoices(null,null,null,null, null);

		//Setup hunt choices with Continue button, the entered will be retained
		huntsPg.setupHuntChoicesWithContinueBtn(hunt1,hunt2,hunt3,hunt4,null);
		hfab.gotoLotteryDetailsPgFromAddHuntsPg();
		hfab.applyLotteryAsIndividualToAddHuntsPg();
		huntsPg.verifyHuntChoices(hunt1,hunt2,hunt3,hunt4, null);

		//Verify selected hunts in shopping cart page
		hfab.chooseLotteryHuntsToCart(hunt1,hunt2,hunt3,null, null);
		shoppingCartPg.verifyLotteryHuntChoices(lottery.getLegalName(), hunt1.getHuntCode(), hunt2.getHuntCode(), hunt3.getHuntCode());
		lotteryOrderNum = hfab.checkOutCart(pay).split(" ")[0].trim();

		//Verify lottery and hunt info in account overview, order history, and lottery page
		generateLotteryAppAndItsAttrs();
		hfab.gotoMyAccountPgFromHeaderBar();
		accountOverviewPg.verifyRecentLotteryAppAttrs(lotteryOrderNum, lotteryDetails_accountOverview, huntInfo);
		
		hfab.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyLotteryAppHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory, huntInfo);

		hfab.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp, huntInfo);

		hfab.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.custNum = hfab.getCustomerNumByEmail("hfab_00012@webhftest.com", schema);
		cus.email = cus.custNum;
		cus.residencyStatus = RESID_STATUS_ALBERTA;

		lottery.setDescription("AB Walleye Lottery");
		lottery.setLegalName("AB Walleye Lottery Legal");
		lottery.setLicenseYear(hfab.getFiscalYear(schema));
		lottery.setMaxChoices("5");

		// Hunts Info
		hunt1 = new HuntInfo();
		hunt1.setDescription("bAB Walleye Hunt");
		hunt1.setHuntCode("bAB");
		hunt1.setSpecie("Walleye");

		hunt2 = new HuntInfo();
		hunt2.setDescription("AB Walleye Hunt");
		hunt2.setHuntCode("ABH");
		hunt2.setSpecie("Walleye");
		
		hunt3 = new HuntInfo();
		hunt3.setDescription("3 Walleye Hunt");
		hunt3.setHuntCode("3AB");
		hunt3.setSpecie("Walleye");
		
		hunt4 = new HuntInfo();
		hunt4.setDescription("Individual Walleye Hunt");
		hunt4.setHuntCode("IAB");
		hunt4.setSpecie("Walleye");
		
		huntInfo = initializeEnteredLotteryHuntAttris(hunt1,hunt2,hunt3);
	}

	public void generateLotteryAppAndItsAttrs(){
		lotteryDetails_lotteryApp = lottery.getLegalName()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+ENTERED_STATUS+".*Show Entered Draws.*";
		lotteryDetails_accountOverview =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getLegalName()+" \\("+lottery.getLicenseYear()+"\\) Status: "+ENTERED_STATUS+".*Show Entered Draws.*";
		lotteryDetails_orderHistory =lotteryDetails_accountOverview;
	}
}
