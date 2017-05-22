package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.tourandtickets;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Note: Details info as DEFECT-34239
 * The tours are displayed firstly, by display sequence/sort key and then, alphabetically, by name.
 * If the sort key is null the tour will be displayed at the end. 

 * @Description:
 * 1. Verify tour names sold online sort by Alphabetical in Park view as list page
 * 2. Verify tour names is same between Park view as list and tour list page
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Tours Search Results)
 *  UWP Unified Search_Search Form_UI (Tours Search Results)
 * @Task#:AUTO-783

 * @author Swang
 * @Date  Oct 27, 2011
 */
public class VerifyTourNamesSoldOnline extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch tour = new UwpUnifiedSearch();
	private String[] tourNames;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(tour);
		web.verifyExpectedPgExist(searchResult);
		this.verifyTourNamesSort();
		this.verifyTourNamesInTourList();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");

		tour.parkId = "72777";
		tour.contractCode = "NRSO";
		tour.whereTextValue = DataBaseFunctions.getFacilityName(tour.parkId, schema); //"VOYAGEURS NATIONAL PARK TOURS";
		tour.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;
	}

	/**Verify tour names sort by sort key and Alphabetical*/
	private void verifyTourNamesSort(){
		int index = 0;
		int count = 0;
		tourNames = searchResult.getFirstParkSoldOnlineTourNames();
		List<String[]> toursFromDb = web.queryTourAndTickets(schema, tour.parkId);
		
		for(int i=0; i<tourNames.length; i++){
			for(int j=index; j<toursFromDb.size(); j++){
				if(tourNames[i].equals(toursFromDb.get(j)[0])){
					MiscFunctions.compareResult(j+"-----Successfully sort tour:"+tourNames[i], toursFromDb.get(j)[0], tourNames[i]);
					index = j;
					count++;
					break;
				}
			}
		}
		if(count!=tourNames.length){
			throw new ErrorOnDataException("Tour name is wrong!", String.valueOf(tourNames.length), String.valueOf(count));

		}

		searchResult.verifyTopParkCategoryTitle(tour.interestInValue.replaceAll("&", "and"), tour.interestInValue);
	}

	/**Verify tour names in tour list page*/
	private void verifyTourNamesInTourList(){
		UwpTourListPage tourListPg = UwpTourListPage.getInstance();
		int count = 0;

		searchResult.clickFirstCheckAvailability();
		tourListPg.waitLoading();

		//Verify tour names in tour list page
		List<String> tourNamesInTourList = tourListPg.getAllEnterDateTours();
		if(tourNames.length==tourNamesInTourList.size()){
			for(int i=0; i<tourNamesInTourList.size(); i++){
				for(int j=0; j<tourNames.length; j++){
					if(tourNamesInTourList.get(i).equals(tourNames[j])){
						count++;
						logger.info("Successfully verify tour name:"+tourNamesInTourList.get(i));
						break;
					}	
				}
			}
			if(count!=tourNames.length){
				throw new ErrorOnDataException("The number of tour name is wrong!.", String.valueOf(tourNames.length), String.valueOf(count));
			}
		}else{
			throw new ErrorOnDataException("The length of tour name list is different.", String.valueOf(tourNames.length), String.valueOf(tourNamesInTourList.size()));
		}
	}
}
