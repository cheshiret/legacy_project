package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.entrance.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEditEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:PCR2530
 * @Preconditions:TC035843
 * @SPEC:
 * @Task#:Auto-893
 * 
 * @author nding1
 * @Date  Feb 21, 2012
 */
public class VerifyUpdateRestrictionWindow extends InventoryManagerTestCase{

	private InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage.getInstance();
	private InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage.getInstance();
	private EntranceInfo entranceInfo = new EntranceInfo();
	private PermitTypeInformation permitTypeInfo = new PermitTypeInformation();
	private String message1 = "Please specify the Update Restriction Window.";
	private String message2 = "The Update Restriction Window should be a whole number greater than or equal to 0. Please change your entry.";
	private String successMsg = "Entrance information updated successfully.";
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		// go to Entrance List Page
		im.gotoEntranceListPage("Entrance Set-up");
		
		// add new Entrance and make sure the new Entrance is added successfully.
		im.addNewEntrance(entranceInfo, null, permitTypeInfo, true);
		
		// 1. the value of Update Restriction Window is left blank.
		permitTypeInfo.updateResWindow = "";
		this.verifyMsg(message1, true);
		
		// 2.the value of Update Restriction Window is:@!# or SDF
		permitTypeInfo.updateResWindow = "@!# or SDF";
		this.verifyMsg(message2, true);

		// 3.edit successfully
		permitTypeInfo.updateResWindow = "0";
		this.verifyMsg(successMsg, true);
		
		// 4.edit successfully
		permitTypeInfo.issueStartDateType = "1 day prior to entry";
		permitTypeInfo.updateResWindow = "1";
		this.verifyMsg(successMsg, true);
		
		// 5.remove permit type information
		im.gotoEditEntrancePage(entranceInfo);
		this.verifyRemoveSuccess();
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

		// searchCriteria
		entranceInfo.searchBy = "Entrance Code";
		entranceInfo.searchValue = entranceInfo.entranceCode;
		entranceInfo.status = "Inactive";
		
		// permitTypeInfo
		permitTypeInfo.permitType = "Day Use Motor";
		permitTypeInfo.permitTypeID = "280877670";
		permitTypeInfo.maxGroupSize ="5";
		permitTypeInfo.maxWatercraft = "10";
		permitTypeInfo.issueStartDateType = "On Entry Day";
		permitTypeInfo.startDateTime = "6:00";
		permitTypeInfo.startAmPm = "AM";
		permitTypeInfo.issueStartDateType = "On Entry Day";
		permitTypeInfo.endDateTime = "9:00";
		permitTypeInfo.endAmPm = "PM";
		permitTypeInfo.updateResWindow = "1";
		permitTypeInfo.status = "Active";
	}
		
	/**
	 * Verify error message.
	 * 
	 * @param expectMsg
	 */
	private void verifyMsg(String expectMsg, boolean flag){
		logger.info("Verify error message:"+expectMsg);
		
		// edit entrance info.
		String message = im.editEntrance(entranceInfo, null, permitTypeInfo, false);
		if(!expectMsg.equals(message)){
			throw new ErrorOnPageException("Displayed message:"+message+" is not the same as expect message:"+expectMsg);
		}
		if(flag){
			editEntrancePg.clickCancel();
			entranceListPg.waitLoading();
		}
		logger.info("Verify error message successfully.");
	}
	
	/**
	 * Verify whether remove permit type information successful or not.
	 */
	public void verifyRemoveSuccess(){
		logger.info("Verify whether remove permit type information successful or not.");
		editEntrancePg.clickRemove();
		ajax.waitLoading();
		boolean flag = editEntrancePg.checkPerminTypeExist();
		
		// if permit type info existed
		if(flag){
			throw new ErrorOnPageException("The permit type information hasn't been remove successfully.");
		}
	}
}
