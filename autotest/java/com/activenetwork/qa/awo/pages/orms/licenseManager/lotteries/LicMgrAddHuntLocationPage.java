package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo.SubLocation;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * This is add hunt location for hunt page in license manager, Admin(drop down list)-->Lotteries --- > 
 * Hunt Locations --<BUTTON>-->Add Hunt Location
 * @author pchen
 * @Date Nov 12, 2012
 */
public class LicMgrAddHuntLocationPage extends LicMgrLotteriesCommonPage{
	private static LicMgrAddHuntLocationPage _instance = null;
	
	protected LicMgrAddHuntLocationPage (){}
	
	public static LicMgrAddHuntLocationPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddHuntLocationPage();
		}
		
		return _instance;
	}
	
	private String prefix = "HuntLocationDetailView-\\d+\\.";
	
	protected Property[] subLocTable() {
		return Property.concatPropertyArray(this.table(), ".id", "huntSubLocationTable");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","huntLocationDetailComponent");
	}
	
	/**
	 * Select specie from drop down list
	 * @param specie
	 */
	public void selectSpecie(String specie){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"species", false), specie);
	}
	
	/**
	 * Set code
	 * @param code
	 */
	public void setCode(String code){
		browser.setTextField(".id", new RegularExpression(prefix+"code", false), code);
	}
	
	/**
	 * Set description
	 * @param description
	 */
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression(prefix+"description", false), description);
	}
	/**
	 * Select huntLocationImage from drop down list
	 * @param huntLocationImage
	 */
	public void selectLocationImage(String huntLocationImage){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"huntLocationImage", false), huntLocationImage);
	}
	
	/**
	 * Click 'View' link
	 */
	public void clickView(){
		browser.clickGuiObject(".class", "Html.A", ".text", "View");
	}
	
	/**
	 * Set long description
	 * @param longDescription
	 */
	public void setLongDescription(String longDescription){
		browser.setTextArea(".id", new RegularExpression(prefix+"longDescription", false), longDescription);
	}
	/**
	 * Select category from drop down list
	 * @param category
	 * @param index
	 */
	public void selectCategory(String category, int index){
		browser.selectDropdownList(".id", new RegularExpression("HuntSubLocationView-\\d+\\.category", false), category, index);
	}
	
	/**
	 * Set value of sub location
	 * @param value
	 * @param index
	 */
	public void setSubLocationValue(String value, int index){
		browser.setTextField(".id", new RegularExpression("HuntSubLocationView-\\d+\\.value", false), value, true, index);
	}
	
	private IHtmlObject[] getHuntSubLocTables() {
		IHtmlObject[] objs = browser.getHtmlObject(this.subLocTable());
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Hunt Sub location tables");
		}
		return objs;
	}
	
	/**
	 * Click 'Add New' link
	 */
	public void clickAddNewCategory(int index){
		IHtmlObject[] objs = this.getHuntSubLocTables();
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New", true, index, objs[0]);
	}
	
	public void clickAddNewLocationImage(){
		browser.clickGuiObject(Property.concatPropertyArray(a(), ".text", "Add New"));
	}
	
	/**
	 * Click remove button
	 * @param index
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, index);
	}
	
	public boolean checkRemoveIsExisting(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "Remove");
	}
	
	/**
	 * Click add sub location button
	 */
	public void clickAddSubLocation(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Sub Location");
	}
	/**
	 * Click ok button
	 */
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	/**
	 * Click cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public List<String> getLocationImages(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix+"huntLocationImage", false));
	}
	
	/**
	 * Set up location info
	 * @param location
	 */
	public void setUpHuntLocationInfo(LocationInfo location){
		if(StringUtil.notEmpty(location.getSpecie())){
			this.selectSpecie(location.getSpecie());
		}
		if(StringUtil.notEmpty(location.getCode())){
			this.setCode(location.getCode());
		}
		if(StringUtil.notEmpty(location.getDescription())){
			this.setDescription(location.getDescription());
		}
		if(StringUtil.notEmpty(location.getImage())){
			this.selectLocationImage(location.getImage());
		}
		if(StringUtil.notEmpty(location.getLongDescription())){
			this.setLongDescription(location.getLongDescription());
		}
		List<SubLocation> locationList = location.getSubLocations();
		for(int i=0; i < locationList.size(); i++){
			this.clickAddSubLocation();
			ajax.waitLoading();
			if(StringUtil.notEmpty(locationList.get(i).getCategory())){
				this.selectCategory(locationList.get(i).getCategory(), i);
			}
			if(StringUtil.notEmpty(locationList.get(i).getValue())){
				this.setSubLocationValue(locationList.get(i).getValue(), i);
			}
		}
	}
	/**
	 * This method set up location information and click ok
	 * @param location
	 */
	public void addHuntLocation(LocationInfo location){
		this.setUpHuntLocationInfo(location);
		this.clickOK();
	}
	
	public List<String> getAllCategory()
	{
		return browser.getDropdownElements(".id", new RegularExpression("HuntSubLocationView-\\d+\\.category", false));
	}
	
	/**
	 * Get error messages on the page
	 */
	public String getErrorMsg(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");

		if(objs.length<1){
			return "";
		}
		if (objs.length > 1) {
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
			return message;
		}
		message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
}
