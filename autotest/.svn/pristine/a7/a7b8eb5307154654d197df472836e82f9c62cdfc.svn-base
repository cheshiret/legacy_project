package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P RFT)
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
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String[] filterNames;
	private List<String> clickableNameFilters;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);

		//Get all clickable filter letters
		clickableNameFilters = viewAsMap.getAllClickableSearchNameFiltersText();

		//Verify no facility starting with that un-clickable letter
		this.verifyNoFacilityStartingWithUnclickableLetter();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "NEW YORK";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = "Everything";

		searchData.contractCode = "NRSO";

		filterNames = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	}

	/**
	 * Get park IDs which haven't the pin "No Map" from "View As List" page
	 */
	private List<String> getFirstPgParkIDsHasMap(){
		List<String> returnFirstPgParkIDs = new ArrayList<String>();
		logger.info("Start to get first page park IDs which haven't the pin 'No Map' from 'View As List' page.");

		List<String> firstPgParkPinTitles = viewAsList.getFirstPgPinTitles();
		List<String> firstPgParkIDs = viewAsList.getFirstPgParkIDs();

		for(int i=0; i<firstPgParkPinTitles.size(); i++){
			if(firstPgParkPinTitles.get(i).equals("View on Map")){
				returnFirstPgParkIDs.add(firstPgParkIDs.get(i));
			}else{
				logger.info("Park pin title is 'Not on Map' for park with id:"+firstPgParkIDs.get(i));
			}
		}

		return returnFirstPgParkIDs;
	}

	/**
	 * Get park IDs which haven't the pin "No Map" from "View As List" page
	 * Start page: View As Map page
	 * End page: View As Map page
	 * @return
	 */
	private List<String> gotoViewAsListToGetParkIDsHasMap(){
		logger.info("Start to get first page park pin text which haven't the pin 'No Map' from 'View As List' page.");
		viewAsMap.clickViewAsList();
		viewAsList.waitLoading();

		List<String> firstPgParkIDsHasMap = this.getFirstPgParkIDsHasMap();

		viewAsList.clickViewAsMap();
		viewAsMap.waitLoading();

		return firstPgParkIDsHasMap;
	}

	private void verifyNoFacilityNameStartsWithUnclickableNameFilter(String parkName, String unclickableNameFilter){
		logger.info("Start to verify no facility starting with the unClickable letter:"+unclickableNameFilter);
		if(parkName.startsWith(unclickableNameFilter)){
			throw new ErrorOnDataException("Park:"+parkName+" starts with the unclickable facility name filter:"+unclickableNameFilter);
		}else{
			logger.info("Successfully verify Park:"+parkName+" doesn't start with the unclickable facility name filter:"+unclickableNameFilter);
		}
	}

	/**
	 * Verify no facility starting with that un-clickable letter
	 */
	private void verifyNoFacilityStartingWithUnclickableLetter(){
		List<String> nameFromMap=new ArrayList<String>();

		//Get all unclickable facility name filters
		List<String> unclickableNameFilters = new ArrayList<String>();
		for(int i=0; i<filterNames.length; i++){
			if(!clickableNameFilters.contains(filterNames[i])){
				unclickableNameFilters.add(filterNames[i]);
			}
		}

		//get all park name from map;
		for(int j=0; j<clickableNameFilters.size(); j++){
			logger.info("Click clickable facility name filters:"+clickableNameFilters.get(j));
			viewAsMap.clickSearchNameFilter(clickableNameFilters.get(j));
			viewAsMap.waitLoading();

			logger.info("Get first page park IDs which have map");
			List<String> parkIDsHasMap = this.gotoViewAsListToGetParkIDsHasMap();
			for(int m=0; m<parkIDsHasMap.size(); m++){
				viewAsMap.clickMapPin(searchData.contractCode, parkIDsHasMap.get(m));
				viewAsMap.mapBubbleWidgetWaitExists();
				
				logger.info("Get the park name with ID:"+parkIDsHasMap.get(m));
				nameFromMap.add(viewAsMap.getParkNameFromBubbleWidget());
				viewAsMap.closeMapBubbleWidget();
			}
		}
		
		//Verify park name not start with unclickable name filter;
		for(String parkName:nameFromMap){
			for(int n=0; n<unclickableNameFilters.size(); n++){
				this.verifyNoFacilityNameStartsWithUnclickableNameFilter(parkName, unclickableNameFilters.get(n));
			}
		}
	}
}
