package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

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
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to verify pricing holding fee when 'Calculate Holding Fee based on selected Choices' set to YES
 * @Precondition:PLA ----- Non-Resident  assigned as permit to hunt 'HFO-1'
 *               PLB ----- Resident   assigned as permit to hunt 'HFO-1'
 *               http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup
 *               INSERT INTO X_LOTTERY_ACCEPT_CONFIG(ID,ACTIVE_IND, APP_ID, ROLE_LOC_ID, ACTIVE_IND)
					VALUES(GET_SEQUENCE('X_LOTTERY_ACCEPT_CONFIG'),1,19,220893983,1)
 * @LinkSetUp:
 *			  d_hf_add_privilege_prd:id=2680,2690,2700,2710 --PLA,PLB,PLC,PLD
 * 			  d_hf_add_pricing:id=4082,4092,4102,4112 --Privilege
 * 			  d_hf_assi_pri_to_store:id=1900,1910,1920,1930
 * 			  d_hf_add_prvi_license_year:id=2800,2810,2820,2830
 * 			  d_hf_add_qty_control:id=1880,1890,1900,1910
 * 			  
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			  d_hf_add_hunt:id=1000,1010 --HFO-1,HFO-2 
 * 			  d_hf_assign_priv_to_hunt:id=700,710,720,730
 * 			  d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 * 			
 * 			  d_hf_add_lottery_prd:id=410(CODE='P07') 
 * 			  d_hf_add_lottery_license_year:id=320
 * 			  d_hf_add_lottery_quantity_control:id=320
 *            d_hf_add_lottery_schedule:id=230 
 *            d_hf_assign_lottery_to_store:id=380
 *            
 *            d_hf_assi_hunts_to_lottery:id=550
 * 			  d_hf_add_hunt_license_year:id=960,1060
 * @SPEC:[TC:055472] Holding Fee - Calculate based on selected Choices - Yes 
 * @Note:Need to run script:
 * insert into X_LOTTERY_ACCEPT_CONFIG values(GET_SEQUENCE('X_LOTTERY_ACCEPT_CONFIG'),'1','19','220893983', '1');commit;
 * If error:Pricing can not be resolved for the choice - to calculate Holding Fee
 * @Task#: Auto-2056
 * @author Phoebe Chen
 * @Date  March 13, 2014
 */
public class Calculation_HoldingFee_BasedOnChoice extends LicenseManagerTestCase{
	private PricingInfo pricing=new PricingInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	private String salesLocation;
	private HuntInfo hunt1 = new HuntInfo(), hunt2 = new HuntInfo();  
	private String pricing_permitA, /*pricing_permitB, pricing_permitC,*/ pricing_permitD;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Add a pricing with rate type as 'Per Application'
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPg);
		lm.addPricingForProduct(pricing, pricingPg, true);
		lm.gotoHomePage();
		lm.logOutLicenseManager();
		
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(salesLocation);
		//Make a lottery application for 'Non-Resident' customer, select hunt1 as choice
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode()));
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		this.gotoFeeDetailPage();
		//The Holding Fee equal Privilege A customer price ($10)
		verifyHoldingFeeAmount(pricing_permitA);
		lm.cancelFromFeeDetailsPage();
		lm.cancelCart();
		
		//Make a lottery application with customer aged '28', select hunt2 as choice
		lottery.setHuntCodes(Arrays.asList(hunt2.getHuntCode()));
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		this.gotoFeeDetailPage();
		//The Holding Fee equal Privilege D customer price ($35)
		verifyHoldingFeeAmount(pricing_permitD);
		lm.cancelFromFeeDetailsPage();
		lm.cancelCart();
		
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode(),hunt2.getHuntCode()));
		//Make a lottery application with customer aged '28' and 'Non-Resident', select both hunt1 and hunt2 as choice
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		this.gotoFeeDetailPage();
		//The Holding Fee equal Privilege D customer price ($35) which is higher
		verifyHoldingFeeAmount(pricing_permitD);
		lm.cancelFromFeeDetailsPage();
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	private void verifyHoldingFeeAmount(String expectAmount) {
		if(!feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_HOLDING_FEE).equalsIgnoreCase(expectAmount)){
			throw new ErrorOnPageException("The holding fee amount is not correct!", pricing_permitA, feepg.getCustomerFeeByFeeType(FEE_TYPE_NAME_HOLDING_FEE));
		}
		logger.info("Holding fee amount is correct!");
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
		
		hunt1.setHuntCode("HFO-1");
		hunt2.setHuntCode("HFO-2");
		
		lottery.setCode("P07");
		lottery.setDescription("Lottery prodcut for pricing 07");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(hunt1.getHuntCode()));
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
		pricing.rateType = OrmsConstants.PER_APPLICATION_TYPE;
		pricing.vendorFee = "0.24";
		pricing.stateFee = "1.01";
		pricing.transFee = "1.14";
	
		pricing.holdingFee = "0.01";
		pricing.keepPreviousPriceDuringChangeIfHigher = false;
		pricing.calculateHoldingFeeBasedOnSelectedChoices = true;
		
		pricing_permitA = "10.00";
		/*pricing_permitB = "20.00";
		pricing_permitC = "23.00"; */
		pricing_permitD = "35.00";
	}
	
	private void gotoFeeDetailPage() {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox("New - 1", true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}
}
