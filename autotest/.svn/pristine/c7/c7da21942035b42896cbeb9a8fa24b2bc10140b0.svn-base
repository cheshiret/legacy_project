package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddQuestionsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrProductQuestionsConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddQuestionFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrProductQuestionsConfigurationPage prodConfPg = LicMgrProductQuestionsConfigurationPage.getInstance();
	private QuestionInfo question = new QuestionInfo();
	private String schema = "";
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget.getInstance();
	private String location;
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		question = (QuestionInfo)param[2];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
	}
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if(questionPg.exists()){
			questionPg.clickCancel();
			ajax.waitLoading();
			prodConfPg.waitLoading();
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoQuestionConfigPgFromTopMenu();
		lm.addQuestionsForContract(question);
		this.verifyResult();
		
		newAddValue = lm.getQuestionIDByDisplayText(schema, question.questDisplayText);
	
	}

	private void verifyResult(){
		logger.info("Verify add question successfully or not.");		
		if(questionPg.exists()){
			String errMsg = questionPg.getErrorMessage();
			questionPg.clickCancel();
			ajax.waitLoading();
			prodConfPg.waitLoading();
			throw new ErrorOnPageException("[FAILED]Add question: question display text ="+question.questDisplayText+",question answer type ="+question.answerType+", due to:" + errMsg);
		}else{
			boolean passed = prodConfPg.verifyNewQuestionInfo(question);
			if(!passed)
			{
				throw new ErrorOnPageException("[FAILED]Add question: question display text ="+question.questDisplayText+",question answer type ="+question.answerType );
			}else{
				logger.info("[PASSED]Add question: question display text ="+question.questDisplayText+",question answer type ="+question.answerType);
		    }
		}
	}
}
