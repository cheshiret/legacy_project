package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.giftcardsalesreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifySalesLocationAll extends ResourceManagerTestCase{
		
	List<String> giftCardProgramsLocation = new ArrayList<String>();
	List<String> salesChannel = new ArrayList<String>();
	List<String> salesLocation = new ArrayList<String>();
	List<String> excludePaymentGroup = new ArrayList<String>();
	List<String> includeRefundsIssuedToGiftCard = new ArrayList<String>();
	List<String> includeInternalGiftCardPaymentsoRRefunds = new ArrayList<String>();
	List<String> reportType = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		//Verify the section of "Gift Card Programs Location".
		rm.VerifyReportSelectionCriteria("GiftCardLocation", giftCardProgramsLocation, "All", giftCardProgramsLocation.get(0));
		
		//Verify the section of "Sales Channel".
		rm.VerifyReportSelectionCriteria("SalesChannel", salesChannel, "All", salesChannel.get(0));
		
		//Verify the section of "Sales Location".
		rm.VerifyReportSelectionCriteria("SalesLocation", salesLocation, "All", salesLocation.get(0));
		
		//Verify the section of "Exclude Payment Group".
		rm.VerifyReportSelectionCriteria("ExcludePaymentGrp", excludePaymentGroup, "None", excludePaymentGroup.get(0));
		
		//Verify the section of "Include Refunds Issued to Gift Card".
		rm.VerifyReportSelectionCriteria("RAFeeAccount", includeRefundsIssuedToGiftCard, "Exclude", includeRefundsIssuedToGiftCard.get(0));
		
		//Verify the section of "Include Internal Gift Card Payments/Refunds".
		rm.VerifyReportSelectionCriteria("IncludeGCPayments", includeInternalGiftCardPaymentsoRRefunds, "Exclude", includeInternalGiftCardPaymentsoRRefunds.get(0));
		
		//Verify the section of "Report Type".
		rm.VerifyReportSelectionCriteria("ByReportType", reportType, "Summary", reportType.get(0));
		
		//Verify the section of "Report Format".
		rm.VerifyReportSelectionCriteria("report_format", reportFormat, "XLS", reportFormat.get(0));
		
		//Verify the section of "Delivery Method".
		rm.VerifyReportSelectionCriteria("deliveryprotocolid", deliveryMethod, "Email", deliveryMethod.get(0));

		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void reportSectionInfo(){
		//giftCardProgramsLocation
		giftCardProgramsLocation.add("All");
		//salesChannel
		salesChannel.add("All");
		salesChannel.add("Call Center");
		salesChannel.add("Field");
		salesChannel.add("Web");
		//salesLocation
		salesLocation.add("All");
		//List<String> salesLocation = new ArrayList<String>();
		excludePaymentGroup.add("None");
		excludePaymentGroup.add("Cash");
		excludePaymentGroup.add("Non Cash Depositable");
		excludePaymentGroup.add("Credit Card");
		excludePaymentGroup.add("Non Depositable");
		excludePaymentGroup.add("ACH");
		excludePaymentGroup.add("Voucher");
		//includeRefundsIssuedToGiftCard
		includeRefundsIssuedToGiftCard.add("Exclude");
		includeRefundsIssuedToGiftCard.add("Include");
		//IncludeInternalGiftCardPaymentsoRRefunds
		includeInternalGiftCardPaymentsoRRefunds.add("Exclude");
		includeInternalGiftCardPaymentsoRRefunds.add("Include");
		//reportType
		reportType.add("Summary");
		reportType.add("Detail");
		//reportFormat
		reportFormat.add("DHTML");
		reportFormat.add("XLS");
		//deliveryMethod
		deliveryMethod.add("Email");
		deliveryMethod.add("Online");
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Gift Card Sales Report";//"Gift Card Sales Summary Report and Gift Card Sales Detail Report";
	}
}
