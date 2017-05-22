/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.add;

import java.util.List;

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
public class VerifyAddFields extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	private String message1, message2, message3;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();

		// add question and verify the Question Display Text is not specified
		lm.addQuestionsForContract(question);
		boolean errormess = this.verifyAddQuestionError(message1);
		
		// add question and verify the Question Print Text is not specified
		question.questDisplayText = "Add New Question For Testing Fields";
	    lm.addQuestionsForContract(question);
	    errormess &= this.verifyAddQuestionError(message3);
	    
	    // add a question for testing exist error message
	    question.questDisplayText = "Add New Question For Test";
	    question.questPrintText = "QA Question";
	    question.answerType = "Date&Time(Any Date&Time)";
	    if(!verifyQuestionExist(question.questDisplayText)){
	    	lm.addQuestionsForContract(question);
	    }
	  
	    // add question and verify the Question Display Text already exists using case-insensitive string matching
	    lm.addQuestionsForContract(question);
		errormess &= this.verifyAddQuestionError(message2);
		this.clickCancelOntheAddQuestion();
		
		if (!errormess) {
			throw new ActionFailedException(
					"Failed to add a new question for contract.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		message1 = "The Question Display Text is required. Please specify the Display Text.";
		message2 = "The Question Display Text entered already exists. The Display Text must be unique.";
		message3 = "The Question Print Text is required. Please specify the Print Text.";
		
	}
	
	private void clickCancelOntheAddQuestion() {
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget
				.getInstance();
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();

		questionPg.clickCancel();
		prodConfPg.waitLoading();
	}
	
	private boolean verifyQuestionExist(String displayText) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();

		List<String> text = prodConfPg.getColumnValue("product_question",
				"Display Text");
		
		if (text.contains(displayText)) {
			return true;
		} 
		
		return false;
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
