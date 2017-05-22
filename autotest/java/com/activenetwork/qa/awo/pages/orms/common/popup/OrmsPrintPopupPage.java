package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

public class OrmsPrintPopupPage extends HtmlPopupPage{

	private static OrmsPrintPopupPage _instance=null;
	private static RegularExpression titlePattern=new RegularExpression("activeworks | outdoors \\w+ Manager",false);
	
	protected OrmsPrintPopupPage() {
		super("title", titlePattern);
	}
	
	public static OrmsPrintPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsPrintPopupPage();
		}
		
		return _instance;
	}
	
	public void waitForDisappear() {
		boolean disappeared=popup==null?true:!popup.exists();
		int count=0;
		int timeout=5;
		while(!disappeared && count<timeout) {
			Browser.sleep(1);
			disappeared=!popup.exists();
			count++;
		}
		
		if(!disappeared){
			logger.warn("Print popup doesn't automatically close in "+timeout+" seconds. Force close!");
			close();
		}
	}
	
	/**
	 * Print all documents successfully
	 */
	public void clickSuccess(){
		popup.clickGuiObject(".class", "Html.A", ".text", "Success");	
	}
	
	public void selectPrinter(String printer) {
		RegularExpression pattern=new RegularExpression("PrinterType-\\d+\\.printerName",false);
		popup.selectDropdownList(".id",pattern, printer);
	}
	
	public void selectDummyPrinter() {
		selectPrinter("Dummy Printer");
	}
	
	public void clickPrint() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Print");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void processPrint() {
		IHtmlObject[] objs=popup.getHtmlObject(".class", "Html.A", ".text", "Success");
		
		if(objs.length>0) {
			objs[0].click();
		} else {
			Browser.unregister(objs);
			RegularExpression pattern=new RegularExpression("PrinterType-\\d+\\.printerName",false);
			objs=popup.getDropdownList(".id",pattern);
			
			if(objs.length>0) {
				String printer = TestProperty.getProperty("printer.name");
				((ISelect) objs[0]).select(printer);
			}
			
			Browser.unregister(objs);
			clickPrint();
			popup.waitExists(Property.toPropertyArray(".class", "Html.A", ".text", "Success"));
			clickSuccess();
		}
	}

}
