package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies.questions.edit;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:This test case is used to verify edit product question for
 *                   supply. 1. add question for supply 2. edit question
 *                   assignment successfully, verify edit success 3. de-active
 *                   question assignment, verify de-active info
 * @Preconditions: 1. This supply is not HIP 2. Prepare two questions, as the
 *                 following info: question1.displayText = How many Geese did
 *                 you bag(auto test)? question1.answserType = Radio Button
 *                 question1.acceptAnswer = Yes (next action: Ask another
 *                 question "Did you test question(auto test)?")
 *                 question1.acceptAnswer = No question1.acceptAnswer = Other
 *
 *                 question2.displayText = Did you test question(auto test)?
 *                 question2.answserType = Radio Button question2.acceptAnswer =
 *                 Yes question2.acceptAnswer = No question2.acceptAnswer =
 *                 Other
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-574
 *
 * @author VZhang1
 * @Date Jun 20, 2011
 */
public class VerifySuccess extends LicenseManagerTestCase {

	private boolean pass = true;
	private String supplyCd = "";
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
		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.dependentAnswers = new ArrayList<String[]>();
		question.dependentAnswers.add(new String[] { "Did you test question(auto test)?",
				"Yes", "No" });
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyEditSuccess();

		question.status = "Inactive";
		lm.deactivateProductQuestionAssignment(question.assignID);
		lm.searchProductQustionByCriteria(question);
		lm.gotoEditProductQuestionPg(question.assignID);
		question.lastUpdateUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		question.lastUpdateLocation = login.location.split("/")[1].trim();
		this.verifyInactiveAction();

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

		supplyCd = "a10";

		question.status = "Active";
		question.displayOrder = "5";
		question.questDisplayText = "How many Geese did you bag(auto test)?";
		question.originalOption = "Optional";
		question.locationClass = "20-MDWFP Lifetime";
		question.collectionMethod = "Once per Product";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.questAnswers = new String[3];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.questAnswers[2] = "Other";
		question.dependentAnswers = new ArrayList<String[]>();
		question.dependentAnswers.add(new String[] { "Did you test question(auto test)?",
				"Yes", "No", "Other" });
		question.lastUpdateUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		question.lastUpdateLocation = login.location.split("/")[1].trim();
		question.lastUpdateDateTime = DateFunctions.getToday("MMM d yyyy",DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
	}

	// verify edit success
	private void verifyEditSuccess() {
		editQuestionWidget.clickOK();
		logger.info("Verify edit success.");

		Object pages = browser.waitExists(editQuestionWidget, supplyQuestionPg);
		if (pages == editQuestionWidget) {
			pass = false;
			logger.error("Expect page is consumable question page.");
		}

		String oldAssignID = question.assignID;
		question.assignID = supplyQuestionPg
				.getProductQuestionAssignmentID(question);
		lm.gotoEditProductQuestionPg(question.assignID);

		if (!editQuestionWidget.getDisplayOrder().equals(question.displayOrder)) {
			pass = false;
			logger
					.error("Edit display order not correct. Expect display order should be "
							+ question.displayOrder
							+ ", but actaul is "
							+ editQuestionWidget.getDisplayOrder());
		}

		if (!editQuestionWidget.getOriginalOption().equals(
				question.originalOption)) {
			pass = false;
			logger
					.error("Edit original option not correct. Expect orginal option should be "
							+ question.originalOption
							+ ", but acutal is "
							+ editQuestionWidget.getOriginalOption());
		}

		if (!editQuestionWidget.getCollectionMethod().equals(
				question.collectionMethod)) {
			pass = false;
			logger
					.error("Edit collection method not correct. Expect collection method should be "
							+ question.collectionMethod
							+ ", but acutal is "
							+ editQuestionWidget.getCollectionMethod());
		}

		if (!editQuestionWidget.getLicenseYearFrom().equals(
				question.licenseYearFrom)) {
			pass = false;
			logger
					.error("Edit license from year not correct. Expect lincense year from should be "
							+ question.licenseYearFrom
							+ ", but actual licnese year from is "
							+ editQuestionWidget.getLicenseYearFrom());
		}

		if (!"".equals(question.licenseYearTo)
				&& !editQuestionWidget.getLicenseYearTo().equals(
						question.licenseYearTo)) {
			pass = false;
			logger
					.error("Edit license to year not correct. Expect license year to should be "
							+ question.licenseYearTo
							+ ", but actual license year to is "
							+ editQuestionWidget.getLicenseYearTo());
		}

		List<String> answer = editQuestionWidget.getAcceptableAnswers();
		if (answer.size() != question.questAnswers.length) {
			pass = false;
			logger.error("Edit acceptable answer not correct."+question.questAnswers.length+"answer.size is :"+answer.size());
		} else {
			for (int i = 0; i < answer.size(); i++) {
				if (!answer.get(i).equals(question.questAnswers[i])) {
					pass &= false;
					logger
							.error("Edit acceptable answer not correct. Expect acceptable answer should be "
									+ question.questAnswers[i]
									+ ", but actual is " + answer.get(i));
				}
			}
		}

		for (int i = 0; i < question.dependentAnswers.size(); i++) {
			List<String> dependentAnswer = editQuestionWidget
					.getDependentQuestionAnswer(question.dependentAnswers
							.get(i)[0]);
			for (int j = 0; j < dependentAnswer.size(); j++) {
				if (!dependentAnswer.get(j).equals(
						question.dependentAnswers.get(i)[j + 1])) {
					pass &= false;
					logger.error("Edit dependent answer not correct. Expect dependent answer should be "
									+ question.dependentAnswers.get(i)[j + 1]
									+ ", but actual is "
									+ dependentAnswer.get(j));
				}
			}
		}

		editQuestionWidget.clickOK();
		supplyQuestionPg.waitLoading();

		question.status = "Inactive";
		lm.searchProductQustionByCriteria(question);
		if (!supplyQuestionPg.questionAssignmentIDExists(oldAssignID)) {
			pass = false;
			logger.error("After edited, the older question assignment ID should inactive.");
		}
		question.status = "Active";
		lm.searchProductQustionByCriteria(question);
	}

	// Verify Inactive action
	private void verifyInactiveAction() {
		logger.info("Verify inactivate action.");

		if (!editQuestionWidget.getStatus().equals(question.status)) {// verify status
			pass = false;
			logger.error("Edit status not correct.");
		}

		if (!editQuestionWidget.getLastUpdateUser().trim().equals(
				question.lastUpdateUser)) {// verify last update user
			pass = false;
			logger.error("Last update user is not correct.");
		}

		if (!editQuestionWidget.getLastUpdateLocation().trim().equals(
				question.lastUpdateLocation)) {// verify last update location
			pass = false;
			logger.error("Last update location is not correct.");
		}

		if (!editQuestionWidget.getLastUpdateDate().contains(question.lastUpdateDateTime)) {// verify last update date
			pass = false;
			logger.error("Last update date is not correct.");
		}

		editQuestionWidget.clickOK();
		supplyQuestionPg.waitLoading();
	}
}
