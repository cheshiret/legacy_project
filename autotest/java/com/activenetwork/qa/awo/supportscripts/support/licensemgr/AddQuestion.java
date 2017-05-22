package com.activenetwork.qa.awo.supportscripts.support.licensemgr;
/**  
 * @Description:  
 * @Preconditions:  Insert the nextActionValue like "RES SPORTSMAN,HIP;FMAP,HIP"
 *                  Insert the nextAction and acceptAnwsers and default and displayOrder like: "Stop the Transaction,Add Privileges"
 * @SPEC:  
 * @Task#: 
 * @author jwang8  
 * @Date  Mar 16, 2012    
 */

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo.AcceptableAnswers;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddQuestionsWidget;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddQuestion extends SupportCase{

	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage.getInstance();
	private QuestionInfo question = new QuestionInfo();
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !prodConfPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn=true;
			lm.gotoQuestionConfigPgFromTopMenu();
		}
		lm.addQuestionsForContract(question);
		this.verifyResult();
		
		contract=login.contract;
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		question.questDisplayText = dpIter.dpString("displayText");
		question.questPrintText = dpIter.dpString("printText");
		question.answerType = dpIter.dpString("AnswerType");
		question.minLength = dpIter.dpString("minLenth");
		question.maxLength = dpIter.dpString("maxLength");
		question.anwsers = this.getAcceptableAnswers();
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = question.questDisplayText;
		logMsg[2] = question.answerType;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 11; // the start point in the data pool
		endpoint = 11; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "Question display text";
		logMsg[2] = "Answer type";
		logMsg[3] = "Result";
	}
	
	/**
	 * Parse the acceptable anwser.
	 * @return
	 */
	public AcceptableAnswers[] getAcceptableAnswers(){
	  String [] anwser = StringUtil.splitByComma(dpIter.dpString("acceptAnwsers"));
	  
	  String [] defaultValue = StringUtil.splitByComma(dpIter.dpString("default"));
	  String [] displayOrder = StringUtil.splitByComma(dpIter.dpString("displayOrder"));
	  String [] nextAction = StringUtil.splitByComma(dpIter.dpString("nextAction"));
	  String [] nextActionValue = this.splitTextBySemicolon(dpIter.dpString("nextActionValue"));
	  AcceptableAnswers[] anwserList = new AcceptableAnswers[anwser.length];
	  if(anwser.length!=defaultValue.length ||anwser.length != displayOrder.length 
			  || anwser.length != nextAction.length || defaultValue.length != displayOrder.length || 
			  defaultValue.length != nextAction.length|| displayOrder.length != nextAction.length){
		  throw new ErrorOnDataException("The value shoule be have same length");
	  }
	  for(int i = 0;i< anwser.length;i++){
		  anwserList[i]= question. new AcceptableAnswers();
		  anwserList[i].answer = anwser[i];
		  anwserList[i].defaultSelection = Boolean.parseBoolean(defaultValue[i]);
		  anwserList[i].dispalyOrder = displayOrder[i];
		  anwserList[i].nextAction = nextAction[i];
		  if((!anwserList[i].nextAction.equals("None") && !anwserList[i].nextAction.equals("Prompt for a Text Input")) && !anwserList[i].nextAction.equals("Skip Remaining Questions")) {
			  String [] actionValues = StringUtil.splitByComma(nextActionValue[i]);
			  anwserList[i].actionValue = new String[actionValues.length];
			  for(int j = 0;j < actionValues.length;j++){
				  anwserList[i].actionValue[j] = actionValues[j];
				  if(anwserList[i].actionValue[j].equals("")){
					  anwserList[i].actionValue[j] = "Please select a Privilege";
				  }
			  }
		  }
	  }
	  return anwserList;
	}
	
//	/**
//	 * split the text by comma
//	 * @param text - text need to comma
//	 * @return String[] - the text list split by comma
//	 */
//	private String[] splitTextByComma(String text){
//		String[] list = new String[]{};
//		if(text.contains(",")){
//			list =  text.split(",", -1);
//		}else if(!text.equals("")){
//			list = new String[]{text};
//		}
//		return list;
//	}
	
	private String[] splitTextBySemicolon(String text){
		String[] list = new String[]{};
		if(text.contains(";")){
			list =  text.split(";", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}

	private void verifyResult(){
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget.getInstance();
		if(!prodConfPg.exists()){
			logger.error("Add question: question display text ="+question.questDisplayText+",question answer type ="+question.answerType+questionPg.getErrorMessage());
			logMsg[3] = "Failed";
		}else{
			logMsg[3] = "Passed";
		}
	}
}
