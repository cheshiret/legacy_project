package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.tax;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductTaxWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify add Tax info on Transaction fee--flat
 * 
 * @Preconditions:1. privilege product:102
 * 
 * @LinkSetUp:
 * 
 * @SPEC: Add Product Pricing - Tax info - Transaction Fee Tax [TC:117421] Add
 *        Product Pricing - Tax info - Privilege - DB check [TC:117361]
 * 
 * @Task#: AUTO-2183
 * 
 * @author szhou
 * @Date May 6, 2014
 */
public class AddTransactionFeeTax_Flat extends LicenseManagerTestCase {
	TaxInfo tax = pricing.new TaxInfo();
	
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

		lm.gotoAddTaxPage(pricing.id, false);
		// verify priceInfo
		this.verifyTaxInfoForTransactionFee(pricing.transactionTaxes, true);
		// verify in DB
		this.verifyTaxInfoInDB(pricing.transactionTaxes, pricing.prodCode,
				FEETYPE_TRANFEE, true, schema);
		
		tax.getAccounts().set(0, new String[]{"Vendor Fee Payable Account","0.06"});
		tax.setTaxAdjustRate("2.00");
		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForTransactionFee(pricing.transactionTaxes);

		lm.gotoAddTaxPage(pricing.id, false);
		// verify priceInfo
		this.verifyTaxInfoForTransactionFee(pricing.transactionTaxes, true);

		lm.logOutLicenseManager();

	}

	private void verifyTaxInfoInDB(List<TaxInfo> transactionTaxes,
			String prdCD, String feeType, boolean isFlat, String schema) {
		List<TaxInfo> taxInfos = lm.getHFProductTaxInfo(transactionTaxes.get(0)
				.getTaxName(), prdCD, feeType, schema);

		if (transactionTaxes.size() != taxInfos.size()) {
			throw new ErrorOnPageException(
					"transaction fee tax size is not correct, please check...",
					transactionTaxes.size(), taxInfos.size());
		}

		for (int i = 0; i < transactionTaxes.size(); i++) {
			TaxInfo expectResult = transactionTaxes.get(i);
			TaxInfo actualResult = taxInfos.get(i);

			boolean result = true;
			if (isFlat) {
				result &= MiscFunctions.compareResult("Tax Calculation Rate",
						String.valueOf(new BigDecimal(expectResult
								.getTaxCalculationRate()).setScale(2)), String
								.valueOf(new BigDecimal(actualResult
										.getTaxCalculationRate()).setScale(2)));
				result &= MiscFunctions.compareResult("Tax amount", String
						.valueOf(new BigDecimal(expectResult.getAccount(0)[1])),
						String.valueOf(new BigDecimal(actualResult.getRate())));
			} else {
				result &= MiscFunctions.compareResult("Rate", String
						.valueOf(new BigDecimal(expectResult.getRate())
								.movePointLeft(2).setScale(2)), String
						.valueOf(new BigDecimal(actualResult.getRate())));
			}
			result &= MiscFunctions.compareResult("Purchase type",
					expectResult.getPurchaseType(),
					actualResult.getPurchaseType());

			if (!result) {
				throw new ErrorOnPageException(
						"tax info is not correct at line :" + i
								+ "; Please check log...");
			}
		}
	}

	private void verifyTaxInfoForTransactionFee(List<TaxInfo> transactionTaxes,
			boolean isFlat) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.compareTransactionFeeTax(transactionTaxes, isFlat);
		taxWidget.clickCancel();

		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}

	private void addTaxForTransactionFee(List<TaxInfo> transactionTaxes) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
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
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		pricing.prodCode = "102";
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
		tax.setTaxAdjustRate("");
		tax.addAccount(new String[]{"Vendor Fee Payable Account","0.15"});
		tax.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		pricing.transactionTaxes.add(tax);
	}

}
