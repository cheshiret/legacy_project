/*
 * Created on Mar 1, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrSchedulerJobPage extends ResourceManagerPage{
  
  	static private ResMgrSchedulerJobPage _instance = null;
  	
  	private ResMgrSchedulerJobPage(){}
  	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	static public ResMgrSchedulerJobPage getInstance() {
		if (null == _instance) {
			_instance = new ResMgrSchedulerJobPage();
		}

		return _instance;
	}
	
	/** Determine if the Resource Manager Scheduler Detail Page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Schedule ID Job ID Job Name.*",false));
	}
	
	public void selectSearchType(String searchType)
	{
	  	browser.selectDropdownList(".id","SearchType",searchType);
	}
	
	public void setSearchValue(String searchValue)
	{
	  	browser.setTextField(".id","SearchTypeValue",searchValue);
	}
	
	public void clickGo()
	{
	  	browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("^(Go|Search)$", false));
	}
	
	public void clickSchedulesTab()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Schedules");
	}
	
	public void searchByScheduleId(String scheduleId)
	{
	  	this.selectSearchType("ScheduleID");
	  	this.setSearchValue(scheduleId);
	  	clickGo();
	  	waitLoading();
	}
	
	/**
	 * This method used to check given scheduler exists in the search list
	 * @param scheduleId
	 */
	public boolean checkSchedulerExists(String scheduleID)
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Schedule ID Job ID Job Name.*",false));
	  	boolean found = false;
	  	int rowCount=((IHtmlTable)objs[0]).rowCount();
	  	for(int i=1;i<rowCount;i++){
	  		if(((IHtmlTable)objs[0]).getCellValue(i,1).equals(scheduleID)){
	  			logger.info("Found Given Schedule "+scheduleID);
	  			found = true;
	  			break;
	  		}
	  	}
	  	Browser.unregister(objs);
	  	
	  	return found;
	}
	
	/**
	 * This method used to check given schedule in Schedule Job list
	 * @param scheduleId
	 */
	public void checkSchedulerIsRun(String scheduleId)
	{
		boolean found = false;
		int count = 0;
		while(!found){
			//search and verify schedule display in job list
			searchByScheduleId(scheduleId);
			found = checkSchedulerExists(scheduleId);
			Browser.sleep(1); 
			if(count>OrmsConstants.SCHEDULER_WAIT_TIME){
				throw new ItemNotFoundException("Can not found schedule "+scheduleId+" in "+count+"Seconds.");
			}
			count++;
		}
	}
}
