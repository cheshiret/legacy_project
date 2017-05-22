/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo.AcceptableAnswers;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddQuestionsWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This test case verify the error message when the User to add a new Question for the Contract.
 * @Preconditions:
 * @SPEC:Use Case Specification: Add Question
 * @Task#:AUTO-505
 * 
 * @author szhou
 * @Date  Aug 26, 2011
 */
public class VerifyAnswerType_CheckBox extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	@SuppressWarnings("unused")
	private QuestionInfo nextactQuestion = new QuestionInfo();
	private String message1, message2,message3;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();

		// add question and verify that at least one of the selected Next Actions is Ask Another Question, but the corresponding Question to be asked is not specified.
		question.anwsers[1].answer = "No";
		question.anwsers[1].nextAction = "Ask Another Question";
		lm.addQuestionsForContract(question);
		boolean errormess = this.verifyAddQuestionError(message1);

		// add question and verify that at least one of the selected Next Actions is Ask Another Question, and the selected Question to be asked also has Ask Another Question as one of its possible Next Actions.
		question.anwsers[1].actionValue = new String[1];
		question.anwsers[1].actionValue[0] = "How many Geese did you bag(auto test)?";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message2);

		// add question and verify that at least one of the selected Next Actions is Stop Purchase, but the corresponding Error Message to be displayed is not specified.
		question.anwsers[1].nextAction = "Stop the Transaction";
		question.anwsers[1].actionValue[0] = "";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message3);
        this.clickCancelOntheAddQuestion();
		
		if (!errormess) {
			throw new ErrorOnPageException(
					"The error message on the add question page displayed wrong.");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		question.questDisplayText = "Add New Question For Testing Answer Type_CheckBox";
		question.questPrintText = "QA Question";
		question.answerType = "Multi Selection - Checkbox (5 max.)";//3.05 build change,"Check Box";
		question.anwsers = new AcceptableAnswers[2];
		question.anwsers[0] = question.new AcceptableAnswers();
		question.anwsers[0].answer = "Yes";
		question.anwsers[0].dispalyOrder = "3";
		question.anwsers[1] = question.new AcceptableAnswers();
		question.anwsers[1].dispalyOrder = "2";
				
		message1 = "The next Question to be asked corresponding to an Acceptable Answer is not specified. Please select the Question from the list provided.";
		message2 = "The next Question to be asked corresponding to an Acceptable Answer also has another Question to potentially be asked. Multiple nested Questions are not allowed.";
		message3 = "The Error Message to be displayed corresponding to an Acceptable Answer is not specified. Please specify the Error Message.";
	}
	
	private void clickCancelOntheAddQuestion() {
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget
				.getInstance();
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();

		questionPg.clickCancel();
		ajax.waitLoading();
		prodConfPg.waitLoading();
	}
	
	private boolean verifyAddQuestionError(String error) {
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget
				.getInstance();
		questionPg.clickOK();
		ajax.waitLoading();
		String message = questionPg.getErrorMessage();

		logger.info("Verify add question error message.");
		if (error.equals(message)) {
			logger
					.info("The error message on the add question page displayed correct.");
		} else {
			logger
					.error("The error message on the add question page displayed wrong. Expected: \""+error+"\", Actual: \""+message+"\"");
			return false;
		}
		return true;
	}
}
