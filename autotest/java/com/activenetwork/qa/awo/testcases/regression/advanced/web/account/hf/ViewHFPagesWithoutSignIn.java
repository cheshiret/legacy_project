/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import com.activenetwork.qa.awo.pages.web.hf.HFHeaderBar;
import com.activenetwork.qa.awo.pages.web.hf.HFSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Check the Sign in page will be shown when view HF web pages without sign in.
 * @Preconditions:
 * @SPEC: direct turn to sign In page [TC:048839] 
 * @Task#: Auto-1482
 * 
 * @author Lesley Wang
 * @Date  Feb 27, 2013
 */
public class ViewHFPagesWithoutSignIn extends HFSKWebTestCase {
	private HFSignInPage signInPg = HFSignInPage.getInstance();
	private HFHeaderBar header = HFHeaderBar.getInstance();
	
	@Override
	public void execute() {
		hf.invokeURL(url);
		header.clickMyAccountTab();
		signInPg.waitLoading();
		
		header.clickPurchaseLicenseTab();
		signInPg.waitLoading();
		
		hf.invokeURL(url + "memberAccountOverview.do", false, false);
		signInPg.waitLoading();
		
		hf.invokeURL(url + "memberUpdateProfile.do", false, false);
		signInPg.waitLoading();
		
		hf.invokeURL(url + "memberUpdatePassword.do", false, false);
		signInPg.waitLoading();
		
		hf.invokeURL(url + "memberUpdateEmail.do", false, false);
		signInPg.waitLoading();
		
		hf.invokeURL(url + "productCategoriesList.do?prodType=license&topTabIndex=PurchaseLicence", false, false);
		signInPg.waitLoading();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		
	}

}
