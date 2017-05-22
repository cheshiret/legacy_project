package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * This is add quota for hunt page in license manager, Admin(drop down list)-->Lotteries --- > Quota --<BUTTON>-->Add Quota
 * @author pchen
 * @Date Nov 12, 2012
 */
public class LicMgrAddQuotaPage extends LicMgrQuotaCommonPage{
	private static LicMgrAddQuotaPage _instance = null;
	
	protected LicMgrAddQuotaPage (){}
	
//	private String prefix = "HuntQuotaView-\\d+\\.";
//	
//	private String quotaTypePrefix = "HuntQuotaDynamicTableVO-\\d+\\.";
	
	public static LicMgrAddQuotaPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddQuotaPage();
		}
		return _instance;
	}
	
	protected Property[] quotalicenseYearList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("HuntQuotaLicenseYearView-\\d+\\.licenseYear", false));
	}
	
	protected Property[] removeLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Remove");
	}
	

	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",
				".text",new RegularExpression("^Lottery Quota", false));
	}

	/**
	 * Select specie from drop down list
	 * @param species
	 */
	public void selectSpecies(String species){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"species", false), species);
	}
	
	public void selectSpecies(int index){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"species", false), index);
	}
	/**
	 * Select license year from drop down list
	 * @param licenseYear
	 */
	public void selectLicenseYear(String licenseYear){
		browser.selectDropdownList(this.quotalicenseYearList(), licenseYear);
	}
	
	public void selectLicenseYear(int index){
		browser.selectDropdownList(this.quotalicenseYearList(), index);
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
	
	public boolean isRemoveExist() {
		return browser.checkHtmlObjectExists(removeLink());
	}
	
	/**
	 * Click remove button
	 * @param index
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, index);
	}
	
	public void removeQuotaType(int index) {
		if (isRemoveExist()) {
			this.clickRemove(index);
			ajax.waitLoading();
			this.waitLoading();
		}
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
		} else {
			this.selectSpecies(0);
		}
		if(StringUtil.notEmpty(quota.getLicenseYear())){
			this.selectLicenseYear(quota.getLicenseYear());
		} else {
			this.selectLicenseYear(0);
		}
		List<QuotaType> quotaList = quota.getQuotaTypes();
		for(int i=0; i<quotaList.size(); i++){
			if(i != 0){
				this.clickAddQuotaType();
				ajax.waitLoading();
			}
			if(quotaList.get(i).getQuotaType() != null){
				this.setQuotaType(quotaList.get(i).getQuotaType(), i);
			}
			if(quotaList.get(i).getQuotaUse() != null){
				this.selectQuotaUse(quotaList.get(i).getQuotaUse(), i);
				ajax.waitLoading();
			} else {
				this.selectQuotaUse(1, i);
				ajax.waitLoading();
			}
			
			if(StringUtil.notEmpty(quotaList.get(i).getAgeMin())){
				this.setAgeMin(quotaList.get(i).getAgeMin(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getAgeMax())){
				this.setAgeMax(quotaList.get(i).getAgeMax(), i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getResidencyStatus())){
				this.selectResidencyStatus(quotaList.get(i).getResidencyStatus(), i);
			} else {
				this.selectResidencyStatus(0, i);
			}
			if(StringUtil.notEmpty(quotaList.get(i).getDrawOrder())){
				this.setQuotaTypeDrawOrder(quotaList.get(i).getDrawOrder(), i);
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
			setSplitIntos(quotaList.get(i).getSplitInto(), quotaList.get(i).getSplitIntos());
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
