/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: This case was designed for verify assign/unassign product to store thru location class
 * 1. Verify assign list table;
 * 2. Verify agent detail page under those location class  
 * @Preconditions:
 * @SPEC: Assign/Unassign Product to Stores thru Location Class
 * @Task#: Auto - 874
 * 
 * @author Jane Wang
 * @Date  Mar 13, 2012
 */
public class AssignAndUnassignVehicleToStoreThruLocationClass extends
		LicenseManagerTestCase {

	private String locationClass = "";
	private LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private ILicMgrProductAgentAssignmentsPage vehicleStoreAssignmentsPg = LicMgrVehicleProductStoreAssignmentsPage.getInstance();
	private String creationTimeInCase;
	private VehicleRTI vehicle = new VehicleRTI();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//1. go to 'Agent Assignments' sub-page of vehicle details page to assign product to stores thru Location Class
		lm.gotoVehicleSearchListPageFromTopMenu();
		gotoVehicleProductDetailsPage();
		
		//2. get location class with minimum number of agent
		lm.gotoAgentAssignmentsSubPgFromProductDetailPg(vehicleDetailPg);
		vehicleStoreAssignmentsPg.unassignAllAgentsThroughLocationClass();
		locationClass = vehicleStoreAssignmentsPg.getLocationClassWithMinNumOfAgents();
		
		//3. assign vehicle to store
		lm.assignVehicleToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		
		//4. positive assigning action verification - verify stores which belong to this location class are all assigned this product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);

		//5. positive assigning result verification - go to store detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Data & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getVehicleStoreAssignmentByCode(vehicle.getPrdCode()));
		}

		//6. un-assign privilege from stores thru location class
		lm.gotoVehicleProductDetailsPage(vehicle.getPrdCode());
		lm.unassignVehicleFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successful
		List<List<String>> unassignedLocationClassRecords = vehicleStoreAssignmentsPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		
		//8. verify un-assigning result correctly
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyStoreProductAssignmentExists(vehicle.getPrdCode(), false);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicle.setPrdCode("AVS");
		vehicle.setPrdName("AssignVehicleToStores");
		vehicle.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicle.setPrdGroup("Registration");
		vehicle.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Individual", true);
		vehicle.setCustClass(map);
	}
	
	private void verifyNegativeAssignAction(List<List<String>> assignments) {
		logger.info("----Verify stores are NOT assigned.");
		for(int i = 0; i < assignments.size(); i++) {
			if(assignments.get(i).get(2).equalsIgnoreCase("Yes") || Integer.parseInt(assignments.get(i).get(4)) != 0) {
				throw new ErrorOnDataException("Assigning action affect to other store which are NOT selected.");
			}
		}
	}
	
	private void verifyStoreProductAssignmentExists(String code, boolean expectedResult) {
		boolean actualResult = storeProductAssignmentsPg.verifyProductAssignedToStoreByCode("vehicle", code);
		if(expectedResult != actualResult) {
			throw new ErrorOnDataException("Vehicle(Code=" + code + ") should "+ (expectedResult ? "be assigned" : "NOT be assigned to this store."));
		}
	}
	
	private void verifyProductAssignedToStoreSuccessfully(ProductStoreAssignment actualProductAssignment) {
		logger.info("----Verify the actual Vehicle-Store Assignment record detail info is correct with expected.");
		
		boolean result = true;
		if(actualProductAssignment.assignID.length() == 0) {
			result &= false;
			logger.error("Product-Store Assignment Assign ID is null.");
		}
		if(!actualProductAssignment.productCode.equalsIgnoreCase(vehicle.getPrdCode())) {
			result &= false;
			logger.error("Product-Store Assignment Code doesn't match.");
		}
		if(!actualProductAssignment.productName.equalsIgnoreCase(vehicle.getPrdName())) {
			result &= false;
			logger.error("Product-Store Assignment Name doesn't match.");
		}
		if(!actualProductAssignment.productGroup.equalsIgnoreCase(vehicle.getPrdGroup())) {
			result &= false;
			logger.error("Product-Store Assignment Product Group doesn't match.");
		}
		if(!actualProductAssignment.vehicleType.equalsIgnoreCase(vehicle.getVehicleType())) {
			result &= false;
			logger.error("Product-Store Assignment Vehicle Type doesn't match.");
		}
		if(!actualProductAssignment.isAssigned) {
			result &= false;
			logger.error("Product-Store Assignment assigned status doesn't match.");
		}
		if(!actualProductAssignment.creationUser.replaceAll("\\s+", StringUtil.EMPTY).equalsIgnoreCase(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName).replaceAll("\\s+", StringUtil.EMPTY))) {
			result &= false;
			logger.error("Product-Store Assignment Creation User doesn't match.");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			actualDate = formatter.parse(actualProductAssignment.creationDateTime + " EDT");
		} catch (ParseException e) {
			throw new ErrorOnDataException(e);
		}
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 120) {
			logger.error("Product-Store Assignment Creation Date/Time doesn't match. Actual date is:"+actualProductAssignment.creationDateTime+", expect is today.");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Vehicle-Store Assignment doesn't match expected.");
		}else {
			logger.info("Verify Product Assigned To Store Successfully.");		
		}
	}
	
	private void gotoVehicleProductDetailsPage() {

		logger.info("go to Vehicle Product Details Page.");
		LicMgrVehiclesListPage vehicleListPg = LicMgrVehiclesListPage.getInstance();
		lm.gotoVehicleSearchListPageFromTopMenu();
		
		// check if the given vehicle exist or not
		boolean existFlag = vehicleListPg.checkProductRecordExist(vehicle.getPrdCode());
		if(!existFlag) {

			// add new vehicle
			lm.addVehicleProduct(vehicle);
		}
		lm.gotoVehicleProductDetailsPageFromListPage(vehicle.getPrdCode());
	}
}
