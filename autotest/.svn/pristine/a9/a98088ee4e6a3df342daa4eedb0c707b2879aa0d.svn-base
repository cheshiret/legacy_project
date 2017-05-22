/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.TestCaseFailedException;

/**
 * @ScriptName EditVehicleRTProduct_Attributtes.java
 * @Date:Apr 1, 2011
 * @Description:
 * @author asun
 */
public class VerifyAttributtes extends LicenseManagerTestCase {

	private List<Exception> list = new ArrayList<Exception>();
	private int count=0;
	private VehicleRTI vehiclerti = new VehicleRTI();
	private HashMap<String, Boolean> boatUseType = new HashMap<String, Boolean>();
	private HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
	
	@Override
	public void execute() {
		
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");

//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is not specified.
//			vehiclerti.setMinLenthOfFt(" ");
//			vehiclerti.setMinLenthOfIn(" ");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMinLenthOfFt("w");
//			vehiclerti.setMinLenthOfIn("1");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMinLenthOfFt("1");
//			vehiclerti.setMinLenthOfIn("w");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMinLenthOfFt("1");
//			vehiclerti.setMinLenthOfIn("0.5");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMinLenthOfFt("0.7");
//			vehiclerti.setMinLenthOfIn("0");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMaxLenthOfFt("1");
//			vehiclerti.setMaxLenthOfIn("w");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.The Boat Max Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMaxLenthOfFt("w");
//			vehiclerti.setMaxLenthOfIn("1");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.The Boat Max Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMaxLenthOfFt("1");
//			vehiclerti.setMaxLenthOfIn("0.5");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the minimum Boat Length is specified.
//			//but <Attribute> it's is not an integer.
//			this.initializeVehicleInfo();
//			vehiclerti.setMaxLenthOfFt("0.7");
//			vehiclerti.setMaxLenthOfIn("0");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
		
		try {
			count++;
			//<Attribute> has been specified, and is defined as type: 
			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
			this.initializeVehicleInfo();
			vehiclerti.setMaxLenthOfFt("-1");
			vehiclerti.setMaxLenthOfIn("1");
			lm.editVehicleProduct(vehiclerti);
			this
			.verifyErrorMessage("The value of Vehicle Type Specific Information - Max Length must be greater than or equal to the value of Vehicle Type Specific Information - Min Length.");//updated by Peter Zhu
		} catch (Exception e) {
            list.add(e);
		}
		
//		try {
//			//Defect
//			count++;
//			//<Attribute> has been specified, and is defined as type: 
//			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
//			this.initializeVehicleInfo();
//
//			vehiclerti.setMinLenthOfFt("0");
//			vehiclerti.setMinLenthOfIn("-1");
//			vehiclerti.setMaxLenthOfFt("0");
//			vehiclerti.setMaxLenthOfIn("0");
//			lm.editVehicleProduct(vehiclerti);
//			this
//			.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.Vehicle Type Specific Information - Max Length must be greater than or equal to 0 feet and 0 inches.");// updated
//		} catch (Exception e) {
//            list.add(e);
//		}
		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the maximum Boat Length is not specified.
//			initializeVehicleInfo();
//			vehiclerti.setMaxLenthOfFt(" ");
//			vehiclerti.setMaxLenthOfIn(" ");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.The Boat Max Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
		
		try {
			count++;
			initializeVehicleInfo();
			//<Attribute> has been specified, and is defined as type: 
			//Number or Decimal, and a Minimum has been defined for the Attribute, and the value is less than the Minimum for the Attribute.
			vehiclerti.setMinLenthOfFt("2");
			vehiclerti.setMinLenthOfIn("2");
			
			vehiclerti.setMaxLenthOfFt("1");
			vehiclerti.setMaxLenthOfIn("1");
			lm.editVehicleProduct(vehiclerti);
			this.verifyErrorMessage("The value of Vehicle Type Specific Information - Max Length must be greater than or equal to the value of Vehicle Type Specific Information - Min Length.");
		} catch (Exception e) {
            list.add(e);
		}
		
//		try {
//			count++;
//			//The Product group is "Registration" and the vehicle Type is "Boat" and the maximum Boat Length less than  minimum length.
//			initializeVehicleInfo();
//			vehiclerti.setMinLenthOfFt("2");
//			vehiclerti.setMinLenthOfIn("0");
//			vehiclerti.setMaxLenthOfFt("1");
//			vehiclerti.setMaxLenthOfIn("1");
//			lm.editVehicleProduct(vehiclerti);
//			this.verifyErrorMessage("The Boat Min Length must contain valid values for both feet and inches.");
//		} catch (Exception e) {
//            list.add(e);
//		}
//		
		try {
			count++;
			//The Product group is "Registration" and the vehicle Type is "Boat" and 
			//there was no applicable boat use type selected System displays an error message.
			initializeVehicleInfo();
			boatUseType.put("Personal Pleasure", false);
			vehiclerti.setBoatUseTyp(boatUseType);
			lm.editVehicleProduct(vehiclerti);
			this.verifyErrorMessage("Vehicle Type Specific Information - Boat Use Types is required. Please re-enter.");
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
		vehiclerti.setPrdCode("C07");
		vehiclerti.setPrdName("QA Auto Test");
		vehiclerti.setPrdGroup("Registration");
		vehiclerti.setVehicleType("Boat");
		custClass.put("Individual", true);
		vehiclerti.setCustClass(custClass);
		vehiclerti.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehiclerti.setValidMonths("1");
		vehiclerti.setAdvanceRenewalDays("1");
		vehiclerti.setLateRenewal("1");
		vehiclerti.setLateRenewUnit("Day");
		vehiclerti.setMonth("Feb");
		vehiclerti.setDay("1");
		vehiclerti.setValidYears("1");
		vehiclerti.setCycleStartYear("2001");
		boatUseType.put("Personal Pleasure", true);
		boatUseType.put("Rental Lease", false);
		boatUseType.put("Commercial Fishing", false);
		boatUseType.put("Commercial Pleasure", false);
		boatUseType.put("Agency", false);
		boatUseType.put("Other", false);	
		vehiclerti.setBoatUseTyp(boatUseType);
		vehiclerti.setMinLenthOfFt("2");
		vehiclerti.setMinLenthOfIn("1");

		vehiclerti.setMaxLenthOfFt("2");
		vehiclerti.setMaxLenthOfIn("2");
	}
	
	/***
	 * Verify error message on CreateVehicleDetailsPage
	 * @param expectmessage
	 * @Return void
	 * @Throws
	 */
	public void verifyErrorMessage(String expectmessage){
		LicMgrEditVehicleDetailsPage vehicleInfoPage=LicMgrEditVehicleDetailsPage.getInstance();	    
	    String msgOnPage="";
		
	    logger.info("verify error message....");
	    ajax.waitLoading();
	    msgOnPage=vehicleInfoPage.getWarningMessage();
	    if(!msgOnPage.equals(expectmessage)){
	    	logger.error("The error message '"+expectmessage+"' is not found. The message on page is '" + msgOnPage + "'.");
	    	throw new ErrorOnDataException("The error message '"+expectmessage+"' is not found. The message on page is '" + msgOnPage + "'.");
	    }
	    logger.info("verify successfully.....");
	}
}
