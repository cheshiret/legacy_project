package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify inactive store - privilege assignment info.
 * Assign a privilege to store, then un-assign this privilege to store, and check the inactive assignment info
 * @Preconditions:
 * @SPEC: View Inactive Store - Product Assignment List
 * @Task#:Auto - 767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewInactivePrivilegeAssignList extends LicenseManagerTestCase{
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private LicMgrProductInactiveAssignmentsWidget viewInactiveAssignmentPg = LicMgrProductInactiveAssignmentsWidget.getInstance();
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private String storeName = "", vendorName = "";

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
		//assign privilege to store
		lm.assignPrivilgeToStore(privilege.code);
		storeAssignInfo.assignID = storeProductAssignmentPg.getProductStoreAssignmentIdByCode(privilege.code);
		//unassign privilege to store
		lm.unassignPrivilgeToStore(privilege.code);
		
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
		
		privilege.code = "U99";
		privilege.name = "InactiveAssignmentList";
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
