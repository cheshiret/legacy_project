/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Aug 31, 2011
 */
public class SysMgrEnvsPage extends SysMgrCommonTopMenuPage {

	private static SysMgrEnvsPage _instance=null;
	
	protected SysMgrEnvsPage(){}
	
	public static SysMgrEnvsPage getInstance(){
		if(_instance==null){
			_instance=new SysMgrEnvsPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		RegularExpression regx=new RegularExpression("^Environments( )?Contracts( )?Order Process Mode( )?Lottery Executions( )?Service Daemon\\(s\\).*",false); 
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", regx);
	}
	
	public void clickEnvironmentsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Environments");
	}
	
	public void clickContractsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Contracts");
	}
	
	public void clickOrderProcessModeTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Order Process Mode");
	}
	
	public void clickLotteryExecutionsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Lottery Executions");
	}
	
	public void clickServiceDaemonsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Service Daemon(s)");
	}
}
