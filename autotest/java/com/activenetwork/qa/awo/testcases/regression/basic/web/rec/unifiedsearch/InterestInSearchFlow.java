package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.unifiedsearch;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: P 
 * Details check point refer to every steps
 * @Preconditions:
 * @SPEC: 'Interested in' - drop down mode (search flow) [TC:042958] 
 * @Task#: AUTO-1234 
 * 
 * @author SWang
 * @Date 9/17/2012
 */
public class InterestInSearchFlow extends RecgovTestCase {
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private static String RESULTS_WITHIN, RESULTS_NEAR, RIDB_SCHEMA, RESULT_PART_OF;

	public void execute() {
		web.invokeURL(url);

		//single exactly matching on facility name or alias
		web.makeUnifiedSearch(searchData);
		searchPanel.verifyInterestInSelectedValue(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN);
		viewAsListPg.verifyParkWithMatchedOn(searchData.contractCode, DataBaseFunctions.getFacilityID(viewAsListPg.getFirstParkName(), schema), searchData.whereTextValue);

		//state search
		searchData.whereTextValue = "California";
		searchData.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		searchData.lookFor = "Group sites";
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyViewHeaderNearValue(this.initialViewHeaderNearValue(RESULTS_WITHIN, searchData.whereTextValue));

		//facility(parent area without GPS) search: "Tongass National Forest"
		searchData.parkId = "2336";
		searchData.whereTextValue = DataBaseFunctions.getRecreationAreaName(searchData.parkId, RIDB_SCHEMA);
		searchData.interestInValue = "Camping & Lodging";
		searchData.lookFor = "Cabins";
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyNoMapPinExist(searchData.whereTextValue);
		viewAsListPg.verifyViewHeaderNearValue(this.initialViewHeaderNearValue(RESULT_PART_OF, searchData.whereTextValue));
		
		//facility(campground) search: "Sandy Lake"
		searchData.parkId = "73389";
		searchData.whereTextValue = DataBaseFunctions.getFacilityName(searchData.parkId, schema);
		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		searchData.lookFor = "";
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyViewHeaderNearValue(this.initialViewHeaderNearValue(RESULTS_NEAR, searchData.whereTextValue));

		//facility(non-parent area without GPS) search: "Death Valley Wilderness"
		searchData.parkId = "12855";
		searchData.whereTextValue = DataBaseFunctions.getRecreationAreaName(searchData.parkId, RIDB_SCHEMA);
		this.gotoViewAsListPage(searchData);

		//Google address search:URHAM, NH, USA
		searchData.contractCode = "";
		searchData.parkId = "";
		searchData.whereTextValue = "DURHAM, NH, USA";
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyViewHeaderNearValue(this.initialViewHeaderNearValue(RESULTS_NEAR, searchData.whereTextValue));
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		RIDB_SCHEMA = TestProperty.getProperty(env+".ridb.schema");
		searchData.contractCode = "NRSO";

		searchData.whereTextValue = "cg21";
		searchData.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		
		RESULTS_WITHIN = "Results within";
		RESULTS_NEAR = "Results near";
		RESULT_PART_OF = "Results part of";
	}

	/**
	 * Initial view header near value
	 * @param nearHeaderType
	 * @param enterValue
	 * @return
	 */
	public String initialViewHeaderNearValue(String nearHeaderType, String enterValue){
		String viewHeaderNearValue = "";

		if(nearHeaderType.equalsIgnoreCase(RESULTS_WITHIN)){
			viewHeaderNearValue = RESULTS_WITHIN+" "+enterValue;
		}else if(nearHeaderType.equalsIgnoreCase(RESULTS_NEAR)){
			viewHeaderNearValue = RESULTS_NEAR+" "+enterValue+" [ * in straight line, not driving distance ]";
		}else if(nearHeaderType.equalsIgnoreCase(RESULT_PART_OF)){
			viewHeaderNearValue = RESULT_PART_OF+" "+enterValue;
		}else throw new ErrorOnDataException("No matched near header type.");

		return viewHeaderNearValue;
	}
}
