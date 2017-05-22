package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.questions.editquestion;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This test case is used to verify cancel action when edit product
 *                   question for consumable. 1. add question for consumable 2.
 *                   edit question assignment, verify cancel action 3. de-active
 *                   question assignment, verify de-active info
 * @Preconditions: 1. This consumable is not HIP
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-574
 * 
 * @author VZhang1
 * @Date Jun 20, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase {

	private boolean pass = true;
	private LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();
	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);
		
		// go to consumable question page
		this.gotoConsumableQuestionPg();
		
		List<String> questionIDs = consumableQuestionPg
				.getProductQuestionIDForCleanUp(question);
		if (questionIDs.size() > 0) {
			for (String questionID : questionIDs) {
				lm.deactivateProductQuestionAssignment(questionID);
			}
		}

		question.assignID = lm.addQuestionsForProduct(question).get(
				question.locationClass);
		lm.gotoEditProductQuestionPg(question.assignID);

		question.displayOrder = "10";
		question.collectionMethod = "Once per Transaction";
		question.originalOption = "Required";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyCancelButton();
		editQuestionWidget.clickOK();
		consumableQuestionPg.waitLoading();

		if (!pass) {
			throw new ErrorOnPageException(
					"For some check points failed,please check error log.");
		}

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		// initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		consumable.name = "Auto_test_For_C_Edit_Cancel";
		consumable.code = "006";
		consumable.description = "Auto_test_For_C_Edit_Cancel";
		consumable.orderType = "Donation";
		
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

	// verify cancel action
	private void verifyCancelButton() {
		logger.info("Verify Cancel Button Function Correct.");

		editQuestionWidget.clickCancel();
		Object page = browser.waitExists(editQuestionWidget,
				consumableQuestionPg);
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
					+ question.originalOption + ", but acutal is "
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
	
	private void gotoConsumableQuestionPg() {
		LicMgrConsumableListPage listPg = LicMgrConsumableListPage.getInstance();
		lm.gotoConsumableSearchListPageFromTopMenu();
		consumable.id = listPg.getConsumableIdByName(consumable.name);
		
		// can not find ID by given consumable name
		if(null == consumable.id || "".equals(consumable.id)) {
			
			// add new consumable with name is "Auto_test_For_C_Edit_Cancel"
			lm.addConsumableProduct(consumable);
			consumable.id = listPg.getConsumableIdByName(consumable.name);
		}
		
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
	}
}
