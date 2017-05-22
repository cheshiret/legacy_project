package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReprintPrivilegeWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Get Privilege List for Reprint
 * @Preconditions:1.privilege can be purchased
 *                (shall include print document)
 *                3.privilege shall not have any rules for purchase
 * @SPEC:Get Privilege List for Reprint.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 19, 2012
 */
public class GetPrivilegeListforReprint extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private LicMgrPrivilegePrintDocumentsPage printDocumentsPage = LicMgrPrivilegePrintDocumentsPage.getInstance();
	private String warnMsg = "";
	private Customer toCust = new Customer();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		// add print document for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Print Documents");
		
		//check and deactive the exist document if already there
		this.checkAndDeactivateDocument();
		lm.addPrintDocumentForProduct(document);

		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.gotoHomePage();
		// make an privilege order for transfer from customer
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay, false).split(" ")[0];//doesn't print
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		// transfer to transfer to customer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		String transferredOrderNum = lm.processOrderCart(pay, false).split(" ")[0];//doesn't print
		lm.gotoPrivilegeOrderDetailPage(transferredOrderNum);
		
		//get maximum reprint count of document template
		int maxReprintCountOfDocTem = lm.getMaxReprintCountOfDocTemplate(schema, document.docTepl);
		this.verifyNumberOfReprint(maxReprintCountOfDocTem + 1);
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "1986/08/12";
		cust.fName = "QA-TransferRule6";
		cust.lName = "TEST-TransferRule6";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		privilege.code = "999";
		privilege.name = "TransferPrivilege";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-TransferQues";
		toCust.lName = "TEST-TransferQues";
		toCust.dateOfBirth = "Jan 2 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";
		
		//document info
		document.prodType = "privilege";
//		document.docTepl = "DocTemplate_GetList2";
		document.docTepl = "License Document";
		document.equipType = "All";
		document.purchaseTypes = new String[]{"Transfer", "Original"};
		document.licYearFrom = "All";
		document.effectiveFromDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1),"EE MMM d yyyy");
		document.effectiveToDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(3),"EE MMM d yyyy");
		document.printOrd = "5";
		document.fulfillMethod = "Printed Immediately";
		document.configVariables = new String[]{"aaa"};
		
		warnMsg = "There is no Document to print.";
	}
	
	/**
	 * Deactive existed print document.
	 */
	private void checkAndDeactivateDocument() {
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		printDocSubTab.showActiveDocRecords();
		logger.info("Deactive existed print document.");
		List<String> documentAssignIDs = new ArrayList<String>();
		
		if(document.equipTypes.length > 0){
			for(int i = 0; i < document.equipTypes.length; i++){
				document.equipType = document.equipTypes[i];
				if(document.purchaseTypes.length > 0){
					for(int j = 0; j < document.purchaseTypes.length; j++){
						document.purchaseType = document.purchaseTypes[j];
					}
					documentAssignIDs = printDocumentsPage.getPrintDocumentAssignmentIdsForCleanUp(document);
					for(int k=0;k<documentAssignIDs.size();k++){
						logger.info("To deactvate document id is : " + documentAssignIDs.get(k));
						lm.deactivePrivilegeDocumentAssignment(documentAssignIDs.get(k));
					}
				} else if(!"".equals(document.purchaseType)){
					documentAssignIDs = printDocumentsPage.getPrintDocumentAssignmentIdsForCleanUp(document);
					for(int k=0;k<documentAssignIDs.size();k++){
						logger.info("To deactvate document id is : " + documentAssignIDs.get(k));
						lm.deactivePrivilegeDocumentAssignment(documentAssignIDs.get(k));
					}
				}
			}
		} else if(!"".equals(document.equipType)) {
			if(document.purchaseTypes.length > 0){
				for(int j = 0; j < document.purchaseTypes.length; j++){
					document.purchaseType = document.purchaseTypes[j];
					documentAssignIDs = printDocumentsPage.getPrintDocumentAssignmentIdsForCleanUp(document);
					for(int k=0;k<documentAssignIDs.size();k++){
						logger.info("To deactvate document id is : " + documentAssignIDs.get(k));
						lm.deactivePrivilegeDocumentAssignment(documentAssignIDs.get(k));
					}
				}
			} else if(!"".equals(document.purchaseType)){
				documentAssignIDs = printDocumentsPage.getPrintDocumentAssignmentIdsForCleanUp(document);
				for(int k=0;k<documentAssignIDs.size();k++){
					logger.info("To deactvate document id is : " + documentAssignIDs.get(k));
					lm.deactivePrivilegeDocumentAssignment(documentAssignIDs.get(k));
				}
			}
		}
	}
	
	/**
	 * Verify number of reprint.
	 * @param maxNum
	 */
	private void verifyNumberOfReprint(int maxNum){
		logger.info("Verify number of reprint.");
		String msg = "";
		int cnt = 0;
		while(cnt < maxNum){
			msg = lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
			cnt++;
			logger.info("This is:"+cnt+"times.");
			if(warnMsg.equals(msg)){
				throw new ErrorOnPageException("This order expected can be reprinted " + (maxNum+1) + " times, but this time is only " + (cnt) + " time and failed.");
			}
		}

		LicMgrPrivilegeOrderDetailsPage orderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		LicMgrReprintPrivilegeWidget reprintWidget = LicMgrReprintPrivilegeWidget.getInstance();
		orderDetailsPage.clickReprint();
		ajax.waitLoading();
		reprintWidget.waitLoading();
		reprintWidget.selectReprintReason("");
		reprintWidget.setNotes("");
		reprintWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(reprintWidget, orderDetailsPage);
		
		if(page == reprintWidget) {
			msg = reprintWidget.getWarnMessage();
			if(!msg.equals(warnMsg)) {
				throw new ErrorOnPageException("This order only can be reprinted " + maxNum+1 + " times, but this time is " + (maxNum+2) + " time.");
			}
		}
		
		reprintWidget.clickClose();
		ajax.waitLoading();
		orderDetailsPage.waitLoading();
	}
}
