/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.storestatusreport;

import java.io.IOException;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.ExcelParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:019680
 * @Task#:AUTO-1394
 * 
 * @author ssong
 * @Date  Mar 14, 2013
 */
public class VerifyReportHeaderAndColumn extends ResourceManagerTestCase{

	private final String RPT_NAME = "STORE STATUS REPORT(OPS-HFSTOR-002)";
	private final String DATE_REG = "Run Date and Time:"+" [a-zA-Z]{3} [0-9]{1,2} [0-9]{4} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2} [A|P]M [a-zA-Z]{3}";
	private final String LOC_STR = "LOCATION: MISSISSIPPI DEPARTMENT OF WILDLIFE, FISHERIES, AND PARKS";
	private final String STORE_STATUS_STR = "STORE STATUS: ACTIVE, CLOSED, INACTIVE, REVOKED";
	private final String[] COL_NAME = new String[]{"BUSINESS","AGENT ID","FIRST NAME","LAST NAME","ADDRESS","CITY","STATE","ZIP"};
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group,rd.reportName);
		String fileName = rm.runSpecialRpt(rd, comparedPath);
		checkReportHeader(fileName);
		
		rm.logoutResourceManager();
	}
	
	public void checkReportHeader(String fileName){
		try {
			ExcelParser parser = new ExcelParser(fileName);
			String rptName = parser.getCellValue(0, 0, 0);
			if(!rptName.equals(RPT_NAME)){
				throw new ErrorOnPageException("Report Name not correct",RPT_NAME,rptName);
			}
			String runDate = parser.getCellValue(0, 1, 4);
			if(!runDate.matches(DATE_REG)){
				throw new ErrorOnPageException("Run Date time format not correct",DATE_REG,runDate);
			}
			String location = parser.getCellValue(0, 2, 0);
			if(!location.equals(LOC_STR)){
				throw new ErrorOnPageException("Location not correct in Report",LOC_STR,location);
			}
			String storeStatus = parser.getCellValue(0, 3, 0);
			if(!storeStatus.equals(STORE_STATUS_STR)){
				throw new ErrorOnPageException("Store Status Not correct",STORE_STATUS_STR,storeStatus);
			}
			List<String> columns = parser.getRowInfo(0, 5);
			for(int i=0;i<COL_NAME.length;i++){
				if(!COL_NAME[i].equals(columns.get(i))){
					throw new ErrorOnPageException("Column Name not correct",COL_NAME[i],columns.get(i));
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
		login.contract = "MS Contract"; 
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		// report information
		rd.group = "All";
		rd.reportName = "Store Status Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.storeStatus = new String[]{"Active","Inactive","Revoked","Closed"};
		
		rd.fileName = "StoreStatusReport_ReportHeader";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}

}
