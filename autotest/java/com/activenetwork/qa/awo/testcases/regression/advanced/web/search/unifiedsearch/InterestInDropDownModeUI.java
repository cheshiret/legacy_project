package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: P
 * 1. Verify interest in drop down list default and all options
 * 2. Verify interest in drop down list is under Where field
 * @Preconditions:
 * @SPEC: 'Interested in' - drop down mode (UI) [TC:042956] 
 * @Task#: AUTO-1234 
 * 
 * @author SWang
 * @Date 9/14/2012
 */
public class InterestInDropDownModeUI extends RecgovTestCase {
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private String[] interestInOptions;

	public void execute() {
		web.invokeURL(url);

		//Verify interest in drop down list default and all option values
		List<String> interestInOptionsFromUI = searchPanel.getAllInterestIns();
		searchPanel.verifyDropDownListValues(interestInOptionsFromUI, interestInOptions, interestInOptionsFromUI.get(0), interestInOptions[0]);

		//Verify interest in location
		this.verifyInterestInUnderWhere();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		interestInOptions = new String[]{UwpUnifiedSearch.DEFAULT_INSTERETED_IN, UwpUnifiedSearch.EVERYTHING_INSTERETED_IN, UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN, UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN, UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN, UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN, UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN};
	}

	private void verifyInterestInUnderWhere(){
		String criteriaSectionValue = searchPanel.getUnifSearchCriteriaText();
		if(!criteriaSectionValue.matches("Where\\?\\s+Interested ?in.*")){
			throw new ErrorOnPageException("Interested in drop down list isn't under Where field.");
		}
		logger.info("Successfully verify Interested in drop down list is under Where field.");
	}
}


