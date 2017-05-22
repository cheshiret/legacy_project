package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1.verify assign product category info when add Lottery transaction fee schedule for Marina
 *              2.verify assign product info when select location is park level when add Lottery transaction fee schedule for Marina
 *              3.verify assign product info when select location is agency level when add Lottery transaction fee schedule for Marina
 *              4.verify product category auto filter
 *              5.verify fee transaction method
 * Blocked by defect DEFECT-38698
 * @Preconditions:
 * @SPEC:Add Fee Schedule - UI [TC:042718]
 * @Task#:AUTO-1346
 * 
 * @author vzhang
 * @Date  Nov 19, 2012
 */

public class VerifyAddMarinaLotteryTranFee_UI extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private List<String> appPrdCetegoryList;
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		//verify assign product category info
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		this.verifyAssignProdCategoryInfo(appPrdCetegoryList);
		
		//verify assign product info when select location is park level
		schedule.assignPrdCategory = "Slip";
		this.verifyAssignProductInfo(schedule.assignPrdCategory, schedule.locationID, schema);
		this.gotoFeeScheduleMainPage();
		
		//verify assign product info when select location is agency level
		schedule.locationID = "550001";
		schedule.location = fnm.getFacilityName(schedule.locationID, schema);//NC Division of Parks and Recreation
		schedule.locationCategory = "Agency";
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		this.verifyAssignProductInfo(schedule.assignPrdCategory, schedule.locationID, schema);
		
		//verify product category auto filter
		schedule.product = "TestSlipLottery";
		this.verifyPrdCategoryFilter(schedule.product, schedule.assignPrdCategory);
		
		//verify fee transaction method
		String defaultFeeTranMethod = "Per Transaction";
		this.verifyFeePerType(defaultFeeTranMethod);
		
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
		schedule.locationID = "552903";
		schedule.location = fnm.getFacilityName(schedule.locationID, schema);//Jordan Lake State Rec Area

	  	schedule.productCategory = "Lottery";
	  	schedule.feeType = "Transaction Fee";
	  	
	  	appPrdCetegoryList = new ArrayList<String>();
	  	appPrdCetegoryList.add("All");
	  	appPrdCetegoryList.add("Permit");
	  	appPrdCetegoryList.add("Site");
	  	appPrdCetegoryList.add("Slip");
	  	appPrdCetegoryList.add("Ticket");
	}
	
	private void verifyAssignProdCategoryInfo(List<String> expAssignPrdCatElements){
		String actAssignPrdCatDefaultValue = detailsPg.getAssignProdCategory();
		if(!actAssignPrdCatDefaultValue.equalsIgnoreCase("All")){
			throw new ErrorOnPageException("The assign product category list default value should be 'All', " +
					"but actullay is " + actAssignPrdCatDefaultValue);
		}else logger.info("The assign product category list default value is 'All'");
		
		List<String> actAssignPrdCatElements = detailsPg.getAssignProdCategoryElements();
		
		if(actAssignPrdCatElements.size() != expAssignPrdCatElements.size()){
			throw new ErrorOnPageException("The assign product category list info is not correct.");
		}
		
		for(String assignPrdCatElement : expAssignPrdCatElements){
			if(!actAssignPrdCatElements.contains(assignPrdCatElement)){
				throw new ErrorOnPageException("The assign product category list should contain " + assignPrdCatElement);
			}else logger.info("The assign product category list contained " + assignPrdCatElement);
		}
	}
	
	private void verifyAssignProductInfo(String assignCategory, String selectLocation, String schema){
		detailsPg.selectAssignProdCategory(assignCategory);
		detailsPg.waitLoading();
		String actPrdDefaultValue = detailsPg.getAssignProduct();
		if(!actPrdDefaultValue.equalsIgnoreCase("All")){
			throw new ErrorOnPageException("The assign product list default value should be 'All', " +
					"but actullay is " + actPrdDefaultValue);
		}else logger.info("The assign product list default value should be 'All'");
		
		List<String> actPrdElements = detailsPg.getAssignProductElements();
		List<String> expAssignPrdElements = this.getAssignProductListInfoFromDB(schema, selectLocation);
		
		actPrdElements.remove(0);//remove all
		if(actPrdElements.size() != expAssignPrdElements.size()){
			throw new ErrorOnPageException("The assign product list info is not correct.");
		}
		
		for(String assignPrdElement : expAssignPrdElements){
			if(!expAssignPrdElements.contains(assignPrdElement)){
				throw new ErrorOnPageException("The assign product list should contain " + assignPrdElement);
			}else logger.info("The assign product list contained " + assignPrdElement);
		}
		
	}
	
	private List<String> getAssignProductListInfoFromDB(String schema, String location){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

		db.resetSchema(schema);
		
		String query = "select prd_name from p_prd where product_cat_id = 9 and appl_prd_cat_id = 20 and loc_id in ("
			+ "select id from d_loc where cd like '%|" + location + "|%' "
			+ "and level_num<=40 and level_num >=(select level_num from d_loc where id = " + location + "))";
		
		List<String> result = db.executeQuery(query, "prd_name");
		
		return result;
	}
	
	private void verifyPrdCategoryFilter(String prd,String prdCategory){
		detailsPg.selectAssignProdCategory("All");
		detailsPg.waitLoading();
		detailsPg.selectAssignProduct(prd);
		detailsPg.waitLoading();
		
		String actAssignPrdCatSelectValue = detailsPg.getAssignProdCategory();
		if(!actAssignPrdCatSelectValue.equalsIgnoreCase(prdCategory)){
			throw new ErrorOnPageException("Expect assign product category should be " + prdCategory +
					", but acttully is "  + actAssignPrdCatSelectValue + ", when select product = " + prd);
		}logger.info("Assign product category is " + prdCategory + ",when select product = " + prd);
	}
	
	private void gotoFeeScheduleMainPage(){
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		
		detailsPg.clickCancel();
		feeMainPg.waitLoading();
	}
	
	private void verifyFeePerType(String expDefaultFeeTranMethod){
		String actDefaultFeeTranMethod = detailsPg.getTransMethod();
		if(!actDefaultFeeTranMethod.equalsIgnoreCase(expDefaultFeeTranMethod)){
			throw new ErrorOnPageException("Expect fee transaction method default value should be " + expDefaultFeeTranMethod
					+", but actullay is " + actDefaultFeeTranMethod);
		}else logger.info("Fee transaction method default value is " + expDefaultFeeTranMethod);
		
		int actObjSize = detailsPg.getTransMethodObjectSize();
		if(actObjSize != 1){
			throw new ErrorOnPageException("UI should have one trans method should have one raido button.");
		}else logger.info("UI have one trans method should have one raido button.");
	}

}
