package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: (P)
 * 1. Make privilege order
 * 2. Void privilege
 * 3. Verify 'Return Documents', 'Void' history record
 * 
 * @Preconditions:
 * 1. Have existing Vendor(AutoReturnVoidedDocumantVendor) has "Auto Return Voided Documents"
 * 2. Have existing Agent(AutoReturned)
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968

 * @author: SWang
 * @Date: 2012-3-31
 */
public class ReturnedPrivilegeHistory extends LicenseManagerTestCase {
	private String orderNum, returnDocumentsComment;
	private String[] privilegeNums;
	private List<OrderHistory> returnDocumentRecord = new ArrayList<OrderHistory>();
	private List<OrderHistory> voidRecord = new ArrayList<OrderHistory>();

	public void execute() {
		lm.loginLicenseManager(login);

		//Make a privilege order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay).split(" ")[0];

		//Void privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		//Go to privilege order history page
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeOrderHistoryPage();

		//Get 'Return Documents', 'Void' history record
		privilegeNums = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		List<OrderHistory> history = this.getReturnDocumentsAndVoidHistory();

		//Verify history records
		this.verifyReturnDocumentsRecord(returnDocumentsComment, history.get(0));
		this.verifyVoidRecord(history.get(1));

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/AutoReturned";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//privilege product info
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";

		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12341";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		returnDocumentsComment = "Automatically returned as per Vendor setting";
	}

	/**
	 * Get 'Return Documents', 'Void' history record
	 * Make sure there should be only one
	 */
	private List<OrderHistory> getReturnDocumentsAndVoidHistory() {
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();

		logger.info("Start to get ony one 'Return Documents' and 'Void' record in 'Privilege Order History' page.");
		returnDocumentRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Return Documents");
		voidRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Void");
		if(returnDocumentRecord==null ||returnDocumentRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Return Documents' record.", "1", String.valueOf(returnDocumentRecord.size()));
		}else if(voidRecord==null ||voidRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Void' record.", "1", String.valueOf(voidRecord.size()));
		}else{
			logger.info("Successfully verify ony one 'Return Documents' and 'Void' record.");
		}

		List<OrderHistory> historyRecords = new ArrayList<OrderHistory>();
		historyRecords.add(returnDocumentRecord.get(0));
		historyRecords.add(voidRecord.get(0));
		return historyRecords; 
	}

	/**
	 * Verify 'Return Documents' record
	 * @param returnDocumentsRecord
	 */
	private void verifyReturnDocumentsRecord(String returnDocumentComment, OrderHistory returnDocumentsRecord){
		logger.info("Start to verify Return Documents record.");

		String actualReturnDocumentComment = returnDocumentsRecord.infoTransaction .split("Comment:")[1].trim();
		if(!actualReturnDocumentComment.equals(returnDocumentsComment)){
			throw new ErrorOnDataException("Return Documents comment is wrong.", returnDocumentComment,actualReturnDocumentComment);
		}
		logger.info("Successfully verify Return Documents commentb:"+returnDocumentComment);
	}

	/**
	 * Verify privilege number for transaction 'Void'
	 * @param voidRecord: Transaction info like: Privilege Numbers(Original): 136347964
	 */
	private void verifyVoidRecord(OrderHistory voidRecord){
		String[] transactionInfo  = voidRecord.infoTransaction .split(":")[1].split("Reason")[0].trim().split(",");

		if(transactionInfo.length!=privilegeNums.length){
			throw new ErrorOnPageException("There should be "+privilegeNums.length+" privilege nums.");
		}
		Arrays.sort(transactionInfo);
		Arrays.sort(privilegeNums);
		for(int i=0;i<privilegeNums.length;i++){
			if(!privilegeNums[i].equals(transactionInfo[i])){
				throw new ErrorOnPageException("Can't find privilage num:"+privilegeNums[i]);
			}
			logger.info("Successfully find privilage number:"+privilegeNums[i]);
		}
	}
}
