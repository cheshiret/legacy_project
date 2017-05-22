/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Mar 18, 2012
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
		List<List<String>> unassignedLocationClassRecords = supplyStoreAssignmentPg.getUnassignedLocationClassStoreRecords();

		//4. positive assigning action verification - verify stores which belong to this location class are all assigned this consumable product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);
		
		//5. negative assigning action verification - verify other stores which don't belong to the location class is NOT assigned this product
		this.verifyNegativeAssignAction(unassignedLocationClassRecords);
		List<StoreInfo> tempStoreList = supplyStoreAssignmentPg.getStoreInfoByLocationClass(unassignedLocationClassRecords.get(new Random().nextInt(unassignedLocationClassRecords.size() - 1)).get(1));
		int tempStoreSize = tempStoreList.size();
		if(tempStoreSize == 1) {
			tempStoreSize += 1;
		}
		StoreInfo aRandomNotAssignedStore = tempStoreList.get(new Random().nextInt(tempStoreSize - 1));
		
		//6. positive assigning result verification - go to detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getSupplyStoreAssignmentByCode(supply.code));
		}
		
		//7. negative assigning result verification - goto a random store detail page to verify the product doesn't be assigned to store which doesn't belong to the selected location class
		lm.gotoStoreProductAssignmentPage(aRandomNotAssignedStore.storeName);
		this.verifyStoreProductAssignmentExists(supply.code, false);
		
		//8. un-assign supply from stores thru location class
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.gotoSupplyDetailFromListPage(supply.code);
		lm.unassignSupplyFromStoresThruLocationClass(locationClass);
		
		//9. verify un-assigning action successfully
		List<List<String>> allAssignments = supplyStoreAssignmentPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyNegativeAssignAction(allAssignments);
		
		//10. verify un-assigning result correctly
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
		supply.code = "SUP";
		supply.name = "AssignSupplyToStore";
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
			logger.info("Product-Store Assignment Assign ID is null.");
		}
		if(!actualProductAssignment.productCode.equalsIgnoreCase(supply.code)) {
			result &= false;
			logger.info("Product-Store Assignment Code doesn't match.");
		}
		if(!actualProductAssignment.productName.equalsIgnoreCase(supply.name)) {
			result &= false;
			logger.info("Product-Store Assignment Name doesn't match." + actualProductAssignment.productName + "==" + supply.name);
		}
		if(!actualProductAssignment.productGroup.equalsIgnoreCase(supply.productGroup)) {
			result &= false;
			logger.info("Product-Store Assignment Product Group doesn't match.");
		}
		if(!actualProductAssignment.isAssigned) {
			result &= false;
			logger.info("Product-Store Assignment Assigned Status doesn't match.");
		}
		if(!actualProductAssignment.creationUser.replaceAll(", ", ",").equalsIgnoreCase(DataBaseFunctions.getLoginUserName(login.userName))) {
			result &= false;
			logger.info("Product-Store Assignment Creation User doesn't match.");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			actualDate = formatter.parse(actualProductAssignment.creationDateTime + " EDT");
		} catch (ParseException e) {
			throw new ErrorOnDataException(e);
		}
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 120) {
			logger.info("Product-Store Assignment Creation Date/Time doesn't match.");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Consumable-Store Assignment doesn't match expected.");
		}
	}

}
