/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;




import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrPosProductAssignmentPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;


public class AssignPOSToCallWebFunction extends FunctionCase {

	private LoginInfo login;
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage.getInstance();
	private FinMgrHomePage homePg = FinMgrHomePage.getInstance();
	private POSInfo pos;
	private String assignChannel = "";
	private String result,contract = "";
	private boolean loggedIn;

	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn && isBrowserOpened) {
			fnm.switchToContract(login.contract, login.location);
		}
		if(!loggedIn || !isBrowserOpened){
			fnm.loginFinanceManager(login);
			loggedIn=true;
		}
		contract = login.contract;
		if(!homePg.exists()){
			fnm.gotoHomePage();
		}
		
		fnm.gotoPosProductAssignmentPage();		
		fnm.assignPosProduct(assignChannel, pos);		
		
		if(!verifyResult(pos.product, assignChannel)){
			result = "Failed!!!----Please check again!!!";
			throw new ErrorOnPageException(result);
		}else{
			result = "Passed";
			newAddValue = result;
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.assignChannel = (String)param[1];
		this.pos = (POSInfo)param[2];
	}
	
	private boolean verifyResult(String productName, String assignChannel){
		Boolean result = false;
		if(assignPg.getPOSProductAssignStatus(pos.product)){
			result = true;
		}else{
			result = false;
			logger.error("Failed to assign pos product "+pos.product+" to "+assignChannel);
		}
		
		return result;
	}

}
