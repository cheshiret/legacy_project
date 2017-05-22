/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import java.io.IOException;
import java.util.Properties;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.ExcelParser;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
@Description: This test case used to cover PCR3198,if setup rate type as 'Percentage' from RA fee schedule,
 *               column 'Scheduled %' will displayed in report
 * @Preconditions:Prepare an invoice at first,and this case depends on create partner invoice setup script
 * @SPEC:TC:036464[PCR 3198]
 * @Task#:AUTO-1114
 * 
 * @author ssong
 * @Date  Jun 25, 2012
 */
public class InvoiceRemittanceReport_ReportColumn extends ResourceManagerTestCase{
	
	private static final String COL_NAME = "Schd %";//update col_name to 'Schd %' after confirmed with Sunny and Iris
	
	@Override
	public void execute() {
		this.checkAndPrepareInvoice();
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		this.verifyEmailReport(rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.logoutResourceManager();
	}
	
	/**
	 * The invoice number used here is prepared by test case RunAndViewPartnerInvoice under finance manager
	 */
	private void checkAndPrepareInvoice() {
		String invoice_id = rm.readQADB("Partner Invoice Num");
		String id = invoice_id.split(";")[0];
		String date = DateFunctions.formatDate(invoice_id.split(";")[1],"MM-dd-yyyy");
		if(rm.checkInvoiceExist(schema, id)){
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(id);
			sb.append(" - ").append(date).append(" - ").append(date).append(" - ");
			sb.append(rd.locationIDNoValidate);
			rd.invoice = sb.toString();
		}else{
			throw new ItemNotFoundException("Invoice "+id+" not exist, please run setup script 'CreatePartnerInvoice' at first.");
		}
	}

	private void verifyEmailReport(Properties[] pros){
		boolean verified = false;
		int count = Integer.parseInt(pros[0].getProperty("attach_count"));
		for(int i=0;i<count;i++){
			String fileName = pros[0].getProperty("attach_" + (i + 1));
			if(fileName.contains("Remittance")){
				this.checkReportColumn(fileName);
				verified = true;
			}
		}
		if(!verified){
			throw new ItemNotFoundException("No Attachment match 'Remittance' report.");
		}
	}
	
	private void checkReportColumn(String fileName){
		try {
			ExcelParser parser = new ExcelParser(fileName);
			String colName = parser.getCellValue(0, 14, 6);//this cell is the schedule % column
			if(!colName.equals(COL_NAME)){
				throw new ErrorOnPageException("Column Name "+colName+" Not Correct");
			}
			for(int i=15;i<parser.getTotalRowNum(0);i++){
				String schedulePrice = parser.getCellValue(0, i, 5);
				String schedulePercentage = parser.getCellValue(0, i, 6);
				if(!StringUtil.isEmpty(schedulePercentage)&&!StringUtil.isEmpty(schedulePrice)){
					throw new ErrorOnPageException("Scheduel Price and Schedule Percentage Can not have value at the same time.");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "RI Contract";
		login.location = "Administrator/State of Rhode Island Contract";
		schema = DataBaseFunctions.getSchemaName("RI", env);
		
		rd.group = "Financial Reports";
		rd.reportName = "Invoice Remittance Report";
		rd.locationIDNoValidate = "State of Rhode Island Contract";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
	}
}
