package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.questions.editquestion;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This test case is used to verify cancel action when edit product
 *                   question for privilege. 1. add question for privilege 2.
 *                   edit question assignment, verify cancel action
 * @Preconditions: This privilege is not HIP
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-574
 * 
 * @author VZhang1
 * @Date Jun 20, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase {

	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget
			.getInstance();
	private LicMgrPrivilegeQuestionPage questionPage = LicMgrPrivilegeQuestionPage
			.getInstance();
	private boolean pass = true;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilege.code);

		List<String> questionIDs = questionPage
				.getProductQuestionIDForCleanUp(question);
		if (questionIDs.size() > 0) {
			for (String questionID : questionIDs) {
				lm.deactivateProductQuestionAssignment(questionID);
			}
		}

		question.assignID = lm.addQuestionsForProduct(question).get(
				question.locationClass);
		lm.gotoEditProductQuestionPg(question.assignID);

		// verify cancel button
		question.displayOrder = "10";
		question.collectionMethod = "Once per Transaction";
		question.originalOption = "Required";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyCancelAction();
		editQuestionWidget.clickOK();
		questionPage.waitLoading();

		lm.deactivateProductQuestionAssignment(question.assignID);

		if (!pass) {
			throw new ErrorOnPageException(
					"For some check points failed,please check error log.");
		}

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.code = "V91";

		question.displayOrder = "19";
		question.questDisplayText = "Auto Test Question";
		question.originalOption = "Optional";
		question.locationClass = "06-State Parks Agent";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.collectionMethod = "Once per Product";
	}

	// verify cancel button
	private void verifyCancelAction() {

		logger.info("Verify Cancel Button Function Correct.");

		editQuestionWidget.clickCancel();
		Object page = browser.waitExists(editQuestionWidget, questionPage);
		if (page == editQuestionWidget) {
			pass = false;
			logger.error("Expect page is question page.");
		}

		lm.gotoEditProductQuestionPg(question.assignID);

		if (!editQuestionWidget.getStatus().equals("Active")) {
			pass = false;
			logger.error("Expect status should be active.");
		}

		if (editQuestionWidget.getDisplayOrder().equals(question.displayOrder)) {
			pass = false;
			logger.error("Expect display order should be "
					+ question.displayOrder + ", but acutal is "
					+ editQuestionWidget.getDisplayOrder());
		}

		if (editQuestionWidget.getOriginalOption().equals(
				question.originalOption)) {
			pass = false;
			logger.error("Expect original option should be "
					+ question.originalOption + ", but acutal is  "
					+ editQuestionWidget.getOriginalOption());
		}

		if (editQuestionWidget.getCollectionMethod().equals(
				question.collectionMethod)) {
			pass = false;
			logger.error("Expect collection method should be "
					+ question.collectionMethod + ", but acutal is "
					+ editQuestionWidget.getCollectionMethod());
		}
	}

}
