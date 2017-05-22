package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.tourfeeanalysisreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case verify that search critial of tour fee analysis report
 * @LinkSetUp:  none
 * @SPEC:[TC:006971]Tour Fee Analysis Report - Selection Criteria     
 * @Task#: Auto-2273
 * @author Phoebe
 * @Date  July 23, 2014
 */
public class VerifyParkAndTourCriteria  extends ResourceManagerTestCase{
	private ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	private List<String> exptours = new ArrayList<String>(), expFacilities = new ArrayList<String>();
	private String facilityId;
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//Agency (NARA) ->Region -> Park
		rmCriteriaPg.setReportCriteria(rd);
		verifyLocationHirachyForAgency(true, false, false, true);
		
		//Agency (USFS) -> Region -> Forest-> Ranger Dirtrict -> Park.
		rd.agencyID = "USFS";
		rmCriteriaPg.setReportCriteria(rd);
		verifyLocationHirachyForAgency(true, true, true, true);
		verifyTour();
		//Verify all the park have product category of ticketing
		rmCriteriaPg.setReportCriteria(rd);
		verifyParkInDropdownList();
		
		rd.facilityID = rm.getFacilityName(facilityId, schema);//"SWEET HOME NATURE AND HERITAGE TOURS";
		exptours.addAll(this.tripFacilityName(rm.getAllTourNamesInParkLevel(schema, facilityId)));
		//The tour element should be the tour for the special park
		rmCriteriaPg.setReportCriteria(rd);
		verifyTour();
		rm.logoutResourceManager();
	}
	
	private void verifyParkInDropdownList() {
		List<String> actParks = rmCriteriaPg.getParkElement();
		if(!actParks.equals(expFacilities)){
			throw new ErrorOnDataException("The facility is not correct!", expFacilities.toString(), actParks.toString());
		}
		logger.info("All the check point are correct!");
	}

	private void verifyLocationHirachyForAgency(boolean regionEx, boolean forestEx, boolean rangerDirtrictEx, boolean parkEx){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Is region exist:", regionEx, rmCriteriaPg.isRegionDpExist());
		passed &= MiscFunctions.compareResult("Is forest exist:", forestEx, rmCriteriaPg.isForestDpExist());
		passed &= MiscFunctions.compareResult("Is ranger dirtrict exist:", rangerDirtrictEx, rmCriteriaPg.isRangerDistrictExist());
		passed &= MiscFunctions.compareResult("Is park exist:", parkEx, rmCriteriaPg.isParkDpExist());
		if(!passed){
			throw new ErrorOnDataException("Not all the check point is correct, please check the log above!");
		}
		logger.info("All the check point are correct!");
	}

	private void verifyTour() {
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
		List<String> tours = rmCriteriaPg.getToursElement();
		if(!(tours.containsAll(exptours)&&exptours.containsAll(tours))){
			throw new ErrorOnPageException("Tours element is not correct", exptours.toString(), tours.toString());
		}
		logger.info("The avaliable tours is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "Tour Fee Analysis Report";
		
		rd.agencyID = "NARA";
		
		exptours.add("All");
		facilityId = "72411"; //SWEET HOME NATURE AND HERITAGE TOURS
		
		expFacilities.add("All");
		expFacilities.addAll(this.getFacilitiesSupportTicket());
		
	}
	
	private List<String> getFacilitiesSupportTicket() {
		AwoDatabase db = AwoDatabase.getInstance();
		db.resetSchema(schema);
		String sql = "select name from d_loc where id in (select loc_id from D_LOC_PRD_CAT where prd_grp_cat_id='6') and cd like '|1|70903|%' " +
				"and id in (select distinct(park_id) from p_prd where product_cat_id=6 and active_ind=1) order by name";
		logger.debug("Execute query: " + sql);
		List<String> values = db.executeQuery(sql, "name");

		db.disconnect();
		return values;
	}
	
	private List<String> tripFacilityName(List<String> fNames){
		List<String> newFName = new ArrayList<String>();
		for(String name:fNames){
			newFName.add(name.trim());
		}
		return newFName;
	}
}
