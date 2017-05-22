package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:Verify change vehicle status to inactive
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-760

 * @author VZhang1
 * @Date Dec 6, 2011
 */

public class ChangeVehicleProductStatus  extends LicenseManagerTestCase{
	private Random r = new Random();
	private LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
	private Map<String, Boolean> status = new HashMap<String, Boolean>();
	private Map<String, Boolean> groups = new HashMap<String, Boolean>();
	private Map<String, Boolean> types = new HashMap<String, Boolean>();
	private VehicleRTI vehicle = new VehicleRTI();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleSearchListPageFromTopMenu();
		//add vehicle product
		lm.addVehicleProduct(vehicle);
		
		lm.gotoVehicleProductDetailsPageFromListPage(vehicle.getPrdCode());	
		vehicle.setStatus(OrmsConstants.INACTIVE_STATUS);
		//Inactive vehicle 
		lm.changeVehicleProductStatus(vehicle.getStatus());

	    status.put("Active", true);
	    status.put("Inactive", false);	    
	    groups.put("Registration", true);
	    groups.put("Title", true);
	    groups.put("Inspection", true);	    
	    types.put("Boat", true);
	    types.put("Motor", true);
	    types.put("Dealer", true);
	    //search active vehicle from list page
		lm.searchVehicleProduct(status, groups, types);
		//verify vehicle code whether exists in list page, should not exists
		this.verifyVehicleCode(vehicle.getPrdCode(), false);
		
		status.clear();
	    status.put("Active", false);
	    status.put("Inactive", true);	
	    groups.clear();
	    groups.put("Registration", true);
	    groups.put("Title", true);
	    groups.put("Inspection", true);	 
	    types.clear();
	    types.put("Boat", true);
	    types.put("Motor", true);
	    types.put("Dealer", true);
	    //search inactive vehicle from list page
		lm.searchVehicleProduct(status, groups, types);
		//verify vehicle code whether exists, should exists
		this.verifyVehicleCode(vehicle.getPrdCode(), true);
		
		//go to vehicle detail page
		lm.gotoVehicleProductDetailsPageFromListPage(vehicle.getPrdCode());
		//verify vehicle status, should be 'Inactive'
		this.verifyVehicleStatus(vehicle.getStatus());
		
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicle.setPrdCode("C" + r.nextInt(99));
		vehicle.setPrdName("Change" + DateFunctions.getCurrentTime());
		vehicle.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicle.setPrdGroup("Registration");
		vehicle.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Individual", true);
		map.put("Business", false);
		vehicle.setCustClass(map);
		vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicle.setValidMonths("2");
		vehicle.setAdvanceRenewalDays("1");
		vehicle.setLateRenewal("2");
	    vehicle.setLateRenewUnit("Day");
	    Map<String, Boolean> boatUseTyps = new HashMap<String, Boolean>();
	    boatUseTyps.put("Personal Pleasure", true);
	    boatUseTyps.put("Rental Lease", false);
	    boatUseTyps.put("Commercial Fishing", true);
	    boatUseTyps.put("Commercial Pleasure", false);
	    boatUseTyps.put("Agency", false);
	    boatUseTyps.put("Other", false);
	    vehicle.setBoatUseTyp(boatUseTyps);
	    vehicle.setMinLenthOfFt("7");
	    vehicle.setMinLenthOfIn("5");
	    vehicle.setMaxLenthOfFt("20");
	    vehicle.setMaxLenthOfIn("14");
	}
	
	private void verifyVehicleCode(String code, boolean isExists){
		logger.info("Verify vehicle code whether exists.");
		
		if(isExists){
			if(!listPage.isThisVehicleExist(code)){
				throw new ErrorOnPageException("Vehicle Code should exist in list page");
			}
		}else {
			if(listPage.isThisVehicleExist(code)){
				throw new ErrorOnPageException("Vehicle Code should not exist in list page");
			}
		}			
	}
	
	private void verifyVehicleStatus(String expectStatus){
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage.getInstance();
		String actualValue = "";
		
		logger.info("verify vehicle status.");
		actualValue = vehicleDetailPg.getVehicleStatus();
		if(!expectStatus.equalsIgnoreCase(actualValue)){
			throw new ErrorOnPageException("Vehicle status should be " + expectStatus
					+ ", but acutally is " + actualValue);
		}		
	}
}
