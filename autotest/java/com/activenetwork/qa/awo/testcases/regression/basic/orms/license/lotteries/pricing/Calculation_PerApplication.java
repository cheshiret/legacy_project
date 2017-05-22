package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

import java.math.BigDecimal;
import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
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
 * @Description:This case is used to verify pricing fee of 'Per Application' type
 * @LinkSetUp:
 *			  d_hf_add_privilege_prd:id=2650 --PFL,Product For Lottery
 * 			  d_hf_add_pricing:id=3892 --Privilege
 * 			  d_hf_assi_pri_to_store:id=1870
 * 			  d_hf_add_prvi_license_year:id=2770
 * 			  d_hf_add_qty_control:id=1850
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			   d_hf_add_hunt:id=850,980 --HFO1,HF02  
 * 			  d_hf_assign_priv_to_hunt:id=550,680
 * 			  d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 * 			
 * 			  d_hf_add_lottery_prd:id=350(CODE='P01') 
 * 			  d_hf_add_lottery_license_year:id=260
 * 			  d_hf_add_lottery_quantity_control:id=260
 *            d_hf_add_lottery_schedule:id=170 
 *            d_hf_assign_lottery_to_store:id=320
 *            
 *            d_hf_assi_hunts_to_lottery:id=320,480
 * 			  d_hf_add_hunt_license_year:id=900,1000
 * @SPEC:[TC:055493] State Fee, Trans Fee, Vendor Fee - Per Application 
 *       [TC:048952] Holding Fee - Calculate based on selected Choices - No
 *       [TC:055497] Privilege Lottery Application Order Price - NEW Lottery Order  
 * @Task#: Auto-2056
 * @author Phoebe Chen
 * @Date  March 06, 2014
 */
public class Calculation_PerApplication extends LicenseManagerTestCase{
	private PricingInfo pricing=new PricingInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	private String salesLocation;
	private HuntInfo hunt1 = new HuntInfo(), hunt2 = new HuntInfo();  
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Add a pricing with rate type as 'Per Application'
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPg);
		lm.addPricingForProduct(pricing, pricingPg, true);
		lm.gotoHomePage();
		
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		this.gotoFeeDetailPage();
		verifyFeeAmount();
		lm.cancelFromFeeDetailsPage();
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	private void verifyFeeAmount() {
		boolean passed = true;
		BigDecimal totalAmount = new BigDecimal(pricing.vendorFee).add(new BigDecimal(pricing.transFee)).add(new BigDecimal(pricing.stateFee)).
				add(new BigDecimal(pricing.holdingFee));
		passed &= MiscFunctions.compareResult("State fee", pricing.stateFee, feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_STATE_FEE));
		passed &= MiscFunctions.compareResult("Transaction fee", pricing.transFee, feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_TRANSACIONT_FEE));
		passed &= MiscFunctions.compareResult("Vendor fee", pricing.vendorFee, feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_VENDOR_FEE));
		passed &= MiscFunctions.compareResult("holding fee", pricing.holdingFee, feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_HOLDING_FEE));
		passed &= MiscFunctions.compareResult("Total Price", totalAmount.toString(), feepg.getCustomerSummaryFeeAmount("TOTAL"));
		if(!passed){
			throw new ErrorOnPageException("Not all the error message is correct, please check the log above!");
		}
		logger.info("Price for privilege lottery with 'Per Application' rate type are all correct!");
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
		
		lottery.setCode("P01");
		lottery.setDescription("Lottery prodcut for pricing 01");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear())); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(new String[]{hunt1.getHuntCode(), hunt2.getHuntCode()}));
		lottery.setApplicantType("Individual");
		
		//Set up pricing information
		pricing.prodType = "Lottery";
		pricing.prodCode = lottery.getCode();
		pricing.status = ACTIVE_STATUS;
		pricing.locationClass = "All";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear());
		pricing.effectFrom = DateFunctions.getDateAfterToday(-1);
		pricing.effectTo = DateFunctions.getDateAfterToday(3);
		pricing.rateType = OrmsConstants.PER_APPLICATION_TYPE;
		pricing.vendorFee = "0.24";
		pricing.stateFee = "1.01";
		pricing.transFee = "1.14";
	
		pricing.holdingFee = "0.01";
		pricing.keepPreviousPriceDuringChangeIfHigher = false;
		pricing.calculateHoldingFeeBasedOnSelectedChoices = false;
	}
	
	private void gotoFeeDetailPage() {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox("New - 1", true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}

}
