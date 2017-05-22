package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.raFee.FinMgrRaFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: This test case is to verify the Transaction/RA/Penalty Fee schedule search to include minimum unit of stay, minimum number of days before arrival date. 
 * @Preconditions: 
 * @SPEC: 
 * 		Search RA Fee schedule [TC:061832] 
 * 		Search Fee Penalty schedule [TC:061831] 
 *		Search fee schedule [TC:061830]
 * @Task#: Auto-1579
 * @author Jane
 * @Date  Apr 18, 2013
 */
public class VerifyMinUnitOfStayAndMinNumOfDaysBeforeArrDateCols extends FinanceManagerTestCase {

	private final String[] cols=new String[]{
			"Min Unit of Stay", "Min Number of Days before Arrival Date"
			};
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		List<String> colsUI = fmFeeMainPg.getAllColNamesOfFeeTable();
		verifyColsDisplayOnFeePage(colsUI);
		
		fnm.gotoRaFeeSchedulePage();
		FinMgrRaFeeMainPage raFeeMainPg = FinMgrRaFeeMainPage.getInstance();
		colsUI.clear();
		colsUI = raFeeMainPg.getAllColNamesOfRAFeeTable();
		verifyColsDisplayOnFeePage(colsUI);
		
		fnm.gotoFeePenaltyPage();
		FinMgrFeePenaltyMainPage penaltyMainPg = FinMgrFeePenaltyMainPage.getInstance();
		colsUI.clear();
		colsUI = penaltyMainPg.getAllColNamesOfPenaltyFeeTable();
		verifyColsDisplayOnFeePage(colsUI);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
	}
	
	/**
	 * This method was used to verify columns displayed on fee page or not
	 * @param colsUI -- columns name list from UI
	 */
	private void verifyColsDisplayOnFeePage(List<String> colsUI) {
		for(String col:cols) {
			if(!colsUI.contains(col))
				throw new ErrorOnPageException("Column "+col+" should display on Fee page.");
		}
		
		logger.info("Verify columns 'Min Unit of Stay' and 'Min Number of Days before Arrival Date' displayed on Fee Page successfully.");
	}
}
