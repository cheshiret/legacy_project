/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.firstletterofname;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the display of the first letter of name filter in Interactive Mode.
 * Interactive mode: The filter shall display irrespective of the type of search that was performed. 
 * @Preconditions: 
 * 1. Make sure the setting for the filter in ui-option.xml file is correct
 * file location: /opt/sites/qa1/uwprec/docs/jsp/layout/ui-option.xml
 * setting: <option visible="true" name="letter-filters" value="interactive"/>  
 * @SPEC: 
 * First letter of name filter (interactive mode) - irrespective of the type of search that was performed. [TC:043206]
 * @Task#: Auto-1232
 * 
 * @author Lesley Wang
 * @Date  Oct 10, 2012
 */
public class DisplayCheckInInteractiveMode extends RecgovTestCase {

	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String[] filterCategory;
	
	public void execute() {
		MiscFunctions.verifyFiltersSetupInUiOption();
		web.invokeURL(url);
		
		// Checkpoint 1 - search by where - cali; Interested In - Other Activities, and check the first letter of name filter display
		this.searchAndCheckFirstLetterFilterDisplay("cali", UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN);
		
		// Checkpoint 2 - search by where - Sandy Lake; Interested In - Camping & Day Use, and check the first letter of name filter display
		this.searchAndCheckFirstLetterFilterDisplay("Sandy Lake", UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN);
		
		// Checkpoint 3 - search by where - Yosemite National Park; Interested In - Everything, and check the first letter of name filter display
		this.searchAndCheckFirstLetterFilterDisplay("Yosemite National Park", UwpUnifiedSearch.EVERYTHING_INSTERETED_IN);
		
		// Checkpoint 4 - search by where - DURHAM, ME, US; Interested In - Tours & Tickets, and check the first letter of name filter display
		this.searchAndCheckFirstLetterFilterDisplay("DURHAM, ME, US", UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		filterCategory = new String[]{UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, 
				UwpUnifiedSearch.FILTERCATEGORY_TYPE, 
				UwpUnifiedSearch.FILTERCATEGORY_AGENCY,
				UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME
		};
	}
	
	/** Search with the given criteria, then check the letter filter displayed below the agency filter */
	private void searchAndCheckFirstLetterFilterDisplay(String where, String interestIn) {
		searchData.whereTextValue = where;
		searchData.interestInValue = interestIn;
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyFilterStructure(filterCategory);
	}
}
