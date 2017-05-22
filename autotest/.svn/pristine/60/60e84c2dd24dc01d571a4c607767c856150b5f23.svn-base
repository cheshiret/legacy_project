package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: This case is used to verify search vehicle
 * @Preconditions:
 * @SPEC: View Vehicle RT Product List.doc
 * @Task#: Auto-760

 * @author VZhang1
 * @Date Dec 5, 2011
 */

public class SearchVehicleProduct extends LicenseManagerTestCase{

	private Random r = new Random();
	private Map<String, Boolean> status = new HashMap<String, Boolean>();
	private Map<String, Boolean> groups = new HashMap<String, Boolean>();
	private Map<String, Boolean> types = new HashMap<String, Boolean>();
	private LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleSearchListPageFromTopMenu();
		//add vehicle product
		lm.addVehicleProduct(vehicleRTI);
		
	    status.put("Active", true);
	    status.put("Inactive", false);
	    groups.put("Registration", true);
	    groups.put("Title", false);
	    groups.put("Inspection", false);
	    types.put("Boat", true);
	    types.put("Motor", false);
	    types.put("Dealer", false);	   
		//search vehicle by search critical
		lm.searchVehicleProduct(status, groups, types);
		//verify vehicle code whether exists in list page from search result, should exists
		this.verifyVehicleCode(vehicleRTI.getPrdCode());
		//verify search result from vehicle list
		this.verifySearchResult(vehicleRTI.getPrdGroup(), vehicleRTI.getStatus(), vehicleRTI.getVehicleType());
		
		//deactivate
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleRTI.getPrdCode());
		vehicleRTI.setStatus("Inactive");
		lm.changeVehicleProductStatus(vehicleRTI.getStatus());	
		status.clear();
	    status.put("Active", false);
	    status.put("Inactive", true);
	    groups.clear();
	    groups.put("Registration", true);
	    groups.put("Title", false);
	    groups.put("Inspection", false);
	    types.clear();
	    types.put("Boat", true);
	    types.put("Motor", false);
	    types.put("Dealer", false);	 
		//search vehicle by search critical
		lm.searchVehicleProduct(status, groups, types);
		//verify vehicle code whether exists in list page from search result, should exists
		this.verifyVehicleCode(vehicleRTI.getPrdCode());
		//verify search result from vehicle list
		this.verifySearchResult(vehicleRTI.getPrdGroup(), vehicleRTI.getStatus(), vehicleRTI.getVehicleType());
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	    
		vehicleRTI.setPrdCode( "S" + r.nextInt(99));
		vehicleRTI.setPrdName( "Search" + DateFunctions.getCurrentTime());
		vehicleRTI.setStatus("Active");
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		custClass.put("Business", false);
		vehicleRTI.setCustClass(custClass);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setValidMonths("2");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
		vehicleRTI.setLateRenewUnit("Day");
		HashMap<String, Boolean> boatUseType = new HashMap<String, Boolean>();
		boatUseType.put("Personal Pleasure", true);
		boatUseType.put("Rental Lease", false);
		boatUseType.put("Commercial Fishing", true);
		boatUseType.put("Commercial Pleasure", false);
		boatUseType.put("Agency", false);
		boatUseType.put("Other", false);	
		vehicleRTI.setBoatUseTyp(boatUseType);
		vehicleRTI.setMinLenthOfFt("7");
		vehicleRTI.setMinLenthOfIn("5");

		vehicleRTI.setMaxLenthOfFt("20");
		vehicleRTI.setMaxLenthOfIn("14");
	}
	
	private void verifyVehicleCode(String code){
		logger.info("Verify vehicle code.");
		
		if(!listPage.isThisVehicleExist(code)){
			throw new ErrorOnPageException("Vehicle Code "+code+" should exist in list page," +
					"when search vehicle product info.");
		}	
	}
	
	private void verifySearchResult(String expectGroup,String expectStatus, String expectVehicleType){
		logger.info("Verify search result info.");		
		List<String> actualColumnValues = new ArrayList<String>();
		
		actualColumnValues = listPage.getProductGropListValues();		
		//verify product group info by search critical, should just have searched group
		if(actualColumnValues.size()!=1){
			throw new ErrorOnPageException("Expect display one product group " + expectGroup);
		}else {
			if(!actualColumnValues.get(0).trim().equals(expectGroup)){
				throw new ErrorOnPageException("Product group search result is not correct. Expect result is " + expectGroup
						+ ", but actually is " + actualColumnValues.get(0));
			}
		}
		
		//verify status info by search critical, should just have searched status
		actualColumnValues = listPage.getColumnValues("Status");
		for(int i=1; i<actualColumnValues.size(); i++){
			if(!actualColumnValues.get(i).equalsIgnoreCase(expectStatus)){
				throw new ErrorOnPageException("Status search result is not correct. Expect result is " + expectStatus +
						", but actually is " +actualColumnValues.get(i));
			}
		}
		
		//verify vehicle type info by search critical, should just have searched vehicle type
		actualColumnValues = listPage.getColumnValues("Vehicle Type");
		for(int i=1; i<actualColumnValues.size(); i++){
			if(!actualColumnValues.get(i).equalsIgnoreCase(expectVehicleType)){
				throw new ErrorOnPageException("Vehicle type search result is not correct. Expect result is " + expectVehicleType
						+ ", but acutally is " + actualColumnValues.get(i));
			}
		}
	}
}
