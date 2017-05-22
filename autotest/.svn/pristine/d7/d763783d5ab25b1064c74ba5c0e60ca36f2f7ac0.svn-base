package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.pages.web.plw.PLWParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.plw.PLWParkListPage;
import com.activenetwork.qa.awo.pages.web.plw.PLWSiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsMapBrowsePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsMapSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43256) Setup site facility and site photo in Photo Manager, then check them can be displayed in 
 *                            campground map browse, campground map search, park list, park details, site details
 * @Preconditions:
 * @SPEC:
 * Campground photos [TC:020522] 
 * Campsite photos [TC:020523] 
 * @Task#: AUTO-1543
 * 
 * @author SWang
 * @Date  Apr 12, 2013
 */
public class ViewUploadedSitePhotosOnPLW extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage facilityPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
	private String reloadPhotoPath, facilityDesc, productDesc, plwURL, clearCacheUrl;
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
		web.invokeURL(plwURL, false);

		//Verify facility and site photo in PLW website
		verifyPhotoDisplaysInPLW();
	}

	public void wrapParameters(Object[] param) {
		//Parameters for photo tool
		schema = DataBaseFunctions.getSchemaName("WI", env);
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "WI";
		letterFilter = "B";
		facility.facilityID = "60004"; //BIG BAY STATE PARK
		facility.facilityName = DataBaseFunctions.getFacilityName(facility.facilityID, schema);

		prdID = "37"; //001
		prdCode = DataBaseFunctions.getSiteNum(prdID, schema);

		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
		facilityDesc = "Auto Photo: "+facility.facilityName;
		productDesc = "Auto Photo: "+prdCode;

		//Parameters for PLW website
		plwURL = TestProperty.getProperty(env + ".web.wi.url");
		clearCacheUrl = plwURL +"/"+ TestProperty.getProperty("clearCache.Suffix");

		bd.state = "South Carolina";
		bd.park = facility.facilityName;
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "4";
		bd.contractCode = facility.contract;

		bd.siteNo = "001";
		bd.siteID = prdID;
	}

	/**
	 * Check facility and site photo setup in photo tool can be displayed in campground map browse, campground map search, park list, park details, site details
	 */
	public void verifyPhotoDisplaysInPLW(){
		PLWParkListPage parkListPg = PLWParkListPage.getInstance();
		PLWParkDetailsPage parkDetailPg = PLWParkDetailsPage.getInstance();
		PLWSiteDetailsPage campsiteDetailPg = PLWSiteDetailsPage.getInstance();
		UwpCampgroundsMapSearchPage campgroundSearchPg = UwpCampgroundsMapSearchPage.getInstance();
		UwpCampgroundsMapBrowsePage campgroundMapBrowserPg = UwpCampgroundsMapBrowsePage.getInstance();

		List<String> errorLogs = new ArrayList<String>();

		web.gotoCampgroundapMapSearchPg(bd);
		campgroundSearchPg.clickMapPin(bd.contractCode, facility.facilityID);
		if(!campgroundSearchPg.checkFacilityPhotoDisplays(bd.contractCode, bd.park)){
			errorLogs.add("Failed to verify photo exists in PLW website campground map search page");
		}

		web.gotoCampgroundMapBrowsePgFromMapSearchPg();
		campgroundMapBrowserPg.searchMapPinDisplays(bd.contractCode, facility.facilityID);
		campgroundMapBrowserPg.clickMapPin(bd.contractCode, facility.facilityID);
		if(!campgroundMapBrowserPg.checkFacilityPhotoDisplays(bd.contractCode, bd.park)){
			errorLogs.add("Failed to verify photo exists in PLW website campground map browse page");
		}

		web.gotoParkListPage(bd);
		if(!parkListPg.checkFacilityPhotoExist(facilityDesc)){
			errorLogs.add("Failed to verify photo exists in PLW website park list page");
		}

		parkListPg.clickParkName(bd.park,bd.contractCode);
		parkDetailPg.waitLoading();
		if(!parkDetailPg.checkFacilityPhotoExist(facilityDesc)){
			errorLogs.add("Failed to verify photo exists in PLW website facility details page");
		}

		web.findCampgroundToSiteListPg(bd);
		web.gotoSiteDetailsPageFromSiteList(bd.siteNo);
		if(!campsiteDetailPg.checkProductPhotoExist(productDesc)){
			errorLogs.add("Failed to verify photo exists in PLW website site details page");
		}

		if(errorLogs.size()>0){
			throw new ErrorOnDataException("Not all check point are passed. Please check details info as below logs\n"+errorLogs.toString());
		}
		logger.info("Successfully verify all check points.");
	}
}
