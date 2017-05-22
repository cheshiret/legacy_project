package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.cancellation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
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
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * when set "Redirection To Refund (web)" of voucher program to No, the refund method is voucher.
 * Firstly cancel reservation to generate voucher refund, the refund method is 'voucher';
 * Second use previous voucher and credit card to pay another reservation.
 * @Preconditions:
 * Site 017 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Cancellation: $10.00
 * 
 * d_fin_use_attr_fee_sched, id=3610
 * d_fin_tran_fee_sched, id=3220, 3230
 * d_fin_voucher_program, id=100
 * 
 * @SPEC: Cancellation with No Redirection to Refund(Web) of voucher program to the reservation [TC:034897] 
 * @Task#: AUTO-1407
 * 
 * @author SWang
 * @Date  Jan 24, 2013
 */
public class PaymentIsVoucherAndCreditCard extends RecgovTestCase{
	private FinanceManager fm = FinanceManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private Voucher vc;
	private VoucherProgram vp;

	private UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
	private UwpCancellationConfirmationPage cancelConfirmPg = UwpCancellationConfirmationPage.getInstance();
	private UwpRedeemableVouchersListPage redeemableVoucherPg = UwpRedeemableVouchersListPage.getInstance();
	private UwpCancellationPage cancellationPg = UwpCancellationPage.getInstance();

	private FeeValidationData useFeeData = new FeeValidationData();
	private FeeValidationData transFeeData1 = new FeeValidationData();
	private FeeValidationData transFeeData2 = new FeeValidationData();
	private FeeScheduleInformation feeInfo = FeeScheduleInformation.getInstance();
	private BigDecimal totalPriceBeforeCancellation, totalPriceAfterCancellation;
	private String paymentMethod, refundMethod;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);

		//Go to Finance Manager to update voucher program
		//"Provide the option during the creation to redirect to refund (web) instead of voucher?" as 'NO'
		fm.loginFinanceManager(login);
		fm.gotoVouchersPage();
		fm.gotoVoucherProgramPage();
		fm.searchToVoucherProgramDetails(vp);
		vp.redirectionToRefundWeb = "No";
		fm.modifyVoucherProgram(vp);
		fm.logoutFinanceManager();

		//Calculate payment info by system
		useFeeData = feeInfo.getFeeScheduleInfo(useFeeData, "2", schema);
		transFeeData1 = feeInfo.getFeeScheduleInfo(transFeeData1, "4", schema);
		transFeeData2 = feeInfo.getFeeScheduleInfo(transFeeData2, "4", schema);
		Map<String, BigDecimal> paymentInfoBeforeCancellation = this.calculatePaymentBySystem(false);
		Map<String, BigDecimal> paymentInfoAfterCancellation = this.calculatePaymentBySystem(true);
		totalPriceBeforeCancellation = paymentInfoBeforeCancellation.get(FeeType.KEY_TOTAL_PRICE);
		totalPriceAfterCancellation = paymentInfoAfterCancellation.get(FeeType.KEY_TOTAL_PRICE);

		//Go to REC.GOV web site to make reservation order
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		String resNum = web.checkOutShoppingCart(pay);

		//Cancel it to get voucher refund
		web.gotoResDetailsFromConfirm(1);
		web.cancelReservation(pay);
		vc=cancelConfirmPg.getVoucherDetailsInfo();

		//Make another reservation with voucher and credit card
		web.makeReservationToCart(bd);
		resNum = this.checkOutShoppingCart(pay);

		//Go to reservation details page to check payment details info
		web.gotoResDetailsFromConfirm(1);
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
		bd.lengthOfStay = "2";
		bd.siteNo = "017";
		bd.siteID = "89133";
		bd.isUnifiedSearch=isUnifiedSearch();

		vc = new Voucher();

		//Finance manager login paras
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.contract = "NRRS Contract";
		login.location = "Administrator - Auto/NRRS";

		vp = new VoucherProgram();
		vp.programeStatus = "Active";
		vp.searchType = "Voucher Program Name";
		vp.searchValue = "BLUE BAY VOUCHER PROGRAM";

		paymentMethod = "Credit Card";
		refundMethod = "Voucher";

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
