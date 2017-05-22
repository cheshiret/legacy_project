package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.entrance.edit;

import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceAttributesInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrAddNewEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEditEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Edit an entrance.
 * @Preconditions:Zone list in add new entrance page have elements.
 * @SPEC:Edit Entrance.UCS
 * @Task#:Auto-936 TC:036141
 * 
 * @author nding1
 * @Date  Feb 28, 2012
 */
public class EditEntranceZoneIndicator extends InventoryManagerTestCase {
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage.getInstance();
	private InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage.getInstance();
	private EntranceInfo entranceInfo = new EntranceInfo();
	private EntranceAttributesInfo attributesInfo = new EntranceAttributesInfo();
	private String errMessage = "";

	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(inventory.facilityID);
		im.gotoEntranceListPage("Entrance Set-up");
		
		List<String> zoneNameList = this.getZoneNameList();
		attributesInfo.zone = zoneNameList.get(new Random().nextInt(zoneNameList.size()) - 1);
		String zoneName = zoneNameList.get(new Random().nextInt(zoneNameList.size()) - 1);
		
		// add new Entrance
		im.addNewEntrance(entranceInfo, attributesInfo, null, false);
		
		// select inactive zone and verify message.
		this.verifyErrMessage(zoneName);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		inventory.facilityID = "72202";
		inventory.facilityName = "Desolation Wilderness";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		errMessage = "The selected Zone is inactive. Please change the Zone.";
		
		// entranceInfo
		entranceInfo.entranceCode = "VerifyZone"+DateFunctions.getCurrentTime();
		entranceInfo.entranceName = "Verify Zone";
		entranceInfo.entranceType = "Entry Point";
		entranceInfo.description = "Verify Zone for PCR2480.";
		entranceInfo.entryType = "Entrance";

		// searchCriteria
		entranceInfo.searchBy = "Entrance Code";
		entranceInfo.searchValue = entranceInfo.entranceCode;
		entranceInfo.status = "Inactive";
	}
	
	/**
	 * Update status of zone.
	 * 
	 * @param newValue
	 */
	private void updateActiveStatus(String newValue){
		logger.info("update status of zone.The new value is:"+newValue);
		db.resetSchema(schema);
		String update = "Update D_LOC set ACTIVE_IND = "+newValue+" where LOC_TYPE_ID = 4 and Name = '"+attributesInfo.zone+"' and PARENT_ID = '"+inventory.facilityID+"'";
		int num = db.executeUpdate(update);
		if(num != 1){
			throw new ErrorOnPageException("The number of Update is not correct.Except number is 1.");
		}
	}
	
	/**
	 * Select an inactive zone to verify error message.
	 * 
	 * @param zoneName
	 */
	private void verifyErrMessage(String zoneName){
		logger.info("Select an inactive zone to verify error message:"+errMessage);
		im.gotoEditEntrancePage(entranceInfo);
		attributesInfo.zone = zoneName;
		
		// make a zone from active to inactive
		this.updateActiveStatus(OrmsConstants.STATUS_INACTIVE);

		// set up information.
		editEntrancePg.setUpInfoForEdit(entranceInfo, attributesInfo, null, false);
		editEntrancePg.clickOK();
		
		
		Object page = browser.waitExists(entranceListPg, editEntrancePg);
		
		// make that zone from inactive to active
		this.updateActiveStatus(OrmsConstants.STATUS_ACTIVE);
		
		// verify message
		if(page == editEntrancePg){
			String message = editEntrancePg.getErrMsg();
			if(!errMessage.equals(message)){
				throw new ErrorOnPageException("Displayed message:"+message+" is not the same as expect message:"+errMessage);
			}
		} else {
			throw new ErrorOnPageException("Error message:"+errMessage+"should be displayed in Edit Entrance page.");
		}
	}
	
	/**
	 * Get Zone list for add and edit Entrance.
	 */
	private List<String> getZoneNameList(){
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage.getInstance();
		InvMgrAddNewEntrancePage newEntrancePg = InvMgrAddNewEntrancePage.getInstance();
		logger.info("Get Zone list for add and edit Entrance.");
		
		// go to add new entrance page
		entranceListPg.clickAddNew();
		newEntrancePg.waitLoading();
		
		// get zone list from page.
		List<String> zoneListInPage = newEntrancePg.getAllZone();
		newEntrancePg.clickCancel();
		entranceListPg.waitLoading();
		return zoneListInPage;
	}
}
