package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.pages.web.plw.PLWParkListPage;
import com.activenetwork.qa.awo.pages.web.ra.RAParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.ra.RAParkDetailsPageForCampAndMarina;
import com.activenetwork.qa.awo.pages.web.ra.RASiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.ra.RAViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsByStatePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsMapBrowsePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsMapSearchPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpParkDetailsCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpProductDetailsCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpProductListCommonPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43256) Setup site facility and site photo in Photo Manager, then check them can be displayed in 
 *                            campground map browse, campground map search, campground directory, park list, park details, site details
 * @Preconditions:
 * @SPEC:
 * Campground photos [TC:020522] 
 * Campsite photos [TC:020523] 
 * @Task#: AUTO-1543
 * 
 * @author SWang
 * @Date  Apr 12, 2013
 */
public class ViewUploadedSitePhotosOnRA extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage facilityPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
	private String reloadPhotoPath, facilityDesc, productDesc, raUrl, clearCacheUrl, linkName;
	private int photoIndex = 0;

	public void execute() {
		//Precondition: Go to photo manager to check facility and product photo exists or not
		pt.invokeURL(url);
		pt.signIn(login);

		//Check facility photo
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoFacilityPhotosPage(StringUtil.EMPTY, stateFilter, letterFilter, facility.facilityID);
		if(!facilityPhotosPg.checkPhotoExist(facilityDesc)){
			pt.removeAllPhotos();
			pt.uploadPhotoAndUpdateDesc(reloadPhotoPath, new String[]{facilityDesc}, new Integer[]{photoIndex});
		}

		//Check product photo
		pt.gotoPrdPhotosPgFromFacilityPhotosPage(prdID);
		if(!productPhotoPg.checkPhotoExist(productDesc)){
			pt.removeAllProductPhotos();
			pt.uploadProductPhotoAndUpdateDesc(reloadPhotoPath, new String[]{productDesc}, new Integer[]{photoIndex});
		}

		//Logout
		pt.logOut();

		//Go to RA web site
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(clearCacheUrl);
		web.invokeURL(raUrl, false);
		web.gotoViewAsListPage(bd);

		//Verify facility and site photo in RA website
		verifyPhotoDisplaysInRA();
	}

	public void wrapParameters(Object[] param) {
		//Parameters for photo tool
		schema = DataBaseFunctions.getSchemaName("SC", env);
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "SC";
		letterFilter = "A";
		facility.facilityID = "10201"; //Aiken
		facility.facilityName = DataBaseFunctions.getFacilityName(facility.facilityID, schema);

		prdID = "1946";
		prdCode = DataBaseFunctions.getSiteNum(prdID, schema);

		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
		facilityDesc = "Auto Photo: "+facility.facilityName;
		productDesc = "Auto Photo: "+prdCode;

		//Parameters for RA website
		raUrl = TestProperty.getProperty(env + ".web.ra.url");
		clearCacheUrl = raUrl +"/"+ TestProperty.getProperty("clearCache.Suffix");

		bd.state = "South Carolina";
		bd.park = facility.facilityName;
		bd.whereTextValue = bd.park;
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "4";
		bd.contractCode = facility.contract;
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		bd.siteNo = "004";
		bd.siteID = prdID;

		linkName = "South Carolina Department of Parks, Recreation & Tourism";
	}

	/**
	 * Check facility and site photo setup in photo tool can be displayed in campground map browse, campground map search, campground directory, park list, park details, site details
	 */
	public void verifyPhotoDisplaysInRA(){
		PLWParkListPage parkListPg = PLWParkListPage.getInstance();
		RAParkDetailsPage parkDetailPg = RAParkDetailsPage.getInstance();
		RASiteDetailsPage campsiteDetailPg = RASiteDetailsPage.getInstance();
		UwpCampgroundsByStatePage statePg = UwpCampgroundsByStatePage.getInstance();
		UwpCampgroundsMapSearchPage campgroundSearchPg = UwpCampgroundsMapSearchPage.getInstance();
		UwpCampgroundsMapBrowsePage campgroundMapBrowserPg = UwpCampgroundsMapBrowsePage.getInstance();
		RAViewAsListPage viewAsListPg = RAViewAsListPage.getInstance();
		RAParkDetailsPageForCampAndMarina parkDetailsPg = RAParkDetailsPageForCampAndMarina.getInstance();
		UwpParkDetailsCommonPage parkDetailsCommonPg = UwpParkDetailsCommonPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();

		List<String> errorLogs = new ArrayList<String>();

		if(!viewAsListPg.checkFacilityPhotoExist(bd.contractCode, bd.parkId, facilityDesc)){
			logger.info("Failed to verify photo exists in view as list page");
		}

		web.gotoProducteDetailsPageFromViewAsListPage(parkDetailsPg);
		if(!parkDetailsCommonPg.checkFacilityPhotoExist(facilityDesc)){
			logger.info("Failed to verify photo exists in facility details page");
		}

		if(parkDetailsPg.exists()){
			parkDetailsPg.clickCheckAvailability();
			siteListPg.waitLoading();
		}

		web.gotoSiteDetailsPageFromSiteList(bd.siteNo);
		if(!campsiteDetailPg.checkProductPhotoExist(productDesc)){
			errorLogs.add("Failed to verify photo exists in RA website site details page");
		}

		web.gotoCampgroundsByStateDirectory(linkName);
		if(!statePg.checkFacilityPhotoExist(facilityDesc)){
			errorLogs.add("Failed to verify photo exists in RA website state directory page");
		}
		//		web.gotoCampgroundapMapSearchPg(bd);
		//		campgroundSearchPg.clickMapPin(bd.contractCode, facility.facilityID);
		//		if(!campgroundSearchPg.checkFacilityPhotoDisplays(bd.contractCode, bd.park)){
		//			errorLogs.add("Failed to verify photo exists in RA website campground map search page");
		//		}
		//
		//		web.gotoCampgroundMapBrowsePgFromMapSearchPg();
		//		campgroundMapBrowserPg.clickMapPin(bd.contractCode, facility.facilityID);
		//		if(!campgroundMapBrowserPg.checkFacilityPhotoDisplays(bd.contractCode, bd.park)){
		//			errorLogs.add("Failed to verify photo exists in RA website campground map browse page");
		//		}

		//		web.gotoParkListPage(bd);
		//		if(!parkListPg.checkFacilityPhotoExist(facilityDesc)){
		//			errorLogs.add("Failed to verify photo exists in RA website park list page");
		//		}
		//
		//		parkListPg.gotoParkDetailsByParkName(bd.park,bd.contractCode);
		//		parkDetailPg.waitLoading();
		//		if(!parkDetailPg.checkFacilityPhotoExist(facilityDesc)){
		//			errorLogs.add("Failed to verify photo exists in RA website facility details page");
		//		}
		//
		//		web.findCampgroundToSiteListPg(bd);
		//		web.gotoSiteDetailsPageFromSiteList(bd.siteNo);
		//		if(!campsiteDetailPg.checkProductPhotoExist(productDesc)){
		//			errorLogs.add("Failed to verify photo exists in RA website site details page");
		//		}

		if(errorLogs.size()>0){
			throw new ErrorOnDataException("Not all check point are passed. Please check details info as below logs\n"+errorLogs.toString());
		}
		logger.info("Successfully verify all check points.");
	}
}
