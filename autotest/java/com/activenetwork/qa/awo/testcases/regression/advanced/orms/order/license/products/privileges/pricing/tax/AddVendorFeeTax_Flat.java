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
 * @Description: Verify add Tax info on Vendor fee--flat
 * 
 * @Preconditions:1. privilege product:106
 * 
 * @LinkSetUp:
 * 
 * @SPEC: Add Product Pricing - Tax info - Vendor Fee Tax [TC:117360] Add
 *        Product Pricing - Tax info - Privilege - DB check [TC:117361]
 * 
 * @Task#: AUTO-2183
 * 
 * @author szhou
 * @Date May 6, 2014
 */
public class AddVendorFeeTax_Flat extends LicenseManagerTestCase {
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
		this.addTaxForVendorFee(pricing.vendorTaxes);

		lm.gotoAddTaxPage(pricing.id, false);
		// verify priceInfo
		this.verifyTaxInfoForVendorFee(pricing.vendorTaxes, true);
		// verify in DB
		this.verifyTaxInfoInDB(pricing.vendorTaxes, pricing.prodCode,
				FEETYPE_VENDORFEE, true, schema);
		
		tax.getAccounts().set(0, (new String[]{"Vendor Fee Payable Account","0.06"}));
		tax.setTaxAdjustRate("6.00");
		lm.gotoAddTaxPage(pricing.id, false);
		this.addTaxForVendorFee(pricing.vendorTaxes);

		lm.gotoAddTaxPage(pricing.id, false);
		// verify priceInfo
		this.verifyTaxInfoForVendorFee(pricing.vendorTaxes, true);

		lm.logOutLicenseManager();

	}

	private void verifyTaxInfoForVendorFee(List<TaxInfo> vendorTaxes,
			boolean isFlat) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.compareVendorFeeTax(vendorTaxes, isFlat);
		taxWidget.clickCancel();

		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}
	
	private void verifyTaxInfoInDB(List<TaxInfo> vendorTaxes,String prdCD,String feeType, boolean isFlat,String schema){
		List<TaxInfo> taxInfos=lm.getHFProductTaxInfo(vendorTaxes.get(0).getTaxName(),prdCD,feeType,schema);
		
		if (vendorTaxes.size() != taxInfos.size()) {
			throw new ErrorOnPageException(
					"vendor fee tax size is not correct, please check...",
					vendorTaxes.size(), taxInfos.size());
		}

		for (int i = 0; i < vendorTaxes.size(); i++) {
			TaxInfo expectResult=vendorTaxes.get(i);
			TaxInfo actualResult=taxInfos.get(i);
			
			boolean result = true;
			if(isFlat){
				result &= MiscFunctions.compareResult("Tax Calculation Rate", String.valueOf(new BigDecimal(expectResult.getTaxCalculationRate()).setScale(2)), String.valueOf(new BigDecimal(actualResult.getTaxCalculationRate()).setScale(2)));
				result &= MiscFunctions.compareResult("Tax amount",String.valueOf(new BigDecimal(expectResult.getAccount(0)[1]).setScale(2)) , String.valueOf(new BigDecimal(actualResult.getRate()).setScale(2)));
			}else{
				result &= MiscFunctions.compareResult("Rate", String.valueOf(new BigDecimal(expectResult.getRate()).movePointLeft(2).setScale(2)),String.valueOf(new BigDecimal( actualResult.getRate())));
			}
			result &= MiscFunctions.compareResult("Purchase type", expectResult.getPurchaseType(), actualResult.getPurchaseType());
			
			if(!result){
				throw new ErrorOnPageException("tax info is not correct at line :"+i+"; Please check log...");
			}
		}
	}

	private void addTaxForVendorFee(List<TaxInfo> vendorTaxes) {
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage
				.getInstance();

		taxWidget.setVendorFeeTax(vendorTaxes);
		taxWidget.clickOK();

		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		pricing.prodCode = "106";
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

		
		tax.setTaxName("TaxForPriviledge_Flat");
		tax.setTaxCalculationRate("5.0000");
//		tax.setTaxAmount("0.05");
		tax.setTaxAdjustRate("");
		tax.addAccount(new String[]{"Vendor Fee Payable Account","0.05"});
		tax.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		tax.setIsFlat(true);
		pricing.vendorTaxes.add(tax);

	}

}
