package com.activenetwork.qa.awo.pages.orms.operationManager.admin;

import com.activenetwork.qa.awo.pages.orms.operationManager.OperationsManagerPage;

/**
 * 
 * @author Ssong
 *
 */
public class OpMgrBatchProcessesPage extends OperationsManagerPage{

	private OpMgrBatchProcessesPage(){
	}
	
	private static OpMgrBatchProcessesPage _instance = null;
	
	public static OpMgrBatchProcessesPage getInstance(){
		if(null==_instance){
			_instance = new OpMgrBatchProcessesPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SELECT",".id","processes");
	}
	
	/**
	 * Select process from drop down list
	 * @param process
	 */
	public void selectProcess(String process){
		browser.selectDropdownList(".id", "processes", process);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
}
