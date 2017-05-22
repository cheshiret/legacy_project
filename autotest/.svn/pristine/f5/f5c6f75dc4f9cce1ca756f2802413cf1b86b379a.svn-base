package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify inactive store - supply assignment info.
 * Assign a supply to store, then un-assign this supply to store, and check the inactive assignment info
 * @Preconditions:
 * @SPEC: View Inactive Store - Product Assignment List
 * @Task#:Auto - 767

 * @author VZhang
 * @Date Dec 9, 2011
 */
public class ViewInactiveSupplyAssignList extends LicenseManagerTestCase{
	private LicMgrSuppliesListPage suppliesListPage = LicMgrSuppliesListPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private LicMgrProductInactiveAssignmentsWidget viewInactiveAssignmentPg = LicMgrProductInactiveAssignmentsWidget.getInstance();
	private ProductStoreAssignment storeAssignInfo = new ProductStoreAssignment();
	private String storeName = "", vendorName = "";

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// supply preparation:check active supply whether existed, if not add a new supply
		lm.gotoSupplySearchListPageFromTopMenu();
		if(!suppliesListPage.isThisSupplyExist(supply.code)){
			lm.addSupplyProudct(supply);
		}
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//assign supply to store
		lm.assignSupplyToStore(supply.code);
		storeAssignInfo.assignID = storeProductAssignmentPg.getProductStoreAssignmentIdByCode(supply.code);
		//unassign supply to store
		lm.unassignSupplyToStore(supply.code);
		
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
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supply.code = "U33";
		supply.name = "InactiveAssignmentList";
		supply.description = "InactiveAssignmentList";
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
		
		storeAssignInfo.productCategory = "Supply";
		storeAssignInfo.productCode = supply.code;
		storeAssignInfo.productName = supply.name;
		storeAssignInfo.productGroup = supply.productGroup;	
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
