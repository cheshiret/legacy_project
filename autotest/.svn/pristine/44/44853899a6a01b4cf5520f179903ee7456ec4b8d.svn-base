package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * @Preconditions: 
 * @SPEC:  
 * 		Add Fee Schedule - Entry of Date-based Percentages for Slip [TC:048084] 
 * 
 * @Task#: Auto-1333
 * 
 * @author Jane
 * @Date  Jan 28, 2013
 */
public class VerifyEntryDateBasesPercentage extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage detailPg = FinMgrFeeSchDetailPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login); 
		fnm.gotoFeeMainPage();
		
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		
		changeRateType(schedule.marinaRateType);
		verifyAddPercentBtnEnabled(schedule.calculationMethod);
		verifyPricingBasedSection();
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.location = fnm.getFacilityName("552818", schema);// Mayo River State Park
		schedule.locationCategory = "Park";
		
		schedule.marinaRateType=OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL;
		schedule.calculationMethod = "Percentage";
	}
	
	private void changeRateType(String type) {
		logger.info("Change rate type to "+type);
		detailPg.selectMarinaRateType(type);
		ajax.waitLoading();
		detailPg.waitLoading();
		
	}
	
	private void verifyAddPercentBtnEnabled(String calculationMethod) {
		logger.info("Select calculation method to "+calculationMethod);
		detailPg.selectCalculationMethod(calculationMethod);
		ajax.waitLoading();
		detailPg.waitLoading();
		boolean enabled=true;
		enabled &=detailPg.checkAddArrivalPercentEnable();
		enabled &=detailPg.checkAddDeparturePercentEnable();
		if(!enabled)
			throw new ErrorOnPageException("'Add Arrival Percent and Add Departure Percent button should be enabled.'");
	}

	private void verifyPricingBasedSection() {
		logger.info("Click Add Arrival Percent button.");
		detailPg.clickAddArrivalPercent();
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.verifyPricingBasedOnArrival();
		
		logger.info("Click Add Departure Percent button.");
		detailPg.clickAddDeparturePercent();
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.verifyPricingBasedOnDeparture();
	}
}
