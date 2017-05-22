
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitesearchform;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: Verify the clear search and show all button will clear the search data populdated in the Find Sites panel.
 * but the value in Arrival Date, Flexiable, and Length of Stay should be retained.
 * 2: Verify click clear search and show all, there is no site in the status of "Enter Date", b/c "Enter Date" only
 * displayed when there is no arrival date specified.
 * @Preconditions:
 * 1: the park we are going to book are available on the date we select below.
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 7, 2011
 */
public class ClearSearchShowAll extends RecgovTestCase {
	private BookingData bd2 = new BookingData();  //search date used for Find Site panel search
	private BookingData bdReset = new BookingData();
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		//verify the clear search and show all link will reset the search value populdated in the find site panle.
		this.verifyFindOtherSitesPopulateValue();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "Camp Sherman Campground".toUpperCase();
		bd.interestInValue = "Camping & Lodging";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		bd.lengthOfStay = "1";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		
		//search data used in the Find sites table. 
		bd2.isUnifiedSearch = this.isUnifiedSearch();
		bd2.whereTextValue = "Camp Sherman Campground".toUpperCase();
		bd2.interestInValue = "Camping & Lodging";
		bd2.loop = "CAMP SHERMAN CAMPGROUND";
		bd2.contractCode = "NRSO";
		bd2.parkId = "72099";
		bd2.siteNo = "002";
		bd2.lookFor = "RV sites";
		bd2.length = "30";
		bd2.moreOptions = true;
		
		bd2.electricHookupValue = "20 Amp (or more)";
		bd2.waterFront = true;
		bd2.sewerHookup = true;
		bd2.pullthroughDriveWay = true;
		bd2.waterHookup = true;
		
		bd2.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd2.lengthOfStay = "2";
		bd2.accessibilityNeeds = true;
		bd2.petsAllowed = true;
		bd2.flexibleValue = "Not Flexible";
		
		//inital search data for Find site panel.
		bdReset.isUnifiedSearch = bd.isUnifiedSearch;
		bdReset.loop = "Any Loop";
		bdReset.lookFor = "Any type of site";
		bdReset.arrivalDate = bd2.arrivalDate;
		bdReset.lengthOfStay = bd2.lengthOfStay;
		bdReset.flexibleValue = bd2.flexibleValue;
	}
	
	
	
	/**
	 * 
	 * 	 verify the find sites p will reset to default value the page was loaded.
	 *   start from park view as list page, ends at campsite list page.
	 */
	public void verifyFindOtherSitesPopulateValue(){
		RecgovViewAsListPage parkViewPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedFindProductCommonPanel findSitesPanel = UwpUnifiedFindProductCommonPanel.getInstance();
		UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
		RecgovSiteListPage campSiteListPg = RecgovSiteListPage.getInstance();
//		UwpDateRangeAvailabilityPage rangePage=UwpDateRangeAvailabilityPage.getInstance();
		List<SiteInfoData> siteInfos = new ArrayList<SiteInfoData>();
		
		//fulfill the search criteria in the find sites panel.
		parkViewPg.clickParkName(bd2.whereTextValue);
		campCommonPg.waitLoading();
		findSitesPanel.makeSearch(bd2);
		campCommonPg.waitLoading();
		
		campCommonPg.clickClearSearchAndShowAll();
		campSiteListPg.waitLoading();
		//verify the search criteria were all set to default value.
		findSitesPanel.verifySearchCriteria(bdReset);
		
		campSiteListPg.getAllSiteInfo();
		
		//verify there is no site which Online availability column display as "Enter Date" button. b/c clear search and show all operation will retain the arrival date, length of stay and Flexible value previously entered
		siteInfos = campSiteListPg.getAllSiteInfo();
		
		if(null == siteInfos || siteInfos.size() == 0){
			throw new ErrorOnPageException("Warning: there should be at least on site for this campground to go through the check points for this script. ");
		}else{
			for(int i = 0 ; i < siteInfos.size(); i ++){
				//loop through each online availability column, check the text should NOT be "Enter Date", b/c
				//"Enter Date" can only be displayed when there is no arrival date specified.
				//"Enter Date" is the key to identify whether the search criteria is cleared or not.
				String onlineStatus = siteInfos.get(i).onlineAvailability;
				if(onlineStatus.equalsIgnoreCase("Enter Date")){
					logger.error("The expect  status on line#" + i + " should not be Enter Date" );
					throw new ErrorOnDataException("verify the Online Availability status failed.");
				}
			}
			logger.info("verify the Online Availability status successfully.");
		}
	}
}
