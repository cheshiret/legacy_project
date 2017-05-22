package com.activenetwork.qa.awo.pages.orms.common.popup;


public class OrmsNewDepositPopupPage extends OrmsPinPopupPage{
	private static OrmsNewDepositPopupPage _instance;
	
	protected OrmsNewDepositPopupPage () {
		super("Deposit");
	}
	
	public static OrmsNewDepositPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsNewDepositPopupPage();
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
