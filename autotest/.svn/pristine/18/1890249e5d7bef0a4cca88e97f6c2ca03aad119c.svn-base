package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.recipient;

import com.activenetwork.qa.awo.pages.orms.resourceManager.recipient.ResMgrRecipientsMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author Ssong
 */
public class SearchRecipient extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>SearchRecipient</b>
	 * Generated     : <b>Jan 28, 2010 5:17:27 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/01/28
	 * @author Ssong
	 */
  private ResMgrRecipientsMainPage mainPg = ResMgrRecipientsMainPage.getInstance();
  
  public void execute() {
	rm.loginResourceManager(login);
	
	//goto recipient page and add a new recipient
	rm.gotoReportRecipientPage();
	rm.addNewReportRecipient(rd);
	
	//search and get recipient ID
	mainPg.setupSearchCriteria(rd);
	String recipientID = mainPg.getFirstRecipientId();
	
	//search by facility name
	mainPg.searchByFacilityName(rd.park);
	mainPg.verifyRecipientInSearchList(recipientID);
	mainPg.verifyRecipientInfo("Facility Name",rd.park);
	
	//search by report group
	mainPg.searchByGroup(rd.group);
	mainPg.verifyRecipientInSearchList(recipientID);   
	mainPg.verifyRecipientInfo("Report Group",rd.group);
	
	//search by report name
	mainPg.searchByReportName(rd.reportName);
	mainPg.verifyRecipientInSearchList(recipientID);
	mainPg.verifyRecipientInfo("Report",rd.reportName);
	
	this.cleanUp(recipientID);

	rm.logoutResourceManager();
  }

  private void cleanUp(String recipientID)
  {
    mainPg.selectCheckBox(recipientID);
	mainPg.clickDelete();
	mainPg.waitLoading();
	if(mainPg.checkRecipientExists(recipientID)){
	  throw new ErrorOnPageException("Clean up,delete Recipient "+recipientID+" Fail.");
	}
  }
  
  public void wrapParameters(Object[] param) {
	login.url = AwoUtil.getOrmsURL(env);
	login.contract = "NRRS Contract";
	login.location = "Administrator/NRRS";

	rd.group = "Financial Reports";
	rd.reportName = "Central Deposit Report";
	rd.park = "RYAN PARK";
	rd.recipient_name = TestProperty.getProperty("notification.from");
  }
}

