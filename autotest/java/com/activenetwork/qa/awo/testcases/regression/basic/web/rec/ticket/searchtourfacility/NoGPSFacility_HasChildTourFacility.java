/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.ticket.searchtourfacility;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Search parent park which hasn't GPS Info, but has child facility;
 * @Preconditions:
 * @SPEC:Search Tours which Target Location With no Longitude and Latitude and the child facilities include tour facility [TC:042233]
 * @Task#: 	AUTO-1181
 * This case should be run on RFT, cause selenium could not click auto-completed box
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class NoGPSFacility_HasChildTourFacility extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	String resultNearTest;
	String childPark="";
	List<String> parkList;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(unifiedSearch);
		
		listPage.verifyNoMapPinExist(unifiedSearch.whereTextValue);//make sure it is no GPS
		listPage.verifyParkList(parkList);
		listPage.verifyResultNearHeaderText(resultNearTest);
		
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		url= TestProperty.getProperty(env + ".web.recgov.url");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);

		unifiedSearch.whereTextValue="Willamette National Forest";//"Death Valley National Park";
		unifiedSearch.interestInValue="Tours & Tickets";
		resultNearTest="All Tours & Tickets Facilities"; //"Results part of "+unifiedSearch.whereTextValue;
		//Scotty's Castle - Death Valley National Park
//		childPark=DataBaseFunctions.getFacilityName("72291", schema);
		childPark=DataBaseFunctions.getFacilityName("72411", schema); // SWEET HOME HERITAGE HIKES
		parkList = new ArrayList<String>();
		parkList.add(unifiedSearch.whereTextValue);
		parkList.add(childPark);
	}
}
