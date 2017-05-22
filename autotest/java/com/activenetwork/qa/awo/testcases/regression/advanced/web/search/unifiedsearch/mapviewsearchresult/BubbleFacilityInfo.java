package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/** @Description(P):
	 * 1: Verify the location pins for the facilities on the current search results page use the pin as displayed on the list page
	 * 2: Verify shows facility name in the format of <facility name>, <facility parent/region name>, <facility State>
	 * 3: Verify Camping and Lodging displays
     * 4: Verify Has sites with title displays in bold followed by Common site attributes Accessible, Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).


	 * @Preconditions:
	 * Must run under RFT
	 * @SPEC: Story J 
	 * @Task#: AUTO - 839
	 * 
	 * @author bzhang
	 * @Date  Dec 19, 2011
	 */
	public class BubbleFacilityInfo extends RecgovTestCase {
		private String faclityState, facilityParent, facilityAgency, expectSiteTypeTitle, expectSiteAmenities;
		public void execute(){
			web.invokeURL(url);
			this.gotoViewAsListPage(bd);
			this.verifyLocationPinInfo();
		}
		
		public void wrapParameters(Object[] param) {
			url = TestProperty.getProperty(env + ".web.recgov.url");
			bd.isUnifiedSearch = this.isUnifiedSearch();
			bd.whereTextValue = "NORTH PINES";
			bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
			bd.contractCode ="NRSO";
			bd.parkId = "70927";
			
			//data used for verify the facility name format.
			faclityState = "California";
			facilityParent = "Yosemite National Park";
			facilityAgency = "National Park Service";
			
			expectSiteTypeTitle = "Camping and Lodging";
			expectSiteAmenities = "Has sites with: Accessible, Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).";
			
		}
		
		public void verifyLocationPinInfo(){
			RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
			UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
			
			// 1: Verify the location pins for the facilities on the current search results page use the pin as displayed on the list page
			String src = listPg.getMapPinPictureSrc(bd.contractCode, bd.parkId);
			listPg.clickViewAsMap();
			mapPg.waitLoading();
			mapPg.waitMapLoadingComplete();
			
			mapPg.verifyMapPinPictureInfo(bd.contractCode, bd.parkId, src);
			
			// 2: Verify shows facility name in the format of <facility name>, <facility state> part of <facility parent/region name>, <agency>
			mapPg.clickMapPin(bd.contractCode, bd.parkId);
			mapPg.mapBubbleWidgetWaitExists();
			mapPg.verifyFirstFacilityViewHeaderFormat(bd.whereTextValue, facilityParent, faclityState, facilityAgency);
			
			// 3: verify Camping and Loading displays.
			mapPg.verifyParkSiteTypeTitle(expectSiteTypeTitle);
			// 4: verify Has sites with title displays.
			mapPg.verifyParkSitesAmenities(expectSiteAmenities);
		}
	}
