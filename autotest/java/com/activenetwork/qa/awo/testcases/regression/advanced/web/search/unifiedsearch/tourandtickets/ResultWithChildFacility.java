package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.tourandtickets;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Verify the top results matching the target facility
 * 2. Verify the child tour facilities (no distance showing for child facilities) displaying as required
 * 3. Verify "Results part of 'parent name'" showing under the target parent facility
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Tours Search Results)
 *  UWP Unified Search_Search Form_UI (Tours Search Results)
 * @Task#:AUTO-783

 * @author Swang
 * @Date  Oct 28, 2011
 */
public class ResultWithChildFacility extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch tour = new UwpUnifiedSearch();
	private String childToursType, childTourID, ridbSchema, searchResultHeader; 
	private List<String> parkNames = new ArrayList<String>();
	private List<String> childTours = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(tour);

		parkNames = searchResult.getAllParkNames();
		searchResult.gotoFirstPage();

		//Verify all child tours displays in view as list page
		childTours = searchResult.getChildFacilities(tour.contractCode, tour.parkId, childToursType);
		searchResult.verifyAllParkNames(parkNames, childTours, false);

		//Verify Child Tour park with parent info
		this.verifyChildTourWithParentInfo();

		//Verify result near header
		searchResult.verifyResultNearHeaderText(searchResultHeader);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		tour.contractCode = "NRSO";
		tour.parkId = "2573";
		tour.whereTextValue = DataBaseFunctions.getRecreationAreaName(tour.parkId, ridbSchema); //Arches National Park
		tour.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;

		childToursType = "Tours and Tickets";
		searchResultHeader = "All Tours & Tickets Facilities [ * in straight line, not driving distance ]";
	}

	/**
	 * Verify Child Tour park with parent info
	 */
	private void verifyChildTourWithParentInfo(){
		for(int i=0; i<childTours.size(); i++){
			childTourID = DataBaseFunctions.getFacilityID(childTours.get(i), schema);
			boolean withParent = searchResult.isParkWithPartOf(tour.contractCode, childTourID, 	tour.whereTextValue);

			if(!withParent){
				throw new ErrorOnPageException("Tour:"+childTours.get(i)+" should include parent:"+tour.whereTextValue+" info.");
			}else logger.info("Successfully tour:"+childTours.get(i)+" includes parent:"+tour.whereTextValue+" info.");
		}
	}
}
