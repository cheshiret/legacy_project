package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;
/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Officer MGT --- > Officers --<click id>
 * @author pchen
 * @Date November 30, 2012
 */
public class LicMgrOfficerDetailPage extends LicMgrOfficerMGTCommonPage{
	private static LicMgrOfficerDetailPage _instance = null;
	
	protected LicMgrOfficerDetailPage (){}
	
	public static LicMgrOfficerDetailPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrOfficerDetailPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","officer_detail_formbar");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply", true);
	}
	
	public void clickInspectionsTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Inspections");
	}
	
	public void clickBadgesTab(){
		browser.clickGuiObject(".class", "Html.A",".text","Badges",1);
	}
}
