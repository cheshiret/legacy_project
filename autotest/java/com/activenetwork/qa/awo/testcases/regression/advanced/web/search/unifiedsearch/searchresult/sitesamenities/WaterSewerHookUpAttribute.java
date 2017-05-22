package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Water Sewer Hook-UP
 * Common Site Attributes (Has sites with: \ufffd) information based on availability 
 * 1:  Enter "Sugar Bottom" in Where field, select "Camping & Lodging" from "Interested in", select "RV sites" from "Looking for", check "Accessibility needs", click Search
 * (the facility only has 3 accessible RV sites among which only 1 has water and sewer Hook-up )
 * 2:  Enter a sample date, enter "1" in Length of stay, click Search
 * (Sample date: The only accessible and water/sewer Hook-up RV site is reserved on this date)
 * 
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: all Water Hook-up, and Sewer Hook-up will be booked/already occupied before we run this script.
 * 
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * 
 * @author bzhang
 * @Date  Oct 28, 2011
 */

public class WaterSewerHookUpAttribute extends RecgovTestCase {
	private String attributes;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		
		web.invokeURL(url);
		web.signIn(email, pw);
		web.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributes,true);

		web.makeReservationToCart(bd);
		web.checkOutCart(pay);
		
		web.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributes,false);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		attributes = "Water Hook-up, Sewer Hook-up";

		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "Sugar Bottom";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "RV sites";
		bd.accessibilityNeeds = true;
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "1";

		//Booking data info for only one water/sewer Hook-up site.
		bd.park = bd.whereTextValue.toUpperCase();
		bd.parkId = "73425";
		bd.contractCode = "NRSO";
		bd.siteNo = "304";// this is the first Electric Hookup site that we need to book;
		bd.siteID = "123052";
		bd.siteType = String.valueOf(web.getSiteRelationTypeID(bd.siteID, schema));
		bd.clickParkName = true;
	}

	/**
	 * verify site amenities info based on different park info.
	 * @param dayUseArray
	 * @param aminites
	 */
	private void verifySiteAttributes(String aminites, boolean withAttribute){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();

		String actualAmenities = scResultPg.getFirstParkAmenities();
		boolean flag = actualAmenities.contains(aminites);

		//verify amenities info
		if(withAttribute){
			//check site has the specified attribute info
			if(flag){
				logger.info("Verify amenities successfully for park:" + bd.whereTextValue);
			}else{
				logger.error("Ameinties verification failed for park:" + bd.whereTextValue);
				throw new ErrorOnPageException("Expect ameinties should contain " + aminites);
			}
		}else{
			//check site DON'T has the specified attribute info
			if(!flag){
				logger.info("Verify amenities successfully for park:" + bd.whereTextValue);
			}else{
				logger.error("Ameinties verification failed for park:" + bd.whereTextValue);
				throw new ErrorOnPageException("Expect ameinties should NOT contain " + aminites);
			}
		}
	}
}
