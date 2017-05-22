package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.otheractivities;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: (RFT)
 * 1. Check Suggestion or Result page existed
 * 2. Check Facility and Address suggestions in Suggestion page
 * Or Check Facility Names in Result page
 * 3. Check Unified Search Panel
 * Conditions:
 * 1. Set Where->Click Search->Select Other activities->check some options->Click Clear Search
 * 2. Don't close browser and click Clear Search
 * 3. Close browser and don't click Clear Search
 * @Preconditions:
 * @SPEC:
 * UWP Unified Search_FacilitySearch_UC (Search Form - Expansion of 'Other Activities')
 * UWP Unified Search_Search Form_UI (Search Form - Expansion of 'Other Activities')
 * @Task#: AUTO-766, AUTO-2176(View case due to DEFECT-62863)
 * 
 * @author SWang
 * @Date  Oct 14, 2011, May 19, 2014
 */
public class ClearSearchAssociated extends RecgovTestCase{
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpUnifiedSearchSuggestionPage searchSuggestion = UwpUnifiedSearchSuggestionPage.getInstance();
	private UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();
	private UwpUnifiedSearch otherActivities = new UwpUnifiedSearch();
	private String[] expectedFacilitySuggestions = new String[]{};
	private String[] expectedAddressSuggestions = new String[]{};
	private List<String> expectedFacilityResults = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);

		//->Set Where->Click Search
		otherActivities.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		this.checkExpectedPageExist();
		//->Select Other activities->check some options->Click Clear Search
		this.checkUIWithClearSearch();

		//->Set Where->Select auto-completed->Click Search
		otherActivities.whereTextValue = "inde";
		otherActivities.selectAutoCompleteOption = true;
		otherActivities.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		otherActivities.otherActivitiesName = new String[]{""};
		otherActivities.clickSearch = true;
		otherActivities.clickClearSearch = false;
		this.checkExpectedPageExist();
		//->Select Other activities->check some options->Click Clear Search
		this.checkUIWithClearSearch();
	}

	public void wrapParameters(Object[] param) {
		otherActivities.whereTextValue = "inde";
	}

	/**Check UI after click Clear Search*/
	private void checkUIWithClearSearch(){
		UwpUnifiedSearchHelpInfoPage helpPage = UwpUnifiedSearchHelpInfoPage.getInstance();

		otherActivities.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		otherActivities.otherActivitiesName = new String[]{"Auto Touring"};
		otherActivities.clickSearch = false;
		otherActivities.clickClearSearch = true;
		web.makeUnifiedSearch(otherActivities);

		//Verify UI after click Clear Search 
		otherActivities.whereTextValue = "";
		otherActivities.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		searchPanel.verifyInterestInitializedUI(otherActivities.interestInValue);

		//Check Suggestion and Result page display
		if(!otherActivities.selectAutoCompleteOption){
//			searchSuggestion.verifyFacilitySuggestions(expectedFacilitySuggestions); Sara[20140717] Per Lisha, we will drop suggestion page for July 2014 Release
//			searchSuggestion.verifyAddressSuggestions(expectedAddressSuggestions);
		}else{
			//searchResult.verifyParkList(expectedFacilityResults);
			this.verifyParkList(expectedFacilityResults);
			if (searchResult.checkNext()) {
				searchResult.clickNext();
				helpPage.waitLoading();
			}
		}
	}

	private void checkExpectedPageExist(){
		Object page = web.makeUnifiedSearch(otherActivities);

		//Go to view as list page if select option from autoCompleted box
		if(otherActivities.selectAutoCompleteOption){
			if(page!=searchResult){
				throw new ErrorOnDataException("Unifies Search Result Page should be existed.");
			}else expectedFacilityResults = searchResult.getFirstPgParkNames();
			
			//Will stay in Suggestion page if doesn't select option from autoCompleted box
		}else{
//			if(page!=searchSuggestion){ Sara[20140717] Per Lisha, we will drop suggestion page for July 2014 Release
//				throw new ErrorOnDataException("Unified Search Suggestion Page should be existed.");
//			}else {
//				expectedFacilitySuggestions = searchSuggestion.getFacilitySuggestions();
//				expectedAddressSuggestions = searchSuggestion.getAddressSuggestions();
//			}
		}
	}

	private void verifyParkList(List<String> parkList){
		List<String> facilityListViaUi = searchResult.getFirstPgParkNames();
		if(facilityListViaUi.toString().equals(parkList.toString())){
			logger.info("Successfully verify all parks in first page.");
		}else throw new ErrorOnPageException("Failed to verify all parks in first page.");
	}
}
