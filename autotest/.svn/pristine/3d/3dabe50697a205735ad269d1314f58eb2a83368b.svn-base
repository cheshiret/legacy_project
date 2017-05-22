/*
 * Created on Jan 21, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;
import com.activenetwork.qa.awo.datacollection.legacy.orms.DistributionInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRunDistributionPage extends FinanceManagerPage{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRunDistributionPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRunDistributionPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRunDistributionPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrRunDistributionPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		//return browser.checkTestObjectExists(".class", "Html.A", ".href", new RegularExpression("\"RunDistribution.do\".*RunDistribution.*", false));
		return browser.checkHtmlObjectExists( ".id", "force_period_end");
	}
	
	public boolean checkCoverageExisting(){
		return browser.checkHtmlObjectExists(".id","run.coverage");
	}
	
	/**
	 * As run distribution need spend much time, hence maintain another wait method
	 *
	 */
	public void waitForExists()
	{
	  int count = 0;
		while (!checkCoverageExisting()) {
			Browser.sleep(1);
			if (count >= Browser.VERY_LONG_SLEEP) {
				throw new PageNotFoundException(
						"FinMgrRunDistributionPage is not found after " + count);
			}
			count++;
		}
	}
	
	public void selectCoverage(String coverage)
	{
	  	browser.selectDropdownList(".id","run.coverage",coverage);
	}
	
	public void setPeriodStartDate(String startDate)
	{
	  	browser.setTextField(".id","run.startDate_ForDisplay",startDate);
	}
	
	public void setPeriodEndDate(String endDate)
	{
	  	browser.setTextField(".id","run.endDate_ForDisplay",endDate);
	}
	
	public String setPeriodEndDateWithInvalidDate(String endDate) {	
		new Thread() {
			public void run() {            
				ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
				confirm.setDismissible(false);
				while (!confirm.exists()) {
					
				}
				confirm.waitLoading();
				TestProperty.putProperty("msg", confirm.text());
				confirm.dismiss();
			};
		}.start();
		
		browser.setTextField(".id","run.endDate_ForDisplay", endDate);
		refreshPage();
		String alertMsg = TestProperty.getProperty("msg");
		return alertMsg;
	}
	
	public void refreshPage(){
		browser.clickGuiObject(".class", "Html.LABEL",".text",new RegularExpression("Period End Date.*",false));
	}
	
	public void selectDistributionCode(String distributionCode){
		browser.selectDropdownList(".id", "config_code", distributionCode);
	} 
	
	public void clickForceEndRadio()
	{
	  	browser.selectCheckBox(".id","force_period_end");
	}
	
	public void clickRunDistribution()
	{
	  	//browser.clickGuiObject(".class","Html.A",".href","javascript:invokeAction(%20\"RunDistribution.do\",%20\"RunDistribution\",%20\"DistributionJobWorker\",%20\"\"%20%20)");
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"RunDistribution.do\".*RunDistribution.*", false));
	}
	
	/**
	 * This method use to enter all required entries and click run distribution button
	 * @param distr
	 */
	public void runDistribution(DistributionInfo distr,boolean isClickForceEndRadio){
		
        logger.info("Start to Run Distribution.");
	  	
	  	selectCoverage(distr.coverage);
	  	setPeriodStartDate(distr.startDate);
	  	setPeriodEndDate(distr.endDate);
	  	
	  	if(distr.distributionCode!=""){
	  		selectDistributionCode(distr.distributionCode);
	  	}
	  	
	  	if(isClickForceEndRadio){
		  	if(distr.endDate==null||distr.endDate.equals("")){
		  	  clickForceEndRadio();
		  	}else if(DateFunctions.compareDates(distr.endDate,DateFunctions.getToday())!=-1){
		  	  clickForceEndRadio();	//if period end date is today or future day, click force period date radio
		  	}
	  	}
	  	clickRunDistribution();
	  	this.waitLoading();
//	  	waitForExists();
	}
	/**
	 * This method use to enter all required entries and click run distribution button
	 * @param distr
	 */
	public void runDistribution(DistributionInfo distr)
	{
	  	runDistribution(distr, true);
	}
	
	public String getWarningMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".id",new RegularExpression("(distribution\\.invalid_(enddate|startdate)|distribution\\.failed)|(msg\\.distribution\\.job\\.((enddate|startdate|location)\\.required)|failed|(enddate\\.invalid\\.lttoday))",false));
		if(objs.length<1)
			return null;
		String errorMessage=objs[0].getProperty(".text").toString();
		
		Browser.unregister(objs);
		return errorMessage;
	}
	
	public String getSuccessMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".id",new RegularExpression("msg.distribution.job.success",false));
		if(objs.length<1)
			return null;
		String successMsg=objs[0].getProperty(".text").toString();
		
		Browser.unregister(objs);
		return successMsg;
	}
}
