package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddQuestionsWidget extends DialogWidget {
	private static LicMgrAddQuestionsWidget _instance = null;

	private LicMgrAddQuestionsWidget() {

	}

	public static LicMgrAddQuestionsWidget getInstance() {
		if (null == _instance) {
			_instance = new LicMgrAddQuestionsWidget();
		}

		return _instance;
	}

	public boolean exists() {
		return super.exists()
				&& browser.checkHtmlObjectExists(".class", "Html.SPAN",
						".text", "Add Question");
	}

	protected Property[] displayText(){
		return Property.toPropertyArray(".id", new RegularExpression(
				"^AbstractQuestionView-\\d+\\.displayText",false));
	}
	
	public void setDisplayText(String displayText) {
		browser.setTextField(displayText(),	displayText);
	}

	protected Property[] printText(){
		return Property.toPropertyArray(".id", new RegularExpression(
				"^AbstractQuestionView-\\d+\\.printText",false));
	}
	
	public void setPrintText(String printText) {
		browser.setTextField(printText(),printText);
	}

	protected Property[] hipQuestion(){
		return Property.toPropertyArray(".id", new RegularExpression(
				"^AbstractQuestionView-\\d+\\.hipQuestion",false));
	}
	
	public void setHIPQuestion(String hipQuestion) {
		browser.setTextField(hipQuestion(),hipQuestion);
	}

	protected Property[] answerType(){
		return Property.toPropertyArray(".id", new RegularExpression(
				"^AbstractQuestionView-\\d+\\.answerType", false));
	}
	public void selectAnswerType(String answerType) {
		browser.selectDropdownList(answerType(),answerType);
	}

	protected Property[] minLength(){
		return Property.toPropertyArray(".id",new RegularExpression(
				"^AbstractQuestionView-\\d+.minLength:ZERO_TO_NULL", false));
	}
	public void setMinLength(String min) {
		browser.setTextField(minLength(), min);
	}

	protected Property[] maxLength(){
		return Property.toPropertyArray(".id",new RegularExpression(
				"^AbstractQuestionView-\\d+.maxLength:ZERO_TO_NULL", false));
	}
	
	public void setMaxLength(String max) {
		browser.setTextField(maxLength(), max);
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickAdd() {
//		browser
//				.clickGuiObject(".class", "Html.A", ".text",
//						"Add",false,1,this.getWidget()[0]);
		IHtmlObject objs[] = this.getAnswerTableObject();
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Answer Table object.");
		}
		IHtmlObject trs[] = browser.getHtmlObject(".class", "Html.TR", objs[0]);
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0, trs[trs.length - 1]);
		
		Browser.unregister(objs);
		Browser.unregister(trs);
	}

	private IHtmlObject[] getAnswerTableObject() {
		return browser.getTableTestObject(".id", "answer_table");
	}
	
	public void clickAddPrivilege(int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Privileges"
				+ index);
		IHtmlTable table = (IHtmlTable) objs[0];
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("\\s*Add", false), false, 0, table);
		Browser.unregister(objs);
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");

	}

	public boolean checkAcceptableAnswer() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.answer", false));
	}

	public int getAcceptableAnswerNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.answer", false));
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}

	public int getAnotherQuestionValueNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^NextActionView-\\d+\\.askAnotherQuestion", false));
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}

	public int getStopTransactionValueNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"^NextActionView-\\d+\\.msg", false));
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}

	public int getPrivilegeValueNum(int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Privileges"
				+ index);
		IHtmlTable table = (IHtmlTable) objs[0];

		IHtmlObject[] objs1 = browser.getHtmlObject(".id",
				new RegularExpression("^PrivilegeProductLightView-\\d+\\.id",
						false), table);
		int num = objs1.length;
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return num;
	}

	public void setAcceptableAnswer(String answer, int index) {
		browser.setTextField(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.answer", false), answer, index);
	}

	public void selectDefault(boolean isSelect, int index) {
		if (isSelect) {
			browser
					.selectCheckBox(
							".id",
							new RegularExpression(
									"^AcceptableAnswerView-\\d+\\.defaultChoice",
									false), index);
		}
	}

	public void setDisplayOrder(String order, int index) {
		browser.setTextField(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.displayOrder", false), order,
				index);
	}

	public void selectNextAction(String action, int index) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^AcceptableAnswerView-\\d+\\.nextActionType", false), action,
				index);
	}

	public void selectAnotherQuestionValue(String value, int index) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^NextActionView-\\d+\\.askAnotherQuestion", false), value,
				index);
	}

	public void selectStopTransactionValue(String value, int index) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^NextActionView-\\d+\\.msg", false), value, index);
	}

	public void selectPrivilegeValue(String value, int topIndex, int index) {
		//selectDropdownList(Property[] property, String item,boolean forced, int objectIndex, HtmlObject top)
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Privileges"
				+ topIndex);
		IHtmlTable table = (IHtmlTable) objs[0];
		Property[] property=new Property[1];
		property[0]=new Property(".id", new RegularExpression("^PrivilegeProductLightView-\\d+\\.id", false));
		browser.selectDropdownList(property, value, true, index, table);
		Browser.unregister(objs);
	}

	public boolean checkAnotherQuestionValue() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^NextActionView-\\d+\\.askAnotherQuestion", false));
	}

	public boolean checkStopTransactionValue() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^NextActionView-\\d+\\.msg", false));
	}

	public boolean checkPrivilegeValue() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^PrivilegeProductLightView-\\d+\\.id", false));
	}

	public boolean checkMinLength() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^AbstractQuestionView-\\d+\\.minLength.*", false));
	}

	public String getErrorMessage() {
		String error = browser.getObjectText(".class", "Html.DIV", ".id",
				"NOTSET");
		return error;
	}

}
