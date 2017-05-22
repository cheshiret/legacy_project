package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify store - Vehicle assignment list info.
 * Assign a Vehicle to store, and check the assignment info from assignment list page.  
 * @Preconditions:
 * @SPEC: View Store - Product Assignment List.doc
 * @Task#: Auto-767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewVehicleAssignmentInfo extends LicenseManagerTestCase{
	private LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String storeName = "", vendorName = "";
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// vehicle preparation:check active vehicle whether existed, if not add a new vehicle
		lm.gotoVehicleSearchListPageFromTopMenu();
		if(!listPage.isThisVehicleExist(vehicleRTI.getPrdCode())){
			lm.addVehicleProduct(vehicleRTI);
		}
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//unassign vehicle to store
		lm.unassignVehicleFromStore(vehicleRTI.getPrdCode());
		storeAssignInfo.isAssigned = false;
		storeAssignInfo.creationUser = "";
		storeAssignInfo.creationDateTime = "";
		//compare assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		//assign vehicle to store
		lm.assignVehicleToStore(vehicleRTI.getPrdCode());
		storeAssignInfo.isAssigned = true;
		storeAssignInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		storeAssignInfo.creationDateTime = DateFunctions.getToday("E MMM d yyyy");
		//compare assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicleRTI.setPrdCode("V11");
		vehicleRTI.setPrdName( "AssignmentList");
		vehicleRTI.setPrdGroup("Title");
		vehicleRTI.setVehicleType("Boat");
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		custClass.put("Business", false);
		vehicleRTI.setCustClass(custClass);
		
		storeAssignInfo.productCategory = "Vehicle";
		storeAssignInfo.productCode = vehicleRTI.getPrdCode();
		storeAssignInfo.productName = vehicleRTI.getPrdName();
		storeAssignInfo.productGroup = vehicleRTI.getPrdGroup();
		storeAssignInfo.vehicleType = vehicleRTI.getVehicleType();
		
		storeName = "WAL-MART";
		vendorName = "Auto Vendor";	
	}
	
	private void verifyStoreAssignmentInfo(ProductStoreAssignment expAssignmentInfo){
		boolean pass = true;
		logger.info("Verify store assignment info.");
		
		storeProductAssignmentPg.compareProductStoreAssignmentInfo(expAssignmentInfo);
		if(!pass){
			throw new ErrorOnPageException("Store assign info is not correct, please check error log.");
		}
	}

}
