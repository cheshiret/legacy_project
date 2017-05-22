/*
 * $Id: FinMgrSelectFeeTypePage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager;

import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * @author CGuo
 */
public class FinMgrSelectFeeTypePage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrSelectFeeTypePage
	 * Generated     : Dec 13, 2004 4:36:54 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/12/13
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrSelectFeeTypePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrSelectFeeTypePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrSelectFeeTypePage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrSelectFeeTypePage();
		}

		return _instance;
	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class","Html.A",".text","Cancel");
		//2013.08.08 Quentin
		return browser.checkHtmlObjectExists(".id", "FeeScheduleDetailUIPluginForm");
	}

	/** Select Use Fee. */
	public void selectUseFee() {
		selectFeeType("Use Fee");
	}
	
	public void selectProductCategory(String category){
	  browser.selectDropdownList(".id","prd_grp_cat_id",category);
	}

	/**
	 * Select fee for given type.
	 * @param feeType
	 */
	public void selectFeeType(String feeType) {
		browser.selectDropdownList(".id", "fee_type", feeType);
	}

}
