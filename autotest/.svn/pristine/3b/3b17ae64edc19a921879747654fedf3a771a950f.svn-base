package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.facilityphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC: Update photo description [TC:025094] 
 * @Task#: AUTO-1539
 * 
 * @author SWang
 * @Date  Mar 11, 2013
 */
public class UpdatePhotoDescription extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private String defaultPhotoDes, updtedPhotoDes, successfullyUpdatePhotoMes, reloadPhotoPath, photoDesTemp;

	public void execute() {
		//Login in to camp ground photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		//Delete all photos
		pt.removeAllPhotos();

		//Initialize descriptions
		defaultPhotoDes = campgroundPhotosPg.getPhotoDescription(0);
		updtedPhotoDes = defaultPhotoDes+DateFunctions.getCurrentTime();

		pt.uploadPhotoAndUpdateDesc(reloadPhotoPath, new String[]{photoDesTemp}, new Integer[]{0});
		
		//Update description
		pt.updatePhotoDescription(new String[]{updtedPhotoDes}, new Integer[]{0});
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyPhotoDescription(updtedPhotoDes, 0);

		pt.logOut();

		//Login in again to check update description, then delete the photo description blank
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);
		campgroundPhotosPg.verifyPhotoDescription(updtedPhotoDes, 0);

		pt.updatePhotoDescription(new String[]{StringUtil.EMPTY}, new Integer[]{0});
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, 0);

		pt.logOut();

		//Login in again to check default description displays
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);
		
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, 0);
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		facility.facilityID = "71744"; //ADMIRALTY COVE CABIN

		successfullyUpdatePhotoMes = "Photo updated successfully.";
		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
		photoDesTemp = "AutoTesting";
	}
}
