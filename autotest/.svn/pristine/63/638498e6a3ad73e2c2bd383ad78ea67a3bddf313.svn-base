package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * Access this page by: Select 'Products' from top menu, and then click 'Consumables' link.
 * @author vzhao
 *
 */
public class LicMgrConsumableListPage extends LicMgrProductPage {
	private static LicMgrConsumableListPage instance = null;
	
	protected LicMgrConsumableListPage() {
	}

	public static LicMgrConsumableListPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrConsumableListPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add POS Product");
	}
	
	/**Click Add POS Product link*/
	public void clickAddPOSProduct() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add POS Product");
	}
	
	public String getConsumableId(String prodCode){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "consumableProductGrid");
		String id = "";
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find consumable list table with id=consumableProductGrid");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(2, prodCode);
		id=table.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}
	
	@Override
	public boolean checkProductRecordExist(String prodCode) {
		IHtmlObject[] objs=browser.getTableTestObject(".id", "consumableProductGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(2, prodCode);
		
		Browser.unregister(objs);
		
		return row > 0;
	}

	/**
	 * 
	 * Get consumable ID by consumable name
	 * 
	 * @param name
	 * @return
	 */
	public String getConsumableIdByName(String name){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "consumableProductGrid");
		String id = "";
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find consumable list table with id=consumableProductGrid");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(3, name);
		if(row<0){

			Browser.unregister(objs);
			// can't find consumable ID with given name.
			return null;
		}
		id=table.getCellValue(row, 0);
		Browser.unregister(objs);

		return id;
	}
	
	/**
	 * Click consumable product id link to goto consumable details page
	 * @param id
	 */
	public void clickConsumableIDLink(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
		ajax.waitLoading();
	}
	
	public void selectStatusCheckBox(String status){
		IHtmlObject[] labObjs = browser.getHtmlObject(".class", "Html.LABEL",".text",status);
		if(labObjs.length<1){
			throw new ItemNotFoundException(status+" label object not found.");
		}
		
		String value = labObjs[0].getProperty(".for");
		
		browser.selectCheckBox(".id", value);
		Browser.unregister(labObjs);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text","Go");
	}
	
	/**check if the Consumable which code is specified is existing */
	public boolean isConsumableExists(String code){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",code);
	}
}
