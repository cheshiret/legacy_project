package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
/**
 * 
 * @Description: 
 * 1. The "Batch Add License Year" and "Batch Edit License Year" button should be disable 
 * under none chart-of-accounts location.
 * 2. For MS contract, the locations other than "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks"
 * is none chart-of-accounts location.
 * @Preconditions:
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 19, 2011
 */
public class BatchAddEditLicenseYearDisable extends LicenseManagerTestCase{
	private String noneChartOfAccountLocation;

	public void execute() {
		//login in 
		lm.loginLicenseManager(login);

		//Check Batch Add/Edit button is able in chart-of-account location
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		this.checkBatchEditLicenseYearButton(true);

		//Check Batch Add/Edit button is disable in none chart-of-account location
		lm.gotoHomePage();
		this.changeLocation();
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		this.checkBatchEditLicenseYearButton(false);

		//Log out 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		noneChartOfAccountLocation = "HF HQ Role - Auto-WAL-MART";
	}

	private void changeLocation(){
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		homePg.selectLocation(noneChartOfAccountLocation);
		homePg.waitLoading();
	}

	private void checkBatchEditLicenseYearButton(boolean isAble){
		LicMgrPrivilegesListPage privilegesListPg = LicMgrPrivilegesListPage.getInstance();
		if(privilegesListPg.checkBatchEditLicenseYeaButtonAble()!=isAble){
			throw new ErrorOnDataException("Batch Edit License Year button should "+(isAble?"":"not ")+"be able.");
		}
		if(privilegesListPg.checkBatchAddLicenseYeaButtonAble()!=isAble){
			throw new ErrorOnDataException("Batch Add License Year button "+(isAble?"":"not ")+"be able.");
		}
	}
}
