package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AssignQuestionToPri extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private QuestionInfo questionInfo = new QuestionInfo();
	LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage.getInstance();
	private String privilegeId = "";

	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}if((!loggedIn )|| (loggedIn && !privilegeQuestionPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
			lm.gotoPrivilegeQuestionPgFromTopMenu(privilegeId);
		}
		lm.addQuestionsForProduct(questionInfo);
		
		this.verifyResult();
		
		contract=login.contract;
	}


	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		privilegeId = dpIter.dpString("privilegeCode");
		questionInfo.displayOrder = dpIter.dpString("displayOrder");
		questionInfo.questDisplayText = dpIter.dpString("prompt");
		questionInfo.originalOption = dpIter.dpString("forOriginal");
		questionInfo.replacementOption = dpIter.dpString("forReplacement");
		questionInfo.transfterOption = dpIter.dpString("forTransfer");
		questionInfo.licenseYearFrom = dpIter.dpString("licenseYearFrom");
		questionInfo.licenseYearTo = dpIter.dpString("licenseToDate");
		questionInfo.collectionMethod = dpIter.dpString("collectionMethod");
		questionInfo.effectiveFromDate = dpIter.dpString("effectiveFromDate");
		if(questionInfo.effectiveFromDate.length()<1){
			questionInfo.effectiveFromDate = DateFunctions.getToday();
		}
		questionInfo.effectiveToDate = dpIter.dpString("effectiveToDate");
		questionInfo.locationClass = dpIter.dpString("locationClass");
		questionInfo.locationClasses = this.splitTextByComma(dpIter.dpString("locationClasses"));
		questionInfo.printDocuments = this.splitTextBySemicolon(dpIter.dpString("printQuestion"));
		questionInfo.questAnswers = this.splitTextByComma(dpIter.dpString("questionAnwsers"));
		questionInfo.dependentAnswers = this.getAnswerDepency();
		
	
		
		logMsg[0]=privilegeId;
		logMsg[1]=questionInfo.questDisplayText;
	}

	
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 2 ; // the start point in the data pool
		endpoint = 2 ; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		logMsg=new String[3];
		logMsg[0]="privilegeCode";
		logMsg[1]="question display text";
		logMsg[2]="result";
	}
	
	/**
	 * split the text by comma
	 * @param text - text need to comma
	 * @return String[] - the text list split by comma
	 */
	private String[] splitTextByComma(String text){
		String[] list = new String[]{};
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}
	
	private String[] splitTextBySemicolon(String text){
		String[] list = new String[]{};
		if(text.contains(";")){
			list =  text.split(";", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}
	
	private List<String[]> getAnswerDepency(){
		List<String[]> answerList = new ArrayList<String[]>();
		String []quesDepency = this.splitTextBySemicolon(dpIter.dpString("quesDepency"));
		for(int i=0;i<quesDepency.length;i++){	
			String [] depency = this.splitTextByComma(quesDepency[i]);
			answerList.add(depency);
		}
		return answerList;
	}
	
	public void verifyResult(){
		LicMgrAddProductQuestionWidget questionWidget = LicMgrAddProductQuestionWidget.getInstance();
		if(!privilegeQuestionPg.exists()){
			logger.error("Assign product to privilege:privilege code ="+privilegeId+",display text="+questionInfo.questDisplayText+questionWidget.getErrorMessage());
			logMsg[2] = "Failed";
		}else{
			logMsg[2]="Pass";
		}
	}

}
