/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:This case used to verify calculate replace privilege inventory fee tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3440
 *             D_HF_ASSI_PRI_TO_STORE:id=2640
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3550
 *             D_HF_ADD_QTY_CONTROL:id=2620
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Replace Privilege Inventory - Calculate tax fee [TC:117596]
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 11, 2014
 */
public class CalculateTax_Privilege_ReplacePriInventory extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,tranTaxSchdID,vendorTaxSchdID,stateTaxSchdID,tax_flat,tax_percent,replaceReason,replaceNote;
	private double stateTaxAmount,vendorTaxAmount,tranTaxAmount,taxAmount_flat,taxAmount_percent;
	private PricingInfo pricing_replacePriInventory = new PricingInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing with vendor tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		
		pricing_replacePriInventory.id = lm.addPricingForProduct(pricing_replacePriInventory, pricingPage, true);
		lm.addTaxForAnExistingPricing(pricing_replacePriInventory);
		//get tax schedule id info
		//get transaction fee tax schedule id
		tranTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, REPLACE_PRIVILEGE_INVENTORY_ID,pricing_replacePriInventory.transactionTaxes.get(0).getTaxName(),schema);
		//get vendor fee tax schedule id
		vendorTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, REPLACE_PRIVILEGE_INVENTORY_ID,pricing_replacePriInventory.vendorTaxes.get(0).getTaxName(),schema);
		//get state fee tax schedule id
		stateTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, REPLACE_PRIVILEGE_INVENTORY_ID,pricing_replacePriInventory.stateTaxes.get(0).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(saleLocation);
		//purchase privilege
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		String originalPriInvNum = lm.addPrivilegeItem(privilege);
		lm.goToCart();
		String orderNum = lm.processOrderCart(pay);

		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		lm.replacePrivInventoryFromPriOrderDetailsPgToOrderCard(originalPriInvNum, StringUtil.EMPTY, replaceReason, replaceNote);
		lm.gotoFeeDetailsPageInCart("New - 1");
		//verify tax detail info at detail page
		priFeesPage.verifyFeeInfo(pricing_replacePriInventory.transactionTaxes.get(0).getTaxName(), tranTaxSchdID, tranTaxAmount, TRANNAME_REPLACE_PRIVILEGE_INVENTORY);
		priFeesPage.verifyFeeInfo(pricing_replacePriInventory.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID, vendorTaxAmount, TRANNAME_REPLACE_PRIVILEGE_INVENTORY);
		priFeesPage.verifyFeeInfo(pricing_replacePriInventory.stateTaxes.get(0).getTaxName(), stateTaxSchdID, stateTaxAmount, TRANNAME_REPLACE_PRIVILEGE_INVENTORY);
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
		
		privilege.name = "TaxProcessPrivilege18";
		privilege.code = "PT8";
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
		
		pricing_replacePriInventory.prodCode=privilege.code;
		pricing_replacePriInventory.prodType="Privilege";
		
		pricing_replacePriInventory.status = "Active";
		pricing_replacePriInventory.locationClass = "All";
		pricing_replacePriInventory.licYearFrom = "All";
		pricing_replacePriInventory.purchaseType = PRIVILEGE_INVENTORY_REPLACEMENT_PURCHASE_TYPE;
		pricing_replacePriInventory.effectFrom = DateFunctions.getToday();
		pricing_replacePriInventory.vendorFee = "23.00";
		pricing_replacePriInventory.stateTransFee = "4.00";
		pricing_replacePriInventory.stateFee = "12.00";
		pricing_replacePriInventory.transFee = "11.00";
		pricing_replacePriInventory.stateFee_SplitBy = "Percent";
		pricing_replacePriInventory.stateFee_SplitInto = "1";
		pricing_replacePriInventory.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		pricing_replacePriInventory.transFee_SplitBy = "Percent";
		pricing_replacePriInventory.transFee_SplitInto = "1";
		pricing_replacePriInventory.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		
		tax_flat = "TaxForPriviledge_Flat";
		tax_percent = "TaxForPriviledge_Percent";
		
		TaxInfo tax_state = pricing_replacePriInventory.new TaxInfo();
		tax_state.setIsFlat(false);
		tax_state.setTaxName(tax_percent);
		tax_state.setRate("25");
		tax_state.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		stateTaxAmount = ((new BigDecimal(pricing_replacePriInventory.stateFee).
				multiply(new BigDecimal(tax_state.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		TaxInfo tax_vendor = pricing_replacePriInventory.new TaxInfo();
		tax_vendor.setIsFlat(false);
		tax_vendor.setTaxName(tax_percent);
		tax_vendor.setRate("25");
		tax_vendor.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		vendorTaxAmount = ((new BigDecimal(pricing_replacePriInventory.vendorFee).
				multiply(new BigDecimal(tax_state.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		TaxInfo tax_tran = pricing_replacePriInventory.new TaxInfo();
		tax_tran.setIsFlat(true);
		tax_tran.setTaxName(tax_flat);
		tax_tran.setTaxCalculationRate("20");
		tax_tran.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tranTaxAmount = ((new BigDecimal(pricing_replacePriInventory.transFee).
				multiply(new BigDecimal(tax_tran.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_tran.setRate(String.valueOf(tranTaxAmount));
		
		pricing_replacePriInventory.stateTaxes.add(tax_state);
		pricing_replacePriInventory.vendorTaxes.add(tax_vendor);
		pricing_replacePriInventory.transactionTaxes.add(tax_tran);
		
		taxAmount_flat = tranTaxAmount;
		taxAmount_percent = stateTaxAmount + vendorTaxAmount;
		
		cust.identifier.identifierType = "Tax ID";//"Tax ID";
		cust.identifier.identifierNum = "222222";//"222222";
		cust.customerClass = "Individual";
		cust.lName = "TEST-Advanced4";
		cust.fName = "QA-Advanced4";
		cust.residencyStatus="Non Resident";
		
		replaceReason = "15 - Lost Privilege";
		replaceNote = "Regression Testing";
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

