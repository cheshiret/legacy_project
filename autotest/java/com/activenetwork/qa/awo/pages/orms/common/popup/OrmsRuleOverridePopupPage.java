package com.activenetwork.qa.awo.pages.orms.common.popup;


public class OrmsRuleOverridePopupPage extends OrmsPinPopupPage {
	private static OrmsRuleOverridePopupPage _instance;
	
	protected OrmsRuleOverridePopupPage () {
		super("Override rule");
	}
	
	public static OrmsRuleOverridePopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsRuleOverridePopupPage();
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
