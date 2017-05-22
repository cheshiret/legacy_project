/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.questions.addquestion;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description::This use case verify the steps taken by the User to add a
 *                    Question that needs to be asked during the sales work flow
 *                    of a particular Product. Check point:cancel button work
 * @Preconditions:need a question and a privilege
 * @SPEC:Use Case Specification: Add Product Question
 * @Task#:AUTO-573
 * 
 * @author szhou
 * @Date May 19, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	private String privilegeCode;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to product detail page
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilegeCode);

		// cancel add a question for product
		lm.addQuestionsForProduct(question, true);
		boolean succ = this.verifyCancelQuestionSuccess(question);

		if (!succ) {
			throw new ActionFailedException(
					"Failed to cancel a new question for product.");
		}

		lm.logOutLicenseManager();

	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "NL2";
		question.questDisplayText = "Auto Test QuestionNN";
		question.locationClass = "All";
		question.licenseYearFrom = String.valueOf(DateFunctions
				.getCurrentYear());
		question.licenseYearTo = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.displayOrder = "929";
		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
	}

	private boolean verifyCancelQuestionSuccess(QuestionInfo question) {
		LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage
				.getInstance();

		logger.info("Verify add question success or not ...");
		// Get Product Question Information by the input information,If the
		// method throw ItemNotFoundException ,it shows that it can not find the
		// question information.

		String id = privilegeQuestionPg
				.getProductQuestionAssignmentID(question);
		if ("".equals(id)) {
			logger.info("Cancel a new question for product successful.");
			return true;
		}

		return false;
	}

}
