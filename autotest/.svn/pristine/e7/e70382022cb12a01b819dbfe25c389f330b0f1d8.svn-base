package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.editlotteryschedule.inventory;

/*
 * This case is block by DEFECT-32022
 * This case is block by DEFECT-32050
 */
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

public class VerifyMessage_AppPeriodOfSalesChannel extends InventoryManagerTestCase{
	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage.getInstance();
	LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	LotterySchedule schData = new LotterySchedule();
	private List<Exception> exceptionList = new ArrayList<Exception>();
	private String actualMessage = "";
	private String msg1 = "Lottery Applications have to be accepted through at least one Sales Channel. Please check a Sales Channel from the list provided.",
	               msg2 = "The From Date and Time for a Sales Channel that will be accepting Lottery Applications is required. Please enter the Sales Channel From Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.",
	               msg3 = "The To Date and Time for a Sales Channel that will be accepting Lottery Applications is required. Please enter the Sales Channel To Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.",
	               msg4 = "The From Date and Time for each Sales Channel that will be accepting Lottery Applications must not be later than the corresponding End Date and Time. Please change your entries.",
	               msg5 = "The From Date and Time and corresponding To Date and Time for each Sales Channel that will be accepting Lottery Applications must be within the specified Application Start Date and Time and Application End Date and Time. Please change your entries.";
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);		
		String lotterySchdID = im.addLotterySchedule(schData, false,"Permit");		
	
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchdID);
		
		// Check error message: No Sales Channel was selected
		schData.field = false;
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		schData.field = true;
		
		// Check error message: From Date & Time for a Sales Channel selected to
		// accept Lottery Applications is left blank
		schData.fieldStartDate = "";
		schData.isUpdateFeild = true;
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: From Date & Time for a Sales Channel is invalid
		schData.fieldStartDate = "aaaaaaaaaaaaaaaaaaaaaaa";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		schData.fieldStartDate = DateFunctions.getDateAfterToday(360);
		
		// Check error message: To Date & Time for a Sales Channel selected to
		// accept Lottery Applications is left blank
		schData.fieldEndDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: invalid To Date for Sales Channel
		schData.fieldEndDate = "aaaaaaaaaaaaaaaaaaaaaa";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: Sales Channel's From Date & Time is greater than
		// the entry for that Sales Channel's To Date & Time
		schData.fieldStartDate = DateFunctions.getDateAfterToday(10);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(5);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg4, actualMessage);
		
		// Check error message: Sales Channel's specified From Date & Time and
		// To
		// Date & Time are not within the specified Application Start Date &
		// Time
		// and Application End Date & Time
		schData.fieldStartDate = DateFunctions.getDateAfterToday(2);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(8);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg5, actualMessage);
		
		// Check error message: Sales Channel's specified From Date & Time and
		// To
		// Date & Time are not within the specified Application Start Date &
		// Time
		// and Application End Date & Time
		schData.fieldStartDate = DateFunctions.getDateAfterToday(8);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(12);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg5, actualMessage);
		
		// Check error message: Sales Channel's specified From Date & Time and
		// To
		// Date & Time are not within the specified Application Start Date &
		// Time
		// and Application End Date & Time
		schData.fieldStartDate = DateFunctions.getDateAfterToday(2);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(12);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg5, actualMessage);
		
		// Check error message: Sales Channel's specified From Date & Time and
		// To
		// Date & Time are not within the specified Application Start Date &
		// Time
		// and Application End Date & Time
		schData.appStartDate = DateFunctions.getDateAfterToday(30);
		schData.appEndDate = DateFunctions.getDateAfterToday(35);
		schData.fieldStartDate = DateFunctions.getDateAfterToday(5);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(10);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg5, actualMessage);
		
		// Check error message: Sales Channel's specified From Date & Time and
		// To
		// Date & Time are not within the specified Application Start Date &
		// Time
		// and Application End Date & Time
		schData.appStartDate = DateFunctions.getDateAfterToday(22);
		schData.appEndDate = DateFunctions.getDateAfterToday(30);
		schData.fieldStartDate = DateFunctions.getDateAfterToday(5);
		schData.fieldEndDate = DateFunctions.getDateAfterToday(10);
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
			exceptionList.add(new ErrorOnPageException("Expect error message should be: " + expectedMessage 
					+ ", but actually error message is: " + actualMessage));
		}
	}

}
