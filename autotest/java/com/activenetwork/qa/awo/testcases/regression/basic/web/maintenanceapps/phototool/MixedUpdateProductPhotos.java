/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify different mixed actions for updating product photos
 * @Preconditions:
 * @SPEC:  Mixed photo update actions [TC:025098]
 * @Task#: Auto-1541
 * 
 * @author Lesley Wang
 * @Date  Mar 25, 2013
 */
public class MixedUpdateProductPhotos extends PhotoToolTestCase {
	private PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
	private String successfullyUpdatePhotoMes, expectedRemovePhotoMes, actualRemovePhotoMes,defaultPhotoDes;
	private List<String> displayOrders, fileNames;
	private int photoNum;
	private String[] changedDes, changedOrders;
	private Integer[] changedDesInd, deletedInd, changedOrdInd = new Integer[1];

	public void execute() {
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);

		logger.info("1. Preparation - remove all and then upload 3 new ones if less than...");
		pt.removeAllProductPhotos();
		photoNum = pt.uploadProductPhotosUpToSpecificNum(3, fileLocation);
		
		//Make sure the successfully upload message disappears
		pt.gotoFacilityPhotosPageFromProductPhotosPage();
		pt.gotoProductPhotosTabFromFacilityPhotosPage();
		
		//Initialize parameters
		this.initailizeParameters();	

		logger.info("1. Update #1 photo display order, #2 photo description, #3 remove photo, try to reload photo, click cancel button");
		actualRemovePhotoMes = pt.updateProductPhotos(fileLocation, changedDes, changedDesInd, deletedInd, false, changedOrders, changedOrdInd);
		verifyRemovePhotoMes();
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, false);
		pt.gotoFacilityPhotosPageFromProductPhotosPage();
		pt.logOut();

		logger.info("2. Login in again to check no any change...");
		pt.signIn(login);
		pt.gotoPrdPhotosPgFromSelectFacilityPg(facility, prdID);
		productPhotosPg.verifyDisplayOrders(displayOrders);
		productPhotosPg.verifyPhotoDescription(defaultPhotoDes, changedDesInd[0]);
		productPhotosPg.verifyPhotoNum(photoNum);

		logger.info("3. Update #1 photo display order, #2 photo description, #3 remove photo, try to reload photo, click ok button");
		actualRemovePhotoMes = pt.updateProductPhotos(fileLocation, changedDes, changedDesInd, deletedInd, true, changedOrders, changedOrdInd);
		verifyRemovePhotoMes();
		productPhotosPg.verifyUpdatedPhotoMsgExist(successfullyUpdatePhotoMes, true);	
		verifyExistingDisplayOrder(fileNames, changedOrdInd[0], Integer.valueOf(changedOrders[0]) - 1, deletedInd[0]);
		productPhotosPg.verifyDisplayOrders(displayOrders);
		productPhotosPg.verifyPhotoDescription(photoDescription, changedOrdInd[0]);
		productPhotosPg.verifyPhotoNum(photoNum);

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		facility.facilityID = "70806"; // AGNEW HORSE CAMP
		
		prdID = "18723"; // 008
		fileLocation = fileLocation + "PhotoLessThan3M.jpg";
		expectedRemovePhotoMes = "One or more photos are marked for removal. Continue?";
		successfullyUpdatePhotoMes = "Photo updated successfully.";	
	}

	private void initailizeParameters() {
		defaultPhotoDes = productPhotosPg.getDefaultPhotoDescription();
		photoDescription = defaultPhotoDes+DateFunctions.getCurrentTime();
		displayOrders = productPhotosPg.getDisplayOrders();
		fileNames = productPhotosPg.getPhotoFileNames();
		changedDes = new String[] {photoDescription};
		changedDesInd = new Integer[] {1};
		changedOrders = new String[] {"2"};
		changedOrdInd = new Integer[] {0};
		deletedInd = new Integer[] {2};	
	}
	
	private void verifyRemovePhotoMes(){
		if(!expectedRemovePhotoMes.equals(actualRemovePhotoMes)){
			throw new ErrorOnDataException("Remove photo message is wrong!", expectedRemovePhotoMes, actualRemovePhotoMes);
		}
		logger.info("Successfully verify remove photo message is right:"+expectedRemovePhotoMes);
	}
	
	/** Verify the display order */
	private void verifyExistingDisplayOrder(List<String> names, int orgOrderIndex, int changedOrderIndex, int deletedInd) {
		List<String> newPhotoFileNames = productPhotosPg.getPhotoFileNames();
		boolean result = true;
		
		// Verify removed photo doesn't exist
		if (newPhotoFileNames.contains(names.get(deletedInd))) {
			result = false;
			logger.info("The photo #" + deletedInd + " doesn't removed!");
		} else {
			result = true;
			logger.info("The photo #" + deletedInd + " has been removed!");
		}
		
		// Verify re-ordered photos correctly
		result &= MiscFunctions.compareString("The original first photo name equal to the updated second one", names.get(orgOrderIndex), newPhotoFileNames.get(changedOrderIndex));
		result &= MiscFunctions.compareString("The orginal second one equal to the updated first one ", names.get(changedOrderIndex), newPhotoFileNames.get(orgOrderIndex));
		
		if (!result) {
			throw new ErrorOnPageException("The photos display orders are wrong!", newPhotoFileNames.toString(), names.toString());
		}
		logger.info("The photos display orders are changed correctly!");
	}
}
