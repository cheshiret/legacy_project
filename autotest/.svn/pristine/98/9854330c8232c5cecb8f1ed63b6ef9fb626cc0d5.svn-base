/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bond;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorViewStoreBondAssignmentWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case used to verify assign bond to an agent,first add a bond and assign the bond to an agent
 * then verify assign successfully from View Agent/Bond Assignments
 * @Preconditions:We need to prepare vendor and agent at first before license go live; the agent must belong to 'Commercial Agent'
 * @SPEC:<View Vendor Bond -Store Assignment List>
 * @Task#:Auto-800
 * 
 * @author ssong
 * @Date  Dec 15, 2011
 */
public class ViewVendorBondAssignment extends LicenseManagerTestCase{

	private String vendorNum,agentName,agentId,assignStatus = "";
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private List<String> assignments = new ArrayList<String>();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoBondSubTabFromVendorDetailPg();
		
		bondInfo.id = lm.addVendorBond(bondInfo, true);
		lm.changeAgentBondAssignment(this.agentId, bondInfo.bondNum,bondInfo.issuer);

		//view agents/bonds assignments by click View button
		lm.gotoViewAgentBondsAssigmentsPg();
		this.verifyAgentBondAssignments();
		
		//view agents/bonds assignments by click number of agents assigned to give bond
		lm.gotoViewAgentBondsAssigmentsPg(bondInfo.id);
		this.verifyAgentBondAssignments();
		
		lm.logOutLicenseManager();
	}

	private void verifyAgentBondAssignments(){
		LicMgrVendorViewStoreBondAssignmentWidget viewPg = LicMgrVendorViewStoreBondAssignmentWidget.getInstance();
		
		viewPg.verifyAssignments(".*"+bondInfo.bondNum+".*",assignments);
		viewPg.searchAssignments(assignStatus, agentName, bondInfo.bondNum,bondInfo.issuer);
		viewPg.verifyAssignments(".*"+bondInfo.bondNum+".*",assignments);
		viewPg.leaveViewBondsPg();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		vendorNum = "Auto555";
		agentName = "TestForBond";
		agentId = lm.getAgentIDByNameFromDB(this.schema,this.agentName);
		bondInfo.issuer = "Farm Bureau";
		bondInfo.type = "Bond";
		bondInfo.bondNum = DateFunctions.getCurrentTime()+"";
		bondInfo.bondAmount = "19.87";
		bondInfo.effectiveFrom = DateFunctions.getToday();
		bondInfo.effectiveTo = DateFunctions.getDateAfterToday(1);
		bondInfo.status = OrmsConstants.ACTIVE_STATUS;
		
		assignStatus = "Active";
		
		assignments.add("\\d+");
		assignments.add(assignStatus);
		assignments.add(agentId);
		assignments.add(agentName+".*");
		assignments.add("Bond #: "+bondInfo.bondNum+" Issuer: "+bondInfo.issuer);
		assignments.add(DateFunctions.getToday("EEE MMM d yyyy")+".*" + DataBaseFunctions.getLoginUserName(login.userName));
	}

}
