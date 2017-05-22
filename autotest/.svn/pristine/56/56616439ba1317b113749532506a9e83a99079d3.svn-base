/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify update photo description correctly
 * @Preconditions: 
 * @SPEC: Update photo description [TC:025094]
 * @Task#: Auto-1541
 * 
 * @author Lesley Wang
 * @Date  Mar 21, 2013
 */
public class UpdateProductPhotoDescription extends PhotoToolTestCase {
	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String defaultPhotoDes, successfullyUpdatePhotoMes;

	public void execute() {
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);

		logger.info("Check if there are photos uploaded. If not, upload one...");
		pt.uploadProductPhotosUpToSpecificNum(1, fileLocation); 
		
		logger.info("1. Get Default Product Photo Description...");
		defaultPhotoDes = productPhotosPg.getDefaultPhotoDescription();
		photoDescription = defaultPhotoDes+DateFunctions.getCurrentTime();

		logger.info("2. Update and verify Product Photo Description...");
		pt.updateFirstPrdPhotoDes(photoDescription);
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		productPhotosPg.verifyFirstPhotoDescription(photoDescription);
		pt.logOut();

		logger.info("3. Login in again, and verify Product Photo Description...");
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		productPhotosPg.verifyFirstPhotoDescription(photoDescription);

		logger.info("4. Update the description as empty and verify Product Photo Description...");
		pt.updateFirstPrdPhotoDes(StringUtil.EMPTY);
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		productPhotosPg.verifyFirstPhotoDescription(defaultPhotoDes);
		pt.logOut();

		logger.info("5. Login in again and verify Product Photo Description shown as default...");
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		productPhotosPg.verifyFirstPhotoDescription(defaultPhotoDes);
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";
	
		facility.contract = "NRSO";
		facility.facilityID = "70806"; // AGNEW HORSE CAMP
		
		prdID = "88378"; // 007
		fileLocation = fileLocation + "PhotoLessThan3M.jpg";
		successfullyUpdatePhotoMes = "Photo updated successfully.";
	}
}
