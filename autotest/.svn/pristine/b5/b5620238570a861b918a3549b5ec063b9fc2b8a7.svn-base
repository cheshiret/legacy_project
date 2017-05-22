package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.ChoiceFee;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryPricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This case is used to verify pricing fee of change item with fee type 'Choice Range' and  'Keep Previous Price during Change if Higher' set to NO.
 * @LinkSetUp: HF HQ Role - Auto	LicenseManager	ViewPrivilegeLotteryOrder
 *			  d_hf_add_privilege_prd:id=2650 --PFL,Product For Lottery
 * 			  d_hf_add_pricing:id=3892 --Privilege
 * 			  d_hf_assi_pri_to_store:id=1870
 * 			  d_hf_add_prvi_license_year:id=2770
 * 			  d_hf_add_qty_control:id=1850
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			  d_hf_add_hunt:id=850,980 --HFO1,HF02
 * 			  d_hf_assign_priv_to_hunt:id=550,680,690
 * 			  d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 * 			
 * 			  d_hf_add_lottery_prd:id=380(CODE='P04') 
 * 			  d_hf_add_lottery_license_year:id=280
 * 			  d_hf_add_lottery_quantity_control:id=280
 *            d_hf_add_lottery_schedule:id=190 
 *            d_hf_assign_lottery_to_store:id=340
 *            d_hf_assi_hunts_to_lottery:id=350,510
 * 			  d_hf_add_hunt_license_year:id=930,1030
 * @SPEC:[TC:055580] Re-calculation of Order Price for Privilege Lottery Application Change--Step 1~4
 * @Task#: Auto-2056
 * @author Phoebe Chen
 * @Date  March 12, 2014
 */
public class RecalculateForChange_ChoiceRange_NotKeep extends LicenseManagerTestCase{
	private PricingInfo pricing=new PricingInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	private String salesLocation, ordNum;
	private HuntInfo hunt1 = new HuntInfo(), hunt2 = new HuntInfo();
	private String voidReason = "14 - Operation Error", voidNote = "Automation Test";
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Add a pricing with rate type as 'Choice Range'
		this.setUpPricingForLottery();
		lm.logOutLicenseManager(); //Add log out step to make sure the new added pricing will work
		
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(salesLocation);
		//Make a "submit lottery entry" work flow with two choices and one point(Total:3)
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		this.gotoFeeDetailPage("New - 1");
		//It is equal to 3, so should in the third range
		verifyFeeAmount(2);
		lm.cancelFromFeeDetailsPage();
		ordNum = lm.processOrderCart(pay);
		
		//Change order by remove one choice, make a "Change Lottery Application Details" work flow with two choices
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode()));
		this.changeApplicationOrderItemToCart();
		this.gotoFeeDetailPage(ordNum);
		//It is equal to 2, so should in the second range
		verifyFeeAmount(1);
		lm.cancelFromFeeDetailsPage();
		ordNum = lm.processOrderCart(pay);
		
		//Change order by remove the other one choice,Point count as 1 choice, thus price from range >=1 is applied
		lottery.setHuntCodes(new ArrayList<String>());
		this.changeApplicationOrderItemToCart();
		this.gotoFeeDetailPage(ordNum);
		//It is equal to 1, so should in the first range
		verifyFeeAmount(0);
		lm.cancelFromFeeDetailsPage();
		lm.cancelCart();
		
		lm.gotoApplicationOrderDetailsPageFromTopMenu(ordNum);
		lm.reverseApplicationOrderToCart(voidReason, voidNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	private void verifyFeeAmount(int rangeIndex) {
		boolean passed = true;
		BigDecimal statFee = new BigDecimal(pricing.choiceFees.get(rangeIndex).stateFee);
		BigDecimal transactionFee = new BigDecimal(pricing.choiceFees.get(rangeIndex).transFee);
		BigDecimal vendorFee = new BigDecimal(pricing.choiceFees.get(rangeIndex).vendorFee);
		BigDecimal totalAmount = vendorFee.add(transactionFee).add(statFee);
		passed &= MiscFunctions.compareResult("State fee", statFee.toString(), feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_STATE_FEE));
		passed &= MiscFunctions.compareResult("Transaction fee", transactionFee.toString(), feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_TRANSACIONT_FEE));
		passed &= MiscFunctions.compareResult("Vendor fee", vendorFee.toString(), feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_VENDOR_FEE));
		passed &= MiscFunctions.compareResult("Total Price", totalAmount.toString(), feepg.getCustomerSummaryFeeAmount("TOTAL"));
		if(!passed){
			throw new ErrorOnPageException("Not all the error message is correct, please check the log above!");
		}
		logger.info("Price for privilege lottery with 'Per Chice' rate type are all correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.residencyStatus="Non Resident";
		
		hunt1.setHuntCode("HFO1");
		hunt2.setHuntCode("HFO2");

		lottery.setCode("P04");
		lottery.setDescription("Lottery prodcut for pricing 04");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(lm.getFiscalYear(schema)); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(new String[]{hunt1.getHuntCode(), hunt2.getHuntCode()}));
		lottery.setPointTypes(Arrays.asList("PFR"));
		lottery.setApplicantType("Individual");
		
		//Set up pricing information
		pricing.prodType = "Lottery";
		pricing.prodCode = lottery.getCode();
		pricing.status = ACTIVE_STATUS;
		pricing.locationClass = "All";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = lm.getFiscalYear(schema);
		pricing.effectFrom = DateFunctions.getDateAfterToday(-1);
		pricing.effectTo = DateFunctions.getDateAfterToday(3);
		pricing.rateType = OrmsConstants.CHOICE_RANGE_TYPE;
		ChoiceFee range1 = pricing.new ChoiceFee();
		range1.range = "1";
		range1.vendorFee = "0.81";
		range1.stateFee = "0.82";
		range1.transFee = "0.83";

		ChoiceFee range2 = pricing.new ChoiceFee();
		range2.range = "2";
		range2.vendorFee = "2.01";
		range2.stateFee = "2.02";
		range2.transFee = "2.03";
		
		ChoiceFee range3 = pricing.new ChoiceFee();
		range3.range = "3";
		range3.vendorFee = "3.01";
		range3.stateFee = "3.02";
		range3.transFee = "3.04";
		pricing.choiceFees.add(range1);
		pricing.choiceFees.add(range2);
		pricing.choiceFees.add(range3);
	
		pricing.keepPreviousPriceDuringChangeIfHigher = false;
		pricing.calculateHoldingFeeBasedOnSelectedChoices = false;
	}
	
	private void changeApplicationOrderItemToCart() {
		lm.gotoApplicationOrderDetailsPageFromTopMenu(ordNum);
		lm.changeAppplicationOrderItemToCart(lottery);
	}
	
	private void setUpPricingForLottery(){
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPg);
		lm.addPricingForProduct(pricing, pricingPg, true);
		lm.gotoHomePage();
	}
	
	private void gotoFeeDetailPage(String ordNum) {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox(ordNum, true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}

}