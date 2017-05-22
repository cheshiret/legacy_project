package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindPermitsPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
	1: Enter in Anacapa Island: Interested in Any Recreation and click Search	Click on View as Map	
	2: Verify Recreation Facilities D (Outdoor Santa Barbara Visitor Center) and G(Ronald Reagan Presidential Library and Museum,CA) display on the map
	3: Verify T displays on the map for Carrizo Plain National Monument,CA
	4: Click on Red T, verify	Bubble open for Carrizo Plain National Monument
	5: Close the Bubble for T	
	6: Click on any of the small blue pins	Verify the bubble opens up for a Camping or Day Use pin
	7: Close the Bubble for the small blue pin	
	8: Click on any small green pin(recreation facility)	Verify the bubble opens up for a Recreation Facility
	9: Close the bubble	
	10: Pan up on the map to a Purple P(Permit Facility in Yosemite) and click on it	Verify the bubble opens for a Permit Facility(Cables on Half Dome,CA)

 * @Preconditions:
 *  1: run using RFT
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 22, 2011
 */
public class OtherFacilitiesAndAreas extends RecgovTestCase {
	BookingData bd2 = new BookingData();
	
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.operationOnViewAsMap();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "ANACAPA ISLAND";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "70984";
		
		bd2.isUnifiedSearch = this.isUnifiedSearch();
		bd2.whereTextValue = "PFEIFFER LAKE";
		bd2.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd2.contractCode = "NRSO";
		bd2.parkId = "70373";
	}
	
	public void operationOnViewAsMap(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		RecgovParkDetailsPage detailsPg = RecgovParkDetailsPage.getInstance();
		UwpRecreationAreaDetailsPage recPg = UwpRecreationAreaDetailsPage.getInstance();
		UwpTourSearchPanel tourPanel = UwpTourSearchPanel.getInstance();
		UwpUnifiedFindPermitsPanel permitPanel = UwpUnifiedFindPermitsPanel.getInstance();
		
		//2: Verify Recreation Facilities D (Outdoor Santa Barbara Visitor Center) and G(Ronald Reagan Presidential Library and Museum,CA) display on the map
		mapPg.waitFirstMapPinDisplays(bd.contractCode, StringUtil.EMPTY);
		if(mapPg.checkMapPinExists("D") &&	mapPg.checkMapPinExists("G")){
		}else{
			throw new ErrorOnPageException("Verify Recreation Area D and G pin failed");
		}
		
		//click on any of the small blue pin, verify the bubble opens up for a camping or Day Use pin
		mapPg.clickSmallCampingPin();	
		mapPg.mapBubbleWidgetWaitExists();
		
		mapPg.waitForParkNameInWidget();
		mapPg.clickParkNameInWidget();
		detailsPg.waitLoading();
		
		//click on any of the small green pin, verify the bubble opens up for a Recreation Facility
		web.gotoHomePage();
		web.gotoViewAsMapPage(bd);
		
		mapPg.clickSmallRecreationPin();	
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.waitForParkNameInWidget();
		mapPg.clickParkNameInWidget();
		recPg.waitLoading();
		
		//click the red small pin for tour park. verify bubble opens for a tour facility
		web.gotoHomePage();
		web.gotoViewAsMapPage(bd);
		mapPg.clickZoomOut();
		mapPg.waitMapLoadingComplete();
		mapPg.waitLoading();
		
		mapPg.clickSmallTourPin();
		mapPg.mapBubbleWidgetWaitExists();
		
		mapPg.waitForParkNameInWidget();
		mapPg.clickParkNameInWidget();
		tourPanel.waitLoading();
		
		//verify the permit small pin.
		web.gotoHomePage();
		web.gotoViewAsMapPage(bd2);
		mapPg.waitFirstMapPinDisplays(bd.contractCode, StringUtil.EMPTY);
		mapPg.waitLoading();
		
		mapPg.clickZoomOut();
		mapPg.waitLoading();
		mapPg.clickZoomOut(); //Lesley[20131119]: need to zoom out due to many search results in 3.05.00
		mapPg.waitLoading();
		mapPg.clickSmallPermitPin();
		mapPg.mapBubbleWidgetWaitExists();
		
		mapPg.waitForParkNameInWidget();
		mapPg.clickParkNameInWidget();
		permitPanel.waitLoading();
	}
}
