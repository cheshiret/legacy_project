/*
 * $Id: AdmMgrHomePage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author CGuo
 */
public class AdmMgrHomePage extends AdminManagerPage {

	/**
	 * Script Name   : InvMgrMainPage
	 * Generated     : Feb 9, 2005 4:43:53 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2005/02/09
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrHomePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrHomePage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",new RegularExpression("Go|Search",false));
	}
    
	public void searchBy(String searchItem){
	    browser.selectDropdownList(".id","SearchBar_SelectIndexFollows_0_like",searchItem);
	}
	
	public void setSearchValue(String value){
	    browser.setTextField(".id","SearchBar_InputIndexFollows_0_like",value);
	}
	
	public void selectShow(String show){
	    browser.selectDropdownList(".id","SearchBar_SelectFieldNameFollows_active_equal",show);
	}
	
	public void clickAddNewUser(){
	    browser.clickGuiObject(".class","Html.A",".text","Add New User");
	}
	
	public void clickUser(){
	   browser.clickGuiObject(".class","Html.A",".text","Users");
	}
	
    public void clickLocations(){
       browser.clickGuiObject(".class","Html.A",".text","Locations");
    }
    
    public void clickRole(){
       browser.clickGuiObject(".class","Html.A",".text","Roles");
    }
    
    /**Select the contract Contract - Rules*/
	public void selectContractRules() {
		selectPageName("Contract - Rules");
	}
	
	public void waitExists(int sleep_time){//as for selenium,the quick response caused check object exist happened before click event,so add sleep time here
		Browser.sleep(sleep_time);
		this.waitLoading();
	}
	
	/**
	 * Select the catalog from the dropdown list
	 * @param pageName -- the catalog will be selected
	 */
	public void selectPageName(String pageName) {
		browser.selectDropdownList(".id", "CatalogDropDown", pageName);
	}
	
	public void clickGo(){
	   browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Go|Search",false));
	}
	
	public void selectAll(){
	   browser.selectCheckBox(".class","Html.INPUT.checkbox",".name","all_slct");
	}
	
	public void clickActivate(){
	   browser.clickGuiObject(".class","Html.A",".text","Activate");
	}
	
	public void clickDeactivate(){
	   browser.clickGuiObject(".class","Html.A",".text","Deactivate");
	}
	
	public void clickLock(){
	   browser.clickGuiObject(".class","Html.A",".text","Lock");
	}
	
	public void clickUnlock(){
	   browser.clickGuiObject(".class","Html.A",".text","Unlock");
	}
	
	public AdminUserInfo getUser(String userName){
		AdminUserInfo user=new AdminUserInfo();
		IHtmlObject[] objs=browser.getTableTestObject(".class", "Html.TABLE");
		int index=0;
		for(int i=0;i<objs.length;i++){
			if(objs[i].getProperty(".text").matches("^Active Locked User Name First Name.*")){
				index=i;
				break;
			}
			if(i==objs.length-1){
				throw new ObjectNotFoundException("User Information table is not found !");
			}
		}
	    
	    IHtmlTable table=(IHtmlTable)objs[index];
	    int row=table.findRow(4, userName);
	    user.activeStatus=table.getCellValue(row, 2).trim().equals("Active")?true:false;
	    user.userName=table.getCellValue(row, 4).trim();
	    Browser.unregister(objs);
	    return user;
	}
	
	public String getUserStatus(){
	   String status = "";
	   IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Active Locked User Name First Name Last Name Locations Roles.*",false));
	   IHtmlTable table = (IHtmlTable)objs[objs.length-1];
	   if(table.rowCount()>0){
		   status = table.getCellValue(1, 2);
	   }
	   Browser.unregister(objs);
	   
	   return status;
	}
	
	public boolean checkUserExist(String userName){
	   return browser.checkHtmlObjectExists(".class","Html.A",".text",userName);
	}
	
	public void clickUser(String userName){
	   browser.clickGuiObject(".class","Html.A",".text",userName);
	}
	
	public void clickSignOut(){
		browser.clickGuiObject(".id","admin.menu.id.4",".text","Sign out");
	}
	
	public String getErrorMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "paymentrefund.search.notfound");
		if(objs.length<1) {
			logger.info("Could not get any error message");
			return null;
		}
		String msg = objs[0].text();
		Browser.unregister(objs);
		return msg;
	}
}
