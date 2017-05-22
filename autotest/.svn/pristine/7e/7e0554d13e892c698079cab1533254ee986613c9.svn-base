package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:(P) 
		1. Enter sample data in 'Where' field and click 'Search';
		2. Check the header for state on Suggestion page;
		3. Check the state showing on Suggestion page;
		4. Check the order if multiple results returned on suggestion page;
		5. Click on the result;

		Expected Result:
		-Header for state on suggestion page - Within State
		-State showing on suggestion page - Full name of the state & it's a link
		-Sorted alphabetically when multiple results returned
		-It will run a state search when click on the results

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 13, 2012
 */
public class UI_Suggestion extends RecgovTestCase {
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String[]> stateFullNames = new ArrayList<String[]>();
	private String searchResultHeader;

	public void execute(){
		web.invokeURL(url);
		this.checkStateHeadingFullNameAndSorting();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");

		searchData.whereTextValues = new String[]{"nebr", "sout", "north", "ohio", "miss"};
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		stateFullNames.add(new String[]{"NEBRASKA"});
		stateFullNames.add(new String[]{"SOUTH CAROLINA", "SOUTH DAKOTA"});
		stateFullNames.add(new String[]{"NORTH CAROLINA", "NORTH DAKOTA"});
		stateFullNames.add(new String[]{"OHIO"});
		stateFullNames.add(new String[]{"MISSISSIPPI", "MISSOURI"});
	}

	/**
	 * Verify State heading, full name, sorting in suggestion page
	 * Verify search result header in view as list page
	 */
	private void checkStateHeadingFullNameAndSorting(){
		UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();

		logger.info("Start to verify State heading, full name, sorting in suggestion page " +
		"and search result header in view as list page.");

		for(int i=0; i<searchData.whereTextValues.length; i++){
			searchData.whereTextValue = searchData.whereTextValues[i];
			web.makeUnifiedSearch(searchData);

			//Check state heading: Within State
			String stateSuggestionContent =  suggestionPg.getStatesSuggestionsListContent();
			if(!stateSuggestionContent.startsWith("Within State")){
				throw new ErrorOnDataException("State heading info is incorrect in suggestion page.");
			}

			//Check full name of the state
			String[] stateSuggestions = suggestionPg.getStatesSuggestions();
			if(stateSuggestions.length!=stateFullNames.get(i).length){
				throw new ErrorOnDataException("Expected state name size doesn't equal to the actual.", 
						String.valueOf(stateFullNames.get(i)), String.valueOf(stateSuggestions.length));
			}
			for(int j=0; j<stateSuggestions.length; j++){
				if(!stateSuggestions[j].equals(stateFullNames.get(i)[j])){
					throw new ErrorOnDataException("The state full name is wrong!", stateFullNames.get(i)[j],stateSuggestions[j]);
				}
			}

			//Sorted alphabetically
			suggestionPg.verifySortByAlphabetical(stateSuggestions);

			//Click on the result
			suggestionPg.clickFirstStateSuggestion();
			viewAsList.waitLoading();
			searchResultHeader = "Results within "+stateFullNames.get(i)[0];
			viewAsList.verifyResultNearHeaderText(searchResultHeader);
		}
	}
}
