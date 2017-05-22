/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DailyEFTJobInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  July 18, 2012
 */
public class FinMgrDailyEFTJobsPage extends FinanceManagerPage {
	
	private static FinMgrDailyEFTJobsPage _instance = null;
	
	protected FinMgrDailyEFTJobsPage() {}
	
	public static FinMgrDailyEFTJobsPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrDailyEFTJobsPage();
		}
		
		return _instance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","dailyEFTJobList_LIST");
	}
	
	
	public void selectSearchType(String type){
		//DailyEFTJobSearchCriteria-1201144299.searchTypeID
		browser.selectDropdownList(".id", new RegularExpression("DailyEFTJobSearchCriteria-\\d+\\.searchTypeID",false), type);
	}
	

	public void setSearchValue(String value){
		//DailyEFTJobSearchCriteria-1285951493.searchValue
		browser.setTextField(".id", new RegularExpression("DailyEFTJobSearchCriteria-\\d+\\.searchValue",false), value);
	}
	
	public void selectSearchDate(String date){
		//DailyEFTJobSearchCriteria-711319443.searchDateID
		browser.selectDropdownList(".id", new RegularExpression("DailyEFTJobSearchCriteria-\\d+\\.searchDateID",false), date);
	}
	
	
	public void setFrom(String date){
		//DailyEFTJobSearchCriteria-104415652.fromDate_ForDisplay
		browser.setTextField(".id", new RegularExpression("DailyEFTJobSearchCriteria-\\d+\\.fromDate_ForDisplay",false), date);
	}
	
	public void setTo(String date){
		//DailyEFTJobSearchCriteria-419723565.toDate_ForDisplay
		browser.setTextField(".id", new RegularExpression("DailyEFTJobSearchCriteria-\\d+\\.toDate_ForDisplay",false), date);
	}
	
	public void selectStatus(String status){
		//DailyEFTJobSearchCriteria-798143362.dailyJobStatusID
		browser.selectDropdownList(".id", new RegularExpression("DailyEFTJobSearchCriteria-\\d+\\.dailyJobStatusID",false), status);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	
	public void setSearchData(DailyEFTJobInfo info){
		if(!StringUtil.EMPTY.equals(info.getSearchType())){
			this.selectSearchType(info.getSearchType());
			if(!StringUtil.EMPTY.equals(info.getSearchValue())){
				this.setSearchValue(info.getSearchValue());
			}else{
				throw new ActionFailedException("Please set the search value...");
			}
		}
		
		if(!StringUtil.EMPTY.equals(info.getSearchDate())){
			this.selectSearchDate(info.getSearchDate());
			if(StringUtil.EMPTY.equals(info.getFromDate())&& StringUtil.EMPTY.equals(info.getToDate())){
				throw new ActionFailedException("Please set the date value...");
			}
			if(!StringUtil.EMPTY.equals(info.getFromDate())){
				this.setFrom(info.getFromDate());
			}
			if(!StringUtil.EMPTY.equals(info.getToDate())){
				this.setTo(info.getToDate());
			}
		}
		
		if(!StringUtil.EMPTY.equals(info.getStatus())){
			this.selectStatus(info.getStatus());
		}
		
	
	}
	
	public void gotoFirstDailyEFTJobsDetailsPg() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","dailyEFTJobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",new RegularExpression("\\d+",false), objs[0]);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}

	public void gotoDetailPageByID(String id){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","dailyEFTJobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",id);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}
	
	}
