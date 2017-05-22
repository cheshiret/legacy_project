package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
	1:Enter Desolation Wilderness,CA in Where field and click Search; Click on Pin A	
	2:Verify the map will show the entire continental USA and notification message (message 3) below will be displayed"Search result has no GPS information"  displays on top of the map
 * @Preconditions:
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 20, 2011
 */
public class NoLatLonChild extends RecgovTestCase {
	String message3;
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		// verify notification message (message 3) displayed
		this.verifyNotificationMessage(message3,true);
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "High Uintas Wilderness"; //"DESOLATION WILDERNESS";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "13059";//"12999";
		
		message3 = "Search result has no GPS information.";
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
