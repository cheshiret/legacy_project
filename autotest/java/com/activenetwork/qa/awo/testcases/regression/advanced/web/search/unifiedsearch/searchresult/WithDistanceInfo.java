package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * If the headerText number info "Search Results: 1-10 of 183" does not match with the product.  the following 3 steps must be done manually to verify the total number match with DB.
 * 1: select count(*) from D_LOC_COORDINATES where (lat_dec between {latitude} - 2.891535680700597 and {latitude} + 2.891535680700597) and long_dec between {longitude} - 2.891535680700597 and {longitude} + 2.891535680700597
 * 2: RIDB is Recreation Search tab on Rec.gov website. RIDB is NOT in ORMS it has it\ufffds own database - QA2_RA_RIDB, so we need to look through RIDB facilities as well.
 * 3: besides RIDB facilities, we also need to go through the RECAREA table in RIDB.
 * after finish above 3 steps add the total number together should be the final total park number displayed in the header text(for example: 1-10 of 183)
 * 
 * Park: Mammoth Picnic Shelters   (use this script get lat and long info from DB for a certain location: select * from D_LOC_COORDINATES where loc_id = '70920';)
 * Latitude: 37.1833333333333333
 * Longitude: -86.15
 * 
 * Latitude between: 34.29179765263273 and 40.07486901403393
 * Longitude: -89.0415356807006 and -83.2584643192994
 * 
 * related knowledge.
 * How to convert miles to degrees:	 
 * degrees = miles/ 3963.0 * 180/ 3.141592653589793;	 
 * 	
 * For 200 miles you get \ufffddegrees = 2.891535680700597	= 2 degree, 53.4 minutes, 5.5284505221492 seconds
 * For 50 miles you get degrees = 0.722883920175149	= 0 degree, 43.2 minutes, 10.3821126305364 seconds
 * For 1000 miles you get degrees = 14.457678403502983 = 14 degree, 27 minutes, 27.6422526107388 seconds
 * @Preconditions:
 * 1: the selected park in wrap parameter must have latitude and longitude info.
 * @SPEC: Story C
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 10, 2011
 */
public class WithDistanceInfo extends RecgovTestCase{
	private String[] siteTypes;
	private int distance;
	private String siteTitle,siteAmenities, headerText,ridbSchema, latitude, longitude;
	private List<String> recareaInDB, recareaOnPg = new ArrayList<String>();
	private List<String> recFacilityInDB, recFacilityOnPg = new ArrayList<String>();
	private List<String> facilityInDB , facilityOnPg= new ArrayList<String>();
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		
		//1: verify facility related info.
		this.verifyFirstFacilityInfo();
		//2:verify the check availability button displays below the site types
		this.verifyCheckAvailabilityDisplayUnderSiteType();
		//3:verify [182] result near Mammoth Picnic Shelters
//		this.verifySearchResultSummaryInfo(headerText, true);[Shane]20140515,affect by customer data change a lot,comment this check point
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env +".db.schema.prefix") + "NRRS";
		ridbSchema = TestProperty.getProperty(env + ".ridb.schema");

		bd.whereTextValue = "Mammoth Picnic Shelters";
		bd.parkId = "70920";
		bd.contractCode = "NRSO";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		//site display attributes:
		siteTypes = new String[]{"Picnic(2)"};
		siteTitle = "Day use and Picnic areas";
		siteAmenities = "Has sites with: Accessible, Electric Hook-up(100), Max Occupants(60), Pets Allowed(Domestic).";
		
		//latitude longitude related info.
		distance = (int)(Double.parseDouble(TestProperty.getProperty("unified.search.distance").trim()));
		
		latitude = web.queryLatitudeInfo(schema, bd.parkId);
		longitude = web.queryLongitudeInfo(schema, bd.parkId);
		
		recareaInDB = web.queryNearByRIDBAreas(ridbSchema, latitude, longitude, distance);
		recFacilityInDB = web.queryNearByRIDBFacilities(ridbSchema, latitude, longitude, distance);
		facilityInDB = web.queryNearByFacilities(schema, latitude, longitude, distance);
		facilityInDB = web.queryNearByFacilitiesDisplayOnWebSearch(schema, facilityInDB);
		headerText = "Search Results: 1-10 of " + (recareaInDB.size() + facilityInDB.size() + recFacilityInDB.size());
	}
	
	/**
	 * verify first facility name, park site type tile, amentities and site types.
	 */
	private void verifyFirstFacilityInfo(){
		RecgovViewAsListPage parkViewListPg = RecgovViewAsListPage.getInstance();
		
		//1: verify Mammoth Picnic Shelters display as target result
		parkViewListPg.verifyFirstParkName(bd.whereTextValue);
		
		//2: verify the lable day use and picnic area: displays
		parkViewListPg.verifyFirstParkSiteTypesTitle(siteTitle);		
		
		//3: verify has sites with Electric Hookup(100); Accessible and pets allowed displays
		parkViewListPg.verifyFirstParkAmenities(siteAmenities);	
		
		//4: verify the site type displays as Picnic(2)
		parkViewListPg.verifyParkSiteTypeItems(bd.contractCode, bd.parkId, siteTypes);
	}
	
	/**
	 * verify the check availability button displays below the site types
	 */
	private void verifyCheckAvailabilityDisplayUnderSiteType(){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		boolean flag = searchResult.checkFirstCheckAvailabilityBelowSiteTypes();
		
		if(!flag){
			throw new ErrorOnPageException("The Check availability button should display under Site types section.");
		}
	}
	
	/**
	 * verify the search result list header info match with the given text.
	 * @param expectMsg
	 * @param isHeader
	 */
	private void verifySearchResultSummaryInfo(String expectMsg, boolean isHeader){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String actualMsg = "";
		
		if(isHeader){
			actualMsg = searchResult.getSearchResultsLabel();
		}else{
			actualMsg = searchResult.getViewHeaderNearValue();
		}
		
		if (!actualMsg.equalsIgnoreCase(expectMsg)){
			//loop through the facilities and recareas find out the missing one.
			List<FacilityData> allFacilities = searchResult.getAllFacilityInfo();
			
			//pares the facility info get on the page, group them into Facilities and Recarea.
			for(int i = 0 ; i < allFacilities.size(); i ++){
				if (allFacilities.get(i).productCategory.equalsIgnoreCase("facility")){
					facilityOnPg.add(allFacilities.get(i).facilityID);
				}else if(allFacilities.get(i).productCategory.equalsIgnoreCase("recarea")){
					recareaOnPg.add(allFacilities.get(i).facilityID);
				}else if(allFacilities.get(i).productCategory.equalsIgnoreCase("recfacility")){
					recFacilityOnPg.add(allFacilities.get(i).facilityID);
				}else{
					throw new ErrorOnDataException("The park:" + allFacilities.get(i).facilityName +" get on the page didn't belong to facility or recarea...");
				}
			}
			
			//find out the difference for Contract facilities.
			if(facilityOnPg.size() != facilityInDB.size()){
				this.parseMissingFacilities(facilityOnPg, facilityInDB);
			}
			
			//find out the difference for rec areas
			if(recareaOnPg.size() != recareaInDB.size()){
				this.parseMissingFacilities(recareaOnPg, recareaInDB);
			}
			
			//find out the differences for rec facilities
			if(recFacilityOnPg.size() != recFacilityInDB.size()){
				this.parseMissingFacilities(recFacilityOnPg, recFacilityInDB);
			}
			
			throw new ErrorOnDataException("The expect Message is:" + expectMsg + "; while the current message is:" + actualMsg);
		}
	}
	
	/**
	 * Find out the missing facilities between DB count query and count number displayed on the page.
	 * @param facilityOnPg
	 * @param facilityInDB
	 * @return
	 */
	private List<String> parseMissingFacilities(List<String> facilityOnPg, List<String> facilityInDB){
		List<String> missingFacilities = new ArrayList<String>();
		
		List<String> largeList = null;
		List<String> smallList = null;
		String log = "";
		
		if(facilityOnPg.size() > facilityInDB.size()){
			largeList = facilityOnPg;
			smallList = facilityInDB;
			log = "Facility query missing from DB is:";
		}else{
			largeList = facilityInDB;
			smallList = facilityOnPg;
			log = "Facility query missing on page is:";
		}
		
		//loop the large list analyze the missing facility info.
		for(int i = 0; i < largeList.size(); i ++){
			if (!smallList.contains(largeList.get(i))){
				missingFacilities.add(largeList.get(i));
			}
		}
		for(int i = 0 ; i < missingFacilities.size(); i ++){
			logger.error(log + missingFacilities.get(i));
		}
		
		return missingFacilities;
	}
}
