/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax.vendorfee.privilege;

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
 * @Description:This case used to verify calculate duplicate vendor tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3320
 *             D_HF_ASSI_PRI_TO_STORE:id=2520
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3430
 *             D_HF_ADD_QTY_CONTROL:id=2500
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Duplicate Privilege - Calculate tax fee [TC:117366] step4
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 9, 2014
 */
public class CalculateTax_Duplicate extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,vendorTaxSchdID_tax1,vendorTaxSchdID_tax2;
	private double vendorTaxAmount_1,vendorTaxAmount_2;
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
		//get vendor fee tax 1 schedule id
		vendorTaxSchdID_tax1 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, REPLACEMENT_PURCHASE_TYPE_ID,pricing_duplicate.vendorTaxes.get(0).getTaxName(),schema);
		//get vendor fee tax 2 schedule id
		vendorTaxSchdID_tax2 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, REPLACEMENT_PURCHASE_TYPE_ID,pricing_duplicate.vendorTaxes.get(1).getTaxName(),schema);
		
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
		
		priFeesPage.verifyFeeInfo(pricing_duplicate.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID_tax1, vendorTaxAmount_1, TRANNAME_DUPLICATE_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing_duplicate.vendorTaxes.get(1).getTaxName(), vendorTaxSchdID_tax2, vendorTaxAmount_2, TRANNAME_DUPLICATE_PRIVILEGE);
		lm.gotoOrderCartPageFromFeeDetailPage();
		//verify tax total amount by tax name
		ordCartPg.verifyFeeAmountCorrect(pricing_duplicate.vendorTaxes.get(0).getTaxName(), true, String.valueOf(vendorTaxAmount_1));
		ordCartPg.verifyFeeAmountCorrect(pricing_duplicate.vendorTaxes.get(1).getTaxName(), true, String.valueOf(vendorTaxAmount_2));
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		privilege.name = "TaxProcessPrivilege6";
		privilege.code = "TP6";
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
		
		pricing_duplicate.prodCode=privilege.code;//PT1
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
		
		TaxInfo tax_vendor_1 = pricing_duplicate.new TaxInfo();
		tax_vendor_1.setIsFlat(true);
		tax_vendor_1.setTaxName("TaxForPriviledge_Flat");
		tax_vendor_1.setTaxCalculationRate("20");
		vendorTaxAmount_1 = ((new BigDecimal(pricing_duplicate.vendorFee).
				multiply(new BigDecimal(tax_vendor_1.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_vendor_1.setRate(String.valueOf(vendorTaxAmount_1));
		tax_vendor_1.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_vendor_1.setPurchaseType(REPLACEMENT_PURCHASE_TYPE_ID);
		
		TaxInfo tax_vendor_2 = pricing_duplicate.new TaxInfo();
		tax_vendor_2.setIsFlat(false);
		tax_vendor_2.setTaxName("TaxForPriviledge_Percent");
		tax_vendor_2.setRate("25");
		tax_vendor_2.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_vendor_2.setPurchaseType(REPLACEMENT_PURCHASE_TYPE_ID);
		vendorTaxAmount_2 = ((new BigDecimal(pricing_duplicate.vendorFee).
				multiply(new BigDecimal(tax_vendor_2.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		pricing_duplicate.vendorTaxes.add(tax_vendor_1);
		pricing_duplicate.vendorTaxes.add(tax_vendor_2);
		
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
