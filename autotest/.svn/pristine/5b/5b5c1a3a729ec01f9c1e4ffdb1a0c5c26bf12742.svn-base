package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 20, 2011
 */
public class LicMgrProductQuestionsConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrProductQuestionsConfigurationPage _instance = null;
	
	protected LicMgrProductQuestionsConfigurationPage() {}
	
	public static LicMgrProductQuestionsConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrProductQuestionsConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "product_question");
	}
	
	public List<String> getQuestionInfo(String identifier) {
		return this.getRowInfo("product_question", identifier, "Display Text");
	}
	
	public boolean verifyNewQuestionInfo(QuestionInfo question) {
		boolean result = true;

		List<String> text = this.getQuestionInfo(question.questDisplayText);
		if(!text.get(0).equals(question.questDisplayText)){
			logger.error("The expected display text is not " + question.questDisplayText + ".");
			result &= false;
		}
        if(!text.get(1).equals(question.questPrintText)){
        	logger.error("The expected print text is not " + question.questPrintText + ".");
			result &= false;
		}
        if(!text.get(2).equals(question.answerType)){
        	logger.error("The expected answer type is not " + question.answerType + ".");
			result &= false;
		}
        if(!text.get(3).equals(question.hipQuestion)){
        	logger.error("The expected hip question is not " + question.hipQuestion + ".");
			result &= false;
		}
        
		return result;
	}
}
