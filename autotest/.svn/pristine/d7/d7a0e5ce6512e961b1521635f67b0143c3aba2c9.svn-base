package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo.SubLocation;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the widget that used to add a new hunt quota in license manager, Admin(drop down list)-->Lotteries --- >
 *  Hunts --<click>-->Add Hunt  --<select>-->specie --->CLICK Add New Hunt Location
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrAddNewHuntLocationWidget extends DialogWidget{
	private static LicMgrAddNewHuntLocationWidget _instance = null;
	
	private String prefix = "HuntLocationDetailView-\\d+\\.";
	protected LicMgrAddNewHuntLocationWidget (){}
	
	public static LicMgrAddNewHuntLocationWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddNewHuntLocationWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"huntLocationDetailComponent");
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
	/**
	 * Click 'Add New' link
	 */
	public void clickAddNew(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New", true, index);
	}
	/**
	 * Click remove button
	 * @param index
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, index);
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
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", 1);
	}
	/**
	 * Click cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", 1);
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
}
