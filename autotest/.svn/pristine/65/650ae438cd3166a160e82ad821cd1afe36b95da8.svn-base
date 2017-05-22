package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Customer must have parameter CERTIFICATION type on file in order to purchase
 * @Preconditions:1.existing 2 customers
 *                (customer(transfer from) has certification and new customer(transfer to) doesn't have certification)
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class NeedParamCertificationType extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private String expectMsg = "";
	private String locationSales = "";
	private Certification certification = new Certification();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.add business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		ruleInfo.id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];logger.info("***"+ruleInfo.id);
		expectMsg = "Rule id="+ruleInfo.id+" violated : "+ruleInfo.name+". The Privilege Instance cannot be transferred because of violation of business rules with respect to the Customer to whom the privilege is being transferred.";

		// 2.set up certification type for customer
		// clean up certification type for transfer to customer
		this.removeCertification(toCust);
		// add certification type for transfer from customer
		this.removeCertification(cust);
		lm.addCustomerCertification(certification, true);

		// 3.make a privilege order
		lm.switchLocationInHomePage(locationSales);
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.invalidatePrivilegePerCustomer(toCust, privilege);
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer, should NOT succeed.
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 5.add certification for new customer(transfer to)
		lm.gotoCustomerDetailFromTopMenu(toCust);
		lm.addCustomerCertification(certification, true);

		// 6.transfer again, should succeed
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
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule14";
		cust.lName = "TEST-TransferRule14";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.identifier.identifierType = "MDWFP #";
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.fName = "QA-TransferRule114";
		toCust.lName = "TEST-TransferRule114";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
	
		privilege.code = "965";
		privilege.purchasingName = "965-ParamCertificationType";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "Customer must have parameter CERTIFICATION type on file in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.parameters[0].certificationType = "Trapper Certification";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-5);
		
		certification.status = "Active";
		certification.type = ruleInfo.parameters[0].certificationType;
		certification.num = "AutoTest" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-5);
		certification.effectiveTo = DateFunctions.getDateAfterToday(5);
		
		locationSales = "HF HQ Role - Auto-WAL-MART";
	}
	
	/**
	 * Remove certification type for customer.
	 * @param customer
	 * @param certificatioId
	 */
	private void removeCertification(Customer customer){
		logger.info("Remove certification type for customer.");
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(customer);
		lm.gotoCertificationFromCustomerDetailsPg();
		lm.removeAllCustomerCertifications(true);
	}
}
