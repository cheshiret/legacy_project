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
 * @Description:This case is used to verify edit vehicle info successful, and check edited info.
 * @Preconditions:
 * @SPEC: Edit Vehicle RT Product.doc
 * @Task#: Auto-760

 * @author Vzhang1
 * @Date Dec 5, 2011
 */

public class EditVehicleProduct extends LicenseManagerTestCase{
	private boolean pass = true;
	private VehicleRTI vehicle = new VehicleRTI();
	private HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
	private HashMap<String, Boolean> boatUseTyp = new HashMap<String, Boolean>();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleSearchListPageFromTopMenu();
		//add vehicle product
		lm.addVehicleProduct(vehicle);
		
		lm.gotoVehicleProductDetailsPageFromListPage(vehicle.getPrdCode());
		vehicle.setPrdName("Edit" + DateFunctions.getCurrentTime());
		custClass.clear();
		custClass.put("Individual", false);
		custClass.put("Business", true);
		vehicle.setCustClass(custClass);
		vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		vehicle.setMonth("Mar");
		vehicle.setDay("5");
		vehicle.setValidYears("4");
		vehicle.setCycleStartYear(String.valueOf(DateFunctions.getCurrentYear()));
		vehicle.setAdvanceRenewalDays("2");
		boatUseTyp.clear();
	    boatUseTyp.put("Personal Pleasure", false);
	    boatUseTyp.put("Rental Lease", true);
	    boatUseTyp.put("Commercial Fishing", false);
	    boatUseTyp.put("Commercial Pleasure", false);
	    boatUseTyp.put("Agency", true);
	    boatUseTyp.put("Other", false);
		vehicle.setBoatUseTyp(boatUseTyp);  
	    vehicle.setMinLenthOfFt("8");
	    vehicle.setMinLenthOfIn("17");
	    vehicle.setMaxLenthOfFt("11");
	    vehicle.setMaxLenthOfIn("6");
	    //edit vehicle info
		lm.editVehicleProduct(vehicle);		
		//verify vehicle info from list
		this.verifyVehicleInfoFromList(vehicle);
		
		//go to vehicle detail page
		lm.gotoVehicleProductDetailsPageFromListPage(vehicle.getPrdCode());
		//verify edited vehicle info
		vehicle.setMinLenthOfFt("9");
	    vehicle.setMinLenthOfIn("5");//if the inches is greater than 12, system will automatically carry to 1 foot
		this.verifyVehicleInfoFromDetialPg(vehicle);
		
		//clean up
		lm.changeVehicleProductStatus("Inactive");
		
		if(!pass){
			throw new ErrorOnPageException("Some vehicle info not correct, please check error log.");
		}
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicle.setPrdCode(StringUtil.getRandomString(3, true));
		vehicle.setPrdName("Auto" + DateFunctions.getCurrentTime());
		custClass.put("Individual", true);
		custClass.put("Business", false);
		vehicle.setPrdGroup("Registration");
		vehicle.setVehicleType("Boat");
		vehicle.setCustClass(custClass);
		vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicle.setValidMonths("2");
		vehicle.setAdvanceRenewalDays("1");
		vehicle.setLateRenewal("1");
		vehicle.setLateRenewUnit("Day");
		vehicle.setStatus("Active");
	    boatUseTyp.put("Personal Pleasure", true);
	    boatUseTyp.put("Rental Lease", false);
	    boatUseTyp.put("Commercial Fishing", true);
	    boatUseTyp.put("Commercial Pleasure", false);
	    boatUseTyp.put("Agency", false);
	    boatUseTyp.put("Other", false);
		vehicle.setBoatUseTyp(boatUseTyp);  
	    vehicle.setMinLenthOfFt("7");
	    vehicle.setMinLenthOfIn("5");
	    vehicle.setMaxLenthOfFt("20");
	    vehicle.setMaxLenthOfIn("14");
	}
	
	private void verifyVehicleInfoFromList(VehicleRTI expectVehicle){
		LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();		
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
		
		//compare vehicle info
		pass &= vehicleDetailPg.compareVehicleInfo(expectVehicle);		
	}
}
