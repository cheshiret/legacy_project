package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Customer must have VERIFIED parameter EDUCATION type on file in order to purchase
 * @Preconditions:1.existing 2 customers
 *                (customer(transfer from) has VERIFIED education and new customer(transfer to) doesn't have VERIFIED education)
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 *                3.Verified education is based on Mock Verifier.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 13, 2012
 */
public class NeedVerifiedParamEducationType extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private Customer toCust = new Customer();
	private String expectMsg = "";
	private Education education = new Education();
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrCustomerEducationPage eduPage = LicMgrCustomerEducationPage.getInstance();
	
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
		
		// 2.update DOB of transfer to customer
		cust.dateOfBirth = "Aug 11 1979";//IMPORTANT DOB and education number matches according to Mock Verifier.
		this.updateDOBForCust(cust);
		toCust.dateOfBirth = "Apr 30 1984";//IMPORTANT DOB and education number matches according to Mock Verifier.
		this.updateDOBForCust(toCust);
		
		// 3.add education type for customer(transfer from).
		education.educationNum = "344191465";//IMPORTANT DOB and education number matches according to Mock Verifier.(Don't change this data!!)
		this.manageEducation(cust, "Add", education);// the new added education type must be "Verified"
		education.educationNum = "381020030";//IMPORTANT DOB and education number matches according to Mock Verifier.(Don't change this data!!)
		this.manageEducation(toCust, "Remove", education);

		// 4.make a privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 5.transfer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		
		// 6.add education type for new customer(transfer to)
		this.manageEducation(toCust, "Add", education);// the new added education type must be "Verified"
		
		// 7.transfer again, should succeed
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
		cust.dateOfBirth = "Aug 11 1978";
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule18";
		cust.lName = "TEST-TransferRule18";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule118";
		toCust.lName = "TEST-TransferRule118";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		
		privilege.code = "969";
		privilege.purchasingName = "969-VerifiedParamEduType";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "Customer must have VERIFIED parameter EDUCATION type on file in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].educationType = "HuntEducation";
		ruleInfo.parameters[0].locationClass = "06 - State Parks Agent";
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-5);

		education.status = OrmsConstants.ACTIVE_STATUS;
		education.educationType = "HuntEducation";
		education.state = "Mississippi";
		education.country = "United States";
	}

	/**
	 * Update DOB for customer.
	 * @param customer
	 * @param days
	 */
	private void updateDOBForCust(Customer customer){
		logger.info("Update DOB for customer.");
		lm.gotoCustomerDetailFromTopMenu(customer);
		custDetailsPg.changeDateOfBirth(customer.dateOfBirth);
	}
	
	/**
	 * Manage Education for customer.
	 * @param customer
	 * @param operation
	 * @param edu
	 */
	private void manageEducation(Customer customer, String operation, Education edu){
		logger.info("Manage Education for customer.");
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCustomerSubTabPage("Education");
		if(("Remove".equals(operation) && eduPage.checkEducationExists(edu.educationType, null)) || "Add".equals(operation)){
			lm.manageEducationsForCustomer(operation, null, edu);
		}
	}
}
