/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Remove Site Photos. 
 * @Preconditions:
 * @SPEC: Remove photos [TC:025095]
 * @Task#: Auto-1541
 * 
 * @author Lesley Wang
 * @Date  Mar 21, 2013
 */
public class RemoveProductPhotos extends PhotoToolTestCase {
	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String expectedRemovePhotoMes, actualRemovePhotoMes, updatePhotoMes;

	public void execute() {
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);

		logger.info("1. Remove all existing photos, and upload one...");
		pt.removeAllProductPhotos();
		pt.uploadProductPhoto(fileLocation);
		productPhotosPg.verifyProductPhotoExist(true);
		productPhotosPg.verifyPhotoNum(1);
		
		logger.info("2. Remove photo but click Cancel, verify photo still exist...");
		pt.gotoFacilityPhotosPageFromProductPhotosPage();
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		actualRemovePhotoMes = pt.removeProductPhoto(new Integer[]{0}, false);
		verifyRemovePhotoMes();
		productPhotosPg.verifyUpdatedPhotoMsgExist(updatePhotoMes, false);
		productPhotosPg.verifyProductPhotoExist(true);
		productPhotosPg.verifyPhotoNum(1);

		logger.info("3. Remove photo and click OK, verify photo has been removed...");
		actualRemovePhotoMes = pt.removeProductPhoto(new Integer[]{0}, true);
		verifyRemovePhotoMes();
		productPhotosPg.verifyUpdatedPhotoMsgExist(updatePhotoMes, true);
		productPhotosPg.verifyProductPhotoExist(false);
		productPhotosPg.verifyPhotoNum(0);
		pt.logOut();

		logger.info("4. Login in again, and verify No Product Photo...");
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		productPhotosPg.verifyProductPhotoExist(false);
		productPhotosPg.verifyPhotoNum(0);
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";
	
		facility.contract = "NRSO";
		facility.facilityID = "72202"; // Desolation Wilderness Permit
		facility.stateCode = "CA";
		
		prdID = "307547"; // Highland
		fileLocation = fileLocation + "PhotoLessThan3M.jpg";
		expectedRemovePhotoMes = "One or more photos are marked for removal. Continue?";
		updatePhotoMes = "Photo updated successfully.";
	}
	
	private void verifyRemovePhotoMes(){
		if(!expectedRemovePhotoMes.equals(actualRemovePhotoMes)){
			throw new ErrorOnDataException("Remove photo message is wrong!", expectedRemovePhotoMes, actualRemovePhotoMes);
		}
		logger.info("Successfully verify remove photo message is right:"+expectedRemovePhotoMes);
	}
}
