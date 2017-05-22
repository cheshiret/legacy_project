package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify store - privilege assignment list info.
 * Assign a privilege to store, and check the assignment info from assignment list page.  
 * @Preconditions:
 * @SPEC: View Store - Product Assignment List.doc
 * @Task#: Auto-767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewPrivilegeAssignmentInfo extends LicenseManagerTestCase{

	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String storeName = "", vendorName = "";
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// privilege preparation:check active privilege whether existed, if not add a new privilege
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		if(!privilegeListPage.isThisPrivilegeExist(privilege.code)){
			lm.addPrivilegeProduct(privilege);
		}
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//unassign privilege to store
		lm.unassignPrivilgeToStore(privilege.code);
		storeAssignInfo.isAssigned = false;
		storeAssignInfo.creationUser = "";
		storeAssignInfo.creationDateTime = "";
		//verify un-assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		//assign privilege to store
		lm.assignPrivilgeToStore(privilege.code);
		storeAssignInfo.isAssigned = true;
		storeAssignInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		storeAssignInfo.creationDateTime = DateFunctions.getToday("E MMM d yyyy");
		//verify assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "S99";
		privilege.name = "AssignmentList";
		privilege.productGroup = "Privileges";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.invType = "None";
		//If change schema, the following info did not exists, you should update the following privilege info
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Trip";
		privilege.reportCategory = "Resident Licenses";
		
		storeAssignInfo.productCategory = "Privilege";
		storeAssignInfo.productCode = privilege.code;
		storeAssignInfo.productName = privilege.name;
		storeAssignInfo.productGroup = privilege.productGroup;
		storeAssignInfo.displayCategory = privilege.displayCategory;
		storeAssignInfo.displaySubCategory = privilege.displaySubCategory;	
		
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
