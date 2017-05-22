/*
 * Created on Mar 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
public class AdmMgrRoleFeaturePage extends AdminManagerPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrRoleFeaturePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrRoleFeaturePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrRoleFeaturePage getInstance() {
		if (null == _instance) {
			_instance = new AdmMgrRoleFeaturePage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",new RegularExpression("^Assigned Application Feature Name.*",false));
	}
	
	public void clickGO(){
		   browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Go|Search",false));
	}
	
    public void clickRole(){
        browser.clickGuiObject(".class","Html.A",".text","Roles");
     }
	
	public void clickAssign(){
	   browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Assign$",false));
	}
	
	public void clickUnAssign(){
	   browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Un-Assign",false));
	}
	
	public void selectSearchType(String feature){
		   browser.selectDropdownList(".id","SearchBar_SelectIndexFollows_0_like",feature);
	}
	
	public void selectShowType(String showType)
	{
		browser.selectDropdownList(".id","SearchBar_SelectFieldNameFollows_assigned",showType);
	}
		
	public void setFeature(String feature){
	   browser.setTextField(".id","SearchBar_InputIndexFollows_0_like",feature);
	}
	
	public void selectFeatureApplication(String application){
	   browser.selectDropdownList(".id","SearchBar_SelectFieldNameFollows_application_equal",application);
	}
	
	public void selectFirstFeature()
	{
		browser.selectCheckBox(".id",new RegularExpression("ConfigurableCheckBox_\\d",false));
	}
	
	public void selectAllFeature(){
		browser.selectCheckBox(".name","all_slct",".value","all");
	}
	
	public void searchFeature(String feature,String showType, String application){
	   this.selectSearchType("Feature");
	   if(feature!=null&&!feature.equals("")){
	   	this.setFeature(feature);
	   }
	   if(showType!=null&&!showType.equals("")){
	   	this.selectShowType(showType);
	   }
	   if(application!=null&&!application.equals("")){
	   	this.selectFeatureApplication(application);
	   }
	   this.clickGO();
	   
	   this.waitLoading();
	}
	
	public String getFeatureDescription(){
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Assigned Application Feature Name Description",false));

	    IHtmlTable tableGrid =(IHtmlTable)objs[0];
	    String desc = "";
	    if(tableGrid.rowCount()>1){
	    	desc = tableGrid.getCellValue(1,5);
	    }else{
	    	throw new ItemNotFoundException("No Feature Found.");
	    }

	    Browser.unregister(objs);
	    return desc;
	}
	
	/** get the assigned statue of any role's feature */
	public String getAssignedStatus(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Assigned Application Feature Name Description",false));
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	    String assigned = "";
	    if(tableGrid.rowCount()>1){
	    	assigned = tableGrid.getCellValue(1,2);
	    }else{
	    	throw new ItemNotFoundException("No Assigned Found.");
	    }
	    
	    Browser.unregister(objs);
	    return assigned;
	}
	
	/**  get the number of role's features */
	public int getRoleFeaturesNumber(){
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("ConfigurableCheckBox_.*",false));
		Browser.unregister(objs);
		return objs.length;
	}
	
	/**  click Next button */
	public void clickNext(){
		browser.clickGuiObject(".text","Next");
	}

	/**
	 * This method used to assign or unassign role feature depend on given parameter
	 * @param feature
	 * @param application
	 * @param isassign
	 */
	public void assignOrUnassignFeature(String feature,String application,boolean isassign){
		this.searchFeature(feature, "*All features", application);
		if(getRoleFeaturesNumber()<1){
			throw new ItemNotFoundException("Not Found Given Feature.");
		}
		this.selectFirstFeature();
		if(isassign){
			clickAssign();
		}else{
			clickUnAssign();
		}
		waitLoading();
	}
	
	/**
	 * Check whether gotonext button exist, if exit,click it.
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);
		this.waitLoading();
		return toReturn;
	}
	
	public List<RoleInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<RoleInfo> records = new ArrayList<RoleInfo>();
		int rows;
		int columns;
		RoleInfo feature;
		
		
		do{
			objs = browser.getTableTestObject(".text", new RegularExpression("Assigned Application Feature Name.*",false));
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't roles feature table object.");
					}
			
			table = (IHtmlTable)objs[1];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page AdmMgrRoleFeaturePage, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				feature = new RoleInfo();
				feature.feature = (table.getCellValue(i, table.findColumn(0, "Feature Name")));
				feature.description = (table.getCellValue(i, table.findColumn(0, "Description")));
				feature.application = (table.getCellValue(i, table.findColumn(0, "Application")));
			
				records.add(feature);			
			}

		}while(gotoNext());
		
		Browser.unregister(objs);
		
		return records;
	}
    
	

	
}
