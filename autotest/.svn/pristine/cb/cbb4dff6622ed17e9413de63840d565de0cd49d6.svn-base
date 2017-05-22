package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Customer must be AT LEAST parameter age on the parameter date
 * Will VIOLATE business rule, verify the error message.
 * @Preconditions:1.existing 2 customers
 *                (customer(transfer from) is older than new customer(transfer to))
 *                2.privilege can be purchased and transferred
 *                3.Privilege must have business rule: Customer must be AT MOST parameter age on the parameter date
 *                (d_hf_add_pri_business_rule, ID:270)
 *                4.Privilege must have license year which is fiscal year.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 7, 2012
 */
public class AtLeastParamAgeOnParamDate extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	private String expectMsg, locationSales, paramDate;
	private int paramAge;
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.update DOB of transfer to customer
		this.updateDOBForCust(cust, 0);
		this.updateDOBForCust(toCust, 1);

		// 2.clean up previous privilege orders
		lm.switchLocationInHomePage(locationSales);
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.invalidatePrivilegePerCustomer(toCust, privilege);

		// 3.make a privilege order
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 4.transfer, should NOT succeed.
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		Object page = lm.transferPrivToOrderCart(toCust, privilege);
		lm.verifyBusinessRuleErrorMessage(page, expectMsg);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.identifierType = "MDWFP #";//Tax ID: 20121220
		cust.fName = "QA-TransferRule1111";
		cust.lName = "TEST-TransferRule1111";//IMPORTANT: this customer's age must be older than 20
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule111";
		toCust.lName = "TEST-TransferRule111";//IMPORTANT: this customer's age must be younger than 20 and older than 10.
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "962";
		privilege.purchasingName = "962-AtLeastParamAgeParamDate";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		// this is param data in business rule. (d_hf_add_pri_business_rule, ID:270)
		paramAge = 20;
		paramDate = "Aug 09 2013";
		
		locationSales = "HF HQ Role - Auto-WAL-MART";
		String ruleName = "Customer must be AT LEAST parameter age on the parameter date";
		expectMsg = "Rule id=\\d+ violated : "+ruleName+".*";
	}
	
	/**
	 * Update DOB for customer.
	 * @param customer
	 * @param days
	 */
	private void updateDOBForCust(Customer customer, int days){
		logger.info("Update DOB for customer.");
		lm.gotoCustomerDetailFromTopMenu(customer);
		customer.dateOfBirth = DateFunctions.calculateDate(paramDate,  paramAge*(-1), days); 
		custDetailsPg.changeDateOfBirth(customer.dateOfBirth);
	}
}
