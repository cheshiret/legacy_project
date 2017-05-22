package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.reservationmethodsreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
/**
 * @Description: This case verify that search critial when to show of reservation methods report
 * @LinkSetUp:  none
 * @SPEC:[TC:070779] Check Slection Criteria - Include Loops
 *       [TC:070780] Check Slection Criteria - Slip Reservation Type    
 * @Task#: Auto-2270
 * @author Phoebe
 * @Date  July 15, 2014
 */
public class VerifySearchCriteriaDisplay extends ResourceManagerTestCase{
	private ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		
		//Verify when the product category is "All"
		verifyFieldsExist(true, false);
		
		//Verify when the product category is "Site"
		selectPrdCategory("Site");
		verifyFieldsExist(true, false);
		
		//Verify when the product category is "Permit"
		selectPrdCategory("Permit");
		verifyFieldsExist(false, false);
		
		//Verify when the product category is "Slip"
		selectPrdCategory("Slip");
		verifyFieldsExist(true, true);
		
		//Verify when the product category is "Ticket"
		selectPrdCategory("Ticket");
		verifyFieldsExist(false, false);
		
		rm.logoutResourceManager();
	}
	
	public void verifyFieldsExist(boolean isLoopExist, boolean isSlipResTypExist){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Default value Product Category:", isLoopExist, rmCriteriaPg.isIncludeLooopsFieldExist());
		passed &= MiscFunctions.compareResult("Default value include loops:", isSlipResTypExist, rmCriteriaPg.isSlipReservationTypeExist());
		if(!passed){
			throw new ErrorOnDataException("Not all the check point is correct, please check the log above!");
		}
		logger.info("The fields exist status is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "All";
		rd.reportName = "Reservation Methods Report";
	}
	
	private void selectPrdCategory(String prdCat){
		rmCriteriaPg.selectProductCategory(prdCat);
		ajax.waitLoading();
		rmCriteriaPg.waitLoading();
	}
}
