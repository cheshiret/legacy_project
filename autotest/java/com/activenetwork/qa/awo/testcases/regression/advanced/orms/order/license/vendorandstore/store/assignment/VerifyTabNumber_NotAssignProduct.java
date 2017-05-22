package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.store.assignment;

import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify the store's product assignments tab info when there no product assigned to this store
 * @Preconditions:
 * Should no product assigned to this store
 * @SPEC: TC:037016
 * @Task#: Auto-970

 * @author VZhang
 * @Date Apr 17, 2012
 */
public class VerifyTabNumber_NotAssignProduct extends LicenseManagerTestCase{
	private String storeName = "", vendorName = "", expectTab = "";

	@Override
	public void execute() {
		//get assignment number from DB
		String numbers = lm.getNumberOfProductAssignmentToStore(schema, storeName);
		if(!numbers.equals("0")){
			lm.unAssignProductToStoreFromDB(schema, storeName);
		}
		
		lm.loginLicenseManager(login);
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//verify the product assignment tab
		this.verifyProductAssignmentTab(expectTab);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		storeName = "NoProductAssign";
		vendorName = "Auto Vendor";
		
		expectTab = "Product Assignments";
	}
	
	private void verifyProductAssignmentTab(String expectTabInfo){
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPage = LicMgrStoreProductAssignmentsPage.getInstance();
		
		logger.info("Verify product assignment tab info.");
		String vauleOfUI = storeProductAssignmentPage.getTabInfo();
		
		if(!vauleOfUI.equals(expectTabInfo)){
			throw new ErrorOnDataException("The product assignment tab info is not correct.", expectTabInfo, vauleOfUI);
		}else{
			logger.info("The product assignment tab info is correct.");
		}
	}
}
