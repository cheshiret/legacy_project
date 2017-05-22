package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Calculate privilege order price in all transactions.
 * @Preconditions: Need 1 existing privilege product - Code#=COP, and 2 existing customers(1#: Green Card=111111, 2#: Tax ID=333333).
 * @SPEC: <<Calculate Privilege Order Price.doc>>
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 23, 2012
 */
public abstract class LicMgrCalculatePrivilegeOrderPriceTestCase extends LicenseManagerTestCase {
	protected Customer anotherCust = new Customer();

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-clcltPrvlPrice1";
		cust.lName = "TEST-clcltPrvlPrice1";
		cust.dateOfBirth = "19880602";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "412693569";
		//cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		anotherCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		anotherCust.fName = "QA-Advanced2";
		anotherCust.lName = "TEST-Advanced2";
		anotherCust.dateOfBirth = "19850224";
		anotherCust.identifier.identifierType = "Tax ID";
		anotherCust.identifier.identifierNum = "333333";
		anotherCust.identifier.country = "Canada";
		anotherCust.residencyStatus = "Non Resident";
		
		pay.payType = OrmsConstants.PMT_TYPE_CASH;
		pay.issueToVoucher = true;
	}
	
	protected void calculateOrderPrice(String transactionType[], String custLname[], String custFname[]) {
		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify privilege order price calculate correct or not.");
		double sumOfAllOredrs = 0.00;
		for(int i = 0; i < transactionType.length; i ++) {
			sumOfAllOredrs += orderCartPage.getOrderPriceByTransactionAndCustName(transactionType[i], custLname[i], custFname[i]);
		}
		
		double totalPrice = orderCartPage.getAmountOwing();
		if(Double.compare(totalPrice, sumOfAllOredrs) != 0) {
			throw new ErrorOnPageException("The Order Price calculate wrongly. Sum of order price is: $" + sumOfAllOredrs + ", but Amount Owing is: $" + totalPrice + ". Please check order cart page manually.");
		} else logger.info("Privilege Order Price is calculated correctly.");
	}
}
