package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * verify "Has site with info" display correctly.
 * @Preconditions:
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * NOTE: sql used to check the site attribute info.
 * 
 * 1: for camping park;
  	"select * from P_PRD_ATTR where PRD_ID IN (
	SELECT PRD_ID FROM P_PRD WHERE PARK_ID =  '70948'  AND PRODUCT_CAT_ID = '3' AND IMPORT_RESERVABLE = 'Y' AND IMPORT_WEB_RESERVABLE= 'Y'
	) and attr_id in(12,13,24,32,33,104,218,220,239) order by attr_id;"
	
   2: for Day use park:
   "select * from P_PRD_ATTR where PRD_ID IN (
	SELECT PRD_ID FROM P_PRD WHERE PARK_ID =  '70948'  AND PRODUCT_CAT_ID = '3' AND IMPORT_RESERVABLE = 'Y' AND IMPORT_WEB_RESERVABLE= 'Y'
	) and attr_id in(12,24,32,104,218,220) order by attr_id;"
   
	PARK_ID need to be changed automaticaly.
	attr_id is something configured, no need to update(check Story P -part1 test case for more info)
	------------------------------------------
	select max(attr_value), attr_name from P_PRD_ATTR where PRD_ID IN (
    SELECT PRD_ID FROM P_PRD WHERE PARK_ID =  '70948'  AND PRODUCT_CAT_ID = '3' AND IMPORT_RESERVABLE = 'Y' AND IMPORT_WEB_RESERVABLE= 'Y'
    ) AND ATTR_ID IN(12,218,239) GROUP BY ATTR_NAME
    UNION
    select distinct(attr_value), attr_name from P_PRD_ATTR where PRD_ID IN (
    SELECT PRD_ID FROM P_PRD WHERE PARK_ID =  '70948'  AND PRODUCT_CAT_ID = '3' AND IMPORT_RESERVABLE = 'Y' AND IMPORT_WEB_RESERVABLE= 'Y'
    ) AND ATTR_ID IN(13,24,32,33,104,220) order by attr_name
 * @author bzhang
 * @Date  Oct 27, 2011
 */
public class AllSiteAttributes extends RecgovTestCase {
	private BookingData[] dayUses = new BookingData[2];//Sara[3/25/2014] Pick two parks, one for day use, the other for camping
	private String[] expectAmenities = new String[23];
	
	public void execute() {
		web.invokeURL(url);
		this.verifySiteAttributes(dayUses, expectAmenities);
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		dayUses[0] = new BookingData();
		
		dayUses[0].whereTextValue = "RUJADA DAY USE PICNIC AREA";
		dayUses[0].parkId = "79058";
		dayUses[0].interestInValue = "Day use & Picnic areas";
		expectAmenities[0] = "Max Occupants(30), Pets Allowed(Domestic), Waterfront.";
		
//		dayUses[1] = new BookingData();
//		dayUses[1].whereTextValue = "CHARBONNEAU PK";
//		dayUses[1].parkId = "73119";
//		dayUses[1].interestInValue = "Day use & Picnic areas";
//		expectAmenities[1] = "Accessible, Electric Hook-up(30), Water Hook-up, Max Occupants(150), Pets Allowed(Domestic).";
		
		dayUses[1] = new BookingData();
		dayUses[1].whereTextValue = "CHARBONNEAU PK";
		dayUses[1].parkId = "73119";
		dayUses[1].interestInValue = "Camping & Lodging";
		expectAmenities[1] = "Accessible, Full Hookup(30), Electric Hook-up(50), Water Hook-up, Sewer Hook-up, Max Occupants(150), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through), Waterfront.";
		
//		dayUses[3] = new BookingData();
//		dayUses[3].whereTextValue = "LEECH LAKE REC AREA";
//		dayUses[3].parkId = "73246";
//		dayUses[3].interestInValue = "Camping & Lodging";
//		expectAmenities[3] = "Accessible, Electric Hook-up(50), Water Hook-up, Sewer Hook-up, Max Occupants(50), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
//
//		dayUses[4] = new BookingData();
//		dayUses[4].whereTextValue = "LEECH LAKE REC AREA";
//		dayUses[4].parkId = "73246";
//		dayUses[4].interestInValue = "Day use & Picnic areas";
//		expectAmenities[4] = "Accessible, Electric Hook-up(30), Max Occupants(50), Pets Allowed(Domestic).";
//		
//		dayUses[5] = new BookingData();
//		dayUses[5].whereTextValue = "LEECH LAKE REC AREA";
//		dayUses[5].parkId = "73246";
//		dayUses[5].interestInValue = "Camping & Lodging";
//		dayUses[5].lookFor = "Day use & Picnic areas";
//		expectAmenities[5] = "Accessible, Electric Hook-up(30), Max Occupants(50), Pets Allowed(Domestic), Driveway Entry (Pull-Through).";
//		
//		dayUses[6] = new BookingData();
//		dayUses[6].whereTextValue = "LEECH LAKE REC AREA";
//		dayUses[6].parkId = "73246";
//		dayUses[6].interestInValue = "Camping & Lodging";
//		dayUses[6].lookFor = "Any type of site";
//		expectAmenities[6] = "Accessible, Electric Hook-up(50), Water Hook-up, Sewer Hook-up, Max Occupants(50), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
//		//line 13 over
//		
//		dayUses[7] = new BookingData();
//		dayUses[7].whereTextValue = "LIGHTNING POINT GROUP CG";
//		dayUses[7].parkId = "75443";
//		dayUses[7].interestInValue = "Camping & Lodging";
//		expectAmenities[7] = "Max Occupants(40), Pets Allowed(Domestic; Domestic,Horse).";
//		
//		dayUses[8] = new BookingData();
//		dayUses[8].whereTextValue = "LIGHTNING POINT GROUP CG";
//		dayUses[8].parkId = "75443";
//		dayUses[8].interestInValue = "Camping & Lodging";
//		dayUses[8].lookFor = "Horse sites";
//		expectAmenities[8] = "Max Occupants(40), Pets Allowed(Domestic,Horse).";
//		
//		dayUses[9] = new BookingData();
//		dayUses[9].whereTextValue = "LIGHTNING POINT GROUP CG";
//		dayUses[9].parkId = "75443";
//		dayUses[9].interestInValue = "Camping & Lodging";
//		dayUses[9].lookFor = "RV sites";
//		expectAmenities[9] = "Max Occupants(40), Pets Allowed(Domestic).";
//		
//		dayUses[10] = new BookingData();
//		dayUses[10].whereTextValue = "FISHERMENS BEND";
//		dayUses[10].parkId = "74081";
//		dayUses[10].interestInValue = "Camping & Lodging";
//		dayUses[10].lookFor = "Any type of site";
//		expectAmenities[10] = "Accessible, Full Hookup(100), Electric Hook-up(100), Water Hook-up, Sewer Hook-up, Max Occupants(110), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through), Waterfront.";
//		
//		dayUses[11] = new BookingData();
//		dayUses[11].whereTextValue = "FISHERMENS BEND";
//		dayUses[11].parkId = "74081";
//		dayUses[11].interestInValue = "Camping & Lodging";
//		dayUses[11].lookFor = "RV sites";
//		expectAmenities[11] = "Accessible, Full Hookup(100), Electric Hook-up(100), Water Hook-up, Sewer Hook-up, Max Occupants(12), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
//		
//		dayUses[12] = new BookingData();
//		dayUses[12].whereTextValue = "FISHERMENS BEND";
//		dayUses[12].parkId = "74081";
//		dayUses[12].interestInValue = "Camping & Lodging";
//		dayUses[12].lookFor = "Trailer sites";
//		expectAmenities[12] = "Accessible, Full Hookup(100), Electric Hook-up(100), Water Hook-up, Sewer Hook-up, Max Occupants(12), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
//	
//		dayUses[13] = new BookingData();
//		dayUses[13].whereTextValue = "FISHERMENS BEND";
//		dayUses[13].parkId = "74081";
//		dayUses[13].interestInValue = "Camping & Lodging";
//		dayUses[13].lookFor = "Tent";
//		expectAmenities[13] = "Accessible, Full Hookup(100), Electric Hook-up(100), Water Hook-up, Sewer Hook-up, Max Occupants(12), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through), Waterfront.";
//		
//		dayUses[14] = new BookingData();
//		dayUses[14].whereTextValue = "FISHERMENS BEND";
//		dayUses[14].parkId = "74081";
//		dayUses[14].interestInValue = "Camping & Lodging";
//		dayUses[14].lookFor = "Cabins";
//		expectAmenities[14] = "Accessible, Electric Hook-up(15), Max Occupants(6), Driveway Entry (Back-In).";
//		
//		dayUses[15] = new BookingData();
//		dayUses[15].whereTextValue = "FISHERMENS BEND";
//		dayUses[15].parkId = "74081";
//		dayUses[15].interestInValue = "Camping & Lodging";
//		dayUses[15].lookFor = "Group sites";
//		expectAmenities[15] = "Accessible, Electric Hook-up(100), Water Hook-up, Max Occupants(110), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
//		
//		dayUses[16] = new BookingData();
//		dayUses[16].whereTextValue = "FISHERMENS BEND";
//		dayUses[16].parkId = "74081";
//		dayUses[16].interestInValue = "Camping & Lodging";
//		dayUses[16].lookFor = "Day use & Picnic areas";
//		expectAmenities[16] = "Accessible, Water Hook-up, Max Occupants(110), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
//		
//		dayUses[17] = new BookingData();
//		dayUses[17].whereTextValue = "DINKEY CREEK";
//		dayUses[17].parkId = "70376";
//		dayUses[17].interestInValue = "Camping & Lodging";
//		dayUses[17].lookFor = "Tent-only sites";
//		expectAmenities[17] = "Accessible, Max Occupants(12), Pets Allowed(Domestic), Driveway Entry (Back-In).";
//		
//		dayUses[18] = new BookingData();
//		dayUses[18].whereTextValue = "BEAR BASIN LOOKOUT AND CABIN";
//		dayUses[18].parkId = "75116";
//		dayUses[18].interestInValue = "Camping & Lodging";
//		dayUses[18].lookFor = "Lookouts";
//		expectAmenities[18] = "Accessible, Max Occupants(12), Pets Allowed(Domestic).";
//		
//		dayUses[19] = new BookingData();
//		dayUses[19].whereTextValue = "AGNEW HORSE CAMP";
//		dayUses[19].parkId = "70806";
//		dayUses[19].interestInValue = "Camping & Lodging";
//		dayUses[19].lookFor = "Horse sites";
////		expectAmenities[19] = "Accessible, Pets Allowed, Driveway Entry (Back-In).";   //this is the value given by Lisha which is incorrect.
//		expectAmenities[19] = "Accessible, Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).";
//	
//		dayUses[20] = new BookingData();
//		dayUses[20].whereTextValue = "CHERRY GLEN CAMPGROUND";
//		dayUses[20].parkId = "73120";
//		dayUses[20].interestInValue = "Camping & Lodging";
//		dayUses[20].lookFor = "Any type of site";
//		expectAmenities[20] = "Accessible, Electric Hook-up(50), Water Hook-up, Sewer Hook-up, Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).";
//		
//		dayUses[21] = new BookingData();
//		dayUses[21].whereTextValue = "CHERRY GLEN CAMPGROUND";
//		dayUses[21].parkId = "73120";
//		dayUses[21].interestInValue = "Camping & Lodging";
//		dayUses[21].lookFor = "Boat sites";
//		expectAmenities[21] = "Electric Hook-up(50), Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).";
//		
//		dayUses[22] = new BookingData();
//		dayUses[22].whereTextValue = "FALLEN LEAF CAMPGROUND";
//		dayUses[22].parkId = "71531";
//		dayUses[22].interestInValue = "Camping & Lodging";
//		dayUses[22].lookFor = "Yurts";
//		expectAmenities[22] = "Accessible, Electric Hook-up(30), Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
	}
	
	/**
	 * verify site amenities info based on different park info.
	 * @param dayUseArray
	 * @param aminites
	 */
	private void verifySiteAttributes(BookingData[] dayUseArray, String[] aminites){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();
		
		//initialize booking data arrival date and length of stay
		for(int i = 0 ; i < dayUseArray.length; i ++){
			dayUseArray[i].isUnifiedSearch = this.isUnifiedSearch();
			dayUseArray[i].contractCode = "NRSO";
		}	
		
		for(int i = 0 ; i < dayUseArray.length; i ++){
			this.gotoViewAsListPage(dayUseArray[i]);
			String actualAmenities = scResultPg.getFirstParkAmenities();
			
			//verify amenities info
			if(actualAmenities.equalsIgnoreCase("Has sites with: " + aminites[i])){
				logger.info("Verify amenities successfully for #" + i + " park:" + dayUseArray[i].whereTextValue);
			}else{
				logger.error("Ameinties verification failed for #" + i + " park:" + dayUseArray[i].whereTextValue);
				logger.error("The expect  amenties is: " + "Has sites with: " + aminites[i]);
				logger.error("The current amenties is: " + actualAmenities);
				throw new ErrorOnPageException("Ameinties verification failed for park: " + dayUseArray[i].whereTextValue);
			}
			
		}
		
	}

}
