package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

import java.util.Arrays;
import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryPricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to verify pricing holding fee when 'Calculate Holding Fee based on selected Choices' set to YES
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
 * 			  d_hf_add_lottery_prd:id=420(CODE='P08') 
 * 			  d_hf_add_lottery_license_year:id=320
 * 			  d_hf_add_lottery_quantity_control:id=320
 *            d_hf_add_lottery_schedule:id=230 
 *            d_hf_assign_lottery_to_store:id=380
 *            d_hf_assi_hunts_to_lottery:id=390,550
 * 			  d_hf_add_hunt_license_year:id=970,1070
 * @SPEC:[TC:057297] Calculate Minimum to Confirm 
 * @Task#: Auto-2056
 * @author Phoebe Chen
 * @Date  March 13, 2014
 */
public class MinimumPayment extends LicenseManagerTestCase{
	private InvMgrFacilityDetailPage facilityDetailPg = InvMgrFacilityDetailPage.getInstance();
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIM = new LoginInfo();
	private String facility;
	private PricingInfo pricing=new PricingInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	private String salesLocation;
	private HuntInfo hunt1 = new HuntInfo(), hunt2 = new HuntInfo();  
	private String halfAmount;
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String expMsg;
	@Override
	public void execute() {
		setEnforceMinimumToConfirmRuleInIM(true);
		
		lm.loginLicenseManager(login);
		//Add a pricing with rate type as 'Choice Range'
		this.setUpPricingForLottery();
		
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		//Pay zero amount, it will be failed
		pay.amount = "0.00";
		this.setUpPaymentAndClickOk();
		verifyErrorMssage();
		//Pay half amount, it will be failed too
		pay.amount = halfAmount;
		this.setUpPaymentAndClickOk();
		verifyErrorMssage();
		lm.cancelCart();
		
		lm.logOutLicenseManager();
		
		setEnforceMinimumToConfirmRuleInIM(false);
		
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		String ordNum = lm.processOrderCart(pay);
		if(!ordNum.matches("17-\\d+")){
			throw new ErrorOnPageException("The privilege lottery order is not make correctly");
		}
		logger.info("It is correct to make half payment!");
		
		lm.logOutLicenseManager();
	}
	
	private void verifyErrorMssage() {
		String actMsg = ordCartPg.getErrorMessage();
		if(!actMsg.matches(expMsg)){
			throw new ErrorOnPageException("The error message is not correct", expMsg, actMsg);
		}
		logger.info("Error message is correct!");
	}

	private void setUpPaymentAndClickOk(){
		ordCartPg.setupPayment(pay);
		ordCartPg.clickProcessOrder();
		ajax.waitLoading();
		ordCartPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		
		facility = "WAL-MART";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.residencyStatus="Non Resident";
		
		hunt1.setHuntCode("HFO1");
		hunt2.setHuntCode("HFO2");
		
		lottery.setCode("P08");
		lottery.setDescription("Lottery prodcut for pricing 08");
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
		
		halfAmount = String.valueOf( (Double.parseDouble(pricing.vendorFee) + Double.parseDouble(pricing.stateFee) + Double.parseDouble(pricing.transFee)
				+ Double.parseDouble(pricing.holdingFee)) /2);
		
		expMsg = "The Total Payment entered is less than the Minimum Payment Due. Please enter additional Payment.";
		
		//Inventory manager login info
		loginIM.url = AwoUtil.getOrmsURL(env);
		loginIM.userName = TestProperty.getProperty("orms.adm.user");
		loginIM.password = TestProperty.getProperty("orms.adm.pw");
		loginIM.contract = "MS Contract";
		loginIM.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	}
	
	private void setEnforceMinimumToConfirmRuleInIM(boolean isEnforced) {
		im.loginInventoryManager(loginIM);
	    im.gotoFacilityDetailsPg(facility);
	    facilityDetailPg.selectEnforceMinimumtoConfirmRuleInField(isEnforced);
		im.logoutInvManager();
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

}
