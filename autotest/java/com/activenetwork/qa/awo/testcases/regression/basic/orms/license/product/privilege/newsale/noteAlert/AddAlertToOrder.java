package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale.noteAlert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAlertDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Alert to privilege order.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * @SPEC:TC:016696
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 16, 2012
 */
public class AddAlertToOrder extends LicenseManagerTestCase {
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	private LicMgrNoteAndAlertListPage noteAlertPage = LicMgrNoteAndAlertListPage.getInstance();
	private LicMgrNoteAlertDetailsWidget alertDetailPage = LicMgrNoteAlertDetailsWidget.getInstance();
	
	@Override
	public void execute() {
        lm.loginLicenseManager(login);
        
        // purchase privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);

		// go to note and alert page.
		alertInfo.id = lm.addNoteOrAlertForOrder(alertInfo);
		noteAlertPage.verifyNoteAndAlertList(alertInfo);
		
		// go to alert detail page and verify details info
		lm.gotoNoteAndAlertDetailsPage(alertInfo.id);
		alertDetailPage.verifyAlertDetail(alertInfo);
		alertDetailPage.clickOK();
		ajax.waitLoading();
		noteAlertPage.waitLoading();

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
		cust.lName = "TEST-TransferRule55";
		cust.fName = "QA-TransferRule55";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		alertInfo.type = "Alert";// Don't change this data.
		alertInfo.startDate = DateFunctions.getDateAfterToday(-2, timeZone);
		alertInfo.endDate = DateFunctions.getDateAfterToday(3, timeZone);
		alertInfo.text = "Add new alert to privilege order.";
		alertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		privilege.code = "PP1";
		privilege.name = "PriParticular1";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";
	}
}
