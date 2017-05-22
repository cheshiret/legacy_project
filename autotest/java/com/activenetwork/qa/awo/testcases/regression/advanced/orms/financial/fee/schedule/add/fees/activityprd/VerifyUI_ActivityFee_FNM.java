package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.activityprd;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify ui for activity during add activity fee in finance manager
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=160
 * @SPEC:[TC:109717] Add Activity Fee Schedule for Activity Product  
 * @Task#: Auto-2135
 * @author Phoebe Chen
 * @Date  April 22, 2014
 */
public class VerifyUI_ActivityFee_FNM extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage detailPg = FinMgrFeeSchDetailPage.getInstance();
	private String locId;
	private List<String> splitIntoNumList = new ArrayList<String>();
	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		this.verifyProductGroup();
		
		this.verifyProductForGroup();
		
		this.verifyAccountSection();
		
		this.verifyTaxAvailable();
		
		cancelFromAddFeeSchedule();
		
		fnm.logoutFinanceManager();
	}

	private void cancelFromAddFeeSchedule() {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
		detailsPg.clickCancel();
		ajax.waitLoading();
		feeMainPg.waitLoading();
	}

	private void verifyTaxAvailable() {
		List<String> taxes = fnm.getTaxForFeeSchedule(schema, FEETYPE_ACTIVITYFEE);
		List<String> actTaxes = detailPg.taxNames();
		if(!taxes.equals(actTaxes)&&actTaxes.equals(taxes)){
			throw new ErrorOnPageException("The taxes available is not correct", actTaxes.toString(), taxes.toString());
		}
		logger.info("The taxes available is correct!");
	}

	private void verifyAccountSection() {
		boolean passed = true;
		String[] splitType = detailPg.getAccountSplitTypes();
		if(splitType.length!=2||!splitType[0].equalsIgnoreCase("Percent")||!splitType[1].equalsIgnoreCase("Amount")){
			logger.info("Account split by type is not correct:"+ "Percent Amount" + ", but actually is:" + splitType.toString());
		}
		List<String> actNums = detailPg.getSplitIntoNums();
		if(!actNums.equals(splitIntoNumList)){
			passed = false;
			logger.info("Split into numbers is not correct, expect:"+ splitIntoNumList.toString() + ", but actually is:" + actNums.toString());
		}
		if(!passed){
			throw new ErrorOnPageException("The ui for accounts section may not correct, please check log above!");
		}
		logger.info("The acccounts section is correct!");
	}

	private void verifyProductForGroup() {
		//Verify the product in list
		detailPg.selectAssignProdGroup(schedule.productGroup);
		ajax.waitLoading();
		detailPg.waitLoading();
		List<String> actPrds = detailPg.getAvailableProductsByPrefix(schedule.product.split(" ")[0]);
		List<String[]> dbPrds = fnm.getProductNameUsingFeeSchdDetailPg(schema, PRDCAT_ACTIVITY, PRDSUBCAT_PRDTYPE_ACTIVITY, locId, locId, true, false, schedule.productGroup);
		List<String> dbPrdIds = new ArrayList<String>();
		for(int i=0; i<dbPrds.size(); i++){
			dbPrdIds.add(dbPrds.get(i)[0] + "(" + dbPrds.get(i)[1] + ")");
		}
		if(!actPrds.equals(dbPrdIds)){
			throw new ErrorOnPageException("Product list content is not correct",	dbPrdIds.toString(),  actPrds.toString());
		}
		logger.info("Aviable product element is correct!");
	}

	private void verifyProductGroup() {
		List<String> actProductGroups = detailPg.getAssignProdGroupElements();
		List<String[]> dbProductGroups = fnm
				.getProductGroupNameUsingFeeSchdDetailPgFromDB(schema, PRDCAT_ACTIVITY, PRDSUBCAT_PRDTYPE_ACTIVITY);
		if (actProductGroups.containsAll(dbProductGroups)&&dbProductGroups.containsAll(actProductGroups)) {
			throw new ErrorOnPageException("Product group is not correct", dbProductGroups.toString(),	actProductGroups.toString());
		}
		logger.info("Aviable product group element is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		// initialize login finance manager loginInfo
		locId = "1";
		login.contract = "MS Contract";
		String facility = fnm.getFacilityName(locId, schema);
		login.location = "Administrator/" + facility;

		schedule.location = facility;
		schedule.locationCategory = "Contract";
		schedule.productCategory = "Activity"; //Do no change
		schedule.feeType = "Activity Fee";
		
		schedule.productGroup = "FeeAddEditGroup";
		schedule.product = "Activity fee 13";
				
		for(int i=1;i<11;i++){
			splitIntoNumList.add(String.valueOf(i));
		}
	}

}
