package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies.questions.edit;

import java.util.ArrayList;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:This test case is used to verify edit product question for
 *                   supply. 1. add question for supply 2. edit question
 *                   assignment, verify error message 3. de-active question
 *                   assignment, verify de-active info
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
public class VerifyMessage extends LicenseManagerTestCase {

	private boolean pass = true;
	private String supplyCd = "";
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7;

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

		question.licenseYearFrom = String.valueOf(DateFunctions
				.getCurrentYear() + 2); // "2013"
		question.licenseYearTo = String
				.valueOf(DateFunctions.getCurrentYear() + 1);// "2012"
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg1);// spec:message3

		question.licenseYearFrom = String.valueOf(DateFunctions
				.getCurrentYear()); // "2011"
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
			this.verifyErrorMessage(msg4); // spec:message10 msg4////Defect 33144 has been closed

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
		question.dependentAnswers.add(new String[] { "Did you test question(auto test)?",
				"Yes" });
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg7);// spec:message16

		editQuestionWidget.clickCancel();
		supplyQuestionPg.waitLoading();
		lm.deactivateProductQuestionAssignment(question.assignID);

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

		supplyCd = "a09";

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
		question.dependentAnswers.add(new String[] { "Did you test question(auto test)?",
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

		Object pages = browser.waitExists(editQuestionWidget, supplyQuestionPg);
		if (pages == supplyQuestionPg) {
			pass = false;
			logger.error("Expect page is edit question widget page.");
		}

		String actualMsg = editQuestionWidget.getErrorMessage();
		if (null == actualMsg || !actualMsg.equals(expectMsg)) {
			pass = false;
			logger
					.error("Error message is not correct. Expect error message should be "
							+ expectMsg
							+ " . But acutul error message is "
							+ actualMsg);
		}
	}

}
