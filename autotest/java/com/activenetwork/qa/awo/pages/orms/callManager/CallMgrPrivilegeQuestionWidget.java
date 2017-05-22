/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;



import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description this page is displayed to ask user to answer questions when doing options on Orders
 */
public class CallMgrPrivilegeQuestionWidget extends DialogWidget {
	private static CallMgrPrivilegeQuestionWidget _instance = null;
	
	protected CallMgrPrivilegeQuestionWidget() {
		
	}
	
	public static CallMgrPrivilegeQuestionWidget getInstance () {
		if (null == _instance) {
			_instance = new CallMgrPrivilegeQuestionWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists()&& browser.checkHtmlObjectExists(".class","Html.SPAN",".text","Question");
	}
	
	/**
	 * Check whether the question is exists
	 * @param questionName
	 * @return
	 */
	public boolean isQuestionExists (String questionName){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",new RegularExpression(questionName + ".*", false));
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
				}else {
					throw new ItemNotFoundException("Not found the answer about" + questionAnswer);
				}
			} else if(questionType.equalsIgnoreCase("textfield")) {
				if(questionAnswer.length() > 0) {
					this.setAnswer(questionAnswer);
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

	
}

