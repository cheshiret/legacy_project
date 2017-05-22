package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.reservationmethodsreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
/**
 * @Description: This case verify that search critial of reservation methods report
 * @LinkSetUp:  none
 * @SPEC:[TC:070777] Check Slection Criteria - Product Category
 * 		 [TC:070779] Check Slection Criteria - Include Loops
 *       [TC:070780] Check Slection Criteria - Slip Reservation Type    
 *       [TC:070782] Check Slection Criteria - Including Registrations
 *       [TC:070783] Check Slection Criteria - Include In-State/Out-of-State    
 *       [TC:070784] Check Slection Criteria - Include Total Columns  
 * @Task#: Auto-2270
 * @author Phoebe
 * @Date  July 15, 2014
 */
public class VerifyDropdownListEle extends ResourceManagerTestCase{
	private ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	private List<String> exp_prdCat = new ArrayList<String>(), exp_inLoops = new ArrayList<String>(),
			exp_registrations = new ArrayList<String>(), exp_slipResType = new ArrayList<String>(),
			exp_incInOutState = new ArrayList<String>(), exp_inTotals = new ArrayList<String>();
	private String def_prdCat = "All", def_includeLoops = "No", def_slipResType = "Transient",
			def_inRegistrations = "Yes", def_inInOrOutstate = "No", def_incluTotal = "No";
	private boolean passed = true;
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.verifyDropdownListAvailableEle();
		
		this.verifyDropdownListDefaultValue();
	
		
		rm.logoutResourceManager();
	}
	
	private void verifyDropdownListDefaultValue() {
		passed = true;
		//Verify product category
		passed &= MiscFunctions.compareResult("Default value include loops:", def_includeLoops, rmCriteriaPg.getIncludeLoopsValue());
		passed &= MiscFunctions.compareResult("Default value include registrations:", def_inRegistrations, rmCriteriaPg.getIncludeRegistrationsValue());
		passed &= MiscFunctions.compareResult("Default value include in/out of state:", def_inInOrOutstate, rmCriteriaPg.getIncludeInOutofStateValue());
		passed &= MiscFunctions.compareResult("Default value include total column:", def_incluTotal, rmCriteriaPg.getIncludeTotalsValue());
		passed &= MiscFunctions.compareResult("Default value include slip reservation type:", def_slipResType, rmCriteriaPg.getSlipReservationTypeValue());
		
		if(!passed){
			throw new ErrorOnDataException("Not all the check point is correct, please check the log above!");
		}
		logger.info("The default value are all correct!");
	}

	private void verifyDropdownListAvailableEle(){
		passed = true;
		//Verify product category
		passed &= this.verifyTheListEle("Product Category", exp_prdCat, rmCriteriaPg.getAllProductCategory());
		//Verify include loops
		passed &= this.verifyTheListEle("Include Loops", exp_inLoops, rmCriteriaPg.getAllIncludeLoops());
		//Verify include registrations
		passed &= this.verifyTheListEle("Include Registrations", exp_registrations, rmCriteriaPg.getIncludeRegistrations());
		//Verify include in/out of state
		passed &= this.verifyTheListEle("Include in/out of state", exp_incInOutState, rmCriteriaPg.getIncludeInOutofState());
		//Verify include total
		passed &= this.verifyTheListEle("Include totals cloumn", exp_inTotals, rmCriteriaPg.getIncludeTotals());
		//Verify slip reservation type
		selectSlipCategory();
		passed &= this.verifyTheListEle("Slip reservation type", exp_slipResType, rmCriteriaPg.getSlipReservationType());
		
		if(!passed){
			throw new ErrorOnDataException("Not all the check point is correct, please check the log above!");
		}
		logger.info("The element is correct for drop down list!");
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
		
		//Set element for product category
		exp_prdCat.add("All");
		exp_prdCat.add("Site");
		exp_prdCat.add("Ticket");
		exp_prdCat.add("Permit");
		exp_prdCat.add("Slip");
		
		//Set element for include loops
		exp_inLoops.add("Yes");
		exp_inLoops.add("No");
		
		//Set element for include regression
		exp_registrations.add("Yes");
		exp_registrations.add("No");

		//Set element for slip reservation type
		exp_slipResType.add("Transient");
		exp_slipResType.add("Seasonal");
		exp_slipResType.add("Lease");
		exp_slipResType.add("All");
		
		//Set element for include in out of state
		exp_incInOutState.add("Yes");
		exp_incInOutState.add("No");
		
		//Set include total column
		exp_inTotals.add("Yes");
		exp_inTotals.add("No");
	}
	
	private void selectSlipCategory(){
		rmCriteriaPg.selectProductCategory("Slip");
		ajax.waitLoading();
		rmCriteriaPg.waitLoading();
	}
	
	private boolean verifyTheListEle(String field, List<String> expEle, List<String> actEle){
		boolean result = true;
		if(! (expEle.containsAll(actEle)&&actEle.containsAll(expEle))){
			result = false;
			logger.info("The expect element is not correct for " + field + ", expect:" + expEle.toString() + ",but actually is:" + actEle.toString());
		}else{
			logger.info("The element for " + field + " is correct!");
		}
		return result;
	}
}
