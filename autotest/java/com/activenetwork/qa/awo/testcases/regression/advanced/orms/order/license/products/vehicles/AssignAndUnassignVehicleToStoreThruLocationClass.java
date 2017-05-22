package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @Description: Assign/Unassign/View Vehicle product to/from Stores thru Location Class
 * @Preconditions:1. Need an existing Vehicle product(support script)
 * @SPEC: <<View Product - Store Assignment List thru Location Class.doc>>, 
 * 				<<Assign Product to Stores thru Location Class>> and <<Unassign Product from Stores thru Location Class>>
 * @Task#: Auto-510
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public class AssignAndUnassignVehicleToStoreThruLocationClass extends LicenseManagerTestCase {
	private String locationClass = "01-MDWFP Headquarters";
	private LicMgrVehicleProductStoreAssignmentsPage vehicleStoreAssignmentsPg = LicMgrVehicleProductStoreAssignmentsPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String creationTimeInCase;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private static int TOP_10_STORE = 10;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. go to 'Agent Assignments' sub-page of vehicle details page to assign product to stores thru Location Class
		lm.gotoVehicleSearchListPageFromTopMenu();
		this.gotoVehicleProductDetailsPage();
		// Clean up: UN-assign this vehicle from stores through location class
		lm.unassignVehicleFromStoresThruLocationClass(locationClass);
		
		//assign this vehicle to stores through location class
		lm.assignVehicleToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		List<List<String>> unassignedLocationClassRecords = vehicleStoreAssignmentsPg.getUnassignedLocationClassStoreRecords();
		
		//2. positive assigning action verification - verify stores which belong to this location class are ALL assigned this product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);
		
		//3. negative assigning action verification - verify other stores which don't belong to the location class is NOT assigned this product
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		List<StoreInfo> tempStoreList = vehicleStoreAssignmentsPg.getStoreInfoByLocationClass(unassignedLocationClassRecords.get(new Random().nextInt(unassignedLocationClassRecords.size() - 1)).get(1));
		int tempStoreSize = tempStoreList.size();
		if(tempStoreSize == 1) {
			tempStoreSize += 1;
		}
		StoreInfo aRandomNotAssignedStore = tempStoreList.get(new Random().nextInt(tempStoreSize - 1));
		
		//4. positive assigning result verification - go to store detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Data & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < TOP_10_STORE/*assignedStores.size()*/; i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getVehicleStoreAssignmentByCode(vehicleRTI.getPrdCode()));
		}
		
		 //5. negative assigning result verification - goto a random store detail page to verify the product doesn't be assigned to store which doesn't belong to the selected location class
		lm.gotoStoreProductAssignmentPage(aRandomNotAssignedStore.storeName);
		this.verifyStoreProductAssignmentExists(vehicleRTI.getPrdCode(), false);
		
		//6. un-assign vehicle from stores thru location class
		lm.gotoVehicleSearchListPageFromTopMenu();
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleRTI.getPrdCode());
		lm.unassignVehicleFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successful
		List<List<String>> allAssignments = vehicleStoreAssignmentsPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(allAssignments);
		
		//8. verify un-assigning result correct
		lm.gotoVendorSearchPg();
		for(int i = 0; i < TOP_10_STORE/*assignedStores.size()*/; i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyStoreProductAssignmentExists(vehicleRTI.getPrdCode(), false);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicleRTI.setPrdCode("VEH");
		vehicleRTI.setPrdName("AssignVehicleToStores");
		vehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicleRTI.setPrdGroup("Inspection");
		vehicleRTI.setVehicleType("Boat");
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		vehicleRTI.setCustClass(custClass);
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
		result &= MiscFunctions.compareResult("Product-Store Assignment Code", vehicleRTI.getPrdCode(), actualProductAssignment.productCode);
		result &= MiscFunctions.compareResult("Product-Store Assignment Name", vehicleRTI.getPrdName(), actualProductAssignment.productName);
		result &= MiscFunctions.compareResult("Product-Store Assignment Product Group", vehicleRTI.getPrdGroup(), actualProductAssignment.productGroup);
		result &= MiscFunctions.compareResult("Product-Store Assignment Vehicle Type", vehicleRTI.getVehicleType(), actualProductAssignment.vehicleType);
		result &= MiscFunctions.compareResult("Product-Store Assignment assigned status", true, actualProductAssignment.isAssigned);
		result &= MiscFunctions.compareResult("Product-Store Assignment Creation User", DataBaseFunctions.getLoginUserName(login.userName), actualProductAssignment.creationUser.replaceAll(", ", ","));
		
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			actualDate = formatter.parse(actualProductAssignment.creationDateTime + " EDT");
		} catch (ParseException e) {
			throw new ErrorOnDataException(e);
		}
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 120) {
			logger.error("Product-Store Assignment Creation Date/Time doesn't match.");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Vehicle-Store Assignment doesn't match expected.");
		}
	}
	
	private void gotoVehicleProductDetailsPage() {

		logger.info("go to Vehicle ProductDetails Page.");
		LicMgrVehiclesListPage vehicleListPg = LicMgrVehiclesListPage.getInstance();
		lm.gotoVehicleSearchListPageFromTopMenu();
		
		// check if the given vehicle exist or not
		boolean existFlag = vehicleListPg.checkProductRecordExist(vehicleRTI.getPrdCode());
		if(!existFlag) {

			// add new vehicle
			lm.addVehicleProduct(vehicleRTI);
		}
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleRTI.getPrdCode());
	}
}
