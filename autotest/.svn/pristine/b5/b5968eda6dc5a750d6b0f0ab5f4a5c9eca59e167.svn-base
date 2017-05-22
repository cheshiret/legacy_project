package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.editlotteryschedule.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LoterrySchExclusion;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class VerifyMessage_InvPeriodOfExclusions extends InventoryManagerTestCase{
	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage.getInstance();
	LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	LotterySchedule schData = new LotterySchedule();
	private List<Exception> exceptionList = new ArrayList<Exception>();
	private String actualMessage = "";
	private String msg1 = "An Inventory Exclusion Start Date for each Inventory Exclusion Period is required. Please enter the Inventory Exclusion Start Date using the format Ddd Mmm dd yyyy in the field provided.",
	               msg2 = "An Inventory End Date for each Inventory Exclusion Period is required. Please enter the Inventory Exclusion End Date using the format Ddd Mmm dd yyyy in the field provided.",
	               msg3 = "The Inventory Exclusion Start Date must not be later than the corresponding Inventory Exclusion End Date. Please change your entries.",
	               msg4 = "The Inventory Exclusion Start Date and corresponding Inventory Exclusion End Date must be within the specified Inventory Start Date and Inventory End Date. Please change your entries.",
	               msg5 = "Multiple overlapping Inventory Exclusion Periods for the same Participating Location 'All' have been entered. Inventory Exclusions for the same Participating Location cannot overlap. Please change your entries.";
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);		
		String lotterySchdID = im.addLotterySchedule(schData, false,"Permit");
		
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchdID);
		
		// Check error message: Exclusion Period is specified but the Inventory
		// Exclusion Start Date is left blank
		schData.exclusions.get(0).ExclusionsStartDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: Exclusion Period is specified but the Inventory
		// Exclusion Start Date is invalid
		schData.exclusions.get(0).ExclusionsStartDate = "aaaaaaaaaaaaaaaaaa";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		schData.exclusions.get(0).ExclusionsStartDate = DateFunctions.getDateAfterToday(20);
		
		// Check error message: Exclusion Period is specified but the Inventory
		// Exclusion End Date is left blank
		schData.exclusions.get(0).ExclusionsEndDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: Exclusion Period is specified but the Inventory
		// Exclusion End Date is invalid
		schData.exclusions.get(0).ExclusionsEndDate = "aaaaaaaaaaaaaaaaaaaaa";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: Exclusion Start Date is greater than the entry
		// for Inventory Exclusion End Date
		schData.exclusions.get(0).ExclusionsStartDate = DateFunctions
				.getDateAfterToday(25);
		schData.exclusions.get(0).ExclusionsEndDate = DateFunctions
				.getDateAfterToday(20);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: Exclusion Start Date and End Date are not within
		// the specified Inventory Start Date and End Date
		schData.exclusions.get(0).ExclusionsStartDate = DateFunctions
				.getDateAfterToday(22);
		schData.exclusions.get(0).ExclusionsEndDate = DateFunctions
				.getDateAfterToday(28);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg4, actualMessage);
		
		// Check error message: same Participating Location with overlapping
		// Inventory Exclusion
		schData.exclusions.get(0).ExclusionsStartDate = DateFunctions.getDateAfterToday(20);
		schData.exclusions.get(0).ExclusionsEndDate = DateFunctions.getDateAfterToday(25);
		schData.exclusions.add(new LoterrySchExclusion("All",
				schData.exclusions.get(0).ExclusionsStartDate,
				schData.exclusions.get(0).ExclusionsEndDate));
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg5, actualMessage);
		
		if (exceptionList.size() > 0) {
			for (Exception e : exceptionList) {
				e.printStackTrace();
			}
			throw new TestCaseFailedException("Some check point failed:"
					+ exceptionList.size());

		}
		
		im.logoutInvManager();			
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");

		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		lottery.id = im.getLotteryId(schema,
				"verify lottery detail edit of permit");		
		
		// Initial Lottery Schedule default information
		schData.appStartDate = DateFunctions.getDateAfterToday(5);
		schData.appEndDate = DateFunctions.getDateAfterToday(10);
		schData.callCenter = false;
		schData.field = true;
		schData.web = false;
		schData.fieldStartDate = DateFunctions.getDateAfterToday(5);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(10);
		schData.executeDate = DateFunctions.getDateAfterToday(15);
		schData.freezeDuration = "1";
		schData.customerAcceptFromDate = DateFunctions.getDateAfterToday(17);
		schData.customerAcceptToDate = DateFunctions.getDateAfterToday(18);
		schData.invStartDate = DateFunctions.getDateAfterToday(20);
		schData.invEndDate = DateFunctions.getDateAfterToday(25);
		schData.exclusions.add(new LoterrySchExclusion("All", DateFunctions.getDateAfterToday(20), 
				DateFunctions.getDateAfterToday(25)));
		
	}
	
	private void verifyErrorMsg(String expectedMessage, String actualMessage) {
		logger.info("Verify Message.");
		if (!actualMessage.equalsIgnoreCase(
				expectedMessage)) {
			exceptionList.add(new ErrorOnPageException(
					"The actual message doesn't match the expected - "
							+ expectedMessage));
		}
	}

}
