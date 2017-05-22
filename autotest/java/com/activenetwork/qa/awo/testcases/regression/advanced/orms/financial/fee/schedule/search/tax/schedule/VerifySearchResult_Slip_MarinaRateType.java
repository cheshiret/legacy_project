package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * @Description: This case is used to verify when search by marina rate type, steps including:
 *				 1. Select 'Slip' as product category.
 * 				 2. Select Transient in the drop down list of Marina Rate Type and click Go button.
 * 				 3.	Select Seasonal in the drop down list of Marina Rate Type and click Go button.
 * 				 4.	Select Lease in the drop down list of Marina Rate Type and click Go button.
 * 				 5.	Select All in the drop down list of Marina Rate Type and click Go button.
 * @Preconditions:none
 * @SPEC:Search Tax Schedule - Marina Rate Type [TC:049973]
 * @Task#: Auto-1441
 * @author Phoebe
 * @Date  Feb 7 2013
 */
public class VerifySearchResult_Slip_MarinaRateType extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private static final String SLIP_PRODUCT_CATEGORY = "Slip";
	@Override
	public void execute() {
		// Login finance manager and go to tax schedule list page
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		
		//Select slip as product category
		selectProductCategory(SLIP_PRODUCT_CATEGORY);
		schedulePg.verifySearchResultInColumn("Product Category", SLIP_PRODUCT_CATEGORY);
		
		//Search by select Transient in the drop down list of Marina Rate Type and verify result.
		searchByMarinaRateType(SLIP_RESERVATION_TYPE_TRANSIENT);
		schedulePg.verifySearchResultInColumn("Marina Rate Type", SLIP_RESERVATION_TYPE_TRANSIENT);
		
		//Search by select Seasonal in the drop down list of Marina Rate Type and verify result.
		searchByMarinaRateType(SLIP_RESERVATION_TYPE_SEASONAL);
		schedulePg.verifySearchResultInColumn("Marina Rate Type", SLIP_RESERVATION_TYPE_SEASONAL);
		
		//Search by select Lease in the drop down list of Marina Rate Type and verify result.
		searchByMarinaRateType(SLIP_RESERVATION_TYPE_LEASE);
		schedulePg.verifySearchResultInColumn("Marina Rate Type", SLIP_RESERVATION_TYPE_LEASE);
		
		//Search by select All in the drop down list of Marina Rate Type and verify result.
		searchByMarinaRateType(SLIP_RESERVATION_TYPE_ALL);
		schedulePg.verifySearchResultInColumn("Marina Rate Type", SLIP_RESERVATION_TYPE_ALL);
		
		fnm.logoutFinanceManager();
	}
	
	private void selectProductCategory(String category){
		schedulePg.selectProductCategory(category);
		ajax.waitLoading();
		schedulePg.waitLoading();
	}
	
	private void searchByMarinaRateType(String mRateType){
		logger.info("Search tax schedule by marina rate type.");
		schedulePg.selectMarinaRateType(mRateType);
		schedulePg.clickGo();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
	}
}
