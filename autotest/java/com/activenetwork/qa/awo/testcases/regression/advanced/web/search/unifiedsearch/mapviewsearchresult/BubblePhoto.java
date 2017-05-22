/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * Click on the photo displaying in the top right hand corner,Verify the photo expands
 * Click on the image to shrink it,Verify the photo collapses
 * Click on the Check Availability button,Verify the Site List page for North Pines displays

 * @Preconditions:
 * must run via RFT;
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 20, 2011
 */
public class BubblePhoto extends RecgovTestCase {
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.verifyPicZoomInAndZoomOut();
		this.verifyCheckAvailabiltyRedirect();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "North Pines";
		bd.interestInValue = BookingData.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "70927";
	}
	
	/**
	 * verify the total near by picture number, verify the zoom in and zoom out function for small picture and large picture.
	 */
	private void verifyPicZoomInAndZoomOut(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		mapPg.clickMapPin(bd.contractCode, bd.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		
		//enlarge the park picture
		mapPg.waitForSmallPicture(bd.parkId);
		mapPg.clickParkSmallPicture(bd.parkId);
		mapPg.waitEnlargedPictureDisplay(bd.parkId);
		
		//verify the initial small park picture is hidden
		mapPg.verifySmallParkPictureHidden(bd.parkId);
		
		//click the enlarged park picture to shrink it.
		mapPg.clickParkEnlargedPicture(bd.parkId);
		mapPg.waitEnlargedPictureHidden(bd.parkId)	;	

		//verify the initial small park picture displayed.
		mapPg.verifySmallParkPictureDisplay(bd.parkId);
	}
	
	private void verifyCheckAvailabiltyRedirect(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
		
		mapPg.waitForParkNameInWidget();
		mapPg.clickCheckAvailabilityInWidget();
		campCommonPg.waitLoading();
	}
}
