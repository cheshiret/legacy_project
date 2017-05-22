package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: (P)
 * 1. Make privilege order using the privilege which has one harvest document, print the document
 * 2. Void privilege
 * 3. Verify 'Void', 'Reverse Fee' and 'Reallocate Payment' history record
 * 
 * @Preconditions: 
 * 1. Have Harvest template: Harvest DOC
 * 2. Have privilege product: "VHR-HarvestPrivilege"
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968
 * 
 * @author: SWang
 * @Date: 2012-3-30
 */
public class VoidPrivilegeHistory extends LicenseManagerTestCase {
	private String[] privilegeNums;
	private String orderNum;
	List<OrderHistory> makePaymentRecord = new ArrayList<OrderHistory>();
	List<OrderHistory> voidRecord = new ArrayList<OrderHistory>();
	List<OrderHistory> reverseRecord = new ArrayList<OrderHistory>();
	List<OrderHistory> reallocateRecord = new ArrayList<OrderHistory>();

	public void execute() {
		lm.loginLicenseManager(login);

		//Make a privilege order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = 	lm.processOrderCart(pay).split(" ")[0];

		lm.gotoPrivilegeOrderDetailPage(orderNum);
		//Reprint privilege
		lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		//Void privilege
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		//Get 'Make Payment', 'Void', 'Reverse Fee' and 'Reallocate Payment' history record
		privilegeNums = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeOrderHistoryPage();
		List<OrderHistory> history = this.getVoidTransactionHistory();

		//Verify history records
		this.verifyVoidRecord(history.get(1));
		this.verifyReallocatePaymentRecord(history.get(0), history.get(3));

		lm.logOutLicenseManager();
	}

	/**
	 * Get 'Make Payment', 'Void', 'Reverse Fee' and 'Reallocate Payment' history record
	 * Make sure there should be only one
	 */
	private List<OrderHistory> getVoidTransactionHistory() {
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();

		logger.info("Start to get the only one 'Make Payment', 'Void', 'Reverse Fee' and 'Reallocate Payment' record in 'Privilege Order History' page.");
		makePaymentRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Make Payment");
		voidRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Void");
		reverseRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Reverse Fee");
		reallocateRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Reallocate Payment");
		if(makePaymentRecord==null ||makePaymentRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Void' record.", "1", String.valueOf(voidRecord.size()));
		}else if(voidRecord==null ||voidRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Void' record.", "1", String.valueOf(voidRecord.size()));
		}else if(reverseRecord==null ||reverseRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Reverse Fee' record.", "1", String.valueOf(reverseRecord.size()));
		}else if(reallocateRecord==null ||reallocateRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Reallocate Payment' record.", "1", String.valueOf(reallocateRecord.size()));
		}else{
			logger.info("Successfully verify ony one 'Void', 'Reverse Fee' and 'Reallocate Payment' record.");
		}

		List<OrderHistory> historyRecords = new ArrayList<OrderHistory>();
		historyRecords.add(makePaymentRecord.get(0));
		historyRecords.add(voidRecord.get(0));
		historyRecords.add(reverseRecord.get(0));
		historyRecords.add(reallocateRecord.get(0));
		return historyRecords; 
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

	/**
	 * Verify reallocate payment record
	 * @param makePaymentRecord: Transaction info like: $25.00 of $25.00 VISA payment
	 * @param reallocateRecord: Transaction info like: $0.00 of $25.00 VISA payment; Approved ($25.00) VISA refund 
	 */
	private void verifyReallocatePaymentRecord(OrderHistory makePaymentRecord, OrderHistory reallocateRecord){
		String totalPaymentAmount = "";

		logger.info("Start to get total pay amount.");
		totalPaymentAmount = makePaymentRecord.infoTransaction .split("of")[1].trim();
		logger.info("Successfully get total payment amount:"+totalPaymentAmount);

		logger.info("Start to verify reallocate payment record.");
		if(!reallocateRecord.infoTransaction .startsWith("$0.00 of "+ totalPaymentAmount)){
			throw new ErrorOnDataException("Reallocate Payment record should not be:"+reallocateRecord.infoTransaction );
		}
		logger.info("Successfully verify reallocate payment record:"+reallocateRecord.infoTransaction );
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		privilege.purchasingName = "VHR-HarvestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";

		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12346";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
