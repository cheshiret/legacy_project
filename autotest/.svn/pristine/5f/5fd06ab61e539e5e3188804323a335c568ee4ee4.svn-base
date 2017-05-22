/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.firstletterofname;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the UI and the filtered results when filter search result by first letter of name: 
 * where - Indiana; Interested in - Everything
 * @Preconditions:
 * 1. Make sure the facility "2011 National Christmas Tree Lighting Ceremony" has been set up by support script, so that
 * the letter filter "#" shows up when search with 'Washington D.C.'.
 * @SPEC: 
 * First letter of name filter - UI [TC:043217]
 * First letter of name filter - verify the filtered results [TC:043205]
 * @Task#: Auto-1232
 * 
 * @author Lesley Wang
 * @Date  Oct 10, 2012
 */
public class UIAndFilteredResultsCheck extends RecgovTestCase {
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String[] filterCategory;
	private String resultFiltersContent;
	private List<String> optionsNames;
	private Map<String, Integer> optionsNamesAndNums;
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);
		resultFiltersContent =  viewAsListPg.getResultFiltersContent();
		optionsNames = viewAsListPg.getFilterNamesFromUI(filterCategory[3]);
		optionsNamesAndNums = viewAsListPg.getFiltersNamesAndNums(filterCategory[3]);
		
		// Checkpoint 1 - verify heading and filter options
		viewAsListPg.verifyFilterStructure(filterCategory);
		this.verifyFirstLetterFilterOptions();
		
		// Checkpoint 2 - check the total count from first letter filter options are equal to the total search results summary on the header
		int totalCount = this.getFirstLetterFilterOptionsTotalNums();
		viewAsListPg.verifySearchResultLabelEquals(viewAsListPg.generateSearchResultLabel(totalCount));

		// Checkpoint 3 - click each option and check the option text, other options display, and result count
		this.filterSearchByEachFirstLetter();
		
		// Checkpoint 4 - check the results after click X next to the filter title
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);
		
		// Checkpoint 5: search by where - Washington D.C; Interested in - Everything, and check the letter filter "#" and its filtered result
		searchData.whereTextValue = "DISTRICT OF COLUMBIA";//"Washington D.C.";
		this.gotoViewAsListPage(searchData);
		resultFiltersContent =  viewAsListPg.getResultFiltersContent();
		viewAsListPg.filterResults("", "", "#");
		this.verifyFilteredResults("#");
		
		// Checkpoint 6 - click X next to Find your results label and check the results
		viewAsListPg.clickClearAllFilters();
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);	
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "Indiana";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		filterCategory = new String[]{UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, 
				UwpUnifiedSearch.FILTERCATEGORY_TYPE, 
				UwpUnifiedSearch.FILTERCATEGORY_AGENCY,
				UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME
		};
	}

	/**
	 * Verify facility type filter options' sorting
	 */
	private void verifyFirstLetterFilterOptions() {
		boolean  result = true;
		int ascii = 0;
		for (String name : optionsNames) {
			// verify the option name is # or the letter from A to Z
			result &= MiscFunctions.compareResult(name + " letter option name matching (# or A-Z)", true, 
					name.matches("#|[A-Z]"));
			// verify the option link by its title
			result &= MiscFunctions.compareResult(name + " letter option link existence", true, 
					viewAsListPg.isFirstLetterOfNameFilterOptionExist(name));
			// verify the option links sorting, starting from # if it exists, then A to Z
			if (ascii >= name.charAt(0)) {
				logger.error(name + " letter sort is wrong! It should be after " + (char)ascii);
				result &= false;
			} else {
				logger.info(name + " letter sort is correct! it is after " + (char)ascii);
				result &= true;
				ascii = name.charAt(0);
			}
		}
		if (!result) {
			throw new ErrorOnPageException("First Letting of name filter options are wrong! Please check the logger error!");
		}
		logger.info("First Letting of name filter options are correct!");
	}

	/**
	 * Get first letter of name filter options total result count
	 * @return
	 */
	private int getFirstLetterFilterOptionsTotalNums() {
		int num = 0;
		for(Object o:optionsNamesAndNums.keySet()){
			num += optionsNamesAndNums.get(o);
		}
		return num;
	}
	
	/**
	 * Click each filter option link, then check all other filter options, the selected filter option, the summary of the filtered results 
	 */
	private void filterSearchByEachFirstLetter(){
		String searchResultLabel = "";
		String name; 
		int num;
		List<String> testOpts = new ArrayList<String>();
		testOpts.add(optionsNames.get(0));
		testOpts.add(optionsNames.get(optionsNames.size()-1));
		int index = optionsNames.indexOf("#");
		if (index > 0 && index < optionsNames.size()-1) {
			testOpts.add("#");
		}
//		for(int i = 0; i < optionsNames.size(); i++){
		for(int i = 0; i < testOpts.size(); i++){
			name = testOpts.get(i);
			num = optionsNamesAndNums.get(name).intValue();
			searchResultLabel = viewAsListPg.generateSearchResultLabel(num);
			viewAsListPg.filterResults("", "", name);
			// verify all filter options links don't exist
			viewAsListPg.verifyFirstLetterFilterOptionsExist(optionsNames, false);
			// verify the selected filter option displayed as plain text
			viewAsListPg.verifySelectedFirstLetterOfNameFilterOptionValue(name);
			// verify the result count from the filter option equal to the total count of search results on the header
			viewAsListPg.verifySearchResultLabelEquals(searchResultLabel);
			// verify the filtered facilities' names start with the selected letter
			this.verifyFilteredResults(name);
			
			logger.info("Successfully verify the selected first letter of name filter option:" + name);

			viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		}
	}
	
	/** 
	 * Verify the filtered facilities' names start with the selected letter 
	 */
	private void verifyFilteredResults(String name) {
		List<String> filteredParksFromUI = viewAsListPg.getAllParkNames();
		boolean result = true;
		boolean actual;
		for (String filteredFacility : filteredParksFromUI) {
			if (name.equals("#")) {
				actual = filteredFacility.matches("^[^a-zA-Z].*");	
			} else {
				actual = filteredFacility.startsWith(name);
			}
			result &= MiscFunctions.compareResult("The filtered facility " + filteredFacility + " starting with " + name, true, actual);	
		}
		if (!result) {
			throw new ErrorOnPageException("The filtered results are wrong! Please check logger error!");
		}
		
		logger.info("Successfully verify the selected first letter of name filter results:" + name);
	}
}
