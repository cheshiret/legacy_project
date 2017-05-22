/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.createlottery;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.AwardRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This case is used to verify create lottery program for slip  
 *               check point:1. Add one Lottery Award Rule and keep the Maximum Number as empty
 *                           2. Add two duplicate Lottery Award Rules with same rule type and same Maximum Number.
 *                           3. Add two duplicate Lottery Award Rules with same rule type and different Maximum Number.
 * @Preconditions: 
 * @SPEC: TC:042083
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 25, 2012
 */
public class VerifyLotteryAwardRulesValidation extends InventoryManagerTestCase {
    String error;
    
	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// Add one Lottery Award Rule and keep the Maximum Number as empty
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		
		// Add two duplicate Lottery Award Rules with same rule type and same Maximum Number.
		error="Duplicate applicable Lottery Award Rules have been specified. Duplicates are not allowed.";
		lottery.awardRules.get(0).maxNumber="1";
		AwardRulePara rule2 = lottery.new AwardRulePara();
		rule2.ruleName = "Maximum Number of Reservations Awarded per Primary Occupant";
		rule2.maxNumber = "1";
		lottery.awardRules.add(rule2);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		
		// Add two duplicate Lottery Award Rules with same rule type and different Maximum Number.
		lottery.awardRules.get(0).maxNumber="2";
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);
		
		// log out
		im.logoutInvManager();
	}
	
	private void verifyErrorMessage(String error) {
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();

		String message = detailPg.getErrorMessage();
		if (!error.equals(message)) {
			throw new ErrorOnPageException("Error Message:", error, message);
		}
	}

	private void addLotteryProgram(Lottery lottery) {
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();

		detailPg.setLotteryName(lottery.name);
		detailPg.setDescription(lottery.description);
		detailPg.selectProCategory(lottery.productCategory);
		detailPg.selectProGroups(lottery.productGroup);
		detailPg.waitLoading();
		detailPg.selectPermitCategories(lottery.permitCategory);
		detailPg.waitLoading();
		detailPg.selectPermitType(lottery.permitTypes);
		detailPg.selectMaxiNum(lottery.maxNumber);
		detailPg.addPreferenceAttributes(lottery.attributes);
		if (null != lottery.awardRules && lottery.awardRules.size() > 0) {
			for (int i = 0; i < lottery.awardRules.size(); i++) {
				detailPg.clickAddNewWardRule();
				detailPg
						.selectAwardRules(lottery.awardRules.get(i).ruleName,
								i + 1);
				detailPg.setMaxNumForAwardRule(lottery.awardRules.get(i).maxNumber,
						i + 1);
			}
		}

		detailPg.clickApply();
		detailPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		lottery.name = "lottery for slip "
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
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);
		lottery.awardRules = new ArrayList<AwardRulePara>();
		AwardRulePara rule1 = lottery.new AwardRulePara();
		rule1.ruleName = "Maximum Number of Reservations Awarded per Primary Occupant";
		rule1.maxNumber = "";
		lottery.awardRules.add(rule1);
		
		error="The required information for each applicable Lottery Award Rule must be specified.";

	}

}
