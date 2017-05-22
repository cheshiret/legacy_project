/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax.vendorfee.privilege;

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
 * @Description:This case used to verify calculate replace privilege inventory vendor fee tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3410
 *             D_HF_ASSI_PRI_TO_STORE:id=2610
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3520
 *             D_HF_ADD_QTY_CONTROL:id=2590
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Replace Privilege Inventory - Calculate tax fee [TC:117596]
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 11, 2014
 */
public class CalculateTax_ReplacePriInventory extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,vendorTaxSchdID_tax1,vendorTaxSchdID_tax2,replaceReason,replaceNote;
	private double vendorTaxAmount_1,vendorTaxAmount_2;
	private PricingInfo pricing_replacePriInv = new PricingInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing with vendor tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		
		pricing_replacePriInv.id = lm.addPricingForProduct(pricing_replacePriInv, pricingPage, true);
		lm.addTaxForAnExistingPricing(pricing_replacePriInv);
		//get tax schedule id info
		//get vendor fee tax 1 schedule id
		vendorTaxSchdID_tax1 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, REPLACE_PRIVILEGE_INVENTORY_ID,pricing_replacePriInv.vendorTaxes.get(0).getTaxName(),schema);
		//get vendor fee tax 2 schedule id
		vendorTaxSchdID_tax2 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE, REPLACE_PRIVILEGE_INVENTORY_ID,pricing_replacePriInv.vendorTaxes.get(1).getTaxName(),schema);
		
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
		
		priFeesPage.verifyFeeInfo(pricing_replacePriInv.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID_tax1, vendorTaxAmount_1, TRANNAME_REPLACE_PRIVILEGE_INVENTORY);
		priFeesPage.verifyFeeInfo(pricing_replacePriInv.vendorTaxes.get(1).getTaxName(), vendorTaxSchdID_tax2, vendorTaxAmount_2, TRANNAME_REPLACE_PRIVILEGE_INVENTORY);
		lm.gotoOrderCartPageFromFeeDetailPage();
		//verify tax total amount by tax name
		ordCartPg.verifyFeeAmountCorrect(pricing_replacePriInv.vendorTaxes.get(0).getTaxName(), true, String.valueOf(vendorTaxAmount_1));
		ordCartPg.verifyFeeAmountCorrect(pricing_replacePriInv.vendorTaxes.get(1).getTaxName(), true, String.valueOf(vendorTaxAmount_2));
		
		//clear up
		lm.cancelCart();
		lm.reversePrivilegeOrderToCleanUp(orderNum, privilege.operateReason , privilege.operateNote, pay);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		privilege.name = "TaxProcessPrivilege15";
		privilege.code = "PT5";
		privilege.invType = "Sanity_Inv";
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
		
		pricing_replacePriInv.prodCode=privilege.code;//PT1
		pricing_replacePriInv.prodType="Privilege";
		
		pricing_replacePriInv.status = "Active";
		pricing_replacePriInv.locationClass = "All";
		pricing_replacePriInv.licYearFrom = "All";
		pricing_replacePriInv.purchaseType = PRIVILEGE_INVENTORY_REPLACEMENT_PURCHASE_TYPE;
		pricing_replacePriInv.effectFrom = DateFunctions.getToday();
		pricing_replacePriInv.vendorFee = "23.00";
		pricing_replacePriInv.stateTransFee = "4.00";
		pricing_replacePriInv.stateFee = "12.00";
		pricing_replacePriInv.transFee = "11.00";
		pricing_replacePriInv.stateFee_SplitBy = "Percent";
		pricing_replacePriInv.stateFee_SplitInto = "1";
		pricing_replacePriInv.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		pricing_replacePriInv.transFee_SplitBy = "Percent";
		pricing_replacePriInv.transFee_SplitInto = "1";
		pricing_replacePriInv.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		
		TaxInfo tax_vendor_1 = pricing_replacePriInv.new TaxInfo();
		tax_vendor_1.setIsFlat(true);
		tax_vendor_1.setTaxName("TaxForPriviledge_Flat");
		tax_vendor_1.setTaxCalculationRate("20");
		vendorTaxAmount_1 = ((new BigDecimal(pricing_replacePriInv.vendorFee).
				multiply(new BigDecimal(tax_vendor_1.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_vendor_1.setRate(String.valueOf(vendorTaxAmount_1));
		tax_vendor_1.addAccount(new String[]{"Vendor Fee Payable Account",""});
		
		TaxInfo tax_vendor_2 = pricing_replacePriInv.new TaxInfo();
		tax_vendor_2.setIsFlat(false);
		tax_vendor_2.setTaxName("TaxForPriviledge_Percent");
		tax_vendor_2.setRate("25");
		tax_vendor_2.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		vendorTaxAmount_2 = ((new BigDecimal(pricing_replacePriInv.vendorFee).
				multiply(new BigDecimal(tax_vendor_2.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		pricing_replacePriInv.vendorTaxes.add(tax_vendor_1);
		pricing_replacePriInv.vendorTaxes.add(tax_vendor_2);
		
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
