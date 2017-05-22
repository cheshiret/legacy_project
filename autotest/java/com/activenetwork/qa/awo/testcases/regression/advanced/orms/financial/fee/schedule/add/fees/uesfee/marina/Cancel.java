package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Cancel the add fee schedule flow.
 * @Preconditions:
 * @SPEC:  TC:043376
 * @Task#: Auto-1326
 * 
 * @author Jasmine
 * @Date  Jan 7, 2013
 */
public class Cancel extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
    private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
    private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
    private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
			.getInstance();
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		Object page = this.cancelFromLocPage();
		this.verifyFeeMainPageExist(page);
		page = this.cancelFromPrdCategoryDetailPg(schedule);
		this.verifyFeeMainPageExist(page);
		page = this.cancelFromFeeScheduleDetailPage(schedule);
		this.verifyFeeMainPageExist(page);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.location = fnm.getFacilityName("552831", schema);// Eno River State Park";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
	}
	/**
	 * verify fee main page was exist.
	 * @param page
	 */
	private void verifyFeeMainPageExist(Object page){
		if(page != feeMainPg){
			throw new ErrorOnPageException("Flow Erro, the fee main page shuld exist");
		}
	}
	/**
	 * go to find loc page.
	 */
	private void gotoFindLocPgFromFeeMainPg(){
		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
	}
	/**
	 * Cancel add fee schedule from loc page.
	 * @return
	 */
	private Object cancelFromLocPage(){
		this.gotoFindLocPgFromFeeMainPg();
		//Go to the select location page after click add new button.
		findLocPg.clickCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(feeMainPg,findLocPg);
		return page;
	}
	/**
	 * go to product category detail page.
	 * @param fd
	 */
	private void gotoPrdCategoryDetailPage(FeeScheduleData fd){
		this.gotoFindLocPgFromFeeMainPg();
		findLocPg.searchByLocationName(fd.location, fd.locationCategory);
		findLocPg.selectLocation(fd.location);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	/**
	 * cancel the fee schedule from product category details page.
	 * @param fd
	 * @return
	 */
	private Object cancelFromPrdCategoryDetailPg(FeeScheduleData fd){
		this.gotoPrdCategoryDetailPage(fd);
		detailsPg.clickCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(feeMainPg,detailsPg);
		return page;
	}
	/**
	 * go to fee schedule details page.
	 * @param fd
	 */
	private void gotoFeeScheduleDetailsPage(FeeScheduleData fd){
		this.gotoPrdCategoryDetailPage(fd);
		detailsPg.selectPrdCategory(fd.productCategory);
		ajax.waitLoading();
		detailsPg.selectFeeType(fd.feeType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	/**
	 * cancel from fee schedule detail page.
	 * @param fd
	 * @return
	 */
	private Object cancelFromFeeScheduleDetailPage(FeeScheduleData fd){
		this.gotoFeeScheduleDetailsPage(fd);
		//Cancel the add fee schedule from fee detail page.
		detailsPg.clickCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(feeMainPg,detailsPg);
		return page;
	}

}
