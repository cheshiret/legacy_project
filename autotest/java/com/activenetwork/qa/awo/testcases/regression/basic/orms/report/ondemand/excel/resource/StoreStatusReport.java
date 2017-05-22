/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import java.io.IOException;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.ExcelParser;
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
 * @Date  Mar 13, 2013
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
		
		//check all above 4 agents in the report when select 4 kinds of store status
		rm.selectOneRpt(rd.group,rd.reportName);
		String fileName = rm.runSpecialRpt(rd, comparedPath);
		if(!this.checkAgentInReport(fileName,closedAgent,activeAgent,inactiveAgent,revokedAgent)){
			throw new ErrorOnPageException("Check log, Not all agent are included in the report");
		}

		//check active store in the report,other 3 status store not in the report
		rd.storeStatus = new String[]{"Active"};
		
		rm.selectOneRpt(rd.group,rd.reportName);
		rd.fileName = "StoreStatusReport_Active";
		fileName = rm.runSpecialRpt(rd, comparedPath);
		if(!this.checkAgentInReport(fileName, activeAgent)){
			throw new ErrorOnPageException("Agent '"+activeAgent+"' should in the report.");
		}
		if(this.checkAgentInReport(fileName, inactiveAgent)){
			throw new ErrorOnPageException("Agent '"+inactiveAgent+"' should not in the report.");
		}
		if(this.checkAgentInReport(fileName, revokedAgent)){
			throw new ErrorOnPageException("Agent '"+revokedAgent+"' should not in the report.");
		}
		if(this.checkAgentInReport(fileName, closedAgent)){
			throw new ErrorOnPageException("Agent '"+closedAgent+"' should not in the report.");
		}
		
		rm.logoutResourceManager();
	}
	
	public boolean checkAgentInReport(String fileName,String... agentName){
		boolean found = true;
		try {
			ExcelParser parser = new ExcelParser(fileName);
			for(String agent:agentName){
				int rowNum = parser.findRow(0, 0, agent.toUpperCase());
				logger.info((rowNum>0?"Found":"Not Found")+" agent "+agent);
				found &= (rowNum>0);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return found;
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
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}

}
