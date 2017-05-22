package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReprintPrivilegeWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description: Verify the maximum reprint count of a privilege order
 * @Preconditions:
 * 							Steps: 1. make a duplicated privilege order with printing document;
 * 									  2. go to order detail page to verify the reprint count is less than or equal to the 
 * 											[Maximum Reprint Count in its associated Document Template PLUS 1]
 * 
 * @SPEC: <<Get Privilege List for Reprint.doc>>
 * @Task#: AUTO-881
 *
 * @author qchen
 * @Date  Apr 17, 2012
 */
public class GetListForReprint_VerifyMaxCount extends LicenseManagerTestCase {
	private static String documentTemplate = "DocTempl_GetList4Reprint";
	private String warnMsg = "There is no Document to print.";

	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		//1. duplicate a privilege order with printing document
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		orderNum = lm.processOrderCart(pay, false);//this time will not print, if print, the reprint count will be reduce 1

		//2. verify the 'Reprint' count is less than or equal to the [Maximum Reprint Count in its associated Document Template PLUS 1]
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		//get maximum reprint count of document template
		int maxReprintCountOfDocTem = lm.getMaxReprintCountOfDocTemplate(schema, documentTemplate);
		this.verifyMaxReprintCount(maxReprintCountOfDocTem + 1);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		privilege.code = "GLR";
		privilege.name = "GetListForReprint";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.qty = "1";
		privilege.operateReason = "";
		privilege.operateNote = "Automation regression test";

		cust.customerClass = "Individual";
		cust.fName = "QA-Basic12";
		cust.lName = "TEST-Basic12";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000012";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
	
	private void verifyMaxReprintCount(int expectedMaxCount) {
		LicMgrPrivilegeOrderDetailsPage orderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		LicMgrReprintPrivilegeWidget reprintWidget = LicMgrReprintPrivilegeWidget.getInstance();
		
		logger.info("Verify the privilege maximum reprint count.");
		for(int i = 0; i < expectedMaxCount; i ++) {
			orderDetailsPage.clickReprint();
			ajax.waitLoading();
			reprintWidget.waitLoading();
			reprintWidget.processReprint();
			ajax.waitLoading();
			Browser.sleep(3);
			Object page = browser.waitExists(reprintWidget, orderDetailsPage);
			
			if(page == reprintWidget) {
				String msg = reprintWidget.getWarnMessage();
					if(msg.equals(warnMsg)) {
						throw new ErrorOnPageException("This order expected can be reprinted " + expectedMaxCount + " times, but this time is only " + (i + 1) + " time and failed.");
					} else logger.info("This order is really only can be reprinted " + expectedMaxCount + " times.");
			}
		}
		
		orderDetailsPage.clickReprint();
		ajax.waitLoading();
		reprintWidget.waitLoading();
		reprintWidget.selectReprintReason("");
		reprintWidget.setNotes("");
		reprintWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(reprintWidget, orderDetailsPage);
		
		if(page == reprintWidget) {
			String msg = reprintWidget.getWarnMessage();
			if(!msg.equals(warnMsg)) {
				throw new ErrorOnPageException("This order only can be reprinted " + expectedMaxCount + " times, but this time is " + (expectedMaxCount + 1) + " time.");
			} else logger.info("This order is really only can be reprinted " + expectedMaxCount + " times.");
		}
		
		reprintWidget.clickClose();
		ajax.waitLoading();
		orderDetailsPage.waitLoading();
	}
}
