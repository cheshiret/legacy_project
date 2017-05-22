package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.licenseyear;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLicYearListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the feature assigned/unassigned for view/create,edit license year for hunt 
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HLY6')
 * 				d_hf_add_lottery_prd:id=210(CODE='HP')
 * 				d_hf_assi_hunts_to_lottery:id=130
 * @SPEC: [TC:050222] Hunt License Year - user's permissions 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 25, 2014
 */
public class VerfiyPermission extends LicenseManagerTestCase{
	private AdminManager adm = AdminManager.getInstance();
	private LoginInfo loginAdm = new LoginInfo();
	private RoleInfo role=new RoleInfo();
	private String[] features = new String[]{"ViewHuntLicenseYear","CreateModifyHuntLicenseYear"};
	private LicenseYear ly = new LicenseYear();
	private LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
	@Override
	public void execute() {
		this.updateFeatureAssignment(true, true);
		//View and create/edit feature are assigned
		lm.loginLicenseManager(login);
		this.gotoHuntDetailPageFromLicenseMangerHomePage();
		lm.gotoHuntLicYearListPgFromHuntDetailPg();
		ly.id = this.addANewLicenseYear();
		verifyCreateAndEditFunction(true);
		lm.logOutLicenseManager();
		
	    this.updateFeatureAssignment(false, false);
		//The hunt license year lable will not shown
		lm.loginLicenseManager(login);
		this.gotoHuntDetailPageFromLicenseMangerHomePage();
	    verifyHuntLicenseYearLableExist(false);
		lm.logOutLicenseManager();
	
		this.updateFeatureAssignment(true, false);
		//The hunt license year label will show but can not create or edit license year
		lm.loginLicenseManager(login);
		this.gotoHuntDetailPageFromLicenseMangerHomePage();
		verifyHuntLicenseYearLableExist(true);
		lm.gotoHuntLicYearListPgFromHuntDetailPg();
		verifyCreateAndEditFunction(false);
		lm.logOutLicenseManager();
	}

	private void verifyCreateAndEditFunction(boolean available) {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Add hunt license year button is available:", available, huntLicYearListPg.isAddLicenseYearButtonExist());
		passed &= MiscFunctions.compareResult("Hunt license year id is available:", available, huntLicYearListPg.isHuntLicenseYearIdLinkExist(ly.id));
		if(!passed){
			throw new ErrorOnPageException("Add Hunt license year button and hunt licnese year id should " + (available?"":"not ") + "available!");
		}
		logger.info("Add Hunt license year button and hunt licnese year id is correct as " + (available?"":"not ") + "available!");
	}

	private String addANewLicenseYear() {
		this.inactiveAllLicenseYear();
		lm.addHuntLicenseYear(ly);
		huntLicYearListPg.searchLicenseYear(ly.status, StringUtil.EMPTY, ly.assignedProd);
		return  huntLicYearListPg.getHuntLicYearID(ly.assignedProd, ly.licYear);
	}
	
	private void inactiveAllLicenseYear(){
		huntLicYearListPg.searchLicenseYear(ACTIVE_STATUS, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> ids = huntLicYearListPg.getAllLicenseYearIds();
		lm.desctiveHuntLicenseYears(ids);
	}

	private void verifyHuntLicenseYearLableExist(boolean exist) {
		LicMgrHuntComponentsSubPage huntDetailPg = LicMgrHuntComponentsSubPage.getInstance();
		if(huntDetailPg.islicenseYearTabExist() != exist){
			throw new ErrorOnPageException("Hunt license year label should " + (exist?"":"not ") + "exist!");
		}
		logger.info("Hunt license year label is correct as " + (exist?"":"not ") + "exist!");
	}

	private void gotoHuntDetailPageFromLicenseMangerHomePage(){
		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
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
		
		//Set up hunt code
		hunt.setHuntCode("HLY2");
		
		//Set up license year info
		ly.prdCategory = "Lottery";
		ly.assignedProd = "HP - Hunt product";
		ly.locationClass =  "01 - MDWFP Headquarters";
		ly.status = ACTIVE_STATUS;
		
		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		
		ly.sellFromDate = DateFunctions.getToday(timezone);
		ly.sellFromTime = "11:11"; 
		ly.sellFromAmPm = "AM";
	
		ly.sellToDate = DateFunctions.getDateAfterToday(300, timezone);
		ly.sellToTime = "11:59";
		ly.sellToAmPm = "PM";
		
		
		//Admin manager login info
		loginAdm.url = AwoUtil.getOrmsURL(env);;
		loginAdm.userName = TestProperty.getProperty("orms.adm.user");
		loginAdm.password = TestProperty.getProperty("orms.adm.pw");
		loginAdm.contract = "MS Contract";
		loginAdm.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		role.roleName = "HF HQ Role - Auto";
		role.application = "LicenseManager";
	}
}
