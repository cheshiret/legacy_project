/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies.questions.view;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyQuestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This script is used to verify UI Requirements in SPEC by verify search feature
 * @Preconditions: Make sure proper products are exist first
 * @SPEC:<View Product Question List.doc>
 * @Task#:AUTO-705
 * 
 * @author ssong
 * @Date  Aug 19, 2011
 */
public class VerifyFiltersBySearch extends LicenseManagerTestCase{

	private LicMgrSupplyQuestionPage questionPg = LicMgrSupplyQuestionPage.getInstance();
	private String supplyCd = "";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoSupplyQuestionPgFromTopMenu(supplyCd);
		
		//clean up
		lm.deactiveAllActiveSupplyQuestions();
				
		question.id = lm.addQuestionsForProduct(question).get(question.locationClass);
		
		//remove initial filter to show all
		questionPg.showAllRecords();
		this.checkGivenQuestionDisplayed();
		
		//search question by status,display text and location class
		question.status = "Active";
		questionPg.searchQuestios(question.status, question.questDisplayText, question.locationClass);
		checkGivenQuestionDisplayed();
		questionPg.verifySearchResultMatchCriteria("Status", question.status);
		questionPg.verifySearchResultMatchCriteria("Display Text", question.questDisplayText);
		questionPg.verifySearchResultMatchCriteria("Location Class", question.locationClass);
		
		//search question by status and location class
		questionPg.searchQuestios(question.status, "", question.locationClass);
		checkGivenQuestionDisplayed();
		questionPg.verifySearchResultMatchCriteria("Status", question.status);
		questionPg.verifySearchResultMatchCriteria("Location Class", question.locationClass);
		
		//search question by active status
		questionPg.searchQuestios(question.status, "", "");
		checkGivenQuestionDisplayed();
		questionPg.verifySearchResultMatchCriteria("Status", question.status);
		
		//search question by display text
		questionPg.searchQuestios("", question.questDisplayText, "");
		checkGivenQuestionDisplayed();
		questionPg.verifySearchResultMatchCriteria("Display Text", question.questDisplayText);
		
		//search question by location class
		questionPg.searchQuestios("", "", question.locationClass);
		checkGivenQuestionDisplayed();
		questionPg.verifySearchResultMatchCriteria("Location Class", question.locationClass);
		
		lm.deactivateProductQuestionAssignment(question.id);
		//search question by inactive status
		question.status = "Inactive";
		questionPg.searchQuestios(question.status, "", "");
		questionPg.verifySearchResultMatchCriteria("Status", question.status);
		
		lm.logOutLicenseManager();
	}
	
	private void checkGivenQuestionDisplayed(){
		if(!questionPg.questionAssignmentIDExists(question.id)){
			throw new ErrorOnPageException("Given Question "+question.id+" Should Displayed.");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplyCd = "a06";
		
		question.displayOrder = "1";
		question.questDisplayText = "Did you test question(auto test)?";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear()+6);
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear()+6);
		question.effectiveFromDate = DateFunctions.getToday();
		question.effectiveToDate = DateFunctions.getDateAfterToday(2);
		question.locationClass = "06-State Parks Agent";
		question.questAnswers = new String[2];
		question.questAnswers[0] = "Yes";
		question.questAnswers[1] = "No";
	}

}
