/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:This abstract class is the base class for three web page class:
 * LicMgrOrigPrivSaleAddItemPage,LicMgrReplacePrivSaleAddItemPage and LicMgrConsumableSaleAddItemPage
 * 
 * @author ssong
 * @Date  Oct 31, 2011
 */
public abstract class LicMgrAddItemPage extends LicMgrCommonTopMenuPage {
	
	public void clickOriginalPrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Original Privileges");
		
	}

	public void clickReplacePrivilege() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("(Replacement|Duplicate) (Permit )?(Privileges|Licences)", false));
	}
	
	public void clickConsumables(){
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Consumables");
	}
	
	public void clickGotoCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go to Cart", true);
	}
	
	public void clickAddToCart(int... index){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "Add to Cart");
		
		for(int i=0;i<index.length;i++) {
			
			if(index[i]>=0) {
				
				objs[index[i]].click();
				
				AwoAjax.getInstance().waitLoading();
			} 
		}
		
		Browser.unregister(objs);
	}
	public void clickAddToCart(IHtmlObject top) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart",true,0,top);
	}
}
