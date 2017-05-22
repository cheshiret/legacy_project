package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteriesCommonPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the permission for view, create/edit lottery schedule
 * @LinkSetUp:  d_hf_add_lottery_execution_config:id=310(DESCRIPTION='Test permission')
 * @SPEC:[TC:050147] Lottery Schedule - User's permissions 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  March 03, 2014
 */
public class VerifyPermission extends LicenseManagerTestCase{
	private AdminManager adm = AdminManager.getInstance();
	private LoginInfo loginAdm = new LoginInfo();
	private RoleInfo role=new RoleInfo();
	private String[] features = new String[]{"ViewPrivilegeLotterySchedule","CreateModifyPrivilegeLotterySchedule"};
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private LicMgrProcessingListPage processingListPage = LicMgrProcessingListPage.getInstance();
	@Override
	public void execute() {
		//Assigned "ViewPrivilegeLotterySchedule" and assigned "CreateModifyPrivilegeLotterySchedule"
		this.updateFeatureAssignment(true, true);
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotterySchedulePage();
		//Clean the old data
		this.clearOldSchedule();
		schedule.setId(lm.addLotteryScheduleAndClickOK(schedule));
		
		processingListPage.searchLotterySchedule(ACTIVE_STATUS, schedule.getLicenseYear());
		verifyCreateAndEditFunction(true);
		lm.logOutLicenseManager();
		
		//Unassigned "ViewPrivilegeLotterySchedule" and unassigned "CreateModifyPrivilegeLotterySchedule"
		this.updateFeatureAssignment(false, false);
		lm.loginLicenseManager(login);
		verifyLotteryScheduleTabExist(false);
		
		lm.logOutLicenseManager();
	}

	private void verifyLotteryScheduleTabExist(boolean exist) {
		LicMgrLotteriesCommonPage commonPage = LicMgrLotteriesCommonPage.getInstance();
		if(commonPage.isProcessiongTabExist() != exist){
			throw new ErrorOnPageException("Lottery schedule label--Processiong should " + (exist?"":"not ") + "exist!");
		}
		logger.info("Lottery schedule label--Processiong is correct as " + (exist?"":"not ") + "exist!");
	}

	private void verifyCreateAndEditFunction(boolean available) {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Add Lottery schdule button is available:", available, processingListPage.isAddScheduleButtonExist());
		passed &= MiscFunctions.compareResult("Lottery schedule id is available:", available, processingListPage.isLotteryScheduleIdLinkExist(schedule.getId()));
		if(!passed){
			throw new ErrorOnPageException("Add Hunt license year button and hunt licnese year id should " + (available?"":"not ") + "available!");
		}
		logger.info("Add Hunt license year button and hunt licnese year id is correct as " + (available?"":"not ") + "available!");
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
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/" + facility;
		
		//privilege lottery schedule info
		schedule.setExecutionConfig("schedule test");
		schedule.setLicenseYear(String.valueOf(DateFunctions.getYearAfterCurrentYear(3)));
		schedule.setDescription("Test permission");
		schedule.setLottery("Hunt Product");
		schedule.setCalculateAgeMethod("Specific Date");
		schedule.setSpecificDate(DateFunctions.getDateAfterToday(3*365+127));
		schedule.setFreezePeriodEndDate(DateFunctions.getDateAfterToday(3*365+128));
		schedule.setFreezePeriodEndTime("11:11");
		schedule.setFreezePeriodEndAmPm("AM");
		schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(3*365+129));
		schedule.setAwardAcceptanceByTime("11:12");
		schedule.setAwardAcceptanceByAmPm("PM");
		
		//Admin manager login info
		loginAdm.url = AwoUtil.getOrmsURL(env);;
		loginAdm.userName = TestProperty.getProperty("orms.adm.user");
		loginAdm.password = TestProperty.getProperty("orms.adm.pw");
		loginAdm.contract = "MS Contract";
		loginAdm.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		role.roleName = "HF HQ Role - Auto";
		role.application = "LicenseManager";
	}
	
	private void clearOldSchedule() {
		lm.deactivateLotteryScheduleByDesc(schedule.getDescription());
		lm.deactiveLotteryScheduleByLicenseYearAndLotteryPrd(schedule.getLottery(), schedule.getLicenseYear());
	}

}
