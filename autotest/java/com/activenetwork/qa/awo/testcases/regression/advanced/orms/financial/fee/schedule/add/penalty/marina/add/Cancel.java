package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.penalty.marina.add;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 		1.Cancel the process of add penalty schecule.
 * @Preconditions:
 * @SPEC: 
 * TC:049451-- Add Fee Penalty Scheduel - Cancel 
 * @Task#:AUTO-1431
 * 
 * @author Jasmine
 * @Date Jan 25, 2013
 */
public class Cancel extends FinanceManagerTestCase{
	private FeePenaltyData schedule = new FeePenaltyData();
	private FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage.getInstance();
	private FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage.getInstance();
	private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
			
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		Object page = this.cancelFromLocPage();
		this.verifyFeeMainPageExist(page);
		page = this.cancelFromPrdCategoryDetailPg(schedule);
		this.verifyFeeMainPageExist(page);
		page = this.cancelFromPenaltyScheduleDetailPage(schedule);
		this.verifyFeeMainPageExist(page);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.location = fnm.getFacilityName("552834", schema);// Medoc Mountain State Park
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
	}
	
	/* 
	 * go to find select fee location page from add new page.
	 */
	private void gotoFindLogPg(){
		mainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
	}
	
	/**
	 * Cancel add fee schedule from loc page.
	 * @return
	 */
	private Object cancelFromLocPage(){
		this.gotoFindLogPg();
		findLocPg.clickCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(mainPg,findLocPg);
		return page;
	}
	/**
	 * verify fee main page was exist.
	 * @param page
	 */
	private void verifyFeeMainPageExist(Object page){
		if(page != mainPg){
			throw new ErrorOnPageException("Flow Erro, the fee main page shuld exist");
		}else{
			logger.info("Result was correct");
		}
	}
	
	/**
	 * go to product category detail page.
	 * @param fd
	 */
	private void gotoPrdCategoryDetailPage(FeePenaltyData penalty){
		this.gotoFindLogPg();
		findLocPg.searchByLocationName(penalty.location, penalty.locationCategory);
		ajax.waitLoading();
		findLocPg.selectLocation(penalty.location);
		detailPg.waitLoading();
	}
	/**
	 * cancel the fee schedule from product category details page.
	 * @param fd
	 * @return
	 */
	private Object cancelFromPrdCategoryDetailPg(FeePenaltyData fd){
		this.gotoPrdCategoryDetailPage(fd);
		//Go to select product category page.
		detailPg.clickCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(mainPg,detailPg);
		return page;
	}
	/**
	 * go to fee schedule details page.
	 * @param fd
	 */
	private void gotoPenaltyScheduleDetailsPage(FeePenaltyData fd){
		this.gotoPrdCategoryDetailPage(fd);
		detailPg.selectPrdCategory(fd.productCategory);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	/**
	 * cancel from fee schedule detail page.
	 * @param fd
	 * @return
	 */
	private Object cancelFromPenaltyScheduleDetailPage(FeePenaltyData fd){
		this.gotoPenaltyScheduleDetailsPage(fd);
		//Go to add penalty schedule detail page.
		detailPg.clickCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(mainPg,detailPg);
		return page;
	}
}
