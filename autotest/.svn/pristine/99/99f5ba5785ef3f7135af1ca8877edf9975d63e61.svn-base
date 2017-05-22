package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.cancellation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCheckoutPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRedeemableVouchersListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * when set "Redirection To Refund (web)" of voucher program to No, the refund method is voucher.
 * Firstly cancel reservation to generate voucher refund, the refund method is 'voucher';
 * Second use previous voucher to fully pay another reservation.
 * @Preconditions:
 * Site 019 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Cancellation: $10.00
 * 
 * d_fin_use_attr_fee_sched, id=3680
 * d_fin_tran_fee_sched, id=3310, 3320
 * d_fin_voucher_program, id=100
 * 
 * @SPEC: Cancellation when payment is voucher [TC:034895] 
 * @Task#: AUTO-1407
 * 
 * @author SWang
 * @Date  Jan 24, 2013
 */
public class PaymentIsVoucher extends RecgovTestCase{
	private Voucher vc = new Voucher();

	private UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
	private UwpCancellationConfirmationPage cancelConfirmPg = UwpCancellationConfirmationPage.getInstance();
	private UwpRedeemableVouchersListPage redeemableVoucherPg = UwpRedeemableVouchersListPage.getInstance();
	private UwpCancellationPage cancellationPg = UwpCancellationPage.getInstance();

	private FeeValidationData useFeeData1 = new FeeValidationData();
	private FeeValidationData useFeeData2 = new FeeValidationData();
	private FeeValidationData transFeeData1 = new FeeValidationData();
	private FeeValidationData transFeeData2 = new FeeValidationData();
	private FeeScheduleInformation feeInfo = FeeScheduleInformation.getInstance();
	private BigDecimal totalPriceBeforeCancellation, totalPriceAfterCancellation;
	private String paymentMethod, refundMethod;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 20, false, bd.siteID);

		//Calculate payment info by system when length of stay =5
		this.initializeFeeData(bd.lengthOfStay, false);
		transFeeData1 = feeInfo.getFeeScheduleInfo(transFeeData1, "4", schema);
		transFeeData2 = feeInfo.getFeeScheduleInfo(transFeeData2, "4", schema);
		useFeeData1 = feeInfo.getFeeScheduleInfo(useFeeData1, "2", schema);
		Map<String, BigDecimal> paymentInfoBeforeCancellation = this.calculatePaymentBySystem(false, false);
		Map<String, BigDecimal> paymentInfoAfterCancellation = this.calculatePaymentBySystem(true, false);
		totalPriceBeforeCancellation = paymentInfoBeforeCancellation.get(FeeType.KEY_TOTAL_PRICE);
		totalPriceAfterCancellation = paymentInfoAfterCancellation.get(FeeType.KEY_TOTAL_PRICE);

		//Make reservation 5-days reservation
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		String resNum = web.checkOutShoppingCart(pay);
		web.gotoResDetailsFromConfirm(1);

		//Cancel reservation to cancellation completed page to check transaction details and refund details info
		web.cancelReservation(pay);
		cancelConfirmPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation, totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		cancelConfirmPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), paymentMethod, refundMethod);

		//Get voucher info and check it in redeemable voucher page
		vc=cancelConfirmPg.getVoucherDetailsInfo();
		web.gotoRedeemableVouchersPg();
		redeemableVoucherPg.verifyVoucherInfo(vc);

		//Make another 2-days reservation with voucher refund
		bd.lengthOfStay = "2";
		web.makeReservationToCart(bd);
		resNum = this.checkOutShoppingCart(pay);
		web.gotoResDetailsFromConfirm(1);

		//Calculate payment info by system when length of stay =2
		this.initializeFeeData(bd.lengthOfStay, true);
		useFeeData2 = feeInfo.getFeeScheduleInfo(useFeeData2, "2", schema);
		paymentInfoBeforeCancellation = this.calculatePaymentBySystem(false, true);
		paymentInfoAfterCancellation = this.calculatePaymentBySystem(true, true);
		totalPriceBeforeCancellation = paymentInfoBeforeCancellation.get(FeeType.KEY_TOTAL_PRICE);
		totalPriceAfterCancellation = paymentInfoAfterCancellation.get(FeeType.KEY_TOTAL_PRICE);
		
		//Payment method changes to Voucher
		paymentMethod = "Voucher"; 

		//Go to reservation details page to check payment details info
		resDetailsPg.verifyPaymentDetailsInResDetailsPg(paymentInfoBeforeCancellation, true);

		//Cancel reservation to cancel reservation page to check transaction details and refund details info
		web.cancelResToCancelResPg(); 
		cancellationPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation, totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		cancellationPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), paymentMethod, refundMethod);

		//Cancel reservation to cancellation completed page to check transaction details and refund details info
		web.cancelResToCancellationCompletedPgFromCancelResPg(pay);
		cancelConfirmPg.verifyTransactionDetailsInfo(BigDecimal.ZERO.subtract(totalPriceBeforeCancellation), totalPriceAfterCancellation, totalPriceAfterCancellation.subtract(totalPriceBeforeCancellation));
		cancelConfirmPg.verifyRefundDetailInfo(totalPriceBeforeCancellation.subtract(totalPriceAfterCancellation), paymentMethod, refundMethod);

		//Go to Redeemable Vouchers page to verify voucher number and value after getting voucher id and amount from 'Cancellation Completed' page
		vc=cancelConfirmPg.getVoucherDetailsInfo();
		web.gotoRedeemableVouchersPg();
		redeemableVoucherPg.verifyVoucherInfo(vc);

		//Go to reservation details page to check payment details info again
		web.gotoReservationDetailPageFromHomePage(resNum, bd.contractCode, "Cancelled");
		resDetailsPg.verifyRefundDetailsInResDetailsPg(paymentInfoAfterCancellation, true);
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
		bd.lengthOfStay = "5";
		bd.siteNo = "019";
		bd.siteID = "89135";
		bd.isUnifiedSearch=isUnifiedSearch();

		paymentMethod = "Credit Card";
		refundMethod = "Voucher";
	}

	/**
	 * Initial fee data info after changing date
	 * @param lengthOfStay
	 * @param newLengthOfStay  true: when length of stay change to 2 from 5 days
	 *                         false: when length of stay is 5, doesn't be changed
	 */
	private void initializeFeeData(String lengthOfStay, boolean newLengthOfStay){
		if(newLengthOfStay){
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
		transFeeData2.tranType = TRANTYPE_CANCELLATION;
		transFeeData2.departureDate = useFeeData1.departureDate;
		transFeeData2.nights.add(lengthOfStay);
	}

	/**
	 * Calculate payment 
	 * @param afterCancellation  true: after cancellation, only have transaction fee info
	 *                           false: before cancellation, has use and transaction fee info
	 * @param isNewLengthOfStay  true: when length of stay change to 2 from 5 days
	 *                         false: when length of stay is 5, doesn't be changed
	 * @return
	 */
	private Map<String, BigDecimal> calculatePaymentBySystem(boolean afterCancellation, boolean isNewLengthOfStay){
		Map<String, BigDecimal> paymentInfo = new HashMap<String, BigDecimal>();
		List<BigDecimal> useFee1 = new ArrayList<BigDecimal>();
		List<BigDecimal> useFee2 = new ArrayList<BigDecimal>();

		if(isNewLengthOfStay){
			useFee2 = feeCal.calculateBaseFee(useFeeData2, 0, true);
		}else{
			useFee1 = feeCal.calculateBaseFee(useFeeData1, 0, true);
		}
		List<BigDecimal> transFee = feeCal.calculateTransactionFee(transFeeData1, null);

		if(afterCancellation){
			transFee.addAll(feeCal.calculateTransactionFee(transFeeData2, null));
			paymentInfo = feeCal.calculateTotalPrice(null, null, transFee, null, null);
		}else{
			if(isNewLengthOfStay){
				paymentInfo = feeCal.calculateTotalPrice(null, useFee2, transFee, null, null);
			}else{
				paymentInfo = feeCal.calculateTotalPrice(null, useFee1, transFee, null, null);
			}
		}

		return paymentInfo;
	}

	/**
	 * Check out shopping cart with voucher only
	 * @param pay
	 * @return
	 */
	public String checkOutShoppingCart(Payment pay) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		logger.info("Check out shopping cart with voucher.");
		shoppingCart.waitLoading();
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();

		//Pay by voucher
		if(!vc.amount.equals(checkoutPg.getVoucherAmountByID(vc.voucherId)))
			throw new ErrorOnPageException("Voucher amount is wrong!");
		checkoutPg.clickVoucherByID(vc.voucherId);
		checkoutPg.waitLoading();

		if(!checkoutPg.getCreditCardAmount().equalsIgnoreCase("0.00"))
			throw new ItemNotFoundException("Not fully paid by voucher.");

		checkoutPg.undoVoucherByID(vc.voucherId);
		checkoutPg.waitLoading();
		browser.sync();

		checkoutPg.clickVoucherByID(vc.voucherId);
		checkoutPg.waitLoading();
		browser.sync();

		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();

		confirmPg.waitLoading();
		confirmPg.verifySuccess();
		confirmPg.verifyBalance("0.00");// verify the balance is 0

		return confirmPg.getAllResNo();
	}
}
