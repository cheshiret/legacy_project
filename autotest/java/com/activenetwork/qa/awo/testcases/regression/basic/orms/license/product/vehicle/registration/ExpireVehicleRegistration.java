package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrSearchRegistrationsPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrServiceDaemonsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:The status of vehicle registration will be expired when the valid to date is past data after today's daemon run.
 * @Preconditions:
 * @SPEC:Expire Vehicle Registration.UCS
 * @Task#:Auto-996
 * 
 * @author nding1
 * @Date  May 31, 2012
 */
public class ExpireVehicleRegistration extends LicenseManagerTestCase {
	private LoginInfo loginSm = new LoginInfo();
	private SystemManager sm = SystemManager.getInstance();
	private String daemonName, daemonRunTime;
	private String regisID = null;
	private TimeZone timeZone_MS;
	private String status = null;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		// get expire date.
		String expireDate = lm.readQADB("VelResExpireDate");
		if(!StringUtil.isEmpty(expireDate)){
			// get order ID
			vehicle.registration.id = lm.readQADB("VelResExpireOrd");
		}
		
		// if no expire time or order doesn't exist, do data preparation
		if(StringUtil.isEmpty(expireDate) || !lm.checkOrderExists(schema, vehicle.registration.id)){
			// go to system manager to get daemon time
			sm.loginSystemManager(loginSm);
			
			//go to System Manager to get 'ExpireSuspension' service daemon running time
			this.getDaemonTimeinSM();
			sm.logoutSystemManager();
			lm.writeQADB("daemonForExpireVelRes", daemonRunTime);
			
			// vehicle registration
			lm.loginLicenseManager(login);
			lm.registerVehicleToOrderCart(cust, vehicle);
			lm.processOrderToOrderSummary(pay);
			vehicle.registration.id = lm.processOrderCart(pay).split(" ")[0];
			lm.writeQADB("VelResExpireOrd", vehicle.registration.id);
			
			// search registration by order ID
			this.searchRegis(vehicle.registration.id);// get regisID, status and valid to
			
			// update valid to date to today of this order
			this.updateValidToDate();
		    lm.logOutLicenseManager();
		    
		    // get expire date
			expireDate = this.getExpireDay();
			lm.writeQADB("VelResExpireDate", expireDate);
			throw new ErrorOnPageException("This registration is not Expired now, please verify its status after 10:30 AM in "+expireDate);

	    // if expire time is past time, verify status is Expired. 
		} else if(DateFunctions.compareToToday(expireDate, timeZone_MS) < 0){

			// get order ID
			vehicle.registration.id = lm.readQADB("VelResExpireOrd");
			
			// search order and check status
			lm.loginLicenseManager(login);
			
			// search registration by order ID
			this.searchRegis(vehicle.registration.id);
			this.verifyStatus(EXPIRED_STATUS);
			lm.writeQADB("VelResExpireDate", "");// reset expire date.
			lm.writeQADB("VelResExpireOrd", "");// reset order number.

		} else if(DateFunctions.compareToToday(expireDate, timeZone_MS) == 0){
			daemonRunTime = lm.readQADB("daemonForExpireVelRes");
			expireDate = this.getExpireDay();
			
			// get order ID
			vehicle.registration.id = lm.readQADB("VelResExpireOrd");
			
			// search order and check status
			lm.loginLicenseManager(login);
			
			// search registration by order ID
			this.searchRegis(vehicle.registration.id);
			if(DateFunctions.compareToToday(expireDate, timeZone_MS) == 0){// daemon hasn't been run today
				this.verifyStatus(ACTIVE_STATUS);
				throw new ErrorOnPageException("This registration is not Expired now, please verify its status after 10:30 AM in "+expireDate);
			} else {// daemon has already been run today
				this.verifyStatus(EXPIRED_STATUS);
				lm.writeQADB("VelResExpireDate", "");// reset expire date.
				lm.writeQADB("VelResExpireOrd", "");// reset order number.
			}
			
		// if expire time is future time, verify status and throw exception
		} else {
			// get order ID
			vehicle.registration.id = lm.readQADB("VelResExpireOrd");
			if(!StringUtil.isEmpty(vehicle.registration.id)){

				// search order and check status
				lm.loginLicenseManager(login);
				
				// search registration by order ID
				this.searchRegis(vehicle.registration.id);
				this.verifyStatus(ACTIVE_STATUS);
				throw new ErrorOnPageException("This registration is not Expired now, please verify its status after 10:30 AM in "+expireDate);
			}
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		//login info
		loginSm.url = AwoUtil.getOrmsURL(env, "systemmgr") ;
		loginSm.userName = TestProperty.getProperty("orms.sm.user");
		loginSm.password = TestProperty.getProperty("orms.sm.pw");
		loginSm.contract = "MS Contract";
		loginSm.location = "Administrator/"+TestProperty.getProperty("ms.admin.location");
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/Wal-Mart";
		login.station = "Station 1 AM";
		daemonName = "com.reserveamerica.licensing.order.impl.daemon.ExpireVehicleRegistrationDaemon";//10:30
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone_MS = DataBaseFunctions.getContractTimeZone(schema);

		cust.lName = "TEST-TransferRule77";
		cust.fName = "QA-TransferRule77";
		
		vehicleRTI.setPrdCode("DZ1");
		vehicleRTI.setPrdName("ViewVehicleRegistration");
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "ExpireVehOrd"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		vehicle.manufacturerName = "Sony";
		vehicle.modelYear = DateFunctions.getCurrentYear()+"";
		vehicle.feet = "15";
		vehicle.inches = "2";
		vehicle.hullMaterial = "Inflatable";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Other";
		vehicle.fuelType = "Other";
		vehicle.typeOfBoat = "PaddleBoat";
		vehicle.propulsion = "Sail";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
	}

	/**
	 * Get daemon time for expire vehicle registration.
	 */
	private void getDaemonTimeinSM() {
		SysMgrServiceDaemonsPage sysMgrServicePage = SysMgrServiceDaemonsPage.getInstance();
		logger.info("Get daemon time for expire vehicle registration.");
		sm.gotoServiceDaemonsPage();
		String daemonStatus = sysMgrServicePage.getDaemonStatus(daemonName);
		if(!daemonStatus.equals("RUNNING")) {
			throw new ErrorOnPageException("The ExpireCustomerSuspension daemon is NOT running, please contact Administrator to start it.");
		}
		daemonRunTime = sysMgrServicePage.getDaemonRunAt(daemonName).split("at")[1].trim();
	}
	
	/**
	 * Update valid to date to past date.
	 */
	private void updateValidToDate(){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Update valid to date to today.");
		String sql = "update O_VEHICLE_RTI_INST set valid_TO = '"+DateFunctions.getDateAfterToday(0, "dd-MMM-yy", timeZone_MS)+"' where id = '"+regisID+"'";
		db.executeUpdate(sql);
	}
	
	/**
	 * Get column value through column name in search registration page.
	 * @param columnName
	 * @return
	 */
	private String getColumnByName(String columnName){
		String value = "";
		LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage.getInstance();
		logger.info("Get column value through column name in search registration page.");
		List<String> regisIDList = registrationsSearchPg.getColumnByName(columnName);
		value = regisIDList.get(0);
		return value;
	}
	
	/**
	 * Search registration by order number.
	 */
	private void searchRegis(String orderID){
		LicMgrSearchRegistrationsPage searchPg = LicMgrSearchRegistrationsPage.getInstance();
		logger.info("Search registration by order number.");
		
		// go to search registration page
		lm.gotoSearchRegisPage();
		vehicle.registration.searchType = "Order #";
		vehicle.registration.searchValue = orderID;
		
		// search by order ID
		searchPg.searchRegistration(vehicle.registration);
		regisID = this.getColumnByName("Registration ID");
		status = this.getColumnByName("Status");
	}
	
	/**
	 * Calculate time difference, and get expire day.
	 * @return
	 */
	private String getExpireDay(){
		logger.info("Calculate time difference, and get expire day.");
		String currentTime = DateFunctions.getCurrentTimeFormated(true);
		int hour =  Integer.valueOf(currentTime.split(":")[0]);
		int minute = Integer.valueOf(currentTime.split(":")[1]);
		int daemonHour = Integer.valueOf(daemonRunTime.split(":")[0]);
		int daemonMinute = Integer.valueOf(daemonRunTime.split(":")[1]);
		int hourdiff = daemonHour - hour;
		int minutediff = daemonMinute - minute;
		String expireDate = "";
		
		// daemon has been run today.
		if(hourdiff < 0 ||
				(hourdiff == 0 && minutediff <=0)){
			// verify after run daemon tomorrow
			expireDate = DateFunctions.getDateAfterToday(1, timeZone_MS);
		} else if(hourdiff > 0){
			// daemon has not been run today
			expireDate = DateFunctions.getToday(timeZone_MS);
		}
		return expireDate;
	}
	
	/**
	 * verify status.
	 */
	private void verifyStatus(String expectStatus){
		logger.info("verify status is Expired or not.");

		if(!MiscFunctions.compareResult("Status", expectStatus, status)) {
			throw new ErrorOnPageException("---Check logs above.");
		} else {
			logger.info("The status is correct!");
		}
	}
}
