package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 11, 2013
 */
public class AddLotteryScheduleFunction extends FunctionCase {

	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private boolean isLoggedIn = false;
	private String loggedInContract, loggedInLocation;
	
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
		lm.gotoLotterySchedulePage();
		searchActiveLotteryScheduleByLotteryPrdAndLicenseYear();
		if(StringUtil.isEmpty(schedule.getId())) {
			String id = lm.addLotterySchedule(schedule);
			this.verifyResult(id);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		schedule = (PrivilegeLotteryScheduleInfo)param[1];
	}
	
	private void verifyResult(String id) {
		if(!id.matches("\\d+")) {
			throw new ErrorOnPageException("Add Privilege Lottery Schedule failed due to: " + id);
		} logger.info("Add Privilege Lottery Schedule successfully - " + id);
		newAddValue = id;
	}
	
	private void searchActiveLotteryScheduleByLotteryPrdAndLicenseYear() {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		logger.info("Search lottery schedule(lotteryProduct=" + schedule.getLottery() + ",licenseYear=" +schedule.getLicenseYear()+")");
		listPage.searchActiveLotteryScheduleByLotteryPrdAndLicenseYear(schedule.getLottery(), schedule.getLicenseYear());
		String id = listPage.getFirstLotteryScheduleIDInList();
		if(StringUtil.notEmpty(id)){
			schedule.setId(id);
		}
	}
}
