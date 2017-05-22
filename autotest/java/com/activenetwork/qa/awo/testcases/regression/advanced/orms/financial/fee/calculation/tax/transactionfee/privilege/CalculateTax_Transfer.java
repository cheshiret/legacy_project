/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax.transactionfee.privilege;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This case used to verify calculate transfer transaction tax
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3370
 *             D_HF_ASSI_PRI_TO_STORE:id=2570
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3480
 *             D_HF_ADD_QTY_CONTROL:id=2550
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Transfer Privilege - Calculate tax fee [TC:117594] step7, step8
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 9, 2014
 */
public class CalculateTax_Transfer extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	private String saleLocation,tranTaxSchdID_tax1,tranTaxSchdID_tax2;
	private double tranTaxAmount_1,tranTaxAmount_2;
	private PricingInfo pricing_transfer = new PricingInfo();
	private Customer toCust = new Customer();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing with vendor tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		
		pricing_transfer.id = lm.addPricingForProduct(pricing_transfer, pricingPage, true);
		lm.addTaxForAnExistingPricing(pricing_transfer);
		//get tax schedule id info
		//get transaction fee tax 1 schedule id
		tranTaxSchdID_tax1 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, TRANSFER_PURCHASE_TYPE_ID,pricing_transfer.transactionTaxes.get(0).getTaxName(),schema);
		//get transaction fee tax 2 schedule id
		tranTaxSchdID_tax2 = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE, TRANSFER_PURCHASE_TYPE_ID,pricing_transfer.transactionTaxes.get(1).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(saleLocation);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum_from = lm.processOrderCart(pay);
		
		//transfer privilege order
		lm.gotoPrivilegeOrderDetailPage(ordNum_from);
		String priNum_from = privOrderDetailsPage.getAllPrivilegesNum();
		
		lm.gotoPrivilegeDetailFromOrderPg(priNum_from);
		lm.transferPrivToOrderCart(toCust, privilege);
		
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE);
		lm.gotoFeeDetailsPageInCart("New - 1");
		//verify tax detail info at detail page
		priFeesPage.verifyFeeInfo(pricing_transfer.transactionTaxes.get(0).getTaxName(), tranTaxSchdID_tax1, tranTaxAmount_1, TRANNAME_TRANSFER_PRIVILEGE);
		priFeesPage.verifyFeeInfo(pricing_transfer.transactionTaxes.get(1).getTaxName(), tranTaxSchdID_tax2, tranTaxAmount_2, TRANNAME_TRANSFER_PRIVILEGE);
		lm.gotoOrderCartPageFromFeeDetailPage();
		//verify tax total amount by tax name
		ordCartPg.verifyFeeAmountCorrect(pricing_transfer.transactionTaxes.get(0).getTaxName(), true, String.valueOf(tranTaxAmount_1));
		ordCartPg.verifyFeeAmountCorrect(pricing_transfer.transactionTaxes.get(1).getTaxName(), true, String.valueOf(tranTaxAmount_2));
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		privilege.name = "TaxProcessPrivilege11";
		privilege.code = "PT1";
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
		
		pricing_transfer.prodCode=privilege.code;
		pricing_transfer.prodType="Privilege";
		
		pricing_transfer.status = "Active";
		pricing_transfer.locationClass = "All";
		pricing_transfer.licYearFrom = "All";
		pricing_transfer.purchaseType = TRANSFER_PURCHASE_TYPE;
		pricing_transfer.effectFrom = DateFunctions.getToday();
		pricing_transfer.vendorFee = "23.00";
		pricing_transfer.stateTransFee = "4.00";
		pricing_transfer.stateFee = "12.00";
		pricing_transfer.transFee = "11.00";
		pricing_transfer.stateFee_SplitBy = "Percent";
		pricing_transfer.stateFee_SplitInto = "1";
		pricing_transfer.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		pricing_transfer.transFee_SplitBy = "Percent";
		pricing_transfer.transFee_SplitInto = "1";
		pricing_transfer.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		
		TaxInfo tax_tran_1 = pricing_transfer.new TaxInfo();
		tax_tran_1.setIsFlat(true);
		tax_tran_1.setTaxName("TaxForPriviledge_Flat");
		tax_tran_1.setTaxCalculationRate("20");
		tranTaxAmount_1 = ((new BigDecimal(pricing_transfer.transFee).
				multiply(new BigDecimal(tax_tran_1.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_tran_1.setRate(String.valueOf(tranTaxAmount_1));
		tax_tran_1.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_tran_1.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		
		TaxInfo tax_tran_2 = pricing_transfer.new TaxInfo();
		tax_tran_2.setIsFlat(false);
		tax_tran_2.setTaxName("TaxForPriviledge_Percent");
		tax_tran_2.setRate("25");
		tax_tran_2.addAccount(new String[]{"Vendor Fee Payable Account","100"});
		tax_tran_2.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		tranTaxAmount_2 = ((new BigDecimal(pricing_transfer.transFee).
				multiply(new BigDecimal(tax_tran_2.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		
		pricing_transfer.transactionTaxes.add(tax_tran_1);
		pricing_transfer.transactionTaxes.add(tax_tran_2);
		
		cust.identifier.identifierType = "Tax ID";//"Tax ID";
		cust.identifier.identifierNum = "222222";//"222222";
		cust.customerClass = "Individual";
		cust.lName = "TEST-Advanced4";
		cust.fName = "QA-Advanced4";
		cust.residencyStatus="Non Resident";
		
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "19880608";
		toCust.identifier.identifierType = "Green Card";
		toCust.identifier.identifierNum = "321456";
		toCust.identifier.country="Canada";
		toCust.residencyStatus = "Non Resident";
		toCust.lName = "Test-Sanity4";
		toCust.fName = "QA-Sanity4";
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
