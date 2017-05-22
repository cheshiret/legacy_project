/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import com.activenetwork.qa.awo.pages.web.uwp.UwpOopsErrorPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPageNotFoundErrorPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: ( 	UWP-1403 ) Input an invalid URL of camp site detail page and check the error page is shown.
 * Access the XML format page by the url like: 
 * http://[host URL]/campsiteDetails.do?contractCode=[ContractId]&parkId=[facilityId]&siteId=[SiteId]&xml=true
 * @Preconditions:
 * @SPEC:
 * Campsite Detail in XML [TC:032531]
 * Refer to the defect 30656
 * According to BA: in a nutshell, as long as the error is handling the same way between XML output and HTML output cases, then it is acceptable 
 * @Task#: Auto-1296
 * 
 * @author Lesley Wang
 * @Date  Nov 1, 2012
 */
public class CampsiteDetail_VerifyInvalidURLParameters extends WebTestCase{
	private UwpOopsErrorPage oopsPage = UwpOopsErrorPage.getInstance();
//	private UwpSignInPage signInPage = UwpSignInPage.getInstance();
	private UwpSignInSignUpPage signInPage = UwpSignInSignUpPage.getInstance();
	private UwpPageNotFoundErrorPage errorPg = UwpPageNotFoundErrorPage.getInstance();
	private String hostSiteURL;
	public void execute() {
		/** Input the invalid URL and verify the error page is shown */
		// 1. Input the URL without contract code parameter
		url = this.updateUrlValue("", bd.parkId, bd.siteID, true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(signInPage);
		
		url = this.updateUrlValue("", bd.parkId, bd.siteID, false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(signInPage);
		
		// 2. Input the URL without park id parameter
		url = this.updateUrlValue(bd.contractCode, "", bd.siteID, true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		url = this.updateUrlValue(bd.contractCode, "", bd.siteID, false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		// 3. Input the URL without site id parameter. Refer to Defect-38337
		url = this.updateUrlValue(bd.contractCode, bd.parkId, "", true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPg);
		
		// 4. Input the URL with a non-numeric park id
		url = this.updateUrlValue(bd.contractCode, "ABCD", bd.siteID, true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		url = this.updateUrlValue(bd.contractCode, "ABCD", bd.siteID, false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		// 5. Input the URL with a park id which is not exist
		url = this.updateUrlValue(bd.contractCode, bd.parkId, "11111111", true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		url = this.updateUrlValue(bd.contractCode, bd.parkId, "11111111", false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		bd.contractCode = "NRSO";
		bd.parkId = "70158";
		bd.siteID = "88151";
		hostSiteURL = TestProperty.getProperty(env + ".web.ra.url") + "campsiteDetails.do?";
		url =  hostSiteURL + "contractCode=" + bd.contractCode + "&parkId=" + bd.parkId + "&siteId=" + bd.siteID + "&xml=true";
	}
	
	private String updateUrlValue(String contractCode, String parkID, String siteID, boolean isXML) {
		String url = hostSiteURL;
		if (!StringUtil.isEmpty(contractCode)) {
			url += "contractCode=" + contractCode; 
		}
		if (!StringUtil.isEmpty(parkID)) {
			if (url.endsWith("?")) {
				url += "parkId=" + parkID;
			} else {
				url += "&parkId=" + parkID;
			}
		}
		if (!StringUtil.isEmpty(siteID)) {
			if (url.endsWith("?")) {
				url += "siteId=" + siteID;
			} else {
				url += "&siteId=" + siteID;
			}
		}
		if (isXML) {
			url += "&xml=true";
		}
		return url;
	}
	
	private void verifyErrorPageDisplayedWhenInputInvalidURL(Object page) {
		String actErrorMeg = null;
		String expErrorCode = null;
		if (page == signInPage) {
			if (!signInPage.exists()) {
				throw new ErrorOnPageException("The sign in page should be shown!");
			}
		} else if (page == oopsPage) {
			oopsPage.waitLoading();
			actErrorMeg = oopsPage.getTechnicalErrorMessage();
			expErrorCode = oopsPage.ERROR_REQUEST_NOT_COMPLETED;
			if (!actErrorMeg.equals(expErrorCode)) {
				throw new ErrorOnPageException("The error code is wrong!", expErrorCode, actErrorMeg);	
			}
		} else if (page == errorPg) {
			errorPg.waitLoading();
			actErrorMeg = errorPg.getTechnicalErrorMessage();
			expErrorCode = errorPg.ERROR_DOCNOTFOUND;
			if (!actErrorMeg.equals(expErrorCode)) {
				throw new ErrorOnPageException("The error code is wrong!", expErrorCode, actErrorMeg);	
			}
		}
		logger.info("The error page is displayed correctly after input the url=" + url);
	}
}
