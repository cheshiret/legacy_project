/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.ticket.searchtourfacility;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Search parent park which hasn't GPS Info and child facility; verify warning message
 * @Preconditions:
 * @SPEC:Search Tours which Target Location With no Longitude and Latitude and no child facilities of tour facility type [TC:042234]
 * @Task#: 	AUTO-1181
 * This case should be run on RFT, cause selenium could not click auto-completed box
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
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		//BELOW DAM SOUTH CAROLINA 
		unifiedSearch.parkId="75452";
		unifiedSearch.whereTextValue="Tongass National Forest";//"Bears Bluff National Fish Hatchery";
//		unifiedSearch.selectAutoCompleteOption=true;
//		unifiedSearch.selectedAutoCompletedOption="Bears Bluff National Fish Hatchery ?, SC";
		unifiedSearch.interestInValue="Tours & Tickets";
		
		warningMsg = "No matching result." +
					 "To help you we've listed everything matching '"+unifiedSearch.interestInValue+"'.";
		heatertext = "All Tours & Tickets Facilities.";
	}
}
