package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrSearchRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Search vehicle registration order by Registration ID.
 * @Preconditions:Vehicle(DZ1) must exist.
 * @SPEC:Search Vehicle Registration.UCS
 * @Task#:Auto-996
 * 
 * @author nding1
 * @Date  May 23, 2012
 */
public class SearchVehicleRegistrationSingleCondition extends LicenseManagerTestCase {
	private RegistrationInfo regisInfo = new RegistrationInfo();
	private BoatInfo vehicle = new BoatInfo();
	private LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage.getInstance();
	private String warningMsg = "";
	private String vehicleRTICode = "";
	
	@Override
	public void execute(){
		lm.loginLicenseManager(login);
		
		// get registration ID
		String regisID = lm.checkRegis(cust, vehicle, pay, "Registration");
		
		// go to search registration page
		lm.gotoSearchRegisPage();
		
		// 1.search by registration ID
		regisInfo.searchType = "Registration ID";
		regisInfo.searchValue = regisID;
		String message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message, "Registration ID", new String[]{regisInfo.searchValue});

		// 2. search by product code
		regisInfo.searchType = null;
		regisInfo.searchValue = "";
		regisInfo.searchProductCd = vehicleRTICode;
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message, "Product", new String[]{regisInfo.searchProductCd});
		
		// 3. search by Date type
		regisInfo.searchProductCd = "";
		regisInfo.searchDateType = "Valid To";
		regisInfo.searchFromDate = DateFunctions.getDateAfterToday(-30);
		regisInfo.searchToDate = DateFunctions.getToday();
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message, regisInfo.searchDateType, new String[]{regisInfo.searchFromDate, regisInfo.searchToDate});
		
		// 4. search by vehicle ID/MI#
		regisInfo.searchDateType = null;
		regisInfo.searchFromDate = "";
		regisInfo.searchToDate = "";
		regisInfo.searchVehicleIDMI = vehicle.registration.miNum;
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message, "Vehicle", new String[]{regisInfo.searchVehicleIDMI});
		
		// 5. search by Hull ID/Serial #
		regisInfo.searchVehicleIDMI = "";
		regisInfo.searchHullID = vehicle.hullIdSerialNum;
		message = registrationsSearchPg.searchRegistration(regisInfo);
		
		// verify search result
		this.verifySearchResult(message, "Hull ID/Serial #", new String[]{regisInfo.searchHullID});
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//login.station = "Station 1 AM";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		cust.lName = "TEST-TransferRule77";
		cust.fName = "QA-TransferRule77";
		vehicleRTICode = "DZ1";
//		vehicle.type = "Boat";
		vehicle.registration.miNum = "MI0141AA";
		vehicle.hullIdSerialNum = "123456987";
		warningMsg = "No Registrations found matching the search criteria. Please re-enter.";
	}
	
	/**
	 * Verify search result
	 * @param message
	 */
	private void verifySearchResult(String message, String columnName, String[] searchValue){
		logger.info("Verify search result...");
		
		// verify search result
		if("".equals(message)){
			// go to registration detail page to verify
			if("Order ID".equals(columnName) || "Hull ID/Serial #".equals(columnName)){
				LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage.getInstance();
				lm.gotoRegisDetailPage(registrationsSearchPg.getColumnByName("Registration ID").get(0));
				// get actual value
				String actualValue = "";
				if("Order ID".equals(columnName)) {
					actualValue= regisDetailPg.getOrderID();// order ID 
				} else {
					actualValue= regisDetailPg.getHullID();// Hull ID/Serial #
				}
				// verify
				if(!searchValue[0].equals(actualValue)){
					throw new ErrorOnPageException("According to search criteria, expect value is:"+searchValue[0]+", but actula value is:"+actualValue);
				}
			} else {
				List<String> resultList = registrationsSearchPg.getColumnByName(columnName);
				for(int i=0; i<resultList.size();i++){
					
					// verify date range
					if("Valid From".equals(columnName) || "Valid To".equals(columnName)){
						if(DateFunctions.compareDates(resultList.get(i), searchValue[0]) < 0 ||
								DateFunctions.compareDates(resultList.get(i), searchValue[1]) > 0){
							throw new ErrorOnPageException("The "+columnName+" Date should between "+searchValue[0]+" and "+searchValue[1]);
						}
					} else {
						if(!resultList.get(i).contains(searchValue[0])){
							throw new ErrorOnPageException("In the search result list, the record without search value ("+searchValue+") should not exist.");
						}
					}
				}
			}
		} else if(warningMsg.equals(message)){
			logger.info(warningMsg);// no record matches the search criteria
		} else {
			throw new ErrorOnPageException("Unexpect error was occured.Please check message:"+message);
		}
	}
}
