package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale.noteAlert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAlertDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Note to privilege order.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * @SPEC:TC:016696
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 16, 2012
 */
public class AddNoteToOrder extends LicenseManagerTestCase {
	private NoteAndAlertInfo noteInfo = new NoteAndAlertInfo();
	private LicMgrNoteAndAlertListPage noteAlertPage = LicMgrNoteAndAlertListPage.getInstance();
	private LicMgrNoteAlertDetailsWidget noteDetailPage = LicMgrNoteAlertDetailsWidget.getInstance();
	
	@Override
	public void execute() {
        lm.loginLicenseManager(login);
        
        // purchase privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);

		// go to note and alert page.
		noteInfo.id = lm.addNoteOrAlertForOrder(noteInfo);
		noteAlertPage.verifyNoteAndAlertList(noteInfo);
		
		// go to note detail page and verify details info
		lm.gotoNoteAndAlertDetailsPage(noteInfo.id);
		noteDetailPage.verifyAlertDetail(noteInfo);
		noteDetailPage.clickOK();
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
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic13";
		cust.fName = "QA-Basic13";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		noteInfo.type = "Note";// Don't change this data.
		noteInfo.text = "Add new note to privilege order.";
		noteInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		noteInfo.startDate = DateFunctions.getToday(timeZone);
		noteInfo.endDate = StringUtil.EMPTY;
		
		privilege.code = "PP1";
		privilege.name = "PriParticular1";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";
	}
}
