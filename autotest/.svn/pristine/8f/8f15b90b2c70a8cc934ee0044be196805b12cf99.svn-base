
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.findsiteform;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1: verify valid occupant value is integer or blank.
 * 2: verify invalid value will result in error message.
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 4, 2011
 */
public class TentOnlyOccupantVerify extends RecgovTestCase {
	private String errorMes, topPageMes; 
	private String[] incorrectOccupants, correctOccupants;

	public void execute() {
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		//1: verify the invalid occupant will result in error message.
		this.verifyOccupantFormat(incorrectOccupants, false);
		
		//2: verify the valid occupant will NOT result in error message.
		this.verifyOccupantFormat(correctOccupants, true);
		
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		errorMes = "Occupants entered is invalid. Please confirm your entry or enter a different number of occupants.";
		topPageMes = "No results found matching your search.";
		incorrectOccupants = new String[]{"-1","a","%","1.5"};
		correctOccupants = new String[]{"","0", "1", "10", "100"};
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		
		//expect value 1
		bd.whereTextValue = "Camp Sherman Campground";
		bd.interestInValue = "Camping & Lodging";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		
		bd.lookFor = "Tent-only sites";
		bd.occupants = "2";
		bd.moreOptions = true;
		bd.waterFront = true;
		
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.flexibleValue = "Flexible for next 2 weeks";
		bd.lengthOfStay = "3";
		bd.accessibilityNeeds = true;
		bd.petsAllowed = true;
		bd.loop = "Any Loop";
	}
	
	/**
	 * Verify occupant format according the message after searching
	 * @param inputOccpuant
	 * @param withCorrectData: true: enter the correct data
	 *                           false: enter the incorrect data
	 */
	private void verifyOccupantFormat(String[] inputOccpuant, boolean withCorrectData){
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();
		UwpCampingPage commonPg = UwpCampingPage.getInstance();
		
		for(String occupant: inputOccpuant){
			findSitePanel.setOccupants(occupant);
			findSitePanel.clickSearch();
			commonPg.waitLoading();
			if(withCorrectData){
				commonPg.verifyWarningMsg(new String[]{StringUtil.EMPTY, topPageMes});
			}else{
				commonPg.verifyWarningMsg(errorMes);
			}
		}
	}

}
