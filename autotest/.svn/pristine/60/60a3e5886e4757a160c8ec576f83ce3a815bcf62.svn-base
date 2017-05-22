/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile.custclassconfig;


import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Added by Nicole:
 * Business can't purchase consumable.
 * Verify point:
 * No message popped up after click Purchase Privilege button, but the 'Consumable' tab is not displayed in add privilege item page.
 * 
 * @Description: This case was designed to verify business class customer could not purchase consumable in license manager
 * since customer class configuration has been set up in table C_CUST_CLASS_CFG;
 * @Preconditions: customer class configuration has been set up in table C_CUST_CLASS_CFG
   insert into C_CUST_CLASS_CFG (ID, APP_ID, PRD_GRP_CAT_ID, PRD_SUBCAT_ID, CUST_CLASS_ID) 
   values (GET_SEQUENCE('C_CUST_CLASS_CFG'),19,4,null,2);
 * @SPEC: TC:038802
 * @Task#: Auto-1065
 * @author Jane Wang
 * @Date  Jun 4, 2012
 */
public class PurchaseConsumableWithBusinessCust_LM extends LicenseManagerTestCase {

	public void execute() {
		//Verify customer class config has been set up for business class customer could not purchase consumable in license manager
		verifyPreConditionForCustClassConfig();
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(cust);
		verifyNoConsumableTabForPurchaseConsumableWithBusinessCust();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		consumable.name="PFS - POSForSale";
		
		cust.customerClass = "Business";
		cust.lName = "QACustClassConfigTestB";
		cust.fName = "QACustClassConfigTestB";
		cust.dateOfBirth = "1984-8-1";
		cust.residencyStatus = "Non Resident";
	}
	
	/**
	 * Verify customer class config has been set up for business class customer could not purchase consumable in license manager
	 */
	private void verifyPreConditionForCustClassConfig(){
		logger.info("Verify pre-condition for test case.");
		//19 - License Manager, 2 - Business Customer Class
		if(!lm.verifyCustClassConfigPrecondition(schema, "19", "4", null, "2", false)){
			throw new ErrorOnDataException("Consumable can NOT be purchased by Business class customer in LM. Please clean up data in table C_CUST_CLASS_CFG manually.");
		}
		logger.info("The pre-condition for test case was set up correctly in DataBase.");
	}
	
	private void verifyNoConsumableTabForPurchaseConsumableWithBusinessCust(){
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrIdentifyCustomerPage identifyPg = LicMgrIdentifyCustomerPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();

		logger.info("Purchase Consumable from customer detail page.");
		custDetailPg.clickPurchasePrivilege();
		ajax.waitLoading();
		identifyPg.waitLoading();
		identifyPg.selectResidencyStatus(cust.residencyStatus);
		ajax.waitLoading();
		identifyPg.waitLoading();
		identifyPg.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
		boolean existed = addItemPg.checkConsumablesTabExisted();
		if(existed)
			throw new ErrorOnPageException("Business class customer could not purchase consumble in License Manager.");
		logger.info("Verify business class customer could not purchase consumable in License Manager successfully.");
	}
}
