package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.notealert;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAddNoteAlertPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerNoteAndAlertPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to check if it'll adjust the invalid  content during adding a alert and a note  
 * The work flow was add a new alert for a customer with invalid content ,check the format;
 * add a new note for a customer with invalid content ,check the format "CreateCustomerProfileNoteAlert"
 * @Task#:Auto-1153
 * @author Phoebe Chen
 * @Date  Jul 20, 2012
 */
public class AddAlert extends LicenseManagerTestCase {
	private LicMgrCustomerNoteAndAlertPage noteAndAlert = LicMgrCustomerNoteAndAlertPage
			.getInstance();
	private LicMgrCustomerAddNoteAlertPage addNoteAlert = LicMgrCustomerAddNoteAlertPage.getInstance();
	private String correctAlertInfo = "";
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	@Override
	public void execute() {
        lm.loginLicenseManager(login);
		
		//go to customer education sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);		
		lm.gotoNotesAndAlertsSubTabFromCustomerDetailsPg();
		
		//clean all the note and alert
		noteAndAlert.deactiveAllNoteAlert();
		
//		lm.AddNoteOrAlertForCustomer("Alert", invalidAlertInfo, alertStart, alertEnd);
		lm.addNoteOrAlertForCustomer(alertInfo);
		this.verifyAlertAddedCorrect();
				   
		//Clear data for next round
		lm.logOutLicenseManager();			
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-AddNoteAlert";
		cust.lName = "TEST-AddNoteAlert";
		
		alertInfo.type = "Alert";
		alertInfo.text = "<a>www.google.com";
		alertInfo.startDate = DateFunctions.getDateAfterToday(-1);
		alertInfo.endDate = DateFunctions.getDateAfterToday(8);
		
//		alertStart = DateFunctions.getDateAfterToday(-1);
//		alertEnd = DateFunctions.getDateAfterToday(8);
//		invalidAlertInfo = "<a>www.google.com";
		correctAlertInfo = "<a>www.google.com</a>"; 
	}
	
	private void verifyAlertAddedCorrect(){
		noteAndAlert.clickFirstNoteAlertInList();
		addNoteAlert.waitLoading();
		String content = addNoteAlert.getNoteAlertText();
		boolean pass = true;		
		pass &= MiscFunctions.compareResult("Alert text has been created---", correctAlertInfo, content);
		if(!pass){
			throw new ErrorOnDataException("Alert is not correct to right format,please check log....");
		}else {
			logger.info("bad tags, and attributes has been removed correctly.");
		}
		addNoteAlert.clickOK();
		ajax.waitLoading();
		noteAndAlert.waitLoading();
	}

}
