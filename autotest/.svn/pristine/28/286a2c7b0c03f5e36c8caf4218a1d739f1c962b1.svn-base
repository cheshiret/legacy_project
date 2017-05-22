/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
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
public class VerifyAnswerType_TextInput extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	private String message1, message2, message3, message4, message5;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();
		
		// add question and verify the Minimum Length is not specified
		lm.addQuestionsForContract(question);
		boolean errormess = this.verifyAddQuestionError(message1);

		// add question and verify the Maximum Length is not specified
		question.minLength = "-3";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message2);

		// add question and verify the Minimum Length is not an integer value greater than 0
		question.maxLength = "-5";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message3);
		
		// add question and verify the Maximum Length is not an integer value greater than 0
		question.minLength = "3";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message4);
		
		// add question and verify the Minimum Length is greater than the Maximum Length.
		question.maxLength = "2";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message5);
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
		
		question.questDisplayText = "Add New Question For Testing Answer Type --- Text Input";
		question.questPrintText = "QA Question";
		question.answerType = "User Entry";//"Text Input"; 305 changed
		
		message1 = "The Minimum Length is required when the selected Answer Type is Text Input Field. Please enter an integer value greater than 0 in the Minimum Length.";
		message2 = "The Maximum Length is required when the selected Answer Type is Text Input Field. Please enter an integer value greater than 0 in the Maximum Length.";
		message3 = "The Minimum Length must be an integer value greater than 0.";
		message4 = "The Maximum Length must be an integer value greater than 0.";
		message5 = "The Minimum Length cannot be greater than the Maximum Length.";
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
