package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.pages.orms.common.OrmsRestPinPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSigninPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case used to verify error message when reset pin number and verify reset pin number message
 * @Preconditions:
 * @SPEC:DEFECT-29204
 * @Task#:Auto-587

 * @author VZhang1
 * @Date May 24, 2011
 */
public class ResetPinNum extends VenueManagerTestCase{

	private OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
	private OrmsRestPinPage resetPinPg = OrmsRestPinPage.getInstance();
	private String errorMsg, expectMsg;
	public void execute() {
		//login in orms home page and go to venue sign in page
		vm.gotoSignInPg(login.url, "Venue");

		login.userName = "";
		login.password = "";
		//set sign in info
		vm.setSignInInfo(login);
		//verify error message when user name and password is blank
		this.verifyErrorMessage(errorMsg);

		login.userName = "qa-user";
		login.password = TestProperty.getProperty("orms.fm.pw");
		//set sign in info
		vm.setSignInInfo(login);
		//verify error message when user name is invalid
		this.verifyErrorMessage(errorMsg);

		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = "";
		//set sign in info
		vm.setSignInInfo(login);
		//verify error message when password is blank
		this.verifyErrorMessage(errorMsg);

		login.password = "auto321";
		//set sign in info
		vm.setSignInInfo(login);
		//verify error message when password is invalid
		this.verifyErrorMessage(errorMsg);

		login.password =TestProperty.getProperty("orms.fm.pw");
		//set sign in info
		vm.setSignInInfo(login);
		//verify reset pin number message
		this.verifyResetPinMessage(expectMsg);

		browser.closeAllBrowsers();	
	}

	public void wrapParameters(Object[] param) {
		//Login info
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");

		errorMsg = "Incorrect user name and/or password.";
		expectMsg = "Your PIN has been reset to " + new RegularExpression("\\d+", false);

	}

	private void verifyErrorMessage(String expectMsg){
		omSigninPg.clickResetPIN();		
		Object pages = browser.waitExists(omSigninPg,resetPinPg);

		if(pages != omSigninPg){
			throw new ItemNotFoundException("This work flow expect a error message");
		}

		if(!omSigninPg.getErrorMessage().equals(expectMsg)){
			throw new ItemNotFoundException(omSigninPg.getErrorMessage() + " is not correct.");
		}		
	}

	private void verifyResetPinMessage(String expectMsg){
		omSigninPg.clickResetPIN();
		Object pages = browser.waitExists(omSigninPg,resetPinPg);

		if(pages != resetPinPg){
			throw new ItemNotFoundException("This work flow expect reset pin message page");
		}

		if(!resetPinPg.getMessage().matches(expectMsg)){
			throw new ItemNotFoundException(resetPinPg.getMessage()+ " is not correct.");
		}
	}
}
