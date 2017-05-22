package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
1: Enter in Death Valley Wilderness, CA in Where form and click Search; Click on View as Map	
2: Notification Message 3 "Search result has no GPS information" displays
3: Zoom in(+) once	
4: Notification Message 1  "Not all facilities displayed. Zoom in for a better view" still displays
5: Zoom in(+) a second time	
6: Notification Message 1  "Not all facilities displayed.  Zoom in for a better view" DOES NOT DISPLAY ANY MORE 
7: Zoom in(+) a third time	
8: Pins start displaying on the map

 * @Preconditions:
 * must run under RFT
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 20, 2011
 */
public class PinNumberExceedsMaximum extends RecgovTestCase {
	String message3, message1;
	
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.verifyNotificationMessage();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "DEATH VALLEY WILDERNESS";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "12855";
		
		message3 = "Search result has no GPS information.";
		message1 = "Not all facilities displayed. Zoom in for a better view.";
	}
	
	public void verifyNotificationMessage(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
//		1: Enter in Death Valley Wilderness, CA in Where form and click Search; Click on View as Map	
//		2: Notification Message 3 "Search result has no GPS information" displays
		this.verifyNotificationMessage(message3, true);
		
//		3: Zoom in(+) once	
//		4: Notification Message 1  "Not all facilities displayed. Zoom in for a better view" still displays
		mapPg.clickZoomIn();
		mapPg.waitMapLoadingComplete();
		this.verifyNotificationMessage(message1, true);
		
//		5: Zoom in(+) a second time	
//		6: Notification Message 1  "Not all facilities displayed.  Zoom in for a better view" DOES NOT DISPLAY ANY MORE 
		mapPg.clickZoomIn();
		mapPg.waitMapLoadingComplete();
		this.verifyNotificationMessage(message1, true);
//		7: Zoom in(+) a third time	
		
		//Sara[20140303], 2014 map uses HTML5, Selenium can't identify the small pin
//		8: Pins start displaying on the map
//		mapPg.clickZoomIn();
//		mapPg.waitMapLoadingComplete();
//		this.verifyNotificationMessage(message1, false);
//		if(mapPg.checkMapPinExists("")){
//			logger.info("verify pin start displaying on the map successfully.");
//		}else{
//			throw new ErrorOnPageException("verify pin start displaying on the map failed.");
//		}
		
		
	}
	
	public void verifyNotificationMessage(String expectMessage, boolean display){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		String currentMsg = mapPg.getMapStatusMessage();
		
		if(display){
			if(currentMsg.equalsIgnoreCase(expectMessage)){
				logger.info("verify map status message successfully.");
			}else{
				logger.error("Current message is:" + currentMsg);
				logger.error("Expect  message is:" + expectMessage);
				throw new ErrorOnPageException("The expect error message did NOT display on the page.");
			}
		}else{
			if(currentMsg.equalsIgnoreCase(expectMessage)){
				logger.error("Current message is:" + currentMsg);
				logger.error("Expect  message is:" + expectMessage);
				throw new ErrorOnPageException("The expect error message did NOT display on the page.");
			}else{
				logger.info("verify map status message successfully.");
			}
		}
		
	}

}