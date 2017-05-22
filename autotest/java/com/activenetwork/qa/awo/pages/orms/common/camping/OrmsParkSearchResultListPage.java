/*
 * Created on Dec 16, 2009
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class OrmsParkSearchResultListPage extends OrmsPage {
  	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsParkSearchResultListPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsParkSearchResultListPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsParkSearchResultListPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsParkSearchResultListPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Park Results");
	}

	/**
	 * Retrieve all park name object through the page.
	 * @return - TestObject
	 */
	public IHtmlObject[] getParkNameLinkObject() {
	  	RegularExpression reg = new RegularExpression(".*ParkDetails\\.do.*parkDetails.*", false);
	  	
	  	return browser.getHtmlObject(".class","Html.A", ".href", reg);
	}
	
    public void clickParkName(String name){
    	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A", ".text", name);
    	objs[0].click();
    	Browser.unregister(objs);
	}
	/**
	 * Retrieve all park name and its status to a vector of the entire page.
	 * @return - name of parks, status of park, true - available; false - not
	 */
	public List<String> getAllParkName() {
	  	List<String> parks = new ArrayList<String>();
	  	int numAParks = 0; 
	  	IHtmlObject[] parkNames = null;
	  	
	  	do {
	  	  	parkNames = this.getParkNameLinkObject();// get the number of available parks
	  	  	numAParks = this.getAvailableParks();  	  	
	  	  	for(int i=0; i<parkNames.length; i++) {
	  	  	  	if(i < numAParks) {
	  	  	  	  parks.add("true"); // true - available; false - not
	  	  	  	}else {
	  	  	  	  parks.add("false");
	  	  	  	}
	  	  	  	parks.add(parkNames[i].getProperty(".text").toString());
	  	  	}
	  	}while(gotoNext());
	  	
	  	Browser.unregister(parkNames);
	  	return parks;
	}
	
	/** Go to next result page by clicking on Next link. */
	public void clickNext() {
	  	browser.clickGuiObject(".class","Html.A",".text","Next");
	}
	
	/**
	 * Check whether there have next page,if have,click Next Button
	 * @return true - available; false - disable
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	/**
	 * Retrieve the number of available parks
	 * @return - number of available parks
	 */
	public int getAvailableParks() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".alt",new RegularExpression("(Exact|Partial) Match",false));
	  	int num = objs.length;
	  	
	  	Browser.unregister(objs);
	  	return num;
	}
	
	public String getPublicLine() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Park:.*", false));
		IHtmlTable parkDetail = (IHtmlTable) objs[0];
		String contact=parkDetail.getCellValue(1, 1).replace("Public Line", "").trim();
		
	  	
	  	Browser.unregister(objs);
	  	return contact;
	}
	
	private IHtmlObject[] getParkTableObject(String parkName){
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Park:.*" + parkName + "( )?State.*", false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found park table object. park name = " + parkName);
		}
		
		return objs;
	}
	
	public void clickDirectionsSummaryTitle(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		browser.clickGuiObject(".class", "Html.A", ".text", "Directions", true, 0, objs[0]);
	  	Browser.unregister(objs);
	}
	
	public void clickImportantInformtionSummaryTitle(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		browser.clickGuiObject(".class", "Html.A", ".text", "Important Information", true, 0, objs[0]);
	  	Browser.unregister(objs);
	}
	
	public String getDescriptionSummaryText(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		IHtmlObject[] descriptionTitleObjs = browser.getHtmlObject(".class", "Html.A", ".text", "Description", objs[0]);
		if(descriptionTitleObjs.length<1){
			throw new ItemNotFoundException("Did not found Description title object.");
		}
		String id = descriptionTitleObjs[0].id();
		IHtmlObject[] descriptionTextObjs = browser.getHtmlObject(".id",id.replaceAll("_title", ""));
		String defaultValue=descriptionTextObjs[0].getProperty(".text");
		
	  	Browser.unregister(objs);
	  	Browser.unregister(descriptionTitleObjs);
	  	Browser.unregister(descriptionTextObjs);
		return defaultValue;
	}
	
	public boolean checkSummaryTextIsExisting(String text){
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",text);
	}
	
	public boolean checkDescriptionSummaryTitleIsExisting(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		
		boolean isExisting = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Description", objs[0]);
	  	Browser.unregister(objs);
		return isExisting;
	}
	
	public boolean checkKeyAmenitiesSummaryTitleIsExisting(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		
		boolean isExisting = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Key Amenities", objs[0]);
	  	Browser.unregister(objs);
		return isExisting;
	}
	
	public boolean checkImportantInformationSummaryTitleIsExisting(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		
		boolean isExisting = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Important Information", objs[0]);
	  	Browser.unregister(objs);
		return isExisting;
	}
	
	public boolean checkDirectionsSummaryTitleIsExisting(String parkName){
		IHtmlObject[] objs = this.getParkTableObject(parkName);
		
		boolean isExisting = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Directions", objs[0]);
	  	Browser.unregister(objs);
		return isExisting;
	}
	
	
}
