/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: It is for HF header bar
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 19, 2013
 */
public class HFHeaderBar extends UwpMemberStatusBar {
	private static HFHeaderBar _instance = null;

	public static HFHeaderBar getInstance() {
		if (null == _instance)
			_instance = new HFHeaderBar();

		return _instance;
	}
	
	protected HFHeaderBar() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "topnav");
	}

	/** Object Property Definition Begin*/
	protected Property[] captionSpan() {
		return Property.concatPropertyArray(span(), ".className", "caption");
	}
	
	Property[] getItemsInCartLinkProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "cartLink"); // Lesley[20130906]: update due to the text is changed from 3.04.03
	}
	
	Property[] checkOutLinkSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "checkoutLink");
	}
//	
//	Property[] checkOutLink() {
//		return Property.toPropertyArray(".class", "Html.A", ".href", "/viewShoppingCart.do");
//	}
	
	Property[] signInLink() {
		return Property.concatPropertyArray(a(), ".id", "signIn");
	}
	
	protected Property[] lotteryTabLink() {
		return Property.concatPropertyArray(this.a(), ".id", "Lottery");
	}
	/** Object Property Definition End*/
	
	public void clickSignInLink() {
//		browser.clickGuiObject(".text", "Sign In", ".href", new RegularExpression(".*memberSignInDisplay.*", false));
		browser.clickGuiObject(signInLink());
	}
	
	public boolean isSignInMode() {
		return browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".text", "Sign In", ".href", new RegularExpression(".*memberSignInDisplay.*", false)));
	}
	
	public boolean isSignUpLinkExisting(){
		return browser.checkHtmlObjectExists(".id", "signUp");
	}
	
	public void clickSignUpLink() {
		browser.clickGuiObject(".id", "signUp");
	}
	
	public void clickSignOutLink() {
		browser.clickGuiObject(".text", "Sign Out", ".href", new RegularExpression(".*memberSignOut.*", false));
	}
	
	public void clickHomeTab() {
		browser.clickGuiObject(".id", "Home");
	}
	
	public void clickMyAccountTab() {
		browser.clickGuiObject(".id", "MyAccount");
	}
	
	public void clickPurchaseLicenseTab() {
		browser.clickGuiObject(".id", new RegularExpression("PurchaseLicen(c|s)e",false));
	}
	
	public void clickLotteryTab() {
		browser.clickGuiObject(lotteryTabLink());
	}
//	public void clickItemsInCartLink() {
//		browser.clickGuiObject(this.getItemsInCartLinkProp());
//	}
//	
//	public void clickCheckOutNowLink() {
//		browser.clickGuiObject(Property.atList(this.checkOutLinkSpan(), this.checkOutLink()), true, 0);
//	}
	
	public String getNumOfItemsInCart() {
		String text = browser.getObjectText(this.getItemsInCartLinkProp());
		return RegularExpression.getMatches(text, "\\d+")[0];
	}
}
