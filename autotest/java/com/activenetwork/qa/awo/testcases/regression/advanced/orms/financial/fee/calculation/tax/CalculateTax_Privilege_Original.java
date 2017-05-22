/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This case used to verify calculate original transaction tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3390
 *             D_HF_ASSI_PRI_TO_STORE:id=2590
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3500
 *             D_HF_ADD_QTY_CONTROL:id=2570
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Purchase Privilege - Calculate tax fee [TC:117364]
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 9, 2014
 */
public class CalculateTax_Privilege_Original extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,tranTaxSchdID,vendorTaxSchdID,stateTaxSchdID,tax_flat,tax_percent;
	private double stateTaxAmount,vendorTaxAmount,tranTaxAmount,taxAmount_flat,taxAmount_percent;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing with vendor tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		
		lm.addTaxForAnExistingPricing(pricing);
		//get tax schedule id info
		//get transaction fee tax schedule id
		tranTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.transactionTaxes.get(0).getTaxName(),schema);
		//get vendor fee tax schedule id
		vendorTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.vendorTaxes.get(0).getTaxName(),schema);
		//get state fee tax schedule id
		stateTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.stateTaxes.get(0).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(saleLocation);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		lm.gotoFeeDetailsPageInCart("New - 1");
		//verify tax detail info at detail page
		priFeesPage.verifyFeeInfo(pricing.transactionTaxes.get(0).getTaxName(), tranTaxSchdID, tranTaxAmount, TRANNAME_PURCHASE_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID, vendorTaxAmount, TRANNAME_PURCHASE_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing.stateTaxes.get(0).getTaxName(), stateTaxSchdID, stateTaxAmount, TRANNAME_PURCHASE_PRIVILEGE);
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
		
		privilege.name = "TaxProcessPrivilege13";
		privilege.code = "PT3";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.privilegeId = lm.getPrdIDByPrdName(privilege.name, null, schema);
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Regression Testing";
		
		pricing.prodCode=privilege.code;//PT1
		pricing.prodType="Privilege";
		
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = "All";
		pricing.purchaseType = "Original";
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
		
		tax_flat = "TaxForPriviledge_Flat";
		tax_percent = "TaxForPriviledge_Percent";
		
		TaxInfo tax_state = pricing.new TaxInfo();
		tax_state.setIsFlat(false);
		tax_state.setTaxName(tax_percent);
		tax_state.setRate("25");
		tax_state.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_state.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		stateTaxAmount = ((new BigDecimal(pricing.stateFee).
				multiply(new BigDecimal(tax_state.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		TaxInfo tax_vendor = pricing.new TaxInfo();
		tax_vendor.setIsFlat(true);
		tax_vendor.setTaxName(tax_flat);
		tax_vendor.setTaxCalculationRate("20");
		vendorTaxAmount = ((new BigDecimal(pricing.vendorFee).
				multiply(new BigDecimal(tax_vendor.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_vendor.setRate(String.valueOf(vendorTaxAmount));
		tax_vendor.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_vendor.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		
		TaxInfo tax_tran = pricing.new TaxInfo();
		tax_tran.setIsFlat(true);
		tax_tran.setTaxName(tax_flat);
		tax_tran.setTaxCalculationRate("20");
		tax_tran.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_tran.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		tranTaxAmount = ((new BigDecimal(pricing.transFee).
				multiply(new BigDecimal(tax_tran.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_tran.setRate(String.valueOf(tranTaxAmount));
		
		pricing.stateTaxes.add(tax_state);
		pricing.vendorTaxes.add(tax_vendor);
		pricing.transactionTaxes.add(tax_tran);
		
		taxAmount_flat = vendorTaxAmount + tranTaxAmount;
		taxAmount_percent = stateTaxAmount;
		
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
