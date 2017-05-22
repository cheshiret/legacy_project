package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.voucherrefund.cancellation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRedeemableVouchersListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * when set "Redirection To Refund (web)" of voucher program to Yes, the refund method is same as the payment method.
 * Check payment and refund info in reservation details page, cancel reservation page and cancellation completed page before and after canceling reservation
 * @Preconditions:
 * Site 006 should have below fee schedules
 * Use fee: $12.0;
 * Transaction fee: Per Transaction, Reservation: $9.00
 *                                   Cancellation: $10.00
 * 
 * d_fin_use_attr_fee_sched, id=3690
 * d_fin_tran_fee_sched, id=3330, 3340
 * d_fin_voucher_program, id=110
 * 
 * @SPEC: Cancellation with Yes Redirection to Refund(Web) of voucher program to the reservation [TC:034899] 
 * @Task#: AUTO-1407
 * 
 * @author SWang
 * @Date  Jan 24, 2013
 */
public class YesRedirectionToRefund extends RecgovTestCase{
	private FinanceManager fm = FinanceManager.getInstance();
	private LoginInfo login = new LoginInfo();
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
		//"Provide the option during the creation to redirect to refund (web) instead of voucher?" as 'YES'
		fm.loginFinanceManager(login);
		fm.gotoVouchersPage();
		fm.gotoVoucherProgramPage();
		fm.searchToVoucherProgramDetails(vp);
		vp.redirectionToRefundWeb = "Yes";
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

		//Go to Redeemable Vouchers page to verify no any voucher refund record 
		web.gotoRedeemableVouchersPg();
		redeemableVoucherPg.verifyNoRedeemableVouchersMes();

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

		bd.parkId = "72099";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //CAMP SHERMAN CAMPGROUND
		bd.contractCode = "NRSO";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "5";
		bd.siteNo = "006";
		bd.siteID = "283840";
		bd.isUnifiedSearch=isUnifiedSearch();

		//Finance manager login paras
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.contract = "NRRS Contract";
		login.location = "Administrator - Auto/NRRS";

		vp = new VoucherProgram();
		vp.programeStatus = "Active";
		vp.searchType = "Voucher Program Name";
		vp.searchValue = "CAMP SHERMAN CAMPGROUND VOUCHER PROGRAM";

		paymentMethod = "Credit Card";
		refundMethod = "Credit Card";
		
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
		paymentInfo = feeCal.calculateTotalPrice(null, useFee, transFee, null, null);

		if(afterCancellation){
			transFee.addAll(feeCal.calculateTransactionFee(transFeeData2, null));
			paymentInfo = feeCal.calculateTotalPrice(null, null, transFee, null, null);
		}

		return paymentInfo;
	}
}
