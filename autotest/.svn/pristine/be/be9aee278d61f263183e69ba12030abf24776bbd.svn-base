package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrAddDocTemplateWidget extends DialogWidget{
	private static LicMgrAddDocTemplateWidget _instance = null;
	
	private LicMgrAddDocTemplateWidget() {
		
	}
	
	public static LicMgrAddDocTemplateWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrAddDocTemplateWidget();
		}
		
		return _instance;
	}
	
	private Property[] getGenerateDocNumDropListProp() {
		return Property.toPropertyArray(".id", new RegularExpression("DocumentTemplateView-\\d+\\.generateDocNumType", false));
	}
	
	private Property[] getReGenerateDocNumDropListProp() {
		return Property.toPropertyArray(".id", new RegularExpression("DocumentTemplateView-\\d+\\.regenerateDocNumberType", false));
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Add Document Template");
	}
	
	public void setDocTemplateName(String docTemplateName){
		browser.setTextField(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.name", false), docTemplateName);
	}
	
	public void selectTemplateType(String tempateType){
		browser.selectDropdownList(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.templateFile(|\\.id)", false), tempateType);
	}
	
	public void selectTemplateType(int tempateTypeIndex){
		browser.selectDropdownList(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.templateFile\\.id", false), tempateTypeIndex);
	}
	
	public void setMaxReprintCount(String maxReprintCount){
		browser.setTextField(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.maxReprintCount", false), maxReprintCount);
	}
	
	public void selectHarvestDocument(String harvestDocument){
		browser.selectDropdownList(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.harvestDocument", false), harvestDocument);
	}
	
	public void selectCombinationIndicator(String indicator){
		browser.selectDropdownList(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.indicator", false), indicator, true);
	}
	
	public boolean isMaxLineCountFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("DocumentTemplateView-\\d+\\.maxLineCount", false));
	}
	
	public void setMaxLineCount(String maxLineCount){
		browser.setTextField(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.maxLineCount", false), maxLineCount, true);
	}
	
	public String getHarvestDocument(){
		return browser.getDropdownListValue(".id", new RegularExpression("^DocumentTemplateView-\\d+\\.harvestDocument", false),0);
	}
	
	public boolean isGenerateDocNumListExist() {
		return browser.checkHtmlObjectExists(this.getGenerateDocNumDropListProp());
	}
	
	public void selectGenerateDocNum(String value) {
		browser.selectDropdownList(getGenerateDocNumDropListProp(), value, true);
	}
	
	public void selectGenerateDocNum(int index) {
		browser.selectDropdownList(getGenerateDocNumDropListProp(), index, true);
	}
	
	public boolean isReGenerateDocNumListExist() {
		return browser.checkHtmlObjectExists(this.getReGenerateDocNumDropListProp());
	}
	
	public void selectReGenerateDocNum(String value) {
		browser.selectDropdownList(getReGenerateDocNumDropListProp(), value, true);
	}
	
	public void selectReGenerateDocNum(int index) {
		browser.selectDropdownList(getReGenerateDocNumDropListProp(), index, true);
	}
	
	public void setDocTemplateInfo(DocumentTemplateInfo docTemplateInfo){
		this.setDocTemplateName(docTemplateInfo.docTemplateName);
		this.selectTemplateType(docTemplateInfo.templateType);
		this.setMaxReprintCount(docTemplateInfo.maxReprintCount);
		this.selectHarvestDocument(docTemplateInfo.harvestDocument);
		this.selectCombinationIndicator(docTemplateInfo.combIndicator);
		ajax.waitLoading();
		this.waitLoading();
		if(isMaxLineCountFieldExists()) {
			this.setMaxLineCount(docTemplateInfo.maxLineCount);
		}
		if(this.isGenerateDocNumListExist()) {
			if(StringUtil.isEmpty(docTemplateInfo.generateDocNum))
				this.selectGenerateDocNum(0);
			else
				this.selectGenerateDocNum(docTemplateInfo.generateDocNum);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(this.isReGenerateDocNumListExist()) {
			if(StringUtil.isEmpty(docTemplateInfo.reGenerateDocNum))
				this.selectReGenerateDocNum(0);
			else
				this.selectReGenerateDocNum(docTemplateInfo.reGenerateDocNum);
		}
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(".class","Html.DIV",".className","message msgerror");
	}
	
}
