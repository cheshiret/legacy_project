package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.questions;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verifies the steps taken by the User to add a new consumable Question.
 * @Preconditions:The product need a exist Question in order to add a new product question.
 * And there isn't exist product questions which have the same Display Text and Location Class
 * and overlapping License Year From/To.
 * @SPEC:Add Product Question.doc & View Product Question List.doc
 * @Task#:Auto-844
 * 
 * @author nding1
 * @Date  2011-12-29
 */
public class AddProductQuestion extends LicenseManagerTestCase {
	private QuestionInfo question = new QuestionInfo();
	
	@Override
	public void execute() {

		lm.loginLicenseManager(login);
		
		// go to consumable question page from top menu
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);

		// add a question for product successful
		Map<String, String> id = lm.addQuestionsForProduct(question);
		question.id = id.get(question.locationClass);

		// verify in the questions list
		this.verifyInQuestionList();
		
		// verify detail
		this.verifyQuestionDetail();

		lm.deactivateProductQuestionAssignment(question.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		consumable.id = "5778";
		
		question.displayOrder = "1";
		question.questDisplayText = "Add New Question For Automation Test01";
		question.originalOption = "Required";
		question.locationClass = "All";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		question.effectiveFromDate = DateFunctions.getDateAfterToday(1);
		question.effectiveToDate = DateFunctions.getDateAfterToday(3);
		question.collectionMethod = "Once per Product";
		question.createUser = DataBaseFunctions.getLoginUserName(login.userName).replaceAll(" ", "");
		question.createLocation = login.location.split("/")[1];
		question.createDateTime = DateFunctions.getToday();
	}

	/**
	 * verify question info in the question list
	 */
	private void verifyInQuestionList() {
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();

		logger.info("Verify add question successfully or not ...");
		boolean result = consumableQuestionPg.compareQuestionRecords(question);
		
		if(!result) {
        	logger.info("Failed to add: " + question.questDisplayText + " question for the consumable.");
        	lm.deactivateProductQuestionAssignment(question.id);
        	throw new ErrorOnPageException("Failed to add: " + question.questDisplayText
					+ " question for the consumable.");
		}
		
        logger.info("Add a new question for the consumable successfully in the questions list page.");
	}
	
	/**
	 * verify detail info of the new added question
	 */
	private void verifyQuestionDetail() {
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage.getInstance();
		LicMgrEditProductQuestionWidget editQuestion = LicMgrEditProductQuestionWidget.getInstance();
		
		logger.info("Verify new added question details.");
		
		// go to detail page
		consumableQuestionPg.clickQuestionAssignmentID(question.id);
		editQuestion.waitLoading();
		
		boolean result = editQuestion.compareQuestionDetailInfo(question);
		
		editQuestion.clickOK();
		consumableQuestionPg.waitLoading();
		if(!result) {

        	logger.info("The detail of new added question is not correct.Please check the log.");
        	lm.deactivateProductQuestionAssignment(question.id);
        	throw new ErrorOnPageException("The detail of new added question is not correct.Please check the log.");
		}

        logger.info("The detail of new added question is correct.");
	}
}
