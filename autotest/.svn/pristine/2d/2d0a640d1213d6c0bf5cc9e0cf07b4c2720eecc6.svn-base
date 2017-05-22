package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Purchase privilege and then transfer it to another customer.
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions, and the corresponding Answers and Next Actions.)
 * @SPEC:Transfer Privilege.doc&&Process Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 6, 2012
 */
public class TransferPrivilege extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		// 1.make an privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum1 = privOrderDetailsPage.getAllPrivilegesNum();
		
		// 2.transfer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum1);
		lm.transferPrivToOrderCart(toCust, privilege);
		String transferredOrderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(transferredOrderNum);
		String allPrivNum2 = privOrderDetailsPage.getAllPrivilegesNum();
		
		// verify Purchase Type for transfer to order.
		String purchaseType = privOrderDetailsPage.getPrivilegeOrderItemPurchaseType(allPrivNum2);
		this.verify(purchaseType, "Transfer");

		// verify transfer item status of transfer to item.
		String exceptedStatus = OrmsConstants.ACTIVE_STATUS;
		String status = this.getPrivStatus(transferredOrderNum, allPrivNum2);
		this.verify(status, exceptedStatus);

		// verify transfer item status of transfer from item.
		exceptedStatus = "Transferred";
		status = this.getPrivStatus(orderNum, allPrivNum1);
		this.verify(status, exceptedStatus);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		login.station = "Station 1 AM";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule6";
		cust.lName = "TEST-TransferRule6";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		
		privilege.code = "999";
		privilege.name = "TransferPrivilege";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferQues";
		toCust.lName = "TEST-TransferQues";
		toCust.dateOfBirth = "Jan 2 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";
	}
	
	/**
	 * Get status of privilege.
	 * 
	 * @param orderNum
	 * @param privNum
	 * @return
	 */
	private String getPrivStatus(String orderNum, String privNum){
		logger.info("Get status of privilge.");
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privNum);
		LicMgrPrivilegeItemDetailPage itemDetailPg = LicMgrPrivilegeItemDetailPage.getInstance();
		return itemDetailPg.getStatus();
	}
	
	/**
	 * Verify whether the content is the same as expect.
	 * 
	 * @param actual
	 * @param expect
	 */
	private void verify(String actual, String expect){
		logger.info("Verify whether the content is the same as expect.");
		if(!expect.equals(actual)){
			throw new ErrorOnPageException("Actual one is:"+actual+"is not the same as expect:("+expect+")!");
		}
	}
}
