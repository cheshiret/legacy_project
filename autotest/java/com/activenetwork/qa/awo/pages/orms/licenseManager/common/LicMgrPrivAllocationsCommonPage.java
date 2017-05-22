package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:Privilege Allocations common page in License Manager. Admin -> Privilege Allocations
 * 
 * @author Lesley Wang
 * @Date  Sep 25, 2013
 */
public class LicMgrPrivAllocationsCommonPage extends LicMgrCommonTopMenuPage {
	private static LicMgrPrivAllocationsCommonPage instance=null;
	
	protected LicMgrPrivAllocationsCommonPage(){}
	
	public static LicMgrPrivAllocationsCommonPage getInstance(){
		if(instance == null){
			instance=new LicMgrPrivAllocationsCommonPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] privAllocationMainTabDiv() {
		return Property.concatPropertyArray(div(), ".id", "PrivilegeAllocationsTabPanel");
	}
	
	protected Property[] privAllocationTabLink(String text) {
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("PrivilegeAllocationsTabPanel_\\d+", false), 
				".text", new RegularExpression(text, false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.privAllocationMainTabDiv());
	}
	
	private void clickAllocationTabLink(String text) {
		browser.clickGuiObject(this.privAllocationTabLink(text));
	}
	
	public void clickAllocationTypeTab() {
		this.clickAllocationTabLink("Allocation Type");
	}
	
	public void clickAllocationTypeLicenseYearsTab() {
		this.clickAllocationTabLink("Allocation Type Licen(c|s)e Years");
	}
	
	public void clickOutfitterAllocationsTab() {
		this.clickAllocationTabLink("(Outfitter|Business) Allocations");
	}
	
	public void clickAllocationsOrdersTab() {
		this.clickAllocationTabLink("Allocation Orders");
	}
}
