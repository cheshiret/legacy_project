package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;


public class ReportData {

	public String areaID = "";

	public String deliveryprotocolid = ""; //delivery method

	public String locationID = ""; //location
	
	public String locationClass = "";//location class for H&F
	
	public String[] transactionTypes;// transaction types for H&F
	
	public String fiscalYear = "";//Fiscal year for H&F

	public String orderBy = "";

	public String reportFormat = ""; //report_format

	public String showEmptySites = "";

	public String startDate = "";//StartDate_ForDisplay

	public String tourDate = "";
	
	public String orderDate = "";
	
	public String agencyID = "";

	public String dateType = "";

	public String facilityID = "";

	public String productGroup = "";
	
	public String productClass = "";
	
	public String productSubClass = "";
	
	public String productCategory = "";

	public String inventoryType = "";
	
	public String regionID = "";
	
	public String includeEquipmentType = "";
	
	public String includeSummary = "";

	public String endDate = "";//EndDate_ForDisplay

	public String reportLoc = "";

	public String sortOrder = "";

	public String callCenterID = "";

	public String operator = "";

	public String productType = "";

	public String paymentGroup = "";

	public String stationID = "";

	public String park = "";

	public String collectIssueLocation = "";//collect location

	public String facilityHQID = ""; //Show Payment Types

	public String includeAdjustments = "";

	public String includeTaxes = "";//Include Non-Depositables
	
	public String includeRAFee = ""; //include RA Fee

	public String rAFeeAccount = "";//RA Fee Account

	public String yesNoFlag = "";//Show Payment Types
	
	public String includeGcPayments = ""; //Include Internal Gift Card Payments

	public String agencyLocID = "";// Agency

	public String facilityLocID = "";// Facility

	public String regionLocID = "";// Region

	public String reportBy = "";// report by

	public String reservationType = "";
	
	public String showAllocation = "";

	//below is new
	public String paymentRefundStatus = "";

	public String startTime = "";

	public String startTimeampm = "";

	public String endTime = "";

	public String endTimeampm = "";

	public String recipient_name = "";

	public String paymentMethod = "";

	public String refund_Status = "";

	public String refund_Payment = "";

	public String reportByLocation = "";

	public String attrFeeType = "";

	public String supressEmptyLine = "";

	public String maxLetters = "";

	public String doNotEmail = ""; //whether Email mail

	public String doNotFax = ""; //whether send Fax

	public String byTicketType = "";

	public String exceptions = "";

	public String unitOfStay = "";

	public String voucherProgram = "";

	public String deliveryMethod = "";
	
	public String emailSubject = "";

	public String category = "";

	public String reportType = "";

	public String reportSubType = "";

	public String balanceType = "";

	public String reservationStatus = "";

	public String orderStatus = "";

	public String paymentStatus = "";

	public String collectionStatus = "";

	public String discountIDs = "";

	public String batchStatus = "";

	public String locationIDNoValidate = "";

	public String customerType = "";

	public String sortBy= "";

	public String salesChannel= "";

	public String reportName = "";

	public String group ="";

	public String recipientFromList ="";

	public String dfmDate ="";

	public String dfmSortOrder ="";
	
	public String faxTo ="";
	
	public String faxFrom ="";
	
	public String faxNum = "";
	
	public String recipientListId ="";
	
	public String tourCategory="";
	
	public String cooperatorID="";
	
	public String depositID_1="";
	
	public String depositID_2 ="";
	
	public String frequency ="";   //_DisbursementFrequencyUIName_
	
	public String period = "";   //_DisbursementPeriodUIName_
	
	public String recipientType = "";
	
	public String finSessionId_1 ="";
	
	public String finSessionId_2 = "";
	
	public String lWLocID = "";
	
	public String invoice = "";
	
	public String tourID = "";
	
	public String tourName = "";
	
	public String districtID = "";
	
	public String projectID = "";
	
	public String lotteryName = "";
	
	public String lotterySched = "";
	
	public String siteNumber = "";
	
	public String siteStatus = "";
	
	public String quotaInterval = "";
	
	public String residenceArea = "";
	
	public String permitEntrance = "";
	
	public String monthYear = "";
	
	public String dateGroup = "";
	
	public String refundRequestId = "";
	
	public String revenueLoc = "";
	
	public String flMonthYear = "";
	
	public String state = "";
	
	public String custLocation = "";
	
	public String giftCardLoc = "";
	
	public String paymentGrp = "";
	
	public String resNumber = "";
	
	public String transmittalID = "";
	
	public String reportTitle = "";
	
	public String reportLayout = "";
	
	public String dataResource = "";
	
	public String reportSection = "";
	
	public String includeDistributed = "";
	
	public String includeRefIssueGiftCard = "";
	
	public String[] posPrds; //used in POS sold with reservation report
	
	public String[] promoCodes;//used in POS sold with reservation report
	
	public String fileName = ""; //used to handle same report name,different report criteria
	
	public String certificationType = "";
	
	public String customerNumber = "";
	
	public String[] privilegeProduct;         // for "Lapsed Customer Report "
	
	public String includePrivilegeCode = "";
	public String reportByType = "";
	public String fixedLengthType = "";
	public String includeDOB = "";
	public String includePrivilegeDescription = "";
	public String displayPrivDescription = "";
	public String includePrivEffeciveToDate = "";
	public String includePrivEffectiveFromDate = "";
	public String includeDriverLicenseNum = "";
	public String includeHunterEducationNum = "";
	public String customerAttribute = "";
	public String[] otherPrivilegeProducts;
	public String includePrivilegeActivity = "";
	public String includeAP = "";
	public String includeCPF = "";
	
	public String privilegeHeldStartDate = ""; // for "Lapsed Customer Report "
	public String privilegeHeldEndDate = ""; // for "Lapsed Customer Report "
	public String privilegeLapsedStartDate = ""; // for "Lapsed Customer Report "
	public String privilegeLapsedEndDate = ""; // for "Lapsed Customer Report "
	
	public String vehicleType = ""; // for "Vehicle Information Report"
	public String vehicleID = ""; // for "Vehicle Information Report"
	public String vehicleAttribute = "";
	public String[] vehicleAttrOptions;
	public String includeLength = "";
	
	public String[] privilegeProductAPR;         // for "Alternate Proof of Residency" 
	public String[] storeStatus;

	//for transaction detail revenue report
	public String transactionDetailReportBy = "";
	public boolean selectDisplayColumns = false;
	public String[] displayColumns;
	public String[] subTotalBy;
	public String breakAccountCode = "";

	//for RA Gross Contract Revenue Report
	public String contract = "";

	public String posReportBy = "";

	public String receiptNum;

	public String store;

	public String reportReversalsAt;

	public String includeStEFTAdjustment;
	
	public String includeLoops = ""; // for "Reservation Methods Report", "No" or "Yes"
	
	public String includeTotals = ""; // for "Reservation Methods Report", "No" or "Yes"
	
	public String includeInOrOutOfState = ""; // for "Reservation Methods Report", "No" or "Yes"
	
	//for Slip Permit Report
	public String dockArea = "";
	
	public String marinaRateType = "";
	
	public String slipType = "";
	
	public String product = "";
	
	public String closureID = "";
	
	public String issueLocation = "";
	public String borrowedStatus = "";//for permit reservation details data report
	public List<String> ordReservationStatus=new ArrayList<String>();//for permit reservation details data report

	public String earnedRevenue = "";// for "Earned Distributed Funds Detail Report", Earned, Unearned, Both
	public String earnedReportBy = "";// for "Earned Distributed Funds Detail Report", Facility, Area/Loop
	public String includeAAD = "";// for "Earned Distributed Funds Detail Report", No, Yes
	
	public String ticketCategory = "";//for "Daily Arrival Report-Ticketing", All, Individual, Organization

}
