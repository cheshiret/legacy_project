/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:  Invalidate privilege
 * @Task#: Auto-869
 * @author jwang8
 * @Date  Feb 15, 2012
 */
public class InvalidatePrivilege extends LicenseManagerTestCase{
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum = lm.processOrderCart(pay);
		if(ordNum.contains(" ")){
			ordNum = ordNum.split(" ")[0];
		}
		//goto privilege order detail page
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		String privNum = privOrderDetailsPage.getAllPrivilegesNum();

		//goto privilege detail page.
		lm.gotoPrivilegeDetailFromOrderPg(privNum);
		//invalidate privilege
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateReason);

		//verify the privilege invalidated correct from UI
		lm.verifyPrivilegeStatusFromUI(INVALID_STATUS);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		//updated role to match new roles AP and CW -- 11/29/11
		//login.location = "LM - Facility/WAL-MART";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		privilege.purchasingName = "adv-LocationDailySales";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";

		cust.identifier.identifierType = "Green Card";//"Green Card";
		cust.identifier.identifierNum = "123456780";//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.lName = "TEST-InvalidOrder";
		cust.fName ="QA-InvalidOrder";
		cust.residencyStatus="Non Resident";
	}
}
