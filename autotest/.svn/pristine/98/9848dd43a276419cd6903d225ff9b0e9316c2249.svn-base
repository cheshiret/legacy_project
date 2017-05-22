package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsSelectDiscountPopupPage extends HtmlPopupPage {
	
   private static OrmsSelectDiscountPopupPage _instance;
	
	protected OrmsSelectDiscountPopupPage () {
//		super("title","Select Discount -- Webpage Dialog");
		super("title", new RegularExpression("Select( Automatic)? Discount", false));
	}
	
	public static OrmsSelectDiscountPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsSelectDiscountPopupPage();
		}
		
		return _instance;
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**
	 * 
	 * @param discountName
	 */
	public void selectDiscount(String discountName) {
		popup.selectDropdownList(".id","selectDiscountDropDown", discountName);
	}
	
	/**
	 * 
	 * @param discountName
	 */
	public void applyDiscount(String discountName) {
		selectDiscount(discountName);
		clickOK();
	}
}
