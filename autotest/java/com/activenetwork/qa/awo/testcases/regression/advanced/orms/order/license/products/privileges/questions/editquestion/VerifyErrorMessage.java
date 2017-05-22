package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.questions.editquestion;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:This test case is used to verify error message when edit product
 *                   question for privilege. 1. add question for privilege 2.
 *                   edit question assignment, verify error message 3. de-active
 *                   question assignment
 * @Preconditions: 1. This privilege is not HIP 2. This privilege should have
 *                 assigned print document template, and this case need to
 *                 update print document info 3. Prepare two questions, as the
 *                 following info: question1.displayText = How many Geese did
 *                 you bag(auto test)? question1.answserType = Radio Button
 *                 question1.acceptAnswer = Yes (next action: Ask another
 *                 question "Did you test question(auto test1)?")
 *                 question1.acceptAnswer = No question1.acceptAnswer = Other
 *
 *                 question2.displayText = Did you test question(auto test1)?
 *                 question2.answserType = Radio Button question2.acceptAnswer =
 *                 Yes question2.acceptAnswer = No question2.acceptAnswer =
 *                 Other
 *
 * @SPEC:Edit Product Question.doc
 * @Task#:Auto-574
 *
 * @author VZhang1
 * @Date Jun 20, 2011
 */
public class VerifyErrorMessage extends LicenseManagerTestCase {

	private String msg1, msg2, msg3, msg5, msg6, msg7, msg8;

	private String msg4;

	private boolean pass = true;
	private LicMgrPrivilegeQuestionPage questionPage = LicMgrPrivilegeQuestionPage
			.getInstance();
	private LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget
			.getInstance();

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

		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 2);
		question.licenseYearTo = String
				.valueOf(DateFunctions.getCurrentYear() + 1);
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
		question.displayOrder = "";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg4); // spec:message10 msg4 DEFECT-31974

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
		question.dependentAnswers.add(new String[] { "Did you test question",
				"Yes" });
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg7); // spec:message16

		question.dependentAnswers = new ArrayList<String[]>();
		question.dependentAnswers.add(new String[] { "Did you test question",
				"Yes", "No" });
		question.printDocuments = new String[2];
		question.printDocuments[0] = "DocumentTest,All,Duplicate,All";//"136380168-DocumentTest,All,Duplicate,All";
		question.printDocuments[1] = "DocumentTest,All,Duplicate,All";//"136380168-DocumentTest,All,Duplicate,All";
		editQuestionWidget.setEditQuestionInfo(question);
		this.verifyErrorMessage(msg8); // spec:message18

		editQuestionWidget.clickCancel();
		questionPage.waitLoading();
		lm.deactivateProductQuestionAssignment(question.assignID);

		if (!pass) {
			throw new ErrorOnPageException(
					"For some check points failed,please check error log.");
		}

		lm.logOutLicenseManager();

	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.code = "V91";

		question.status = "Active";
		question.displayOrder = "13";
		question.questDisplayText = "How many Geese did you bag(auto test)?";
		question.originalOption = "Optional";
		question.locationClass = "03-Lakes Offices";
		question.collectionMethod = "Once per Product";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.printDocuments = new String[1];
		question.printDocuments[0] = "DocumentTest,All,Transfer,All";//"136380170-DocumentTest,All,Transfer,All";
		question.questAnswers = new String[3];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.questAnswers[2] = "Other";
		question.dependentAnswers = new ArrayList<String[]>();
		question.dependentAnswers.add(new String[] { "Did you test question(auto test)?",
				"Yes", "No", "Other" });

		msg1 = "The License Year From must not be later than the License Year To. Please change your entries.";
		msg2 = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
		msg3 = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
		msg4 = "The Display Order is required. Please specify the Display Order.";
		msg5 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
		msg6 = "At least two Acceptable Answers must be selected. Please select the Acceptable Answers from the list provided.";
		msg7 = "At least two Acceptable Answers must be selected for a Dependent Question. "
				+ "Please select the Acceptable Answers for the Dependent Question from the list provided.";
		msg8 = "A Product Fulfillment Document record cannot be selected more than once. Please change your entries.";
	}

	private void verifyErrorMessage(String expectMsg) {
		editQuestionWidget.clickOK();

		logger.info("Verify Error Messages.");
		Object pages = browser.waitExists(editQuestionWidget, questionPage);
		if (pages == questionPage) {
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
			//test
			throw new ErrorOnPageException("Error message is not correct. Expect error message should be "
					+ expectMsg
					+ " . But acutul error message is "
					+ actualMsg);
			//test
		}
	}

}
