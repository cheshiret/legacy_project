/*
 * Created on Jan 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDistributionTabs extends FinanceManagerPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDistributionTabs _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDistributionTabs() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDistributionTabs getInstance(){
		if (null == _instance) {
			_instance = new FinMgrDistributionTabs();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Distributions");
	}
	
	/**
	 * Click Distributions link from tab
	 *
	 */
	public void clickDistributionTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Distributions");
	}
	
	/**
	 * Click Run Distribution link from tab
	 *
	 */
	public void clickRunDistributionTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Run Distribution");
	}
	/**
	 * Click Recipient Schedules link from tab
	 *
	 */
	public void clickRecipientScheduleTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Recipient Schedules");
	}
	
	/**
	 * Click Recipient Permits link from tab
	 *
	 */
	public void clickRecipientPermitTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Recipient Permits");
	}
	
	/**
	 * Click Configuration Schedules link from tab
	 *
	 */
	public void clickConfigurationSchdTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Configuration Schedules");
	}
}
