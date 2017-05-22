/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.weapons;


import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrWeaponsConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 			1. Add one correct weapon, check result.
 * 			2. Add a couple of weapon on different conditions. Check error messages.
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
 * @SPEC:TC046912,046905,046738,04673,046914,046915
 * @Task#:AUTO-1236
 * 
 * @author pzhu
 * @Date  Sep 19, 2012
 */

public class AddWeapons extends LicenseManagerTestCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private WeaponInfo addWeapon = new WeaponInfo();
	private LicMgrWeaponsConfigurationPage listPg = LicMgrWeaponsConfigurationPage.getInstance();
	
	private String[] errorMsg;	
	private String[][] features ={
				{"CreateWeapon",	"Add Weapon"},
				}; 
		
	
	@Override
	public void execute() {
		//preparation
		this.deactivateOldWeaponRecord(this.addWeapon);
		lm.checkRolesFeatures("HF Administrator", this.features, LICENSE_MANAGER, schema);

		//log into License Manager.
		lm.loginLicenseManager(login);
		lm.gotoProdConfPgFromTopMenu("Weapons");
		String msg = lm.addNewWeapon(addWeapon);

		if(!StringUtil.isEmpty(msg))
		{
			throw new ErrorOnDataException("Add new weapon failed, error message is->"+msg);
		}else{
			logger.info("Add new weapon("+addWeapon.getCode()+") successfully!!!");
		}
		
		//Check point 1: check add result
		this.checkAddResult();
		
		//Step 2: Check adding weapon on different conditions...
		this.checkFailedMsg();
		
		
		lm.logOutLicenseManager();
	}
	
	private void checkAddResult() {
		List<WeaponInfo> records = listPg.getAllRecordsOnPage();
		logger.info("Checking add result....");
		boolean found = false;
		for(WeaponInfo rs: records)
		{
			if(rs.getCode().equalsIgnoreCase(this.addWeapon.getCode())
				&&rs.getDescription().equalsIgnoreCase(this.addWeapon.getDescription())
				&&rs.getCreateUser().replaceAll("\\s+", StringUtil.EMPTY).equalsIgnoreCase(this.addWeapon.getCreateUser().replaceAll("\\s+", StringUtil.EMPTY))
				&&rs.getCreateLoc().equalsIgnoreCase(this.addWeapon.getCreateLoc()))
			{
				found = true;
				break;
			}
				
		}
		
		if(!found)
		{
			throw new ErrorOnPageException("Can not found new added Weapons-->"+StringUtil.ObjToString(this.addWeapon));
		}
		
		logger.info("Check result of new created Weapon Passed!!!");
		
	}

	private void checkFailedMsg() {
		String rand = String.valueOf(((new Random().nextInt(99999))));
		String msg;
		int msgIdx = 0;
		WeaponInfo info = new WeaponInfo();
		
		//Add duplicated code.
		msgIdx = 0;
		info.setCode(this.addWeapon.getCode());
		info.setDescription(this.addWeapon.getDescription()+rand);
		msg = lm.addNewWeapon(info);
		if((msg!=null)&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of duplicated code Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of duplicated code wrong, ",this.errorMsg[msgIdx],msg );	
		}
		
		//Add empty code.
		msgIdx = 1;
		info.setCode(null);
		info.setDescription(this.addWeapon.getDescription()+rand);
		msg = lm.addNewWeapon(info);
		if((msg!=null)&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of empty code Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of empty code wrong, ",this.errorMsg[msgIdx],msg );	
		}
		
		//Add duplicate description.
		msgIdx = 2;
		info.setCode(this.addWeapon.getCode().substring(1)+"M");
		info.setDescription(this.addWeapon.getDescription());
		msg = lm.addNewWeapon(info);
		if((msg!=null)&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of duplicate description Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of duplicate description wrong, ",this.errorMsg[msgIdx],msg );	
		}
		
		//Add empty description.
		msgIdx = 3;
		info.setCode(this.addWeapon.getCode().substring(1)+"Z");
		info.setDescription(null);
		msg = lm.addNewWeapon(info);
		if((msg!=null)&&(msg.trim().equalsIgnoreCase(this.errorMsg[msgIdx])))
		{
			logger.info("Check error msg of empty description Passed!!!");
		}else{
			throw new ErrorOnPageException("Error message of empty description wrong, ",this.errorMsg[msgIdx],msg );	
		}
	
	}

	/**
	 * @param info
	 */
	private void deactivateOldWeaponRecord(WeaponInfo info) {
		
		db.resetSchema(schema);
		String update = "DELETE FROM D_WEAPON WHERE CODE='" +
				info.getCode() +
				"'";
		logger.info("delete SQL:"+update);
		
		int result = db.executeUpdate(update);
		logger.info("Total "+result+" weapon record in table D_WEAPON deleted...");	
		
	}

	private String getRandomWeaponID(){
		String val = "";
	    Random random = new Random();
	    for(int i = 0; i < 3; i++) { // ID length is 3.
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
	        if("char".equalsIgnoreCase(charOrNum)) {
	            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;// 65 is lower, 97 is upper
	            val += (char) (choice + random.nextInt(26));
	        }
	        else if("num".equalsIgnoreCase(charOrNum)) {
	            val += String.valueOf(random.nextInt(10));
	        }
	    }
	    return val;
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		addWeapon.setCode(this.getRandomWeaponID());
		addWeapon.setDescription("AddWeapons"+addWeapon.getCode());
		addWeapon.setCreateUser(DataBaseFunctions.getLoginUserName(login.userName));
		addWeapon.setCreateLoc(lm.getFacilityName("1", schema));
		
		errorMsg = new String[]{
				"A Weapon with the same Code already exists. Please verify.",
				"Code is required. Please specify the Code.",
				"A Weapon with the same Description already exists. Please verify.",
				"Description is required. Please specify the Description."};
	}
}