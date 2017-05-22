/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.add;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @ScriptName CancleWhenAddingVehicle.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public class CancelWhenAddingVehicle extends LicenseManagerTestCase {
	private VehicleRTI vehicle = new VehicleRTI();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		this.cancelWhenCreatingVehicle(vehicle);
		this.verifyThisVehicleNotExist(vehicle.getPrdCode(),vehicle.getPrdGroup(),vehicle.getVehicleType());
		lm.gotoHomePage();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicle.setPrdCode(String.valueOf(DataBaseFunctions.getEmailSequence()).substring(0, 3));
	    vehicle.setPrdName("QA Auto Test");
	    vehicle.setPrdGroup("Inspection");
	    vehicle.setVehicleType("Boat");
	    HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
	    custClass.put("Individual", true);
	    vehicle.setCustClass(custClass);
	}

	/**
	 * Cancel a creating vehicle. Work flow:Start form Vehicle list page,End in
	 * vehicle list page
	 * @param vehicle
	 * @Return void
	 * @Throws
	 */
	public void cancelWhenCreatingVehicle(VehicleRTI vehicle) {
		LicMgrVehiclesListPage vListPage =LicMgrVehiclesListPage.getInstance();
		LicMgrCreateNewVehiclePage addVehiclePage=LicMgrCreateNewVehiclePage.getInstance();
		
		logger.info("Cancel a creating vehicle.");
		vListPage.clickAddVehicleProduct();
		addVehiclePage.waitLoading();
		addVehiclePage.setVehicleInfo(vehicle);
		addVehiclePage.clickCancel();
		vListPage.waitLoading();
	}

	/**
	 * verify the vehicle which code is specified is not existing.
	 * @param vehicleCode
	 * @param group
	 * @param type
	 * @Return void
	 * @Throws
	 */
	public void verifyThisVehicleNotExist(String vehicleCode,String group,String type) {
		LicMgrVehiclesListPage vListPage =LicMgrVehiclesListPage.getInstance();
		
		logger.info("verify the vehicle which code is "+vehicleCode+" is not existing.");
		
		vListPage.selectActiveStatus();
		vListPage.selectInactiveStatus();
		if(group.equalsIgnoreCase("Registration")){
		   vListPage.selectProGroup_Registration();
		}else if(group.equalsIgnoreCase("Title")){
           vListPage.selectProGroup_Title();
		}else if(group.equalsIgnoreCase("Inspection")){
		   vListPage.selectProGroup_Inspection();
		}else{
			throw new ErrorOnDataException("Unknow Vehicle Group");
		}
		
		if(type.equalsIgnoreCase("Boat")){
			vListPage.selectVehicleTyp_Boat();
		}else if(type.equalsIgnoreCase("Motor")){
			vListPage.selectVehicleTyp_Motor();
		}else if(type.equalsIgnoreCase("Dealer")){
			vListPage.selectVehicleTyp_Dealer();
		}else{
			throw new ErrorOnDataException("Unknown vehicle type...");
		}
		
		vListPage.clickGo();
		if(vListPage.isThisVehicleExist(vehicleCode)){
			throw new ErrorOnPageException("this vehicle should not be added.");
		}
		logger.info("Verify sucessfully.");
	}

}
