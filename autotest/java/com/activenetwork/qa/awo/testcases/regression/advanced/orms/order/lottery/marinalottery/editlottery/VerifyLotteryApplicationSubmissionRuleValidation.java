package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlottery;

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
 * @Description: This case is used to verify edit lottery program for slip  
 *               check point:1. Modify Maximum Number of one Lottery Application Submission Rule to empty.
 *                           2. Modify Maximum Number of one Lottery Application Submission Rule same as another Lottery Application Submission Rule.
 *                           3. Add one duplicate Lottery Application Submission Rules with same rule type and different Maximum Number to one existing rule.
 * @Preconditions: 
 * @SPEC: TC:042105
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 27, 2012
 */
public class VerifyLotteryApplicationSubmissionRuleValidation extends
		InventoryManagerTestCase {
	List<String[]> rules;
	String error;
	
	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// add new lottery
		lottery.id = im.addNewLottery(lottery);

		// Modify Maximum Number of one Lottery Application Submission Rule to empty.
		rules = new ArrayList<String[]>();
		String[] rule1 = new String[2];
		rule1[0] = "Maximum Number per Primary Occupant";
		rule1[1] = "";
		rules.add(rule1);
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		String message = this.editLottery(lottery,rules);
		this.verifyErrorMessage(error, message);

		// Modify Maximum Number of one Lottery Application Submission Rule same as another Lottery Application Submission Rule.
		error = "Duplicate applicable Lottery Application Submission Rules have been specified. Duplicates are not allowed.";
		rules.get(0)[1]="1";
		String[] rule2 = new String[2];
		rule2[0] = "Maximum Number per Primary Occupant";
		rule2[1] = "1";
		rules.add(rule2);
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		message = this.editLottery(lottery,rules);
		this.verifyErrorMessage(error, message);

		// Add one duplicate Lottery Application Submission Rules with same rule type and different Maximum Number to one existing rule.
		rules.get(0)[1]="2";
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		message = this.editLottery(lottery,rules);
		this.verifyErrorMessage(error, message);

		// log out
		im.logoutInvManager();

	}
	
	private void verifyErrorMessage(String error, String message) {
		if (!error.equals(message)) {
			throw new ErrorOnPageException("Error Message:", error, message);
		}
	}

	private String editLottery(Lottery lottery,List<String[]> rules) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		logger.info("Edit existing Lottery.");

		this.updateLotteryDetail(lottery,rules);
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
	
	public void updateLotteryDetail(Lottery lottery, List<String[]> rules) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		
		lotteryDetailsPg.setDescription(lottery.description);
		lotteryDetailsPg.selectPermitCategories(lottery.permitCategory);
		if (lotteryDetailsPg.isMaxNumDropdownListEnabled()) {
			lotteryDetailsPg.selectMaxiNum(lottery.maxNumber);
		}
		if (lottery.isCollectCreditCard) {
			lotteryDetailsPg.selectCreditCard();
		} else {
			lotteryDetailsPg.unselectCreditCard();
		}
		if (lottery.isRequiredByPermitType) {
			lotteryDetailsPg.selectRequiredPermit();
		} else {
			lotteryDetailsPg.unselectRequiredPermit();
		}
		if (lottery.isRequiresCustomerAcceptance) {
			lotteryDetailsPg.selectCustomerAcceptance();
		} else {
			lotteryDetailsPg.unselectCustomerAcceptance();
		}

		// submission rule
		int num = lotteryDetailsPg.getSubmissionRuleRemoveObjectLength();
		if (num > 1) {
			for (int i = 0; i < num-1; i++) {
				lotteryDetailsPg.clickRemoveSubmissionRule(i);
			}
		}
		if (null != rules && rules.size() > 0) {
			for (int i = 0; i < rules.size(); i++) {
				lotteryDetailsPg.clickAddNewSubRules();
				lotteryDetailsPg.selectSubmissionRules(rules.get(i)[0], i + 1);
				lotteryDetailsPg.setMaxNumOfPriOccupant(rules.get(i)[1], i + 1);
			}
		}

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
		attr6.entryRequired = "Per Preference";
		attr6.displayOrder = "6";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);


		error = "The required information for each applicable Lottery Application Submission Rule must be specified.";

	}

}
