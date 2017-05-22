/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: verify process privilege instance info when purchasing Partner privilege
 * @Preconditions: d_hf_add_cust_profile=3270
 *				   d_hf_add_privilege_prd=3150,3160
 *				   d_hf_add_pricing=4592,4602
 *				   d_hf_assi_pri_to_store=2370,2380
 *				   d_hf_add_prvi_license_year=3280,3290
 *				   d_hf_add_qty_control=2350,2360 
 * @SPEC:
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 12, 2014
 */
public class VerifyRefInstancePartnerPrimaryPri extends LicenseManagerTestCase{
	private Customer partnerCust = new Customer();
	private PrivilegeInfo partnerPrivilege = new PrivilegeInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. make Primary Privilege order which is associated partner privilege order
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum_associated = lm.processOrderCart(pay);
		
		//2. get primary privilege number to search and select during associated privilege purchasing workflow
		String priNum_associated = lm.getPrivilegeNumByOrdNum(schema, ordNum_associated);
		//3. verify primary privilege's partner privilege instance which not associate with partner privilege order
		String act_parterPriInstanceNum = lm.getPartnerInstanceIDByPrivilegeNum(priNum_associated, schema);
		this.verifyReferencePrivilegeInfo("Partner privilege instance number", priNum_associated, StringUtil.EMPTY, act_parterPriInstanceNum);
		
		partnerPrivilege.primaryPrivilegeNum = priNum_associated;
		//4. make Associated Privilege to order cart
		lm.makePrivilegeToCartFromCustomerTopMenu(partnerCust, partnerPrivilege);
		String ordNum_partner = lm.processOrderCart(pay);
		
		//5. get partner privilege number
		String priNum_partner = lm.getPrivilegeNumByOrdNum(schema, ordNum_partner);
		
		//6. verify primary privilege's partner privilege instance which associate with partner privilege order
		act_parterPriInstanceNum = lm.getPartnerInstanceIDByPrivilegeNum(priNum_associated, schema);
		this.verifyReferencePrivilegeInfo("Partner privilege instance number", priNum_associated, priNum_partner, act_parterPriInstanceNum);
		
		//7. verify partner privilege's primary privilege instance
		String act_primaryPriInstanceNum = lm.getPrimaryInstanceIDByPrivilegeNum(priNum_partner, schema);
		this.verifyReferencePrivilegeInfo("Primary privilege instance number", priNum_partner, priNum_associated, act_primaryPriInstanceNum);
		
		//clear up
		lm.reversePrivilegeOrderToCleanUp(ordNum_associated, privilege.operateReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(ordNum_partner, privilege.operateReason, privilege.operateNote, pay);
		
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
		
		//Associated customer info
		partnerCust.fName = "QA-SKIndividual09";
		partnerCust.lName = "TEST-SKIndividual09";
		partnerCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		partnerCust.dateOfBirth = "19850505";
		partnerCust.custNum = lm.getCustomerNumByCustName(partnerCust.lName, partnerCust.fName, schema);
		partnerCust.residencyStatus = cust.residencyStatus;
		
		//Primary privilege info
		privilege.code = "PPP";
		privilege.name = "PrimaryPartnerPrivilege";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.authorizedCust = partnerCust;
		
		//Associated privilege info
		partnerPrivilege.code = "APP";
		partnerPrivilege.name = "AssociatedPartnerPriv";
		partnerPrivilege.purchasingName = partnerPrivilege.code + "-" + partnerPrivilege.name;
		partnerPrivilege.licenseYear = lm.getFiscalYear(schema);
		
		pay = new Payment();
		pay.payType = "Visa*";
		pay.pin = "9671111";
	}
	
	private void verifyReferencePrivilegeInfo(String referenceInfo,String priNum,String expInstanceNum,String actInstanceNum){
		logger.info("Verify reference privilege info.");
		boolean result = MiscFunctions.compareResult("The " + referenceInfo + " of privilege number = " +priNum , expInstanceNum, actInstanceNum);
		if(!result){
			throw new ErrorOnPageException("The " + referenceInfo + " of privilege number = " +priNum + " info not correct, please check log.");
		}else logger.info("The " + referenceInfo + " of privilege number = " +priNum + " info is correct.");
	}
	

}
