/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: HF Product Questionnaire page. The page title is shown as "Product Information Required". 
 * The page will be shown after click Add To Cart on product details page if there are some questions for the product
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 11, 2013
 */
public class HFProductQuestionnairePage extends HFHeaderBar {
	private static HFProductQuestionnairePage _instance = null;

	public static HFProductQuestionnairePage getInstance() {
		if (null == _instance)
			_instance = new HFProductQuestionnairePage();

		return _instance;
	}
	
	protected HFProductQuestionnairePage() {
	}

	protected Property[] returnToLicDetailsLink() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Return to Licence Details", false));
	}
	
	protected Property[] prodInfo() {
		return Property.concatPropertyArray(div(), ".id", "prodInfo");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Form", ".id", "PrivilegeQuestionaireKit_form");
	}
	
	public String getProdInfo(){
		return browser.getObjectText(prodInfo());
	}
	
	public void verifyProdName(String prodName){
		String prodInfoFromUI = getProdInfo();
		if(prodInfoFromUI.startsWith(prodName.toUpperCase())){
			logger.info("Successfully verify privilege name - "+prodName);
		}else throw new ErrorOnPageException("Failed to verify privilege name", prodName, prodInfoFromUI);
	}
	
	public void clickContinue() {
		browser.clickGuiObject(".id", "submitForm_submitForm", ".text", "Continue");
	}
	
	public void clickReturnToLicDetails() {
		browser.clickGuiObject(returnToLicDetailsLink());
	}
	
	public boolean isQuestionExists(String questionDispalyText) {
		return browser.checkHtmlObjectExists(".class", "Html.Div", ".text",new RegularExpression(questionDispalyText + ".*", false));
	}
	
	public void selectAnswerRadioButton(String answer) {
		IHtmlObject labelObjs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", answer);
		if(labelObjs.length < 1) {
			throw new ItemNotFoundException("Can't find radio type answer - " + answer);
		}
		String forValue = labelObjs[0].getProperty("for");
		Browser.unregister(labelObjs);
		
		browser.selectRadioButton(".id", forValue);
	}
	
	public void setAnswer(String answer) {
		//TODO
	}
	
	public void selectAnswerFromDropdownList(String answer) {
		//TODO
	}
	/**
	 * Answer the privilege question
	 * @param questionDispalyText
	 * @param questionType
	 * @param questionAnswer
	 */
	public void answerPrivilegeQuestion(String questionDispalyText, String questionType, String questionAnswer) {
		
		if (this.isQuestionExists(questionDispalyText) && questionAnswer.length()>0){
			if (questionType.equalsIgnoreCase("radio")){
				this.selectAnswerRadioButton(questionAnswer);
			} else if(questionType.equalsIgnoreCase("textfield")) {
				if(questionAnswer.length() > 0) {
					this.setAnswer(questionAnswer);
				}
			} else if(questionType.equalsIgnoreCase("dropdownlist")) {
				if(questionAnswer.length() > 0) {
					this.selectAnswerFromDropdownList(questionAnswer);
				}
			}
		}
	}
	
	public void answerPrivilegeQuestions(QuestionInfo[] questions) {
		for (QuestionInfo ques : questions) {
			this.answerPrivilegeQuestion(ques.questDisplayText, ques.questionType, ques.questAnswer);
		}
	}
	
	protected Property[] validFromDateDiv() {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("PrivilegeQuestionaireKit_quest_\\d+_\\d+_attrs", false));
	}
	
	public boolean isFromDateExisted(){
		return browser.checkHtmlObjectExists(Property.atList(validFromDateDiv(), this.input("text")));
	}
	
	public void setValidFromDate(String date) {
		browser.setTextField(Property.atList(validFromDateDiv(), this.input("text")), date);
	}
	
	public void setValidFromDate(String date, int index) {
		IHtmlObject[] objs = browser.getHtmlObject(validFromDateDiv());
		if (objs.length < 1 || objs.length <= index) {
			throw new ObjectNotFoundException("Can't find valid from date div");
		}
		browser.setCalendarField(this.input("text"), date, true, 0, objs[index]);
		Browser.unregister(objs);
	}
}
