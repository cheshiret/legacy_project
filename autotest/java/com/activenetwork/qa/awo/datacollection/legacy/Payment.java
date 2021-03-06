package com.activenetwork.qa.awo.datacollection.legacy;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class Payment extends TestData implements OrmsConstants {

	/** States with their own park-level credit-card processing systems: */
	/** New York, Massachusetts, Kentucky, Wisconsin */
	public static final String[] STATES_WITH_OWN_CC = { "NY Contract",
			"MA Contract", "KT Contract", "WI Contract" };

	public static final String PAY_MASTERCARD = "MasterCard";

	public static final String PAY_VISA = "Visa";

	public static final String PAY_CASH = "Cash";
	
	public static final String PAY_DEF_CASH = "Cash*";
	
	public static final String PAY_DEF_MON_ORDER = "Money Order*";
	
	public static final String PAY_DEF_PER_CHQ = "Personal Check*";
	
	public static final String PAY_PER_CHQ = "Personal Check";
	
	public static final String MASTERCARD_NUM = "5454545454545454";

	public static final String VISA_NUM = "4111111111111111";

	public static final String CVV_NUM_VISA = "382";
	public static final String CVV_NUM_MC = "382";
	public static final String CVV_NUM_AMEX = "1234";
	public static final String CVV_NUM_DISC = "111";

	public String payTypeGrp = ""; // used for database grouping of payment
									// types

	public String payType = "";
	public boolean flexible=false; //flag if payment type is flexible

	public String pin = "9671111";

	public String amount = ""; // The first payment amount in query result

	public String amount_1 = "";// The second payment amount in query result

	public String checkNumber = "";

	public String checkName = "";

	public String checkDate = "";

	public String creditCardNumber = "";

	public String expiryMon = "";

	public String expiryYear = "";

	public String cardHolder = "";

	public String fName = "";

	public String lName = "";

	public String zip = "12345";

	public boolean ignoreCCspecifics = false; // Use when interacting at walk-in
												// level with certain states
												// (see constants section)

	public String additionalPayType = "";

	public String additionalAmount = "";

	public String additionalCheckNumber = "";

	public String additionalCheckName = "";

	public String additionalCheckDate = "";

	public String additionalCreditCardNumber = "";

	public String additionalExpiryMon = "";

	public String additionalExpiryYear = "";

	public String additionalCardHolder = "";

	public String contract = "";

	public String deposit = "";

	public String finSession = "";

	public String searchType = "";

	public String searchValue = "";

	public String dateRange = "";

	public String startDate = "";

	public String endDate = "";

	public String status = "";

	public String belongOrderNum = ""; // this payment blong which order

	public String collectUser = "";

	public String customer = "";

	public String collectLocation = "";

	public String paymentID = "";

	public String securityCode = "";

	public String reason = "";

	public String paymentVoidReason = "";

	public String giftCardNum = "";

	public String giftCardSecCode = "";

	public boolean useVoucherPayment = false;

	public boolean useCreditCardOnFile = false;

	public String voucherID = "";

	public String otherVoucherID = "";

	public String changeAmount = ""; // change amount generated by the payment

	public String salesChanl = ""; // mark the payment generated by which sales
									// channel
	public String refundType = "";
	
	public boolean issueToVoucher = false;

	public boolean issueCash = false;

	public boolean issueGiftCard = false;
	
	public boolean isMinimumPayment=false;
	
	public boolean noMinimumPayment = false; //Flag no minimum payment field displays

	// qa-auto-fm,qa-auto-om,qa-auto-cm......
	public String currentUser = "";

	public TicketInfo ticketInfo = new TicketInfo();

	public int payInstruction = FULL;

	public static final int FULL = 0;
	public static final int HALF = 1;
	public static final int ONETHIRD = 2;
	public static final int ONEFORTH = 3;
	public static final int ONEFIFTH = 4;
	public static final int MINIMUM = 5;
	public static final int AMOUNTOWING = 6;
	
	public String orderNum = "";
	public String product = "";
	public String feeTranTypeAndAllocationTranType = "";
	public String paymentIDAndType = "";

	public Payment() {
	}

	public Payment reset(String type) {
		setType(type) ;
		
		return this;
	}
	
	public Payment(String type) {
		amount = "";
		additionalPayType = "";
		additionalAmount = "";
		pin = "9671111";
		setType(type);
	}
	
	public void setType(String type) {
		if(type.toLowerCase().matches("visa|master")) {
			payTypeGrp = Integer.toString(PMT_GROUP_CC);
			if (type.equalsIgnoreCase("visa")) {
				payType = PAY_VISA;
				creditCardNumber = VISA_NUM;
				securityCode = CVV_NUM_VISA;
			} else if (type.equalsIgnoreCase("master")) {
				payType = PAY_MASTERCARD;
				creditCardNumber = MASTERCARD_NUM;
				securityCode = CVV_NUM_MC;
			} else {
				throw new NotSupportedException("The CC type is not supported.");
			}
			additionalCreditCardNumber = "";
			additionalExpiryMon = "";
			additionalExpiryYear = "";
			additionalCardHolder = "";
			expiryMon = "12";
			expiryYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(5));
			cardHolder = "QA TESTER";
			lName = "TESTER";
			fName = "QA";
		}else if (type.equalsIgnoreCase("cash")) {
			payTypeGrp = Integer.toString(PMT_GROUP_CASH);
			payType = PAY_CASH;
		} else if (type.equalsIgnoreCase("cash*")) {
			payTypeGrp = Integer.toString(PMT_GROUP_DEF_CASH);
			payType = PAY_DEF_CASH;
		} else if (type.equalsIgnoreCase("personal check")) {
			payTypeGrp = Integer.toString(PMT_GROUP_NONCASH);
			payType = PAY_PER_CHQ;
			checkNumber = "123456";
			checkName = "AutoTest";
			checkDate = DateFunctions.getDateAfterToday(1);
			additionalCheckNumber = "";
			additionalCheckName = "";
			additionalCheckDate = "";
		} 
	}

	/**
	 * @param ignoreCCspecifics
	 *            The ignoreCCspecifics to set.
	 */
	public void setIgnoreCCspecifics(boolean ignoreCCspecifics) {
		this.ignoreCCspecifics = ignoreCCspecifics;
	}
}
