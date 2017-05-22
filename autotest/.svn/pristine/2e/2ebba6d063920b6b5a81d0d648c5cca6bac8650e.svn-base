/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrEditIdentifierPage.java
 * @Date:Feb 11, 2011
 * @Description:
 * @author asun
 */
public class LicMgrEditIdentifierPage extends DialogWidget {

	private static LicMgrEditIdentifierPage instance=null;

	private LicMgrEditIdentifierPage(){}

	public static LicMgrEditIdentifierPage getInstance(){
		if(instance==null){
			instance=new LicMgrEditIdentifierPage();
		}
		return instance;
	}

	public RegularExpression countryRegx=new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.country|IdentifierView-\\d+\\.country",false);
	public RegularExpression stateRegx=new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.stateProvince|IdentifierView-\\d+\\.stateProvince",false);
	public RegularExpression numrRegx=new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierNumber",false);
	public RegularExpression iDRegx=new RegularExpression("CustomerIdentifierView-\\d+\\.id:ZERO_TO_NEW",false);
	public RegularExpression statusRegx = new RegularExpression("CustomerIdentifierView-\\d+\\.status:CB_TO_NAME", false);
	public RegularExpression typeRegx = new RegularExpression("CustomerIdentifierView-\\d+\\.identifier.identifierType:CB_TO_NAME", false);
	public RegularExpression createAppRegx = new RegularExpression("CustomerIdentifierView-\\d+\\.identifier.creationApplication.appName", false);
	public RegularExpression createDateRegx = new RegularExpression("CustomerIdentifierView-\\d+\\.identifier.createDate:DATE_TIME", false);
	public RegularExpression createUserRegx = new RegularExpression("CustomerIdentifierView-\\d+\\.identifier.createUser:CB_TO_NAME", false);
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".text","Edit Identifierclose");
	}


	/**click identifier number button*/
	public void setIdentifierNum(String idNum){
		browser.setTextField(".id", numrRegx, idNum);
	}

	public void selectCountry(String country){
		if(country==null || country.trim().length()<1){
			this.selectCountry(0);
			return;
		}
		browser.selectDropdownList(".id", countryRegx, country);
		ajax.waitLoading();
	}
	public void selectCountry(int index){
		browser.selectDropdownList(".id", countryRegx, index);
		ajax.waitLoading();
	}

	public boolean isCountryExist(){
		return browser.checkHtmlObjectExists(".id", countryRegx);
	}

	public void selectState(String state){
		if(state==null || state.trim().length()<1){
			this.selectState(0);
			return;
		}
		browser.selectDropdownList(".id", stateRegx, state);
		ajax.waitLoading();
	}

	public void selectState(int index){
		browser.selectDropdownList(".id", stateRegx, index);
		ajax.waitLoading();
	}

	public boolean isStateExist(){
		return browser.checkHtmlObjectExists(".id", stateRegx);
	}

	public void editIdentifier(CustomerIdentifier identifier){
		this.setIdentifierNum(identifier.identifierNum);

		if(this.isCountryExist()){
			this.selectCountry(identifier.country);
			ajax.waitLoading();
		}

		if(this.isStateExist()){
			this.selectState(identifier.state);
			ajax.waitLoading();
		}

	}

	public String getID(){
		return browser.getTextFieldValue(".id", iDRegx);
	}

	/** Get warning message*/
	public String getWarnMes(){
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id","NOTSET");
		if(objs.length>0){
			warnMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warnMes;
	}

	/**
	 * Check un editable object
	 */
	public void checkObjectUnEditable(RegularExpression res){
		boolean unEdit = false;
//		unEdit = browser.checkHtmlObjectExists(".id",res,".disabled","true");
		IHtmlObject[] objs = browser.getHtmlObject(".id", res);
		unEdit = objs[0].isEnabled();
		Browser.unregister(objs);
		if(unEdit){
			throw new ErrorOnPageException("The object with RegularExpression = "+res+" should be un-edit...");
		}
	}
	
	public void checkIdUnEditable(){
		this.checkObjectUnEditable(this.iDRegx);
	}
	
	public void checkStatusEditable(){
		this.checkObjectUnEditable(this.statusRegx);
	}
	
	public void checkTypeUnEditable(){
		this.checkObjectUnEditable(this.typeRegx);
	}
	
	public void checkCreationApp(){
		this.checkObjectUnEditable(this.createAppRegx);
	}
	
	public void checkCreationDate(){
		this.checkObjectUnEditable(this.createDateRegx);
	}
	
	public void checkcreationUser(){
		this.checkObjectUnEditable(this.createUserRegx);
	}
	
	public String getStatus(){
		return browser.getTextFieldValue(".id",statusRegx);
	}
	
	public String getIdentifierType(){
		return browser.getTextFieldValue(".id",typeRegx);
	}
	
	public String getIdentifierNum(){
		return browser.getTextFieldValue(".id",numrRegx);
	}
	
	public String getIdentifierState(){
		return browser.getDropdownListValue(".id",stateRegx);
	}
	
	public String getCreateApp(){
		return browser.getTextFieldValue(".id",createAppRegx);
	}
	
	public String getCreateDate(){
		return browser.getTextFieldValue(".id",createDateRegx);
	}
	
	public String getCreateUser(){
		return browser.getTextFieldValue(".id",createUserRegx);
	}
	
	public void verifyCustIdentifier(CustomerIdentifier identifier){
		String temp = this.getID();
		if(!identifier.id.equals(temp)){
			throw new ErrorOnPageException("Identifier ID "+temp+" not correct.");
		}
		temp = this.getStatus();
		if(!identifier.status.equals(temp)){
			throw new ErrorOnPageException("Identifier Status "+temp+" not correct.");
		}
		temp = this.getIdentifierType();
		if(!identifier.identifierType.equals(temp)){
			throw new ErrorOnPageException("Identifier Type "+temp+" not correct.");
		}
		temp = this.getIdentifierNum();
		if(!identifier.identifierNum.equals(temp)){
			throw new ErrorOnPageException("Identifier Number "+temp+" not correct.");
		}
		
		if(identifier.state!=null&&identifier.state.length()>0){
			temp = this.getIdentifierState();
			if(!identifier.state.equals(temp)){
				throw new ErrorOnPageException("Identifier State "+temp+" not correct.");				
			}
		}
		temp = this.getCreateApp();
		if(!this.getManagerName().replaceAll(" ", "").equals(temp)){
			throw new ErrorOnPageException("Identifier Create Application "+temp+" not correct.");
		}
		temp = this.getCreateDate();
		if(DateFunctions.compareDates(identifier.createDate, temp.split(" ")[0].trim())!=0){
			throw new ErrorOnPageException("Identifier Create Date "+temp+" not correct.");
		}
		temp = this.getCreateUser();
		if(!identifier.createUser.equals(temp)){
			throw new ErrorOnPageException("Identifier Create User "+temp+" not correct.");
		}
	}
}
