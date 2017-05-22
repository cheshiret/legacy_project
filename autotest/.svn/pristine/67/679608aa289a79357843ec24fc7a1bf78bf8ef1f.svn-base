package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * @Description: This case is used to verify when search by show, steps including:
 *				 1. Select Active in the drop down list of Show Field and click Go button.
 * 				 2. Select Inactive in the drop down list of Show Field and click Go button.
 * @Preconditions:none
 * @SPEC:Search Tax Schedule - Show field [TC:049976]
 * @Task#: Auto-1441
 * @author Phoebe
 * @Date  Feb 7 2013
 */
public class VerifySearchResult_ShowField extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();

	@Override
	public void execute() {
		// Login finance manager and go to tax schedule list page
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		
		//Search by select 'Active' in the drop down list of show field and verify result.
		searchByShowField(ACTIVE_STATUS);
		schedulePg.verifySearchResultInColumn("Active", YES_STATUS);
		
		//Search by select 'Inactive' in the drop down list of show field and verify result.
		searchByShowField(INACTIVE_STATUS);
		schedulePg.verifySearchResultInColumn("Active", NO_STATUS);

		fnm.logoutFinanceManager();
	}
	
	private void searchByShowField(String status){
		logger.info("Search tax schedule by show field(status).");
		schedulePg.selectShowStatusDropDownList(status);
		schedulePg.clickGo();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
	}

}
