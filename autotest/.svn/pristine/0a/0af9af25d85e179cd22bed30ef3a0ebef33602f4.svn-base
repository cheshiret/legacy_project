package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Feb 29, 2012
 */
public class LicMgrCustomerHarvestQuestionWidget extends DialogWidget {
	private static LicMgrCustomerHarvestQuestionWidget _instance = null;
	
	private LicMgrCustomerHarvestQuestionWidget() {
		super("Question");
	}
	
	public static LicMgrCustomerHarvestQuestionWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrCustomerHarvestQuestionWidget();
		}
		
		return _instance;
	}
	
	public void clickPreviousQuestion() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Previous Question");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickNextQuestion() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next Question");
	}
	
	public boolean checkDoneExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Done");
	}
	
	public void clickDone() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Done");
	}
	
	/**
	 * Set date of kill value
	 * @param date
	 */
	public void setDateOfKill(String date) {
		browser.setCalendarField(".id", new RegularExpression("SurveyQuestion-\\d+\\.answerDateTime_ForDisplay", false), date, true);
	}
	
	/**
	 * Select radio button of corresponding answer
	 * @param question
	 * @param answer
	 */
	public void selectAnswer(String question, String answer) {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^" + question, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("The question displayed is different from expected question: " + question);
		}
		
		IHtmlObject trs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^" + answer, false));
		if(trs.length < 1) {
			throw new ItemNotFoundException("Can't find TR object by answer: " + answer);
		}
		
		browser.selectRadioButton(Property.toPropertyArray(".id", new RegularExpression("SurveyQuestion-\\d+\\.singleSelectedAnswer", false)), true, 0, trs[0]);
		Browser.unregister(objs);
		Browser.unregister(trs);
	}
	
	/**
	 * Set up harvest report detail info
	 * @param harvest
	 */
	public void setupInfo(Harvest harvest) {
		this.setDateOfKill(harvest.dateOfKill);
		//TODO
		this.clickNextQuestion();
		ajax.waitLoading();
		for(Map.Entry<String, String> entry : harvest.questionAnswers.entrySet()) {
			this.selectAnswer(entry.getKey(), entry.getValue());
			ajax.waitLoading();
			if(!this.checkDoneExists()) {
				this.clickNextQuestion();
			}
			ajax.waitLoading();
		}
	}
}
