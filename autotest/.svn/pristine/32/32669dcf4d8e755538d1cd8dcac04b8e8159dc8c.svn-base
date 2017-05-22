package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @Description: Assign/Unassign/View consumable-POS product to/from Stores Thru Location Class
 * @Preconditions:1. Need an existing Consumable product(support script)
 * @SPEC:<<View Product - Store Assignment List thru Location Class.doc>>, 
 * 				<<Assign Product to Stores thru Location Class>> and <<Unassign Product from Stores thru Location Class>>
 * @Task#:Auto-510
 * 
 * @author QA-qchen
 * @Date  May 30, 2011
 */
public class AssignAndUnassignConsumableToStoreThruLocationClass extends LicenseManagerTestCase {
	private String locationClass = "01-MDWFP Headquarters";
	private ConsumableInfo consumable = new ConsumableInfo();
	private LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String creationTimeInCase;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//get consumable product id by consumable name dynamically
		consumable.id = lm.getProductID("Product Name", consumable.name, "", schema);
		
		//1. switch to 'Store Assignment' sub-page of Consumable Details page to assign this product to stores thru Location Class
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
		//unassign first to clean up former dirty data
		lm.unassignConsumableFromStoresThruLocationClass(locationClass);
		lm.assignConsumableToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		List<List<String>> unassignedLocationClassRecords = consumableStoreAssignmentPg.getUnassignedLocationClassStoreRecords();
		
		//2. positive assigning action verification - verify stores which belong to this location class are all assigned this consumable product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);
		
		//3. negative assigning action verification - verify other stores which don't belong to the location class is NOT assigned this product
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		List<StoreInfo> tempStoreList = consumableStoreAssignmentPg.getStoreInfoByLocationClass(unassignedLocationClassRecords.get(new Random().nextInt(unassignedLocationClassRecords.size() - 1)).get(1));
		int tempStoreSize = tempStoreList.size();
		if(tempStoreSize == 1) {
			tempStoreSize += 1;
		}
		StoreInfo aRandomNotAssignedStore = tempStoreList.get(new Random().nextInt(tempStoreSize - 1));
		
		//4. positive assigning result verification - go to detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
//		for(int i = 0; i < assignedStores.size(); i ++) {
		int assignedStoresNum = assignedStores.size() % 10;
		for(int i = 0; i < assignedStoresNum; i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getConsumableStoreAssignmentByCode(consumable.code));
		}
		
		//5. negative assigning result verification - goto a random store detail page to verify the product doesn't be assigned to store which doesn't belong to the selected location class
		lm.gotoStoreProductAssignmentPage(aRandomNotAssignedStore.storeName);
		this.verifyStoreProductAssignmentExists(consumable.code, false);
		
		//6. un-assign consumable from stores thru location class
		lm.gotoConsumableSearchListPageFromTopMenu();
		lm.gotoConsumableProductDetailsPageFromListPage(consumable.id);
		lm.unassignConsumableFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successfully
		unassignedLocationClassRecords = consumableStoreAssignmentPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		
		//8. verify un-assigning result correctly
		lm.gotoVendorSearchPg();
//		for(int i = 0; i < assignedStores.size(); i ++) {
		for(int i = 0; i < assignedStoresNum; i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyStoreProductAssignmentExists(consumable.code, false);
		}
		
		//9. de-activate consumable product and log out system
		lm.deactivateConsumableProduct(consumable.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		consumable.orderType = "POS Sale";
		consumable.name = "AssignAndUnassignConsumableToStoreThruLocationClass";
		consumable.code = "POS";
		consumable.status = "Active";
		consumable.productGroup = "Other";
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
		boolean actualResult = storeProductAssignmentsPg.verifyProductAssignedToStoreByCode("Consumable", code);
		if(expectedResult != actualResult) {
			throw new ErrorOnDataException("Consumable(Code=" + code + ") should "+ (expectedResult ? "be assigned" : "NOT be assigned to this store."));
		}
	}
	
	private void verifyProductAssignedToStoreSuccessfully(ProductStoreAssignment actualProductAssignment) {
		logger.info("Verify the actual Product-Store Assignment record detail info is correct with expected.");
		boolean result = true;
		if(actualProductAssignment.assignID.length() == 0) {
			result &= false;
			logger.error("Product-Store Assignment Assign ID is null.");
		}
		result &= MiscFunctions.compareResult("Product-Store Assignment Code", consumable.code, actualProductAssignment.productCode);
		result &= MiscFunctions.compareResult("Product-Store Asignment Name", consumable.name, actualProductAssignment.productName);
		result &= MiscFunctions.compareResult("Product-Store Assignment Product Group", consumable.productGroup, actualProductAssignment.productGroup);
		result &= MiscFunctions.compareResult("Product-Store Assignment Order Type", consumable.orderType, actualProductAssignment.orderType);
		result &= MiscFunctions.compareResult("Product-Store Assignment Assigned Status", true, actualProductAssignment.isAssigned);
		result &= MiscFunctions.compareResult("Product-Store Assignment", DataBaseFunctions.getLoginUserName(login.userName), actualProductAssignment.creationUser.replace(", ", ","));
		
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			actualDate = formatter.parse(actualProductAssignment.creationDateTime + " EDT");
		} catch (ParseException e) {
			throw new ErrorOnDataException(e);
		}
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 120) {
			logger.error("Product-Store Assignment Creation Date/Time doesn't match.");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Consumable-Store Assignment doesn't match expected.");
		}
	}
}
