package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.loyaltyprogram;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.EarnParameter;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrLoyaltyProgramDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrLoyaltyProgramListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the progress of editing Loyalty Program
 * @Preconditions:
 * @SPEC: Edit Loyalty Program [TC:101447]
 * @Task#: AUTO-2114
 * 
 * @author qchen
 * @Date  Mar 18, 2014
 */
public class Edit extends InventoryManagerTestCase {

	private LoyaltyProgram program = new LoyaltyProgram();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLoyaltyProgramSearchPage();
		//1. add new a loyalty program
		program.setId(im.addNewLoyaltyProgram(program));
		
		//2. update loyalty program
		im.gotoLoyaltyProgramDetailsPage(program.getStatus(), program.getId());
		im.editLoyaltyProgram(this.initializeEditInfo());
		
		//3. verify the loyalty program list info
		InvMgrLoyaltyProgramListPage.getInstance().verifyLoyaltyProgramListInfo(program);
		
		//4. verify the loyalty program details info
		im.gotoLoyaltyProgramDetailsPage(program.getStatus(), program.getId());
		InvMgrLoyaltyProgramDetailsPage.getInstance().verifyLoyaltyProgramDetailsInfo(program);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552818";
		
		program.setStatus(OrmsConstants.NO_STATUS);
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
		
		EarnParameter param2 = program.new EarnParameter();
		param2.setProducateCategory("Site");
		param2.setEarnGroup("Bonus Earnings");
		param2.setPointType("AutoTestWithRedee");
		param2.setMethod("Per Unit");
		parameters.add(param2);
		
		program.setEarnParameters(parameters);
		program.setAssociatedProduct("CustLoyaltyPOSAuto");
		program.setEffectiveStartDate(DateFunctions.getToday());
		program.setEffectiveEndDate(program.getEffectiveStartDate());
	}
	
	private LoyaltyProgram initializeEditInfo() {
		program.setName(program.getName() + "_Edit");
		program.setDescription(program.getDescription() + "_Edit");
		List<EarnParameter> parameters = new ArrayList<EarnParameter>();
		EarnParameter param3 = program.new EarnParameter();
		param3.setProducateCategory("POS");
		param3.setEarnGroup("Bonus Earnings");
		param3.setPointType("AutoTestWithRedee");
		param3.setMethod("Per Unit");
		parameters.add(param3);
		
		program.setEarnParameters(parameters);
		program.setAssociatedProduct("CustLoyaltyPOSAuto");
		program.setEffectiveStartDate(DateFunctions.getDateAfterToday(3));
		program.setEffectiveEndDate(program.getEffectiveStartDate());
		
		return program;
	}
}
