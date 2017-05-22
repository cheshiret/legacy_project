package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.retrievefacilityphotoinfo.racontract;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityListPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectFacilityPage;
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
 * @Date  Feb 20, 2013
 */
public class SingleStateAndWithinOnePgFacilities extends PhotoToolTestCase {
	private PhotoToolSelectFacilityPage selectFacilityPg = PhotoToolSelectFacilityPage.getInstance();
	private PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
	private String initialContractValue;

	public void execute() {
		//Login in with a valid account (Location: RA Contract)
		pt.invokeURL(url);
		pt.signIn(login);

		//Verify default Contract value and they sort alphabetically
		selectFacilityPg.verifyContract(initialContractValue);
		selectFacilityPg.verifyAlphabeticalSorting(selectFacilityPg.getContractsExcludedTheFirst());

		//Select the contract has single state and within one page facilities
		verifySelectedContractWithSingleStates();
		pt.gotoFacilityListPage(facility.contract);

		//Verify no state and letter selector, both previous and next page selector are disabled, Total facilities amount is within one page (less than or equal 25)
		facilityListPg.verifyNoStateDDLExisting();
		facilityListPg.verifyNoLetterSelectorExisting();
		facilityListPg.verifyPreviousLinDisabled();
		facilityListPg.verifyNextLinkDisabled();
		facilityListPg.verifyTotalResultNumWithinOnePg();

		//Click first facility to camp ground photos page, then back to facility list page
		pt.gotoCampgroundPhotosPage(StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
		pt.changeFacility();

		//Verify selected contract, no state and letter selector, both previous and next page selector are disabled, Total facilities amount is within one page (less than or equal 25) 
		facilityListPg.verifyContract(facility.contract);
		facilityListPg.verifyNoStateDDLExisting();
		facilityListPg.verifyNoLetterSelectorExisting();
		facilityListPg.verifyPreviousLinDisabled();
		facilityListPg.verifyNextLinkDisabled();
		facilityListPg.verifyTotalResultNumWithinOnePg();

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		initialContractValue = "-- Select a contract first --";
		facility.contract = "LA";
		schema = DataBaseFunctions.getSchemaName(facility.contract, env);
	}

	private void verifySelectedContractWithSingleStates(){
		List<String> states = web.getAllStatesInContractLevel(schema);
		if(states.size()!=1){
			throw new ErrorOnDataException("Please check the selected contract:"+facility.contract+" which doesn't have single states.");
		}
		logger.info("Successfully verify selected contract:"+facility.contract+" has single states:"+states.size());
	}
}
