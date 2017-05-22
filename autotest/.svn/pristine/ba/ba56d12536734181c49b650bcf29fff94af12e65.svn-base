package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddWeaponWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrWeaponsConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: add a new weapon for hunt.
 * @Preconditions: 
 * 1.  Configure Hunt Components(http://wiki.reserveamerica.com/display/dev/Big+Game+Supplementary+Setup)
 * 		Insert into P_HUNT_CFG  (ID,STATUS_ID,CHART_OF_ACCT_ID,DELETED_IND,CREATE_DATETIME,CREATE_USER_ID,CREATE_LOC_ID,UPDATE_DATETIME,UPDATE_USER_ID,UPDATE_LOC_ID) VALUES (1,1,1,'0',TO_DATE('05-MAY-12','DD-MON-RR'),3,1,NULL,NULL,NULL);
 * 		INSERT INTO P_HUNT_COMP_DSP_ORDR (ID,COMPONENT_ID,DISP_ORDER,CFG_ID,STATUS_ID,DELETED_IND) VALUES (2,2,2,1,1,'0');
 * 		--COMPONENT_ID: 1: Species sub type, 2: Weapon, 3: Hunt location, 4: Date period. Refer to CBL class HuntComponent.java.
 * 		--DISP_ORDER decide which component is shown first. In the above setting, weapon will be shown before species sub type.
 * 
 * 2. Roles/features to role 'HF Administrator' as followed(QA setup: D_ASSIGN_FEATURE ID=2780). 
 * 		LicenseManager	CreateWeapon	Add Weapon
 * @author Phoebe
 * @Date  Nov 12, 2012
 */
public class AddWeaponFunction extends FunctionCase {
	LoginInfo login;
	private boolean loggedin = false;
	private LicenseManager lm = LicenseManager.getInstance();
	private String contract = "";
	private String location = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private WeaponInfo weapon = new WeaponInfo();
	private LicMgrWeaponsConfigurationPage listPg = LicMgrWeaponsConfigurationPage.getInstance();

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[1];
		weapon = (WeaponInfo)param[2];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoProdConfPgFromTopMenu("Weapons");
		lm.addNewWeapon(weapon);
		this.verifyResult();			
		newAddValue = weapon.getCode();
	}

	private void verifyResult() {
		boolean passed = false;
		LicMgrAddWeaponWidget addPg = LicMgrAddWeaponWidget.getInstance();
		if(addPg.exists()){
			logger.error("[FAILED]New added Weapon(code:" + weapon.getCode() + ") failed, error msg:" + addPg.getErrorMsg());
		}else{
			List<WeaponInfo> records = listPg.getAllRecordsOnPage();
			logger.info("Checking add result....");
			for(WeaponInfo rs: records)
			{
				if(rs.getCode().equalsIgnoreCase(weapon.getCode())
					&&rs.getDescription().equalsIgnoreCase(weapon.getDescription()))
				{
					passed = true;
					logger.info("[PASSED]New weapon(code:" + weapon.getCode() + ") added successfully");
					break;
				}
			}
		}
		if(!passed)
		{
			throw new ErrorOnPageException("Can not found new added Weapons-->"+StringUtil.ObjToString(weapon));
		}
	}

}
