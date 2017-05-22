/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.questions.addquestion;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verify the steps taken by the User to add a
 *              Question that needs to be asked during the sales work flow
 *              of a particular Product. 
 *              Check point:add question successful and verify the question
 *                          information
 * @Preconditions:need a question
 * @SPEC:Use Case Specification: Add Product Question
 * @Task#:AUTO-573
 * 
 * @author szhou
 * @Date  May 19, 2011
 */
public class VerifyAddSuccess extends LicenseManagerTestCase{
	private QuestionInfo question = new QuestionInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to consumable question page
		this.gotoConsumableQuestionPg();

		// add a question for product successful
		Map<String, String> id = lm.addQuestionsForProduct(question);
		question.id = id.get(question.locationClass);
		boolean succ = this.verifyAddQuestionSuccess(question);
		lm.deactivateProductQuestionAssignment(question.id);
		
		if (!succ) {
			throw new ActionFailedException(
					"Failed to add a new question for privilege.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		consumable.name = "Auto_test_For_C_Add";
		consumable.code = "002";
		consumable.description = "Auto_test_For_C_Add";
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
		question.createLocation=login.location.split("/")[1];
		question.createDateTime = DateFunctions.getToday();
	}
	
	private boolean verifyAddQuestionSuccess(QuestionInfo Info) {
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();
		LicMgrEditProductQuestionWidget editQuestion = LicMgrEditProductQuestionWidget
				.getInstance();

		logger.info("Verify add question success or not ...");

		logger.info("View question list ...");
		if (consumableQuestionPg.compareQuestionRecords(Info)) {
			logger
					.info("Add question for product successful on the privilege question page.");
		} else {
			logger.error("Failed to add '" + Info.questDisplayText
					+ " ' text for product on the privilege detail page.");
			return false;
		}

		logger.info("View question detail information ...");
		lm.gotoEditProductQuestionPg(Info.id);
		if (editQuestion.compareQuestionDetailInfo(Info)) {
			logger
					.info("Edit text for product successful on the text detail page.");
		} else {
			logger.error("Failed to add '" + Info.id
					+ " ' for product on the text detail page.");
			return false;
		}

		editQuestion.clickOK();
		ajax.waitLoading();
		consumableQuestionPg.waitLoading();

		return true;
	}
	
	private void gotoConsumableQuestionPg() {
		LicMgrConsumableListPage listPg = LicMgrConsumableListPage.getInstance();
		lm.gotoConsumableSearchListPageFromTopMenu();
		consumable.id = listPg.getConsumableIdByName(consumable.name);
		
		// can not find ID by given consumable name
		if(null == consumable.id || "".equals(consumable.id)) {
			
			// add new consumable with name is "Auto_test_For_C_Add"
			lm.addConsumableProduct(consumable);
			consumable.id = listPg.getConsumableIdByName(consumable.name);
		}
		
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
	}
}
