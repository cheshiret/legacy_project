/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:check point:1. verify product list filter after choose product group
 *                          2. compare product group, dock, product content after choose product
 * @Preconditions:
 * @SPEC:TC:042196
 * @Task#:AUTO-1335
 * 
 * @author szhou
 * @Date  Nov 14, 2012
 */
public class VerifyAssigmentSelection_PrdGrpAndPrd_Slip extends
		FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		this.gotoAddRaFeeThresholdPg();
		// verify product list filter after select product group
		this.verifyProductFilter(schedule.productGroup);
		schedule.productGroup="All";
		this.verifyProductFilter(schedule.productGroup);
		
		// verify product group selection
		schedule.productGroup="Full attributes";
		this.verifyProductGroup(schedule.product);
		schedule.product="All";
		this.verifyProductGroup(schedule.product);

		fnm.logoutFinanceManager();
	}

	private void verifyProductGroup(String prd) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		detailPg.selectProduct(prd);
		ajax.waitLoading();
		detailPg.waitLoading();
		String dock = detailPg.getDock();
		String prdGroup = detailPg.getProductGroup();
		String product = detailPg.getProduct();
		boolean result = true;
		result &= MiscFunctions.compareResult("Dock", schedule.dock, dock);
		result &= MiscFunctions.compareResult("Product Group",
				schedule.productGroup, prdGroup);
		result &= MiscFunctions.compareResult("Product", schedule.product,
				product);
		if (!result) {
			throw new ErrorOnPageException(
					"Product group filter is not correct.Please find the error in the log file...");
		}

	}

	private void verifyProductFilter(String prdGrp) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		detailPg.selectProductGroup(prdGrp);
		ajax.waitLoading();
		detailPg.waitLoading();
		List<String> productListUI = detailPg.getProductList();
		List<String> productList =null;
		if("All".equals(prdGrp)){
			productList = this.getProductListByLocation(schedule.location);
		}else{
			productList = this.getProductListByGroupAndLocation(
					prdGrp, schedule.location);
		}
		productListUI.remove(productListUI.indexOf("All"));
		if (productList.size() != productListUI.size()) {
			throw new ErrorOnPageException(
					"product list filter is not correct. Expect size is "
							+ productList.size() + ",but actually size is"
							+ productListUI.size());
		}

	}

	private List<String> getProductListByLocation(String loc) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);

		String queryString = "select * from p_prd INNER JOIN d_loc ON d_loc.id=p_prd.park_id "
				+ "where d_loc.name='" + loc + "' and p_prd.active_ind='1' and prd_rel_type !=3";
		List<String> resultList = db.executeQuery(queryString, "PRD_NAME");

		return resultList;

	}

	private List<String> getProductListByGroupAndLocation(String grp, String loc) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);

		String queryString = "select * from p_prd INNER JOIN P_PRD_GRP on  P_PRD_GRP.PRD_GRP_ID=p_prd.prd_grp_id "
				+ "INNER JOIN d_loc ON d_loc.id=p_prd.park_id "
				+ "where d_loc.name='"
				+ loc
				+ "' AND P_PRD_GRP.PRD_GRP_NAME='"
				+ grp  + "' and prd_rel_type !=3" ;
		List<String> resultList = db.executeQuery(queryString, "PRD_NAME");

		return resultList;

	}

	private void gotoAddRaFeeThresholdPg() {
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		logger.info("Start to Add New RA Fee Threshold Schedule.");

		searchPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocationPg.selectLocation(schedule.location);
		detailPg.waitLoading();
		detailPg.selectProdCategory(schedule.productCategory);
		detailPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		// initialize schedule data
		schedule.location = "Mayo River State Park";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.productGroup="Full attributes";
		schedule.product="ATT01-slip test 01";
		schedule.dock="AutoDock";
	}

}
