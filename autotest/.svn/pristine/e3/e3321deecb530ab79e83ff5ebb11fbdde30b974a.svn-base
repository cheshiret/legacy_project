package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.ordercart;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Order Cart and Order Summary UI when purchasing Authorized Allocation Privilege with Outfitter customer
 * @Preconditions: D_ASSIGN_FEATURE=6202
 * 							d_hf_add_cust_profile=3250,3260
 *							d_hf_add_allo_type_licyear=80
 *							d_hf_assoc_cust_store=80
 *							d_hf_add_outfitter_alloc=80
 *						  	d_hf_add_privilege_prd=3120,3140
 *							d_hf_add_pricing=4562,4582
 *							d_hf_assi_pri_to_store=2340,2360
 *							d_hf_add_prvi_license_year=3250,3270
 *							d_hf_add_qty_control=2320,2340
 *							d_hf_assi_allo_type=40
 * @SPEC: Order Cart - Wildlife Outfitter [TC:058429]
 * 				Order Summary - Wildlife Outfitter [TC:058430]
 * @Task#: AUTO-2165
 * 
 * @author qchen
 * @Date  May 26, 2014
 */
public class PurchaseOutfitterAuthorizedPrivilege extends LicenseManagerTestCase {
	private Data<PrivilegePurchaseAuthorization> authorization = new Data<PrivilegePurchaseAuthorization>();
	private Customer individualCust = new Customer();
	private PrivilegeInfo individualPrivilege = new PrivilegeInfo();
	private OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private String outfitterPrivilegeNameAndNum;
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//clean up previous Outfitter and Individual privilege orders
		lm.reverseAllPrivilegesPerCustomer(individualCust, privilege.operateReason, privilege.operateNote);
		lm.reverseAllPrivilegesPerCustomer(cust, privilege.operateReason, privilege.operateNote);
		
		//1. add an Authorization in customer details page
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerPurchaseAuthorizationPage();
		lm.addPrivilegePurchaseAuthorization(authorization);
		
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		//2. verify Order Cart page - Outfitter customer purchase outifitter privilege
		result &= cart.verifyCustomerInfo(cust, null);
		result &= cart.compareOutfitterLicenseNum(cust.businessNum);
		result &= cart.compareAuthorizationCustomer(privilege.authorizedCust.fName, privilege.authorizedCust.lName, privilege.authorizedCust.custNum);
		
		lm.processOrderCartToSummary(pay);
		
		//3. verify Order Summary page - Outfitter customer purchase outifitter privilege
		result &= summaryPage.verifyCustomerInfo(cust, null);
		result &= summaryPage.compareOutfitterLicenseNum(cust.businessNum);
		result &= summaryPage.compareAuthorizationCustomer(privilege.authorizedCust.fName, privilege.authorizedCust.lName, privilege.authorizedCust.custNum);
		
		String ordNum = summaryPage.getAllOrdNums();
		lm.finishOrder();
		outfitterPrivilegeNameAndNum += lm.getPrivilegeNumByOrdNum(schema, ordNum);
		
		//4. verify Order Cart page - Individual Customer purchase individual privilege
		lm.makePrivilegeToCartFromCustomerQuickSearch(individualCust, individualPrivilege);
		result &= cart.verifyCustomerInfo(individualCust, null);
		result &= cart.compareOutfitterLicenseNum(cust.businessNum);
		result &= cart.checkCustInfoOrVehiclInfo(outfitterPrivilegeNameAndNum);
		
		lm.processOrderCartToSummary(pay);
		//5. verify Order Summary page - Individual Customer purchase individual privilege
		result &= summaryPage.verifyCustomerInfo(individualCust, null);
		result &= summaryPage.compareOutfitterLicenseNum(cust.businessNum);
		result &= summaryPage.checkCustInfoOrVehiclInfo(outfitterPrivilegeNameAndNum);
		
		String ordNum2 = summaryPage.getAllOrdNums();
		lm.finishOrder();
		
		//clean up
		lm.reversePrivilegeOrderToCleanUp(ordNum2, privilege.operateReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(ordNum, privilege.operateReason, privilege.operateNote, pay);
		
		if(!result) throw new ErrorOnPageException("Not all checkpoints are passed, please refer log for details info.");
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		//Outifitter customer info
		cust.fName = "QA-SKOutfitter07";
		cust.lName = "TEST-SKOutfitter07";
		cust.businessName = "Outfitter4OrderCartSummary2";
		cust.businessNum = "O4OCS0620";
		cust.customerClass = OrmsConstants.OUTFITTER_CUSTOMER_CLASS;
		cust.dateOfBirth = "19820202";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Saskatchewan Resident";
		cust.mailingAddr.address = "Happy Road";
		cust.mailingAddr.city = "Saskatoon";
		cust.mailingAddr.state = "Saskatchewan";
		cust.mailingAddr.zip = "S7N 5B5";
		
		//Individual customer info
		individualCust.fName = "QA-SKIndividual07";
		individualCust.lName = "TEST-SKIndividual07";
		individualCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		individualCust.dateOfBirth = "19830303";
		individualCust.custNum = lm.getCustomerNumByCustName(individualCust.lName, individualCust.fName, schema);
		individualCust.residencyStatus = cust.residencyStatus;
		individualCust.mailingAddr.address = cust.mailingAddr.address;
		individualCust.mailingAddr.city = cust.mailingAddr.city;
		individualCust.mailingAddr.state = cust.mailingAddr.state;
		individualCust.mailingAddr.zip = cust.mailingAddr.zip;
		
		//Outfitter privilege info
		privilege.code = "ACS";
		privilege.name = "Auth Cart Summary";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.authorizedCust = individualCust;
		
		//Individual privilege info
		individualPrivilege.code = "2CS";
		individualPrivilege.name = "Individual Cart Summary 2";
		individualPrivilege.purchasingName = individualPrivilege.code + "-" + individualPrivilege.name;
		individualPrivilege.licenseYear = lm.getFiscalYear(schema);
		
		//outfitter privilege name and number in cart when purchasing Individual privilege by Individual customer
		outfitterPrivilegeNameAndNum = privilege.name + " #: ";
		
		//customer Purchase Authorization info
		authorization.put(PrivilegePurchaseAuthorization.AuthorizationType, "General");
		authorization.put(PrivilegePurchaseAuthorization.AuthedPrivilege, privilege.code + " - " + privilege.name);
		authorization.put(PrivilegePurchaseAuthorization.AuthedPrivLicenseYear, privilege.licenseYear);
		authorization.put(PrivilegePurchaseAuthorization.AuthedReason, this.fullCaseName);
		
		pay = new Payment();
		pay.payType = "Visa*";
		pay.pin = "9671111";
	}
}
