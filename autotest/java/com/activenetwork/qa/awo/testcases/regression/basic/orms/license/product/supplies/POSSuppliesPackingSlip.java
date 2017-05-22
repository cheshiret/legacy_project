package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies;

import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CarrierInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to check the support of uppercase of POS Supplies Packing Slip.
 * @Task#:Auto-1159
 * @author Phoebe Chen
 * @Date  Aug 14, 2012
 */
public class POSSuppliesPackingSlip  extends
LicenseManagerTestCase {
    private CarrierInfo carrier = new CarrierInfo(); 
    private String reportFile = "";
	private ReportData rd = new ReportData();
	private static final boolean UPPER_CASE = true;
	private String agent="", fulfillFrom = "", fulfillTo = "";
	@Override
	public void execute() {
		// Set upperCase		
		lm.updateUpperCaseConfigInDB(schema,UPPER_CASE);
		//Login and goto supply order search page
		lm.loginLicenseManager(login);
		lm.gotoSuppliesOrderPgFromTop();
		
		lm.printPakingSlip(agent, fulfillFrom, fulfillTo, carrier.trackingNumber, rd.fileName);

		this.verifyPdfReportUpperCase(rd.fileName);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//Set up schema for setting upperCase
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		logger.info("Use schema: "+ schema);
		
		//Carrier infomation
		carrier.shippingDate = DateFunctions.getDateAfterToday(1);
		carrier.shippingNotes = "This is for test of uppercase of POSSuppliesPackingSlip report!";
		
		fulfillFrom = "7/30/2012";
		carrier.trackingNumber = "shipped";
		agent = "WAL MART 708 - 437";
		
		rd.reportName = "BoatInspectionForm";	
		
		reportFile = logger.getLogPath() + "ComparedFile";
		reportFile = reportFile.replace("/", "\\");
		
		this.setReportFileName(reportFile);
	}
	
	private void setReportFileName(String reportPath){
		File file = new File(reportPath);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new ItemNotFoundException(
				"Failed To Make Directory.");
			}
		}
		String fileName = file.getAbsolutePath()+ "\\";
		fileName +=  rd.reportName.replaceAll(" ", "");	
		fileName += ".pdf";
		rd.fileName = fileName;
	}
       
	public void verifyPdfReportUpperCase(String reportPath) {
		logger.info("Verify PDF format Report.");
		File file = new File(reportPath);
		List<String> lines = PDFParser.getPDFContentInOrder(file.getAbsolutePath());
		String line;
		String[] itemNames = new String[]{"Qty Previously","Qty Shipped in","Item Shipped","Qty Ordered",
				"Qty Approved","Shipped","Package","Totals:","Shipping Notes:"};
		String errorlog = "";
		for(int i=0; i<lines.size();i++)
		{
			boolean isItemName = false;
			line = lines.get(i).trim();
		    for(String str:itemNames){
				if(str.equals(line)){
					isItemName = true;
					break;
				}
			}		    
			if(!isItemName && !line.toUpperCase().equals(line))
			{
				errorlog += line + " is not UpperCase;" + "\n";
			}				
		}	
		if(errorlog.equals("")){
			logger.info("UpperCase checked passed!");
		}else{
			logger.info(errorlog);
			throw new ErrorOnPageException("Some places are not upperCase in the report, please check the logger above!");
		}
		
	}
}
