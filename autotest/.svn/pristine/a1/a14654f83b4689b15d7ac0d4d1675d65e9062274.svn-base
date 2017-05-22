package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.ticketinglottery;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo.lotteryChoice;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the the minimal value equals full payment even when a minimal pay has been set for ticket lottery
 * @Preconditions:
 * 1. Create tour 'Lottery Tour For Ticked Finance' [d_inv_create_tour ID=1080] 
 * 2. Add tour inventory [d_inv_tour_inventory ID=1160] 
 * 3. Add Use fee for Ticket for 'Lottery Tour For Ticked Finance'  [d_fin_ticket_fee_sched ID=1000] 
 * 4. Create lottery program 'LotteryForTicketToBuy', the lottery should be on going [d_inv_new_lottery_program ID=520] 
 * 5. Add transaction fees (Submit Lottery Entry) for lottery 'LotteryForTicketToBuy' [d_fin_base_fee_sched ID=450] 
 * 6. Add feature(AcceptAwardedReservation/RevokeAwardedPermit/%Lottery%) for role 'NRRS - Call Center Manager - Auto' in Call manager: 
 * 		[d_assign_feature ID=3530] 
 * 		[d_assign_feature ID=3540] 
 * 		[d_assign_feature ID=3550] 
 * 7. A minimal pay has been set up for the lottery 'LotteryForTicketToBuy'
 * @LinkSetUp:  none
 * @SPEC: Calculate Minimum to Confirm - Ticketing Lottery [TC:052869]
 * @Task#: Auto-1452
 * @author Phoebe Chen
 * @Date  March 25, 2013
 */
public class FullPaymentEvenMinimumPaySet extends VenueManagerTestCase{
	private Lottery lottery = new Lottery();
	private String facilityId;
	OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
	@Override
	public void execute() {
		setupMinPayment();
		//Check minimal payment has been set up for lottery product
		this.checkPreparedDataForFeeSchdAndMiniRule(schema, facilityId, lottery.name);
		//login venue manager
		vm.loginVenueManager(login);
		
		//Make order for the ticket lottery
		vm.makeLotteryTicketOrderToCart(ticket,cust);
		this.verifyThatMinimalPayIsFullPay();
		String ordNum = vm.processOrderCartToSummaryPg(pay);
		vm.finishOrder();
		
		//clear up
		vm.gotoLotteryApplicationDetailPage(ordNum);
		vm.voidLotteryToCart();
		vm.processOrderCart(pay);
		
		vm.logoutVenueManager();
		
	}

	private void verifyThatMinimalPayIsFullPay() {
		BigDecimal totalPrice = ordCartPg.getTotalPrice();
		BigDecimal minimunPayment = new BigDecimal(ordCartPg.getFeeAmountValue("Minimum Payment Due"));
		
		// compare to
		if (totalPrice.compareTo(minimunPayment) != 0) {
			throw new ErrorOnDataException(
					"minimun payment should equals total price...");
		}
		logger.info("Minimun payment is set as full payment even minimum rule has been set up!");
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
		ticket.startDate = DateFunctions.getDateAfterGivenDay(vm.queryPermitLotteryInventoryStartDate(schema, lottery.name),2); //"1/20/2014"
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

	}
	
	private void checkPreparedDataForFeeSchdAndMiniRule(String schema,
			String locID, String prdName) {
		// get Minimum Payment rule from DB
		List<String[]> minPaymentRules = vm.getMinimumPaymentRuleInfo(schema,
				PRDCAT_LOTTERY, locID, null, prdName);
		if (minPaymentRules.size() != 1) {
			throw new ErrorOnPageException(
					"Please check minimum payment rule info, need to prepare minimum payment rule.");
		}
	}
	
	/**Setup minimum payment for the product */
	private void setupMinPayment() {
		String prdID = vm.getProductID("Product Name", lottery.name, null, schema);
		String id = vm.setMinPaymentCFM(schema, null, facilityId, null, prdID, PRDCAT_LOTTERY, null, SALESCHAN_ALL);
		vm.setMinPaymentEntry(schema, id, FEETYPE_USEFEE, RULE_TYPE_PERCENT, "0.5");
	}
}
