package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 10, 2013
 */
public class AddLotteryLicenseYearFunction extends FunctionCase {

	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private LicenseYear licenseYear = new LicenseYear();
	private int numOfYears = 0;
	private String loggedInContract, loggedInLocation, schema;
	private boolean isLoggedIn = false;
	private LicMgrLotteryLicenseYearsPage licenseYearPage = LicMgrLotteryLicenseYearsPage.getInstance();
	private boolean isSellFromCurrent;
	
	@Override
	public void execute() {
		if(isLoggedIn) {
			if(!loggedInContract.equalsIgnoreCase(login.contract)) {
				lm.logOutLicenseManager();
				isLoggedIn = false;
			} else if(loggedInLocation.equalsIgnoreCase(login.location)) {
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		
		if(!isLoggedIn) {
			lm.loginLicenseManager(login);
			isLoggedIn = true;
		}
		
		loggedInContract = login.contract;
		loggedInLocation = login.location;
		
		if(!LicenseMgrHomePage.getInstance().exists()) {
			lm.gotoHomePage();
		}
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(licenseYear.productCode);
		lm.gotoLotteryProductLicenseYearsPage();
		
		int baseLicenseYear = Integer.parseInt(licenseYear.licYear);
		String currentYearSellFromDate = licenseYear.sellFromDate;
		int effectiveYearType = lm.getFiscalYearEffectiveYearType(schema);
		boolean isEndingAsEffective = effectiveYearType == 2;
		String startEndDates[] = lm.getFiscalYearStartingEndingDates(schema);
		for(int i = 0; i <= numOfYears; i ++) {
			licenseYear.licYear = String.valueOf(baseLicenseYear + i);
			
			if (!isSellFromCurrent) {
//				String startingYear = isEndingAsEffective ? String.valueOf(Integer.parseInt(licenseYear.licYear) - 1) : licenseYear.licYear;
//				String endingYear = isEndingAsEffective ? licenseYear.licYear : String.valueOf(Integer.parseInt(licenseYear.licYear) + 1);
//				
//				if(Integer.parseInt(startingYear) <= DateFunctions.getCurrentYear()) {
//					licenseYear.sellFromDate = currentYearSellFromDate;
//				} else {
//					licenseYear.sellFromDate =  startEndDates[0] + "/" + startingYear;
//				}
//				licenseYear.sellToDate = startEndDates[1] + "/" + endingYear;
				licenseYear.calculateSellFromAndToDates(isEndingAsEffective, startEndDates, currentYearSellFromDate);
			}
			lm.addLotteryLicenseYear(licenseYear);
			newAddValue += this.verifyResult(licenseYear) + (i < numOfYears ? ", " : "");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		licenseYear = (LicenseYear)param[1];
		numOfYears = (int)param[2];
		isSellFromCurrent = (boolean)param[3];
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.replace("Contract", StringUtil.EMPTY).trim();
	}
	
	public String verifyResult(LicenseYear year) {
		LicMgrLotteryLicenseYearDetailsWidget addWidget = LicMgrLotteryLicenseYearDetailsWidget.getInstance();
		
		String msg = "Create Lottery product license year failed: code = "+year.productCode + ", license year = " 
			+ year.licYear + ", license year location class = " + year.locationClass + ", license year status = " + year.status;
		if (addWidget.exists()) {
			String errMsg = addWidget.getErrorMsg();
			addWidget.clickCancel();
			ajax.waitLoading();
			licenseYearPage.waitLoading();
			throw new ErrorOnPageException(msg + ". Due to: " + errMsg);
		}
		
		if(!licenseYearPage.exists()) {
			 throw new ErrorOnPageException(msg);
		}
		
		String id = licenseYearPage.getLicenseYearId(year.status, year.locationClass, year.licYear);
		if (StringUtil.isEmpty(id)) {
			throw new ErrorOnPageException(msg);
		} else logger.info("Create Lottery product license year successfully: code = "+year.productCode+", license year = " + year.licYear);
		
		return id;
	}
}
