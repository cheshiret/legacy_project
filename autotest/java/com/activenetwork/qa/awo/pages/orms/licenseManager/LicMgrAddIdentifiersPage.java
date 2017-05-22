/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrAddIdentifiersPage.java
 * @Date:Feb 11, 2011
 * @Description:
 * @author asun
 */
public class LicMgrAddIdentifiersPage extends DialogWidget {

	private static LicMgrAddIdentifiersPage instance=null;
	
	private LicMgrAddIdentifiersPage(){
		super("Add Identifier");
	}
	
	public static LicMgrAddIdentifiersPage getInstance(){
		if(instance==null){
			instance=new LicMgrAddIdentifiersPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists();
	}
	
	public RegularExpression countryRegx=new RegularExpression("CustomerIdentif(i)?erView-\\d+\\.identifier\\.country|IdentifierView-\\d+\\.country",false);
	public RegularExpression stateRegx=new RegularExpression("CustomerIdentif(i)?erView-\\d+\\.identifier\\.stateProvince|IdentifierView-\\d+\\.stateProvince",false);
	public RegularExpression numrRegx=new RegularExpression("CustomerIdentif(i)?erView-\\d+\\.identifier\\.identifierNumber",false);
	public RegularExpression typeRegx = new RegularExpression("CustomerIdentif(i)?erView-\\d+\\.identifier\\.identifierType", false);
	
	/**select identifier type*/
	public void selectIdentifierType(String item){
		
		browser.selectDropdownList(".id", typeRegx, item);
		ajax.waitLoading();
	}
	
	/**click ok button*/
	public void setIdentifierNum(String idNum){
		browser.setTextField(".id", numrRegx, idNum);
	}
	
	public boolean isCountryExist(){
		return browser.checkHtmlObjectExists(".id", countryRegx);
	}
	
	public boolean isStateExist(){
		return browser.checkHtmlObjectExists(".id", stateRegx);
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", countryRegx, country);
		ajax.waitLoading();
	}
	
	public void selectCountry(int index){
		browser.selectDropdownList(".id", countryRegx, index);
		ajax.waitLoading();
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", stateRegx, state);
		ajax.waitLoading();
	}
	
	public void selectState(int index){
		browser.selectDropdownList(".id", stateRegx, index);
		ajax.waitLoading();
	}
	
	public void setIdentifier(CustomerIdentifier identifier){
		this.selectIdentifierType(identifier.identifierType);
		ajax.waitLoading();
		this.setIdentifierNum(identifier.identifierNum);
		
		if(this.isCountryExist()){
			this.selectCountry(identifier.country);
		}
		
		if(this.isStateExist()){
			this.selectState(identifier.state);
		}
	}
	
	/**
	 * Get warning message
	 * @return
	 */
	public String getWarnMes(){
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id","NOTSET");
		if(objs.length>0){
			warnMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warnMes;
	}
}
