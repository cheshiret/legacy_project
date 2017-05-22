package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.ra.RAParkListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: (P)
		1. Enter sample data in 'Where' field, pick up a state from autocomplete list, click 'Search';
		2. Open another window, do a search at ra.com for the same state;
		3. Compare the facility summary in 'Camping & Day Use (xxx)' fielter at rec.gov and 'federal(xx)' at ra.com;

		Expected Result:
		The facility summary should be same  in 'Camping & Day Use (xxx)' fielter at rec.gov and 'federal(xx)' at ra.com;

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class AvailabilityCompareWithRA_Any extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private BookingData bd;
	private String raURL;
	private int searchResultNumFromRA, searchResultNumFromREC;
	private List<String> facilityNamesFromRA = new ArrayList<String>();
	private List<String> facilityNamesFromREC = new ArrayList<String>();

	public void execute(){
		//Go to RA to get the "federal" number and park list
		web.invokeURL(raURL);
		this.getStateFederalSearchResultFromRA();

		//Go to REC.GOV to get "Camping & Day Use" number and park list
		web.invokeURL(url);
		web.makeUnifiedSearch(searchData);
		this.getStateCampDayUseSearchResultFromREC();

		//Compare the search result number and facility names between RA and REC
		this.compareSearchResultBetweenRAAndREC();
	}

	public void wrapParameters(Object[] param) {
		//RA parameters
		raURL = TestProperty.getProperty(env + ".web.ra.url");
		bd = new BookingData();
		bd.state = "SOUTH CAROLINA";
		bd.conType = "federal";

		//REC parameters
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = bd.state;
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}

	private void getStateFederalSearchResultFromRA(){
		logger.info("Get state federal search result number and all park names from RA.");
		RAParkListPage parkList = RAParkListPage.getInstance();

		web.gotoParkListPage(bd);
		parkList.clickFederalLink();
		parkList.waitLoading();

		searchResultNumFromRA = parkList.getNumOfSearchTypeFilter(bd.conType);
		facilityNamesFromRA = parkList.getAllParkName();
	}

	private void getStateCampDayUseSearchResultFromREC(){
		logger.info("Get total search result number and all parks names from REC");
		searchResultNumFromREC = viewAsList.getTotalSearchResultNum();
		facilityNamesFromREC = viewAsList.getAllParkNames();
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
