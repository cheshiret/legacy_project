package com.activenetwork.qa.awo.pages.orms.operationManager.admin;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.operationManager.OperationsManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

public class OpMgrImportGiftCardFilePage extends OperationsManagerPage{

	private OpMgrImportGiftCardFilePage(){
		
	}
	
	private static OpMgrImportGiftCardFilePage _instance = null;
	
	public static OpMgrImportGiftCardFilePage getInstance(){
		if(null == _instance){
			_instance = new OpMgrImportGiftCardFilePage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Start Import");
	}
	
	public List<String> getGiftCardPrograms(){
		return browser.getDropdownElements(".id", "codebaseDropdown");
	}
	
	/**
	 * Select gift card program from drop down list
	 * @param giftCartProgram
	 */
	public void selectGiftCardProgram(String giftCartProgram){
		browser.selectDropdownList(".id", "codebaseDropdown", giftCartProgram);
	}
	
	public boolean errorMessageIsExists(){
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public void setLocationOfGiftCardFile(String locationOfFile){
//		browser.setFileField(".id", "filepath", locationOfFile);
		browser.setTextField(".id", "filepath", locationOfFile);
	}
	
	public void clickStartImport(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Start Import");
	}
	
	public void clickBrowse() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", "filepath");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find File Upload component.");
		}
		objs[0].click();
		
		Browser.unregister(objs);
	}

}
