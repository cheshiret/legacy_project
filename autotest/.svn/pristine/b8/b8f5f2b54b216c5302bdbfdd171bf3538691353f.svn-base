package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify store - Supply assignment list info.
 * Assign a Supply to store, and check the assignment info from assignment list page.  
 * @Preconditions:
 * @SPEC: View Store - Product Assignment List.doc
 * @Task#: Auto-767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewSupplyAssignmentInfo extends LicenseManagerTestCase{
	private LicMgrSuppliesListPage suppliesListPage = LicMgrSuppliesListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String storeName = "", vendorName = "";
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private LicMgrSuppliesListPage supplisetPage = LicMgrSuppliesListPage
			.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// supply preparation:check active supply whether existed, if not add a new supply
		lm.gotoSupplySearchListPageFromTopMenu();
		supplisetPage.searchAllSupplierPrd();
		if(suppliesListPage.isThisSupplyExist(supply.code)){
			if(!supplisetPage.checkSupplierActive(supply.code)){
				lm.activeOrDeactiveSupplier(supply.code, "Active");
			}
		}else{
			 lm.addSupplyProudct(supply);
		}
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//unassign supply to store
		lm.unassignSupplyToStore(supply.code);
		storeAssignInfo.isAssigned = false;
		storeAssignInfo.creationUser = "";
		storeAssignInfo.creationDateTime = "";
		//verify un-assignment info
		this.verifyStoreAssignmentInfo(storeAssignInfo);
		
		//assign supply to store
		lm.assignSupplyToStore(supply.code);
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
		
		supply.code = "S33";
		supply.name = "AssignmentList";
		supply.description = "AssignmentList";
		supply.productGroup = "Supply Items";
		supply.availableToApp.put("All",true);
		supply.ofPanels = "2";
		supply.fulfillmentParty = "FulfillmentParty Internal";
		supply.supplyCost = "53";
		supply.shippingCost = "3";
		supply.maxDailyOrder = "3";
		supply.recorderThreshold = "3";
		supply.recorderMail = "bryant.boyd@activenetwork.com";
		supply.qtyOnHand = "10000";		
		
		storeName = "WAL-MART";
		vendorName = "Auto Vendor";	
		
		storeAssignInfo.productCategory = "Supply";
		storeAssignInfo.productCode = supply.code;
		storeAssignInfo.productName = supply.name;
		storeAssignInfo.productGroup = supply.productGroup;	
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
