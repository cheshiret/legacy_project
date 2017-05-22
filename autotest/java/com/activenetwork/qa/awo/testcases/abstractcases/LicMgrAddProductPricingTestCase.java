/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:'Add Product Pricing.doc'
 * @Task#:Auto-666
 * @Defects:
 * 
 * @author asun
 * @Date Jul 21, 2011
 */
public abstract class LicMgrAddProductPricingTestCase extends
		LicenseManagerTestCase {
	
	private String licenseFiscalYear;

	protected ILicMgrProductPricingPage pricingPage = null;

	protected static int CANCEL = 0;

	protected static int ERROR = 1;

	protected static int ExistRecord_Active_LocClass_YearFrom_PurchaseType = 2;

	protected static int LocClass_NotAll_PurTypWithExistRecord_LocClass_NotAll_PurTyp = 3;

	protected static int LocClass_NotAll_PurTypWithExistRecord_LocClass_All_PurTyp = 4;

	protected static int LocClass_All_PurTypWithExistRecord_LocClass_NotAll_PurTyp = 5;

	protected static int SUCCESS = 6;

	protected int action = 0;

	protected List<Exception> list = new ArrayList<Exception>();
	protected int count = 0;

	protected boolean ok = false;// true:Click Ok;false:click 'Cancel' in
	// princing widget

	protected String blankEffectFromMsg;
	protected String inVaildEffectiveFromMsg;
	protected String inVaildEffectiveToMsg;
	protected String fromGreaterThanToMsg;
	protected String duplicateAccountsMsg_StateFee;
	protected String duplicateAccountsMsg_TransFee;
	protected String oneOfAccNoValueMsg_StateFee;
	protected String oneOfAccNoValueMsg_TransFee;
	protected String inVaildPercentValueMsg_StateFee;
	protected String invaildAmountValueMsg_StateFee;
	protected String invaildAmountValueMsg_transFee;
	protected String inVaildPercentValueMsg_TransFee;
	protected String inVaildNumValueMsg_StateFee;
	protected boolean isPrivilegeOrVehicle;
	protected VehicleRTI vehicle = new VehicleRTI();

	protected LicMgrAddProductPricingTestCase() {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		

		blankEffectFromMsg = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
		inVaildEffectiveFromMsg = "The Effective From Date entered is not valid. Please enter the Effective From Date using the format Ddd Mmm dd yyyy.";
		inVaildEffectiveToMsg = "The Effective To Date entered is not valid. Please enter the Effective To Date using the format Ddd Mmm dd yyyy.";
		fromGreaterThanToMsg = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
		duplicateAccountsMsg_StateFee = "Duplicate Accounts for the State Fee Fund Splits is not allowed.";
		oneOfAccNoValueMsg_StateFee = "An Account for the State Fee Fund Splits does not have its corresponding value. Please specify the value in the field provided.";
		inVaildPercentValueMsg_StateFee = "A Percentage Fund Split value entered for State Fee is not valid. Please enter a numeric value between 0.00% and 100.00%.";
		inVaildNumValueMsg_StateFee = "An Amount Fund Split value entered for State Fee is not valid. Please enter either a numeric value greater than or equal to $0.00 or select the Balance option.";
		duplicateAccountsMsg_TransFee = "Duplicate Accounts for the Transaction Fee Fund Splits is not allowed.";
		oneOfAccNoValueMsg_TransFee = "An Account for the Transaction Fee Fund Splits does not have its corresponding value. Please specify the value in the field provided.";
		inVaildPercentValueMsg_TransFee = "A Percentage Fund Split value entered for Transaction Fee is not valid. Please enter a numeric value between 0.00% and 100.00%.";
		invaildAmountValueMsg_transFee = "An Amount Fund Split value entered for Transaction Fee is not valid. Please enter either a numeric value greater than or equal to $0.00 or select the Balance option.";

		// common pricing info
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

	}

	@Override
	public void execute() {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		licenseFiscalYear=this.getTranslationForLicenseFiscalYear(schema);
		isPrivilegeOrVehicle=pricing.prodType.matches("(?i)(Privilege|Vehicle)");
		
		lm.loginLicenseManager(login);
		LicMgrProductPage prodPage = lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		
		checkAndAddProduct(prodPage);
		
		pricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		if (action == CANCEL) {
			lm.addPricingForProduct(pricing, pricingPage, false);
			this.verifyCancel(pricing);
		} else if (action == ERROR) {
			this.verifyEntriesValid(pricing, pricingPage);
		} else if (action == ExistRecord_Active_LocClass_YearFrom_PurchaseType) {
			this.verifyAddConflictWithExistRecordSame_Active_LocClass_YearFrom_PurchaseType(
							pricing, pricingPage);
		} else if (action == LocClass_NotAll_PurTypWithExistRecord_LocClass_NotAll_PurTyp) {
			this.verifyAddPricing_LocClass_NotAll_PurTypeWithExistRecord_LocClass_NotAll_PurType(
							pricing, pricingPage);
		} else if (action == LocClass_NotAll_PurTypWithExistRecord_LocClass_All_PurTyp) {
			this.verifyAddPricing_LocClass_NotAll_PurTypeConflictWithExistRecord_LocClass_All_PurType(
							pricing, pricingPage);
		} else if (action == LocClass_All_PurTypWithExistRecord_LocClass_NotAll_PurTyp) {
			this.verifyAddPricing_LocClass_All_PurConflictWithExistRecord_LocClass_NotAll_PurcahseType(
							pricing, pricingPage);
		} else if (action == SUCCESS) {
			this.verifySuccessfully();
		} else {
			throw new ErrorOnDataException("Unknow Action..");
		}

		lm.logOutLicenseManager();
	}
	
	protected void checkAndAddProduct(LicMgrProductPage prodPage) {
		if (!prodPage.checkProductRecordExist(pricing.prodCode)) {
			if (pricing.prodType.equalsIgnoreCase("Consumable")) {
				newConsumableProduct();
				lm.addConsumableProduct(consumable);
			} else if (pricing.prodType.equalsIgnoreCase("Privilege")) {
				newPrivilegeProduct();
				lm.addPrivilegeProduct(privilege);
			} else if (pricing.prodType.equalsIgnoreCase("Vehicle")) {
				newVehicleProduct();
				lm.addVehicleProduct(vehicle);
			} else if (pricing.prodType.equalsIgnoreCase("Supply")) {
				lm.addSupplyProudct(supply);
			}
		}
	}

	private void newPrivilegeProduct() {
		privilege.code = pricing.prodCode;
		privilege.name = "Privilege " + privilege.code;
		privilege.customerClasses = new String[]{"Individual"};
	}

	protected void newVehicleProduct() {
		vehicle.setPrdCode(pricing.prodCode);
		vehicle.setPrdName("Vehicle" + pricing.prodCode);
		vehicle.setPrdGroup("Inspection");
		vehicle.setVehicleType("Boat");
		
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		
		vehicle.setCustClass(custClass);
	}

	private void newConsumableProduct() {
		consumable.code = pricing.prodCode;
		consumable.name = "TestForAddProductPricingTest";
		consumable.description = "TestForAddProductPricingTest";
		consumable.orderType = "POS Sale";
		consumable.productGroup = "other";		
	}

	/**
	 * add and verify if adding is succussfully
	 */
	protected void verifySuccessfully() {
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyPricingInfoInPricingWidget(pricingPage, pricing);
	}

	/**
	 * verify adding pricing info
	 * 
	 * @param pricingPage
	 * @param pricing
	 */
	protected void verifyPricingInfoInPricingWidget(
			ILicMgrProductPricingPage pricingPage, PricingInfo pricing) {

		PricingInfo pricingOnPage = lm.getProductPricingInfo(pricing.id,
				pricingPage);
		logger.info("verify adding pricing info....");
		boolean result = comparePricingInfo(pricingOnPage, pricing);
		if (!result) {
			throw new ErrorOnPageException("verify 'Add pricing info' failed");
		}

	}

	/**
	 * compare pricing info
	 * 
	 * @param pricing1
	 * @param pricing2
	 * @return
	 */
	private boolean comparePricingInfo(PricingInfo pricing1,
			PricingInfo pricing2) {
		boolean result = true;
		if (!pricing1.locationClass.equalsIgnoreCase(pricing2.locationClass)) {
			result &= false;
			logger.error("Pricing Location Class doesn't match.");
			logger.error("Expected value is:"+pricing2.locationClass+", and actual value is:"+pricing1.locationClass);
		}
		if (!pricing1.licYearFrom.equalsIgnoreCase(pricing2.licYearFrom)) {
			result &= false;
			logger.error("Pricing License Year From doesn't match.");
			logger.error("Expected value is:"+pricing2.licYearFrom+", and actual value is:"+pricing1.licYearFrom);
		}
		if (!pricing1.licYearTo.equalsIgnoreCase(pricing2.licYearTo)) {
			result &= false;
			logger.error("Pricing License Year To doesn't match.");
			logger.error("Expected value is:"+pricing2.licYearTo+", and actual value is:"+pricing1.licYearTo);
		}
		if (pricing1.prodType.matches("(?i)privilege|(?i)Vehicle")) {
			if (!pricing1.purchaseType.equalsIgnoreCase(pricing2.purchaseType)) {
				result &= false;
				logger.error("Pricing Purchase Type doesn't match.");
				logger.error("Expected value is:"+pricing2.purchaseType+", and actual value is:"+pricing1.purchaseType);
			}
		}
		if (!DateFunctions.formatDate(pricing1.effectFrom).equalsIgnoreCase(
				DateFunctions.formatDate(pricing2.effectFrom))) {
			result &= false;
			logger.error("Pricing Effective From Date doesn't match.");
			logger.error("Expected value is:"+pricing2.effectFrom+", and actual value is:"+pricing1.effectFrom);
		}

		if (pricing1.effectTo.trim().length() != 0
				&& pricing2.effectTo.trim().length() != 0) {
			if (!DateFunctions.formatDate(pricing1.effectTo).equalsIgnoreCase(
					DateFunctions.formatDate(pricing2.effectTo))) {
				result &= false;
				logger.error("Pricing Effective To Date doesn't match.");
				logger.error("Expected value is:"+pricing2.effectTo+", and actual value is:"+pricing1.effectTo);
			}
		} else {
			if (!pricing1.effectTo.equals(pricing2.effectTo)) {
				result &= false;
				logger.error("Pricing Effective To Date doesn't match.");
				logger.error("Expected value is:"+pricing2.effectTo+", and actual value is:"+pricing1.effectTo);
			}
		}

		if (Double.parseDouble(pricing1.vendorFee)!=Double.parseDouble(pricing2.vendorFee)) {
			result &= false;
			logger.error("Pricing Vendor Fee doesn't match.");
			logger.error("Expected value is:"+pricing2.vendorFee+", and actual value is:"+pricing1.vendorFee);
		}

		if (Double.parseDouble(pricing1.stateTransFee)!=Double.parseDouble(pricing2.stateTransFee)) {
			result &= false;
			logger.error("Pricing State Trans Fee doesn't match.");
			logger.error("Expected value is:"+pricing2.stateTransFee+", and actual value is:"+pricing1.stateTransFee);
		}
		
		if (Double.parseDouble(pricing1.stateFee)!=Double.parseDouble(pricing2.stateFee)) {
			result &= false;
			logger.error("Pricing State Fee doesn't match.");
			logger.error("Expected value is:"+pricing2.stateFee+", and actual value is:"+pricing1.stateFee);
		}
		if (!pricing2.prodType.matches("(?i)Vehicle")) {
			if (!pricing1.stateFee_SplitBy
					.equalsIgnoreCase(pricing2.stateFee_SplitBy)) {
				result &= false;
				logger.error("Pricing State Fee Split By doesn't match.");
				logger.error("Expected value is:"+pricing2.stateFee_SplitBy+", and actual value is:"+pricing1.stateFee_SplitBy);
			}
		}
		if (!pricing1.stateFee_SplitInto
				.equalsIgnoreCase(pricing2.stateFee_SplitInto)) {
			result &= false;
			logger.error("Pricing State Fee Split Into doesn't match.");
			logger.error("Expected value is:"+pricing2.stateFee_SplitInto+", and actual value is:"+pricing1.stateFee_SplitInto);
		}
		for (int i = 0; i < pricing1.stateFee_accounts.size(); i++) {
			if (!pricing1.stateFee_accounts.get(i)[0]
					.equalsIgnoreCase(pricing2.stateFee_accounts.get(i)[0])) {
				result &= false;
				logger.error("Pricing State Fee Account doesn't match.");
				logger.error("Expected value is:"+pricing2.stateFee_accounts.get(i)[0]+", and actual value is:"+pricing1.stateFee_accounts.get(i)[0]);
			}
			if(Double.parseDouble(pricing1.stateFee_accounts.get(i)[1]) != Double.parseDouble(pricing2.stateFee_accounts.get(i)[1])){
				result &= false;
				logger.error("Pricing State Fee Account Value doesn't match.");
				logger.error("Expected value is:"+Double.parseDouble(pricing2.stateFee_accounts.get(i)[1])+", and actual value is:"+Double.parseDouble(pricing1.stateFee_accounts.get(i)[1]));
			}
		}
		
		if (Double.parseDouble(pricing1.transFee)!=Double.parseDouble(pricing2.transFee)) {
			result &= false;
			logger.error("Pricing Transaction Fee doesn't match.");
			logger.error("Expected value is:"+pricing2.transFee+", and actual value is:"+pricing1.transFee);
		}
		if (!pricing2.prodType.matches("(?i)Vehicle")) {
			if (!pricing1.transFee_SplitBy
					.equalsIgnoreCase(pricing2.transFee_SplitBy)) {
				result &= false;
				logger.error("Pricing Transaction Fee Split By doesn't match.");
				logger.error("Expected value is:"+pricing2.transFee_SplitBy+", and actual value is:"+pricing1.transFee_SplitBy);
			}
		}
		if (!pricing1.transFee_SplitInto
				.equalsIgnoreCase(pricing2.transFee_SplitInto)) {
			result &= false;
			logger.error("Pricing Transaction Fee Split Into doesn't match.");
			logger.error("Expected value is:"+pricing2.transFee_SplitInto+", and actual value is:"+pricing1.transFee_SplitInto);
		}
		for (int i = 0; i < pricing1.transFee_accounts.size(); i++) {
			if (!pricing1.transFee_accounts.get(i)[0]
					.equalsIgnoreCase(pricing2.transFee_accounts.get(i)[0])) {
				result &= false;
				logger.error("Pricing Transaction Fee Account doesn't match.");
				logger.error("Expected value is:"+pricing2.transFee_accounts.get(i)[0]+", and actual value is:"+pricing1.transFee_accounts.get(i)[0]);
			}			
			if (Double.parseDouble(pricing1.transFee_accounts.get(i)[1])!=Double.parseDouble(pricing2.transFee_accounts.get(i)[1])) {
				result &= false;
				logger.error("Pricing Transaction Fee Account Value doesn't match.");
				logger.error("Expected value is:"+Double.parseDouble(pricing2.transFee_accounts.get(i)[1])+", and actual value is:"+Double.parseDouble(pricing1.transFee_accounts.get(i)[1]));
			}
		}
		return result;
	}

	/**
	 * verify effective from date,location class,License Year From/To date for
	 * UI requirement
	 */
	protected void VerifyCommonUIRequirement() {
		LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget
				.getInstance();

		pricingPage.clickAddProductPricing();
		addWidget.waitLoading();
		logger
				.info("verify effective from date,location class,License Year From/To date for UI requirement");

		if (addWidget.isStatusEditable()) {
			throw new ErrorOnPageException(
					"The status drop down list should not be editable");
		}

		String effectiveDate = DateFunctions.formatDate(addWidget
				.getEffectiveFromDate());
		if (!effectiveDate.equals(DateFunctions.getToday(DataBaseFunctions
				.getContractTimeZone(schema)))) {
			throw new ErrorOnPageException(
					"The default effective from date should be today");
		}
		addWidget.verifyLocationClassDropdownList();
		addWidget.verifyLicenseYearFrom();
		addWidget.verifyLicenseYearTo();

		if (!addWidget.isAccountsForStateFeeSortedByASC()) {
			throw new ErrorOnPageException(
					"Accounts for state fee should be sorted by ASC");
		}

		if (!addWidget.isAccountsForTransFeeSortedByASC()) {
			throw new ErrorOnPageException(
					"Accounts for trans fee should be sorted by ASC");
		}

		addWidget.clickCancel();
		pricingPage.waitLoading();
	}

	/**
	 * The specified License/Fiscal Year From is not "All" and there is an
	 * existing Product Pricing record for the same Product that is also
	 * "Active" and where the License/Fiscal Year From is not "All" and has the same
	 * values as this record for the following: Location Class and (if
	 * applicable) Purchase Type.
	 * 
	 * @param pricing
	 * @param pricingPage
	 */
	protected void verifyAddPricing_LocClass_NotAll_PurTypeWithExistRecord_LocClass_NotAll_PurType(
			PricingInfo pricing, ILicMgrProductPricingPage pricingPage) {
		String existId = "";
		String expectMsg;
		PricingInfo existingPricing = new PricingInfo();
		existingPricing = pricing.clone();

		int thisYear = Integer.parseInt(DateFunctions.getToday().split("/")[2]);

		pricing.licYearFrom = String.valueOf(thisYear);
		pricing.licYearTo = String.valueOf(thisYear + 3);
		existingPricing.licYearFrom = String.valueOf(thisYear + 1);
		existingPricing.licYearTo = String.valueOf(thisYear + 4);

		this.setUpAnExistingRecord(existingPricing, pricingPage);
		existId = pricingPage.getPricingID(existingPricing.status,
				existingPricing.locationClass, existingPricing.licYearFrom,
				existingPricing.purchaseType);
//		expectMsg = "Another active Product Pricing record "
//				+ existId
//				+ " already exists for the same Product with the same Location Class"+(this.isPrivilegeOrVehicle?" and Purchase Type":"")+", at least one of the same State/Province, and with overlapping "
//				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
//				+ " Year From and To entries. Please inactivate that record first or modify the "
//				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
//				+ " Year Range of that record first to eliminate the overlap.";
// update for debugging license.products.privileges.pricing.add.VerifyConflictWithExistRecord_3		
		expectMsg = "Another active Product Pricing record "
				+ existId
				+ " already exists for the same Product with the same Location Class"+(this.isPrivilegeOrVehicle?" and Purchase Type":"")+", and with overlapping "
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
				+ " Year From and To entries. Please inactivate that record first or modify the "
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
				+ " Year Range of that record first to eliminate the overlap.";
		lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyErrorMessages(expectMsg);
	}

	/**
	 * The specified License/Fiscal Year From is not "All" and there is an
	 * existing Product Pricing record for the same Product that is also
	 * "Active" and where the License/Fiscal Year From is "All" and has the same
	 * values as this record for the following: Location Class and (if
	 * applicable) Purchase Type.
	 * 
	 * @param pricing
	 * @param pricingPage
	 */
	protected void verifyAddPricing_LocClass_NotAll_PurTypeConflictWithExistRecord_LocClass_All_PurType(
			PricingInfo pricing, ILicMgrProductPricingPage pricingPage) {
		String existId = "";
		String expectMsg;
		PricingInfo existingPricing = new PricingInfo();
		existingPricing = pricing.clone();

		pricing.licYearFrom = DateFunctions.getToday().split("/")[2];
		pricing.licYearTo=pricing.licYearFrom;
		existingPricing.licYearFrom = "All";

		this.setUpAnExistingRecord(existingPricing, pricingPage);
		existId = pricingPage.getPricingID(existingPricing.status,
				existingPricing.locationClass, existingPricing.licYearFrom,
				existingPricing.purchaseType);
		expectMsg = "Another active Product Pricing record "
				+ existId
				+ " already exists for the same Product with the same Location Class"+(this.isPrivilegeOrVehicle?" and Purchase Type":"")+", and with overlapping "
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
				+ " Year From and To entries. Please inactivate that record first or modify the "
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
				+ " Year Range of that record first to eliminate the overlap.";
		lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyErrorMessages(expectMsg);
	}

	/**
	 * The specified License/Fiscal Year From is "All" and there is an existing
	 * Product Pricing record for the same Product that is also "Active" and
	 * where the License/Fiscal Year From is not "All" and has the same values
	 * as this record for the following: Location Class and (if applicable)
	 * Purchase Type.
	 * 
	 * @param pricing
	 * @param pricingPage
	 */
	protected void verifyAddPricing_LocClass_All_PurConflictWithExistRecord_LocClass_NotAll_PurcahseType(
			PricingInfo pricing, ILicMgrProductPricingPage pricingPage) {
		String existId = "";
		String expectMsg;
		PricingInfo existingPricing = new PricingInfo();
		existingPricing = pricing.clone();

		pricing.licYearFrom = "ALL";
		existingPricing.licYearFrom = DateFunctions.getToday().split("/")[2];
		existingPricing.licYearTo=existingPricing.licYearFrom;

		this.setUpAnExistingRecord(existingPricing, pricingPage);
		existId = pricingPage.getPricingID(existingPricing.status,
				existingPricing.locationClass, existingPricing.licYearFrom,
				existingPricing.purchaseType);
		expectMsg = "Another active Product Pricing record "
				+ existId
				+ " already exists for the same Product with the same Location Class"+(this.isPrivilegeOrVehicle?" and Purchase Type":"")+", and with overlapping "
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
				+ " Year From and To entries. Please inactivate that record first or modify the "
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear)
				+ " Year Range of that record first to eliminate the overlap.";
		lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyErrorMessages(expectMsg);
	}

	/**
	 * 
	 * There is already an existing Product Pricing record for the same Product
	 * that is also "Active" and that has the same values as this record for the
	 * following: Location Class, License/Fiscal Year From, and (if applicable)
	 * Purchase Type.
	 * 
	 * @param pricing
	 * @param pricingPage
	 */
	protected void verifyAddConflictWithExistRecordSame_Active_LocClass_YearFrom_PurchaseType(
			PricingInfo pricing, ILicMgrProductPricingPage pricingPage) {
		String existId = "";
		String expectMsg;
		PricingInfo existingPricing = new PricingInfo();
		existingPricing = pricing.clone();

		this.setUpAnExistingRecord(existingPricing, pricingPage);
		existId = pricingPage.getPricingID("Active",
				existingPricing.locationClass, existingPricing.licYearFrom,
				existingPricing.purchaseType);
		
//		expectMsg = "Another active Product Pricing record "
//				+ existId
//				+ " already exists for the same Product with the same Location Class"+(isPrivilegeOrVehicle?", ":" and ")
//				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear )
//				+ " Year From"+(isPrivilegeOrVehicle?", Purchase Type, and at least one of the same State/Province. ":". ")+"Duplicate active records are not allowed.";
// update by Lesley for debugging the case ...license.products.privileges.pricing.add.VerifyConflictWithExistRecord_0
		expectMsg = "Another active Product Pricing record "
				+ existId
				+ " already exists for the same Product with the same Location Class"+(isPrivilegeOrVehicle?", ":" and ")
				+ ((pricing.prodType.equalsIgnoreCase("Privilege")) ? "License":licenseFiscalYear )
				+ " Year From"+(isPrivilegeOrVehicle?" and Purchase Type. ":". ")+"Duplicate active records are not allowed.";
		
		lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyErrorMessages(expectMsg);
	}

	protected void setUpAnExistingRecord(PricingInfo pricing,
			ILicMgrProductPricingPage pricingPage) {
		logger.info("Set up a existing pricing record.");
		if (!pricingPage.checkPricingRecordExists(pricing)) {
			lm.addPricingForProduct(pricing, pricingPage, true);
		}
	}

	protected void verifyErrorMessages(String expectMsg) {
		LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget
				.getInstance();

		if (!addWidget.exists()) {
			throw new PageNotFoundException(
					"WorkFlow should stay in 'Add Pricing widget'");
		}
		String msgOnPage = addWidget.getErrorMsg();
		//updated by pzhu 2012/12/14 delete any blank space and change to uppercase for compare.
		if (!(msgOnPage.replaceAll(" and ", "").replaceAll(",", "").replaceAll("\\s", "").toUpperCase()).equals(expectMsg.replaceAll(" and ", "").replaceAll(",", "").replaceAll("\\s", "").toUpperCase())) {
			throw new ErrorOnPageException("The message should be '"
					+ expectMsg + "', The message on page is '" + msgOnPage + "'.");
		}
		logger.info("Verify successfully:'" + expectMsg + "'");
		addWidget.clickCancel();
		ajax.waitLoading();

	}

	/**
	 * add pricing and do verification for message
	 * @param pricing
	 * @param msg
	 *            message for verification
	 */
	protected void addPricingAndVerifyMessage(PricingInfo pricing, String msg) {
		try {
			count++;
			lm.addPricingForProduct(pricing, pricingPage, true);
			this.verifyErrorMessages(msg);
		} catch (Exception e) {
			list.add(e);
		}
	}

	protected void verifyEntriesValid(PricingInfo pricing,
			ILicMgrProductPricingPage pricingPage) {

		// verify ecffect from date is blank
		pricing.effectFrom = "";
		this.addPricingAndVerifyMessage(pricing, blankEffectFromMsg);
		pricing.effectFrom = DateFunctions.getToday();

		// verify effective from date is invaild
		this.verifyEffectiveDate(pricing, inVaildEffectiveFromMsg, true);

		// verify effective from date is invaild
		this.verifyEffectiveDate(pricing, inVaildEffectiveToMsg, false);

		// verify Effective From Date is greater than the entry for
		// Effective To Date.
		pricing.effectFrom = DateFunctions.getDateAfterToday(10);
		pricing.effectTo = DateFunctions.getDateAfterToday(1);
		this.addPricingAndVerifyMessage(pricing, fromGreaterThanToMsg);
		pricing.effectFrom = DateFunctions.getDateAfterToday(1);
		pricing.effectTo = DateFunctions.getDateAfterToday(10);

		// The same Account is specified more than once for the State Fee
		// Fund Splits.
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.clear();
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"20" });
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"80" });
		this.addPricingAndVerifyMessage(pricing, duplicateAccountsMsg_StateFee);

		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.clear();

		// A specified Account does not have a corresponding value for the
		// State Fee Fund Splits.
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - Membership Fees - Fisheries", "" });
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"80" });
		pricing.effectTo = DateFunctions.getDateAfterToday(1);
		this.addPricingAndVerifyMessage(pricing, oneOfAccNoValueMsg_StateFee);

		pricing.stateFee_accounts.clear();
		pricing.stateFee_SplitInto = "1";

		// A State Fee Fund Split by Percentage value is not a numeric value
		// greater than or equal to 0.00 and less than or equal to 100.00.
		// less than 0
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "-1" });
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","0" });
		pricing.effectTo = DateFunctions.getDateAfterToday(1);
		this.addPricingAndVerifyMessage(pricing,inVaildPercentValueMsg_StateFee);

		// greater than 100
		pricing.stateFee_accounts.clear();
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "101" });
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","0" });
		pricing.effectTo = DateFunctions.getDateAfterToday(1);
		this.addPricingAndVerifyMessage(pricing,inVaildPercentValueMsg_StateFee);
		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.clear();

		// A State Fee Fund Split by Percentage value is not a numeric value
		// greater than or equal to 0.00 and less than or equal to 100.00.
		// less than 0
		pricing.stateFee_SplitBy = "Amount";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "-1" });
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","0" });
		pricing.effectTo = DateFunctions.getDateAfterToday(1);
		this.addPricingAndVerifyMessage(pricing, inVaildNumValueMsg_StateFee);
		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.clear();

		// The same Account is specified more than once for the Transaction
		// Fee Fund Splits.
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.clear();
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","20" });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","80" });
		this.addPricingAndVerifyMessage(pricing, duplicateAccountsMsg_TransFee);
		pricing.transFee_accounts.clear();
		pricing.stateFee_SplitInto = "1";

		// A specified Account does not have a corresponding value for the
		// Transaction Fee Fund Splits.
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", " " });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","80" });
		this.addPricingAndVerifyMessage(pricing, oneOfAccNoValueMsg_TransFee);
		pricing.transFee_accounts.clear();
		pricing.transFee_SplitInto = "1";

		// A Transaction Fee Fund Split by Percentage value is not a numeric
		// value greater than or equal to 0.00 and less than or equal to
		// 100.00.
		// less than 0
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.clear();
		pricing.transFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "-1" });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","0" });
		this.addPricingAndVerifyMessage(pricing,inVaildPercentValueMsg_TransFee);
		// greater than 100
		pricing.transFee_accounts.clear();
		pricing.transFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "101" });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","0" });
		this.addPricingAndVerifyMessage(pricing,inVaildPercentValueMsg_TransFee);
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.clear();

		// A Transaction Fee Fund Split by Amount value is neither a numeric
		// value greater than or equal to 0.00,
		// nor the Balance indicator set to true.
		pricing.transFee_SplitBy = "Amount";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "-1" });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries"," " });
		this.addPricingAndVerifyMessage(pricing,invaildAmountValueMsg_transFee);
		pricing.transFee_accounts.clear();
		pricing.transFee_SplitInto = "1";

		// The sum of the values for the Transaction Fee Fund Split by
		// Percentage is not equal to 100.00%.
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "30" });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","80" });
		this.setPricingInfoAndVerifySum(pricing, "100", false);
		pricing.transFee_accounts.clear();
		pricing.transFee_SplitInto = "1";

		// The sum of the values for the State Fee Fund Split by Percentage
		// is not equal to 100.00%.
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - Membership Fees - Fisheries", "30" });
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","80" });
		this.setPricingInfoAndVerifySum(pricing, "100", true);
		pricing.stateFee_accounts.clear();
		pricing.stateFee_SplitInto = "1";

		// The sum of the values for the Transaction Fee Fund Split by
		// Amount is greater than the specified State Fee.
		pricing.transFee_SplitBy = "Amount";
		pricing.transFee_SplitInto = "2";
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","30" });
		pricing.transFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","80" });
		this.setPricingInfoAndVerifySum(pricing, pricing.transFee, false);
		pricing.transFee_accounts.clear();
		pricing.transFee_SplitInto = "1";

		// The sum of the values for the State Fee Fund Split by Amount is
		// greater than the specified State Fee.
		pricing.stateFee_SplitBy = "Amount";
		pricing.stateFee_SplitInto = "2";
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","30" });
		pricing.stateFee_accounts.add(new String[] {"POS Revenue - General Entrance / Daily Permits - Fisheries","80" });
		this.setPricingInfoAndVerifySum(pricing, pricing.stateFee, true);
		pricing.stateFee_accounts.clear();
		pricing.stateFee_SplitInto = "1";

		if (list.size() > 0) {
			for (Exception e : list) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			throw new TestCaseFailedException("Total verifications:" + count
					+ ", failed:" + list.size());
		}

	}

	/**
	 * set pricing info and verify sum..
	 * 
	 * @param pricing
	 * @param sum
	 * @param isStateFee
	 */
	protected void setPricingInfoAndVerifySum(PricingInfo pricing, String sum,
			boolean isStateFee) {
		try {
			count++;
			this.setPricingInfo(pricing, pricingPage);
			this.verifySumOfAccountValues(sum, isStateFee);
		} catch (Exception e) {
			list.add(e);
		}
	}

	/**
	 * verify sum of account values
	 * 
	 * @param sum
	 * @param isStateFee
	 *            true:state fee, false:transFee
	 * @param isPercent
	 *            true:percent, false:amount
	 */
	protected void verifySumOfAccountValues(String sum, boolean isStateFee) {
		LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget
				.getInstance();
		logger.info("Verify Sum of "
				+ (isStateFee ? "state fee " : "Transaction fee "));
		double sumFromPage = 0d;
		if (isStateFee)
			sumFromPage = Double.parseDouble(addWidget
					.getSumOfAccountValueForStateFee());
		else
			sumFromPage = Double.parseDouble(addWidget
					.getSumOfAccountValueForTransFee());
		if (Double.parseDouble(sum) != sumFromPage) {
			throw new ErrorOnPageException("Sum of "
					+ (isStateFee ? "state fee " : "Transaction fee ")
					+ " account values should be " + sum);
		}

		addWidget.clickCancel();
		ajax.waitLoading();

	}

	/**
	 * set pricing info, work flow start from Product pricing page,end in add
	 * pricing widget.
	 * 
	 * @param pricing
	 * @param page
	 */
	protected void setPricingInfo(PricingInfo pricing,
			ILicMgrProductPricingPage page) {
		LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget
				.getInstance();
		logger.info("set pricing info ");
		if (!addWidget.exists()) {
			page.clickAddProductPricing();
			addWidget.waitLoading();
		}
		addWidget.setPricingInfo(pricing);
	}

	protected void verifyEffectiveDate(PricingInfo pricing, String expectMsg,
			boolean isFrom) {
		LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget
				.getInstance();

		if (!addWidget.exists()) {
			pricingPage.clickAddProductPricing();
			addWidget.waitLoading();
		}

		logger.info("verify Invaild Effective " + (isFrom ? "From" : "To")
				+ " date");
		try {
			count++;
			if (isFrom) {
				addWidget.verifyEffectiveFromDateInValid(expectMsg);
			} else {
				addWidget.verifyEffectiveToDateInvalid(expectMsg);
			}
		} catch (Exception e) {
			list.add(e);
		}
		logger.info("Verify successfully");
		addWidget.clickCancel();
		ajax.waitLoading();
	}

	/**
	 * verify this record is not existing
	 * 
	 * @param pricing
	 */
	protected void verifyCancel(PricingInfo pricing) {
		logger.info("Verify cancel");

		pricingPage.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		pricingPage.selectStatus("");
		pricingPage.selectLocationClass(pricing.locationClass);
		pricingPage.clickGo();
		ajax.waitLoading();
		
		if (pricingPage.getRowCount() > 1
				&& pricingPage.checkPricingRecordExists(pricing)) {
			throw new ErrorOnPageException("There should no any record");
		}
		logger.info("Verify 'Cancel' successfully");
	}
	
	protected String getTranslationForLicenseFiscalYear(String schema){
		
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		String query="SELECT LABEL_VALUE FROM X_Translation WHERE LABEL_KEY='translatable.licensefiscalyear'";
		db.resetSchema(schema);
		String value=db.executeQuery(query, "LABEL_VALUE", 0).split(" ")[0].trim();
		return value;
	}
}
