package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLicYearListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Hunt License Year function in LM
 * @author Lesley Wang
 * @Date  Aug 19, 2013
 */
public class AddHuntLicenseYearFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private LicenseYear ly = new LicenseYear();
	private String contract = "";
	private String location = "";
	private String huntCode, schema;
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrAddHuntLicYearWidget addHuntLYPg = LicMgrAddHuntLicYearWidget.getInstance();
	private LicMgrHuntLicYearListPage huntLYListPg = LicMgrHuntLicYearListPage.getInstance();
	private int numOfYears = 0;
	private boolean isSellFromCurrent;
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		huntCode = (String)param[1];
		ly = (LicenseYear)param[2];
		numOfYears = (int)param[3];
		isSellFromCurrent = (boolean)param[4];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.replace("Contract", StringUtil.EMPTY).trim();
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
		
		lm.gotoHuntsListPg();
		lm.gotoHuntLicYearListFromHuntsListPg(huntCode);
		
		int baseLicenseYear = Integer.parseInt(ly.licYear);
		int effectiveYearType = lm.getFiscalYearEffectiveYearType(schema);
		boolean isEndingAsEffective = effectiveYearType == 2;
		String startEndDates[] = lm.getFiscalYearStartingEndingDates(schema);
		for(int i = 0; i <= numOfYears; i ++) {
			ly.licYear = String.valueOf(baseLicenseYear + i);
			
			if (!isSellFromCurrent) {
				ly.calculateSellFromAndToDates(isEndingAsEffective, startEndDates, StringUtil.EMPTY);
			}
			searchLicenseYearByAssignedPrdAndLicYear();
			if(StringUtil.isEmpty(ly.id)) {
				lm.addHuntLicenseYear(ly);
				newAddValue += this.verifyResult(ly) + (i < numOfYears ? ", " : "");
			}
		}
	}

	private String verifyResult(LicenseYear year) {
		String msg0 = "Failed to add hunt license year for hunt with code=" + huntCode + ", license year= " + year.licYear;
		if (addHuntLYPg.exists()) {
			String msg = addHuntLYPg.getErrorMsg();
			addHuntLYPg.clickCancel();
			ajax.waitLoading();
			huntLYListPg.waitLoading();
			throw new ErrorOnPageException(msg0 + " due to " + msg);
		}
		String id = huntLYListPg.getHuntLicYearID(year.assignedProd, year.licYear);
		if (StringUtil.isEmpty(id)) {
			throw new ErrorOnPageException(msg0);
		} else logger.info("Create Lottery product license year successfully: code = "+huntCode+", license year = " + year.licYear);
		
		return id;
	}
	
	private void searchLicenseYearByAssignedPrdAndLicYear() {
		LicMgrHuntLicYearListPage listPage = LicMgrHuntLicYearListPage.getInstance();
		String id = listPage.getLicenseYearId(ly.status, ly.assignedProd, ly.licYear);
		if(StringUtil.notEmpty(id))
			ly.id = id;
	}
}
