package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;

/**
 * 
 * @Description:This case is used to verify lottery list UI info
 * @Preconditions: 
 * @SPEC: Lottery List [TC:043083]
 * @Task#:Auto-1344

 * @author VZhang
 * @Date Dec 24, 2012
 */

public class VerifyLotteryListUI extends InventoryManagerTestCase{
	private LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();

	@Override
	public void execute() {
		// Goto Lottery Details page
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		//initial sorted by the lottery id in descending order
		this.verifyColumnSortingWhetherCorrect("Lottery ID", false);
		//after click Lottery ID head, All the lotteries are sorted in ascending order
		lotterySearchPg.clickColumnHeading("Lottery ID");
		this.verifyColumnSortingWhetherCorrect("Lottery ID", true);
		//after click Lottery Name head, All the lotteries are sorted by Lottery Name in ascending order.
		lotterySearchPg.clickColumnHeading("Lottery Name");
		this.verifyColumnSortingWhetherCorrect("Lottery Name", true);
		//after click Lottery Name head, All the lotteries are sorted by Lottery Name in descending order.
		lotterySearchPg.clickColumnHeading("Lottery Name");
		this.verifyColumnSortingWhetherCorrect("Lottery Name", false);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
	}
	
	private List<String> columnSortingOrder(String columnName, boolean isAscending){
		List<String> sortingList = lotterySearchPg.getLotteryColumnValues(columnName);
		Collections.sort(sortingList,String.CASE_INSENSITIVE_ORDER);
		if(!isAscending){		
			Collections.reverse(sortingList);
		}
		
		return sortingList;
	}
	
	private boolean verifyColumnSortingWhetherCorrect(String columnName, boolean isAscending){
		logger.info("Verify column sorting. Columun Name = " + columnName);
		
		List<String> colValuesFromUI = lotterySearchPg.getLotteryColumnValues(columnName);		
		List<String> colValuesExp = this.columnSortingOrder(columnName, isAscending);

		boolean correct = colValuesExp.equals(colValuesFromUI);
		
		if(!correct){
			logger.error("Column sorting not correct.");
		}else {
			logger.info("Column sorting is correct.");
		}
		
		return correct;
	}

}
