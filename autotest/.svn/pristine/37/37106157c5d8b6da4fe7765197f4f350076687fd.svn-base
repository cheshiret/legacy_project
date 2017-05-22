package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.site;

import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Transaction Fee schedule details page UI when adding a new schedule with 'Apply Fee' - Per Unit
 * @Preconditions:
 * @SPEC: Add Fee Schedule [TC:056913]
 * @Task#: AUTO-1851
 * TODO DEFECT-48640
 * @author qchen
 * @Date  Aug 19, 2013
 */
public class VerifyUI_PerUnit extends FinanceManagerTestCase {
	
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage detailsPage = FinMgrFeeSchDetailPage.getInstance();
	private String maximumFeeRestrictions[], rateAppliesTos[];
	private boolean result = true;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//1. select Transaction Type as  'Cancellation' and Apply Fee as 'Per Unit', 
		//1.1 verify Applicable Units enabled, and by default, 'All' shall be selected;
		detailsPage.selectTransactionType(schedule.tranType);
		detailsPage.selectTransactionMethod(schedule.tranFeeOption);
		result &= MiscFunctions.compareResult("Applicable Units options shall be enabled", true, detailsPage.isApplicableUnitsEnabled());
		result &= MiscFunctions.compareResult("Applicable Units default value", "All", detailsPage.getApplicableUnit());
		
		//1.2 verify Transaction Fee and Transaction Fee (Flat Amount) exist
		result &= MiscFunctions.compareResult("Transaction Fee Rate shall be enabled", true, detailsPage.isTransactionFeeExists());
		result &= MiscFunctions.compareResult("Transaction Fee Rate (Flat Amount) shall be enabled", true, detailsPage.isTransactionFeeFlatAmountExists());
		
		//1.3 verify Maximum Fee Restriction - 'None', 'Flat', 'Based On Penalty Charges' and 'Combination of Flat and Penalty Charges' enabled, by default, 'None' is selected;
		result &= this.compareMaximumFeeRestrictionOptions(maximumFeeRestrictions);
		result &= MiscFunctions.compareResult("Maximum Fee Restriction shall be all enabled", true, detailsPage.isMaximumFeeRestrictionEnabled());
		result &= MiscFunctions.compareResult("Maximum Fee Restriction default value", "None", detailsPage.getMaximumFeeRestriction());
		
		//1.4 verify if selects 'Flat' or 'Combination of Flat and Penalty Charges', system shall allow user to specify Maximum Fee Amount.
		detailsPage.selectMaximumFeeRestriction("Flat");
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Maximum Fee Amount shall exist", true, detailsPage.isMaximumFeeRateExists());
		detailsPage.selectMaximumFeeRestriction("Combination of Flat and Penalty Charges");
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Maximum Fee Amount shall exist", true, detailsPage.isMaximumFeeRateExists());
		fnm.cancelFromFeeScheduleDetailsPage();
		
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		//2. select Transaction Type is EITHER "Change Dates" or "Transfer Same Facility â€“ Same Value" or "Transfer Same Facility â€“ Diff Value" or "Transfer Different Facility" or "Mid Stay Transfer"
		detailsPage.selectTransactionType(new String[] {"Change Dates", "Transfer Same Facility - Same Value", "Transfer Same Facility - Diff Value", "Transfer Different Facility", "Mid Stay Transfer"}[new Random().nextInt(5)]);
		detailsPage.waitLoading();
		detailsPage.selectTransactionMethod("Per Unit");
		detailsPage.waitLoading();
		//2.1 verify Rate Applies To - 'New Unit(s)' and 'New/Changed Unit(s)' enabled, be default, 'New Unit(s)' is selected
		result &= this.compareRateAppliestoOptions(rateAppliesTos);
		result &= MiscFunctions.compareResult("Rate Applies To shall be exist", true, detailsPage.isRateAppliesToEnabled());
		result &= MiscFunctions.compareResult("Rate Applies To default selected value", rateAppliesTos[0], detailsPage.getRateAppliesTo());
		
		//2.2 verify if Rate Applies To is selected as 'New Unit(s)', Rate (New Units), Rate (Flat Amount) shall exist
		result &= MiscFunctions.compareResult("Rate (New Units) shall exist", true, detailsPage.isTransactionFeeExists());
		result &= MiscFunctions.compareResult("Rate (Flat Amount) shall exist", true, detailsPage.isTransactionFeeFlatAmountExists());
		
		//2.3 verify if Rate Applies To is selected  as 'New/Changed Unit(s)', Rate (New Units), Rate(Changed Units) and Rate(Flat Amount) shall exist
		detailsPage.selectRateAppliesTo(rateAppliesTos[1]);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Rate (New Units) shall exist", true, detailsPage.isTransactionFeeExists());
		result &= MiscFunctions.compareResult("Rate (Changed Units) shall exist", true, detailsPage.isTransactionFeeChangedUnitsExists());
		result &= MiscFunctions.compareResult("Rate (Flat Amount) shall exist", true, detailsPage.isTransactionFeeFlatAmountExists());
		
		//2.4 verify Maximum Fee Restriction  - 'None' and  'Flat' are enable to select, by default, 'None' is selected
		maximumFeeRestrictions = new String[] {"None", "Flat"};
		result &= this.compareMaximumFeeRestrictionOptions(maximumFeeRestrictions);
		result &= MiscFunctions.compareResult("Maximum Fee Restriction are enabled", true, detailsPage.isMaximumFeeRestrictionEnabled());
		result &= MiscFunctions.compareResult("Maximum Fee Restriction - 'None' is default selected", maximumFeeRestrictions[0], detailsPage.getMaximumFeeRestriction());
		
		//2.5 verify if selects 'Flat', Maximum Fee Amount shall exist
		detailsPage.selectMaximumFeeRestriction(maximumFeeRestrictions[1]);
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Maximum Fee Amount is enabled", true, detailsPage.isMaximumFeeRateExists());
		
		//3. select Apply Fee as 'Per Transaction' and Transaction Type is NOT 'Cancellation', verify Transaction Fee exist
		detailsPage.selectTransactionMethod("Per Transaction");
		detailsPage.waitLoading();
		result &= MiscFunctions.compareResult("Rate", true, detailsPage.isTransactionFeeExists());
		
		//4. select Apply Fee as 'Per Transaction' and Transaction Type is 'Cancellation', 
		// verify Maximum Fee Restriction - 'None', 'Based on Penalty Charges', be default, 'None' is selected
		detailsPage.selectTransactionType("Cancellation");
		detailsPage.waitLoading();
		detailsPage.selectTransactionMethod("Per Transaction");
		detailsPage.waitLoading();
		maximumFeeRestrictions = new String[] {"None", "Based On Penalty Charges"};//TODO DEFECT-48640 has been only fixed in build - 3.05.00, after this defect has been fixed, please delete below line of code and uncomment this line
		result &= this.compareMaximumFeeRestrictionOptions(maximumFeeRestrictions);
		result &= MiscFunctions.compareResult("Maximum Fee Restriction default selected value", "None", detailsPage.getMaximumFeeRestriction());
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "KY Contract";
		login.location = "Administrator - Auto/Commonwealth of Kentucky";
		
		//transaction fee schedule info
		String facilityID = "90800";//KENTUCKY HORSE PARK
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Site";
		schedule.feeType = "Transaction Fee";
		schedule.tranType = "Cancellation";
		schedule.tranFeeOption = "Per Unit";
		schedule.rateApplyTo = OrmsConstants.RATE_APPLIES_TO_NEW_CHANGED_UNITS;
		
		rateAppliesTos = new String[] {"New Unit(s)", "New/Changed Unit(s)"};
		maximumFeeRestrictions = new String[] {"None", "Flat", "Based On Penalty Charges", "Combination of Flat and Penalty Charges"};
	}
	
	private boolean compareMaximumFeeRestrictionOptions(String expected[]) {
		boolean toReturn = true;
		
		List<String> actual = detailsPage.getMaximumFeeRestrictionOptions();
		if(actual.size() != expected.length) throw new ErrorOnPageException("Maximum Fee Restriction options size", expected.length, actual.size());
		for(int i = 0; i < expected.length; i ++) {
			if(!actual.contains(expected[i])) {
				logger.error("Maximum Fee Restriction shall contain option - " + expected[i]);
				toReturn &= false;
			}
		}
		
		return toReturn;
	}
	
	private boolean compareRateAppliestoOptions(String expected[]) {
		boolean toReturn = true;
		
		List<String> actual = detailsPage.getRateAppliesToOptions();
		if(actual.size() != expected.length) throw new ErrorOnPageException("Maximum Fee Restriction options size", expected.length, actual.size());
		for(int i = 0; i < expected.length; i ++) {
			if(!actual.contains(expected[i])) {
				logger.error("Maximum Fee Restriction shall contain option - " + expected[i]);
				toReturn &= false;
			}
		}
		
		return toReturn;
	}
}
