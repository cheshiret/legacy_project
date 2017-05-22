package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrInvTypeLicenseYearChangeHistoryPage extends LicMgrTopMenuPage {
	
	private static LicMgrInvTypeLicenseYearChangeHistoryPage _instance = null;
	
	protected LicMgrInvTypeLicenseYearChangeHistoryPage(){
		
	}
	
	public static LicMgrInvTypeLicenseYearChangeHistoryPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrInvTypeLicenseYearChangeHistoryPage();
		}
		return _instance;
	}
	
	public boolean exists() {

		return browser.checkHtmlObjectExists(".id", "auditlog_LIST");
	}
	
	public String getInventoryType(){
		/*List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^Inventory Type.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		HtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found inventory type span object.");
		}
		
		String value = objs[1].getProperty(".text");
		Browser.unregister(objs);*/
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SPAN", ".text", new RegularExpression("^Inventory Type.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
//		IHtmlObject[] spanObjs = browser.getHtmlObject(".class","Html.SPAN", objs[2]);
//		if(spanObjs.length<1){
//			throw new ErrorOnPageException("No span element exist");
//		}
		String value = objs[2].getProperty(".text");// It is Inventory Types tab when index is 0
		Browser.unregister(objs);
//		Browser.unregister(spanObjs);
		return value.replaceAll("Inventory Type", StringUtil.EMPTY);		
	}
	
	public void clickReturn(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return");
	}
	
	public String getLicneseYear(){
		List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^License Year.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		IHtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found License Year span object.");
		}
		
		String value = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return value.replaceAll("License Year", StringUtil.EMPTY);		
	}
	

	public List<ChangeHistory> getHistoriesInformation() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "auditlog_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find any change history.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowCount = table.rowCount();
		for (int i = 1; i < rowCount; i++) {
			ChangeHistory history = new ChangeHistory();
			history.changeDate = table.getCellValue(i, 0);
			history.object = table.getCellValue(i, 1);
			history.action = table.getCellValue(i, 2);
			history.field = table.getCellValue(i, 3);
			history.oldValue = table.getCellValue(i, 4);
			history.newValue = table.getCellValue(i, 5);
			history.user = table.getCellValue(i, 6);
			history.location = table.getCellValue(i, 7);
			list.add(history);
		}
		Browser.unregister(objs);
		return list;
	}		

}
