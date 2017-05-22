package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:1.Search fee schedule with Fee type, product categary and unit group.
 *              
 * @Preconditions:
 * @SPEC:Use Case Specification: Search Fee Schedule
 * @Task#:Auto-1430
 * @TC:TC:049400
 * @author Jasmine
 * @Date  Jan 21, 2013
 */
public class FeeSearchUIManagement extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> marinaRateTypeList = new ArrayList<String>();
	private List<String> boatCategoryTypeList = new ArrayList<String>();
	private List<String> raftingList = new ArrayList<String>();
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.searchFeeSchedule(schedule);
		feeMainPg.verifyMarinaTypeListInfo(marinaRateTypeList);
		feeMainPg.verifyBoatCategoryListInfo(boatCategoryTypeList);
		feeMainPg.verifyRaftingListInfo(raftingList);
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schedule.productCategory = "Slip";
		schedule.marinaRateType = null;
		
		marinaRateTypeList.add("");
		marinaRateTypeList.add("All");
		marinaRateTypeList.add("Lease");
		marinaRateTypeList.add("Seasonal");
		marinaRateTypeList.add("Transient");
		
		boatCategoryTypeList.add("");
		boatCategoryTypeList.add("All");
		boatCategoryTypeList.add("Commercial");
		boatCategoryTypeList.add("Personal");
		
		raftingList.add("");
		raftingList.add("All");
		raftingList.add("Excluding Rafting");
		raftingList.add("Rafting Only");
	}
	
}
