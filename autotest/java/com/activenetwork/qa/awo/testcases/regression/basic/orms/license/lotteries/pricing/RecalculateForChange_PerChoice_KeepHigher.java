package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

import java.math.BigDecimal;
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
 * @Description:This case is used to verify pricing fee of change item with fee type 'Per Choice' and  'Keep Previous Price during Change if Higher' set to YES.
 * @LinkSetUp:
 *			  d_hf_add_privilege_prd:id=2650 --PFL,Product For Lottery
 * 			  d_hf_add_pricing:id=3892 --Privilege
 * 			  d_hf_assi_pri_to_store:id=1870
 * 			  d_hf_add_prvi_license_year:id=2770
 * 			  d_hf_add_qty_control:id=1850
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			  d_hf_add_hunt:id=850,980,990 --HFO1,HF02,HF03  
 * 			  d_hf_assign_priv_to_hunt:id=550,680,690
 * 			  d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 * 			
 * 			  d_hf_add_lottery_prd:id=390(CODE='P05') 
 * 			  d_hf_add_lottery_license_year:id=290
 * 			  d_hf_add_lottery_quantity_control:id=290
 *            d_hf_add_lottery_schedule:id=200 
 *            d_hf_assign_lottery_to_store:id=350
 *            d_hf_assi_hunts_to_lottery:id=360,520,610
 * 			  d_hf_add_hunt_license_year:id=940,1040,1370
 * @SPEC:[TC:055580] Re-calculation of Order Price for Privilege Lottery Application Change--Step 5~7
 * @Task#: Auto-2056
 * @author Phoebe Chen
 * @Date  March 12, 2014
 */
public class RecalculateForChange_PerChoice_KeepHigher extends LicenseManagerTestCase{
	private PricingInfo pricing=new PricingInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	private String salesLocation, ordNum;
	private HuntInfo hunt1 = new HuntInfo(), hunt2 = new HuntInfo(), hunt3 = new HuntInfo(); 
	private String voidReason = "14 - Operation Error", voidNote = "Automation Test";
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Add a pricing with rate type as 'Per Choice'
		this.setUpPricingForLottery();
		lm.logOutLicenseManager(); //Add log out step to make sure the new added pricing will work,if the fee still not availble pleas comment this part and run the following again
		
		lm.loginLicenseManager(login);
		//Make a privilege lottery order with two choices, there is not additional choice
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		this.gotoFeeDetailPage();
		verifyFeeAmount(0);
		lm.cancelFromFeeDetailsPage();
		ordNum = lm.processOrderCart(pay);
		
		//Change order by add two more choices, make a "Change Lottery Application Details" work flow with four choices
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode(), hunt2.getHuntCode(), hunt3.getHuntCode()));
		this.changeApplicationOrderItemToCart();
		this.gotoFeeDetailPage(ordNum);
		//Additional chart equal to 2
		verifyFeeAmount(2);
		lm.cancelFromFeeDetailsPage();
		ordNum = lm.processOrderCart(pay);
		
		//Remove one choice, make a "Change Lottery Application Details" work flow with four choices
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode(), hunt2.getHuntCode()));
		this.changeApplicationOrderItemToCart();
		this.gotoFeeDetailPage(ordNum);
		//Although additional chart equal to 1 now, but it will use the higher price before change, so it is still count the price as two additional choices
		verifyFeeAmount(2);
		lm.cancelFromFeeDetailsPage();
		lm.cancelCart();
		
		lm.gotoApplicationOrderDetailsPageFromTopMenu(ordNum);
		lm.reverseApplicationOrderToCart(voidReason, voidNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	private void verifyFeeAmount(int additionNum) {
		boolean passed = true;
		BigDecimal statFee = new BigDecimal(pricing.choiceFees.get(0).stateFee).add(
				new BigDecimal(pricing.choiceFees.get(1).stateFee).multiply(new BigDecimal(additionNum)));
		BigDecimal transactionFee = new BigDecimal(pricing.choiceFees.get(0).transFee).add(
				new BigDecimal(pricing.choiceFees.get(1).transFee).multiply(new BigDecimal(additionNum)));
		BigDecimal vendorFee = new BigDecimal(pricing.choiceFees.get(0).vendorFee).add(
				new BigDecimal(pricing.choiceFees.get(1).vendorFee).multiply(new BigDecimal(additionNum)));
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
		hunt3.setHuntCode("HFO3");
		
		lottery.setCode("P05");
		lottery.setDescription("Lottery prodcut for pricing 05");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(lm.getFiscalYear(schema)); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode()));
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
		pricing.rateType = OrmsConstants.PER_CHOICE_TYPE;
		//This is for base choice
		ChoiceFee range_base = pricing.new ChoiceFee();
		range_base.range = "2";
		range_base.vendorFee = "1.00";
		range_base.stateFee = "1.00";
		range_base.transFee = "1.00";

		//This is for additional per choice
		ChoiceFee range_additional = pricing.new ChoiceFee();
		range_additional.vendorFee = "0.50";
		range_additional.stateFee = "0.50";
		range_additional.transFee = "0.50";
		pricing.choiceFees.add(range_base);
		pricing.choiceFees.add(range_additional);
	
		pricing.keepPreviousPriceDuringChangeIfHigher = true; //This can not be changed
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
	
	private void gotoFeeDetailPage() {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox("New - 1", true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}
	
	
	private void gotoFeeDetailPage(String ordNum) {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox(ordNum, true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}

}
