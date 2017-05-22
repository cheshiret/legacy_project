/*
 * Created on Aug 14, 2009
 *
 */
package com.activenetwork.qa.awo.pages.orms.financeManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class FinMgrFeesTabs extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrFeesTabs _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrFeesTabs() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrFeesTabs getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrFeesTabs();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Fees", ".href",
				new RegularExpression("javascript:invokeAction(.*\"FindFees.do.*\",.*\"FindFee\",.*\"FeeWorker\",.*\"FromTab\".*)", false));
	}

	/** Click on Fees link. */
	public void clickFees() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees");
	}

	/** Click on RA Fee Schedules link. */
	public void clickRAFeeSchedules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "RA Fee Schedules");
	}

	/** Click on RA Fee Thresholds link. */
	public void clickRAFeeThresholds() {
		browser.clickGuiObject(".class", "Html.A", ".text", "RA Fee Thresholds");
	}

	/** Click on Fee Penalties link. */
	public void clickFeePenalties() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Fee Penalties", false));
	}
	
	/**
	 * check if fee penalties tab exist
	 * @return
	 */
	public boolean isFeePenaltiesTabExist(){
	  return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Fee Penalties");
	}
}
