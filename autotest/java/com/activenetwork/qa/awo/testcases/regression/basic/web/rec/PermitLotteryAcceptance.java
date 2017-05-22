package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import java.util.Calendar;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampingLotteryApplicationListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify the lottery acceptance feature.
 * 
 * 1. Make sure there is an active Lottery program exists in System;
 * 2. Make sure there is an active corresponding Lottery Schedule exists in System;
 * 3. Update the lottery schedule 'Application Start Date Time' as current time, to make the permit marked from 'A' to 'L';
 * 4. Make the permit lottery application in Rec.gov site;
 * 5. After finishing above step, update the 'Application End Date Time' as current time, to make the schedule finished effected,
 * 		and also update the 'Execution Date Time' as next minute to let System automatically executes the lottery schedule;
 * 6. After lottery schedule execution, update the 'Execution Date Time' as **2 days ago(or any time what is earlier than the 'Customer Acceptance Deadline' set in Lottery Schedule)**
 * 		to trigger the 'Acceptance Reservation' button appears.
 * 7. Accept the lottery, make a regular permit order, final cancel it.
 * 
 * @Preconditions: 
 * Case will do the below two checks by itself
 * 1. Check whether lottery program exists or not; if not, create a new permit lottery program
 * 2. Create a new permit lottery schedule
 * @SPEC:
 * @Task#: AUTO-912
 * 
 * @author qchen
 * @Date  Feb 15, 2012
 */
public class PermitLotteryAcceptance extends RecgovTestCase {
	private InventoryManager im = InventoryManager.getInstance();
	private Lottery lottery = new Lottery();
	private LotterySchedule lotterySchdl = new LotterySchedule();
	private BWCooperator bw = BWCooperator.getInstance();
	private PermitInfo permit = new PermitInfo();
	private String lotteryAppStatus = "Awarded - Payment Unconfirmed";
	private TimeZone timeZone = null;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		/**
		 * prepare lottery program and lottery schedule
		 */
		im.gotoLotterySearchPage();
		//1. check lottery program exists or not; if not, add a new one
		if(lottery.id.length() < 1) {//lottery id got in wrapParameters method
			//2. add a new lottery program
			lottery.id = im.addNewLottery(lottery);
		} else {
			this.gotoLotteryDetailPage(lottery.id);
		}
		
		//3. check whether there is any active lottery schedule exists or not; if yes, deactivate it
		im.deactivateLotterySchedulesFromDB(schema, lottery.id);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		
		//4. add a new lottery schedule
		lotterySchdl.id = im.addLotterySchedule(lotterySchdl, true,"Permit");
		//5. update the lottery 'Application Start Date' as current time
		im.updateLotteryScheduleAppStartDateTime(schema, lotterySchdl.id, DateFunctions.getToday("yyyy/MM/dd h:m aa", timeZone));
		im.logoutInvManager();
		
		/**
		 * sign in Rec.gov to make a permit lottery application
		 */
		web.invokeURL(url);
		web.signIn(email, pw);
		//6. make a permit lottery application
		bw.makePermitLotteryToOrderDetailPage(permit);
		bw.fillInLotteryDetails(permit);
		String lotteryAppId = bw.checkOutCart(pay);
		
		//7. update lottery 'Application End Date' to make it invalid
		String currentTime = DateFunctions.getToday("yyyy/MM/dd h:m aa", timeZone);
		web.updateLotteryScheduleAppEndDateTime(schema, lotterySchdl.id, currentTime);
		
		//8. update lottery 'Execution Date' as Next minute to let System automatically run this lottery schedule by itself
		String executionDateTime = this.addTimeMinute(currentTime, 1);
		web.updateLotteryScheduleExecutionDateTime(schema, lotterySchdl.id, executionDateTime);
		Browser.sleep(120);
		
		//9. after lottery execution, update lottery schedule 'Execution Date' as 2 days before the original execution date
		//to trigger Customer Acceptance Deadline feature
		String executionDateTime_2daysAgo = DateFunctions.getDateAfterGivenDay(DateFunctions.getToday(timeZone), -3);
		web.updateLotteryScheduleExecutionDateTime(schema, lotterySchdl.id, executionDateTime_2daysAgo);
		web.signOut();
		
		/**
		 * verify the check points: 
		 * 		in lottery application list page, this lottery application status should be 'Awarded - Payment Unconfirmed';
		 * 		and, the 'Accept Reservation' button should be displayed at lottery application detail page.
		 */
		web.signIn(email, pw);
//		web.gotoLotteryApplicationsListPage();
		this.verifyLotteryApplicationStatus(lotteryAppId);
		//accept lottery application
		web.gotoLotteryApplicationDetailPage(lotteryAppId, permit.contractCode, true);
		permit.orderID = web.acceptPermitLottery(permit,pay);
		
		//cancel permit order
		bw.cancelPermitOrders(true, permit.orderID);
		web.signOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		url = TestProperty.getProperty(env + WEB_URL_RECGOV);
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		timeZone = TimeZone.getTimeZone(DataBaseFunctions.getTimeZoneString(""));//Lottery always uses US/Eastern time zone
		
		lottery.name = "Four Rivers Lottery - Salmon River " + DateFunctions.getCurrentYear();
		lottery.id = im.getLotteryId(schema, lottery.name);
		lottery.status = OrmsConstants.ACTIVE_STATUS;
		lottery.permitTypes = new String[]{"River Launch Permit (Private)"};
		
		String temp1[] = DateFunctions.getDateAfterToday(2, "yyyy/MM/dd h:m aa", timeZone).split(" ");
		lotterySchdl.appStartDate = temp1[0];
		lotterySchdl.appStartDateHour = Integer.parseInt(temp1[1].split(":")[0]) == 12? "0" : temp1[1].split(":")[0];
		lotterySchdl.appStartDateMinute = temp1[1].split(":")[1];
		lotterySchdl.appStartDateAM = temp1[2].equalsIgnoreCase("am") ? true:false;
		lotterySchdl.appStartDateAM = true;
		
		String temp2[] = DateFunctions.getDateAfterToday(3, "yyyy/MM/dd h:m aa", timeZone).split(" ");
		lotterySchdl.appEndDate = temp2[0];
		lotterySchdl.appEndDateHour = Integer.parseInt(temp2[1].split(":")[0]) == 12 ? "0" : temp2[1].split(":")[0];
		lotterySchdl.appEndDateMinute = temp2[1].split(":")[1];
		lotterySchdl.appEndDateAM = temp2[2].equalsIgnoreCase("am") ? true:false;
		
		lotterySchdl.web = true;
		
		String temp3[] = DateFunctions.getDateAfterToday(4, "yyyy/MM/dd h:m aa", timeZone).split(" ");
		lotterySchdl.executeDate = temp3[0];
		lotterySchdl.executeHour = temp3[1].split(":")[0];
		lotterySchdl.executeHour = Integer.parseInt(temp3[1].split(":")[0]) == 12 ? "0" : temp3[1].split(":")[0];
		lotterySchdl.executeMin = temp3[1].split(":")[1];
		lotterySchdl.executeAM = temp3[2].equalsIgnoreCase("am") ? true:false;
		
		lotterySchdl.freezeDuration = "1";
		lotterySchdl.customerAcceptanceDeadline = "2";//if changing this value, should also change the negative value in Step 9.
		
		lotterySchdl.invStartDate = DateFunctions.getDateAfterToday(30);
		lotterySchdl.invEndDate = DateFunctions.getDateAfterToday(35);
		lotterySchdl.appHeaderMsg = "Automation basic regression";
		
		permit.facility = "SALMON RIVER";
		permit.permitType = "River Launch Permit (Private)";
		permit.entrance = "01 Salmon River";
		permit.isRange = false;
		permit.entryDate = DateFunctions.getDateAfterGivenDay(lotterySchdl.invStartDate, 1);
		permit.isLotteryOrder = true;
		permit.isUnifiedSearch = true;
		permit.parkId = "75533";
		permit.contractCode = "NRSO";
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, 1);
		permit.personTypes = new String[]{"Adult/Youth"}; 
		permit.personNums = new String[]{"3"};
		permit.personNums = new String[]{String.valueOf(permit.groupMembers.getSize())};
	}
	
	public String addTimeMinute(String baseTime, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(DateFunctions.parseDateString(baseTime));
		c.add(Calendar.MINUTE, offset);
		return DateFunctions.formatDate(c.getTime(), "yyyy/MM/dd h:m aa");
	}
	
	private void gotoLotteryDetailPage(String id) {
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();
		
		lotterySearchPg.setLotteryID(id);
		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		lotterySearchPg.clickLotteryID(id);
		lotteryDetailPg.waitLoading();
	}
	
	/**
	 * Verify whether the lottery application status correct or not
	 * @param id
	 */
	private void verifyLotteryApplicationStatus(String id) {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpCampingLotteryApplicationListPage lotteryApplicationPage = UwpCampingLotteryApplicationListPage.getInstance();
		
		String status = lotteryApplicationPage.getLotteryOrderStatus(id);
		if(!status.equals(lotteryAppStatus)) {
			Browser.sleep(120);
			int count = 0;
			do{
				accountPanel.gotoLotteryApplications();
				lotteryApplicationPage.waitLoading();
				status = lotteryApplicationPage.getLotteryOrderStatus(id);
				count ++;
				Browser.sleep(1);
			} while(!status.equals(lotteryAppStatus) && count < 20);
		}
		if(status.equals(lotteryAppStatus)) {
			logger.info("Lottery application status really is: " + status);
		} else {
			throw new ErrorOnPageException("Lottery application status is wrong. Expected value is '" + lotteryAppStatus + "', but actual value is '" + status + "'.");
		}
	}
}
