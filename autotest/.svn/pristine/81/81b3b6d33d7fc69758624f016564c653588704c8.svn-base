package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the widget that used to select a specie before add a new hunt in license manager, Admin(drop down list)-->Lotteries --- >
 *  Hunts --<click>-->Add Hunt 
 * @author pchen
 *
 */
public class LicMgrAddHuntSelectSpeciesWidget extends DialogWidget{
	private static LicMgrAddHuntSelectSpeciesWidget _instance = null;
	
	protected LicMgrAddHuntSelectSpeciesWidget (){}
	
	public static LicMgrAddHuntSelectSpeciesWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddHuntSelectSpeciesWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Species", false));
	}
	/**
	 * Select a specie from drop down list
	 * @param specie
	 */
	public void selectSpecie(String specie){
		browser.selectDropdownList(".id", new RegularExpression("HuntView-\\d+\\.species", false), specie );
	}
	
	/**
	 * Click ok button
	 */
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	/**
	 * Click cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
    /**
     * Get error message when add a new hunt
     * @return
     */
	public String getErrorMess(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find error message.");
		}
		String message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
}
