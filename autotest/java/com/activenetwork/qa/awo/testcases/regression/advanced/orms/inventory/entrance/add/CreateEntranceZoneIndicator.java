package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.entrance.add;

import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceAttributesInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrAddNewEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: TC036139 Step 4: For all Permit facilities using zones within the NRRS contract, check the table D_loc where loc_type_id=4, 
 * The value of active/inactive indicator shall be set to active as initial.
 * @Preconditions:Zone list in add new entrance page have elements.
 * @SPEC:Create Entrance.UCS
 * @Task#:Auto-936 TC:036138 TC036139(Step4)
 * 
 * @author nding1
 * @Date  Feb 27, 2012
 */
public class CreateEntranceZoneIndicator extends InventoryManagerTestCase {
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private EntranceInfo entranceInfo = new EntranceInfo();
	private EntranceAttributesInfo attributesInfo = new EntranceAttributesInfo();
	private InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage.getInstance();
	private InvMgrAddNewEntrancePage newEntrancePg = InvMgrAddNewEntrancePage.getInstance();
	private String errMessage = "";
	
	public void execute(){
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(inventory.facilityID);
		
		// go to Entrance Detail Page
		im.gotoEntranceListPage("Entrance Set-up");

		// 1.verify default value of Zone.
		this.verifyZoneDefaultSelectedValue();

		// 2.verify all active zone displayed in the drop down list.
		List<String> zonesList = this.getActiveZone();
		this.verifyZoneList(zonesList);
		
		// 3.verify initial status of all the zone is Active.
		//this.verifyInitialStatus();//As Author's comment, this step is no need to verify
		
		// 4.select inactive zone and verify message.
		attributesInfo.zone = zonesList.get(new Random().nextInt(zonesList.size() - 1));
		this.verifyErrMessage();
		im.logoutInvManager();
	}
	
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		inventory.facilityID = "72202";
		inventory.facilityName = "Desolation Wilderness";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		
		// entranceInfo
		entranceInfo.entranceCode = "VerifyZone"+DateFunctions.getCurrentTime();
		entranceInfo.entranceName = "Verify Zone";
		entranceInfo.entranceType = "Entry Point";
		entranceInfo.description = "Verify Zone for PCR2480.";
		entranceInfo.entryType = "Entrance";
		attributesInfo.zone = "Please Select";
		errMessage = "The selected Zone is inactive. Please change the Zone.";
	}
	
	/**
	 * Verify whether the default value of Zone is 'Please Select' or not.
	 */
	private void verifyZoneDefaultSelectedValue(){
		logger.info("Verify whether the default value of Zone is '" + attributesInfo.zone + "' or not.");
		
		// go to add new entrance page
		im.gotoNewEntrancePage();

		// get the default value of Zone
		String zone = newEntrancePg.getZone();
		logger.info("The selection now is:"+zone);
		if(!attributesInfo.zone.equals(zone)) {
			throw new ErrorOnPageException("Default value of Zone should be:"+attributesInfo.zone+".But actual is:"+ zone);
		}
		newEntrancePg.clickCancel();
		entranceListPg.waitLoading();
	}
	
	/**
	 * Get all active Zones from Table:D_LOC.
	 * 
	 * @return
	 */
	private List<String> getActiveZone(){
		logger.info("Get all active Zones from Table:D_LOC.");
		db.resetSchema(schema);
		
		// get all active Zone.
		String query = "Select NAME from D_LOC where LOC_TYPE_ID = 4 and ACTIVE_IND = 1 and PARENT_ID = '"+inventory.facilityID+"' ORDER BY NAME";
		List<String> list = db.executeQuery(query, "NAME");
		if(null == list || list.size() == 0) {
			throw new ErrorOnPageException("There isn't any active zone!");
		}
		return list;
	}
	
	/**
	 * Verify Zone drop down list is correct or not.
	 * 
	 * @param expectedZones
	 */
	private void verifyZoneList(List<String> expectedZones){
		logger.info("Verify Zone drop down list is correct or not.");
		boolean result = true;
		
		// go to add new entrance page
		im.gotoNewEntrancePage();

		// get zone list from page.
		List<String> zoneListInPage = newEntrancePg.getAllZone();
		
		// the first one is 'please select'
		zoneListInPage.remove(0);
		
		// verify size is correct or not.
		if(zoneListInPage.size() != expectedZones.size()){
			result &= false;
			logger.error("The number of element in Zone list in the page is different from number in DB!!Number in page is:"+zoneListInPage.size()+", number in DB is:"+expectedZones.size());
		}

		// verify content is correct or not.
		for(int i=0;i<expectedZones.size();i++){
			if(!zoneListInPage.contains(expectedZones.get(i).trim())){
				result &= false;
				logger.error("The element displayed in the page is different from DB.Element on page is:"+zoneListInPage.get(i)+", in DB is:"+expectedZones.get(i).trim());
			}
		}
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Please check the log above.");
		} else {
			logger.info("All active Zone is displayed in the drop down list.");
		}
		newEntrancePg.clickCancel();
		entranceListPg.waitLoading();
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
	 */
	private void verifyErrMessage(){
		logger.info("Select an inactive zone to verify error message:"+errMessage);

		// go to add new entrance page
		entranceListPg.clickAddNew();
		newEntrancePg.waitLoading();

		// set up entrance information.
		newEntrancePg.setUpEntranceInfo(entranceInfo);
		
		// make a zone from active to inactive
		this.updateActiveStatus(OrmsConstants.STATUS_INACTIVE);
		
		// set up entrance attributes.
		newEntrancePg.setUpEntranceAttriInfo(attributesInfo);
		newEntrancePg.clickOK();
		
		Object page = browser.waitExists(entranceListPg, newEntrancePg);
		
		// make that zone from inactive to active
		this.updateActiveStatus(OrmsConstants.STATUS_ACTIVE);
		// verify message
		if(page == newEntrancePg){
			String message = newEntrancePg.getErrMsg();
			if(!errMessage.equals(message)){
				throw new ErrorOnPageException("Displayed message:"+message+" is not the same as expect message:"+errMessage);
			}
		} else {
			throw new ErrorOnPageException("Error message:"+errMessage+" should be displayed in Add New Entrance page.");
		}
	}
	
//	/**
//	 * Verify the initial status of all the zone is Active or not.
//	 */
//	private void verifyInitialStatus(){
//		logger.info("Verify the initial status of all the zone is Active or not.");
//		boolean statusFlag = true;
//		db.resetSchema(schema);
//		
//		// get status and name from DB.
//		String query = "Select NAME,ACTIVE_IND from D_LOC where PARENT_ID = '"+inventory.facilityID+"'";
//		String[] colNames = {"NAME", "ACTIVE_IND"};
//		List<String[]> resultList = db.executeQuery(query, colNames);
//		
//		for(String[] result: resultList){
//			
//			// check if the status is active or not.
//			if(!OrmsConstants.STATUS_ACTIVE.equals(result[1])){
//				statusFlag = false;
//				logger.error("The initial Active_IND(name = "+result[0]+") is inactive!");
//			}
//		}
//		if(!statusFlag){
//			throw new ErrorOnPageException("Not all the initial Active_IND of zone is active! Please check the log above.");
//		}
//		logger.info("All the initial Active_IND of zone is active!");
//	}
	
}
