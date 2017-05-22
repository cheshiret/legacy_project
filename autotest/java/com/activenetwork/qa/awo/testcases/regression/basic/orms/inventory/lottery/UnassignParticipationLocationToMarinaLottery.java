package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryParticipationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is to verify unassign a participation location to marina lottery(slip lottery product), and the lottery is not on-going
 * the steps is: Assign a participation to lottery then unasign the participation location
 * @Preconditions:
 * 1. Make sure the lottery has been setup(unassign_participation_location_forSlipLottery)
 * @LinkSetUp:[linked--<changed>-->set up by case self]  d_inv_new_lottery_program:id=390(LOTTERYNAME='unassign_participation_location_forSlipLottery')
 * @SPEC: TC:042119
 * @Task#: Auto-1342
 * @author Phoebe Chen
 * @Date  Dec 24, 2012
 */
public class UnassignParticipationLocationToMarinaLottery extends InventoryManagerTestCase{

	@Override
	public void execute() {
		//login into the inventory manager
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id = im.getLotteryId(schema,lottery.name);
		if(StringUtil.isEmpty(lottery.id)){
			lottery.id = im.addNewLottery(lottery);
		}
		
		//Go to the lottery details page
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		//assign the location to lottery if it hasn't
		if (!im.checkLocationAssigned(lottery.facility)) {
			im.assignLocationToLottery(lottery);
		}
		//unassign the location
		im.unassignLocationToLottery();

		this.verifyUnassignLocation();
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//Logininfo for inventory manager
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "unassign_participation_location_forSlipLottery";
		
		lottery.locationCategory = "Park";
		lottery.description = "QA AUTO TEST";		
		lottery.location = im.getFacilityName("552832", schema);//"Jones Lake State Park";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		
		lottery.facility = lottery.location;//"Jones Lake State Park";
		lottery.productGroup = "All";
		lottery.products = "All";
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

	public void verifyUnassignLocation(){
		LotteryParticipationPage lotteryPaticipationPg=LotteryParticipationPage.getInstance();
				
		List<String> result=lotteryPaticipationPg.getLotteryParticipationInfo(lottery.facility);
		
		if(!(result.size() == 0)){
			throw new ErrorOnPageException(lottery.area+" has been assign to lottery after unassign operation.");
		}
	}
}
