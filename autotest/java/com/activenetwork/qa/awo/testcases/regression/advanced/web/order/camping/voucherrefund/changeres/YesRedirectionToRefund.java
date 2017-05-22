package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.changeres;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeReservationConfirmPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * when set "Redirection To Refund (web)" of voucher program to Yes, the refund method is same as the payment method.
 * Check payment and refund info in reservation details page, cancel reservation page and cancellation completed page before and after changing date reservation
 * @Preconditions:
 * Site 007 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Shorten Stay Leave Earlier: $10.00
 * 
 * d_fin_use_attr_fee_sched, id=3700
 * d_fin_tran_fee_sched, id=3350, 3360
 * d_fin_voucher_program, id=110
 * 
 * @SPEC: Reservation change with Yes Redirection to Refund(Web) of voucher program to the reservation [TC:034900] 
 * @Task#: AUTO-1407
 * 
 * @author QA
 * @Date  Jan 29, 2013
 */
public class YesRedirectionToRefund extends RecgovTestCase{
	private UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
	private UwpChangeReservationConfirmPage changeResConfirmPg = UwpChangeReservationConfirmPage.getInstance();

	private FeeValidationData useFeeData1 = new FeeValidationData();
	private FeeValidationData useFeeData2 = new FeeValidationData();
	private FeeValidationData transFeeData1 = new FeeValidationData();
	private FeeValidationData transFeeData2 = new FeeValidationData();
	private FeeScheduleInformation feeInfo = FeeScheduleInformation.getInstance();
	private BigDecimal totalPriceBeforeCancellation, totalPriceAfterCancellation;
	private String method, successfulMes;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.valueOf(bd.lengthOfStay), false, bd.siteID);

		//Calculate payment info by system
		this.initializeFeeData(bd.lengthOfStay, false);
		transFeeData1 = feeInfo.getFeeScheduleInfo(transFeeData1, "4", schema);
		transFeeData2 = feeInfo.getFeeScheduleInfo(transFeeData2, "4", schema);

		useFeeData1 = feeInfo.getFeeScheduleInfo(useFeeData1, "2", schema);
		Map<String, BigDecimal> paymentInfoBeforeCancellation = this.calculatePaymentBySystem(false);
		totalPriceBeforeCancellation = paymentInfoBeforeCancellation.get(FeeType.KEY_TOTAL_PRICE);

		this.initializeFeeData(newBd.lengthOfStay, true);
		useFeeData2 = feeInfo.getFeeScheduleInfo(useFeeData2, "2", schema);
		Map<String, BigDecimal> paymentInfoAfterCancellation = this.calculatePaymentBySystem(true);
		totalPriceAfterCancellation = paymentInfoAfterCancellation.get(FeeType.KEY_TOTAL_PRICE);

		//Go to REC.GOV web site to make reservation
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		web.checkOutShoppingCart(pay);

		//Go to reservation details page to check payment details info
		web.gotoResDetailsFromConfirm(1);
		resDetailsPg.verifyPaymentDetailsInResDetailsPg(paymentInfoBeforeCancellation, true);

		//Make 3-days reservation to 2-days to update reservation details page to check payment info
		web.gotoChangeResPgFromResDetailsPg();
		web.changeDateOrTransferSiteToChangeResDetailsPg(newBd);
		web.gotoChangeResDetailsPgFromUpdateResDetailsPg(bd.equip, bd.equipLength);
		changeResConfirmPg.verifyFinallyTotalPayment(totalPriceAfterCancellation, BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		changeResConfirmPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), method, method);

		//Check successful message and payment info 
		web.finishUpdateResFromChangeResDetailsPg(pay);
		resDetailsPg.verifySuccessMes(successfulMes);
		resDetailsPg.verifyPaymentDetailsInResDetailsPg(paymentInfoAfterCancellation, true);

		//Cancel reservation
		web.cancelReservation(pay);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url=TestProperty.getProperty(env+WEB_URL_RECGOV);
		email=web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		bd.parkId = "72099";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //CAMP SHERMAN CAMPGROUND
		bd.contractCode = "NRSO";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "5";
		bd.siteNo = "007";
		bd.siteID = "283841";
		bd.isUnifiedSearch=isUnifiedSearch();

		newBd.isChangingDateOnly = true;
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "2";

		method = "Credit Card";
		successfulMes = "Your request executed successfully.";
	}

	/**
	 * Initial fee data
	 * @param lengthOfStay
	 * @param isChangingDate  true: Initialize fee data when changing date
	 *                        false: Initialize fee data before changing date
	 */
	private void initializeFeeData(String lengthOfStay, boolean isChangingDate){
		if(isChangingDate){
			useFeeData2.productID = bd.siteID;
			useFeeData2.arriveDate = bd.arrivalDate;
			useFeeData2.departureDate = DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.valueOf(lengthOfStay));
			useFeeData2.nights.add(lengthOfStay);
			useFeeData2.time.add(useFeeData1.arriveDate);
		}else{
			useFeeData1.productID = bd.siteID;
			useFeeData1.arriveDate = bd.arrivalDate;
			useFeeData1.departureDate = DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.valueOf(lengthOfStay));
			useFeeData1.nights.add(lengthOfStay);
			useFeeData1.time.add(useFeeData1.arriveDate);
		}

		transFeeData1.productID = bd.siteID;
		transFeeData1.arriveDate = bd.arrivalDate;
		transFeeData1.tranType = TRANTYPE_RESERVATION;
		transFeeData1.departureDate = useFeeData1.departureDate;
		transFeeData1.nights.add(lengthOfStay);

		transFeeData2.productID = bd.siteID;
		transFeeData2.arriveDate = bd.arrivalDate;
		transFeeData2.tranType = TRANTYPE_SHORTEN_STAY_LEAVE_EARLIER;
		transFeeData2.departureDate = useFeeData1.departureDate;
		transFeeData2.nights.add(lengthOfStay);
	}

	/**
	 * Calculate payment 
	 * @param isChangingDate  true: calculate fee when changing date
	 *                        false: calculate fee before changing date
	 * @return
	 */
	private Map<String, BigDecimal> calculatePaymentBySystem(boolean isChangingDate){
		Map<String, BigDecimal> paymentInfo = new HashMap<String, BigDecimal>();
		List<BigDecimal> transFee = feeCal.calculateTransactionFee(transFeeData1, null);

		if(isChangingDate){
			List<BigDecimal> useFee = feeCal.calculateBaseFee(useFeeData2, 0, true);
			transFee.addAll(feeCal.calculateTransactionFee(transFeeData2, null));
			paymentInfo = feeCal.calculateTotalPrice(null, useFee, transFee, null, null);
		}else{
			List<BigDecimal> useFee = feeCal.calculateBaseFee(useFeeData1, 0, true);
			paymentInfo = feeCal.calculateTotalPrice(null, useFee, transFee, null, null);
		}

		return paymentInfo;
	}
}
