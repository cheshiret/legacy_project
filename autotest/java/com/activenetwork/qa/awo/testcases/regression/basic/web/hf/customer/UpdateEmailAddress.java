/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer;

import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Update email address and verify the updated email in DB and UI
 * @Preconditions:
 * @SPEC: Update Customer profile - update email address [TC:045719]
 * @Task#: Auto-1620
 * 
 * @author Lesley Wang
 * @Date  Apr 24, 2013
 */
public class UpdateEmailAddress extends HFSKWebTestCase {
	private String newEmail;
	@Override
	public void execute() {
//		hf.invokeURL(url);
		hf.signIn(url, cus.email, cus.password);
		this.veirfyEmailAddrInOverviewPg(cus.email);
		hf.updateEmailAddress(cus.password, newEmail);
		hf.gotoMyAccountPgFromHeaderBar();
		this.veirfyEmailAddrInOverviewPg(newEmail);
		this.verifyEmailAddrInDB(cus.email, newEmail);
		hf.signOut();
		
		hf.signIn(url, newEmail, cus.password);
		this.veirfyEmailAddrInOverviewPg(newEmail);
		hf.updateEmailAddress(cus.password, cus.email);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "updateemailaddr01@test.com";
		cus.password = "asdfasdf";
		newEmail = "UPDATEemailaddr02@test.com";
	}

	private void veirfyEmailAddrInOverviewPg(String email) {
		HFAccountOverviewPage overviewPg = HFAccountOverviewPage.getInstance();
		String actual = overviewPg.getPersonalEmail();
		if (!email.toLowerCase().equals(actual)) {
			throw new ErrorOnPageException("Email Address in Account Overview page is wrong!", email, actual);
		}
		logger.info("Verify Email address is correct in Account Overview page.");
	}
	
	private void verifyEmailAddrInDB(String email, String newEmail) {
		email = email.toLowerCase();
		newEmail = newEmail.toLowerCase();
		if (hf.checkLoginNameExists(schema, email) || !hf.checkLoginNameExists(schema, newEmail)) {
			throw new ErrorOnDataException("Email Address:" + newEmail + " should exist but " + email + " should NOT exist in DB!");
		}
		logger.info("Verify Update email address correctly in DB");
	}
}
