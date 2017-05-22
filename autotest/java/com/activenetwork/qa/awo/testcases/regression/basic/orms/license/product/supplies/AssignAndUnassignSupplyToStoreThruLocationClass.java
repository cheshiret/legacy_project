/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyProductDetailsPage;
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
 * @Date  Mar 14, 2012
 */
public class AssignAndUnassignSupplyToStoreThruLocationClass extends
		LicenseManagerTestCase {

	private String locationClass = "";
	private SupplyInfo supply = new SupplyInfo();
	private LicMgrSupplyProductDetailsPage supplyDetailsPg = LicMgrSupplyProductDetailsPage.getInstance();
	private LicMgrSupplyAgentAssignmentsPage supplyStoreAssignmentPg = LicMgrSupplyAgentAssignmentsPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private String creationTimeInCase;
	
	public void execute() {
		lm.loginLicenseManager(login);

		//1. switch to 'Store Assignment' sub-page of Supply Details page to assign this product to stores thru Location Class
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.gotoSupplyDetailFromListPage(supply.code);
		
		//2. get location class with minimum number of agent
		lm.gotoAgentAssignmentsSubPgFromProductDetailPg(supplyDetailsPg);
		supplyStoreAssignmentPg.unassignAllAgentsThroughLocationClass();
		locationClass = supplyStoreAssignmentPg.getLocationClassWithMinNumOfAgents();
		
		//3. assign supply to store
		lm.assignSupplyToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());

		//4. positive assigning action verification - verify stores which belong to this location class are all assigned this consumable product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);
		
		//5. positive assigning result verification - go to detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getSupplyStoreAssignmentByCode(supply.code));
		}
		
		//6. un-assign supply from stores thru location class
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.gotoSupplyDetailFromListPage(supply.code);
		lm.unassignSupplyFromStoresThruLocationClass(locationClass);
		
		//7. verify un-assigning action successfully
		List<List<String>> unassignedLocationClassRecords = supplyStoreAssignmentPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		
		//8. verify un-assigning result correctly
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyStoreProductAssignmentExists(supply.code, false);
		}
		
		lm.logOutLicenseManager();
	}
	
	private void verifyStoreProductAssignmentExists(String code, boolean expectedResult) {
		boolean actualResult = storeProductAssignmentsPg.verifyProductAssignedToStoreByCode("Supply", code);
		if(expectedResult != actualResult) {
			throw new ErrorOnDataException("Supply(Code=" + code + ") should "+ (expectedResult ? "be assigned" : "NOT be assigned to this store."));
		}
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supply.status = "Active";
		supply.code = "ASS";
		supply.name = "AssignAndUnassignSupplyToStore";
		supply.productGroup = "Supply Items";
		
	}
	
	private void verifyNegativeAssignAction(List<List<String>> unassignments) {
		logger.info("----Verify stores are NOT assigned.");
		for(int i = 0; i < unassignments.size(); i++) {
			if(unassignments.get(i).get(2).equalsIgnoreCase("Yes") || Integer.parseInt(unassignments.get(i).get(4)) != 0) {
				throw new ErrorOnDataException("Assigning action affect to other store which are NOT selected.");
			}
		}
	}
	
	private void verifyProductAssignedToStoreSuccessfully(ProductStoreAssignment actualProductAssignment) {
		logger.info("Verify the actual Product-Store Assignment record detail info is correct with expected.");
		boolean result = true;
		if(actualProductAssignment.assignID.length() == 0) {
			result &= false;
			logger.error("Product-Store Assignment Assign ID is null.");
		}
		if(!actualProductAssignment.productCode.equalsIgnoreCase(supply.code)) {
			result &= false;
			logger.error("Product-Store Assignment Code doesn't match.");
		}
		if(!actualProductAssignment.productName.equalsIgnoreCase(supply.name)) {
			result &= false;
			logger.error("Product-Store Assignment Name doesn't match." + actualProductAssignment.productName + "==" + supply.name);
		}
		if(!actualProductAssignment.productGroup.equalsIgnoreCase(supply.productGroup)) {
			result &= false;
			logger.error("Product-Store Assignment Product Group doesn't match.");
		}
		if(!actualProductAssignment.isAssigned) {
			result &= false;
			logger.error("Product-Store Assignment Assigned Status doesn't match.");
		}
		if(!actualProductAssignment.creationUser.replaceAll("\\s+", StringUtil.EMPTY).equalsIgnoreCase(DataBaseFunctions.getLoginUserName(login.userName).replaceAll("\\s+", StringUtil.EMPTY))) {
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
		}else {
			logger.info("Verify Product Assigned To Store Successfully.");		
		}
	}

}
