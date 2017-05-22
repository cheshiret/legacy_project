package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * 
 * @Description: Assign/Unassign/View Privilege product to/from Stores thru Location Class
 * @Preconditions:1. Need an existing privilege product(support script)
 * @SPEC: <<View Product - Store Assignment List thru Location Class.doc>>, 
 * 				<<Assign Product to Stores thru Location Class>> and <<Unassign Product from Stores thru Location Class>>
 * @Task#: Auto-510
 * 
 * @author QA-qchen
 * @Date  May 18, 2011
 */
public class AssignAndUnassignPrivilegeToStoreThruLocationClass extends LicenseManagerTestCase {
	private String locationClass = "01-MDWFP Headquarters";//there will be a support script to create this location class and assign to 2 stores
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private ILicMgrProductAgentAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage.getInstance();
	private String creationTimeInCase;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//1. go to 'Store Assignments' sub page of Privilege Details page to assign product to stores thru Location Class
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		//unassign first to clean up former dirty data
		lm.unassignPrivilegeFromStoresThruLocationClass(locationClass);
		lm.assignPrivilegeToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		List<List<String>> unassignedLocationClassRecords = privilegeStoreAssignmentsPg.getUnassignedLocationClassStoreRecords();
		
		//2. positive assigning action verification - verify stores which belong to this location class are all assigned this product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);
		
		 //3. negative assigning action verification - verify other stores which don't belong to the location class is NOT assigned this product
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		List<StoreInfo> tempStoreList = privilegeStoreAssignmentsPg.getStoreInfoByLocationClass(unassignedLocationClassRecords.get(new Random().nextInt(unassignedLocationClassRecords.size() - 1)).get(1));
		int tempStoreSize = tempStoreList.size();
		if(tempStoreSize == 1) {
			tempStoreSize += 1;
		}
		StoreInfo aRandomNotAssignedStore = tempStoreList.get(new Random().nextInt(tempStoreSize - 1));
		
		//4. positive assigning result verification - go to store detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		assignedStores = assignedStores.size() > 5 ? assignedStores.subList(0, 5) : assignedStores;
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getPrivilegeStoreAssignmentByCode(privilege.code));
		}
		
		 //5. negative assigning result verification - goto a random store detail page to verify the product doesn't be assigned to store which doesn't belong to the selected location class
		lm.gotoStoreProductAssignmentPage(aRandomNotAssignedStore.storeName);
		this.verifyStoreProductAssignmentExists(privilege.code, false);
		
		//6. un-assign privilege from stores thru location class
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.unassignPrivilegeFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successful
		unassignedLocationClassRecords = privilegeStoreAssignmentsPg.getAllLocationClassStoreAssignmentsInfo();
		unassignedLocationClassRecords = unassignedLocationClassRecords.size() > 3 ? unassignedLocationClassRecords.subList(0, 3) : unassignedLocationClassRecords;
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
		
		privilege.name = "AssignPrivilegeToStores";
		privilege.code = "ATS";
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
		//Vivian[20131212] create user Test-Auto, QA-Auto always changed
//		userName = userName.replace(" ", "");
//		if(!actualProductAssignment.creationUser.equalsIgnoreCase(userName)) {
//			result &= false;
//			logger.error("Product-Store Assignment Creation User doesn't match.");
//		}
		if(!actualProductAssignment.creationUser.split(",")[0].trim().equalsIgnoreCase(userName.split(",")[0].trim()) ||
				!actualProductAssignment.creationUser.split(",")[1].trim().equalsIgnoreCase(userName.split(",")[1].trim())) {
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
		}
	}
}
