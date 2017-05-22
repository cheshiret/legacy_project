package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:(P) "Day Use & Picnic Areas" && "Camping & lodging" + "Day use & Picnic areas"
		1. Enter sample data in 'Where' field, pick up a state from autocomplete list, select 'Day Use & Picnic Areas' from 'Interested in' drop down list, click 'Search';
		2. Open another window, pick up the same state, select 'Camping & lodging' then 'Day Use & Picnic Areas' in  'Looking for' drop down list, click Search';
		3. Compare the search results.

		Expected Result: The results should be the same.

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 12, 2012
 */
public class FacilitySummary_DayUse extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private int searchResultNumFromDayUse, searchResultNumFromCampDayUse;
	private List<String> facilityNamesFromDayUse = new ArrayList<String>();
	private List<String> facilityNamesFromCampDayUse = new ArrayList<String>();

	public void execute(){
		web.invokeURL(url);

		//Make unified search using: "Day use & Picnic areas"
		searchResultNumFromDayUse = this.getTotalSearchResultNum();
		facilityNamesFromDayUse = this.getAllFacilityNames();

		//Make unified search using: "Camping & lodging" + "Day use & Picnic areas"
		searchData.interestInValue = "Camping & Lodging";
		searchData.lookFor = "Day use & Picnic areas";
		searchResultNumFromCampDayUse = this.getTotalSearchResultNum();
		facilityNamesFromCampDayUse = this.getAllFacilityNames();

		//Compare the search result number and facility names between Day use and Camping day use
		this.compareSearchResultBetweenCampDayUseAndDayUse();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");

		searchData.whereTextValue = "PENNSYLVANIA";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = "Day use & Picnic areas";
	}

	private int getTotalSearchResultNum(){
		logger.info("Get total search result number.");
//		web.makeUnifiedSearch(searchData);
		this.gotoViewAsListPage(searchData);
		return viewAsList.getTotalSearchResultNum();
	}

	private List<String> getAllFacilityNames(){
		List<String> facilityNames = viewAsList.getAllParkNames();
		viewAsList.gotoFirstPage();

		return facilityNames;
	}

	/**
	 * Compare the search result number and facility names between Camping day use and Day use
	 */
	private void compareSearchResultBetweenCampDayUseAndDayUse(){
		logger.info("Compare the search result number and facility names between Camping day use and Day use.");


		//Compare the search result number and park names number
		if(searchResultNumFromDayUse!=searchResultNumFromCampDayUse){
			throw new ErrorOnDataException("The search result number is different between camping day use and day use", String.valueOf(searchResultNumFromDayUse), String.valueOf(searchResultNumFromCampDayUse));
		}
		if(facilityNamesFromDayUse.size()!=facilityNamesFromCampDayUse.size()){
			throw new ErrorOnDataException("The size of park names is different between camping day use and day use", String.valueOf(facilityNamesFromDayUse.size()), String.valueOf(facilityNamesFromCampDayUse.size()));
		}

		//Compare the facility names
		for(int i=0; i<facilityNamesFromDayUse.size(); i++){
			if(!facilityNamesFromDayUse.get(i).equals(facilityNamesFromCampDayUse.get(i))){
				throw new ErrorOnDataException("Facility name is incorrect.", facilityNamesFromDayUse.get(i),facilityNamesFromCampDayUse.get(i));
			}
		}
	}
}
