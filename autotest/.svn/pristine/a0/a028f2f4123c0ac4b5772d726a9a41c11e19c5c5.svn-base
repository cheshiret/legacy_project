package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewasmap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Run by RFT because of the click() map pin doesn't work under Selenium
 * The search result should be match filters(type, agency and letter)
 * @Preconditions:
 * @SPEC: Interatcions between filters (Type, Agency and Letter) below the search form [TC:043199] 
 * @Task#: AUTO-1233 
 * 
 * @author SWang5
 * @Date  Oct 9, 2012
 */
public class FilterResult  extends RecgovTestCase{
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private String agencyFilterOption, letterFilterOption1, letterFilterOption2;
	private String parkID_Camping, parkName_Camping, parkSrcValue_Camping, parkID_DayUse, parkName_DayUse, parkSrcValue_DayUse, parkAgency;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
		web.invokeURL(url);

		//Verify "Camping & Lodging" park result after filter
		this.gotoViewAsListPage(bd);
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, StringUtil.EMPTY, letterFilterOption1);
		parkSrcValue_Camping = viewAsListPg.getMapPinPictureSrc(bd.contractCode, parkID_Camping);
		web.gotoViewAsMapFromViewAsList();
		this.verifyResultMatchFilters(letterFilterOption1, parkSrcValue_Camping, bd.contractCode, parkID_Camping, parkName_Camping, UwpUnifiedSearch.PARKTYPE_CAMPINGANDLODGING, agencyFilterOption);

		//Verify "Day use & Picnic areas" park result after filter
		this.gotoViewAsListPage(bd);
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, StringUtil.EMPTY, letterFilterOption2);
		parkSrcValue_DayUse = viewAsListPg.getMapPinPictureSrc(bd.contractCode, parkID_DayUse);
		web.gotoViewAsMapFromViewAsList();
		this.verifyResultMatchFilters(letterFilterOption2, parkSrcValue_DayUse, bd.contractCode, parkID_DayUse, parkName_DayUse, UwpUnifiedSearch.PARKTYPE_DAYUSEANDPICNICAREAS, agencyFilterOption);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.contractCode = "NRSO";
		bd.whereTextValue = "DURHAM, NC"; //DURHAM, NC, USA
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
//		bd.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
//		bd.otherActivityName = "Biking";
		agencyFilterOption = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_USARMYCORPSOFENGINEERS_ID, schema);
		letterFilterOption1= "B";
		letterFilterOption2= "P";

		parkID_Camping = "73105";
		parkName_Camping = DataBaseFunctions.getFacilityName(parkID_Camping, schema); //BUFFALO PARK
		parkID_DayUse = "73338";
		parkName_DayUse = DataBaseFunctions.getFacilityName(parkID_DayUse, schema); //PHILPOTT PARK
	}

	/**
	 * Verify result, park type, park agency, park name are match filters
	 */
	public void verifyResultMatchFilters(String letterFilterOption, String srcValue, String contractCode, String parkId, String parkName, String parkType, String expectedParkAgency){
		viewAsMapPg.gotoMapBubbleWidget(contractCode, parkId);

		//Verify facility name start with letter W
		viewAsMapPg.verifyParkNameStratsWith(parkName, letterFilterOption);

		//Verify product type title
		viewAsMapPg.verifyParkSiteTypeTitleOnMapBubble(parkType);

		//Verify park agency 
		parkAgency = viewAsMapPg.getParkAgencyFromMapBubbleWidget();
		MiscFunctions.validateResult("Park Agency", expectedParkAgency, parkAgency);

		//Close map bubble widget
		viewAsMapPg.closeMapBubbleWidget();
	}
}
