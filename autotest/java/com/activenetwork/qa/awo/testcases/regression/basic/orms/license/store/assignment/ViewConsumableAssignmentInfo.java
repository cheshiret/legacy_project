package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify store - consumable assignment list info.
 * Assign a consumable to store, and check the assignment info from assignment list page.  
 * @Preconditions:
 * @SPEC: View Store - Product Assignment List.doc
 * @Task#: Auto-767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewConsumableAssignmentInfo extends LicenseManagerTestCase{
	private LicMgrConsumableListPage consumableListPage = LicMgrConsumableListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private String storeName = "", vendorName = "";

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// consumable preparation:check active consumable whether existed, if not add a new consumable
		lm.gotoConsumableSearchListPageFromTopMenu();
		try{
			consumable.id = consumableListPage.getConsumableId(consumable.code);
		} catch(Exception e){
			logger.error("Consumble doesn't exist, add a new one.");
			if(null == consumable.id || consumable.id.length() == 0){
				lm.addConsumableProduct(consumable);
			}
		}
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//unassign consumable to store
		lm.unassignConsumableToStore(consumable.code);
		storeAssignInfo.isAssigned = false;
		storeAssignInfo.creationUser = "";
		storeAssignInfo.creationDateTime = "";
		//verify un-assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		//assign consumable to store
		lm.assignConsumableToStore(consumable.code);
		storeAssignInfo.isAssigned = true;
		storeAssignInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		storeAssignInfo.creationDateTime = DateFunctions.getToday("E MMM d yyyy");
		//verify assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		lm.logOutLicenseManager();				
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		consumable.orderType = "POS Sale";
		consumable.code = "S55";
		consumable.name = "AssignmentList";
		consumable.description = "AssignmentList";
		consumable.productGroup = "Other";
		
		storeName = "WAL-MART";
		vendorName = "Auto Vendor";
		
		storeAssignInfo.productCategory = "Consumable";
		storeAssignInfo.productCode = consumable.code;
		storeAssignInfo.productName = consumable.name;
		storeAssignInfo.productGroup = consumable.productGroup;
		storeAssignInfo.orderType = consumable.orderType;
	}
	
	private void verifyStoreAssignmentInfo(ProductStoreAssignment expAssignmentInfo){
		boolean pass = true;
		logger.info("Verify store assignment info.");
		
		pass = storeProductAssignmentPg.compareProductStoreAssignmentInfo(expAssignmentInfo);
		if(!pass){
			throw new ErrorOnPageException("Store assign info is not correct, please check error log.");
		}
	}
}
