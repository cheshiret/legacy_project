/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddVehicleProductFunction;

/**
 * @ScriptName AddVehicle.java
 * @Date:Mar 29, 2011
 * @Description:
 * @author asun
 */
public class AddVehicleProduct extends SetupCase{
	/*
	 * To add Vehicle type, run below sqls:
	 * 
	 * insert into D_VEHICLE_RTI_TYPE values(get_sequence('D_VEHICLE_RTI_TYPE'), 102, 11101, 1, 0, null, 5);
	 * insert into D_VEHICLE_RTI_TYPE values(get_sequence('D_VEHICLE_RTI_TYPE'), 103, 11102, 1, 0, null, 6); 
	 */
	private LoginInfo login = new LoginInfo();
	private VehicleRTI vehicle = new VehicleRTI();
	private AddVehicleProductFunction addVehProFunc = new AddVehicleProductFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = vehicle;
		addVehProFunc.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_vehicle_prd";
		this.queryDataSql = "";
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("loc");
		
		vehicle.setPrdCode(datasFromDB.get("code"));
		vehicle.setPrdName(datasFromDB.get("prd_name"));
		vehicle.setPrdGroup(datasFromDB.get("prd_group"));
		vehicle.setVehicleType(datasFromDB.get("VehicleType"));
		HashMap<String, Boolean> custClassMap = new HashMap<String, Boolean>();
		String[] custClasses=datasFromDB.get("custClasses").split(",");
		for(String custClass:custClasses){
			custClassMap.put(custClass, true);
		}
		vehicle.setCustClass(custClassMap);
		vehicle.setValidToDate(datasFromDB.get("validToDate"));
		vehicle.setValidMonths(datasFromDB.get("validMonths"));
		vehicle.setMonth(datasFromDB.get("month val"));
		vehicle.setDay(datasFromDB.get("day val"));
		vehicle.setValidYears(datasFromDB.get("validYears"));
		vehicle.setCycleStartYear(datasFromDB.get("cycleStartYear"));
		vehicle.setAdvanceRenewalDays(datasFromDB.get("advanceRenDays"));
		vehicle.setLateRenewal(datasFromDB.get("lateRenewal"));
		vehicle.setLateRenewUnit(datasFromDB.get("lateRenewalUnit"));
		String[] boatUseTypes=datasFromDB.get("boatUseTypes").split(",");
		HashMap<String, Boolean> boatUseTypeMap = new HashMap<String, Boolean>();
		for(String type:boatUseTypes){
			boatUseTypeMap.put(type, true);
		}
		vehicle.setBoatUseTyp(boatUseTypeMap);
		vehicle.setMinLenthOfFt(datasFromDB.get("minLengthOfFt"));
		vehicle.setMinLenthOfIn(datasFromDB.get("minLengthOfIn"));
		vehicle.setMaxLenthOfFt(datasFromDB.get("maxLengthOfFt"));
		vehicle.setMaxLenthOfIn(datasFromDB.get("maxLengthOfIn"));

	}
}
