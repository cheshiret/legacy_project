package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo.SubLocation;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Hunt Location Details page in License Manager, Admin(drop down list)-->Lotteries --- > Hunt  Location --->click hunt loc id
 * @author Lesley Wang
 * @Date  Aug 7, 2013
 */
public class LicMgrHuntLocationDetailPage extends LicMgrLotteriesCommonPage {
	private static LicMgrHuntLocationDetailPage _instance= null;

	protected LicMgrHuntLocationDetailPage() {}
	
	public static LicMgrHuntLocationDetailPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntLocationDetailPage();
		}
		
		return _instance;
	}
	
	/** Page Object Property Definition Begin*/
	protected Property[] statusList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("HuntLocationDetailView-\\d+\\.status", false));
	}
	
	protected Property[] speciesList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("HuntLocationDetailView-\\d+\\.species", false));
	}
	
	protected Property[] locCodeTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("HuntLocationDetailView-\\d+\\.code", false));
	}
	
	protected Property[] locDescriptionTextFiled() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("HuntLocationDetailView-\\d+\\.description", false));
	}

	protected Property[] locImgList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("HuntLocationDetailView-\\d+\\.huntLocationImage", false));
	}
	
	protected Property[] locLongDesTextArea() {
		return Property.toPropertyArray(".class", "Html.TEXTAREA", ".id", new RegularExpression("HuntLocationDetailView-\\d+\\.longDescription", false));
	}
	
	protected Property[] subLocTable() {
		return Property.concatPropertyArray(this.table(), ".id", "huntSubLocationTable");
	}
	
	protected Property[] addSubLocBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add Sub Location");
	}
	
	protected Property[] subLocCategoryList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("HuntSubLocationView-\\d+\\.category", false));
	}
	
	protected Property[] addNewLink() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add New");
	}
	
	protected Property[] subLocValueTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("HuntSubLocationView-\\d+\\.value", false));
	}
	
	protected Property[] subLocTR(String value) {
		return Property.toPropertyArray(".class", "Html.tr", ".id", new RegularExpression("\\d+_\\d", false), 
				".text", new RegularExpression(".*"+value+".*", false));
	}
	
	protected Property[] removeBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Remove");
	}
	
	protected Property[] okBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	protected Property[] cancelBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	protected Property[] applyBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Apply");
	}
	
	protected Property[] addNewImgLInk() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add New");
	}
	/** Page Object Property Definition END*/
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","huntLocationDetailComponent");
	}
	
	public void selectHuntLocStatus(String status) {
		browser.selectDropdownList(this.statusList(), status, true);
	}
	
	public void selectHuntLocSpecies(String species) {
		browser.selectDropdownList(this.speciesList(), species, true);
	}
	
	public void setHuntLocCode(String code) {
		browser.setTextField(this.locCodeTextField(), code, true, 0);
	}
	
	public void setHuntLocDescription(String des) {
		browser.setTextField(this.locDescriptionTextFiled(), des, true, 0);
	}
	
	public void selectHuntLocImage(String img) {
		browser.selectDropdownList(this.locImgList(), img, true);
	}

	public void selectHuntLocImage(int index) {
		browser.selectDropdownList(this.locImgList(), index, true);
	}
	
	public List<String> getAllLocImages() {
		return browser.getDropdownElements(this.locImgList());
	}
	
	public void setHuntLocLongDescription(String des) {
		browser.setTextArea(".id", new RegularExpression("HuntLocationDetailView-\\d+\\.longDescription", false), des);
	}
	
	public void clickAddSubLoc() {
		browser.clickGuiObject(this.addSubLocBtn());
	}
	
	public void clickAddSubLocAndWait() {
		this.clickAddSubLoc();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void selectCategory(String category, int index) {
		browser.selectDropdownList(".id", new RegularExpression("HuntSubLocationView-\\d+\\.category", false), category, index);
	}
	
	public List<String> getAllCategory() {
		return browser.getDropdownElements(this.subLocCategoryList());
	}
	
	public void clickAddNewImage() {
		browser.clickGuiObject(this.addNewLink());
	}
	
	public void clickAddNewCategory() {
		browser.clickGuiObject(Property.atList(this.subLocTable(), this.addNewLink()));
	}
	
	public void setSubLocValue(String value, int index) {
		browser.setTextField(this.subLocValueTextField(), value, true, index);
	}
	
	public boolean isRemoveExist() {
		return browser.checkHtmlObjectExists(this.removeBtn());
	}
	
	public void clickRemove() {
		browser.clickGuiObject(this.removeBtn());
	}
	
	public void clickRemoveAndWait() {
		this.clickRemove();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickRemove(String value) {
		browser.clickGuiObject(Property.atList(this.subLocTR(value), this.removeBtn()), true, 0);
	}
	
	public void removeAll() {
		while (this.isRemoveExist()) {
			this.clickRemove();
			ajax.waitLoading();
		}
	}
	
	public void clickOK() {
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(this.cancelBtn());
	}
	
	public void clickApply() {
		browser.clickGuiObject(this.applyBtn());
	}
	
	/**
	 * Set up location info
	 * @param location
	 */
	public void setUpHuntLocationInfo(LocationInfo location){
		if(StringUtil.notEmpty(location.getLocationStatus())){
			this.selectHuntLocStatus(location.getLocationStatus());
		}
		if(StringUtil.notEmpty(location.getSpecie())){
			this.selectHuntLocSpecies(location.getSpecie());
		}
		if(StringUtil.notEmpty(location.getCode())){
			this.setHuntLocCode(location.getCode());
		}
		if(StringUtil.notEmpty(location.getDescription())){
			this.setHuntLocDescription(location.getDescription());
		}
		if(StringUtil.notEmpty(location.getImage())){
			this.selectHuntLocImage(location.getImage());
		} else {
			this.selectHuntLocImage(0);
		}
		if(StringUtil.notEmpty(location.getLongDescription())){
			this.setHuntLocLongDescription(location.getLongDescription());
		}
		List<SubLocation> locationList = location.getSubLocations();
		this.removeAll();
		if (locationList != null) {
			for(int i=0; i < locationList.size(); i++){
				this.clickAddSubLoc();
				ajax.waitLoading();
				if(StringUtil.notEmpty(locationList.get(i).getCategory())){
					this.selectCategory(locationList.get(i).getCategory(), i);
				}
				if(StringUtil.notEmpty(locationList.get(i).getValue())){
					this.setSubLocValue(locationList.get(i).getValue(), i);
				}
			}
		}
	}
}
