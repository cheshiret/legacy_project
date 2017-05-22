package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * @Description: This case is used to verify when search by product category, steps including:
 *				 1. Select Slip as Product Category.
 * 				 2. Select Site as Product Category.
 * 				 3. Select Permit as Product Category.
 * 				 4. Select List as Product Category.
 * @Preconditions:none
 * @SPEC:Search Tax Schedule - Product Category [TC:049977]
 * @Task#: Auto-1441
 * @author Phoebe
 * @Date  Feb 7 2013
 */
public class VerifySearchResult_ProductCategory extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private static final String SLIP_PRODUCT_CATEGORY = "Slip";
	private static final String SITE_PRODUCT_CATEGORY = "Site";
	private static final String PERMIT_PRODUCT_CATEGORY = "Permit";
	private static final String LIST_PRODUCT_CATEGORY = "List";
	@Override
	public void execute() {
		// Login finance manager and go to tax schedule list page
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		
		//Select slip as product category
		selectProductCategory(SLIP_PRODUCT_CATEGORY);
		schedulePg.verifySearchResultInColumn("Product Category", SLIP_PRODUCT_CATEGORY);
		
		//Select site as product category
		selectProductCategory(SITE_PRODUCT_CATEGORY);
		schedulePg.verifySearchResultInColumn("Product Category", SITE_PRODUCT_CATEGORY);
		
		//Select permit as product category
		selectProductCategory(PERMIT_PRODUCT_CATEGORY);
		schedulePg.verifySearchResultInColumn("Product Category", PERMIT_PRODUCT_CATEGORY);
		
		//Select list as product category
		selectProductCategory(LIST_PRODUCT_CATEGORY);
		schedulePg.verifySearchResultInColumn("Product Category", LIST_PRODUCT_CATEGORY);
		
		fnm.logoutFinanceManager();
	}

	private void selectProductCategory(String category){
		schedulePg.selectProductCategory(category);
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
