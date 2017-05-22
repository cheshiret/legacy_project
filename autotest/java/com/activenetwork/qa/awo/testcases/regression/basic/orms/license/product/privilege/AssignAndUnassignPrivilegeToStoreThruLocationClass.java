/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

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
public class AssignAndUnassignPrivilegeToStoreThruLocationClass extends LicenseManagerTestCase{
	private String locationClass = "";
	private LicMgrPrivilegeProductDetailsPage privilegeDetailPg = LicMgrPrivilegeProductDetailsPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private ILicMgrProductAgentAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage.getInstance();
	private String creationTimeInCase;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//1. go to 'Store Assignments' sub page of Privilege Details page to assign product to stores thru Location Class
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		
		//2. get location class with minimum number of agent
		lm.gotoAgentAssignmentsSubPgFromProductDetailPg(privilegeDetailPg);
		privilegeStoreAssignmentsPg.unassignAllAgentsThroughLocationClass();
		locationClass = privilegeStoreAssignmentsPg.getLocationClassWithMinNumOfAgents();
		
		//3. assign privilege to store
		lm.assignPrivilegeToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		
		//4. positive assigning action verification - verify stores which belong to this location class are all assigned this product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);

		//5. positive assigning result verification - go to store detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getPrivilegeStoreAssignmentByCode(privilege.code));
		}

		//6. un-assign privilege from stores thru location class
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.unassignPrivilegeFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successful
		List<List<String>> unassignedLocationClassRecords = privilegeStoreAssignmentsPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		
		//8. verify un-assigning result correctly
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyStoreProductAssignmentExists(privilege.code, false);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.name = "AssignPrivilegeToAgent";
		privilege.code = "APS";
		privilege.productGroup = "Privileges";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.invType = "none";
		privilege.displayCategory = "Hunting";
		privilege.displaySubCategory = "Licenses";
		
	}
	
	private void verifyNegativeAssignAction(List<List<String>> unassignments) {
		logger.info("----Verify stores are NOT assigned.");
		for(int i = 0; i < unassignments.size(); i++) {
			if(unassignments.get(i).get(2).equalsIgnoreCase("Yes") || Integer.parseInt(unassignments.get(i).get(4)) != 0) {
				throw new ErrorOnDataException("Assigning action affect to other store which are NOT selected.");
			}
		}
	}
	
	private void verifyStoreProductAssignmentExists(String code, boolean expectedResult) {
		boolean actualResult = storeProductAssignmentsPg.verifyProductAssignedToStoreByCode("privilege", code);
		if(expectedResult != actualResult) {
			throw new ErrorOnDataException("Privilege(Code=" + code + ") should "+ (expectedResult ? "be assigned" : "NOT be assigned to this store."));
		}
	}
	
	private void verifyProductAssignedToStoreSuccessfully(ProductStoreAssignment actualProductAssignment) {
		logger.info("Verify the actual Product-Store Assignment record detail info is correct with expected.");
		
		boolean result = true;
		if(actualProductAssignment.assignID.length() == 0) {
			result &= false;
			logger.error("Product-Store Assignment Assign ID is null.");
		}
		if(!actualProductAssignment.displayCategory.equalsIgnoreCase(privilege.displayCategory)) {
			result &= false;
			logger.error("Product-Store Assignment Display Category doesn't match.");
		}
		if(!actualProductAssignment.productCode.equalsIgnoreCase(privilege.code)) {
			result &= false;
			logger.error("Product-Store Assignment Code doesn't match.");
		}
		if(!actualProductAssignment.productName.equalsIgnoreCase(privilege.name)) {
			result &= false;
			logger.error("Product-Store Assignment Name doesn't match.");
		}
		if(!actualProductAssignment.productGroup.equalsIgnoreCase(privilege.productGroup)) {
			result &= false;
			logger.error("Product-Store Assignment Product Group doesn't match.");
		}
		if(!actualProductAssignment.isAssigned) {
			result &= false;
			logger.error("Product-Store Assignment Assigned Status doesn't match.");
		}
		String userName = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		if(!actualProductAssignment.creationUser.replace(", ", ",").equalsIgnoreCase(userName.replace(", ", ","))) {
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
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 6000) {
			result &= false;
			logger.error("Product-Store Assignment Creation Date/Time doesn't match.");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Privilege-Store Assignment doesn't match expected.");
		}else {
			logger.info("Verify Product Assigned To Store Successfully.");		
		}
	}

}
