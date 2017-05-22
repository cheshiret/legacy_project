package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.PermitLottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Please find DEFECT-36522 if case is failed. ->passed
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
public class BW_LotteryApplicationPg extends WebTestCase{
	private BWCooperator bw = BWCooperator.getInstance();
	private PermitInfo permit = new PermitInfo();
	private BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
	private List<String> exitPoint_Overnight = new ArrayList<String>();
	private List<String> exitPoint_DayUse = new ArrayList<String>();

	public void execute(){
		bw.invokeURL(url);
		bw.signInBW(email, pw);
		bw.submitLotteryToLotteryApplication(permit, false);

		//Permit lottery Preference attributes sorting order is same as the "Display Order" in "Lottery Details" page in IM
		this.verifyLotteryPreferenceAttributesOrder();

		//Preference attribute "Facility" displays in 'Applying for' section
		bwOrdDetails.verifyFacilityDisabled();
		bwOrdDetails.verifyFacilityName(permit.facility);

		//"Preferred Choice" section
		//1. Exit Date (Not default): trip itinerary rows according to exit date 
		bwOrdDetails.verifyTripItineraryRows(1);
		bwOrdDetails.setPreferredExitDateToSyncTripItinerary(DateFunctions.getDateAfterGivenDay(permit.entryDate, 2), 2);
		bwOrdDetails.verifyTripItineraryRows(2);
		bwOrdDetails.verifyLotteryExitPoints(exitPoint_Overnight);

		//2. Exit Date (Same As Entry Date): trip itinerary row=1 and exit date is read only
		bwOrdDetails.selectLotteryPermitType(permit.alternativePermitType);
		bwOrdDetails.verifyTripItineraryRows(1);
		bwOrdDetails.verifyLotteryExitDateReadOnly();
		bwOrdDetails.verifyLotteryExitPoints(exitPoint_DayUse);

		//"Alternative Choice 1" section
		//3. Exit Date (Same As Entry Date): trip itinerary row=1 and exit date is read only
		this.setLotteryAlternativeInfo();
		
		bwOrdDetails.verifyTripItineraryRows(1);
		bwOrdDetails.verifyLotteryAlternativeExitDateReadOnly();
		bwOrdDetails.verifyLotteryAlternativeExitPoints(exitPoint_DayUse);
		
		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
		//Login BW info
		url = TestProperty.getProperty(env + ".web.bw.url");
		email = TestProperty.getProperty("mtwhitney.coop.email");
		pw = TestProperty.getProperty("mtwhitney.coop.pwd");

		//Lottery information
		permit.parkId = "72201";
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		permit.facility = DataBaseFunctions.getFacilityName(permit.parkId, schema); //"Mt. WHITNEY";

		permit.permitType = "Auto Overnight (Commercial)";
		permit.entrance = "MTOV BackCountryMtOverNight"; 
		permit.entryDate = bw.queryPermitLotteryInventoryStartDate(schema, "Mt Whitney Lottery 2012");//Please use this lottery setups in support script
		permit.groupSize = "2";
		permit.isRange = false;

		//Preferred Choice
		permit.exitPoint = permit.entrance;
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, 1);

		//Alternative Choice 1
		permit.alternativePermitType = "Auto Day Use (Commercial)";
		permit.alternativeEntrance = "MTDU BackCountryMtDayUse"; 
		permit.alternativeEntryDate = permit.entryDate;
		
		exitPoint_Overnight.add("- Please Select -");
		exitPoint_Overnight.add(permit.entrance);
		exitPoint_DayUse.add("- Please Select -");
		exitPoint_DayUse.add(permit.alternativeEntrance);
	}

	private void verifyLotteryPreferenceAttributesOrder(){
		String lotterPreferencesContent = bwOrdDetails.getLotteryPreferencesContent();
		String regex = "^Applying for Facility.*Permit Type.*(Trail/Zone|Entrance).*Permit Entry Date.*Permit Group Size.*Exit Point.*Exit Date.*";

		if(!lotterPreferencesContent.matches(regex)){
			throw new ErrorOnDataException("Failed to verify 'Lottery Preference Attributes' order.");
		}
		logger.info("Successfully verify 'Lottery Preference Attributes' order.");
	}
	
	private void setLotteryAlternativeInfo(){
		bwOrdDetails.selectLotteryAlternativePermitTypeToSyncEntrance(permit.alternativePermitType, permit.alternativeEntrance);
		bwOrdDetails.selectLotteryAlternativeEntrance(permit.alternativeEntrance);
		bwOrdDetails.refreshPage();
		bwOrdDetails.setLotteryAlternativeEntryDate(permit.alternativeEntryDate);
		bwOrdDetails.refreshPage();
	}
}
