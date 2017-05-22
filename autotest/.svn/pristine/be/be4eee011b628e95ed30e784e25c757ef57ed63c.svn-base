/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestQuestion;
import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestReportQuestionnaires;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jun 16, 2011
 */
public class LicMgrHarvestQuestionsDetailsPage extends LicMgrProductCommonPage {

	private static LicMgrHarvestQuestionsDetailsPage instance=null;
	
	private LicMgrHarvestQuestionsDetailsPage(){}
	
	private boolean pass = true;
	
	public static LicMgrHarvestQuestionsDetailsPage getInstance(){
		if(instance==null){
			instance=new LicMgrHarvestQuestionsDetailsPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","editHarvestQuestionaire-container");
	}
	
	public String getId(){
		String id="";
		RegularExpression regx=new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.id",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", regx);
	    if(objs.length<0){
	    	throw new ObjectNotFoundException("Cann't find the ID DIV");
	    }
		id=objs[0].getProperty(".text").replaceAll("ID", "");
		Browser.unregister(objs);
		return id;
	}
	
	public String getSpecies(){
		RegularExpression regx=new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.speciesName",false);		
		return this.getSpanValue("Species", regx);
	}
	
	public String getSeason(){
		RegularExpression regx=new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.seasonName",false);		
		return this.getSpanValue("Season", regx);
	}
	
	public String getCreateUser(){
		RegularExpression regx=new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.createUser",false);
		return this.getSpanValue("Create User", regx);
	}
	
	public String getCreateLocation(){
		RegularExpression regx=new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.createLocation",false);
	    return this.getSpanValue("Create Location", regx);
	}
	public String getEffectiveDate(){
		return browser.getTextFieldValue(".id", new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.effectiveDate_ForDisplay",false));
	}
	
	public String getPrintText(){
		return browser.getTextFieldValue(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.printText",false));
	}
	
	public String getAcceptAnswer(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.answer",false),index);
	}
	
	public String getDisOder(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.displayOrder",false),index);
	}
	
	public String getDisplayText(){
		return browser.getTextAreaValue(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.displayText",false));
	}
	
	public String getDisplayText(int index){
		IHtmlObject[] objs = browser.getTextArea(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.displayText",false));
		if(objs.length<index +1){
			throw new ItemNotFoundException("Did not get any display text object.");
		}
		
		String text = objs[index].text();
		Browser.unregister(objs);
		return text;
	}
	
	public String getCreateDateTime(){
		RegularExpression regx=new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.createDateTime",false);
		return this.getSpanValue("Create Date/Time", regx);
	}
	private String getSpanValue(String labelName,RegularExpression regx){
		String value="";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.Span", ".id", regx);
	    if(objs.length<0){
	    	throw new ObjectNotFoundException("Cann't find the Span for " + labelName);
	    } 
	    value=objs[0].getProperty(".text").replaceAll(labelName, "");
		Browser.unregister(objs); 
		return value;
	}
	

	public boolean isRemoveThisQuestionExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Remove This Question");
	}
	
	/**
	 * Get the default check box value.
	 * @param index - the index of check box.
	 * @return- the value of check box.
	 */
	public boolean getDefaultChckbox(int index){
		
		IHtmlObject[] top = browser.getTableTestObject(".id", "HarvestQuestionAssignmentWhat");
		Property[] preperty = new Property[1];
		preperty[0] = new Property(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.defaultChoice",false));
		IHtmlObject[] objCheckbox = browser.getCheckBox(preperty, top[0]);
		ICheckBox checkBox = (ICheckBox)objCheckbox[index];
		boolean isChecked = checkBox.isSelected();
		Browser.unregister(objCheckbox);
		Browser.unregister(top);
		return isChecked;
	}
	
	/**
	 * Get the row value
	 * @param col - the col.
	 * @param value - the value of cell.
	 * @return the value of row.
	 */
	public List<String> getHarvestReportTableRowValue(int col,Object value){
		
		List<String> rowValue =null;
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",".id","editHarvestQuestionaire-container");
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find the Havest question report table...");
		}
		IHtmlTable tableObject = (IHtmlTable)objs[0];
		int row = tableObject.findRow(col, value);
		if(row > -1){
		    rowValue = tableObject.getRowValues(row);
		}else{
			rowValue = new ArrayList<String>();
		}
		return rowValue;
	}



	/**
	 * Set the effective date..
	 * @param effectiveDate - the date value.
	 */
	public void setEffectiveDate(String effectiveDate){	
		browser.setTextField(".id", new RegularExpression("HarvestReportQuestionaireDetailView-\\d+\\.effectiveDate_ForDisplay",false), effectiveDate);
	}
	
	/**
	 * Set the  Display Text..
	 * @param effectiveDate - the text value.
	 * @param index - the display text input box index.
	 */
	public void setDisplayText(String disPlayText,int index){
		browser.setTextField(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.displayText",false), disPlayText, index);
	}
	
	public void setDisplayText(String disPlayText){
		browser.setTextArea(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.displayText",false), disPlayText);
	}
	/**
	 * Set the Print Text.
	 * @param printText - the text value.
	 * @param index - the print text input box index.
	 */
	public void setPrintText(String printText,int index){
		browser.setTextField(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.printText",false), printText,index);
	}
	
	public void setPrintText(String printText){
		browser.setTextField(".id", new RegularExpression("HarvestQuestionAssignmentView-\\d+\\.printText",false), printText);
	}
	
	/**
	 * Set the Acceptable Answers.
	 * @param acceptAnswer - the text value.
	 * @param index - the print text input box index.
	 */
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
	 * Set the display Order.
	 * @param displayOrder - the text value.
	 * @param index - the print text input box index.
	 */
	public void setDisplayOrder(String displayOrder,int index){
		browser.setTextField(".id",new RegularExpression("AcceptableAnswerView-\\d+\\.displayOrder",false),displayOrder,index);
	}
	/**
	 * Select the default.
	 * @param index - the default check box index.
	 */
	public void selectDefault(int index){
		browser.selectCheckBox(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.defaultChoice",false),index);
	}
	
	
	/**
	 * Unselect the default.
	 * @param index - the default check box index.
	 */
	public void unSelectDefault(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("AcceptableAnswerView-\\d+\\.defaultChoice",false),index);
	}
	
	/**
	 * Click the add harvest question button.
	 */
	public void clickAddHarvestQuestion(){	
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Harvest Question");
	}
	
	/**
	 * Click the remove harvest question button.
	 * @param index- the index of remove harvest question button.
	 */
	public void clickRemoveQuestion(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove This Question",index);
	}
    
	/**
	 * Click add button for the acceptable answer and display order.
	 * @param index- the index of add button .
	 */
	public void clickAdd(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add",index);
	}
	
	/**
	 * Click remove button for the acceptable answer and display order.
	 * @param index- the index of remove button .
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove",index);
	}
	
	/**
	 * Click ok.
	 */
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * Click cancel.
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**
	 * Click Add question dependencies button.
	 * @param index-the index of add question dependencies button.
	 */
	public void clickAddQustDependencies(int index){	
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Question Dependencies",index);
	}
	
 
	/**
	 * Set the acceptable answer & default & display order value
	 * @param index-the index
	 * @param acceptableText - the text value of acceptableText.
	 * @param isDefault - the isDefault value.
	 * @param displayOrder - the value of displayOrder
	 */
	
	public void removeAnwsers(HarvestQuestion harvestQuestion){
		for(int i=harvestQuestion.harvestAnwsers.size()-1;i>0;i--)
		{
			this.clickRemove(i);
			ajax.waitLoading();		
		}
	}

	/**
	 * Edit the harvest question
	 * @param harvestQuestion-the question info.
	 */
	public void editHarvestQuesion(HarvestQuestion harvestQuestion) {
		this.setDisplayText(harvestQuestion.displayText);
		this.setPrintText(harvestQuestion.printText);
		for (int i = 0; i < harvestQuestion.harvestAnwsers.size(); i++) {
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
			
			if (harvestQuestion.harvestAnwsers.get(i).isDefault) {
				this.selectDefault(i);
			}
			if(i<harvestQuestion.harvestAnwsers.size()-1){
				this.clickAdd(i);
			}
		}
			
		this.removeAnwsers(harvestQuestion);
	}
	
	
	/**
	 * Remove the acceptable answer & default & display order value
	 * @param index - the index of remove harvest question button.
	 */
	public void removeHarvestQuestions(int index){
			this.clickRemoveQuestion(index);
	}
	
	
	/**
	 * Click edit question dependencies.
	 * @param index - the index of add the edit question dependencies.
	 */
	public void clickEditQuesionDePendencies(int index){
       browser.clickGuiObject(".class", "Html.A", ".text", "Edit Question Dependencies",index);
	}
	
	/**
	 * Get the dependencies check box 
	 * @return - the value of check box.
	 */
	public boolean getAddDependenciesCheckBox(){
		IBrowser browser = Browser.getInstance();
		IHtmlObject[] object = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression(
						"^Remove This Question ?Edit Question Dependencies ?1$",
						false));
		Property[] property = new Property[1];
		property[0] = new Property(".class","Html.INPUT.checkbox");
		boolean isChecked = browser.isCheckBoxSelected(property, object[0]);
		Browser.unregister(object);
		return isChecked;
	}
	
	public boolean getEditDependenciesCheckBox(){
		
		IBrowser browser = Browser.getInstance();
		IHtmlObject[] object = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression(
						"^Remove This Question ?Add Question Dependencies ?1$",
						false));
		Property[] property = new Property[1];
		property[0] = new Property(".class","Html.INPUT.checkbox");
		boolean isChecked = browser.isCheckBoxSelected(property, object[0]);
		Browser.unregister(object);
		return isChecked;
	}

	/**
	 * Verify the harvest Answers.
	 */
	private void VerifyHarvestAnwser(HarvestQuestion harvestQuestion) {
		for (int i = 0; i < harvestQuestion.harvestAnwsers.size(); i++) {
			if (!this.getAcceptAnswer(i).equals(
					harvestQuestion.harvestAnwsers.get(i).acceptableAnswer)) {
				pass &= false;
				logger
						.error("Acceptable Answer "
								+ harvestQuestion.harvestAnwsers.get(i).acceptableAnswer
								+ " display error");
			}

			if (!this.getDisOder(i).equals(
					harvestQuestion.harvestAnwsers.get(i).displayOrder)) {
				pass &= false;
				logger.error("Display Order "
						+ harvestQuestion.harvestAnwsers.get(i).displayOrder
						+ " display error");
			}
			if (this.getDefaultChckbox(i) != harvestQuestion.harvestAnwsers
					.get(i).isDefault) {
				pass &= false;
				logger.error("Default "
						+ harvestQuestion.harvestAnwsers.get(i).isDefault
						+ " display error");
			}
		}

	}
	/**
	 * Verify the harvest questions.
	 */
	public void verifyHarvestQuestionnaire(
			HarvestReportQuestionnaires harvestQuestnna) {
		
		if(0!=DateFunctions.compareDates(DateFunctions.formatDate(this.getEffectiveDate(), "E MMM dd yyyy"), DateFunctions.formatDate(harvestQuestnna.effectiveDate,
		"E MMM dd yyyy"))) {
			pass &= false;
			logger.error("Effective Date " + harvestQuestnna.effectiveDate
					+ " display error");
		}

		for (int i = 0; i < harvestQuestnna.harvestQuestions.size(); i++) {
			if (!(this.getDisplayText().trim()
					.equals(harvestQuestnna.harvestQuestions.get(i).displayText
							.trim()))) {
				 pass &= false;
				 logger.error("Display text "+harvestQuestnna.harvestQuestions.get(i).displayText+" display error");
			}

			if (!this.getPrintText().trim().equals(
					harvestQuestnna.harvestQuestions.get(i).printText.trim())) {
				pass &= false;
				logger.error("Print text "
						+ harvestQuestnna.harvestQuestions.get(i).printText
						+ " display error");
			}
			this.VerifyHarvestAnwser(harvestQuestnna.harvestQuestions.get(i));
		}
	}

	/**
	 * Verify the remove questions.
	 */
	public void verifyRemoveQuestionSuccessful(
			HarvestReportQuestionnaires havQuestReport) {
		for (int i = 0; i < havQuestReport.harvestQuestions.size(); i++) {
			if (this.getDisplayText(i).equals(
					havQuestReport.harvestQuestions.get(i).displayText)) {
				throw new ErrorOnDataException("Harvest question remove failed");
			}
		}

	}

	/**
	 * Verify the effective data questions.
	 */
	public void verifyAddQuesDepencySuccess(boolean expectResult) {
		// The quesionNumber for verify the number of question.
		boolean isChecked = this.getAddDependenciesCheckBox();
		if (isChecked != expectResult) {
			throw new ErrorOnDataException("Add quesstion depencies error");
		}

	}

	// Verify the Edit dependency result
	public void verifyEditQuesDepencySuccess(boolean expectResult) {

		boolean isChecked = this.getEditDependenciesCheckBox();
		if (isChecked !=expectResult) {
			throw new ErrorOnDataException("Edit quesstion depencies error");
		}
	}

	public void verifyEditEffectiveDateSuccessful(
			HarvestReportQuestionnaires harvestReport) {
		if(!DateFunctions.formatDate(this.getEffectiveDate(), "E MMM dd yyyy").equals(DateFunctions.formatDate(harvestReport.effectiveDate,"E MMM dd yyyy"))) {
			throw new ErrorOnDataException("Effeictive Edit updated failed. Expected: \""+harvestReport.effectiveDate+"\", actual: \""+getEffectiveDate()+"\"");
		}
	}
}
	

