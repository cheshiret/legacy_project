/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpOopsErrorPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPageNotFoundErrorPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking campsite next available date In XML Format. 
 * Access the XML format page by the url like: 
 * http://[host URL]/campsiteCalendar.do?[Contract]&[Campground]&[search parameter]&[search parameter]&[Date parameter]&findavail=next...&xml=true
 * @Preconditions:
 * @SPEC: 
 * 	Next Available Date in XML [TC:032550]
 * @Task#: Auto-1299
 * @Note: related Defect-38950 was fixed to ignore all invalid siteType values
 * 
 * @author Lesley Wang
 * @Date  Dec 3, 2012
 */
public class CampsiteNextAvailableDate_VerifyInvalidURLParameters extends WebTestCase {
	private UwpDateRangeAvailabilityPage searchResultPg = UwpDateRangeAvailabilityPage.getInstance();
	private String hostSiteURL;
	private UwpPageNotFoundErrorPage errorPage = UwpPageNotFoundErrorPage.getInstance();
	private UwpOopsErrorPage oopsPage = UwpOopsErrorPage.getInstance();
	
	public void execute() {
		// 1. Input the URL with parkId but without contractCode
		url = this.updateUrlValue("parkId", bd.parkId, true);
		this.inputInvalidURLAndVerifyErrorPage(url, errorPage);
		
		// 2. Input the URL with contractCode, but without parkId
		url = this.updateUrlValue("contractCode", bd.contractCode, true);
		this.inputInvalidURLAndVerifyErrorPage(url, oopsPage);
		
		hostSiteURL = this.updateUrlValue("contractCode", bd.contractCode, false) + "&";
		hostSiteURL = this.updateUrlValue("parkId", bd.parkId, false) + "&";
	
		// 3. Input the URL with invalid arvdate 
		String date = DateFunctions.getDateAfterToday(-2);
		this.inputURLWithInvalidParamAndVerifyErrorMessage("arvdate", date, "past arrival date");
		
		// 4. Input the URL with wrong format arvdate
		date = DateFunctions.getDateAfterToday(1, "yyyy/mm/dd");
		this.inputURLWithInvalidParamAndVerifyErrorMessage("arvdate", date, "arrival date with wrong format");
		
		// 5. Input the URL with invalid lengthOfStay
		date = DateFunctions.getDateAfterToday(2);
		this.inputURLWithInvalidParamAndVerifyErrorMessage("arvdate=" + date + "&lengthOfStay", "0", "invalid lengthOfStay");
		
		// 6. Input the URL with invalid enddate
		String endDate = DateFunctions.getDateAfterToday(366);
		this.inputURLWithInvalidParamAndVerifyErrorMessage("range=2&arvdate=" + date + "&lengthOfStay=1&enddate=", endDate, "end date is more than 12 weeks after start date");
		
		// 7. Input the URL without enddate when range=2
		this.inputURLWithInvalidParamAndVerifyErrorMessage("range=2&arvdate=" + date + "&lengthOfStay", "1", "no end date when range=2");
		
		// 8. Input the URL without arvdate when range=2
		this.inputURLWithInvalidParamAndVerifyErrorMessage("range=2&enddate=" + date + "&lengthOfStay", "1", "no arrival date when range=2");
		
		hostSiteURL = this.updateUrlValue("arvdate", date, false) + "&";
		hostSiteURL = this.updateUrlValue("lengthOfStay","1", false) + "&";
		
//		// 9. Input the URL with a non-integer siteType - Defect-38950
//		url = this.updateUrlValue("siteType", "AB", true);
//		this.inputInvalidURLAndVerifyErrorPage(url, oopsPage);
		
//		// 10. Input the URL with invalid amenity - Defect-39164 ->Sara[4/3/2014] Closed as won't fix by Steve because 1yr ago, still pending
//		this.inputURLWithInvalidParamAndVerifyErrorMessage("expfits=1&eqplen", "abc", "eqplen is not integer");
//
//		// 11. Input the URL with invalid maxpeople - Defect-39164
//		this.inputURLWithInvalidParamAndVerifyErrorMessage("expfits=1&maxpeople", "abc", "invalid eqplen");
	}	
	
	@Override
	public void wrapParameters(Object[] param) {
		bd.contractCode ="CO";
		bd.parkId = "50020";
		hostSiteURL =  TestProperty.getProperty(env + ".web.ra.url") + "campsiteCalendar.do?findavail=next&";
	}
	
	private void inputURLWithInvalidParamAndVerifyErrorMessage(String param, String value, String meg) {
		url = this.updateUrlValue(param, value, true);
		web.invokeURL(url);
		searchResultPg.waitLoading();
		String errorFromXML = searchResultPg.getErrorMessage();
		
		// Lesley[20130912]: the following code is not valid after RA changed to Unified Search mode
//		url = this.updateUrlValue(param, value, false);
//		web.invokeURL(url);
//		searchResultPg.waitExists();
//		String errorFromHttp = searchResultPg.getErrorMessage();
		
//		if (!errorFromXML.equals(errorFromHttp)) {
//			throw new ErrorOnPageException(meg + " error Message is wrong", errorFromHttp, errorFromXML);
//		}
		if (StringUtil.isEmpty(errorFromXML)) {
			throw new ErrorOnPageException(meg + " error Message should not be empty!");
		}
		logger.info(meg + " error Message is correct as: " + errorFromXML);
	}
	
	private String updateUrlValue(String param, String value, boolean isXML) {
		String url = hostSiteURL + param + "=" + value;
		
		if (isXML) {
			url += "&xml=true";
		}	
		return url;
	}
	
	private void inputInvalidURLAndVerifyErrorPage(String url, Object page) {
		String actErrorMeg = null;
		String expErrorCode = null;
		
		web.invokeURL(url);
		if (page == errorPage) {
			errorPage.waitLoading();
			actErrorMeg = errorPage.getTechnicalErrorMessage();
			expErrorCode = errorPage.ERROR_DOCNOTFOUND;
		} else if (page == oopsPage) {
			if (!oopsPage.exists()) 
				throw new ErrorOnPageException("oops page should be shown.");
			oopsPage.waitLoading();
			actErrorMeg = oopsPage.getTechnicalErrorMessage();
			expErrorCode = oopsPage.ERROR_REQUEST_NOT_COMPLETED;
		}
		
		if (!actErrorMeg.equals(expErrorCode)) {
			throw new ErrorOnPageException("The error code is wrong!", expErrorCode, actErrorMeg);	
		}
		logger.info("The 'page not found' error page is displayed correctly after input the url=" + url);
	}
}
