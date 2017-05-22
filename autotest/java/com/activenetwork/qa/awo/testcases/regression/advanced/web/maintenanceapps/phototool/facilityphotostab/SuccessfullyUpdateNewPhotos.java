package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.facilityphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Need IE7 because reload picture
 * @Preconditions:
 * @SPEC: Upload new photos [TC:020511] 
 * @Task#: AUTO-1539
 * 
 * @author SWang
 * @Date  Mar 11, 2013
 */
public class SuccessfullyUpdateNewPhotos extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private String defaultPhotoDes, updtedPhotoDes, reloadPhotoPath, successfullyUpdatePhotoMes;

	public void execute() {
		//Login in with a valid account
		pt.invokeURL(url);
		pt.signIn(login.userName, login.password);
		pt.selectRoleLocAndContinue(login.role, login.location);

		//Go to camp ground photos page
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		//Delete all photos
		pt.removeAllPhotos();

		//Make sure the successfully upload message disappears
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		pt.gotoFacilityPhotosPageFromProductPhotosPage();

		//Initialize descriptions
		defaultPhotoDes = campgroundPhotosPg.getPhotoDescription(0);
		updtedPhotoDes = defaultPhotoDes+DateFunctions.getCurrentTime();

		//Upload photo(<3m, jpg) with description
		pt.uploadPhotoAndUpdateDesc(reloadPhotoPath, new String[]{updtedPhotoDes}, new Integer[]{0});
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyPhotoDescription(updtedPhotoDes, 0);

		//Upload photo(<3m, jpg) without escription
		pt.uploadPhotoAndUpdateDesc(reloadPhotoPath, new String[]{StringUtil.EMPTY}, new Integer[]{1});
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, 1);

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "LA";
		facility.facilityID = "240035";

		successfullyUpdatePhotoMes = "Photo updated successfully.";
		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
	}
}
