package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentFulfillmentInfo;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment.LicMgrDocFulfillmentPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Purchase a privilege with inventory type in web, and select to fulfilled by mail to finish the purchase.
 * 1. Purchase the privilege in web and fulfilled by mail.
 * 2. On Confirmation page, print the order and check no inventory number on the print file  
 * @Preconditions:
 * 1. SQL about setup fulfillment method for web, setup by updateXPROP.sql:
 * select * from x_prop where name like 'ApplicableInventoryFulfillmentMethods' and namespace='PublicWeb' and value='1,2';
 * (1 - fulfilled by mail, 2 - inventory on hand)
 * 2. Inventory type "ConvPack Type" exists, create and allocate inventories for the type
 * 3. Product "SIA - HFSK InventoryProd001" exists, and set as below:
 * Product Group: Inventory
 * Inventory type: ConvPack Type
 * Inventory Quantity: 2
 * Assign to store
 * 4. Product "SIB - HFSK InventoryPriv001" exists, and set as below:
 * Product Group: Privileges
 * Inventory type: ConvPack Type
 * Qty Type: Fixed
 * Inventory Quantity: 1
 * Assign to web
 * @LinkSetUp:
 * d_assign_feature:id=4562
 * d_web_hf_signupaccount:id=990
 * d_hf_priv_invtype_licyear:id=80
 * d_hf_add_allo_pri_inv:id=80
 * d_hf_add_privilege_prd:id=2230
 * d_hf_add_pricing:id=3122
 * d_hf_assi_pri_to_store:id=1480
 * d_hf_add_prvi_license_year:id=2270
 * d_hf_add_qty_control:id=1460
 * @SPEC:
 * Convenience Pack - Licence with inventory type sales flow - fulfilled by mail [TC:101706]
 * @Task#: Auto-1865
 * @Note: If there is always a fulfillment job in progress in system manager, please use the sql to clean up:
 * update O_RCPT_FULFILLMENT_JOB set status_id = 3, comments = 'Manual Update' where id = 14447736; 
 * @author Lesley Wang
 * @Date  Aug 29, 2013
 */
public class PurchasePrivWithInvType_FulfilledByMail extends HFSKWebTestCase {
	private String path, fullFileName, privOrd, storeName, privInvTypeCode, receiptDate, dateFormat;
	private LoginInfo loginSM = new LoginInfo();
	private SystemManager sm = SystemManager.getInstance();
	private DocumentFulfillmentInfo docFulfillInfo;
	
	@Override
	public void execute() {
		//Get the previous privilege number which is stored in qa_automation table
		String values[] = lm.readQADB(this.caseName).split(",");
		
		// if there is no value in DB, purchase a privilege with inventory and fulfill by mail in web, then process document fulfillment in system manager
		if (values.length > 0 && lm.checkOrderExists(schema, values[0])) {
			if (!isReadyForTest(values[1])) {
				throw new TestCaseFailedException("Order #" + values[0] + " has not been fulfilled by mail by system. Please run this case after 1 hour of " + values[1] + ".");
			}
			// Go to License Manager to check the fulfilled document's print file - inventory type numbers should be shown on the file
			privOrd = values[0];
			receiptDate = values[1];
			this.initailDocFulfillInfo(privOrd, receiptDate);
			
			// Go to License Manager to check the fulfilled document's print file - inventory type numbers should be shown on the file
			lm.loginLicenseManager(loginLM);
			lm.gotoDocumentFulfillmentPg();
			this.verifyPrivilegeFulfilled();
//			fullFileName = lm.printDocumentFulfillment(docFulfillInfo, path); // Java Crash when print document by auto
//			this.verifyPrivInvNumInPrintFile(privOrd, privilege.invType, docFulfillInfo.getInventoryStartNum()); // Empty inventory number
			lm.logOutLicenseManager();
			lm.writeQADB(this.caseName, "");
		} else {
			// Create a new order for next running
			// Purchase the privilege in web, fulfilled by mail
			hf.invokeURL(url);
			hf.makePrivilegeOrderToCart(cus, privilege, false);
			privOrd = hf.checkOutCartToConfirmationPage(pay);

			// Check there is no inventory type number in the print file
			fullFileName = hf.printPrivilegesOnConfirmationPg(path);
			this.verifyPrivInvNumInPrintFile(privOrd, privilege.invType, StringUtil.EMPTY); // Empty inventory number
			hf.signOut();
			
			// Go to System Manager to start document fulfillment
			sm.loginSystemManager(loginSM);
			sm.startDocumentFulfillment();	// Can't log out because ajax refresh will always exist until the process is done. Just kill browser 		
			
			//Insert order number and privilege number into db for next running
			receiptDate = DateFunctions.formatDate(DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema)), dateFormat);
			lm.writeQADB(this.caseName, privOrd + "," + receiptDate);
			throw new TestCaseFailedException("Order #" + privOrd + " has not been fulfilled by mail by system. Please run this case after 1 hour of " + receiptDate + ".");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer info
		cus.fName = "ConvPack02_FN"; // d_web_hf_signupaccount, id=990
		cus.lName = "ConvPack02_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = OrmsConstants.IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4154";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		
		// Privilege info
		privInvTypeCode = "ConvPack Type";
		privilege.invType = "Con#";
		privilege.inventoryQty = "1";
		privilege.name = "HFSK InventoryPriv001";
		privilege.licenseYear = hf.getFiscalYear(schema);;
		privilege.qty = "1";		
		
		// Update inventory type to make the type alias shown as the label for the inventory type on print file
		hf.updatePrivInvType(schema, privInvTypeCode, "1", privilege.invType);
		
		// Login info for LM
		loginLM.location = "SK Admin - Auto/SASK";
		storeName = "Ministry of Environment - Big River";
		
		// Download print file path
		path =logger.getLogPath();		
		
		// SM login info
		loginSM.url = AwoUtil.getOrmsURL(env, "systemmgr");
		loginSM.contract = loginLM.contract;
		loginSM.location = "Administrator - Auto/Saskatchewan Ministry of Environment";
		loginSM.userName = TestProperty.getProperty("orms.adm.user");
		loginSM.password = TestProperty.getProperty("orms.adm.pw");
		
		dateFormat = "yyyy/MM/dd h:m a";
	}

	private void initailDocFulfillInfo(String privOrd, String date) {
		docFulfillInfo = new DocumentFulfillmentInfo();
		docFulfillInfo.setReceiptNum(hf.getReceiptNumsByOrderNum(schema, privOrd).get(0));
		date = DateFunctions.formatDate(date, "MM/dd/yyyy");
		docFulfillInfo.setReceiptDateFrom(date);
		docFulfillInfo.setReceiptDateTo(date);
		docFulfillInfo.setInventoryStartNum(hf.getAvailablePrivilegeInventoryNumber(schema, privInvTypeCode, storeName));
	}
	
	/** Verify the inventory type info is displayed on the print file */
	private void verifyPrivInvNumInPrintFile(String ordNum, String invType, String invNum) {
		String privNum = hf.getPrivilegeNumByOrdNum(schema, ordNum);
		String msg = invType + ":";
		File file = new File(this.fullFileName);
		
		if(!file.exists()) {
			throw new ItemNotFoundException("Can't find print privileges PDF file - " + this.fullFileName);
		}

		List<String> lineContents = PDFParser.getPDFContentInOrder(file.getAbsolutePath());
		boolean found=false;
		for(int i = 0; i < lineContents.size(); i++) {
			String line = lineContents.get(i);
			logger.info("Text line in PDF is -->"+line);
			if(line.toUpperCase().contains(privNum))
			{
				logger.info(lineContents.get(i+1).trim());
				logger.info(lineContents.get(i+2).trim());
				found = lineContents.get(i+1).trim().equalsIgnoreCase(msg) && lineContents.get(i+2).trim().equalsIgnoreCase(invNum);
				break;
			}
		}
	
		if (!found) {
			throw new ErrorOnPageException("The inventory number info:" + msg + invNum +  "should be displayed in print file!");
		}
		logger.info("---Successfully verify the inventory number info:" + msg + invNum +  "is in print file!");
	}
	
	private boolean isReadyForTest(String receiptDate) {
		String current = DateFunctions.formatDate(DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema)), dateFormat);
		return DateFunctions.diffMinBetween(current, receiptDate) > 60;
	}
	
	private void verifyPrivilegeFulfilled() {
		LicMgrDocFulfillmentPage docFulfillPg = LicMgrDocFulfillmentPage.getInstance();
		docFulfillPg.searchDocFulfillment(docFulfillInfo);
		String receiptNum = docFulfillInfo.getReceiptNum();
		if (!docFulfillPg.isReceiptNumExist(receiptNum)) {
			throw new ErrorOnPageException("The privilege is not fulfilled. Receipt Num=" + receiptNum);
		}
		logger.info("Successfully verify privielge fulfilled. Receipt Num=" + receiptNum);
	}
}
