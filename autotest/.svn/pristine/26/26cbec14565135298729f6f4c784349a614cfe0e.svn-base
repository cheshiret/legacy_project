package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Sara Wang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrLoopAreasPage extends InventoryManagerPage{
	
	private static InvMgrLoopAreasPage _instance = null;

	public static InvMgrLoopAreasPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrLoopAreasPage();
		}

		return _instance;
	}

	protected InvMgrLoopAreasPage() {

	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Add New Loop/Area");
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", 
				new RegularExpression("^Loop/Area Name Parent Sites Description.*",false));
	}

	/**click sites tab*/
	public void clickSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sites");
	}
	
	/**click Non-Site-Specific Groups tab*/
	public void clickNonSpecSiteGroup(){
	  browser.clickGuiObject(".class", "Html.A", ".text", "Non-Site-Specific Groups");
	}

	/**
	 * Click look or area site number
	 * @param loopAreaID
	 */
	public void clickLoopAreaSiteNum(String loopAreaID){
		RegularExpression rex = new RegularExpression("\"LoopSites\".+\"" + loopAreaID + "\"", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}

	/** Click on Add New Loop/Area link. */
	public void clickAddNewLoopArea() {
		browser.clickGuiObject(".class", "Html.A", ".text",
						"Add New Loop/Area");
	}

	/**
	 * Go to special loop's details page by given loop name.
	 * @param loopOrAreaName
	 */
	public void clickLoopLink(String loopOrAreaName) {
		browser.clickGuiObject(".class", "Html.A", ".text", loopOrAreaName);
	}
	
	/**
	 * Check if site tab exist
	 * @return
	 */
	public boolean isSiteTabExist(){
	   return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Sites");
	}
	
	/**
	 * check if NSS tab exist
	 * @return
	 */
	public boolean isNSSTabExist(){
	  return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Non-Site-Specific Groups");
	}
	
	/**
	 * Select Loop/Area check box via  Loop/Area ids 
	 * @param LoopOrAreaIDs
	 */
	public void selectLoopOrAreaCheckBox(String LoopOrAreaIDs){
		  browser.selectCheckBox(".class","Html.INPUT.checkbox",".value",LoopOrAreaIDs);
		}
	
	/**
	 * Select Loop/Area box check boxes via Loop/Area ids 
	 * @param LoopOrAreaIDs
	 */
	public void selectLoopOrAreaCheckBoxs(String[] LoopOrAreaIDs){
		if(null ==LoopOrAreaIDs || LoopOrAreaIDs.length<0){
			return;
		}else{
			for(int i=0; i<LoopOrAreaIDs.length; i++){
				selectLoopOrAreaCheckBox(LoopOrAreaIDs[i]);
			}
		}
	}
	
	/** Click delete button */
	public void clickDelete(){
		browser.clickGuiObject(".class", "Html.A", ".text","Delete");
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";

		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Get loop or area id value
	 * @param loopAreaName
	 * @return
	 */
	public String getLoopAreaID(String loopAreaName){
		String loopAreaID = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".class",	"Html.A", ".text", loopAreaName);
		if(objs.length>0){
			String hrefValue = objs[0].getProperty(".href").toString();
	        Pattern p = Pattern.compile("\\d+(,\\d{3})*");         
	        Matcher m = p.matcher(hrefValue);
	        if(m.find()){
	        	loopAreaID = m.group();
	        }
		}else throw new ItemNotFoundException("Object doesn't find.");

		
        Browser.unregister(objs);
		return loopAreaID;
	}
	
	/**
	 * Get all name of Loop/Area Name
	 * @return loopOrAreaName
	 */
	public String getAllNameOfLoopOrAreas(){
		String loopOrAreaName = "";
		StringBuffer buf=new StringBuffer();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Loop/Area",false));
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount()>0){
			List<String> a = table.getColumnValues(1);
			for(int i=0;i<a.size();i++){
				//loopOrAreaName += a.get(i)+"#";
				buf.append(a.get(i)+"#");
			}
		}
		loopOrAreaName=buf.toString();
		return loopOrAreaName;
	}
}
