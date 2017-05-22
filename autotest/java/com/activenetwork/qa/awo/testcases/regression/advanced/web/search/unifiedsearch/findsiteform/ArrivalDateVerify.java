
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.findsiteform;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1: verify valid date format reset to the format of "MMM DD YYYY"
 * 2: verify invalid date format reset to blank(or current date format)
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 4, 2011
 */
public class ArrivalDateVerify extends RecgovTestCase {
	private String[] inValidDates, validDates;

	public void execute() {
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		//1: verify the invalid date format
		this.verifyArrivalDateFormat(inValidDates);
		
		//2: verify the valid date format
		this.verifyArrivalDateFormat(validDates);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		inValidDates = OrmsConstants.INVALID_DATES;
		validDates = new String[]{DateFunctions.getDateAfterToday(3, "MMM dd yyyy"), "2020/10/11", "2020-10-21"};
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "Camp Sherman Campground";
		bd.interestInValue = "Camping & Lodging";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		bd.lookFor = "Yurts";
		bd.occupants = "2";
		bd.moreOptions = true;
		bd.waterFront = true;
		bd.arrivalDate = DateFunctions.getDateAfterToday(3, "MMM dd yyyy");
		bd.flexibleValue = "Flexible for next 4 weeks";
		bd.lengthOfStay = "4";
		bd.accessibilityNeeds = true;
		bd.petsAllowed = true;
		bd.loop = "Any Loop";
	}
	
	private void verifyArrivalDateFormat(String[] inputDates){
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();
		boolean currentFlag = findSitePanel.isInvaildDateParsedProperlyByDateComponent(inputDates);
		
		if(currentFlag){
			throw new ErrorOnPageException("The Arrival date parsed INVALID date to valid ones..");
		}
	}

}
