package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.tourandtickets;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Verify tour names sold park sort by Alphabetical
 * 2. Verify Category title when tickets are only available at the facility
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Tours Search Results)
 *  UWP Unified Search_Search Form_UI (Tours Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 27, 2011
 */
public class VerifyTourNamesSoldPark extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch tour = new UwpUnifiedSearch();
	private String categoryTitle;
	private String[] tourNames;

	public void execute() {
		web.invokeURL(url);
//		web.makeUnifiedSearch(tour);
		this.gotoViewAsListPage(tour);
		web.verifyExpectedPgExist(searchResult);
		this.verifyCategoryTitle();
		this.verifyTourNamesSoldParkSort();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		tour.whereTextValue = "MAMMOTH CAVE NATIONAL PARK TOURS";

		tour.parkId = "77817";
//		tour.selectedAutoCompletedOption = "MAMMOTH CAVE NATIONAL PARK TOURS , MAMMOTH CAVE NATIONAL PARK, KY";
//		tour.selectAutoCompleteOption = true;

		tour.interestInValue = "Tours & Tickets";
	}

	/**Verify tour names sold park sort by Alphabetical */
	private void verifyTourNamesSoldParkSort(){
		int index = 0;
		int count = 0;
		tourNames = searchResult.getFirstParkTourNamesSoldPark();
		List<String[]> toursFromDb = web.queryTourAndTickets(schema, tour.parkId);
		for(int i=0; i<tourNames.length; i++){
			for(int j=index; j<toursFromDb.size(); j++){
				if(tourNames[i].equals(toursFromDb.get(j)[0])){
					MiscFunctions.compareResult("-----Successfully sort tour:"+tourNames[i], toursFromDb.get(j)[0], tourNames[i]);
					index = j;
					count++;
					break;
				}
			}
		}

		if(count!=tourNames.length){
			throw new ErrorOnDataException("Tour name is wrong!", String.valueOf(tourNames.length), String.valueOf(count));

		}
	}

	/**Verify Category title when tickets are only available at the facility*/
	private void verifyCategoryTitle(){
		categoryTitle = searchResult.getFirstParkTourTitleSoldPark();
		if(!categoryTitle.equals("Tour Tickets sold at Facility only:")){
			throw new ErrorOnDataException("The title is incrrect when tickets are available at the facility only");
		}
	}
}
