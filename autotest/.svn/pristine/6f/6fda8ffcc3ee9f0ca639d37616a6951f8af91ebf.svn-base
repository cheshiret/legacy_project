package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify page control in lottery application page
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1230 --RCMP #|1R9Y4x4178
 * d_hf_add_privilege_prd:id=2600 --LAA,Lottery App Pri 01
 * d_hf_add_pricing:id=3712,3732 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1820
 * d_hf_add_prvi_license_year:id=2710
 * d_hf_add_qty_control:id=1800
 * d_hf_add_hunt:id=450 --LAH1
 * d_hf_add_hunt_license_year:id=440
 * d_hf_assign_priv_to_hunt:id=530
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=140 --WL1
 * d_hf_add_lottery_license_year:id=60
 * d_hf_add_lottery_quantity_control:id=60
 * d_hf_assi_hunts_to_lottery:id=30
 * d_hf_add_lottery_execution_config:id=50 --WebLottery01Config
 * d_hf_add_lottery_schedule:id=30 --WebLottery01Processing
 * d_assign_feature :id=5232
 * @SPEC:
 * @SPEC:Lottery Applications page - UI [TC:050346] 
 * @Task#:Auto-1724
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class LotteryAppPgControl extends HFSKLotteryApplicationTestCase {
	private String searchResultLabel1, searchResultLabel2, lotteryAppListAttrs;
	private int totalNum;

	@Override
	public void execute() {
		//Go to HFSK to lottery application page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();

		//Prepare lottery applications
		totalNum = lotteryAppPg.getTotalNum();
		if(totalNum<11){
			prepareLotteryApplaInLicenseManager();
			hf.invokeURL(url);
			hf.lookupAccount(cus);
			hf.gotoLotteryAppPgFromYourAccountFoundPg();
		}
		
		//Generate search result label
		searchResultLabel1 = lotteryAppPg.generateSearchResultLabel(1);
		searchResultLabel2 = lotteryAppPg.generateSearchResultLabel(2);
		
		//Initial
		verifySearchResult(searchResultLabel1, false, StringUtil.EMPTY);
		lotteryAppListAttrs = lotteryAppPg.getLotteryAppListAttrs();
		
		//Click next link
		lotteryAppPg.actionPageControl(true);
		verifySearchResult(searchResultLabel2, true, lotteryAppListAttrs);
		lotteryAppListAttrs = lotteryAppPg.getLotteryAppListAttrs();
		
		//click previous link
		lotteryAppPg.actionPageControl(false);
		verifySearchResult(searchResultLabel1, false, lotteryAppListAttrs);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4178";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = "1986-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//lottery info
		lottery.setCode("WL1");
		lottery.setDescription("WebLottery01");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList("LAH1"));
		lottery.setApplicantType("Individual");
	}
	
	private void prepareLotteryApplaInLicenseManager(){
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(salesLocation);
		for(int i=0; i<=10-totalNum; i++){
			lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cus, lottery);
			lm.processOrderCart(pay);
		}
		lm.logOutLicenseManager();
	}
	
	private void verifySearchResult(String resultLable, boolean clickNext, String expLotteryAppListAttrs){
		String actLotteryAppListAttrs = lotteryAppPg.getLotteryAppListAttrs();
		boolean result = MiscFunctions.compareResult("Result Lable", resultLable, lotteryAppPg.getResultLabel());
		result &= MiscFunctions.compareResult("Previous link", clickNext?true:false, lotteryAppPg.isPreviousLinkExists());
		result &= MiscFunctions.compareResult("Next link", clickNext?false:true, lotteryAppPg.isNextLinkExists());
		result &= !MiscFunctions.compareResult("Lottery application attrs", expLotteryAppListAttrs, actLotteryAppListAttrs);
		if(!result){
			throw new ErrorOnPageException("Failed to verify searching result in Lottery application list page.");
		}else logger.info("Successfully verify searching result in Lottery application list page.");
	}
}
