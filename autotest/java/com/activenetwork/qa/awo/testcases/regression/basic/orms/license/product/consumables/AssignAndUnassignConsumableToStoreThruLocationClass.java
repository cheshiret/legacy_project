/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

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
public class AssignAndUnassignConsumableToStoreThruLocationClass extends
		LicenseManagerTestCase {

	private String locationClass = "";
	private ConsumableInfo consumable = new ConsumableInfo();
	private LicMgrConsumableListPage consumableListPg = LicMgrConsumableListPage.getInstance();
	private LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage.getInstance();
	private LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String creationTimeInCase;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoConsumableSearchListPageFromTopMenu();
		consumable.id = consumableListPg.getConsumableId(consumable.code);
		
		//1. switch to 'Store Assignment' sub-page of Consumable Details page to assign this product to stores thru Location Class
		lm.gotoConsumableProductDetailsPageFromListPage(consumable.id);
		
		//2. get location class with minimum number of agent
		lm.gotoAgentAssignmentsSubPgFromProductDetailPg(consumableDetailsPg);
		consumableStoreAssignmentPg.unassignAllAgentsThroughLocationClass();
		locationClass = consumableStoreAssignmentPg.getLocationClassWithMinNumOfAgents();
		
		//3. assign consumable to store
		lm.assignConsumableToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		
		//4. positive assigning action verification - verify stores which belong to this location class are all assigned this consumable product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);
		
		//5. positive assigning result verification - go to detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getConsumableStoreAssignmentByCode(consumable.code));
		}
		
		//6. un-assign consumable from stores thru location class
		lm.gotoConsumableSearchListPageFromTopMenu();
		lm.gotoConsumableProductDetailsPageFromListPage(consumable.id);
		lm.unassignConsumableFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successfully
		List<List<String>> unassignedLocationClassRecords = consumableStoreAssignmentPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		
		//8. verify un-assigning result correctly
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyStoreProductAssignmentExists(consumable.code, false);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		consumable.orderType = "POS Sale";
		consumable.name = "AssignAndUnassignConsumableToStore";
		consumable.code = "ACS";
		consumable.status = OrmsConstants.ACTIVE_STATUS;
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
		if(!actualProductAssignment.productCode.equalsIgnoreCase(consumable.code)) {
			result &= false;
			logger.error("Product-Store Assignment Code doesn't match.");
		}
		if(!actualProductAssignment.productName.equalsIgnoreCase(consumable.name)) {
			result &= false;
			logger.error("Product-Store Assignment Name doesn't match." + actualProductAssignment.productName + "==" + consumable.name);
		}
		if(!actualProductAssignment.productGroup.equalsIgnoreCase(consumable.productGroup)) {
			result &= false;
			logger.error("Product-Store Assignment Product Group doesn't match.");
		}
		if(!actualProductAssignment.orderType.equalsIgnoreCase(consumable.orderType)) {
			result &= false;
			logger.error("Product-Store Assignment Order Type doesn't match.");
		}
		if(!actualProductAssignment.isAssigned) {
			result &= false;
			logger.error("Product-Store Assignment Assigned Status doesn't match.");
		}
		if(!actualProductAssignment.creationUser.replaceAll(" ", StringUtil.EMPTY).equalsIgnoreCase(DataBaseFunctions.getLoginUserName(login.userName).replaceAll(" ", StringUtil.EMPTY))) {
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
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 120) {
			logger.error("Product-Store Assignment Creation Date/Time doesn't match.");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Consumable-Store Assignment doesn't match expected.");
		} else {
			logger.info("Verify Product Assigned To Store Successfully.");		
		}
	}
}
