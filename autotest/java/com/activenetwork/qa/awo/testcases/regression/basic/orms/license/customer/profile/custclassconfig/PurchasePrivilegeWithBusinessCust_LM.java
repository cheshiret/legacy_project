/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile.custclassconfig;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed to verify business class customer could not purchase privilege successfully in license manager
 * since customer class configuration has been set up in table C_CUST_CLASS_CFG;
 * 
 * Checkpoint1: There is no business class radio button for purchase privilege on identify customer page; 
 * Checkpoint2: The error message pop up when click purchase privilege on business class customer detail page;
 * 
 * @Preconditions: customer class configuration has been set up in table C_CUST_CLASS_CFG
   insert into C_CUST_CLASS_CFG (ID, APP_ID, PRD_GRP_CAT_ID, PRD_SUBCAT_ID, CUST_CLASS_ID) 
   values (GET_SEQUENCE('C_CUST_CLASS_CFG'),19,10,null,2);
 * @SPEC: TC:038802
 * @Task#: Auto-1065
 * This case should be de-activated, cause some test cases need to purchase privilege in License Manager with business class customer.
 * @author Jane Wang
 * @Date  Jun 5, 2012
 */
public class PurchasePrivilegeWithBusinessCust_LM extends
		LicenseManagerTestCase {

	private String errorMsg = "The customer is of customer class that is not allowed to purchase privilege through this application."; 
	
	public void execute() {
		//Verify customer class config has been set up for business class customer could not purchase privilege in license manager
		verifyPreConditionForCustClassConfig();
		
		lm.loginLicenseManager(login);
		//Verify there is no business radio button for purchase privilege on identify customer page
		verifyIdentifyCustPageForPrivilegeSale();
		
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		//Verify error message for purchase privilege on business class customer detail page
		verifyErrorMsgForPurchasePrivilegeWithBusinessCust();
		lm.logOutLicenseManager();
	}

	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		cust.customerClass = "Business";
		cust.lName = "QACustClassConfigTestB";
		cust.fName = "QACustClassConfigTestB";
	}
	
	/**
	 * Verify customer class config has been set up for business class customer could not purchase privilege in license manager
	 */
	private void verifyPreConditionForCustClassConfig(){
		logger.info("Verify pre-condition for test case.");
		if(!lm.verifyCustClassConfigPrecondition(schema, "19", "10", null, "2", false)){
			throw new ErrorOnDataException("Privilege should NOT be sold by business class customer in license manager. Please clean up data in table C_CUST_CLASS_CFG manually.");
		}
		logger.info("The pre-condition for test case was set up correctly in DataBase.");
	}
	
	/**
	 * Verify there is no business radio button for purchase privilege on identify customer page
	 */
	private void verifyIdentifyCustPageForPrivilegeSale(){
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrIdentifyCustomerPage identifyCustPage = LicMgrIdentifyCustomerPage.getInstance();

		logger.info("Go to Identify Customer page from Home page.");
		homePg.clickPurchasePrivilege();
		identifyCustPage.waitLoading();
		
		if(identifyCustPage.getCustClass().contains(cust.customerClass)){
			throw new ErrorOnPageException("The business class customer should not show for choose on identify customer page.");
		}
		
		logger.info("--The business class cusotmer does not exist on identify customer page.");
	}
	
	/**
	 * Verify error message for purchase privilege on business class customer detail page
	 */
	private void verifyErrorMsgForPurchasePrivilegeWithBusinessCust(){
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialog = LicMgrConfirmDialogWidget.getInstance();
		LicMgrIdentifyCustomerPage identifyPg = LicMgrIdentifyCustomerPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		logger.info("Purchase privilege from customer detail page.");
		custDetailPg.clickPurchasePrivilege();
		Object page = browser.waitExists(confirmDialog, identifyPg, addItemPg);
		if(page == confirmDialog){
			String content = confirmDialog.getMessage();
			if(!content.equalsIgnoreCase(errorMsg)){
				throw new ErrorOnPageException("The pop up message was not correct.", errorMsg, content);
			}
			logger.info("---Verify pop up error message was correct when purchase privilege with customer clas that is not allowed.");
			confirmDialog.clickOK();
			custDetailPg.waitLoading();
		}else{
			throw new ActionFailedException("Error message didn't pop up to forbid purchase privilege with customer clas that is not allowed.");
		}
	}
}
