package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the widget that used to add a new hunt quota in license manager, Admin(drop down list)-->Lotteries --- >
 *  Hunts --<click>-->Add Hunt  --<select>-->specie --->CLICK Add New Hunt Quota
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrAddNewHuntQuotaWidget extends DialogWidget{
	private static LicMgrAddNewHuntQuotaWidget _instance = null;
	
	private String prefix = "HuntQuotaView-\\d+\\.";
	private String quotaTypePrefix = "HuntQuotaDynamicTableVO-\\d+\\.";
	protected LicMgrAddNewHuntQuotaWidget (){}
	
	public static LicMgrAddNewHuntQuotaWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddNewHuntQuotaWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"AddHuntQuotaDialog");
	}
	/**
	 * Set quota description
	 * @param description
	 */
	public void setQuotaDescription(String description){
		browser.setTextField(".id", new RegularExpression(prefix+"description", false), description);
	}
	/**
	 * Select specie from drop down list
	 * @param species
	 */
	public void selectSpecies(String species){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"species", false), species);
	}
	/**
	 * Select license year from drop down list
	 * @param licenseYear
	 */
	public void selectLicenseYear(String licenseYear){
		browser.selectDropdownList(".id", new RegularExpression("HuntQuotaLicenseYearView-\\d+\\.licenseYear", false), licenseYear);
	}
	/**
	 * Set quota type description
	 * @param quotaTypeDescription
	 * @param index
	 */
	public void setQuotaType(String quotaTypeDescription, int index){
		browser.setTextField(".id", new RegularExpression(quotaTypePrefix+"quotaTypeDescription", false), quotaTypeDescription,
				true, index);
	}
	
	public void selectQutoaUse(String quotaUse, int index){
		browser.selectDropdownList(".id",  new RegularExpression(quotaTypePrefix+"quotaType.quotaUseType", false),quotaUse, index);
	}
	/**
	 * Set min age
	 * @param ageMin
	 * @param index
	 */
	public void setAgeMin(String ageMin, int index){
		browser.setTextField(".id", new RegularExpression(quotaTypePrefix+"quotaType\\.ageMin", false), ageMin,
				true, index);
	}
	/**
	 * Set max age
	 * @param ageMax
	 * @param index
	 */
	public void setAgeMax(String ageMax, int index){
		browser.setTextField(".id", new RegularExpression(quotaTypePrefix+"quotaType\\.ageMax", false), ageMax,
				true, index);
	}
	/**
	 * Select residency status from drop down list
	 * @param residency
	 * @param index
	 */
	public void selectResidencyStatus(String residency, int index){
		browser.selectDropdownList(".id", new RegularExpression(quotaTypePrefix+"quotaType\\.residency", false), residency, index);
	}
	
	public void setDrowOrder(String drowOrder, int index){ 
		browser.setTextField(".id", new RegularExpression(quotaTypePrefix+"drawOrder$", false), drowOrder,
				true, index);
	}
	
	/**
	 * Set quota
	 * @param quota
	 * @param index
	 */
	public void setQuota(String quota, int index){
		browser.setTextField(".id", new RegularExpression(quotaTypePrefix+"quota$", false), quota,
				true, index);
	}
	
	/**
	 * Select check box hybrid
	 * @param index
	 */
	public void selectHybrid(int index){
		browser.selectCheckBox(".id", new RegularExpression(quotaTypePrefix+"hybrid", false), index, true);
		ajax.waitLoading();
	}
	
	/**
	 * Set quota
	 * @param quota
	 * @param index
	 */
	public void setWeighted(String quota, int index){
		browser.setTextField(".id", new RegularExpression(quotaTypePrefix+"weightOnQuota", false), quota, true, index);
		ajax.waitLoading();
	}
	/**
	 * Click remove button
	 * @param index
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, index);
	}
	/**
	 * Click 'Add Quota Type' button
	 * @param index
	 */
	public void clickAddQuotaType(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Quota Type");
	}
	/**
	 * Click 'OK' button
	 */
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", 1);
	}
	/**
	 * Click 'Cancel' button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", 1);
	}
	
	/**
	 * Set up quota type information
	 * @param quota
	 */
	public void setUpQuotaTypeInfo(QuotaInfo quota){
		if(StringUtil.notEmpty(quota.getDescription())){
			this.setQuotaDescription(quota.getDescription());			
		}
		if(StringUtil.notEmpty(quota.getSpecie())){
			this.selectSpecies(quota.getSpecie());
		}
		if(StringUtil.notEmpty(quota.getLicenseYear())){
			this.selectLicenseYear(quota.getLicenseYear());
		}
		List<QuotaType> quotaList = quota.getQuotaTypes();
		for(int i=0; i<quotaList.size(); i++){
			if(i != 0){
				this.clickAddQuotaType();
				ajax.waitLoading();
			}
			if(StringUtil.notEmpty(quotaList.get(i).getQuotaType())){
				this.setQuotaType(quotaList.get(i).getQuotaType(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getQuotaUse())){
				this.selectQutoaUse(quotaList.get(i).getQuotaUse(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getAgeMin())){
				this.setAgeMin(quotaList.get(i).getAgeMin(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getAgeMax())){
				this.setAgeMax(quotaList.get(i).getAgeMax(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getResidencyStatus())){
				this.selectResidencyStatus(quotaList.get(i).getResidencyStatus(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getDrawOrder())){
				this.setDrowOrder(quotaList.get(i).getDrawOrder(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getQuota())){
				this.setQuota(quotaList.get(i).getQuota(), i);
			}
			if(quotaList.get(i).getIsHybrid()){
				this.selectHybrid(i);
				if(StringUtil.notEmpty(quotaList.get(i).getWeighted())){
					this.setWeighted(quotaList.get(i).getWeighted(), i);
				}
			}
		}
	}
	/**
	 * Set up quota information and click ok
	 * @param quota
	 */
	public void addQuota(QuotaInfo quota){
		this.setUpQuotaTypeInfo(quota);
		this.clickOK();
	}
	
}
