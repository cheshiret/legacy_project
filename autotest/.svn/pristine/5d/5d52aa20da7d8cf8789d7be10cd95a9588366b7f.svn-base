/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.searchfacility;

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
 * @SPEC:Search Permits which Target Location With no Longitude and Latitude and the child facilities include permit facility [TC:042240]
 * @Task#: 	AUTO-1182
 * 
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class NoGPSFacility_HasChildTourFacility extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	String resultNearTest;
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
		//Inyo National Forest
		unifiedSearch.whereTextValue="Inyo National Forest";
		unifiedSearch.interestInValue="Permits & Wilderness";
		resultNearTest= "All Permits & Wilderness Facilities"; //"Results part of "+unifiedSearch.whereTextValue;
       
		parkList=new ArrayList<String>();
		parkList.add(unifiedSearch.whereTextValue);
		//Inyo National Forest - Wilderness Permits
		parkList.add(DataBaseFunctions.getFacilityName("72203", schema));
		//Mt. WHITNEY
		parkList.add(DataBaseFunctions.getFacilityName("72201", schema));
	}
}
