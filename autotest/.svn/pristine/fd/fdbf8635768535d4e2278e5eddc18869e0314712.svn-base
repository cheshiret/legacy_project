/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:check point: 1. print documents for privilege sale work flow
 *                           2. re-print documents for privilege sale work flow
 * @Preconditions:privilege need print document
 * @SPEC:Print Privilege Documents
 * @Task#:AUTO-880
 * 
 * @author szhou
 * @Date Apr 26, 2012
 */
public class PrintDocuments_PrintImmediately extends LicenseManagerTestCase {
	private OrderTransactionInfo transinfo = new OrderTransactionInfo();
	private int count;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// purchase a privilege and print
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		// go to privilege order detail
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		// go to history page
		this.gotoHistotyPage();
		// verify print info in the page
		this.setupTransInfo(orderNum);
		this.verifyPrintTransactionInfo(transinfo,TRANNAME_PRINT_DOCUMENT);
//		this.verifyPrintTransactionInfo(transinfo,TRANNAME_GENERATE_DOCUMENT);
		
		// verify print info in DB
		this.verifyPrintCountInDB(schema, orderNum);
		this.verifyPrintTransactionInfoInDB(transinfo,privilege,schema,orderNum,TRANTYPE_PRINT_DOCUMENT);
//		this.verifyPrintTransactionInfoInDB(transinfo,privilege,schema,orderNum,TRANTYPE_GENERATE_DOCUMENT);
		this.backToOrderDetailPg();
		
		// reprint documents
        lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
        
		// go to history page
		this.gotoHistotyPage();
		
		// verify print info in the page
		transinfo.setTransactionType(TRANTYPE_REPRINT_DOCUMENT);
		this.verifyPrintTransactionInfo(transinfo,TRANNAME_REPRINT_DOCUMENT);
		
		// verify print info in DB
		this.verifyPrintCountInDB(schema, orderNum);
		this.verifyPrintTransactionInfoInDB(transinfo,privilege,schema,orderNum,TRANTYPE_REPRINT_DOCUMENT);
		this.backToOrderDetailPg();
		
		//clean up
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	private void backToOrderDetailPg() {
		LicMgrPrivilegeOrderHistoryPage historypg = LicMgrPrivilegeOrderHistoryPage
		.getInstance();
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
		.getInstance();
		
		historypg.clickOK();
		privOrderDetailsPage.waitLoading();
	}

	private void compare(String actual, String expt) {
		if (!expt.equals(actual)) {
			throw new ErrorOnPageException(
					"Compare contents is not correct...", expt, actual);
		}
	}

	private void verifyPrintTransactionInfo(OrderTransactionInfo transinfo,String transID) {
		LicMgrPrivilegeOrderHistoryPage historypg = LicMgrPrivilegeOrderHistoryPage
				.getInstance();
        List<OrderHistory> infos=historypg.getHistoryInfoByTransaction(transID);
		OrderHistory history = infos.get(0);

		String date = history.date.substring(4, history.date.lastIndexOf(" "));// remove time zone and week
		date = date.substring(0, date.lastIndexOf(" "));// remove AM or PM
		date = date.substring(0, date.lastIndexOf(" "));// remove time
		if(!MiscFunctions.compareResult("Date", transinfo.getTransactionDate(), DateFunctions.formatDate(date, "M/d/yyyy"))){
			throw new ErrorOnPageException("Date & Time isn't correct!");
		}
		
//		this.compare(DateFunctions.formatDate(date, "MM/dd/yyyy"), DateFunctions.formatDate(transinfo.getTransactionDate(), "MM/dd/yyyy"));
		this.compare(history.transaction, this.convertType(transinfo.getTransactionType()));
		this.compare(history.infoTransaction , transinfo.getTransactionInfo());
		this.compare(history.transactionLocation, transinfo.getLocation());
		this.compare(history.user, transinfo.getUser());
	}

	private void verifyPrintCountInDB(String schema, String ordnum) {
		String actualCount = lm.getPrintCountForPrivDoc(schema, ordnum);
		count++;
		if ((count - Integer.parseInt(actualCount)) != 0) {
			throw new ErrorOnPageException(
					"Print count for privilege document is not correct..."
							+ "Expect count is " + count
							+ ", but actual count is" + actualCount);
		}
	}

	private void verifyPrintTransactionInfoInDB(OrderTransactionInfo transinfo,PrivilegeInfo privilege,String schema, String ordnum,String transID) {
		String orderItemId=this.getOrderItemID(ordnum);
        String[] info=lm.getOrderTransInfoByOrderItemAndTransType(schema, ordnum, transID);
        this.compare(DateFunctions.formatDate(info[2].split(" ")[0], "MM/dd/yyyy"), DateFunctions.formatDate(transinfo.getTransactionDate(), "MM/dd/yyyy"));
        this.compare(info[9], transinfo.getTransactionType());
        this.compare(info[5], transinfo.getSalesChannel());
        this.compare(info[3], transinfo.getLocation());
        
        String[] doc=lm.getDocumentByOrderItem(schema, orderItemId);
        this.compare(doc[0], privilege.document.docTepl);
        this.compare(doc[1], privilege.name);
	}

	private void gotoHistotyPage() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrPrivilegeOrderHistoryPage historypg = LicMgrPrivilegeOrderHistoryPage
				.getInstance();

		privOrderDetailsPage.clickHistory();
		historypg.waitLoading();
	}
	
	private void setupTransInfo(String order){
		transinfo.setOrderID(order);
		transinfo.setTransactionDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		transinfo.setTransactionType(TRANTYPE_PRINT_DOCUMENT);
//		transinfo.setTransactionType(TRANTYPE_GENERATE_DOCUMENT);
		transinfo.setTransactionInfo("Templates: "+privilege.document.docTepl);
		transinfo.setLocation(login.location.split("\\/")[1]);
		transinfo.setUser("Test-Auto,QA-Auto");
		transinfo.setSalesChannel("4");//2 - Web, 3 - Call Center, 4 - Filed
	}
	
	private String getOrderItemID(String orderNum){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		logger.info("Query Order item ID From DB.");
		db.resetSchema(schema);
		String query = "select o_ord_item.ID from o_ord_item INNER JOIN o_order ON o_order.ID=o_ord_item.ord_id where o_order.ORD_NUM ='"+orderNum+"'";
		logger.debug("Execute query: " + query);
		String orderItemID = db.executeQuery(query, "ID", 0).trim();
		return orderItemID;
	}
	
	private String convertType(String trans){
		if(trans.matches(TRANTYPE_PRINT_DOCUMENT)){
			return TRANNAME_PRINT_DOCUMENT;
		}else if(trans.matches(TRANTYPE_GENERATE_DOCUMENT)) {
			return TRANNAME_GENERATE_DOCUMENT;
		} else if (trans.matches(TRANTYPE_REPRINT_DOCUMENT)){
			return TRANNAME_REPRINT_DOCUMENT;
		}else{
			return null;
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login license manager loginInfo
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.document.docTepl = "License Document";
		privilege.name="CalculateOrderPrice";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000013";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic13";
		cust.fName = "QA-Basic13";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		transinfo.setLocation(login.location.split("\\/")[1]);
	}

}
