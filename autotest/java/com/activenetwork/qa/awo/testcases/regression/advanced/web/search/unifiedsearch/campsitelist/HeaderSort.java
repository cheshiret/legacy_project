package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitelist;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: pass on local
 * @Description:
 * 
 * Verify all the Site list links( next/previous for pagination, sorting link on column headers) are working.
 * @Preconditions:
 * 1: the park has more than 1 page sites on the site list page.
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 9, 2011
 */
public class HeaderSort extends RecgovTestCase {
	private String[] columnHeader = new String[]{"Site #","Facility Area","Site Type"};
	private String[] columnItems;
	private int siteTotalNumberPerPage; 
	
	public void execute() {
		web.invokeURL(url);
		web.bookParkToSiteListPg(bd);
		this.verifySearchResultOrder();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		siteTotalNumberPerPage = 25;  //The total number of sites that can be displayed per page on site list page.
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.park = "BIRCH COVE";
		bd.contractCode = "NRSO";
		bd.parkId = "73114";
		bd.interestInValue = "Camping & Lodging";
		bd.clickParkName = true;
	}
	
	/**
	 * 1: verify Preview and Next button works.
	 * 2: verify search result will order correctly when we click different column header name
	 * 
	 */
	public void verifySearchResultOrder(){
		RecgovSiteListPage siteListPg = RecgovSiteListPage.getInstance();
		
		List<SiteInfoData> siteResults = null;
		//1: verify more than 25 site displayed on the search result.
		siteResults = siteListPg.getAllSiteInfo();
		if(siteResults.size() <= siteTotalNumberPerPage){
			throw new ErrorOnDataException("Please make sure there are more than " + siteTotalNumberPerPage + " sites on the search result , in order to verify the preview/Next paging function");
		}
		
		//2: loop through each column header, verify display order changes whenever we click each header name.
		for (int i = 0 ; i <columnHeader.length; i ++){
			//click the column header 1st time, check order by ascending
			logger.info("Start verify the column header:" + columnHeader[i] + " sort function.");
			siteListPg.gotoFirstPage();
			siteListPg.clickColumnHeader(columnHeader[i]);
			siteListPg.waitLoading();
			
			siteResults = siteListPg.getAllSiteInfo();
			columnItems = this.wrapColumnItems(siteResults, columnHeader[i]);
			siteListPg.verifySearchResultOrder(columnItems, false); 
			//click the column header second time, check order by descending
			siteListPg.gotoFirstPage();
			siteListPg.clickColumnHeader(columnHeader[i]);
			siteListPg.waitLoading();
			
			siteResults = siteListPg.getAllSiteInfo();
			columnItems = this.wrapColumnItems(siteResults, columnHeader[i]);
			siteListPg.verifySearchResultOrder(columnItems, true);
		}
	}
	
	/**
	 * wrap the column name in the String array, in order to check the display order.
	 * @param siteResult
	 * @param columName
	 * @return
	 */
	public String[] wrapColumnItems(List<SiteInfoData> siteResult, String columName ){
		String[] wrapArray = null;
		
		wrapArray = new String[siteResult.size()];
		for(int i = 0 ; i < siteResult.size(); i ++){
			if(columName.equalsIgnoreCase("onlineAvailability")){  // online availability is not suitable for click to order result for now.
				wrapArray[i] = siteResult.get(i).onlineAvailability;
			}else if (columName.equalsIgnoreCase("Site #")){
				wrapArray[i] = siteResult.get(i).siteNumber;
			}else if (columName.equalsIgnoreCase("Facility Area")){
				wrapArray[i] = siteResult.get(i).area;
			}else if (columName.equalsIgnoreCase("Site Type")){
				wrapArray[i] = siteResult.get(i).siteType;
			}else{
				throw new ErrorOnDataException("The function don't suport the given column name: " + columName);
			}
		}
		return wrapArray;
	}
}
