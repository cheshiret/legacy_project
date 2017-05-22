/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.reactivate;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case used to verify reactivate privilege order which instance is transfer to
 * @LinkSetUp:
 * @SPEC:Reactivate Privilege - UI [TC:004617]
 * @Task#:Auto-2134
 * 
 * @author Vivian
 * @Date  Apr 9, 2014
 */
public class Reactivate_TransferPurchase extends LicenseManagerTestCase{
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String privilegeOrdNum = lm.processOrderCart(pay);
		
		//transfer privilege order 
		lm.gotoPrivilegeOrderDetailPage(privilegeOrdNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		String transferredOrderNum = lm.processOrderCart(pay);
		
		lm.gotoPrivilegeOrderDetailPage(transferredOrderNum);
		//invalidate transfer to privilege order
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		//reactivate privilege order and verify related privileges status changed to active
		lm.reactivatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		//verify related privileges status changed to active
		lm.verifyAllPrivilegesStatus(transferredOrderNum, PRIV_STATUS_ACTIVE, schema);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		String location = "WAL-MART";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/" + location;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//privilege info
		privilege.code = "TDP";
		privilege.name = "TestPrivilege";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		
		//customer info
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule2";
		cust.lName = "TEST-TransferRule2";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		//transfer to customer info
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferQues";
		toCust.lName = "TEST-TransferQues";
		toCust.dateOfBirth = "Jan 2 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";
	}

}
