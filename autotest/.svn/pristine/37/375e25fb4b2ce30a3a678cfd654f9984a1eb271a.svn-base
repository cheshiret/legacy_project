/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Use compare new generated report with template strategy to verify configure display location id as revenue location id in the report
 * @Preconditions:need to assign this report feature from admin
 * @SPEC:TC035864,TC:028930
 * @Task#:AUTO-1198
 * 
 * @author ssong
 * @Date  Aug 21, 2012
 */
public class TransactionDetailRevenueReport extends ResourceManagerTestCase{

	private AwoDatabase dbCon = (AwoDatabase) AwoDatabase.getInstance();
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		//configure from DB to display location id as revenue location id
		this.confirgureDisplayLoc(true);
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName); 
		
		//turn off configuration, use import id as revenue location id
		this.confirgureDisplayLoc(false);
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);   
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();		
	}

	private void confirgureDisplayLoc(boolean isDisplay){
		logger.info("Confirgure to Display Location ID "+isDisplay);
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "ID";
		dbCon.resetSchema(schema);
		String sql = "select count(*) qty from x_prop where name = 'DisplayLocationIDForTransactionDetailRevenueReport'";
		String qty = dbCon.executeQuery(sql, "qty", 0);
		if(Integer.parseInt(qty)==0){
			sql = "insert into x_prop values( get_sequence('x_prop'), 'DisplayLocationIDForTransactionDetailRevenueReport', 'Contract', 'Boolean', 'true')";
			dbCon.executeUpdate(sql);
		}
		if(isDisplay){
			sql = "update x_prop set value='true' where name ='DisplayLocationIDForTransactionDetailRevenueReport'";
			rd.fileName = rd.reportName.replaceAll(" ", "")+"_DisplayLocID";
		}else{
			sql = "update x_prop set value='false' where name ='DisplayLocationIDForTransactionDetailRevenueReport'";
			rd.fileName = rd.reportName.replaceAll(" ", "")+"_NotDisplayLocID";
		}
		dbCon.executeUpdate(sql);
	}
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";

		rd.group = "Financial Reports";
		rd.reportName = "Transaction Detail Revenue Report";	
		rd.transactionDetailReportBy = "Transaction Location";
		rd.includeRAFee = "Yes";
		rd.displayColumns = new String[]{"Account ID","Account Name","Batch ID","Batch/Deposit Date","Deposit ID","Payment Type","Product ID","Product Name","Revenue Location ID","Revenue Location Name","Revenue Notes","Transaction Date","Transaction Location ID","Transaction Location Name"};
		rd.breakAccountCode = "No";
		rd.subTotalBy = new String[]{"Batch ID","Deposit ID","Payment Type","Revenue Location ID","Transaction Location ID"};
		
		rd.startDate = "Sat Jan 01 2011";
		rd.endDate = "Sat Jan 01 2011";
		rd.paymentGroup = "*All";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "ONLINE";
	}
}
