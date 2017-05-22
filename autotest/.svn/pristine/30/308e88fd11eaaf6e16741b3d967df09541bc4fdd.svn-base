package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
/**
 * 
 * @Description: verify the error message when No accounts available to assign for location B
	select B from a filtered list of Locations in the Location hierarchy currently set-up in the system
 * @Preconditions:
 * @SPEC: Location Selection-select a location without an available account [TC:041555]
 * @Task#: Auto-1459
 * 
 * @author Jasmine
 * @Date  Feb 5, 2013(P)
 */
public class VerifyMsgSelectInvalidLoc extends FinanceManagerTestCase{
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private final String NoAccount_Available_Msg = "There are no accounts available to assign for the location selected, please select another location for creating the fee schedule.";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		//add a new RA Fee Schedule for Slip Lottery
		String actaualMsg = this.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		this.verifyNoAccountAvaliableMsg(actaualMsg, NoAccount_Available_Msg);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schedule.location ="North Carolina State Parks";
		schedule.locationCategory = "Contract";
	}
	
	private String gotoRAFeeScheduleDetailPgByAddNew(String location,
			String locationCategory){
		String text = "";
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage
				.getInstance();
		ConfirmDialogPage dialogPg = ConfirmDialogPage.getInstance();
		dialogPg.setDismissible(false);

		logger.info("Go to RA fee schedule detail page.");

		schMainPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(location, locationCategory);
		findLocationPg.selectLocation(location);
		Object page = browser.waitExists(dialogPg,detailPg);
		if(page == dialogPg){
			text = dialogPg.text();
			dialogPg.clickOK();
		}
		return text;
	}
	
	private void verifyNoAccountAvaliableMsg(String actualMsg,String expectedMsg){
		if(!actualMsg.equals(expectedMsg)){
			throw new ErrorOnPageException("Error Message",expectedMsg,actualMsg);
		}
	}

}
