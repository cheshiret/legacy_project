/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This case used to verify calculate duplicate transaction tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3400
 *             D_HF_ASSI_PRI_TO_STORE:id=2600
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3510
 *             D_HF_ADD_QTY_CONTROL:id=2580
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Duplicate Privilege - Calculate tax fee [TC:117366]
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 9, 2014
 */
public class CalculateTax_Privilege_Duplicate extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,tranTaxSchdID,vendorTaxSchdID,stateTaxSchdID,tax_flat,tax_percent;
	private double stateTaxAmount,vendorTaxAmount,tranTaxAmount,taxAmount_flat,taxAmount_percent;
	private PricingInfo pricing_duplicate = new PricingInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing with vendor tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		
		pricing_duplicate.id = lm.addPricingForProduct(pricing_duplicate, pricingPage, true);
		lm.addTaxForAnExistingPricing(pricing_duplicate);
		//get tax schedule id info
		//get transaction fee tax schedule id
		tranTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, REPLACEMENT_PURCHASE_TYPE_ID,pricing_duplicate.transactionTaxes.get(0).getTaxName(),schema);
		//get vendor fee tax schedule id
		vendorTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, REPLACEMENT_PURCHASE_TYPE_ID,pricing_duplicate.vendorTaxes.get(0).getTaxName(),schema);
		//get state fee tax schedule id
		stateTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, REPLACEMENT_PURCHASE_TYPE_ID,pricing_duplicate.stateTaxes.get(0).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(saleLocation);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//replace privilege
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_DUPLICATE_PRIVILEGE);
		lm.gotoFeeDetailsPageInCart("New - 1");
		//verify tax detail info at detail page
		priFeesPage.verifyFeeInfo(pricing_duplicate.transactionTaxes.get(0).getTaxName(), tranTaxSchdID, tranTaxAmount, TRANNAME_DUPLICATE_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing_duplicate.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID, vendorTaxAmount, TRANNAME_DUPLICATE_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing_duplicate.stateTaxes.get(0).getTaxName(), stateTaxSchdID, stateTaxAmount, TRANNAME_DUPLICATE_PRIVILEGE);
		lm.gotoOrderCartPageFromFeeDetailPage();
		//verify tax total amount by tax name
		ordCartPg.verifyFeeAmountCorrect(tax_flat, true, String.valueOf(taxAmount_flat));
		ordCartPg.verifyFeeAmountCorrect(tax_percent, true, String.valueOf(taxAmount_percent));
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		privilege.name = "TaxProcessPrivilege14";
		privilege.code = "PT4";
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
		pricing.purchaseType = ORIGINAL_PURCHASE_TYPE;
		pricing.effectFrom = DateFunctions.getToday();
		pricing.vendorFee = "23.00";
		pricing.stateTransFee = "4.00";
		pricing.stateFee = "12.00";
		pricing.transFee = "11.00";
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		
		pricing_duplicate.prodCode=privilege.code;
		pricing_duplicate.prodType="Privilege";
		
		pricing_duplicate.status = "Active";
		pricing_duplicate.locationClass = "All";
		pricing_duplicate.licYearFrom = "All";
		pricing_duplicate.purchaseType = DUPLICATE_PURCHASE_TYPE;
		pricing_duplicate.effectFrom = DateFunctions.getToday();
		pricing_duplicate.vendorFee = "23.00";
		pricing_duplicate.stateTransFee = "4.00";
		pricing_duplicate.stateFee = "12.00";
		pricing_duplicate.transFee = "11.00";
		pricing_duplicate.stateFee_SplitBy = "Percent";
		pricing_duplicate.stateFee_SplitInto = "1";
		pricing_duplicate.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		pricing_duplicate.transFee_SplitBy = "Percent";
		pricing_duplicate.transFee_SplitInto = "1";
		pricing_duplicate.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		
		tax_flat = "TaxForPriviledge_Flat";
		tax_percent = "TaxForPriviledge_Percent";
		
		TaxInfo tax_state = pricing_duplicate.new TaxInfo();
		tax_state.setIsFlat(false);
		tax_state.setTaxName(tax_percent);
		tax_state.setRate("25");
		tax_state.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_state.setPurchaseType(REPLACEMENT_PURCHASE_TYPE_ID);
		stateTaxAmount = ((new BigDecimal(pricing_duplicate.stateFee).
				multiply(new BigDecimal(tax_state.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		TaxInfo tax_vendor = pricing_duplicate.new TaxInfo();
		tax_vendor.setIsFlat(false);
		tax_vendor.setTaxName(tax_percent);
		tax_vendor.setRate("25");
		tax_vendor.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_vendor.setPurchaseType(REPLACEMENT_PURCHASE_TYPE_ID);
		vendorTaxAmount = ((new BigDecimal(pricing_duplicate.vendorFee).
				multiply(new BigDecimal(tax_state.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		TaxInfo tax_tran = pricing_duplicate.new TaxInfo();
		tax_tran.setIsFlat(true);
		tax_tran.setTaxName(tax_flat);
		tax_tran.setTaxCalculationRate("20");
		tax_tran.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_tran.setPurchaseType(REPLACEMENT_PURCHASE_TYPE_ID);
		tranTaxAmount = ((new BigDecimal(pricing_duplicate.transFee).
				multiply(new BigDecimal(tax_tran.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_tran.setRate(String.valueOf(tranTaxAmount));
		
		pricing_duplicate.stateTaxes.add(tax_state);
		pricing_duplicate.vendorTaxes.add(tax_vendor);
		pricing_duplicate.transactionTaxes.add(tax_tran);
		
		taxAmount_flat = tranTaxAmount;
		taxAmount_percent = stateTaxAmount + vendorTaxAmount;
		
		cust.identifier.identifierType = "Tax ID";//"Tax ID";
		cust.identifier.identifierNum = "222222";//"222222";
		cust.customerClass = "Individual";
		cust.lName = "TEST-Advanced4";
		cust.fName = "QA-Advanced4";
		cust.residencyStatus="Non Resident";
	}
	
	private String getTaxSchdID(String privilegeID,String feeType,String purchaseType, String taxName,String schema){
		List<String> ids = lm.getHFTaxSchdIDByPrdIDFeeTypeAndPurchaseType(privilegeID, feeType, purchaseType, taxName,schema);
		if(ids.size() !=1){
			throw new ErrorOnDataException("Should have one active tax schedule for privilege id = " + privilegeID 
					              + "; and fee type is " + feeType + "; and purchase type is " + purchaseType);
		}
		return ids.get(0);
	}
	
}
