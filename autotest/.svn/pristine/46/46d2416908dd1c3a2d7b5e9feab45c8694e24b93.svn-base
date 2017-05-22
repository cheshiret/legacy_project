/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Upload Product Photo Successfully
 * @Preconditions:
 * @SPEC: Upload new photos [TC:025097]
 * @Task#: Auto-1541
 * 
 * @author Lesley Wang
 * @Date  Mar 25, 2013
 */
public class UploadProductPhoto extends PhotoToolTestCase {

	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String defaultPhotoDes, successfullyUpdatePhotoMes, displayOrderForNew;
	private List<String> displayOrders, photoFileNames;
	private String[] descriptions, orders;
	private Integer[] indexs;
	
	public void execute() {
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		
		logger.info("1. Remove all photos for testing...");
		pt.removeAllProductPhotos();
		pt.gotoFacilityPhotosPageFromProductPhotosPage();
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		
		//Initialize descriptions
		defaultPhotoDes = productPhotosPg.getDefaultPhotoDescription();
		photoDescription = defaultPhotoDes+DateFunctions.getCurrentTime();
		
		logger.info("2. Upload photo(<3m, jpg) with description...");
		descriptions[0] = photoDescription;
		indexs[0] = 0;
		
		pt.uploadProductPhotoAndUpdateDesc(fileLocation, descriptions, indexs);
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		productPhotosPg.verifyFirstPhotoDescription(photoDescription);

		logger.info("3. Upload photo(<3m, jpg) without description and with default order...");
		descriptions[0] = StringUtil.EMPTY;
		indexs[0] = 1;
		
		pt.uploadProductPhotoAndUpdateDesc(fileLocation, descriptions, indexs);
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		productPhotosPg.verifyPhotoDescription(defaultPhotoDes, indexs[0]);

		logger.info("4. Upload photo(<3m, jpg) with description and specify order...");
		displayOrders = productPhotosPg.getDisplayOrders();
		photoFileNames = productPhotosPg.getPhotoFileNames();
		photoDescription = defaultPhotoDes+DateFunctions.getCurrentTime();
		displayOrderForNew = "2";
		descriptions[0] = photoDescription;
		indexs[0] = 2;
		orders[0] = displayOrderForNew;
		
		pt.uploadProductPhotoAndUpdateDesc(fileLocation, descriptions, indexs, orders, indexs);
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);
		productPhotosPg.verifyPhotoDescription(photoDescription, Integer.valueOf(displayOrderForNew) - 1);
		this.verifyExistingDisplayOrder(displayOrders, photoFileNames, displayOrderForNew);
		
		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "CA";
		facility.facilityID = "120102"; // HEARST CASTLE		
		prdID = "23510"; // Private Ticket
		
		fileLocation = fileLocation + "PhotoLessThan3M.jpg";
		successfullyUpdatePhotoMes = "Photo updated successfully.";
		
		descriptions = new String[1];
		orders = new String[1];
		indexs = new Integer[1];
		
	}
	
	private void verifyExistingDisplayOrder(List<String> displayOrders, List<String> names, String changedOrder) {
		List<String> newDisplayOrders = productPhotosPg.getDisplayOrders();
		List<String> newPhotoFileNames = productPhotosPg.getPhotoFileNames();
		newDisplayOrders.remove(newDisplayOrders.size() - 1);
		int index = Integer.valueOf(changedOrder) - 1;
		newPhotoFileNames.remove(index);
		if (!newDisplayOrders.equals(displayOrders) || !newPhotoFileNames.equals(names)) {
			throw new ErrorOnPageException("The display orders are wrong!", newPhotoFileNames.toString(), names.toString());
		}
		logger.info("The display orders are changed correctly!");
	}
}
