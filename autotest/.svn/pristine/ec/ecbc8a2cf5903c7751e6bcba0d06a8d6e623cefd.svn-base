package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.changeres;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeReservationConfirmPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCheckoutPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRedeemableVouchersListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: P
 * when set "Redirection To Refund (web)" of voucher program to No, the refund method is voucher.
 * Firstly change date reservation to generate voucher refund, the refund method is 'voucher';
 * Second use previous voucher and credit card to pay another reservation.
 * @Preconditions:
 * Site 018 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Cancellation: $10.00
 * 
 * d_fin_use_attr_fee_sched, id=3620
 * d_fin_tran_fee_sched, id=3240, 3250
 * d_fin_voucher_program, id=100
 * 
 * @SPEC: Reservation change with No Redirection to Refund(Web) of voucher program to the reservation [TC:034898] 
 * @Task#: AUTO-1407
 * 
 * @author SWang
 * @Date  Jan 24, 2013
 */
public class PaymentIsVoucherAndCreditCard extends RecgovTestCase{
	private Voucher vc = new Voucher();

	private UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
	private UwpRedeemableVouchersListPage redeemableVoucherPg = UwpRedeemableVouchersListPage.getInstance();
	private UwpChangeReservationConfirmPage changeResConfirmPg = UwpChangeReservationConfirmPage.getInstance();

	private FeeValidationData useFeeData1 = new FeeValidationData();
	private FeeValidationData useFeeData2 = new FeeValidationData();
	private FeeValidationData transFeeData1 = new FeeValidationData();
	private FeeValidationData transFeeData2 = new FeeValidationData();
	private FeeScheduleInformation feeInfo = FeeScheduleInformation.getInstance();
	private BigDecimal totalPriceBeforeCancellation, totalPriceAfterCancellation;
	private String paymentMethod, refundMethod;
	private String[] resNums = new String[2];

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 20, false, bd.siteID);

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

		//Go to REC.GOV web site to make 3-days reservation order
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		resNums[0] = web.checkOutShoppingCart(pay);

		//Change date to 2-days to generate voucher refund
		web.gotoResDetailsFromConfirm(1);
		web.changeDateOrTransferSite(newBd);

		//Get voucher id and amount info
		web.gotoRedeemableVouchersPg();
		vc = redeemableVoucherPg.getVoucherInfo();

		//Make another 3-days reservation with voucher and credit card
		bd.arrivalDate = DateFunctions.getDateAfterGivenDay(bd.arrivalDate, 3);
		newBd.arrivalDate = bd.arrivalDate;
		web.makeReservationToCart(bd);
		resNums[1] = this.checkOutShoppingCart(pay);

		//Go to reservation details page to check payment details info
		web.gotoResDetailsFromConfirm(1);
		resDetailsPg.verifyPaymentDetailsInResDetailsPg(paymentInfoBeforeCancellation, true);

		//Change date to change reservation details page to check reservation and voucher refund info
		web.changeDateOrTransferSiteToChangeResConfirmPage(newBd);
		changeResConfirmPg.verifyFinallyTotalPayment(totalPriceAfterCancellation, BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		changeResConfirmPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), paymentMethod, refundMethod);
		web.finishUpdateResFromChangeResDetailsPg(pay);

		web.cancelReservation(resNums, bd.contractCode, "Confirmed", pay);
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

		bd.lengthOfStay = "3";
		bd.siteNo = "018";
		bd.siteID = "89134";
		bd.isUnifiedSearch=isUnifiedSearch();

		newBd.isChangingDateOnly = true;
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "2";

		paymentMethod = "Credit Card";
		refundMethod = "Voucher";
	}

	/**
	 * Initial fee data
	 * @param lengthOfStay
	 * @param isChangingDate  true: calculate fee when changing date
	 *                        false: calculate fee before changing date
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

	/**
	 * Check out shopping cart with voucher and credit card
	 * @param pay
	 * @return
	 */
	public String checkOutShoppingCart(Payment pay) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		logger.info("Check out shopping cart with voucher and credit card.");
		shoppingCart.waitLoading();
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();

		//Pay by voucher
		checkoutPg.clickVoucherByID(vc.voucherId);
		checkoutPg.waitLoading();

		if(checkoutPg.getCreditCardAmount().equalsIgnoreCase("0.00"))
			throw new ItemNotFoundException("Voucher amount is large than order total.");

		//Pay by credit card
		checkoutPg.setupPayment(pay);
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();
		confirmPg.waitLoading();
		confirmPg.verifySuccess();
		confirmPg.verifyBalance("0.00");// verify the balance is 0

		return confirmPg.getAllResNo();
	}
}
