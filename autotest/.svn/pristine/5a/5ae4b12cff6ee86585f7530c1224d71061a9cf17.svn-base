package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.confletter;

import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a consumable.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 *               Cust name, order number, receipt number, consumable fiscal year and consumable product name.
 * 				 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='true' where name like 'UppercaseOutput'>;
 * 					3. A vehicle product: V03 - ProcessFeeAdjustments
 * 					4. Customer: QA-RAFee2/TEST-RAFee2 
 * 				
 * @SPEC: TC 024171
 * @Task#: AUTO-1272
 * 
 * @author Jasmine
 * @Date  Oct 08, 2012
 * 
 */
public class PosConfLetterByPDF extends LicenseManagerTestCase{
    private String totalFee;
    private String fullFileName;
    private String pdfFileFolder;
    private String[] REPORT_LINE_STRING; 
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
		pay.belongOrderNum =  lm.processOrderCart(pay);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		//Update the order number;
		REPORT_LINE_STRING[6] = REPORT_LINE_STRING[6].replace("//Num//", pay.belongOrderNum);
		logger.info(" Consumable order number is "+pay.belongOrderNum);
		lm.gotoConsumableOrderDetailsPage(pay.belongOrderNum);
		String receiptNum = lm.gotoReceiptPageFromConsumableOrder();
		//Update the receiptNum number.
		REPORT_LINE_STRING[7] = REPORT_LINE_STRING[7].replace("//Num//", receiptNum);
		logger.info("receiptNum is "+receiptNum);
		
		this.fullFileName = lm.getReceiptConfirmationLetterOnline(pdfFileFolder, receiptNum);
		this.checkInfoInConfirmationLetter();
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
  /**
   * compare the expected info in PDF;
   * @param expectedInfo
   * @return
   */
    private boolean comparePDFInfo(String expectedInfo){
    	boolean isEqual = false;
    	List<String> lines = this.getParserContent();
    	if(null  ==lines ||lines.size()<1){
			throw new ErrorOnPageException("No pdf contend exist");
		}
    	for (String line : lines) {
			if (line.toUpperCase().equals(expectedInfo.toUpperCase())) {
				isEqual = true;
				logger.info(""+expectedInfo+" was found in PDF");
				break;
			}
		}
    	return isEqual;
    }
    /**
     * check expected info in confirmation letter.
     */
	private void checkInfoInConfirmationLetter() {
		boolean isPass =true;
		List<String> lines = this.getParserContent();
		if(null  ==lines ||lines.size()<1){
			throw new ErrorOnPageException("No pdf contend exist");
		}
		
		for (int i = 0; i < REPORT_LINE_STRING.length; i++)// 7 strings
		{
			isPass &= this.comparePDFInfo(REPORT_LINE_STRING[i]);
		}
		if(!isPass){
			throw new ErrorOnPageException("Confirm letter Info error");
		}
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		consumable.name = "WL2 - POSForVoid";
		consumable.quantity = "1";
		consumable.code = "WL2";

		cust.lName = "TEST-RAFee2";
		cust.fName = "QA-RAFee2";
		cust.dateOfBirth = "Jun 01 1980";

		totalFee = lm.getCusumableCustPrice(schema, consumable.code);
		if (!totalFee.contains(".")) {
			totalFee = totalFee + ".00";
		}

		// path to save ticket printed PDF file
		this.pdfFileFolder = logger.getLogPath();

		REPORT_LINE_STRING = new String[9];
		REPORT_LINE_STRING[0] = login.location.split("/")[1];
		REPORT_LINE_STRING[1] = "WAL-MART, Belden, Mississippi, 38826";
		REPORT_LINE_STRING[2] = cust.fName + " " + cust.lName;
		REPORT_LINE_STRING[3] = "XIAN";
		REPORT_LINE_STRING[4] = "YORK AL 36925";
		REPORT_LINE_STRING[5] = "\\(" + lm.getFiscalYear(schema) + "\\) "+ consumable.name;
		REPORT_LINE_STRING[6] = "//Num//";
		REPORT_LINE_STRING[7] = "//Num//";
		REPORT_LINE_STRING[8] ="$" + totalFee;
	}

}
