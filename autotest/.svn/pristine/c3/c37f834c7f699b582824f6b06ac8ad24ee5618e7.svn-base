package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.store.assignment;

import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify the number of store's product assignments tab 
 * @Preconditions:
 * Some products should be assigned to this store
 * @SPEC: TC:037016
 * @Task#: Auto-970

 * @author VZhang
 * @Date Apr 17, 2012
 */
public class VerifyTabNumber_AssignProduct extends LicenseManagerTestCase{
	private String storeName = "", vendorName = "", expectNumber = "";

	@Override
	public void execute() {
		//get assignment number from DB
		expectNumber = lm.getNumberOfProductAssignmentToStore(schema, storeName);
		if(expectNumber.equals("0")){
			throw new ErrorOnDataException("Please assign some products to this store. The prepared data is not correct.");
		}
		
		lm.loginLicenseManager(login);
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		//verify the assignment number
		this.verifyTabNumber(expectNumber);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		storeName = "WAL-MART";
		vendorName = "Auto Vendor";		
	}
	
	private void verifyTabNumber(String numberOfExpect){
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPage = LicMgrStoreProductAssignmentsPage.getInstance();
		
		logger.info("Verify tab number of assignment.");
		String vauleOfUI = storeProductAssignmentPage.getTabInfo();
		String numberOfUI = vauleOfUI.split("\\(")[1].split("\\)")[0];
		if(!numberOfUI.equals(numberOfExpect)){
			throw new ErrorOnDataException("The tab number is not correct.", numberOfExpect, numberOfUI);
		}else{
			logger.info("The tab number is correct.");
		}
	}
}
