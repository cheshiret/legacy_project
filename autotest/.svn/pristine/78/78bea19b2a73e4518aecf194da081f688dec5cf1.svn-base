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
 * @Description: Verify the facility type filter options display and counts when search by different interested in options. 
 * sample data: 
 * 1. where - California; Interested in -  'Camping & Lodging', 'Permit & Wilderness' and 'Tours & Tickets'; check the result counts
 * 2. where - Yosemite National Park; Interested in -  'Camping & Lodging'; type display - 'Camping & Day Use' and 'Other Activities'
 * 3. where - ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES; Interested in -  'Day use & Picnic areas'; type display - 'Camping & Day Use' and 'Tours & Tickets'
 * 4. where - MEADOW CREEK CABIN ; Interested in -  'Permits & Wilderness'; type display - 'Camping & Day Use' and 'Permits & Wilderness'
 * 5. where - INYO NATIONAL FOREST - WILDERNESS PERMITS ; Interested in -  'Tours & Tickets'; type display - 'Permits & Wilderness' and 'Tours & Tickets'
 * 6. where - BLANCHARD SPRINGS CAVERNS; Interested in -  'Other Activities'; no activities selected; type display - 'Camping & Day Use' and 'Tours & Tickets' and 'Other Activities'
 * 7. where - Sandy Lake ; Interested in -  'Other Activities'; 'Biking' selected; type display - 'Camping & Day Use' and 'Other Activities'
 * @Preconditions:
 * @SPEC: 
 * Facility type filter - verify the filtered results [TC:043203]
 * Facility type filter - Displays regardless of the 'Interested in' choice [TC:043202]
 * @Task#: Auto-1231
 * 
 * @author Lesley Wang
 * @Date  Oct 9, 2012
 */
public class UICheckWhenSearchByDiffInterestedIn extends RecgovTestCase {

	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String[] interestedInOptions, typeOptionsTitles;
	
	public void execute() {
		web.invokeURL(url);
		
		// Checkpoint 1 - Search by 'Camping & Lodging', 'Permit & Wilderness' and 'Tours & Tickets', and by Everything, then compare the count for each type filter option
		String[] searchResultLabels = this.getSearchResultLabelsWhenSearchByDiffInterestedIn(interestedInOptions);
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		this.gotoViewAsListPage(searchData);
		int[] searchResultNums = viewAsListPg.getFacilityTypeFilterResultNums(typeOptionsTitles);
		this.verifySearchResultNumbers(searchResultLabels, searchResultNums);
		
		// Checkpoint 2 - search by camping & lodging, and check the displayed type filter options
		searchData.whereTextValue = searchData.whereTextValues[0];
		searchData.interestInValue = interestedInOptions[0];
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, new boolean[] {true, false, false, true});
		
		// Checkpoint 3 - search by 'Day use & Picnic areas', and check the displayed type filter options
		searchData.whereTextValue = searchData.whereTextValues[1];
		searchData.interestInValue = UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN;
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, new boolean[] {true, false, true, false});
		
		// Checkpoint 4 - search by 'Permits & Wilderness', and check the displayed type filter options
		searchData.whereTextValue = searchData.whereTextValues[2];
		searchData.interestInValue = interestedInOptions[1];
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, new boolean[] {true, true, true, true});
		
		// Checkpoint 5 - search by 'Tours & Tickets', and check the displayed type filter options
		searchData.whereTextValue = searchData.whereTextValues[3];
		searchData.interestInValue = interestedInOptions[2];
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, new boolean[] {false, true, true, false});
		
		// Checkpoint 6 - search by 'Other Activities', no activities selected and check the displayed type filter options
		searchData.whereTextValue = searchData.whereTextValues[4];
		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, new boolean[] {true, false, true, true});

		// Checkpoint 7 - search by 'Other Activities', select 'Biking' and check the displayed type filter options
		searchData.whereTextValue = searchData.whereTextValues[5];
		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		searchData.otherActivitiesName = new String[] {UwpUnifiedSearch.EXPLORE_BIKING};
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFacilityTypeFilterOptionsExist(typeOptionsTitles, new boolean[] {true, false, false, false});
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "MINNESOTA";
		interestedInOptions = new String[] { UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN, 
				UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN, 
				UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN
		};
		typeOptionsTitles = new String[] { UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, 
				UwpUnifiedSearch.FILTER_PERMITANDWILTERNESS, 
				UwpUnifiedSearch.FILTER_TOURSANDTICKETS, 
				UwpUnifiedSearch.FILTER_OTHERACTIVITIES_LINKTITLE
		};
		searchData.whereTextValues = new String[] { "Yosemite National Park", "ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES",
				"MEADOW CREEK CABIN", "INYO NATIONAL FOREST - WILDERNESS PERMITS", 
				"BLANCHARD SPRINGS CAVERNS", "Sandy Lake"
		};
	}
	
	/**
	 * Get Search Result Labels when search by 'Camping & Lodging', 'Permit & Wilderness' and 'Tours & Tickets'
	 */
	private String[] getSearchResultLabelsWhenSearchByDiffInterestedIn (String[] interestedInOptions) {
		String[] resultLabels = new String[interestedInOptions.length];
		for (int i = 0; i < interestedInOptions.length; i++) {
			searchData.interestInValue = interestedInOptions[i];
			this.gotoViewAsListPage(searchData);
			resultLabels[i] = viewAsListPg.getSearchResultsLabel();
		}
		return resultLabels;
	}

	/**
	 * Verify the count for each type filter option matches the summary of search result by related interested in option. 
	 * Compare the numbers by the result labels. 
	 */
	private void verifySearchResultNumbers(String[] searchResultLabels, int[] searchResultNums) {
		boolean result = true;
		for (int i = 0; i < searchResultLabels.length; i++) {
			result &= MiscFunctions.compareResult(typeOptionsTitles[i] + " search result num", searchResultLabels[i], 
					viewAsListPg.generateSearchResultLabel(searchResultNums[i]));
		}
		if (!result) {
			throw new ErrorOnPageException("The count for each type filter option doesn't match the summary of search results for diff interested in options");
		}
		logger.info("The count for each type filter option matches the summary of search results for diff interested in options");
	}
}
