package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.dailyreconciliationdetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case verify that search critial of daily reconciliation detail report
 * @LinkSetUp:  none
 * @SPEC:[TC:015316] Selection Criteria    
 * @Task#: Auto-2274
 * @author Phoebe
 * @Date  July 23, 2014
 */
public class VerifyStationAndTour extends ResourceManagerTestCase{
	private String errMsg;
	private ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//Verify the error message when there is no park support ticket product
		this.setReportCriteriaAndClickOk();
		verifyErrorMessage();
		rm.logoutResourceManager();
		
		//Login to contract with ticket park
		this.setContracLocation();
		this.setUpReportInfo();
		
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//Verify default value for start date
		verifyStartDate();
		//Verify select facility with station, the station will show
		rmCriteriaPg.setReportCriteria(rd);
		verifyStationCritialExistOrNot(true);
		
		//Verify select facility without station, the station will not show
		rd.facilityID = rm.getFacilityName("77811", schema);   //WASHINGTON MONUMENT
		rmCriteriaPg.setReportCriteria(rd);
		verifyStationCritialExistOrNot(false);
		rm.logoutResourceManager();
	}
	
	private void verifyStartDate() {
		String actStartDate = rmCriteriaPg.getStartDate();
		String expStartDate =  DateFunctions.getToday(DataBaseFunctions.getParkTimeZone(schema, rm.getFacilityName("77811", schema)));
		if(DateFunctions.compareDates(actStartDate, expStartDate)!=0){
			throw new ErrorOnPageException("The default start date is not correct", expStartDate, actStartDate);
		}
		logger.info("The default start date is correct!");
	}

	private void verifyStationCritialExistOrNot(boolean exist) {
		if(rmCriteriaPg.isStationDpExist()!=exist){
			throw new ErrorOnPageException("The station drop down list statue is not correct", exist, rmCriteriaPg.isStationDpExist());
		}
		logger.info("The station drop down list is correct!");
	}

	private void verifyErrorMessage(){
		String actMsg = rmCriteriaPg.getErrorMsg();
		if(!actMsg.matches(errMsg)){
			throw new ErrorOnPageException("The error message is not correct!", errMsg, actMsg);
		}
		logger.info("The error message is correct!");
	}
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator - Auto/South Carolina State Park Service";

		// initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Daily Reconciliation Detail Report";
		
		errMsg = "There are no Ticketing facilities accessible for your logged-in location level. Please access this report via a different Role/Location.";
	}
	
	private void setContracLocation(){
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
	}
	
	private void setUpReportInfo(){
		rd.agencyID = "NPS";
		rd.facilityID = rm.getFacilityName("72777", schema);   //VOYAGEURS NATIONAL PARK TOURS
		rd.regionID = "All";
	}
	
	private void setReportCriteriaAndClickOk(){
		rmCriteriaPg.setReportCriteria(rd);
		rmCriteriaPg.clickOk();
		rmCriteriaPg.waitLoading();
	}

}
