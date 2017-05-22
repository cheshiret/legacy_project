package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.facilityphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Need IE7 because reload picture
 * @Preconditions:
 * @SPEC: Remove photos [TC:020509] 
 * @Task#: AUTO-1539
 * 
 * @author SWang
 * @Date  Mar 12, 2013
 */
public class RemovePhotos extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private String successfullyUpdatePhotoMes, reloadPhotoPath, expectedRemovePhotoMes, actualRemovePhotoMes;
	private int photoNum;

	public void execute() {
		//Login in to camp ground photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		//Remove all photos and reload one
		pt.removeAllPhotos();
		pt.reloadPhoto(reloadPhotoPath);
		
		//Make sure the successfully upload message disappears
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		pt.gotoFacilityPhotosPageFromProductPhotosPage();
		photoNum = campgroundPhotosPg.getPhotoNum();

		//Delete photo and verify message
		//Click cancel button
		actualRemovePhotoMes = pt.removePhoto(new Integer[]{0}, false);
		verifyRemovePhotoMes();
		campgroundPhotosPg.noSuccessfullyUpdatedPPhotoMes();
		campgroundPhotosPg.verifyPhotoNum(photoNum);

		//Click ok button
		actualRemovePhotoMes = pt.removePhoto(new Integer[]{0}, true);
		verifyRemovePhotoMes();
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyPhotoNum(photoNum-1);

		pt.logOut();

		//Login in again to check updated result
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);
		
		campgroundPhotosPg.verifyPhotoNum(photoNum-1);
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		facility.facilityID = "71642"; //ADAMS FORK

		expectedRemovePhotoMes = "One or more photos are marked for removal. Continue?";
		successfullyUpdatePhotoMes = "Photo updated successfully.";
		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
	}

	private void verifyRemovePhotoMes(){
		if(!expectedRemovePhotoMes.equals(actualRemovePhotoMes)){
			throw new ErrorOnDataException("Remove photo message is wrong!", expectedRemovePhotoMes, actualRemovePhotoMes);
		}
		logger.info("Successfully verify remove photo message is right:"+expectedRemovePhotoMes);
	}
}
