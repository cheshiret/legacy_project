package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify inactive store - vehicle assignment info.
 * Assign a vehicle to store, then un-assign this vehicle to store, and check the inactive assignment info
 * @Preconditions:
 * @SPEC: View Inactive Store - Product Assignment List
 * @Task#:Auto - 767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewInactiveVehicleAssignList extends LicenseManagerTestCase{
	private LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private LicMgrProductInactiveAssignmentsWidget viewInactiveAssignmentPg = LicMgrProductInactiveAssignmentsWidget.getInstance();
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private String storeName = "", vendorName = "";
	private VehicleRTI vehicleRTI = new VehicleRTI();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// vehicle preparation:check active privilege whether existed, if not add a new vehicle
		lm.gotoVehicleSearchListPageFromTopMenu();
		if(!listPage.isThisVehicleExist(vehicleRTI.getPrdCode())){
			lm.addVehicleProduct(vehicleRTI);
		}
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//assign vehicle to store
		lm.assignVehicleToStore(vehicleRTI.getPrdCode());
		storeAssignInfo.assignID = storeProductAssignmentPg.getProductStoreAssignmentIdByCode(vehicleRTI.getPrdCode());
		//unassign vehicle to store
		lm.unassignVehicleFromStore(vehicleRTI.getPrdCode());
		
		//go to view inactive assignment page from store product assignment page
		lm.gotoViewInactiveAssignmentPgFromStoreProductAssignmentPg();
		//compare assignment info
		this.verifyProductInactiveAssignmentListInfo(storeAssignInfo);
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPgFromViewInactiveAssignmentPg();		
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicleRTI.setPrdCode("U11");
		vehicleRTI.setPrdName("InactiveAssignmentList");
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
		storeAssignInfo.assignStatus = "Inactive";
		storeAssignInfo.creationDateTime = DateFunctions.getToday("E MMM d yyyy");
		storeAssignInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		storeAssignInfo.lastModDate = DateFunctions.getToday("E MMM d yyyy");
		storeAssignInfo.lastModUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		
		storeName = "WAL-MART";
		vendorName = "Auto Vendor";			
	}
	
	private void verifyProductInactiveAssignmentListInfo(ProductStoreAssignment expectAssignmentInfo){
		logger.info("Verify product inactive assignment list info.");

		boolean pass = viewInactiveAssignmentPg.compareProductInactiveAssignInfo(expectAssignmentInfo);
		if(!pass){
			throw new ErrorOnPageException("Product inactive assignment list info is not correct, please check error log.");
		}
	}

}
