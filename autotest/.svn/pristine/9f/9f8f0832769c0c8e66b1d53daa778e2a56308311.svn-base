package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.bw.BwBookPermitPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43256->DEFECT-62048->passed) Verify entrance and permit facility photos setup in photo manager display in RECGOV view as list, permit facility details, facility map, entrance list and entrance details page 
 * @Preconditions:
 * @SPEC: Permit / Entrance photos [TC:020524] 
 * @Task#: AUTO-1543
 * @Note: DEFECT-43222 has been fixed in 3.04.00
 * 
 * @author SWang
 * @Date  Apr 12, 2013
 */
public class ViewUploadedPermitPhotosOnRec extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage facilityPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
	private String reloadPhotoPath, facilityDesc, productDesc, recgovUrl, clearCacheUrl;
	private int photoIndex = 0;

	private PermitInfo permit = new PermitInfo();

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
		web.invokeURL(clearCacheUrl);
		web.invokeURL(recgovUrl, false);
		web.gotoViewAsListPage(bd);

		//Verify entrance and permit facility photos setup in photo manager display in RECGOV view as list, permit facility details, facility map, entrance list and entrance details page 
		verifyPhotoDisplaysInRecgov();
	}

	public void wrapParameters(Object[] param) {
		//Parameters for photo tool
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		stateFilter = "MN";
		letterFilter = "B";
		facility.facilityID = "72600"; //Boundary Waters Canoe Area Wilderness (Reservations)
		facility.facilityName = DataBaseFunctions.getFacilityName(facility.facilityID, schema);

		prdID = "277467";
		prdName = DataBaseFunctions.getSiteName(prdID, schema); //Fall Lake (op,om) 

		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
		facilityDesc = "Auto Photo: "+facility.facilityName;
		productDesc = "Auto Photo: "+prdName;

		//Parameters for recgov
		recgovUrl = TestProperty.getProperty(env + ".web.recgov.url");
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		bd.isUnifiedSearch = true;
		bd.parkId = facility.facilityID;
		bd.park = facility.facilityName;
		bd.contractCode = facility.contract;
		bd.whereTextValue = bd.park;
		bd.interestInValue = UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN;

		permit.permitType = "Overnight Paddle";
		permit.entrance = "24 Fall Lake (op,om)";
		permit.entryDate = DateFunctions.getDateAfterToday(10);
		permit.groupSize = "2";
	}

	/**
	 * Verify entrance and permit facility photos setup in photo manager display in RECGOV view as list, permit facility details, facility map, entrance list and entrance details page
	 */
	public void verifyPhotoDisplaysInRecgov(){
		BWCooperator bw = BWCooperator.getInstance();
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();

		RecgovPermitAreaDetailsPage permitParkDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		RecgovEntranceListPage entranceListPg = RecgovEntranceListPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

		List<String> errorLogs = new ArrayList<String>();

		if(!viewAsListPg.checkFacilityPhotoExist(bd.contractCode, bd.parkId, facilityDesc)){
			errorLogs.add("Failed to verify photo exists in view as list page");
		}

		web.gotoProducteDetailsPageFromViewAsListPage(permitParkDetailsPg);
		if(!permitParkDetailsPg.checkFacilityPhotoExist(facilityDesc)){
			errorLogs.add("Failed to verify photo exists in permit facility details page");
		}

		//To do, check photo in map, blocked by DEFECT-43222 now

		bw.findPermits(permit);
		if(!entranceListPg.checkProductPhotoExist(prdName, productDesc)){
			errorLogs.add("Failed to verify photo exists in entrance list page");
		}

		entranceListPg.clickEntranceName(bd.parkId, prdID);

		bwBookPage.waitLoading();
		if(!bwBookPage.checkProductPhotoExist(productDesc)){
			errorLogs.add("Failed to verify photo exists in entrance details page");
		}

		if(errorLogs.size()>0){
			throw new ErrorOnDataException("Not all check point are passed. Please check details info as below logs\n"+errorLogs.toString());
		}
		logger.info("Successfully verify all check points.");
	}
}

