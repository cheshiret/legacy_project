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
 * 1. Verify park names
 * 2. Verify all park without distance info
 * 3. Verify Search Result header info
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Tours Search Results)
 *  UWP Unified Search_Search Form_UI (Tours Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 27, 2011
 */
public class ResultViaGoogle extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch tour = new UwpUnifiedSearch();
	private UwpUnifiedSearch tour_search = new UwpUnifiedSearch();
	private List<String> allParkName_Expected = new ArrayList<String>();
	private String errorMes, searchResultHeader;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(tour_search);
		searchResult.verifyErrorMes(tour_search.contractCode, tour_search.parkId, errorMes);
		allParkName_Expected = searchResult.getAllParkNames();
		allParkName_Expected.remove(0);

		this.gotoViewAsListPage(tour);
		this.verifyParkNames();
		searchResult.verifyAllParkWithDistance();
		searchResult.verifyResultNearHeaderText(searchResultHeader);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		tour.whereTextValue = "WASHINGTON, DC, USA"; //"WASHINGTON, D.C., DC, USA";
		tour.interestInValue = "Tours & Tickets";

		tour_search.parkId = "72202";
		tour_search.whereTextValue = DataBaseFunctions.getFacilityName(tour_search.parkId, schema); //"DESOLATION WILDERNESS ";
		tour_search.contractCode = "NRSO";
		tour_search.interestInValue = tour.interestInValue;

		errorMes = "Sorry, no tour found"; //"No Tour found";
		searchResultHeader = "All Tours & Tickets Facilities [ * in straight line, not driving distance ]";
	}

	private void verifyParkNames(){
		List<String> allParkName_Actual = searchResult.getAllParkNames();
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
}
