/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @author Reed Wang
 * 
 */
public class AddVoucherProgramFunction extends FunctionCase {

	private VoucherProgram vp;
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private LoginInfo login;
	private boolean loggedIn;
	private String previousContract;
	
	
	@Override
	public void execute() {		
		if (!login.contract.equals(previousContract) && loggedIn && isBrowserOpened) {
			fnm.switchToContract(login.contract, login.location);
		} 
		
		if (!loggedIn || !isBrowserOpened) { // Logged in
			fnm.loginFinanceManager(login);
			loggedIn = true;
		}
		previousContract = login.contract;
		
		if(!homePage.exists()){
			fnm.gotoHomePage();
		}
		
		fnm.gotoVouchersPage();
		fnm.gotoVoucherProgramPage();
		String programId = fnm.addNewVoucherProgram(vp);
		if(!programId.matches("\\d+"))
		{
			throw new ErrorOnPageException("Add new voucher program failed...");
		}else{
			newAddValue  = programId;
			logger.info("Add new voucher program success...");
		}
		fnm.changeVoucherProgramStatus(programId, OrmsConstants.ACTIVE_STATUS);								
		
	}


	@Override
	public void wrapParameters(Object[] param) {	
		this.login = (LoginInfo)param[0];
		this.vp = (VoucherProgram)param[1];		
	}
}
