package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 27, 2013
 */
public class CheckAndUpdateFeeScheduleFunction extends FunctionCase {
	
	private LoginInfo login;
	private FeeScheduleData feeDatas[];
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrFeeMainPage feeMainPage = FinMgrFeeMainPage.getInstance();
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		feeDatas = new FeeScheduleData[param.length - 1];
		for(int i = 1; i < param.length; i ++) {
			feeDatas[i - 1] = (FeeScheduleData)param[i];
		}
	}

	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		for(FeeScheduleData feeData : feeDatas) {
			
//			if(!StringUtil.isEmpty(feeData.product) && !feeData.product.equalsIgnoreCase("All") && feeData.product.contains("-")) {
//				//product is specified
//				feeMainPage.searchFeeScheduleBySimpleInfo(feeData);
//				List<String> toDeactivateIDs = feeMainPage.getFeeID();
//				if(toDeactivateIDs.size() > 0) {
//					feeMainPage.activateOrDeactivateFeeSchedule(false, toDeactivateIDs.toArray(new String[0]));
//				}
//			}
			
			feeData.feeSchdId = fnm.addNewFeeSchedule(feeData);
			
			if(!feeMainPage.isFeeScheduleExists(feeData.feeSchdId)) {
				feeMainPage.searchByFeeScheduleId(feeData.feeSchdId);
			}
			if(!feeMainPage.isFeeScheduleActive(feeData.feeSchdId) 
					&& (feeData.activeStatus.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS) || feeData.activeStatus.equalsIgnoreCase(OrmsConstants.YES_STATUS) || StringUtil.isEmpty(feeData.activeStatus))) {
				feeMainPage.activateOrDeactivateFeeSchedule(true, feeData.feeSchdId);
			} else if(feeMainPage.isFeeScheduleActive(feeData.feeSchdId) && (feeData.activeStatus.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS) || feeData.activeStatus.equalsIgnoreCase(OrmsConstants.NO_STATUS))) {
				feeMainPage.activateOrDeactivateFeeSchedule(false, feeData.feeSchdId);
			}
		}
		
		fnm.logoutFinanceManager();
	}
}
