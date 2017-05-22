package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name: Customer must be AT MOST parameter age on the date of privilege purchase
 * Will NOT VIOLATE business rule, should transfer successfully.
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                3.Privilege must have business rule: Customer must be AT MOST parameter age on the date of privilege purchase
 *                (d_hf_add_pri_business_rule, ID: 240)
 *                4.Privilege must have license year which is fiscal year.
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 7, 2012
 */
public class AtMostParameterAgeOnPurchasePrivilegeDate2 extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	private String locationSales;
	int paramAge;
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 1.update DOB of transfer to customer
		this.updateDOBForCust(cust, 1);//Make the customer's age is one day before 21 years old
		this.updateDOBForCust(toCust, 1);//Make the customer's age is just 21 years old

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

		// 4.transfer, should succeed
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
		cust.fName = "QA-TransferRule26";
		cust.lName = "TEST-TransferRule26";// IMPORTANT this customer must be younger than 20.
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		privilege.code = "994";
		privilege.purchasingName = "994-TransferAtMostParamAge";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferRule226";
		toCust.lName = "TEST-TransferRule226";// IMPORTANT this customer must be older than 20 and younger 40
		toCust.identifier.identifierType = "MDWFP #";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		locationSales = "HF HQ Role - Auto-WAL-MART";
		paramAge = 20;// this is param in business rule, don't change it.
	}

	/**
	 * Update DOB for customer.
	 * @param customer
	 * @param days
	 */
	private void updateDOBForCust(Customer customer, int days){
		logger.info("Update DOB for customer.");
		lm.gotoCustomerDetailFromTopMenu(customer);
		customer.dateOfBirth = DateFunctions.calculateDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), (paramAge+1)*(-1), days); 
		custDetailsPg.changeDateOfBirth(customer.dateOfBirth);
	}
}
