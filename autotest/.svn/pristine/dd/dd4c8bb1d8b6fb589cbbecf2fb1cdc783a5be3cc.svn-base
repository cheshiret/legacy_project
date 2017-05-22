/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.edit;

import java.util.HashMap;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @ScriptName CancelWhenEditingVehicleProduct.java
 * @Date:Mar 29, 2011
 * @Description:
 * @author asun
 */
public class CancelWhenEditing extends LicenseManagerTestCase {
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		this.cancelWhenAddingVehicleProduct(vehicleRTI);
		this.verifyThisVehicleNotExisting(vehicleRTI.getPrdCode());
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	    
		vehicleRTI.setPrdCode(String.valueOf(new Random().nextInt(1000)));
		vehicleRTI.setPrdName("QA Auto Test");
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Motor");
		custClass.put("Individual", true);
		vehicleRTI.setCustClass(custClass);
		vehicleRTI.setValidMonths("1");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
		vehicleRTI.setLateRenewUnit("Day");
		vehicleRTI.setMonth("Feb");
		vehicleRTI.setDay("1");
		vehicleRTI.setValidYears("1");
		vehicleRTI.setCycleStartYear("2001");
		
	}
	
	/***
	 * cancel when creating vehicle product.
	 * @param vehicle
	 * @Return void
	 */
	public void cancelWhenAddingVehicleProduct(VehicleRTI vehicle){
		LicMgrCreateNewVehiclePage vehicleInfoPage=LicMgrCreateNewVehiclePage.getInstance();	    
		LicMgrVehiclesListPage listPage=LicMgrVehiclesListPage.getInstance();
		
		logger.info("set vehicle information in CreateVehicleDetailsPage...");
		if(!vehicleInfoPage.exists()){
			listPage.clickAddVehicleProduct();
			vehicleInfoPage.waitLoading();
		}
		vehicleInfoPage.setVehicleInfo(vehicle);
		vehicleInfoPage.clickCancel();
		browser.waitExists(listPage);
	}
	
	/**
	 * verify the specified vehicle is not existing
	 * @param vehicleCode
	 * @Return void
	 */
	public void verifyThisVehicleNotExisting(String vehicleCode){
		LicMgrVehiclesListPage listPage=LicMgrVehiclesListPage.getInstance();
        logger.info("verify the specified vehicle is not existing");
		if(listPage.isThisVehicleExist(vehicleCode)){
	    	throw new ErrorOnPageException("The vehicle which code is"+vehicleCode+" should not exist");
	    }
		logger.info("verify successfully!");
	}

}
