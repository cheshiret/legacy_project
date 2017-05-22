package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 17, 2012
 */
public abstract class LicMgrGetListForDuplicate extends LicenseManagerTestCase {
	public PricingInfo duplicatePricing = new PricingInfo();
	public PricingInfo transferPricing = new PricingInfo();
	public LicenseYear licenseYear = new LicenseYear();
	public QuantityControlInfo quantityControl = new QuantityControlInfo();
	public Customer transferCust = new Customer();
	public String locationClass = "06-State Parks Agent";
	public String fiscalYear;
	
	protected void createPrivilegeProduct() {
		//Precondition#1. Privilege Instance Potentially Available for Duplicate Purchase: Inventory Type="None"
		lm.gotoProductSearchListPageFromTopMenu("Privileges");
		if(!lm.checkPrivilegeExisted(privilege.code, schema)) {
			lm.addPrivilegeProduct(privilege);
		}
		
		//Precondition#2. Privilege Instance is Missing Replacement Pricing Info
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		//a. deactivate all existing pricing
		lm.deactivateAllProductPricings(LicMgrPrivilegePricingPage.getInstance());
		//b. add 'Original' pricing
		lm.addPricingForProduct(pricing, LicMgrPrivilegePricingPage.getInstance());
		//c. add 'duplicate' pricing
		lm.addPricingForProduct(duplicatePricing, LicMgrPrivilegePricingPage.getInstance());
		//d. add 'transfer' pricing
		lm.addPricingForProduct(transferPricing, LicMgrPrivilegePricingPage.getInstance());
		
		//Precondition#3. Privilege Product is Not Assigned to the Sales Location
		lm.unassignPrivilegeFromStoresThruLocationClass(locationClass);
		lm.assignPrivilegeToStoresThruLocationClass(locationClass);
		
		//Precondition#4. Privilege Instance Cannot be Replaced at Sales Location
		lm.gotoPrivilegeLicenseYearPage();
		lm.deactivateAllPrivilegeLicenseYear();
		lm.addLicenseYear(licenseYear);
		
		//Precondition#5. Privilege Instance Replacement Maximum Allowed Exceeded
		lm.deactivatePrivilegeQuantityControlRecords();
		lm.addPrivilegeQuantityControl(quantityControl);
		Browser.sleep(60);//to ensure the new created privilege displayed at list page
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//customer info
		cust.customerClass = "Individual";
		cust.fName = "QA-Basic25";
		cust.lName = "TEST-Basic25";
		cust.dateOfBirth = "19880612";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000025";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		transferCust.customerClass = "Individual";
		transferCust.fName = "QA-Advanced2";
		transferCust.lName = "TEST-Advanced2";
		transferCust.dateOfBirth = "19850224";
		transferCust.identifier.identifierType = "Tax ID";
		transferCust.identifier.identifierNum = "333333";
		transferCust.identifier.country = "Canada";
		transferCust.residencyStatus = "Non Resident";
		
		fiscalYear = lm.getFiscalYear(schema);
		
		//privilege product info
		String fiscalYear = lm.getFiscalYear(schema);
//		privilege.code = StringUtil.getRandomString(3, true);
		privilege.status = OrmsConstants.ACTIVE_STATUS;
		privilege.name = "GetPrivListForDuplicate";
//		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.qty = "1";
		privilege.licenseYear = fiscalYear;
		privilege.invType = "None";
		privilege.validFromDateCalculation = "Based on Priv LY Record or Purchase Date (If Greater)";
		privilege.validToDateCalculation = "Based on Priv License Year Record";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.authorizationQuantity = "Return as Individual Privileges";
		privilege.displayCategory = "Hunting";
		privilege.displaySubCategory = "Annual";
		privilege.reportCategory = "Resident Licenses";
		
		//privilege pricing info
		pricing.status = OrmsConstants.ACTIVE_STATUS;
		pricing.prodType = "Privilege";
		pricing.locationClass = "All";
		pricing.licYearFrom = fiscalYear;
		pricing.licYearTo = pricing.licYearFrom;
		pricing.purchaseType = "Original";
		pricing.effectFrom = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		pricing.effectTo = DateFunctions.getDateAfterGivenDay(pricing.effectFrom, 5);
		pricing.vendorFee = "10";
		pricing.stateTransFee = "9";
		pricing.stateFee = "8";
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "1";
		pricing.transFee = "7";
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		
		duplicatePricing.status = OrmsConstants.ACTIVE_STATUS;
		duplicatePricing.prodType = "Privilege";
		duplicatePricing.locationClass = "All";
		duplicatePricing.licYearFrom = fiscalYear;
		duplicatePricing.licYearTo = duplicatePricing.licYearFrom;
		duplicatePricing.purchaseType = "Duplicate";
		duplicatePricing.effectFrom = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		duplicatePricing.effectTo = DateFunctions.getDateAfterGivenDay(duplicatePricing.effectFrom, 5);
		duplicatePricing.vendorFee = "6";
		duplicatePricing.stateTransFee = "5";
		duplicatePricing.stateFee = "4";
		duplicatePricing.stateFee_SplitBy = "Percent";
		duplicatePricing.stateFee_SplitInto = "1";
		duplicatePricing.transFee = "3";
		duplicatePricing.transFee_SplitBy = "Percent";
		duplicatePricing.transFee_SplitInto = "1";
		
		transferPricing.status = OrmsConstants.ACTIVE_STATUS;
		transferPricing.prodType = "Privilege";
		transferPricing.locationClass = "All";
		transferPricing.licYearFrom = fiscalYear;
		transferPricing.licYearTo = transferPricing.licYearFrom;
		transferPricing.purchaseType = "Transfer";
		transferPricing.effectFrom = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		transferPricing.effectTo = DateFunctions.getDateAfterGivenDay(transferPricing.effectFrom, 5);
		transferPricing.vendorFee = "5";
		transferPricing.stateTransFee = "5";
		transferPricing.stateFee = "5";
		transferPricing.stateFee_SplitBy = "Percent";
		transferPricing.stateFee_SplitInto = "1";
		transferPricing.transFee = "5";
		transferPricing.transFee_SplitBy = "Percent";
		transferPricing.transFee_SplitInto = "1";
		
		//license year info
		licenseYear.locationClass = "All";
		licenseYear.licYear = fiscalYear;
		licenseYear.sellFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		licenseYear.sellFromTime = "12:00";
		licenseYear.sellFromAmPm = "AM";
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(5);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";
		
		//privilege quantity control info
		quantityControl.locationClass = "All";
		quantityControl.multiSalesAllowance = "Yes (Within Same Transaction only)";
		quantityControl.minQuantityPerTran = "1";
		quantityControl.maxQuantityPerTran = "100";
		quantityControl.replacementMaxAllowed = "50";
	}
}
