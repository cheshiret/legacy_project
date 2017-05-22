/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.uppercaseoutput.off;


import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a privilege.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 * 				 #Title: user name
 *				 #Sales Location: location name/address
 *				 #Privilege orders: user name/address 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "HF HQ Role" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='false' where name like 'UppercaseOutput'>; 
 * 					3. A privilege product: 703-ProcessFeeAdjustments
 * 					4. Customer: QA-RAFee3/TEST-RAFee3 
 * 				
 * @debugHistory:1,Assign feature 'RequestConfirmationLetter' to 'HF HQ Role'.
 * 				 2,If feature above assigned, but button 'Request H&F Conf Letter' still did not appear, we should run SQL following:
 * 					UPDATE C_CONF_LETTER_CNFG SET ALLOW_FAX_IND = 1, ALLOW_EMAIL_IND = 1, ALLOW_PRINT_IND = 1 WHERE DELETED_IND =0 AND LOC_ID =1 AND LETTER_TYPE_ID=3
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
	private static final boolean NON_UPPER_CASE = false;

	private String pdfFileFolder;
	private String fullFileName;
	private String receiptNum;
	private String totalFee;
	private String exceptedPriInfo;
	
	private static final String[] REPORT_LINE_STRING = {
		"QA-RAFee3 TEST-RAFee3",
		"Xian",
		"York AL 36925",
		"WAL-MART",
		"WAL-MART, Belden, Mississippi, 38826",
		"TEST-RAFee3, QA-RAFee3; Non Resident",
		"Xian",
		"York, AL, 36925"
		};
	private static final String SUCCESS_RESULT="11111111"; // means 7 strings of 'REPORT_LINE_STRING' are all found. 
	
	@Override
	public void execute() {
		
		//Set upper case configuration to 'true'.
		lm.updateUpperCaseConfigInDB(schema,NON_UPPER_CASE); 
		
		
		lm.loginLicenseManager(login);
			
		// make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		pay.belongOrderNum =lm.processOrderCart(pay);
		if (pay.belongOrderNum.contains(" ")) {
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		//pay.belongOrderNum = "8-39872";
		logger.info("OrderNum is "+pay.belongOrderNum);
		
		lm.gotoPrivilegeOrderDetailPage(pay.belongOrderNum);
		this.receiptNum = lm.gotoReceiptPageFromPrivilegeOrder();
		logger.info("receiptNum is "+receiptNum);
		
		this.fullFileName = lm.getReceiptConfirmationLetterOnline(pdfFileFolder, receiptNum);
		
		this.checkUpperCaseInConfirmationLetter();
		//Add check point by Jasmine.
		this.verifyPriSaleOrderInfo(exceptedPriInfo, pay.belongOrderNum, this.receiptNum,totalFee);

		
		//log out LM
		lm.logOutLicenseManager();

	}
		
	/**
	 * get parser content.
	 * @return
	 */
    private List<String> getParserContent(){
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
		if(null == lines){
			throw new ErrorOnPageException("No PDF content exist");
		}
		return lines;
    }
    
	private void checkUpperCaseInConfirmationLetter() {

		
		List<String> lines = this.getParserContent();
		if(null == lines || lines.size()<1){
			throw new ErrorOnPageException("No PDF file content exist");
		}
		/* check following part in report:
		 * 
			qA-RAFee3 TEST-RAFee3
			Xian
			York AL 36925
		
			WAL-MART
			WAL-MART, Belden, Mississippi, 38826
			
			TEST-RAFee3, QA-RAFee3; Non Resident; Xian
			York, AL, 36925
		 */
		int result = 0;
		/**
		 * we use first 7 bit of variable 'result'.
		 * if all comparing pass(7 values of array REPORT_LINE_STRING), 'result' should be '01111111'.
		 * if only the comparing with REPORT_LINE_STRING[0], not pass. 'result' should be '01111110'.
		 * if only the comparing with REPORT_LINE_STRING[1], not pass. 'result' should be '01111101'. 
		 * if only the comparing with REPORT_LINE_STRING[6], not pass. 'result' should be '01011111'.
		 * */
		for(int i=0; i<REPORT_LINE_STRING.length; i++)//7 strings
		{
			for(String line: lines)
			{
				if(line.equals(REPORT_LINE_STRING[i]))
				{
					result |= 1<<i;
					break;
				}
			}
		}	
		
		
		logger.info("Check result is -->"+Integer.toBinaryString(result)+", 0 means cannot find the coresponding string.");
	
		String notFound="";
		
		
		//generate error result.
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
	/**
	 * check the confirm letter info. Add by Jasmine.
	 * @param lists
	 * @param info
	 * @return
	 */
	private boolean checkInfo(List<String> lists,String info){
		boolean isEqual = false;
		for(String line: lists){
			if(line.toUpperCase().equals(info.toUpperCase())){
				isEqual = true;
				logger.info(""+info+" exist in confirm letter");
				break;
			}
		}
		return isEqual;
	}
	
	/**
	 * verify the privilege product info and order number and receipt number confirm letter info. Add by Jasmine.
	 * @param lists
	 * @param info
	 * @return
	 */
	private void verifyPriSaleOrderInfo(String priInfo,String orderNum,String receiptNum,String totalFee){
		List<String> array = this.getParserContent();
		boolean isPass = true;
		isPass &= this.checkInfo(array, priInfo);
		isPass &= this.checkInfo(array, orderNum);
		isPass &= this.checkInfo(array, receiptNum);
		if(!totalFee.contains(".")){
			totalFee = "$" + totalFee+".00";
		}
		isPass &= this.checkInfo(array, totalFee);
		if(!isPass){
			throw new ErrorOnPageException("Privilege Confirm letter error");
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

		privilege.code = "703";
		privilege.name = "ProcessFeeAdjustments";
		privilege.purchasingName = "703-ProcessFeeAdjustments";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";

		
		cust.lName = "TEST-RAFee3";
		cust.fName = "QA-RAFee3";
		cust.dateOfBirth = "Jun 01 1980";
		
		pay.payType = "Cash";
		
		//path to save ticket printed PDF file
		this.pdfFileFolder = logger.getLogPath();
		
		totalFee = "20";/*lm.getCustPriceByPrdCodeAndAppToAllState(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID);*/
		exceptedPriInfo = "\\("+String.valueOf(DateFunctions.getCurrentYear())+"\\) "+privilege.code+" - "+privilege.name+"; Valid: "+DateFunctions.getToday("EEE MMM d yyyy")+" to";
	}
}

