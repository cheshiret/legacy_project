
package com.activenetwork.qa.awo.pages.orms.licenseManager.common.product;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddProductQuestionWidget extends DialogWidget {

	private static LicMgrAddProductQuestionWidget _instance = null;

	private LicMgrAddProductQuestionWidget() {

	}

	public static LicMgrAddProductQuestionWidget getInstance() {
		if (null == _instance) {
			_instance = new LicMgrAddProductQuestionWidget();
		}

		return _instance;
	}

	public boolean exists() {
		return super.exists()&& browser.checkHtmlObjectExists(".class", "Html.SPAN",
					".text", new RegularExpression("[Add Product Question | Edit Product Question]", false));
	}

	String prefix = "AbstractPrdQuestionAssignmentView-\\d+\\.";
	
	protected Property[] applicableAnswersCheckBox() {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", new RegularExpression("AcceptableAnswerView-\\d+\\.applicable$", false));
	}
	
	protected Property[] acceptableAnswersTR() {
		return Property.concatPropertyArray(this.tr(), ".text", new RegularExpression("^Acceptable Answers.*", false));
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"active", false), status, true);
	}

	public void setDisplayOrder(String displayOrder) {
		browser.setTextField(".id", new RegularExpression(prefix+"displayOrder:ZERO_TO_NULL", false), displayOrder);
	}

	public void selectPrompt(String prompt) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"questionLightView", false), prompt);
	}

	public String getPromptFirstValue() {
		String prompt = browser.getDropdownListValue( ".id", new RegularExpression(prefix+"questionLightView", false), 0);
		return prompt;
	}

	public void selectOriginal(String option) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"originalOption", false), option);
	}

	public String getOriginalDefaultValue() {
		String value = browser.getDropdownListValue(".id", new RegularExpression(prefix+"originalOption", false), 0);
		return value;
	}

	public void selectReplacement(String option) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"replacementOption", false), option);
	}

	public String getReplacementDefaultValue() {
		String value = browser.getDropdownListValue(".id", new RegularExpression(prefix+"replacementOption", false), 0);
		return value;
	}

	public void selectTransfer(String option) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"transferOption", false), option);
	}

	public String getTransferDefaultValue() {
		String value = browser.getDropdownListValue(".id",new RegularExpression(prefix+"transferOption", false), 0);
		return value;
	}

	public void selectLicenseYearFrom(String year) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"lyFromStr", false), year);
	}

	public void selectLicenseYearTo(String year) {
		browser.selectDropdownList(".id", new RegularExpression(
				prefix+"lyTo", false), year);
	}

	public List<String> getFiscalYearToValue() {
		List<String> value = browser.getDropdownElements(".id", new RegularExpression(prefix+"lyTo", false));
		return value;
	}

	public void selectCollectionMethod(String method) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"collectionMethod", false), method);
	}

	public String getCollectionMethodDefaultValue() {
		String value = browser.getDropdownListValue(".id",new RegularExpression(prefix+"collectionMethod",false), 0);
		return value;
	}

	public void setEffectiveFromDate(String date) {
		browser.setTextField(".id",new RegularExpression(prefix+"effectiveFrom_ForDisplay",false), date);
	}

	public String getEffectiveFromDateValue() {
		String value = browser.getTextFieldValue(".id",new RegularExpression(prefix+"effectiveFrom_ForDisplay",false));
		return value;
	}

	public void setEffectiveToDate(String date) {
		browser.setTextField(".id",new RegularExpression(prefix+"effectiveTo_ForDisplay",false), date);
	}

	public String getEffectiveToDate() {
		String value = browser.getTextFieldValue(".id",new RegularExpression(prefix+"effectiveTo_ForDisplay", false));
		return value;
	}

	public void selectLocationClass(String location) {
		IHtmlObject[] objs = browser.getHtmlObject(".className", "trailing",
				".text", location);
		objs[0].click();
		Browser.unregister(objs);
	}

	public void selectLocationAll() {
		browser.selectCheckBox(".id", new RegularExpression(
				prefix+"allLocationClassID",
				false));
	}

	public void unselectLocationAll() {
		browser.unSelectCheckBox(".id", new RegularExpression(
				prefix+"allLocationClassID",
				false));
	}

	public void clickAddPrintQuestion() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"fulfilementDocuments");
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", false, 0,
				objs[0]);
	}

	public int getPrintQuestionNum() {
		IHtmlObject[] objs = browser.getDropdownList(".id",
				new RegularExpression(
						"^DocumentTemplateAssignmentView-\\d+\\.id", false));
		return objs.length;

	}

	public void selectPrintQuestion(String document, int index) {
//		HtmlObject[] objs = browser.getTableTestObject(".id",
//				"fulfilementDocuments");
//		browser.selectDropdownList(new Property[] { new Property(".id",
//				new RegularExpression(
//						"^DocumentTemplateAssignmentView-\\d+\\.id", false)) },
//				document, false, index, objs[0]);
		IHtmlObject[] objs = browser.getDropdownList(".id", 
				new RegularExpression("^DocumentTemplateAssignmentView-\\d+\\.id", false));
		if(objs == null || objs.length <= index) {
			throw new ItemNotFoundException("Failed to find the Print document dropdown list");
		}
		List<String> options = ((ISelect)objs[index]).getAllOptions();
		boolean isExist = false;
		for (String option : options) {
			if (option.contains(document)) {
				isExist = true;
				((ISelect)objs[index]).select(option);
				break;
			}
		}
		if (!isExist) {
			throw new ItemNotFoundException("The option " + document + " does NOT exist in the list!");
		}
		Browser.unregister(objs);	
	}

	public void unselectAllApplicableAnswers() {
		IHtmlObject[] objs = browser.getCheckBox(Property.atList(this.acceptableAnswersTR(), this.applicableAnswersCheckBox()));
		for (int i = 0;  i < objs.length; i++) {
			browser.unSelectCheckBox(this.applicableAnswersCheckBox(), i);
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}
	
	public void selectApplicableAnswers(String answer) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Applicable Answers.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the applicable answer table");
		}
		IHtmlTable answerTable = (IHtmlTable) objs[0];
		List<String> answers = answerTable.getColumnValues(0);

		int j = 0;
		for (int i = 0; i < answers.size(); i++) {
			if (answer.trim().equals(answers.get(i).trim())) {
				j = i;
			}
		}

		if (j > 0) {
			browser.selectCheckBox(".id", new RegularExpression(
					"^AcceptableAnswerView-\\d+\\.applicable", false), j - 1);
			ajax.waitLoading();
			this.waitLoading();
		} else {
			throw new ItemNotFoundException("Can't find applicable answer.");
		}

		Browser.unregister(objs);
	}

	public void selectDependentApplicableAnswers(String[] answer) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^" + answer[0] + ".*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the dependent applicable answer table" + answer[0]);
		}
		IHtmlObject[] obj = browser.getTableTestObject(
				new Property[] { new Property(".text", new RegularExpression(
						"^Applicable Answers.*", false)) }, objs[0]);
		if (obj.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the applicable answer table");
		}
		IHtmlTable answerTable = (IHtmlTable) obj[0];
		List<String> answers = answerTable.getColumnValues(0);
		for (int z = 1; z < answer.length; z++) {
			int j = 0;
			for (int i = 0; i < answers.size(); i++) {
				if (answer[z].equals(answers.get(i))) {
					j = i;
				}
			}
			if (j > 0) {
				browser.selectCheckBox(new Property[] { new Property(".id",
						new RegularExpression(
								"^AcceptableAnswerView-\\d+\\.applicable",
								false)) }, j - 1, true, answerTable);
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("Can't find applicable answer.");
			}
		}
		Browser.unregister(obj);
		Browser.unregister(objs);
	}
	
	public boolean checkAcceptableAnswers() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Applicable Answers.*", false));
	}

	public boolean checkDependentAcceptableAnswers() {
		return browser.checkHtmlObjectExists(".className", "label_row",
				".text", "Dependent Question Acceptable Answers");
	}

	public boolean checkPrintQuestion() {
		return browser.checkHtmlObjectExists(".className", "label_row",
				".text", new RegularExpression("^Print Question.*", false));
	}

	public String getErrorMessage() {
		String error = browser.getObjectText(".class", "Html.DIV", ".id",
				"NOTSET");
		return error;
	}

	/**
	 * Set up detail question assignment info
	 * 
	 * @param question
	 */
	public void setupQuestionAssignmentInfo(QuestionInfo question) {
		setDisplayOrder(question.displayOrder);
		selectPrompt(question.questDisplayText);
		ajax.waitLoading();
		selectOriginal(question.originalOption);
		selectReplacement(question.replacementOption);
		selectTransfer(question.transfterOption);
		selectLicenseYearFrom(question.licenseYearFrom);
		ajax.waitLoading();
		selectLicenseYearTo(question.licenseYearTo);
		selectCollectionMethod(question.collectionMethod);
		
		if ("unselect".equals(question.locationClass)) {
			unselectLocationAll();
			ajax.waitLoading();
		} else if ("All".equals(question.locationClass)) {
			selectLocationAll();
			ajax.waitLoading();
		} else {
			unselectLocationAll();
			ajax.waitLoading();
			if (null != question.locationClasses
					&& question.locationClasses.length > 0) {
				for (String s : question.locationClasses) {
					selectLocationClass(s.split("-")[1].trim());
				}
			} else {
				selectLocationClass(question.locationClass.split("-")[1].trim());
			}
		}

		if (this.checkPrintQuestion() && null != question.printDocuments
				&& question.printDocuments.length > 0) {
			for (int i = 0; i < question.printDocuments.length; i++) {
//				if (this.getPrintQuestionNum() > question.printDocuments.length) {
					this.clickAddPrintQuestion();
					ajax.waitLoading();
//				}
				this.selectPrintQuestion(question.printDocuments[i], i);
			}
		}

		if (this.checkAcceptableAnswers() && null != question.questAnswers
				&& question.questAnswers.length > 0) {
			// unselect all questions firstly. Lesley[20131108]: questions are selected by default in 3.05.00
			this.unselectAllApplicableAnswers();
			for (int i = 0; i < question.questAnswers.length; i++) {
				this.selectApplicableAnswers(question.questAnswers[i]);
			}
		}

		if (this.checkDependentAcceptableAnswers()
				&& null != question.dependentAnswers
				&& question.dependentAnswers.size() > 0) {
			for (int i = 0; i < question.dependentAnswers.size(); i++) {
				this.selectDependentApplicableAnswers(question.dependentAnswers
						.get(i));
			}
		}
		
		setEffectiveFromDate(question.effectiveFromDate);

		if (!"".equals(question.effectiveToDate)) {
			setEffectiveToDate(question.effectiveToDate);
		}
	}
}
