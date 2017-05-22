/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;


import com.activenetwork.qa.awo.pages.web.uwp.UwpOopsErrorPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPageNotFoundErrorPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Input an invalid URL of campground search page and check the error message or error page is shown.
 * Access the XML format page by the url like: 
 * http://[host URL]/campgroundSearch.do?[search parameter]&[search parameter]&\ufffd&xml=true
 * @Preconditions:
 * @SPEC: 
 * Campground Search Result in XML [TC:032549]
 * Verify detailed Search parameters in Campground Search Result [TC:032834]
 * @Task#: Auto-1297
 * @Note: related Defect-38950 was fixed to ignore all invalid siteType values
 * NOT RUN if RA.com has Unified Search
 * @author Lesley Wang
 * @Date  Nov 28, 2012
 */
public class CampgroundSearchResult_VerifyInvalidURLParams extends WebTestCase {
	private UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
	private String hostSiteURL;
	private UwpPageNotFoundErrorPage errorPage = UwpPageNotFoundErrorPage.getInstance();
	private UwpOopsErrorPage oopsPage = UwpOopsErrorPage.getInstance();
	
	public void execute() {
		// 1. Input the URL with invalid pname parameter
		this.inputURLWithInvalidParamAndVerifyErrorMessage("pname", "AB", "park name short than 4 chars");
		
		// 2. Input the URL with invalid site type parameter. Blocked by  Defect-38950
//		url = this.updateUrlValue("siteType", "abcd", true);
//		web.invokeURL(url);
//		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
//		
//		url = this.updateUrlValue("siteType", "abcd", false);
//		web.invokeURL(url);
//		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		// 3. Input the URL with invalid arvdate 
		String date = DateFunctions.getDateAfterToday(-2);
		this.inputURLWithInvalidParamAndVerifyErrorMessage("arvdate", date, "parst arrival date");
		
		// 4. Input the URL with wrong format arvdate
		date = DateFunctions.getDateAfterToday(1, "yyyy/mm/dd");
		this.inputURLWithInvalidParamAndVerifyErrorMessage("arvdate", date, "arrival date with wrong format");
		
		// 5. Input the URL with invalid lengthOfStay
		date = DateFunctions.getDateAfterToday(2);
		this.inputURLWithInvalidParamAndVerifyErrorMessage("arvdate=" + date + "&lengthOfStay", "0", "invalid lengthOfStay");
		
		// 6. Input the URL without enddate when range=2
		this.inputURLWithInvalidParamAndVerifyErrorMessage("range=2&arvdate=" + date + "&lengthOfStay", "1", "no end date when range=2");
		
		// 7. Input the URL without arvdate when range=2
		this.inputURLWithInvalidParamAndVerifyErrorMessage("range=2&enddate=" + date + "&lengthOfStay", "1", "no arrival date when range=2");
		
		// 8. Input the URL with invalid amenity
		url = this.updateUrlValue("expwith=1&amenity", "1234", true);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		url = this.updateUrlValue("expwith=1&amenity", "1234", false);
		web.invokeURL(url);
		this.verifyErrorPageDisplayedWhenInputInvalidURL(oopsPage);
		
		// 9. Input the URL with invalid eqplen
		this.inputURLWithInvalidParamAndVerifyErrorMessage("expfits=1&eqplen", "abc", "invalid eqplen");
		
		// 10. Input the URL with invalid maxpeople
		this.inputURLWithInvalidParamAndVerifyErrorMessage("expfits=1&maxpeople", "abc", "invalid eqplen");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		hostSiteURL = TestProperty.getProperty(env + ".web.ra.url") + "campgroundSearch.do?pstate=CA&";	
	}
	
	private void inputURLWithInvalidParamAndVerifyErrorMessage(String param, String value, String meg) {
		url = this.updateUrlValue(param, value, true);
		web.invokeURL(url);
		String errorFromXML = this.getErrorMsgWhenInputInvalidURL();
		
		url = this.updateUrlValue(param, value, false);
		web.invokeURL(url);
		String errorFromHttp = this.getErrorMsgWhenInputInvalidURL();
		
		if (!errorFromXML.equals(errorFromHttp)) {
			throw new ErrorOnPageException(meg + " error Message is wrong", errorFromHttp, errorFromXML);
		}
		logger.info(meg + " error Message is correct as: " + errorFromHttp);
	}
	
	private String updateUrlValue(String param, String value, boolean isXML) {
		String url = hostSiteURL + param + "=" + value;
		
		if (isXML) {
			url += "&xml=true";
		}	
		return url;
	}
	
	private String getErrorMsgWhenInputInvalidURL() {
		searchPanel.waitLoading();
		return searchPanel.getErrorMsg();
	}
	
	private void verifyErrorPageDisplayedWhenInputInvalidURL(Object page) {
		String actErrorMeg = null;
		String expErrorCode = null;
		if (page == errorPage) {
			errorPage.waitLoading();
			actErrorMeg = errorPage.getTechnicalErrorMessage();
			expErrorCode = "404 - Document Not Found";
		} else if (page == oopsPage) {
			if (!oopsPage.exists()) 
				throw new ErrorOnPageException("oops page should be shown.");
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
