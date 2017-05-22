/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This report not fit for template compare strategy as the store status are always changed, no fixed template
 * so will parse and verify report content directly
 * 
 * @Preconditions:
 * @SPEC:TC019679,019680,019681
 * @Task#:Auto-1394
 * 
 * @author ssong
 * @Date  Mar 18, 2013
 */
public class StoreStatusReport extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		String closedAgent = rm.getAgentNameByStatus(schema, AGENT_CLOSED_STATUS).get(0);
		String activeAgent = rm.getAgentNameByStatus(schema, AGENT_ACTIVE_STATUS).get(0);
		String inactiveAgent = rm.getAgentNameByStatus(schema, AGENT_INACTIVE_STATUS).get(0);
		String revokedAgent = rm.getAgentNameByStatus(schema, AGENT_REVOKED_STATUS).get(0);
		
		rm.selectOneRpt(rd.group,rd.reportName);
		String fileName = rm.runSpecialRpt(rd, comparedPath);
		this.checkAgentInReport(fileName,closedAgent,activeAgent,inactiveAgent,revokedAgent);
		
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.deliveryMethod = "Email";
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		String saveAsName = "StoreStatusReport_All_Email";
		rm.getReportFromMailBox(host, username, password, comparedPath,saveAsName, rd.emailSubject);
		fileName = comparedPath+"\\"+saveAsName+"."+rd.reportFormat;
		this.checkAgentInReport(fileName, closedAgent,activeAgent,inactiveAgent,revokedAgent);
		
		rm.logoutResourceManager();
	}

	private void checkAgentInReport(String fileName,String...agents){
		String[] lines = FileUtil.readLines(fileName);
		for(String agent:agents){
			boolean found = false;
			for(String line:lines){
				if(line.contains(agent.toUpperCase())){
					logger.info("Found Agent "+agent);
					found = true;
					break;
				}
			}
			if(!found){
				throw new ErrorOnPageException("Not found Agent "+agent);
			}
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
		
		rd.fileName = "StoreStatusReport_All";
		rd.reportFormat = "CSV";
		rd.deliveryMethod = "Online";
		
	}

}
