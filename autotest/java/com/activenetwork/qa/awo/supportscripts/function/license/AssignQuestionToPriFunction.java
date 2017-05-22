package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AssignQuestionToPriFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private QuestionInfo questionInfo = new QuestionInfo();
	LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage.getInstance();
	LicMgrAddProductQuestionWidget questionWidget = LicMgrAddProductQuestionWidget.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		questionInfo = (QuestionInfo)param[2];
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
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoPrivilegeQuestionPgFromTopMenu(questionInfo.proCode);
		lm.addQuestionsForProduct(questionInfo);
		newAddValue = this.verifyResult();
	}
	
	public String verifyResult() {
		String qusAssignId = "";
		if(questionWidget.exists()){
			String errMsg = questionWidget.getErrorMessage();
			questionWidget.clickCancel();
			ajax.waitLoading();
			privilegeQuestionPg.waitLoading();
			throw new ErrorOnPageException("[FAILED]Assign product to privilege:privilege code ="+questionInfo.proCode+",display text="+questionInfo.questDisplayText+ ", due to:" + errMsg);
		}else{
			qusAssignId = privilegeQuestionPg.getProductQuestionAssignmentID(questionInfo);
			if(!StringUtil.notEmpty(qusAssignId))
			{
				throw new ErrorOnPageException("[FAILED]Assign product to privilege:privilege code ="+questionInfo.proCode+",display text="+questionInfo.questDisplayText );
			}else{
				logger.info("[PASSED]Assign product to privilege:privilege code ="+questionInfo.proCode+",display text="+questionInfo.questDisplayText);
		    }
		}
		
		return qusAssignId;
	}
}
