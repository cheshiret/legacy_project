/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date Mar 26, 2012
 */
public class AdmMgrUserLocationPage extends AdmMgrUserCommonTabPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrUserLocationPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrUserLocationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrUserLocationPage getInstance() {
		if (null == _instance) {
			_instance = new AdmMgrUserLocationPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^Assigned Location name Level Region State.*",false));
	}

	public void searchLocation(String category) {
		browser.selectDropdownList(".id",
				"SearchBar_SelectIndexFollows_0_like", category);
	}

	public void setLocationValue(String value) {
		// browser.setTextField(".id","SearchBar_InputIndexFollows_0_like",value);
		browser.setTextField(".id",
				"SearchBar_InputIndexFollows_0_likeignorecase", value, true);
	}

	public void selectLocation() {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id",
				new RegularExpression("ConfigurableCheckBox_.*", false));
	}

	public void selectLocationShow(String show) {
		if (show != null && show.length() > 0) {
			browser.selectDropdownList(".id",
					"SearchBar_SelectFieldNameFollows_assigned", 0);
		}
		browser.selectDropdownList(".id",
				"SearchBar_SelectFieldNameFollows_assigned", show);
	}
	
	public void selectLocationLevel(String level){
		browser.selectDropdownList(".id", "SearchBar_SelectFieldNameFollows_level_equal", level);
	}
	
	
	public String getAssignedLocation(){
	   String assignedLocation = "";
	   IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Assigned Location name Level Region State.*",false));
	   IHtmlTable table = (IHtmlTable)objs[0];
	   if(table.rowCount()>0){
		   assignedLocation = table.getProperty(".text").toString();//table.getCellValue(1,2)+" "+table.getCellValue(1,3)+ " "+table.getCellValue(1,4)+ " "+table.getCellValue(1,5)+ " "+table.getCellValue(1,6);
	   }
	   Browser.unregister(objs);
	   
	   return assignedLocation;
	}
	
	public void clickAssign(){
	    browser.clickGuiObject(".class","Html.A",".text","Assign");
	}
	
	public void clickUnAssign(){
	    browser.clickGuiObject(".class","Html.A",".text","Un-Assign");
	}
	
	public void clickGo(){
		   browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	private IHtmlObject[] getTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".class","Html.TABLE",".text",new RegularExpression("^Assigned Location name Level Region State.*",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find User Location table object.");
		}
		
		return objs;
	}
	
	public String getAssignStatus(String location) {
		IHtmlObject objs[] = getTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int rowIndex = table.findRow(3, location);
		String assignedStatus = table.getCellValue(rowIndex, 2).trim();
		Browser.unregister(objs);
		
		return assignedStatus;
	}
	
	public boolean isLocationAssigned(String location) {
		return getAssignStatus(location).equals(OrmsConstants.YES_STATUS);
	}
}
