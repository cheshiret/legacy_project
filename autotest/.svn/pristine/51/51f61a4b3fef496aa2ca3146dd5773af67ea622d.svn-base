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

public class VerifyMessage_ExecPeriodOfDate extends InventoryManagerTestCase{
	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage.getInstance();
	LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	private LotterySchedule schData = new LotterySchedule();
	private List<Exception> exceptionList = new ArrayList<Exception>();
	private String actualMessage = "";
	private String msg1 = "An Execution Date and Time for the Lottery Schedule is required. Please enter the Execution Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.",
	               msg2 = "The Execution Period (Execution Date and Execution Freeze Duration) must be after the Application Period (Application Start and End Dates). Please change your entries.",
	               msg3 = "The Execution Period (Execution Date and Execution Freeze Duration) must be before the Inventory Period (Inventory Start and End Dates). Please change your entries.";
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);		
		String lotterySchdID = im.addLotterySchedule(schData, false,"Permit");
		
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchdID);
		
		// Check error message: Execution Date and Time entry is left blank
		schData.executeDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: invalid Execution Date and Time
		schData.executeDate = "%%*&%&*#%!&";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: from the Execution Date to the Execution Date
		// plus
		// the Lottery Execution Freeze Duration) overlaps with the Application
		// Period
		schData.executeDate = DateFunctions.getDateAfterToday(7);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: from the Execution Date to the Execution Date
		// plus the Lottery Execution Freeze Duration) is before the Application
		// Period
		schData.executeDate = DateFunctions.getDateAfterToday(3);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: from the Execution Date to the Execution Date
		// plus the Lottery Execution Freeze Duration) overlaps with the
		// Inventory Period
		schData.executeDate = DateFunctions.getDateAfterToday(23);
		schData.customerAcceptFromDate = DateFunctions.getDateAfterToday(26);
		schData.customerAcceptToDate = DateFunctions.getDateAfterToday(27);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);
		
		// Check error message: from the Execution Date to the Execution Date
		// plus the Lottery Execution Freeze Duration) is after the Inventory
		// Period
		schData.executeDate = DateFunctions.getDateAfterToday(30);
		schData.customerAcceptFromDate = DateFunctions.getDateAfterToday(32);
		schData.customerAcceptToDate = DateFunctions.getDateAfterToday(33);
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg3, actualMessage);	
		
		if (exceptionList.size() > 0) {
			for (Exception e : exceptionList) {
				e.printStackTrace();
				logger.error(e.getMessage());
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
