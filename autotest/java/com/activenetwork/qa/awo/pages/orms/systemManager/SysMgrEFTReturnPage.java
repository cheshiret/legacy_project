package com.activenetwork.qa.awo.pages.orms.systemManager;

public class SysMgrEFTReturnPage extends SysMgrCommonTopMenuPage {

		public static SysMgrEFTReturnPage _instance = null;

		protected SysMgrEFTReturnPage() {
		}

		public static SysMgrEFTReturnPage getInstance() {
			if (_instance == null) {
				_instance = new SysMgrEFTReturnPage();
			}
			return _instance;
		}

		@Override
		public boolean exists() {
			return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Process");
		}
		
		public void clickOk(){
			browser.clickGuiObject(".class", "Html.A", ".text", "Ok");
		}
		
		public void clickCancel(){
			browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
		}
		
		public String getNote(){
			return browser.getTextFieldValue(".id", "NOTSET");
		}
}


