/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrAddEducationPage.java
 * @Date:Feb 12, 2011
 * @Description:
 * @author asun
 */
public class LicMgrAddEducationPage extends DialogWidget {
	private static LicMgrAddEducationPage instance=null;
	
	private LicMgrAddEducationPage(){
		super("Add Education");
	}
	
	public static LicMgrAddEducationPage getInstance(){
		if(instance==null){
			instance=new LicMgrAddEducationPage();
		}
		return instance;
	}
	
	public RegularExpression statusRegx =new RegularExpression("CustomerEducationView-\\d+\\.status",false);
	public RegularExpression typeRegx =new RegularExpression("CustomerEducationView-\\d+\\.type",false);
	public RegularExpression eduNumRegx =new RegularExpression("CustomerEducationView-\\d+\\.educationNumber",false);
	public RegularExpression stateRegx =new RegularExpression("CustomerEducationView-\\d+\\.state",false);
	public RegularExpression CountryRegx =new RegularExpression("CustomerEducationView-\\d+\\.country",false);
	
	
	public void selectStatus(String status){
	    browser.selectDropdownList(".id", statusRegx, status);
    }
	
	public void selectEducationType(String type){
	    browser.selectDropdownList(".id", typeRegx, type);
	}
	
	public void setEducationNum(String educationNum){
	    browser.setTextField(".id", eduNumRegx, educationNum);
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
	
	public void selectCountry(String country){
		if(country==null || country.trim().length()<1){
			this.selectCountry(0);
			return;
		}
	    browser.selectDropdownList(".id", CountryRegx, country);
		ajax.waitLoading();
	}
	
	public void selectCountry(int index){
		browser.selectDropdownList(".id", CountryRegx, index);
		ajax.waitLoading();
	}
	
	public boolean isCountryExist(){
	    return browser.checkHtmlObjectExists(".id", CountryRegx);
	}
	
	
	
	
	public void setEducation(Education edu){
		this.selectStatus(edu.status);
		this.selectEducationType(edu.educationType);
		this.setEducationNum(edu.educationNum);
		
		if(this.isCountryExist()){
			this.selectCountry(edu.country);
			ajax.waitLoading();
		}
		
		if(this.isStateExist()){
			this.selectState(edu.state);
		}
		ajax.waitLoading();
	}
	
	/**
	 * Get warning message
	 * @return
	 */
	public String getWarnMes(){
		String warnMes = "";
		IHtmlObject[] widgets=this.getWidget();
		IHtmlObject[] objs = browser.getHtmlObject(".id","NOTSET",widgets[0]);
		if(objs.length>0){
			warnMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(widgets,objs);
		return warnMes;
	}
	
	/**
	 * Verify whether drop down list  option is same as expected
	 * @param regx
	 * @param expectOption
	 */
	private void verifyDropDownListOption(RegularExpression regx, String expectOption){
		String actualDefaultOption = browser.getDropdownListValue(".id", regx, 0);
		if(!actualDefaultOption.equals(actualDefaultOption)){
			 throw new ErrorOnDataException("The actual default option: '" + actualDefaultOption
					+"' is not match the expect default option: '" +actualDefaultOption+"'");
		}
	}
	
	public void verifyStatusOption(String expectOption){
		verifyDropDownListOption(statusRegx,expectOption);
	}

	/**
	 * Verify drop down list options exist or not
	 * @param regx
	 * @param expectDropDownListOption
	 */
	public void verifyDropDownListOptionsExist(RegularExpression regx, List<String> expectDropDownListOptions){
		int count = 0;
		List<String> actualOptions = new ArrayList<String>();
		actualOptions = browser.getDropdownElements(".id", regx);
		
		for(int j=0; j<expectDropDownListOptions.size(); j++){
			if(actualOptions.contains(expectDropDownListOptions.get(j))){
				count++;
			}
		}
		if(count!=expectDropDownListOptions.size()){
			throw new ErrorOnDataException("Some options aren't in drop down list.");
		}
	}
}
