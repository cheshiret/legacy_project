package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.entrance.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrAddNewEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:PCR2530
 * @Preconditions:TC035844
 * @SPEC:
 * @Task#:Auto-893
 *
 * @author nding1
 * @Date  Feb 14, 2012
 */
public class VerifyUpdateRestrictionWindow extends InventoryManagerTestCase{

	private InvMgrAddNewEntrancePage newEntrancePg = InvMgrAddNewEntrancePage.getInstance();
	private InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage.getInstance();
	private EntranceInfo entranceInfo = new EntranceInfo();
	private PermitTypeInformation permitTypeInfo = new PermitTypeInformation();
	private String message1 = "Please specify the Update Restriction Window.";
	private String message2 = "The Update Restriction Window should be a whole number greater than or equal to 0. Please change your entry.";
	private String successMsg = "Entrance created successfully.";

	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		// go to Entrance Detail Page
		im.gotoEntranceListPage("Entrance Set-up");

		// verify default value of Update Restriction Window
		this.verifyDefaultUpdateResWindow();

		// 1.the value of Update Restriction Window is left blank.
		permitTypeInfo.updateResWindow = "";
		this.verifyMessage(message1);

		// 2.the value of Update Restriction Window is:@!# or SDF
		permitTypeInfo.updateResWindow = "@!# or SDF";
		this.verifyMessage(message2);

		// 3.add successfully
		entranceInfo.entranceCode = "Verify1"+DateFunctions.getCurrentTime();
		permitTypeInfo.updateResWindow = "0";
		this.verifyAddSuccess(successMsg);

		// 4.add successfully
		entranceInfo.entranceCode = "Verify2"+DateFunctions.getCurrentTime();
		permitTypeInfo.issueStartDateType = "1 day prior to entry";
		permitTypeInfo.updateResWindow = "0";
		this.verifyAddSuccess(successMsg);

		// 5.add successfully
		entranceInfo.entranceCode = "Verify3"+DateFunctions.getCurrentTime();
		permitTypeInfo.issueStartDateType = "On Entry Day";
		permitTypeInfo.updateResWindow = "1";
		this.verifyAddSuccess(successMsg);

		// 6.add successfully
		entranceInfo.entranceCode = "Verify4"+DateFunctions.getCurrentTime();
		permitTypeInfo.issueStartDateType = "1 day prior to entry";
		permitTypeInfo.updateResWindow = "1";
		this.verifyAddSuccess(successMsg);

		// log out
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		inventory.facilityName = "Boundary Waters Canoe Area Wilderness (Reservations)";// ID=72600

		// entranceInfo
		entranceInfo.entranceCode = "Verify"+DateFunctions.getCurrentTime();
		entranceInfo.entranceName = "Verify Update Restriction Window";
		entranceInfo.entranceType = "Entry Point";
		entranceInfo.description = "Verify Update Restriction Window for PCR2530.";
		entranceInfo.entryType = "Entrance";

		// permitTypeInfo
		permitTypeInfo.permitType = "Day Use Motor";
		permitTypeInfo.permitTypeID = "280877670";
		permitTypeInfo.maxGroupSize ="5";
		permitTypeInfo.maxWatercraft = "10";
		permitTypeInfo.issueStartDateType = "On Entry Day";
		permitTypeInfo.startDateTime = "6:00";
		permitTypeInfo.startAmPm = "AM";
		permitTypeInfo.issueEndDateType = "On Entry Day";
		permitTypeInfo.endDateTime = "9:00";
		permitTypeInfo.endAmPm = "PM";
		permitTypeInfo.status = "Active";
	}

	/**
	 * Verify whether the default value of Update Res Window is 0 or not.
	 */
	private void verifyDefaultUpdateResWindow(){
		logger.info("Verify whether the default value of Update Res Window is 0 or not.");

		// go to add new entrance page
		im.gotoNewEntrancePage();
		newEntrancePg.clickAdd();
		ajax.waitLoading();

		// get the default value of Update Res Window
		String updateResWindow = newEntrancePg.getUpdateResWindow();
		if(!"0".equals(updateResWindow)) {
			logger.error("Default value of Update Res Window should be 0.But actual is:"+ updateResWindow);
			throw new ErrorOnPageException("Default value of Update Res Window should be 0.But actual is:"+ updateResWindow);
		}
		newEntrancePg.clickCancel();
		entranceListPg.waitLoading();
	}

	/**
	 * Verify error message.
	 *
	 * @param expectMessage
	 */
	private void verifyMessage(String expectMessage){
		logger.info("Verify error message:"+expectMessage);
		im.addNewEntrance(entranceInfo, null, permitTypeInfo, true);

		String message = newEntrancePg.getErrMsg();
		if(!expectMessage.equals(message)){
			logger.error("Displayed message:"+message+" is not the same as expect message:"+expectMessage);
			throw new ErrorOnPageException("Displayed message:"+message+" is not the same as expect message:"+expectMessage);
		}
		newEntrancePg.clickCancel();
		entranceListPg.waitLoading();
		logger.info("Verify error message successfully.");
	}

	/**
	 * Verify whether adding new entrance successful or not.
	 *
	 * @param exceptNote
	 */
	private void verifyAddSuccess(String exceptMsg){
		logger.info("Verify whether adding new entrance successful or not.");
		im.addNewEntrance(entranceInfo, null, permitTypeInfo, true);

		// get successful message
		String message = entranceListPg.getNotesInfo();
		if(!exceptMsg.equals(message)) {
			logger.error("The new entrance has not been added successfully!");
			throw new ErrorOnPageException("The new entrance has not been added successfully!");
		}

		logger.info("Verify whether adding new entrance successfully.");
	}
}
