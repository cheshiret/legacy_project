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
public class SingleStateAndMoreThanOnePgFacilities extends PhotoToolTestCase {
	private PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
	private String letterSelectorValue;

	public void execute() {
		//Login in with a valid account (Location: RA Contract)
		pt.invokeURL(url);
		pt.signIn(login);

		//Select the contract has single state and more than one page facilities
		verifySelectedContractWithSingleStates();
		pt.gotoFacilityListPage(facility.contract);

		//Verify no state drop down list but has state text, letter selector value, total facilities amount is more than one page (more than 25)
		facilityListPg.verifyNoStateDDLExisting();
		facilityListPg.verifyStateText(facility.contract);
		facilityListPg.verifyLetterSelectorValue(letterSelectorValue);
		facilityListPg.verifyTotalResultNumMoreThanOnePg();

		//Click letter selector and first facility to camp ground photos page, then back to facility list page
		String filterResultContent = pt.gotoCampgroundPhotosPage(StringUtil.EMPTY, StringUtil.EMPTY, letterFilter);
		pt.changeFacility();

		//Verify selected contract, no state drop down list but has state text, letter selector value, selected letter
		facilityListPg.verifyContract(facility.contract);
		facilityListPg.verifyNoStateDDLExisting();
		facilityListPg.verifyStateText(stateFilter);
		facilityListPg.verifyLetterSelectorValue(letterSelectorValue);
		facilityListPg.verifyLetterSelected(letterFilter);

		//verify filter result doesn't changed and facilities sort alphabetically in facility list
		facilityListPg.verifyFilterResultContent(filterResultContent);
		facilityListPg.verifyAlphabeticalSorting(facilityListPg.getFacilitiesInFirstPg());

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "SC";
		stateFilter = facility.contract;
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
		letterSelectorValue = "ALLABCDEFGHIJKLMNOPQRSTUVWXYZ#ALL";
		letterFilter = "A";
	}

	private void verifySelectedContractWithSingleStates(){
		List<String> states = web.getAllStatesInContractLevel(schema);
		if(states.size()!=1){
			throw new ErrorOnDataException("Please check the selected contract:"+facility.contract+" which doesn't have single states.");
		}
		logger.info("Successfully verify selected contract:"+facility.contract+" has single states:"+states.size());
	}
}
