package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
  1:On Bubble Verify the message displays as First Come First Served at the Facility
  2:Availability Details link displays

 * @Preconditions:
 * 1: the search date is within minimum window of the facility.
 * 2: the facility must setup a minimum window.
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 22, 2011
 */
public class FirstcomeFirstServed extends RecgovTestCase {
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.verifyFirstComeFirstServedPinInfo();
	}
	
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "BOYKIN SPRINGS RECREATION AREA";
		bd.park = bd.whereTextValue;
		bd.contractCode ="NRSO";
		bd.parkId = "72156";
		
		bd.interestInValue = "Day use & Picnic areas";
		bd.arrivalDate = DateFunctions.getToday(); //make sure the arrival date is within minimum window
		bd.flexibleValue = "Not Flexible";
		bd.lengthOfStay = "1";
	}
	
	public void verifyFirstComeFirstServedPinInfo(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		//click on the Pin and verify the button is Book Sites
		mapPg.clickMapPin(bd.contractCode, bd.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		
		// 1:On Bubble Verify the message displays as First Come First Served at the Facility
		mapPg.waitForParkNameInWidget(bd.whereTextValue);
		mapPg.verifyFirstComeFirstServedExist();
		//	 2:Availability Details link displays	
		mapPg.verifyAvailabilityDetailsExist();
		
		//close bubble and clear search.
		mapPg.closeMapBubbleWidget();
	}
}
