package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlottery;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.AwardRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: This case is used to verify edit lottery program for slip  
 *               check point:1. Modify Maximum Number of one Lottery Award Rule to empty.
 *                           2. Modify Maximum Number of one Lottery Award  Rule same as another Lottery Application Submission Rule.
 *                           3. Add one duplicate Lottery Award  Rules with same rule type and different Maximum Number to one existing rule.
 * @Preconditions: 
 * @SPEC: TC:042106
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 27, 2012
 */
public class VerifyLotteryAwardRulesValidation extends InventoryManagerTestCase {
	String error;

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// add new lottery
		lottery.id = im.addNewLottery(lottery);

		// Modify Maximum Number of one Lottery Award Rule to empty. 
		lottery.awardRules = new ArrayList<AwardRulePara>();
		AwardRulePara rule1 = lottery.new AwardRulePara();
		rule1.ruleName = "Maximum Number of Reservations Awarded per Primary Occupant";
		rule1.maxNumber = "";
		lottery.awardRules.add(rule1);
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		String message = this.editLottery(lottery);
		this.verifyErrorMessage(error, message);

		// Modify Maximum Number of one Lottery Award  Rule same as another Lottery Application Submission Rule.
		error = "Duplicate applicable Lottery Award Rules have been specified. Duplicates are not allowed.";
		lottery.awardRules.get(0).maxNumber = "1";
		AwardRulePara rule2 = lottery.new AwardRulePara();
		rule2.ruleName = "Maximum Number of Reservations Awarded per Primary Occupant";
		rule2.maxNumber = "1";
		lottery.awardRules.add(rule2);
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		message = this.editLottery(lottery);
		this.verifyErrorMessage(error, message);

		// Add one duplicate Lottery Award  Rules with same rule type and different Maximum Number to one existing rule.
		lottery.awardRules.get(0).maxNumber = "2";
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		message = this.editLottery(lottery);
		this.verifyErrorMessage(error, message);

		// log out
		im.logoutInvManager();

	}

	private void verifyErrorMessage(String error, String message) {
		if (!error.equals(message)) {
			throw new ErrorOnPageException("Error Message:", error, message);
		}
	}

	private String editLottery(Lottery lottery) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		logger.info("Edit existing Lottery.");

		lotteryDetailsPg.updateLotteryDetail(lottery, null, null);
		lotteryDetailsPg.clickApply();
		ajax.waitLoading();
		lotteryDetailsPg.waitLoading();

		String lotteryInfo = null;
		if (lotteryDetailsPg.isErrorOccur()) {
			lotteryInfo = lotteryDetailsPg.getErrorMessage();
			lotteryDetailsPg.clickCancel();
			ajax.waitLoading();
		} else {
			lotteryInfo = lotteryDetailsPg.getLotteryId();
			lotteryDetailsPg.clickOK();
			ajax.waitLoading();
		}

		lotterySearchPg.waitLoading();

		return lotteryInfo;
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		lottery.name = "lottery for edit "
			+ DataBaseFunctions.getEmailSequence();
		lottery.location = "Jordan Lake State Rec Area";
		lottery.locationCategory = "Park";
		lottery.description = "lottery for slip test";
		lottery.productCategory = "Slip";
		lottery.isCollectCreditCard = true;
		lottery.isRequiresCustomerAcceptance = true;
		LotteryPreferenceAttribute attr1 = new LotteryPreferenceAttribute();
		attr1.label ="Facility";
		attr1.displayOrder ="1";
		LotteryPreferenceAttribute attr2 = new LotteryPreferenceAttribute();
		attr2.label ="Min Slip Depth";
		attr2.displayOrder ="2";
		LotteryPreferenceAttribute attr3 = new LotteryPreferenceAttribute();
		attr3.label ="Min Slip Length";
		attr3.displayOrder ="3";
		LotteryPreferenceAttribute attr4 = new LotteryPreferenceAttribute();
		attr4.label ="Min Slip Width";
		attr4.displayOrder ="4";
		LotteryPreferenceAttribute attr5 = new LotteryPreferenceAttribute();
		attr5.label ="Boat Category";
		attr5.displayOrder ="5";
		LotteryPreferenceAttribute attr6 = new LotteryPreferenceAttribute();
		attr6.label ="Dock";
		attr6.displayOrder ="6";
		attr6.entryRequired="Per Preference";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);
		
		
		error="The required information for each applicable Lottery Award Rule must be specified.";

	}

}
