/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.add;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.TestCaseFailedException;

/**
 * @ScriptName AddVehicleRTProduct_Attributtes.java
 * @Date:Mar 29, 2011
 * @Description:
 * @author asun
 */
public class VerifyAttributtes extends LicenseManagerTestCase {

	private List<Exception> list = new ArrayList<Exception>();
	private int count=0;
	private VehicleRTI vehicle = new VehicleRTI();
	HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
	HashMap<String, Boolean> boatUseType = new HashMap<String, Boolean>();
	@Override
	public void execute() {
		
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");

		try {
			count++;
			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is not specified.
			vehicle.setMinLenthOfFt(" ");
			vehicle.setMinLenthOfIn(" ");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
		} catch (Exception e) {
            list.add(e);
		}
	
		try {
			count++;
			//<Attribute> has been specified, and is defined as type: 
			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
			this.initializeVehicleInfo();
			vehicle.setMinLenthOfFt("-1");
			vehicle.setMinLenthOfIn("1");
			lm.addVehicleProduct(vehicle);
			this
					.verifyErrorMessage("Vehicle Type Specific Information - Min Length must be greater than or equal to 0 feet and 0 inches.");//updated by Peter Zhu
		} catch (Exception e) {
            list.add(e);
		}
		
		try {
			//Defect
			count++;
			//<Attribute> has been specified, and is defined as type: 
			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
			this.initializeVehicleInfo();
			vehicle.setMinLenthOfFt("0");
			vehicle.setMinLenthOfIn("-1");
			vehicle.setMaxLenthOfFt("0");
			vehicle.setMaxLenthOfIn("0");
			lm.addVehicleProduct(vehicle);
			this
					.verifyErrorMessage("Vehicle Type Specific Information - Min Length must be greater than or equal to 0 feet and 0 inches.");// updated
																																				// by
																																				// Peter
																																				// Zhu
		} catch (Exception e) {
            list.add(e);
		}
		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the maximum Boat Length is not specified.
//			initializeVehicleInfo();
//			vehicle.setMaxLenthOfFt(" ");
//			vehicle.setMaxLenthOfIn(" ");
//			lm.addVehicleProduct(vehicle);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.The Boat Max Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
		
		try {
			count++;
			//<Attribute> has been specified, and is defined as type: 
			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
			//TODO ISSUE CONFIRM
			initializeVehicleInfo();
			vehicle.setMaxLenthOfFt("-1");
			vehicle.setMaxLenthOfIn("1");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("The value of Vehicle Type Specific Information - Max Length must be greater than or equal to the value of Vehicle Type Specific Information - Min Length.");
		} catch (Exception e) {
            list.add(e);
		}
		
//		try {
//			count++;
//			initializeVehicleInfo();
//			//<Attribute> has been specified, and is defined as type: 
//			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
//			//TODO-ISSUE CONFIRM
//			vehicle.setMinLenthOfFt("0");
//			vehicle.setMinLenthOfIn("0");
//			vehicle.setMaxLenthOfFt("0");
//			vehicle.setMaxLenthOfIn("-1");
//			lm.addVehicleProduct(vehicle);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.Vehicle Type Specific Information - Max Length must be greater than or equal to 0 feet and 0 inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the maximum Boat Length less than  minimum length.
//			initializeVehicleInfo();
//			vehicle.setMinLenthOfFt("2");
//			vehicle.setMinLenthOfIn("2");
//			vehicle.setMaxLenthOfFt("1");
//			vehicle.setMaxLenthOfIn("1");
//			lm.addVehicleProduct(vehicle);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
		
		try {
			count++;
			//The Product group is "Registration" and the vehicle Type is "Boat" and 
			//there was no applicable boat use type selected System displays an error message.
			initializeVehicleInfo();
			boatUseType.put("Personal Pleasure", false);
			vehicle.setBoatUseTyp(boatUseType);
			lm.addVehicleProduct(vehicle);
			this
					.verifyErrorMessage("Vehicle Type Specific Information - Boat Use Types is required. Please re-enter.");
		} catch (Exception e) {
            list.add(e);
		}
		
		if(list.size()>0){
		  for(Exception e:list){
			  e.printStackTrace();
		  }	
		  throw new TestCaseFailedException("Total verifications:"+count);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// initialize vehicle information
		initializeVehicleInfo();
	}

	/**
	 * initialize vehicle information
	 * 
	 * @Return void
	 */
	private void initializeVehicleInfo() {
		// vehicle information
		vehicle.setPrdCode((new Random()).nextInt(1000)+"");
		vehicle.setPrdName("QA Auto Test");
		vehicle.setPrdGroup("Registration");
		vehicle.setVehicleType("Boat");
		custClass.put("Individual", true);
		vehicle.setCustClass(custClass);
		vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicle.setValidMonths("1");
		vehicle.setAdvanceRenewalDays("1");
		vehicle.setLateRenewal("1");
		vehicle.setLateRenewUnit("Day");
		vehicle.setMonth("Feb");
		vehicle.setDay("1");
		vehicle.setValidYears("1");
		vehicle.setCycleStartYear("2001");
		boatUseType.put("Personal Pleasure", true);
		boatUseType.put("Rental Lease", false);
		boatUseType.put("Commercial Fishing", false);
		boatUseType.put("Commercial Pleasure", false);
		boatUseType.put("Agency", false);
		boatUseType.put("Other", false);	
		vehicle.setBoatUseTyp(boatUseType);
		vehicle.setMinLenthOfFt("2");
		vehicle.setMinLenthOfIn("1");

		vehicle.setMaxLenthOfFt("3");
		vehicle.setMaxLenthOfIn("2");
	}
	
	/***
	 * Verify error message on CreateVehicleDetailsPage
	 * @param expectmessage
	 * @Return void
	 * @Throws
	 */
	public void verifyErrorMessage(String expectmessage){

		LicMgrCreateNewVehiclePage vehicleInfoPage=LicMgrCreateNewVehiclePage.getInstance();	    
	    String msgOnPage="";
		
	    logger.info("verify error message....");
	    ajax.waitLoading();
	    msgOnPage=vehicleInfoPage.getWarningMessage();

	    if(!msgOnPage.equals(expectmessage)){
		    logger.info("expected msg is -->> "+expectmessage+"; but real msg is -->> "+msgOnPage);//updated by Peter Zhu
	    	throw new ErrorOnDataException("The error message '"+expectmessage+"' is not found.");
	    }
	    logger.info("verify successfully.....");
	}
}
