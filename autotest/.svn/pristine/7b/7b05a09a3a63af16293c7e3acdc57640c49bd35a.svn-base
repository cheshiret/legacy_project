/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.searchfacility;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Search parent park which hasn't GPS Info and child facility; verify warning message
 * @Preconditions:
 * @SPEC:Search Permits which Target Location With no Longitude and Latitude and no child facilities of permit facility type [TC:042241]
 * @Task#: 	AUTO-1182
 * Tips: if the site is not matched the requirement, query by the sql:
 * select * from recarea where recarealatitude is null and recarealongitude is null and 
 *	recareaid in (select recareaid from recareafacilitymapping 
 * where facilityid in (select loc_id from LIVE_NRRS.d_loc_prd_cat where prd_grp_cat_id=5));
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class NoGPSFacility_NoChildTourFacility extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	String warningMsg;
	String heatertext;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(unifiedSearch);
		
		listPage.verifyNoMapPinExist(unifiedSearch.whereTextValue);//make sure it is no GPS
		listPage.verifyWarningMes(warningMsg);
		listPage.verifyResultNearHeaderText(heatertext);
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		url= TestProperty.getProperty(env + ".web.recgov.url");
	

		unifiedSearch.whereTextValue="Idaho Panhandle National Forests"; //"Poplar Grove National Cemetery"; //"Cleveland National Forest";//"Bears Bluff National Fish Hatchery";
		unifiedSearch.interestInValue="Permits & Wilderness";
		
		warningMsg = "No matching result.To help you we've listed everything matching '"+unifiedSearch.interestInValue.replaceAll("&", "\\\\&")+"'.";
		heatertext = "All "+unifiedSearch.interestInValue+" Facilities.";
	}
}
