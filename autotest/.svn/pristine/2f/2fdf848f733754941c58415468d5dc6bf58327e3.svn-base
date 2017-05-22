/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import com.activenetwork.qa.awo.pages.web.uwp.UwpOopsErrorPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPageNotFoundErrorPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Input an invalid URL of campground detail page and check the error page is shown.
 * Access the XML format page by the url like: 
 * http://[host URL]/campgroundDetails.do?contractCode=[ContractId]&parkId=[facilityId]&xml=true
 * @Preconditions:
 * @SPEC:
 * Campground Detail in XML [TC:032509]
 * @Task#: Auto-1295
 * 
 * @author Lesley Wang
 * @Date  Oct 31, 2012
 */
public class CampgroundDetail_VerifyInvalidURLParameters extends WebTestCase {
	private UwpPageNotFoundErrorPage errorPage = UwpPageNotFoundErrorPage.getInstance();
	private UwpOopsErrorPage oopsPage = UwpOopsErrorPage.getInstance();
	private String hostSiteURL, contractAndParkIDURL;
	public void execute() {
		// 1. Input the URL without contract code parameter
		url = this.updateUrlValue("", bd.parkId, true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage);
		
		url = this.updateUrlValue("", bd.parkId, false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage);
		
		// 2. Input the URL without park id parameter
		url = this.updateUrlValue(bd.contractCode, "", true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage);
		
		url = this.updateUrlValue(bd.contractCode, "", false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage);
		
		// 3. Input the URL with a non-numeric park id
		url = this.updateUrlValue(bd.contractCode, "ABCD", true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage);
		
		url = this.updateUrlValue(bd.contractCode, "@#$%", false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage);
		
		// 4. Input the URL with a park id which is not exist
		url = this.updateUrlValue(bd.contractCode, "11111111", true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage); //Sara[20140409] DEFECT-62054, oopsPage);
		
		url = this.updateUrlValue(bd.contractCode, "11111111", false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(errorPage); //Sara[20140409] DEFECT-62054, oopsPage);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		bd.contractCode = "NRSO";
		bd.parkId = "70984";
		hostSiteURL = TestProperty.getProperty(env + ".web.ra.url") + "campgroundDetails.do?";
		contractAndParkIDURL = "contractCode=" + bd.contractCode + "&parkId=" + bd.parkId;
		url =  hostSiteURL + contractAndParkIDURL + "&xml=true";
	}
	
	private String updateUrlValue(String contractCode, String parkID, boolean isXML) {
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
		if (isXML) {
			url += "&xml=true";
		}	
		return url;
	}
	
	private void verifyErrorPageDisplayedWhenInputInvalidURL(Object page) {
		String actErrorMeg = null;
		String expErrorCode = null;
		if (page == errorPage) {
			errorPage.waitLoading();
			actErrorMeg = errorPage.getTechnicalErrorMessage();
			expErrorCode = "404 - Document Not Found";
		} else if (page == oopsPage) {
			oopsPage.waitLoading();
			actErrorMeg = oopsPage.getTechnicalErrorMessage();
			expErrorCode = "Your request cannot be completed";
		}
		
		if (!actErrorMeg.equals(expErrorCode)) {
			throw new ErrorOnPageException("The error code is wrong!", expErrorCode, actErrorMeg);	
		}
		logger.info("The 'page not found' error page is displayed correctly after input the url=" + url);
	}

}
