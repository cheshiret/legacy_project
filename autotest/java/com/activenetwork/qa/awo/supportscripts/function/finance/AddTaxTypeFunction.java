/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 22, 2012
 */
public class AddTaxTypeFunction extends FunctionCase {
	private FinanceManager fin = FinanceManager.getInstance();
  	private FinMgrTaxMainPage fintaxMainPg = FinMgrTaxMainPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	private LoginInfo login = new LoginInfo();
	private Tax tax = new Tax();
	
	public void wrapParameters(Object[] param) {
		tax = (Tax)param[1];
		
		login = (LoginInfo)param[0];
	}

	public void execute() {
		if (!login.contract.equals(previousContract) && loggedIn && isBrowserOpened) {
			fin.switchToContract(login.contract, login.location);
		} 
		
		if (!loggedIn || !isBrowserOpened) { // Logged in
			fin.loginFinanceManager(login);
			loggedIn = true;
		}
		previousContract = login.contract;
		
		if(!homePage.exists()){
			fin.gotoHomePage();
		}
		
		fin.gotoTaxMainPage();
		
		fintaxMainPg.searchByTaxName(tax.taxName);
		if(!fintaxMainPg.isTaxExists(tax.taxName))
		{		
			 //add new tax type
			fin.addNewTax(tax);
			newAddValue = tax.taxName;	
		}
       	//Activate tax
		fintaxMainPg.changeTaxStatus(tax.taxName,"Yes");
	    
	    //get current tax's status
	    if(!fintaxMainPg.isTaxActive()){
	    	throw new ErrorOnPageException("Failed to add new tax.");
	    }
	}
	
}
