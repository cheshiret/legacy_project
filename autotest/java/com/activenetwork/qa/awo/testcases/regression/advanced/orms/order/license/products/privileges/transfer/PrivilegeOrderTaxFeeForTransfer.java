/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.transfer;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
/**
 * @Description:
 * Step1:set up transfer pricing with tax information according to following rule:
 * 	 Vendor fee tax: split type=percent 100% to one account
 *   State fee tax: split type=flat $0.4 to one account,$1.6 to another account
 * 	 Transaction fee tax: split type=percent 100% to one account
 * Step2:purchase the above privilege whose tax is set
 * Step3:Verify following information for privilege order fee detail:
 *   1.Tax basic information including fee type, schedule ID, amount, transaction, qty  and transaction date 
 *   2.Tax split information including fee split amount/percentage and account
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=3290,4702
 * d_hf_add_privilege_product:id=3410
 * d_hf_add_prvi_license_year:id=3580
 * d_hf_add_qty_control:id=2610
 * d_hf_assi_pri_to_store:id=2630 
 * d_hf_add_pricing:id=4702
 * @SPEC: TC:117598
 * @Task#:auto-2166
 * 
 * @author sborjigin
 * @Date  Jun 20, 2014
 */
public class PrivilegeOrderTaxFeeForTransfer extends LicenseManagerTestCase {
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailPg;
	private ILicMgrProductPricingPage pricingPage;
	private String vendorTaxSchdID;
	private String stateTaxSchdID;
	private String transactionTaxSchdID;
	private String walMartRole;
	private double vendorTaxAmount,stateTaxAmount,transactionTaxAmount;
	private BigDecimal taxSplitedAmount1;
	private BigDecimal taxSplitedAmount2;

	
	private Customer toCustomer=new Customer();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing and tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
        //lm.deactivateAllProductPricings(pricingPage);
		checkAndInactiveTranferPricing();
		//add pricing
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		//add tax
		lm.addTaxForAnExistingPricing(pricing);
		//get vendor fee tax schedule id
		vendorTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, TRANSFER_PURCHASE_TYPE_ID,pricing.vendorTaxes.get(0).getTaxName(),schema);
		//get state fee tax schedule id
		stateTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, TRANSFER_PURCHASE_TYPE_ID,pricing.stateTaxes.get(0).getTaxName(),schema);
		//get transaction fee tax schedule id
		transactionTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, TRANSFER_PURCHASE_TYPE_ID,pricing.transactionTaxes.get(0).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(walMartRole);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String priviOrderNum=lm.processOrderCart(pay).split(" ")[0];
		//transfer privilege
		lm.gotoPrivilegeOrderDetailPage(priviOrderNum);
		privOrderDetailPg=LicMgrPrivilegeOrderDetailsPage.getInstance();
		String privilegeNumber=privOrderDetailPg.getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNumber);
		lm.transferPrivToOrderCart(toCustomer, privilege);
		String privTransferOrdNum = lm.processOrderCart(pay).split(" ")[0];
		//go to privilege order fee detail page
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(privTransferOrdNum);
		lm.gotoPrivilegeOrderFeesDetailPage();
		//verify basic tax info
		//verify vendor fee tax info at fee detail page
		String transcationDateToday=getTransactionDateToday();
		priFeesPage.verifyFeeInfo(pricing.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID, vendorTaxAmount, TRANNAME_TRANSFER_PRIVILEGE,StringUtil.EMPTY,transcationDateToday);
		//verify state fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing.stateTaxes.get(0).getTaxName(), stateTaxSchdID, stateTaxAmount, TRANNAME_TRANSFER_PRIVILEGE,StringUtil.EMPTY,transcationDateToday);
		//verify transaction fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing.transactionTaxes.get(0).getTaxName(), transactionTaxSchdID, transactionTaxAmount, TRANNAME_TRANSFER_PRIVILEGE,StringUtil.EMPTY,transcationDateToday);
		
		//verify tax split info for state tax fee
		priFeesPage.verifyFeeSplitInfo(stateTaxSchdID,getExpectedSplitInfoRegExp());
		//verify tax split info for transaction tax fee
		priFeesPage.verifyFeeSplitInfo(transactionTaxSchdID,"Tax Fund Split Type: Percent");

		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		walMartRole = "HF HQ Role - Auto-WAL-MART";
		//initialize privilege product
		privilege.name = "OrderTaxPriv03";
		privilege.code = "TX3";
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
		pricing.purchaseType = "Transfer";
		pricing.effectFrom = DateFunctions.getDateAfterToday(-1);
		pricing.vendorFee = "10";
		pricing.stateTransFee = "5";
		pricing.stateFee = "10";
		pricing.transFee = "10";
		pricing.stateFee_SplitBy = "Flat";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.add(new String[] {
				"710 - Boat Launch",
				"20.00" });
		pricing.stateFee_accounts.add(new String[] {
				"Agent Fee - 100%",
				"70.00" });

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
		tax_vendor.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		pricing.vendorTaxes.add(tax_vendor);
		
		TaxInfo tax_state = pricing.new TaxInfo();
		tax_state.setIsFlat(true);
		tax_state.setTaxName("TaxForPrivFlat");
		tax_state.setTaxCalculationRate("20");
		tax_state.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		tax_state.setSplitNumberInfo("2");
		tax_state.addAccount(new String[]{"Vendor Fee Payable Account",""});

		taxSplitedAmount1=(new BigDecimal(0.40)).setScale(2,BigDecimal.ROUND_HALF_UP);
		taxSplitedAmount2=(new BigDecimal(1.60)).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		tax_state.addAccount(new String[]{"Voucher Account",""});
		pricing.stateTaxes.add(tax_state);
		stateTaxAmount = ((new BigDecimal(pricing.stateFee).
				multiply(new BigDecimal(tax_state.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		TaxInfo tax_transaction = pricing.new TaxInfo();
		tax_transaction.setIsFlat(false);
		tax_transaction.setTaxName("TaxForPrivPercent");
		tax_transaction.setRate("20");
		tax_transaction.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_transaction.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		pricing.transactionTaxes.add(tax_transaction);
		transactionTaxAmount =((new BigDecimal(pricing.transFee).
				multiply(new BigDecimal(tax_transaction.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();

		
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "PASS0624";
		cust.customerClass = "Individual";
		cust.lName = "TEST-MSIndiv02";
		cust.fName = "QA-MSIndiv02";
		cust.residencyStatus="Non Resident";
		
		
		toCustomer.identifier.identifierType = "Passport";
		toCustomer.identifier.identifierNum = "PASS0625";
		toCustomer.customerClass = "Individual";
		toCustomer.lName = "TEST-MSIndiv03";
		toCustomer.fName = "QA-MSIndiv03";
		toCustomer.residencyStatus="Non Resident";
		toCustomer.dateOfBirth="07/07/1987";
		toCustomer.identifier.country="Canada";

		
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
		String expectedSplitInfoRegExp="Tax Fund Split Type: Fee Split "+
										"1: \\$"+taxSplitedAmount1.toString()+" to "+"Vendor Fee Payable Account" +
												".*"+
										"2: \\$"+taxSplitedAmount2.toString()+" to "+"Voucher Account" +
												".*";
		return expectedSplitInfoRegExp;
										
	}
	private void checkAndInactiveTranferPricing(){
		logger.info("Check and deactivate existing tranfer price.");
		pricingPage.uncheckShowCurrentRecordsOnly();
		if(pricingPage.checkPricingRecordExists(pricing)){
			String tranferPricingId=pricingPage.getPricingID(pricing);
			lm.deactivateProductPricing(tranferPricingId, pricingPage);
		}
	}
	private String getTransactionDateToday(){
		logger.info("Get transaction date today with format EEE MMM d yyyy.");
		TimeZone timez = DataBaseFunctions.getContractTimeZone(schema);
		return DateFunctions.getToday("EEE MMM d yyyy", timez);
	}

}
