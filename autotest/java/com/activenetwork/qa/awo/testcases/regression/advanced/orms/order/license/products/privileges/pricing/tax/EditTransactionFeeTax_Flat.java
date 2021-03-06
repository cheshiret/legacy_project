package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.tax;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductTaxWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;


/**
 * 
 * @Description: Verify edit Tax info on Transaction fee--flat
 * 
 * @Preconditions:1. privilege product:109
 * 
 * @LinkSetUp: 
 *                     
 * @SPEC: Edit Product Pricing - Tax info & Pricing (Privilege & Privilege Lottery) [TC:117593]  
 * 
 * @Task#: AUTO-2183
 * 
 * @author szhou
 * @Date  May 6, 2014
 */
public class EditTransactionFeeTax_Flat extends LicenseManagerTestCase {
	TaxInfo tax=pricing.new TaxInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);

		ILicMgrProductPricingPage pricingPage = lm
				.gotoProductPricingPageFromListPage(pricing.prodType,
						pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);

		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForTransactionFee(pricing.transactionTaxes);

		// edit tax
		lm.gotoAddTaxPage(pricing.id, false);
		tax.setTaxCalculationRate("6.0000");
		this.editTaxForTransactionFee(pricing.transactionTaxes);

		lm.gotoAddTaxPage(pricing.id, false);
		// verify priceInfo
		this.verifyTaxInfoForTransactionFee(pricing.transactionTaxes, true);

		lm.logOutLicenseManager();

	}

	private void verifyTaxInfoForTransactionFee(List<TaxInfo> transactionTaxes,
			boolean isFlat) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget
				.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.compareTransactionFeeTax(transactionTaxes, isFlat);
		taxWidget.clickCancel();

		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}
	
	private void editTaxForTransactionFee(List<TaxInfo> transactionTaxes) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget
				.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.setTransactionFeeTax(transactionTaxes);
		taxWidget.clickOK();

		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}

	private void addTaxForTransactionFee(List<TaxInfo> transactionTaxes) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget
				.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.setTransactionFeeTax(transactionTaxes);
		taxWidget.clickOK();

		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		pricing.prodCode = "109";
		pricing.prodType = "Privilege";

		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = "All";
		pricing.purchaseType = "Original";
		pricing.effectFrom = DateFunctions.getToday();
		pricing.vendorFee = "1.00";
		pricing.stateTransFee = "1.00";
		pricing.stateFee = "2.00";
		pricing.transFee = "3.00";
		pricing.stateFee_SplitBy = "Amount";
		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"2.00" });
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });

		tax.setIsFlat(true);
		tax.setTaxName("TaxForPriviledge_Flat");
		tax.setTaxCalculationRate("5.0000");
//		tax.setTaxAmount("0.05");
		tax.addAccount(new String[]{"Vendor Fee Payable Account","0.05"});
		tax.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		pricing.transactionTaxes.add(tax);
	}

}
