package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ProcessExpiredPrivilegeTestCase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify processing ACTIVE privilege instance to expired
 * 
 * @Preconditions: 1. For each Privilege Instance with an "Active", "Invalid", or "Revoked" Status and with a non-null/blank 
 * 								Valid To Date&Time that is less than the Current Date&Time, the System sets the privileges Status of that Privilege Instance to "Expired";
 * 							2. So need make 1 privilege instance with 'Active' status as precondition;
 * 								Finally, after the daemon running time in next day, the above privilege instance' status shall been set as "Expired"
 * 							3. In order to make privilege order, we need an existing privilege product with pricing, store assignment, license year and quantity control, etc.
 * 
 * @SPEC: <<Process Expired Privileges.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Feb 28, 2012
 */
public class ProcessExpiredFromActive extends ProcessExpiredPrivilegeTestCase {
	
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
		if(orderNum.contains(" ")){
			orderNum = orderNum.split(" ")[0];
		}
		//2. go to privilege detail page to get privilege number
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//3. update valid from and valid to values as current date time
		String currentTime = DateFunctions.getToday("yyyy/MM/dd h:m aa", timeZone_MS);
		lm.updatePrivilegeInstanceValidFromAndTo(schema, privilegeNum, currentTime, currentTime);
		
		//4. insert order number and privilege number into db for next running
		lm.writeQADB(this.caseName, orderNum + "," + privilegeNum);
		
		//5. calculate estimate expired date time
		estimateExpireDateTime = this.calculateEstimateExpiredDateTime(currentTime);
		lm.logOutLicenseManager();
		
		if(!isCheck){// just prepare orders, not verify, so throw exception. 
			throw new TestCaseFailedException("A new privilege(#=" + privilegeNum + ") has been purchased for testing expiry. Please run this case after " + estimateExpireDateTime + ".");
		}
	}
}
