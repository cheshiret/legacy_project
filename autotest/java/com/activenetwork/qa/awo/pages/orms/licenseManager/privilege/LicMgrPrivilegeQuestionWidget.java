package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description this page is displayed to ask user to answer questions when doing options on Orders
 */
public class LicMgrPrivilegeQuestionWidget extends DialogWidget {
	private static LicMgrPrivilegeQuestionWidget _instance = null;
	
	protected LicMgrPrivilegeQuestionWidget() {
		super("Question");
	}
	
	public static LicMgrPrivilegeQuestionWidget getInstance () {
		if (null == _instance) {
			_instance = new LicMgrPrivilegeQuestionWidget();
		}
		
		return _instance;
	}
	
	/*public boolean exists(){
		return super.exists()&& browser.checkHtmlObjectExists(".class","Html.SPAN",".text","Question");
	}*/
	
	/**
	 * Check whether the question is exists
	 * @param questionName
	 * @return
	 */
	public boolean isQuestionExists (String questionName){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",new RegularExpression(questionName + ".*", false));
	}
	
	public void selectAnswer(String answer) {
		browser.selectDropdownList(".id", new RegularExpression("SurveyQuestion-\\d+\\.singleSelectedAnswer", false), answer);
	}
	
	public void selectAnswerRadioButton(String answer) {
		IHtmlObject labelObjs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", answer);
		if(labelObjs.length == 0) {
			throw new ItemNotFoundException("Can't find radio type answer - " + answer);
		}
		String forValue = labelObjs[0].getProperty("for");
		Browser.unregister(labelObjs);
		
		browser.selectRadioButton(".value", forValue);
	}
	
	/**
	 * Select the answer of yes
	 */
	public void selectAnswerRadioButtonOfYes (){
		browser.selectRadioButton(".class", "Html.INPUT.radio", ".id", new RegularExpression("SurveyQuestion-\\d+\\.singleSelectedAnswer", false), 0);
	}
	
	/**
	 * Select the answer of no
	 */
	public void selectAnswerRadioButtonOfNo (){
		browser.selectRadioButton(".class", "Html.INPUT.radio", ".id", new RegularExpression("SurveyQuestion-\\d+\\.singleSelectedAnswer", false), 1);
	}
	
	public void setAnswer(String text) {
		browser.setTextField(".id", new RegularExpression("SurveyQuestion-\\d+\\.answerText", false), text, true);
	}
	
	/**
	 * Answer the privilege question
	 * @param questionName
	 * @param questionType
	 * @param questionAnswer
	 */
	public void answerPrivilegeQuestion (String questionName, String questionType, String questionAnswer) {
		
		if (this.isQuestionExists(questionName) && questionAnswer.length()>0){
			if (questionType.equalsIgnoreCase("radio")){
				if (questionAnswer.equalsIgnoreCase("Yes")){
					this.selectAnswerRadioButtonOfYes();
					ajax.waitLoading();
				}else if (questionAnswer.equalsIgnoreCase("No")){
					this.selectAnswerRadioButtonOfNo();
					ajax.waitLoading();
				} else {
					this.selectAnswerRadioButton(questionAnswer);
					ajax.waitLoading();
//					throw new ItemNotFoundException("Not found the answer about: " + questionAnswer);
				}
			} else if(questionType.equalsIgnoreCase("textfield")) {
				if(questionAnswer.length() > 0) {
					this.setAnswer(questionAnswer);
				}
			} else if(questionType.equalsIgnoreCase("dropdownlist")) {
				if(questionAnswer.length() > 0) {
					this.selectAnswer(questionAnswer);
					ajax.waitLoading();
				}
			}
		}
	}
	
	/**
	 * click done button
	 */
	public void clickDone(){
		super.clickButtonByText("Done");
	}
	
	public void clickOK(){
		super.clickButtonByText("OK");
	}
	
	public boolean checkDoneExist(){
		return browser.checkHtmlObjectExists(".class", "html.A", ".text", "Done");
	}

	
}

