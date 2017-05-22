/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.reactivate;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verifying that when you purchase Privilege A which resulted in the invalidation of the specified parameter privilege instance, 
 * when you then void/reverse that purchase of Privilege A, the parameter privilege instance that was invalidated should be automatically reactivated by the system.
 * 
 * this case need two privilege, which could refer to: testcases.regression.basic.orms.license.product.privilege.transfer.businessrule.InvalidOnFileParamPrivilege
 * privilege code = 972, which have business rule
 * parameter privilege code = 941
 * 
 * 
 * 1.purchase parameter privilege
 * 2.purchase privilege
 * 3.verify parameter privilege order status should be invalid
 * 4.void privilege order
 * 5.verify parameter privilege order status is active
 * @LinkSetUp:d_hf_add_cust_profile:id=2930
 * @SPEC:Reactivate Privilege - "INVALID when selected privilege purchased" [TC:005001]
 * @Task#:Auto-2134
 * 
 * @author Vivian
 * @Date  Apr 9, 2014
 */
public class Reactivate_InvalidOnFileParamPrivilegeRule extends LicenseManagerTestCase{
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private PrivilegeInfo parameterPriv = new PrivilegeInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//clear up date
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.gotoHomePage();
		
		//make parameter privilege reservation
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, parameterPriv);
	    String paramOrdNum = lm.processOrderCart(pay).split(" ")[0];
	    
		//make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		
		//verify parameter privilege number is invalid
		lm.gotoPrivilegeOrderDetailPage(paramOrdNum);
		String paramPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(paramPrivNum);
		lm.verifyPrivilegeStatusFromUI(OrmsConstants.INVALID_STATUS);
		
		//void privilege order
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		//verify parameter privilege number is active
		lm.gotoPrivilegeOrderDetailPage(paramOrdNum);
		lm.gotoPrivilegeDetailFromOrderPg(paramPrivNum);
		lm.verifyPrivilegeStatusFromUI(OrmsConstants.ACTIVE_STATUS);
		
		//void parameter privilege order
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(paramOrdNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "1985/08/15";
		cust.fName = "QA-ReactivatePriOrd01";
		cust.lName = "Test-ReactivatePriOrd01";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "19850815";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";// Don't change this data!

		privilege.code = "972";
		privilege.name = "InvalidParamPrivilege";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		parameterPriv.code = "941";
		parameterPriv.name = "ParamPrivilege11";
		parameterPriv.purchasingName = parameterPriv.code+"-"+parameterPriv.name;
		parameterPriv.licenseYear = fiscalYear;
		parameterPriv.qty = "1";
		parameterPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
	}

}
