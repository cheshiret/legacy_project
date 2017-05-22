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
 * tax1: split type=percent 100% to one account
 * tax2: split type=percent 20% to one account ,30% to one account and 50% to another account
 * @LinkSetUp:
 * D_HF_ADD_LOTTERY_PRD:id=610
 * D_HF_ADD_LOTTERY_QUANTITY_CONT:id=480
 * D_HF_ADD_LOTTERY_LICENSE_YEAR:id=480
 * D_HF_ADD_HUNT:id=1180
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1300
 * D_HF_ADD_HUNT_QUOTA:id=360
 * D_HF_ASSI_HUNTS_TO_LOTTERY:id=750
 * D_HF_ASSIGN_LOTTERY_TO_STORE:id=480
 * D_ASSIGN_FEATURE:id=6502 
 * @SPEC:TC:116161
 * @Task#:Auto-2166
 * 
 * @author sborjigin
 * @Date  Jun 26, 2014
 */
public class TransFeeTaxPercent extends LicenseManagerTestCase {
	private LicMgrOrderFeesDetailsPage lotteyAppFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private String walMartRole;
	private double transactionTaxAmount1,transactionTaxAmount2;
	private BigDecimal taxSplitedAmount1;
	private BigDecimal taxSplitedAmount2;
	private BigDecimal taxSplitedAmount3;
	private String transactionTaxSchdID1,transactionTaxSchdID2;
	private String lotteryProductId;
	private TaxInfo tax1,tax2;
	private String operationReason,operationNote;
	private ILicMgrProductPricingPage pricingPage;
	
	protected String transactionDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	@Override
	public void execute() {
		//login
		lm.loginLicenseManager(login);
		//deactivate pricing
		lm.gotoLotteriesProductListPgFromTopMenu();
		pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		//add pricing
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		//add tax
		lm.addTaxForAnExistingPricing(pricing);
		//get transaction fee tax schedule id
		transactionTaxSchdID1 = this.getTaxSchdID(lotteryProductId, FEETYPE_TRANFEE, null,pricing.transactionTaxes.get(0).getTaxName(),schema);
		transactionTaxSchdID2 = this.getTaxSchdID(lotteryProductId, FEETYPE_TRANFEE, null,pricing.transactionTaxes.get(1).getTaxName(),schema);

		//switch location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(walMartRole);
		//apply lottery
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lotteryPrd);
		String lotteryOrdNum1 = lm.processOrderCart(pay).split(" ")[0];
		//verify lottery basic tax info
		//go to privilege order fee detail page
		lm.gotoApplicationOrderDetailsPageFromTopMenu(lotteryOrdNum1);
		lm.gotoApplicationOrderFeesDetailPage();
		//verify transaction fee tax info at fee detail page
		lotteyAppFeesPage.verifyFeeInfo(pricing.transactionTaxes.get(0).getTaxName(), transactionTaxSchdID1, transactionTaxAmount1, TRANNAME_SUBMIT_LOTTERY_ENTRY,StringUtil.EMPTY,transactionDate);
		lotteyAppFeesPage.verifyFeeInfo(pricing.transactionTaxes.get(1).getTaxName(), transactionTaxSchdID2, transactionTaxAmount2, TRANNAME_SUBMIT_LOTTERY_ENTRY,StringUtil.EMPTY,transactionDate);
		//verify tax split info
		lotteyAppFeesPage.verifyFeeSplitInfo(transactionTaxSchdID2,getExpectedSplitInfoRegExp());
		lotteyAppFeesPage.verifyFeeSplitInfo(transactionTaxSchdID1,"Get expected tax split info");

		//clear up
		lm.gotoHomePage();
		lm.gotoApplicationOrderDetailsPageFromTopMenu(lotteryOrdNum1);
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
		lotteryPrd.setCode("LF6");
		lotteryPrd.setDescription("LotteryForTaxSplit6");
		lotteryPrd.setPurchasingName(lotteryPrd.getCode() + "-" + lotteryPrd.getDescription());
		lotteryPrd.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear())); //lm.getFiscalYear(schema));
		lotteryPrd.setHuntCodes(Arrays.asList(new String[]{hunt.getHuntCode()}));
		lotteryPrd.setApplicantType("Individual");
		lotteryProductId = lm.getProductID(lotteryPrd.getCode(), null, schema);

		//pricing1(transaction fee to one account) info
		pricing.prodType = "Lottery";
		pricing.prodCode = lotteryPrd.getCode();
		pricing.rateType = OrmsConstants.PER_APPLICATION_TYPE;
		
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = "All";
		pricing.effectFrom = DateFunctions.getDateAfterToday(-1);
		pricing.transFee = "5";

		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.add(new String[] {
				"710 - Boat Launch",
				"100.00" });
		
		//princing2 info(transaction fee to two account)
		//initialize tax1(transaction fee tax to one accounts)
		tax1 = pricing.new TaxInfo();
		tax1.setIsFlat(false);
		tax1.setTaxName("TaxForPrivPercent");
		tax1.setRate("30");
		tax1.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax1.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		pricing.transactionTaxes.add(tax1);
		transactionTaxAmount1 =((new BigDecimal(pricing.transFee).
				multiply(new BigDecimal(tax1.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();

		//initialize tax info(transaction fee tax to three account)
		tax2 = pricing.new TaxInfo();
		tax2.setIsFlat(false);
		tax2.setTaxName("TaxForPriviledge_Percent");
		tax2.setSplitNumberInfo("3");
		tax2.setRate("20");
		tax2.addAccount(new String[]{"Vendor Fee Payable Account","20"});
		tax2.addAccount(new String[]{"Voucher Account","30"});
		tax2.addAccount(new String[]{"Sales Tax","50"});
		transactionTaxAmount2 = ((new BigDecimal(pricing.transFee).
				multiply(new BigDecimal(tax2.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		taxSplitedAmount1=((new BigDecimal(transactionTaxAmount2).
				multiply(new BigDecimal(tax2.getAccount(0)[1]).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP));
		taxSplitedAmount2=((new BigDecimal(transactionTaxAmount2).
				multiply(new BigDecimal(tax2.getAccount(1)[1]).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP));
		taxSplitedAmount3=((new BigDecimal(transactionTaxAmount2).
				multiply(new BigDecimal(tax2.getAccount(2)[1]).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP));
		pricing.transactionTaxes.add(tax2);

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
	protected String getExpectedSplitInfoRegExp() {
		logger.info("Get expected tax split info");
		String splitPercent1=pricing.transactionTaxes.get(1).getAccount(0)[1];
		String splitPercent2=pricing.transactionTaxes.get(1).getAccount(1)[1];;
		BigDecimal hundred=new BigDecimal("100");
		BigDecimal bgdSplitPercent1=new BigDecimal(splitPercent1);
		BigDecimal bgdSplitPercent2=new BigDecimal(splitPercent2);
		String splitPercent3=(hundred.subtract(bgdSplitPercent1).subtract(bgdSplitPercent2)).setScale(1).toString();
		String expectedSplitInfoRegExp="Fund Split Type : Percent "+
				"1 : \\$"+taxSplitedAmount1.toString()+" \\("+splitPercent1+"%\\) to "+"Vendor Fee Payable Account" +
						".*"+
				"2 : \\$"+taxSplitedAmount2.toString()+" \\("+splitPercent2+"%\\) to "+"Voucher Account" +
						".*"+
				"3 : \\$"+taxSplitedAmount3.toString()+" \\("+splitPercent3+"%\\) to "+"Sales Tax" +
						".*";
		return expectedSplitInfoRegExp;
										
	}


}
