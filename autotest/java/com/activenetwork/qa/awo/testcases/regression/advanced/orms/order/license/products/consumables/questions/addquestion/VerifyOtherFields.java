/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.questions.addquestion;

import java.util.ArrayList;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verify the steps taken by the User to add a
 *              Question that needs to be asked during the sales work flow
 *              of a particular Product.
 *              Check point: message 13,16 in the SPEC 
 * @Preconditions:need a question and a consumable
 * @SPEC:Use Case Specification: Add Product Question
 * @Task#:AUTO-573
 * 
 * @author szhou
 * @Date Sep 5, 2011
 */
public class VerifyOtherFields extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	private String message1;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to consumable question page
		this.gotoConsumableQuestionPg();

		question.questAnswers = new String[1];
		question.questAnswers[0] = "Yes";
		Map<String, String> id = lm.addQuestionsForProduct(question);
		question.id = id.get(question.locationClass);
		// Acceptable Answers apply and less than two of the Acceptable Answers
		// have been selected.
		boolean errormess = this.verifyAddQuestionError(message1);System.out.println(errormess);

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
		errormess &= this.verifyAddQuestionError(message1);System.out.println(errormess);

		this.gotoConsumableQuestionPageFromAddQuestionPage();

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

		consumable.name = "Auto_test_For_C_Other";
		consumable.code = "004";
		consumable.description = "Auto_test_For_C_Other";
		consumable.orderType = "Donation";

		question.displayOrder = "229";
		question.questDisplayText = "Auto Test Question";
		question.originalOption = "Required";
		question.locationClass = "All";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);

		message1 = "At least two Acceptable Answers must be selected. Please select the Acceptable Answers from the list provided.";
	}

	private void gotoConsumableQuestionPageFromAddQuestionPage() {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget.getInstance();
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();

		logger.info("go to Consumable detail page from add question page...");
		questionPg.clickCancel();
		consumableQuestionPg.waitLoading();

	}

	private boolean verifyAddQuestionError(String error) {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();
		String message = questionPg.getErrorMessage();
		
		logger.info("Verify add question for Consumable error message."+error);
		if (error.equals(message)) {
			logger
					.info("The error message on the add product question page displayed correct.");
		} else {
			logger
					.error("The error message on the add product question page displayed wrong."+message);
			return false;
		}
		return true;
	}
	
	private void gotoConsumableQuestionPg() {
		LicMgrConsumableListPage listPg = LicMgrConsumableListPage.getInstance();
		lm.gotoConsumableSearchListPageFromTopMenu();
		consumable.id = listPg.getConsumableIdByName(consumable.name);
		
		// can not find ID by given consumable name
		if(null == consumable.id || "".equals(consumable.id)) {
			
			// add new consumable with name is "Auto_test_For_C_Other"
			lm.addConsumableProduct(consumable);
			consumable.id = listPg.getConsumableIdByName(consumable.name);
		}
		
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
	}

}
