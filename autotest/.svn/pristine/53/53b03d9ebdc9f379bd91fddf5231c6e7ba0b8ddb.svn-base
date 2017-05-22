package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.tourandtickets;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Verify park names(Exact and near by)
 * 2. Verify No Tour found error message
 * 3. Verify other than first park, the park is with distance info
 * 4. Verify result nears header
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Tours Search Results)
 *  UWP Unified Search_Search Form_UI (Tours Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 28, 2011
 */
public class NoTourFoundValidatio extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch tour = new UwpUnifiedSearch();
	private String errorMes, searchResultHeader;
	private UwpUnifiedSearch tour_search = new UwpUnifiedSearch();
	private List<String> allParkName_Expected = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(tour_search);
		searchResult.verifyErrorMes(tour_search.contractCode, tour_search.parkId, errorMes);
		allParkName_Expected = searchResult.getAllParkNames();
		allParkName_Expected.remove(0);

		//Compground / Day Use park
		this.verifyNoTourFoundSearchResult();

		//Permit
		tour.whereTextValue = "CABLES ON HALF DOME";
		tour.parkId = "79064";
		this.verifyNoTourFoundSearchResult();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		tour.interestInValue = "Tours & Tickets";
		tour.parkId = "73751";
		tour.whereTextValue = DataBaseFunctions.getFacilityName(tour.parkId, schema); //"SOUTH KAWISHIWI RIVER";
		tour.contractCode = "NRSO";

		errorMes = "No Tour found";
		searchResultHeader = "All Tours & Tickets Facilities [ * in straight line, not driving distance ]";

		tour_search.whereTextValue = "DESOLATION WILDERNESS ";
		tour_search.contractCode = "NRSO";
		tour_search.parkId = "72202";
		tour_search.whereTextValue = DataBaseFunctions.getFacilityName(tour_search.parkId, schema); //"DESOLATION WILDERNESS ";
		tour_search.interestInValue = tour.interestInValue;
	}

	private void verifyParkNames(){
		List<String> allParkName_Actual = searchResult.getAllParkNames();
		allParkName_Actual.remove(0);
		int count = 0;

		if(allParkName_Expected.size()==allParkName_Actual.size()){
			for(int i=0; i<allParkName_Actual.size(); i++){
				if(allParkName_Expected.toString().contains(allParkName_Actual.get(i))){
					count++;
					logger.info("Successfully verify park name:"+allParkName_Actual.get(i));
				}
			}
			if(count!=allParkName_Expected.size()){
				throw new ErrorOnDataException("The number of park name is wrong!", String.valueOf(allParkName_Expected.size()), String.valueOf(count));
			}
		}else throw new ErrorOnDataException("The length of two compared list is different.", String.valueOf(allParkName_Expected.size()), String.valueOf(allParkName_Actual.size()));
	}

	private void verifyNoTourFoundSearchResult(){
		this.gotoViewAsListPage(tour);
		searchResult.verifyFirstParkName(tour.whereTextValue);
		this.verifyParkNames();

		searchResult.gotoFirstPage();
		searchResult.verifyWarningMes(errorMes);
		searchResult.verifyFirstParkWithoutDistance();
		searchResult.verifySecondParkWithDistance();
		searchResult.verifyResultNearHeaderText(searchResultHeader);
	}
}
