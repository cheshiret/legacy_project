package com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrEditHarvestQuesDepcyWiget extends DialogWidget{

private static LicMgrEditHarvestQuesDepcyWiget instance = null;
	
	private LicMgrEditHarvestQuesDepcyWiget(){
		super("Edit Question Dependencies");
	}
	
	public static LicMgrEditHarvestQuesDepcyWiget getInstance(){
		if(null == instance){
			instance = new LicMgrEditHarvestQuesDepcyWiget();
		}
		return instance;
			
	}
	
	/**
	 * Select the question check box.
	 * @param index - the index of check box.
	 */
	public void selectQuesion(int index){                 
		browser.selectCheckBox(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.dependentOnQuestionsForEditing_\\d+", false), index, this.getWidget()[0]);
	}
	
	/**
	 * UnSelect the question check box.
	 * @param index - the index of check box.
	 */
	public void unSelectQusion(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.dependentOnQuestionsForEditing_\\d+", false), index, this.getWidget()[0]);
	}
	
	/**
	 * Select the Answer check box.
	 * @param index - the index of check box.
	 */
	public void selectAnswers(int index){
		browser.selectCheckBox(".id", new RegularExpression("AnswerDependencyUIView-\\d+\\.displayAnswerList_\\d+",false), index,this.getWidget()[0]);
	}
	
	/**
	 * UnSelect the Answer check box.
	 * @param index - the index of check box.
	 */
	public void unSelectAnswer(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("AnswerDependencyUIView-\\d+\\.displayAnswerList_\\d+",false), index, this.getWidget()[0]);
	}
	

	/**
	 * UnSelect all the Answer and question check box.
	 * @param questionNumber - the total number of check box.
	 */
	public void unSelectQuestions(int questionNumber){
			this.unSelectAnswer(questionNumber);
			ajax.waitLoading();
			this.unSelectQusion(questionNumber);
			ajax.waitLoading();
		
	}
	
	/**
	 * Select all the Answer and question check box.
	 * @param questionNumber - the total number of check box.
	 */
	public void selectQuestions(int questionNumber){
		
		for(int i = 0;i<questionNumber;i++){
			this.selectQuesion(i);
			this.selectAnswers(i);
		}
	}
	
	/**
	 * Get the error message.
	 * @param String - the widget message.
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = objs[0].getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
public void selectQuestion(int questionNumber){
		
		for(int i = 0;i<questionNumber;i++){
			this.selectQuesion(i);
			this.selectAnswers(i);
		}
	}
	
}
