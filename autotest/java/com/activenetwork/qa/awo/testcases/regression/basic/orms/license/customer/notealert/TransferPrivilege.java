package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.notealert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerNoteAndAlertPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Transfer a privilege to a customer who has alert. When identify customer, the alert should be popped up.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * 2.Transfer to customer has an active alert, and current date is between alert start date and to date.
 * 3.Transfer from customer should better has an alert.
 * @SPEC:TC:020017
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 16, 2012
 */
public class TransferPrivilege extends LicenseManagerTestCase {
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private LicMgrCustomerNoteAndAlertPage addAlertPage = LicMgrCustomerNoteAndAlertPage.getInstance();
	private Customer toCust = new Customer();
	
	@Override
	public void execute() {
		// add alert for customer.
        lm.loginLicenseManager(login);
		
		//go to customer education sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(toCust);		
		lm.gotoNotesAndAlertsSubTabFromCustomerDetailsPg();
		
		//clean all the note and alert
		addAlertPage.deactiveAllNoteAlert();
		alertInfo.id = lm.addNoteOrAlertForCustomer(alertInfo);

		// purchase privilege
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum1 = privOrderDetailsPage.getAllPrivilegesNum();

		// transfer privilege, go to identify customer page.
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum1);
		this.trasferPri();
		String alert = lm.selectCustForPurchasePri(toCust);
		
		// verify alert info.
		this.verifyAlertInfo(alert);
		
		// clean up
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		login.userName = TestProperty.getProperty("orms.fm.user");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Aug 12 1986";
		cust.lName = "TEST-TransferRule119";
		cust.fName = "QA-TransferRule119";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		toCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.lName = "TEST-TransferRule20";
		toCust.fName = "QA-TransferRule20";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";

		alertInfo.type = "Alert";
		alertInfo.startDate = DateFunctions.getDateAfterToday(-2, timeZone);
		alertInfo.endDate = DateFunctions.getDateAfterToday(3, timeZone);
		alertInfo.text = "Are you happy?";
		alertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		privilege.code = "PP1";
		privilege.name = "PriParticular1";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";
	}
	
	private void trasferPri(){
		LicMgrPrivilegeItemDetailPage itemPg = LicMgrPrivilegeItemDetailPage.getInstance();
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage.getInstance();
		
		logger.info("Transfer privilege.");
		itemPg.clickTransfer();
		ajax.waitLoading();
		identifyCustPg.waitLoading();
	}
	
	private void verifyAlertInfo(String alert){
		boolean result = true;
		String[] actualAlert = alert.split(":")[1].split("\\|");
		result &= MiscFunctions.compareResult("Effective Date", DateFunctions.formatDate(alertInfo.startDate, "MM/dd/yyyy")+" - "+DateFunctions.formatDate(alertInfo.endDate, "MM/dd/yyyy"), actualAlert[0].trim());
		result &= MiscFunctions.compareResult("Create User", alertInfo.createUser, actualAlert[1].trim());
		result &= MiscFunctions.compareResult("Text", alertInfo.text, actualAlert[2].trim());

		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
}
