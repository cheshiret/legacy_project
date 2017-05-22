package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.PermitLottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitLotteryApplicationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Please look at DEFECT-36522 if case is failed.
 * @Preconditions:
 * @SPEC: 
 * TC030079: Permit Lottery Application page - Preference attributes
 * TC035684: Permit Lottery Application - Preference attribute 'Facility' 
 * TC030245: Lottery application details page - Trip Itinerary
 * TC033588: Permit Lottery Application - Exit date 
 * TC033589: Permit Lottery Application - Exit Point 
 * @Task#:
 * 
 * @author SWang5
 * @Date  Nov 9, 2012
 */
public class REC_LotteryApplicationPg extends RecgovTestCase{
	private BWCooperator bw = BWCooperator.getInstance();
	private UwpPermitLotteryApplicationPage lotteryApplicablePg = UwpPermitLotteryApplicationPage.getInstance();
	private PermitInfo permit = new PermitInfo();
	private List<String> exitPoint_Overnight = new ArrayList<String>();
	private List<String> exitPoint_DayUse = new ArrayList<String>();

	public void execute(){
		web.invokeURL(url);
		web.signIn(email, pw);
		bw.submitLotteryToLotteryApplication(permit, true);

		//Permit lottery Preference attributes sorting order is same as the "Display Order" in "Lottery Details" page in IM
		this.verifyLotteryPreferenceAttributesOrder();

		//Preference attribute "Facility" displays in 'Applying for' section
		lotteryApplicablePg.verifyFacilityDisabled();
		lotteryApplicablePg.verifyFacilityName(permit.facility);

		//"Preferred Choice" section
		//1. Exit Date (Not default): trip itinerary rows according to exit date 
		lotteryApplicablePg.verifyTripItineraryRows(1);
		lotteryApplicablePg.setPreferredExitDateToSyncTripItinerary(DateFunctions.getDateAfterGivenDay(permit.entryDate, 2), 2);
		lotteryApplicablePg.verifyTripItineraryRows(2);
		lotteryApplicablePg.verifyLotteryExitPoints(exitPoint_Overnight);

		//2. Exit Date (Same As Entry Date): trip itinerary row=1 and exit date is read only
		lotteryApplicablePg.selectLotteryPermitType(permit.alternativePermitType);
		lotteryApplicablePg.verifyTripItineraryRows(1);
		lotteryApplicablePg.verifyLotteryExitDateReadOnly();
		lotteryApplicablePg.verifyLotteryExitPoints(exitPoint_DayUse);

		//"Alternative Choice 1" section
		//3. Exit Date (Same As Entry Date): trip itinerary row=1 and exit date is read only
		this.setLotteryAlternativeInfo();
		
		lotteryApplicablePg.verifyTripItineraryRows(1);
		lotteryApplicablePg.verifyLotteryAlternativeExitDateReadOnly();
		lotteryApplicablePg.verifyLotteryAlternativeExitPoints(exitPoint_DayUse);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + WEB_URL_RECGOV);
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		permit.isUnifiedSearch = this.isUnifiedSearch();

		//Lottery information
		permit.parkId = "72201";
		permit.contractCode = "NRSO";
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		permit.facility = DataBaseFunctions.getFacilityName(permit.parkId, schema); //"Mt. WHITNEY";

		permit.permitType = "Auto Overnight";
		permit.entrance = "MTOV BackCountryMtOverNight";
		permit.entryDate = web.queryPermitLotteryInventoryStartDate(schema, "Mt Whitney Lottery 2012"); //Please use this lottery setups in support script
		permit.groupSize = "2";
		permit.isRange = false;

		//Preferred Choice
		permit.exitPoint = permit.entrance;
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, 1);
		
		//Alternative Choice 1
		permit.alternativePermitType = "Auto Day Use";
		permit.alternativeEntrance = "MTDU BackCountryMtDayUse"; 
		permit.alternativeEntryDate = permit.entryDate;
		
		exitPoint_Overnight.add("- Please Select -");
		exitPoint_Overnight.add(permit.entrance);
		exitPoint_DayUse.add("- Please Select -");
		exitPoint_DayUse.add(permit.alternativeEntrance);
	}

	private void verifyLotteryPreferenceAttributesOrder(){
		String lotterPreferencesContent = lotteryApplicablePg.getLotteryPreferencesContent();
		String regex = "^Applying for Facility.*Permit Type.*Trail/Zone.*Permit Entry Date.*Permit Group Size.*Exit Point.*Exit Date.*";

		if(!lotterPreferencesContent.matches(regex)){
			throw new ErrorOnDataException("Failed to verify 'Lottery Preference Attributes' order.");
		}
		logger.info("Successfully verify 'Lottery Preference Attributes' order.");
	}
	
	private void setLotteryAlternativeInfo(){
		lotteryApplicablePg.selectLotteryAlternativePermitTypeToSyncEntrance(permit.alternativePermitType, permit.alternativeEntrance);

		lotteryApplicablePg.selectLotteryAlternativeEntrance(permit.alternativeEntrance);
		Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
		lotteryApplicablePg.waitForLotteryAlternativeExitPointSync(exitPoint_DayUse.get(1));
		
		lotteryApplicablePg.setLotteryAlternativeEntryDate(permit.alternativeEntryDate);
		lotteryApplicablePg.refreshPage();
	}
}
