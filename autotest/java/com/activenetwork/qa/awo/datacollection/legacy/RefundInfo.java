/*
 * Created on Nov 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefundInfo {
	public String refundID = "";
	
	public String fromPaymentId = "";
	
	public String fromOrderNum = "";

	public String status = "";

	public String paymentMethod = "";
	
	public String dateRange = "";

	public String startDate = "";

	public String endDate = "";

	public String refundType = "";

	public String searchType = "";

	public String searchValue = "";

	public String collectLocation = ""; // requesting location

	public String customer = "";

	public String amount = "";
	
	public String refundMethod = "";

	public String location = "";

	public String user = ""; // Pin User
	
	public String transferReason = "";
	
	public String issuedToGiftCardNum = "";
	
	public String issuedGiftCardOrd = "";
	
	public String issueChanel = "";
	
	public String issuedLocation = "";
	
	public String issuedUser = "";
	
	// add more issuing info
	public String issuedStatus = "";
	
	public String issuedCCExpiryDate = "";
	
	public String issuedCCHolderNm = "";
	
	public String issuedCCNum = "";
	
	public String issuedDate = "";
	
	public String issuedVoucherID = "";
	
	public String issuedVoucherProgramID = "";
	
	public String issuedVoucherProgramNm = "";
	
	public String issuedGiftCardPrgID = "";
	
	public String issuedGiftCardPrgName = "";
	
	// add approving info by lesley wang
	public String approvingUser = "";
	
	public String approvingLocation = "";
	
	public String approvingDateTime = "";
	
	// add requesting date & time by lesley wang
	public String requestingUser = "";
	
	public String requestingDateTime = "";
	
	public String refundNote = "";
	
	// add void info by lesley wang
	public String voidUser = "";
	public String voidLocation = "";
	public String voidDateTime = "";
	
	// add void issuing info by lesley wang
	public List<Properties> allVoidedIssueInfos = new ArrayList<Properties>();
	
}
