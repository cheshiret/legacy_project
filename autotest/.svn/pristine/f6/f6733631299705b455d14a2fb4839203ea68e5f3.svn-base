package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriLicenseYearFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseYear licenseYear = new LicenseYear();
	private int numOfYearsAfterCurrent;
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private boolean loggedin=false;
	private String contract, location;
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicMgrPrivilegeAddLicYearWidget addYearPg = LicMgrPrivilegeAddLicYearWidget.getInstance();
	private String schema;
	private boolean isSellFromCurrent, isSameSalesDates;
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		licenseYear = (LicenseYear)param[2];
		numOfYearsAfterCurrent = (int)param[3];
		isSellFromCurrent = (boolean)param[4];
		isSameSalesDates = (boolean)param[5];
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.replace("Contract", StringUtil.EMPTY);
	}

	@Override
	public void execute() {
		if(loggedin && isBrowserOpened && addYearPg.exists()){ //If the last one is failed by throw an exception in the step 'lm.addLicenseYear(licenseYear);', the addYear page will not be closed which will infect the follow operation
			addYearPg.clickCancel();
			ajax.waitLoading();
			licenseYearPg.waitLoading();
		}
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
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(licenseYear.productCode);
		lm.gotoPrivilegeLicenseYearPage();
		
		int baseLicenseYear = Integer.parseInt(licenseYear.licYear);
		int effectiveYearType = lm.getFiscalYearEffectiveYearType(schema);
		boolean isEndingAsEffective = effectiveYearType == 2;
		String startEndDates[] = lm.getFiscalYearStartingEndingDates(schema);
		for(int i = 0; i <= numOfYearsAfterCurrent; i ++) {
			licenseYear.licYear = String.valueOf(baseLicenseYear + i);
			
			if (!isSellFromCurrent) {
//				String startingYear = isEndingAsEffective ? String.valueOf(Integer.parseInt(licenseYear.licYear) - 1) : licenseYear.licYear;
//				String endingYear = isEndingAsEffective ? licenseYear.licYear : String.valueOf(Integer.parseInt(licenseYear.licYear) + 1);
//				
//				licenseYear.sellFromDate = startEndDates[0] + "/" + startingYear;
//				licenseYear.sellToDate = startEndDates[1] + "/" + endingYear;
				if(!isSameSalesDates)
					licenseYear.calculateSellFromAndToDates(isEndingAsEffective, startEndDates, StringUtil.EMPTY);
			}
			lm.addLicenseYear(licenseYear);
			this.verifyResult();
			newAddValue += licenseYearPg.getLicenseYearId(licenseYear.status, licenseYear.locationClass, licenseYear.licYear) + (i < numOfYearsAfterCurrent ? ", " : "");
		}
	}
	
	public void verifyResult() {
		String msg = "create privilege product license year failed:code="+licenseYear.productCode+",name="+licenseYear.productName+ ", license year=" 
			+ licenseYear.licYear + ",license year location class=" + licenseYear.locationClass + ",license year status =" + licenseYear.status;
		if (addYearPg.exists()) {
			String errMsg = addYearPg.getErrorMsg();
			addYearPg.clickCancel();
			ajax.waitLoading();
			licenseYearPg.waitLoading();
			throw new ErrorOnPageException(msg + ". Due to: "+errMsg);
		}
		
		if(!licenseYearPg.exists()) {
			 throw new ErrorOnPageException(msg);
		}
		
		if (!licenseYearPg.verifyLicenseYearExist(licenseYear)) {
			throw new ErrorOnPageException(msg);
		}
		logger.info("Successfully add privilege product license year: code="+licenseYear.productCode+", license year=" + licenseYear.licYear);
	}
}
