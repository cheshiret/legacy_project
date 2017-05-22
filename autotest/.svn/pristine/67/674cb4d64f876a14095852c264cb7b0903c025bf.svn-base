package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.pagestrigger.rec;

import java.util.Calendar;

import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampingLotteryApplicationListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class LotteryApplicationsPageTrigger extends RecgovTestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		
		//selects the \ufffdLottery Applications\ufffd link from the \ufffdMy Reservation & Account\ufffd page
		web.signIn(email, pw); 
		this.gotoLotteryApplicationsPg();
		
		//selects \ufffdLottery Application\ufffd link from the \ufffdReservations\ufffd section in Account Overview Page
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection("Lottery Applications");
		
		//selects \ufffdView My Current Reservations\ufffd link from the Lottery Order Confirmation page
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		web.fillInLotteryDetails(bd, od);// fill in lottery application details page 1 and 2
		String resID=web.checkOutFreeResCart(); // check out free shopping cart
		web.gotoCurrentReservationsPage();//from order confirmation page
		this.gotoLotteryApplicationsPg();
		web.accountPanelVerification(resID);
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");		
		
		bd.state = "South Carolina";
		bd.park = "CLARKS HILL";
		bd.conType = "Federal";

		bd.lengthOfStay = "3";
		bd.contractCode = "NRSO";
		bd.parkId="71090";
		bd.loop = "ELIJ";
		bd.siteNo = "3";
		bd.siteID = "67662";
		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate = this.getArrivalDateForLottery();
		bd.isUnifiedSearch=isUnifiedSearch();
		
	}
	
	/**Go to Lottery Applications page from account panel.*/
	public void gotoLotteryApplicationsPg() {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpCampingLotteryApplicationListPage lotteryList = 
														UwpCampingLotteryApplicationListPage.getInstance();
		logger.info("Go to Lottery Applications page from account panel.");
		
		accountPanel.gotoLotteryApplications();
		lotteryList.waitLoading();
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.TUESDAY, "EEE MMM d yyyy");
		return lotDate;		
	}
}
