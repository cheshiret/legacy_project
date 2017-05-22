package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignQuestionToPriFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AssignQuestionToPri extends SetupCase{
	private QuestionInfo questionInfo = new QuestionInfo();
	LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage.getInstance();
	LicMgrAddProductQuestionWidget questionWidget = LicMgrAddProductQuestionWidget.getInstance();
	private Object[] args = new Object[3];
	private AssignQuestionToPriFunction func = new AssignQuestionToPriFunction();
	
	public void executeSetup() {
		func.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_question_to_pri";
	}

	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
		questionInfo.proCode = datasFromDB.get("privilegeCode");
		questionInfo.displayOrder = datasFromDB.get("displayOrder");
		questionInfo.questDisplayText = datasFromDB.get("prompt");
		questionInfo.originalOption = datasFromDB.get("forOriginal");
		questionInfo.replacementOption = datasFromDB.get("forReplacement");
		questionInfo.transfterOption = datasFromDB.get("forTransfer");
		questionInfo.licenseYearFrom = datasFromDB.get("licenseYearFrom");
		if (questionInfo.licenseYearFrom.isEmpty()) {
			questionInfo.licenseYearFrom = "All";
		}
		questionInfo.licenseYearTo = datasFromDB.get("licenseToDate");
		questionInfo.collectionMethod = datasFromDB.get("collectionMethod");
		questionInfo.effectiveFromDate = datasFromDB.get("effectiveFromDate");
		if(questionInfo.effectiveFromDate.length()<1){
			questionInfo.effectiveFromDate = DateFunctions.getToday();
		}
		questionInfo.effectiveToDate = datasFromDB.get("effectiveToDate");
		questionInfo.locationClass = datasFromDB.get("locationClass");
		questionInfo.locationClasses = this.splitTextByComma(datasFromDB.get("locationClasses"));
		questionInfo.printDocuments = this.splitTextBySemicolon(datasFromDB.get("printQuestion"));
		questionInfo.questAnswers = this.splitTextByComma(datasFromDB.get("questionAnwsers"));
		questionInfo.dependentAnswers = this.getAnswerDepency();
		args[2] = questionInfo;
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
		String []quesDepency = this.splitTextBySemicolon(datasFromDB.get("quesDepency"));
		for(int i=0;i<quesDepency.length;i++){	
			String [] depency = this.splitTextByComma(quesDepency[i]);
			answerList.add(depency);
		}
		return answerList;
	}
}
