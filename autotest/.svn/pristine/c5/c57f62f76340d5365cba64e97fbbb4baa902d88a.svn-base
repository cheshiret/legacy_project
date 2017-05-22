package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.headertabswitch;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: pass on local
 * @Description:
 * Close all browser windows, open a new window
	Step 1. Land on Home page, enter "abcd" in 'Where' field without click search button
	Step 2. Switch to 'My Account' page, then switch to 'Home' tab ,verify Blank in 'where' field on home page
 * @Preconditions:
 * @SPEC: Story X
 * @Task#:AUTO-797
 * 
 * @author bzhang
 * @Date  Nov 10, 2011
 */
public class BlankWhereFieldReset extends RecgovTestCase{
	
	public void execute(){
		web.invokeURL(url);
		//set "where" value in search panel without click search button
		web.makeUnifiedSearch(bd);
		
		bd.whereTextValue = UwpUnifiedSearch.DEFAULT_WHEREVALUE; // default value changed
		this.verifyMyAccountSwitchToHome();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.clickSearch = false;
		bd.whereTextValue = "abcd";
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
	}
	
	
	public void verifyMyAccountSwitchToHome(){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		UwpSignInPage singInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		//1: switch to "my account" page via header TAB
		headerBar.gotoMyReservationsAccount();
//		singInPg.waitLoading();
		signInOrUpPg.waitLoading(); //Sara[12/3/2013] New sign in or Sign up in rec.gov website
		
		//2: goto home page via header TAB
		web.gotoHomePage();		
		searchPanel.verifySearchCriteria(bd);
	}

}
