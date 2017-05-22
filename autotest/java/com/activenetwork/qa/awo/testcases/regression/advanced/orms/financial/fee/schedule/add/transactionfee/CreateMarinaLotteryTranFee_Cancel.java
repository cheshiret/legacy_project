package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. verify cancel action at select location page when add Lottery transaction fee schedule for Marina
 *              2. verify cancel action at select product and fee type page when add Lottery transaction fee schedule for Marina
 *              3. verify cancel action at add detail page when add Lottery transaction fee schedule for Marina
 * @Preconditions:
 * @SPEC:Add Fee Schedule - Cancel [TC:042717]
 * @Task#:AUTO-1346
 * 
 * @author vzhang
 * @Date  Nov 16, 2012
 */

public class CreateMarinaLotteryTranFee_Cancel extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		this.gotoSelectLocationPage();
		this.verifyCancelActionAtSelectLocationPage();
		
		this.gotoSelectPrdAndFeeTypePage(schedule.location, schedule.locationCategory);
		this.verifyCancelActionAtFeeScheduleDetailPage();
		
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		this.verifyCancelActionAtFeeScheduleDetailPage();
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.contract = "NC Contract";
	  	login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	  	
	  	//initialize schedule data
	  	schedule.locationCategory = "Park";
		schedule.location = fnm.getFacilityName("552903", schema);//Jordan Lake State Rec Area
	  	schedule.productCategory = "Lottery";
	  	schedule.feeType = "Transaction Fee";
	}
	
	private void gotoSelectLocationPage(){
		logger.info("Go to select location page.");
		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
	}
	
	private void verifyCancelActionAtSelectLocationPage(){
		findLocPg.clickCancel();
		try{
			feeMainPg.waitLoading();
		}catch(PageNotFoundException e){
			throw new ErrorOnPageException("Expect fee main search page not existing.");
		}		
	}
	
	private void gotoSelectPrdAndFeeTypePage(String location, String locationCategory){
		this.gotoSelectLocationPage();
		findLocPg.searchByLocationName(location, locationCategory);
		findLocPg.selectLocation(location);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	private void verifyCancelActionAtFeeScheduleDetailPage(){
		detailsPg.clickCancel();
		try{
			feeMainPg.waitLoading();
		}catch(PageNotFoundException e){
			throw new ErrorOnPageException("Expect fee main search page not existing.");
		}
	}

}
