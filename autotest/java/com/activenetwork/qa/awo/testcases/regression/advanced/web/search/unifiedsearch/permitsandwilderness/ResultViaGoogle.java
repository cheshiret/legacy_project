package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * 1. Verify park names
 * 2. Verify all park with distance info
 * 3. Verify Search Result header info
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class ResultViaGoogle extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
	private UwpUnifiedSearch permit = new UwpUnifiedSearch();
	private BookingData bd_search= new BookingData();
	private String errorMes_permit, nearHeaderText;
	private List<String> facilitiesNames_Expected;
	private List<String> parkNamesList = new ArrayList<String>();
	private List<String> facilityListViaUi = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd_search);
//		searchResult.verifyWarningMes(errorMes_permit);
		this.getAllParkNames();

		this.gotoViewAsListPage(permit);
//		web.makeUnifiedSearch(permit);
//		web.verifyExpectedPgExist(suggestionPg);
//
//		suggestionPg.clickFirstAddressSuggestion();
//		web.verifyExpectedPgExist(searchResult);

		this.verifyParkNames();
		searchResult.gotoFirstPage();

		searchResult.verifyFirstParkName(facilityListViaUi.get(0));
		searchResult.verifyAllParkWithDistance();
		searchResult.verifyResultNearHeaderText(nearHeaderText);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		permit.whereTextValue = "POLLOCK PINES, CA, USA";
		permit.interestInValue = "Permits & Wilderness";

		errorMes_permit = "No Permit Type found";
		nearHeaderText = "All Permits & Wilderness Facilities [ * in straight line, not driving distance ]";

		bd_search.whereTextValue = DataBaseFunctions.getFacilityName("72168", DataBaseFunctions.getSchemaName("NRRS", env)); //"CARRIZO PLAIN NATIONAL MONUMENT";
		bd_search.interestInValue = permit.interestInValue;
	}

	public void getAllParkNames(){
		parkNamesList = searchResult.getAllParkNames();
		facilitiesNames_Expected = new ArrayList<String>();
		for(int i=1; i<parkNamesList.size(); i++){
			facilitiesNames_Expected.add(parkNamesList.get(i));
		}
	}

	/**
	 * Verify top result matching the target facility
	 * Verify the child tour facility
	 */
	private void verifyParkNames(){
		int numOfParks = 0;
		facilityListViaUi = searchResult.getAllParkNames();

		if(facilitiesNames_Expected.size()==facilityListViaUi.size()){
			for(int j=0; j<facilityListViaUi.size(); j++){
				if(facilitiesNames_Expected.toString().contains(facilityListViaUi.get(j))){
					numOfParks++;
					logger.info("Successfully verify park name:"+facilityListViaUi.get(j));
				}
			}
			if(numOfParks!=facilityListViaUi.size()){
				throw new ErrorOnDataException("The number of park name is wrong!", String.valueOf(numOfParks), String.valueOf(facilityListViaUi.size()));
			}
		}else throw new ErrorOnDataException("The length of two compared list is different.", String.valueOf(facilitiesNames_Expected.size()), String.valueOf(facilityListViaUi.size()));
	}
}
