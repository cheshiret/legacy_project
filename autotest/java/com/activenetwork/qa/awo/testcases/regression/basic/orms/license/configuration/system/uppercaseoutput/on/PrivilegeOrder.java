/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.uppercaseoutput.on;


import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**@Attention: these test cases should be executed one by one, because it will update the same record to different value in DB.
 * 				testCases.regression.basic.orms.license.configuration.system.uppercaseoutput.on
 * 				testCases.regression.basic.orms.license.configuration.system.uppercaseoutput.off
 * 
 * @Description: 1. Purchase a privilege.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 * 				 #Title: user name
 *				 #Sales Location: location name/address
 *				 #Privilege orders: user name/address 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='true' where name like 'UppercaseOutput'>; 
 * 					3. A privilege product: 703-ProcessFeeAdjustments
 * 					4. Customer: QA-RAFee3/TEST-RAFee3 
 * 				
 * @SPEC:  Report body provides Upper case Output configuration
 * @Task#: AUTO-1147
 * 
 * @author pzhu
 * @Date  July 23, 2012
 * 
 */
public class PrivilegeOrder extends LicenseManagerTestCase {


	private LoginInfo loginfFnm = new LoginInfo();
	List<String[]> raFeeRecord;
	private static final boolean UPPER_CASE = true;

	private String pdfFileFolder;
	private String fullFileName;
	private String receiptNum;
	
	private static final String SUCCESS_RESULT="11111111"; // means 8 strings of 'REPORT_LINE_STRING' are all found. 
	
	@Override
	public void execute() {
		
		//Set upper case configuration to 'true'.
		lm.updateUpperCaseConfigInDB(schema,UPPER_CASE); 
		
		lm.loginLicenseManager(login);
			
		// make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		pay.belongOrderNum =lm.processOrderCart(pay);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		logger.info("OrderNum is "+pay.belongOrderNum);
		//pay.belongOrderNum = "8-520622";
		lm.gotoPrivilegeOrderDetailPage(pay.belongOrderNum);
		this.receiptNum = lm.gotoReceiptPageFromPrivilegeOrder();
		logger.info("receiptNum is "+receiptNum);
		
		this.fullFileName = lm.getReceiptConfirmationLetterOnline(pdfFileFolder, receiptNum);
		
		this.checkUpperCaseInConfirmationLetter();

		//log out LM
		lm.logOutLicenseManager();
	}

	private void checkUpperCaseInConfirmationLetter() {
		logger.info("Enter into checkUpperCaseInConfirmationLetter--->>");
				
		File file = new File(this.fullFileName);
		
		if(!file.exists()) {
			logger.info("Can't find confirmation letter PDF file - " + this.fullFileName);
			throw new ItemNotFoundException("Can't find confirmation PDF file - " + this.fullFileName);
		}
		String content = PDFParser.retrievePDFContent(file);
		logger.info("Start of PDF content------>>");
		logger.info(content);
		logger.info("End of PDF content------<<");
		
		List<String> lines = PDFParser.getPDFContentInOrder(file.getAbsolutePath());
		/* check following part in report:
		 * 
			QA-RAFEE3 TEST-RAFEE3
			XIAN
			YORK AL 36925
			
			
			WAL-MART
			WAL-MART, BELDEN, MISSISSIPPI, 38826
			
			TEST-RAFEE3, QA-RAFEE3; Non Resident; XIAN
			YORK, AL, 36925			
		 */
		int result = 0;
		/**
		 * we use first 7 bit of variable 'result'.
		 * if all comparing pass(7 values of array REPORT_LINE_STRING), 'result' should be '11111111'.
		 * if only the comparing with REPORT_LINE_STRING[0], not pass. 'result' should be '11111110'.
		 * if only the comparing with REPORT_LINE_STRING[1], not pass. 'result' should be '11111101'. 
		 * if only the comparing with REPORT_LINE_STRING[6], not pass. 'result' should be '11011111'.
		 * */

		final String[] REPORT_LINE_STRING = {
			"QA-RAFEE3 TEST-RAFEE3",
			"XIAN",
			"YORK AL 36925",
			"WAL-MART",
			"WAL-MART, BELDEN, MISSISSIPPI, 38826",
			"TEST-RAFEE3, QA-RAFEE3 ("+ cust.identifier.identifierNum+"); Non Resident",
			"XIAN",
			"YORK, AL, 36925"
		};
		for(int i=0; i<REPORT_LINE_STRING.length; i++)//8 strings
		{
			for(String line: lines)
			{
				if(line.replaceAll("\\\\", "").equals(REPORT_LINE_STRING[i]))
				{
					result |= 1<<i;
					break;
				}
			}
		}	
		
		logger.info("Check result is -->"+Integer.toBinaryString(result)+", 0 means cannot find the coresponding string.");
		String notFound="";
		
		/**
		 * Get those which do not pass the comparing. And print out.
		 * */
		if(!Integer.toBinaryString(result).equals(SUCCESS_RESULT))
		{
			for(int j=0; j<REPORT_LINE_STRING.length; j++)
			{
				if(((result>>j)&(0x01))==0)
				{
					notFound += "["+REPORT_LINE_STRING[j]+"]";
				}
			}
						
			throw new ErrorOnDataException("Cannot find following strings on report: "+notFound);
		}else{
			logger.info("Found all strings in report body.");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		loginfFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginfFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginfFnm.contract = "MS Contract";
		loginfFnm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.purchasingName = "703-ProcessFeeAdjustments";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";

		cust.lName = "TEST-RAFee3";
		cust.fName = "QA-RAFee3";
		cust.dateOfBirth = "Jun 01 1980";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		pay.payType = "Cash";
		
		//path to save ticket printed PDF file
		this.pdfFileFolder = logger.getLogPath();
	}
}
