package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * Panning(Browsing) on the Map
	Enter Tongass National Forest,AK in Where field and click Search
	Click on View as Map tab
	(Hover) Mouse over the Pan Indicator(Hand) in the top left hand corner of the map
	(Hover) Mouse over the Top Up arrow indicator
	Click on Pan Up arrow
	(Hover) Mouse over the Bottom Down indicator
	Click on Bottom down arrow
	(Hover) Mouse over the Pan Right indicator
	Click on Pan Right indicator 
	(Hover) Mouse over the Pan Left indicator
	Click on Pan Left indicator
	
	Zoom In Indicator(+)
	(Hover) Mouse over Zoom In(+) indicator
	Click on Zoom In indicator
	Zoom Out Indicator(-)
	(Hover) Mouse over Zoom Out(-) indicator
	Click on Zoom Out indicator
 * @Preconditions:
 There are only two Pin on the map one is "B" the other is "A" based on the where and intested value we entered.

 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 12, 2011
 */
public class PanningIndicator extends RecgovTestCase {
	RecgovViewAsListPage parkLilstPg = RecgovViewAsListPage.getInstance();
	BookingData bd1 = new BookingData();
	BookingData bd2 = new BookingData();
	String parkId_B;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsListPage(bd); 
		parkId_B = DataBaseFunctions.getFacilityID(parkLilstPg.getSecondParkName(), schema);
		web.gotoViewAsMapFromViewAsList();
		
//		web.gotoViewAsMapPage(bd);		
		this.verifyPanningHoverOverText();
		this.verifyInitialDataOnViewAsMap();
		this.verifyPanLeftRightFunction();
		web.gotoViewAsMapPage(bd);	
		this.verifyPanUpDownFunction();
		web.gotoViewAsMapPage(bd);	
		this.verifyZoomInOutFunction();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		//PanA
		bd.whereTextValue = "FIERY FURNACE TOUR - ARCHES";
		bd.interestInValue = "Permits & Wilderness";
		bd.contractCode ="NRSO";
		bd.parkId = "93768";
		
		// Pan B
		bd2.parkId = "75534";
		bd2.contractCode = "NRSO";
		bd2.whereTextValue = "MIDDLE FORK OF THE SALMON";
	}
	 
	
	/**
	 * verify the display text for panning indicator when mouse over it.
	 */
	private void verifyPanningHoverOverText(){
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		unifiedMapPg.verifyPanDownText();
		unifiedMapPg.verifyPanLeftText();
		unifiedMapPg.verifyPanRightText();
		unifiedMapPg.verifyPanUpText();
		unifiedMapPg.verifyZoomInText();
		unifiedMapPg.verifyZoomOutText();
	}
	
	private void verifyInitialDataOnViewAsMap(){
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
//		if(!unifiedMapPg.checkMapPinExists("B")||!unifiedMapPg.checkMapPinExists("A")){
		if(!unifiedMapPg.checkMapPinExists(bd.contractCode, parkId_B)||!unifiedMapPg.checkMapPinExists(bd.contractCode, bd.parkId)){
		throw new ErrorOnDataException("The inital data didn't meet the prerequisiste, please see prerequisiste sections for more info.")	;
		}
	}
	
	/**
	 * pan left/right move the map to the left/right.
	 */
	private void verifyPanLeftRightFunction(){
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		unifiedMapPg.clickPanLeft();
		unifiedMapPg.waitMapLoadingComplete();
		//click the pin, if pin bubble don't exist then verify pan left/right works as expected
		unifiedMapPg.clickMapPin(bd.contractCode, bd.parkId);
		if(unifiedMapPg.checkMapBubbleExist(bd.whereTextValue)){
			throw new ErrorOnDataException("Pan up could not work as expected! Pin\"A\" still displayed on the map.")	;
		}
		
		unifiedMapPg.clickPanRight();
		unifiedMapPg.waitMapLoadingComplete();
		unifiedMapPg.clickMapPin(bd.contractCode, bd.parkId);
		unifiedMapPg.mapBubbleWidgetWaitExists();
		if(!unifiedMapPg.checkMapBubbleExist(bd.whereTextValue)){
			throw new ErrorOnDataException("Pan down could not work as expected! Pin\"A\" should be displayed on the map.")	;
		}
		
		unifiedMapPg.clickViewAsList();
		parkLilstPg.waitLoading();
	}
	
	/**
	 * pan left will move the map to the left.
	 */
	private void verifyPanUpDownFunction(){
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
	
		unifiedMapPg.clickPanDown();
		unifiedMapPg.waitMapLoadingComplete();
		//click the pin, if pin bubble don't exist then verify pan up/down works as expected
		unifiedMapPg.clickMapPin(bd2.contractCode, bd2.parkId);
		if(unifiedMapPg.checkMapBubbleExist(bd2.whereTextValue)){
			throw new ErrorOnDataException("Pan up could not work as expected! Pin\"B\" still displayed on the map.")	;
		}
		
		unifiedMapPg.clickPanUp();
		unifiedMapPg.waitMapLoadingComplete();
		unifiedMapPg.clickMapPin(bd2.contractCode, bd2.parkId);
		unifiedMapPg.waitMapLoadingComplete();
		if(!unifiedMapPg.checkMapBubbleExist(bd2.whereTextValue)){
			throw new ErrorOnDataException("Pan down could not work as expected! Pin\"B\" should be displayed on the map.")	;
		}
		unifiedMapPg.clickViewAsList();
		parkLilstPg.waitLoading();
	}
	
	/**
	 * pan left will move the map to the left.
	 */
	private void verifyZoomInOutFunction(){
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		unifiedMapPg.clickZoomIn();
		unifiedMapPg.waitMapLoadingComplete();
		unifiedMapPg.clickZoomIn();
		unifiedMapPg.waitMapLoadingComplete();
		//click the pin, if pin bubble don't exist then verify zoom in works as expected
		unifiedMapPg.clickMapPin(bd.contractCode, bd.parkId);
		if(unifiedMapPg.checkMapBubbleExist(bd.whereTextValue)){
			throw new ErrorOnDataException("Pan up could not work as expected! Pin\"A\" still displayed on the map.")	;
		}
		//comment below part because it's very un-stable, always failed by dynamic park amounts changed
//		unifiedMapPg.clickZoomIn();
//		unifiedMapPg.waitMapLoadingComplete();
//		unifiedMapPg.clickMapPin(bd2.contractCode, bd2.parkId);
//		if(unifiedMapPg.checkMapBubbleExist(bd2.whereTextValue)){
//			throw new ErrorOnDataException("Pan up could not work as expected! Pin\"B\" still displayed on the map.")	;
//		}
		
		web.gotoViewAsMapPage(bd);	
		unifiedMapPg.clickZoomIn();
		unifiedMapPg.waitMapLoadingComplete();

		unifiedMapPg.clickZoomOut();
		unifiedMapPg.waitMapLoadingComplete();
//		if(!unifiedMapPg.checkMapPinExists("B")||!unifiedMapPg.checkMapPinExists("A")){
		if(!unifiedMapPg.checkMapPinExists(bd.contractCode, parkId_B)||!unifiedMapPg.checkMapPinExists(bd.contractCode, bd.parkId)){
			throw new ErrorOnDataException("Zoom out could not work as expected! Pin\"A\"  and \"B\"  should be displayed on the map.")	;
		}
		
		unifiedMapPg.clickViewAsList();
		parkLilstPg.waitLoading();
	}

}
