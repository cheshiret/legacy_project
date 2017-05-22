package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.facility.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to cover the basic adding facility(Permit) process
 * @Preconditions: Need to assign 'CreateFacility' feature to the role after every schema changed
 * @SPEC: <<Facility.UIS>> <<Add Facility.doc>>
 * @Task#: AUTO-768
 * 
 * @author qchen
 * @Date  Dec 8, 2011
 */
public class AddFacility_Permit extends InventoryManagerTestCase {
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
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		facility.agency = "BLM";
		facility.region = "CA";
		facility.district = "9100";
		facility.project = "6000";
		facility.facilityName = "Add Facility Of Permit - " + DateFunctions.getCurrentTime();
		facility.shortName = StringUtil.getRandomString(4, true);
		facility.status = "Non-Production";
		facility.description = "This facility is created by automation basic regression case - AddFacility";
		facility.productCategory = "Permit";
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
		
		facility.alias = "itmreP fO ytilicaF ddA";
		facility.brochureDescription = "Automation basic regression test case create by Quentin";
		facility.concessionaire = true;
		facility.drivingDirection = "upupdowndownleftrightleftrightleftright";
		facility.usesFieldApplication = true;
		facility.pageDefaultRowCount = "50 rows per page";
		facility.financialSessionType = "Station";
		facility.openingFloatRequired = true;
		facility.closeBlindly = true;
		facility.transmittals = true;
		facility.transmittalTraceNumRequired = true;
		facility.latitudeDirection = "North";
		facility.latitudeDegrees = "34";
		facility.latitudeMinutes = "13";
		facility.latitudeSeconds = "32";
		facility.longitudeDirection = "East";
		facility.longitudeDegrees = "108";
		facility.longitudeMinutes = "52";
		facility.longitudeSeconds = "37";
		facility.importantInfo = "There is nothing important. This facility should be deleted by script itself.";
		facility.showExitAlerts = true;
		facility.commercialReservationsMadeFor = "Commercial User always makes reservations for a Group Leader";
		facility.confirmationPeriod = "10";
		facility.confirmationPeriodStartTime = "10:00";
		facility.confirmationPeriodStartTimeAmPm = "AM";
		facility.displayAvailabilityOnButtonCaption = true;
		facility.hiddenOnWebSearch = true;
		facility.onlinePermitPrintPeriod = "10";
		facility.onlinePermitPrintPeriodStartTime = "11:00";
		facility.onlinePermitPrintPeriodStartTimeAmPm = "AM";
		facility.permitInventoryAllocationsApplicable = true;
		facility.randomReleaseOfInventoryAppliesToRevokes = true;
		facility.walkinAllocationsIncludeAdvancedAllocations = true;
		facility.printPermitInformation = "Web Settings - Print Permit Information";
		facility.webFacilitySpecialMessage = "Web Facility Special Message";
	}
}
