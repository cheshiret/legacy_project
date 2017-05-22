package com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions;

import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestQuestion;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * Select the question from drop down list
 * @param question - the value of question
 * @param value - the value of above property
 * @return String - the value of text
 */

public class LicMgrAddHarvestQuestionWidget extends DialogWidget{

	private static LicMgrAddHarvestQuestionWidget instance = null;
	
	private LicMgrAddHarvestQuestionWidget(){
		super("Add Question");
	};
	
	public static LicMgrAddHarvestQuestionWidget getInsatance(){
		if(null == instance){
			instance = new LicMgrAddHarvestQuestionWidget();
		}
		return instance;	
	}
	
	 /**
	 * Select the question from drop down list
	 * @param question - the value of question
	 */
	public void selectQuestionNum(String question){
		browser.selectDropdownList(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.displayOrder",false), question, false, this.getWidget()[0]);
	}
	
	 /**
	 * Set the display text
	 * @param displayText - the  of display text
	 */
	public void setDisplayText(String displayText){
		browser.setTextArea(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.displayText",false), displayText, true, 0, this.getWidget()[0]);
	}
	
	 /**
	 * Set the print text
	 * @param printText - the  of print text
	 */
	public void setPrintText(String printText){
        browser.setTextField(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.printText",false), printText,this.getWidget()[0]);		
	}
	
	public void setAcceptableAnswers(String acceptAnswer,int index){
		browser.setTextField(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.answer",false), acceptAnswer,index);
	}
	
	public void selectAnwserType(String anwserType, int index){
		browser.selectDropdownList(Property.toPropertyArray(".id",new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.questionType",false)), anwserType, index);
	}
	
	public void setMinLength(String minLength, int index){
		browser.setTextField(Property.toPropertyArray(".id",new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.minValue",false)), minLength, true, index);
	}
	
	public void setMaxLength(String maxLength, int index){
		browser.setTextField(Property.toPropertyArray(".id",new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.maxValue",false)), maxLength, true, index);
	}
	
	/**
	 * Set the display order
	 * @param disPlayOrder - the  of display order
	 * @param index - the  index of display order
	 */
	public void setDisplayOrder(String disPlayOrder,int index){	
      browser.setTextField(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.displayOrder:ZERO_TO_NULL",false), disPlayOrder, false, index, this.getWidget()[0]);
	}
	
	/**
	 * Click the remove button
	 * @param question - the  of display order
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
	}
	
	/**
	 * Select the default of check box
	 * @param index - the  index of check box
	 */
	public void selectDefault(int index){	
		browser.selectCheckBox(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.defaultChoice",false), index, this.getWidget()[0]);
	}
	
	/**
	 * Unselect the default of check box
	 * @param index - the  index of check box
	 */
	public void unselectDefault(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.defaultChoice",false), index);
	}
	
	/**
	 * Click the add answer button
	 */
	public void clickAdd(){
		IHtmlObject[] widget=getWidget();
		Property[] p=new Property[2];
		p[0]=new Property(".class","Html.A");
		p[1]=new Property(".text",new RegularExpression("Add", false));
		
		browser.clickGuiObject(p, true, 0, widget[0]);
		Browser.unregister(widget);
	}
	
	/**
	 * Remove the item of accpect answer and display order
	 * @param answser - the array of accept answer
	 */
	public void removeAnswers(String[] answser){
		for(int i = 0; i<answser.length;i++){
			this.clickRemove(i);
		}
	}
	
	/**
	 * Get the error add widget error message
	 * @return String - the error message.
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = objs[0].getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
	
	/**
	 * Set the question info
	 * @param harvestQuestion - the info content of question.
	 */
	public void setHarvestQuestionInfo(HarvestQuestion harvestQuestion){
		this.selectQuestionNum(harvestQuestion.questionNumber);
		this.setDisplayText(harvestQuestion.displayText);
		this.setPrintText(harvestQuestion.printText);
		for(int i=0;i<harvestQuestion.harvestAnwsers.size();i++){
			if(StringUtil.notEmpty(harvestQuestion.harvestAnwsers.get(i).answerType)){
				this.selectAnwserType(harvestQuestion.harvestAnwsers.get(i).answerType, i);
			}
			
			if(StringUtil.notEmpty(harvestQuestion.harvestAnwsers.get(i).minLength)){
				this.setMinLength(harvestQuestion.harvestAnwsers.get(i).minLength, i);
			}
			
			if(StringUtil.notEmpty(harvestQuestion.harvestAnwsers.get(i).maxLength)){
				this.setMaxLength(harvestQuestion.harvestAnwsers.get(i).maxLength, i);
			}
			if(StringUtil.notEmpty(harvestQuestion.harvestAnwsers.get(i).acceptableAnswer)){
				this.setAcceptableAnswers(
						harvestQuestion.harvestAnwsers.get(i).acceptableAnswer, i);
			}
			if(StringUtil.notEmpty(harvestQuestion.harvestAnwsers.get(i).displayOrder)){
				this.setDisplayOrder(
						harvestQuestion.harvestAnwsers.get(i).displayOrder, i);
			}
			this.selectDefault(i);
			ajax.waitLoading();
			if(i<harvestQuestion.harvestAnwsers.size() -1)
			{
				this.clickAdd();
				ajax.waitLoading();
			}
		}
		
		this.removeAnwsers(harvestQuestion);	
	}
	
	/**
	 * Remove the Answers
	 * @param harvestQuestion - the info content of question.
	 */
	public void removeAnwsers(HarvestQuestion harvestQuestion){
		for(int i=harvestQuestion.harvestAnwsers.size()-1;i>0;i--)
		{
			this.clickRemove(i);
			ajax.waitLoading();		
		}	
	}

}
