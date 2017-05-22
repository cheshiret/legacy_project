package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 17, 2014
 */
abstract class InvMgrLoyaltyProgramCommonPage extends InvMgrTopMenuPage {
	
	private Property[] loyaltyPrograms() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".text", "Loyalty Programs");
	}
	
	private Property[] pointTypes() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".text", "Point Types");
	}
	
	private Property[] errorMsg() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public void clickLoyaltyProgramsTab() {
		browser.clickGuiObject(loyaltyPrograms());
	}
	
	public void clickPointTypesTab() {
		browser.clickGuiObject(pointTypes());
	}
	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(errorMsg());
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(errorMsg());
	}
}
