package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.question;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrProductQuestionsConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case verifies the steps taken by the User to add a new Question.
 * @Preconditions:
 * @SPEC:Add Question.doc & View Question Details.doc & View Question List.doc
 * @Task#:Auto-844
 * 
 * @author nding1
 * @Date  2011-12-28
 */
public class AddAndViewQuestion extends LicenseManagerTestCase {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();
		
		// add new question for contract
		lm.addQuestionsForContract(question);
		
		// verify in the question list
		this.verifyInQuestionList();
		
		// verify detail
		this.verifyQuestionDetail();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		question.questDisplayText = "Add New Question For Automation Test "+ seq;
		question.questPrintText = "QA Question";
		question.answerType = "User Entry";
		question.minLength = "3";
		question.maxLength = "5";
		question.createUser=com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		question.createLocation=login.location.split("/")[1];
		question.createDateTime=DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM dd yyyy");
	}

	/**
	 * verify question info in the question list
	 */
	private void verifyInQuestionList() {
		LicMgrProductQuestionsConfigurationPage prodQuestionConfPg = LicMgrProductQuestionsConfigurationPage.getInstance();
		
		logger.info("Verify add question successfully or not.");		
		boolean result = prodQuestionConfPg.verifyNewQuestionInfo(question);

        if(!result) {
        	logger.info("Failed to add: " + question.questDisplayText + " question for contract.");
        	throw new ErrorOnPageException("Failed to add: " + question.questDisplayText + " question for contract.");
        }
        logger.info("Add a new question for contract successfully in the questions list page.");
	}

	/**
	 * verify detail info of the new added question
	 */
	private void verifyQuestionDetail() {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage.getInstance();
		LicMgrProductConfigurationQuestionsDetailPage detailPg = LicMgrProductConfigurationQuestionsDetailPage.getInstance();

		boolean result=true;		
		prodConfPg.clickLink(question.questDisplayText);
		ajax.waitLoading();
		detailPg.waitLoading();
		
		// verify detail of question info
		result = detailPg.compareQuestionDetailInfo(question);

		if (!result) {
        	logger.info("Failed to add: " + question.questDisplayText + " question on the question detail page.");
        	throw new ErrorOnPageException("Failed to add: " + question.questDisplayText + " question on the question detail page.");
		}
		
		// get all the assignments
		List<List<String>> assigementList = detailPg.getAllQuestionAssignments(false);
		
		// verify question assignment
		if(assigementList.size() != 0) {
        	logger.info("The new added question should not have assignment product question.");
        	throw new ErrorOnPageException("The new added question should not have assignment product question.");
		}
		
		detailPg.clickBack();
		ajax.waitLoading();
		prodConfPg.waitLoading();
		logger.info("Add question successfully on the question detail page.");
	}
}
