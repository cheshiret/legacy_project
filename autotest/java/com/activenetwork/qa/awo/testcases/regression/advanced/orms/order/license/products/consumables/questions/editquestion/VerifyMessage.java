package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.questions.editquestion;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:This test case is used to verify edit product question for consumable. 
 * 						1. add question for consumable;
 * 						2. edit question assignment, verify error message;
 * 						3. de-activate question assignment, verify de-active info
 * 
 * @Preconditions: 1. This consumable is not HIP;
 * 							2. Prepare two questions, as below:
 * 
 * 							Question1: this must be created first 
 * 								displayText = Did you test question(auto test)?
 *                 				answserType = Radio Button
 *                 				acceptAnswer = Yes, No, Other
 *                 
 *                 			Question2:
 *                 				displayText = How many Geese did you bag(auto test)?
 *                 				answserType = Radio Button
 *                 				acceptAnswer = Yes (next action: Ask another question - "Did you test question(auto test)?")
 *                 				acceptAnswer = No
 *								acceptAnswer = Other
 *                 
 * @SPEC: Edit Product Question.doc
 * @Task#: Auto-574
 *
 * @author VZhang1
 * @Date Jun 20, 2011
 */
public class VerifyMessage extends LicenseManagerTestCase {

	private boolean pass = true;
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7;
	private LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage
			.getInstance();
	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget
			.getInstance();

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

		question.assignID = lm.addQuestionsForProduct(question).get(question.locationClass);
		lm.gotoEditProductQuestionPg(question.assignID);

		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 2);
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg1);// spec:message3

		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.effectiveFromDate = "";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg2);// spec:message4

		question.effectiveFromDate = DateFunctions.getDateAfterToday(12);
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg3);// spec:message7

		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		if(!MiscFunctions.blockByDefect()){// DEFECT-31974 furture release
			question.displayOrder = "";
			editQuestionWidget.setEditQuestionInfo(question);
			this.verifyErrorMessage(msg4); // spec:message10
		}

		question.displayOrder = "0";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg5); // spec:message10

		question.displayOrder = "10";
		question.questAnswers[1] = "";
		question.questAnswers[2] = "";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg6); // spec:message13

		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.dependentAnswers = new ArrayList<String[]>();
		question.dependentAnswers.add(new String[] { "Did you test question","Yes" });
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg7);// spec:message16

		editQuestionWidget.clickCancel();
		consumableQuestionPg.waitLoading();
		lm.deactivateProductQuestionAssignment(question.assignID);

		if (!pass) {
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		// initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		consumable.name = "Auto_test_For_C_Message";
		consumable.code = "007";
		consumable.description = "Auto_test_For_C_Message";
		consumable.orderType = "Donation";

		// initial question info
		question.status = "Active";
		question.displayOrder = "13";
		question.questDisplayText = "How many Geese did you bag(auto test)?";
		question.originalOption = "Optional";
		question.locationClass = "03-Lakes Offices";
		question.collectionMethod = "Once per Product";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.questAnswers = new String[3];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.questAnswers[2] = "Other";
		question.dependentAnswers = new ArrayList<String[]>();
		question.dependentAnswers.add(new String[] { "Did you test question",
				"Yes", "No", "Other" });

		msg1 = "The Fiscal Year From must not be later than the Fiscal Year To. Please change your entries.";
		msg2 = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
		msg3 = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
		msg4 = "The Display Order is required. Please specify the Display Order.";
		msg5 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
		msg6 = "At least two Acceptable Answers must be selected. Please select the Acceptable Answers from the list provided.";
		msg7 = "At least two Acceptable Answers must be selected for a Dependent Question. Please select the Acceptable Answers for the Dependent Question from the list provided.";

	}

	private void verifyErrorMessage(String expectMsg) {
		editQuestionWidget.clickOK();

		Object pages = browser.waitExists(editQuestionWidget, consumableQuestionPg);
		if (pages == consumableQuestionPg) {
			pass = false;
			logger.error("Expect page is edit question widget page.");
		}

		String actualMsg = editQuestionWidget.getErrorMessage();
		if (null == actualMsg || !actualMsg.equals(expectMsg)) {
			pass = false;
			logger.error("Error message is not correct. Expect error message should be '"
							+ expectMsg
							+ "'. But acutul error message is '"
							+ actualMsg + "'.");
		}
	}

	private void gotoConsumableQuestionPg() {
		LicMgrConsumableListPage listPg = LicMgrConsumableListPage.getInstance();
		lm.gotoConsumableSearchListPageFromTopMenu();
		consumable.id = listPg.getConsumableIdByName(consumable.name);

		// can not find ID by given consumable name
		if(null == consumable.id || "".equals(consumable.id)) {

			// add new consumable with name is "Auto_test_For_C_Message"
			lm.addConsumableProduct(consumable);
			consumable.id = listPg.getConsumableIdByName(consumable.name);
		}

		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
	}
}
