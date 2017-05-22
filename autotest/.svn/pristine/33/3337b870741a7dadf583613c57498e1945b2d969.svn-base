package com.activenetwork.qa.awo.pages.orms.common.popup;


public class OrmsOtherVoucherPinPopupPage extends OrmsPinPopupPage {

	private static OrmsOtherVoucherPinPopupPage _instance;
	
	protected OrmsOtherVoucherPinPopupPage () {
		super("Dummy title to be replaced");
	}
	
	public static OrmsOtherVoucherPinPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsOtherVoucherPinPopupPage();
		}
		
		return _instance;
	}
	
	@Override
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Cancel");		
	}

	@Override
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "OK");		
	}

}
