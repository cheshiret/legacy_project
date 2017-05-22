package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case is used to verify when search by marina rate type, steps including:
 *				 1. Select 'List' as product category.
 * 				 2.	Select Slip in the drop down list of Applicable Product Category and click Go button.
 * @Preconditions:none
 * @SPEC:Search Tax Schedule - Applicable Product Category [TC:051565]
 * @Task#: Auto-1441
 * @author Phoebe
 * @Date  Feb 7 2013
 */
public class VerifySearchResult_List_ApplicableProductCategory extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private static final String LIST_PRODUCT_CATEGORY = "List";
	private static final String SLIP_APPLICABLE_PRODUCT_CATEGORY = "Slip";
	@Override
	public void execute() {
		// Login finance manager and go to tax schedule list page
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		
		//Select slip as product category
		selectProductCategory(LIST_PRODUCT_CATEGORY);
		schedulePg.verifySearchResultInColumn("Product Category", LIST_PRODUCT_CATEGORY);
				
		//Search by select Slip in the drop down list of applicable product category and verify result.
		searchByApplicableProductCategory(SLIP_APPLICABLE_PRODUCT_CATEGORY);
		this.verifySearchResult();
		
		fnm.logoutFinanceManager();
	}
	
	private void verifySearchResult() {
		boolean passed = true;
		List<String> colList = schedulePg.getColumnByName("Tax Schedule ID");
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		for(String schemaId:colList){
			String query = "select applicable_prd_cat_id as appId from P_TAX_SCHEDULE where id=" + schemaId;
			String appIdInDB = db.executeQuery(query, "appId", 0);
			if(!appIdInDB.equals("20")){
				passed = false;
				logger.error("The tax schema:" + schemaId + " is not with Applicable Product Category is Slip");
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Not all the List Tax Schedule with Applicable Product Category is Slip, check log above!");
		}
	}

	private void selectProductCategory(String category){
		schedulePg.selectProductCategory(category);
		ajax.waitLoading();
		schedulePg.waitLoading();
	}
	
	private void searchByApplicableProductCategory(String appPrdCat){
		logger.info("Search tax schedule by marina rate type.");
		schedulePg.selectApplicableProductCategory(appPrdCat);
		schedulePg.clickGo();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
	}

}
