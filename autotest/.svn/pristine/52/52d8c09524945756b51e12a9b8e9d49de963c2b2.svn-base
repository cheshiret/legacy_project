package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.loyaltyprogram;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.EarnParameter;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrLoyaltyProgramListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the search function of loyalty program
 * @Preconditions:
 * @SPEC: search loyalty program [TC:101333]
 * @Task#: AUTO-2114
 * 
 * @author qchen
 * @Date  Mar 18, 2014
 */
public class Search extends InventoryManagerTestCase {

	private LoyaltyProgram program = new LoyaltyProgram();
	private InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLoyaltyProgramSearchPage();
		program.setId(im.addNewLoyaltyProgram(program));
		
		//search by inactive status, verify the all searched loyalty programs' status is No
		listPage.searchLoyaltyProgram(OrmsConstants.INACTIVE_STATUS);
		listPage.verifyLoyaltyProgramStatus(OrmsConstants.NO_STATUS);
		
		//search by active status, verify the all searched loyalty programs' status is Yes
		im.activateLoyaltyProgram(program.getId());
		listPage.searchLoyaltyProgram(OrmsConstants.ACTIVE_STATUS);
		listPage.verifyLoyaltyProgramStatus(OrmsConstants.YES_STATUS);
		
		//clean up
		im.deactivateLoyaltyProgram(program.getId());
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552818";
		
		program.setStatus(OrmsConstants.INACTIVE_STATUS);
		program.setLocation(im.getFacilityName(facilityID, schema));
		program.setLocationCategory("Park");
		
		program.setName(this.caseName + DateFunctions.getCurrentTime());
		program.setDescription(this.fullCaseName);
		List<EarnParameter> parameters = new ArrayList<EarnParameter>();
		EarnParameter param1 = program.new EarnParameter();
		param1.setProducateCategory("POS");
		param1.setEarnGroup("Basic Earnings");
		param1.setPointType("AutoTestWithRedee");
		param1.setMethod("Per $ Value");
		parameters.add(param1);
		
		program.setEarnParameters(parameters);
		program.setAssociatedProduct("CustLoyaltyPOSAuto");
		program.setEffectiveStartDate(DateFunctions.getToday());
		program.setEffectiveEndDate(program.getEffectiveStartDate());
	}
}
