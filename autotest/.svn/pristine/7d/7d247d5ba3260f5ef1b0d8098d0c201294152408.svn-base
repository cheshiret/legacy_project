package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Agents;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreStatusReasonMgtPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: Add the store status reason.
 * @Preconditions:  The specific vendor is exist.
 * @SPEC:  Add store status reason
 * @Task#: Auto-753
 * @author jwang8
 * @Date  Jan 10, 2012
 */
public class AddStoreStatusReason extends LicenseManagerTestCase{
	Agents agents = new Agents();
	LicMgrStoreStatusReasonMgtPage  storeStuReasonPg = LicMgrStoreStatusReasonMgtPage.getInstance();
	@Override
	public void execute() {

		lm.loginLicenseManager(login);
		//go to system configuration sub Agents Status/Reason Mgt page.
		lm.gotoAgentStatusReasonMgtPage();
		//Add a new store status reason.
		lm.addStoreStatusReason(agents.status, agents.reason);
		//Verify the added store status reason info.
		this.verifyStoreStatusReasonListInfo(agents);

		lm.logOutLicenseManager();
	}
	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		agents.status = "Inactive";
		agents.reason = "Auto-test" + DataBaseFunctions.getEmailSequence();
		agents.systemStatusReason = "No";
		agents.creatUser = DataBaseFunctions.getLoginUserName(login.userName);
		agents.creatLocation = login.location.split("/")[1];
	}

	/**
	 * Verify the store reason list item info.
	 * @param expectedAgents -  the expected agents info.
	 */
   private void verifyStoreStatusReasonListInfo(Agents expectedAgents){
	  boolean pass =  storeStuReasonPg.compareStoreStatusReasonList(expectedAgents);
	  if(!pass){
		  throw new ErrorOnPageException("Add store reason info error");
	  }else{
		  logger.info("Add store status reason info correct");
	  }
   }

}
