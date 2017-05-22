package com.activenetwork.qa.awo.keywords.web;

import com.activenetwork.qa.awo.keywords.Awo;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovInteragencyPassDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCreateAccountPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCurrentResListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPastResListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPolicyChangePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPrintTicketsAndPermitsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSocialSecurityNumberRequiredPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateProfilePage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;

public abstract class Web extends Awo {
	
	/**
	 * Invoke a new browser with the given URL. 
	 * By default this method will firstly get and set the current web build number, 
	 * since each Test case in QA need to retrieve the build number. 
	 * There are some scenarios where we don't need to get the build number. 
	 * In this case, use method invokeURL(String url, boolean getBuildNum)
	 * @param url
	 */
	public void invokeURL(String url) {
		invokeURL(url, false, true);
	}

	/** for some existing test cases*/
	public void invokeURL(String url, boolean getBuildNum) {
		invokeURL(url, getBuildNum, true);
	}
	
	/**
	 * Invoke a new browser with the given URL. 
	 * We can use getBuildNum flag to control if we want to get a build number. 
	 * @param url - the url to open
	 * @param getBuildNum - flag to control if we want to get a build number	 
	 */
	public void invokeURL(String url, boolean getBuildNum, boolean isNewBrowser) {
		invokeURL(url, RA, getBuildNum, isNewBrowser);
	}
	
	/**
	 * Sign in web account with the given email and password.
	 * starts at header bar, ends at account over view page.
	 * @param email
	 * @param pw
	 */
	public void signIn(String email, String pw) {
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpPolicyChangePage policyPg = UwpPolicyChangePage.getInstance();
		UwpSocialSecurityNumberRequiredPage ssnPg=UwpSocialSecurityNumberRequiredPage.getInstance();
		
		logger.info("Sign in with email=" + email + "/password=" + pw);
		if(!signInPg.exists() && !signInOrUpPg.exists()) {
		  	memberStatusBar.waitLoading();
			memberStatusBar.gotoSignIn();
//			signInPg.waitExists();
		}
		Object page = browser.waitExists(signInPg, signInOrUpPg);// update from 3.04.01 due to sign in page changed in RA
		if (page == signInPg) {
			signInPg.signIn(email, pw);
		} else if (page == signInOrUpPg) {
			signInOrUpPg.signIn(email, pw);
		}
		
		page=browser.waitExists(accountOVPg,orderDetails,policyPg,ssnPg);
		
		if(page==policyPg){
			if (policyPg.isKeepCurrentUserExist()) { //Lesley[20131014]: update due to new Policy Change page in RA.com in 3.05.00
				policyPg.clickKeepCurrentUser();
			} else {
				policyPg.clickAcceptPolicy();
			}
			page = browser.waitExists(accountOVPg,orderDetails,ssnPg);
		}
		if(page == ssnPg) {
			ssnPg.setSocialSecurityNumber("078051120");
			ssnPg.clickContinue();
			browser.waitExists(orderDetails,accountOVPg);
		}
	}
	
	/**
	 * Go to Sign Up page from UwpHomePage
	 */
	public void gotoSignUppage() {
		UwpCreateAccountPage accountPage=UwpCreateAccountPage.getInstance();
		UwpHomePage homePage=UwpHomePage.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		logger.info("Go to Sign Up page from UwpHomePage");
		homePage.clickSigUpLink();
		Object page = browser.waitExists(accountPage, signInOrUpPg);
		if (page == signInOrUpPg) {
			signInOrUpPg.clickContinueToSignUp();
			signInOrUpPg.waitLoading();
		}
	}
	
	/**
	 * Goto update profile page from 'My Account' panel
	 */
	public void gotoUpdateProfilePage() {
		UwpUpdateProfilePage profilePage=UwpUpdateProfilePage.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();
		logger.info("Goto update profile page from 'My Account' panel");
		accountPanelPg.waitLoading();
		accountPanelPg.gotoUpdateProfile();
		profilePage.waitLoading();
	}

	public void gotoHomePage() {
		UwpHomePage hmPg = UwpHomePage.getInstance();
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		headerBar.clickHomeLink();
		hmPg.waitLoading();
	}
	
	/**
	 * Sign out the web account, starts at header bar and ends at home page.
	 */
	public void signOut() {
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		UwpHomePage hmPg = UwpHomePage.getInstance();
		ConfirmDialogPage dialog=ConfirmDialogPage.getInstance();
		RecgovInteragencyPassDetailsPage interagencyPassDetailsPg = RecgovInteragencyPassDetailsPage.getInstance();
		
		logger.info("Sign out");
		
//		memberStatusBar.waitExists();
		memberStatusBar.signOut();

		dialog.resetDefault();
		browser.waitExists(dialog,hmPg, interagencyPassDetailsPg);
	}
	
	public void gotoPrintTicketsPermitsPg() {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpPrintTicketsAndPermitsPage printPg = UwpPrintTicketsAndPermitsPage.getInstance();
		logger.info("Go to print tickets & permits page from account panel...");
		accountPanel.clickPrintTicketsPermits();
		printPg.waitLoading();
	}
	
	public void gotoPastResPgFromCurrentResPg(){
		UwpCurrentResListPage currentResListPg = UwpCurrentResListPage.getInstance();
		UwpPastResListPage pastResListPg = UwpPastResListPage.getInstance();
		logger.info("Go to past reservation list page from current reservation list page based on past reservation link in current reservation page");
		currentResListPg.clickPastReservationLink();
		pastResListPg.waitLoading();
	}
	
	public void gotoCurrentResPgFromPastResPg(){
		UwpCurrentResListPage currentResListPg = UwpCurrentResListPage.getInstance();
		UwpPastResListPage pastResListPg = UwpPastResListPage.getInstance();
		logger.info("Go to current reservation list page from past reservation list page based on current reservation link in past reservation page");
		pastResListPg.clickCurrentReservationLink();
		currentResListPg.waitLoading();
	}
}
