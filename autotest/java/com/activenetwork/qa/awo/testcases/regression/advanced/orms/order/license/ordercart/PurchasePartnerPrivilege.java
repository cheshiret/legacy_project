package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.ordercart;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify Order Cart and Summary page UI when purchasing Partner Licence
 * @Preconditions: d_hf_add_cust_profile=3270
 *							d_hf_add_privilege_prd=3150,3160
 *							d_hf_add_pricing=4592,4602
 *							d_hf_assi_pri_to_store=2370,2380
 *							d_hf_add_prvi_license_year=3280,3290
 *							d_hf_add_qty_control=2350,2360
 * @SPEC: Order Cart - Privilege Sales - Partner License [TC:123800]
 * 				Order Summary - Privilege Sales - Partner License [TC:123799]
 * @Task#: AUTO-2165
 * 
 * @author qchen
 * @Date  May 20, 2014
 */
public class PurchasePartnerPrivilege extends LicenseManagerTestCase {

	private Customer associatedCust = new Customer();
	private PrivilegeInfo associatedPrivilege = new PrivilegeInfo();
	private OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. make Primary Privilege order
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum = lm.processOrderCart(pay);
		
		//2. get primary privilege number to search and select during associated privilege purchasing workflow
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, ordNum);
		
		associatedPrivilege.primaryPrivilegeNum = privilege.privilegeId;
		//3. make Associated Privilege to order cart
		lm.makePrivilegeToCartFromCustomerTopMenu(associatedCust, associatedPrivilege);
		
		//4. verify Partner Privilege order cart page
		result &= cart.verifyCustomerInfo(associatedCust, null);
		result &= cart.comparePrimaryPrivilegeHolder(cust.fName, cust.lName, cust.custNum);
		result &= cart.comparePrimaryPrivilegeName(privilege.code, privilege.name, privilege.privilegeId);
		
		lm.processOrderToOrderSummary(pay);
		//5. verify Partner Privilege order summary page
		summaryPage.verifyCustomerInfo(associatedCust, null);
		result &= summaryPage.comparePrimaryPrivilegeHolder(cust.fName, cust.lName, cust.custNum);
		result &= summaryPage.comparePrimaryPrivilegeName(privilege.code, privilege.name, privilege.privilegeId);
		
		String associatedOrdNum = summaryPage.getAllOrdNums();
		lm.finishOrder();
		
		//clean up
		lm.reversePrivilegeOrderToCleanUp(associatedOrdNum, privilege.operateReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(ordNum, privilege.operateReason, privilege.operateNote, pay);
		
		if(!result) throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		//Primary customer info
		cust.fName = "QA-SKIndividual08";
		cust.lName = "TEST-SKIndividual08";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19840404";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Saskatchewan Resident";
		cust.mailingAddr.address = "Happy Road";
		cust.mailingAddr.city = "Saskatoon";
		cust.mailingAddr.state = "Saskatchewan";
		cust.mailingAddr.zip = "S7N 5B5";
		
		//Associated customer info
		associatedCust.fName = "QA-SKIndividual09";
		associatedCust.lName = "TEST-SKIndividual09";
		associatedCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		associatedCust.dateOfBirth = "19850505";
		associatedCust.custNum = lm.getCustomerNumByCustName(associatedCust.lName, associatedCust.fName, schema);
		associatedCust.residencyStatus = cust.residencyStatus;
		associatedCust.mailingAddr.address = cust.mailingAddr.address;
		associatedCust.mailingAddr.city = cust.mailingAddr.city;
		associatedCust.mailingAddr.state = cust.mailingAddr.state;
		associatedCust.mailingAddr.zip = cust.mailingAddr.zip;
		
		//Primary privilege info
		privilege.code = "PPP";
		privilege.name = "PrimaryPartnerPrivilege";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.authorizedCust = associatedCust;
		
		//Associated privilege info
		associatedPrivilege.code = "APP";
		associatedPrivilege.name = "AssociatedPartnerPriv";
		associatedPrivilege.purchasingName = associatedPrivilege.code + "-" + associatedPrivilege.name;
		associatedPrivilege.licenseYear = lm.getFiscalYear(schema);
		
		pay = new Payment();
		pay.payType = "Visa*";
		pay.pin = "9671111";
	}
}
