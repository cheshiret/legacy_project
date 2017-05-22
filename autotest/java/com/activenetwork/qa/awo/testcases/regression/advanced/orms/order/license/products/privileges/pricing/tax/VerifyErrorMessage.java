/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.tax;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductTaxWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify error message when adding tax info
 * 
 * @Preconditions:1. privilege product:116
 * 
 * @LinkSetUp:
 * 
 * @SPEC: Add Product Pricing - Tax info - Flow of Event [TC:117075] 
 *        Edit Product Pricing - Tax info - Error Message validation [TC:117362] 
 * 
 * @Task#: AUTO-2183
 * 
 * @author szhou
 * @Date May 6, 2014
 */
public class VerifyErrorMessage extends LicenseManagerTestCase {

	String error;

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
		this.addTaxForVendorFee(pricing.vendorTaxes);
		// verify error message
		this.verifyErrorMessage(error);

		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForStateFee(pricing.stateTaxes);
		// verify error message
		error = "The Tax Calculation Rate for Privilege Fee and TaxForPriviledge_Flat must be between 0% and 100%. Please change your entries.";
		this.verifyErrorMessage(error);

		pricing.stateTaxes.get(0).setTaxName("TaxForPriviledge_Percent");
		pricing.stateTaxes.get(0).setIsFlat(false);
		pricing.stateTaxes.get(0).setRate("101");
		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForStateFee(pricing.stateTaxes);
		// verify error message
		error = "The Tax Rate for Privilege Fee and TaxForPriviledge_Percent must be between 0% and 100%. Please change your entries.";
		this.verifyErrorMessage(error);

		pricing.stateTaxes.get(0).setRate("10");
		pricing.stateTaxes.get(0).addAccount(new String [] {"Vendor Fee Payable Account","50"});
		pricing.stateTaxes.get(0).setSplitNumberInfo("2");
		pricing.stateTaxes.get(0).addAccount(new String [] {"Vendor Fee Payable Account","50"});
		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForStateFee(pricing.stateTaxes);
		// verify error message
		error = "Duplicate Accounts for the Privilege Fee and TaxForPriviledge_Percent Tax Split is not allowed.";
		this.verifyErrorMessage(error);
		
		pricing.stateTaxes.get(0).removeSplitAccount(0);
		pricing.stateTaxes.get(0).addAccount(new String [] {"Sales Tax","102"});
		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForStateFee(pricing.stateTaxes);
		// verify error message
		error = "A Percentage Tax Split value entered for Privilege Fee and TaxForPriviledge_Percent is not valid. Please enter a numeric value between 0.00% and 100.00%.";
		this.verifyErrorMessage(error);
		

		lm.logOutLicenseManager();

	}

	private void verifyErrorMessage(String error) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		String actually = taxWidget.getErrorMsg();
		if (!error.equalsIgnoreCase(actually)) {
			throw new ErrorOnPageException(
					"Error Message is not corret,please check log...", error,
					actually);
		}

		taxWidget.clickCancel();
		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}

	private void addTaxForStateFee(List<TaxInfo> stateTaxes) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.setStateFeeTax(stateTaxes);
		taxWidget.clickOK();

		ajax.waitLoading();
		browser.waitExists(taxWidget, prdPricingPg);
	}

	private void addTaxForVendorFee(List<TaxInfo> vendorTaxes) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.setVendorFeeTax(vendorTaxes);
		taxWidget.clickOK();

		ajax.waitLoading();
		browser.waitExists(taxWidget, prdPricingPg);
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		pricing.prodCode = "116";
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

		TaxInfo tax = pricing.new TaxInfo();
		tax.setTaxName("TaxForPriviledge_Flat");
		tax.setIsFlat(true);
		TaxInfo taxOver = pricing.new TaxInfo();
		taxOver.setTaxName("TaxForPriviledge_Flat");
		taxOver.setIsFlat(true);
		pricing.vendorTaxes.add(tax);
		pricing.vendorTaxes.add(taxOver);

		TaxInfo statetax = pricing.new TaxInfo();
		statetax.setTaxName("TaxForPriviledge_Flat");
		statetax.setIsFlat(true);
		statetax.setTaxCalculationRate("120");
		pricing.stateTaxes.add(statetax);

		error = "There is an already existing record with TaxForPriviledge_Flat for Vendor Fee Tax Entry. Duplicate records are not allowed.";
	}

}
