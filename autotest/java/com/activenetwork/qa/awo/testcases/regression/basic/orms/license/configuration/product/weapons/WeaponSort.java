package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.weapons;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrWeaponsConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 			1. Add a couple of weapon(wCode)
 * 			2. Check sort of weapon code on the list page.
 * 			
 * @Preconditions:  
 * 1.  Configure Hunt Components(http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup)
 * 		Insert into P_HUNT_CFG  (ID,STATUS_ID,CHART_OF_ACCT_ID,DELETED_IND,CREATE_DATETIME,CREATE_USER_ID,CREATE_LOC_ID,UPDATE_DATETIME,UPDATE_USER_ID,UPDATE_LOC_ID) VALUES (1,1,1,'0',TO_DATE('05-MAY-12','DD-MON-RR'),3,1,NULL,NULL,NULL);
 * 		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CFG_ID,STATUS_ID,DELETED_IND) VALUES (2,2,2,1,1,'0');
 * 		--COMPONENT_ID: 1: Species sub type, 2: Weapon, 3: Hunt location, 4: Date period. Refer to CBL class HuntComponent.java.
 * 		--DISP_ORDER decide which component is shown first. In the above setting, weapon will be shown before species sub type.
 * 
 * 2. Roles/features to role 'HF Administrator' as followed(QA setup: D_ASSIGN_FEATURE ID=2780). 
 * 		LicenseManager	CreateWeapon	Add Weapon
 * 
 * @SPEC:TC046743
 * @Task#:AUTO-1236
 * 
 * @author pzhu
 * @Date  Sep 19, 2012
 */

public class WeaponSort extends LicenseManagerTestCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private WeaponInfo addWeapon = new WeaponInfo();
	private LicMgrWeaponsConfigurationPage listPg = LicMgrWeaponsConfigurationPage.getInstance();

	private String[][] features ={
				{"CreateWeapon",	"Add Weapon"},
				}; 
		
	private String[] wCode;
	@Override
	public void execute() {
		//preparation
		this.deactivateOldWeaponRecord(this.wCode);
		lm.checkRolesFeatures("HF Administrator", this.features, LICENSE_MANAGER, schema);

		//log into License Manager.
		lm.loginLicenseManager(login);
		lm.gotoProdConfPgFromTopMenu("Weapons");
				
		//Step 1: Add a couple of Weapons..
		this.addCoupleOfWeapons();
				
		//Check point 1: check order of weapon code in the list.
		this.checkWeaponSort();
		
		
		lm.logOutLicenseManager();
	}
	
	
	/**
	 * 
	 */
	private void addCoupleOfWeapons() {
		for(String s: this.wCode)
		{
			addWeapon.setCode(s);
			addWeapon.setDescription(this.randomDescription());
			String msg = lm.addNewWeapon(addWeapon);

			if(!StringUtil.isEmpty(msg))
			{
				throw new ErrorOnDataException("Add new weapon failed, error message is->"+msg);
			}else{
				logger.info("Add new weapon("+addWeapon.getCode()+") successfully!!!");
			}
		}		
		
	}


	/**
	 * 
	 */
	private void checkWeaponSort() {
		List<WeaponInfo> records = listPg.getAllRecordsOnPage();
		List<String> tmpCode = new ArrayList<String>();
		
		for(WeaponInfo info: records)
		{
			if(Arrays.binarySearch(this.wCode, info.getCode())>=0)
			{
				tmpCode.add(info.getCode());
			}
			
		}
		//get code on page, and covert to Array.
		String[] arrayCode = (String[])tmpCode.toArray(new String[tmpCode.size()]);
		
		Arrays.sort(this.wCode);// sort source array...
		
		if(!Arrays.toString(this.wCode).equals(Arrays.toString(arrayCode)))
		{
			throw new ErrorOnDataException("Sort of weapon code on the page is wrong ",Arrays.toString(this.wCode),Arrays.toString(arrayCode));
		}
		
		logger.info("Verify sort of  weapon code on the page Passed!!!");
		
	}


		
	/**
	 * @param info
	 */
	private void deactivateOldWeaponRecord(String[] code) {
		
		db.resetSchema(schema);
		String update = "DELETE FROM D_WEAPON WHERE UPPER(CODE) in(";
		for(int i=0; i<code.length; i++)
		{
			update +="'"+code[i].toUpperCase();
			if(i==code.length-1)
			{
				update +="')";
			}else{
				update +="',";
			}
		}
		
		logger.info("delete:"+update);
		
		int result = db.executeUpdate(update);
		logger.info("Total "+result+" weapon record in table D_WEAPON deleted...");	
		
	}

	private String randomDescription(){
		return ("WeaponSort"+String.valueOf(((new Random().nextInt(999999)))));
	}


	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		wCode = new String[]{"1BB","QBB","rAB","rBB"};//sorted in alphanumeric case-insensitive ascending order.
		
	}
	
	

}

