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
 * tax2: split type=flat 100% to one account
 * @LinkSetUp:
 * D_HF_ADD_LOTTERY_PRD:id=620
 * D_HF_ADD_LOTTERY_QUANTITY_CONT:id=490
 * D_HF_ADD_LOTTERY_LICENSE_YEAR:id=490
 * D_HF_ADD_HUNT:id=1180
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1270
 * D_HF_ADD_HUNT_QUOTA:id=360
 * D_HF_ASSI_HUNTS_TO_LOTTERY:id=760
 * D_HF_ASSIGN_LOTTERY_TO_STORE:id=490
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1310
 * D_ASSIGN_FEATURE:id=6502 
 * @SPEC:TC:116161
 * @Task#:Auto-2166
 * 
 * @author sborjigin
 * @Date  Jun 26, 2014
 */
public class VendorFeeTax extends LicenseManagerTestCase {
	private LicMgrOrderFeesDetailsPage lotteyAppFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private String walMartRole;
	private double vondorTaxAmount1,vondorTaxAmount2;
	private String vondorTaxSchdID1,vondorTaxSchdID2;
	private String lotteryProductId;
	private TaxInfo tax1,tax2;
	private String operationReason,operationNote;
	
	
	protected String transactionDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	@Override
	public void execute() {
		//login
		lm.loginLicenseManager(login);
		//deactivate pricing
		lm.gotoLotteriesProductListPgFromTopMenu();
		ILicMgrProductPricingPage pricingPage1 =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage1);
		//add pricing
		pricing.id = lm.addPricingForProduct(pricing, pricingPage1, true);
		//add tax
		lm.addTaxForAnExistingPricing(pricing);
		//get transaction fee tax schedule id
		vondorTaxSchdID1 = this.getTaxSchdID(lotteryProductId, FEETYPE_VENDORFEE, null,pricing.vendorTaxes.get(0).getTaxName(),schema);
		vondorTaxSchdID2 = this.getTaxSchdID(lotteryProductId, FEETYPE_VENDORFEE, null,pricing.vendorTaxes.get(1).getTaxName(),schema);

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
		lotteyAppFeesPage.verifyFeeInfo(pricing.vendorTaxes.get(0).getTaxName(), vondorTaxSchdID1, vondorTaxAmount1, TRANNAME_SUBMIT_LOTTERY_ENTRY,StringUtil.EMPTY,transactionDate);
		lotteyAppFeesPage.verifyFeeInfo(pricing.vendorTaxes.get(1).getTaxName(), vondorTaxSchdID2, vondorTaxAmount2, TRANNAME_SUBMIT_LOTTERY_ENTRY,StringUtil.EMPTY,transactionDate);

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
		lotteryPrd.setCode("LF7");
		lotteryPrd.setDescription("LotteryForTaxSplit7");
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
		pricing.vendorFee = "10";
		
		//initialize tax1(transaction fee tax to two accounts)
		tax1 = pricing.new TaxInfo();
		tax1.setIsFlat(false);
		tax1.setTaxName("TaxForPrivPercent");
		tax1.setRate("20");
		tax1.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax1.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		pricing.vendorTaxes.add(tax1);
		vondorTaxAmount1 =((new BigDecimal(pricing.vendorFee).
				multiply(new BigDecimal(tax1.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();

		//initialize tax info(transaction fee tax to one account)
		tax2 = pricing.new TaxInfo();
		tax2.setIsFlat(true);
		tax2.setTaxName("TaxForPrivFlat");
		tax2.setSplitNumberInfo("3");
		tax2.setTaxCalculationRate("20");
		tax2.addAccount(new String[]{"Vendor Fee Payable Account",""});
		vondorTaxAmount2 = ((new BigDecimal(pricing.vendorFee).
				multiply(new BigDecimal(tax2.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		pricing.vendorTaxes.add(tax2);

		
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

}
