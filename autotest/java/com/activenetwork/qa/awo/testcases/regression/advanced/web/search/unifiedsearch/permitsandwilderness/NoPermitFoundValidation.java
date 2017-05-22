package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Verify park names(Exact and near by)
 * 2. Verify No Permit found error message
 * 3. Verify other than first park, the park is with distance info
 * 4. Verify result nears header
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class NoPermitFoundValidation extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private String errorMes_permit, nearHeaderText;
	private BookingData bd_search= new BookingData();
	private List<String> facilitiesNames_Expect;
	private List<String> facilitiesNames_Actual;
	private List<String> parkNamesList = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd_search);
		searchResult.verifyWarningMes(errorMes_permit);
		this.getAllParkNames();

		//Day Use
		this.verifyNoPermitFoundSearchResult(bd.parkId);

		//Camp ground
		bd.parkId = "70930";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//CRANE FLAT
		this.verifyNoPermitFoundSearchResult(bd.parkId);

		//Tours
		bd.parkId = "72777";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//VOYAGEURS NATIONAL PARK TOURS
		this.verifyNoPermitFoundSearchResult(bd.parkId);
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");

		bd.contractCode = "NRSO";
		bd.parkId = "70253";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//DALLES
		bd.interestInValue = "Permits & Wilderness";

		errorMes_permit = "No Permit Type found";
		nearHeaderText = "All Permits & Wilderness Facilities [ * in straight line, not driving distance ]";

		bd_search.whereTextValue = "CARRIZO PLAIN NATIONAL MONUMENT";
		bd_search.interestInValue = bd.interestInValue;
	}

	public void getAllParkNames(){
		parkNamesList = searchResult.getAllParkNames();
		facilitiesNames_Expect= new ArrayList<String>();
		for(int i=1; i<parkNamesList.size(); i++){
			facilitiesNames_Expect.add(parkNamesList.get(i));
		}
	}

	/**
	 * Verify top result matching the target facility
	 * Verify the child tour facility
	 */
	private void verifyParkNames(){
		int count = 0;
		facilitiesNames_Actual = searchResult.getAllParkNames();
		facilitiesNames_Actual.remove(0);

		if(facilitiesNames_Actual.size()==facilitiesNames_Expect.size()){
			for(int i=0; i<facilitiesNames_Actual.size(); i++){
				if(facilitiesNames_Expect.toString().contains(facilitiesNames_Actual.get(i))){
					count++;
					logger.info("Successfully verify facility name:"+ facilitiesNames_Actual.get(i));
				}
			}
			if(count!=facilitiesNames_Actual.size()){
				throw new ErrorOnPageException("The number of facility name is wrong!", String.valueOf(facilitiesNames_Actual.size()),String.valueOf(count));
			}
		}else throw new ErrorOnDataException("The length of two compared list is different.", String.valueOf(facilitiesNames_Expect.size()), String.valueOf(facilitiesNames_Actual.size()));
	}

	private void verifyNoPermitFoundSearchResult(String facilityID){
		this.gotoViewAsListPage(bd);

		//Verify all park names
		searchResult.verifyFirstParkName(bd.whereTextValue);
		this.verifyParkNames();
		searchResult.gotoFirstPage();

		//Verify error message about first park
//		searchResult.verifyWarningMes(errorMes_permit);
		searchResult.verifyWarningMes(errorMes_permit, bd.contractCode, facilityID);

		//Verify only nearby park has instance info
		searchResult.verifyFirstParkWithoutDistance();
		searchResult.verifySecondParkWithDistance();
		searchResult.verifyResultNearHeaderText(nearHeaderText);
	}
}
