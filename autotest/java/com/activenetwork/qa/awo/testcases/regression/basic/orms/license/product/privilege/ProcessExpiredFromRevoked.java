package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ProcessExpiredPrivilegeTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify processing REVOKED privilege instance to expired
 * @Preconditions: 1. For each Privilege Instance with an "Active", "Invalid", or "Revoked" Status and with a non-null/blank 
 * 								Valid To Date&Time that is less than the Current Date&Time, the System sets the privileges Status of that Privilege Instance to "Expired";
 * 							2. So need make 1 privilege instance with 'Revoked' status as precondition;
 * 								Add a customer suspension record to make this privilege as 'Revoked';
 * 								Finally, after the daemon running time in next day, the above privilege instance' status shall been set as "Expired"
 * 							3. In order to make privilege order, we need an existing privilege product with pricing, store assignment, license year and quantity control, etc.
 * @SPEC: <<Process Expired Privileges.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Mar 2, 2012
 */
public class ProcessExpiredFromRevoked extends ProcessExpiredPrivilegeTestCase {
	
	@Override
	public void execute() {
		sm.loginSystemManager(loginSm);
		daemonRunTime = sm.getDaemonRunningTime(daemonName);
		sm.logoutSystemManager();
		
		lm.loginLicenseManager(login);
		//1. get the previous privilege number which is stored in qa_automation table
		String values[] = lm.readQADB(this.caseName).split(",");
		
		boolean isCheck = values.length > 0 && lm.checkOrderExists(schema, values[0]) && lm.checkPrivilegeNumberExists(schema, values[1]);
		if(isCheck) {// there is existing order for check
			this.verifyExistingPrivilege(values[0], values[1]);
		}
		
		//create a new privilege instance and insert the order number and privilege number into db
		//1. make 1 privilege instance into cart
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//2. go to privilege detail page to get privilege number
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//3. goto customer detail page to add a suspension record to make privilege status as 'Revoked'
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension);

		//4. update valid from and valid to values as current date time
		String currentTime = DateFunctions.getToday("yyyy/MM/dd h:m aa", timeZone_MS);
		lm.updatePrivilegeInstanceValidFromAndTo(schema, privilegeNum, currentTime, currentTime);
		
		//5. insert order number and privilege number into db for next running
		lm.writeQADB(this.caseName, orderNum + "," + privilegeNum);
		
		//6. calculate estimate expired date time
		estimateExpireDateTime = this.calculateEstimateExpiredDateTime(currentTime);
		lm.logOutLicenseManager();
		
		if(!isCheck){// just prepare orders, not verify, so throw exception. 
			throw new TestCaseFailedException("A new privilege(#=" + privilegeNum + ") has been purchased for testing expiry. Please run this case after " + estimateExpireDateTime + ".");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		//log info
		loginSm.url = AwoUtil.getOrmsURL(env) + "/SystemMgrLogin.do";
		loginSm.userName = TestProperty.getProperty("orms.adm.user");
		loginSm.password = TestProperty.getProperty("orms.adm.pw");
		loginSm.contract = "MS Contract";
		loginSm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		login.contract = loginSm.contract;
		login.location = "HF HQ Role/WAL-MART";
		
		daemonName = "com.reserveamerica.licensing.order.impl.daemon.ExpirePrivilegeDaemon";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone_MS = DataBaseFunctions.getContractTimeZone(schema);
		
		privilege.code = "PEP";
		privilege.name = "ProcessExpiredPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation test";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Dont";
		cust.lName = "TEST-Touchme";
		cust.dateOfBirth = "20120222";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "63667792";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = "Bad Check Suspension";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1);
		suspension.endDate = DateFunctions.getDateAfterToday(1);
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked - " + DateFunctions.getCurrentTime();
	}
}
