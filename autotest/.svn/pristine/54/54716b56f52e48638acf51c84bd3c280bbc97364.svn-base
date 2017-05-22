package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
/**
 * 
 * @Description: (P) Check page title, identifier types and DOB reminder message in account lookup page
 * @Preconditions:
 * @SPEC: Profile Identification Page UI [TC:044126] 
 * @Task#: AUTO-1625
 * 
 * @author SWang
 * @Date  Apr 10, 2013
 */
public class AccountLookupPgUI extends HFSKWebTestCase {
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	private String pageTitle, reminderInfoForDOB, idenTypesFromDB, idenTypesFromUI;
	private boolean passed = true;

	public void execute() {
		//Go to lookup account page
		hf.invokeURL(url);
		hf.gotoLookupAccountPage();
		
		//Get identifier type from DB and UI
		idenTypesFromDB = hf.getAllIdenTypesFromDB(schema).toString();
		idenTypesFromUI = lookupAccountPg.getAllIdenTypes().toString().replaceFirst(" #", "");

		//Check page title, identifier types and date of birth reminder message
		passed &= MiscFunctions.matchString("Lookup page title", lookupAccountPg.getPgTitle(), pageTitle);
		passed &= MiscFunctions.compareResult("Identifier types", idenTypesFromDB, idenTypesFromUI);
		passed &= MiscFunctions.compareResult("Date of birth reminder message", lookupAccountPg.getDOBReminderMes(), reminderInfoForDOB);

		if(!passed){
			throw new TestCaseFailedException("Test case is failed. Please check detals info from previous logs.");
		}
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);
		pageTitle = ".*Create New (HAL )?Account.*";
		reminderInfoForDOB = "For verification purposes, please enter your Date of Birth (YYYY-MM-DD)";
	}
}
