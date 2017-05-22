package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Verify search result when search by product.
 * 1.Enter a valid product name.
 * 2.Enter part of a valid product name.
 * 3.Enter low-case product name.
 * 4.Enter invalid product name.
 * @Preconditions:
 * @SPEC: Step12~15 of 'Search Fee Schedule - Search field [TC:048898]'
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 29, 2013
 */
public class VerifySearchByProductGroup extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private String expectMsg, colName;

	public void execute() {
		fnm.loginFinanceManager(login);
		
		// 1.Enter a valid product name.
		fnm.searchFeeSchedule(schedule);
		List<String> groupList = this.getGroupList(schedule.searchValue);
		this.verifyProductGroupSearchResults(groupList, schedule.searchValue);
		
		// 2.Enter part of a valid product name.
		schedule.searchValue = schedule.productGroup.substring(0, 10);
		this.verifyProductGroupSearchResults(groupList, schedule.productGroup);
		
		// 3.Enter low-case product name.
		schedule.searchValue = schedule.productGroup.toLowerCase();
		this.verifyProductGroupSearchResults(groupList, schedule.productGroup);
		
		// 4.Enter invalid product name.
		schedule.searchValue = "invalidGroup";
		fnm.searchFeeSchedule(schedule);
		if(!MiscFunctions.compareResult("Error message", expectMsg, feeMainPg.getErrorMsg())){
			throw new ErrorOnPageException("--Check logs above.");
		}
		
		fnm.logoutFinanceManager();
	}
	
	private List<String> getGroupList(String groupName){
		logger.info("Get all product name which product group name is"+groupName);
		List<String> valuesInDB = fnm.getProductUsingSameGroup(schema, groupName);
		valuesInDB.add(groupName);
		return valuesInDB;
	}
	
	private void verifyProductGroupSearchResults(List<String> groupList, String groupName){
		List<String> values = feeMainPg.getSpecificColValueList(colName);
		for(String value:values){
			if(!groupList.contains(value)){
				throw new ErrorOnPageException("Product group should not contains product:"+value);
			}
		}
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		colName = "Product or Product Group";
		
		schedule.searchType = "Product Group";// Don't change!
		schedule.productGroup = "Full attributes";
		schedule.searchValue = schedule.productGroup;
		schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		
//		expectMsg = "No Fee Schedules found for search criteria";
		expectMsg = "No Matches Found";
	}
}
