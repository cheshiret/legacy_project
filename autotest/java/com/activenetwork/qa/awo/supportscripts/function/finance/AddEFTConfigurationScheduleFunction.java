/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTConfigurationScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration.FinMgrEFTConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 21, 2012
 */
public class AddEFTConfigurationScheduleFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private EFTConfigurationScheduleInfo scheduleInfo = new EFTConfigurationScheduleInfo();
	private FinMgrEFTConfigurationPage EFTConfigPg = FinMgrEFTConfigurationPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String contract;
	private boolean loggedIn = false;
	public void wrapParameters(Object[] param) {
		scheduleInfo = (EFTConfigurationScheduleInfo)param[1];

		login = (LoginInfo)param[0];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

	public void execute() {
		//Finance Manage can switch
		if (!login.contract.equalsIgnoreCase(contract) && loggedIn && isBrowserOpened) {
			fnm.switchToContract(login.contract, login.location);
		}
		if (!loggedIn || !isBrowserOpened) {
			fnm.loginFinanceManager(login);
			loggedIn = true;
		}
		contract = login.contract;
		if(!homePage.exists()){
			fnm.gotoHomePage();
		}

		fnm.gotoEFTConfigurationPg();

		// add new EFT Configuration Schedule
		Object page = fnm.createEFTConfigSchedule(scheduleInfo);
		if(page != EFTConfigPg){
			throw new ErrorOnPageException("Failed to create new EFT configuration schedule!");
		}

		// set up search info
		scheduleInfo.status = OrmsConstants.ACTIVE_STATUS;
		scheduleInfo.searchDate = "Effective";
		scheduleInfo.fromDate = scheduleInfo.effectiveDate;
		scheduleInfo.toDate = scheduleInfo.effectiveDate;
		EFTConfigPg.searchEFTConfigSchedule(scheduleInfo);
		EFTConfigurationScheduleInfo actualScheduleInfo = this.getScheduleListInfo();
		newAddValue = actualScheduleInfo.id;
		boolean result = verifyScheduleInfo(scheduleInfo, actualScheduleInfo);
		if(!result){
			throw new ErrorOnPageException("Record was not added successfully, please check logs above.");
		}
	}

	/**
	 * Get EFT Configuration Schedule info from search result list.
	 * @return
	 */
	private EFTConfigurationScheduleInfo getScheduleListInfo(){
		logger.info("Get EFT Configuration Schedule info from search result list.");
		List<EFTConfigurationScheduleInfo> scheduleList = EFTConfigPg.searchEFTConfigSchedule(scheduleInfo);
		EFTConfigurationScheduleInfo actualScheduleInfo = new EFTConfigurationScheduleInfo();
		if(scheduleList.size() < 1){
			throw new ErrorOnPageException("Can't find the new added EFT Configuration Schedule info list.");
		} else {
			actualScheduleInfo = scheduleList.get(scheduleList.size()-1);
		}
		return actualScheduleInfo;
	}
	
	/**
	 * Verify whether EFT Configuration Schedule is correct or not
	 * @param expectInfo
	 * @param actualInfo
	 */
	private boolean verifyScheduleInfo(EFTConfigurationScheduleInfo expectInfo, EFTConfigurationScheduleInfo actualInfo){
		boolean result = true;
		logger.info("Verify whether EFT Configuration Schedule is correct or not");
		result &= MiscFunctions.compareResult("location", expectInfo.location, actualInfo.location);
		result &= MiscFunctions.compareResult("payment group", expectInfo.paymentGrp, actualInfo.paymentGrp);
		result &= MiscFunctions.compareResult("payment type", expectInfo.paymentType, actualInfo.paymentType);
		result &= MiscFunctions.compareResult("status", expectInfo.status, actualInfo.status);
		result &= MiscFunctions.compareResult("deposit adjustment", expectInfo.includeDepositAdj, actualInfo.includeDepositAdj);
		result &= MiscFunctions.compareResult("deduct vendor fee", expectInfo.deductVendorFee, actualInfo.deductVendorFee);
		result &= MiscFunctions.compareResult("effective date", expectInfo.effectiveDate, actualInfo.effectiveDate);
		result &= MiscFunctions.compareResult("transmit invoice", expectInfo.invoiceTransEnabled, actualInfo.invoiceTransEnabled);

		result &= MiscFunctions.compareResult("transmit remittance", expectInfo.remittanceTransEnabled, actualInfo.remittanceTransEnabled);
		
		logger.info("Verify deposit adjustment store...");
		// if include deposit adjustment has not been selected, deposit adjustment store should not be set up
		if(!expectInfo.includeDepositAdj){
			if(!StringUtil.isEmpty(actualInfo.depositAdjStore)){
				logger.error("--Incluede Deposit Adjustment has not been selected, so Deposit Adjustment Store should be blank. But now its value is:"+actualInfo.depositAdjStore+".");
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Deposit Adjustment Store", expectInfo.depositAdjStore, actualInfo.depositAdjStore);
		}
		return result;
	}
}
