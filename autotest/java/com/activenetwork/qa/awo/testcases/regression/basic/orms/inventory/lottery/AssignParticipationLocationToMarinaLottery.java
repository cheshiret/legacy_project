package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryParticipationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is to verify assign a participation location to marina lottery(slip lottery product), and the lottery is not on-going
 * the steps as follow:
 *               1.Make sure there is a lottery who has no participation
 *               2.Lottery with no Participation:Assign participation location to the lottery and check the information
 *               3.Lottery with one participation:Assign another participation location to the lottery and check the information
 *               4.Set up participation information and click cancel and check the participation is not assigned
 * @Preconditions:
 * 1. Make sure the lottery has been setup:assign_participation_location_forSlipLottery
 * 2. Make sure three docks has been added for the park:AssParticipationDock1  & AssParticipationDock2 & AssParticipationDock3
 *    Note:The docks are borrowed from other case which has written before this case, and it does not matter what the dock is,just make sure they
 *          are three different docks
 * @LinkSetUp[linked--<changed>-->set up by case self]:  d_inv_new_lottery_program:id=340(LOTTERYNAME='assign_participation_location_forSlipLottery')
             [linked] d_inv_add_dock_area:id=160(DOCKAREANAME='AssParticipationDock1')  
             [linked] d_inv_add_dock_area:id=170(DOCKAREANAME='AssParticipationDock2')  
             [linked] d_inv_add_dock_area:id=180(DOCKAREANAME='AssParticipationDock3')  
 * @SPEC: TC:042119
 * @Task#: Auto-1342
 * @author Phoebe Chen
 * @Date  Dec 24, 2012
 */
public class AssignParticipationLocationToMarinaLottery extends InventoryManagerTestCase{
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
		// Unassign all locations if the location has been assigned. 
		if (im.checkLocationAssigned(lottery.area)) {
			im.unassignLocationToLottery();
		}
		//Assign the location to lottery who has no Participation
		im.assignLocationToLottery(lottery);
		//Verify the assign
		this.verifyParticipationInfo();
		
		//Assign location to lottery who has one participation location
		lottery.area="AssParticipationDock2";
		lottery.productGroup = "All";
		im.assignLocationToLottery(lottery);
		//Verify the assign
		this.verifyParticipationInfo();
		
		//Assign location to lottery but click cancel
		lottery.area="AssParticipationDock3";
		im.assignLocationToLottery(lottery, false);
		//Verify the assign
		this.verifyParticipationInfoNotExist();
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "assign_participation_location_forSlipLottery";
		
		lottery.locationCategory = "Park";
		lottery.description = "QA AUTO TEST";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";
		lottery.area = "AssParticipationDock1";   //It is shown as dock for marina lottery of this case
		lottery.productGroup="Full attributes";
		lottery.products="All";
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

	public void verifyParticipationInfo(){
		LotteryParticipationPage lotteryPaticipationPg=LotteryParticipationPage.getInstance();
		
		List<String> participationInfo=lotteryPaticipationPg.getLotteryParticipationInfo(lottery.area);
		
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Participation Location:", lottery.area, participationInfo.get(1));
		passed &= MiscFunctions.compareResult("Participation ProductGroup:", lottery.productGroup, participationInfo.get(2));
		passed &= MiscFunctions.compareResult("Participation Products:", lottery.products, participationInfo.get(3));
		
		if(!passed){
			throw new ErrorOnPageException("New assigned participation location for lottery may not correct, please check the log above!");
		}
	}
	
	public void verifyParticipationInfoNotExist(){
		LotteryParticipationPage lotteryPaticipationPg=LotteryParticipationPage.getInstance();
		
		List<String> result=lotteryPaticipationPg.getLotteryParticipationInfo(lottery.area);
		
		if(result.size() != 0){
			throw new ErrorOnPageException(lottery.area+" has been assign to lottery after click 'Cancel'");
		}
	}
}
