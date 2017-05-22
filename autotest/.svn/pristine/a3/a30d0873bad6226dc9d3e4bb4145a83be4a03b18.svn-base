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
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrRoleDetailsPage extends AdminManagerPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrRoleDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrRoleDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrRoleDetailsPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrRoleDetailsPage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
//		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".className", new RegularExpression("tabanchor(|_active)", false), ".text", "Role Details"));  //Shane:for 3.05 build, no .classname attribute for 'Role Details' link
		// below updated by Nicole:
		// Delete Role button only exist on this page when edit role, and if add new role, this button doesn't exist!
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A",  ".text", "Delete Role")) ||
				browser.checkHtmlObjectExists(".class", "Html.A", ".text",  new RegularExpression("Role Details", false));// Role Details sub page name
	}
	
	public void setRoleName(String roleName){
	    browser.setTextField(".id","oFormView_name",roleName);
	}
	
	public void setDescription(String description){
	    browser.setTextArea(".id","oFormView_description",description);
	}
	
	public void clickOK(){
	    browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
	    browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void searchApplicationCategory(String app){
	   browser.selectDropdownList(".id","SearchBar_SelectIndexFollows_0_like",app);
	}
	
	public void searchApplication(String category,String value){
	   this.searchApplicationCategory(category);
	   this.setApplication(value);
	   clickGO();
	   this.waitLoading();
	}
	
	public void setApplication(String app){
	   browser.setTextField(".id","SearchBar_InputIndexFollows_0_like",app);
	}
	
	public void clickGO(){
	   browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	public void clickAssign(){
	   browser.clickGuiObject(".class","Html.A",".text","Assign");
	}
	
	public void clickDeleteRole(){
	   browser.clickGuiObject(".class","Html.A",".text","Delete Role");
	}
	
	public void clickUnAssign(){
	   browser.clickGuiObject(".class","Html.A",".text","Un-Assign");
	}
	
	public void selectFeature(String feature){
	   browser.selectDropdownList(".id","SearchBar_SelectIndexFollows_0_like",feature);
	}
	
	public void setFeature(String feature){
	   browser.setTextField(".id","SearchBar_InputIndexFollows_0_like",feature);
	}
	
	public void selectFeatureApplication(String application){
	   browser.selectDropdownList(".id","SearchBar_SelectFieldNameFollows_application_equal",application);
	}
	
	public void searchFeature(String feature,String application){
	   this.selectFeature("Feature");
	   this.setFeature(feature);
	   this.selectFeatureApplication(application);
	   this.clickGO();
	   
	   this.waitLoading();
	}
	
	public void addRoleApplication(String application){
	   searchApplicationCategory("Application");
	   setApplication(application);
	   clickGO();
	   this.waitLoading();
	   selectAll();
	   clickAssign();
	   this.waitLoading();
	}
	
	public void addRoleFeature(String feature,String application){
	   selectFeature("Feature");
	   setFeature(feature);
	   selectFeatureApplication(application);
	   clickGO();
	   this.waitLoading();
	   selectAll();
	   clickAssign();
	   this.waitLoading();
	}
	
	public void selectAll(){
	   browser.selectCheckBox(".name","all_slct");
	}
	
	public void clickRoleApplication(){
	   browser.clickGuiObject(".class","Html.A",".text","Role's Applications");
	}
	
	public void clickRoleFeature(){
	   browser.clickGuiObject(".class","Html.A",".text","Role's Features");
	}
	
	public void clickRoleDetails(){
	   browser.clickGuiObject(".class","Html.A",".text","Role Details");
	}
	
	public String getApplicationStatus(){
	   String status = "";
	   IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Assigned Application Description",false));
	   IHtmlTable table = (IHtmlTable)objs[0];
	   if(table.rowCount()>0){
		   status = table.getCellValue(1, 2);
	   }
	   
	   return status;
	}
	
	public String getFeatureStatus(){
		String status = "";
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Assigned Application Feature Name Description",false));
	    IHtmlTable table = (IHtmlTable)objs[0];
	    if(table.rowCount()>0){
	    	status = table.getCellValue(1, 2);
	    }
	  
	    Browser.unregister(objs);
	    return status;
	}
	
}
