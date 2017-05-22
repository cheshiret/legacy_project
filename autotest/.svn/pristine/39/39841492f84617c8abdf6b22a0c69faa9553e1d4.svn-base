package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.ra.RAParkListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:(P) State + 'Day use & Picnic areas' + 'more options...'
		1. Do a state seacrh at rec.gov with date information entered;
		2. Open another window do a state search at ra.com with the same criteria and date information, click on 'federal(xx)' filter;
		3. Compare the results returned at rec.gov and ra.com

		Expected Result: The results should be the same at rec.gov and ra.com

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 12, 2012
 */
public class AvailabilityCompareWithRA_DayUse extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private BookingData bd;
	private String raURL;
	private int searchResultNumFromRA, searchResultNumFromREC;
	private List<String> facilityNamesFromRA = new ArrayList<String>();
	private List<String> facilityNamesFromREC = new ArrayList<String>();

	public void execute(){
		//Go to RA to get the state search result
		web.invokeURL(raURL);
		this.getStateSearchResultFromRA();

		//Go to REC.GOV to get the state search result
		web.invokeURL(url);
		web.makeUnifiedSearch(searchData);
		searchResultNumFromREC = viewAsList.getTotalSearchResultNum();
		facilityNamesFromREC = viewAsList.getAllParkNames();

		//Compare the search result number and facility names between RA and REC
		this.compareSearchResultBetweenRAAndREC();
	}

	public void wrapParameters(Object[] param) {
		//RA parameters
		raURL = TestProperty.getProperty(env + ".web.ra.url");
		bd = new BookingData();
		bd.siteType = "Day use";
		bd.state = "Mississippi";
		bd.conType = "federal";
		bd.spotWith = true;
		bd.spotWithElectricHookup = "30 Amp (or more)";
		bd.arrivalDate = DateFunctions.getDateAfterToday(0);
		bd.lengthOfStay = "2";

		//REC parameters
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = bd.state.toUpperCase();
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = "Day use & Picnic areas";
		searchData.moreOptions = true;
		searchData.electricHookupValue = bd.spotWithElectricHookup;
		searchData.arrivalDate = bd.arrivalDate;
		searchData.lengthOfStay = bd.lengthOfStay;
	}

	private void getStateSearchResultFromRA(){
		RAParkListPage parkList = RAParkListPage.getInstance();
		logger.info("Get state federal search result number and all park names from RA.");
		web.gotoParkListPage(bd);
		parkList.clickFederalLink();
		parkList.waitLoading();

		facilityNamesFromRA = parkList.getAllParkName();
		searchResultNumFromRA = parkList.getNumOfSearchTypeFilter(bd.conType);
	}

	/**
	 * Compare the search result number and facility names between RA and REC
	 */
	private void compareSearchResultBetweenRAAndREC(){
		logger.info("Compare the search result number and facility names between RA and REC");

		//Compare the search result number and park names number
		if(searchResultNumFromREC!=searchResultNumFromRA){
			throw new ErrorOnDataException("The search result number is different between REC and RA", String.valueOf(searchResultNumFromRA), String.valueOf(searchResultNumFromREC));
		}
		if(facilityNamesFromREC.size()!=facilityNamesFromRA.size()/2){
			throw new ErrorOnDataException("The size of park names is different between REC and RA", String.valueOf(facilityNamesFromRA.size()), String.valueOf(facilityNamesFromREC.size()));
		}

		//Compare the facility names
		for(int i=0; i<facilityNamesFromREC.size(); i++){
			if(!facilityNamesFromRA.toString().contains(facilityNamesFromREC.get(i))){
				throw new ErrorOnDataException("Facility name is incorrect.", facilityNamesFromRA.get(i),facilityNamesFromREC.get(i));
			}
		}
	}
}
