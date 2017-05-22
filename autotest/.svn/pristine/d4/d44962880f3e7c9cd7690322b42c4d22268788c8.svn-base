package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify lottery attribute in add hunts page
 * @Preconditions:
 * Make sure there is one lottery "Lottery With Mult Hunts" without parameter setup;
 * Make sure there is one lottery "Lottery with parameter" with parameter setup.
 * How to setup lottery parameter: License Manager -> Lotteries product set up page -> Parameters tab
 * @SPEC: 
 * 	List Available Hunts - Lottery Product Attributes [TC:056287]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=490,520
 * d_hf_add_hunt_license_year:id=520,640
 * d_hf_assign_priv_to_hunt:id=370,400
 * d_hf_add_hunt_quota:id=210
 * d_hf_add_lottery_prd:id=200,220
 * d_hf_add_lottery_license_year:id=80,130
 * d_hf_assign_lottery_to_store:id=80,130
 * d_hf_add_lottery_quantity_control:id=80,130
 * d_hf_assi_hunts_to_lottery:id=90,190
 * d_hf_add_lottery_parameter:id=10
 * @Task#: Auto-1773
 * 
 * @author Lesley Wang
 * @Date  Feb 13, 2014
 */
public class HuntsList_LotteryAttr extends HFSKWebTestCase {
	private HFLotteryProduct lotteryWithParam;
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		
		// Lottery without parameter
		hf.gotoLotteryCategoriesListPg(cus);
		this.applyLotteryAndVerifyLotteryAttributes(lottery);
		
		// Lottery with parameter
		hf.gotoLotteryCategoriesListPg(cus);
		this.applyLotteryAndVerifyLotteryAttributes(lotteryWithParam);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		lottery.setDescription("Lottery With Mult Hunts");
		lottery.setLicenseYear(Integer.toString(DateFunctions.getCurrentYear()));
		lottery.setMaxChoices("5");
		lottery.setAlgorithm("Draw Lottery");
		
		lotteryWithParam = new HFLotteryProduct();
		lotteryWithParam.setDescription("Lottery with parameter");
		lotteryWithParam.setLicenseYear(lottery.getLicenseYear());
		lotteryWithParam.setMaxChoices("5");
		lotteryWithParam.setAlgorithm("Draw Lottery");
		lotteryWithParam.setParameters("lottery param des", "lottery param value", false);
	}

	private void applyLotteryAndVerifyLotteryAttributes(HFLotteryProduct lottery) {
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		
		// Apply as Individual without points
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyLotteryAttributes(lottery);
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
			
		// Apply as Group Leader
		hf.applyLotteryAsGroupLeaderToAddHuntsPg();
		this.verifyLotteryAttributes(lottery);
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		
		// Submit with points as individual
		hf.submitLotteryWithPointsAsIndividualToAddHuntsPg();
		this.verifyLotteryAttributes(lottery);
	}
	
	private void verifyLotteryAttributes(HFLotteryProduct lottery) {
		boolean result = true;
		String actualInfo = huntsPg.getLotteryAttributes();
		result &= MiscFunctions.startWithString("Lottery description", actualInfo, lottery.getDescription());
		for (int i = 0; i < lottery.getParameters().size(); i++) {
			Data<LotteryParameterInfo> param = lottery.getParameters().get(i);
			String expParam = param.stringValue(LotteryParameterInfo.Description) + ": " + param.stringValue(LotteryParameterInfo.Value);
			result &= MiscFunctions.containString("Lottery parameter", actualInfo, expParam);
		}
		
		// The attributes' labels are configurable in /opt/sites/qa3/uwppl/docs/properties/config/hf/HFLotteriesResouce.properties, key like 'hflottery.details.attributes' */	
		result &= MiscFunctions.containString("Lottery license year", actualInfo, "Licence Year: " + lottery.getLicenseYear());
		result &= MiscFunctions.containString("Lottery maximum choices", actualInfo, "Maximum Choices: " + lottery.getMaxChoices());
		
		if (!result) {
			throw new ErrorOnPageException("Lottery attributes are wrong on add hunts page for " + lottery.getDescription());
		}
		logger.info("Succesffully verify lottery attributes on add hunts page for " + lottery.getDescription());
	}
}
