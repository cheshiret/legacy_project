package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BadgeInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrAddBadgesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrBadgeSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This is for set up badges in license manager
 * @Preconditions:
 * @author pchen
 * @Date  Dec 03, 2012
 */
public class AddBadgeFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private List<BadgeInfo> badgeList = new ArrayList<BadgeInfo>();
	private boolean loggedin=false;
	private String contract = "";
	private LicMgrBadgeSearchPage badgeSearchPg = LicMgrBadgeSearchPage.getInstance();
	private String location;
	private String badgeIds = "";
	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		badgeList = (List<BadgeInfo>)param[1];
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
		contract = login.contract;
		location = login.location;
		
		if(!badgeSearchPg.exists()){
			lm.gotoOfficerSearchPgFromTopMenu();
			lm.gotoBadgeSearchPgFromOfficerSearchPg();
		}
		
		lm.addBadgeFromBadgeSearchPage(badgeList);
		this.verifyResult();
		newAddValue = badgeIds;
	}
	private void verifyResult() {
		boolean passed = true;
		LicMgrAddBadgesWidget addBadgeWidget = LicMgrAddBadgesWidget.getInstance();
		if(addBadgeWidget.exists()){
			logger.error("[FAILED]Add new badges failed, failed reson:" + addBadgeWidget.getErrorMsg());
			addBadgeWidget.clickCancel();
			ajax.waitLoading();
			badgeSearchPg.waitLoading();
			passed = false;
		}else{
			for(BadgeInfo badge:badgeList){
				badgeSearchPg.searchBadgeByNum(badge.badgeNum);
				List<String> badgeInfo = badgeSearchPg.getRowInfoByBadgeNum(badge.badgeNum);
				if(badgeInfo.size()<1||!badgeInfo.get(1).equals(OrmsConstants.ACTIVE_STATUS)||
						!badgeInfo.get(3).equalsIgnoreCase(badge.district)){
					passed = false;
					logger.error("Add badge failed, badgeNum:" + badge.badgeNum);
				}else{
					badge.id = badgeInfo.get(0);
					badgeIds += badge.id + ",";
				}
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Add badges failed");
		}
	}
}
