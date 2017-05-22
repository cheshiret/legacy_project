package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.OfficerInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrAddOrChangeBadgeAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficerBadgesAssignPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficerDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficersAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficersSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This is for set up officer in license manager, it including two method:add officer, assign badge to officer
 * @Preconditions:If a badge need to be assigned to the officer, then the badge must be added first:AddBadge.java
 * @author pchen
 * @Date  Dec 03, 2012
 */
public class AddOfficerFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private OfficerInfo officer = new OfficerInfo();
	private boolean loggedin=false;
	private String contract = "";
	private LicMgrOfficersSearchPage officerSearchPg = LicMgrOfficersSearchPage.getInstance();
	private String location;
	private boolean needAddNewOfficer;
	private boolean needAssignBadge;
	LicMgrOfficersAddPage addOfficerPg = LicMgrOfficersAddPage.getInstance();
	LicMgrOfficerDetailPage officerDetailPg = LicMgrOfficerDetailPage.getInstance();
	LicMgrAddOrChangeBadgeAssignmentWidget assignWidge = LicMgrAddOrChangeBadgeAssignmentWidget.getInstance();

	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		officer = (OfficerInfo)param[1];
		needAddNewOfficer = (Boolean)param[2];
		needAssignBadge = (Boolean)param[3];
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
		if (!loggedin || !isBrowserOpened || assignWidge.exists()) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		contract = login.contract;
		location = login.location;
		
		if(!officerSearchPg.exists()){
			lm.gotoOfficerSearchPgFromTopMenu();
		}
		
		if(!needAddNewOfficer){
			officer.id = lm.searchOfficerAndGoToDetailPage(officer);
		}else{
			lm.addOfficerFromOfficerSearchPage(officer);
			this.verifyOfficerAddedResult();
			if(needAssignBadge){
				officerSearchPg.clickOfficerId(officer.id);
				officerDetailPg.waitLoading();
			}
		}
		if(needAssignBadge){
			lm.assignBadgeToOfficerInOfficerDetailPage(officer.badge);
			this.verifyBadgeAssignResult();
		}
		newAddValue = officer.id + "_" + officer.badge.id;
	}
	
	private void verifyOfficerAddedResult() {
		boolean passed = true;
		if(addOfficerPg.exists()){
			logger.error("[FAILED]Add new offficer failed, failed reason:" + addOfficerPg.getErrorMsg());
			addOfficerPg.clickCancel();
			ajax.waitLoading();
			officerSearchPg.waitLoading();
			passed = false;
		}else{
			officerSearchPg.searchByOfficerBasicInfo(officer.firstName, officer.lastName, officer.birthday);
			List<String> searchResult = officerSearchPg.getFirstSearchResultInfo();
			if(searchResult.size() < 1){
				passed = false;
				logger.error("[FAILED]Add new officer failed, firstName:"+officer.firstName + ",lastName:" + officer.lastName);
			}else{
				officer.id = searchResult.get(0);
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Add officer failed!");
		}
	}
	
	private void verifyBadgeAssignResult(){
		boolean passed = true;
		
		LicMgrOfficerBadgesAssignPage badgeAssPg = LicMgrOfficerBadgesAssignPage.getInstance();
		if(assignWidge.exists()){
			logger.error("[FAILED]Assign badge to officer failed, failed reason:" + assignWidge.getErrorMsg());
			assignWidge.clickCancel();
			ajax.waitLoading();
			officerDetailPg.waitLoading();
			passed = false;
		}else{
			List<String> badgeInfo = badgeAssPg.getRowInfoByBadgeNum(officer.badge.badgeNum);
			if(badgeInfo.size()<1||!badgeInfo.get(2).equals(OrmsConstants.ACTIVE_STATUS)||
					!badgeInfo.get(4).equalsIgnoreCase(officer.badge.district)){
				passed = false;
				logger.error("Assign badge to officer failed, badgeNum:" + officer.badge.badgeNum);
			}else{
				officer.badge.id = badgeInfo.get(1);
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Assign badge to officer failed!");
		}
	}
}
