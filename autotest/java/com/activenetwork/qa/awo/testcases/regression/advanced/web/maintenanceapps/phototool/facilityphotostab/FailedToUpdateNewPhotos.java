package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.facilityphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
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
public class FailedToUpdateNewPhotos extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private String defaultPhotoDes, updtedPhotoDes, fileLocation1, fileLocation2, fileLocation3, fileLocation4, uploadErrorMes1, uploadErrorMes2, uploadErrorMes3, uploadErrorMes4, actualErrorMes;
	private int photoIndex = 0;

	public void execute() {
		//Login in with a valid account
		pt.invokeURL(url);
		pt.signIn(login);

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

		//Upload photo(>3m, jpg) with description
		pt.uploadPhotoAndUpdateDesc(fileLocation1, new String[]{updtedPhotoDes}, new Integer[]{photoIndex});
		campgroundPhotosPg.noSuccessfullyUpdatedPPhotoMes();
		campgroundPhotosPg.verifyUploadErrorMes(uploadErrorMes1);
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, photoIndex);

		//Upload photo(<3m, text) with description
		FileUtil.generateAndWriteFile(fileLocation2, "Automation testing");
		pt.uploadPhotoAndUpdateDesc(fileLocation2, new String[]{updtedPhotoDes}, new Integer[]{photoIndex});
		campgroundPhotosPg.noSuccessfullyUpdatedPPhotoMes();
		campgroundPhotosPg.verifyUploadErrorMes(uploadErrorMes2);
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, photoIndex);
		FileUtil.deleteFile(fileLocation2);
		
		//Upload photo, then delete the photo from local drive to make the photo not existed
		FileUtil.generateAndWriteFile(fileLocation3, StringUtil.EMPTY);
		pt.uploadPhotoAndUpdateDesc(fileLocation3, fileLocation3, new String[]{StringUtil.EMPTY}, new Integer[]{photoIndex});
		campgroundPhotosPg.noSuccessfullyUpdatedPPhotoMes();
		campgroundPhotosPg.verifyUploadErrorMes(uploadErrorMes3);
		campgroundPhotosPg.verifyPhotoDescription(defaultPhotoDes, photoIndex);

		//Update more than 6 photos
		actualErrorMes = pt.reloadMultiPhotos(fileLocation4, 7);
		verifyErrorMesWhenUploadedPhotosMoreThan6();

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("LA", env);

		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "LA";
		facility.facilityID = "240018"; //Tickfaw State Park

		fileLocation1 = fileLocation+"PhotoMoreThan3M.jpg";
		fileLocation2 = fileLocation+"FailedToUpdateNewPhotos.class";
		fileLocation3 = fileLocation+"EmptyPhotoFile.txt";
		fileLocation4 = fileLocation+"PhotoLessThan3M.jpg";

		uploadErrorMes1 = "The maximum file size is 3M. Please reduce file size and try again";
		uploadErrorMes2 = "The file format is not supported. Please upload photo in Jpeg format";
		uploadErrorMes3 = "EmptyPhotoFile.txt does not exist or is empty. Please choose another file and try again.";
		uploadErrorMes4 = "Maximum number of photos (6) has been reached. Please remove photo(s) and try again";
	}

	private void verifyErrorMesWhenUploadedPhotosMoreThan6(){
		if(!actualErrorMes.equals(uploadErrorMes4)){
			throw new ErrorOnDataException("Failed to verify error message when uploaded photos is more than 6.", uploadErrorMes4, actualErrorMes);
		}
		logger.info("Successfully verify  error message when uploaded photos is more than 6:\n"+actualErrorMes);
	}
}
