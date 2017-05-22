package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CampgroundPictureVerification_KY extends WebTestCase
{
	/**
	 * Verify the pictures for Campground.
	 * @author vzhao
	 */
  	private String url, parkID;
  
	public void execute() {
		web.invokeURL(url);
		web.gotoParkList(bd); //go to park search result page
		web.verifyParkPictures(bd.contractCode, parkID, 1);//1 picture in park search.
		
		web.gotoCampgroundDetailsPg(bd); //go to park details page
		web.verifyParkPictures(bd.contractCode, parkID, 5);//5 picture in park details.
		
		web.searchSiteFromSiteListToSiteDetails(bd);// go to site details page
		web.verifyParkPictures(bd.contractCode, parkID, 1);//1 picture in site details.
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ky.url");
	
		bd.state = "Kentucky";
		bd.park = "GENERAL BURNSIDE STATE PARK";
		parkID = "91811";//should be updated if bd.park changed
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "KY";
		bd.ignoreStatus = true;
		
		bd.loop = "Sites 1-15, 51-94";
		bd.siteNo = "002";
		bd.siteID="1513";
	}
}
