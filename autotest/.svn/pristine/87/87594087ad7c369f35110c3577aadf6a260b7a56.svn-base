package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @author Reed Wang
 * 
 */
public class AddVoucherProgram extends AbstractSingleDatapoolSupportCase {

	private VoucherProgram vp;
	private String previousContract;
	private boolean loggedIn;
	private FinanceManager fm = FinanceManager.getInstance();
	private static AutomationLogger logger = AutomationLogger.getInstance();

	@Override
	protected void initRange() {
		startpoint = 0;
		endpoint = 1;
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		vp = DatapoolUtil.fill(VoucherProgram.class, dpIter);
		vp.startDate = DateFunctions.getDateAfterToday(-1);
		vp.endDate = DateFunctions.getDateAfterToday(180);
	}

	@Override
	public void execute(Orms orms) {
		try {
			LoginInfo loginInfo = orms.loginInfo;
			
			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				fm.logoutFinanceManager();
				loggedIn = false;
			} 
			
			if (!loggedIn) { // Logged in
				fm = orms.loginFinanceManager();
				previousContract = loginInfo.contract;
				loggedIn = true;

				FinMgrHomePage homePage = FinMgrHomePage.getInstance();
				homePage.waitLoading();

				fm.gotoVouchersPage();
				fm.gotoVoucherProgramPage();
			}

			String programId = fm.addNewVoucherProgram(vp);
			fm.changeVoucherProgramStatus(programId, OrmsConstants.ACTIVE_STATUS);
			
			setResult("Success");

		} catch (Exception e) {
			setResult("Error -- " + e.getMessage());
			
			logger.error(e);
			loggedIn = false;
			
			throw new RuntimeException(e);
		}
	}
}
