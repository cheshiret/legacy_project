package com.activenetwork.qa.awo.util;

public class ConstantsInterpreter {
	//payment method
	private static final String PMT_METHOD_VISA = "Visa";
	private static final String PMT_METHOD_MASTERCARD = "MasterCard";
	private static final String PMT_METHOD_AMEX = "American Express";
	private static final String PMT_METHOD_DISCOVER = "Discover";
	private static final String PMT_METHOD_CASH = "Cash";
	private static final String PMT_METHOD_PERCHQ = "Personal Check";
	private static final String PMT_METHOD_TRAVCHK = "Travellers Check";
	private static final String PMT_METHOD_CERTCHQ = "Certified Check";
	private static final String PMT_METHOD_HIST = "Historical";
	private static final String PMT_METHOD_MONORD = "Money Order";
	private static final String PMT_METHOD_GIFTCARD = "GIFT 2U";
	private static final String PMT_METHOD_VOUCHUR= "Voucher";
	private static final String PMT_METHOD_DEF_CASH= "Cash*";
	private static final String PMT_METHOD_DEF_PERCHQ= "Personal Check*";
	
	private static final String UNKNOWN_STATUS = "Unknown";
	
	//sales channel
	private static final String SALES_CHANNEL_ALL = "All";
	private static final String SALES_CHANNEL_WEB = "Web";
	private static final String SALES_CHANNEL_CALL = "CallCenter";
	private static final String SALES_CHANNEL_FIELD = "FieldMgr";
	
	//product category
	private static final String SITE_PRODUCT = "Site";
	private static final String PERMIT_PRODUCT = "Permit";
	private static final String TICKET_PRODUCT = "Ticket";
	
	//payment status
	private static final String PMT_STATUS_PENDING = "Pending";
	private static final String PMT_STATUS_RECEIVE = "Received";
	private static final String PMT_STATUS_APPROVE = "Approved";
	private static final String PMT_STATUS_ISSUED = "Issued";
	private static final String PMT_STATUS_VOID = "Voided";
	private static final String PMT_STATUS_DECLINE = "Declined";
	private static final String PMT_STATUS_MAILED = "Mailed";
	private static final String PMT_STATUS_NSF = "NSF";
	private static final String PMT_STATUS_CHARGEBACK = "Chargeback";
	private static final String PMT_STATUS_Voucher = "Issued to Voucher";	
	
	
	public static String getPaymentType(String payTypeCode){
		if(payTypeCode.equals("81052")){
			return PMT_METHOD_CASH;
		}else if(payTypeCode.equals("81053")){
			return PMT_METHOD_MASTERCARD;
		}else if(payTypeCode.equals("81054")){
			return PMT_METHOD_VISA;
		}else if(payTypeCode.equals("81055")){
			return PMT_METHOD_PERCHQ;
		}else if(payTypeCode.equals("81056")){
			return PMT_METHOD_TRAVCHK;
		}else if(payTypeCode.equals("81057")){
			return PMT_METHOD_MONORD;
		}else if(payTypeCode.equals("81058")){
			return PMT_METHOD_CERTCHQ;
		}else if(payTypeCode.equals("81059")){
			return PMT_METHOD_HIST;
		}else if(payTypeCode.equals("81051")){
			return PMT_METHOD_AMEX;
		}else if(payTypeCode.equals("81050")){
			return PMT_METHOD_DISCOVER;
		}else if(payTypeCode.equals("100000")){
			return PMT_METHOD_GIFTCARD;
		}else if(payTypeCode.equals("81070")){
			return PMT_METHOD_VOUCHUR;
		}else if(payTypeCode.equals("1002")){
			return PMT_METHOD_DEF_CASH;
		}if(payTypeCode.equals("1006")){
			return PMT_METHOD_DEF_PERCHQ;
		}else{
			return UNKNOWN_STATUS;
		}
	}
	
	public static String getSaleChannel(String channelCode){
		if(channelCode.equals("1")){
			return SALES_CHANNEL_ALL;
		}else if(channelCode.equals("2")){
			return SALES_CHANNEL_WEB;
		}else if(channelCode.equals("3")){
			return SALES_CHANNEL_CALL;
		}else if(channelCode.equals("4")){
			return SALES_CHANNEL_FIELD;
		}else{
			return UNKNOWN_STATUS;
		}
	}
	
	public static String getPaymentStatus(String paymentStatus){
		if(paymentStatus.equals("1")){
			return PMT_STATUS_PENDING;
		}else if(paymentStatus.equals("2")){
			return PMT_STATUS_RECEIVE;
		}else if(paymentStatus.equals("3")){
			return PMT_STATUS_APPROVE;
		}else if(paymentStatus.equals("4")){
			return PMT_STATUS_ISSUED;
		}else if(paymentStatus.equals("5")){
			return PMT_STATUS_VOID;
		}else if(paymentStatus.equals("6")){
			return PMT_STATUS_MAILED;
		}else if(paymentStatus.equals("7")){
			return PMT_STATUS_NSF;
		}else if(paymentStatus.equals("8")){
			return PMT_STATUS_CHARGEBACK;
		}else if(paymentStatus.equals("11")){
			return PMT_STATUS_DECLINE;
		}else if(paymentStatus.equals("19")){
			return PMT_STATUS_Voucher;
		}else {
			return UNKNOWN_STATUS;
		}
	}
	
	public static String getPrdCategory(int cat_id){
		if(cat_id == 3){
			return SITE_PRODUCT;
		}else if(cat_id == 5){
			return PERMIT_PRODUCT;
		}else if(cat_id == 6){
			return TICKET_PRODUCT;
		}else{
			return UNKNOWN_STATUS;
		}
	}
	
	/**
	 * Get rule name by given rule id,and the mapping was defined in 
	 * http://wiki.reserveamerica.com/display/dev/ORMS+Code+Base+Look-ups
	 * @param rule_type_id
	 * @return
	 */
	public static String getRuleName(int rule_type_id){
		String rule_name = "";
		switch(rule_type_id)
		{
			case 1:
				rule_name = "MaximumWindow";
				break;
			case 2:
				rule_name = "AccessType";
				break;
			case 3:
				rule_name = "MinimumWindow";
				break;
			case 4:
				rule_name = "StayBeyondMaximumWindow";
				break;
			case 5:
				rule_name = "TimeRestrictionBeforeTransactionAllowed";
				break;
			case 6:
				rule_name = "AccessTime";
				break;
			case 7:
				rule_name = "MinimumStay";
				break;
			case 8:
				rule_name = "SpecifiedStayStart";
				break;
			case 9:
				rule_name = "MaximumTimeToReceivePayment";
				break;
			case 10:
				rule_name = "MaximumNumberOfStaysPerPeriod";
				break;
			case 12:
				rule_name = "MaximumNumberOfConcurrentReservations";
				break;
			case 13:
				rule_name = "MaximumNumberOfReservationsWithSameStartDate";
				break;
			case 14:
				rule_name = "AllowConcurrentReservationsBetweenFacilities";
				break;
			case 15:
				rule_name = "MaximumTotalStay";
				break;
			case 16:
				rule_name = "MaximumStayPerPeriod";
				break;
			case 18:
				rule_name = "MaximumConsecutiveStay";
				break;
			case 19:
				rule_name = "SiteRestrictedInUse";
				break;
			case 20:
				rule_name = "InventoryHoldTimeout";
				break;
			case 21:
				rule_name = "NoShow";
				break;
			case 22:
				rule_name = "MinimumPaymentToConfirm";
				break;
			case 23:
				rule_name = "AdditionalProductGroupSale";
				break;
			case 24:
				rule_name = "AdditionalProductSale";
				break;
			case 25:
				rule_name = "TimeToClear";
				break;
			case 26:
				rule_name = "TransactionRestriction";
				break;
			case 27:
				rule_name = "MaximumNumberOfOrdersPerCall";
				break;
			case 28:
				rule_name = "MaximumNumberOfOrdersWithinABookingPeriod";
				break;
			case 29:
				rule_name = "MaximumNumberOfOrdersWithinStayPeriod";
				break;
		}
		return rule_name;
	}
}
