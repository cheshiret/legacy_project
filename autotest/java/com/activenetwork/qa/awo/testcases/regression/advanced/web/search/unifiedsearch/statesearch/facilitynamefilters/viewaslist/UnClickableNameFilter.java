package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewaslist;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
		Check all the un-clickable letter in facility name filter;

		Expected Result:
	    Verify there is no facility starting with that un-clickable letter;

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class UnClickableNameFilter extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String[] filterNames;
	private List<String> clickableNameFilters, allParkNames;

	public void execute(){
		web.invokeURL(url);
//		web.makeUnifiedSearch(searchData);
		this.gotoViewAsListPage(searchData);

		//Get all facility Names
		allParkNames = viewAsList.getAllParkNames();
		viewAsList.gotoFirstPage();

		//Get all clickable filter letters
		clickableNameFilters = viewAsList.getAllClickableSearchNameFiltersText();

		//Verify no facility starting with that un-clickable letter
		this.verifyNoFacilityStartingWithUnclickableLetter();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "CONNECTICUT";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue =UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		filterNames = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	}

	/**
	 * Verify no facility starting with that un-clickable letter
	 */
	private void verifyNoFacilityStartingWithUnclickableLetter(){
		for(int i=0; i<filterNames.length; i++){
			if(!clickableNameFilters.contains(filterNames[i])){

				logger.info("Start to verify no facility starting with the unClickable letter-"+filterNames[i]);
				for(int j=0; j<allParkNames.size(); j++){
					if(allParkNames.get(j).startsWith(filterNames[i])){
						throw new ErrorOnDataException("It is failed to verify no facility start with the unClickable letter:"+filterNames[i],allParkNames.get(j), filterNames[i]);
					}else{
						logger.info("Successfully verify no facility start with unclickable letter:"+filterNames[i]);
					}
				}
			}
		}
	}
}

