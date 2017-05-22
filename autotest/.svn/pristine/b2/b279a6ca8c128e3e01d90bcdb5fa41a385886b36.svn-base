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
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class VerifyMessage_InvPeroidOfDate extends InventoryManagerTestCase{
	LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
	LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage.getInstance();
	LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	LotterySchedule schData = new LotterySchedule();
	private List<Exception> exceptionList = new ArrayList<Exception>();
	private String actualMessage = "";
	private String msg1 = "An Inventory Start Date for the Lottery Schedule is required. Please enter the Inventory Start Date using the format Ddd Mmm dd yyyy in the field provided.",
	               msg2 = "An Inventory End Date for the Lottery Schedule is required. Please enter the Inventory End Date using the format Ddd Mmm dd yyyy in the field provided.",
	               msg3 = "End date should be greater than or equal to start date";
	
	public void execute() {		
		im.loginInventoryManager(login);
		im.gotoLotteryDetailsPageFromHomePage(lottery);		
		String lotterySchdID = im.addLotterySchedule(schData, false,"Permit");
		
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchdID);
		
		// Check error message: Inventory Start Date is left blank.
		schData.invStartDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		// Check error message: invalid Inventory start date
		schData.invStartDate = "&*^%^&$^aaf";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg1, actualMessage);
		
		schData.invStartDate = DateFunctions.getDateAfterToday(20);
		
		// Check error message: Inventory End Date is left blank.
		schData.invEndDate = "";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: invalid Inventory End Date
		schData.invEndDate = "%$@@$%$@%%@";
		im.editLotterySchedule(schData,"Permit");
		actualMessage = lotteryScheDetailPg.getErrorMessage();
		this.verifyErrorMsg(msg2, actualMessage);
		
		// Check error message: Start Date is greater than the entry for
		// Inventory End Date
		schData.invStartDate = DateFunctions.getDateAfterToday(25);
		schData.invEndDate = DateFunctions.getDateAfterToday(20);
		actualMessage = this.getInventoryStartDateGreateThanEndDateMsg(schData);
		this.verifyErrorMsg(msg3, actualMessage);		
		
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
	
	private String getInventoryStartDateGreateThanEndDateMsg(LotterySchedule editSchedule) {
		ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();
		alertPg.setDismissible(false);
		
		lotteryScheDetailPg.setInventoryStartDate(editSchedule.invStartDate,"Permit");
		lotteryScheDetailPg.clickInvPeriodSpaces();
		String txt = lotteryScheDetailPg.setInvalidInventoryEndDate(editSchedule.invEndDate,"Permit");
		if(!StringUtil.isEmpty(txt)){
			return txt;
		}
		lotteryScheDetailPg.clickInvPeriodSpaces();
		Object pages = browser.waitExists(lotteryScheDetailPg,alertPg);
		if(pages == lotteryScheDetailPg){
			lotteryScheDetailPg.clickAppSpaces();
			alertPg.waitLoading();
		}
		
		String alertText = alertPg.text();
		alertPg.clickOK();
		lotteryScheDetailPg.waitLoading();
		
		return alertText;
	}

}
