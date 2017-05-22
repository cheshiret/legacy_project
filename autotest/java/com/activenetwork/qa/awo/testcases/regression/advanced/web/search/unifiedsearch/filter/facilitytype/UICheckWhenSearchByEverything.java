/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.facilitytype;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the UI when filter search result by facility type: where - California; Interested in - Everything
 * @Preconditions:
 * @SPEC: 
 * Facility type filter - UI [TC:043201]
 * @Task#: Auto-1231
 * 
 * @author Lesley Wang
 * @Date  Oct 8, 2012
 */
public class UICheckWhenSearchByEverything extends RecgovTestCase {
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String[] filterCategory, typeOptionsTitles, typeOptionsNames;
	private String resultFiltersContent;
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);
		resultFiltersContent =  viewAsListPg.getResultFiltersContent();
		
		// Checkpoint 1 - verify heading and type options
		viewAsListPg.verifyFilterStructure(filterCategory);
		this.verifyFacilityTypeFilterOptions();
		
		// Checkpoint 2 - check the total count from facility type filter options are equal to the total search results summary on the header
		int totalCount = viewAsListPg.getFacilityTypeFiltersResultTotalNum(typeOptionsTitles);
		viewAsListPg.verifySearchResultLabelEquals(viewAsListPg.generateSearchResultLabel(totalCount));

		// Checkpoint 3 - click each option and check the option text, other options display, and result count
		this.filterSearchByEachFacilityType();
		
		// Checkpoint 4 - check the results after click X next to the filter title
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);
		
		// Checkpoint 5 - click X next to Find your results label and check the results
		viewAsListPg.filterResults(typeOptionsTitles[0], "", "");
		viewAsListPg.clickClearAllFilters();
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);	
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "California";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		filterCategory = new String[]{UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, 
				UwpUnifiedSearch.FILTERCATEGORY_TYPE, 
				UwpUnifiedSearch.FILTERCATEGORY_AGENCY,
				UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME
		};
		typeOptionsTitles = new String[] { UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, 
				UwpUnifiedSearch.FILTER_PERMITANDWILTERNESS, 
				UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN, 
				UwpUnifiedSearch.FILTER_OTHERACTIVITIES_LINKTITLE
		};
		typeOptionsNames = new String[] { UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_NAME, 
				UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN, 
				UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN,
				UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN	
		};
	}

	/**
	 * Verify facility type filter options' text and tooltips
	 */
	private void verifyFacilityTypeFilterOptions() {
		boolean  result = true;
		for (int i = 0; i < typeOptionsTitles.length; i++) {
			result &= MiscFunctions.compareResult(typeOptionsTitles[i] + " filter type name existence", true, 
					viewAsListPg.isFacilityTypeFilterOptionExist(typeOptionsTitles[i]));
			result &= MiscFunctions.compareResult(typeOptionsTitles[i] + " filter type name", typeOptionsNames[i], 
					viewAsListPg.getFacilityTypeFilterOptionName(typeOptionsTitles[i]));
		}
		if (!result) {
			throw new ErrorOnPageException("Facility type filter options are wrong! Please check the logger error!");
		}
		logger.info("Facility type filter options are correct!");
	}

	/**
	 * Click each filter option link, then check all other filter options, the selected filter option, the summary of the filtered results 
	 */
	private void filterSearchByEachFacilityType(){
		String searchResultLabel = "";
		for(int i = 0; i < typeOptionsTitles.length; i++){
			searchResultLabel = viewAsListPg.generateSearchResultLabel(
					viewAsListPg.getFacilityTypeFilterOptionResultNum(typeOptionsTitles[i]));
			viewAsListPg.filterResults(typeOptionsTitles[i], "", "");
			// verify all filter options links don't exist
			boolean[] linkExist = new boolean[] {false, false, false, false};
			linkExist[i] = true;
			viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, linkExist);
//			viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, false); // Lesley[20140225] update due to isFacilityTypeFilterOptionExist method changed
			// verify the selected filter option displayed as plain text
			viewAsListPg.verifySelectedFacilityTypeFilterName(typeOptionsNames[i]);
			// verify the result count from the filter option equal to the total count of search results on the header
			viewAsListPg.verifySearchResultLabelEquals(searchResultLabel);
			logger.info("Successfully verify the selected facility type filter option:"+typeOptionsTitles[i]);

			viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		}
	}
}
