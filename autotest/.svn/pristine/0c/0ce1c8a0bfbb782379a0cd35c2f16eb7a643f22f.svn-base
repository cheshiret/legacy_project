package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.manualhuntselection;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: (P) In hunt selection page, verify error message and successfully message
 * @Preconditions: Privilege lottery setup: Min choice for lottery=2; Max choice for lottery=5
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1580
 * D_HF_ADD_PRIVILEGE_PRD:id=3170  -ABT ABLotteryAppPriv 
 * D_HF_ADD_PRICING:id=4612,4622
 * D_HF_ASSI_PRI_TO_STORE:id=2390
 * D_HF_ADD_QTY_CONTROL:id=2370
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3300
 * D_HF_ADD_HUNT:id=1110,1120,1130,1140,1150,1160,1170
 * -ABH, AB Walleye Hunt, Individual;Group,DisplayOrder(3)
 * -bAB, bAB Walleye Hunt, Individual;Group, DisplayOrder(1)
 * -3AB, 3 Walleye Hunt, Individual;Group, DisplayOrder(empty) 
 * -IAB, Individual Walleye Hunt, Individual, DisplayOrder(empty) 
 * -GAB, Group Walleye Hunt, Group, DisplayOrder(empty) 
 * -cAB, cAB Walleye Hunt, Individual, DisplayOrder(empty)
 * -dAB, dAB Walleye Hunt, Individual, DisplayOrder(empty)
 * D_HF_ADD_HUNT_QUOTA:id=340
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1190,1200,1210,1220,1230,1240,1250
 * D_HF_ASSIGN_PRIV_TO_HUNT:id=830,840,850,860,870,880,890
 * D_HF_ADD_LOTTERY_PRD:id=560
 * D_HF_ADD_LOTTERY_LICENSE_YEAR:id=440
 * D_HF_ASSIGN_LOTTERY_TO_STORE:id=430
 * D_HF_ADD_LOTTERY_QUANTITY_CONT:id=440
 * D_HF_ASSI_HUNTS_TO_LOTTERY:id=630,640,650,660,670.680
 * D_HF_ADD_LOTTERY_PARAMETER:id=20
 * @SPEC:Manual Hunt selection - validation error message [TC:121558]
 * @Task#:AUTO-2129
 * 
 * @author SWang
 * @Date  Jun 5, 2014
 */
public class ErrorMsgValidation extends HFABLotteryApplicationTestCase{
	private String errorMsg1, errorMsg2, errorMsg3, errorMsg4;
	private String displayOrder_1, displayOrder_3;
	
	@Override
	public void execute() {
		//Precondition: Update hunts' display order
		hfab.updateHuntDisplayOrder(hunt2.getHuntCode(), displayOrder_3, schema);
		hfab.updateHuntDisplayOrder(hunt4.getHuntCode(), displayOrder_1, schema);
		
		//Go to hunt selection page
		hfab.signIn(url, cus.email, cus.password);
		hfab.gotoAddHuntsPgAsGroupLeaderFromHeaderBar(cus, lottery.getLegalName());

		//Leave all the dropdowns as default
		verifyUIMsg(new String[]{errorMsg1}, null,null,null,null);

		//Select a hunt for 1st choice, submit
		verifyUIMsg(new String[]{errorMsg1}, hunt2, null, null, null);

		//Select a hunt for 3rd choice, submit
		verifyUIMsg(new String[]{errorMsg2}, hunt2, null, hunt3, null);

		//Select the same hunt as 1st choice for 2nd choice, submit
		verifyUIMsg(new String[]{errorMsg3}, hunt2, hunt2, null, null);

		//leave only 1st choice has option selected, submit
		verifyUIMsg(new String[]{errorMsg1}, hunt2, null, null, null);

		//Select the same hunt as 1st choice for 3rd choice, submit
		verifyUIMsg(new String[]{errorMsg2,errorMsg3}, hunt2, null, hunt2, null);

		//Select different hunts for top 3 choices, submit
		verifyUIMsg(new String[]{errorMsg4}, true, hunt1, hunt2, hunt3, hunt4);

		//Verify hunt section info in shopping cart page
		verifyHuntSelectionsInShoppingCart();
		
		hfab.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.custNum = hfab.getCustomerNumByEmail("hfab_00010@webhftest.com", schema);
		cus.email = cus.custNum;
		cus.residencyStatus = RESID_STATUS_ALBERTA;

		//Lottery info
		lottery.setDescription("AB Walleye Lottery");
		lottery.setLegalName("AB Walleye Lottery Legal");
		lottery.setLicenseYear(hfab.getFiscalYear(schema));
		lottery.setMaxChoices("5");

		// Hunts Info
		hunt1 = new HuntInfo();
		hunt1.setDescription("bAB Walleye Hunt");
		hunt1.setHuntCode("bAB");
		
		hunt2 = new HuntInfo();
		hunt2.setDescription("AB Walleye Hunt");
		hunt2.setHuntCode("ABH");
		
		hunt3 = new HuntInfo();
		hunt3.setDescription("3 Walleye Hunt");
		hunt3.setHuntCode("3AB");
		
		hunt4 = new HuntInfo();
		hunt4.setDescription("Group Walleye Hunt");
		hunt4.setHuntCode("GAB");
		
		displayOrder_1 = "1";
		displayOrder_3 = "3";
		
		errorMsg1 = "The minimum number of choices is 2";
		errorMsg2 = "A choice selection was blank\\. Please update and continue\\.";
		errorMsg3 = "You have selected duplicate choices\\. Please update and continue";
		errorMsg4 = "Your choices are displayed below\\. Please confirm these are valid by clicking Continue to proceed\\.";
	}

	/**
	 * Verify error message and successful message in hunt selection page
	 * @param meses
	 * @param successMsg: true:successfully message, false:error message
	 * @param hunts
	 */
	private void verifyUIMsg(String[] meses, boolean successMsg, HuntInfo...hunts){
		huntsPg.setupHuntChoices(hunts);
		huntsPg.clickContinueBtn();
		huntsPg.waitLoading();

		boolean result = true;
		for(int i=0; i<meses.length; i++){
			if(successMsg){
				result &= MiscFunctions.compareResult("Success message", true, huntsPg.isSuccessMsgExist(meses[i]));
			}else result &= MiscFunctions.compareResult("Error message", true, huntsPg.isErrorMsgExist(meses[i]));
		}

		if (!result){
			throw new ErrorOnPageException("Failed to verify all error messages, please check the details from previous logs.");
		}
		logger.info("Successfully verify all error messages.");
	}

	private void verifyUIMsg(String[] meses, HuntInfo...hunts){
		verifyUIMsg(meses, false, hunts);
	}
	
	private void verifyHuntSelectionsInShoppingCart(){
		huntsPg.clickContinueBtn();
		addGroupMembersPg.waitLoading();
		addGroupMembersPg.clickContinueBtn();
		addGroupMembersPg.waitLoading();
		addGroupMembersPg.waitForSuccessMsg();
		addGroupMembersPg.clickContinueBtn();
		shoppingCartPg.waitLoading();
		
		shoppingCartPg.verifyLotteryHuntChoices(lottery.getLegalName(), hunt1.getHuntCode(), hunt2.getHuntCode(), hunt3.getHuntCode(), hunt4.getHuntCode());
	}
}

