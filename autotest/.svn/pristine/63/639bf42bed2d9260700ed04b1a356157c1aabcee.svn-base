package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryAssignNewParticipationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is to verify assign a participation location to marina lottery(slip lottery product), and the lottery is not on-going
 * the steps as follow:
 *               1.Assign a location which has already been assigned to the lottery
 *               2.Assign a location which is overlapped with the assigned location of the lottery
 * @Preconditions:
 * 1. Make sure the following two lottery has been setup: location_valid_when_assign_for_slipLottery
 * 2. Make sure the dock has been added for the park:AddSeasonDock 
 *    Note:The docks are borrowed from other case which has written before this case, and it does not matter what the dock is
 * @LinkSetUp: [linked--<changed>-->set up by case self] d_inv_new_lottery_program:id=400(LOTTERYNAME='location_valid_when_assign_for_slipLottery')
 *             [linked]  d_inv_add_dock_area:id=160(DOCKAREANAME='AssParticipationDock1') 
 * @SPEC: TC:042119
 * @Task#: Auto-1342
 * @author Phoebe Chen
 * @Date  Dec 24, 2012
 */
public class AssignParticipation_ParticipationLocationValidation extends InventoryManagerTestCase{
	private String errorMsg1 ="";
	private String errorMsg2 ="";
	LotteryAssignNewParticipationPage lotteryAssignNewParticiptionPg = LotteryAssignNewParticipationPage
			.getInstance();

	@Override
	public void execute() {
		//login into the inventory manager
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		//Go to the lottery details page
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		
		if(!locationAssignedToLottery(lottery.area)){
			im.assignLocationToLottery(lottery);
		}
		
		//Assign the location which has already been assigned to the lottery
		im.assignLocationToLottery(lottery);
		//Verify the error message
		this.verifyErrorMessage(errorMsg1);
		
		//Assign location which is overlapped with the already assigned location
		lottery.area = "All";
		im.assignLocationToLottery(lottery);
		//Verify the error message
		this.verifyErrorMessage(errorMsg2);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "location_valid_when_assign_for_slipLottery";
		
		lottery.locationCategory = "Park";
		lottery.description = "QA AUTO TEST";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";
		lottery.area = "AssParticipationDock1";   //It is shown as dock for marina lottery of this case
			
		errorMsg1 = "The selected Location is already a Participating Location in the Lottery. Duplicates are not allowed.";
		errorMsg2 = "The selected Location overlaps with another participating location associated with the Lottery. Please change the participating location.";
	}
	
	private void setPreferenceAttributes() {
		lottery.attributes.clear();
		String[] attributes = {"Facility","Min Slip Depth","Min Slip Length","Min Slip Width","Boat Category","Dock"};
		for (int i = 0; i < attributes.length; i++) {
			LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute();
			attr.label = attributes[i];
			attr.displayOrder = String.valueOf(i + 1);
			lottery.attributes.add(attr);
		}
	}
	
	public boolean locationAssignedToLottery(String location){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String sql = "select id from P_PRD_LOTTERY_PARTICIPATION where loc_id=(select id from d_loc where name='" + location +
				"') and lottery_id=" + lottery.id;
		logger.info("Excute sql:" + sql);
		List<String> idList = db.executeQuery(sql, "ID");
		if(idList.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	private void verifyErrorMessage(String errorMsg) {
		String errorMessage = lotteryAssignNewParticiptionPg.getErrorMessage();
		if(!errorMsg.equalsIgnoreCase(errorMessage)){
			throw new ErrorOnPageException("The error message:"+errorMsg+" is expected, but now it is:" + errorMessage);
		}
	}
}
