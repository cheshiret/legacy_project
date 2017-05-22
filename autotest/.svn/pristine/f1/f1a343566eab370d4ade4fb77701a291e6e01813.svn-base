package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is an abstract class, it contains the method used both by LicMgrHuntDetailPage and LicMgrAddNewHuntPage
 * @author pchen
 * @date Sep 20, 2012
 */
public abstract class LicMgrHuntsCommonPage extends LicMgrLotteriesCommonPage{
	private String prefix = "HuntView-\\d+\\.";
	/**
	 * Get huntCode
	 * @return
	 */
	public String getHuntCode(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"code", false));
	}
	
	/**
	 * Get description 
	 * @return
	 */
	public String getDescription(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"description", false));
	}
	
	/**
	 * Set description
	 * @param description
	 */
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression(prefix+"description", false), description);
	}
	
	/**
	 * Select one applicant according to its name
	 * @param applicant
	 */
	public void selectApplicant(String applicant){
		IHtmlObject[] divs=browser.getHtmlObject(".class","Html.SPAN",".text", new RegularExpression(".*" + applicant + "$", false));//Sara[10/11/2013]:Html.DIV
		if(divs.length<1){
			throw new ItemNotFoundException("Cann't find the applicant: " + applicant);
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix+"allow.*", false), 0, divs[0]);
		Browser.unregister(divs);
	}
	
	/**
	 * Select one applicant according to its name
	 * @param applicant
	 */
	public void unselectAllApplicant(){
		IHtmlObject[] checkBoxes = browser.getCheckBox(".name", new RegularExpression(prefix+"allow.*", false), ".type", "checkbox");
		if(checkBoxes.length<1){
			throw new ItemNotFoundException("Cann't find any applicant!");
		}
		for(int i=0; i<checkBoxes.length; i ++){
			browser.unSelectCheckBox(".id", new RegularExpression(prefix+"allow.*", false), i);
			ajax.waitLoading();
		}
		Browser.unregister(checkBoxes);
	}
	
	/**
	 * Select one applicant according to its name
	 * @param applicant
	 */
	public void unselectApplicant(String applicant){
		IHtmlObject[] divs=browser.getHtmlObject(".class","Html.DIV",".text", new RegularExpression(".*" + applicant + "$", false));
		if(divs.length<1){
			throw new ItemNotFoundException("Cann't find the applicant: " + applicant);
		}
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"allow.*", false), 0, divs[0]);
		Browser.unregister(divs);
	}
	
	/**
	 * Select individual
	 */
	public void selectIndividual(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"allowIndividual", false));
	}
	
	/**
	 * UnSelect individual
	 */
	public void unselectIndividual(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"allowIndividual", false));
	}
	
	/**
	 * Select group
	 */
	public void selectGroup(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"allowGroup", false));
		ajax.waitLoading();
	}
	
	/**
	 * Unselect group
	 */
	public void unselectGroup(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"allowGroup", false));
		ajax.waitLoading();
	}
	
	public void selectApplicantType(String appType){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"allow" + appType, false));
	}
	
	public void unSelectApplicantType(String appType){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"allow" + appType, false));
	}
	
	/**
	 * Set min size of group if group is allowed
	 * @param minGroupSize
	 */
	public void setMinAllowedOfGroup(String minGroupSize){
		browser.clickGuiObject(".id", new RegularExpression(prefix+"minGroupSize", false));
		browser.setTextField(".id", new RegularExpression(prefix+"minGroupSize", false), minGroupSize);
	}
	
	/**
	 * Set max size of group if group is allowed
	 * @param maxGroupSize
	 */
	public void setMaxAllowedOfGroup(String maxGroupSize){
		browser.setTextField(".id", new RegularExpression(prefix+"maxGroupSize", false), maxGroupSize);
	}
	
	/**
	 * Select hunt quota unlimited radio button
	 */
	public void selectHuntQuotaUnlimited() {
		browser.selectRadioButton(".id", new RegularExpression("HuntView-\\d+\\.huntQuotaLimited", false), 0);
	}
	
	/**
	 * Select hunt quota limited radio button
	 */
	public void selectHuntQuotaLimited() {
		browser.selectRadioButton(".id", new RegularExpression("HuntView-\\d+\\.huntQuotaLimited", false), 1);
	}
	
	/**
	 * Select lottery quota from drop down list
	 * @param quota
	 */
	public void selectHuntQuota(String quota){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"huntQuota", false), quota);
		ajax.waitLoading();
	}
	
	/**
	 * Select weapon from drop down list
	 * @param weapon
	 */
	public void selectWeapon(String weapon){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"weapon", false), weapon);
	}
	
	/**
	 * Select species sub-type from drop down list
	 * @param subType
	 */
	public void selectSpecieSubType(String subType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"speciesSubType", false), subType);
	}
	
	/**
	 * Select hunt Location from drop down list
	 * @param huntLocation
	 */
	public void selectHuntLocation(String huntLocation){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"huntLocation", false), huntLocation);
		ajax.waitLoading();
	}
	
	/**
	 * Select date period from drop down list
	 * @param datePeriod
	 */
	public void selectDatePeriod(String datePeriod){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"datePeriod", false), datePeriod);
		ajax.waitLoading();
	}
	/**
	 * Click Add New Hunt Quota
	 */
	public void clickAddNewHuntQuota(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Quota",true);
	}
	/**
	 * Click Add New Date Period
	 */
	public void clickAddNewDatePeriod(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Date Period",true);
	}
	/**
	 * Click Add New Hunt Location
	 */
	public void clickAddNewHuntLocation(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Hunt Location",true);
	}
	
	/**
	 * Click OK button
	 */
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK",true);
	}
	
	/**
	 * Click cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel",true);
	}
	
	/**
	 * Click apply button
	 */
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply",true);
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
