package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * If the park is available for search criteria, pin will be Blue.
 * If the park is unavailable for search criteria, pin will be Brown.
 * @Preconditions:
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 16, 2011
 */
public class PinAvailabilityVerification extends RecgovTestCase {

	public void execute(){
		web.invokeURL(url);
		this.verifyParkPinInfo();
	}
	
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "BOYKIN SPRINGS RECREATION AREA";
		bd.park = bd.whereTextValue;
		bd.contractCode ="NRSO";
		bd.parkId = "72156";
		
		bd.siteNo = "001";
		bd.siteID = "300491";
		
		bd.interestInValue = "Day use & Picnic areas";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5); //make sure the arriale date have available site.
		bd.flexibleValue = "Not Flexible";
		bd.lengthOfStay = "1";
	}
	
	public void verifyParkPinInfo(){
		//1: If the park is available for search criteria, pin will be Blue. the button will be "Book Sites"
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		this.operateParkPin(bd, "Book Sites");
		
		// 2: If the park is not available for search criteria, pin will be Brown. the button will be "Next available Date"
		web.signIn(email, pw);
		web.makeReservationToCart(bd);  // book the site to order cart make it become unavailable
		this.operateParkPin(bd, "Next Available Date");
	}
	
	public void operateParkPin(BookingData tempbd, String parkStatus){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		
		web.gotoViewAsMapPage(tempbd);
		
		if(parkStatus.equalsIgnoreCase("Book Sites")){
			if(!mapPg.checkMapPinMatched("A")){//"A" is the pin picture displayed char. these value may change, it will not affect this script if we change it.
				throw new ErrorOnPageException("The park pin should be in the char of \"A\" display as Blue, as it is the first park we get by search criteria.");
			}	
		}else if(parkStatus.equalsIgnoreCase("Next Available Date")){
			if(!mapPg.checkMapPinNoMatched("A")){//"A" is the pin picture displayed char. these value may change, it will not affect this script if we change it.
				throw new ErrorOnPageException("The park pin should be in the char of \"A\" display as Brown, as it is the first park we get by search criteria.");
			}	
		}else{
			throw new ErrorOnDataException("The current park Status should only support \"Book Sites\" and \"Next Available Date\".");
		}
		//click on the Pin and verify the button is Book Sites
		mapPg.clickMapPin(tempbd.contractCode, tempbd.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		//check the bubble is the park we searched for
//		mapPg.checkMapBubbleExist(tempbd.whereTextValue);
		//check the park status is "Book Sites"
		
		if(mapPg.checkBookSitesButtonExist(parkStatus)){
			logger.info("verify park availability button successfully with text \"" + parkStatus + "\"");
		}else{
			throw new ErrorOnPageException("The Park Pin popup widget should have a \"" + parkStatus + "\" button based on search criteria");
		}
		
		//close bubble and clear search.
		mapPg.closeMapBubbleWidget();
		mapPg.clickViewAsList();
		searchPanel.clickClearSearch();
	}

}
