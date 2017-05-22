package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.confletter;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.license.OrmsRequestHFConfirmLetterPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a privilege.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.

 * @Preconditions: 1. In Admin Manager,assign rule's feature "HF HQ Role" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='false' where name like 'UppercaseOutput'>; 
 * 					3. A privilege product: 703-ProcessFeeAdjustments
 * 					4. Customer: TEST-Basic1/QA-Basic1 
 * 				
 * @debugHistory:1,Assign feature 'RequestConfirmationLetter' to 'HF HQ Role'.
 * 				 2,If feature above assigned, but button 'Request H&F Conf Letter' still did not appear, we should run SQL following:
 * 					UPDATE C_CONF_LETTER_CNFG SET ALLOW_FAX_IND = 1, ALLOW_EMAIL_IND = 1, ALLOW_PRINT_IND = 1 WHERE DELETED_IND =0 AND LOC_ID =1 AND LETTER_TYPE_ID=3
 * @SPEC: Request On-Demand [TC:025078]
 * @Task#: AUTO-1272
 * 
 * @author Jasmine
 * @Date  July 23, 2012
 * 
 */
public class ConfirmLetterByEmailUiInfo extends LicenseManagerTestCase{
   private String expectedMsg;
	public void execute() {
		lm.loginLicenseManager(login);
		
		// make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		pay.belongOrderNum =lm.processOrderCart(pay);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		logger.info("OrderNum is "+pay.belongOrderNum);
		
		lm.gotoPrivilegeOrderDetailPage(pay.belongOrderNum);
		String receiptNum = lm.gotoReceiptPageFromPrivilegeOrder();
		logger.info("receiptNum is "+receiptNum);
		
		this.verifyConfLetterButtonEnable();
		
		lm.gotoConfimLetterPg();
		this.verifyConfirLetterCustInfo(cust);
		String actualMsg = lm.sentReceiptConfirmationLetterByEmail("Privilege");
		this.verifySendConfLetterMsg(actualMsg, expectedMsg);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		privilege.purchasingName = "703-ProcessFeeAdjustments";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";

		
		cust.lName = "TEST-Basic11";
		cust.fName = "QA-Basic11";
		cust.dateOfBirth = "Jun 08 1988";
		cust.email = "qabasic11.testbasic11@reserveamerica.com";
		cust.residencyStatus = "Non Resident";
		
		cust.physicalAddr.address = "Xian";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		
		pay.payType = "Cash";
		
		expectedMsg = "The confirmation letter request has been sent successfully.";
	}
	/**
	 * verify confirm letter button enable.
	 */
	private void verifyConfLetterButtonEnable(){
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage.getInstance();
		if(!receiptPg.isRequestHFConfLetterEnabled()) {
			throw new ErrorOnPageException("Confirm letter button should be enable");
		}else{
			logger.info("Confirm letter button enable is correct");
		}
	}
	/**
	 * verify confirm letter customer info.
	 * @param cust
	 */
	private void verifyConfirLetterCustInfo(Customer cust){
		OrmsRequestHFConfirmLetterPage confirmLetterPg = OrmsRequestHFConfirmLetterPage
				.getInstance();
		
		if(!confirmLetterPg.compareConfLetterCustInfo(cust)){
			throw new ErrorOnPageException("Confirm letter customer info error");
		}else{
			logger.info("Confirm letter customer info correct");
		}
	}
	/**
	 * verify the alert message after confirm letter send successfully.
	 * @param actualMsg
	 * @param expectedMsg
	 */
	public void verifySendConfLetterMsg(String actualMsg,String expectedMsg){
		if(!MiscFunctions.compareResult("Alert message", actualMsg, expectedMsg)){
			throw new ErrorOnPageException("Confirm letter can't send successfully");
		}else{
			logger.info("Confirm letter send successfully");
		}
	}

}
