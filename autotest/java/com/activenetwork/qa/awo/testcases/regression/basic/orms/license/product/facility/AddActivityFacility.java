package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.facility;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddressesContactsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityAttributesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Successfully add facility and verify result in facility list page and then facility details, addresses and facilty attribures page
 * @Preconditions: Used role has features "ViewActivityProduct", "ViewActivityFacility" and "CreateModifyActivityFacility"
 * @LinkSetUp: 
 * d_assign_feature:id=4882,4892,4902 
 * @SPEC:Add Facility-Check values after adding facility [TC:110581] 
 * @Task#:AUTO-2048 
 * 
 * @author SWang
 * @Date  Jan 8, 2014
 */
public class AddActivityFacility extends LicenseManagerTestCase {
	private LicMgrFacilityListPage faclityListPg = LicMgrFacilityListPage.getInstance();
	private LicMgrFacilityDetailsPage faclityDetailsPg = LicMgrFacilityDetailsPage.getInstance();
	private LicMgrAddressesContactsPage addressesContactsPg = LicMgrAddressesContactsPage.getInstance();
	private LicMgrFacilityAttributesPage facilityAttriPg = LicMgrFacilityAttributesPage.getInstance();
	private FacilityData fd = new FacilityData();
	private Data<SearchFacilityAttr> searchFacility = new Data<SearchFacilityAttr>();

	public void execute() {
		lm.loginLicenseManager(login);

		//Add facility
		lm.gotoFacilityListPgFromHomePg();
		fd.facilityID = lm.addFacility(fd);

		//Verify facility in list page
		searchFacility.put(SearchFacilityAttr.facilityId, fd.facilityID);
		faclityListPg.searchFacility(searchFacility);
		faclityListPg.verifyFacilityInListPg(Arrays.asList(fd));

		//Verify in facility details page
		lm.gotoFacilityDetailsPgFromFacilityListPg(fd.facilityID);
		faclityDetailsPg.verifyFacilityInfo(fd);

		//Verify in addresses page
		addressesContactsPg.verifyAddressesInfo(fd);

		//Verify in facility attributes page
		lm.gotoFacilityAttriPgFromAddressesPg();
		facilityAttriPg.verifyFacilityAttriInfo(fd);

		//Clean and logout
		lm.deleteFacilityFromDB(fd.facilityID, schema);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);

		//Login in License Manager
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Facility parameters
		fd.agency = "STATE PARKS";
		fd.region = "DISTRICT 2";
		fd.facilityName = "Add Facility Of Activity - " + DateFunctions.getCurrentTime();
		fd.shortName = StringUtil.getRandomString(4, true);
		fd.status = "Active";
		fd.mailingAddress = "KeJi 2 Rd No.68 Xi'an Software Park";
		fd.mailingSupplementalAddress = fd.mailingAddress;
		fd.mailingCityTown = "Xi'an";
		fd.mailingState = "Kentucky";
		fd.mailingCounty = "Adair";
		fd.mailingZipCode = "710075";
		fd.mailingCountry = "United States";
		fd.publicLine = "68685958";
		fd.fax = "8888";
		fd.email = "LoadTest.Astra@activenetwork.com";
		fd.http = "www.active.com";
		fd.primaryPOCLastName = "LoadTest";
		fd.primaryPOCFirstName = "Astra";
		fd.primaryPOCPhone = "02968685958";
		fd.primaryPOCFax = "8888";
		fd.primaryPOCEmail = "LoadTest.Astra@activenetwork.com";
		fd.primaryPOCAddress = "Xi'an Software Park Qin Feng Ge E201";
		fd.primaryPOCCityTown = "Xi'an";
		fd.primaryPOCState = "Kentucky";
		fd.primaryPOCZipCode = "710075";
		fd.primaryPOCCountry = "United States";
		fd.otherPOCLastName = fd.primaryPOCLastName;
		fd.otherPOCFirstName = fd.primaryPOCFirstName;
		fd.otherPOCPhone = fd.primaryPOCPhone;
		fd.otherPOCFax = fd.primaryPOCFax;
		fd.otherPOCEmail = fd.primaryPOCEmail;
		fd.otherPOCAddress = fd.primaryPOCAddress;
		fd.otherPOCCityTown = fd.primaryPOCCityTown;
		fd.otherPOCState = fd.primaryPOCState;
		fd.otherPOCZipCode = fd.primaryPOCZipCode;
		fd.otherPOCCountry = "United States";
		fd.timeZone = "America/Chicago";
		fd.geographicRegion = "Hills Region";
		fd.brochureDescription = "brochureDescription of Facility";
		fd.drivingDirection = "drivingDirection of facility";
		fd.adaAccessible = true;
		fd.latitudeDirection = "North";
		fd.latitudeDegrees = "34";
		fd.latitudeMinutes = "13";
		fd.latitudeSeconds = "32";
		fd.longitudeDirection = "East";
		fd.longitudeDegrees = "108";
		fd.longitudeMinutes = "52";
		fd.longitudeSeconds = "37";
		fd.hourOfOperation = "hourOfOperation of facility";
	}
}

