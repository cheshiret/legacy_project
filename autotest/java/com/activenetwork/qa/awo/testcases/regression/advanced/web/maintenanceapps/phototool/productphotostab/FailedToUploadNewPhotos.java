/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify error messages when upload photos failed
 * @Preconditions: 
 * @SPEC: Upload new photos [TC:025097]
 * @Task#: Auto-1541
 * 
 * @author Lesley Wang
 * @Date  Mar 21, 2013
 */
public class FailedToUploadNewPhotos extends PhotoToolTestCase {

	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String defaultPhotoDes,fileLoc_Larger3M, fileLoc_InvFormat, fileLoc_NotExist, fileLoc_Valid, 
	errMsg_LargeFile, errMsg_InvFormat, errMsg_NotExist, errMsg_ExceedMax, successfullyUpdatePhotoMes;
	private int photoIndex = 0, maxNum = 6;

	public void execute() {
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		
		defaultPhotoDes = productPhotosPg.getDefaultPhotoDescription();
		photoDescription = defaultPhotoDes+DateFunctions.getCurrentTime();

		logger.info("1. Remove all photos...");
		pt.removeAllProductPhotos();
		pt.gotoFacilityPhotosPageFromProductPhotosPage();
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		
		logger.info("2. Upload photo(>3m, jpg) with description...");
		pt.uploadProductPhotoAndUpdateDesc(fileLoc_Larger3M, new String[]{photoDescription}, new Integer[]{photoIndex});
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, false);
		productPhotosPg.verifyUploadErrorMes(errMsg_LargeFile);
		productPhotosPg.verifyPhotoDescription(defaultPhotoDes, photoIndex);

		logger.info("3. Upload photo(<3m, Java) with description...");
		FileUtil.generateAndWriteFile(fileLoc_InvFormat, "Automation testing");
		pt.uploadProductPhotoAndUpdateDesc(fileLoc_InvFormat, new String[]{photoDescription}, new Integer[]{photoIndex});
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, false);
		productPhotosPg.verifyUploadErrorMes(errMsg_InvFormat);
		productPhotosPg.verifyPhotoDescription(defaultPhotoDes, photoIndex);
		FileUtil.deleteFile(fileLoc_InvFormat);
		
		logger.info("4. Upload photo which doesn't exist in local drive...");
		FileUtil.generateAndWriteFile(fileLoc_NotExist, StringUtil.EMPTY);
		pt.uploadProductPhotoAndUpdateDesc(fileLoc_NotExist, fileLoc_NotExist, new String[]{StringUtil.EMPTY}, new Integer[]{photoIndex});
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, false);
		productPhotosPg.verifyUploadErrorMes(errMsg_NotExist);
		productPhotosPg.verifyPhotoDescription(defaultPhotoDes, photoIndex);

		logger.info("5. Upload more than 6 photos and verify the error message...");
		pt.uploadProductPhotosUpToSpecificNum(maxNum, fileLoc_Valid);
		String actualMsg = pt.uploadProductPhoto(fileLoc_Valid);
		this.verifyErrorMsg(actualMsg, errMsg_ExceedMax);
		
		pt.removeAllProductPhotos();
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		facility.facilityID = "72202"; // Desolation Wilderness Permit
		facility.stateCode = "CA";
		prdID = "307602"; // Half Moon
		
		fileLoc_Larger3M = fileLocation+"PhotoMoreThan3M.jpg";
		fileLoc_InvFormat = fileLocation+"FailedToUpdateNewPhotos.class";
		fileLoc_NotExist = fileLocation+"EmptyPhotoFile.txt";
		fileLoc_Valid = fileLocation+"PhotoLessThan3M.jpg";
		
		errMsg_LargeFile = "The maximum file size is 3M. Please reduce file size and try again";
		errMsg_InvFormat = "The file format is not supported. Please upload photo in Jpeg format";
		errMsg_NotExist = "EmptyPhotoFile.txt does not exist or is empty. Please choose another file and try again.";
		errMsg_ExceedMax = "Maximum number of photos ("+maxNum+") has been reached. Please remove photo(s) and try again"; 
		successfullyUpdatePhotoMes = "Photo updated successfully.";
	}
	
	private void verifyErrorMsg(String actual, String exp){
		if(!exp.equals(actual)){
			throw new ErrorOnDataException("Upload photo error message is wrong!", exp, actual);
		}
		logger.info("Successfully verify upload photo error message is right:"+exp);
	}
}
