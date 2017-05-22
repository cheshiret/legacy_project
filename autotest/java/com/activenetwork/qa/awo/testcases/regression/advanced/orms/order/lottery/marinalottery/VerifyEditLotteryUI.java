package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify editing lottery details UI info
 * @Preconditions: 
 * @SPEC: Editing the Lottery Details [TC:043077]
 * @Task#:Auto-1344

 * @author VZhang
 * @Date Dec 24, 2012
 */

public class VerifyEditLotteryUI extends InventoryManagerTestCase{
	private LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
	private LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();

	@Override
	public void execute() {
		// Goto Lottery Details page
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		this.prepareLottery();
		
		im.gotoLotteryDetailsPageFromLotterySearchPage(lottery);
		//verify lottery id and name and coverage
		this.verifyLotteryIDNameCoverageDisable();
		//verify product group
		this.verifyProductGroup();
		//verify application submission rule
		this.verifyAppSubmissionRule();
		//verify award rule
		this.verifyAwardRule();
		
		im.logoutInvManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		lottery.name = "VerifyEditLotteryUI";
		lottery.location =  im.getFacilityName("552903", schema); //Jordan Lake State Rec Area
		lottery.locationCategory = "Park";
		lottery.description = "VerifyEditLotteryUI";
		lottery.productCategory = "Slip";
		lottery.isCollectCreditCard = true;
		lottery.isRequiresCustomerAcceptance = false;
		LotteryPreferenceAttribute attr1 = new LotteryPreferenceAttribute();
		attr1.label ="Facility";
		attr1.displayOrder ="1";
		LotteryPreferenceAttribute attr2 = new LotteryPreferenceAttribute();
		attr2.label ="Min Slip Depth";
		attr2.displayOrder ="2";
		LotteryPreferenceAttribute attr3 = new LotteryPreferenceAttribute();
		attr3.label ="Min Slip Length";
		attr3.displayOrder ="3";
		LotteryPreferenceAttribute attr4 = new LotteryPreferenceAttribute();
		attr4.label ="Min Slip Width";
		attr4.displayOrder ="4";
		LotteryPreferenceAttribute attr5 = new LotteryPreferenceAttribute();
		attr5.label ="Boat Category";
		attr5.displayOrder ="5";
		LotteryPreferenceAttribute attr6 = new LotteryPreferenceAttribute();
		attr6.label ="Dock";
		attr6.displayOrder ="6";
		attr6.entryRequired = "Per Preference";
		lottery.attributes.add(attr1);
		lottery.attributes.add(attr2);
		lottery.attributes.add(attr3);
		lottery.attributes.add(attr4);
		lottery.attributes.add(attr5);
		lottery.attributes.add(attr6);
	}
	
	private void prepareLottery(){
		logger.info("Prepare lottery.");
		lotterySearchPg.searchLotterByName(lottery.name);
		
		List<String> lotteryIDs = lotterySearchPg.getLotteryIDColumnValues();
		if(lotteryIDs.size()>0){
			lottery.id = lotteryIDs.get(0);
		}else{
			lottery.id = im.addNewLottery(lottery);
		}
	}
	
	private void verifyLotteryIDNameCoverageDisable(){
		boolean result = true;
		
		boolean actLotteryIDEditable = lotteryDetailsPg.checkLotteryIDIsEditable();
		boolean actLotteryNameEditable = lotteryDetailsPg.checkLotteryNameIsEditable();
		boolean actLotteryCoverageEditable = lotteryDetailsPg.checkLotteryCoverageIsEditable();
		
		result &= MiscFunctions.compareResult("Lottery ID is Editable", false, actLotteryIDEditable);
		result &= MiscFunctions.compareResult("Lottery Name is Editable", false, actLotteryNameEditable);
		result &= MiscFunctions.compareResult("Lottery Coverage is Editable", false, actLotteryCoverageEditable);
		if(!result){
			throw new ErrorOnPageException("Lottery ID or Name Or Coverage editable info not correct, please check log.");
		}else{
			logger.info("Lottery ID or Name Or Coverage editable info is correct.");
		}
	}
	
	private void verifyProductGroup(){
		boolean actIsEnabled = lotteryDetailsPg.checkProductGroupsIsEnabled();
		if(actIsEnabled){
			throw new ErrorOnPageException("The Product Group should be disabled.");
		}else{
			logger.info("The Product Group is disabled.");
		}	
	}
	
	private void verifyAppSubmissionRule(){
		boolean result = true;
		//after click Add button, could add new submission rule
		int submissionRuleDropDownListObjectLengthOrg = lotteryDetailsPg.getSubmissionRuleDropDownListObjectLength();
		int submissionRuleRemoveObjectLengthOrg = lotteryDetailsPg.getSubmissionRuleRemoveObjectLength();		
		lotteryDetailsPg.clickAddNewSubRules();
		int submissionRuleDropDownListObjectLengthPre = lotteryDetailsPg.getSubmissionRuleDropDownListObjectLength();
		int submissionRuleRemoveObjectLengthPre = lotteryDetailsPg.getSubmissionRuleRemoveObjectLength();
		
		result &= MiscFunctions.compareResult("Submission Rule drop down list Object length after click Add", 
				submissionRuleDropDownListObjectLengthOrg+1, submissionRuleDropDownListObjectLengthPre);
		result &= MiscFunctions.compareResult("Remove Object length after click Add", 
				submissionRuleRemoveObjectLengthOrg+1, submissionRuleRemoveObjectLengthPre);
		
		//after click Remove button, could remove new submission rule
		lotteryDetailsPg.clickRemoveSubmissionRule(0);
		submissionRuleDropDownListObjectLengthPre = lotteryDetailsPg.getSubmissionRuleDropDownListObjectLength();
		submissionRuleRemoveObjectLengthPre = lotteryDetailsPg.getSubmissionRuleRemoveObjectLength();
		result &= MiscFunctions.compareResult("Submission Rule drop down list Object length after click Remove", 
				submissionRuleDropDownListObjectLengthOrg, submissionRuleDropDownListObjectLengthPre);
		result &= MiscFunctions.compareResult("Remove Object length after click Add", 
				submissionRuleRemoveObjectLengthOrg, submissionRuleRemoveObjectLengthPre);
		
		if(!result){
			throw new ErrorOnPageException("Application Submission Rule Add/Remove function not correct, please check log.");
		}else{
			logger.info("Application Submission Rule Add/Remove function is correct.");
		}
	}
	
	private void verifyAwardRule(){
		boolean result = true;
		//after click Add button, could add new award rule
		int awardRuleDropDownListObjectLengthOrg = lotteryDetailsPg.getAwardRuleDropDownListObjectLength();
		int awardRuleRemoveObjectLengthOrg = lotteryDetailsPg.getAwardRuleRemoveObjectLength();
		lotteryDetailsPg.clickAddNewWardRule();
		int awardRuleDropDownListObjectLengthPre = lotteryDetailsPg.getAwardRuleDropDownListObjectLength();
		int awardRuleRemoveObjectLengthPre = lotteryDetailsPg.getAwardRuleRemoveObjectLength();
		
		result &= MiscFunctions.compareResult("Award Rule drop down list Object length after click Add", 
				awardRuleDropDownListObjectLengthOrg+1, awardRuleDropDownListObjectLengthPre);
		result &= MiscFunctions.compareResult("Remove Object length after click Add", 
				awardRuleRemoveObjectLengthOrg+1, awardRuleRemoveObjectLengthPre);
		
		//after click Remove button, could remove new award rule
		lotteryDetailsPg.clickRemoveAwardRule(0);
		awardRuleDropDownListObjectLengthPre = lotteryDetailsPg.getAwardRuleDropDownListObjectLength();
		awardRuleRemoveObjectLengthPre = lotteryDetailsPg.getAwardRuleRemoveObjectLength();
		result &= MiscFunctions.compareResult("Award Rule drop down list Object length after click Remove", 
				awardRuleDropDownListObjectLengthOrg, awardRuleDropDownListObjectLengthPre);
		result &= MiscFunctions.compareResult("Remove Object length after click Add", 
				awardRuleRemoveObjectLengthOrg, awardRuleRemoveObjectLengthPre);
		
		if(!result){
			throw new ErrorOnPageException("Award Rule Add/Remove function not correct, please check log.");
		}else{
			logger.info("Award Rule Add/Remove function is correct.");
		}
	}

}
