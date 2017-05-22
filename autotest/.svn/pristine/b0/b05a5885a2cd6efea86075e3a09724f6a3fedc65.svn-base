package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.ticketsalesstatisticsreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case verify that search critial of ticket sale statistic report
 * @LinkSetUp:  none
 * @SPEC:[TC:006969] Ticket Sales Statistics Report - Selection Criteria   
 * @Task#: Auto-2273
 * @author Phoebe
 * @Date  July 23, 2014
 */
public class VerifyParkAndTourCriteria extends ResourceManagerTestCase{
	private String errMsg;
	private ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	private List<String> exptours = new ArrayList<String>();
	private String facilityId;
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//Verify the error message when there is no park support ticket product
		rm.verifyReportDate(rd.startDate, rd.endDate, errMsg);
		rm.logoutResourceManager();
		
		//Verify the critial element for ticket and tour critial
		this.setContracLocation("NRRS Contract", "Administrator/NRRS");
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//The park is not special, so there is only 'All' available for Tour
		rmCriteriaPg.setReportCriteria(rd);
		verifyTour();
		
		rd.facilityID = rm.getFacilityName(facilityId, schema);//"Carlsbad Caverns National Park Tours";
		exptours.addAll(this.tripFacilityName(rm.getAllTourNamesInParkLevel(schema, facilityId)));
		//The tour element should be the tour for the special park
		rmCriteriaPg.setReportCriteria(rd);
		verifyTour();
		rm.logoutResourceManager();
	}

	private void verifyTour() {
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
		List<String> tours = rmCriteriaPg.getToursElement();
		if(!tours.equals(exptours)){
			throw new ErrorOnPageException("Tours element is not correct", exptours.toString(), tours.toString());
		}
		logger.info("The avaliable tours is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator - Auto/South Carolina State Park Service";

		// initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Ticket Sales Statistics Report";
		
		rd.agencyID = "NPS";
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Thu Jul 3 2008";
		
		errMsg = "There are no Ticketing facilities accessible for your logged-in location level. Please access this report via a different Role/Location.";
		
		exptours.add("All");
		facilityId = "77813"; //Carlsbad Caverns National Park Tours
	}
	
	private void setContracLocation(String contract, String location){
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
	}
	
	private List<String> tripFacilityName(List<String> fNames){
		List<String> newFName = new ArrayList<String>();
		for(String name:fNames){
			newFName.add(name.trim());
		}
		return newFName;
	}
}
