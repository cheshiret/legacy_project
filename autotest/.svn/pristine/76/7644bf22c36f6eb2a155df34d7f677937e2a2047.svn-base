package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.recipient;

import com.activenetwork.qa.awo.pages.orms.resourceManager.recipient.ResMgrRecipientListDetailsPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.recipient.ResMgrRecipientsMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author Ssong
 */
public class AddNewRecipient extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>AddNewRecipient</b>
	 * Generated     : <b>Jan 29, 2010 2:04:36 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/01/29
	 * @author Ssong
	 */
  private ResMgrRecipientsMainPage mainPg = ResMgrRecipientsMainPage.getInstance();
  private ResMgrRecipientListDetailsPage detailPg = ResMgrRecipientListDetailsPage.getInstance();
  
  public void execute() {
	rm.loginResourceManager(login);
	
	//goto recipient page and add a new recipient
	rm.gotoReportRecipientPage();
	rm.addNewReportRecipient(rd);
	
	//search and get recipient ID
	mainPg.setupSearchCriteria(rd);
	rd.recipientListId = mainPg.getFirstRecipientId();
	logger.info("Recipient id:"+rd.recipientListId);
	//goto recipient detail page and verify detail info
	mainPg.clickFirstRecipient();
	detailPg.waitLoading();
	detailPg.verifyDetailInfo(rd);
	detailPg.clickOk();
	mainPg.waitLoading();
	
	this.cleanUp(rd.recipientListId);
	
	rm.logoutResourceManager();

  }

  private void cleanUp(String recipientID)
  {
    mainPg.waitLoading();
    mainPg.selectCheckBox(recipientID);
	mainPg.clickDelete();
	mainPg.waitExists(1);
	if(mainPg.checkRecipientExists(recipientID)){
	  throw new ErrorOnPageException("Clean up,delete Recipient "+recipientID+" Fail.");
	}
  }
  
  public void wrapParameters(Object[] param) {
	login.url = AwoUtil.getOrmsURL(env);
	login.contract = "NRRS Contract";
	login.location = "Administrator/NRRS";

	//initialize report data
	rd.group = "Operational Reports";
	rd.reportName = "Will Call Report";
	rd.park = "RYAN PARK";
	rd.recipient_name = TestProperty.getProperty("notification.from");
//	rd.recipientFromList = "Test-Auto, QA-Auto (qa-auto-adm)";
//	rd.recipientFromList = "Administrator, System (admin)";//bad performance to select recipient from drop down list for selenium, so comment it.
	rd.faxTo = "qa";
	rd.faxFrom = "test";
	rd.faxNum = "800-123-1234";
  }
}
