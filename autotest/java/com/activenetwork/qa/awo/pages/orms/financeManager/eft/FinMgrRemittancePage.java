/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTRemittanceInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 3, 2012
 */
public class FinMgrRemittancePage extends FinanceManagerPage {
	
	private static FinMgrRemittancePage _instance = null;
	
	protected FinMgrRemittancePage() {}
	
	public static FinMgrRemittancePage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrRemittancePage();
		}
		
		return _instance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","eftremittancejobList_LIST");
	}
	
	
	public void selectSearchType(String type){
		//EFTRemittanceSearchCriteria-944585264.searchType
		browser.selectDropdownList(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.searchType",false), type);
	}
	

	public void setSearchValue(String value){
		//EFTRemittanceSearchCriteria-1445413424.searchValue
		browser.setTextField(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.searchValue",false), value);
	}
	
	public void selectSearchDate(String date){
		//EFTRemittanceSearchCriteria-728402304.searchDate
		browser.selectDropdownList(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.searchDate",false), date);
	}
	
	
	public void setFrom(String date){
		//EFTRemittanceSearchCriteria-1600735428.dateFrom_ForDisplay
		browser.setTextField(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.dateFrom_ForDisplay",false), date);
	}
	
	public void setTo(String date){
		//EFTRemittanceSearchCriteria-2032692427.dateTo_ForDisplay
		browser.setTextField(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.dateTo_ForDisplay",false), date);
	}
	
	public void selectStatus(String status){
		//EFTRemittanceSearchCriteria-1767259668.status
		browser.selectDropdownList(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.status",false), status);
	}
	
	

	public void selectTransStatus(String status){
		//EFTRemittanceSearchCriteria-486918419.remitTransStatus
		browser.selectDropdownList(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.remitTransStatus",false), status);
	}
	
	public void selectAdjustType(String type){
		//EFTRemittanceSearchCriteria-1725173063.adjustType
		browser.selectDropdownList(".id", new RegularExpression("EFTRemittanceSearchCriteria-\\d+\\.adjustType",false), type);
	}
	
	
	
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	
	public void searchRemittanceData(EFTRemittanceInfo info){
		if(!StringUtil.isEmpty(info.getSearchType())){
			this.selectSearchType(info.getSearchType());
			if(!StringUtil.isEmpty(info.getSearchValue())){
				this.setSearchValue(info.getSearchValue());
			}else{
				throw new ActionFailedException("Please set the search value...");
			}
		}
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
			
	}
	
	public void gotoFirstDailyEFTJobsDetailsPg() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","eftremittancejobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",new RegularExpression("\\d+",false), objs[0]);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}

	public void gotoDetailPageByID(String id){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","eftremittancejobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",id);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}
	
	}
