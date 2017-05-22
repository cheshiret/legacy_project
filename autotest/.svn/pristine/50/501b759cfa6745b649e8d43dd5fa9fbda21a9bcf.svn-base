/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 21, 2012
 */
public class AddDiscountFunction extends FunctionCase {
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage.getInstance();
	private LoginInfo login;
	private DiscountData discount;
	private String returnValue,contract;
	private boolean loggedIn = false;
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		discount = (DiscountData)param[1];
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

		fnm.gotoDiscountPage();
		
		if(!discountPg.checkExist(discount.name))
		{
		
			// add new discount.
			returnValue = fnm.addNewDiscount(discount);
			if(returnValue.matches("The " + discount.name + " Discount already exists.")){// error message existed.
				this.gotoDiscountMainPage();
				logger.info("The " + discount.name + " Discount already exists.");
			}else if(!returnValue.matches(discount.name)){
				throw new ErrorOnPageException("The " + discount.name + "Discount added failed.");
			}
		}
		
		newAddValue = discount.name;
		// Activate Discount
		discountPg.changeDiscountStatus(discount.name, OrmsConstants.ACTIVE_STATUS);
		boolean result = discountPg.verifyDiscountInfo(discount);

		// verify current discount's status and put out log information
		if (!result) {
			throw new ErrorOnPageException("Record was not added successfully, please check logs above.");
		}
	}

	private void gotoDiscountMainPage(){
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage.getInstance();

		detailsPg.clickCancel();
		mainPg.waitLoading();
	}
}
