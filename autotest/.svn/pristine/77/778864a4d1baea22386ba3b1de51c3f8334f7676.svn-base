/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.createlottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This case is used to verify create lottery program for slip  
 *               check point:1. Add one Lottery Application Submission Rule and keep the Maximum Number as empty.
 *                           2. Add two duplicate Lottery Application Submission Rules with same rule type and same Maximum Number.
 *                           3. Add two duplicate Lottery Application Submission Rules with same rule type and different Maximum Number.
 * @Preconditions: 
 * @SPEC: TC:042084
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 25, 2012
 */
public class VerifyLotteryApplicationSubmissionRuleValidation extends
		InventoryManagerTestCase {
	List<String[]> rules;
	String error;

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// Add one Lottery Application Submission Rule and keep the Maximum Number as empty.
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery,rules);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();

		// Add two duplicate Lottery Application Submission Rules with same rule type and same Maximum Number.
		error = "Duplicate applicable Lottery Application Submission Rules have been specified. Duplicates are not allowed.";
		rules.get(0)[1]="1";
		String[] rule2 = new String[2];
		rule2[0] = "Maximum Number per Primary Occupant";
		rule2[1] = "1";
		rules.add(rule2);
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery,rules);
		this.verifyErrorMessage(error);
		this.gotoSearchPage();

		// Add two duplicate Lottery Application Submission Rules with same rule type and different Maximum Number.
		rules.get(0)[1]="2";
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery,rules);
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
	
	private void gotoSearchPage(){
		LotteryDetailsPage detailPg = LotteryDetailsPage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		
		detailPg.clickCancel();
		ajax.waitLoading();
		lotterySearchPg.waitLoading();
	}

	private void addLotteryProgram(Lottery lottery, List<String[]> rules) {
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
		if (null != rules && rules.size() > 0) {
			for (int i = 0; i < rules.size(); i++) {
				detailPg.clickAddNewSubRules();
				detailPg.selectSubmissionRules(rules.get(i)[0], i + 1);
				detailPg.setMaxNumOfPriOccupant(rules.get(i)[1], i + 1);
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
		attr6.displayOrder = "6";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);
		rules = new ArrayList<String[]>();
		String[] rule1 = new String[2];
		rule1[0] = "Maximum Number per Primary Occupant";
		rule1[1] = "";
		rules.add(rule1);

		error = "The required information for each applicable Lottery Application Submission Rule must be specified.";

	}

}
