
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.findsiteform;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1:Verify that when the value of 'Arrival date' is blank, the value of 'Length of stay' can be blank. 
 * 2:When the value of 'Arrival date' is not blank, the value of 'Length of stay' must be a number between 1 and 365.
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 4, 2011
 */
public class LengthOfStayVerify extends RecgovTestCase {
	private String errorMes, topPageMes; 
	
	private String[] incorrectLengthStay, correctLengthStay;

	public void execute() {
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		//1: verify when the value of "Arrival date" is blank, the value of "length of stay" can be blank.
		this.verifyLengthOfStayFormat(incorrectLengthStay, true);
		
		//2:When the value of 'Arrival date' is not blank, the value of 'Length of stay' must be a number between 1 and 365.
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "3";
		web.gotoCampgroundDetailsPg(bd);
		//3: verify the invalid length of stay will result in error message.
		this.verifyLengthOfStayFormat(incorrectLengthStay, false);
		
		//4: verify the valid valid length of stay will NOT result in error message.
		this.verifyLengthOfStayFormat(correctLengthStay, true);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		errorMes = "Length of stay must be a number between 1 and 365.";
		topPageMes = "No results found matching your search.";
		incorrectLengthStay = new String[]{"","0","366","a","%","1.5"};
		correctLengthStay = new String[]{"1", "365", "100"};
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "Camp Sherman Campground";
		bd.interestInValue = "Camping & Lodging";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		
		bd.lookFor = "Lookouts";
		bd.occupants = "2";
		bd.moreOptions = true;
		bd.electricHookupValue = "15 Amp (or more)";
		bd.waterFront = true;
		
		bd.flexibleValue = "Not Flexible";
		bd.accessibilityNeeds = true;
		bd.petsAllowed = true;
		bd.loop = "Any Loop";
	}
	
	/**
	 * Verify length format according the message after searching
	 * @param inputLength
	 * @param correctFunctional: true: option correct functional
	 *                           false: option incorrect function
	 */
	private void verifyLengthOfStayFormat(String[] lengthOfStays, boolean correctFunctional){
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();
		UwpCampingPage commonPg = UwpCampingPage.getInstance();
		
		for(String lengthOfStay: lengthOfStays){
			findSitePanel.setLengthOfStay(lengthOfStay);
			findSitePanel.clickSearch();
			commonPg.waitLoading();
			if(correctFunctional){
				commonPg.verifyWarningMsg(new String[]{StringUtil.EMPTY, topPageMes});
			}else{
				commonPg.verifyWarningMsg(errorMes);
			}
		}
	}

}
