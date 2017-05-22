package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.facilityphotostab;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC: Re-order photos [TC:025096] 
 * @Task#: AUTO-1539
 * 
 * @author SWang
 * @Date  Mar 11, 2013
 */
public class ReOrderPhotos extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private String successfullyUpdatePhotoMes,reloadPhotoPath, photoDesTemp1, photoDesTemp2;
	private List<String> displayOrders, photoNames;

	public void execute() {
		//Login in to camp ground photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		//Delete all photos
		pt.removeAllPhotos();
		pt.uploadPhotoAndUpdateDesc(reloadPhotoPath, new String[]{photoDesTemp1}, new Integer[]{0});
		pt.uploadPhotoAndUpdateDesc(reloadPhotoPath, new String[]{photoDesTemp2}, new Integer[]{1});
		
		//Initialize display orders and photo names
		displayOrders = campgroundPhotosPg.getDisplayOrders();
		photoNames = campgroundPhotosPg.getPhotoNames();

		//Update displaying order, 2 for first photo so that 1 changes to second photo
		pt.reOrderPhoto(new String[]{"2"}, new Integer[]{0});
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyDisplayOrders(displayOrders.toArray(new String[displayOrders.size()]));
		campgroundPhotosPg.verifyPhotoNames(new String[]{photoNames.get(1), photoNames.get(0)});

		//Update displaying order, make first and second photos have the same order 2
		pt.reOrderPhoto(new String[]{"2", "2"}, new Integer[]{0, 1});
		campgroundPhotosPg.verifySuccessfullyUpdatedPhotoMes(successfullyUpdatePhotoMes);
		campgroundPhotosPg.verifyDisplayOrders(displayOrders.toArray(new String[displayOrders.size()]));
		campgroundPhotosPg.verifyPhotoNames(photoNames.toArray(new String[photoNames.size()]));

		pt.logOut();

		//Login in again to check updated displaying order
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoCampgroundPhotosPage(facility.facilityID);

		campgroundPhotosPg.verifyDisplayOrders(displayOrders.toArray(new String[displayOrders.size()]));
		campgroundPhotosPg.verifyPhotoNames(photoNames.toArray(new String[photoNames.size()]));

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		facility.facilityID = "75157"; //ADELAIDE CAMPGROUND

		successfullyUpdatePhotoMes = "Photo updated successfully.";
		reloadPhotoPath = fileLocation + "PhotoLessThan3M.jpg";
		photoDesTemp1 = "AutoTesting1";
		photoDesTemp2 = "AutoTesting2";
	}
}
