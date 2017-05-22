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

public class VerifyMessage_AppPeriodOfDate extends InventoryManagerTestCase{
	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage.getInstance();
	LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	LotterySchedule schData = new LotterySchedule();
	private List<Exception> exceptionList = new ArrayList<Exception>();
	private String actualMessage = "";
	private String msg1 ="An Application Start Date and Time for the Lottery Schedule is required. Please enter the Application Start Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided",
	               msg2 = "The Application Start Date and Time must be a date in the future.",
	               msg3 = "An Application End Date and Time for the Lottery Schedule is required. Please enter the Application End Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided",
	               msg4 = "The Application Start Date and Time must not be later than the Application End Date and Time. Please change your entries.",
	               msg5 = "The Application Period (Application Start and End Dates/Time) must be before the Inventory Period (Inventory Start and End Dates). Please change your entries.",
	               msg6 = "The Application Period (Application Start and End Dates/Time) must not overlap with the Inventory Period (Inventory Start and End Dates). Please change your entries.";
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);		
		String lotterySchdID = im.addLotterySchedule(schData, false,"Permit");
		
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchdID);
		
		// Check error message: Application Start Date & Time is left blank
		schData.appStartDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: invalid start date
		schData.appStartDate = "aaaaaaaaaaaaaaaaaaaaaaaa";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: Start Date & Time entry is less than or equal to
		// the Current Date & Time
		schData.appStartDate = DateFunctions.getDateAfterToday(-5);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);	
		schData.appStartDate = DateFunctions.getDateAfterToday(5);
		
		// Check error message: Application End Date & Time is left blank
		schData.appEndDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: Application End Date & Time is invalid
		schData.appEndDate = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: Application Start Date & Time is greater than
		// the entry for Application End Date & Time
		schData.appStartDate = DateFunctions.getDateAfterToday(10);
		schData.appEndDate = DateFunctions.getDateAfterToday(5);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg4, actualMessage);
		
		// Check error message: Application Date after Inventory
		schData.appStartDate = DateFunctions.getDateAfterToday(30);
		schData.appEndDate = DateFunctions.getDateAfterToday(35);		
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg5, actualMessage);
		
		//Check error message: Application Date overlap Inventory Date
		schData.appStartDate = DateFunctions.getDateAfterToday(5);
		schData.appEndDate = DateFunctions.getDateAfterToday(23);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg6, actualMessage);
		
		
		if (exceptionList.size() > 0) {
			for (Exception e : exceptionList) {
				e.printStackTrace();
			}
			throw new TestCaseFailedException("Some check point failed:"
					+ exceptionList.size());

		}
		
		im.logoutInvManager();		
	}

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
