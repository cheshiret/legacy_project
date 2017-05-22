package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies.questions.edit;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This test case is used to verify cancel action when edit product
 *                   question for supply. 1. add question for supply 2. edit
 *                   question assignment, verify cancel action 3. de-active
 *                   question assignment, verify de-active info
 * @Preconditions: 1. This supply is not HIP
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-574
 * 
 * @author VZhang1
 * @Date Jun 20, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase {

	private String supplyCd = "";
	private boolean pass = true;
	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget
			.getInstance();
	private LicMgrSupplyQuestionPage supplyQuestionPg = LicMgrSupplyQuestionPage
			.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoSupplyQuestionPgFromTopMenu(supplyCd);
		
		//clean up
		lm.deactiveAllActiveSupplyQuestions();

		question.assignID = lm.addQuestionsForProduct(question).get(
				question.locationClass);
		lm.gotoEditProductQuestionPg(question.assignID);

		question.displayOrder = "10";
		question.collectionMethod = "Once per Transaction";
		question.originalOption = "Required";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyCancelButton();
		editQuestionWidget.clickOK();
		supplyQuestionPg.waitLoading();

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

		supplyCd = "a08";

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

	private void verifyCancelButton() {
		logger.info("Verify Cancel Button Function Correct.");

		editQuestionWidget.clickCancel();
		Object page = browser.waitExists(editQuestionWidget, supplyQuestionPg);
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
