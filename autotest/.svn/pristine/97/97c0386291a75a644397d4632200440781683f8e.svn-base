package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.cancellation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
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
 * When using park doesn't have voucher program, check payment and refund info
 * in reservation details page, cancel reservation page and cancellation completed page before and after canceling reservation
 * @Preconditions:
 * Site 005 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Cancellation: $10.00
 * 
 * d_fin_use_attr_fee_sched, id=3630
 * d_fin_tran_fee_sched, id=3260, 3270
 * 
 * @SPEC: Normal cancellation [TC:034884]
 * @Task#: AUTO-1407
 * 
 * @author SWang
 * @Date  Jan 28, 2013
 */
public class NoVoucherProgram extends RecgovTestCase{
	private UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
	private UwpCancellationConfirmationPage cancelConfirmPg = UwpCancellationConfirmationPage.getInstance();
	private UwpCancellationPage cancellationPg = UwpCancellationPage.getInstance();

	private FeeValidationData useFeeData = new FeeValidationData();
	private FeeValidationData transFeeData1 = new FeeValidationData();
	private FeeValidationData transFeeData2 = new FeeValidationData();
	private FeeScheduleInformation feeInfo = FeeScheduleInformation.getInstance();
	private BigDecimal totalPriceBeforeCancellation, totalPriceAfterCancellation;
	private String method;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);

		//Calculate payment info by system
		useFeeData = feeInfo.getFeeScheduleInfo(useFeeData, "2", schema);
		transFeeData1 = feeInfo.getFeeScheduleInfo(transFeeData1, "4", schema);
		transFeeData2 = feeInfo.getFeeScheduleInfo(transFeeData2, "4", schema);
		Map<String, BigDecimal> paymentInfoBeforeCancellation = this.calculatePaymentBySystem(false);
		Map<String, BigDecimal> paymentInfoAfterCancellation = this.calculatePaymentBySystem(true);
		totalPriceBeforeCancellation = paymentInfoBeforeCancellation.get(FeeType.KEY_TOTAL_PRICE);
		totalPriceAfterCancellation = paymentInfoAfterCancellation.get(FeeType.KEY_TOTAL_PRICE);

		//Go to REC.GOV web site to make reservation
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		String resNum = web.checkOutShoppingCart(pay);

		//Go to reservation details page to check payment details info
		web.gotoResDetailsFromConfirm(1);
		resDetailsPg.verifyPaymentDetailsInResDetailsPg(paymentInfoBeforeCancellation, true);

		//Go to cancel reservation page to check transaction details info and refund detail info
		web.cancelResToCancelResPg(); 
		cancellationPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation, totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		cancellationPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), method, method);

		//Go to cancellation completed to check transaction details info and refund detail info
		web.cancelResToCancellationCompletedPgFromCancelResPg(pay);
		cancelConfirmPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation, totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		cancelConfirmPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), method, method);

		//Go to reservation details page to check refund details info
		web.gotoReservationDetailPageFromHomePage(resNum, bd.contractCode, "Cancelled");
		resDetailsPg.verifyRefundDetailsInResDetailsPg(paymentInfoAfterCancellation, true);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url=TestProperty.getProperty(env+WEB_URL_RECGOV);
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		bd.parkId = "73721";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //SKULL CREEK
		bd.contractCode = "NRSO";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.siteNo = "005";
		bd.siteID = "134480";
		bd.isUnifiedSearch=isUnifiedSearch();

		method = "Credit Card";
		
		//Initial fee data
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
	}

	/**
	 * Calculate payment 
	 * @param afterCancellation  true: after cancellation, only have transaction fee info
	 *                           false: before cancellation, has use and transaction fee info
	 * @return
	 */
	private Map<String, BigDecimal> calculatePaymentBySystem(boolean afterCancellation){
		Map<String, BigDecimal> paymentInfo = new HashMap<String, BigDecimal>();
		List<BigDecimal> useFee = feeCal.calculateBaseFee(useFeeData, 0, true);
		List<BigDecimal> transFee = feeCal.calculateTransactionFee(transFeeData1, null);

		if(afterCancellation){
			transFee.addAll(feeCal.calculateTransactionFee(transFeeData2, null));
			paymentInfo = feeCal.calculateTotalPrice(null, null, transFee, null, null);
		}else{
			paymentInfo = feeCal.calculateTotalPrice(null, useFee, transFee, null, null);
		}

		return paymentInfo;
	}
}
