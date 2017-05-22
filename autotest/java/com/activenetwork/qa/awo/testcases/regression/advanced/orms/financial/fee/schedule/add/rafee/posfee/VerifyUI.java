package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.posfee;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify ui for pos fee schedule
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 * @SPEC:[TC:109346]RA Fee Schedule -- UI details -- for POS Product Category  
 * @Task#: Auto-2121
 * @author Phoebe Chen
 * @Date  April 28, 2014
 */
public class VerifyUI extends FinanceManagerTestCase {
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage.getInstance();
	private List<String> purchaseType = new ArrayList<String>();
	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    fnm.gotoRaFeeSchedulePage();
	    
	    //add new RA Fee Schedule
	   fnm.addNewRaFeeScheduleToDetailPg(schedule);
	   veifyTheAviableValueForPurchaseType();
	   
	   this.editSubCategory("Subscription Sale");
	   verifyPurchaseTypeSectionIsNotExist();
	   
	   this.editSubCategory("Donation");
	   verifyPurchaseTypeSectionIsNotExist();
	   
	   fnm.logoutFinanceManager();
	}

	private void verifyPurchaseTypeSectionIsNotExist(){
		if(detailPg.isPurchaseTypeSectionExist()){
			throw new ErrorOnPageException("The purchase type should not exist!");
		}
		logger.info("The purchase type is correct as not show");
	}
	
	private void editSubCategory(String subCate) {
		detailPg.selectPrdSubCategory(subCate);
		ajax.waitLoading();
		detailPg.waitLoading();
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
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
	  	
	  	 //initialize schedule data
	  	schedule.locationCategory = "Park";
	  	schedule.location = fnm.getFacilityName("10137", schema); //CHARLES TOWNE LANDING
	  	schedule.productCategory="POS";
	  	
	  	schedule.productSubCategory="POS Sale";
	  	
	  	purchaseType.add("All");
	  	purchaseType.add("Original");
	  	purchaseType.add("Replacement");
	}

}
