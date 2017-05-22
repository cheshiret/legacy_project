package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrNewFacilitySetupPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: (P) In new facility setup page verify 
 * 1.error message when fields not specified or with invalid value
 * 2.facility id default value "New" and can't be edit "Active" status
 * @Preconditions: Used role has features "ViewActivityProduct", "ViewActivityFacility" and "CreateModifyActivityFacility"
 * @LinkSetUp: 
 * d_assign_feature:id=4882,4892,4902 
 * @SPEC:Add Facility-Validation [TC:110303] 
 * @Task#:AUTO-2048 
 * 
 * @author SWang
 * @Date  Jan 7, 2014
 */
public class AddFacilityWithErrorMsg extends LicenseManagerTestCase {
	private FacilityData fd = new FacilityData();
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8, msg9, invilateC1, invilateC2, invilateC3, invilateC4, facilityID;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoNewFacilitySetupPgFromHomePg(fd.agency, fd.region);
		verifyFacilityIDAndStatusValues();
		verifyErrorMsg();
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
		fd.facilityName = "AddFacilityWithErrorMsg";
		fd.shortName = "AFWE";
		fd.mailingAddress = "AddressOfActivityFacility";
		fd.mailingCityTown = "CityOfActivityFacility";
		fd.mailingZipCode = "12345";
		fd.publicLine = "8761234563";
		fd.status = "Active";
		facilityID = "New";

		invilateC1 = "@@";
		invilateC2 = "AFW";
		invilateC3 = "AFWEA";
		invilateC4 = "afasdfa";
		msg1 = "Facility Name is required. Please specify the Facility Name.";
		msg2 = "Facility Name may only contain alphanumeric characters, spaces, left and right parenthesis, underscores, dots, dashes, apostrophes, or standard accents. Please re-enter the Facility Name.";
		msg3 = "Short Name is required. Please specify the Short Name.";
		msg4 = "Short Name must be 4 characters and may only contain alphanumeric characters. Please re-enter the Short Name.";
		msg5 = "Address (within Mailing Address) is required. Please specify the Address.";
		msg6 = "City/Town (within Mailing Address) is required. Please specify the City/Town.";
		msg7 = "ZIP/Postal (within Mailing Address) is required. Please specify the ZIP/Postal.";
		msg8 = "Public Line must be a valid phone number and must only contain numbers, spaces, brackets, dashes or an 'x' (to denote extension number). Please re-enter the Public Line.";
		msg9 = "Fax must be a valid fax number and must only contain numbers, spaces, brackets, dashes or an 'x' (to denote extension number). Please re-enter the Fax.";
	}

	private boolean checkErrorMsg(String attriName, String expectedErrorMes){
		LicMgrNewFacilitySetupPage newFacilitySetupPg = LicMgrNewFacilitySetupPage.getInstance();
		newFacilitySetupPg.clickApply();
		ajax.waitLoading();
		String errorMesFromUI = newFacilitySetupPg.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg about"+attriName, expectedErrorMes, errorMesFromUI);
	}

	private void verifyErrorMsg(){
		LicMgrNewFacilitySetupPage newFacilitySetupPg = LicMgrNewFacilitySetupPage.getInstance();

		//Facility name
		boolean result = checkErrorMsg("Facility name has not been specified", msg1);
		newFacilitySetupPg.setFacilityName(invilateC1);
		result &= checkErrorMsg("Facility Name with an invilate characters", msg2);

		//Short name
		newFacilitySetupPg.setFacilityName(fd.facilityName);
		result &= checkErrorMsg("Short name has not been specified", msg3);
		newFacilitySetupPg.setFacilityShortName(invilateC2);
		result &= checkErrorMsg("Short name which is less than 4 characters", msg4);
		newFacilitySetupPg.setFacilityShortName(invilateC3);
		result &= checkErrorMsg("Short name which is greater than 4 characters", msg4);
		newFacilitySetupPg.setFacilityShortName(invilateC1);
		result &= checkErrorMsg("Short name which is equal to 4 characters but contain one or more special character", msg4);

		//Address
		newFacilitySetupPg.setFacilityShortName(fd.shortName);
		result &= checkErrorMsg("The 'Address' has not been specified", msg5);

		//City/Town
		newFacilitySetupPg.setAddress(fd.mailingAddress);
		result &= checkErrorMsg("City/Town has not been specified", msg6);

		//Zip/Postal
		newFacilitySetupPg.setCityTown(fd.mailingCityTown);
		result &= checkErrorMsg("ZIP/Postal was left blank", msg7);

		//Public line
		newFacilitySetupPg.setZip(fd.mailingZipCode);
		newFacilitySetupPg.setPublicLine(invilateC4);
		result &= checkErrorMsg("Invalid public line", msg8);

		//Fax
		newFacilitySetupPg.setPublicLine(fd.publicLine);
		newFacilitySetupPg.setFax(invilateC4);
		result &= checkErrorMsg("Invalid fax number", msg9);

		if(!result){
			throw new ErrorOnPageException("Not all error message are passed in new facility setup page. Please check the details from previous logs.");
		}
		logger.info("All error message are passed in new facility setup page.");
	}

	private void verifyFacilityIDAndStatusValues(){
		LicMgrNewFacilitySetupPage newFacilitySetupPg = LicMgrNewFacilitySetupPage.getInstance();
		boolean result = MiscFunctions.compareResult("Facility ID", facilityID, newFacilitySetupPg.getFacilityID());
		result &= MiscFunctions.compareResult("Status", fd.status, newFacilitySetupPg.getStatus());
		result &= MiscFunctions.compareResult("Status can't edit", false, newFacilitySetupPg.isStatusEditable());
		if(!result){
			throw new ErrorOnPageException("Failed to verify Facility ID and Staus fields. Please check the details from previous logs.");
		}
		logger.info("Successfully verify Facility ID and Staus fields");
	}
}
