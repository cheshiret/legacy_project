package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;

/**
 * 
 * @author raonqa
 *
 * TODO Add factories for instantiating these around certain sets of fields.  For example, site avail TestData
 * where u parameterize site info and use that.
 */
public class ReservationInfo {

	//customer's first name and last name
	public String firstName = "";

	public String lastName = "";

	public String selectLastName = "";

	//reservation info (from which date, how many day stay)
	public String nightNum = "";

	public String arriveDate = "";

	public String departDate = "";

	public String siteQty = "1";

	public String email = "";
	
	public String phone = "";

	public String receiptnum = "";

	public String eventID = "";

	public String eventName = "";
	
	public String eventStartDate = "";
	
	public String eventEndDate = "";

	public String closureID = "";
	
	public String primaryOccupantLastName = "";
	
	public String primaryOccupantPhone = "";
	
	public String primaryOccupantFirstName = "";

	public String otherOccupantLastName = "";

	public String numofOccupant = "";
	
	public int numofVehicle = 0;

	public boolean includelaterarrivedate = false;
	
	public boolean includeEarlierDepartures = false;

	public boolean accessPass = false;

	public String discountName="";

	//change dates
	public String shorten = "";

	public String extend = "";

	public String changeArrive = "";

	public String changeDepart = "";

	public String rangeStart = "";

	public String rangeEnd = "";

	public String reservNum = "";

	public String midstayNum = "";

	public String reservStatus = "";

	public String orderStatus = "";
	
	public String orderPrice = "";
	
	public String orderType = "";
	
	public String orderPaid = "";
	
	public String orderBalance = "";
	
	public String orderDate = "";
	
	public String orderConfStatus = "";
	
	public String ordercollectLoc = "";

	public Customer customer = new Customer();

	public SiteInfoData site = new SiteInfoData();
	
	public RefundInfo refund = new RefundInfo();

	//for transfer to new site
	public SiteInfoData newSite = new SiteInfoData();
	
	public SiteInfoData oldSite;
	
	public Payment pay=new Payment();

	//	public Vector vehicles;
	//	public Vector campingUnits="";
	
	public String pin=""; //for override rule

	public String invoiceNum = "";

	public String invoiceDate = "";

	public String paidAmount = "";

	public String payStatus = "";

	// the transaction type to verify
	public String tranType = "";

	public String feeTypes = "";

	public String feeSums = "";

	//transaction reason for void, cancel etc.
	public String voidReason = "Regression Test";

	public String cancelReason = "Cancellation";

	public String transferReason = "";

	public String changeDateReason = "";

	public String note = "QA Auto Sanity";

	//General purpose parameters
	public String source = "";

	public String target = "";

	public String sourceID = "";

	public String targetID = "";

	// Refund data
	public String refundStatus = "";

	public String refundType = "";

	public String refundGroup = "";

	// Cancel call parameters
	public String reasonID = "";

	public String reasonDesc = "QA Automation";

	public boolean areacode = false;

	public String cancelreserreason = "";

	public String cancelnote = "QA Auto Test";
	
	public boolean splitstay=false;
	
	public String splitTo = "";
	
	public String promoCode="";
	
	public String parkName = "";
	
	public String parkId = "";
	
	public String[] siteIDs ;
	
	public String warningMessage = "";
	
	public boolean displayWarningMessage = false;
	
	private String resType;// for slip reservation, Lease, Transient, Seasonal
	
	//advanced reservation search parameters
	public boolean advancedSearch = false;

	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public void switchSites() {
		SiteInfoData temp = site;
		site = newSite;
		newSite = temp;
	}
	
	
	public static class ReservationHistory
	{
		public String dateTime;
		public String transType;
		public String transOcc;
		public String info;
		public String transLocation;
		public String user;
	}
	public List<ReservationHistory> history = new ArrayList<ReservationHistory>();
	
	/* Nicole
	 * If 'Collect Credit Card Info During Checkin' is Yes in facility details page,
	 * the Credit Card info will be displayed on reservation details page during making order.
	 */
	public boolean isCollectCCInfo = false;
	/*Nicole
	 * if isCollectCCInfo is true, below field mustn't be blank or null! -- Start
	 */
	public String creditCardType = "VISA";// default value is VISA, another option is MAST
	public String creditCardNumber;
	public String creditCardMM;
	public String creditCardYY;
	public String creditCardHolderName;
	/*Nicole
	 * if isCollectCCInfo is true, below field mustn't be blank or null! -- End
	 */
}
