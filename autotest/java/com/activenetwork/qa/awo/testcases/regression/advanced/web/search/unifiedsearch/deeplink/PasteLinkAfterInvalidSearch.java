package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(Blocked by DEFECT-34736->Defect-40014->passed): 
 * Per Victor's comment: I think you are right and we probably should have a consistent behavior. 
                         I will align the behavior. But for a future release, not for May release because there's some serious work to do. 
	Step1. Close all browser windows then open a new window;
	Step2. Enter "abc" in 'Where' field and search to get a validation error page;
	Step3. Paste a deep link of facility/rec area details page and press "Enter";
	Step4. Click on 'Find other facilities' link or google map inset (if applicable);

	Expect behavior:
	On step3:
	It shows the validation error page and all input/selection on search form shall be retained

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author bzhang
 * @Date  Jan 16, 2012
 */
public class PasteLinkAfterInvalidSearch extends RecgovTestCase {
	private String[][] urls = new String[5][2];
	private String errorMsg, ridbSchema, invalidWhereValue;
	UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	public void execute(){
		//1: click Find Other Facilities hyperlink, verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		//2: click Find other facilities on map hyperlink,  verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData(false);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);
		errorMsg = "Where did you want to go? Enter at least 4 letters and you'll start seeing facilities and addresses that match your input.";

		searchData.contractCode = "NRSO";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		String threshold = WebConfiguration.getInstance().getUIOption(Conf.rec_UIOptions, UIOptions.Threshold);
		invalidWhereValue = "abc".substring(0, Integer.valueOf(threshold.split(",")[1])-1);
		searchData.whereTextValue = invalidWhereValue;

		//Campground 
		urls[0][0] = url + "/camping/Big_Trinity_Cabin/r/campgroundDetails.do?contractCode=NRSO&parkId=70630&topTabIndex=Search";
		urls[0][1] = DataBaseFunctions.getFacilityName("70630", schema); //"BIG TRINITY CABIN";

		//Permit facility
		urls[1][0] = url + "/permits/Boundary_Waters_Canoe_Area_Wilderness/r/wildernessAreaDetails.do?page=detail&contractCode=NRSO&parkId=72600";
		urls[1][1] = DataBaseFunctions.getFacilityName("72600", schema); //"Boundary Waters Canoe Area Wilderness (Reservations)";

		//Tour facility
		urls[2][0] = url + "/tourParkDetail.do?contractCode=NRSO&parkId=72369";
		urls[2][1] = DataBaseFunctions.getFacilityName("72369", schema); //"PEARL HARBOR HISTORIC SITES (USS Arizona)";

		//Rec area
		urls[3][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=437&agencyCode=130";
		urls[3][1] = DataBaseFunctions.getRecreationAreaName("437", ridbSchema); //"Okeechobee Lake";

		//RIDB
		// Based on Lisha's reply, I will ignore below test data to click map link.
		// We display \ufffdNo Map\ufffd pins for recreation areas or facilities which don\ufffdt have GPS information. 
		// On their details page there will be no Google map showing because no GPS  data in database.
		urls[4][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=1061&agencyCode=70903"; 
		urls[4][1] = DataBaseFunctions.getRecreationAreaName("1061", ridbSchema); //Angeles National Forest
	}

	/**
	 * Verify default page error message and search Data
	 * @param clickFindOtherFacility  --true: Click "Find other facility" link
	 *                                --false: Click "Find other facilities on map" link 
	 *                                         or "find the Google map"
	 */
	private void pasteUrlCheckDefaultPageAndSearchData(boolean clickFindOtherFacility){
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchHelpInfoPage infoPg = UwpUnifiedSearchHelpInfoPage.getInstance();
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		
		for(int i = 0 ; i < urls.length; i ++ ){
			//1: Enter "abc" in 'Where' field and search to get a validation error page;
			web.invokeURL(url);
			web.makeUnifiedSearch(searchData);
			searchPanel.verifySearchCriteria(searchData);
			infoPg.verifyErrorMes(errorMsg);

			String tempUrl = urls[i][0];
			//2:Paste a deep link of facility/rec area details page and press "Enter";
			web.invokeURL(tempUrl,false,false);
			Object page = browser.waitExists(campGroundDetailPg, permitAreaDetailsPg, recreationAreaDetailsPg);
			
			//3:Click on 'Find other facilities' link or google map inset (if applicable);
			if(clickFindOtherFacility){
				if(page == campGroundDetailPg){
					campGroundDetailPg.clickFindOtherFacilities();
				}else if(page == permitAreaDetailsPg){
					permitAreaDetailsPg.clickFindOtherFacilities();
				}else recreationAreaDetailsPg.clickBackToSearch();
			}else{
				if(i==urls.length-1 || i==urls.length-2){//The last two PARK no map link
					logger.info("It is the RIDB facility without map pin.");
					break;
				}else{
					//if Find other facilities on map hyperlink exist, click it. 
					//otherwise click find the Google map on the left top conner of the page;
					if(campGroundDetailPg.checkFindOtherFacilitiesOnMap()){
						campGroundDetailPg.clickFindOtherFacilitiesOnMap();
					}else{
						campGroundDetailPg.clickFacilityGoogleMap();
					}
				}
			}
			if(i==urls.length-1 || i==urls.length-2){//Sara[2014/05/13] DEFECT-40014 is closed by Steve because created 1 yr ago, still pending, won't fix
				searchData.whereTextValue = invalidWhereValue;
				infoPg.waitLoading();
				infoPg.verifyErrorMes(errorMsg);
			}else {
				searchData.whereTextValue = urls[i][1];
				viewAsListPg.waitLoading();
			}
			
			searchPanel.verifySearchCriteria(searchData);
			searchData.whereTextValue = invalidWhereValue;
		}
	}

}
