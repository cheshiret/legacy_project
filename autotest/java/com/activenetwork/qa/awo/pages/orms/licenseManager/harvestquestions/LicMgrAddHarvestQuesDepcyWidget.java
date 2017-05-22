package com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddHarvestQuesDepcyWidget extends DialogWidget{

	private static LicMgrAddHarvestQuesDepcyWidget instance = null;
	
	private LicMgrAddHarvestQuesDepcyWidget(){
		super("Add Question Dependencies");
	}
	
	public static LicMgrAddHarvestQuesDepcyWidget getInstance(){
		if(null == instance){
			instance = new LicMgrAddHarvestQuesDepcyWidget();
		}
		return instance;
	}
	

	/*public boolean exists(){
		System.out.println("check");
		return browser.checkHtmlObjectExists(".text","Add Question Dependencies",".id","ui-dialog-title-Dialog001");
	}*/
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
		for(int i = 0;i<questionNumber;i++){
			this.unSelectQusion(i);
			this.unSelectAnswer(i);
		}
	}
	
	/**
	 * Select all the Answer and question check box.
	 * @param questionNumber - the total number of check box.
	 */
	public void selectQuestion(int questionNumber){
			this.selectQuesion(questionNumber);
			ajax.waitLoading();
			this.selectAnswers(questionNumber);
			ajax.waitLoading();
		
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
	
}

   
