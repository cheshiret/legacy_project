package com.activenetwork.qa.awo.keywords;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.LoginAttr;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.pages.orms.OrmsGeneralSignedInPage;
import com.activenetwork.qa.awo.pages.orms.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.OrmsLoginPage;
import com.activenetwork.qa.awo.pages.orms.OrmsPage;
import com.activenetwork.qa.awo.pages.orms.OrmsSigninPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.datacollection.StringData;
import com.activenetwork.qa.testapi.keyword.Keyword;
import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class Orms extends Keyword implements OrmsConstants{
	protected Ajax ajax=AwoAjax.getInstance();
	AwoDatabase db=AwoDatabase.getInstance();
	
	/**
	 * The method execute the process that open the url
	 * 
	 * @param url
	 */
	public void invokeURL(String url, boolean newBrowser,boolean getBuildNum) {
		super.invokeURL(url, newBrowser);
		
		OrmsHomePage ormsHmPg = OrmsHomePage.getInstance();
		ormsHmPg.waitLoading();
		if (getBuildNum) {
			getBuildNumber();
		}
	}
	
	@Override
	public void invokeURL(String url, boolean newBrowser) {
		invokeURL(url,newBrowser,true);
	}
	
	public void invokeURL(String url) {
		invokeURL(url, true);
	}
	
	public void invokeANEURL(String url, boolean newBrowser)
	{
		super.invokeURL(url, newBrowser);
	}
	
	public void login(StringData<LoginAttr> login, SalesChannel channel) {
		login(login,channel,true);
	}
	
	public String getBuildNumber() {
		String bn = OrmsGeneralSignedInPage.getInstance().getBuildNum();
		TestProperty.putProperty("orms.build", bn);
		logger.info("Orms Build# is " + bn);
		
		return bn;
	}
	
	public String getSalesChannel() {
		return OrmsGeneralSignedInPage.getInstance().getManagerName();
	}
	
	/**
	 * This method executes the work flow of logging into Orms
	 * The work flow will open a new browser to orms home page and ends at
	 * mrg home page.
	 */
	public void login(StringData<LoginAttr> login, SalesChannel channel, boolean newBrowser) {
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
		OrmsLoginPage omLoginPg = OrmsLoginPage.getInstance();

		OrmsPage page = signIn(login, channel,newBrowser);
		if (page == omSigninPg)
			throw new ItemNotFoundException("Failed to login to " + channel);
		else if (page == omLoginPg) {
			omLoginPg.logIn(login);
		}
		
	}
	
	public OrmsPage signIn(StringData<LoginAttr> login, SalesChannel mgr, boolean newBrowser) {
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
		OrmsLoginPage omLoginPg = OrmsLoginPage.getInstance();
		OrmsGeneralSignedInPage omSignedInPg = OrmsGeneralSignedInPage
				.getInstance();

		logger.info("Login in " + mgr + " Manager with url=" + login.get(LoginAttr.url));

		gotoSignInPg(login.get(LoginAttr.url), mgr,newBrowser);
		omSigninPg.login(login);

		Object page = browser.waitExists(omSigninPg, omLoginPg, omSignedInPg);
		
		if(page!=omSigninPg) {
			String currentID=((OrmsPage)page).getSessionID();
			String previousIDs=TestProperty.getProperty("session.id","");
			if(StringUtil.isEmpty(previousIDs) || !previousIDs.contains(currentID)) {
				String sessionIDs=StringUtil.isEmpty(previousIDs)?currentID:previousIDs+";"+currentID;
				TestProperty.putProperty("session.id", sessionIDs);
				DataBaseFunctions.recordSessionID(Integer.parseInt(TestProperty.getProperty("caseID", "0")), TestProperty.getProperty("runningId", ""), sessionIDs,currentID);
			} 
		}

		return (OrmsPage) page;
	}
	
	/**
	 * This work flow start from orms home page and end at sign in page
	 * 
	 * @param login
	 * @param mgr
	 */
	public void gotoSignInPg(String url, SalesChannel mgr, boolean newBrowser) {
		OrmsHomePage omHmPg = OrmsHomePage.getInstance();
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();

		logger.info("Go to sign in page from orms home page");
		invokeURL(url, newBrowser);
		omHmPg.clickLink(mgr);

		omSigninPg.waitLoading();
	}
}
