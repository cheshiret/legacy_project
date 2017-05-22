package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.facility.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:check point:
 *              1.edit privilege facility work flow
 * @Preconditions:
 * @SPEC:Ability to manually create a Facility/Location Code [TC:038069]
 * @Task#:AUTO-991
 * 
 * @author szhou
 * @Date Apr 19, 2012
 */
public class EditFacility_Privilege extends InventoryManagerTestCase{
	private FacilityData facility = new FacilityData();

	@Override
	public void execute() {
		im.loginInventoryManager(login);
		//prepare data--add facility
		facility.facilityID = im.addNewFacility(facility);
		
		//go to facility detail page
		im.gotoFacilityDetailPageById(facility.facilityID);
		this.changeFacilityDetailInfo();
		//edit facility detail info
		im.editFacility(facility);
		
		//verify edit facility list info with expected
		im.verifyFacilityListInfo(facility);
		//verify edit facility details info match with expected
		im.verifyFacilityDetailInfo(facility);
		
		//clean up
		im.deleteFacilityFromDB(facility.facilityID, schema);
		im.logoutInvManager();
	}
	
	private void changeFacilityDetailInfo(){
		facility.facilityName = "Edit Facility Of Privilege - " + DateFunctions.getCurrentTime();
		
		facility.mailingAddress = "Xi'an Software Park Qin Feng Ge E201";
		
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
		facility.primaryPOCState = "Mississippi";
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
		facility.timeZone = "America/Maceio";
		facility.geographicRegion = "Capital River Region";
		
		facility.closeBlindly = true;
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		facility.agency = "MSHF";
		facility.region = "Central Region";
		facility.facilityName = "Add Facility Of Privilege - " + DateFunctions.getCurrentTime();
		facility.shortName = StringUtil.getRandomString(4, true);
		facility.status = "Non-Production";
		facility.description = "This facility is created by automation basic regression case - AddFacility";
		
		facility.productCategory = "Privilege";
		facility.mailingAddress = "KeJi 2 Rd No.68 Xi'an Software Park";
		facility.mailingCityTown = "Xi'an";
		facility.mailingState = "Mississippi";
		facility.mailingZipCode = "710075";
		facility.mailingCountry = "United States";
		
	}
}