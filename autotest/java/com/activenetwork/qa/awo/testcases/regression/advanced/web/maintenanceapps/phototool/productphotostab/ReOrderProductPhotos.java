/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: ReOrder Photos. 
 * @Preconditions: 
 * @SPEC: Re-order photos [TC:025096]
 * @Task#: Auto-1541
 * 
 * @author Lesley Wang
 * @Date  Mar 21, 2013
 */
public class ReOrderProductPhotos extends PhotoToolTestCase {
	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String successfullyUpdatePhotoMes;
	private List<String> displayOrders, photoFileNames;

	public void execute() {
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);

		logger.info("1. make sure there are at least 2 photos. If not, upload photos firstly...");
		pt.uploadProductPhotosUpToSpecificNum(2, fileLocation);
		
		logger.info("2. Change the first photo's order to 2, and verify order updated correctly...");
		displayOrders = productPhotosPg.getDisplayOrders();
		photoFileNames = productPhotosPg.getPhotoFileNames();
		pt.reOrderProductPhoto(new String[]{"2"}, new Integer[]{0});
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		productPhotosPg.verifyDisplayOrders(displayOrders);
		this.updatePhotoNamesList(photoFileNames);
		productPhotosPg.verifyPhotoFileNames(photoFileNames);

		logger.info("3. Change first and second photos' order as 1, and verify order reversed...");
		pt.reOrderProductPhoto(new String[]{"1", "1"}, new Integer[]{0, 1});
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		this.updatePhotoNamesList(photoFileNames);
		productPhotosPg.verifyPhotoFileNames(photoFileNames);
		pt.logOut();

		//Login in again to check updated displaying order
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		
		productPhotosPg.verifyDisplayOrders(displayOrders);
		productPhotosPg.verifyPhotoFileNames(photoFileNames);
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "CA";
		facility.facilityID = "120102"; // HEARST CASTLE
		prdID = "28310"; // Gift Ticket
		
		fileLocation = fileLocation + "PhotoLessThan3M.jpg";
		successfullyUpdatePhotoMes = "Photo updated successfully.";
	}
	
	/** Change the first and second photo sources order in the list*/
	private void updatePhotoNamesList(List<String> srcs) {
		String temp = srcs.get(0);
		srcs.set(0, srcs.get(1));
		srcs.set(1, temp);
	}
}
