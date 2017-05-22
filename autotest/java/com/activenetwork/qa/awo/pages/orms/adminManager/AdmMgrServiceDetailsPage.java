/*
 * Created on Feb 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.awo.datacollection.legacy.ServiceAndActivity;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrServiceDetailsPage extends AdminManagerPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrServiceDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrServiceDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrServiceDetailsPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrServiceDetailsPage();
		}

		return _instance;
	}

	/**Check the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","datg");
	}
	
	public void selectServiceCategory(String category){
	    browser.selectDropdownList(".id","datg",category);
	}
	
	public void selelctServiceType(String type){
	    browser.selectDropdownList(".id","type",type);
	}
	
	public void selectIcon(String icon){
	    browser.selectDropdownList(".id","icon",icon);
	}
	
	public void setName(String name){
	    browser.setTextField(".id","inpName",name);
	}
	
	public void setDescription(String description){
	    browser.setTextArea(".id","dscr",description);
	}
	
	public void clickOK(){
	    browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
	    browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void clickApply(){
	    browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void setServiceInfo(ServiceAndActivity sa){
	   selectServiceCategory(sa.category);
	   selelctServiceType(sa.type);
	   setName(sa.name);
	   setDescription(sa.description);
	   selectIcon(sa.icon);
	   
	}
	
	public boolean checkObjEnable(String objID){
	   IHtmlObject[] obj=browser.getHtmlObject(".id",objID);
	   boolean enable=false;
	   if(obj[0].getProperty(".disabled").toString().equalsIgnoreCase("true")){
	      enable=true;
	   }
	   
	   return enable;
	}

}
