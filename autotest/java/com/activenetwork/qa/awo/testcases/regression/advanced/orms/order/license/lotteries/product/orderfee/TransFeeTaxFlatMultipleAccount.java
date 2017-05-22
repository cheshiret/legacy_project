/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.orderfee;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:This case is designed to test transaction fee tax information(name, amount,transaction type and date) and tax split information.
 * set up original pricing with tax information according to following rule:
 * tax1: split type=flat 100% to one account
 * tax2: split type=flat 20% to one account and 80% to another account
 * @LinkSetUp:
 * D_HF_ADD_LOTTERY_PRD:id=580
 * D_HF_ADD_LOTTERY_QUANTITY_CONT:id=450
 * D_HF_ADD_LOTTERY_LICENSE_YEAR:id=450
 * D_HF_ADD_HUNT:id=1180
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1270
 * D_HF_ADD_HUNT_QUOTA:id=360
 * D_HF_ASSI_HUNTS_TO_LOTTERY:id=720
 * D_HF_ASSIGN_LOTTERY_TO_STORE:id=450
 * D_ASSIGN_FEATURE:id=6502 
 * @SPEC:TC:116161
 * @Task#:Auto-2166
 * @author sborjigin
 * @Date  Jun 26, 2014
 */
public class TransFeeTaxFlatMultipleAccount extends LicenseManagerTestCase {
	private LicMgrOrderFeesDetailsPage lotteyAppFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private String walMartRole;
	private double transactionTaxAmount;
	private BigDecimal taxSplitedAmount1;
	private BigDecimal taxSplitedAmount2;
	private String transactionTaxSchdID;
	private String lotteryProductId;
	private TaxInfo tax;
	private String operationReason,operationNote;
	
	protected String transactionDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	@Override
	public void execute() {
		//login
		lm.loginLicenseManager(login);
		//deactivate pricing
		lm.gotoLotteriesProductListPgFromTopMenu();
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		//add pricing
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		//add tax
		lm.addTaxForAnExistingPricing(pricing);
		//get transaction fee tax schedule id
		transactionTaxSchdID = this.getTaxSchdID(lotteryProductId, FEETYPE_TRANFEE, null,pricing.transactionTaxes.get(0).getTaxName(),schema);
		//switch location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(walMartRole);
		//apply lottery
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lotteryPrd);
		String lotteryOrdNum2 = lm.processOrderCart(pay).split(" ")[0];
		//verify lottery basic tax info
		//go to privilege order fee detail page
		lm.gotoApplicationOrderDetailsPageFromTopMenu(lotteryOrdNum2);
		lm.gotoApplicationOrderFeesDetailPage();
		//verify transaction fee tax info at fee detail page
		lotteyAppFeesPage.verifyFeeInfo(pricing.transactionTaxes.get(0).getTaxName(), transactionTaxSchdID, transactionTaxAmount, TRANNAME_SUBMIT_LOTTERY_ENTRY,StringUtil.EMPTY,transactionDate);
		//verify tax split info for transaction tax fee
		lotteyAppFeesPage.verifyFeeSplitInfo(transactionTaxSchdID,getExpectedSplitInfoRegExp());

		//clear up
		lm.gotoHomePage();
		lm.gotoApplicationOrderDetailsPageFromTopMenu(lotteryOrdNum2);
		lm.reverseApplicationOrderToCart(operationReason, operationNote);
		lm.processOrderCart(pay);
		//logout
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//schema  and loggin info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		walMartRole = "HF HQ Role - Auto-WAL-MART";
		
		//reverse info
		operationReason="14 - Operation Error";
		operationNote="Regression Testing";
		//lottery info
		hunt.setHuntCode("LF3");
		lotteryPrd.setCode("LF3");
		lotteryPrd.setDescription("LotteryForTaxSplit3");
		lotteryPrd.setPurchasingName(lotteryPrd.getCode() + "-" + lotteryPrd.getDescription());
		lotteryPrd.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear())); //lm.getFiscalYear(schema));
		lotteryPrd.setHuntCodes(Arrays.asList(new String[]{hunt.getHuntCode()}));
		lotteryPrd.setApplicantType("Individual");
		lotteryProductId = lm.getProductID(lotteryPrd.getCode(), null, schema);

		//princing info(transaction fee to two account)
		pricing.prodType = "Lottery";
		pricing.prodCode = lotteryPrd.getCode();
		pricing.rateType = OrmsConstants.PER_APPLICATION_TYPE;
		
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = "All";
		pricing.effectFrom = DateFunctions.getDateAfterToday(-1);
		pricing.transFee = "5";
	
		//split info for transaction fee
		pricing.transFee_SplitBy = "Flat";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.add(new String[] {
				"710 - Boat Launch",
				"20.00" });
		pricing.transFee_accounts.add(new String[] {
				"Agent Fee - 100%",
				"80.00" });
		//initialize tax(transaction fee tax to two accounts)
		tax = pricing.new TaxInfo();
		tax.setIsFlat(true);
		tax.setTaxName("TaxForPrivFlat");
		tax.setTaxCalculationRate("20");
		tax.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax.setSplitNumberInfo("2");
		tax.setPurchaseType(REPLACEMENT_PURCHASE_TYPE_ID);
		transactionTaxAmount =((new BigDecimal(pricing.transFee).
				multiply(new BigDecimal(tax.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		taxSplitedAmount1=(new BigDecimal(transactionTaxAmount*0.2)).setScale(2,BigDecimal.ROUND_HALF_UP);
		taxSplitedAmount2=(new BigDecimal(transactionTaxAmount*0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);

		tax.addAccount(new String[]{"Voucher Account",""});
		pricing.transactionTaxes.add(tax);
		
		//customer info
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "PASS0624";
		cust.customerClass = "Individual";
		cust.lName = "TEST-MSIndiv02";
		cust.fName = "QA-MSIndiv02";
		cust.residencyStatus="Non Resident";

	}
	protected String getTaxSchdID(String privilegeID,String feeType,String purchaseType,String taxName, String schema){
		List<String> ids = lm.getHFTaxSchdIDByPrdIDFeeTypeAndPurchaseType(privilegeID, feeType, purchaseType, taxName,schema);
		if(ids.size() !=1){
			throw new ErrorOnDataException("Should have one active tax schedule for privilege id = " + privilegeID 
					              + "; and fee type is " + feeType + "; and purchase type is " + purchaseType);
		}
		return ids.get(0);		
	}
	protected String getExpectedSplitInfoRegExp(){
		logger.info("Get expected tax split info.");
		String expectedSplitInfoRegExp="Fund Split Type : Fee Split "+
										"1 : \\$"+taxSplitedAmount1.toString()+" to "+"Vendor Fee Payable Account" +
												".*"+
										"2 : \\$"+taxSplitedAmount2.toString()+" to "+"Voucher Account" +
												".*";
		return expectedSplitInfoRegExp;
	}

}
