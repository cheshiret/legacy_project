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

public class VerifyMessage_ExecPeriodOfOthers extends InventoryManagerTestCase{
	
	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage.getInstance();
	LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	private LotterySchedule schData = new LotterySchedule();
	private List<Exception> exceptionList = new ArrayList<Exception>();
	private String actualMessage = "";
	private String msg1 = "The Lottery Execution Freeze Duration is required. Please enter the Lottery Execution Freeze Duration (in days) in the field provided.",
	               msg2 = "The Lottery Execution Freeze Duration must be an integer value greater than or equal to 0. Please change your entries.",
//	               msg3 = "The Lottery Execution Customer Acceptance Deadline must be an integer value greater than the Lottery Execution Freeze Duration. Please change your entries.";
				   msg3 = "The Acceptance period start date/time for the Lottery Schedule is required. Please enter the Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.",
				   msg4 = "The Acceptance period start date/time must be after the lottery execution date plus the freeze period. Please change your entries.",
				   msg5 = "The Acceptance period end date/time must be after the Acceptance period start date/time.";
	                       
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);		
		String lotterySchdID = im.addLotterySchedule(schData, false,"Permit");
		
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchdID);
		
		// Check error message: The Lottery Execution Freeze Duration is blank
		schData.freezeDuration = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: The Lottery Execution Freeze Duration is not an
		// integer value greater than or equal to 0
		schData.freezeDuration = "-1"; // Change according to PCR2893
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		schData.freezeDuration = "1";
		
		// Check error message: Customer Acceptance Deadline is left blank
		schData.customerAcceptanceDeadline = "";
		schData.customerAcceptFromDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: Customer Acceptance from date is less than execution date
		schData.customerAcceptFromDate =  DateFunctions.getDateAfterGivenDay(schData.executeDate,Integer.parseInt(schData.freezeDuration));
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg4, actualMessage);
		
		// Check error message: Customer Acceptance from date and to date is same date
		schData.customerAcceptFromDate = schData.customerAcceptToDate;
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
		lottery.name = "verify lottery detail edit of permit";
		
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
		schData.customerAcceptanceDeadline = "2";
		schData.customerAcceptFromDate = DateFunctions.getDateAfterToday(17);
		schData.customerAcceptToDate = DateFunctions.getDateAfterToday(19);
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
