package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrSearchRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:View history of vehicle registration.
 * @Preconditions:Vehicle(DZ1) must exist.
 * This vehicle doesn't have document.
 * @SPEC:View Vehicle Registration History.UCS
 * @Task#:Auto-996
 * 
 * @author nding1
 * @Date  May 29, 2012
 */
public class ViewVehicleRegisHistory extends LicenseManagerTestCase {
	private LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage.getInstance();
	private LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage.getInstance();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute(){
		// get order number
		lm.loginLicenseManager(login);
		
		// search registration
		lm.gotoSearchRegisPage();
		registrationsSearchPg.searchRegistration(vehicle.registration);
		List<String> regisIDList = registrationsSearchPg.getColumnByName("Registration ID");
		if(regisIDList.size() <= 0){
			lm.gotoHomePage();
			lm.registerVehicleToOrderCart(cust, vehicle);
			vehicle.registration.searchValue = lm.processOrderCart(pay);
			
			// search the registration
			lm.gotoSearchRegisPage();
			registrationsSearchPg.searchRegistration(vehicle.registration);
			regisIDList = registrationsSearchPg.getColumnByName("Registration ID");
		}
		
		// verify
		lm.gotoRegisDetailPage(regisIDList.get(0));
		vehicle.registration.searchValue = regisDetailPg.getOrderID();
		this.verifyHistory();
		
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
		
		vehicle = new BoatInfo();
		vehicle.registration.rTIPrdCode = "DZ1";
		vehicle.hullIdSerialNum = "transfer" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "Other";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicle.registration.rTIPrdCode + " - " + "ViewVehicleRegistration";
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		vehicle.registration.status = "Active";
		vehicle.registration.searchProductCd = vehicle.registration.rTIPrdCode;
		vehicle.registration.searchVehicleType = "Boat";
		vehicle.registration.searchStatus = vehicle.registration.status;
		vehicle.registration.searchValue = "Order #";
	}
	
	private boolean compare(String expectInfo, String actualInfo){
		boolean result = true;
		if(!expectInfo.equals(actualInfo)){
			result = false;
			logger.error("----Search result info is not correct.Expect one is:"+expectInfo+", but actual one is:"+actualInfo);
		}
		return result;
	}
	
	/**
	 * Verify registration history
	 */
	private void verifyHistory(){
		List<RegistrationInfo> historyList = regisDetailPg.getRegisHistory();
		RegistrationInfo historyUI = historyList.get(0);
		RegistrationInfo expectHistory = this.getExpectHistoryList();
		boolean result = true;
		logger.info("Verify registration history...");

		// compare each field
		result &= this.compare(expectHistory.transaction, historyUI.transaction);
		result &= this.compare(expectHistory.infoAtTimeOfTras, historyUI.infoAtTimeOfTras);
		result &= this.compare(expectHistory.transLocation, historyUI.transLocation);
		result &= this.compare(expectHistory.user.replaceAll("\\s+", StringUtil.EMPTY), historyUI.user.replaceAll("\\s+", StringUtil.EMPTY));
		if(!result){
			throw new ErrorOnPageException("Not all the check pointa are passed...Please check log above...");
		}
	}
	
	/**
	 * Set up expect history info for verification.
	 * @return
	 */
	private RegistrationInfo getExpectHistoryList(){
		RegistrationInfo historyInfo = new RegistrationInfo();
		logger.info("Set up expect history info for verification.");
		//historyInfo.transaction = "Register Vehicle";
		historyInfo.transaction = "Registration";
		historyInfo.infoAtTimeOfTras = "Order: "+vehicle.registration.searchValue+", Receipt: "+this.getReceipt();
		historyInfo.transLocation = login.location.split("/")[1];
		historyInfo.user = DataBaseFunctions.getLoginUserName(login.userName);
		return historyInfo;
	}
	
	/**
	 * Get receipt from table O_RCPT.
	 * @return
	 */
	private String getReceipt(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Get receipt number from table O_RCPT.");
		
		// get Order ID
		String sqlOrdID = "SELECT ID FROM O_ORDER WHERE ORD_NUM = '"+vehicle.registration.searchValue+"'";
		String ordID = db.executeQuery(sqlOrdID, "ID").get(0).trim();
		
		// get receipt ID
		String sqlRcptID = "SELECT RCPT_ID FROM O_RCPT_ORD WHERE ORDER_ID = '"+ordID+"'";
		String rcptID = db.executeQuery(sqlRcptID, "RCPT_ID").get(0).trim();
		return rcptID;
	}
}
