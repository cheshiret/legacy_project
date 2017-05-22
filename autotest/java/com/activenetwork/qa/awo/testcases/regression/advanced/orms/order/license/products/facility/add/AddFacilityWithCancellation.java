package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.add;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
/**
 * 
 * @Description: (P) Failed to add facility after click Cancel button in Add Facility Widget and New Facility page
 * @Preconditions: Used role has features "ViewActivityProduct", "ViewActivityFacility" and "CreateModifyActivityFacility"
 * @LinkSetUp: 
 * d_assign_feature:id=4882,4892,4902 
 * @SPEC:Add Facility-Cancellation [TC:110301] 
 * @Task#:AUTO-2048 
 * 
 * @author SWang
 * @Date  Jan 6, 2014
 */
public class AddFacilityWithCancellation extends LicenseManagerTestCase {
	private FacilityData fd = new FacilityData();
	private Data<SearchFacilityAttr> searchFacility = new Data<SearchFacilityAttr>();
	private String meg;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoFacilityListPgFromHomePg();
		lm.addFacility(fd, true, false);
		lm.addFacility(fd, false, true);
		verifyResultWithCancellation();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//Login in License Manager
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Facility parameters
		fd.agency = "STATE PARKS";
		fd.region = "DISTRICT 2";
		fd.facilityName = "AddFacilityWithCancellation";
		fd.shortName = "AFWC";
		fd.mailingAddress = "AddressOfActivityFacility";
		fd.mailingCityTown = "CityOfActivityFacility";
		fd.mailingZipCode = "12345";
		fd.mailingCountry = "United States";
		fd.mailingState = "Missouri";
		fd.mailingCounty= "Adair";
		fd.status = "Active";

		searchFacility.put(SearchFacilityAttr.facilityName, fd.facilityName);
		searchFacility.put(SearchFacilityAttr.city, fd.mailingCityTown);
		searchFacility.put(SearchFacilityAttr.county, fd.mailingCounty);
		searchFacility.put(SearchFacilityAttr.status, fd.status);

		meg = "No Facility found matching the search criteria\\. Please re-enter\\.";
	}

	private void verifyResultWithCancellation(){
		LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
		facilityListPg.searchFacility(searchFacility);
		boolean result = MiscFunctions.compareResult("", 0, facilityListPg.getNumOfResult());
		result &= MiscFunctions.compareResult("No matching facility found meg", true, facilityListPg.isMsgExisted(meg));
		if(!result){
			throw new ErrorOnPageException("Failed to verify no matching facility with Cancellation.");
		}
		logger.info("Successfully verify no matching facility with Cancellation.");
	}
}

