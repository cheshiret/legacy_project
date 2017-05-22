package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Hunt Quota License Year function.
 * 
 * @author Lesley Wang
 * @Date  Mar 24, 2014
 */
public class AddHuntQuotaLicenseYearFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private QuotaInfo quota;
	private String contract = "";
	private String location = "";
	private String schema;
	private int numOfYears = 1;
	private int numOfYearsAfterCurrent = 0;
	private LicMgrQuotaListPage quotaLisPg = LicMgrQuotaListPage.getInstance();
	private LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage.getInstance();
	private LicMgrQuotaLicenseYearWidget addWg = LicMgrQuotaLicenseYearWidget.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		quota = (QuotaInfo)param[1];
		numOfYearsAfterCurrent = (int)param[2];
		numOfYears = (int)param[3];
		
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
		
		if(!quotaLisPg.exists()){
			lm.gotoLotteriesProductListPgFromTopMenu();
			lm.gotoQuotaListPgFromLotteriesProdListPg();
		}
		quota.setQuotaId(lm.getQuotaID(quota.getDescription(), schema));
		lm.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		
		int baseLicenseYear = Integer.parseInt(quota.getLicenseYear());
		for(int i = numOfYearsAfterCurrent; i < numOfYears+numOfYearsAfterCurrent; i ++) {
			quota.setLicenseYear(String.valueOf(baseLicenseYear + i));
			
			listPage.searchLicenseYear(ACTIVE_STATUS, StringUtil.EMPTY);
			if (!listPage.isLicenseYearExist(quota.getLicenseYear())) {
				lm.addLicenseYearQuota(quota);
			}
			newAddValue += this.verifyResult(quota) + (i < numOfYears ? ", " : "");
		}
	}

	private String verifyResult(QuotaInfo quota) {
		String msg0 = " add quota license year for quota with description=" + quota.getDescription() + ", license year= " + quota.getLicenseYear();
		if (addWg.exists()) {
			String msg = addWg.getErrorMsg();
			addWg.clickCancel();
			ajax.waitLoading();
			listPage.waitLoading();
			throw new ErrorOnPageException("Failed to" + msg0 + " due to " + msg);
		}
		String id = listPage.getLicenseYearId(quota.getLicenseYear());
		if (StringUtil.isEmpty(id)) {
			throw new ErrorOnPageException("Failed to" + msg0);
		} else logger.info("Successfully" + msg0);
		
		return id;
	}
}
