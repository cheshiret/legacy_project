/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.questions.add;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrProductQuestionsConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the steps taken by the User to add a new Question for the Contract.
 * @Preconditions:
 * @SPEC:Use Case Specification: Add Question 
 * @Task#:AUTO-505
 * 
 * @author szhou
 * @Date  Aug 26, 2011
 */
public class VerifyAddSuccess extends LicenseManagerTestCase{

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to question page
		lm.gotoQuestionConfigPgFromTopMenu();
		
		// add question for contract successful
		lm.addQuestionsForContract(question);
		boolean succ = this.verifyAddQuestionSuccess(question);
		
		if (!succ) {
			throw new ActionFailedException(
					"Failed to add a new question for contract.");
		}

		lm.logOutLicenseManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		question.questDisplayText = "Add New Question For Automation Test "
				+ seq;
		question.questPrintText = "QA Question";
		question.answerType = "User Entry";//"Text Input"; 305 changed
		question.minLength = "3";
		question.maxLength = "5";
		question.createUser=DataBaseFunctions.getLoginUserName(login.userName);
		question.createLocation=login.location.split("/")[1];
		question.createDateTime=DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM dd yyyy");
		
	}
	
	private boolean verifyAddQuestionSuccess(QuestionInfo question) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrProductConfigurationQuestionsDetailPage detailPg = LicMgrProductConfigurationQuestionsDetailPage.getInstance();
		boolean result=true;
		
		logger.info("Verify add question success or not ...");

		logger.info("View question list ...");
		if (this.compareQuestionListInfo(question)) {
			logger.info("Add a new question for contract successful.");
		} else {
			logger.info("Failed to add " + question.questDisplayText
					+ " question for contract.");
			result= false;
		}
		
		logger.info("View question detail information ...");
		prodConfPg.clickLink(question.questDisplayText);
		ajax.waitLoading();
		detailPg.waitLoading();
		if (detailPg.compareQuestionDetailInfo(question)) {
			logger
					.info("Add question successful on the question detail page.");
		} else {
			logger.error("Failed to add '" + question.questDisplayText
					+ " ' question on the question detail page.");
			result= false;
		}
		
		detailPg.clickBack();
		ajax.waitLoading();
		prodConfPg.waitLoading();
		return result;
	}
	
	private boolean compareQuestionListInfo(QuestionInfo Info){
		LicMgrProductQuestionsConfigurationPage prodQuestionConfPg = LicMgrProductQuestionsConfigurationPage
		.getInstance();
		boolean result = true;
		
		List<String> text = prodQuestionConfPg.getQuestionInfo(question.questDisplayText);
		if(!text.get(0).equals(Info.questDisplayText)){
			logger.error("The expected display text is not " + Info.questDisplayText + ".");
			result &= false;
		}
        if(!text.get(1).equals(Info.questPrintText)){
        	logger.error("The expected print text is not " + Info.questPrintText + ".");
			result &= false;
		}
        if(!text.get(2).equals(Info.answerType)){
        	logger.error("The expected answer type is not " + Info.answerType + ".");
			result &= false;
		}
        if(!text.get(3).equals(Info.hipQuestion)){
        	logger.error("The expected hip question is not " + Info.hipQuestion + ".");
			result &= false;
		}

		return result;
	}

}
