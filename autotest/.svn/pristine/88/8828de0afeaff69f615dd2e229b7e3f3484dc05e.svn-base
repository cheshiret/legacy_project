package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: Verify the indicator will be on the map itself
 * 2: A  Pin Target should NOT display on the map
 * 3: Notification Message 1 displays "Not all facilities displayed. Zoom in for a better view."
 *
 * @Preconditions:
 * "INYO NATIONAL FOREST" is not facility.
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 19, 2011
 */
public class TargetIsNotFacility extends RecgovTestCase {
//	private String expectMsg;
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.verifyPinInfoOnMap();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "INYO NATIONAL FOREST";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "1064";
		
//		expectMsg = "Not all facilities displayed. Zoom in for a better view.";
	}
	
	public void verifyPinInfoOnMap(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		// * 1: Verify the indicator will be on the map itself		
		//* 2: search park should not have a pin on google map for it.
		String pinSrc =  mapPg.getMapPinMarkerSrc(bd.contractCode, bd.parkId);
		mapPg.verifyMapPinExists(pinSrc, false, false);
		// see defect DEFECT-35718, we will ignore default message.
		//* 3: Notification Message 1 displays "Not all facilities displayed. Zoom in for a better view."
//		mapPg.verifyMapStatusMessage(expectMsg);
	}

}
