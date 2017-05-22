
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.renewal;
/**
 * @Description:cancel the customer profile confirm in renew privilege process.
 * @Preconditions:  1."ModifyCustomerProfile" feature need to assign.
 * @SPEC:TC:036470
 * @Task#::Auto-1106
 * 
 * @author jwang8
 * @Date  2012-07-03
 */

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class CancelRenewPriCusomerConfirm extends LicenseManagerTestCase{

	private LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
	public void execute() {
		lm.loginLicenseManager(login);
		//precondition: to invalidate all active privileges of current customer
		lm.invalidateAllPrivilegesPerCustomer(cust, privilege.operateReason, privilege.operateNote);
		
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum1 =lm.processOrderCart(pay);
		
		lm.gotoQuickRenewPrivilegeCustomerProfilePage(cust.identifier.identifierNum);
		confirmPg.verifyCancelButtonEnable();
		this.VerifyCancelRenewPrivilegeFromCustConfirmPg();
		
		lm.gotoHomePage();
		lm.gotoOrderPageFromOrdersTopMenu(orderNum1);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.fName = "QA-Renewal";
		cust.lName = "TEST-Renewal";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 01 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);//"814618203";
		cust.residencyStatus = "Non Resident";
		
		privilege.purchasingName = "rew-quickRenewal";
		privilege.licenseYear = lm.getFiscalYear(schema);	
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate =  DateFunctions.getDateAfterToday(365);//The privilege vaild date was set 356 day.
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "QA Automation";
	}
	/**
	 * cancel renew privilege from customer confirm page.
	 */
	private void VerifyCancelRenewPrivilegeFromCustConfirmPg(){
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		logger.info("Cancle renew privilege");
		
		confirmPg.clickCancel();
		ajax.waitLoading();
		homePg.waitLoading();
		if(!homePg.exists()){
			throw new ErrorOnPageException("Home page should display after cancel customer profile");
		}
	}
	

}
