package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This case is used to verify edit a privilege product for big game
 * 1.Hunts Required is No.
 * 2.Purchase this privilege.
 * 3.Try to update the Hunts Required to Yes, error message will be displayed.
 * Verify this error message.
 * @Preconditions:available privilege:TDP-TestPrivilege
 * Hunts Required  of privilege is No.
 * Privilege must have Valid from and to date.
 * @SPEC: TC046727
 * @Task#: AUTO-1235
 * 
 * @author szhou
 * @Date  Sep 24, 2012
 */
public class EditPrivilegeForBigGame_ActivePrivilege extends
		LicenseManagerTestCase {
	private String error;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// purchase a privilege as precondition
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.processOrderCart(pay);

		// edit privilege info
		login.location = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		lm.switchLocationInHomePage(login.location);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);

		// change status and verify error
		this.changeStatus(error);
		
		lm.logOutLicenseManager();
	}
	
	private void changeStatus(String message){
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		
		privilegeDetailsPg.selectHuntsRequired(YES_STATUS);
		ajax.waitLoading();
		privilegeDetailsPg.clickApply();
		ajax.waitLoading();
		privilegeDetailsPg.waitLoading();
		
		String error=privilegeDetailsPg.getMessage();
		if(!message.equals(error)){
			throw new ErrorOnPageException("Error Message is not correct. Expect message is "+message+";But Actually is "+error);
		}

		privilegeDetailsPg.clickCancel();
		privilegePage.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.name = "TetsPrivilege";
		privilege.purchasingName = "TDP-TestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.code="TDP";
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111111";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		error="The indicator Available via Application Only is \"Yes\" and there is at least one Active Privilege Instance with Effective To Date & Time greater than or equal to the current date & time. Please change your entry.";
	}

}
