package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;

import java.util.List;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

public class LicMgrOfficerBadgesAssignPage extends LicMgrOfficerDetailPage{
	private static LicMgrOfficerBadgesAssignPage _instance = null;
	
	protected LicMgrOfficerBadgesAssignPage (){}
	
	public static LicMgrOfficerBadgesAssignPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrOfficerBadgesAssignPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add/Change Badge Assignment");
	}
	
	public void clickAddOrChangeBadgeAssignment(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add/Change Badge Assignment", true);
	}
	
	/**
	 * The method used to get specific row values
	 * @param identifier
	 *            ----one cell value in your expected row
	 * @return
	 */
	public List<String> getRowInfoByBadgeNum(String identifier) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "officerAssignment");
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(3, identifier);
		if(row < 0){
			throw new ObjectNotFoundException("The row which badge# " + identifier + "is not found under colum:3 ");
		}
		List<String> values = table.getRowValues(row);
		Browser.unregister(objs);
		return values;
	}
}
