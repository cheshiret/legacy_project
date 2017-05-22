package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: Hover(mouse) over the Change Map Style indicator(below the previous/next links)	
 * 2: Change map style displays text displays
   3: Click on Change Map Style indicator drop-down
	   	Map option displays
	 	Satellite option displays
	 	Terrain check box displays
   4: Hover(mouse) over the Map option	Show street map text displays
   5: Hover(mouse)  over the Satellite option	Show satellite imagery text displays
   6: Hover(mouse)  over the Terrain check box	Show street map with terrain text displays

 * @Preconditions:
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 20, 2011
 */
public class MapStyleIndicator extends RecgovTestCase {
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.verifyMapIndicatorItems();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "ARCHES NATIONAL PARK";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "2573";
	}
	
	public void verifyMapIndicatorItems(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		// Hover(mouse) over the Change Map Style indicator(below the previous/next links)
		// Change map style displays text displays
		mapPg.verifyChangeMapStyleDisplayText();
		//	verify items in the Change Map Style indicator drop-down
		//	Map option displays,Hover(mouse) over the Map option	Show street map text displays
		mapPg.verifyMapItemExistInDDList();
		//	Satellite option displays, Hover(mouse)  over the Satellite option	Show satellite imagery text displays
		mapPg.verifySatelliteItemExistInDDList();
		//	Terrain check box displays,Hover(mouse)  over the Terrain check box	Show street map with terrain text displays
		mapPg.verifyTerrainCheckBoxExistInDDList();
		// Click on Satellite option	Map changes to Satellite view
		mapPg.clickChangeMapStyleDropdownList();
		mapPg.selectSatellite();
		// Hover(mouse)  over Labels	Show imagery with street names text displays
		mapPg.verifyLabelsCheckBoxExistInDDList();
		mapPg.removeFocus();
		//	Labels is checked by default and is changeable
		mapPg.uncheckLabelsForSatellite();
		mapPg.removeFocus();
		mapPg.checkLabelsForSatellite();
	}

}
