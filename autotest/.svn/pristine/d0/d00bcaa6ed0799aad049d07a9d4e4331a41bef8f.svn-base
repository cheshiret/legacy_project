package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrSearchRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Search vehicle registration order by Registration ID and product code.
 * @Preconditions:Vehicle(DZ1) must exist.
 * @SPEC:Search Vehicle Registration.UCS
 * @Task#:Auto-996
 * 
 * @author nding1
 * @Date  May 29, 2012
 */
public class SearchVehicleRegistrationMultiCondition extends LicenseManagerTestCase {
	private BoatInfo boat = new BoatInfo();
	private RegistrationInfo regisInfo = new RegistrationInfo();
	private LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage.getInstance();
	private String regisID = "";
	private String warningMsg = "";
	
	@Override
	public void execute(){
		lm.loginLicenseManager(login);

		// get registration ID
		regisID = lm.checkRegis(cust, boat, pay, "Registration");
		
		// go to search registration page
		lm.gotoSearchRegisPage();

		// 1. search by registration ID and product code
		regisInfo.searchType = "Registration ID";
		regisInfo.searchValue = regisID;
		regisInfo.searchProductCd = boat.registration.rTIPrdCode;
		String message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message);
		
		// 2. search by product code and vehicle type and vehicle search type and vehicle search type value
		regisInfo.searchType = null;
		regisInfo.searchValue = "";
		regisInfo.searchVehicleType = "Boat";
		regisInfo.searchVehicleSearchType = "Boat Use";
		regisInfo.searchVehicleSearchValue = "OTHER";
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message);
		
		// 3. search by product code and status
		regisInfo.searchVehicleType = "";
		regisInfo.searchVehicleSearchType = "";
		regisInfo.searchVehicleSearchValue = "";
		regisInfo.searchStatus = ACTIVE_STATUS;
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message);
		
		// 4. search by valid from date and status
		regisInfo.searchProductCd = "";
		regisInfo.searchDateType = "Valid From";
		regisInfo.searchFromDate = DateFunctions.getDateAfterToday(-30);
		regisInfo.searchToDate = DateFunctions.getToday();
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//login.station = "Station 1 AM";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		cust.lName = "TEST-TransferRule789";
		cust.fName = "QA-TransferRule789";
		
		boat.type = "Boat";
		RegistrationInfo regisInfo = new RegistrationInfo();
		regisInfo.miNum = "MI0141AA";
		regisInfo.rTIPrdCode = "DZ1";
		boat.registration.product = "REG - RegisterBoat";
		boat.registration.rTIPrdCode = "DZ1";
		boat.registration.miNum =  "MI0141AA";
		boat.hullIdSerialNum = "Sar"+DateFunctions.getCurrentTime();
		boat.manufacturerName = "YAMA";
		boat.modelYear = Integer.toString(DateFunctions.getCurrentYear());
		boat.feet = "2";  //this value should be in the range of the related record in 'AddVehicleProduct.datapool'
		boat.inches = "0";//this value should be in the range of the related record in 'AddVehicleProduct.datapool'
		boat.hullMaterial = "Other";
		boat.boatUse = "PLEASURE";//this value should be equal to the related record in 'AddVehicleProduct.datapool'
		boat.propulsion = "Other";
		boat.fuelType = "Other";
		boat.typeOfBoat = "Other";
		boat.status = ACTIVE_STATUS;
		boat.manufacturerPrintName = "YAMA";
		warningMsg = "No Registrations found matching the search criteria. Please re-enter.";
	}
	
	/**
	 * Verify search result
	 * @param message
	 */
	private void verifySearchResult(String message){
		logger.info("Verify search result...");

		// verify search result
		if("".equals(message)){
			List<String> regisIDList = registrationsSearchPg.getColumnByName("Registration ID");
			if(!regisIDList.contains(regisID)){
				logger.info("Registration ID:"+regisID+" should in search result list.");
			}
		} else if(warningMsg.equals(message)){
			logger.info(warningMsg);// no record matches the search criteria
		} else {
			throw new ErrorOnPageException("Unexpect error was occured.Please check message:"+message);
		}
	}
}
