/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * Step1:set up original pricing with tax information according to following rule:
 * 	 Vendor fee tax: split type=percent 100% to one account
 *   State fee tax: split type=flat 100% to one account
 * 	 Transaction fee tax: split type=percent 20% to one account, 30% to one account and 50% to another account
 * Step2:purchase the above privilege whose tax is set
 * Step3:Verify following information for privilege order fee detail:
 *   1.Tax basic information including fee type, schedule ID, amount, transaction, qty  and transaction date 
 *   2.Tax split information including fee split amount/percentage and account
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=3290
 * d_hf_add_privilege_product:id=3390
 * d_hf_add_prvi_license_year:id=3560
 * d_hf_add_qty_control:id=2590
 * d_hf_assi_pri_to_store:id=2610 
 * @SPEC:TC:117598
 * @Task#:auto-2166 
 * 
 * @author sborjigin
 * @Date  Jun 11, 2014
 */
public class PrivilegeOrderTaxFeeForOriginal  extends LicenseManagerTestCase {
	
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private String vendorTaxSchdID;
	private String stateTaxSchdID;
	private String transactionTaxSchdID;
	private String walMartRole;
	private double vendorTaxAmount,stateTaxAmount,transactionTaxAmount;
	private BigDecimal taxSplitedAmount1;
	private BigDecimal taxSplitedAmount2;
	private BigDecimal taxSplitedAmount3;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing and tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		//add pricing
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		//add tax
		lm.addTaxForAnExistingPricing(pricing);
		//get tax schedule id from DB
		//get vendor fee tax schedule id
		vendorTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.vendorTaxes.get(0).getTaxName(),schema);
		//get state fee tax schedule id
		stateTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.stateTaxes.get(0).getTaxName(),schema);
		//get transaction fee tax schedule id
		transactionTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.transactionTaxes.get(0).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(walMartRole);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String privOrdNum = lm.processOrderCart(pay).split(" ")[0];
		//go to privilege order fee detail page
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(privOrdNum);
		lm.gotoPrivilegeOrderFeesDetailPage();
		//verify basic tax info
		//verify vendor fee tax info at fee detail page
		String transcationDateToday=getTransactionDateToday();
		priFeesPage.verifyFeeInfo(pricing.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID, vendorTaxAmount, TRANNAME_PURCHASE_PRIVILEGE,StringUtil.EMPTY,transcationDateToday);
		//verify state fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing.stateTaxes.get(0).getTaxName(), stateTaxSchdID, stateTaxAmount, TRANNAME_PURCHASE_PRIVILEGE,StringUtil.EMPTY,transcationDateToday);
		//verify transaction fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing.transactionTaxes.get(0).getTaxName(), transactionTaxSchdID, transactionTaxAmount, TRANNAME_PURCHASE_PRIVILEGE,StringUtil.EMPTY,transcationDateToday);
		
		//verify tax split info for state tax fee
		priFeesPage.verifyFeeSplitInfo(stateTaxSchdID,"Tax Fund Split Type: Fee Split");
		//verify tax split info for transaction tax fee
		priFeesPage.verifyFeeSplitInfo(transactionTaxSchdID,getExpectedSplitInfoRegExp());

		//clear up
		lm.gotoPrivilegeOrderDetailPageFromFeeDetailPage();
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		//logout
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		walMartRole = "HF HQ Role - Auto-WAL-MART";
		//initialize privilege product
		privilege.name = "OrderTaxPriv01";
		privilege.code = "TX1";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.privilegeId = lm.getPrdIDByPrdName(privilege.name, null, schema);
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Regression Testing";
		
		pricing.prodCode=privilege.code;
		pricing.prodType="Privilege";
		
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = "All";
		pricing.purchaseType = "Original";
		pricing.effectFrom = DateFunctions.getDateAfterToday(-1);
		pricing.vendorFee = "10";
		pricing.stateTransFee = "10";
		pricing.stateFee = "5";
		pricing.transFee = "5";
		pricing.stateFee_SplitBy = "Flat";
		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.add(new String[] {
				"710 - Boat Launch",
				"100.00" });
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.add(new String[] {
				"710 - Boat Launch",
				"100.00" });
		//initialize tax info
		TaxInfo tax_vendor = pricing.new TaxInfo();
		tax_vendor.setIsFlat(false);
		tax_vendor.setTaxName("TaxForPrivPercent");
		tax_vendor.setRate("20");
		vendorTaxAmount = ((new BigDecimal(pricing.vendorFee).
				multiply(new BigDecimal(tax_vendor.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();		
		tax_vendor.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_vendor.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		pricing.vendorTaxes.add(tax_vendor);
		
		TaxInfo tax_state = pricing.new TaxInfo();
		tax_state.setIsFlat(true);
		tax_state.setTaxName("TaxForPrivFlat");
		tax_state.setTaxCalculationRate("30");
		tax_state.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_state.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		pricing.stateTaxes.add(tax_state);
		stateTaxAmount = ((new BigDecimal(pricing.stateFee).
				multiply(new BigDecimal(tax_state.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		TaxInfo tax_transaction = pricing.new TaxInfo();
		tax_transaction.setIsFlat(false);
		tax_transaction.setTaxName("TaxForPrivPercent");
		tax_transaction.setRate("20");
		tax_transaction.addAccount(new String[]{"Vendor Fee Payable Account","20"});
		tax_transaction.addAccount(new String[]{"Voucher Account","30"});
		tax_transaction.addAccount(new String[]{"Sales Tax","50"});
		tax_transaction.setSplitNumberInfo("3");
		transactionTaxAmount = ((new BigDecimal(pricing.stateFee).
				multiply(new BigDecimal(tax_transaction.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		taxSplitedAmount1=((new BigDecimal(transactionTaxAmount).
				multiply(new BigDecimal(tax_transaction.getAccount(0)[1]).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP));
		taxSplitedAmount2=((new BigDecimal(transactionTaxAmount).
				multiply(new BigDecimal(tax_transaction.getAccount(1)[1]).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP));
		taxSplitedAmount3=((new BigDecimal(transactionTaxAmount).
				multiply(new BigDecimal(tax_transaction.getAccount(2)[1]).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP));

		tax_transaction.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		pricing.transactionTaxes.add(tax_transaction);

		
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "PASS0624";
		cust.customerClass = "Individual";
		cust.lName = "TEST-MSIndiv02";
		cust.fName = "QA-MSIndiv02";
		cust.residencyStatus="Non Resident";
		
		
	}
	private String getTaxSchdID(String privilegeID,String feeType,String purchaseType,String taxName, String schema){
		List<String> ids = lm.getHFTaxSchdIDByPrdIDFeeTypeAndPurchaseType(privilegeID, feeType, purchaseType, taxName,schema);
		if(ids.size() !=1){
			throw new ErrorOnDataException("Should have one active tax schedule for privilege id = " + privilegeID 
					              + "; and fee type is " + feeType + "; and purchase type is " + purchaseType);
		}
		return ids.get(0);
	}
	private String getExpectedSplitInfoRegExp(){
		logger.info("Get expected tax split info.");
		String splitPercent1=pricing.transactionTaxes.get(0).getAccount(0)[1];
		String splitPercent2=pricing.transactionTaxes.get(0).getAccount(1)[1];
		String splitPercent3=(new BigDecimal(100-Double.valueOf(splitPercent1)-Double.valueOf(splitPercent2))).setScale(1).toString();
		String expectedSplitInfoRegExp="Tax Fund Split Type: Percent "+
										"1: \\$"+taxSplitedAmount1.toString()+" \\("+splitPercent1+"%\\)to "+"Vendor Fee Payable Account" +
												".*"+
										"2: \\$"+taxSplitedAmount2.toString()+" \\("+splitPercent2+"%\\)to "+"Voucher Account" +
												".*"+
										"3: \\$"+taxSplitedAmount3.toString()+" \\("+splitPercent3+"%\\)to "+"Sales Tax" +
												".*";
		return expectedSplitInfoRegExp;
										
	}
	private String getTransactionDateToday(){
		logger.info("Get transaction date today with format EEE MMM d yyyy.");
		TimeZone timez = DataBaseFunctions.getContractTimeZone(schema);
		return DateFunctions.getToday("EEE MMM d yyyy", timez);
	}

}
