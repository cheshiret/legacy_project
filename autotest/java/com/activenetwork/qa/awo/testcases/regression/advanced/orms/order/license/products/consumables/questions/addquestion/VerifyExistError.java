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
 *              Check point: message 14,15 in the SPEC
 * @Preconditions:need a question and a privilege
 * @SPEC:Use Case Specification: Add Product Question
 * @Task#:AUTO-573
 * 
 * @author szhou
 * @Date  Sep 27, 2011
 */
public class VerifyExistError extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	private LicMgrConsumableProductQuestionsPage questionPage = LicMgrConsumableProductQuestionsPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to consumable question page
		this.gotoConsumableQuestionPg();

		Map<String, String> id = lm.addQuestionsForProduct(question);
		questionPage.searchQuestios(null, null, null);
		question.id = id.get(question.locationClass);

		lm.addQuestionsForProduct(question, false);
		// There is already an existing Product Question record for the same
		// Product that is also "Active" and has the same values as this record
		// for the following: Question Display Text, Location Class, and
		// License/Fiscal Year From.
		boolean errormess = this.verifyAddQuestionError(getErrorMessage(id
				.get(question.locationClass), "message1"));

		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		lm.addQuestionsForProduct(question, false);
		// The specified License/Fiscal Year From is not "All" and there is an
		// existing Product Question record for the same Product that is also
		// "Active" and where the License/Fiscal Year From is "All" and has the
		// same values as this record for the following: Question Display Text
		// and Location Class.
		errormess = this.verifyAddQuestionError(getErrorMessage(id
				.get(question.locationClass), "message2"));
		this.gotoConsumableQuestionPageFromAddQuestionPage();
		questionPage.searchQuestios(null, null, null);
		lm.deactivateProductQuestionAssignment(id.get(question.locationClass));
		
		question.questDisplayText = "How many Doves did you bag (auto test)?";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+1);
		question.dependentAnswers = new ArrayList<String[]>();
		String[] answser = new String[3];
		answser[0] = "Auto Test Question";
		answser[1] = "Yes";
		answser[2] = "No";
		question.dependentAnswers.add(answser);
		id = lm.addQuestionsForProduct(question);
		question.id = id.get(question.locationClass);
		question.licenseYearFrom = "All";
		lm.addQuestionsForProduct(question, false);
		// The specified License/Fiscal Year From is "All" and there is an
		// existing Product Question record for the same Product that is also
		// "Active" and where the License/Fiscal Year From is not "All" and has
		// the same values as this record for the following: Question Display
		// Text and Location Class.
		errormess &= this.verifyAddQuestionError(getErrorMessage(id
				.get(question.locationClass), "message2"));

		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+1);
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+2);
		lm.addQuestionsForProduct(question, false);
		// The specified License/Fiscal Year From is not "All" and the specified
		// License/Fiscal Year From and To entries overlap with the
		// License/Fiscal Year From and To entries in an existing Product
		// Question record for the same Product that is also "Active" and has
		// the same values as this record for the following: Question Display
		// Text and Location Class.
		errormess &= this.verifyAddQuestionError(getErrorMessage(id
				.get(question.locationClass), "message2"));
		this.gotoConsumableQuestionPageFromAddQuestionPage();
		lm.deactivateProductQuestionAssignment(id.get(question.locationClass));
		
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

		consumable.name = "Auto_test_For_C_ExistErr";
		consumable.code = "003";
		consumable.description = "Auto_test_For_C_ExistErr";
		consumable.orderType = "Donation";
		
		question.displayOrder = "229";
		question.questDisplayText = "Auto Test Question";
		question.originalOption = "Required";
		question.locationClass = "All";
		question.licenseYearFrom = "All";
		question.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		question.effectiveToDate = DateFunctions.getDateAfterToday(10);
		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
		question.collectionMethod="Once per Product";
		
	}
	
	private void gotoConsumableQuestionPageFromAddQuestionPage() {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();

		logger.info("go to privilege detail page from add question page...");
		questionPg.clickCancel();
		consumableQuestionPg.waitLoading();

	}
	
	private String getErrorMessage(String RuleId, String message) {
		String error = "";
		if ("message1".equals(message)) {
			error = "Another active Product Question record "
					+ RuleId
					+ " already exists for the same Product with the same Question Display Text, Location Class, and Fiscal Year From. Duplicate active records are not allowed.";
		} else if ("message2".equals(message)) {
			error = "Another active Product Question record "
					+ RuleId
					+ " already exists for the same Product with the same Question Display Text, Location Class, and with overlapping Fiscal Year From and To entries. Please inactivate that record first or modify the "
					+ RuleId
					+ " Year Range of that record first to eliminate the overlap.";
		}
		return error;
	}
	
	private boolean verifyAddQuestionError(String error) {
		LicMgrAddProductQuestionWidget questionPg = LicMgrAddProductQuestionWidget
				.getInstance();
		String message = questionPg.getErrorMessage();

		logger.info("Verify add question for privilege error message."+error);
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
			
			// add new consumable with name is "Auto_test_For_C_ExistErr"
			lm.addConsumableProduct(consumable);
			consumable.id = listPg.getConsumableIdByName(consumable.name);
		}
		
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
	}
}
