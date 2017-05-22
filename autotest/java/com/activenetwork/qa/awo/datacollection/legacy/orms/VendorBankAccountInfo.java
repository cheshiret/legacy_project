package com.activenetwork.qa.awo.datacollection.legacy.orms;


/**
 * ScriptName: VendorBankAccountInfo
 * Description:
 * @date: Mar 29, 2011-4:21:26 AM
 * @author QA-qchen
 *
 */
public class VendorBankAccountInfo {
	
	public String accountID = "";
	
	public String accountStatus = "";
	
	public String accountPrenoteStatus = "";
	
	public String accountType = "";
	
	/**the routing number must be valid**/
	public String routingNum = "";
	
	public String accountNum = "";
	
	public String creationDateTime = "";
	
	public String creationUser = "";
	
	public String creationLocation = "";
	
	public int numOfAssignedStore = 0;
	
	public String effectiveDate = "";
	
	public String accountRegx = null;
	
	public String assignID = "";
	
	public String assignStatus = "";
	
	public String assignAgentName = "";
	
	private String bankName;
	
	private String bankBranchName;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
}
