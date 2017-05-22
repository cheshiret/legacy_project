/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.questions.addquestion;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verify the steps taken by the User to add a
 *              Question that needs to be asked during the sales work flow
 *              of a particular Product.
 *              Check point: message 13,16,17,18 in the SPEC 
 * @Preconditions:need a question and a privilege
 * @SPEC:Use Case Specification: Add Product Question
 * @Task#:AUTO-573
 * 
 * @author szhou
 * @Date  Sep 5, 2011
 */
public class VerifyOtherFields extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	private String privilegeCode;
	private String message1, message2;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to product detail page
		lm.gotoPrivilegeQuestionPgFromTopMenu(privilegeCode);
		
		question.questAnswers = new String[1];
		question.questAnswers[0] = "Yes";
		lm.addQuestionsForProduct(question, false);
		// Acceptable Answers apply and less than two of the Acceptable Answers have been selected.
		boolean errormess = this.verifyAddQuestionError(message1);
		
		question.questDisplayText = "How many Doves did you bag (auto test)?";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+1);
		question.dependentAnswers = new ArrayList<String[]>();
		String[] answser = new String[2];
		answser[0] = "Auto Test Question";
		answser[1] = "Yes";
		question.dependentAnswers.add(answser);
		lm.addQuestionsForProduct(question, false);
		// A Dependent Question applies and Acceptable Answers apply to the
		// Dependent Question and less than two of the Acceptable Answers have
		// been selected.
		errormess &= this.verifyAddQuestionError(message1);
		
		question.questDisplayText = "Auto Test Question";
		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.printDocuments = new String[2];
		String currentYear = Integer.toString(DateFunctions.getCurrentYear());
		question.printDocuments[0] = "Harvest DOC,All,Original," + currentYear + " to " + currentYear;//"136975493-Harvest DOC,All,Original,2012 to 2012";
		question.printDocuments[1] = "Harvest DOC,All,Original," + currentYear + " to " + currentYear;//"136975493-Harvest DOC,All,Original,2012 to 2012";
		lm.addQuestionsForProduct(question, false);
		// The option to Print the Question and Answer in Documents has been
		// selected, i.e. "Yes", and the same Product Fulfillment Document
		// record has been specified more than once.
		errormess &= this.verifyAddQuestionError(message2);
			
		this.gotoPrivilegeQuestionPageFromAddQuestionPage();

		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add product question page displayed wrong.");
		}
		
		lm.logOutLicenseManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "A10";
	
		question.displayOrder = "229";
		question.questDisplayText = "How many Doves did you bag (auto test)?";;
		question.originalOption = "Required";
		question.locationClass = "All";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		
		message1 = "At least two Acceptable Answers must be selected. Please select the Acceptable Answers from the list provided.";
		message2 = "A Product Fulfillment Document record cannot be selected more than once. Please change your entries.";
		
	}
	
	private void gotoPrivilegeQuestionPageFromAddQuestionPage() {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget.getInstance();
		LicMgrPrivilegeQuestionPage questionPage = LicMgrPrivilegeQuestionPage.getInstance();

		logger.info("go to privilege detail page from add question page...");
		questionPg.clickCancel();
		questionPage.waitLoading();

	}
	
	private boolean verifyAddQuestionError(String error) {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();
		String message = questionPg.getErrorMessage();

		logger.info("Verify add question for privilege error message."+error);
		if (error.equals(message)) {
			logger.info("The error message on the add product question page displayed correct.");
		} else {
			logger.error("The error message on the add product question page displayed wrong."+message);
			//Test
			throw new ErrorOnPageException("The error message on the add product question page displayed wrong."+message);
			//Test
			//return false;
		}
		return true;
	}
}
