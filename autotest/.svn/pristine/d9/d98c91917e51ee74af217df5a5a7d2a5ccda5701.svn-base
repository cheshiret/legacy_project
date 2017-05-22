package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddWeaponFunction;
/**
 * @Preconditions:  
 * 1.  Configure Hunt Components(http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup)
 * 		Insert into P_HUNT_CFG  (ID,STATUS_ID,CHART_OF_ACCT_ID,DELETED_IND,CREATE_DATETIME,CREATE_USER_ID,CREATE_LOC_ID,UPDATE_DATETIME,UPDATE_USER_ID,UPDATE_LOC_ID) VALUES (1,1,1,'0',TO_DATE('05-MAY-12','DD-MON-RR'),3,1,NULL,NULL,NULL);
 * 		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CFG_ID,STATUS_ID,DELETED_IND) VALUES (2,2,2,1,1,'0');
 * 		--COMPONENT_ID: 1: Species sub type, 2: Weapon, 3: Hunt location, 4: Date period. Refer to CBL class HuntComponent.java.
 * 		--DISP_ORDER decide which component is shown first. In the above setting, weapon will be shown before species sub type.
 * 
 * 2. Roles/features to role 'HF Administrator' as followed(QA setup: D_ASSIGN_FEATURE ID=2780). 
 * 		LicenseManager	CreateWeapon	Add Weapon
 * @author pchen
 * @Date  Nov 28, 2012
 */
public class AddWeapon extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private WeaponInfo weapon = new WeaponInfo();
	private AddWeaponFunction addWeaponFunc = new AddWeaponFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login.contract;
		args[1] = login;
		args[2] = weapon;
		addWeaponFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		weapon.setCode(datasFromDB.get("code"));
		weapon.setDescription(datasFromDB.get("description"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_weapon";
	}	
}
