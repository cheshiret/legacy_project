package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.SysInfo;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * This script will add a Hunts lottery Permit based on provided data in License Manager of AWO
 * @author jdu
 *
 */
public class AddHuntLotteryPermits extends SupportCase {
	LoginInfo login=new LoginInfo();
	private boolean loggedIn = false;// Don't login.
	private String huntCode = "", applicantType="",permit="",ageMin="",ageMax="",residenceStatus="";
	private HuntPermitInfo permitInfo = null;
	private boolean newBrowser=true;
	private LicenseManager lm = LicenseManager.getInstance();
	private int count=0;

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 0; // the start point in the data pool
		endpoint = 999; // the end point in the data pool
		
		fromDB=false;
		dataSource = "supportscripts/support/licensemgr/AddHuntLotteryPermit.datapool";//SysInfo.getPath("AddHuntLotteryPermits")+"/AddHuntLotteryPermits.datapool";
		logFile="X:/SK_AddHuntPermit/"+SysInfo.getHostName()+".log";
		
//		login.url = "Https://uat2-orms.reserveamerica.com";
		login.url="https://web01.qa.reserveamerica.com:6401";
//		login.userName = TestProperty.getProperty("orms.fm.user");
//		login.password = TestProperty.getProperty("orms.fm.pw");
		login.userName="ra-jdu";
		login.password="auto1234";

		login.role="SK Admin";
		login.location="SASK";
		login.contract="SK Contract";
		
		TestProperty.putProperty("role.auto","false");
		
		if(param!=null && param.length>0) {//overwrite 	
			String temp=(String) TestDriverUtil.getParameter(param, "user");
			if(StringUtil.notEmpty(temp)) {
				login.userName=temp;
			}
			
			temp=(String) TestDriverUtil.getParameter(param, "pw");
			if(StringUtil.notEmpty(temp)) {
				login.password=temp;
			}
			
			temp=(String) TestDriverUtil.getParameter(param, "url");
			if(StringUtil.notEmpty(temp)) {
				login.url=temp;
			}
			
			
			if(StringUtil.isEmpty(login.userName) || StringUtil.isEmpty(login.password) || StringUtil.isEmpty(login.url)) {
				throw new ItemNotFoundException("Missing user name, password and url");
			}
			
		}
		
		initLogMsg("Index","hunt_code","applicantType","license","ageMin","ageMax","residenceStatus","licenseID","Result");
		
	}

	@Override
	public void execute() {
		
		if(!loggedIn) {
			lm.loginLicenseManager(login);
			loggedIn=true;
		} else if(count%20==0) {
			try{
				lm.logOutLicenseManager();
			} catch(Exception e){}
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		
		navigateToHuntListPage();
		addHuntLicense();
			
		reset();		
	}
	
	private void navigateToHuntListPage() {
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		
		if(!huntsListPg.exists()) {
			lm.gotoLotteriesProductListPgFromTopMenu();
			lm.gotoHuntsListPgFromLotteriesProdListPg();
		}
	}
	
	private void addHuntLicense() {	
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
		LicMgrAddHuntPermitWidget addHuntPermitPg = LicMgrAddHuntPermitWidget.getInstance();
		LicMgrHuntComponentsSubPage huntDetailPg = LicMgrHuntComponentsSubPage.getInstance();
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		
		logger.info("Add hunt permit info.");
		lm.gotoHuntDetailPgFromHuntsListPg(huntCode);
		lm.gotoHuntPermitListPgFromHuntDetailPg();
		lm.gotoAddHuntPermitPgFromHuntPermitListPg();
		List<HuntPermitInfo> permitInfos=new ArrayList<HuntPermitInfo>();
		permitInfos.add(permitInfo);
		addHuntPermitPg.setupHuntPermitInfo(permitInfos);
		addHuntPermitPg.clickOK();

		ajax.waitLoading();
		boolean disapeared=addHuntPermitPg.waitDisappear();
		if(disapeared){
			huntPermitListPg.getHuntPermitIDByHuntPermitInfo(permitInfos);
			logMsg[7]=permitInfo.getHuntPermitID();
			logMsg[8]="Successful";
			
		} else {
			String error=addHuntPermitPg.getErrorMessage();
			logMsg[8]="Failed: due to "+error;
			addHuntPermitPg.clickCancel();
			huntDetailPg.waitLoading();
		}
		
		huntDetailPg.clickOK();
		huntsListPg.waitLoading();
	}

	@Override
	public void getNextData() {
		huntCode=dpIter.dpString("HUNT CODE");
		applicantType=dpIter.dpString("APPLICANT TYPE");
		permit=dpIter.dpString("LICENCE");
		ageMin=dpIter.dpString("MINIMUM AGE");
		ageMax=dpIter.dpString("MAXIMUM AGE");
		residenceStatus=dpIter.dpString("RESIDENCY STATUS");
		
		permitInfo = new HuntPermitInfo();
		
		permitInfo.setApplicantType(applicantType);
		permitInfo.setHuntCode(huntCode);
		permitInfo.setPermit(permit);
		permitInfo.setMinAge(ageMin);
		permitInfo.setMaxAge(ageMax);
		permitInfo.setResidencyStatus(residenceStatus);
		
		logMsg[0]=Integer.toString(cursor);
		logMsg[1]=huntCode;
		logMsg[2]=applicantType;
		logMsg[3]=permit;
		logMsg[4]=ageMin;
		logMsg[5]=ageMax;
		logMsg[6]=residenceStatus;
		logMsg[7]="Unknown";
		logMsg[8]="Failed";
	}
	
	private void reset() {
		huntCode = "";
		applicantType="";
		permit="";
		ageMin="";
		ageMax="";
		residenceStatus="";
	}
	
	
	

}
