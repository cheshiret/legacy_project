package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

public class LicMgrProductConfigurationReportCategoriesDetailPage extends LicMgrTopMenuPage{
	private static LicMgrProductConfigurationReportCategoriesDetailPage _instance = null;
	
	protected void LicMgrProductConfigurationQuestionsDetailPage() {}
	
	public static LicMgrProductConfigurationReportCategoriesDetailPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrProductConfigurationReportCategoriesDetailPage();
		}
		
		return _instance;
	}
	 
	/**
	 * Get Id by description
	 * @param description
	 * @return id
	 */
	public String getReportCategoryIdByDescription(String description){
		int toRecord = 0;
		int count = 0;
		String record = "";
		
		IHtmlObject [] objs = browser.getTableTestObject(".id", "displaycategorylist");
		IHtmlTable table = (IHtmlTable) objs[0];
		for(int row = 0; row<table.rowCount();row++){
			if(!table.getCellValue(row, 1).equalsIgnoreCase(description)){
				count ++;
				toRecord = row;
				break;
			}
		}
		
		if(count ==0){
			throw new ItemNotFoundException("The given description type cannot be found !");
		}
		
		record = table.getCellValue(toRecord, 0);
		Browser.unregister(objs);
		return record;
	}
	
	public void clickId(String id){
		browser.clickGuiObject(".class","Html.A",".text", id);
	}
}
