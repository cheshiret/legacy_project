package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCustomersPrivilegeOrderSearchPage extends LicMgrCommonTopMenuPage {
	private static LicMgrCustomersPrivilegeOrderSearchPage _instance = null;

	protected LicMgrCustomersPrivilegeOrderSearchPage() {

	}

	public static LicMgrCustomersPrivilegeOrderSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrCustomersPrivilegeOrderSearchPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".text","Privileges:Privilege Search/List")
				|| browser.checkHtmlObjectExists(".id", new RegularExpression("searchprivilegeinstance.SearchComponent", false));
	}

//	/**Get valid from and to date*/
//	public List<String> getValidFromToDate(String priviNum){
//		HtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".id","privilegeInstanceList_LIST");
//		List<String> list = new ArrayList<String>();
//		String [] priviNums = null;
//		int count = 0;
//		int toRecord = 0;
//		
//		ITable table = (ITable) objs[0];
//		
//		for(int i=1;i<table.rowCount();i++){
//			if(table.getCellValue(i, 0).equalsIgnoreCase(priviNum)){
//				count++;
//				toRecord = i;
//				break;
//			}
//		}
//		if(count ==0){
//				throw new ItemNotFoundException("The given privilege number cannot be found!");
//		}
//		
//		priviNums= table.getCellValue(toRecord, 5).split(" ");
//		String validFromDate = priviNums[0]+" "+priviNums[1]+" "+priviNums[2]+" "+priviNums[3];
//		String validToDate = priviNums[4]+" "+priviNums[5]+" "+priviNums[6]+" "+priviNums[7];
//		list.add(validFromDate);
//		list.add(validToDate);
//		Browser.unregister(objs);
//		
//		return list;
//	}
	
	/**Get valid from and to date*/
	public Map<String, List<String>> getValidFromToDate(String priviOrderNum){
		IHtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".id","privilegeInstanceList_LIST");
		Map<String, List<String>> fromToDates = new HashMap<String, List<String>>();
		String [] dates = null;
		int count = 0;
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		for(int i=1;i<table.rowCount();i++){
			count++;
			String priviNum = table.getCellValue(i, 0);
			List<String> list = new ArrayList<String>();
//			map.put(, table.getCellValue(i,5));
			dates= table.getCellValue(i, 6).split(" ");
			String validFromDate = dates[0]+" "+dates[1]+" "+dates[2]+" "+dates[3];
			String validToDate = dates[4]+" "+dates[5]+" "+dates[6]+" "+dates[7];
			list.add(validFromDate);
			list.add(validToDate);
			fromToDates.put(priviNum, list);
		}
		if(count ==0){
				throw new ItemNotFoundException("The given privilege number cannot be found!");
		}
		
		Browser.unregister(objs);
		return fromToDates;
	}
	
	/**
	 * Set privilege order number
	 * @param priviOrderNum
	 */
	public void setPriviOrder(String priviOrderNum){
		browser.setTextField(".id",new RegularExpression("PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.searchTypeValue",false),priviOrderNum, false);
	}
	
	/**Click search button*/
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	
	/**Search privilege by order number*/
	public void searchPrivilegeOrder(String priviOrderNum){
		this.setPriviOrder(priviOrderNum);
		this.clickSearch();
		ajax.waitLoading();
	}
}
