/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.pages.web.ra.RAParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpGoogleStateMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify the Google map image in camp ground details page;
 * @Preconditions:
 * @SPEC: 
 * Click the Google map image [TC:025256];
 * Check "alt" and "title" attribute of the Google map image [TC:025252]; 
 * Check location of Google Map image (link to Regional Map) [TC:020502]
 * @Task#:AUTO-1183
 * 
 * @Author asun
 * @Date Aug 15, 2012
 */
public class GoogleMapImageDisplaying extends RATestCase {
	RAParkDetailsPage campDetails = RAParkDetailsPage.getInstance();
	UwpGoogleStateMapPage mapPage = UwpGoogleStateMapPage.getInstance();
	String titleOrAltInfo, linkText;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		campDetails.verifyFindOtherCampgroundsLinkExist();
		campDetails.verifyGetDirectionsLinkExist();
		//if (UIOptionConfigurationUtil.isPLFacilityMapShownAsLink(bd.contractCode)) {
		if(WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.PLFacilityMapShownAsLink,bd.contractCode)) {
			campDetails.verifyMapLinkExist();
			campDetails.verifyMapLinkText(linkText);
			this.gotoStateMapPageByClickingMapLinkInCampgroundDetailsPage();
		} else {
			campDetails.verifyGoogleMapExist();
			campDetails.verifyGoogleMapTitle(titleOrAltInfo);
			campDetails.verifyGoogleMapAlt(titleOrAltInfo);
			this.gotoStateMapPageByClickingMapImageInCampgroundDetailsPage();
		}	
		this.verifyTargetFacility(bd.park);

	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ne.url"); //".web.ny.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		bd.contractCode = "NE"; //NY";
		schema = DataBaseFunctions.getSchemaName(bd.contractCode, env);
		
		// ALGER ISLAND
		bd.park = DataBaseFunctions.getFacilityName("230275", schema);//695
		/** this is a gps info for park "AIKEN", So it is very stable; **/
		this.titleOrAltInfo = "View Regional Map - GPS: 43.7457833, -74.8745861";
		linkText = "View Regional Map";
//		bd.whereTextValue = bd.park;
//		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
	}

	private void gotoStateMapPageByClickingMapImageInCampgroundDetailsPage() {
		logger.info("go to Campground Map Page By Clicking Map Image In Campground Details Page.");
		campDetails.clickMapImage();
		campDetails.waitForProgressBarDisapear();
		mapPage.waitLoading();
	}

	private void gotoStateMapPageByClickingMapLinkInCampgroundDetailsPage() {
		logger.info("go to Campground Map Page By Clicking Map Link In Campground Details Page.");
		campDetails.clickMapLink();
		campDetails.waitForProgressBarDisapear();
		mapPage.waitLoading();
	}
	/**
	 * @param park
	 */
	private void verifyTargetFacility(String park) {
		String targetParkOnPage = mapPage.getChosenParkFromleftMapBrowser();
		park += ", ?" + bd.contractCode;

		logger.info("Verify target park is " + park);
		if (!targetParkOnPage.matches(park)) {
			throw new ErrorOnPageException("targer park is wrong.", park,
					targetParkOnPage);
		}

	}

}
