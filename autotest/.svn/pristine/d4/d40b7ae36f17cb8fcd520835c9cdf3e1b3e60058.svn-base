package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.cancellation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.TransCode;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * Customer pay less fee amount than the expected. Check payment info especially balance and refund info
 * in reservation details page, cancel reservation page and cancellation completed page before and after canceling reservation
 * @Preconditions:
 * Site 016 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Cancellation: $10.00
 * Penalty: Cancellation, Percent, 100
 * 
 * d_fin_use_attr_fee_sched, id=3600
 * d_fin_tran_fee_sched, id=3200 3210
 * d_fin_add_fee_penalty, id=1470
 * d_fin_voucher_program, id=100
 * 
 * @SPEC: Site cancellation when refund is negative [TC:034892]
 * @Task#: AUTO-1407
 * 
 * @author Swang
 * @Date  Jan 22, 2013
 */
public class NegativeRefund extends RecgovTestCase{
	private UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
	private UwpCancellationPage cancelResPg = UwpCancellationPage.getInstance();
	private UwpCancellationConfirmationPage cancelConfirmPg = UwpCancellationConfirmationPage.getInstance();

	private FeeValidationData useFeeData, transFeeData1, transFeeData2, penaltyData;
	private FeeScheduleInformation feeInfo = FeeScheduleInformation.getInstance();
	private BigDecimal totalPriceBeforeCancellation, totalPriceAfterCancellation;

	public void execute(){
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.valueOf(bd.lengthOfStay), false, bd.siteID);

		//Calculate payment info by system
		useFeeData = feeInfo.getFeeScheduleInfo(useFeeData, "2", schema);
		transFeeData1 = feeInfo.getFeeScheduleInfo(transFeeData1, "4",schema);
		transFeeData2 = feeInfo.getFeeScheduleInfo(transFeeData2, "4", schema);
		penaltyData = feeInfo.getPenaltyScheduleInfo(penaltyData, schema);
		Map<String, BigDecimal> paymentInfoBeforeCancellation = this.calculatePaymentBySystem(false);
		Map<String, BigDecimal> paymentInfoAfterCancellation = this.calculatePaymentBySystem(true);
		totalPriceBeforeCancellation = paymentInfoBeforeCancellation.get(FeeType.KEY_TOTAL_PRICE);
		totalPriceAfterCancellation = paymentInfoAfterCancellation.get(FeeType.KEY_TOTAL_PRICE);
		
		//Make reservation
		web.invokeURL(url);	
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		String resNum = web.checkOutShoppingCart(pay);

		//Go to reservation details page to check payment details info, especially balance=$0.00
		web.gotoResDetailsFromConfirm(1);
		resDetailsPg.verifyPaymentDetailsInResDetailsPg(paymentInfoBeforeCancellation, true);

		//Go to cancel reservation page check payment info, especially balance, owing $10.00
		web.cancelResToCancelResPg();
		cancelResPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), paymentInfoAfterCancellation.get(FeeType.KEY_TRANS_FEES), paymentInfoBeforeCancellation.get(FeeType.KEY_USE_FEES), totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));

		//Go to cancellation Completed page to check payment info, especially balance, paid $10.00
		web.cancelResToCancellationCompletedPgFromCancelResPg(pay);
		cancelConfirmPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), paymentInfoAfterCancellation.get(FeeType.KEY_TRANS_FEES), paymentInfoBeforeCancellation.get(FeeType.KEY_USE_FEES), totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));

		//Go to reservation details page again to check payment info, especially balance $0.00 because pay balance in previous step 
		web.gotoReservationDetailPageFromHomePage(resNum, bd.contractCode, "Cancelled");
		resDetailsPg.verifyRefundDetailsInResDetailsPg(paymentInfoAfterCancellation, false, totalPriceAfterCancellation);//totalPriceBeforeCancellation

		web.signOut();
	}		

	public void wrapParameters(Object[] param) {
		url=TestProperty.getProperty(env+WEB_URL_RECGOV);
		email=web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		bd.parkId = "71560";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //BLUE BAY
		bd.contractCode = "NRSO";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.siteNo = "016";
		bd.siteID = "89132";
		bd.isUnifiedSearch=isUnifiedSearch();

		//Initialize fee data
		useFeeData = new FeeValidationData();
		transFeeData1 = new FeeValidationData();
		transFeeData2 = new FeeValidationData();
		penaltyData = new FeeValidationData();
		
		useFeeData.productID = bd.siteID;
		useFeeData.arriveDate = bd.arrivalDate;
		useFeeData.departureDate = DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.valueOf(bd.lengthOfStay));
		useFeeData.nights.add(bd.lengthOfStay);
		useFeeData.time.add(useFeeData.arriveDate);

		transFeeData1.productID = bd.siteID;
		transFeeData1.arriveDate = bd.arrivalDate;
		transFeeData1.tranType = TRANTYPE_RESERVATION;
		transFeeData1.departureDate = useFeeData.departureDate;
		transFeeData1.nights.add(bd.lengthOfStay);

		transFeeData2.productID = bd.siteID;
		transFeeData2.arriveDate = bd.arrivalDate;
		transFeeData2.tranType = TRANTYPE_CANCELLATION;
		transFeeData2.departureDate = useFeeData.departureDate;
		transFeeData2.nights.add(bd.lengthOfStay);

		penaltyData.productID = bd.siteID;
		penaltyData.arriveDate = bd.arrivalDate;
		penaltyData.departureDate = useFeeData.departureDate;
		penaltyData.tranType = TRANTYPE_CANCELLATION;
	}

	/**
	 * Calculate total price
	 * @param afterCancellation true:calculate payment after cancellation
	 *                          false:calculate payment before cancellation
	 * @return
	 */
	private Map<String, BigDecimal> calculatePaymentBySystem(boolean afterCancellation){
		Map<String, BigDecimal> paymentInfo = new HashMap<String, BigDecimal>();
		List<BigDecimal> useFee = feeCal.calculateBaseFee(useFeeData, 0, true);
		List<BigDecimal> transFee = feeCal.calculateTransactionFee(transFeeData1, null);
		paymentInfo = feeCal.calculateTotalPrice(null, useFee, transFee, null, null);

		if(afterCancellation){
			transFee.addAll(feeCal.calculateTransactionFee(transFeeData2, null));
			Map<String, List<Object>> penaltyFee = feeCal.calculateFeePenalty(penaltyData, TransCode.CANCELLATION, null, paymentInfo.get(FeeType.KEY_USE_FEES));
			paymentInfo = feeCal.calculateTotalPrice(null, null, transFee, null, penaltyFee);
		}

		return paymentInfo;
	}
}
