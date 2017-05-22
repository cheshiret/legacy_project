package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Need IE7 because reload picture
 * @Preconditions:
 * @SPEC: Mixed photo update actions [TC:020512] 
 * @Task#: AUTO-1539
 * 
 * @author SWang
 * @Date  Mar 12, 2013
 */
public class MixedPhotoUpdateActions extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private String successfullyUpdatePhotoMes, reloadPhotoPath, expectedRemovePhotoMes, actualRemovePhotoMes,
	defaultPhotoDes, updtedPhotoDes;
	private List<String> displayOrders;
	private int photoNum;

	public void execute() {
		//Login in to camp ground photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		//Reload 3 photos
		pt.removeAllPhotos();
		pt.reloadPhoto(reloadPhotoPath);
		pt.reloadPhoto(reloadPhotoPath);
		pt.reloadPhoto(reloadPhotoPath);

		//Make sure the successfully upload message disappears
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		pt.gotoFacilityPhotosPageFromProductPhotosPage();

		//Initialize parameters
		defaultPhotoDes = campgroundPhotosPg.getPhotoDescription(0);
		updtedPhotoDes = defaultPhotoDes+DateFunctions.getCurrentTime();
		displayOrders = campgroundPhotosPg.getDisplayOrders();
		photoNum = campgroundPhotosPg.getPhotoNum();

		//Update #1 photo display order, #2 photo description, #3 remove photo, try to reload photo, click cancel button
		actualRemovePhotoMes = pt.updateCampgroundPhotos(reloadPhotoPath, new String[]{updtedPhotoDes}, new Integer[]{1}, new Integer[]{2}, false, new String[]{"2"}, new Integer[]{0});
		verifyRemovePhotoMes();
		campgroundPhotosPg.noSuccessfullyUpdatedPPhotoMes();
		pt.logOut();

		//Login in to camp ground photos page to check no any changed
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		campgroundPhotosPg.verifyDisplayOrders(displayOrders.toArray(new String[displayOrders.size()]));
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, 1);
		campgroundPhotosPg.verifyPhotoNum(photoNum);

		//Update #1 photo display order, #2 photo description, #3 remove photo, try to reload photo, click ok button
		actualRemovePhotoMes = pt.updateCampgroundPhotos(reloadPhotoPath, new String[]{updtedPhotoDes}, new Integer[]{1}, new Integer[]{2}, true, new String[]{"2"}, new Integer[]{0});
		verifyRemovePhotoMes();
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyPhotoDescription(updtedPhotoDes, 0);
		campgroundPhotosPg.verifyPhotoNum(photoNum);

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		facility.facilityID = "97075"; //AGUA PIEDRA GROUP SHELTER

		expectedRemovePhotoMes = "One or more photos are marked for removal. Continue?";
		successfullyUpdatePhotoMes = "Photo updated successfully.";
		reloadPhotoPath = fileLocation+ "PhotoLessThan3M.jpg";
	}

	private void verifyRemovePhotoMes(){
		if(!expectedRemovePhotoMes.equals(actualRemovePhotoMes)){
			throw new ErrorOnDataException("Remove photo message is wrong!", expectedRemovePhotoMes, actualRemovePhotoMes);
		}
		logger.info("Successfully verify remove photo message is right:"+expectedRemovePhotoMes);
	}
}


