/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.systemManager;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Mar 22, 2012
 */
public class SysMgrEFTTransmissionPage extends SysMgrCommonTopMenuPage {

	public static SysMgrEFTTransmissionPage _instance = null;

	protected SysMgrEFTTransmissionPage() {
	}

	public static SysMgrEFTTransmissionPage getInstance() {
		if (_instance == null) {
			_instance = new SysMgrEFTTransmissionPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Process");
	}
	
	public void clickProcess(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Process");
	}
	
	public String getMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".id", "NOTSET");
	}
}
