package com.activenetwork.qa.awo.pages.orms.operationManager.admin;

import com.activenetwork.qa.awo.pages.orms.operationManager.OperationsManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OpMgrAdminUserPage extends OperationsManagerPage{
	private OpMgrAdminUserPage(){
	}
	
	private static OpMgrAdminUserPage _instance = null;
	
	public static OpMgrAdminUserPage getInstance(){
		if(null==_instance){
			_instance = new OpMgrAdminUserPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class","Html.A",".text","Activate");
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text", new RegularExpression("Active(\\s)?Locked(\\s)?User Name", false));
	}
	
	public void clickBatchProcess(){
		browser.clickGuiObject(".class","Html.A",".text","Batch Processes");
	}
	
	public void clickImportGiftCardFile(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Import Gift Card File");
	}
}
