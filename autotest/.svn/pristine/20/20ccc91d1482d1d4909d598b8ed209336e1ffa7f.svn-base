package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrLotteriesCommonPage extends LicMgrCommonTopMenuPage {
	
	private static LicMgrLotteriesCommonPage _instance = null;
	
	protected LicMgrLotteriesCommonPage() {}
	
	public static LicMgrLotteriesCommonPage getInstance() {
		if(_instance == null) _instance = new LicMgrLotteriesCommonPage();
		return _instance;
	}
	
	protected Property[] quotaTab() {
		return Property.concatPropertyArray(this.a(), ".text", "Quota");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "tab_LotteryTabMain");
	}
	
	public void clickProductTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Product");
	}
	
	protected Property[] pointTypesTab() {
		return Property.concatPropertyArray(a(), ".text", "Point Types");
	}
	
	public void clickHuntsTab(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A",".text","Hunts", 
				".id", new RegularExpression("LotteryTabMain_\\d+",false)));
	}
	
	public void clickHuntLocationsTab(){
		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("(Hunt )?Locations",false));
	}
	
	public void clickDatePeriodsTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Date Periods");
	}
	
	public void clickQuotaTab(){
		browser.clickGuiObject(quotaTab());
	}
	
	public void clickPointTypesTab(){
		browser.clickGuiObject(pointTypesTab());
	}
	
	public boolean checkQuotaTabExists() {
		return browser.checkHtmlObjectExists(quotaTab());
	}
	
	public void clickExecutionConfigTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Execution Config");
	}
	
	public void clickProcessingTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Processing");
	}
	
	public boolean isProcessiongTabExist(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "Processing");
	}
}
