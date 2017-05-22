/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.accountoverview;

import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateEmailAddressPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Personal Information Section in Account Overview Page
 * @Preconditions:
 * @SPEC: 
 * Account Overview page - Personal Information section UI [TC:048783]
 * Account Overview page displays [TC:048782]
 * @Task#: Auto-1639
 * 
 * @author Lesley Wang
 * @Date  Apr 22, 2013
 */
public class VerifyPersonalInforamtionSection extends HFSKWebTestCase {
	private HFAccountOverviewPage acctPg = HFAccountOverviewPage.getInstance();
	private HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
	private String pageTitle, notice;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);	
		acctPg.verifyPageTitleAndCaption(pageTitle);
		
		// Go to Update Account page to get the account info
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		cus.identifier.identifierNum = updAcctPg.getIdenNum(cus.identifier.identifierType);
		cus.fName = updAcctPg.getFirstName();
		cus.lName = updAcctPg.getLastName();
		cus.hPhone = updAcctPg.getHomePhone();
		
		// Verify Personal Info section
		hf.gotoMyAccountPgFromHeaderBar();
		this.verifyPersonalInformation(cus.identifier.identifierNum, cus.fName, cus.lName, cus.email, cus.hPhone, notice);
		this.verifyPersonalInfoLinks();
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		cus.email = "validateaddress@test.com";
		cus.password = "asdfasdf";
		cus.identifier.identifierType = IDENT_TYPE_HAL;
		
		pageTitle = "Account Overview\\s*Summary of your personal account information, recent transactions and licences.";
		notice = "Is this information correct? Update";
	}

	/** Verify Personal Information details */
	private void verifyPersonalInformation(String halID, String fName, String lName, String email, String phone, String notice) {
		boolean result = true;
		result &= MiscFunctions.compareString("HAL ID in personal info section", halID, acctPg.getPersonalHALID());
		result &= MiscFunctions.compareString("Name in personal info section", fName +" "+lName, acctPg.getPersonalName());
		result &= MiscFunctions.compareString("Email in personal info section", email, acctPg.getPersonalEmail());
		result &= MiscFunctions.compareString("Phone in personal info section", phone, acctPg.getPersonalPhone());
		result &= MiscFunctions.compareString("Notice in personal info section", notice, acctPg.getPersonalNotice());
		if (!result) {
			throw new ErrorOnPageException("Personal information is wrong. check logger error.");
		}
		logger.info("---Verify personal information correctly!");
	}
	
	/** Verify the links under Personal Information section correctly */
	private void verifyPersonalInfoLinks() {
		HFUpdateEmailAddressPage updEmailPg = HFUpdateEmailAddressPage.getInstance();
		
		acctPg.clickPersonalInfoLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
		
		acctPg.clickHALIDLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
		
		if(hf.isSignInWorkFlows(url)){ //Only in Address mode, update email page will display after click Email address link.
			acctPg.clickEmailAddrLink();
			updEmailPg.waitLoading();
			hf.gotoMyAccountPgFromHeaderBar();
		}
		
		acctPg.clickPhoneNumLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
		
		logger.info("---Verify personal information links correctly!");
	}
}
