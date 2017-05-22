package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This case is used to verify add vehicle success, and check the vehicle info whether correct.
 * @Preconditions:
 * @SPEC: Add Vehicle RT Product.doc
 * @Task#: Auto-760

 * @author VZhang
 * @Date Sep 29, 2011
 */

public class AddVehicleProduct extends LicenseManagerTestCase{
	private VehicleRTI vehicleRTI = new VehicleRTI();
	
	private LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
	private boolean pass = true;
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleSearchListPageFromTopMenu();
		//add vehicle product
		lm.addVehicleProduct(vehicleRTI);
		//verify vehicle info from vehicle list page
		this.verifyVehicleInfoFromList(vehicleRTI);
		
		//go to vehicle product detail page
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleRTI.getPrdCode());
		//verify vehicle info from vehicle detail page
		this.verifyVehicleInfoFromDetialPg(vehicleRTI);		
		
		//clean up
		lm.changeVehicleProductStatus("Inactive");
		
		if(!pass){
			throw new ErrorOnPageException("Some vehicle info not correct, please check error log.");
		}	
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicleRTI.setPrdCode(StringUtil.getRandomString(3, true));
		vehicleRTI.setPrdName("Add" + DateFunctions.getCurrentTime());
		vehicleRTI.setStatus("Active");
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		HashMap<String,Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		custClass.put("Business", false);
		vehicleRTI.setCustClass(custClass);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setValidMonths("2");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
	    vehicleRTI.setLateRenewUnit("Day");
	    HashMap<String,Boolean> boatUseTyp = new HashMap<String, Boolean>();
	    boatUseTyp.put("Personal Pleasure", true);
	    boatUseTyp.put("Rental Lease", false);
	    boatUseTyp.put("Commercial Fishing", true);
	    boatUseTyp.put("Commercial Pleasure", false);
	    boatUseTyp.put("Agency", false);
	    boatUseTyp.put("Other", false);
	    vehicleRTI.setBoatUseTyp(boatUseTyp);
	    vehicleRTI.setMinLenthOfFt("7");
	    vehicleRTI.setMinLenthOfIn("5");
	    vehicleRTI.setMaxLenthOfFt("20");	  
	    vehicleRTI.setMaxLenthOfIn("11");	 
	}
	
	private void verifyVehicleInfoFromList(VehicleRTI expectVehicle){
		VehicleRTI actualVehicle = listPage.getVehicleInfoByVehicleCode(expectVehicle.getPrdCode());
		
		logger.info("Verify vehicle info.");
		
		if(!listPage.isThisVehicleExist(expectVehicle.getPrdCode())){
			throw new ErrorOnPageException("Vehicle Code should exist in list page," +
					"when add vehicle product success.");
		}	
		
		//verify vehicle whether display on its group list, should display on its group list
		if(!listPage.checkVehicleDisplayProductGroupIsCorrect(expectVehicle.getPrdCode(), expectVehicle.getPrdGroup())){
			throw new ErrorOnPageException("The vehicle code " + expectVehicle.getPrdCode() + " should display in " 
					+ expectVehicle.getPrdGroup() + " group list. ");
		}
		
		//verify vehicle name
		if(!actualVehicle.getPrdName().equals(expectVehicle.getPrdName())){
			pass &= false;
			logger.error("Expect vehicle name should be " + expectVehicle.getPrdName() 
					+ ", but actually is " + actualVehicle.getPrdName());
		}
		
		//verify vehicle status
		if(!actualVehicle.getStatus().equals(expectVehicle.getStatus())){
			pass &= false;
			logger.error("Expect vehicle status should be " + expectVehicle.getStatus() 
					+ ", but actually is " + actualVehicle.getStatus());
		}
		
		//verify vehicle type
		if(!actualVehicle.getVehicleType().equals(expectVehicle.getVehicleType())){
			pass &= false;
			logger.error("Expect vehicle type should be " + expectVehicle.getVehicleType() 
					+ ", but actually is " + actualVehicle.getVehicleType());
		}
	}	
	
	private void verifyVehicleInfoFromDetialPg(VehicleRTI expectVehicle){
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage.getInstance();
		logger.info("Verify vehicle info from vehicle detail page.");
		
		//verify vehicle detail info
		pass &= vehicleDetailPg.compareVehicleInfo(expectVehicle);
	}
}
