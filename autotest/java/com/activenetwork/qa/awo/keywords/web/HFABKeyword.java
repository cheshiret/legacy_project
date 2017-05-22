package com.activenetwork.qa.awo.keywords.web;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryAddGroupMembersPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;

public class HFABKeyword extends HFKeyword{
	private static HFABKeyword _instance = null;

	public static HFABKeyword getInstance() {
		if (null == _instance)
			_instance = new HFABKeyword();

		return _instance;
	}

	protected HFABKeyword() {
	}

	public void goToAddMemberPgFromEduInfoPg(){
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
		eduInfoPg.clickYesAttest();
		eduInfoPg.clickSubmit();
		addGroupMembersPg.waitLoading();
	}

	public void chooseLotteryHuntsToEduInfoPg(HuntInfo...hunts){
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
		huntsPg.setupHuntChoicesWithContinueBtn(hunts);
		huntsPg.clickContinueBtn();
		eduInfoPg.waitLoading();
	}

	public void chooseLotteryHuntsToCart(boolean isIndividual, List<Customer> custs, HuntInfo...hunts){
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
		HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();

		huntsPg.setupHuntChoicesWithContinueBtn(hunts);
		huntsPg.clickContinueBtn();
		if(isIndividual){
			cartPg.waitLoading();
		}else {
			addGroupMembersPg.waitLoading();
			if(null!=custs && custs.size()>0){
				for(int i=0; i<custs.size(); i++){
					addGroupMembersPg.clickAddMoreMembers();
					addGroupMembersPg.waitForRemove(i+1);
					addGroupMembersPg.setWinNum(custs.get(i).custNum, i+1);
					addGroupMembersPg.setDateOfBirth(custs.get(i).dateOfBirth, i+1);
				}
			}
			addGroupMembersPg.clickContinueBtn();
			addGroupMembersPg.waitForSuccessMsg();
			addGroupMembersPg.clickContinueBtn();
			cartPg.waitLoading();
		}
	}

	/**
	 * Choose lottery hunts for individual
	 * @param hunts
	 */
	public void chooseLotteryHuntsToCart(HuntInfo...hunts){
		chooseLotteryHuntsToCart(true, null, hunts);
	}

	/**
	 * Choose lottery hunts for group leader
	 * @param custs
	 * @param hunts
	 */
	public void chooseLotteryHuntsToCart(List<Customer> custs, HuntInfo...hunts){
		chooseLotteryHuntsToCart(false, custs, hunts);
	}

	public void gotoUpdateHuntPgFromLotteryAppPage(){
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
		lotteryAppPg.clickUpdateChoice();
		huntsPg.waitLoading();
	}

	public void chooseLotteryHuntsToEduRequired(boolean isIndividual, List<Customer> custs, HuntInfo...hunts){
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
		HFEducationInfomationRequiredPage eduRequiredPg = HFEducationInfomationRequiredPage.getInstance();
		chooseLotteryHuntsToAddGroupMembersPg(isIndividual, custs, hunts);
		addGroupMembersPg.clickContinueBtn();
		eduRequiredPg.waitLoading();
	}

	public void chooseLotteryHuntsToEduRequired(HuntInfo...hunts){
		chooseLotteryHuntsToEduRequired(true, null, hunts);
	}

	public void gotoShoppingCartPgFromEduRequiredPg(){
		HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
		HFEducationInfomationRequiredPage eduRequiredPg = HFEducationInfomationRequiredPage.getInstance();
		eduRequiredPg.clickSubmit();
		cartPg.waitLoading();
	}

	public void gotoUpdateHuntsPgFromUpdateMembersPage(){
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
		addGroupMembersPg.goBackToPreviousPg();
		huntsPg.waitLoading();
	}
}
