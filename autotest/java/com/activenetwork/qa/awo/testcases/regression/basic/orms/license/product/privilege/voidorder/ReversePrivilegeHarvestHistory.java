package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
 * 1. Make privilege order using the privilege which has one harvest document, print the document
 * 2. Reverse privilege
 * 3. Verify "Reverse Harvest Record" and "Generate Document" history record
 * 
 * @Preconditions:
 * 1. Have Harvest template: Harvest DOC
 * 2. Have privilege product: "VHR-HarvestPrivilege"
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968
 * 
 * @author SWang
 * @Date  2012-3-29
 */
public class ReversePrivilegeHarvestHistory extends LicenseManagerTestCase {
	private Harvest harvest = new Harvest();
	private LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage.getInstance();
	private String orderNum;
	private String[] privilegeNums;
	private List<OrderHistory> generateDocumentRecord = new ArrayList<OrderHistory>();
	private List<OrderHistory> reverseHarvestRecord = new ArrayList<OrderHistory>();

	public void execute() {
		lm.loginLicenseManager(login);

		//Make a privilege which has Harvest document order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay, false).split(" ")[0];

		//Reverse privilege
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		//Go to Harvest tab to get Harvest#
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Harvest");
		this.getHarvestNumber();

		//Go to privilege order history page
		privilegeNums = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeOrderHistoryPage();

		//Verify 'Generate Document', 'Reverse Harvest Record' history record
		List<OrderHistory> history = this.getReverseTransactionHistory();
		this.verifyGenerateDocumentRecord("Harvest DOC", history.get(0));
		this.verifyReverseHarvestRecord(harvest.harvestNum, history.get(1));

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//privilege product info
		privilege.code = "VHR";
		privilege.purchasingName = "VHR-HarvestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";

		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12342";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}

	/**
	 * Get harvest number
	 * @return
	 */
	private void getHarvestNumber(){
		privilegeNums = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		if(privilegeNums.length!=1){
			throw new ErrorOnDataException("The order:"+orderNum+" should only have one privilege number.");
		}else{
			logger.info("Successfully verify the order:"+orderNum+" only has one privilege number.");
		}

		lm.searchHarvestInCustHarvestListPg(harvest);
		harvest = harvestPage.getHarvestListInfo(privilegeNums[0]);
		logger.info("Get harvest number:"+harvest.harvestNum);
	}

	/**
	 * Get 'Generate Document', 'Reverse Harvest Record' history record
	 * Make sure there should be only one
	 */
	private List<OrderHistory> getReverseTransactionHistory() {
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();

		logger.info("Start to get ony one 'Generate Document' and 'Void Harvest Record' record in 'Privilege Order History' page.");
		generateDocumentRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Generate Document");
		reverseHarvestRecord = privOrderHistoryPg.getHistoryInfoByTransaction("Reverse Harvest Record");
		if(generateDocumentRecord==null ||generateDocumentRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Generate Document' record.", "1", String.valueOf(generateDocumentRecord.size()));
		}else if(reverseHarvestRecord==null ||reverseHarvestRecord.size()!=1){
			throw new ErrorOnPageException("There should be only one 'Void Harvest Record' record.", "1", String.valueOf(reverseHarvestRecord.size()));
		}else{
			logger.info("Successfully verify ony one 'Generate Document' and 'Void Harvest Record' record.");
		}

		List<OrderHistory> historyRecords = new ArrayList<OrderHistory>();
		historyRecords.add(generateDocumentRecord.get(0));
		historyRecords.add(reverseHarvestRecord.get(0));
		return historyRecords; 
	}

	/**
	 * Verify 'Generate Document' record
	 * @param harvestDocumentName
	 * @param documentTemplate: Transaction info like: Templates: Harvest DOC 
	 */
	private void verifyGenerateDocumentRecord(String harvestDocumentName, OrderHistory documentTemplate){
		logger.info("Start to verify Generate Document record.");
		String harvestDocument = documentTemplate.infoTransaction .split("Templates:")[1].trim();
		if(!harvestDocument.equals(harvestDocumentName)){
			throw new ErrorOnDataException("Harvest Document Template info is wrong.", harvestDocumentName, harvestDocument);
		}
		logger.info("Successfully verify Harvest Document Template info:"+harvestDocumentName);
	}

	/**
	 * Verify 'Reverse Harvest Record' (harvest number)
	 */
	private void verifyReverseHarvestRecord(String expectedHarvestNum, OrderHistory makePaymentRecord) {
		logger.info("Start to verify harvest# in 'Privilege Order History' page.");
		String harvestNum = makePaymentRecord.infoTransaction .split("Harvest \\#:")[1].trim();
		if(!harvestNum.equals(expectedHarvestNum)){
			throw new ErrorOnDataException("Can't find Harvest num:"+expectedHarvestNum, expectedHarvestNum, harvestNum);
		}
		logger.info("Successfully find Harvest number:"+expectedHarvestNum);
	}
}
