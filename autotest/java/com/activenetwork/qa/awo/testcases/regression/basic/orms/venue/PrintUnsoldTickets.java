/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrPrintTicketsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case used to cover defect-32351, both Positive (these are unsold tickets )and Negative (there are no unsold tickets) scenarios. 
 * @Preconditions: We should add a tour inventory for 'Independence Hall Tour' at 9:00AM, and also make sure that there is no inventory at 6:00PM
 * @SPEC: 
 * @Task#: Auto - 833
 * 
 * @author jwang7
 * @Date  Jan 6, 2012
 */
public class PrintUnsoldTickets extends VenueManagerTestCase {

	private InventoryInfo tourInv = new InventoryInfo();
	private InventoryInfo tourInv1 = new InventoryInfo();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	
	private String errMsg = "";
	
	public void execute() {
	  	// make sure there are unsold tickets from UI
		tourInv.capacity = prepareTourInv(tourInv, true);
		// make sure there are no unsold tickets from UI
		prepareTourInv(tourInv1, false);
		
		vm.loginVenueManager(login);
		// verify unsold ticket print
		verifyUnsoldTicketNum(tourInv, true);
		// and also warning message for no unsold tickets 	
		verifyUnsoldTicketNum(tourInv1, false);
		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		String facilityName = vm.getFacilityName("77815", schema);
		login.location = "NRRS - Venue Supervisor/" + facilityName.toUpperCase();
		
		tourInv.tourName = "Independence Hall - Allocation by Time Interval";
		tourInv.tourValue = "Independence Hall";
		tourInv.startDate = DateFunctions.getToday();
		tourInv.endDate = DateFunctions.getToday();
		tourInv.firstTime = "9:00";
		tourInv.firstTimeStamp = "AM";
		tourInv.lastTime = "10:00";
		tourInv.lastTimeStamp = "AM";
		
		tourInv1.tourName = "Independence Hall - Allocation by Time Interval";
		tourInv1.tourValue = "Independence Hall";
		tourInv1.startDate = DateFunctions.getToday();
		tourInv1.endDate = DateFunctions.getToday();
		tourInv1.firstTime = "6:00";
		tourInv1.firstTimeStamp = "PM";
		tourInv1.lastTime = "8:00";
		tourInv1.lastTimeStamp = "PM";
		
		errMsg = "There are no tour inventories that meet the specified search criteria. Please review the search criteria and re-submit your request.";
	}
	
	private String prepareTourInv(InventoryInfo tourInv, boolean flag){
//		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
//		VnuMgrTourInventoryListPage tourInvListPg = VnuMgrTourInventoryListPage
//				.getInstance();
//
//		logger.info("Prepare tour inventory from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
//		
//		vmHmPg.clickTourInventory();
//		tourInvListPg.waitExists();
//
//		int num = 0;
//		tourInvListPg.searchTourInventory(tourInv);
//		
//		if(!tourInvListPg.verifyNoResult()){
//			num = tourInvListPg.getUnsoldTicketsNum();
//		}
//		
//		if(flag){
//			if(num>0){
//				logger.info("There are "+num+" unsold tickets from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
//			}else{
//				logger.error("There should be tour inventory existed from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
//			}
//		}else{
//			if(num==0){
//				logger.info("There're no unsold tickets from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
//			}else{
//				logger.warn("There should no tour inventory existed from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
//				//TODO should confirm that is it necessary to add a new method to delete tour inventory
////				vm.deleteTourInventory(tourInv);
////				
////				tourInvListPg.searchTourInventory(tourInv);
////				num = tourInvListPg.getUnsoldTicketsNum();
//			}
//		}
		
		//Query DB to get unsold ticket num from tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp
		logger.info("Query DB to get unsold ticket num from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
		db.resetSchema(schema);
		String query = "select TOT_AVAIL from I_TOUR_INV, P_PRD where P_PRD.PRD_ID=I_TOUR_INV.PRD_ID and I_TOUR_INV.ACTIVE_IND=1" +
				" and PRD_NAME='" + tourInv.tourValue +
				"' and to_char(date_time,'yyyy-MM-dd')='" + DateFunctions.formatDate(tourInv.startDate, "yyyy-MM-dd") +
				"' and I_TOUR_INV.DELETED_IND =0 and I_TOUR_INV.start_time between "+Integer.valueOf(tourInv.firstTime.split(":")[0])*60+" and " + Integer.valueOf(tourInv.lastTime.split(":")[0])*60;
		int totalNums = 0;
		logger.info("SQL: "+query);
		List<String> nums = db.executeQuery(query, "TOT_AVAIL");
		for(int i=0; i<nums.size(); i++){
			totalNums = totalNums + Integer.parseInt(nums.get(i));
		}
		
		if(flag){
			if(totalNums>0){
				logger.info("There are "+totalNums+" unsold tickets from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
			}else{
				logger.error("There should be tour inventory existed from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
			}
		}else{
			if(totalNums==0){
				logger.info("There're no unsold tickets from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
			}else{
				logger.warn("There should no tour inventory existed from "+tourInv.firstTime+tourInv.firstTimeStamp+" to "+tourInv.lastTime+tourInv.lastTimeStamp);
				throw new ErrorOnDataException("");
				//TODO should confirm that is it necessary to add a new method to delete tour inventory
			}
		}
		return String.valueOf(totalNums);
	}
	
	private void verifyUnsoldTicketNum(InventoryInfo tourInv, boolean flag){
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		VnuMgrPrintTicketsPage printTicketsPg = VnuMgrPrintTicketsPage.getInstance();
		
		logger.info("Search unsold ticket num for specified date and time.");
		
		vmHmPg.clickPrintTickets();
		printTicketsPg.waitLoading();
		
		printTicketsPg.searchUnsoldTickets(tourInv);
		
		String unsoldNumFromUI = "";
		String errMsgFromUI = "";
		
		if(flag){
			unsoldNumFromUI = printTicketsPg.getUnsoldQty();
			if(unsoldNumFromUI.equalsIgnoreCase(tourInv.capacity)){
				logger.info("Unsold Tickets num displayed correctly on Print Unsold Tickets Page");
			}else{
				logger.error("unsold ticket num should be "+tourInv.capacity + ", but actual is " + unsoldNumFromUI);
				throw new ErrorOnPageException("Unsold Tickets num displayed uncorrectly on Print Unsold Tickets Page");
			}
		}else{
			errMsgFromUI = printTicketsPg.getErrorMessages();
			if(errMsgFromUI.equalsIgnoreCase(errMsg)){
				logger.info("There's no unsold Tickets, and warning message displayed correctly on Print Unsold Tickets Page");
			}else{
				logger.error("There's no unsold Tickets, and warning message should display");
				throw new ErrorOnPageException("Warning message should be:"+errMsg);
			}
		}	
	}
}
