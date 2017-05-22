package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrHuntParameterCommonWidget extends DialogWidget {
	
	protected LicMgrHuntParameterCommonWidget(String title) {
		super(title);
	}
		
	/** Page Object Property Begin */
	protected Property[] paramDesTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("DisplayParameterView-\\d+\\.text", false));
	}
	
	protected Property[] paramValueTextArea() {
		return Property.toPropertyArray(".class", "Html.TEXTAREA", ".id", new RegularExpression("DisplayParameterView-\\d+\\.value", false));
	}
	
	protected Property[] printParamRadio() {
		return Property.toPropertyArray(".class", "Html.INPUT.radio", ".id", new RegularExpression("DisplayParameterView-\\d+\\.printIndicator", false));
	}
	
	protected Property[] removeBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Remove");
	}
	
	protected Property[] addBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add");
	}
	/** Page Object Property END */
	
	public void setParamDescription(String des, int index) {
		browser.setTextField(this.paramDesTextField(), des, true, index);
	}
	
	public void setParamDescription(String des) {
		this.setParamDescription(des, 0);
	}
	
	public void setParamValue(String value, int index) {
		browser.setTextArea(".id", new RegularExpression("DisplayParameterView-\\d+\\.value", false), value, true, index);
	}
	
	public void setParamValue(String value) {
		this.setParamValue(value, 0);
	}
	
	public void selectPrintParamYes(int index) {
		browser.selectRadioButton(this.printParamRadio(), index*2);
	}
	
	public void selectPrintParamYes() {
		this.selectPrintParamYes(0);
	}
	
	public void selectPrintParamNo(int index) {
		browser.selectRadioButton(this.printParamRadio(), index*2+1);
	}
	
	public void selectPrintParamNo() {
		this.selectPrintParamNo(0);
	}
	
	public void clickAdd() {
		browser.clickGuiObject(this.addBtn());
	}
	
	public void clickRemove() {
		browser.clickGuiObject(this.removeBtn());
	}
	
	public int getParameterNum(){
		return browser.getHtmlObject(this.paramDesTextField()).length;
	}
	
	public void setParameterInfo(String des,String value, boolean isPrint, int index) {
		this.setParamDescription(des, index);
		this.setParamValue(value, index);
		if (isPrint) {
			this.selectPrintParamYes(index);
		} else {
			this.selectPrintParamNo(index);
		}
	}
	
	public String getParameterDes(int index){
		return browser.getTextFieldValue(this.paramDesTextField(), index);
	}
	
	public String getParameterValue(int index){
		return browser.getTextAreaValue(this.paramValueTextArea(), index, null);
	}
	
	public boolean isPrintParameter(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("Parameter Description.*",false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find the parameter item table!");
		}
		boolean isPrint = false;
		if(browser.isRadioButtonSelected(".id", new RegularExpression("DisplayParameterView-\\d+\\.printIndicator", false), ".value","true",objs[0])){
			isPrint = true;
		}else{
			isPrint = false;
		}
		return isPrint;
	}
}
