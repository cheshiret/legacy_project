package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class UwpPayBalanceConfirmationPage  extends UwpPage {
	private static UwpPayBalanceConfirmationPage _instance = null;

	public static UwpPayBalanceConfirmationPage getInstance() {
		if (null == _instance)
			_instance = new UwpPayBalanceConfirmationPage();

		return _instance;
	}

	public UwpPayBalanceConfirmationPage() {
	  	timeout = Browser.VERY_LONG_SLEEP;
	}

	public boolean exists() {
		
		boolean bConfirm = browser.checkHtmlObjectExists(".id", "shophdrtop",".text",new RegularExpression(" ?Payment Completed!",false));
		boolean bPayBalanceConfirm = browser.checkHtmlObjectExists(".id", "shoppingitems");
		 
		return bConfirm & bPayBalanceConfirm;
	}
	
	/**
	 * Verify whether or not this is pay balance confirmation page, because there will be 3 pages used
	 * in 1 keywords and have no unique page mark to identify.
	 * @return true - pay balance confirm page, false - not
	 */
	public boolean isPayBalanceConfirmPg() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shophdr");
		String str = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);

		if (str.matches(".*Payment Completed.*"))
			return true;
		else
			return false;
	}
	
	/**
	 * Verify whether the order has been processed completed
	 */
	public void verifySuccess() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shophdr");
		String returnStr = ((String) objs[0].getProperty(".text"));
		Browser.unregister(objs);

		if (!returnStr.matches(UwpSchConstants.PASS))
			throw new ItemNotFoundException(
					"The order processing may not be complete.");
	}
	
	/**
	 * Click 'Continue to Current reservation' link
	 */
	public void clickContinueToCurrentRes() {
//		browser.clickGuiObject(".id", "viewcrr");
		browser.clickGuiObject(".id", "reservationLink");
	}
	
	/**
	 * Verify the balance message when minimum paid
	 */
	public void verifyBalanceMsg() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "shoppingitems1");

		String content = obj[0].getProperty(".text").toString();
		if (!content
				.matches(".*Balance of \\$\\d+\\.\\d+ is due upon check in.*"))
			throw new ItemNotFoundException("No balance remainning message!");

		Browser.unregister(obj);
	}
	
	/**
	 * Verify the payment balance is zero
	 */
	public void verifyPayBalanceSuccess() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shoppingitems");
		String returnStr = ((String) objs[0].getProperty(".text"));
		Browser.unregister(objs);
		//System.out.println(returnStr);
		if (!returnStr.matches(".*Balance\\:(\\s)+\\$0\\.00.*"))
			throw new ItemNotFoundException("The pay balance should NOT be complete.");
	}
	
	/**
	 * Verify the balance in balance confirm page is equal as given value.
	 * @param balance - balance
	 */
	public void verifyBalance(String balance) {
		String context = getShoppingItemTableText();

		if(context.indexOf("Balance: $" + balance)==-1&&context.indexOf("Balance:$" + balance)==-1)
		  	throw new ItemNotFoundException("The balanceis not correct.");
		else
		  	logger.info("The balance is correct.");
	}
	
	/**
	 * Retrieve the shoping items table's text
	 * @return
	 */
	public String getShoppingItemTableText(){
	  	IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems1");
		String returnStr = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return returnStr;
	}
}
