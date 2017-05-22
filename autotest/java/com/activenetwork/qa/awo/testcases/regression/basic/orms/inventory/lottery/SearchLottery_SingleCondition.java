/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This case is used to verify search lottery program for slip
 *               check point:1. search by lottery name
 *                           2. search by coverage
 *                           3. search by revenue location
 *                           4. search by status
 *                           5. search by product category
 *                           6. search by product group
 * @Preconditions: 
 * @SPEC: TC:042109,TC:042110,TC:042111,TC:042112,TC:042113,TC:042114
 * @Task#: AUTO-1341 
 * 
 * @author szhou
 * @Date  Jan 4, 2013
 */
public class SearchLottery_SingleCondition extends InventoryManagerTestCase {
	LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();

	@Override
	public void execute() {
		// log into inventory manager
		im.loginInventoryManager(login);

		// go to search page
		this.gotoLotterySearchPage();

		// search by lottery name
		lotterySearchPg.setLotteryName(lottery.name);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Lottery Name", lottery.name);

		// search by coverage
		this.cleanupSearchCondition();
		lotterySearchPg.setCoverage(lottery.location);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Lottery Coverage", lottery.location);

		// search by revenue location
		this.cleanupSearchCondition();
		lotterySearchPg.setRevenLocation(lottery.location);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Revenue Location", lottery.location);

		// search by status
		this.cleanupSearchCondition();
		lotterySearchPg.selectStatus(lottery.status);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Active", "Yes");

		// search by product category
		this.cleanupSearchCondition();
		lotterySearchPg.selectProductCategory(lottery.productCategory);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Product Category", lottery.productCategory);

		// search by product group
		this.cleanupSearchCondition();
		lotterySearchPg.selectProductGroup(lottery.productGroup);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		this.verifySearchResultCol("Product Groups", lottery.productGroup);

		// log out
		im.logoutInvManager();

	}
	
	private void cleanupSearchCondition(){
		lotterySearchPg.setLotteryName("");
		lotterySearchPg.setCoverage("");
		lotterySearchPg.setRevenLocation("");
		lotterySearchPg.selectBlankStatus();
		lotterySearchPg.selectBlankProductCategory();
		lotterySearchPg.selectBlankProductGroup();
	}

	private void verifySearchResultCol(String colName, String value) {
		List<String> colInfo = lotterySearchPg.getLotteryColumnValues(colName);

		for (int i = 0; i < colInfo.size(); i++) {
			if (!colInfo.get(i).contains(value)) {
				throw new ErrorOnPageException(
						"col value is not corret at row :" + i, value,
						colInfo.get(i));
			}
		}

	}

	private void gotoLotterySearchPage() {
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();

		invHmPg.selectPageFromDropDownList("Lottery Setup");
		lotterySearchPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";

		lottery.name = "TestSlipLottery";
		lottery.location = "Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		lottery.status="Active";
		lottery.productGroup="Full attributes";
	}

}
