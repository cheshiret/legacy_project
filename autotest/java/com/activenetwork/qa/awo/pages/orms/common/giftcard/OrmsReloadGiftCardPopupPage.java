/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.giftcard;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Mar 17, 2014
 */
public class OrmsReloadGiftCardPopupPage extends HtmlPopupPage {
	private static OrmsReloadGiftCardPopupPage _instance;
	
	protected OrmsReloadGiftCardPopupPage() { 
		super("url", new RegularExpression(".*ReloadGiftCard\\.do\\?.*orms_session_id=.*", false));
//		super("title", "");
	}
	
	public static OrmsReloadGiftCardPopupPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsReloadGiftCardPopupPage();
		}
		return _instance;
	}
	
	public void clickSwipeCard() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Swipe Card");
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setAmount(String amount) {
		popup.setTextField(".id", "amount", ".class", "Html.INPUT.text", amount, false,0);
	}
	
	public void setGiftCardNumber(String giftCardNumber) {
		popup.setTextField(".id", "cardNumber", giftCardNumber);
	}
}
