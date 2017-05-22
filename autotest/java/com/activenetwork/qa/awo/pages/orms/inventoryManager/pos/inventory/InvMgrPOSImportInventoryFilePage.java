package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 30, 2012
 */
public class InvMgrPOSImportInventoryFilePage extends InvMgrPOSPhysicalInventoryReconciliationCommonPage {
	
	private static InvMgrPOSImportInventoryFilePage _instance = null;
	
	private InvMgrPOSImportInventoryFilePage() {}
	
	public static InvMgrPOSImportInventoryFilePage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrPOSImportInventoryFilePage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "uploadFiles");
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setInventoryFilePath(String path) {
		browser.setTextField(".id", "uploadFiles", path, true);
	}
	
	public void clickBrowse() {
		browser.clickGuiObject(".id", "uploadFiles");
	}
	
	public void importInventoryFile(String path) {
		setInventoryFilePath(path);
		clickOK();
		waitLoading();
	}
	
	public boolean isSuccess() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "error.import.inventory.file.success");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Success Message DIV object.");
		}
		
		return true;
	}
	
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Error Message DIV object.");
		}
		
		String text = objs[0].getProperty(".text").trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	public String getSuccessMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "error.import.inventory.file.success");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Success Message DIV object.");
		}
		
		String text = objs[0].getProperty(".text").trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	public String getFileImportID() {
		//1 inventory entries in the missing barcode8.txt import file have been successfully imported (137711674). The imported file has been renamed and moved to /home/finance/incoming/qa2/pos/import/137711674_1343636099527.txt.
		String text = getSuccessMsg();
		String id = text.split("\\(")[1].split("\\)")[0];
		
		return id;
	}
}
