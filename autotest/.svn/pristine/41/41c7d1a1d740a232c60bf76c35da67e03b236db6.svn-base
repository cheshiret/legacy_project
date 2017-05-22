/*
 * $Id: FinMgrHomePage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.financeManager;

import java.util.List;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class FinMgrHomePage extends FinMgrCommonTopMenuPage {

	/**
	 * Script Name   : <b>FinMgrHomePage</b>
	 * Generated     : <b>Oct 6, 2004 5:33:11 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/06
	 * @author CGuo
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrHomePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrHomePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression("Chart of Account",false));
	}

	/**
	 * Select by given value to go to special page.
	 * @param item
	 */
	public void selectFromList(String item) {
		browser.selectDropdownList(".id", "page_name", item);
	}

	/** Go to Fee Schedules page.*/
	public void selectFeesSchedules() {
		this.selectFromList("Fee Schedules");
	}

	/** Go to Discounts page.*/
	public void selectDiscounts() {
		this.selectFromList("Discounts");
	}

	/** Go to Taxes page.*/
	public void selectTaxes() {
		this.selectFromList("Taxes");
	}
	
	public void selectEFT() {
		this.selectFromList("EFT");
	}
	
	public void selectEFTConfiguration(){
		this.selectFromList("EFT Configuration");
	}

	public void selectPOSProductAssignment(){
		this.selectFromList("POS Product Assignment");
	}
	
	/** Go to Finance Session page.*/
	public void selectFinSession() {
		this.selectFromList("Finance Session");
	}

	/** Go to Distribution page.*/
	public void selectDistribution() {
		this.selectFromList("Distribution");
	}

	/** Go to Deposits, Credit Cards, Reconciliations page.*/
	public void selectDeposits() {
		this.selectFromList("Deposits, Credit Cards, Reconciliations");
	}
	
	/** Go to Batches and Reconciliations page.*/
	public void selectReconciliation() {
		this.selectFromList("Batches and Reconciliations");
	}

	/** Go to Accounts and Journals page.*/
	public void selectAccountsAndJounals() {
		this.selectFromList("Accounts and Journals");
	}

	/** Click on the CallManager log out link */
	public void signOut() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sign out");
	}
	/*
	 * check the string option exist in dropdown list option or not.
	 */
    public boolean checkDropdownOption(String item){
    	List<String> array = browser.getDropdownElements(".id", "page_name");
    	if(array.contains(item)){
    		return  true;
    	}else{
    		return false;
    	}
    	
    }
    /*
     * check POS Product assignment exist in drop down list or not.
     */
	public boolean checkPosProductAssignmentOption(){
		return this.checkDropdownOption("POS Product Assignment");
	}
	
	
}
