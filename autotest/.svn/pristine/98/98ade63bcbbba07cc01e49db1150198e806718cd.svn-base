package com.activenetwork.qa.awo.pages.orms.common.giftcard;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;

public class OrmsAddGiftCardPge extends OrmsPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsAddGiftCardPge _instance = null;
		
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsAddGiftCardPge() {
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsAddGiftCardPge getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsAddGiftCardPge();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "giftCardOrderView");
	}
	
	/**
	 * Select gift card program name from drop down list
	 * @param giftCardProgName gift card program name
	 */
	public void selectGiftCardProgramName(String giftCardProgName){
		browser.selectDropdownList(".id", "GiftCardOrderView.giftCardProgramID", giftCardProgName);
	}
	
	/**
	 * Input amount for gift card
	 * @param amount
	 */
	public void setAmount(String amount){
		browser.setTextField(".id", "GiftCardOrderView.initAmount", amount);
	}
	
	/**
	 * The method used to set gift card number
	 * @param giftCardNum
	 */
	public void setCardNumber(String giftCardNum){
		browser.setTextField(".id", "GiftCardOrderView.giftCardNum", giftCardNum);
	}
	
	/**click Add to cart button*/
	public void clickAddToCart(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart");
	}
	
	private Property[] promptInformationObj() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", "Is the Gift Card being mailed to the customer?");
	}
	
	private Property[] giftCardBeingMailedRadioBtn(String value) {
		return Property.toPropertyArray(".id", "GiftCardOrderView.giftCardBeingMailed", ".value", value);
	}
	
	public boolean isPromptInformationExist(){
		return browser.checkHtmlObjectDisplayed(this.promptInformationObj());
	}

	public void selectGiftCardBeingMailed(boolean isMailed){
		String yesOrNo = isMailed?"true":"false";
		browser.selectRadioButton(this.giftCardBeingMailedRadioBtn(yesOrNo), 0);
	}
}
