/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.add;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;

/**
 * @Description:This test case verify the cancel steps when the user add a new Question for the Contract.
 * @Preconditions:
 * @SPEC:Use Case Specification: Add Question 
 * @Task#:AUTO-505
 * 
 * @author szhou
 * @Date  Aug 26, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();
		
		// cancel add question for contract
		lm.addQuestionsForContract(question, true);
		boolean succ = this
				.verifyCancelQuestionSuccess(question.questDisplayText);
		
		if (!succ) {
			throw new ActionFailedException(
					"Failed to cancel a new question for contract.");
		}

		lm.logOutLicenseManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		question.questDisplayText = "Add New Question For Testing Cancel";
		question.questPrintText = "QA Question";
		question.answerType = "Date(Any Date)";
	}
	
	private boolean verifyCancelQuestionSuccess(String displayText) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();

		logger.info("Verify add question success or not ...");

		List<String> text = prodConfPg.getColumnValue("product_question",
				"Display Text");
		if (!text.contains(displayText)) {
			logger.info("Cancel a new question for contract successful.");
		} else {
			logger.info("Failed to cancel " + displayText
					+ " question for contract.");
			return false;
		}
		return true;

	}

}
