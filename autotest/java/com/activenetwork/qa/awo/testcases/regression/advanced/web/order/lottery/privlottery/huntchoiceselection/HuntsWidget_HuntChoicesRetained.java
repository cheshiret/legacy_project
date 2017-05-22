package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Verify hunt widget retained or cleared when navigation to another pages
 * @Preconditions:
 * 1. The hunts "Web Lottery Hunt 001", "Web Lottery Hunt 010" exist.
 * 2. All the hunts have been assigned to the lottery "Lottery With Mult Hunts".
 * @SPEC: 
 * Widget - Hunt Choices retained / Cleared [TC:056292]
 * List Available Hunts - Action button (Add Choice / Choice Added) [TC:056238]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40
 * d_hf_add_hunt_location:id=90
 * d_hf_add_date_period:id=60
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=480,570
 * d_hf_add_hunt_license_year:id=510,600
 * d_hf_assign_priv_to_hunt:id=360,450
 * d_hf_add_hunt_quota:id=210
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * @Task#: Auto-1774
 * 
 * @author Lesley Wang
 * @Date  Feb 19, 2014
 */
public class HuntsWidget_HuntChoicesRetained extends HFSKWebTestCase{
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private String anotherLotteryDes, hunt1, hunt2;
	
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		
		// Add some hunts and navigate to lottery details page, then apply the same type, verify added hunts retained
		hf.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getDescription(), false);
		huntsPg.addHuntChoices(hunt1, hunt2);	
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsWidget(true, hunt1, hunt2);
		
		// Remove one hunt and navigate to lottery details page, then apply the same type, verify added hunts retained
		huntsPg.removeHuntChoice(hunt1);
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsWidget(true, hunt2);
		
		// Navigation to lottery details page and apply another type, verify added hunts cleared
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.applyLotteryAsGroupLeaderToAddHuntsPg();
		this.verifyHuntsWidget(false, new String[0]);
		
		// Add hunts and click My Account tab, then choose "Stay on this page" in the popup dialog, verify hunts retained
		huntsPg.addHuntChoices(hunt1);
		hf.gotoMyAccountPgFromHeaderBar(false);
		this.verifyHuntsWidget(true, hunt1);
		
		// click My Account tab, then choose "Leave this page" in the popup dialog; apply as the same type and verify hunts cleared
		hf.gotoMyAccountPgFromHeaderBar(true);
		hf.gotoAddHuntsPgAsGroupLeaderFromHeaderBar(cus, lottery.getDescription(), false);
		this.verifyHuntsWidget(false, new String[0]);
		
		// click My Account tab, then choose "Leave this page" in the popup dialog; apply as another type and verify hunts cleared
		hf.gotoMyAccountPgFromHeaderBar(true);
		hf.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getDescription(), false);
		this.verifyHuntsWidget(false, new String[0]);
		
		// Add hunts and click My Account tab, then choose "Leave this page" in the popup dialog; apply as the same type of another lottery, verify hunts cleared
		huntsPg.addHuntChoices(hunt1);
		hf.gotoMyAccountPgFromHeaderBar(true);
		hf.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, anotherLotteryDes, false);
		this.verifyHuntsWidget(false, new String[0]);
		
		// Add hunts and submit to shopping cart page, then continue shopping and apply the same type, verify hunts cleared
		hf.submitLotteryWithoutPointsAsIndividualToCart(cus, lottery.getDescription(), hunt1);
		hf.continueShoppingToLotteryCategoryListPg();
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsWidget(false, new String[0]);
		
		// Add hunts and submit to shopping cart page, then abandon the cart and apply the same type, verify hunts cleared
		huntsPg.addHuntChoices(hunt1);
		hf.submitLotteryHuntsToCart();
		hf.abandonCart();
		hf.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getDescription(), false);
		this.verifyHuntsWidget(false, new String[0]);
		
		hf.signOut();
	}
	
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// Lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		anotherLotteryDes = "Lottery for MinMax Choices";
		
		// Hunts Info
		hunt1 = "Web Lottery Hunt 001";
		hunt2 = "Web Lottery Hunt 010";
	}
	
	private void verifyHuntsWidget(boolean isOpened, String... huntDescs) {
		boolean result = true;
		int totalHunts = huntDescs.length;
		
		result &= MiscFunctions.compareResult("Hunt Widget Opened", isOpened, huntsPg.checkHuntWidgetOpened());
		result &= MiscFunctions.compareResult("Hunt Widget Closed", !isOpened, huntsPg.checkHuntWidgetClosed());
		
		huntsPg.openHuntWidget();
		result &= MiscFunctions.compareResult("Total added hunts", totalHunts, huntsPg.getNumOfAddedHunts());
		List<String> expHunts = new ArrayList<String>();
		for (int i = 0; i < totalHunts; i++) {
			expHunts.add(huntDescs[i]);
		}
		result &= MiscFunctions.compareListString("Added Hunts Description", expHunts, huntsPg.getHuntWidgetItemsDesc());
		
		if (!result) {
			throw new ErrorOnPageException("Hunts widget is displayed wrongly!");
		}
		logger.info("Successfully verify hunts widget!");
	}
}
