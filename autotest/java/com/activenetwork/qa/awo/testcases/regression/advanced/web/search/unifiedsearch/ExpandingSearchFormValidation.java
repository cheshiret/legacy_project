package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: p 
 * No expanding search form when selecting 'Interesnt in' as 'Please Select', 'Everything', 'Tours & Tickets'' and 'Permits & Wilderness', 
 * @Preconditions:
 * @SPEC: 'Interested in' - drop down mode (expanding search form) [TC:042957] 
 * @Task#: AUTO-1234 
 * 
 * @author SWang
 * @Date 9/13/2012
 */
public class ExpandingSearchFormValidation extends RecgovTestCase {
	private List<String[]> interestInAndExpandingFormStatus = new ArrayList<String[]>();

	public void execute() {
		web.invokeURL(url);
		for(int i=0; i<interestInAndExpandingFormStatus.size(); i++){
			this.verifyExpandingSearchFormExist(interestInAndExpandingFormStatus.get(i)[0], interestInAndExpandingFormStatus.get(i)[1], Boolean.valueOf(interestInAndExpandingFormStatus.get(i)[2]));
		}
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		interestInAndExpandingFormStatus.add(new String[]{UwpUnifiedSearch.EVERYTHING_INSTERETED_IN, "all", "false"});
		interestInAndExpandingFormStatus.add(new String[]{UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN, "camping", "true"});
		interestInAndExpandingFormStatus.add(new String[]{UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN, "dayuse", "true"});
		interestInAndExpandingFormStatus.add(new String[]{UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN, "permit", "false"});
		interestInAndExpandingFormStatus.add(new String[]{UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN, "tour", "false"});
		interestInAndExpandingFormStatus.add(new String[]{UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN, "other", "true"});
	}

	/**
	 * Verify interest in expanding search form exist, firstly make sure the interest in DIV exist
	 * @param interestInOptionName
	 * @param interestInIdValue, all, camping, dayuse, permit, tour, other
	 * @param existed
	 */
	public void verifyExpandingSearchFormExist(String interestInOptionName, String interestInIdValue, boolean existed) {
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		searchPanel.selectInterestIn(interestInOptionName);
		searchPanel.checkInterestInDIVExist(interestInIdValue);
		searchPanel.verifyInterestInExpandingFormExist(interestInIdValue, existed); 
	}
}


