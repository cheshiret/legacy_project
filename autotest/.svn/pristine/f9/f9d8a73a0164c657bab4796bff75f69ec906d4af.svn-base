package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.ticketinglottery;

import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo.lotteryChoice;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeeDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the value and status for RA fee of ticketing lottery 
 * @Preconditions:
 * 1. Create tour 'Lottery Tour For Ticked Finance'[d_inv_create_tour ID=1080] 
 * 2. Add tour inventory [d_inv_tour_inventory ID=1160] 
 * 3. Create lottery program 'LotteryForTicketToBuy', the lottery should be on going  [d_inv_new_lottery_program ID=520] 
 * 4. Add Use fee for Ticket for 'Lottery Tour For Ticked Finance'  [d_fin_ticket_fee_sched ID=1000] 
 * 5. Add transaction fees (Submit Lottery Entry) for lottery 'LotteryForTicket' [d_fin_base_fee_sched ID=450] 
 * 6. Add feature(AcceptAwardedReservation/RevokeAwardedPermit/%Lottery%) for role 'NRRS - Call Center Manager - Auto' in Call manager: 
 * 		[d_assign_feature ID=3530] 
 * 		[d_assign_feature ID=3540] 
 * 		[d_assign_feature ID=3550] 
 * 7.Add RA FEE schedule for lottery 'LotteryForTicketToBuy' [d_fin_ra_fee_schedule = 1370]
 * @LinkSetUp:  none
 * @SPEC:  Price RA Fee-ticketing lottery [TC:053379]
 * @Task#: Auto-1452
 * @author Phoebe Chen
 * @Date  March 25, 2013
 */
public class PriceRAFee extends VenueManagerTestCase{
	private String raSchdID = "";
	private Lottery lottery = new Lottery();
	private FeeScheduleData feeSchData = new FeeScheduleData();
	private OrmsFeeDetailsPage feeDetailPg = OrmsFeeDetailsPage.getInstance();
	private Customer cust = new Customer();
	private String facilityId;
	private String RAFEE_STATUS_PRICED = "2";
	
	@Override
	public void execute() {
		raSchdID = vm.queryFeeScheduleID(feeSchData, schema);
		String raFeeRate = vm.getRAFeeRateFromDB(raSchdID, schema);
		
		//login venue manager
		vm.loginVenueManager(login);
		
		//Make order for the ticket lottery
		vm.makeLotteryTicketOrderToCart(ticket,cust);
		String ordNum = vm.processOrderCartToSummaryPg(pay);
		vm.finishOrder();
	
		vm.gotoLotteryApplicationDetailPage(ordNum);
		vm.gotoFeeDetailPageInRes();
		
		this.verifyRAFeeRateIsCorrect(raFeeRate);
		this.checkRAFeeStatusIsPriced(ordNum);
		vm.gotoLotteryApplicationDetailPageFromFeeDetailPg();
		
		//clear up
		vm.gotoLotteryApplicationDetailPage(ordNum);
		vm.voidLotteryToCart();
		vm.processOrderCart(pay);
	}

	private void verifyRAFeeRateIsCorrect(String raFeeRate) {
		Double rate = feeDetailPg.getRAFee();
		if(!rate.equals(Double.parseDouble(raFeeRate))){
			throw new ErrorOnPageException("The rate for RA Fee is not correct, expect:" + raFeeRate + ",but actual is:" + rate);
		}
		logger.info("RA Fee is equal to the Rate in the RA Fee Schedule, it is correct!");
	}
	
	private void checkRAFeeStatusIsPriced(String ordNum) {
		logger.info("Trying to check Ra fee amount and threshold counter for order -->"+ordNum);
		
		List<HashMap<String,String>> raFeeRecords= vm.getRAFeeInfoFromDB(ordNum,schema);
				
		if(raFeeRecords.size()!=1)
		{
			throw new ErrorOnDataException("There should be only 1 RA Fee(for reservation) record for ordNum-->"+ordNum, "1", String.valueOf(raFeeRecords.size()));
		}
		
		String raFeeStatsuInDB = raFeeRecords.get(0).get("RAFEE_STATUS");
	
		/*check Ra fee status */
		if(StringUtil.compareNumStrings(raFeeStatsuInDB,RAFEE_STATUS_PRICED)!=0)
		{
			throw new ErrorOnDataException("Checking RA fee status of order -->"+ordNum+" failed..",RAFEE_STATUS_PRICED,raFeeStatsuInDB);
		}
		logger.info("Checking RA fee status of order -->"+ordNum+" passed!!!");
	}


	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		facilityId = "71746"; //YOUNG LAKE (SOUTH) CABIN
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		String facility = vm.getFacilityName(facilityId, schema); 
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/" + facility; 
		login.station = "Ash River VC";
		cust.email = vm.getNextEmail();
		lottery.name = "LotteryForTicketToBuy";
		//ticket info
		ticket.tour = "Lottery Tour For Ticked Finance";
		
		ticket.facility = facility;
		ticket.startDate = DateFunctions.getDateAfterGivenDay(vm.queryPermitLotteryInventoryStartDate(schema, lottery.name),1); //"1/20/2014"
		ticket.category = "Organization";
		ticket.quantity = "1";
		
		lotteryChoice firstChoice = new lotteryChoice();
		firstChoice.typeNums = new String[2];
		firstChoice.types = new String[2];
		firstChoice.types[0] = "Adult";
		firstChoice.types[1] = "Child";
		firstChoice.typeNums[0] = "1";
		firstChoice.typeNums[1] = "1";
		firstChoice.tourDate = DateFunctions.formatDate(ticket.startDate,"EEE MMM d yyyy");
		firstChoice.tourTime = "08:00AM";
		firstChoice.deliveryMethod = "Mail Out";
		
		ticket.lotteryChoices.add(firstChoice);

		ticket.voidReason = "Other";
		ticket.note = "QA Automation";

		//initial RA fee schedule info
		feeSchData.activeStatusID = STATUS_ACTIVE;
		feeSchData.locationID = facilityId;  //YOUNG LAKE (SOUTH) CABIN
		feeSchData.productID = vm.getProductID("Product Name", lottery.name, null, schema); //LotteryForTicketToBuy
		feeSchData.productCategoryID = PRDCAT_LOTTERY;
		feeSchData.feeTypeID = FEETYPE_RATRANFEE; //RA Fee
		feeSchData.feeSchdTypeID = FEESCHDTYPE_RAFEESCHD; // RA Threshold schedule
		feeSchData.salesChannelID = SALESCHAN_ALL;
		feeSchData.tranTypeID = TRANTYPE_SUBMIT_LOTTERY_ENTORY; //Advance ticket purchase
		feeSchData.custType = "All";
		
		//make sure did not auto print ticket, in order to clear up
		pay.ticketInfo.autoPrintTicketTurnOn = true;
		pay.ticketInfo.unSelectAutoPrintTicket = true;
	}
}
