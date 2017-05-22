package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.otheractivities;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * 1. Check error message
 * 2. Check Other Activities UI
 * 3. Check Header and Footer information
 * @Preconditions:
 * @SPEC:
 * UWP Unified Search_FacilitySearch_UC (Search Form - Expansion of 'Other Activities')
 * UWP Unified Search_Search Form_UI (Search Form - Expansion of 'Other Activities')
 * @Task#: AUTO-766
 * 
 * @author SWang
 * @Date  Oct 13, 2011
 */
public class ErrorMesValidation extends WebTestCase{
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpUnifiedSearch otherActivities = new UwpUnifiedSearch();

	public void execute() {
		web.invokeURL(url);

		//Only select "Other activities" and some options and click Search
		this.verifyErrorMes(str1);
		otherActivities.whereTextValue = UwpUnifiedSearch.DEFAULT_WHEREVALUE;
		searchPanel.verifySearchCriteria(otherActivities);

		//Set Where and select "Other activities" and some options and click Search
		otherActivities.whereTextValue = "zheq";
		this.verifyErrorMes(str2);
		searchPanel.verifySearchCriteria(otherActivities);

		//Handel in case: testCases.regression.advanced.web.search.unifiedsearch.NoMatchingResultMesValidation
//		//Set Where(select only GOOGLE result) and select "Other activities" and select "Off Highway Vehicle" check box
//		otherActivities.whereTextValue = "north pole";
//		otherActivities.selectAutoCompleteOption = true;
//		otherActivities.selectedAutoCompletedOption = "NORTH POLE, AK 99705, USA";
//		this.verifyErrorMes(str3);
//		
//		otherActivities.whereTextValue = otherActivities.selectedAutoCompletedOption;
//		searchPanel.verifySearchCriteria(otherActivities);

	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");

		//Unified Search 
		otherActivities.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		otherActivities.otherActivitiesName = new String[]{"Auto Touring","Biking"};
	}

	private void verifyErrorMes(String message){
		UwpUnifiedSearchSuggestionPage searchSuggestion = UwpUnifiedSearchSuggestionPage.getInstance();
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		Object page = web.makeUnifiedSearch(otherActivities);
		if(page == searchSuggestion){
			searchSuggestion.verifyErrorMsg(message);
		}else{
			if(page == searchResult){
				searchResult.verifyWarningMes(message);
			}
		}

	}

	private String str1 = "Where did you want to go? Enter at least 4 letters and you'll start seeing facilities and addresses that match your input.";
	private String str2 = "We are unable to find a location that matches \"zheq\". Please try a different location and search again.";
//	private String str3 = "There are no matching results based on the information you have provided. Please modify your search and try again.";
}
