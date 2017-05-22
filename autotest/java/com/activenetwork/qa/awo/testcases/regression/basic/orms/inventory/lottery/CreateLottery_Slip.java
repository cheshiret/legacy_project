/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery.AwardRulePara;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**
 * 
 * @Description: This case is used to verify create lottery program for slip   
 * @Preconditions: 
 * @SPEC: TC:042099,TC:043677
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 24, 2012
 */
public class CreateLottery_Slip extends InventoryManagerTestCase {
	LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// add new lottery
		lottery.id = im.addNewLottery(lottery);

		// verify add results
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		Lottery actually = detailPg.getLotteryInfo(lottery);
		detailPg.compareLotteryProgrammerInfo(lottery, actually);

		// log out
		im.logoutInvManager();

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
		attr1.label = "Facility";
		attr1.displayOrder = "1";
		LotteryPreferenceAttribute attr2 = new LotteryPreferenceAttribute();
		attr2.label = "Min Slip Depth";
		attr2.displayOrder = "2";
		LotteryPreferenceAttribute attr3 = new LotteryPreferenceAttribute();
		attr3.label = "Min Slip Length";
		attr3.displayOrder = "3";
		LotteryPreferenceAttribute attr4 = new LotteryPreferenceAttribute();
		attr4.label = "Min Slip Width";
		attr4.displayOrder = "4";
		LotteryPreferenceAttribute attr5 = new LotteryPreferenceAttribute();
		attr5.label = "Boat Category";
		attr5.displayOrder = "5";
		LotteryPreferenceAttribute attr6 = new LotteryPreferenceAttribute();
		attr6.label = "Dock";
		attr6.entryRequired = "Per Preference";//PCR 4027
		attr6.displayOrder = "6";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);
		lottery.isMaxNumPerPrimaryOccu = true;
		lottery.maxNumOfPriOccupant="1";
		lottery.awardRules = new ArrayList<AwardRulePara>();
		AwardRulePara rule = lottery.new AwardRulePara();
		rule.ruleName = "Maximum Number of Reservations Awarded per Primary Occupant";
		rule.maxNumber = "1";
		lottery.awardRules.add(rule);
		lottery.productGroups= new ArrayList<String>();
		lottery.productGroups.add("All");
		lottery.revenueLocation=lottery.location;
	}

}
