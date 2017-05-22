package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name: Customer must be a RESIDENT in order to purchase this license
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 7, 2012
 */
public class ResidentCustomer extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
//	private boolean result = true;
	private String expectMsg = "";
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		ruleInfo.id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];
		expectMsg = "Rule id="+ruleInfo.id+" violated : "+ruleInfo.name+". The Privilege Instance cannot be transferred because of violation of business rules with respect to the Customer to whom the privilege is being transferred.";

		// 2.make an privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		
		// 3.transfer, should NOT succeed
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
//		this.verifyMessage(page);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 4. transfer again
		toCust.residencyStatus = "Resident";// Verify point, don't change this data.
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule22";
		cust.lName = "TEST-TransferRule22";
		cust.dateOfBirth = "Aug 12 1986";
		cust.residencyStatus = "Resident";// Verify point, don't change this data.
		cust.additionalProofOfResidency = "MDWFP Approved";
		cust.identifier.identifierType = "Social Security Number";//// Verify point, don't change this data.
		cust.identifier.identifierNum = "123654789";

		privilege.code = "997";
		privilege.purchasingName = "997-TransferResCust";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferRule11";
		toCust.lName = "TEST-TransferRule11";
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.residencyStatus = "Non Resident";// Verify point, don't change this data.
		toCust.identifier.identifierType = "Social Security Number";// Verify point, don't change this data.
		toCust.identifier.identifierNum = "987321456";
		toCust.additionalProofOfResidency = "MDWFP Approved";
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be a RESIDENT in order to purchase this License";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ruleInfo.isTheOnlyOneRule = true;//Quentin[20140401] script has been changed
	}
	
//	/**
//	 * When the Transfer To customer violate the rule, verify the displayed message is correct or not.
//	 */
//	private void verifyMessage(Object page){
//		logger.info("When violate the rule, it should displaye message("+expectMsg+") on the top of confirm customer page.");
//		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
//		if(page == confirmPg){
//			String messageUI = confirmPg.getMessage();
//			if(null == messageUI || "".equals(messageUI)){
//				result = false;
//				logger.error("There isn't any messge display in this page.");
//			} else if(!expectMsg.equals(messageUI)) {
//				result = false;
//				logger.error("Displayed message("+messageUI+") is not the same as expected("+expectMsg+").");
//			}
//		} else if(page != confirmPg){
//			result = false;
//			logger.error("Expect page is LicMgrConfirmCustomerPage!!");
//			if(page instanceof OrmsOrderCartPage){
//				lm.cancelCart();
//				logger.error("Transfer is successfully!This is not expect!");
//			}
//		}
//		if(!result){
//			throw new ErrorOnPageException("Not all the check points are passed! Please check the log above.");
//		}
//	}
}
