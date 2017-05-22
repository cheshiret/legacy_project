package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale.noteAlert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Search note in note&alert sub page of privilege order detail page.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * @SPEC:TC:016670
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 18, 2012
 */
public class SearchNote extends LicenseManagerTestCase{
	private NoteAndAlertInfo noteAndAlertInfo = new NoteAndAlertInfo();
	private NoteAndAlertInfo searchInfo = new NoteAndAlertInfo();
	private TimeZone timeZone;
	private LicMgrNoteAndAlertListPage noteAlertPage = LicMgrNoteAndAlertListPage.getInstance();
	
	@Override
	public void execute() {
	    lm.loginLicenseManager(login);
	    
	    // purchase privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
	
		// go to note and alert page.
		noteAndAlertInfo.id = lm.addNoteOrAlertForOrder(noteAndAlertInfo);

		// search by Show Current Records Only
		searchInfo.isShowCurrentRecordsOnly = true;
		noteAlertPage.searchNoteAndAlert(searchInfo);
		noteAlertPage.verifyNoteAndAlertAfterSearch("Show Current Records Only", null, DateFunctions.getToday(timeZone));
		
		// search by status and type
		searchInfo.isShowCurrentRecordsOnly = false;
		searchInfo.type = noteAndAlertInfo.type;
		searchInfo.status = OrmsConstants.ACTIVE_STATUS;
		noteAlertPage.searchNoteAndAlert(searchInfo);
		noteAlertPage.verifyNoteAndAlertAfterSearch("Status", searchInfo.status, null);
		noteAlertPage.verifyNoteAndAlertAfterSearch("Type", searchInfo.type, null);

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
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Aug 12 1986";
		cust.lName = "TEST-TransferRule99";
		cust.fName = "QA-TransferRule99";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		noteAndAlertInfo.type = "Note";// Don't change this data.
		noteAndAlertInfo.text = "Add new note to privilege order for search.";
		noteAndAlertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		privilege.code = "PP1";
		privilege.name = "PriParticular1";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";
	}
}
