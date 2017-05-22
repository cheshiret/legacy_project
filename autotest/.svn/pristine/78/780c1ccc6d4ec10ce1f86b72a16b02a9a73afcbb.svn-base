package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.site;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the RA Fee schedule UI display for 'Per Unit'
 * @Preconditions:
 * @SPEC: Add RA Fee Schedule [TC:056880]
 * @Task#: AUTO-1851
 * 
 * @author qchen
 * @Date  Aug 19, 2013
 */
public class VerifyUI_PerUnit extends FinanceManagerTestCase {
	
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private String expectedTransactionTypes[], rateAppliesTos[], applyRates[];
	private boolean result = true;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		
		//1. select 'Do Not Reverse and Reprice on Order Changes', 'Per Unit', 'Change Dates', 
		//verify Rate Applies To options: 'New Unit(s)' and 'New/Changed Unit(s)' exist
		//verify 'Add Rate' button shall be displayed and enabled
		detailsPage.selectReverseAndRepriceIndicator(schedule.reverseAndRepriceIndicator);
		detailsPage.selectApplyRate(schedule.applyRate);
		detailsPage.selectTransactionType(schedule.tranType);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Rate Applies To shall be enabled", true, detailsPage.isRateAppliesToEnable());
		result &= this.compareRateAppliestoOptions(rateAppliesTos);
		result &= MiscFunctions.compareResult("'Add Rate' shall be enabled", true, detailsPage.isAddRateEnable());
		
		//2. select Rate Applies To as 'New Unit(s)', verify 'Rate (New Units)' and 'Rate (Flat Amount)' shall exist
		detailsPage.selectRateAppliesTo(OrmsConstants.RATE_APPLIES_TO_NEW_UNITS);
		result &= MiscFunctions.compareResult("Rate (New Units) shall exist", true, detailsPage.isBaseRateExists());
		result &= MiscFunctions.compareResult("Rate (Flat Amount) shall exist", true, detailsPage.isRateFlatAmountExists());
		
		//3. select Rate Applies To as 'New/Changed Unit(s)', verify 'Rate (New Units)', 'Rate (Changed Units)' and 'Rate (Flat Amount)' shall exist
		detailsPage.selectRateAppliesTo(OrmsConstants.RATE_APPLIES_TO_NEW_CHANGED_UNITS);
		result &= MiscFunctions.compareResult("Rate (New Units) shall exist", true, detailsPage.isBaseRateExists());
		result &= MiscFunctions.compareResult("Rate (Changed Units) shall exist", true, detailsPage.isRateChangedUnitsExists());
		result &= MiscFunctions.compareResult("Rate (Flat Amount) shall exist", true, detailsPage.isRateFlatAmountExists());
		
		//4. Selected Transaction Type is NEITHER "Change Dates" or "Transfer Same Facility â€“ Same Value" or "Transfer Same Facility â€“ Diff Value" or "Transfer Different Facility" or "Mid Stay Transfer",
		//verify 'Rate' and 'Rate (Flat Amount)' exist
		schedule.tranType = "Checkin";
		detailsPage.selectTransactionType(schedule.tranType);
		result &= MiscFunctions.compareResult("Rate shall exist", true, detailsPage.isBaseRateExists());
		result &= MiscFunctions.compareResult("Rate (Flat Amount) shall exist", true, detailsPage.isRateFlatAmountExists());
		
		//5. select 'Reverse and Reprice on Order Changes', 
		//verify system shall only display 'Reservation', 'Regisration/Walkin' and 'Mid Stay Transfer' transaction type, shall default apply rate to 'Per Unit' and not editable
		schedule.reverseAndRepriceIndicator = true;
		schedule.applyRate = "Per Unit";
		detailsPage.selectReverseAndRepriceIndicator(schedule.reverseAndRepriceIndicator);
		result &= this.compareTransactionType(expectedTransactionTypes);
		result &= MiscFunctions.compareResult("Apply Rate shall default to 'Per Unit'", schedule.applyRate, detailsPage.getApplyRate());
		result &= MiscFunctions.compareResult("Apply Rate all options shall be disabled", true, !detailsPage.isApplyRateEditable());
		
		//6. Apply Rate 'Per Unit' is selected, Maximum Fee Restriction shall default select 'None'
		schedule.maximumFeeRestriction = "None";
		result &= MiscFunctions.compareResult("Maximum Fee Restriction shall default as 'None'", schedule.maximumFeeRestriction, detailsPage.getMaximumFeeRestriction());
		
		//7. select Maximum Fee Restriction as 'Flat', verify Maximum Fee Amount displayed
		schedule.maximumFeeRestriction = "Flat";
		detailsPage.selectMaximumFeeRestriction(schedule.maximumFeeRestriction);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("'Maximum Fee Amount' shall exist", true, detailsPage.isMaximumFeeAmountExists());
		
		//8. select 'Do Not Reverse and Reprice on Order Changes', 'Cancellation', verify 'Per Transaction', 'Per Unit' and 'Percentage' shall exist
		schedule.reverseAndRepriceIndicator = false;
		schedule.tranType = "Cancellation";
		detailsPage.selectReverseAndRepriceIndicator(schedule.reverseAndRepriceIndicator);
		detailsPage.selectTransactionType(schedule.tranType);
		result &= this.compareApplyRateOptions(applyRates);
		result &= MiscFunctions.compareResult("'Per Transaction'/'Per Unit'/'Percentage' shall be enable", true, detailsPage.isApplyRateEditable());
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer log above for details info.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "KY Contract";
		login.location = "Administrator - Auto/Commonwealth of Kentucky";
		
		String facilityID = "90800";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Site";
		schedule.reverseAndRepriceIndicator = false;
		schedule.applyRate = "Per Unit";
		schedule.tranType = "Change Dates";
		
		expectedTransactionTypes = new String[] {"Mid Stay Transfer", "Registration/Walk-in", "Reservation"};
		rateAppliesTos = new String[] {"New Unit(s)", "New/Changed Unit(s)"};
		applyRates = new String[] {"Per Transaction", "Per Unit", "Percentage"};
	}
	
	private boolean compareTransactionType(String expected[]) {
		List<String> actual = detailsPage.getTransactionTypeOptions();

		boolean toReturn = true;
		if(actual.size() != expected.length) toReturn &= false;
		for(int i = 0; i < expected.length; i ++) {
			if(!actual.contains(expected[i])) {
				toReturn &= false;
			}
		}
		
		if(!toReturn) logger.error("Transaction Type is not correct as expected.");
		
		return toReturn;
	}
	
	private boolean compareRateAppliestoOptions(String expected[]) {
		boolean toReturn = true;
		
		List<String> actual = detailsPage.getRateAppliesToOptions();
		if(actual.size() != expected.length) throw new ErrorOnPageException("Rate Applies To options size", expected.length, actual.size());
		for(int i = 0; i < expected.length; i ++) {
			if(!actual.contains(expected[i])) {
				logger.error("Rate Applies To shall contain option - " + expected[i]);
				toReturn &= false;
			}
		}
		
		return toReturn;
	}
	
	private boolean compareApplyRateOptions(String expected[]) {
		boolean toReturn = true;
		
		List<String> actual = detailsPage.getApplyRateOptions();
		if(actual.size() != expected.length) throw new ErrorOnPageException("Apply Rate options size", expected.length, actual.size());
		for(int i = 0; i < expected.length; i ++) {
			if(!actual.contains(expected[i])) {
				logger.error("Apply Rate shall contain option - " + expected[i]);
				toReturn &= false;
			}
		}
		
		return toReturn;
	}
}
