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
public class VerifyAnswerType_DropDown extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	private String message1, message2,message3,message4;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();
		
		// add question and verify that at least one of the selected Next Actions is Add Privilege(s), but the corresponding Privilege to be added is not specified.
		question.anwsers[1].nextAction = "Add Privileges";
		lm.addQuestionsForContract(question);
		boolean errormess = this.verifyAddQuestionError(message1);

		// add question and verify that at least one of the selected Next Actions is Remove Privilege(s), but the corresponding Privilege to be removed is not specified.
		
		question.anwsers[1].nextAction = "Remove Privileges";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message3);

		// add question and verify that at least one of the selected Next Actions is Remove Privilege(s), but duplicate corresponding Privileges have been specified.
		
		question.anwsers[1].actionValue = new String[2];
		question.anwsers[1].actionValue[0] = "HIP";
		question.anwsers[1].actionValue[1] = "HIP";
		lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message4);

		// add question and verify that at least one of the selected Next Actions is Add Privilege(s), but duplicate corresponding Privileges have been specified.
		question.anwsers[1].nextAction = "Add Privileges";
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

		question.questDisplayText = "Add New Question For Testing Answer Type --- Dropdown List2";
		question.questPrintText = "QA Question2";
		question.answerType = "Single Selection - Dropdown";
		question.anwsers = new AcceptableAnswers[2];
		question.anwsers[0] = question.new AcceptableAnswers();
		question.anwsers[0].answer = "Yes";
		question.anwsers[0].dispalyOrder = "3";
		question.anwsers[1] = question.new AcceptableAnswers();
		question.anwsers[1].answer = "No";
		question.anwsers[1].dispalyOrder = "2";
	
		message1 = "The Privilege to be added corresponding to an Acceptable Answer is not specified. Please select the Privilege(s) from the list provided.";
		message2 = "Duplicate Privileges to be added corresponding to an Acceptable Answer have been specified.";
		message3 = "The Privilege to be removed corresponding to an Acceptable Answer is not specified. Please select the Privilege(s) from the list provided.";
		message4 = "Duplicate Privileges to be removed corresponding to an Acceptable Answer have been specified.";	
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
					.error("The error message on the add question page displayed wrong.");
			logger.error("Expected:"+error);
			logger.error("Actual:"+message);
			return false;
		}
		return true;
	}

}
