package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:This class is the base class for three web page class:
 * CallMgrOrigPrivSaleAddItemPage,CallMgrReplacePrivSaleAddItemPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Feb 29, 2012
 */
public class CallMgrAddItemPage extends CallMgrCommonTopMenuPage {
	
	public void clickAddToCart(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart",
				index);
	}
	
	public void clickGotoCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go to Cart");
	}
	
	public void clickOriginalPrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Original Privileges");
		
	}

	public void clickReplacePrivilege() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text",
				new RegularExpression("(Duplicate|Replacement) Privileges", false));
		
	}
	
	public void clickConsumables(){
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Consumables");
	}
	
}
