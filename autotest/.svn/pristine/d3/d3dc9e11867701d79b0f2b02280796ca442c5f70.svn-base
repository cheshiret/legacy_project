package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEditEntrancePage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case is for PCR2530.Edit an existed entrance info.
 * @Preconditions:TC035844
 * @SPEC:
 * @Task#:Auto-893
 * 
 * @author nding1
 * @Date  Feb 23, 2012
 */
public class EditEntrance extends InventoryManagerTestCase{
	private InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage.getInstance();
	private EntranceInfo entranceInfo = new EntranceInfo();
	private PermitTypeInformation permitTypeInfo = new PermitTypeInformation();
	private String successMsg = "Entrance information updated successfully.";
	
    public void execute(){

		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		// go to Entrance List Page
		im.gotoEntranceListPage("Entrance Set-up");
		im.addNewEntrance(entranceInfo, null, permitTypeInfo, false);
		this.editEntrance();
		this.verifyEditEntranceInfo();
		im.logoutInvManager();
    }
    
    public void wrapParameters(Object[] args) {
        login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		inventory.facilityName = "Boundary Waters Canoe Area Wilderness (Reservations)";// ID=72600
		
		// entranceInfo
		entranceInfo.entranceCode = "AddNewEntrance"+DateFunctions.getCurrentTime();
		entranceInfo.entranceName = "Add New Entrance";
		entranceInfo.entranceType = "Entry Point";
		entranceInfo.description = "Add New Entrance for PCR2530.";
		entranceInfo.entryType = "Entrance";

		// searchCriteria
		entranceInfo.searchBy = "Entrance Code";
		entranceInfo.searchValue = entranceInfo.entranceCode;
		entranceInfo.status = "Inactive";
		
		// permitTypeInfo
		permitTypeInfo.permitType = "Day Use Motor";
		permitTypeInfo.permitTypeID = "280877670";
		permitTypeInfo.maxGroupSize ="6";
		permitTypeInfo.maxWatercraft = "11";
		permitTypeInfo.issueStartDateType = "On Entry Day";
		permitTypeInfo.startDateTime = "6:00";
		permitTypeInfo.startAmPm = "AM";
		permitTypeInfo.issueEndDateType = "On Entry Day";
		permitTypeInfo.endDateTime = "10:00";
		permitTypeInfo.endAmPm = "PM";
		permitTypeInfo.updateResWindow = "1";
		permitTypeInfo.status = "Active";
    }
	
    /**
     * Edit Entrance and verify whether edit successfully or not.
     */
    private void editEntrance(){
    	entranceInfo.entranceName = "Edit New Entrance";
		entranceInfo.entranceType = "Boat Launch";
		entranceInfo.description = "Add New Entrance for PCR2530 and edit.";
		entranceInfo.entryType = "Exit Point";
		String message = im.editEntrance(entranceInfo, null, permitTypeInfo, false);
		if(!successMsg.equals(message)) {
			logger.error("The entrance has not been edit successfully!");
			throw new ErrorOnPageException("The entrance has not been edit successfully!");
		}
    }
    
    /**
     * Verify info of Entrance is correct or not.
     */
    private void verifyEditEntranceInfo(){
		logger.info("Verify info of Entrance is correct or not.");
		im.gotoEditEntrancePage(entranceInfo);
		EntranceInfo entranceInfoUI = editEntrancePg.getEntranceInfo();
		PermitTypeInformation permitTypeInfoUI = editEntrancePg.getPermitTypeInfo();

		// verify entrance info
		if(!entranceInfo.entranceCode.equals(entranceInfoUI.entranceCode)){
			throw new ErrorOnPageException("Entrance Code is not correct.Expect is:"+entranceInfo.entranceCode+", but actual is:"+entranceInfoUI.entranceCode);
		}

		if(!entranceInfo.entranceName.equals(entranceInfoUI.entranceName)){
			throw new ErrorOnPageException("Entrance Name is not correct.Expect is:"+entranceInfo.entranceName+", but actual is:"+entranceInfoUI.entranceName);
		}

		if(!entranceInfo.entranceType.equals(entranceInfoUI.entranceType)){
			throw new ErrorOnPageException("Entrance Type is not correct.Expect is:"+entranceInfo.entranceType+", but actual is:"+entranceInfoUI.entranceType);
		}

		if(!entranceInfo.entryType.equals(entranceInfoUI.entryType)){
			throw new ErrorOnPageException("Entry Type is not correct.Expect is:"+entranceInfo.entryType+", but actual is:"+entranceInfoUI.entryType);
		}

		if(!entranceInfo.description.equals(entranceInfoUI.description)){
			throw new ErrorOnPageException("Entrance Description is not correct.Expect is:"+entranceInfo.description+", but actual is:"+entranceInfoUI.description);
		}

		// verify permit type info
		if(!permitTypeInfo.permitType.equals(permitTypeInfoUI.permitType)){
			throw new ErrorOnPageException("Permit Type is not correct.Expect is:"+permitTypeInfo.permitType+", but actual is:"+permitTypeInfoUI.permitType);
		}

		if(!permitTypeInfo.maxGroupSize.equals(permitTypeInfoUI.maxGroupSize)){
			throw new ErrorOnPageException("Max Group Size is not correct.Expect is:"+permitTypeInfo.maxGroupSize+", but actual is:"+permitTypeInfoUI.maxGroupSize);
		}

		if(!permitTypeInfo.maxWatercraft.equals(permitTypeInfoUI.maxWatercraft)){
			throw new ErrorOnPageException("Max Watercraft is not correct.Expect is:"+permitTypeInfo.maxWatercraft+", but actual is:"+permitTypeInfoUI.maxWatercraft);
		}

		if(!permitTypeInfo.issueStartDateType.equals(permitTypeInfoUI.issueStartDateType)){
			throw new ErrorOnPageException("Issue Start Date Type is not correct.Expect is:"+permitTypeInfo.issueStartDateType+", but actual is:"+permitTypeInfoUI.issueStartDateType);
		}

		if(!permitTypeInfo.startDateTime.equals(permitTypeInfoUI.startDateTime)){
			throw new ErrorOnPageException("Start Date Time is not correct.Expect is:"+permitTypeInfo.startDateTime+", but actual is:"+permitTypeInfoUI.startDateTime);
		}

		if(!permitTypeInfo.startAmPm.equals(permitTypeInfoUI.startAmPm)){
			throw new ErrorOnPageException("Start Date/Time AmPm is not correct.Expect is:"+permitTypeInfo.startAmPm+", but actual is:"+permitTypeInfoUI.startAmPm);
		}

		if(!permitTypeInfo.issueEndDateType.equals(permitTypeInfoUI.issueEndDateType)){
			throw new ErrorOnPageException("Issue End Date Type is not correct.Expect is:"+permitTypeInfo.issueEndDateType+", but actual is:"+permitTypeInfoUI.issueEndDateType);
		}

		if(!permitTypeInfo.endDateTime.equals(permitTypeInfoUI.endDateTime)){
			throw new ErrorOnPageException("End Date Time is not correct.Expect is:"+permitTypeInfo.endDateTime+", but actual is:"+permitTypeInfoUI.endDateTime);
		}

		if(!permitTypeInfo.endAmPm.equals(permitTypeInfoUI.endAmPm)){
			throw new ErrorOnPageException("End Date/Time AmPm is not correct.Expect is:"+permitTypeInfo.endAmPm+", but actual is:"+permitTypeInfoUI.endAmPm);
		}

		if(!permitTypeInfo.updateResWindow.equals(permitTypeInfoUI.updateResWindow)){
			throw new ErrorOnPageException("Update Res Window is not correct.Expect is:"+permitTypeInfo.updateResWindow+", but actual is:"+permitTypeInfoUI.updateResWindow);
		}

		if(!permitTypeInfo.status.equals(permitTypeInfoUI.status)){
			throw new ErrorOnPageException("Status is not correct.Expect is:"+permitTypeInfo.status+", but actual is:"+permitTypeInfoUI.status);
		}
    }
}
