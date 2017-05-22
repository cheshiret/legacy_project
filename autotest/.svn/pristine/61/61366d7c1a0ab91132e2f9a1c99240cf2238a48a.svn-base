/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.createlottery;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This case is used to verify create lottery program for slip  
 *               check point:1. Lottery Name as empty
 *                           2. Enter a Lottery Name which same as (case-insensitive) an existing Lottery Product 
 * @Preconditions: 
 * @SPEC: TC:041646
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Dec 24, 2012
 */
public class VerifyLotteryNameValidation extends InventoryManagerTestCase {
	String error;

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// Lottery Name as empty and click OK button
		im.gotoAddNewLottery(lottery);
		this.addLotteryProgram(lottery);
		this.verifyErrorMessage(error);

		// Enter a Lottery Name which same as (case-insensitive) an existing Lottery Product
		error="The entered Lottery Name already exists in the system. Please specify another Lottery Name.";
		lottery.name = "TestSlipLottery";
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

		detailPg.clickApply();
		detailPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		lottery.name = "";
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
		
		error="The Lottery Name is required. Please specify the Lottery Name.";

	}

}
