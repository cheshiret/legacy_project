/*
 * Created on Mar 1, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.PageNotFoundException;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrSearchLocationPage extends AdminManagerPage {
  
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrSearchLocationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrSearchLocationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrSearchLocationPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrSearchLocationPage();
		}

		return _instance;
	}

	/**Check the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SELECT",".id","showCategory");
	}
	
	public void setSearchValue(String value){
	   browser.setTextField(".id","locationName",value);
	}
	
	public void selectShow(String show){
	   browser.selectDropdownList(".id","assignment",show);
	}
	
	public void selectLocationCategory(String category){
	   browser.selectDropdownList(".id","showCategory",category);
	}
	
	public void clickSearch(){
	   browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	public void clickSelect(){
	  browser.clickGuiObject(".class","Html.A",".text","Select");
	}
	
	public void searchLocation(String value,String category){
	   setSearchValue(value);
	   selectLocationCategory(category);
	   clickSearch();
	   waitLoading();
	}

}
