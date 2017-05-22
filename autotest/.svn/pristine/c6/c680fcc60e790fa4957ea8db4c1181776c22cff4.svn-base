/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This case is used to verify search lottery program for slip
 *               check point:1. search by lottery id
 *                           2. search by complex condition
 * @Preconditions: 
 * @SPEC: TC:042108,TC:042118
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Jan 4, 2013
 */
public class SearchLottery_ComplexCondition extends InventoryManagerTestCase {
	LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// add new lottery
		lottery.id = im.addNewLottery(lottery);

		// search by lottery id
		lotterySearchPg.setLotteryID(lottery.id);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Lottery ID", lottery.id);

		// search by combine condition
		lottery.productGroup="";
		this.searchLotteryUsingCombineCondition();
		this.verifyLotteryExist(lottery.id);

		// log out
		im.logoutInvManager();
	}

	private void verifyLotteryExist(String id) {
		if (!lotterySearchPg.checkLotteryExists(lottery.id)) {
			throw new ErrorOnPageException("Lottery : " + id
					+ " should be in the list");
		}
	}

	private void searchLotteryUsingCombineCondition() {
		lotterySearchPg.setLotteryID("");
		lotterySearchPg.setLotteryName(lottery.name);
		lotterySearchPg.setCoverage(lottery.location);
		lotterySearchPg.setRevenLocation(lottery.location);
		lotterySearchPg.selectStatus(lottery.status);
		lotterySearchPg.selectProductCategory(lottery.productCategory);
		lotterySearchPg.selectProductGroup(lottery.productGroup);

		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
	}

	private void verifySearchResultCol(String colName, String value) {
		List<String> colInfo = lotterySearchPg.getLotteryColumnValues(colName);

		for (int i = 0; i < colInfo.size(); i++) {
			if (!value.equalsIgnoreCase(colInfo.get(i))) {
				throw new ErrorOnPageException(
						"col value is not corret at row :" + i, value,
						colInfo.get(i));
			}
		}

	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";

		
		lottery.status="Inactive";
		lottery.name = "lottery for search "+DataBaseFunctions.getEmailSequence();
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
		attr6.entryRequired = "Per Preference";//PCR 4027
		attr6.displayOrder ="6";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);

	}

}
