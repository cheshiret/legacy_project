/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectPrimaryPrivilegeWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: verify select primary privilege page info when purchasing Partner privilege
 * @Preconditions: d_hf_add_cust_profile=3260
 *				   d_hf_add_privilege_prd=3220,3230
 *				   d_hf_add_pricing=4652,4662
 *				   d_hf_assi_pri_to_store=2400,2410
 *				   d_hf_add_prvi_license_year=3310,3320
 *				   d_hf_add_qty_control=2380,2390
 * @SPEC:Process Privilege Sale - Partner License [TC:123794]
 *       Purchase Privilege (New & Duplicate) - Partner License [TC:123797]
 *       Purchase Privilege - Partner License [TC:123798]
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 12, 2014
 */
public class VerifySelectPrimaryPrivilegeUIAndMsg extends LicenseManagerTestCase{
	private LicMgrSelectPrimaryPrivilegeWidget selectPrimaryPrivWidget = LicMgrSelectPrimaryPrivilegeWidget.getInstance();
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	private Customer partnerCust = new Customer();
	private PrivilegeInfo partnerPrivilege = new PrivilegeInfo();
	private String message_notSpefic,message_notCorrect,message_hasAssociated;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. make Primary Privilege order which is associated partner privilege order
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum_associated = lm.processOrderCart(pay);
		
		//2. get primary privilege number to search and select during associated privilege purchasing workflow
		String priNum_associated = lm.getPrivilegeNumByOrdNum(schema, ordNum_associated);
		
		partnerPrivilege.primaryPrivilegeNum = priNum_associated;
		//3. make Associated Privilege to order cart
		lm.makePrivilegeToCartFromCustomerTopMenu(partnerCust, partnerPrivilege);
		String ordNum_partner = lm.processOrderCart(pay);
		
		//4. make a primary privilege order which is not associated partner privilege order
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum_notAssociated = lm.processOrderCart(pay);
		
		//5. get primary privilege number which is not associated partner privilege order
		String priNum_notAssociated = lm.getPrivilegeNumByOrdNum(schema, ordNum_notAssociated);
		
		partnerPrivilege.primaryPrivilegeNum = priNum_notAssociated;
		partnerPrivilege.primaryPrivilegeName = "(" + privilege.licenseYear + ")" + privilege.purchasingName;
		partnerPrivilege.primaryPrivilegeHolder = cust.fName + " " + cust.lName + "(" + cust.custNum + ")";
		lm.gotoAddItemPgFromCustomerTopMenu(partnerCust);
		//6. verify error message and UI info at select primary privilege page
		this.gotoSelectPrimaryPrivilegePageFromAddItemPage(partnerPrivilege);
		//verify error message when setup primary privilege number with blank
		this.verifyUIWithsetupPrimaryPrivilegeInfo(StringUtil.EMPTY, false, message_notSpefic,StringUtil.EMPTY,StringUtil.EMPTY);
		//verify error message when setup primary privilege number with not correct
		this.verifyUIWithsetupPrimaryPrivilegeInfo("123452", false, message_notCorrect,StringUtil.EMPTY,StringUtil.EMPTY);
		//verify error message when setup primary privilege number with associated partner privilege order
		this.verifyUIWithsetupPrimaryPrivilegeInfo(priNum_associated, false, message_hasAssociated,StringUtil.EMPTY,StringUtil.EMPTY);
		//verify setup a valid privilege number
		this.verifyUIWithsetupPrimaryPrivilegeInfo(priNum_notAssociated, true, StringUtil.EMPTY,partnerPrivilege.primaryPrivilegeName,partnerPrivilege.primaryPrivilegeHolder);
		
		this.gotoAddItemPageFromSelectPrimaryPrivilegePage();
		
		//clean up
		lm.reversePrivilegeOrderToCleanUp(ordNum_associated, privilege.operateReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(ordNum_partner, privilege.operateReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(ordNum_notAssociated, privilege.operateReason, privilege.operateNote, pay);
		
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
		
		message_notSpefic = "Licence # is required. Please enter the Licence # to continue.";
		message_notCorrect = "The specified Licence cannot have a Partner Licence association.";
		message_hasAssociated = "The specified Licence already has an associated Partner Licence.";
	}
	
	public void gotoSelectPrimaryPrivilegePageFromAddItemPage(PrivilegeInfo privilege){
		logger.info("Go to select primary privilege page from add item page.");
		addItemPg.addProductToCart(privilege.purchasingName,privilege.licenseYear, privilege.qty);
		selectPrimaryPrivWidget.waitLoading();
	}
	
	private void verifyUIWithsetupPrimaryPrivilegeInfo(String privilegeNum, boolean isValid, String expMessage, String priName,String primaryHolder){
		selectPrimaryPrivWidget.searchPrivilegeNum(privilegeNum);
		boolean isSearchButtonExists = selectPrimaryPrivWidget.isSearchButtonExists();
		boolean isChangeButtonExists = selectPrimaryPrivWidget.isChangeButtonExists();
		boolean isOkButtonExists = selectPrimaryPrivWidget.isOkButtonExists();
		boolean isSearchButtonExists_exp,isChangeButtonExists_exp,isOkButtonExists_exp;
		String licenseInformation_act = selectPrimaryPrivWidget.getPrivilegeName();
		String primaryHolder_act = selectPrimaryPrivWidget.getPrimaryPrivilegeHolder();
		boolean pass = true;
		
		if(!isValid){
			String message = selectPrimaryPrivWidget.getErrorMsg();
			isSearchButtonExists_exp = true;
			isChangeButtonExists_exp = false;
			isOkButtonExists_exp = false;
			
			pass &= MiscFunctions.compareResult("Message", expMessage, message);
		}else{
			isSearchButtonExists_exp = false;
			isChangeButtonExists_exp = true;
			isOkButtonExists_exp = true;
		}
		
		pass &= MiscFunctions.compareResult("Message", isSearchButtonExists_exp, isSearchButtonExists);
		pass &= MiscFunctions.compareResult("Message", isChangeButtonExists_exp, isChangeButtonExists);
		pass &= MiscFunctions.compareResult("Message", isOkButtonExists_exp, isOkButtonExists);
		pass &= MiscFunctions.compareResult("License information", priName, licenseInformation_act);
		pass &= MiscFunctions.compareResult("Primary Holder", primaryHolder, primaryHolder_act);
		
		if(!pass){
			throw new ErrorOnPageException("Message and UI display info not correct please check.");
		}else logger.info("Message and UI display info is correct.");
		
	}
	
	private void gotoAddItemPageFromSelectPrimaryPrivilegePage(){
		logger.info("Go to add item page from select primary privilege page.");
		selectPrimaryPrivWidget.clickCancel();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}

}
