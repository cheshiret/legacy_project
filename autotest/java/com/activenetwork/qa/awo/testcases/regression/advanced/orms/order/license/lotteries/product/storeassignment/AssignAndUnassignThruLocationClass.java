package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.storeassignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Assign and Unassign lottery product to stores throught location class, and verify the result.
 * @Preconditions:
 * 1. Make sure the role has been assigned the features: 
 * ViewPrivilegeLotteryProductStoreAssignmentViaLocClass
 * AssignPrivilegeLotteryProductToStoresViaLocClass
 * UnassignPrivilegeLotteryProductFromStoresViaLocClass
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=30
 * @SPEC: 
 * View Store Assignments List through Location Class - UI [TC:046469]
 * View Product - Store Assignment List thru Location Class - UC [TC:044943]
 * Assign Product to Stores thru Location Class - UC [TC:044944]
 * Unassign Product from Stores thru Location Class - UC [TC:044945]
 * Stores in Location Class Popup [TC:046470]
 * Actively Assigned Stores in Location Class Popup [TC:046471]
 * @Task#: Auto-2061
 * 
 * @author Lesley Wang
 * @Date  Jan 13, 2014
 */
public class AssignAndUnassignThruLocationClass extends LicenseManagerTestCase {
	private String locationClass = "";
	private LicMgrLotteryProductDetailsPage lotteryDetailPg = LicMgrLotteryProductDetailsPage.getInstance();
	private LicMgrStoreProductAssignmentsPage storeProductAssignmentsPg = LicMgrStoreProductAssignmentsPage.getInstance();
	private ILicMgrProductAgentAssignmentsPage lotteryStoreAssignmentsPg = LicMgrLotteryStoreAssignmentsPage.getInstance();
	private String creationTimeInCase;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//1. go to 'Store Assignments' sub page of Privilege Details page to assign product to stores thru Location Class
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		
		//2. get location class with minimum number of agent
		lm.gotoAgentAssignmentsSubPgFromProductDetailPg(lotteryDetailPg);
		lotteryStoreAssignmentsPg.unassignAllAgentsThroughLocationClass();
		locationClass = lotteryStoreAssignmentsPg.getLocationClassWithMinNumOfAgents();
		
		//3. assign privilege to store
		lm.assignLotteryProductToStoresThruLocationClass(locationClass);
		creationTimeInCase = String.valueOf(DateFunctions.getCurrentTime());
		
		//4. positive assigning action verification - verify stores which belong to this location class are all assigned this product
		List<StoreInfo> assignedStores = lm.verifyAssignProductToStoresThruLocationClassAction(locationClass);

		//5. positive assigning result verification - go to store detail page to verify the Product-Store Assignment ID, the known User who added the record, the known logged-in Location of the User,
		//and the Date & Time of addition set to the Current Date & Time
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyProductAssignedToStoreSuccessfully(storeProductAssignmentsPg.getLotteryStoreAssignmentByCode(lotteryPrd.getCode()));
		}

		//6. un-assign privilege from stores thru location class
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		lm.unassignLotteryProductToStoresThruLocationClass(locationClass);
		lm.gotoAgentAssignmentsSubPgFromProductDetailPg(lotteryDetailPg);
		
		//7. verify un-assigning action successful
		List<List<String>> unassignedLocationClassRecords = lotteryStoreAssignmentsPg.getAllLocationClassStoreAssignmentsInfo();
		this.verifyUnassignAction(unassignedLocationClassRecords);
		
		//8. verify un-assigning result correctly
		lm.gotoVendorSearchPg();
		for(int i = 0; i < assignedStores.size(); i ++) {
			 lm.gotoStoreProductAssignmentPage(assignedStores.get(i).storeName);
			 this.verifyStoreProductAssignmentExists(lotteryPrd.getCode(), false);
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		lotteryPrd.setCode("PL1");
		lotteryPrd.setDisplayCategory("Freshwater Fishing");
		lotteryPrd.setDisplaySubCategory("Annual");
		lotteryPrd.setDescription("PrivilegeLotterySetup1");
		lotteryPrd.setSpecies(new String[] {"Band-Tailed Pigeons"});
	}
	
	private void verifyUnassignAction(List<List<String>> unassignments) {
		logger.info("----Verify stores are NOT assigned.");
		for(int i = 0; i < unassignments.size(); i++) {
			if(unassignments.get(i).get(2).equalsIgnoreCase("Yes") || Integer.parseInt(unassignments.get(i).get(4)) != 0) {
				throw new ErrorOnDataException("Assigning action affect to other store which are NOT selected.");
			}
		}
	}
	
	private void verifyStoreProductAssignmentExists(String code, boolean expectedResult) {
		boolean actualResult = storeProductAssignmentsPg.verifyProductAssignedToStoreByCode("Lottery", code);
		if(expectedResult != actualResult) {
			throw new ErrorOnDataException("Lottery(Code=" + code + ") should "+ (expectedResult ? "be assigned" : "NOT be assigned to this store."));
		}
	}
	
	private void verifyProductAssignedToStoreSuccessfully(ProductStoreAssignment actualProductAssignment) {
		logger.info("Verify the actual Product-Store Assignment record detail info is correct with expected.");
		
		boolean result = true;
		if(actualProductAssignment.assignID.length() == 0) {
			result &= false;
			logger.error("Product-Store Assignment Assign ID is null.");
		}
		result &= MiscFunctions.compareString("Product-Store Assignment Display Category", lotteryPrd.getDisplayCategory(), actualProductAssignment.displayCategory);
		result &= MiscFunctions.compareString("Product-Store Assignment Code", lotteryPrd.getCode(), actualProductAssignment.productCode);
		result &= MiscFunctions.compareString("Product-Store Assignment Name", lotteryPrd.getDescription(), actualProductAssignment.productName);
		result &= MiscFunctions.compareString("Product-Store Assignment Species", lotteryPrd.getSpecies()[0], actualProductAssignment.species);
		result &= MiscFunctions.compareResult("Product-Store Assignment Assigned Status", true, actualProductAssignment.isAssigned);
		
		String userName = DataBaseFunctions.getLoginUserName(login.userName);
		result &= MiscFunctions.compareResult("Product-Store Assignment Creation User", userName.replace(", ", ","), actualProductAssignment.creationUser.replace(", ", ","));
		
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			actualDate = formatter.parse(actualProductAssignment.creationDateTime + " EDT");
		} catch (ParseException e) {
			throw new ErrorOnDataException(e);
		}
		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 6000) {
			result &= false;
			logger.error("Product-Store Assignment Creation Date/Time doesn't match.");
		} else {
			logger.info("Product-Store Assignment Creation Date/Time is correct!");
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Privilege-Store Assignment doesn't match expected.");
		}else {
			logger.info("Verify Product Assigned To Store Successfully.");		
		}
	}
}
