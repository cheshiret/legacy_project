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
import com.activenetwork.qa.testapi.ActionFailedException;

/**
 * @Description:This test case verify the error message when the User to add a new Question for the Contract.
 * @Preconditions:
 * @SPEC:Use Case Specification: Add Question
 * @Task#:AUTO-505
 * 
 * @author szhou
 * @Date  Aug 26, 2011
 */
public class VerifyAnswerType_RadioButton extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	private String message1, message2;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();
		
		// add question and verify that less than two Acceptable Answers have been specified specified.
		question.anwsers = new AcceptableAnswers[1];
		question.anwsers[0] = question.new AcceptableAnswers();
		question.anwsers[0].answer = "Yes";
		question.anwsers[0].dispalyOrder = "3";
		lm.addQuestionsForContract(question);
		boolean errormess = this.verifyAddQuestionError(message1);
		
		// add question and verify the duplicate Acceptable Answers have been specified using case-insensitive string matching.
		question.anwsers = new AcceptableAnswers[2];
		question.anwsers[0] = question.new AcceptableAnswers();
		question.anwsers[0].answer = "Yes";
		question.anwsers[0].dispalyOrder = "3";
		question.anwsers[1] = question.new AcceptableAnswers();
		question.anwsers[1].answer = "Yes";
		question.anwsers[1].dispalyOrder = "2";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message2);
	    this.clickCancelOntheAddQuestion();
		
		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add question page displayed wrong.");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		question.questDisplayText = "Add New Question For Testing Answer Type --- Radio Buttons";
		question.questPrintText = "QA Question";
		question.answerType = "Single Selection - Radio (5 max.)";//"Radio Button";
	
		message1 = "At least two Acceptable Answers must be specified.";
		message2 = "Each Acceptable Answer specified must be unique.";
	}
	
	private void clickCancelOntheAddQuestion() {
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget
				.getInstance();
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();

		questionPg.clickCancel();
		prodConfPg.waitLoading();
	}
	
	private boolean verifyAddQuestionError(String error) {
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget
				.getInstance();
		String message = questionPg.getErrorMessage();

		logger.info("Verify add question error message.");
		if (error.equals(message)) {
			logger
					.info("The error message on the add question page displayed correct.");
		} else {
			logger
					.error("The error message on the add question page should be '"
							+ error + " '");
			return false;
		}
		return true;
	}

}
