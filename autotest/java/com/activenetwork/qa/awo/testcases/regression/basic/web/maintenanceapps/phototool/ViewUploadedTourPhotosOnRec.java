package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourParkDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43256) Setup tour facility and tour photo in Photo Manager, then check them can be displayed in view as list, tour facility details, tour list and tour details page
 * @Preconditions:
 * @SPEC: Tour facility / Tour photos [TC:020525] 
 * @Task#: AUTO-1543
 * 
 * @author SWang
 * @Date  Apr 12, 2013
 */
public class ViewUploadedTourPhotosOnRec extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage facilityPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
	private String reloadPhotoPath, facilityDesc, productDesc, recgovUrl;
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

		//Go to RECGOV web site
//		web.invokeURL(clearCacheUrl);
		web.invokeURL(recgovUrl, false);
		web.gotoViewAsListPage(bd);

		//Verify tour and tour facility photos setup in photo manager display in RECGOV view as list, tour facility details, tour list and tour details page 
		verifyTourRelatedPhotosDisplaysInRecgov();
	}

	public void wrapParameters(Object[] param) {
		//Parameters for photo tool
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		stateFilter = "KY";
		letterFilter = "M";
		facility.facilityID = "77817"; //MAMMOTH CAVE NATIONAL PARK TOURS
		facility.facilityName = DataBaseFunctions.getFacilityName(facility.facilityID, schema);

		prdID = "206155";
		prdName = DataBaseFunctions.getSiteName(prdID, schema);

		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
		facilityDesc = "Auto Photo: "+facility.facilityName;
		productDesc = "Auto Photo: "+prdName;

		//Parameters for recgov
		recgovUrl = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = true;
		bd.parkId = facility.facilityID;
		bd.park = facility.facilityName;
		bd.contractCode = facility.contract;
		bd.whereTextValue = bd.park;
		bd.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;

		ticket.tourName = prdName;
		ticket.tourDate = DateFunctions.getDateAfterToday(10);
		ticket.isUnifiedSearch = bd.isUnifiedSearch;
		ticket.ticketNums = "2";
	}

	/**
	 * Verify tour and tour facility photos setupped in photo manager display in RECGOV view as list, tour facility details, tour list and tour details page.
	 */
	public void verifyTourRelatedPhotosDisplaysInRecgov(){
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
		UwpTourParkDetailsPage tourParkDetailsPg = UwpTourParkDetailsPage.getInstance();
		UwpTourListPage tourListPg = UwpTourListPage.getInstance();
		UwpTourDetailsPage tourDetailsPg = UwpTourDetailsPage.getInstance();

		List<String> errorLogs = new ArrayList<String>();

		if(!viewAsListPg.checkFacilityPhotoExist(bd.contractCode, bd.parkId, facilityDesc)){
			logger.info("Failed to verify photo exists in view as list page");
		}

		web.gotoProducteDetailsPageFromViewAsListPage(tourParkDetailsPg);
		if(!tourParkDetailsPg.checkFacilityPhotoExist(facilityDesc)){
			logger.info("Failed to verify photo exists in tour facility details page");
		}

		web.findTour(ticket);
		if(!tourListPg.checkProductPhotoExist(prdName, productDesc)){
			logger.info("Failed to verify photo exists in tour list page");
		}

		tourListPg.clickTourName(bd.parkId, prdID);
		tourDetailsPg.waitLoading();
		if(!tourDetailsPg.checkProductPhotoExist(productDesc)){
			logger.info("Failed to verify photo exists in tour details page");
		}

		if(errorLogs.size()>0){
			throw new ErrorOnDataException("Not all check point are passed. Please check details info as below logs\n"+errorLogs.toString());
		}
		logger.info("Successfully verify all check points.");
	}
}
