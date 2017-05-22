package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.penalty.marina.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 		1.Add fee penalty with diff marina rate type then verify the diff penalty unit option value
 * @Preconditions:
 * @SPEC: 
 * TC:049556-- Add Fee Penalty Scheduel - Creation info, Penalty Unit
 * @Task#:AUTO-1431
 * 
 * @author Jasmine
 * @Date Jan 25, 2013(P)
 */
public class CheckPenaltyUnit extends FinanceManagerTestCase{
	private FeePenaltyData schedule = new FeePenaltyData();
	private FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage.getInstance();
	private List<String> unitList;
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		this.gotoFeePenaltyDetialPg(schedule);
		this.setMarinaRateType(schedule.marinaRateType);
		this.verifyPenaltyUnitOptionValue(unitList);
		schedule.marinaRateType = "Lease";
		unitList.clear();
		unitList.add("Percent");
		unitList.add("Flat");
		this.setMarinaRateType(schedule.marinaRateType);
		this.verifyPenaltyUnitOptionValue(unitList);
		schedule.marinaRateType = "Seasonal";
		this.setMarinaRateType(schedule.marinaRateType);
		this.verifyPenaltyUnitOptionValue(unitList);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.location = fnm.getFacilityName("552834", schema);// Medoc Mountain State Park
		schedule.locationCategory = "Park";
		schedule.loop = null;
		schedule.dock = "All";
		schedule.productGroup = "Full attributes";
		schedule.product = "All";
		schedule.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.startInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.tranType = "Change Boat";
		schedule.tranOccur = "All";
		schedule.marinaRateType = "Transient";
		schedule.units = "Nights";
		schedule.value = "5";	
		
		unitList = new ArrayList<String>();
		unitList.add("Nights");
		unitList.add("Days");
		unitList.add("Percent");
		unitList.add("Flat");
	}
	
	private void gotoFeePenaltyDetialPg(FeePenaltyData fp){
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		
		mainPg.clickAddNew();
		findLocPg.waitLoading();		
		findLocPg.searchByLocationName(fp.location, fp.locationCategory);
		ajax.waitLoading();
		findLocPg.selectLocation(fp.location);
		detailPg.waitLoading();
		if(fp.productCategory != null && fp.productCategory.length() > 0) {
		detailPg.setSlipType(fp.productCategory);
		detailPg.selectPrdCategory(fp.productCategory);
		}
	}
	
	private void setMarinaRateType(String rateType){
		if(!StringUtil.isEmpty(rateType))
		{
			detailPg.selectMarinaRateType(rateType);
			ajax.waitLoading();
			detailPg.waitLoading();
		}
	}
	
	private void verifyPenaltyUnitOptionValue(List<String> expectedList){
		List<String> actualList = detailPg.getPenaltyUnitList();
		if(expectedList.size() != actualList.size()){
			throw new ErrorOnPageException("unit option size should same",expectedList.size(),actualList.size());
		}
		if(expectedList.contains(actualList)){
			throw new ErrorOnPageException("penalty unit  option info error");
		}else{
			logger.info("Result was correct");
		}
	}
}
