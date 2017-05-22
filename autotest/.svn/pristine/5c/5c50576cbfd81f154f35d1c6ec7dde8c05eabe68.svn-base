package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.web.recgov.MarketingWebsiteSignInPage;
import com.activenetwork.qa.awo.pages.web.recgov.OrmsApplicationLaunchPadPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.util.TestProperty;

public class NRRSMarketingWebsite extends WebTestCase {
	/**
	 * Script Name   : <b>CrossoverFromRaToRecGov</b>
	 * Generated     : <b>Oct 13, 2009 2:54:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/13
	 * @author vzhao
	 */
	private String url;
	private LoginInfo login;

	public void execute() {
		web.invokeURL(url,false);
		web.agencySignIn(login.userName, login.password, login.envType);
		this.verifyNRRSMarketingPage();
		System.exit(0);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		login = new LoginInfo();
		login.userName = "qa-jdu-adm";
		login.password = "waitforreplacement";
		login.envType = "";//choose default
	}
	
	public void verifyNRRSMarketingPage() {
		OrmsApplicationLaunchPadPage launchPad = OrmsApplicationLaunchPadPage.getInstance();
		MarketingWebsiteSignInPage marketSignIn = MarketingWebsiteSignInPage.getInstance();
		AlertDialogPage secAlertDialog = AlertDialogPage.getInstance();
		
		logger.info("Verify accessing Marketing website.");
		launchPad.selectContract("NRRS Contract");
		browser.waitExists(secAlertDialog,launchPad);
		launchPad.clickMarketingSite();
		browser.waitExists(marketSignIn,secAlertDialog);//add marketing home page when system works
		
		if(marketSignIn.exists()) {
			throw new ErrorOnPageException("User should be logged in marketing site.");
		} else {
			logger.info("---User access to Marketing site successful.");
		}
	}
}
