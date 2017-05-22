/*
 * Created on Jan 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDistributionRptSummaryPage extends FinanceManagerPage{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDistributionRptSummaryPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDistributionRptSummaryPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDistributionRptSummaryPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrDistributionRptSummaryPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Distribution Recipient Summary");
	}
	
	public void selectSearchType(String searchType)
	{
	  	browser.selectDropdownList(".id","srch.rcpnt.param",searchType);
	}
	
	public void setSearchValue(String searchValue)
	{
	  	browser.setTextField(".id","srch.rcpnt.input",searchValue);
	}
	
	public void selectRecipientType(String recipientType)
	{
	  	browser.selectDropdownList(".id","srch.rcpnt.type",recipientType);
	}
	
	public void clickGo()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	/**
	 * This method used to search recipient by recipient name
	 * @param rptName
	 */
	public void searchByRecipientName(String rptName)
	{
	  selectSearchType("Recipient Name");
	  setSearchValue(rptName);
	  clickGo();
	  waitLoading();
	}
	
	/**
	 * This method used to click the first numeric recipient
	 * if not found, it will throw not found exception
	 */
	public void clickFirstRecipient()
	{
	  	browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("\\d+",false),true);
	}
}
