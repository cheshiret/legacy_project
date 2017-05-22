package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PriLandownerAttr;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrPriLandownerSubPage extends LicMgrPrivilegeProductDetailsPage{
	private static LicMgrPriLandownerSubPage _instance = null;
	protected LicMgrPriLandownerSubPage(){}
	
	public static LicMgrPriLandownerSubPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrPriLandownerSubPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(landownerDIV()) && super.exists();
	}

	private String prefix = "LandownerConfigurationView-\\d+.";
	
	protected Property[] landownerDIV() {
		return Property.toPropertyArray(".id", new RegularExpression("ProductPrivilegeLandownerUI", false));
	}

	protected Property[] minAcreage() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"minAcreage", false));
	}
	
	public void setMinAcresToQuantity(String minAcres){
		browser.setTextField(minAcreage(), minAcres);
	}

	protected Property[] maxCountyDeclaration() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"maxCountyDeclaration", false));
	}
	
	/**
	 * option in drop down list from 1 to 20
	 * @param maxCounty
	 */
	public void setMaxCountyDeclaration(String maxCounty){
		if(null == maxCounty || StringUtil.isEmpty(maxCounty)){
			browser.selectDropdownList(maxCountyDeclaration(), 0);
		} else {
			browser.selectDropdownList(maxCountyDeclaration(), maxCounty);
		}
	}

	protected Property[] onlySoldAsLandownerBonus() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"onlySoldAsLandownerBonus", false));
	}

	public void selectSellAsBonusOnly(){
		browser.selectCheckBox(onlySoldAsLandownerBonus());
	}

	public void unSelectSellAsBonusOnly(){
		browser.unSelectCheckBox(onlySoldAsLandownerBonus(), 0);
	}

	/**
	 * If check 'Sell As Bonus Only', this drop down will be disable.
	 * @param bonusPri
	 */
	protected Property[] bonusLandownerPrivilege() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"bonusLandownerPrivilege", false));
	}

	public void selectBonusPrivilege(String bonusPri){
		if(null == bonusPri || StringUtil.isEmpty(bonusPri)){
			browser.selectDropdownList(bonusLandownerPrivilege(), 0);
		} else {
			browser.selectDropdownList(bonusLandownerPrivilege(), bonusPri);
		}
	}
	
	public void clickSave(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Save");
	}
	
	public void setupPriLandownerInfo(Data<PriLandownerAttr> src){
		this.setMinAcresToQuantity(PriLandownerAttr. minAcresToQuantity.getStrValue(src));
		this.setMaxCountyDeclaration(PriLandownerAttr. maxCountyDeclaration.getStrValue(src));
		
		if(Boolean.valueOf(PriLandownerAttr.isAsBonusOnly.getStrValue(src))){
			this.selectSellAsBonusOnly();
			ajax.waitLoading();
		} else {
			this.unSelectSellAsBonusOnly();
			ajax.waitLoading();
			this.selectBonusPrivilege(PriLandownerAttr. bonusPriName.getStrValue(src));
		}
		
		this.clickSave();
		ajax.waitLoading();
		this.waitLoading();
	}
}