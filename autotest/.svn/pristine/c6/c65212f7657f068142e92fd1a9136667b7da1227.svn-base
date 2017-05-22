package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.facility.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This case is used to cover the basic adding facility(Activity) process
 * @Preconditions:Need to assign 'CreateFacility' feature to the role after every schema changed
 * @LinkSetUp: 
 * d_assign_feature:id=4962
 * @SPEC:Add Facility-Add Activity Facility through Inventory Manager [TC:111976] 
 * @Task#:Auto-2048
 * 
 * @author SWang
 * @Date  Jan 8, 2014
 */
public class AddFacility_Activity extends InventoryManagerTestCase {
	private FacilityData facility = new FacilityData();

	@Override
	public void execute() {
		im.loginInventoryManager(login);

		//add facility
		facility.facilityID = im.addNewFacility(facility);

		//verify new facility list info with expected
		im.verifyFacilityListInfo(facility);

		//verify new facility details info match with expected
		im.verifyFacilityDetailInfo(facility);

		//clean up
		im.deleteFacilityFromDB(facility.facilityID, schema);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		login.contract = "MS Contract";
		login.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		facility.agency = "STATE PARKS";
		facility.region = "DISTRICT 2";
		facility.facilityName = "Add Facility Of Activity - " + DateFunctions.getCurrentTime();
		facility.shortName = StringUtil.getRandomString(4, true);
		facility.status = "Non-Production";
		facility.description = "This facility is created by automation basic regression case - AddFacility";
		facility.productCategory = "Activity";
		facility.mailingAddress = "KeJi 2 Rd No.68 Xi'an Software Park";
		facility.mailingCityTown = "Xi'an";
		facility.mailingState = "Kentucky";
		facility.mailingZipCode = "710075";
		facility.mailingCountry = "United States";
		facility.directLine = "029";
		facility.publicLine = "68685958";
		facility.fax = "8888";
		facility.email = "LoadTest.Astra@activenetwork.com";
		facility.http = "www.active.com";
		facility.primaryPOCLastName = "LoadTest";
		facility.primaryPOCFirstName = "Astra";
		facility.primaryPOCPhone = "02968685958";
		facility.primaryPOCFax = "8888";
		facility.primaryPOCEmail = "LoadTest.Astra@activenetwork.com";
		facility.primaryPOCAddress = "Xi'an Software Park Qin Feng Ge E201";
		facility.primaryPOCCityTown = "Xi'an";
		facility.primaryPOCState = "Kentucky";
		facility.primaryPOCZipCode = "710075";
		facility.primaryPOCCountry = "United States";
		facility.otherPOCLastName = facility.primaryPOCLastName;
		facility.otherPOCFirstName = facility.primaryPOCFirstName;
		facility.otherPOCPhone = facility.primaryPOCPhone;
		facility.otherPOCFax = facility.primaryPOCFax;
		facility.otherPOCEmail = facility.primaryPOCEmail;
		facility.otherPOCAddress = facility.primaryPOCAddress;
		facility.otherPOCCityTown = facility.primaryPOCCityTown;
		facility.otherPOCState = facility.primaryPOCState;
		facility.otherPOCZipCode = facility.primaryPOCZipCode;
		facility.otherPOCCountry = "United States";
		facility.timeZone = "America/Chicago";
		facility.geographicRegion = "Hills Region";

		facility.enforceMinimumToConfirmRuleInField = true;
	}
}

