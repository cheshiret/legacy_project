/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddCampingUnitComboFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:support script for Auto-2161
 * @Task#:Auto-2161
 * 
 * @author ssong
 * @Date  May 5, 2014
 */
public class AddCampingUnitCombo extends SetupCase{

	private LoginInfo loginInfo=new LoginInfo();
	private CampingUnit unit;
	private String facilityName;
	private AddCampingUnitComboFunction func = new AddCampingUnitComboFunction();
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.awo.supportscripts.SetupCase#executeSetup()
	 */
	@Override
	public void executeSetup() {
		func.execute(loginInfo,unit,facilityName);
		
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.awo.supportscripts.SetupCase#readDataFromDatabase()
	 */
	@Override
	public void readDataFromDatabase() {
		loginInfo.contract = datasFromDB.get("contract");
		loginInfo.location = datasFromDB.get("location");
		facilityName = datasFromDB.get("facility");
		unit = new CampingUnit();
		unit.name = datasFromDB.get("COMBO_NAME");
		unit.description = datasFromDB.get("COMBO_DESC");
		unit.isEquipReq = Boolean.parseBoolean(datasFromDB.get("EQUIPEMENT_REQ"));
		unit.maxOfAllCombined = datasFromDB.get("MAX_OF_ALL_COMB");
		unit.maxOfSpecialCombined = datasFromDB.get("MAX_OF_SPEC_COMB");
		String tent_Ind = datasFromDB.get("TENT_IND");
		if(StringUtil.notEmpty(tent_Ind)){
			unit.tentIndex = Integer.parseInt(tent_Ind);
		}
		String fifth_wh_ind = datasFromDB.get("FIFTH_WHEEL_IND");
		if(StringUtil.notEmpty(fifth_wh_ind)){
			unit.fifthWheelIndex = Integer.parseInt(fifth_wh_ind);
		}
		String rv_ind = datasFromDB.get("RV_IND");
		if(StringUtil.notEmpty(rv_ind)){
			unit.fifthWheelIndex = Integer.parseInt(rv_ind);
		}
		unit.isAssignedSitesAndLoops = Boolean.parseBoolean(datasFromDB.get("APPLY_TO_SITE"));
		if(unit.isAssignedSitesAndLoops){
			unit.campUnitSitesNames = datasFromDB.get("SITE_NAMES").split(",");
			unit.campUnitLoopsNames = datasFromDB.get("LOOP_NAMES").split(",");
		}
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_campunit_combo"; 
		    
		env = TestProperty.getProperty("target_env");
		loginInfo.url = AwoUtil.getOrmsURL(env);
		loginInfo.userName = TestProperty.getProperty("orms.im.user");
		loginInfo.password = TestProperty.getProperty("orms.im.pw");
		
	}

}
