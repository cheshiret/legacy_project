package com.activenetwork.qa.awo.testcases.abstractcases;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @Description: This case is the common case for EditProductPricing and
 *               VerifyEditingBusinessRule cases, it includes the main work flow
 *               and verification point
 * @Preconditions:
 * @SPEC: <<Edit Product Pricing.doc>> and <<Add Product Pricing.doc>>
 * @Task#:AUTO-667
 * 
 * @author qchen
 * @Date Jul 21, 2011
 */
public abstract class LicMgrEditProductPricingTestCase extends
		LicenseManagerTestCase {
	private String licenseFiscalYear;
	
	protected PricingInfo pricing, existingPricing;
	protected ILicMgrProductPricingPage productPricingPage = null;
	protected LicMgrEditProductPricingWidget editPricingWidget = LicMgrEditProductPricingWidget
			.getInstance();
	protected String effectiveFromDateBlank_expectedMsg = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
	protected String EffectiveToDateLessThanFrom_expectedMsg = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
	protected String moreThanOnceSpecifiedSameAccountForStateFee_expectedMsg = "Duplicate Accounts for the State Fee Fund Splits is not allowed.";
	protected String stateFeeAccountDoesntHaveValue_expectedMsg = "An Account for the State Fee Fund Splits does not have its corresponding value. Please specify the value in the field provided.";
	protected String stateFeeSplitByIsnt0to100Numeric_expectedMsg = "A Percentage Fund Split value entered for State Fee is not valid. Please enter a numeric value between 0.00% and 100.00%.";
	protected String moreThanOnceSpecifiedSameAccountForTransFee_expectedMsg = "Duplicate Accounts for the Transaction Fee Fund Splits is not allowed.";
	protected String transFeeAccountDoesntHaveValue_expectedMsg = "An Account for the Transaction Fee Fund Splits does not have its corresponding value. Please specify the value in the field provided.";
	protected String transFeeSplitByIsnt0to100Numeric_expectedMsg = "A Percentage Fund Split value entered for Transaction Fee is not valid. Please enter a numeric value between 0.00% and 100.00%.";
	protected String existingActiveRecord_expectedMsg,
			existingActiveNotAllLicYearFrom_expectedMsg,
			existingActiveAllLicYearFrom_expectedMsg,
			existingActiveOverlapFromAndTo_expectedMsg = "";
	protected String actualMsg, expectedUpdateTime, expectedNewAddTime = "";
	protected boolean CANCEL_AND_SUCCESS = false;
	protected boolean VERIFY_INVALID_ENTRIES = false;
	protected boolean VERIFY_BUSINESS_RULE = false;
	protected boolean VERIFY_EDIT_CONFLICT_WITH_EXISTING_ACTIVE_RECORD = false;
	protected boolean VERIFY_EDIT_LICYEAR_ALL_CONFLICT_WITH_EXISTING_LICYEAR_NOTALL_ACTIVE_RECORD = false;
	protected boolean VERIFY_EDIT_LICYEAR_NOTALL_CONFLICT_WITH_EXISTING_LICYEAR_ALL_ACTIVE_RECORD = false;
	protected boolean VERIFY_EDIT_LICYEAR_NOTALL_OVERLAP_CONFLICT_WITH_EXISTING_LICYEAR_ACTIVE_RECORD = false;
	protected boolean VERIFY_STATUS_CHANGEMENT = false;
	private boolean runningResult = true;
	protected VehicleRTI vehicle = new VehicleRTI();

	protected LicMgrEditProductPricingTestCase() {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	}

	@Override
	public void execute() {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		licenseFiscalYear=this.getTranslationForLicenseFiscalYear(schema);
		
		lm.loginLicenseManager(login);
		LicMgrProductPage prodPage = lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		
		checkAndAddProduct(prodPage);

		lm.gotoProductPricingPageFromListPage(pricing.prodType,
				pricing.prodCode);

		// clean up environment
		this.cleanUpEnvironment();

		// create a product pricing
		pricing.id = lm.addPricingForProduct(pricing, productPricingPage, true);

		if (CANCEL_AND_SUCCESS) {
			runningResult &= this.verifyEditingCancellation();
			runningResult &= this.verifyEditingSuccess();
		}
		if (VERIFY_INVALID_ENTRIES) {
			runningResult &= this.verifyEntriesIsValid();
		}
		if (VERIFY_BUSINESS_RULE) {
			runningResult &= this.verifyBusinessRule();
		}
		if (VERIFY_EDIT_CONFLICT_WITH_EXISTING_ACTIVE_RECORD) {
			runningResult &= this.editConflictWithExistingActiveRecord();
		}
		if (VERIFY_EDIT_LICYEAR_ALL_CONFLICT_WITH_EXISTING_LICYEAR_NOTALL_ACTIVE_RECORD) {
			runningResult &= this
					.editLicenseYearFromIsAllConflictWithExistingNotAllActiveRecord();
		}
		if (VERIFY_EDIT_LICYEAR_NOTALL_CONFLICT_WITH_EXISTING_LICYEAR_ALL_ACTIVE_RECORD) {
			runningResult &= this
					.editLicenseYearFromIsNotAllConflictWithExistingAllActiveRecord();
		}
		if (VERIFY_EDIT_LICYEAR_NOTALL_OVERLAP_CONFLICT_WITH_EXISTING_LICYEAR_ACTIVE_RECORD) {
			runningResult &= this
					.editLicenseYearFromIsNotAllConflictWithExistingOverlapLicenseYearFromAndToActiveRecord();
		}
		if (VERIFY_STATUS_CHANGEMENT) {
			runningResult &= this.verifyStatusChangement();
		}

		// verify the running result
		if (!runningResult) {
			throw new ErrorOnPageException(
					"The checkpoints are NOT all passed. Please refer log for the detail info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}
		
		// clean up environment
		this.cleanUpEnvironment();
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
				newSupplyProduct();
				lm.addSupplyProudct(supply);
			}
		}
	}
	
	private void newPrivilegeProduct() {
		privilege.code = pricing.prodCode;
		privilege.name = "Privilege " + privilege.code;
		privilege.customerClasses = new String[]{"Individual"};
	}
	
	private void newVehicleProduct() {
		vehicle.setPrdCode(pricing.prodCode);
		vehicle.setPrdName("Vehicle" + pricing.prodCode);
		vehicle.setPrdGroup("Registration");
		vehicle.setVehicleType("Boat");
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		vehicle.setCustClass(custClass);
		vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicle.setValidMonths("2");
		vehicle.setAdvanceRenewalDays("1");
		vehicle.setLateRenewal("1");
	    vehicle.setLateRenewUnit("Day");
	    HashMap<String, Boolean> boatUseTyp = new HashMap<String, Boolean>();
	    boatUseTyp.put("Personal Pleasure", true);
	    vehicle.setBoatUseTyp(boatUseTyp);
	    vehicle.setMinLenthOfFt("7");
	    vehicle.setMinLenthOfIn("5");//update by pzhu
	    vehicle.setMaxLenthOfFt("20");	  
	    vehicle.setMaxLenthOfIn("14");
	}
	
	private void newConsumableProduct() {
		consumable.code = pricing.prodCode;
		consumable.name = "TestForEditProductPricingTest";
		consumable.description = "TestForEditProductPricingTest";
		consumable.orderType = "POS Sale";
		consumable.productGroup = "other";		
	}
	
	private void newSupplyProduct() {
		supply.code = pricing.prodCode;
		supply.name = "Supply" + pricing.prodCode;
		supply.status = ACTIVE_STATUS;
		supply.description = "Supply"+DateFunctions.getCurrentTime();
		supply.productGroup = "Supply Items";
		supply.availableToApp.put("All", true);
		supply.fulfillmentParty = "FulfillmentParty Internal";
		supply.supplyCost = "4.2";
		supply.shippingCost = "3.5";
		supply.maxDailyOrder = "10";
		supply.recorderThreshold = "3";
		supply.recorderMail = lm.getNextEmail();
		supply.qtyOnHand = "2";
		
	}
	
	protected void cleanUpEnvironment() {

		productPricingPage
				.searchPricingRecords("Active", pricing.locationClass);

		List<String> pricingIds = productPricingPage.getPricingIDs();

		if (pricingIds.size() > 0) {
			for (int i = 0; i < pricingIds.size(); i++) {
				lm.deactivateProductPricing(pricingIds.get(i),
						productPricingPage);
			}
		}
	}

	/**
	 * Verify system displays the correct error message when user enter an
	 * invalid entry
	 * 
	 * @return
	 */
	private boolean verifyEntriesIsValid() {
		boolean result = true;
		/**
		 * System verifies that all the entries are invalid
		 */
		lm.gotoEditProductPricingWidget(pricing.id, productPricingPage);

		// 1. Effective From Date is left blank
		resetPricingInfo();
		pricing.effectFrom = "";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				effectiveFromDateBlank_expectedMsg);

		// 2. Effective From Date entry is not a valid date
		lm.gotoEditProductPricingWidget(pricing.id, productPricingPage);
		result &= editPricingWidget.verifyFromDate(new String[] { "2015-12-32",
				"automation" });

		// 3. Effective To Date entry is specified but is not a valid date
		result &= editPricingWidget.verifyToDate(new String[] { "2015-13-01",
				"@quentinic" });
		editPricingWidget.clickCancel();
		ajax.waitLoading();

		// 4. Effective To Date entry is specified but entry for Effective From
		// Date is greater than the entry for Effective To Date
		resetPricingInfo();
		pricing.effectTo = DateFunctions.getDateAfterGivenDay(
				pricing.effectFrom, -2);
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				EffectiveToDateLessThanFrom_expectedMsg);

		// 5. The same Account is specified more than once for the State Fee
		// Fund Splits
		resetPricingInfo();
		pricing.stateFee_accounts.get(1)[0] = pricing.stateFee_accounts.get(0)[0];// set
																					// the
																					// 2nd
																					// account
																					// info
																					// as
																					// the
																					// same
																					// with
																					// 1st
																					// one
		pricing.stateFee_accounts.get(0)[1] = "50";
		pricing.stateFee_accounts.get(1)[1] = "50";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				moreThanOnceSpecifiedSameAccountForStateFee_expectedMsg);

		// 6. A specified Account does not have a corresponding value for the
		// State Fee Fund Splits
		resetPricingInfo();
		pricing.stateFee_accounts.get(1)[0] = "Standard Campsite - Revenue";
		pricing.stateFee_accounts.get(0)[1] = "0";
		pricing.stateFee_accounts.get(1)[1] = "100";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				stateFeeAccountDoesntHaveValue_expectedMsg);

		// 7. A State Fee Fund Split by Percentage value is not a numeric value
		// greater than or equal to 0.00 and less than or equal to 100.00
		resetPricingInfo();
		pricing.stateFee_accounts.get(0)[1] = "101";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				stateFeeSplitByIsnt0to100Numeric_expectedMsg);

		// 8. Select more than 1 State Fee accounts to split by, change the 1st
		// amount of the fields, verify that sum of percentage is always 100%
		resetPricingInfo();
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_accounts.get(0)[1] = "999";
		lm.editProductPricing(pricing, productPricingPage);
		String sum = editPricingWidget.getSumOfAccountValueForStateFee();
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifySumOfAccountValue(sum, "100.0");

		// 9. Select more than 1 State Fee accounts to split by, change the 1st
		// amount of the fields, verify that sum of amount equals to the value
		// set in State Fee field
		resetPricingInfo();
		pricing.stateFee_SplitBy = "Amount";
		pricing.stateFee_accounts.get(0)[1] = "50";
		lm.editProductPricing(pricing, productPricingPage);
		sum = editPricingWidget.getSumOfAccountValueForStateFee();
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifySumOfAccountValue(sum, pricing.stateFee);

		// 10. The same Account is specified more than once for the Transaction
		// Fee Fund Splits
		resetPricingInfo();
		pricing.transFee_accounts.get(1)[0] = pricing.transFee_accounts.get(0)[0];// set
																					// the
																					// 2nd
																					// account
																					// info
																					// as
																					// the
																					// same
																					// with
																					// 1st
																					// one
		pricing.transFee_accounts.get(0)[1] = "50";
		pricing.transFee_accounts.get(1)[1] = "50";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				moreThanOnceSpecifiedSameAccountForTransFee_expectedMsg);

		// 11. A specified Account does not have a corresponding value for the
		// Transaction Fee Fund Splits
		resetPricingInfo();
		pricing.transFee_accounts.get(0)[1] = "0";
		pricing.transFee_accounts.get(1)[1] = "100";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				transFeeAccountDoesntHaveValue_expectedMsg);

		// 12. A Transaction Fee Fund Split by Percentage value is not a numeric
		// value greater than or equal to 0.00 and less than or equal to 100.00
		resetPricingInfo();
		pricing.transFee_accounts.get(0)[1] = "-10";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				transFeeSplitByIsnt0to100Numeric_expectedMsg);

		// 13. Select more than 1 Transaction Fee accounts to split by, change
		// the 1st amount of the fields, verify that sum of percentage is always
		// 100%
		resetPricingInfo();
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_accounts.get(0)[1] = "999";
		lm.editProductPricing(pricing, productPricingPage);
		sum = editPricingWidget.getSumOfAccountValueForTransFee();
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifySumOfAccountValue(sum, "100.0");

		// 14. Select more than 1 Transaction Fee accounts to split by, change
		// the 1st amount of the fields, verify that sum of amount equals to the
		// value set in Transaction Fee field
		resetPricingInfo();
		pricing.transFee_SplitBy = "Amount";
		pricing.transFee_accounts.get(0)[1] = "50";
		lm.editProductPricing(pricing, productPricingPage);
		sum = editPricingWidget.getSumOfAccountValueForTransFee();
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifySumOfAccountValue(sum, pricing.transFee);

		return result;
	}

	private boolean editConflictWithExistingActiveRecord() {
		boolean result = true;
		// There is already an existing Product Pricing record for the same
		// Product that is also "Active" and that has the same values as this
		// record
		// for the following: Location Class, License Year/Fiscal Year From, and
		// (if applicable) Purchase Type
		// a. add a existing pricing record as pre condition
		existingPricing.id = lm.addPricingForProduct(existingPricing,
				productPricingPage, true);

		if ("Consumable".equals(pricing.prodType)) {

			existingActiveRecord_expectedMsg = "Another active Product Pricing record "
					+ existingPricing.id
					+ " already exists for the same Product with the same Location Class and "
					+ licenseFiscalYear
					+ " Year From. Duplicate active records are not allowed.";

		} else if ("Privilege".equals(pricing.prodType)
				|| "Vehicle".equals(pricing.prodType)) {

			existingActiveRecord_expectedMsg = "Another active Product Pricing record "
					+ existingPricing.id
					+ " already exists for the same Product with the same Location Class, "
					+ licenseFiscalYear
//					+ " Year From, and at least one of the same State/Province. Duplicate active records are not allowed."; 
					+ " Year From and Purchase Type. Duplicate active records are not allowed.";
		} else if("Supply".equals(pricing.prodType)) {
			existingActiveRecord_expectedMsg = "Another active Product Pricing record "
				+ existingPricing.id
				+ " already exists for the same Product with the same Location Class, "
				+ licenseFiscalYear
				+ " Year From . Duplicate active records are not allowed.";
		}

		// b. edit the pricing has the same values as above
		resetPricingInfo();
		pricing.licYearFrom = existingPricing.licYearFrom;
		pricing.licYearTo = existingPricing.licYearTo;
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				existingActiveRecord_expectedMsg);

		return result;
	}

	private boolean editLicenseYearFromIsAllConflictWithExistingNotAllActiveRecord() {
		boolean result = true;
		// The specified License/Fiscal Year From is "All" and there is an
		// existing Product Pricing record for the same Product that is also
		// "Active"
		// and where the License/Fiscal Year From is not "All" and has the same
		// values as this record for the following: Location Class and (if
		// applicable) Purchase Type
		// a. add a existing pricing record as pre condition
		existingPricing.id = lm.addPricingForProduct(existingPricing,
				productPricingPage, true);

		if ("Consumable".equals(pricing.prodType)
				|| "Supply".equals(pricing.prodType)) {

			existingActiveNotAllLicYearFrom_expectedMsg = "Another active Product Pricing record "
					+ existingPricing.id
					+ " already exists for the same Product with the same Location Class, and with overlapping "
					+ licenseFiscalYear
					+ " Year From and To entries. Please inactivate that record first or modify the "
					+ licenseFiscalYear
					+ " Year Range of that record first to eliminate the overlap.";

		} else if ("Privilege".equals(pricing.prodType)
				|| "Vehicle".equals(pricing.prodType)) {

			existingActiveNotAllLicYearFrom_expectedMsg = "Another active Product Pricing record "
					+ existingPricing.id
//					+ " already exists for the same Product with the same Location Class and Purchase Type, and at least one of the same State/Province, and with overlapping "
					+ " already exists for the same Product with the same Location Class and Purchase Type, and with overlapping "
					+ licenseFiscalYear
					+ " Year From and To entries. Please inactivate that record first or modify the "
					+ licenseFiscalYear
					+ " Year Range of that record first to eliminate the overlap.";

		}

		// b. edit the pricing has the same values as above
		resetPricingInfo();
		pricing.licYearFrom = "All";
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		result &= this.verifyErrorMsg(actualMsg,
				existingActiveNotAllLicYearFrom_expectedMsg);

		return result;
	}

	private boolean editLicenseYearFromIsNotAllConflictWithExistingAllActiveRecord() {
		boolean result = true;
		// The specified License/Fiscal Year From is not "All" and there is an
		// existing Product Pricing record for the same Product that is also
		// "Active"
		// and where the License/Fiscal Year From is "All" and has the same
		// values as this record for the following: Location Class and (if
		// applicable) Purchase Type
		// a. add an existing pricing set its License Year From as "All"
		existingPricing.id = lm.addPricingForProduct(existingPricing,
				productPricingPage, true);

		// b. edit the pricing has the same values as above
		existingPricing.licYearFrom = "All";
		actualMsg = lm.editProductPricing(existingPricing, productPricingPage);
		expectedUpdateTime = String.valueOf(DateFunctions.getCurrentTime());
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		
		if ("Consumable".equals(pricing.prodType)
				|| "Supply".equals(pricing.prodType)) {

			existingActiveAllLicYearFrom_expectedMsg = "Another active Product Pricing record "
					+ pricing.id
					+ " already exists for the same Product with the same Location Class, and with overlapping "
					+ licenseFiscalYear
					+ " Year From and To entries. Please inactivate that record first or modify the "
					+ licenseFiscalYear
					+ " Year Range of that record first to eliminate the overlap.";

		} else if ("Privilege".equals(pricing.prodType)
				|| "Vehicle".equals(pricing.prodType)) {

			existingActiveAllLicYearFrom_expectedMsg = "Another active Product Pricing record "
					+ pricing.id
					+ " already exists for the same Product with the same Location Class and Purchase Type, and with overlapping "
					+ licenseFiscalYear
					+ " Year From and To entries. Please inactivate that record first or modify the "
					+ licenseFiscalYear
					+ " Year Range of that record first to eliminate the overlap.";

		}
		
		result &= this.verifyErrorMsg(actualMsg,
				existingActiveAllLicYearFrom_expectedMsg);

		return result;
	}

	private boolean editLicenseYearFromIsNotAllConflictWithExistingOverlapLicenseYearFromAndToActiveRecord() {
		boolean result = true;
		// The specified License/Fiscal Year From is not "All" and the specified
		// License/Fiscal Year and To entries overlap with the License/Fiscal
		// Year From and To entries
		// in an existing Product Pricing record for the same Product that is
		// also "Active" and has the same values as this record for the follow:
		// Location Class and (if applicable) Purchase Type
		// a. add an existing pricing set its License Year From and License Year
		// To
		existingPricing.id = lm.addPricingForProduct(existingPricing,
				productPricingPage, true);

		// b. edit the pricing has the same values as above and the license year
		// from and to is overlap with existing one's
		resetPricingInfo();
		pricing.licYearTo = existingPricing.licYearTo;
		actualMsg = lm.editProductPricing(pricing, productPricingPage);
		editPricingWidget.clickCancel();
		ajax.waitLoading();

		if ("Consumable".equals(pricing.prodType)
				|| "Supply".equals(pricing.prodType)) {

			existingActiveOverlapFromAndTo_expectedMsg = "Another active Product Pricing record "
					+ existingPricing.id
					+ " already exists for the same Product with the same Location Class, and with overlapping "
					+ licenseFiscalYear
					+ " Year From and To entries. Please inactivate that record first or modify the "
					+ licenseFiscalYear
					+ " Year Range of that record first to eliminate the overlap.";

		} else if ("Privilege".equals(pricing.prodType)
				|| "Vehicle".equals(pricing.prodType)) {

			existingActiveOverlapFromAndTo_expectedMsg = "Another active Product Pricing record "
					+ existingPricing.id
					+ " already exists for the same Product with the same Location Class and Purchase Type, and with overlapping "
					+ licenseFiscalYear
					+ " Year From and To entries. Please inactivate that record first or modify the "
					+ licenseFiscalYear
					+ " Year Range of that record first to eliminate the overlap.";

		}

		// existingActiveOverlapFromAndTo_expectedMsg =
		// "Another active Product Pricing record " + existingPricing.id +
		// " already exists for the same Product with the same Location Class and Purchase Type, and with overlapping Fiscal Year From and To entries. Please inactivate that record first or modify the Fiscal Year Range of that record first to eliminate the overlap.";
		result &= this.verifyErrorMsg(actualMsg,
				existingActiveOverlapFromAndTo_expectedMsg);

		return result;
	}

	private boolean verifyStatusChangement() {
		boolean result = true;
		/**
		 * System verifies that either changes were made not just to the Status
		 * or only the Status was changed to "Active"
		 */
		// Only the Status was changed to "Inactive"
		pricing.status = "Inactive";
		lm.editProductPricing(pricing, true, productPricingPage);
		pricing = lm.getProductPricingInfo(pricing.id, productPricingPage);
		result &= this.verifyPricingAddUpdateInfo(pricing);

		// The Status of the Product Pricing record prior to the changes was
		// "Inactive"
		// a. update product pricing record from Inactive to Active
		String tempID = pricing.id;
		resetPricingInfo();
		pricing.status = "Active";
		pricing.id = lm.editProductPricing(pricing, true, productPricingPage);
		expectedNewAddTime = String.valueOf(DateFunctions.getCurrentTime());
		// b. verify the old record still exists
		PricingInfo oldPricing = lm.getProductPricingInfo(tempID,
				productPricingPage);
		result &= this.verifyPricingAddUpdateInfo(oldPricing);
		// c. verify system adds a new Product Pricing with information based on
		// the old one
		PricingInfo newPricing = lm.getProductPricingInfo(pricing.id,
				productPricingPage);
		result &= this.verifyPricingInfo(oldPricing, newPricing);

		return result;
	}

	/**
	 * Verify Product Pricing ID, Location Class and Purchase Type fields are
	 * not editable in Edit Product Pricing widget
	 * 
	 * @param productPricingPage
	 * @return
	 */
	private boolean verifyNotEditable(
			ILicMgrProductPricingPage productPricingPage) {
		logger.info("Verify Product Pricing ID, Location Class and Purchase Type shall not be editable in edit widget.");
		boolean result = true;
		if (editPricingWidget.checkPricingIDEditable()) {
			result &= false;
			logger.error("Pricing ID shall NOT be editable.");
		}
		if (editPricingWidget.checkLocationClassEditable()) {
			result &= false;
			logger.error("Location Class shall NOT be editable.");
		}
		if (editPricingWidget.isPurchaseTypeExists()) {
			if (editPricingWidget.checkPurchaseTypeEditable()) {
				result &= false;
				logger.error("Purchase Type shall NOT be editable.");
			}
		}

		return result;
	}

	/**
	 * Verify whether the actual message is the same with the expected
	 * 
	 * @param actualMsg
	 * @param expectedMsg
	 * @return
	 */
	private boolean verifyErrorMsg(String actualMsg, String expectedMsg) {
		logger.info("Verify the error message displayed at Edit HF Product Pricing Widget correctly.");
		//updated by pzhu 2012/12/14 delete any blank space and change to uppercase for compare.
		if (!(actualMsg.replaceAll(" and ", "").replaceAll(",", "").replaceAll("\\.", "").replaceAll("\\s", "").toUpperCase()).equalsIgnoreCase(expectedMsg.replaceAll(" and ", "").replaceAll(",", "").replaceAll("\\.", "").replaceAll("\\s", "").toUpperCase())) {
			logger.error("Actual error message doesn't match the expected. The expected is:'"
					+ expectedMsg
					+ "', but the actual is: '"
					+ actualMsg
					+ "'.");
			return false;
		} else {
			logger.info("Error message -'" + actualMsg
					+ "' is displayed correctly.");
			return true;
		}
	}

	/**
	 * Verify whether the sum of accounts values is correct
	 * 
	 * @param actualSum
	 * @param expectedSum
	 * @return
	 */
	private boolean verifySumOfAccountValue(String actualSum, String expectedSum) {
		boolean result = true;
		logger.info("Verify the actual sum of account values is correct.");
		if (Double.compare(Double.parseDouble(actualSum),
				Double.parseDouble(expectedSum)) != 0) {
			logger.error("Actual sum of account values doesn't equal to expected. The expected sum is: "
					+ expectedSum + ", but the actual is: " + actualSum + ".");
			result = false;
		} else {
			logger.info("Sum of State Fee/Transaction Fee accounts value is correct.");
		}

		return result;
	}

	protected abstract void resetPricingInfo();

	/**
	 * Verify the pricing updated information match the expected
	 * 
	 * @param pricing
	 * @return
	 */
	protected boolean verifyPricingAddUpdateInfo(PricingInfo pricing) {
		boolean result = true;
		if (!pricing.status.equalsIgnoreCase("Inactive")) {
			result &= false;
			logger.error("Status is NOT correct.");
		}
		if (!pricing.lastUpdateUser.replaceAll(" ", StringUtil.EMPTY).equalsIgnoreCase(DataBaseFunctions
				.getLoginUserName(login.userName).replaceAll(" ", StringUtil.EMPTY))) {
			result &= false;
			logger.error("Last Update User is NOT correct.");
		}
		if (!pricing.lastUpdateLocation.equalsIgnoreCase(login.location
				.split("/")[1].trim())) {
			result &= false;
			logger.error("Last Update Location is NOT correct.");
		}
		SimpleDateFormat formatter = new SimpleDateFormat(
				"E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		if(StringUtil.notEmpty(pricing.lastUpdateTime)){
			try {
				// format the creation date/time from UI to the same form with value
				// generated in case
					actualDate = formatter.parse(pricing.lastUpdateTime);
			} catch (ParseException e) {
				throw new ErrorOnDataException(e);
			}
	
			// allow there is 5 minutes relative errors between creation date/time
			// from UI and from case
			if ((Long.parseLong(expectedUpdateTime) - actualDate.getTime()) / 1000 > 300) {
				result &= false;
				logger.error("Last Update Time is NOT correct.");
			}
		} else {
			if(!MiscFunctions.compareResult("Last Update Date/Time", expectedUpdateTime, pricing.lastUpdateTime)){
				result &= false;
			}
		}

		return result;
	}

	/**
	 * Verify the 2 pricing detail information match
	 * 
	 * @param oldPricing
	 * @param newPricing
	 * @return
	 */
	protected boolean verifyPricingInfo(PricingInfo oldPricing,
			PricingInfo newPricing) {
		boolean result = true;
		if (!oldPricing.locationClass
				.equalsIgnoreCase(newPricing.locationClass)) {
			result &= false;
			logger.error("Pricing Location Class doesn't match.");
			logger.error("Expected value is:"+oldPricing.locationClass+", and actual value is:"+newPricing.locationClass);
		}
		if (!oldPricing.licYearFrom.equalsIgnoreCase(newPricing.licYearFrom)) {
			result &= false;
			logger.error("Pricing License Year From doesn't match.");
			logger.error("Expected value is:"+oldPricing.licYearFrom+", and actual value is:"+newPricing.licYearFrom);
		}
		if (!oldPricing.licYearTo.equalsIgnoreCase(newPricing.licYearTo)) {
			result &= false;
			logger.error("Pricing License Year To doesn't match.");
			logger.error("Expected value is:"+oldPricing.licYearTo+", and actual value is:"+newPricing.licYearTo);
		}
		if (oldPricing.prodType.matches("(?i)privilege|(?i)Vehicle")){
			//Only for Privilege|Vehicle have purchase type
			if (!oldPricing.purchaseType.equalsIgnoreCase(newPricing.purchaseType)) {
				result &= false;
				logger.error("Pricing Purchase Type doesn't match.");
				logger.error("Expected value is:"+oldPricing.purchaseType+", and actual value is:"+newPricing.purchaseType);
			}
		}
		if ("" != oldPricing.effectFrom
			&& "" != newPricing.effectFrom
			&&	DateFunctions.compareDates(
				DateFunctions.formatDate(oldPricing.effectFrom),
				DateFunctions.formatDate(newPricing.effectFrom)) != 0) {
			result &= false;
			logger.error("Pricing Effective From Date doesn't match.");
			logger.error("Expected value is:"+oldPricing.effectFrom+", and actual value is:"+newPricing.effectFrom);
		}
		if ("" != oldPricing.effectTo
			&& "" != newPricing.effectTo
			&&	DateFunctions.compareDates(
				DateFunctions.formatDate(oldPricing.effectTo),
				DateFunctions.formatDate(newPricing.effectTo)) != 0) {
			result &= false;
			logger.error("Pricing Effective To Date doesn't match.");
			logger.error("Expected value is:"+oldPricing.effectTo+", and actual value is:"+newPricing.effectTo);
		}
		if (new BigDecimal(oldPricing.vendorFee).compareTo(new BigDecimal(newPricing.vendorFee)) != 0) {
			result &= false;
			logger.error("Pricing Vendor Fee doesn't match.");
			logger.error("Expected value is:"+oldPricing.vendorFee+", and actual value is:"+newPricing.vendorFee);
		}

		if (new BigDecimal(oldPricing.stateTransFee).compareTo(new BigDecimal(newPricing.stateTransFee)) != 0) {
			result &= false;
			logger.error("Pricing State Trans Fee doesn't match.");
			logger.error("Expected value is:"+oldPricing.stateTransFee+", and actual value is:"+newPricing.stateTransFee);
		}
		
		if (new BigDecimal(oldPricing.stateFee).compareTo(new BigDecimal(newPricing.stateFee)) != 0) {
			result &= false;
			logger.error("Pricing State Fee doesn't match.");
			logger.error("Expected value is:"+oldPricing.stateFee+", and actual value is:"+newPricing.stateFee);
		}

		if (!oldPricing.prodType.matches("(?i)Vehicle")) {
			if (!oldPricing.stateFee_SplitBy
					.equalsIgnoreCase(newPricing.stateFee_SplitBy)) {
				result &= false;
				logger.error("Pricing State Fee Split By doesn't match.");
				logger.error("Expected value is:"+oldPricing.stateFee_SplitBy+", and actual value is:"+newPricing.stateFee_SplitBy);
			}
		}
		if (!oldPricing.stateFee_SplitInto
				.equalsIgnoreCase(newPricing.stateFee_SplitInto)) {
			result &= false;
			logger.error("Pricing State Fee Split Into doesn't match.");
			logger.error("Expected value is:"+oldPricing.stateFee_SplitInto+", and actual value is:"+newPricing.stateFee_SplitInto);
		}
		for (int i = 0; i < oldPricing.stateFee_accounts.size(); i++) {
			if (!oldPricing.stateFee_accounts.get(i)[0].
					equals(newPricing.stateFee_accounts.get(i)[0])) {
				result &= false;
				logger.error("Pricing State Fee Account doesn't match.");
				logger.error("Expected value is:"+oldPricing.stateFee_accounts.get(i)[0]+", and actual value is:"+newPricing.stateFee_accounts.get(i)[0]);
			}
			
			String oldAccount = oldPricing.stateFee_accounts.get(i)[1];
			String newAccount = newPricing.stateFee_accounts.get(i)[1];
			if (new BigDecimal(oldAccount).
					compareTo(new BigDecimal(newAccount)) != 0) {
				result &= false;
				logger.error("Pricing State Fee Account Value doesn't match.");
				logger.error("Expected value is:"+oldPricing.stateFee_accounts.get(i)[1]+", and actual value is:"+newPricing.stateFee_accounts.get(i)[1]);
			}
		}
		
		
		if (new BigDecimal(oldPricing.transFee).compareTo(new BigDecimal(newPricing.transFee)) != 0) {
			result &= false;
			logger.error("Pricing Transaction Fee doesn't match.");
			logger.error("Expected value is:"+oldPricing.transFee+", and actual value is:"+newPricing.transFee);
		}
		if (!oldPricing.prodType.matches("(?i)Vehicle")) {
			if (!oldPricing.transFee_SplitBy
					.equalsIgnoreCase(newPricing.transFee_SplitBy)) {
				result &= false;
				logger.error("Pricing Transaction Fee Split By doesn't match.");
				logger.error("Expected value is:"+oldPricing.transFee_SplitBy+", and actual value is:"+newPricing.transFee_SplitBy);
			}
		}
		if (!oldPricing.transFee_SplitInto
				.equalsIgnoreCase(newPricing.transFee_SplitInto)) {
			result &= false;
			logger.error("Pricing Transaction Fee Split Into doesn't match.");
			logger.error("Expected value is:"+oldPricing.transFee_SplitInto+", and actual value is:"+newPricing.transFee_SplitInto);
		}
		for (int i = 0; i < oldPricing.transFee_accounts.size(); i++) {			
			if (!oldPricing.transFee_accounts.get(i)[0].equals(newPricing.transFee_accounts.get(i)[0])) {
				result &= false;
				logger.error("Pricing Transaction Fee Account doesn't match.");
				logger.error("Expected value is:"+oldPricing.transFee_accounts.get(i)[0]+", and actual value is:"+newPricing.transFee_accounts.get(i)[0]);
			}
			String oldAccount = oldPricing.transFee_accounts.get(i)[1];
			String newAccount = newPricing.transFee_accounts.get(i)[1];
			if (new BigDecimal(oldAccount).
					compareTo(new BigDecimal(newAccount)) != 0) {
				result &= false;
				logger.error("Pricing Transaction Fee Account Value doesn't match.");
				logger.error("Expected value is:"+oldPricing.transFee_accounts.get(i)[1]+", and actual value is:"+newPricing.transFee_accounts.get(i)[1]);
			}
		}
		if (!oldPricing.createUser.replaceAll("\\s+", StringUtil.EMPTY).equalsIgnoreCase(newPricing.createUser.replaceAll("\\s+", StringUtil.EMPTY))) {
			result &= false;
			logger.error("Pricing Create User doesn't match.");
			logger.error("Expected value is:"+oldPricing.createUser+", and actual value is:"+newPricing.createUser);
		}
		if (!oldPricing.createLocation
				.equalsIgnoreCase(newPricing.createLocation)) {
			result &= false;
			logger.error("Pricing Create Location doesn't match.");
			logger.error("Expected value is:"+oldPricing.createLocation+", and actual value is:"+newPricing.createLocation);
		}
		SimpleDateFormat formatter = new SimpleDateFormat(
				"E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			// format the creation date/time from UI to the same form with value
			// generated in case
			actualDate = formatter.parse(newPricing.createTime);
		} catch (ParseException e) {
			throw new ErrorOnDataException(e);
		}

		// allow there is 10 minutes relative errors between creation date/time
		// from UI and from case
		if ((Long.parseLong(expectedNewAddTime) - actualDate.getTime()) / 1000 > 600) {
			result &= false;
			logger.error("Pricing Create Time doesn't match.");
			logger.error("Expected value is:"+expectedNewAddTime+", and actual value is:"+actualDate.getTime());
		}

		return result;
	}

	/**
	 * Verify system proceed the edit process when user confirms
	 * 
	 * @return
	 */
	private boolean verifyEditingSuccess() {
		/**
		 * User confirms their entries and desire to proceed in saving the
		 * changes made to the Product Pricing record, user proceeds
		 */
		boolean result = true;
		lm.gotoEditProductPricingWidget(pricing.id, productPricingPage);
		pricing.status = "Inactive";
		lm.editProductPricing(pricing, true, productPricingPage);
		// productPricingPage.uncheckShowCurrentRecordsOnly();
		productPricingPage.selectStatus("Inactive");
		productPricingPage.clickGo();
		ajax.waitLoading();
		if (!productPricingPage.checkPricingRecordExists(pricing)) {
			result = false;
			logger.error("The pricing should be updated because user proceeds.");
		}
		logger.info("Editing product pricing process succeeds.");

		return result;
	}

	/**
	 * Verify system abort the edit process when user cancels
	 * 
	 * @return
	 */
	private boolean verifyEditingCancellation() {
		/**
		 * User confirms their entries and desire to proceed in saving the
		 * changes made to the Product Pricing record, user cancels
		 */
		boolean result = true;
		lm.gotoEditProductPricingWidget(pricing.id, productPricingPage);
		pricing.status = "Inactive";
		lm.editProductPricing(pricing, false, productPricingPage);
		resetPricingInfo();
		if (!productPricingPage.checkPricingRecordExists(pricing)) {
			result = false;
			logger.error("The pricing should NOT be updated because user cancels.");
		}
		logger.info("Cancellation of editing product pricing process successfully.");

		return result;
	}

	/**
	 * Verify business rules, including not editable fields, License Year
	 * From/To in ascending order, account of State/Transaction Fee in ascending
	 * order
	 * 
	 * @return
	 */
	private boolean verifyBusinessRule() {
		boolean result = true;

		logger.info("Verify the Business Rules.");
		lm.gotoEditProductPricingWidget(pricing.id, productPricingPage);

		// verify Product Pricing ID, Location Class and Purchase Type(if
		// applicable) shall not be editable according to Product Pricing
		// Editing business rule
		result &= this.verifyNotEditable(productPricingPage);

		// verify the Location Class options are displayed with the "All" option
		// as the first option in the list, followed by the
		// Location Classes defined for the Contract sorted by the Code in
		// ascending order
		logger.info("Verify Location Class options are displayed with 'All' as first option and followed by the code in ascending order.");
		try {
			editPricingWidget.verifyLocationClassDropdownList();
			logger.info("Location Class is displayed correctly.");
		} catch (Exception e) {
			result &= false;
			logger.error("Location Class is displayed in wrong way.");
		}

		// verify the License Year From selected from a list of Years starts
		// from the Current Year up to 10 years into the future and including
		// the "All" option
		// if the "All" option is selected, system shall not prompt for the
		// License Year To. if not, system shall allow the user to optionally
		// specify the License Year To
		// selected from a list of Years starting from the Current Year up to 10
		// years into the future
		logger.info("Verify License Year From option are displayed with 'All' as first option and the reset of options is a list of years starting from Current Year up to 10 years into the future.");
		try {
			editPricingWidget.verifyLicenseYearFrom();
			logger.info("License Year From is dsiplayed correctly.");
		} catch (Exception e) {
			result &= false;
			logger.error("License Year From is displayed in wrong way.");
		}

		logger.info("Verify License Year To option are displayed with 'All' as first option and the reset of options is a list of years starting from Current Year up to 10 years into the future.");
		try {
			editPricingWidget.verifyLicenseYearTo();
			logger.info("License Year To is dsiplayed correctly.");
		} catch (Exception e) {
			result &= false;
			logger.error("License Year To is displayed in wrong way.");
		}

		// verify the options in Account of State Fee are displayed in ascending
		// order
		if (editPricingWidget.isAccountsForStateFeeSortedByASC()) { // Blocked by DEFECT-61958
			logger.info("Account of State Fee is displayed correctly in ascending order.");
		} else {
			result &= false;
			logger.error("Account of State Fee is displayed in wrong way.");
		}

		// verify the options in Account of Transaction Fee are displayed in
		// ascending order
		if (editPricingWidget.isAccountsForStateFeeSortedByASC()) {
			logger.info("Account of Transaction Fee is displayed correctly in ascending order.");
		} else {
			result &= false;
			logger.error("Account of Transaction Fee is displayed in wrong way.");
		}

		editPricingWidget.clickCancel();
		ajax.waitLoading();
		return result;
	}
	
	protected String getTranslationForLicenseFiscalYear(String schema){
		
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		String query="SELECT LABEL_VALUE FROM X_Translation WHERE LABEL_KEY='translatable.licensefiscalyear'";
		db.resetSchema(schema);
		String value=db.executeQuery(query, "LABEL_VALUE", 0).split(" ")[0].trim();
		return value;
	}
}
