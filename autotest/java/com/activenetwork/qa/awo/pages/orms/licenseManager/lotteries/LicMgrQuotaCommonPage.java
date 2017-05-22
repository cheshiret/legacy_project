package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.testapi.InvalidDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Lottery Quota common page for Add Quota page and Quota Details page 
 * 
 * @author Lesley Wang
 * @Date  Jan 21, 2014
 */
public abstract class LicMgrQuotaCommonPage extends LicMgrLotteriesCommonPage {

	protected Property[] okBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "OK");
	}
	
	protected Property[] applyBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Apply");
	}
	
	protected Property[] cancelBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Cancel");
	}
	
	protected Property[] quotaIDSpan() {
		return Property.concatPropertyArray(this.span(), ".text", new RegularExpression("^Quota ID.*", false));
	}
	
	protected String prefix = "HuntQuotaView-\\d+\\.";
	
	protected Property[] quotaStatusList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression(prefix+"status", false));
	}
	
	protected Property[] quotaDescriptionField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression(prefix+"description", false));
	}
	
	protected Property[] quotaSpeciesList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression(prefix+"species", false));
	}
	
	protected String quotaTypePrefix = "HuntQuotaDynamicTableVO-\\d+\\.";
	
	protected Property[] quotaTypeDescriptionField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression(quotaTypePrefix+"quotaTypeDescription", false));
	}
	
	protected Property[] quotaTypeMinAgeField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression(quotaTypePrefix+"quotaType\\.ageMin", false));
	}
	
	protected Property[] quotaTypeMaxAgeField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression(quotaTypePrefix+"quotaType\\.ageMax", false));
	}
	
	protected Property[] quotaTypeResStatusList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression(quotaTypePrefix+"quotaType\\.residencyStatus", false));
	}
	
	protected Property[] quotaTypeDrawOrderField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression(quotaTypePrefix+"drawOrder", false));
	}
	
	protected Property[] quotaUseList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression(quotaTypePrefix+"quotaType\\.quotaUseType", false));
	}
	
	protected Property[] addQuotaType() {
		return Property.concatPropertyArray(a(), ".text", "Add Quota Type");
	}
	
	protected Property[] splitInto(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("DropdownExt-\\d+\\.selectedValue",false));
	}
	
	protected Property[] quotaSplit(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("HuntInventorySplitView-\\d+\\.quantity",false));
	}
	
	protected Property[] splitToHunt(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("HuntInventorySplitView-\\d+\\.hunt",false));
	}
	
	protected Property[] quotaLicenseYearsLabel() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Quota License Years\\(\\d+\\)",false));
	}
	
	protected Property[] quotaHuntTab(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Hunt(s)?(\\(\\d+\\))?", false), 
				".id", new RegularExpression("EditHuntQuota_\\d+",false));
	}
	
	protected Property[] quotaTransferTab(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Quota Transfer(s)?(\\(\\d+\\))?", false));
	}
	
	public void clickQuotaHuntsTab() {
		browser.clickGuiObject(quotaHuntTab());
	}
	
	public void clickQuotaTransfer(){
		browser.clickGuiObject(quotaTransferTab());
	}
	
	/**
	 * Click 'OK' button
	 */
	public void clickOK(){
		browser.clickGuiObject(okBtn());
	}
	
	/**
	 * Click 'Cancel' button
	 */
	public void clickCancel(){
		browser.clickGuiObject(cancelBtn());
	}
	
	/**
	 * Click 'Cancel' button
	 */
	public void clickApply(){
		browser.clickGuiObject(applyBtn());
	}
	
	public void clickAddQuotaType(){
		browser.clickGuiObject(addQuotaType());
	}
	
	public String getQuotaID() {
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaIDSpan());
		String text = browser.getTextFieldValue(this.input("text"), objs[0]);
		Browser.unregister(objs);
		return text;
	}
	
	public boolean isQuotaIDFieldEnabled() {
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaIDSpan());
		boolean result = browser.checkHtmlObjectEnabled(this.input("text"), objs[0]);
		Browser.unregister(objs);
		return result;
	}
	
	public String getQuotaStatus() {
		return browser.getDropdownListValue(this.quotaStatusList(), 0);
	}
	
	public void selectQuotaStatus(String status) {
		browser.selectDropdownList(this.quotaStatusList(), status);
	}

	/**
	 * Set quota description
	 * @param description
	 */
	public void setQuotaDescription(String description){
		browser.setTextField(this.quotaDescriptionField(), description);
	}
	
	public String getQuotaDescription() {
		return browser.getTextFieldValue(this.quotaDescriptionField());
	}
	
	public boolean isQuotaSpeciesListEnabled() {
		return browser.checkHtmlObjectEnabled(this.quotaSpeciesList());
	}
	
	public String getQuotaSpecies() {
		return browser.getDropdownListValue(this.quotaSpeciesList(), 0);
	}
	
	/**
	 * Set quota type description
	 * @param quotaTypeDescription
	 * @param index
	 */
	public void setQuotaType(String quotaTypeDescription, int index){
		browser.setTextField(this.quotaTypeDescriptionField(), quotaTypeDescription,
				true, index);
	}
	
	public String getQuotaType(int index) {
		return browser.getTextFieldValue(this.quotaTypeDescriptionField(), index);
	}

	public String getQuotaTypeAgeMin(int index) {
		return browser.getTextFieldValue(this.quotaTypeMinAgeField(), index);
	}
	
	public String getQuotaTypeAgeMax(int index) {
		return browser.getTextFieldValue(this.quotaTypeMaxAgeField(), index);
	}
	
	public String getQuotaTypeResidencyStatus(int index) {
		return browser.getDropdownListValue(this.quotaTypeResStatusList(), index);
	}
	
	/**
	 * Set min age
	 * @param ageMin
	 * @param index
	 */
	public void setAgeMin(String ageMin, int index){
		if(null != ageMin){
			browser.setTextField(quotaTypeMinAgeField(), ageMin, true, index);
		}
	}
	/**
	 * Set max age
	 * @param ageMax
	 * @param index
	 */
	public void setAgeMax(String ageMax, int index){
		if(null != ageMax){
			browser.setTextField(quotaTypeMaxAgeField(), ageMax, true, index);
		}
	}
	/**
	 * Select residency status from drop down list
	 * @param residency
	 * @param index
	 */
	public void selectResidencyStatus(String residency, int index){
		browser.selectDropdownList(quotaTypeResStatusList(), residency, index);
	}

	public void selectResidencyStatus(int optionIndex, int index){
		browser.selectDropdownList(quotaTypeResStatusList(), optionIndex, index, true, null);
	}
	public void setQuotaTypeDrawOrder(String text, int index) {
		if(null != text){
			browser.setTextField(this.quotaTypeDrawOrderField(), text, index);
		}
	}
	
	public String getQuotaTypeDrawOrder(int index) {
		return browser.getTextFieldValue(this.quotaTypeDrawOrderField(), index);
	}
	
	/**
	 * Get error messages on the page
	 */
	public String getErrorMsg(){
		String message="";
//		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".className", "message msgerror");

//		if(objs.length<1){
//			return "";
//		}
//		if (objs.length > 1) {
		if (objs.length > 0) {
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
//			return message;
		}
//		message=objs[0].getProperty(".text");
//		Browser.unregister(objs);
		return message;
	}
	
	public void selectSplitInto(String splitInto){
		browser.selectDropdownList(splitInto(), splitInto);
	}
	
	public String getSplitInto(){
		return browser.getDropdownListValue(splitInto(),0);
	}
	
	public void setQuotaSplit(String quota,int index){
		browser.setTextField(quotaSplit(), quota,index);
	}
	
	public String getQuotaSplit(int index){
		return browser.getTextFieldValue(quotaSplit(),index);
	}
	
	public void selectHunt(String hunt,int index){
		browser.selectDropdownList(splitToHunt(),hunt, index);
	}
	
	public String getHunt(int index){
		return browser.getDropdownListValue(splitToHunt(),index);
	}
	
	
	public void setSplitIntos(String splitInto,List<List<String>> splitIntos){
		if(StringUtil.notEmpty(splitInto)){
			this.selectSplitInto(splitInto);
			ajax.waitLoading();
			
			if(splitIntos.size()!=Integer.parseInt(splitInto)){
				throw new InvalidDataException("Split Into Number and given Split Into info size not same.");
			}
			for(int i=0;i<splitIntos.size();i++){
				List<String> list = splitIntos.get(i);
				setQuotaSplit(list.get(0), i);
				selectHunt(list.get(1), i);
			}
		}
	}
	
	public void selectQuotaUse(int index, int objIndex) {
		browser.selectDropdownList(quotaUseList(), index, objIndex, true, null);
	}
	
	public void selectQuotaUse(String quotaUse, int index){
		browser.selectDropdownList(this.quotaUseList(), quotaUse, index);
	}	
}
