package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: When you debug this test case, you can't move the mouse pointer..
 * @Description:
 * 1: verify there are 9 pin ICON, and each one's display title when hover over it.
 * 2: verify there are 9 park picture, and each one's display title when hover over it.
 * 3: verify each park picture click will enlarge it. click again will shrink it.
 * @Preconditions:
 * This test case can't debug under Win7 b/c of access id denied error.
 * Make sure the searched facility hasn't lats and longs
 * @SPEC: StoryC
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 13, 2011
 */
public class PhotoImage extends RecgovTestCase{
	private String whereValue , pinTitle;
//	private int parkPicNum, parkPinNum;
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifyPicZoomInAndZoomOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.whereTextValue = "Green Mountain National Forest";//"Yosemite National Park";
		bd.parkId = "1089";//"2991";
		bd.contractCode = "NRSO";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.isUnifiedSearch = true;
		whereValue = bd.whereTextValue;//"Yosemite National Park";
		pinTitle = "View on Map";
		bd.stateCode = "VT";
//		parkPinNum = 10;  // the small pin total number displayed on the page.
//		parkPicNum = 9;   // the park picture info displayed on the right side of the park name.
	}
	
	/**
	 * verify the total near by picture number, verify the zoom in and zoom out function for small picture and large picture.
	 */
	private void verifyPicZoomInAndZoomOut(){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();
		
		//get the first facility name, make sure it matches with the given where value.
		String firstFacility = scResultPg.getFirstParkName();
		if(!firstFacility.startsWith(whereValue)){
			throw new ErrorOnPageException("Search failed, the first facility should be:" + whereValue);
		}
		List<String> pinTitles = scResultPg.getAllPinTitle();
		scResultPg.gotoFirstPage();  //reset to first page after get all park picture info.
		
		List<String> picIds = scResultPg.getAllParkPictureID();
		scResultPg.gotoFirstPage();  //reset to first page after get all park picture info.
		
		List<String> parkNames = scResultPg.getAllParkNames();
		scResultPg.gotoFirstPage();  //reset to first page after get all park name info.
		
		// verify the number of pin Icon is greater than the number of pic number
		if (picIds.size() >= pinTitles.size() ) {
			throw new ErrorOnPageException("The number of displayed pictures: " + picIds.size() + " must be less than " +
					"the number of displayed pin icons:" + pinTitles.size());
		} else {
			// verify pin Icon's title
			for(int i = 1 ; i < pinTitles.size(); i ++){
				if (pinTitle.equalsIgnoreCase(pinTitles.get(i))){
					throw new ErrorOnPageException("The #" + i + " pin hover over title should be: " + pinTitle);
				}
			}
		}
//		//verify there are 9 pin ICON base on the value of "partOfParkNum". 		
//		if(pinTitles.size() != parkPinNum){
//			throw new ErrorOnPageException("The system should diplay " + parkPinNum + " pin ICON.");
//		}else{
//			//verify the 9 pin ICON's title are all in the given value "View on Map".
//			for(int i = 0 ; i < pinTitles.size(); i ++){
//				if (pinTitle.equalsIgnoreCase(pinTitles.get(i))){
//					throw new ErrorOnPageException("The #" + i + " pin hover over title should be: " + pinTitle);
//				}
//			}
//		}
//		
//		//verify there are 9 pictures base on the value of "parkPicNum". 		
//		if(picIds.size() != parkPicNum){
//			throw new ErrorOnPageException("The system should diplay " + parkPicNum + " park pictures.");
//		}
		
		for(int i = 0 ; i <picIds.size(); i ++){
			
			String pictureID = picIds.get(i);
			String enlargedPicID = "popcopy" + pictureID;
			//enlarge the park picture
			scResultPg.enlargeParkPicture(pictureID);
			scResultPg.verifyEnlargedPictureDisplay(enlargedPicID, 5);
			
			//verify the initial small park picture is hidden
			if (!scResultPg.checkInitialParkPictureHidden(pictureID)){
				logger.error("The inital small park picture should be hidden after click it. PictureID:" + pictureID);
				throw new ErrorOnPageException("The inital small park picture should be hidden after click it. PictureID:" + pictureID);
			}else{
				logger.info("Verify the initial small park picture hidden successful after click it.");
			}
			
			//verify the description for the enlarged park picture's title.
			String actualEnlargedPicTitle = scResultPg.getParkPictureTitleInfo(enlargedPicID, true);
//			String expectPicTitle = "Photo: " + parkNames.get(i+1); //remove the first target facility which don't have pic info.
			String expectPicTitle = "Photo: " + parkNames.get(i+1) + "," + bd.stateCode; //remove the first target facility which don't have pic info.
			actualEnlargedPicTitle=actualEnlargedPicTitle.replaceAll(", ",",");
			if(actualEnlargedPicTitle.equalsIgnoreCase(expectPicTitle)){
				logger.info("verify enlarged park picture info successful for:" + expectPicTitle);
			}else{
				logger.error("The current enlarged park picture's title is:" + actualEnlargedPicTitle);
				logger.error("The expect enlarged park picture's title  is:" + expectPicTitle);
				throw new ErrorOnPageException("The current enlarged park picture's title didn't match with the expect value.");
			}
			
			//click the enlarged park picture to shrink it.
			scResultPg.clickParkPicture(enlargedPicID);
			scResultPg.verifyEnlargedPictureHidden(enlargedPicID, 5);			

			//verify the description for the shrink park picture's title
			String actualSmallPicTitle = scResultPg.getParkPictureTitleInfo(pictureID);
			actualSmallPicTitle=actualSmallPicTitle.replaceAll(", ", ",");
			if(!actualSmallPicTitle.equalsIgnoreCase(expectPicTitle)){
				throw new ErrorOnPageException("The current small park picture's title didn't match with the expect value.",expectPicTitle,actualSmallPicTitle);
			}
			logger.info("verify small park picture info successful for:" + expectPicTitle);
			
			//verify the initial small park picture displayed.
			if (!scResultPg.checkInitialParkPictureHidden(pictureID)){
				logger.info("Verify the initial small park picture display successful after click it.");
			}else{
				throw new ErrorOnPageException("The inital small park picture should display after click enlarged park picture.");
			}
		}
	}

}
