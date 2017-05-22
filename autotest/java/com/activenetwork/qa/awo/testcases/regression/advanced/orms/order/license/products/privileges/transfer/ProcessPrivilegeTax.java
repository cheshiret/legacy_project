/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.transfer;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.TaxInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This case used to verify privilege tax process for transfer sale flow
 * @Preconditions:This customer must be NOT "Tax Exempt", this attribute could be configure at customer detail page
 * @LinkSetUp: D_HF_ADD_PRIVILEGE_PRD:id=3280
 *             D_HF_ASSI_PRI_TO_STORE:id=2480
 *             D_HF_ADD_PRVI_LICENSE_YEAR:id=3390
 *             D_HF_ADD_QTY_CONTROL:id=2460
 *             D_HF_ADD_CUST_PROFILE:id=810
 * @SPEC:Process Privilege Transfer - Calculate tax fee [TC:117595]
 * @Task#:Auto-2225
 * 
 * @author Vivian
 * @Date  Jun 3, 2014
 */
public class ProcessPrivilegeTax extends LicenseManagerTestCase{
	private LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private String saleLocation,vendorTaxSchdID,stateTaxSchdID,transactionTaxSchdID,ordNum_from,ordNum_to;
	private double vendorTaxAmount,stateTaxAmount,transactionTaxAmount;
	private String[] vendorTaxProcessInfo,stateTaxProcessInfo,TransactionTaxProcessInfo;
	private Customer toCust = new Customer();
	private PricingInfo pricing_transaction = new PricingInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//add original pricing and transaction pricing with vendor/state/transaction tax
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		ILicMgrProductPricingPage pricingPage =lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		
		//add transaction pricing with vendor/state/transaction tax
		pricing_transaction.id = lm.addPricingForProduct(pricing_transaction, pricingPage, true);
		lm.addTaxForAnExistingPricing(pricing_transaction);
		
		//get tax schedule id info
		//get vendor fee tax schedule id
		vendorTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_VENDORFEE,TRANSFER_PURCHASE_TYPE_ID, pricing_transaction.vendorTaxes.get(0).getTaxName(),schema);
		//get state fee tax schedule id
		stateTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_PRIVILEGEFEE,TRANSFER_PURCHASE_TYPE_ID, pricing_transaction.stateTaxes.get(0).getTaxName(),schema);
		//get transaction fee tax schedule id
		transactionTaxSchdID = this.getTaxSchdID(privilege.privilegeId, FEETYPE_TRANFEE,TRANSFER_PURCHASE_TYPE_ID, pricing_transaction.transactionTaxes.get(0).getTaxName(),schema);
		
		//switch to sale location
		lm.gotoHomePage();
		lm.switchLocationInHomePage(saleLocation);
		//purchase privilege
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		ordNum_from = lm.processOrderCart(pay,true).split(" ")[0];
		
		//transfer privilege order
		lm.gotoPrivilegeOrderDetailPage(ordNum_from);
		String priNum_from = privOrderDetailsPage.getAllPrivilegesNum();
		
		lm.gotoPrivilegeDetailFromOrderPg(priNum_from);
		lm.transferPrivToOrderCart(toCust, privilege);
		ordNum_to = lm.processOrderCart(pay).split(" ")[0];
		
		//go to tranfer to privilege order detail page
		lm.gotoPrivilegeOrderDetailPage(ordNum_to);
		lm.gotoPrivilegeOrderFeesDetailPage();
		//verify tax info
		//verify vendor fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing_transaction.vendorTaxes.get(0).getTaxName(), vendorTaxSchdID, vendorTaxAmount, TRANNAME_TRANSFER_PRIVILEGE);
		//verify state fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing_transaction.stateTaxes.get(0).getTaxName(), stateTaxSchdID, stateTaxAmount, TRANNAME_TRANSFER_PRIVILEGE);
		//verify transaction fee tax info at fee detail page
		priFeesPage.verifyFeeInfo(pricing_transaction.transactionTaxes.get(0).getTaxName(), transactionTaxSchdID, transactionTaxAmount, TRANNAME_TRANSFER_PRIVILEGE);
		
		//verify tax process info
		this.initialTaxProcessInfo();
		//verify vendor fee tax process info at DB
		this.verifyTaxProcessInfo(vendorTaxProcessInfo);
		//verify state fee tax process info at DB
		this.verifyTaxProcessInfo(stateTaxProcessInfo);
		//verify transaction fee tax process info at DB
		this.verifyTaxProcessInfo(TransactionTaxProcessInfo);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		privilege.name = "TaxProcessPrivilege2";
		privilege.code = "TP2";
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
		pricing.purchaseType = ORIGINAL_PURCHASE_TYPE;//original
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
		
		pricing_transaction.prodCode=privilege.code;
		pricing_transaction.prodType="Privilege";
		
		pricing_transaction.status = "Active";
		pricing_transaction.locationClass = "All";
		pricing_transaction.licYearFrom = "All";
		pricing_transaction.purchaseType = TRANSFER_PURCHASE_TYPE;//transaction
		pricing_transaction.effectFrom = DateFunctions.getToday();
		pricing_transaction.vendorFee = "23.00";
		pricing_transaction.stateTransFee = "4.00";
		pricing_transaction.stateFee = "12.00";
		pricing_transaction.transFee = "11.00";
		pricing_transaction.stateFee_SplitBy = "Amount";
		pricing_transaction.stateFee_SplitInto = "1";
		pricing_transaction.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"2.00" });
		pricing_transaction.transFee_SplitBy = "Percent";
		pricing_transaction.transFee_SplitInto = "1";
		pricing_transaction.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.00" });
		
		TaxInfo tax_vendor = pricing_transaction.new TaxInfo();
		tax_vendor.setIsFlat(false);
		tax_vendor.setTaxName("TaxForPriviledge_Percent");
		tax_vendor.setRate("25");
		tax_vendor.addAccount(new String[]{"Vendor Fee Payable Account","100"});;
		tax_vendor.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		vendorTaxAmount = ((new BigDecimal(pricing_transaction.vendorFee).
				multiply(new BigDecimal(tax_vendor.getRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		pricing_transaction.vendorTaxes.add(tax_vendor);

		TaxInfo tax_state = pricing_transaction.new TaxInfo();
		tax_state.setIsFlat(true);
		tax_state.setTaxName("TaxForPriviledge_Flat");
		tax_state.setTaxCalculationRate("20");
		tax_state.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_state.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		stateTaxAmount = ((new BigDecimal(pricing_transaction.stateFee).
				multiply(new BigDecimal(tax_state.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_state.setRate(String.valueOf(stateTaxAmount));
		pricing_transaction.stateTaxes.add(tax_state);
		
		TaxInfo tax_transaction = pricing_transaction.new TaxInfo();
		tax_transaction.setIsFlat(true);
		tax_transaction.setTaxName("TaxForPriviledge_Flat");
		tax_transaction.setTaxCalculationRate("20");
		tax_transaction.addAccount(new String[]{"Vendor Fee Payable Account",""});
		tax_transaction.setPurchaseType(TRANSFER_PURCHASE_TYPE_ID);
		transactionTaxAmount =((new BigDecimal(pricing_transaction.transFee).
				multiply(new BigDecimal(tax_transaction.getTaxCalculationRate()).movePointLeft(2))).
				setScale(2,BigDecimal.ROUND_HALF_UP)).doubleValue();
		tax_transaction.setRate(String.valueOf(transactionTaxAmount));
		pricing_transaction.transactionTaxes.add(tax_transaction);
		
		cust.identifier.identifierType = "Tax ID";//"Tax ID";
		cust.identifier.identifierNum = "222222";//"222222";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
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
		List<String> ids = lm.getHFTaxSchdIDByPrdIDFeeTypeAndPurchaseType(privilegeID, feeType, purchaseType,taxName,schema);
		if(ids.size() !=1){
			throw new ErrorOnDataException("Should have one active tax schedule for privilege id = " + privilegeID 
					              + "; and fee type is " + feeType + "; and purchase type is " + purchaseType);
		}
		return ids.get(0);
	}
	
	private void initialTaxProcessInfo(){
		vendorTaxProcessInfo = new String[7];
		vendorTaxProcessInfo[1] =FEETYPE_VENDORFEE; 
		vendorTaxProcessInfo[2] = vendorTaxSchdID;
		vendorTaxProcessInfo[3] = TAX_RATE_TYPE_FLAT;
		vendorTaxProcessInfo[4] = pricing_transaction.vendorTaxes.get(0).getTaxCalculationRate();
		vendorTaxProcessInfo[5] = String.valueOf(vendorTaxAmount);
		vendorTaxProcessInfo[6] = lm.getRevenueLocationIDFromOrdItemInfo(ordNum_to, schema);
		
		stateTaxProcessInfo = new String[7];
		stateTaxProcessInfo[1] =FEETYPE_PRIVILEGEFEE; 
		stateTaxProcessInfo[2] = stateTaxSchdID;
		stateTaxProcessInfo[3] = TAX_RATE_TYPE_PERCENT;
		stateTaxProcessInfo[4] = "0";
		stateTaxProcessInfo[5] = String.valueOf(new BigDecimal(pricing_transaction.stateTaxes.get(0).getRate()).movePointLeft(2));
		stateTaxProcessInfo[6] = vendorTaxProcessInfo[6];
		
		TransactionTaxProcessInfo = new String[7];
		TransactionTaxProcessInfo[1] =FEETYPE_TRANFEE; 
		TransactionTaxProcessInfo[2] = transactionTaxSchdID;
		TransactionTaxProcessInfo[3] = TAX_RATE_TYPE_PERCENT;
		TransactionTaxProcessInfo[4] = "0";
		TransactionTaxProcessInfo[5] = String.valueOf(new BigDecimal(pricing_transaction.transactionTaxes.get(0).getRate()).movePointLeft(2));
		TransactionTaxProcessInfo[6] = vendorTaxProcessInfo[6];
	}
	
	private void verifyTaxProcessInfo(String[] expTaxProcessInfo){
		logger.info("Verify tax process info at DB.");
		String[] actTaxProcessInfo = lm.getHFTaxProcessInfo(ordNum_to, expTaxProcessInfo[1], schema);
		boolean result = true;
		result &= MiscFunctions.compareResult("Fee Type of Tax", expTaxProcessInfo[1], actTaxProcessInfo[1]);
		result &= MiscFunctions.compareResult("Tax Schedule", expTaxProcessInfo[2], actTaxProcessInfo[2]);
		result &= MiscFunctions.compareResult("Tax rate type", expTaxProcessInfo[3], actTaxProcessInfo[3]);
		result &= MiscFunctions.compareResult("Tax calculation rate", expTaxProcessInfo[4], actTaxProcessInfo[4]);
		result &= MiscFunctions.compareResult("Tax applied rate", Double.valueOf(expTaxProcessInfo[5]), Double.valueOf(actTaxProcessInfo[5]));
		result &= MiscFunctions.compareResult("Tax revenue location", expTaxProcessInfo[6], actTaxProcessInfo[6]);
		
		if(!result){
			throw new ErrorOnDataException("Tax process info not correct at DB. Please check log.");
		}else {
			logger.info("Tax process info is correct at DB.");
		}
	}
	
}
