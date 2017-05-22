package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify privilege print/reprint transaction info both in UI and DB are correct
 * @Preconditions: 1. an existing privilege product(pricing, agent assignment, license year, quantity control and document);
 * 							2. an available customer
 * @SPEC: <<Print Privilege Documents.UCS>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Apr 24, 2012
 */
public class PrintDocument extends LicenseManagerTestCase {
	private OrderTransactionInfo transactionInfo = new OrderTransactionInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//make a duplicated-original privilege order with printing document
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		orderNum = lm.processOrderCart(pay, true).split(" ")[0];// print document

		//go to privilege order detail to verify PRINT DOCUMENT transaction info
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeOrderHistoryPage();
		//verify transaction info on UI
		transactionInfo.setOrderID(orderNum);
		transactionInfo.setTransactionType(TRANNAME_GENERATE_DOCUMENT);
		transactionInfo.setTransactionTypeCode(TRANTYPE_GENERATE_DOCUMENT);
		this.verifyTransactionInfo(transactionInfo);
		
		//verify transaction info in DB
		this.verifyPrintTransactionInfoInDB(transactionInfo, privilege, schema, orderNum, OrmsConstants.TRANTYPE_GENERATE_DOCUMENT);
		//verify print count in DB
		this.verifyPrintIndexInDB(schema, orderNum, 1);
		lm.backPrivilegeOrderDetailPgFromHistoryPg();
		
		//reprint this privilege order and verify REPRINT DOCUMENT transaction info
		lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		lm.gotoPrivilegeOrderHistoryPage();
		//verify transaction info on UI
		transactionInfo.setTransactionType(OrmsConstants.TRANNAME_REPRINT_DOCUMENT);
		transactionInfo.setTransactionTypeCode(TRANTYPE_REPRINT_DOCUMENT);
		this.verifyTransactionInfo(transactionInfo);
		
		//verify transaction info in DB
		this.verifyPrintTransactionInfoInDB(transactionInfo, privilege, schema, orderNum, OrmsConstants.TRANTYPE_REPRINT_DOCUMENT);
		//verify print count in DB
		this.verifyPrintIndexInDB(schema, orderNum, 2);
		lm.backPrivilegeOrderDetailPgFromHistoryPg();
		
		//clean up
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//privilege info
		privilege.code = "PPD";
		privilege.name = "PrintPrivilegeDocuments";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		privilege.document.docTepl = "DocTempl_GetList4Reprint";
		
		//customer info
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19880312";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111192";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		//expected transaction info
		transactionInfo.setSalesChannel("4");//4 - Field, 3- Call Center, 2 - Web
		transactionInfo.setTransactionDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		transactionInfo.setTransactionInfo("Templates: " + privilege.document.docTepl);
		transactionInfo.setLocation(login.location.split("/")[1].trim());
		transactionInfo.setUser(DataBaseFunctions.getLoginUserName(login.userName).replaceAll(" ", StringUtil.EMPTY));
	}
	
	private void verifyTransactionInfo(OrderTransactionInfo oti) {
		LicMgrPrivilegeOrderHistoryPage historyPage = LicMgrPrivilegeOrderHistoryPage.getInstance();
		
		logger.info("Verify transaction info in Order Change History page.");
		List<OrderHistory> histories = historyPage.getHistoryInfoByTransaction(oti.getTransactionType());
		OrderHistory history = histories.get(histories.size() - 1);
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Transaction", oti.getTransactionType(), history.transaction);
		result &= MiscFunctions.compareResult("Information at time of transaction", oti.getTransactionInfo(), history.infoTransaction);
		result &= MiscFunctions.compareResult("Transaction Location", oti.getLocation(), history.transactionLocation);
		result &= MiscFunctions.compareResult("User", oti.getUser(), history.user);

		String date = history.date.substring(4, history.date.lastIndexOf(" "));// remove time zone and week
		date = date.substring(0, date.lastIndexOf(" "));// remove AM or PM
		date = date.substring(0, date.lastIndexOf(" "));// remove time
		if(!MiscFunctions.compareResult("Transaction Date", oti.getTransactionDate(), DateFunctions.formatDate(date, "M/d/yyyy"))){
			throw new ErrorOnPageException("Transaction Date & Time isn't correct!");
		}
		
//		result &= MiscFunctions.compareResult("Transaction Date", DateFunctions.formatDate(oti.getTransactionDate(), "MM/dd/yyyy"), DateFunctions.formatDate(history.date, "MM/dd/yyyy"));
		
		if(!result) {
			throw new ErrorOnPageException("Transaction info in Change History is wrong. Please refer to log for detail info.");
		} else logger.info("Transaction info in Change History is correct.");
	}
	
	private void verifyPrintTransactionInfoInDB(OrderTransactionInfo transinfo,PrivilegeInfo privilege,String schema, String ordNum,String transID) {
        logger.info("Veriy Print transaction info is correct in DB.");
        boolean result = true;
		String[] info = lm.getOrderTransInfoByOrderItemAndTransType(schema, ordNum, transID);
		String ordItemId = lm.getOrderItemID(schema, ordNum);
		
		result &= MiscFunctions.compareResult("Order ID", info[0], transinfo.getOrderID());
		result &= MiscFunctions.compareResult("Transaction Date", DateFunctions.formatDate(info[2].split(" ")[0], "MM/dd/yyyy"), DateFunctions.formatDate(transinfo.getTransactionDate(), "MM/dd/yyyy"));
		result &= MiscFunctions.compareResult("Transaction Location", info[3], transinfo.getLocation());
		result &= MiscFunctions.compareResult("Sales Channel", info[5], transinfo.getSalesChannel());
		result &= MiscFunctions.compareResult("Transaction Type", info[9], transinfo.getTransactionTypeCode());
		
        String[] doc = lm.getDocumentByOrderItem(schema, ordItemId);
        result &= MiscFunctions.compareResult("Document Template", doc[0], privilege.document.docTepl);
        result &= MiscFunctions.compareResult("Product Name", doc[1], privilege.name);
        if(!result) {
        	throw new ErrorOnDataException("Transaction info is wrong with DB, please refer log for details info.");
        } else logger.info("Transaction info in is really correct with DB.");
	}
	
	private void verifyPrintIndexInDB(String schema, String ordnum, int index) {
		String indexInDB = lm.getPrintCountForPrivDoc(schema, ordnum);
		if (index != Integer.parseInt(indexInDB)) {
			throw new ErrorOnPageException("Print count for privilege document is not correct. Expect print index is " + index + ", but actual is" + indexInDB);
		}
	}
}
