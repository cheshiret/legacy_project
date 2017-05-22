
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitesearchform;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: Verify the book sites(button ) in the unified campground search results page.
 * 2: verify the book sites button can lead us to site list page.
 * 3: verify each site type hyperlink will display corresponding site info on site list page.
 * @Preconditions:
 * 1: the park we are going to book are available on the date we select below.
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 7, 2011
 */
public class SiteTypeLinkVerify extends RecgovTestCase {
	private RecgovSiteListPage  campSiteListPg = RecgovSiteListPage.getInstance();
	private String[] siteTypes= new String[2];
	private Integer[] siteTotalNumberPerSiteType = new Integer[2];
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		//goto site list page via clicking book sites button.
		this.verifyBookSitesLink();
		
		//verify each site type hyperlink will display corresponding site type info.
		this.verifyEachSiteTypeHyperlink();
		
		//verify clear search and show all link will reset campsite list page to the default display items.
		this.verifyClearSearchAndShowAllLink();
		
		//verify there is no site display as "Enter Date" status, after click clear search hyperlink.
		this.verifyOnLineAvailabilityStatus();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		//There are two types of sites for the park on the time of coding this script. 
		siteTypes[0] = "STANDARD NONELECTRIC";
		siteTypes[1]= "TENT ONLY NONELECTRIC";
		
		siteTotalNumberPerSiteType[0] = 0;
		siteTotalNumberPerSiteType[1] = 0;
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "Camp Sherman Campground".toUpperCase();
		bd.interestInValue = "Camping & Lodging";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		bd.lengthOfStay = "1";
		bd.lookFor="RV sites";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
	}
	
	/**
	 * verify book site button will lead people to site list page and display all site info(despite available or not available)
	 */
	public void verifyBookSitesLink(){
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
		
		//verify book site button will lead people to site list page.
		viewAsListPg.clickBookSitesByParkID(Integer.parseInt(bd.parkId));
		campSiteListPg.waitLoading();
		
		
		//verify site list display all site info
		List<String> siteTypeOnPg = campSiteListPg.getAllSiteTypeNames();
		if(siteTypeOnPg.size() ==0){
			throw new ErrorOnPageException("In order to run this script, please make sure there is at least 1 sites on the site list page.");
		}
		
		//record each site type's site number info.
		for(String siteType: siteTypeOnPg){
			if(siteType.equalsIgnoreCase(siteTypes[0])){
				siteTotalNumberPerSiteType[0]+=1;
			}else if(siteType.equalsIgnoreCase(siteTypes[1])){
				siteTotalNumberPerSiteType[1]+=1;
			}else{
				throw new ErrorOnPageException("Site type info is not consistent with the data when this script was created, please have a check for site type info in inventory manager manully.");
			}
		}		
	}
	
	/**
	 * verify each site type link will display it's own site info, and the total site number per each site type match with the initial page loading's total number.
	 */
	public void verifyEachSiteTypeHyperlink(){
		//click each site type and then verify the total site number match with the value we get the first time page was loaded.
		for(int i = 0 ; i <siteTypes.length; i ++){
			campSiteListPg.clickSiteTypeLink(siteTypes[i]);
			campSiteListPg.waitLoading();
			
			int currentTotalNum = campSiteListPg.getTotalSiteNumberInAvailTable();
			if(siteTotalNumberPerSiteType[i] != currentTotalNum){
				logger.error("The current site total number for site type:" + siteTypes[i] + " is:" + currentTotalNum);
				logger.error("The expect  site total number for site type:" + siteTypes[i] + " is:" + siteTotalNumberPerSiteType[i]);
				throw new ErrorOnPageException("Site total number per each site type verify failed for: " + siteTypes[i]);
			}else{
				logger.info("The total site number per each site type verify successfully for :" + siteTypes[i]);
			}
		}		
	}
	
	/**
	 * verify the clear search and show all link will result the site list page to the inital status with all site displayed.
	 * 
	 */
	public void verifyClearSearchAndShowAllLink(){
		campSiteListPg.clickClearSearchAndShowAll();
		campSiteListPg.waitLoading();
		
		//verify the total site number displayed after click "Clear search and show all" hyperlink equal with the total site number site list page first loaded after search.
		int totalSiteDisplay = campSiteListPg.getTotalSiteNumberInAvailTable();
		int totalSiteExpect = 0;
		for (int i = 0 ; i < siteTotalNumberPerSiteType.length; i ++){
			totalSiteExpect += siteTotalNumberPerSiteType[i];
		}
		
		if(totalSiteDisplay !=  totalSiteExpect){
			logger.error("The total site number displayed was:" + totalSiteExpect);
			logger.error("The total site number after click 'Clear search and show all' is:" + totalSiteDisplay);
			throw new ErrorOnPageException("The total site number after click 'Clear search and show all' is not match with the inital page loading value.");
		}else{
			logger.info("verify total site number info successfully for 'Clear search and show all' hyperlink");
		}		
	}
	
	/**
	 * verify after click "Clear search and show all" hyperlink, the online availability grid will NOT show "Enter Date" button for all sites.
	 * b/c clear search and show all operation will retain the arrival date and length of stay and flexible value previously entered.
	 * 
	 */
	public void verifyOnLineAvailabilityStatus(){
		List<SiteInfoData> siteInfos = new ArrayList<SiteInfoData>();
		
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
