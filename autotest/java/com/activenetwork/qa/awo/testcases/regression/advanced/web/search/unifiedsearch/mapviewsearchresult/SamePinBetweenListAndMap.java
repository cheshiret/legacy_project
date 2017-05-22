package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * on the view as list mode Click on the Pin A
 * verify the same pin will be used to identify the facility on the map.
 * verify the first 5 park displayed on view as list page, has the same pin src when switch to map page.
 * @Preconditions:
 * Must run using RFT
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 16, 2011
 */
public class SamePinBetweenListAndMap extends RecgovTestCase {
	
	UwpUnifiedSearch[] unifiedSearch = new UwpUnifiedSearch[5];
	public void execute(){
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifyParkPinInfo();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "NORTH PINES";
		bd.interestInValue = "Camping & Lodging";
		bd.contractCode ="NRSO";
		bd.parkId = "70927";
		
		//FacilityID which PIN number is "A", "B", "C", "D", "E" on the first page. this data may change when the result changed you just need to
		//modify the corresponding facilityID
		unifiedSearch[0] = new UwpUnifiedSearch();
		unifiedSearch[0].parkId = "70927"; //NORTH PINES;  PIN:A
		unifiedSearch[1] = new UwpUnifiedSearch();
		unifiedSearch[1].parkId = "70928"; //LOWER PINES;  PIN:B
		unifiedSearch[2] = new UwpUnifiedSearch();
		unifiedSearch[2].parkId = "70925"; //UPPER PINES;	PIN:C
		unifiedSearch[3] = new UwpUnifiedSearch();
		unifiedSearch[3].parkId = "79064"; //CABLES ON HALF DOME;	PIN:D
		unifiedSearch[4] = new UwpUnifiedSearch();
		unifiedSearch[4].parkId = "70931"; //BRIDALVEIL CREEK GROUP AND HORSE CAMP;	PIN:E
		for(int i = 0 ; i <unifiedSearch.length; i ++){
			unifiedSearch[i].contractCode  = "NRSO";
		}
	}
	
	public void verifyParkPinInfo(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		String pinSrcExpect[] = new String[5];
		//get facility pin picture src on View as list Page.
		for(int i = 0 ; i <unifiedSearch.length; i ++){
			pinSrcExpect[i] = listPg.getMapPinPictureSrc(unifiedSearch[i].contractCode, unifiedSearch[i].parkId);
		}
		//click the first Pin Picture goto map page.
		listPg.clickMapPin(bd.contractCode, bd.parkId);
		mapPg.waitLoading();
		
		//compare the Pin src info between map page and view as list page for same park.
		for(int i = 0 ; i <unifiedSearch.length; i ++){
			mapPg.verifyMapPinPictureInfo(unifiedSearch[i].contractCode, unifiedSearch[i].parkId, pinSrcExpect[i]);
		}
	}
}
