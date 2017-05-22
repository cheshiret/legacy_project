/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 31, 2012
 */
public class RefundIRequestnfo {
	
	// refund request info using  when request refund in field manager
	public String requestStatus;
	public String requestReason;
	public String requestAmount;
	public String requestDetails;
	
	// refund request record in operation manager and in field manager
	public String id;
	public String orderNum;
	public String reqStatus;
	public String reason;
	public String reqDate;
	public String lastUpdateDate;
	public String location;
	public String state;
	public String customer;
	public String laseSentDate;
	public String sentTo;
	
	// for search in operation manager
	public String searchBy;
	public String searchValue;
	public String searchDate;
	public String from;
	public String to;
	public String searchLocation;
	public String custLastName;
	public String custFirstName;
	public String phoneNum;
	public String searchReqStatus;
	public String searchReqReason;
	public String operatLastName;
	public String opeartFirstName;
}
