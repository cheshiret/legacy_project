package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify inactive store - consumable assignment info.
 * Assign a consumable to store, then un-assign this consumable to store, and check the inactive assignment info
 * @Preconditions:
 * @SPEC: View Inactive Store - Product Assignment List
 * @Task#:Auto-767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewInactiveConsumableAssignList extends LicenseManagerTestCase{
	private LicMgrConsumableListPage consumableListPage = LicMgrConsumableListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private LicMgrProductInactiveAssignmentsWidget viewInactiveAssignmentPg = LicMgrProductInactiveAssignmentsWidget.getInstance();
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private String storeName = "", vendorName = "";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// consumable preparation:check active consumable whether existed, if not add a new consumable
		lm.gotoConsumableSearchListPageFromTopMenu();		
		consumable.id = consumableListPage.getConsumableId(consumable.code);
		if(null == consumable.id || consumable.id.length() == 0){
			lm.addConsumableProduct(consumable);
		}
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//assign consumable to store
		lm.assignConsumableToStore(consumable.code);
		storeAssignInfo.assignID = storeProductAssignmentPg.getProductStoreAssignmentIdByCode(consumable.code);
		//unassign consumable to store
		lm.unassignConsumableToStore(consumable.code);
				
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
		
		consumable.orderType = "POS Sale";
		consumable.code = "U55";
		consumable.name = "InactiveAssignmentList";
		consumable.description = "InactiveAssignmentList";
		consumable.productGroup = "Other";
		
		storeAssignInfo.productCategory = "Consumable";
		storeAssignInfo.productCode = consumable.code;
		storeAssignInfo.productName = consumable.name;
		storeAssignInfo.productGroup = consumable.productGroup;	
		storeAssignInfo.orderType = consumable.orderType;
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
