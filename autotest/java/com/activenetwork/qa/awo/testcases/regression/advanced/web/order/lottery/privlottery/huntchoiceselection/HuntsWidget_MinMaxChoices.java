package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Verify minimum and maximum choices when add hunts 
 * @Preconditions:
 * 1. "Lottery With Mult Hunts" setup as: Minimum choices: 1; Maximum choices: 5
 * 2. "Lottery For MinMax Choices" setup as: Minimum choices: 2; Maximum choices: 5
 * @SPEC: 
 * Widget - Minimum / Maximum choices [TC:056384]
 * List Available Hunts - Action button (Add Choice / Choice Added) [TC:056238]
 * @LinkSetUp: 
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40,50
 * d_hf_add_hunt_location:id=60,70,80,90
 * d_hf_add_date_period:id=60,70
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822,3882
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=480,490,500,510,520,530,540,550,560,570,580,590
 * d_hf_add_hunt_license_year:id=510,520,530,540,550,560,570,580,590,600,610,670,680,690,700,710,720
 * d_hf_assign_priv_to_hunt:id=360,370,380,390,400,410,420,430,440,450,460,470
 * d_hf_add_hunt_quota:id=210,270
 * d_hf_add_lottery_prd:id=200,250
 * d_hf_add_lottery_license_year:id=80,160
 * d_hf_assign_lottery_to_store:id=80,160
 * d_hf_add_lottery_quantity_control:id=80,160
 * d_hf_assi_hunts_to_lottery:id=90,220
 * @Task#: Auto-1774
 * 
 * @author Lesley Wang
 * @Date  Feb 19, 2014
 */
public class HuntsWidget_MinMaxChoices extends HFSKWebTestCase {
	private HFLotteryProduct lottery_DiffMinChoices = new HFLotteryProduct();
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private List<String> hunts;
	private String msg;
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		
		// Apply as individual without points - minimum=1 and maximum=5
		this.applyLotteryAndVerifyMinMaxChoices(false, lottery);
		
		// Submit as individual with points - minimum=1 and maximum=5
		this.applyLotteryAndVerifyMinMaxChoices(true, lottery);
		
		// Apply as individual without points - minimum=2 and maximum=5
		this.applyLotteryAndVerifyMinMaxChoices(false, lottery_DiffMinChoices);
		
		//Sara[20140404] 
		//Issue:Error message displays when add Points + Hunts Individual application
		//Solution from Lisha: Suggest we hold the web test cases related to this setup since I'm pretty sure there are some issues on this setup on web
//		// Submit as individual with points - minimum=2 and maximum=5
//		this.applyLotteryAndVerifyMinMaxChoices(true, lottery_DiffMinChoices);
		
		hf.signOut();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// Lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		lottery.setMinChoices("1");
		lottery.setMaxChoices("5");
		
		lottery_DiffMinChoices.setDescription("Lottery for MinMax Choices");
		lottery_DiffMinChoices.setMinChoices("2");
		lottery_DiffMinChoices.setMaxChoices("5");
		
		// Hunt description
		hunts = new ArrayList<String> ();
		hunts.add("Web Lottery Hunt 001");
		hunts.add("Web Lottery Hunt 006");
		hunts.add("Web Lottery Hunt 007");
		hunts.add("Web Lottery Hunt 008");
		hunts.add("Web Lottery Hunt 010");
		hunts.add("Web Lottery Hunt 011");
		
		msg = "Maximum Reached!";
	}
	
	private void applyLotteryAndVerifyMinMaxChoices(boolean isPurchasedPoints, HFLotteryProduct lottery) {
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		if (isPurchasedPoints) {
			hf.submitLotteryWithPointsAsIndividualToAddHuntsPg();
		} else {
			hf.applyLotteryAsIndividualToAddHuntsPg();
		}
		
		int min = Integer.valueOf(lottery.getMinChoices());
		int max = Integer.valueOf(lottery.getMaxChoices());
		if (isPurchasedPoints) {
			min -= 1;
			max -= 1;
		}
		
		// Add hunts less than min choices, verify submit button is disabled.
		for (int i = 0; i < min-1; i++) {
			huntsPg.addHuntChoices(hunts.get(i));
		}
		if (!isPurchasedPoints || min!=0) {//If purchase point and the minimum choice is 1, the submit button should be enabled 
			huntsPg.openHuntWidget();
			huntsPg.verifyHuntWidgetSubmitBtnEnabled(false); 
			if (huntsPg.checkHuntWidgetRemoveChoicesLinkExist()) {
				huntsPg.removeAllHuntChoicesFromHuntWidget();
			}
		}
		
		// Add hunts equal to min choices, verify submit button enabled; then remove one, verify disabled
		for (int i = 0; i < min; i++) {
			huntsPg.addHuntChoices(hunts.get(i));
		}
		huntsPg.verifyHuntWidgetSubmitBtnEnabled(true);
		if (!isPurchasedPoints || min!=0) {//If purchase point and the minimum choice is 1, the submit button should be enabled 
			huntsPg.removeHuntChoice(hunts.get(0));
			huntsPg.openHuntWidget();
			huntsPg.verifyHuntWidgetSubmitBtnEnabled(false);
		}
		if (huntsPg.checkHuntWidgetRemoveChoicesLinkExist()) {
			huntsPg.removeAllHuntChoicesFromHuntWidget();
		}
		
		// Add hunts greater than max choices, verify error message
		for (int i = 0; i < max; i++) {
			huntsPg.addHuntChoices(hunts.get(i));
		}
		huntsPg.clickAddChoice(hunts.get(max));
		huntsPg.waitLoading();
		huntsPg.verifyRemoveChoiceBtnExist(hunts.get(max), false);
		huntsPg.verifyHuntWidgetErrorMsg(msg);
		huntsPg.removeAllHuntChoicesFromHuntWidget();
		
		// Add hunts equal to min choices, then submit to shopping cart page
		for (int i = 0; i < min; i++) {
			huntsPg.addHuntChoices(hunts.get(i));
		}
		hf.submitLotteryHuntsToCart();
		hf.abandonCart();
	}
}
