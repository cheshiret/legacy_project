package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CampgroundPictureVerification extends WebTestCase
{
	/**
	 * Verify the picture in campground details page.
	 * @author vzhao
	 */
  	private String url, parkID;
  
	public void execute() {
		web.invokeURL(url,false);
		web.gotoParkList(bd); //go to park search result page
		web.verifyParkPictures(bd.contractCode, parkID, 1);//1 picture in park search.
		
		web.gotoCampgroundDetailsPg(bd); //go to park details page
		web.verifyParkPictures(bd.contractCode, parkID, 7);//7 picture in park details.//Vivian[2014/05/20]
		
		web.searchSiteFromSiteListToSiteDetails(bd);// go to site details page
		web.verifyParkPictures(bd.contractCode, parkID, 2);//2 picture in site details.//Quentin[20131217]
	}
	
	public void wrapParameters(Object[] param) {
		if(env.equalsIgnoreCase("live")) 
			AwoUtil.loadLiveInformation();
		url = TestProperty.getProperty(env + ".web.ra.url");
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
		
		bd.whereTextValue = bd.park;
		bd.interestInValue = "Camping & Lodging";
	}
}
