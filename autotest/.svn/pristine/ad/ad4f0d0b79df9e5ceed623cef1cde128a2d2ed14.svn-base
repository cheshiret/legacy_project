package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:1. print documents for privilege sale work flow
 *              2. re-print documents for privilege sale work flow
 *              3.fulfillment method is Print Immediately.
 * @Preconditions:privilege need print document(ForTestPrint) and set Max Reprint Count as 10000
 * @SPEC:Print Privilege Documents
 * @Task#:Auto-892
 * 
 * @author nding1
 * @Date  May 4, 2012
 */
public class PrintDocuments extends LicenseManagerTestCase {
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private Customer toCust = new Customer();
	private int count = 0;
	private OrderTransactionInfo transInfo = new OrderTransactionInfo();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// make an privilege order for transfer from customer
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		
		// transfer to transfer to customer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		String transferredOrderNum = lm.processOrderCart(pay).split(" ")[0];
		count++;
		transInfo.setOrderID(this.getOrderID(transferredOrderNum));

		// go to transfer to privilege order detail
		lm.gotoOrderPageFromOrdersTopMenu(transferredOrderNum);
		// go to history page
		this.gotoHistotyPage();
		// verify print info in the page
		transInfo.setTransactionType(TRANTYPE_PRINT_DOCUMENT);
		this.verifyPrintTransactionInfo(transInfo, TRANNAME_GENERATE_DOCUMENT);
		// verify print info in DB
		this.verifyPrintCountInDB(schema, transferredOrderNum, count);
		
		this.verifyPrintTransactionInfoInDB(transInfo, privilege, schema, transferredOrderNum, TRANTYPE_PRINT_DOCUMENT);
		this.backToOrderDetailPg();
		
		// reprint documents
		transInfo.setTransactionType(TRANTYPE_REPRINT_DOCUMENT);
        lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		count++;
		// go to history page
		this.gotoHistotyPage();
		// verify print info in the page
		this.verifyPrintTransactionInfo(transInfo, TRANNAME_REPRINT_DOCUMENT);
		// verify print info in DB
		this.verifyPrintCountInDB(schema, transferredOrderNum, count);
		this.verifyPrintTransactionInfoInDB(transInfo, privilege, schema, transferredOrderNum, TRANTYPE_REPRINT_DOCUMENT);
		this.backToOrderDetailPg();
		
		//clean up
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule6";
		cust.lName = "TEST-TransferRule6";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		
		privilege.code = "999";
		privilege.name = "TransferPrivilege";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.document.docTepl = "License Document";

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferQues";
		toCust.lName = "TEST-TransferQues";
		toCust.dateOfBirth = "Jan 2 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";
		this.setupTransInfo();
	}

	/**
	 * Go to order detail page from history page.
	 */
	private void backToOrderDetailPg() {
		LicMgrPrivilegeOrderHistoryPage historypg = LicMgrPrivilegeOrderHistoryPage.getInstance();
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		logger.info("Go to order detail page from history page.");
		historypg.clickOK();
		privOrderDetailsPage.waitLoading();
	}

	private void compare(String actual, String expt) {
		if (!expt.equals(actual)) {
			throw new ErrorOnPageException("Compare contents is not correct...", expt, actual);
		}
	}
	
	/**
	 * Compare date.
	 * @param actual
	 * @param expt
	 */
	private void compareDate(String actual, String expt){
		if(DateFunctions.compareDates(expt, actual)!=0){
			throw new ErrorOnPageException("Date is not correct...", expt, actual);
		}
	}

	/**
	 * Verify print transaction info in history page
	 * @param transinfo
	 * @param transID
	 */
	private void verifyPrintTransactionInfo(OrderTransactionInfo transinfo,String transName) {
		logger.info("Verify print transaction info in history page");
		LicMgrPrivilegeOrderHistoryPage historypg = LicMgrPrivilegeOrderHistoryPage.getInstance();
        List<OrderHistory> infos = historypg.getHistoryInfoByTransaction(transName);
		OrderHistory history = infos.get(infos.size()-1);
		this.compareDate(history.date.substring(4, 15), transinfo.getTransactionDate());
		this.compare(history.transaction, transName);
		this.compare(history.infoTransaction , transinfo.getTransactionInfo());
		this.compare(history.transactionLocation, transinfo.getLocation());
		this.compare(history.user, transinfo.getUser());
	}

	/**
	 * Verify print count in DB
	 * @param schema
	 * @param ordnum
	 * @param count
	 */
	private void verifyPrintCountInDB(String schema, String ordnum, int count) {
		logger.info("Verify print count in DB");
		String actualCount = lm.getPrintCountForPrivDoc(schema, ordnum);
		if ((count - Integer.parseInt(actualCount)) != 0) {
			throw new ErrorOnPageException(
					"Print count for privilege document is not correct..."
							+ "Expect count is " + count
							+ ", but actual count is" + actualCount);
		}
	}

	/**
	 * Verify print transaction info in DB.
	 * @param transinfo
	 * @param privilege
	 * @param schema
	 * @param ordItemnum
	 * @param transID
	 */
	private void verifyPrintTransactionInfoInDB(OrderTransactionInfo transinfo,PrivilegeInfo privilege,String schema, String ordItemnum,String transID) {
        logger.info("Verify print transaction info in DB.");
		String[] info=lm.getOrderTransInfoByOrderItemAndTransType(schema, ordItemnum, transID);
		String date = info[2].split(" ")[0];
        this.compareDate(date, transinfo.getTransactionDate());
        this.compare(info[9], transinfo.getTransactionType());
        this.compare(info[5], transinfo.getSalesChannel());
        this.compare(info[3], transInfo.getLocation());
        String ordItemID = this.getOrderItemID(ordItemnum);
        
        // verify document info
        String[] doc=lm.getDocumentByOrderItem(schema, ordItemID);
        this.compare(doc[0], privilege.document.docTepl);
        this.compare(doc[1], privilege.name);
	}

	/**
	 * Go to privilege order history page from privilege detail page.
	 */
	private void gotoHistotyPage() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		LicMgrPrivilegeOrderHistoryPage historypg = LicMgrPrivilegeOrderHistoryPage.getInstance();

		logger.info("Go to privilege order history page from privilege detail page.");
		privOrderDetailsPage.clickHistory();
		historypg.waitLoading();
	}
	
	/**
	 * Set up expect transaction information.
	 */
	private void setupTransInfo(){
		transInfo.setTransactionDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		transInfo.setTransactionInfo("Templates: "+privilege.document.docTepl);
		transInfo.setLocation(login.location.split("\\/")[1]);
		transInfo.setUser("Test-Auto,QA-Auto");
		transInfo.setSalesChannel("4");//2 - Web, 3 - Call Center, 4 - Filed
	}
	
	/**
	 * Get order ID from table o_order.
	 * @param orderNum
	 * @return
	 */
	private String getOrderID(String orderNum){
		logger.info("Query Order ID From DB.");
		db.resetSchema(schema);
		String query = "select ID from o_order where ORD_NUM='"+orderNum+"'";
		logger.debug("Execute query: " + query);
		String orderID = db.executeQuery(query, "ID", 0).trim();
		return orderID;
	}
	
	/**
	 * Get order item ID by order ID from table o_ord_item.
	 * @param orderNum
	 * @return
	 */
	private String getOrderItemID(String orderNum){
		logger.info("Query Order item ID From DB.");
		db.resetSchema(schema);
		String query = "select ID from o_ord_item where ord_id ='"+this.getOrderID(orderNum)+"'";
		logger.debug("Execute query: " + query);
		String orderItemID = db.executeQuery(query, "ID", 0).trim();
		return orderItemID;
	}
}
