/*
 * Created on Jan 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DistributionInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDistributionsMainPage extends FinanceManagerPage{

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDistributionsMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDistributionsMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDistributionsMainPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrDistributionsMainPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Distribution Job ID(| )Status .*",false));
	}
	
	/**
	 * Click Recipient Schedules link from tab
	 *
	 */
	public void clickRecipientScheduleTab()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Recipient Schedules");
	}
	
	/**
	 * Click Recipient Permits link from tab
	 *
	 */
	public void clickRecipientPermitTab()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Recipient Permits");
	}
	
	/**
	 * Click Configuration Schedules link from tab
	 *
	 */
	public void clickConfigurationSchdTab()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Configuration Schedules");
	}
	
	/**
	 * Click Run Distribution link from tab
	 *
	 */
	public void clickRunDistributionTab()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Run Distribution");
	}
	
	/**
	 * Select search type from drop down list
	 * @param searchType
	 */
	public void selectSearchBy(String searchType)
	{
	  	browser.selectDropdownList(".id","srchBy.param",searchType);
	}
	
	/**
	 * Input search value
	 * @param searchValue
	 */
	public void setSearchByValue(String searchValue)
	{
	  	browser.setTextField(".id","srch.input",searchValue);
	}
	
	public void selectDateRange(String dateRange)
	{
	  	browser.selectDropdownList(".id","srchDate.type",dateRange);
	}
	
	public void setStartDate(String startDate)
	{
	  	browser.setTextField(".id","srch.startDate_ForDisplay",startDate);
	}
	
	public void setEndDate(String endDate)
	{
	  	browser.setTextField(".id","srch.endDate_ForDisplay",endDate);
	}
	
	public void selectStatus(String status)
	{
	  	browser.selectDropdownList(".id","srch.status",status);
	}
	
	public void selectCoverage(String coverage)
	{
	  	browser.selectDropdownList(".id","srch.coverage",coverage);
	}
	
	public void clickGo()
	{
	  	browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Go|Search", false));
	}
	
	/**
	 * This method used to search distribution by different criteria
	 * @param distrib-DistributionInfo
	 */
	public void setUpSearchCriteria(DistributionInfo distrib)
	{
	  	if(distrib.searchBy!=null&&!distrib.searchBy.equals("")){
		  	selectSearchBy(distrib.searchBy);
		  	if(distrib.searchBy.equalsIgnoreCase("Distribution Job ID")){
		  	  setSearchByValue(distrib.distributionJobId);
		  	}else if(distrib.searchBy.equalsIgnoreCase("Distribution User (Last Name)")){
		  	  setSearchByValue(distrib.distribUser);
		  	}
	  	}
	  	if(distrib.dateType!=null&&!distrib.dateType.equals("")){
	  	  	selectDateRange(distrib.dateType);
	  	  	setStartDate(distrib.startDate);
	  	  	setEndDate(distrib.endDate);
	  	}
	  	if(distrib.status!=null&&!distrib.status.equals("")){
	  	  	selectStatus(distrib.status);
	  	}
	  	if(distrib.coverage!=null&&!distrib.coverage.equals("")){
	  	  	selectCoverage(distrib.coverage);
	  	}
	  	this.clickGo();
	  	waitLoading();
	}
	
	/**
	 * The method used to search in progress distribution
	 */
	public void searchInProgressDistribution(){
		selectStatus("In Progress");
		this.clickGo();
	  	waitLoading();
	}
	
	public String getFirstDistributionId(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("\\d+",false));
		String id = "";
		if(objs.length>0){
			String temp = objs[0].text();
			if(temp.matches("\\d+")){
				id = temp;
			}
		}
		Browser.unregister(objs);
		return id;
	}
	
	public void selectFristDistributeJobRadio(){
		browser.selectRadioButton(".id","job_id");
	}
	/**
	 * This method used to click the first Distribution Job Link
	 *
	 */
	public void clickFirstDistributeJob()
	{
	  	browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("\\d+",false),true);
	}
	
	public int getDistirbuteJobIndex(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("\\d+",false));
		return objs.length;
	}
	public void clickLastDistributeJob(){
		int length = this.getDistirbuteJobIndex();
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("\\d+",false),false,length-1);
	}
	
	public void clickUndoDistribute(){
		browser.clickGuiObject(".class","Html.A",".text","Undo Distribution");
	}
}
