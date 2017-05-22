package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name: Customer must be AT MOST parameter age on the parameter date
 * Will VIOLATE business rule, verify the error message.
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                3.Privilege must have business rule: Customer must be AT MOST parameter age on the parameter date
 *                (d_hf_add_pri_business_rule, ID:250)
 *                4.Privilege must have license year which is fiscal year.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class AtMostParamAgeOnParamDate extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	private String expectMsg, locationSales, paramDate;
	private int paramAge;
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.update DOB of customer, will violate rule when transfer
		this.updateDOBForCust(cust, 1);
		this.updateDOBForCust(toCust, 0);

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

		// 4.transfer, should NOT succeed
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
		cust.fName = "QA-TransferRule13";
		cust.lName = "TEST-TransferRule13";//IMPORTANT: this customer's age must be younger than 30.
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferRule113";
		toCust.lName = "TEST-TransferRule113";//IMPORTANT: this customer's age must be older than 30.
		toCust.identifier.identifierType = "MDWFP #";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "964";
		privilege.purchasingName = "964-AtMostParamAgeOnParamDate";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		// this is param data in business rule. (d_hf_add_pri_business_rule, ID:250)
		paramAge = 30;
		paramDate = "Jul 22 2013";

		locationSales = "HF HQ Role - Auto-WAL-MART";
		String ruleName = "Customer must be AT MOST parameter age on the parameter date";
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
		customer.dateOfBirth = DateFunctions.calculateDate(paramDate, (paramAge+1)*(-1), days);
		custDetailsPg.changeDateOfBirth(customer.dateOfBirth);
	}
}