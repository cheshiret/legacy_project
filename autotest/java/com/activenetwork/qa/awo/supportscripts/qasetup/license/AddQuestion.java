package com.activenetwork.qa.awo.supportscripts.qasetup.license;
/**  
 * @Description:  
 * @Preconditions:  Insert the nextActionValue like "RES SPORTSMAN,HIP;FMAP,HIP"
 *                  Insert the nextAction and acceptAnwsers and default and displayOrder like: "Stop the Transaction,Add Privileges"
 * @SPEC:  
 * @Task#: 
 * @author jwang8  
 * @Date  Mar 16, 2012    
 */

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo.AcceptableAnswers;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddQuestionFunction;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddQuestion extends SetupCase{
	private QuestionInfo question = new QuestionInfo();
	private Object[] args = new Object[3];
	private AddQuestionFunction func = new AddQuestionFunction();
	
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_question";
	}
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("loc");
		
		question.questDisplayText = datasFromDB.get("displayText");
		question.questPrintText = datasFromDB.get("printText");
		question.answerType = datasFromDB.get("AnswerType");
		question.minLength = datasFromDB.get("minLenth");
		question.maxLength = datasFromDB.get("maxLength");
		question.anwsers = this.getAcceptableAnswers();
		args[2] = question;
	}
	
	/**
	 * Parse the acceptable anwser.
	 * @return
	 */
	public AcceptableAnswers[] getAcceptableAnswers(){
	  String [] anwser = StringUtil.splitByComma(datasFromDB.get("acceptAnwsers"));
	  
	  String [] defaultValue = StringUtil.splitByComma(datasFromDB.get("default value"));
	  String [] displayOrder = StringUtil.splitByComma(datasFromDB.get("displayOrder"));
	  String [] nextAction = StringUtil.splitByComma(datasFromDB.get("nextAction"));
	  String [] nextActionValue = this.splitTextBySemicolon(datasFromDB.get("nextActionValue"));
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

	private String[] splitTextBySemicolon(String text){
		String[] list = new String[]{};
		if(text.contains(";")){
			list =  text.split(";", 0);
		}else {
			list = new String[]{text};
		}
		return list;
	}
}
