/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax.statefee;

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
 * @Description:This case used to verify calculate state tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3300
 *             D_HF_ASSI_PRI_TO_STORE:id=2500
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3410
 *             D_HF_ADD_QTY_CONTROL:id=2480
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Purchase Privilege - Calculate tax fee [TC:117364] step3, step4
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 9, 2014
 */
public class CalculateTax_Original extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,stateTaxSchdID_tax1,stateTaxSchdID_tax2;
	private double stateTaxAmount_1,stateTaxAmount_2;

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
		//get state fee tax 1 schedule id
		stateTaxSchdID_tax1 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.stateTaxes.get(0).getTaxName(),schema);
		//get state fee tax 2 schedule id
		stateTaxSchdID_tax2 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE, ORIGINAL_PURCHASE_TYPE_ID,pricing.stateTaxes.get(1).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(saleLocation);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		
		lm.gotoFeeDetailsPageInCart("New - 1");
		priFeesPage.verifyFeeInfo(pricing.stateTaxes.get(0).getTaxName(), stateTaxSchdID_tax1, stateTaxAmount_1, TRANNAME_PURCHASE_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing.stateTaxes.get(1).getTaxName(), stateTaxSchdID_tax2, stateTaxAmount_2, TRANNAME_PURCHASE_PRIVILEGE);
		lm.gotoOrderCartPageFromFeeDetailPage();
		//verify tax total amount by tax name
		ordCartPg.verifyFeeAmountCorrect(pricing.stateTaxes.get(0).getTaxName(), true, String.valueOf(stateTaxAmount_1));
		ordCartPg.verifyFeeAmountCorrect(pricing.stateTaxes.get(1).getTaxName(), true, String.valueOf(stateTaxAmount_2));
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		privilege.name = "TaxProcessPrivilege4";
		privilege.code = "TP4";
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
		
		TaxInfo tax_state_1 = pricing.new TaxInfo();
		tax_state_1.setIsFlat(true);
		tax_state_1.setTaxName("TaxForPriviledge_Flat");
		tax_state_1.setTaxCalculationRate("20");
		stateTaxAmount_1 = ((new BigDecimal(pricing.stateFee).
				multiply(new BigDecimal(tax_state_1.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_state_1.setRate(String.valueOf(stateTaxAmount_1));
		tax_state_1.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_state_1.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		
		TaxInfo tax_state_2 = pricing.new TaxInfo();
		tax_state_2.setIsFlat(false);
		tax_state_2.setTaxName("TaxForPriviledge_Percent");
		tax_state_2.setRate("25");
		tax_state_2.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_state_2.setPurchaseType(ORIGINAL_PURCHASE_TYPE_ID);
		stateTaxAmount_2 = ((new BigDecimal(pricing.stateFee).
				multiply(new BigDecimal(tax_state_2.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		pricing.stateTaxes.add(tax_state_1);
		pricing.stateTaxes.add(tax_state_2);
		
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
