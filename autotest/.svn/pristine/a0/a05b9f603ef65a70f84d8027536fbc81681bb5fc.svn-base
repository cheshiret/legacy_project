package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.parameter;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the error message during add parameter for hunt
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HFP7')
 * @SPEC:[TC:050421] Hunt Parameters - user's permissions 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 26, 2014
 */
public class VerifyPermission extends LicenseManagerTestCase{
	private HuntParameterInfo parameters = new HuntParameterInfo();
	private LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
	private AdminManager adm = AdminManager.getInstance();
	private LoginInfo loginAdm = new LoginInfo();
	private RoleInfo role=new RoleInfo();
	private String[] features = new String[]{"ViewHuntParameters","CreateModifyHuntParameters"};
	@Override
	public void execute() {
		this.updateFeatureAssignment(true, true);
		//View and create/edit feature are assigned
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		lm.deactiveAllActiveHuntParameters();
		
		parameters.setHuntParamID(lm.addHuntParameters(parameters).get(0));
		verifyCreateAndEditFunction(true);
		lm.logOutLicenseManager();
		
		this.updateFeatureAssignment(false, false);
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoHuntsListPg();
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		this.verifyHuntParameterLableExist(false);
		lm.logOutLicenseManager();
		
		this.updateFeatureAssignment(true, false);
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		this.verifyHuntParameterLableExist(true);
		verifyCreateAndEditFunction(false);
		lm.logOutLicenseManager();
	}
	
	private void verifyCreateAndEditFunction(boolean available) {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Add hunt parameter button is available:", available, paramListPg.isAddParameterButtonExist());
		passed &= MiscFunctions.compareResult("Hunt parameter id is available:", available, paramListPg.isHuntParameterIdLinkExist(parameters.getHuntParamID()));
		if(!passed){
			throw new ErrorOnPageException("Add Hunt parameter button and hunt parameter id should " + (available?"":"not ") + "available!");
		}
		logger.info("Add Hunt parameter button and hunt parameter id is correct as " + (available?"":"not ") + "available!");
	}
	
	private void verifyHuntParameterLableExist(boolean exist) {
		LicMgrHuntComponentsSubPage huntDetailPg = LicMgrHuntComponentsSubPage.getInstance();
		if(huntDetailPg.isParametersTabExist() != exist){
			throw new ErrorOnPageException("Hunt parameter label should " + (exist?"":"not ") + "exist!");
		}
		logger.info("Hunt parameter label is correct as " + (exist?"":"not ") + "exist!");
	}

	private void updateFeatureAssignment(boolean assignedView, boolean assignedCreate) {
		//Goto Admin Manager to assigned all the feature
		adm.loginAdminMgr(loginAdm);
		role.feature = features[0];
		adm.assignOrUnAssignRoleFeature(role, assignedView);
		role.feature = features[1];
		adm.assignOrUnAssignRoleFeature(role, assignedCreate);
		adm.logoutAdminMgr();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		TimeZone timezone = DataBaseFunctions.getContractTimeZone(schema);
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/" + facility;
		
		//Admin manager login info
		loginAdm.url = AwoUtil.getOrmsURL(env);;
		loginAdm.userName = TestProperty.getProperty("orms.adm.user");
		loginAdm.password = TestProperty.getProperty("orms.adm.pw");
		loginAdm.contract = "MS Contract";
		loginAdm.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		role.roleName = "HF HQ Role - Auto";
		role.application = "LicenseManager";
		
		String description = "Verify permission";
		String value = "Verify permission for parameter";
		//Set up parameter information
		parameters.setHuntParamStatus(ACTIVE_STATUS);
		parameters.setHuntParamDes(description);
		parameters.setHuntParamValue(value);
		
		hunt.setHuntCode("HFP7");
	}

}
