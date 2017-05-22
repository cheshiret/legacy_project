/*
 * Created on Feb 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrServicePage extends AdminManagerPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrServicePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrServicePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrServicePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrServicePage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Add New Service-Activity");
	}
	
	public void searchBy(String service){
	   browser.selectDropdownList(".id","srchBy",service);
	}
	
	public void selectCategory(String category){
	   browser.selectDropdownList(".id","srchByCatg",category);
	}
	
	public void setService(String service){
	   browser.setTextField(".id","srchByText",service);
	}
	
	public void clickSearch(){
	   browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	public void addNewService(){
	   browser.clickGuiObject(".class", "Html.A", ".text","Add New Service-Activity");
	}
	
	public void clickEventID(String eventID){
	  browser.clickGuiObject(".class","Html.A",".text",eventID);
	}
	
	public void searchService(String searchBy,String service){
	   searchBy(searchBy);
	   setService(service);
	   
	   clickSearch();
	   waitLoading();
	}
	
	public String serviceInfo(){
	   String serviceInfo = "";
	   IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^ID Category Type Name Icon # of Locations.*",false));
	   IHtmlTable table = (IHtmlTable)objs[0];
	   if(table.rowCount()>0){
	       serviceInfo = table.getCellValue(1,1)+" "+table.getCellValue(1,2)+" "+table.getCellValue(1,3)+" "+
	              table.getCellValue(1,4)+table.getCellValue(1,5)+" "+table.getCellValue(1,6);
	   }
	   Browser.unregister(objs);
	   
	   return serviceInfo;
	}
	
	public void selectServiceCheckbox(){
	   browser.selectCheckBox(".id","row_0_checkbox");
	}
	
	public void clickDelete(){
	   browser.clickGuiObject(".class","Html.A",".text","Delete");
	}
}
