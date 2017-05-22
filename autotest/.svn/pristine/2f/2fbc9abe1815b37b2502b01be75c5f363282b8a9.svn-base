package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.activity.confletter;

import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed to verify payment section for HF confirmation letter
 * @Preconditions:
 * @LinkSetUp:
 *D_ASSIGN_FEATURE:id=6202
 *D_HF_ADD_CUST_PROFILE:id=3210
 *D_HF_ADD_FACILITY:id=60,70
 *D_HF_ADD_FACILITY_PRD:id=70,80
 *D_HF_ADD_ACTIVITY:id=450,460
 * @SPEC:
 * Conf Letter for Activity-Payment info with Home currency [TC:116705] 
 * Conf Letter for Activity-Payment info check [TC:116706] 
 * @Task#: Auto-2228
 * 
 * @author Jane Wang
 * @Date  July 07, 2014
 */
public class PayInfoValidation extends LicenseManagerTestCase {
	private Data<ActivityAttr> activity = new Data<ActivityAttr>();
	private Voucher voucher = new Voucher();
//	private Voucher voucherUI = new Voucher();//voucher on order summary page
	private String host, username, password, emailSubject, parkName, total, paymentTendered;//, voucherRedeemed;
	private Properties props[] = null;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String privOrdNum = lm.processOrderCart(pay);
		
		lm.gotoPrivilegeOrderDetailPage(privOrdNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		pay.issueToVoucher = true;
		voucher = lm.processOrderAndGetVoucherInfo();
		
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		lm.makeActivityToCartFromOrderCartPage(cust, activity);
		pay.useVoucherPayment = true;
		pay.voucherID = voucher.voucherId + " : $" + voucher.amount;
		String ordNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		getPaymentInfo();
		lm.finishOrder();
		String receiptNum = lm.getReceiptNumByOrderNum(schema, ordNum.split(" ")[0]);
		
		lm.gotoReceiptDetailsPage(receiptNum);
		lm.requestHFConfirmLetter("Email");
		emailSubject = "Receipt#: "+receiptNum+" - "+parkName;
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		verifyActivityConfirmationLetterContent(ordNum, cust);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		
		//customer info
		cust.lName = "Test-HFConfirmLetter01";
		cust.fName = "QA-HFConfirmLetter01";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19790501";
		cust.residencyStatus = "Non Resident";
		
		privilege.code = "tan";
		privilege.name = "TANPrivilege";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
				
		//Set up activity info
		activity.put(ActivityAttr.activityCode, "ACL01");
		activity.put(ActivityAttr.activityName, "Activity for Confirmation Letter01");
		activity.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		parkName = "Mississippi Department of Wildlife, Fisheries, and Parks";
	}
	
	private void getPaymentInfo() {
		OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
		
		logger.info("Get payment info from order summary page.");
		total = summaryPage.getTotalPrice(true);//Total
		paymentTendered = summaryPage.getPaymentTendered();//Payment Tendered
		voucher = summaryPage.getVoucherRedeemedInfo();//Voucher info
	}

	private void verifyActivityConfirmationLetterContent(String ordNum, Customer cust) {
		logger.info("Verify activity confirmation letter content.");
		boolean passed = true;
		String emailContent = props[0].getProperty("text");
		logger.info(emailContent);
		
		if(!emailContent.contains(ordNum)) {
			passed = false;
			logger.error("Could not get order number from mail content. ---"+ordNum);
		}
		
		String custName = "NAME: "+cust.fName.toUpperCase()+" "+cust.lName.toUpperCase();
		if(!emailContent.contains(custName)) {
			passed = false;
			logger.error("Could not get customer name from mail content. ---"+custName);
		}
		
		String totalInfo = "TOTAL: "+total;
		if(!emailContent.contains(totalInfo)) {
			passed = false;
			logger.error("Could not get total info from mail content. ---"+totalInfo);
		}
		
		String paymentTenderedInfo = "PAYMENT TENDERED: "+paymentTendered;
		if(!emailContent.contains(paymentTenderedInfo)) {
			passed = false;
			logger.error("Could not get PAYMENT TENDERED info from mail content. ---"+paymentTenderedInfo);
		}
		
		String voucherRedeemedInfo = "VOUCHER REDEEMED: " + voucher.voucherId + voucher.customer + voucher.amount;
		if(!emailContent.contains(voucherRedeemedInfo)) {
			passed = false;
			logger.error("Could not get VOUCHER REDEEMED info from mail content. ---"+voucherRedeemedInfo);
		}
		
		if(!passed)
			throw new ErrorOnPageException("Failed to verify confirmation letter content.");
		logger.info("---Verify confirmation letter content successfully.");
	}
}
