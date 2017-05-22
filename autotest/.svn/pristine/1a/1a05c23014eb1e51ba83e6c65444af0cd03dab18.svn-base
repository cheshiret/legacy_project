package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.retrievefacilityphotoinfo.racontract;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC:
 * 1."Contract" drop down list [TC:020484] 
 * 2."State" drop down list [TC:020504] 
 * 3.Starting With Character" [TC:020505] 
 * 4.Result Summary Bar and Paging Control [TC:020506] 
 * 5.Facility Result Table [TC:024753] 
 * @Task#:AUTO-1409
 * 
 * @author SWang
 * @Date  Feb 21, 2013
 */
public class MultiStatesAndMoreThanOnePgFacilities extends PhotoToolTestCase {
	private PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
	private String defaultState;

	public void execute() {
		//Login in with a valid account (Location: RA Contract)
		pt.invokeURL(url);
		pt.signIn(login);

		//Select the contract has multiple state and more than one page facilities
		verifySelectedContractWithMultiStates();
		pt.gotoFacilityListPage(facility.contract);

		//Verify state default value, states sort alphabetically and total facilities amount is more than one page (more than 25)
		facilityListPg.verifyState(defaultState);
		facilityListPg.verifyAlphabeticalSorting(facilityListPg.getStatesExcludedTheFirst());
		facilityListPg.verifyTotalResultNumMoreThanOnePg();

		//Verify page results with next and previous link
		facilityListPg.verifyPageResults(facilityListPg.generatePageResultsInFirstPage(facilityListPg.getTotalResultNum()));
		facilityListPg.navigatePage(true);
		facilityListPg.verifyPageResults(facilityListPg.generatePageResults(facilityListPg.getTotalResultNum(), 2));
		facilityListPg.navigatePage(false);
		facilityListPg.verifyPageResults(facilityListPg.generatePageResultsInFirstPage(facilityListPg.getTotalResultNum()));

		//Click letter selector and first facility to camp ground photos page, then back to facility list page
		pt.gotoCampgroundPhotosPage(StringUtil.EMPTY, stateFilter, letterFilter);
		pt.changeFacility();

		//Verify selected contract, state, letter
		facilityListPg.verifyContract(facility.contract);
		facilityListPg.verifyState(stateFilter);
		facilityListPg.verifyLetterSelected(letterFilter);

		//Verify facilities with selected state in facility list
		facilityListPg.verifyStateInFacilityList(stateFilter);

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		stateFilter = "AR";
		letterFilter = "B";
		defaultState = "All States";
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
	}

	private void verifySelectedContractWithMultiStates(){
		List<String> states = web.getAllStatesInContractLevel(schema);
		if(states.size()<2){
			throw new ErrorOnDataException("Please check the selected contract:"+facility.contract+" which doesn't have multiple states.");
		}
		logger.info("Successfully verify selected contract:"+facility.contract+" has multiple states:"+states.size());
	}
}
