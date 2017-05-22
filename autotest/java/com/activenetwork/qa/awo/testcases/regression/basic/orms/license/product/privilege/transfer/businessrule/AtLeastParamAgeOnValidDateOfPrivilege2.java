package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name: Customer must be AT LEAST parameter age on the valid date of the privilege purchased
 * Will NOT VIOLATE business rule, should transfer successfully.
 * @Preconditions:1.existing 2 customers
 *                (d_hf_add_cust_profile, ID: 1940, 1950)
 *                2.privilege can be purchased and transferred
 *                3.Privilege must have business rule: Customer must be AT LEAST parameter age on the valid date of the privilege purchased
 *                (d_hf_add_pri_business_rule, ID:180)
 *                4.Privilege must have license year which is fiscal year.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class AtLeastParamAgeOnValidDateOfPrivilege2 extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	private String locationSales = "";
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.update DOB of customers in order to not violate the rule.
		this.updateDOBForCust(cust, 0);
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

		//4.transfer, should succeed
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
		cust.fName = "QA-TransferRule10";
		cust.lName = "TEST-TransferRule10";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		toCust.customerClass = "Individual";
		toCust.fName = "QA-TransferRule1110";
		toCust.lName = "Test-TransferRule1110";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		
		privilege.code = "988";
		privilege.purchasingName = "988-AtLeastParamAgeValidDate";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		locationSales = "HF HQ Role - Auto-WAL-MART";
	}
	
	/**
	 * Update DOB for customer.
	 * @param customer
	 * @param days
	 */
	private void updateDOBForCust(Customer customer, int days){
		logger.info("Update DOB for customer."); 
		lm.gotoCustomerDetailFromTopMenu(customer);
		privilege.licYear.validFromDate = DateFunctions.getToday();// privilege instance valid from date is today.
		customer.dateOfBirth = DateFunctions.calculateDate(privilege.licYear.validFromDate, (-20), days);// 20 is age in business rule.
		custDetailsPg.changeDateOfBirth(customer.dateOfBirth);
	}
}
