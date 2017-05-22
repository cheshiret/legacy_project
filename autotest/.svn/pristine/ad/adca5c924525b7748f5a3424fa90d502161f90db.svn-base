/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName AddVehicle.java
 * @Date:Mar 29, 2011
 * @Description:
 * @author asun
 */
public class AddVehicleProduct extends SupportCase {
	/*
	 * To add Vehicle type, run below sqls:
	 * 
	 * insert into D_VEHICLE_RTI_TYPE values(get_sequence('D_VEHICLE_RTI_TYPE'), 102, 11101, 1, 0, null, 5);
	 * insert into D_VEHICLE_RTI_TYPE values(get_sequence('D_VEHICLE_RTI_TYPE'), 103, 11102, 1, 0, null, 6); 
	 */
	private boolean logedin = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private VehicleRTI vehicle = new VehicleRTI();
	private LicenseManager lm = LicenseManager.getInstance();
	private String contract = "";

	@Override
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && logedin) {
			lm.logOutLicenseManager();
			logedin = false;
		}
		if(!logedin){
			lm.loginLicenseManager(login);
			logedin=true;
			lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		}
		lm.addVehicleProduct(vehicle);
		verifyResultSuccess();
		contract=login.contract;
	}

	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 27;// the start point in the data pool
		endpoint = 27;// the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.envType = "QA";
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "VehicleCode";
		logMsg[2] = "VehicleName";
		logMsg[3] = "Result";
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		vehicle.setPrdCode(dpIter.dpString("code"));
		vehicle.setPrdName(dpIter.dpString("prd_name"));
		vehicle.setPrdGroup(dpIter.dpString("prd_group"));
		vehicle.setVehicleType(dpIter.dpString("VehicleType"));
		HashMap<String, Boolean> custClassMap = new HashMap<String, Boolean>();
		String[] custClasses=dpIter.dpString("custClasses").split(",");
		for(String custClass:custClasses){
			custClassMap.put(custClass, true);
		}
		vehicle.setCustClass(custClassMap);
		vehicle.setValidToDate(dpIter.dpString("validToDate"));
		vehicle.setValidMonths(dpIter.dpString("validMonths"));
		vehicle.setMonth(dpIter.dpString("month val"));
		vehicle.setDay(dpIter.dpString("day val"));
		vehicle.setValidYears(dpIter.dpString("validYears"));
		vehicle.setCycleStartYear(dpIter.dpString("cycleStartYear"));
		vehicle.setAdvanceRenewalDays(dpIter.dpString("advanceRenDays"));
		vehicle.setLateRenewal(dpIter.dpString("lateRenewal"));
		vehicle.setLateRenewUnit(dpIter.dpString("lateRenewalUnit"));
		String[] boatUseTypes=dpIter.dpString("boatUseTypes").split(",");
		HashMap<String, Boolean> boatUseTypeMap = new HashMap<String, Boolean>();
		for(String type:boatUseTypes){
			boatUseTypeMap.put(type, true);
		}
		vehicle.setBoatUseTyp(boatUseTypeMap);
		vehicle.setMinLenthOfFt(dpIter.dpString("minLengthOfFt"));
		vehicle.setMinLenthOfIn(dpIter.dpString("minLengthOfIn"));
		vehicle.setMaxLenthOfFt(dpIter.dpString("maxLengthOfFt"));
		vehicle.setMaxLenthOfIn(dpIter.dpString("maxLengthOfIn"));
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = vehicle.getPrdCode();
		logMsg[2] = vehicle.getPrdName();
	}
	
	/**
	 * judge whether this vehicle is created sucessfully
	 * @return boolean
	 */
	public void verifyResultSuccess() {
		LicMgrVehiclesListPage listPage=LicMgrVehiclesListPage.getInstance();
		LicMgrCreateNewVehiclePage vehicleInfoPage=LicMgrCreateNewVehiclePage.getInstance();	    
		if(!listPage.exists()) {
			String msg="create vehicle product failed:code="+vehicle.getPrdCode()+",name="+vehicle.getPrdName();
			if(vehicleInfoPage.exists()){
				msg+=";"+vehicleInfoPage.getWarningMessage();
			}
			 logMsg[3] = "Failed";
	    } else {
	    	logMsg[3] = "Passed";
	    }
	}
}
