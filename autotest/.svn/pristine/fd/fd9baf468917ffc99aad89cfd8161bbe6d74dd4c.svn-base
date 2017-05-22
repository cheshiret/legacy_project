package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.posfee;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:This case is used to verify ui for pos fee schedule
 * @LinkSetUp:  none
 * @SPEC:[TC:109344] POS Fee Schedule -- UI details 
 * @Task#: Auto-2121
 * @author Phoebe Chen
 * @Date  April 28, 2014
 */
public class VerifyUI extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage detailPg=FinMgrFeeSchDetailPage.getInstance();
	private List<String> purchaseType = new ArrayList<String>();
	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
		
	    //Add two pos Fee schedule data, and active them, just with the purchase type is different
	    fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
	    veifyTheAviableValueForPurchaseType();
	    this.cancelFromSetFeeSchedule();
	    
	    //If product  category is 'Gift card', there is no purchase type
	    schedule.productCategory = "GiftCard";
	    fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
	    verifyPurchaseTypeSectionIsNotExist();
	    this.cancelFromSetFeeSchedule();
	    
	    //If product  category is "POS" but fee type is 'Transaction Fee', there is no purchase type
	    schedule.productCategory = "POS";
	    schedule.feeType = "Transaction Fee";
	    fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
	    verifyPurchaseTypeSectionIsNotExist();
	    this.cancelFromSetFeeSchedule();
	    
	    fnm.logoutFinanceManager();
	}
	
	private void verifyPurchaseTypeSectionIsNotExist(){
		if(detailPg.isPurchaseTypeSectionExist()){
			throw new ErrorOnPageException("The purchase type should not exist!");
		}
		logger.info("The purchase type is correct as not show");
	}

	private void cancelFromSetFeeSchedule() {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		detailPg.clickCancel();
		ajax.waitLoading();
		feeMainPg.waitLoading();
	}

	private void veifyTheAviableValueForPurchaseType() {
		List<String> actElement = detailPg.getPuchaseTypeElements();
		if(!purchaseType.containsAll(actElement)||!actElement.containsAll(purchaseType)){
			throw new ErrorOnPageException("The element for purchase type is not correct", purchaseType.toString(), actElement.toString());
		}
		logger.info("The element for purchase type is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "SC Contract";
	  	login.location = "Administrator/South Carolina State Park Service";
	  	
	    //initialize the first fee schedule data
	  	schedule.productCategory = "POS";
	  	schedule.feeType ="POS Fee";
	  	schedule.locationCategory = "Park";
	  	schedule.location = "AIKEN";
	  	
	  	purchaseType.add("All");
	  	purchaseType.add("Original");
	  	purchaseType.add("Replacement");
	}

}
